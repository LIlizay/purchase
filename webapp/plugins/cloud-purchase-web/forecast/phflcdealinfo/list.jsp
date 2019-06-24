<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-长协交易数据维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel  slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 年份 -->
			<su-form-group :label-name='formFields.year.label' col="4" label-align="right">
				 <su-datepicker format="YYYY" :data.sync="params.phfLcDealInfo.year" ></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="search" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel> 
	<mk-panel title="省内长协交易数据列表"  slot="bottom" line="true" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		</div>
		<div id='listGrid'></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
		params:{
			phfLcDealInfo:{year:''}
		}
	},
	ready:function(){
		ComponentUtil.getFormFields("purchase.forecast.phflcdealinfo",this);
		ComponentUtil.buildGrid("purchase.forecast.phflcdealinfo",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phfLcDealInfoController/page',
			queryParams:this.params,
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    });
	},
	methods:{
		search:function(){
			$("#listGrid").datagrid("load",this.params);
		},
		reset:function(){
			this.params.phfLcDealInfo.year='';
		},
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/forecast/phflcdealinfo/add',
				title:'长协交易数据新增',
				iframe:true,
				modal:true,
				width:"80%",
				height:250,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save(function(){
	                    		MainFrameUtil.closeDialog('add');
	                    		me.search();
	                    	});
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('add');
	                    }
	                }]
			});
		},
		edit:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"失败",body:"请选择数据"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'edit',
	  			params:{id:row.id},
				href:'${Config.baseURL}view/cloud-purchase-web/forecast/phflcdealinfo/edit',
				title:'长协交易数据编辑',
				iframe:true,
				modal:true,
				width:"80%",
				height:250,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save(function(){
	                    		MainFrameUtil.closeDialog('edit');
	                    		me.search();
	                    	});
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('edit');
	                    }
	                }]
			});
		},
		del:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"失败",body:"请选择数据"});
				return;
			}
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"删除后不可恢复，确认删除选中行？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.ajax({
		    	  			url:basePath+"cloud-purchase-web/phfLcDealInfoController/"+row.id,
		    	  			type:"delete",
		    				success:function(data){
		    					if(data.flag){
		    						MainFrameUtil.alert({title:"提示",body:data.msg});
		    						me.search();
		    					}else{
		    						MainFrameUtil.alert({title:"错误",body:data.msg});
		    					}
		    				}
		    	  		})
		            }
		        }
		    }); 
		}
	}
}); 
</script>
</body>
</html>