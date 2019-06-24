<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="detailBody">
<mk-form-panel title="年度电量分解测算方案明细" >
	<mk-form-row>
		<!-- 方案名称 -->
        <mk-form-col :label="formFields.schemeName.label" colspan="3">
        	<su-textbox :data.sync="params.schemeName" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<mk-form-panel title="经营利润分析方案依据" num="2">
	<mk-form-row>
		<!-- 预测合同总电量 -->
        <mk-form-col :label="formFields.agrePq.label" >
        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 预测合同总销售额 -->
        <mk-form-col :label="formFields.agreAmtForm.label" >
        	<su-textbox :data.sync="params.agreAmt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 预测平均代理单价 -->
        <mk-form-col :label="formFields.avgPrcForm.label" >
        	<su-textbox :data.sync="params.avgPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 利润指标 -->
		<mk-form-col :label="formFields.profitNorm.label">
        	<su-textbox :data.sync="params.profitNorm" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<mk-form-panel title="经营利润分析方案" num="2">
	<mk-form-row>
		<!-- 长协电量占比-->
        <mk-form-col :label="formFields.lcPropForm.label" >
        	<su-textbox :data.sync="params.lcProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 长协经验电价  -->
        <mk-form-col :label="formFields.lcExperiencePrc.label" >
        	<su-textbox :data.sync="params.lcExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 竞价电量占比 -->
        <mk-form-col :label="formFields.bidPropForm.label" >
        	<su-textbox :data.sync="params.bidProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价经验平均电价  -->
        <mk-form-col :label="formFields.bidExperiencePrc.label" >
        	<su-textbox :data.sync="params.bidExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<mk-form-panel title="经营利润分析结果" num="2">
	<mk-form-row>
		<!-- 长协电量 -->
        <mk-form-col :label="formFields.lcPqForm.label" >
        	<su-textbox :data.sync="params.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价电量  -->
        <mk-form-col :label="formFields.bidPqForm.label" >
        	<su-textbox :data.sync="params.bidPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 长协电价 -->
        <mk-form-col :label="formFields.lcPrc.label" >
        	<su-textbox :data.sync="params.lcPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价电价  -->
        <mk-form-col :label="formFields.bidPrc.label" >
        	<su-textbox :data.sync="params.bidPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 购电成本 -->
        <mk-form-col :label="formFields.costAwt.label" >
        	<su-textbox :data.sync="params.costAwt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 利润  -->
        <mk-form-col :label="formFields.profitForm.label" >
        	<su-textbox :data.sync="params.profit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
   		 <!-- 差值利润 -->
         <mk-form-col :label="formFields.dvalueProfitForm.label" >
        	 <su-textbox :data.sync="params.dvalueProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
         </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var detailVue = new Vue({
	el:"#detailBody",
	data:{
		formFields:{},
		params:{
			schemeName:'',agrePq:'',agreAmt:'',avgPrc:'',lcProp:'',lcExperiencePrc:'',bidProp:'',bidExperiencePrc:'',lcPq:'',bidPq:'',lcPrc:'',bidPrc:'',
			costAwt:'',profit:'',dvalueProfit:'',profitNorm:''
		}
	},
	ready:function(){
		var me = this;
		ComponentUtil.getFormFields("purchase.plan.phmbusinessplanscheme",this);
		var id = MainFrameUtil.getParams('detail').id;
		$.ajax({
			url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/"+id,
			type:"get",
			success:function(data){
				if(data.flag){
					var obj = data.data;
					me.params = obj;
					me.params.costAwt = (obj.agreAmt - obj.profit).toFixed(2);
				}else{
					MainFrameUtil.alert({title:"警告",body:data.msg});
				}
			}
		})
	}
})
</script>
</body>
</html>