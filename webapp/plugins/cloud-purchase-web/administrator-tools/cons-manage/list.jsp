<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-用户管理平台</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" title="用电用户信息管理" deployable="false">
			<!-- 省份 -->
			<su-form-group label-name='省份'  class="form-control-row "  col="4" label-align="right">
                  	<su-select placeholder="--请选择--" label-name="name" multiple="true" search="true" :data-source.sync="provinceData" :data.sync="params.provinceCodeList" name="provinceCode"
							url="${Config.baseURL}globalCache/queryProvinceList" ></su-select>
               </su-form-group>
               <!-- 公司名称 -->
			<su-form-group label-name='公司名称'  class="form-control-row "  col="4" label-align="right">
            	<su-textbox :data.sync="params.companyName" type="text"></su-textbox>
            </su-form-group>
              
           	<!-- 用户类型 -->
           	<su-form-group label-name='用户类型'  class="form-control-row " col="4" label-align="right">
				<su-select placeholder="--请选择--" label-name="name" multiple="true" :data-source.sync="consTypeData" :data.sync="params.consTypeCodeList" name="consTypeCode"
					 url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_consType" ></su-select>
            </su-form-group>
               <!-- 系统版本  -->
			<su-form-group label-name='系统版本'  class="form-control-row " col="4" label-align="right">
				<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.versionCode"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_version" ></su-select>
            </su-form-group>
           	 <!-- 合同开始时间 -->
			<su-form-group label-name="合同开始时间" class="form-control-row" col="4" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.effectiveDate" ></su-datepicker>
			</su-form-group>
			<!-- 合同结束时间 -->
			<su-form-group label-name='合同结束时间'  class="form-control-row " col="4" label-align="right">
                   <su-datepicker format="YYYY-MM-DD" :data.sync="params.expiryDate" ></su-datepicker>
               </su-form-group>
              	<!-- 销售经理 -->
			<su-form-group label-name='销售经理'  class="form-control-row " col="4" label-align="right">
                   <su-textbox :data.sync="params.managerName" type="text"></su-textbox>
               </su-form-group>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
	</mk-search-panel>
		 
		<mk-panel title="平台用户明细" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_010102edit" >编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click= "extension" resourceCode="cloud_selling_010102delete" >续期</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button>
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
			    	provinceCodeList: [],
			    	comapnyName: '',
			    	consTypeCodeList: [],
			    	versionCode: '',
			    	effectiveDate: null,
			    	expiryDate: null,
			    	managerName: null
			    }
			},
			ready:function(){
				//按钮权限
// 				$.checkButtonAuthority(this);
				//列表数据加载
			   	$("#consGrid").datagrid({
	           		url: basePath+"cloud-purchase-web/systemcompanyContractController/page",
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
	    		    frozenColumns:[[
	    				{field:'provinceName',title:'省份',width:100,align:'center'},
	    				{field:'companyName',title:'公司名称',width:210,align:'center'},
	      			]],
	                columns:[[
	                	{field:'consTypeCodeName',title:'用户类型',width:100,align:'center'},
	    				{field:'versionCodeName',title:'系统版本',width:100,align:'center'},
	    				{field:'companyDomain',title:'访问地址',width:200,align:'center',
	    	            	formatter:function(value,row,index){
	    	            		if(row.companyDomain != null && row.companyDomain != ''){
	    			        		var html="";
	    			        		html+= "<a class='left-btn' onclick=\"consVue.detail('"+row.companyDomain+"')\">"+row.companyDomain+"</a>";
	    			        		return html;
	    	            		}else{
	    	            			return null;
	    	            		}
	    	            	}
						},
	    				{field:'managerName',title:'销售经理',width:100,align:'center'},  				
	    				{field:'systemEffectiveDate',title:'系统开通时间',width:100,align:'center'},
	    				{field:'systemExpiryDate',title:'系统停止时间',width:100,align:'center'},
	    				{field:'effectiveDate',title:'合同开始时间',width:100,align:'center'},
	    				{field:'expiryDate',title:'合同结束时间',width:100,align:'center'},
	    				{field:'accountPassword',title:'账号密码',width:100,align:'center'},
	    				{field:'remark',title:'备注',width:100,align:'center'}
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
				//更新页面
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
						href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/cons-manage/edit',
						title:'平台用户编辑',
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
				//超链接
				detail: function(domain){
					if(!this.uuid){
						this.uuid=Math.random();
					}
					window.open("https://" + domain + "/gip")//跳转路径
				},
				//导出
				download:function(){
					var me = this;
					var me = this;
					var rows  = $("#consGrid").datagrid("getRows");
					if(rows.length < 1){
						MainFrameUtil.alert({title:"提示",body:"无导出数据！"});
						return;
					}
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"是否导出？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				    			var url = basePath + "systemcompanyContractController/exportExcel";
				                location.href = url;
				            }
				        }
		    		});
				},
				//续期
				extension: function(){
					  var selInfo = $('#consGrid').treegrid('getChecked');
					    if(selInfo==null||selInfo.length==0){
					    	MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
					    	return;
					    }else if(selInfo.length>1){
					    	MainFrameUtil.alert({title:"提示",body:"只能选择一条数据！"});
					    	return;
					    }
					    var time = null;
					    var s1 = selInfo[0].systemExpiryDate;
					    if(s1 != null && s1 != ''){
		    	  			s1 = new Date(s1.replace(/-/g, "/"));
						    s2 = new Date();
						    var days = s2.getTime() - s1.getTime();
						    time = parseInt(days / (1000 * 60 * 60 * 24));
					    }
					  	if(time == null){
					  		MainFrameUtil.alert({title:"提示",body:"请先维护公司信息！"});
					    	return;
					  	}else if(time <= -7){
					    	MainFrameUtil.alert({title:"提示",body:"用户未到期，无需续期！"});
					    	return;
					    }
				  		MainFrameUtil.openDialog({
				  			params:{"row" : selInfo},
							href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/cons-manage/flow-start',
							title:'平台用户系统续期',
							iframe:true,
							maximizable:true,
							modal:true,
							width:"80%",
							height:"700",
							onClose:function(){
							},
						});
				}
			},
			watch : {
				
			}
			
		}); 
	</script>
</body>
</html>