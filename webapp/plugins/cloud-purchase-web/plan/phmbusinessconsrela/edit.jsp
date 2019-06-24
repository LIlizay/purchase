<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="editBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<div class="row row-margin-bottom" slot="top" >
		<mk-form-panel title="用户电量收集">
			<mk-form-row>
				<!-- 制定人 -->
		        <mk-form-col :label="formFields.setters.label" >
		        	<su-textbox :data.sync="setters" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 年份 -->
		        <mk-form-col :label="formFields.year.label" required_icon="true">
		        	<su-datepicker :data.sync="year" format="YYYY" disabled></su-datepicker>
		        </mk-form-col>
		   	</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="近三年长协电价">
			<mk-form-row>
				<!-- 长协电价 -->
		        <mk-form-col :label="year3" >
		        	<su-textbox :data.sync="priceData.year3" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 长协电价 -->
		        <mk-form-col :label="year2" >
		        	<su-textbox :data.sync="priceData.year2" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 长协电价 -->
		        <mk-form-col :label="year1" >
		        	<su-textbox :data.sync="priceData.year1" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		   	</mk-form-row>
		</mk-form-panel>
	</div>
	<mk-panel title="购电用户列表" slot="bottom" height="100%" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="eye" v-on:click="">负荷预测</su-button>
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
		setters:'',
		first:true, //表格第一次加载标志
		dataMap:{},
		total:0,
		nowPage:1,  //记录当前是第几页
		planId:'',
		year:'',
		year1:'',
		year2:'',
		year3:'',
		priceData:{
			year1:'',
			year2:'',
			year3:'',
		}
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('edit');
		me.setters = obj.setters;
		var year = parseInt(obj.year);
		me.year = year;
		me.year3 = year-3+"年长协电价";
		me.year2 = year-2+"年长协电价";
		me.year1 = year-1+"年长协电价";
		me.planId = obj.planId;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'edit');
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.purchasingpower",$("#editGrid"),{
			data:[],
		    height:"100%",
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    columnsReplace:[
		                    {field:'forecastAgrePq',title:'预测合同用电量</br>（兆瓦时）',editor:{type:'numberbox',options:{precision:2,min:0,}},width:100},
		                    {field:'forecastAgrePrc',title:'预估合同电价</br>（元/兆瓦时)',editor:{type:'numberbox',options:{precision:2,min:0,}},width:100},
		                    {field:'actPq1',title:(year-3)+'年用电量</br>（兆瓦时）',align:'right',width:120},
		                    {field:'actPq2',title:(year-2)+'年用电量</br>（兆瓦时）',align:'right',width:120},
		                    {field:'actPq3',title:(year-1)+'年用电量</br>（兆瓦时）',align:'right',width:120},
		                    {field:'hisProxyPq1',title:(year-3)+'年合同电价</br>（元/兆瓦时)',align:'right',width:120},
		                    {field:'hisProxyPq2',title:(year-2)+'年合同电价</br>（元/兆瓦时)',align:'right',width:120},
		                    {field:'hisProxyPq3',title:(year-1)+'年合同电价</br>（元/兆瓦时)',align:'right',width:120},
		                    ],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(data){
    			if(me.first){
    				$("#editGrid").datagrid("loading");
    				me.first = false;
    				$('#editGrid').datagrid("getPager").pagination({
    					 onRefresh:function(pageNumber,pageSize){},
	       		   		 onSelectPage:function(pageNumber,pageSize){
	       		   			 $("#editGrid").datagrid("loading");
	       		   			 me.saveEdit(me.nowPage);
	       		   			 me.nowPage = pageNumber;
	       		   			 $("#editGrid").datagrid('options').pageNumber =pageNumber;
	       		   			 $("#editGrid").datagrid('options').pageSize =pageSize;
	       		   			 me.getDate(pageNumber,pageSize,function(rows){
	       					 	$("#editGrid").datagrid('loadData',{total:me.total,rows:rows});
	       					 	$("#editGrid").datagrid("loaded");
	       		   			 })
	       		   		 }
       	  	 		});
    				me.getDate(1,10,function(rows){
    					$("#editGrid").datagrid('loadData',{total:me.total,rows:rows});
    					$("#editGrid").datagrid("loaded");
    				});
    			}
    			me.beginEdit(data.rows.length);
     		}
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
		$.ajax({
				url:basePath + 'cloud-purchase-web/phfLcDealInfoController/getPhfLcDealInfoByYear/'+year,
				type:'get',
	 			success:function(data){
	 				if(data.flag){
	 					for(var i in data.rows){
	 						var obj = data.rows[i];
	 						var number = year - obj.year;
	 						me.priceData['year'+number] = obj.dealPrc;
	 					}
	 				}else{
	 					MainFrameUtil.alert({title:"失败",body:"查询长协平均价失败！"});
	 				}
	 			}
			})
	},
	methods: {
		getDate:function(pageNumber,pageSize,backFun){
			var me = this;
			var number = pageSize/10;
			var rows = [];
			for(var i=0 ; i<number ; i++){
				if(typeof(this.dataMap[pageNumber+i])==='undefined'){
					$.ajax({
						url:basePath+"cloud-purchase-web/phmBusinessConsRelaController/purchaingPowerPage",
			 			type:'post',
			 			data:$.toJSON({year:me.year,page:pageNumber+i,rows:(pageSize-10*i),phmBusinessConsRela:{planId:me.planId}}),
			 			contentType : 'application/json;charset=UTF-8',
			 			success:function(data){
			 				me.total = data.total;
			 				for(var j=i;j<number;j++){
			 					var dataRows = data.rows.splice(0,10);
			 					if(typeof(me.dataMap[pageNumber+i])==='undefined'){
			 						me.dataMap[pageNumber+i] = dataRows;
			 					}
			 					rows = rows.concat(me.dataMap[pageNumber+i]);
			 				}
			 				backFun(rows);
			 			}
					})
					return;
				}else{
					rows = rows.concat(me.dataMap[pageNumber+i]);
				}
			}
			backFun(rows);
		},
		saveEdit:function(pageNumber){
			var rows = $("#editGrid").datagrid("getRows");
			for(var i =0;i<rows.length;i++){
				$("#editGrid").datagrid("endEdit",i);
			}
			rows = $("#editGrid").datagrid("getRows");
			this.dataMap[pageNumber] = rows;
		},
		beginEdit:function(length){
			for(var i=0;i<length;i++){
 				$("#editGrid").datagrid('beginEdit',i);
 			}
		},
		save:function(backFun){
			var me = this;
			me.saveEdit(me.nowPage);
			var rows = [];
			for(var i in me.dataMap){
				rows = rows.concat(me.dataMap[i]);
			}
			$.ajax({
				url:basePath+"cloud-purchase-web/phmBusinessConsRelaController/saveList",
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
		}
	}
}); 
</script>
</body>
</html>