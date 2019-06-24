<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="phPlanVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="客户信息管理">
			<mk-search-visible>
				<!-- 购电交易名称 -->
				<su-form-group label-name='购电交易名称'  class="form-control-row " col="4" label-align="right">
					<su-textbox :data.sync="params.planName" ></su-textbox>
				</su-form-group>
				<!-- 起始时间 -->
				<su-form-group label-name='起始日期'  class="form-control-row " col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.startYm" name="startYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group label-name='结束日期'  class="form-control-row " col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="购电交易列表" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout">删除</su-button>
		    </div>
		    <div id="planGrid" class="col-xs-12" ></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var phPlanVue = new Vue({
			el: '#phPlanVue',
			data: {
				formFields:{},
			    params:{startYm:null,endYm:null,planName:null}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("purchase.bid.purchasePlanMonth",$("#planGrid"),{ 
					url: basePath + 'cloud-purchase-web/phmPurchasePlanMonthController/page',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:this.params,
				    singleSelect : true,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onBeforeSelect: function(index, row){
				    	var oldSelectRow = $("#planGrid").datagrid("getSelected");
				    	if(oldSelectRow == null){
				    		return true;
				    	}
				    	var oldIndex = $("#planGrid").datagrid("getRowIndex",oldSelectRow);
				    	if(index == oldIndex){
				    		$("#planGrid").datagrid("unselectAll");
				    		return false;
				    	}
				    	return true;
				    }
			    }); 
			},
			methods: {
				reset:function(){
					this.params = {startYm:null,endYm:null,planName:null};
				},
				getDataGrid:function(){
					var me = this;
					if(me.params.startYm != null && me.params.startYm != "" && me.params.endYm != null && me.params.endYm != ""){
						var s = parseInt(me.params.startYm.replace("-",""));
						var e = parseInt(me.params.endYm.replace("-",""));
						if(s > e){
							MainFrameUtil.alert({title:"提示",body:"起始日期不能大于结束日期！"});
					    	return;
						}
					}
					$('#planGrid').datagrid('options').queryParams = this.params;  
				    $("#planGrid").datagrid('load'); 
				},
				add:function(){
					MainFrameUtil.openDialog({
			  			id:"plan_add",
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/add',
						title:'购电计划制定',
						maximizable:true,
						iframe:true,
						modal:true,
						width:"80%",
						height:630,
						onClose:function(){
							$("#planGrid").datagrid('load');
						}
					});
				},
				edit:function(){
				    var row = $('#planGrid').datagrid('getSelected');
				    if(row == null){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }
				    var title = "成交信息确认";
				    if(row.planStatus == "00"){
				    	title = "购电计划制定";
				    }
				    if(row.planStatus == "01"){
				    	title = "委托电量审核";
				    }
				    if(row.planStatus == "02"){
				    	title = "交易信息申报";
				    }
				    MainFrameUtil.openDialog({
			  			id:"plan_edit",
			  			params:{planId:row.id,planStatus:row.planStatus,ym:row.ym,mode:row.mode},
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/edit',
						title: title,
						maximizable:true,
						iframe:true,
						modal:true,
						width:"80%",
						height:630,
						onClose:function(){
							$("#planGrid").datagrid('load');
						}
					});
				    /* if(selInfo.length !=0 && selInfo[0].planStatus == "00"){
				    	var row = selInfo[0];
				    	var planId = row.id;
				    	var ym = row.ym;
				    	window.location.href="${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/edit?planId="+planId+"&ym="+ym;
				    } */
				},
				onlogout:function(){//注销（删除）
					var me = this;
	            	var selInfo = $('#planGrid').datagrid('getChecked');
					if(selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}
					var id = selInfo[0].id;
					var ym = selInfo[0].ym.replace("-",'');
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"该操作不能恢复，确定要删除选中的记录吗？",
				        onClose:function(type){
				        	if(type=="ok"){//确定
				        		me.del(id,ym,"false");
				            }
				        }
		    		});
				},
				del:function(id,ym,flag){
					var me = this;
					$.ajax({
                		url:basePath + 'cloud-purchase-web/phmPurchasePlanMonthController',
                		type:"delete",
						contentType : 'application/json;charset=UTF-8',
                		data:$.toJSON({flag:flag,phmPurchasePlanMonth:{id:id,ym:ym}}),
                		success:function(data){
                			if(data.flag){
                				if(data.data){
	    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
	    	      					phPlanVue.getDataGrid();
	    	      					return false;
                				}else{
                					MainFrameUtil.confirm({
								        title:"确认",
								        body:"删除交易数据将影响月度结算数据，是否确认删除？",
								        onClose:function(type){
								        	if(type=="ok"){//确定
								        		me.del(id,ym,"true");
								            }
								        }
						    		});
                				}
    	      				}else{
    	      					MainFrameUtil.alert({title:"错误",body:"删除失败！"});
    	      				} 
                		},
                		error:function(data){
                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
                		}
                	});
				},
				planRender:function(value,row,text){
					if(value){
						return "<a onclick=\"phPlanVue.detail('"+row.id+"','"+row.planStatus+"','"+row.ym+"','"+row.mode+"')\">"+value+"</a>";
					}
				},
				detail:function(id,planStatus,ym,mode){
					//详细信息
				    var title = "成交信息确认";
				    if(planStatus == "00"){
				    	title = "购电计划制定";
				    }
				    if(planStatus == "01"){
				    	title = "委托电量审核";
				    }
				    if(planStatus == "02"){
				    	title = "交易信息申报";
				    }
				    MainFrameUtil.openDialog({
			  			id:"plan_detail",
			  			params:{planId:id,planStatus:planStatus,ym:ym,mode: mode},
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/detail',
						title: title,
						maximizable:true,
						iframe:true,
						modal:true,
						width:"80%",
						height:630,
						onClose:function(){
							$("#planGrid").datagrid('load');
						}
					});
				}
			}
		}); 
	</script>
</body>
</html>