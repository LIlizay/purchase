<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="phPlanVue" v-cloak>
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
				<!-- 状态 -->
            	<su-form-group :label-name='formFields.status.label'  class="form-control-row "
							:class="{'display-none':!formFields.status.searchHidden}" col="4" label-align="right">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.status"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_consCalcStatus"></su-select>
                </su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="用户计算列表" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="calculator" v-on:click="calc">计算</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plane" v-on:click="submit">提交</su-button>
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
			    params:{startYm:null,endYm:null, status : null}
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildGrid("selling.delivery.planList",$("#planGrid"),{ 
					url: basePath + 'cloud-purchase-web/smSellDeliveryController/getPlanList',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    singleSelect : true,
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
				ComponentUtil.getFormFields("selling.delivery.planList",this);
			},
			methods: {
				reset:function(){
					this.params = {startYm:null,endYm:null, status : null};
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
				    $("#planGrid").datagrid('reload'); 
				},
				calc:function(){
					var me = this;
					var row =  $("#planGrid").datagrid('getSelected');
					if(row == null){
						MainFrameUtil.alert({ title:"提示", body:"请选择计算的数据！"});
						return;
					}
					if(row.status == "02"){
						MainFrameUtil.alert({ title:"提示", body:"已提交的数据不能计算！"});
						return;
					}
					var param = {
							ym : row.ym,
							status : row.status
					};
					$.ajax({
						url:basePath+"cloud-purchase-web/smSellDeliveryController/calc",
						type:"post",
						data:$.toJSON(param),
	                    contentType : 'application/json;charset=UTF-8',
						success:function(data){
							if(data.flag){
								MainFrameUtil.alert({ title:"提示", body:data.msg }); 
							}else{
								MainFrameUtil.alert({ title:"错误", body:data.msg }); 
							}
							$("#planGrid").datagrid('reload');
						}
					})
				},
				planRender:function(value,row,text){
					if(value){
						return "<a onclick=\"phPlanVue.detail('"+row.ym+"','"+row.status+"')\">"+value+"</a>";
					}
				},
				detail:function(ym,status){
					//详细信息
			  		MainFrameUtil.openDialog({
			  			id:"plan_detail",
			  			params:{ym:ym,status:status},
						href:'${Config.baseURL}view/cloud-purchase-web/delivery/sellingdelivery/calc',
						iframe:true,
						modal:true,
						width:"80%",
						height:600
					});
				},
				submit:function(){
					var me = this;
					var row =  $("#planGrid").datagrid('getSelected');
					if(row == null){
						MainFrameUtil.alert({ title:"提示", body:"请选择提交的数据！"});
						return;
					}
					if(row.status == "02"){
						MainFrameUtil.alert({ title:"提示", body:"已提交的数据不能重复提交！"});
						return;
					}
					if(row.status == "00"){
						MainFrameUtil.alert({ title:"提示", body:"未计算的数据不能提交！"});
						return;
					}
					var param = {
							ym : row.ym
					};
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"确认提交吗？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	$.messager.progress({title:"请等待",msg:"正在提交...",interval:100});
				            	$.ajax({
									url:basePath+"cloud-purchase-web/smSellDeliveryController/submit",
									data:$.toJSON(param),
				                    contentType : 'application/json;charset=UTF-8',
									type:"post",
									success:function(data){
										$.messager.progress('close');
										if(data.flag){
											MainFrameUtil.alert({title:"提示",body:data.msg});
										}else{
											MainFrameUtil.alert({title:"失败",body:data.msg});
										}
										$("#planGrid").datagrid('reload');
									}
								})
				            }
				        }
		    		});
				}
			}
		}); 
	</script>
</body>
</html>