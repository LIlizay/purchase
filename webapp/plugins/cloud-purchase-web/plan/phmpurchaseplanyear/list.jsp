<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
 <title>购售电交易-年度购电计划</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel  slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 开始年份 -->
			<su-form-group :label-name='formFields.startYear.label' col="4" label-align="right">
				 <su-datepicker format="YYYY" :data.sync="params.startYear" ></su-datepicker>
			</su-form-group>
			<!-- 截止年份 -->
			<su-form-group :label-name='formFields.endYear.label' col="4" label-align="right">
				 <su-datepicker format="YYYY" :data.sync="params.endYear" ></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="年度购电计划列表"  line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="book" v-on:click="scheme">合同电量分配</su-button>
  		</div>
	    <div id="listGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{}
	},
	ready:function(){
		var me = this;
		$("#listGrid").treegrid({
			url:basePath + 'cloud-purchase-web/phmPurchasePlanYearController/planPage',
			idField:"id",
			treeField:"name",
			queryParams:this.params,
			columns:[[
			          {title:"购电计划名称",field:"name",width:120,formatter:me.formatter},
			          {title:"制定人",field:"setters",width:80,align:'center'},
			          {title:"用户数",field:"consSum",width:80,align:'center',formatter:me.yearFormatter},
			          {title:"总合同电量 （兆瓦时）",field:"agrePq",width:120,align:'center'},
			          {title:"合同总销售额（元）",field:"agreAmt",width:80,align:'center'},
			          {title:"双边电量 （兆瓦时）",field:"lcPq",width:80,align:'center'},
			          {title:"竞价电量 （兆瓦时）",field:"bidPq",width:80,align:'center'}
			          ]],
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: true,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess : function(){
		    	$("#listGrid").datagrid("clearChecked");
		    },
		    onBeforeExpand : function(row, param) {
				 $(this).treegrid('options').url = basePath+"cloud-purchase-web/phmPhPlanYearSchemeController/getTreeList";
				 $(this).treegrid('options').queryParams ={phmPhPlanYearScheme:{planId:row.id}};
			 }
		})
		$('#listGrid').treegrid("getPager").pagination({
			 onSelectPage:function(pageNumber,pageSize){
				 $(this).pagination("loading");
				 $("#listGrid").treegrid('options').url = basePath + 'cloud-purchase-web/phmPurchasePlanYearController/planPage';
				 $("#listGrid").treegrid('options').queryParams = me.params;
				 $("#listGrid").treegrid('options').pageNumber =pageNumber;
				 $("#listGrid").treegrid('options').pageSize =pageSize;
				 $("#listGrid").treegrid('reload');
			 }
		 });
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
			$("#listGrid").treegrid('options').url = basePath + 'cloud-purchase-web/phmPurchasePlanYearController/planPage';
		    $("#listGrid").treegrid('load',this.params); 
		},
		add:function(){
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"add",
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/add',
				title:'年度购电计划新增',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:600,
				buttons:[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	params.save(function(){
                    		MainFrameUtil.closeDialog('add');
                    		me.getDataGrid();
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
			var row = $("#listGrid").treegrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"edit",
	  			params:{id:row.planId},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/edit',
				title:'年度购电计划编辑',
				iframe:true,
				modal:true,
				maximizable:true,
				width:1000,
				height:600,
				buttons:[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	params.save(function(){
                    		MainFrameUtil.closeDialog('edit');
                    		me.getDataGrid();
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
			var row =  $("#listGrid").treegrid('getSelected'); 
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据"});
				return;
			}
			 MainFrameUtil.confirm({
		        title:"确认",
		        body:"删除后不可恢复，确认删除选中行？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.ajax({
		    	  			url:basePath+"cloud-purchase-web/phmPurchasePlanYearController/"+row.id,
		    	  			type:"delete",
		    				success:function(data){
		    					if(data.flag){
		    						MainFrameUtil.alert({title:"提示",body:data.msg});
		    						me.getDataGrid();
		    					}else{
		    						MainFrameUtil.alert({title:"错误",body:data.msg});
		    					}
		    				}
		    	  		})
		            }
		        }
		    }); 
		},
		scheme:function(){
			var me = this;
			var row = $("#listGrid").treegrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			var params = {id:row.planId,planName:row.planName,year:row.year};
	  		MainFrameUtil.openDialog({
	  			id:"scheme",
	  			maximizable:true,
	  			params:params,
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/scheme',
				title:'合同电量分配',
				iframe:true,
				modal:true,
				maximizable:true,
				width:1000,
				height:620,
				height:620,buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('scheme');
                    }
                }],
				onClose:function(){
					me.getDataGrid();
				}
			});
		},
		formatter:function(value,row,index){  
			return "<span title="+value+"><a onclick=\"listVue.schemeList('"+row.planId+"')\">"+value+"</a></span>";
		},
		schemeList:function(id){
			MainFrameUtil.openDialog({
	  			id:"schemeList",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/schemelist',
				iframe:true,
				maximizable:true,
				modal:true,
				width:1000,
				height:620,buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('schemeList');
                    }
                }]
			});
		},
		yearFormatter:function(value,row,index){
			return "<a onclick=\"listVue.detail('"+row.planId+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/detail',
				iframe:true,
				modal:true,
				maximizable:true,
				width:1000,
				height:620,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('detail');
                    }
                }]
			});
		},
	}
}); 
</script>
</body>
</html>