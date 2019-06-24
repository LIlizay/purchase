<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购售电交易-月度购电管理竞价交易申报添加交易申报</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="../../../../business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
<style type="text/css">
.datagrid-row-selected{
	color:#000000;
}
</style>
</head>
<body id="examineVue" style="height:100%" v-cloak>
	<div id="transaction" style="height:90%">
		<mk-form-panel  height="20%;" num="3">
			<mk-form-row>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety" disabled="disabled"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :data.sync="params.phmPurchasePlanMonth.ym" name="ym" disabled="disabled"></su-datepicker>
				</mk-form-col>
				<!-- 已成交电量 -->
				<mk-form-col :label="formFields.dealPq.label">
					<su-textbox :data.sync="countParams.dealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
				<mk-form-col :label="formFields.reportPq.label" colspan="1">
					<su-textbox :data.sync="countParams.agrePq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 申报电量  -->
				<mk-form-col :label="formFields.bidReportPq.label">
					<su-textbox :data.sync="countParams.reportPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<br/>
		<mk-tabpanel height="80%" auto_fit="true" :selected.sync="selectedTabIndex">
	         <mk-tabpage index=0 header="售电公司申报明细" height="100%" :href.sync="transactionReportDetailPage" iframe="false" selected="true"></mk-tabpage>
	         <!-- 加载远程代码片段   iframe=false -->
	         <mk-tabpage index=1 header="委托电量明细" height="100%" :href.sync="proxyPqDetailPage" iframe="false" ></mk-tabpage>
	     </mk-tabpanel>
	     <br/>
     </div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var mineVue = new Vue({
	el:"#examineVue",
	data:{
		selectedTabIndex : 0,//选中页的绑定属性
		transactionReportDetailPage: null,//href的绑定属性
		proxyPqDetailPage:"${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/proxyPqDetail",//href的绑定属性
		formFields:{},
		first:true, //表格第一次加载标志
		dataMap:{},
		total:0,
		sum:{id :'',ym :'',consNum :'',reportPq :'',ppaPq:'',bidReportPq:'',bidReportLastPq:''}, //合计
		params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null,reportPq:null},
		lastConsId:"",
	},
	events:{
        //监听选项卡切换事件
        tabPanelSelectedChange:function(val){
        }
    },
	ready:function(){
		var me  = this;
		var id = location.search.substr(8);
		if(id.substr(id.length-2) == "sl"){
			me.sum.id = id.substr(0,id.length-2);
			me.initData();
			me.transactionReportDetailPage = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/transactionReportDetail_sl";
		}else{
			me.sum.id = id;
			me.initData();
			me.transactionReportDetailPage = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/transactionReportDetail";
			//me.transactionReportDetailPage = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/transactionReportDetail_sl";
		}
        ComponentUtil.getFormFields("purchase.bid.transactionlreport",this);
       
	},
	methods:{
		initData:function(){
			var me = this;
			//初始化数据
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.sum.id,
				type : 'get',
				async: false,
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data.data!=null){
						me.params.phmPurchasePlanMonth = data.data;
						if(data.extMap != null){
	                		if(data.extMap["agrePq"] != null){
	                			me.countParams.agrePq = data.extMap["agrePq"];
	                		}
	                		if(data.extMap["dealPq"] != null){
								me.countParams.dealPq = data.extMap["dealPq"];
	                		}
	                	}
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"查询明细失败！"});
				}
			});
		},
	}
})
</script>
</body>
</html>