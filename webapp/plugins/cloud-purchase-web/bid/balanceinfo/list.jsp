<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>结算管理-实际用电量录入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="phPlanVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="客户信息管理">
			<mk-search-visible>
				<!-- 起始时间 -->
				<su-form-group :label-name='formFields.startYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.startYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.startYm" name="startYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group :label-name='formFields.endYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.endYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="实际用电信息列表" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
				<su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deleteF">删除</su-button>
		    </div>
		    <div id="planGrid" class="col-xs-12" ></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		var cloudsellingProvince = "${cloudsellingProvince}";		//数据库配置的省码
		//对应 js
		var phPlanVue = new Vue({
			el: '#phPlanVue',
			data: {
				formFields:{},
			    params:{startYm:null,endYm:null,flag:"false"}
			},
			ready:function(){
				//system_config取省码，如果没有，取当前登录人orgCode。如果还取不到，取广东
 				if(cloudsellingProvince == null || ''== cloudsellingProvince){
 					var userInfo = $.getLoginUserInfo(basePath);
 					cloudsellingProvince = (userInfo.orgCode).substr(0,2) + '0000';
					$.ajax({
						url: basePath+'globalCache/queryCityByParentCode/' + cloudsellingProvince,
	    				method:'get',
						success: function(data){
							if(data == null || data.length == 0){
								cloudsellingProvince = '440000';
							}
						}
					});
 				}
				//列表数据加载
				ComponentUtil.buildGrid("purchase.bid.practicalPqWrite",$("#planGrid"),{ 
					url: basePath + 'scConsElectricityController/practicalPowerPage',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    singleSelect: false,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onLoadSuccess:function(){

				    }
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.bid.practicalPqWrite",this);
			},
			methods: {
				reset:function(){
					this.params = {startYm:null,endYm:null};
				},
				getDataGrid:function(){
					$('#planGrid').datagrid('options').queryParams = this.params;  
				    $("#planGrid").datagrid('reload'); 
				},
				add:function(){
					//一般省份的实际用电量录入页面
				    var href = basePath + "view/cloud-purchase-web/bid/balanceinfo/add"; 
				  	//如果是江苏省，则跳转至江苏省的实际用电量录入页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/bid/balanceinfo/jiangsu/add"; 
			 		}
					//添加页面
			  		MainFrameUtil.openDialog({
						id:'add',			  			
						href: href,
						title:'',
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#planGrid").datagrid('reload');
						}
					});
				},
				edit: function(){
					//更新页面
				    var selInfo = $('#planGrid').datagrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
				    var smd =  $('#planGrid').datagrid('getChecked')[0];
				  	//一般省份的实际用电量录入页面
				    var href = basePath + "view/cloud-purchase-web/bid/balanceinfo/edit"; 
				  	//如果是江苏省，则跳转至江苏省的实际用电量录入页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/bid/balanceinfo/jiangsu/edit"; 
			 		}
			  		MainFrameUtil.openDialog({
			  			id:"addActualPqDialog",
			  			params:{smd:smd},
						href: href,
						title:'实际用电量录入',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						onClose:function(){
// 							$("#planGrid").datagrid('load');
						}
					});
				},
				deleteF: function(){
					var me = this;
					var selInfo = $('#planGrid').datagrid('getChecked');
					MainFrameUtil.confirm({
				        title:"确认",
				        body: "该操作不能恢复，确定要删除选中的记录吗？",
				        onClose:function(type){
				        	 if(type=="ok"){
			        			var ymList = new Array();
				            	for(var obj in selInfo){
				            		ymList.push(selInfo[obj].ym);
				            	}
				        		 $.ajax({
										url:basePath+"scConsElectricityController/deleteElectricity",
										method:'delete',
										data:$.toJSON({ymList:ymList}),
							            contentType : 'application/json;charset=UTF-8',
										type:"json",
										success:function(data){
											if(data.flag){
					 							MainFrameUtil.alert({title:"提示",body:"删除成功"});
					 							$.messager.progress('close');
					 							 $("#planGrid").datagrid("reload")
											}else{
												MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
												$.messager.progress('close');
										    	return;
											}
										},
						        		error:function(data){
						        			MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"}); 
						        		}
									});
				        	 }
				        }
					})
				},
				ymRender: function(value,row,text){
					if(value){
						return "<a onclick=\"phPlanVue.detail('"+row.ym+"')\">"+value+"</a>";
					}
				},
				detail: function(ym){
					//一般省份的实际用电量页面
				    var href = basePath + "view/cloud-purchase-web/bid/balanceinfo/detail"; 
				  	//如果是江苏省，则跳转至江苏省的实际用电量页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/bid/balanceinfo/jiangsu/detail"; 
			 		}
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"detaiLDeliveryVue",
			  			params:{ym : ym},
						href: href,
						title:'实际用电量录入信息查看',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('detail');
		                    }
		                }]
					});
				}
			}
		}); 
	</script>
</body>
</html>