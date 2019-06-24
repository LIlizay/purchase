<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%-- <%@page import="com.hhwy.business.system.util.SystemServiceUtil"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	
	//system_config取省码，如果没有，取当前登录人orgCode。如果还取不到，取广东
	/* if(cloudsellingProvince == null || "".equals(cloudsellingProvince)){
		String orgCode = SystemServiceUtil.getLoginUserOrgCode();
		if(orgCode != null && !"".equals(orgCode) && !"00".equals(orgCode)) {
			cloudsellingProvince = orgCode.substring(0,2) + "0000";
		}else {
			//默认是 广东省
			cloudsellingProvince = "440000";
		}
	} */
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
	//如果是福建省，则转发到福建专用的list页面
// 	if("350000".equals(cloudsellingProvince)){
// 		request.getRequestDispatcher("/view/cloud-purchase-web/settlement/fujian/list").forward(request, response);
// 	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>结算管理-月度收益结算</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="phPlanVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="">
			<mk-search-visible>
				<!-- 起始时间 -->
				<su-form-group label-name='起始时间'  class="form-control-row " 
						:class="{'display-none':!formFields.startYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.startYm" name="startYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group label-name='结束时间'  class="form-control-row " 
						:class="{'display-none':!formFields.endYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="月度购电列表" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		    </div>
		    <div id="settlementGrid" class="col-xs-12" ></div>
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
			    params:{startYm:null,endYm:null}
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
				$("#settlementGrid").datagrid({
					url: basePath + 'cloud-purchase-web/smSettlementMonthController/page',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams: this.params,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns: true,
				    singleSelect: true,
				    pageSize: 10,
				    scrollbarSize:10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    columns:[[
				    	   {field:'ck',title:'选择',checkbox:true},
	      				   {field:'ym',title:'用电年月',width:100 ,align:'center',
	      					 formatter:function(value,row,index){
	      						if(value){
	      							return "<a onclick=\"phPlanVue.detail("+index+")\">"+value+"</a>";
	      						}
	      	            	  }
	      				   },
	      				   {field:'consNum',title:'用户数',width:100 ,align:'center'},
	      				   {field:'totalProxyPq',title:'委托电量</br>(兆瓦时)',width:'13%',align:'center',
	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }},
	      				  {field:'totalPurchasePq',title:'总购电量</br>(兆瓦时)',width:100,align:'center',
 	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }},
   				   		/* {field:'totalLcPq',title:'双边电量</br>(兆瓦时)',width:100,align:'center',
 	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }},
	      				   {field:'totalBidPq',title:'竞价电量</br>(兆瓦时)',width:100,align:'center',
 	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }},
	      				   {field:'totalListedPq',title:'挂牌电量</br>(兆瓦时)',width:100,align:'center',
 	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }}, */
	      				   {field:'deliveryPq',title:'结算电量</br>(兆瓦时)',width:100,align:'center',
 	      					 formatter:function(value,row,index){
	      	            		  if(value == null || value == ""){
	      	            			  return 0;
	      	            		  }else{
	      	            			  return value;
	      	            		  }
	      	            	  }},
	      				   {field:'companyPro',title:'售电利润(元)',width:100,align:'center',
  	      					 /* formatter:function(value,row,index){
 	      	            		  if(value == null || value == ""){
 	      	            			  return 0;
 	      	            		  }else{
 	      	            			  return value;
 	      	            		  }
 	      	            	  } */}
					     ]],
					onLoadSuccess:function(){
					}
			    }); 
			},
			methods: {
				reset:function(){
					this.params = {startYm:null,endYm:null};
				},
				getDataGrid: function(){
					$('#settlementGrid').datagrid('options').queryParams = this.params;  
				    $("#settlementGrid").datagrid('load'); 
				},
				add: function(){
				    //一般省份的结算方案列表页面
				    var href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListAdd"; 
				  	//如果是江苏省，则跳转至江苏省的结算相关页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListAdd"; 
			 		}else if(cloudsellingProvince == "350000"){		//福建
			 			href = basePath + "view/cloud-purchase-web/settlement/fujian/add"; 
			 		}else{
			 			href = basePath + "view/cloud-purchase-web/settlement/other/add"; 
			 		}
			  		MainFrameUtil.openDialog({
			  			id:"settleSchemeAddDialog",
			  			//params: selInfo[0],
						href: href,
						title:'月度收益结算',
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#settlementGrid").datagrid('load');
						}
					});
				},
				edit:function(){
					//结算
				    var selInfo = $('#settlementGrid').datagrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
				    //一般省份的结算方案列表页面
				    href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListAdd"; 
				    var id = "settleSchemeEditDialog";
				  	//如果是江苏省，则跳转至江苏省的结算相关页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListEdit"; 
			 		}else if(cloudsellingProvince == "350000"){		//福建
			 			id = "settleSchemeAddDialog";
			 			href = basePath + "view/cloud-purchase-web/settlement/fujian/add"; 
			 		}else{
			 			id = "settleSchemeAddDialog";
			 			href = basePath + "view/cloud-purchase-web/settlement/other/add"; 
			 		}
			  		MainFrameUtil.openDialog({
			  			id: id,
			  			params: selInfo[0],
						href: href,
						title:'月度收益结算',
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#settlementGrid").datagrid('load');
						}
					});
				},
				detail: function(index){
					var obj = $('#settlementGrid').datagrid('getData').rows[index];
					//一般省份的结算方案列表页面
				   href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListAdd"; 
				  	//如果是江苏省，则跳转至江苏省的结算相关页面
			 		if(cloudsellingProvince == "320000"){
			 			href = basePath + "view/cloud-purchase-web/settlement/jiangsu/schemeListDetail"; 
			 		}else if(cloudsellingProvince == "350000"){		//福建
			 			id = "settleSchemeAddDialog";
			 			href = basePath + "view/cloud-purchase-web/settlement/fujian/detail"; 
			 		}else{
			 			href = basePath + "view/cloud-purchase-web/settlement/other/detail"; 
			 		}
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"schemeListDetailDialog",
			  			params: obj,
						href: href,
						title:'月度收益结算信息查看',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('settlementSchemeDialog');
		                    }
		                }]
					});
				},
				del: function(){
					var me = this;
					var row = $("#settlementGrid").datagrid("getSelected");
					if(row==null){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
						return;
					}
					var id = row.id;
					var url = "";
					if(cloudsellingProvince == "320000"){				//江苏
			 			url = basePath + 'cloud-purchase-web/smSettlementMonthController/'+id
			 			if(row.schemeStatus == '1'){	//已有归档的方案
							MainFrameUtil.alert({title:"失败提示",body:"此结算已有归档的方案，不允许删除！"});
							return;
						}
			 		}else{		//福建或其他省份，不需要判断是否已有归档的方案
			 			url = basePath + 'cloud-purchase-web/settlementControllerFj/'+id
			 		}
					MainFrameUtil.confirm({
		      	        title:"确认",
		      	        body:"该操作不能恢复，确定要删除选中记录吗？",
		      	        onClose:function(type){
		      	        	if(type=='ok'){
		      	        		$.ajax({
		      	        			url: url ,
		      	        			type:'delete',
		      	        			success:function(data){
		      	        				if(data.flag){
		      	        					MainFrameUtil.alert({title:"提示",body:data.msg});
		      	        					me.getDataGrid();
		      	        				}else{
		      	        					MainFrameUtil.alert({title:"失败",body:data.msg});
		      	        				}
		      	        			}
		      	        		});
		      	        	}
		      	        }
		  	    	}); 
				}
			}
		}); 
	</script>
</body>
</html>