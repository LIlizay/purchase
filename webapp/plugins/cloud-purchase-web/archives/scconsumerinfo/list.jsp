<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>档案管理-用户档案</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" title="用电用户信息管理" deployable="true">
			<mk-search-visible> 
					<!-- 用户名称 -->
					<su-form-group :label-name='formFields.consName.label'  class="form-control-row " 
								:class="{'display-none':!formFields.consName.searchHidden}" col="4" label-align="right">
	                    <su-textbox :data.sync="params.scConsumerInfo.consName" type="text"></su-textbox>
	                </su-form-group>
	                <!-- 用电类别 -->
					<su-form-group :label-name='formFields.elecTypeCode.label'  class="form-control-row " 
								:class="{'display-none':!formFields.elecTypeCode.searchHidden}" col="4" label-align="right">
	                    <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.elecTypeCode"
									url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" name="elecTypeCode"></su-select>
	                </su-form-group>
	               
	            	<!-- 电压等级 -->
	            	<su-form-group :label-name='formFields.voltCode.label'  class="form-control-row "
								:class="{'display-none':!formFields.voltCode.searchHidden}" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" multiple="true" :data-source.sync="voltCodeData" :select-value.sync="params.scConsumerInfo.voltCode"
									url="${Config.baseURL}/globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&valueNotIn=AC07501,AC05001,AC03802" name="voltCode"></su-select>
	                </su-form-group>
	                <!-- 所属供电公司  -->
					<su-form-group :label-name='formFields.orgId.label'  class="form-control-row "
								:class="{'display-none':!formFields.orgId.searchHidden}" col="4" label-align="right">
						<mk-trigger-text  :data.sync="orgInfo.name" editable="false" @mk-trigger="selectOrg" ></mk-trigger-text>
	                </su-form-group>
	            	 <!-- 用户类型 -->
					<su-form-group :label-name="formFields.consType.label" class="form-control-row" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.consType"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_consType"></su-select>
					</su-form-group>
					<!-- 客户经理 -->
					<su-form-group :label-name='formFields.sellPerson.label'  class="form-control-row " 
								:class="{'display-none':!formFields.consName.searchHidden}" col="4" label-align="right">
	                    <su-textbox :data.sync="params.scConsumerInfo.sellPerson" type="text"></su-textbox>
	                </su-form-group>
                </mk-search-visible>
                <mk-search-hidden>
	                 <!-- 市场状态 -->
					<su-form-group :label-name="formFields.marketStatusName.label" class="form-control-row" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.marketStatus"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_marketStatusCode"></su-select>
						</su-form-group>
					 <!-- 是否直购电用户-->
					<su-form-group :label-name="formFields.directPowerName.label" class="form-control-row" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.directPower"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag"></su-select>
					</su-form-group>
				</mk-search-hidden>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
		 
		<mk-panel title="用电用户列表" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add" resourceCode="cloud_selling_010102add" >新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_010102edit" >编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout" resourceCode="cloud_selling_010102delete" >删除</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="download" v-on:click="downloadTemplate" resourceCode="cloud_selling_010102add" >模板下载</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-in" v-on:click="importCons" resourceCode="cloud_selling_010102add" >导入</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="exportCons">导出</su-button>
