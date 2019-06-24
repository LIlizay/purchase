<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>合同管理-售电合同管理</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="spaVue" class="height-fill" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">	    
		<mk-search-panel title="购电合同查询" deployable="false" slot="top">
		        <!-- 合同名称 -->
		        <su-form-group :label-name="formFields.agreName.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreName.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-textbox :data.sync="params.smPpa.agreName"></su-textbox>
		        </su-form-group>
		        <!-- 合同编号 -->
		        <su-form-group :label-name="formFields.agreNo.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreNo.searchHidden}" col="4" label-width="120px" label-align="right">
		           	<su-textbox :data.sync="params.smPpa.agreNo"></su-textbox>
		        </su-form-group>
		        <!-- 用户名称 -->
		        <su-form-group :label-name="formFields.consName.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.consName.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-textbox :data.sync="params.consName"></su-textbox>
		        </su-form-group>
		        <!-- 合同类型 -->
		        <%-- <su-form-group :label-name="formFields.agreType.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreType.searchHidden}" col="4" label-width="120px" label-align="right">
					<su-select label-name="name" :select-value.sync="params.smPpa.agreType" :high="150"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellElecContractType" name="agreType"></su-select>
		        </su-form-group> --%>
				<!-- 合同状态 -->
		        <su-form-group :label-name="formFields.agreStatus.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreStatus.searchHidden}" col="4" label-width="120px" label-align="right">
					<su-select label-name="name" :select-value.sync="params.smPpa.agreStatus" :high="auto"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_contractStatusCode" name="agreStatus"></su-select>
		        </su-form-group>  
		        
		        <!-- 合同开始日期 -->
		        <su-form-group :label-name="formFields.effectiveDate.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.effectiveDate.searchHidden}" col="4" label-width="120px" label-align="right">
					<su-datepicker name="effectDate" :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="params.smPpa.effectiveDate" ></su-datepicker>
		        </su-form-group>
		        
		        <!-- 合同结束日期 -->
		        <su-form-group :label-name="formFields.expiryDate.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.expiryDate.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-datepicker name="effectDate" :z-index="999999" id="expiryDate" format="YYYY-MM-DD"  :data.sync="params.smPpa.expiryDate" ></su-datepicker>
		        </su-form-group>
	        <div slot="buttons" class="pull-right " style="text-align:right">
	            <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
	            <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
	        </div>
	   	</mk-search-panel>
		<mk-panel title="售电合同列表" line="true" slot="bottom" height="100%">
		    <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add" resourceCode="cloud_selling_01030202add" >新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_01030202edit" >编辑</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deleteAgre" resourceCode="cloud_selling_01030202delete" >删除</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="file-text-o" v-on:click="supAdd" resourceCode="cloud_selling_01030202agreement">添加补充协议</su-button>
	        </div>
	        <div id="ppaGrid"></div>
		</mk-panel>
	</mk-top-bottom>
