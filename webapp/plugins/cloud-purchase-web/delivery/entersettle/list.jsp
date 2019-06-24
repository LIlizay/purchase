<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算管理——结算单录入</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<style type="text/css">
.left_btn{
        list-style: none;
        height: 58px;
        cursor: pointer;
        font-size: 12px;
        font-family: 微软雅黑;
        width: auto;
    }
</style>
</head>
<body id="entersettleVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="">
			<mk-search-visible>
				<!-- 起始时间 -->
				<su-form-group :label-name='formFields.startYm.label'  class="form-control-row " col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.startYm" name="startYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group :label-name='formFields.endYm.label'  class="form-control-row " col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="结算单列表" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		    </div>
		    <div id="settleGrid" class="col-xs-12" ></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var entersettleVue = new Vue({
			el: '#entersettleVue',
			data: {
				formFields:{},
			    params:{startYm:null,endYm:null}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("purchase.delivery.entersettle",$("#settleGrid"),{ 
					url: basePath + 'cloud-purchase-web/enterSettleController/page',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    singleSelect:false,
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
				ComponentUtil.getFormFields("purchase.bid.purchasePlanMonth",this);
			},
			methods: {
				reset:function(){
					this.params = {startYm:null,endYm:null};
				},
				getDataGrid:function(){
					$('#settleGrid').datagrid('options').queryParams = this.params;  
				    $("#settleGrid").datagrid('load'); 
				},
				
				planRender:function(value,row,index){
					if(value){
						return "<a class='left_btn' onclick=\"entersettleVue.detail('"+index+"')\">"+value+"</a>";
					}
				},
				add:function(){
					//添加页面
			  		MainFrameUtil.openDialog({
			  			id:"settle_add",
						href:'${Config.baseURL}view/cloud-purchase-web/delivery/entersettle/add',
						title:'结算单新增',
						iframe:true,
						modal:true,
						width:"80%",
						height:620,
						maximizable:true,
						onClose:function(){
							$("#settleGrid").datagrid('load');
						}
					});
				},
				edit:function(){
					//编辑页面
					var rows = $('#settleGrid').datagrid("getChecked");
					if(rows.length == 0){
						MainFrameUtil.alert({title:"提示",body:"请选择要编辑的数据！"});
		  	    		return;
					}
					if(rows.length >1){
						MainFrameUtil.alert({title:"提示",body:"只能选择一条数据进行编辑！"});
		  	    		return;
					}
					MainFrameUtil.openDialog({
						id:"settle_edit",
		                params:{"id":rows[0].id,"ym":rows[0].ym,"compProfit":rows[0].compProfit,"compCheck":rows[0].compCheck},
		                href:'${Config.baseURL}view/cloud-purchase-web/delivery/entersettle/edit',
		                modal:true,
		                iframe:true,
		                width:"80%",
		                height:620,
		                onClose:function(){
		                	$('#settleGrid').datagrid("load"); //重新加载列表
		                }
		           })
				},
				del:function(){
					var rows = $('#settleGrid').datagrid("getChecked");
					if(rows.length == 0){
						MainFrameUtil.alert({title:"提示",body:"请选择要删除的数据！"});
		  	    		return;
					}
					MainFrameUtil.confirm({
		    	        title:"确认删除吗?",
		    	        body:"删除后不可恢复",
		    	        onClose:function(type){
		    	            if(type=="ok"){//确定
		    	            	var ids = new Array();
		    	            	for(var i=0; i<rows.length; i++){ //获取每行id
		    	            		ids.push(rows[i].id);
		    	            	}
		    	            	$.ajax({
		    	                    url:basePath+"cloud-purchase-web/enterSettleController",
		    	                    type:"delete",
		    	                    data:$.toJSON(ids),
		    	                    contentType : 'application/json;charset=UTF-8',
		    	                    success : function(data) {
		    	                        if(data.flag){
		    	                             MainFrameUtil.alert({title:"成功提示：",body:data.msg});
		    	                             $("#settleGrid").datagrid('reload');
		    	                        }else{
		    	                            MainFrameUtil.alert({title:"失败提示：",body:data.msg,});
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
				},
				detail:function(index){
					var row = $('#settleGrid').datagrid('getRows')[index];
			  		MainFrameUtil.openDialog({
			  			id:"settle_detail",
			  			params:{"id":row.id,"ym":row.ym,"compProfit":row.compProfit,"compCheck":row.compCheck},
						href:'${Config.baseURL}view/cloud-purchase-web/delivery/entersettle/detail',
						title:'结算信息查看',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620
					});
				}
			}
		}); 
	</script>
</body>
</html>