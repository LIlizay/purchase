<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>合同管理-购电合同管理</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="ppaVue" class="height-fill" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">	    
		<mk-search-panel title="购电合同查询" deployable="false" slot="top">
			<mk-search-visible>
			 	<!-- 合同名称 -->
		        <su-form-group :label-name="formFields.agreName.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreName.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-textbox :data.sync="params.phmPpa.agreName"></su-textbox>
		        </su-form-group>
		        
		         <!-- 合同编号-->
		        <su-form-group :label-name="formFields.agreNo.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreNo.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-textbox :data.sync="params.phmPpa.agreNo"></su-textbox>
		        </su-form-group>
		        
		        <!-- 电厂名称 -->
		        <su-form-group :label-name="formFields.elecName.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.elecName.searchHidden}" col="4" label-width="120px" label-align="right">
		        	<su-textbox :data.sync="params.elecName"></su-textbox>
		        </su-form-group>
		        
		         <!-- 合同状态  -->
				<su-form-group :label-name='formFields.agreStatusName.label'  class="form-control-row " label-width="120px"
							:class="{'display-none':!formFields.agreStatusName.searchHidden}" col="4" label-align="right">
					<su-select label-name="name" :select-value.sync="params.phmPpa.agreStatus"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_agreStatusCode" ></su-select>
	            </su-form-group>
		        
		        <!-- 合同开始日期 -->
		        <su-form-group :label-name="formFields.bgnDate.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.year.searchHidden}" col="4" label-width="120px" label-align="right">
		           	<su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.bgnDate" name="bgnDate"></su-datepicker>
		        </su-form-group>
		        
		        
		        <!-- 合同终止日期 -->
		        <su-form-group :label-name="formFields.endDate.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.year.searchHidden}" col="4" label-width="120px" label-align="right">
		           	<su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.endDate" name="endDate"></su-datepicker>
		        </su-form-group>
		        
	            <!-- 电厂区分  -->
				<!-- <su-form-group :label-name='formFields.networkFlag.label'  class="form-control-row " label-width="120px"
							:class="{'display-none':!formFields.networkFlag.searchHidden}" col="4" label-align="right">
					<su-select label-name="name" :select-value.sync="params.networkFlag"
							:data-source="networkFlagList"></su-select>
	            </su-form-group> -->
			</mk-search-visible>
	        <div slot="buttons" class="pull-right " style="text-align:right">
	            <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
	            <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
	        </div>
	   	</mk-search-panel>
		<mk-panel title="购电合同列表" line="true" slot="bottom" height="100%">
		    <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add" resourceCode="cloud_selling_01020302add" >新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_01020302edit" >编辑</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deleteAgre" resourceCode="cloud_selling_01020302delete" >删除</su-button>
	        </div>
	        <div id="ppaGrid"></div>
		</mk-panel>
	</mk-top-bottom>
