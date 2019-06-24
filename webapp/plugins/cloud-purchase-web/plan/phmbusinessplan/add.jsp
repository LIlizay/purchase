<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="addBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="年度购电计划新增" num="3">
		<mk-form-row>
			<!-- 制定人 -->
	        <mk-form-col :label="formFields.setters.label" required_icon="true">
	        	<su-textbox :data.sync="params.setters"></su-textbox>
	        </mk-form-col>
	        <!-- 年份 -->
	        <mk-form-col :label="formFields.year.label" required_icon="true">
	        	<su-datepicker :data.sync="params.year" format="YYYY" disabled></su-datepicker>
	        </mk-form-col>
    	</mk-form-row>
	</mk-form-panel>
	<mk-panel title="年度经营计划用户列表" slot="bottom" height="100%" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="addCons">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="delCons">删除</su-button>
	    </div>
	    <div id="addGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el: '#addBody',
	data: {
		formFields:{},
		params:{setters:'',year:''},
		ids:[],
		gridData:[],
		checkMap:{},
	},
	ready:function(){
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'add');
        this.params.year = new Date().getFullYear()+1;
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.consInfo",$("#addGrid"),{
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
	},
	methods: {
		save:function(backFun){
	  		if(this.params.setters==''){
	  			MainFrameUtil.alert({title:"警告",body:"请填写制定人！"});
	  			return;
	  		}
	  		if(this.ids.length==0){
	  			MainFrameUtil.alert({title:"警告",body:"请添加用户！"});
	  			return;
	  		}
	  		$.ajax({
				url : basePath + "cloud-purchase-web/phmBusinessPlanController",
   	   			type : 'post',
   	   			data : $.toJSON({consIds:this.ids,phmBusinessPlan:this.params}),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   				MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
						backFun();
					}});
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		});
		},
		addCons:function(){
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"consDialog",
	  			href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanmonth/cons-dialog',
	  			params:{singleSelect:false},
				iframe:true,
				modal:true,
				width:"50%",
				height:620,
				onClose:function(params){
					if(typeof(params[0])==="object"){
						var ids = [];
						for(var i=0;i<params.length;i++){
							var obj = params[i];
							var id = obj.consId;
							if(me.checkMap[id]){
								MainFrameUtil.alert({ title:"错误", body:obj.consName+"已经被选择"});
								for(var j=0;j<i;j++){
									me.checkMap[params[j].id] = false;
								}
								return false;
							}else{
								me.checkMap[id] = true;
								ids.push(id);
							}
						}
						me.getConsData(ids);
					}
				}
			});
		},
		delCons:function(){
			var me = this;
			var row =  $("#addGrid").treegrid('getSelected'); 
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"删除后不可恢复，确认删除选中行？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	var index = $("#addGrid").treegrid('getRowIndex',row);
		    			var id = me.ids[index];
		    			me.ids.splice(index,1);
		    			me.gridData.splice(index,1);
		    			me.checkMap[id] = false;
		    			$("#addGrid").datagrid("loadData",me.gridData);
		    			MainFrameUtil.alert({title:"提示",body:"删除成功！"});
		            }
		        }
			}); 
			
		},
		getConsData:function(ids){
			var me = this;
			$.ajax({
				url: basePath + 'cloud-purchase-web/phmBusinessConsRelaController/getConsInfo',
        		type:'post',
        		data:$.toJSON(ids),
        		contentType : 'application/json;charset=UTF-8',
        		success:function(data){
        			if(data.flag){
        				var consIds = [];
        				for(var i in data.data){
        					consIds.push(data.data[i].id);
        				}
        				me.ids = me.ids.concat(consIds);
        				me.gridData = me.gridData.concat(data.data);
						$("#addGrid").datagrid("loadData",me.gridData);
        			}else{
        				for(var i=0;i<ids.length;i++){
							me.checkMap[ids[i]] = false;
						}
        				MainFrameUtil.alert({title:"错误",body:data.msg});
        			}
        		}
			})
		}
	}
}); 
</script>
</body>
</html>