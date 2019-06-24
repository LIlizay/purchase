<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="ruleDialogVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="预警规则查询">
			<mk-search-visible> 
				<!-- 规则名称 -->
				<su-form-group :label-name='formFields.name.label'  class="form-control-row " 
							:class="{'display-none':!formFields.name.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.smDeviationEarlyWarning.name" type="text"></su-textbox>
                </su-form-group>
			</mk-search-visible>
		       
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="基本预警规则列表" line="true" v-cloak slot="bottom" height="250px">
		    <div class="col-xs-12 height-fill">
	       	 	<table id="ruleGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
		
		<mk-panel title="用户预警规则列表" line="true" v-cloak slot="bottom" height="250px">
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consRuleGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var ruleDialogVue = new Vue({
			el: '#ruleDialogVue',
			data: {
				formFields:{},
			    params:{
			    	smDeviationEarlyWarning:{
			    		name:null	
			    	},
			    	smConsEarlyWarning:{
			    		name:null	
			    	}
				}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("selling.deviationcheck.ruledialogfirst",$("#ruleGrid"),{ 
					url: basePath + 'cloud-purchase-web/smDeviationEarlyWarningController/page',
				    width:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    pagination: true,
				    singleSelect:true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onSelect:function(index, row){
				    	$("#consRuleGrid").datagrid('clearSelections');
				    }
			    }); 
				//列表数据加载
				ComponentUtil.buildGrid("selling.deviationcheck.ruledialog",$("#consRuleGrid"),{ 
					url: basePath + 'cloud-purchase-web/smConsEarlyWarningController/page',
				    width:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    pagination: true,
				    singleSelect:true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onSelect:function(index, row){
				    	$("#ruleGrid").datagrid('clearSelections');
				    }
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.deviationcheck.smdeviationearlywarning",this);
				MainFrameUtil.setDialogButtons(this.getButtons(),"ruleDialog");
			},
			methods: {
				
				getButtons:function(){
	                var buttons=[{
	                    text:"确定",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	var rulerow = $("#ruleGrid").datagrid('getChecked');
	                    	var consrow = $("#consRuleGrid").datagrid('getChecked');
	                    	if(rulerow.length == 0 && consrow.length == 0){
	                    		MainFrameUtil.alert({title:"提示",body:"请选择规则！"});
	                    		return false;
	                    	}else {
	                    		var row = [];
	                    		if(rulerow.length > 0 && consrow.length == 0){
	                    			row = rulerow;
	                    		}else if(rulerow.length == 0 && consrow.length > 0){
	                    			row = consrow;
	                    		}else{
	                    			MainFrameUtil.alert({title:"提示",body:"基本预警规则与用户预警规则只能选择一个规则！"});
		                    		return false;
	                    		}
	                    		MainFrameUtil.setParams(row,"ruleDialog");
		                    	MainFrameUtil.closeDialog("ruleDialog");
	                    	}
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                        MainFrameUtil.closeDialog("ruleDialog");
	                    }
	                }];
	                return buttons;
	            },
				
				reset:function(){
					this.params.smDeviationEarlyWarning = {name:null};
					this.params.smConsEarlyWarning = {name:null};
				},
				getDataGrid:function(){
					$('#ruleGrid').datagrid('options').queryParams = this.params;  
				    $("#ruleGrid").datagrid('load'); 
				    $('#consRuleGrid').datagrid('options').queryParams = this.params;
				    $("#consRuleGrid").datagrid('load');
				},
				
				ruleRender:function(value,row,text){
					if(value){
						return "<a onclick=\"ruleDialogVue.detail('"+row.id+"')\">"+value+"</a>";//ruleDialogVue.ruleRender
					}
				},
				
				detail:function(id){
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"ruledetail",
			  			params:{id:id},
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarningrule/detail',
						iframe:true,
						modal:true,
						width:"80%",
						height:620
					});
					
				},
				consRuleRender:function(value,row,text){
					if(value){
						return "<a onclick=\"ruleDialogVue.consRuleDetail('"+row.id+"')\">"+value+"</a>";//ruleDialogVue.consRuleRender
					}
				},
				
				consRuleDetail:function(id){
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"ruledetail",
			  			params:{id:id},
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/conswarningrule/detail',
						iframe:true,
						modal:true,
						width:"80%",
						height:620
					});
				}
			},
			watch:{
				"params.smDeviationEarlyWarning.name":function(){
					var me = this;
					me.params.smConsEarlyWarning.name = me.params.smDeviationEarlyWarning.name;
				}
			} 
		}); 
	</script>
</body>
</html>