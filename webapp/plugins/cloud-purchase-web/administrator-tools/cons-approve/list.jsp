<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-用户续期审批</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" title="用户续期审批" deployable="false">
           	<!-- 审批状态 -->
           	<su-form-group label-name='审批状态'  class="form-control-row " col="4" label-align="right">
				<su-select placeholder="--请选择--" label-name="name" multiple="false" :select-value.sync="params.approvalStatus" name="approvalStatus"
					 url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_approvalType" ></su-select>
            </su-form-group>
           	 <!-- 流程发起时间 -->
			<su-form-group label-name="流程发起时间" class="form-control-row" col="4" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.startTime" ></su-datepicker>
			</su-form-group>
			<!-- 至-->
			<su-form-group label-name='至'  class="form-control-row " col="4" label-align="right">
               <su-datepicker format="YYYY-MM-DD" :data.sync="params.endTime" ></su-datepicker>
            </su-form-group>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
	</mk-search-panel>
		 
		<mk-panel title="用户续期审批" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_010102edit" >续期审批</su-button>
		    </div>
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consVue = new Vue({
			el: '#consVue',
			data: {
				uuid: null,
			    params:{
			    	approvalStatus: '',
			    	startTime: null,
			    	endTime: null,
			    	
			    }
		
			},
			ready:function(){
				//按钮权限
// 				$.checkButtonAuthority(this);
				//列表数据加载
			   	$("#consGrid").datagrid({
	           		url: basePath+"cloud-purchase-web/systemcompanyApprovalController/page",
	           		method: 'post',
	           		queryParams: this.params,
	                width:'100%',
	                height:"100%",
	                pagination: true,
	                checkOnSelect : true,
	                singleSelect:true,
	                rownumbers: true,
	                nowrap: false,
	                fitColumns: true,
	  		      	scrollbarSize: 0,
	                columns:[[
	                	{field:'provinceName',title:'省份',width:100,align:'center'},
	                	{field:'companyName',title:'公司名称',width:100,align:'center'},
	    				{field:'cause',title:'续期原因',width:100,align:'center'},
	    				{field:'createTime',title:'流程发起时间',width:100,align:'center'},
	    				{field:'approvalStatusName',title:'审批状态',width:200,align:'center',
	    	            	formatter:function(value,row,index){
	    	            		if(row.approvalStatusName != null && row.approvalStatusName != ''){
	    			        		var html="";
	    			        		html+= "<a class='left-btn' onclick=\"consVue.detail('"+index+"')\">"+row.approvalStatusName+"</a>";
	    			        		return html;
	    	            		}else{
	    	            			return null;
	    	            		}
	    	            	}
						},
	    				
	    		    ]],
	            }); 
			},
			methods: {
				reset:function(){
					this.params={
				    	provinceCodeList: [],
				    	comapnyName: '',
				    	consTypeCodeList: [],
				    	versionCode: '',
				    	effectiveDate: null,
				    	expiryDate: null,
				    	managerName: null
				    }
				},
				getDataGrid:function(){
					$('#consGrid').datagrid('options').queryParams = this.params;  
				    $("#consGrid").datagrid('load'); 
				},
				//审批
				edit:function(){
				    var selInfo = $('#consGrid').treegrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
			  		MainFrameUtil.openDialog({
			  			params:{"row" : selInfo},
						href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/cons-approve/flow-approval',
						title:'平台用户系统续期审批',
						iframe:true,
						maximizable:true,
						modal:true,
						width:"80%",
						height:"620",
						onClose:function(){
							$("#consGrid").datagrid('reload');
						},
					});
				},
				//详情
				detail: function(index){
					$()
					MainFrameUtil.openDialog({
			  			id:"detail",
			  			params:{id:id},
						href:'${Config.baseURL}view/cloud-purchase-web/agreement/template/detail',
						title:'合同模版信息查看',	
						iframe:true,
						modal:true,
						width:800,
						height:410,
						buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('detail');
		                    }
		                }]
					});
				},
			},
			watch : {
				
			}
			
		}); 
	</script>
</body>
</html>