</div>
<script type="text/javascript">
	var basePath="${Config.baseURL}";
	var ppaVue = new Vue({
	    el: '#ppaVue',
	    data: {
	  	  	formFields:{},
	  	  	params:{
	  	  		elecName:"",
		  	  	phmPpa:{
			  	  	agreNo:"",
			  	  	agreName:"",
			  		agreStatus:"",
			  		elecId:"",
		  	  	},
	  	  		year:"",
	  	  		networkFlag:"",
	  	  	},
	  	  networkFlagList:[
	  	      {"value":1,"name":"网内",},
	  	    {"value":0,"name":"网外",},
	  	  ]
	    },
	    ready:function(){
	    	//按钮权限 resourceCode
	    	$.checkButtonAuthority(this);
	  	  //初始化表单
	  	  ComponentUtil.getFormFields("purchase.agreement.phmppa",this);
	  	   //初始化表格
	  	  ComponentUtil.buildGrid("purchase.agreement.phmppa",$("#ppaGrid"),{ 
	  		  	  url : basePath+'cloud-purchase-web/phmPpaController/page',
	  		      method:'post',
				  queryParams:this.params,
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
				this.params.elecName = "";
				this.params.phmPpa = {elecId:"",agreNo:"",agreName:"",agreStatus:""};
				this.params.year="";
				this.params.networkFlag="";
			},
			getDataGrid:function(){
				$('#ppaGrid').datagrid('options').queryParams = this.params;  
			    $("#ppaGrid").datagrid('load'); 
			},
			
			add:function(){
				//添加页面
		  		MainFrameUtil.openDialog({
		  			id:"ppaAdd",
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/add',
					title:'购电合同新增',
					iframe:true,
					modal:true,
					maximizable:true,
					width:"80%",
					height:620,
					onClose:function(){
						$("#ppaGrid").datagrid('load');
					}
				})
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
		  		MainFrameUtil.openDialog({
		  			id:"ppaEdit",
		  			params:{id:selInfo[0].id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/edit',
					title:'购电合同编辑',
					iframe:true,
					modal:true,
					width:"80%",
					maximizable:true,
					height:620,
					onClose:function(){
						$("#ppaGrid").datagrid('load');
					}
				})
			},
			
			//渲染合同名称
			agreNameRender:function(value,row,index){
				if(value){
					return "<a onclick=\"ppaVue.detail('"+row.id+"')\">"+value+"</a>";
				}
			},
			
			//渲染电厂区分
			networkFlagRender:function(value,row,index){
				if(value == 1){
					return "网内";
				}else if(value == 0){
					return "网外";
				}
			},
			
			detail:function(id){
				//更新页面
		  		MainFrameUtil.openDialog({
		  			id:"ppaDetail",
		  			params:{id:id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/detail',
					title:'购电合同信息查看',
					iframe:true,
					modal:true,
					maximizable:true,
					width:"80%",
					height:620,
					buttons:[{
	                    text:"关闭",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('ppaDetail');
	                    }
	                }]
				})
			},
			
			implementation:function(id){
				MainFrameUtil.openDialog({
		  			id:"detail",
		  			params:{id:id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/implementation',
					iframe:true,
					modal:true,
					width:"80%",
					height:620,
					buttons:[{
	                    text:"关闭",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('detail');
	                    }
	                }]
				})
			},
			
			//渲染合同名称
			agreStatusRender:function(value,row,index){
				return "<a onclick=\"ppaVue.implementation('"+row.id+"')\">"+value+"</a>";
			},
			
			//附件
			fileIdRender:function(value,row,index){
				if(value){
					var obj = eval('(' + value + ')');
					return "<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+obj.fileName+"</a>";
				}
			},
			
			//删除
			deleteAgre:function(){
	        	var selInfo = $('#ppaGrid').datagrid('getChecked');
				if(selInfo.length<1){
					MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
			    	return;
				}
				MainFrameUtil.confirm({
			        title:"确认",
			        body:"该操作不能恢复，确定要删除选中的记录吗？",
			        onClose:function(type){
			            if(type=="ok"){//确定
			            	var ids = new Array();
			            	for(var obj in selInfo){
			            		ids.push(selInfo[obj].id);
			            	}
			            	$.ajax({
		                		url:basePath + 'cloud-purchase-web/phmPpaController/',
		                		type:"delete",
		                		data:$.toJSON({ids: ids,deleteflag: 'false'}),
								contentType : 'application/json;charset=UTF-8',
		                		success:function(data){
		                			if(data.flag){
		    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
		    	      					$("#ppaGrid").datagrid('load');
		    	      				}else{
		    	      					//有月度结算数据
		    	      					MainFrameUtil.confirm({
		    	      				        title:"提示",
		    	      				        body:data.msg,
		    	      				        onClose:function(type){
		    	      				            if(type=="ok"){//确定
		    	      				            	$.ajax({
		    	      			                		url:basePath + 'cloud-purchase-web/phmPpaController/',
		    	      			                		type:"delete",
		    	      			                		data:$.toJSON({ids: ids,deleteflag: 'true'}),//deleteFlag：true标识有结算数据确定删除
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
		    	      				        }
		    	      		    		})
		    	      				} 
		                		},
		                		error:function(data){
		                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
		                		}
		                	});
			            }
			        }
	    		})
			}
	    }
	}); 
</script>
</body>
</html>
