<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>偏差考核预警-偏差预警规则</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="ruleVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="偏差预警规则查询">
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
		 
		<mk-panel title="偏差预警规则列表" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		    </div>
		    <div class="col-xs-12 height-fill">
	       	 	<table id="ruleGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var ruleVue = new Vue({
			el: '#ruleVue',
			data: {
				formFields:{},
			    params:{
			    	smDeviationEarlyWarning:{
			    		name:null	
			    	}
				}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("selling.deviationcheck.smdeviationearlywarning",$("#ruleGrid"),{ 
					url: basePath + 'cloud-purchase-web/smDeviationEarlyWarningController/page',
				    width:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    }
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.deviationcheck.smdeviationearlywarning",this);
			},
			methods: {
				reset:function(){
					this.params.smDeviationEarlyWarning = {name:null};
				},
				getDataGrid:function(){
					$('#ruleGrid').datagrid('options').queryParams = this.params;  
				    $("#ruleGrid").datagrid('load'); 
				},
				add:function(){
					//添加页面
			  		MainFrameUtil.openDialog({
			  			id:"ruleadd",
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarningrule/add',
						title:'偏差预警规则新增',
						iframe:true,
						modal:true,
						width:"80%",
						height:620,
						maximizable:true,
						onClose:function(){
							$("#ruleGrid").datagrid('load');
						}
					});
				},
				edit:function(){
					//更新页面
				    var selInfo = $('#ruleGrid').datagrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
			  		MainFrameUtil.openDialog({
			  			id:"ruleedit",
			  			params:{id:selInfo[0].id},
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarningrule/edit',
						title:'偏差预警规则编辑',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						onClose:function(){
							$("#ruleGrid").datagrid('load');
						}
					});
				},
				
				ruleRender:function(value,row,text){
					if(value){
						return "<a onclick=\"ruleVue.detail('"+row.id+"')\">"+value+"</a>";//ruleVue.ruleRender
					}
				},
				
				detail:function(id){
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"ruledetail",
			  			params:{id:id},
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarningrule/detail',
						title:'偏差预警规则信息查看',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620
					});
					
				},
				
				del:function(){
					var rows = $('#ruleGrid').datagrid("getSelections");
		    		if(rows.length == 0 ){
		    			 MainFrameUtil.alert({
		                     title:"提示",
		                     body:"请选择删除对象！",
		                 });
		                 return;
		    		}
		    		MainFrameUtil.confirm({
		    	        title:"警告",
		    	        body:"确认删除吗？删除后数据不可恢复！ ",
		    	        onClose:function(type){
		    	            if(type=="ok"){//确定
		    	            	var ids = new Array();
		    	            	for(var i=0; i<rows.length; i++){ //获取每行id
		    	            		ids.push(rows[i].id);
		    	            	}
		    	            	$.ajax({
		    	                    url:basePath+"cloud-purchase-web/smDeviationEarlyWarningController/",
		    	                    type:"delete",
		    	                    data:$.toJSON(ids),
		    	                    contentType : 'application/json;charset=UTF-8',
		    	                    success : function(data) {
		    	                        if(data.flag == true){
		    	                             MainFrameUtil.alert({
		    	                                 title:"成功提示：",
		    	                                 body:data.msg,
		    	                             });
		    	                             $("#ruleGrid").datagrid('reload');
		    	                        }else{
		    	                            MainFrameUtil.alert({
		    	                                title:"失败提示：",
		    	                                body:data.msg,
		    	                            });
		    	                        }
		    	                    },
		    	                    error : function() {
		    	                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
		    	                    }
		    	                }) 
		    	            }else if(type=="cancel"){//取消
		    	                
		    	            }

		    	        }
		    	    })	
				}
			}
		}); 
	</script>
</body>
</html>