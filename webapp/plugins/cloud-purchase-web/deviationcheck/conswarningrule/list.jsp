<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
		<title>购售电交易-用户预警预测</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consRuleVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用户预警规则查询">
			<mk-search-visible> 
				<!-- 用户名称 -->
		        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
		        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
		        	 <su-textbox :data.sync="params.consName" type="text"></su-textbox>
		        </su-form-group>
		        <!-- 预警频率 -->
		        <su-form-group :label-name='formFields.frequencyName.label'  class="form-control-row " 
							:class="{'display-none':!formFields.frequencyName.searchHidden}" col="4" label-align="right">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smConsEarlyWarning.frequency"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnFrequency" ></su-select>
				</su-form-group>
				<!-- 规则名称 -->
				<su-form-group :label-name='formFields.name.label'  class="form-control-row " 
							:class="{'display-none':!formFields.name.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.smConsEarlyWarning.name" type="text"></su-textbox>
                </su-form-group>
			</mk-search-visible>
		       
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="用户预警规则列表" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		    </div>
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consRuleGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consRuleVue = new Vue({
			el: '#consRuleVue',
			data: {
				formFields:{},
				consInfo:null, //用户信息
			    params:{
			    	consName:null,
			    	smConsEarlyWarning:{
			    		frequency:"",
			    		name:null	
			    	}
				}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("selling.deviationcheck.smconsearlywarning",$("#consRuleGrid"),{ 
					url: basePath + 'cloud-purchase-web/smConsEarlyWarningController/page',
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
				ComponentUtil.getFormFields("selling.deviationcheck.smconsearlywarning",this);
			},
			methods: {	
				reset:function(){
					this.consInfo = null;
					this.params.consName = null;
					this.params.smConsEarlyWarning = {frequency:"",name:null};
				},
				getDataGrid:function(){
					$('#consRuleGrid').datagrid('options').queryParams = this.params;  
				    $("#consRuleGrid").datagrid('load'); 
				},
				add:function(){
					//添加页面
			  		MainFrameUtil.openDialog({
			  			id:"ruleadd",
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/conswarningrule/add',
						title:'用户预警规则新增',
						iframe:true,
						modal:true,
						width:"80%",
						height:620,
						maximizable:true,
						onClose:function(){
							$("#consRuleGrid").datagrid('load');
						}
					});
				},
				edit:function(){
					//更新页面
				    var selInfo = $('#consRuleGrid').datagrid('getChecked');
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
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/conswarningrule/edit',
						title:'用户预警规则编辑',
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#consRuleGrid").datagrid('load');
						}
					});
				},
				
				consRuleRender:function(value,row,text){
					if(value){
						return "<a onclick=\"consRuleVue.consRuleDetail('"+row.id+"')\">"+value+"</a>";//consRuleVue.consRuleRender
					}
				},
				
				consRuleDetail:function(id){
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"ruledetail",
			  			params:{id:id},
						href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/conswarningrule/detail',
						title:'用户预警规则信息查看',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620
					});
					
				},
				
				del:function(){
					var rows = $('#consRuleGrid').datagrid("getSelections");
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
		    	            		if(rows[i].ruleFlag != "1"){
			    	            		ids.push(rows[i].id);
		    	            		}else{
		    	            			MainFrameUtil.alert({
	    	                                title:"提示：",
	    	                                body:"默认规则不能删除!",
	    	                            });
		    	            			return;
		    	            		}
		    	            	}
		    	            	$.ajax({
		    	                    url:basePath+"cloud-purchase-web/smConsEarlyWarningController/",
		    	                    type:"delete",
		    	                    data:$.toJSON(ids),
		    	                    contentType : 'application/json;charset=UTF-8',
		    	                    success : function(data) {
		    	                        if(data.flag == true){
		    	                             MainFrameUtil.alert({
		    	                                 title:"成功提示：",
		    	                                 body:data.msg,
		    	                             });
		    	                             $("#consRuleGrid").datagrid('reload');
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