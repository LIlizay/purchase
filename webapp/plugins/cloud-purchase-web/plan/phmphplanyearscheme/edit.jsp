<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-年度购电管理双边电量分月电量分解</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="editBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<div class="row row-margin-bottom" slot="top" >
		<mk-form-panel title="">
			<mk-form-row>
				<!-- 长协电量 -->
		        <mk-form-col :label="formFields.lcPqForm.label" >
		        	<su-textbox :data.sync="params.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 合同总电量 -->
		        <mk-form-col :label="formFields.agrePq.label" >
		        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 附加利润 -->
		        <mk-form-col :label="formFields.addProfitForm.label" v-if="flag1">
		        	<su-textbox :data.sync="params.addProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 差值利润 -->
		        <mk-form-col :label="formFields.differenceProfitForm.label" v-if="flag2">
		        	<su-textbox :data.sync="params.differenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
		        </mk-form-col>
		   	</mk-form-row>
		</mk-form-panel>
	</div>
	<mk-panel title="" slot="bottom" height="100%" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="decompose">电量分解</su-button>
  		</div>
	    <div id="editGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editVue = new Vue({
	el: '#editBody',
	data: {
		formFields:{},
		flag1:false,//控制附加利润
		flag2:false,//控制差值利润
		params:{
			lcPq:'',agrePq:'',dvaluePq:'',addProfit:'',differenceProfit:''
		}
	},
	ready:function(){
		var obj = MainFrameUtil.getParams('edit');
		this.params = obj;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'edit');
        if(obj.addProfit != null){
        	this.flag1 = true;
        }else if(obj.differenceProfit != null){
        	this.flag2 = true;
        }
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.phmlcdist",$("#editGrid"),{
			url:basePath + 'cloud-purchase-web/phmLcDistController/getAgreMonthPq',
			method:"get",
			queryParams:{id:obj.id},
		    height:"100%",
		    rownumbers: false,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    columnsReplace:[
		                   {field:'editLcPq',title:'双边电量分解调整（兆瓦时）',width:120,align:'right',editor:{type:'numberbox',options:{precision:4,min:0,}}}
		                    ],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(data){
    			for(var i in data.rows){
    				$("#editGrid").datagrid("beginEdit",i);
    			}
     		}
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.scheme",this);
	},
	methods: {
		ymFormatter:function(value,row,index){
			return parseInt(value.substring(4,6))+"月";
		},
		save:function(backFun){
			var rows = $("#editGrid").datagrid("getRows");
			for(var i=0 ;i<rows.length;i++){
				$("#editGrid").datagrid("endEdit",i);
			}
			rows = $("#editGrid").datagrid("getRows");
			var sum = 0;
			for(var i=0 ;i<rows.length;i++){
				if(rows[i].editLcPq != "NaN" && rows[i].editLcPq != "" && rows[i].editLcPq != null && rows[i].editLcPq != undefined && rows[i].editLcPq != "null"){
				sum += parseFloat(rows[i].editLcPq);
				}
			}
			sum = sum.toFixed(4);
			var lcPq = this.params.lcPq.toFixed(4);
			if(Math.abs(sum - lcPq) > 1){
				MainFrameUtil.alert({title:"警告",body:"双边分解总电量需小于等于双边电量"});
				for(var i=0 ;i<rows.length;i++){
					$("#editGrid").datagrid("beginEdit",i);
				}
				return ;
			}
			return;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmLcDistController/saveList",
				type:'post',
				data:$.toJSON(rows),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
	 				if(data.flag){
	 					MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
	 						backFun();
	 					}});
	 				}else{
	 					MainFrameUtil.alert({title:"失败",body:data.msg});
	 				}
	 			}
			})
		},
		decompose:function(){
			var rows = $("#editGrid").datagrid("getRows");
			for(var i=0 ;i<rows.length;i++){
				$("#editGrid").datagrid("endEdit",i);
			}
			rows = $("#editGrid").datagrid("getRows");
			var lcPq = this.params.lcPq;	//长协电量
			var sumPq = null;
			if(this.params.dvaluePq ==null||this.params.dvaluePq==''){
				sumPq = this.params.agrePq;	//合同总电量
			}else{
				sumPq = this.params.agrePq + this.params.dvaluePq;
			}
			var pq = 0;
			for(var i=0 ;i<rows.length-1;i++){
				row = rows[i];
				row.lcPq = (row.agrePq/sumPq*lcPq).toFixed(4);
				pq = pq + parseFloat(row.lcPq);
			}
			rows[rows.length-1].lcPq = (lcPq - pq).toFixed(4);
			$("#editGrid").datagrid("loadData",rows);
		}
		
	}
}); 
</script>
</body>
</html>