<!-- 		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button> -->
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
				orgInfo : null,
				voltCodeData:[],
				formFields:{},
			    params:{scConsumerInfo:{consName:null,elecTypeCode:null,voltCode:null,orgId:null,consType:null,sellPerson:null,directPower:null,marketStatus:null}}
			},
			ready:function(){
				//按钮权限
				$.checkButtonAuthority(this);
				//列表数据加载
				ComponentUtil.buildTreeGrid("selling.archives.scconsumerinfo",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/scConsumerInfoController/page',
				    width:"100%",
				    scrollbarSize : 0,
				    method: 'post',
				    queryParams:this.params,
				    idField:"id", 
				    treeField:"consName", 
			        striped:true,
				    rownumbers: true,
				    pagination: true,
				    singleSelect:false,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onLoadSuccess : function(){
				    	var pageNumber = $("#consGrid").datagrid("getPager").data("pagination").options.pageNumber;
				    	var pageSize = $("#consGrid").datagrid("getPager").data("pagination").options.pageSize;
				    	var trow = $("#consGrid").parent().find(".datagrid-view1 .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
				    	for(var i in trow){
				    		trow[i].textContent = (parseInt(pageNumber) - 1) * parseInt(pageSize) + parseInt(i) + 1;
				    	}
				    	$("#consGrid").datagrid("clearChecked");
				    },
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.archives.scconsumerinfo",this);
			},
			methods: {
				selectOrg:function(){
		 			var me = this;
		 			MainFrameUtil.openDialog({
			  			id:'mk-power-up-dialog-id',
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scorginfo/org-dialog',
						iframe:true,
						modal:true,
						width:800,
						height:520,
						onClose:function(params){
							if(typeof(params)==="object"){
								console.log(params);
								me.orgInfo = params;
								me.params.scConsumerInfo.orgId = params.id;
							}
						}
					});
		 		},
				reset:function(){
					this.params.scConsumerInfo = {consName:null,elecTypeCode:"",voltCode:"",orgId:"",consType:""};
					this.orgInfo = null;
				},
				//导出  结算-历史电量信息贴过来  后台的需要修改 因为那个是树
// 				download:function(){
// 					var selInfo = $('#consGrid').datagrid('getChecked');
// 					if(selInfo.length < 1){
// 				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
// 				    	return;
// 				    }
// 					var childIds = "";
// 					var parentIds = '';
// 					for(var i = 0 ; i < selInfo.length ; i++){
// 						if(selInfo[i]._parentId == null || selInfo[i]._parentId == ""){
// 							parentIds += selInfo[i].consId+",";
// 						}else{
// 							childIds += selInfo[i].consId + ",";
// 						}
// 					}
					
// 					var str = parentIds.substr(0,parentIds.length-1) + "*" + childIds.substr(0,childIds.length-1);
// 					var url = basePath + "scConsElectricityController/exportExcel?ids=" + str;
// 					return;
// 		            location.href = url;
// 				},
				getDataGrid:function(){
					$('#consGrid').treegrid('options').queryParams = this.params;  
				    $("#consGrid").treegrid('load'); 
				},
				add:function(){
					//添加页面
			  		MainFrameUtil.openDialog({
			  			id:"cons-add",
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/add',
						title:'用户档案新增',
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#consGrid").treegrid('load');
						},
			            buttons:[{text:"取消",type:'default',handler:function(btn,params){
				            	MainFrameUtil.closeDialog("cons-add");
				            }}
			               ]
					});
				},
				edit:function(){
					//更新页面
				    var selInfo = $('#consGrid').treegrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
			  		MainFrameUtil.openDialog({
			  			id:"cons-edit",
			  			params:{id:selInfo[0].id},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/edit',
						title:'用户档案编辑',
						iframe:true,
						maximizable:true,
						modal:true,
						width:"80%",
						height:"620",
						onClose:function(){
							$("#consGrid").treegrid('load');
						},
			            buttons:[
			            {text:"取消",type:'default',handler:function(btn,params){
			            	MainFrameUtil.closeDialog("cons-edit");
			            }}]
					});
				},
				onlogout:function(){//注销（删除）
	            	var selInfo = $('#consGrid').treegrid('getChecked');
					if(selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"是否确认删除选中的 "+selInfo.length+" 个用户？该操作不可恢复！",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	var ids = new Array();
				            	for(var obj in selInfo){
				            		ids.push(selInfo[obj].id);
				            	}
				            	$.ajax({
			                		url:basePath + 'cloud-purchase-web/scConsumerInfoController/',
			                		type:"delete",
			                		data:$.toJSON(ids),
									contentType : 'application/json;charset=UTF-8',
			                		success:function(data){
			                			if(data.flag){
			    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
			    	      					consVue.getDataGrid();
			    	      				}else{
			    	      					MainFrameUtil.alert({title:"错误",body:data.msg});
			    	      				} 
			                		},
			                		error:function(data){
			                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
			                		}
			                	})
				            }
				        }
		    		});
				},
				consNameRender:function(value,row,text){
					if(value){
						return "<a onclick=\"consVue.detail('"+row.id+"')\">"+value+"</a>";
					}
				},
				detail:function(id){
					var rowData=$('#consGrid').treegrid('find',id);
					var consName="";
					if(rowData != null){
						consName = rowData.consName;
					}
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"cons-detail",
			  			params:{consId:id,consName:consName},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/detail',
						iframe:true,
						title:'用户档案信息查看',
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
			            buttons:[{text:"关闭",type:'default',handler:function(btn,params){
			            	MainFrameUtil.closeDialog("cons-detail");
			            }}]
					});
					
				},
				getVoltCode:function(url){
		 			var me = this;
		 			$.ajax({
		 				url:url,
		 				type:"get",
		 				success:function(data){
		 					me.voltCodeData = data;
		 				}
		 			});
		 		},
		 		downloadTemplate: function(){
		 			location.href = '${Config.baseURL}/plugins/cloud-purchase-web/archives/scconsumerinfo/用户档案导入模板.xlsx';//导出excel模板
		 		},
		 		importCons: function(url){
		 			var me = this;
		 			//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"import_excel_dialog",
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/importExcelConsInfo',
						iframe:true,
						title:'用户档案导入',
						modal:true,
						width:"50%",
						maximizable:true,
						height:440,
						onClose:function(){
							$("#consGrid").treegrid('load');
						}
					});
		 		},
				exportCons: function(){
					var selInfo = $('#consGrid').datagrid('getChecked');
					debugger;
					//区分 是手动选择还是条件导出
					var consIds = null;
					if(selInfo != null && selInfo.length > 0){
						for(var i in selInfo){
							consIds.push(selInfo[i].id);
						}
					}
					//导出字段
			  		MainFrameUtil.openDialog({
			  			id:"export-field",
			  			params: {scConsumerInfo:this.params.scConsumerInfo,consIds: consIds},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/exportFields',
						iframe:true,
						title:'请选择导出字段',
						modal:true,
						width:"40%",
						maximizable:true,
						height:620,
			            buttons:[{
		                    text:"确定",
		                    type:"primary",
		                    handler:function(btn,params){
		                    	params.confirmHandler(btn.target);
		                    	}},
		                    {text:"取消",type:'default',handler:function(btn,params){
	                    	 MainFrameUtil.closeDialog("export-field");}
	                     }]
					});
		 		},
			},
			watch : {
				//监听用电类别,改变电压等级
				"params.scConsumerInfo.elecTypeCode":function(value){
					var me = this;
					if(value == "100" || value == "400"){
						me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&pCode.content5=" + value;
						me.getVoltCode(me.voltCodeUrl);
						me.params.scConsumerInfo.voltCode = "";
					}else{
						me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode";
						me.getVoltCode(me.voltCodeUrl);
						me.params.scConsumerInfo.voltCode = "";
					}
				}
				
			}
			
		}); 
	</script>
</body>
</html>