</div>
<script type="text/javascript">
	var basePath="${Config.baseURL}";
	var spaVue = new Vue({
	    el: '#spaVue',
	    data: {
	  	  	formFields:{},
	  	  	params:{smPpa:{agreNo:null,agreName:null,agreType:null,agreStatus:null,effectiveDate:null,expiryDate:null},
	  	  			consName:null,consNo:null,custName:null}		//,orgId:null
	    },
	    ready:function(){
	    	//按钮权限 resourceCode
	    	$.checkButtonAuthority(this);
	  	  //初始化表单
	  	  ComponentUtil.getFormFields("selling.agreement.smppa",this);
	  	   //初始化表格
	  	  ComponentUtil.buildGrid("selling.agreement.smppa",$("#ppaGrid"),{ 
	  		  	  url : basePath+'cloud-purchase-web/smPpaController/page',
	  		      method:'post',
				  queryParams:this.params,
				  singleSelect: true,
	  		      rownumbers: true,
	  		      pagination: true,
	  		      nowrap: false,
	  		      pageSize: 10,
	  		      pageList: [10, 20, 50, 100, 150, 200],
	  		      fitColumns:true,
	  		      fit:true,
	  		      scrollbarSize:0,
	  		      rowStyler:function(idx,row){
	  		          return "height:35px;font-size:12px;";
	  		      }
	  		  });
	    },
	    methods: {
			reset:function(){
				this.params.smPpa = {agreNo:null,agreName:null,agreType:null,agreStatus:"",effectiveDate:null,expiryDate:null};
				this.params.consName = null;
				this.params.consNo = null;
				this.params.custName = null;
			},
			getDataGrid:function(){
				$('#ppaGrid').datagrid('options').queryParams = this.params;  
			    $("#ppaGrid").datagrid('load'); 
			},
			supAdd:function(){
				//更新页面
			    var selInfo = $('#ppaGrid').datagrid('getChecked');
			    if(selInfo==null||selInfo.length==0){
			    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
			    	return;
			    }else if(selInfo.length>1){
			    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
			    	return;
			    }
				//补充协议类渲染
			    MainFrameUtil.openDialog({
		  			id:"sup-add",
		  			params:{agreId:selInfo[0].id,partyA:selInfo[0].partyA,partyB:selInfo[0].partyB},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/supplementary-add',
					iframe:true,
					modal:true,
					width:"80%",
					maximizable:true,
					height:620,
					onClose:function(){
						$("#ppaGrid").datagrid('load');
					}
				});
			},
			//渲染合同状态
			agreStatusRender:function(value,row,index){
				return "<a onclick=\"spaVue.implementation('"+row.id+"')\">"+value+"</a>";
			},
			implementation:function(id){
				MainFrameUtil.openDialog({
		  			id:"detail",
		  			params:{id:id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/implementation',
					iframe:true,
					modal:true,
					maximizable:true,
					width:"80%",
					height:420
				})
			},
			supRender:function(value,row,text){
				//补充协议类渲染
				if(value){
					if(value==0){
						return value;
					}else{
						return "<a onclick=\"spaVue.supDetail('"+row.id+","+row.partyA+","+row.partyB+"')\">"+value+"</a>";
					}
				}
			},
			supDetail:function(obj){
				var content = obj.split(",");
		  		MainFrameUtil.openDialog({
		  			id:"sup-list",
		  			params:{agreId:content[0],partyA:content[1],partyB:content[2]},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/smagresup-list',
					iframe:true,
					modal:true,
					maximizable:true,
					width:"80%",
					height:620,
					onClose:function(){
                        $("#ppaGrid").datagrid('load');
                    }
				});
			},
			add:function(){
				//添加页面
		  		MainFrameUtil.openDialog({
		  			id:"ppa-add",
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/add',
					title:'售电合同新增',
					iframe:true,
					modal:true,
					width:"80%",
					maximizable:true,
					height:620,
					onClose:function(){
						$("#ppaGrid").datagrid('load');
					}
				});
			},
			edit:function(){
				//更新页面
			    var selInfo = $('#ppaGrid').datagrid('getChecked');
			    if(selInfo==null||selInfo.length==0){
			    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
			    	return;
			    }else if(selInfo.length>1){
			    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
			    	return;
			    }
			    var row = selInfo[0];
			    	MainFrameUtil.openDialog({
			  			id:"ppa-edit",
			  			params:{id:selInfo[0].id},
						href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/edit',
						title:'售电合同编辑',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						onClose:function(){
							$("#ppaGrid").datagrid('load');
						}
					});
		  		
			},
			agreNoRender:function(value,row,text){
				if(value){
					return "<a onclick=\"spaVue.detail('"+row.id+"')\">"+value+"</a>";
				}
			},
			detail:function(id){
				//更新页面
		  		MainFrameUtil.openDialog({
		  			id:"ppa-detail",
		  			params:{id:id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/detail',
					title:'售电合同信息查看',
					iframe:true,
					modal:true,
					maximizable:true,
					width:"80%",
					height:620
				});
			},
			deleteAgre:function(){
	        	var selInfo = $('#ppaGrid').datagrid('getChecked');
				if(selInfo.length<1){
					MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
			    	return;
				}
				if(selInfo[0].agreStatus!="00"){
					MainFrameUtil.confirm({
				        title:"确认",
				        body: "该操作不能恢复，确定要删除选中的记录吗？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	$.ajax({
									url : basePath + 'cloud-purchase-web/smPpaController/deleteRelated/'+selInfo[0].id,
									type : 'get',
									contentType : 'application/json;charset=UTF-8',
									success : function(data) {
										var msg = null;
										if(data.flag ){
											if(data.msg != "true"){
												msg = data.msg;
												MainFrameUtil.confirm({
											        title:"确认",
											        body:msg,
											        onClose:function(type){
											            if(type=="ok"){//确定
											            	$.ajax({
										                		url:basePath + 'cloud-purchase-web/smPpaController/deleteById',
										                		type:"delete",
										                		data:selInfo[0].id,
																contentType : 'application/json;charset=UTF-8',
										                		success:function(data){
										                			if(data.flag){
										    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
										    	      					$("#ppaGrid").datagrid('load');
										    	      				}else{
										    	      					MainFrameUtil.alert({title:"错误",body:"删除失败！"});
										    	      				} 
										                		},
										                		error:function(data){
										                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
										                		}
										                	})
											            }
											        }
									    		});
											}else{
												$.ajax({
							                		url:basePath + 'cloud-purchase-web/smPpaController/deleteById',
							                		type:"delete",
							                		data:selInfo[0].id,
													contentType : 'application/json;charset=UTF-8',
							                		success:function(data){
							                			if(data.flag){
							    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
							    	      					$("#ppaGrid").datagrid('load');
							    	      				}else{
							    	      					MainFrameUtil.alert({title:"错误",body:"删除失败！"});
							    	      				} 
							                		},
							                		error:function(data){
							                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
							                		}
							                	});
											}
										}else{
											MainFrameUtil.alert({title:"错误",body:data.msg}); 
										}
									},
									error : function() {
										MainFrameUtil.alert({title:"提示",body:"网络异常，请稍后重试！"});
									}
								});
				            }
				        }
		    		});
				}else{
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"该操作不能恢复，确定要删除选中的记录吗？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	var ids = new Array();
				            	ids.push(selInfo[0].id);
				            	$.ajax({
			                		url:basePath + 'cloud-purchase-web/smPpaController/',
			                		type:"delete",
			                		data:$.toJSON(ids),
									contentType : 'application/json;charset=UTF-8',
			                		success:function(data){
			                			if(data.flag){
			    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
			    	      					$("#ppaGrid").datagrid('load');
			    	      				}else{
			    	      					MainFrameUtil.alert({title:"错误",body:"删除失败！"});
			    	      				} 
			                		},
			                		error:function(data){
			                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
			                		}
			                	})
				            }
				        }
		    		});
				}
			},
			stop:function(){
			    var selInfo = $('#ppaGrid').datagrid('getChecked');
			    if(selInfo==null||selInfo.length==0){
			    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
			    	return;
			    }else if(selInfo.length>1){
			    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
			    	return;
			    }
			    if(selInfo[0].agreStatus != "01"){
			    	MainFrameUtil.alert({title:"警告",body:"只能终止正常合同！"});
			    	return;
			    }
		  		MainFrameUtil.openDialog({
		  			id:"stop-edit",
		  			params:selInfo[0],
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/stop-edit',
					iframe:true,
					modal:true,
					width:"80%",
					height:620,
					onClose:function(){
						$("#ppaGrid").datagrid('load');
					}
				});
			}
	    }
	}); 
</script>
</body>
</html>
