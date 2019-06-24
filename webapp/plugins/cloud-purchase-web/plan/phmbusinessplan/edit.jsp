<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="editBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="年度购电计划修编" num="3">
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
	    <div id="editGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editVue = new Vue({
	el: '#editBody',
	data: {
		formFields:{},
		params:{setters:'',year:''},
		ids:[],
		deleteIds:[],
		deleteGridData:{},
		gridData:[],
		checkMap:{}
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams("edit").id;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'edit');
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.consInfo",$("#editGrid"),{
			data:[],
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(){
		    	$("#editGrid").datagrid("options").onLoadSuccess = function(){};
		    	$("#editGrid").datagrid("loadData",me.gridData);
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
		$.ajax({
			url:basePath+"/phmBusinessPlanController/"+id,
			type:"get",
			success:function(data){
				if(data.flag){
					var obj = data.data;
					me.params = obj.phmBusinessPlan;
					me.gridData = obj.consList;
					$("#editGrid").datagrid("loadData",obj.consList);
					for(var i in obj.consIds){
						me.checkMap[obj.consIds[i]] = true;
					}
				}else{
					MainFrameUtil.alert({title:"警告",body:data.msg});
				}
			}
		})
	},
	methods: {
		save:function(backFun){
	  		if(this.params.setters==''){
	  			MainFrameUtil.alert({title:"警告",body:"请填写制定人！"});
	  			return;
	  		}
	  		if(this.gridData.length==0){
	  			MainFrameUtil.alert({title:"警告",body:"请添加用户！"});
	  			return;
	  		}
	  		$.ajax({
				url : basePath + "cloud-purchase-web/phmBusinessPlanController",
   	   			type : 'put',
   	   			data : $.toJSON({consIds:this.ids,deleteIds:this.deleteIds,phmBusinessPlan:this.params}),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   				MainFrameUtil.alert({title:"警告",body:data.msg,onClose:function(){
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
								var index = $.inArray(id, me.deleteIds);
								if(index==-1){
									ids.push(id);
								}else{
									me.deleteIds.splice(index,1);
									me.gridData.push(me.deleteGridData[id]);
								}
							}
						}
						me.getConsData(ids);
					}
				}
			});
		},
		delCons:function(){
			var me = this;
			var row =  $("#editGrid").treegrid('getSelected'); 
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"删除后不可恢复，确认删除选中行？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	var index = $("#editGrid").treegrid('getRowIndex',row);
		            	me.gridData.splice(index,1);
		            	me.deleteGridData[row.id] = row;
		    			var id = row.id;
		    			var i = $.inArray(id, me.ids);
		    			if(i==-1){
		    				me.deleteIds.push(id);
		    			}else{
		    				me.ids.splice(i,1);
		    			}
		    			me.checkMap[id] = false;
		    			$("#editGrid").datagrid("loadData",me.gridData);
		    			MainFrameUtil.alert({title:"提示",body:"删除成功！"});
		            }
		        }
			}); 
			
		},
		getConsData:function(ids){
			var me = this;
			if(ids.length==0){
				$("#editGrid").datagrid("loadData",me.gridData);
				return;
			}
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
						$("#editGrid").datagrid("loadData",me.gridData);
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