<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-购电计划制定详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="phPlanEditVue">
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel>
			<mk-form-row>
				<!-- 制定人 -->
				<mk-form-col :label="formFields.setters.label" required_icon="true">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.setters" type="text" disabled="disabled" ></su-textbox>
				</mk-form-col>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :disabled="ymDisable" :data.sync="params.phmPurchasePlanMonth.ym" name="ym"></su-datepicker>
				</mk-form-col>
				<!-- 计划名称  -->
				<mk-form-col :label="formFields.planName.label">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.planName" type="text" disabled="disabled"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</su-form>
	<mk-panel title="用户列表" line="true" slot="bottom" height="80%">
		<div id="consGrid" class="col-xs-12" ></div>
	</mk-panel>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var phPlanEditVue=new Vue({
			el: '#phPlanEditVue',
			data: {
				ymFormat:null,
				ymDisable: true,
				queryParams:{planId:''},
				formFields:{},
			    params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null},
			    	examineList:[]},
			},
			ready:function(){
				var me = this;
				if(MainFrameUtil.getParams("plan_edit")){
					MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
				}else if(MainFrameUtil.getParams("plan_detail")){
					MainFrameUtil.setDialogButtons(me.getButtons(),"plan_detail");
				}
				//查询字段名称加载
				me.queryParams.planId = location.search.substr(8);
				//列表数据加载
				ComponentUtil.getFormFields("purchase.bid.consPlanRela",this);
				ComponentUtil.buildGrid("purchase.bid.consPlanRela",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/phmAgrePqExamineController/getPhmAgreExamineByPlanId',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:me.queryParams,
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
			},
			methods: {
				initData:function(){
					var me = this;
					//初始化数据
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.queryParams.planId,
						type : 'get',
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data.data!=null){
								me.params.phmPurchasePlanMonth = data.data;
							}else{
								var userInfo = $.getLoginUserInfo(basePath);
								me.params.phmPurchasePlanMonth.setters = userInfo.userName;
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"查询明细失败！"});
						}
					});
				},
				//按钮组
		        getButtons: function(){
		        	var me = this;
		        	var type="";
		        	if(MainFrameUtil.getParams("plan_edit")){
		        		type="plan_edit";
		        	}else if(MainFrameUtil.getParams("plan_detail")){
		        		type="plan_detail";
		        	}
		        	
		        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog(type)}}];
		            return buttons;
		        }
			},
			watch:{
				formFields:function(){
					this.initData();
				}
			}
		});
	</script>
</body>
</html>