<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
 <title>购售电交易-年度电量分解测算明细</title>
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
<mk-form-panel title="年度电量分解测算依据" num="2">
	<mk-form-row>
		<!-- 合同总电量 -->
        <mk-form-col :label="formFields.agrePqForm.label" >
        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 年度利润指标 -->
        <mk-form-col :label="formFields.profitIndexForm.label" >
        	<su-textbox :data.sync="params.profitIndex" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 合同总销售额 -->
        <mk-form-col :label="formFields.agreAmt.label" >
        	<su-textbox :data.sync="params.agreAmt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 平均代理单价 -->
        <mk-form-col :label="formFields.avgPrc.label" >
        	<su-textbox :data.sync="params.avgPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 附加利润 -->
        <mk-form-col :label="formFields.addProfitForm.label" >
        	<su-textbox :data.sync="params.addProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 差值利润 -->
        <mk-form-col :label="formFields.differenceProfitForm.label" >
        	<su-textbox :data.sync="params.differenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<mk-form-panel title="年度电量分解测算方案明细" num="2">
	<mk-form-row>
		<!-- 双边经验电价 -->
        <mk-form-col :label="formFields.lcExperiencePrc.label" >
        	<su-textbox :data.sync="params.lcExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价经验平均电价  -->
        <mk-form-col :label="formFields.bidExperiencePrc.label" >
        	<su-textbox :data.sync="params.bidExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<mk-form-panel title="年度电量分解测算结果" num="2">
	<mk-form-row>
		<!-- 双边电量 -->
        <mk-form-col :label="formFields.lcPqForm.label" >
        	<su-textbox :data.sync="params.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价电量  -->
        <mk-form-col :label="formFields.bidPqForm.label" >
        	<su-textbox :data.sync="params.bidPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 双边电价 -->
        <mk-form-col :label="formFields.lcPrcForm.label" >
        	<su-textbox :data.sync="params.lcPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 竞价电价  -->
        <mk-form-col :label="formFields.bidPrcForm.label" >
        	<su-textbox :data.sync="params.bidPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row>
		<!-- 购电成本 -->
        <mk-form-col :label="formFields.costAwt.label" >
        	<su-textbox :data.sync="params.costAwt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 购电成本单价  -->
        <mk-form-col :label="formFields.costPrc.label" >
        	<su-textbox :data.sync="params.costPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
   	<mk-form-row v-if="flag">
   		<!-- 竞价差值电量 -->
        <mk-form-col :label="formFields.bidDifferencePq.label" >
        	<su-textbox :data.sync="params.bidDifferencePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
   	</mk-form-row>
</mk-form-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var detailVue = new Vue({
	el:"#detailBody",
	data:{
		formFields:{},
		flag:false,
		params:{
			schemeName:'',agrePq:'',profitIndex:'',agreAmt:'',avgPrc:'',dvaluePq:'',dvalueAwt:'',lcExperiencePrc:'',bidExperiencePrc:'',lcPq:'',
			bidPq:'',lcPrc:'',costAwt:'',costPrc:'',
		}
	},
	ready:function(){
		var me = this;
		ComponentUtil.getFormFields("purchase.plan.scheme",this);
		var id = MainFrameUtil.getParams('detail').id;
		$.ajax({
			url:basePath+"cloud-purchase-web/phmPhPlanYearSchemeController/"+id,
			type:"get",
			success:function(data){
				if(data.flag){
					me.params = data.data;
					me.params.dvalueAwt = (me.params.avgPrc - me.params.costPrc).toFixed(2);
					me.params.costAwt = (me.params.lcPq*me.params.lcPrc+me.params.bidPq*me.params.bidPrc).toFixed(2);
					if(me.params.differenceProfit==null || me.params.differenceProfit == ''){
						me.flag = false;
					}else{
						me.flag = true;
					}
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