<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
<body id="purchasesettleVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="">
			<mk-search-visible>
				<!-- 起始时间 -->
				<su-form-group :label-name='formFields.startYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.startYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.startYm" name="startYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group :label-name='formFields.endYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.endYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="月度购电计划列表" line="true" slot="bottom" height="100%">
		    <div id="planGrid" class="col-xs-12" ></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var purchasesettleVue = new Vue({
			el: '#purchasesettleVue',
			data: {
				formFields:{},
			    params:{startYm:null,endYm:null}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("purchase.delivery.purchasesettle",$("#planGrid"),{ 
					url: basePath + 'cloud-purchase-web/purchaseSettleController/getPurchasesettleInfo',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    singleSelect:true,
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
					$('#planGrid').datagrid('options').queryParams = this.params;  
				    $("#planGrid").datagrid('load'); 
				},
				
				planRender:function(value,row,index){
					if(value){
						return "<a class='left_btn' onclick=\"purchasesettleVue.detail('"+index+"')\">"+value+"</a>";
					}
				},
				
				detail:function(index){
					var row = $('#planGrid').datagrid('getRows')[index];
			  		MainFrameUtil.openDialog({
			  			id:"sellSettlementDetail",
			  			params:$.parseJSON($.toJSON(row)),
						href:'${Config.baseURL}view/cloud-purchase-web/delivery/purchasesettle/detail',
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