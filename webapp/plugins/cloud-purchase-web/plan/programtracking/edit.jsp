<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="detailBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="方案信息" num="3">
		<mk-form-row>
			<!-- 方案名称 -->
	        <mk-form-col :label="formFields.schemeName.label" >
	        	<su-textbox :data.sync="planName" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 总合同电量 -->
	        <mk-form-col :label="formFields.agrePqForm.label" >
	        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 利润指标 -->
	        <mk-form-col :label="formFields.profitIndexForm.label" >
	        	<su-textbox :data.sync="params.profitIndex" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
    	</mk-form-row>
    	<mk-form-row>
	        <!-- 长协电量 -->
	        <mk-form-col :label="formFields.lcPqForm.label" >
	        	<su-textbox :data.sync="params.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价电量 -->
	        <mk-form-col :label="formFields.bidPqForm.label" >
	        	<su-textbox :data.sync="params.bidPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 附加利润 -->
	        <mk-form-col :label="formFields.addProfitForm.label" v-if="flag1">
	        	<su-textbox :data.sync="params.addProfitForm" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 差值利润 -->
	        <mk-form-col :label="formFields.differenceProfitForm.label" v-if="flag2">
	        	<su-textbox :data.sync="params.differenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
    	</mk-form-row>
	</mk-form-panel>
	<mk-panel title="方案跟踪信息" slot="bottom" height="100%" line="true">
	    <div id="detailGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var detailVue = new Vue({
	el: '#detailBody',
	data: {
		formFields:{},
		planName:"",
		flag1:false,
		flag2:false,
		params:{
			schemeName:'',agrePq:'',profitIndex:'',agreAmt:'',avgPrc:'',dvaluePq:'',dvalueAwt:'',lcExperiencePrc:'',bidExperiencePrc:'',lcPq:'',
			bidPq:'',lcPrc:'',costAwt:'',costPrc:'',
		}
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams("edit");
		var id = obj.id;
		me.planName = obj.planName;
		ComponentUtil.getFormFields("purchase.plan.scheme",this);
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.programtracking",$("#detailGrid"),{
			url:basePath+"cloud-purchase-web/phmLcDistController/getTrackInfo",
			queryParams:{id:id},
			method:"get",
		    height:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		$.ajax({
			url:basePath+"cloud-purchase-web/phmPhPlanYearSchemeController/"+id,
			type:"get",
			success:function(data){
				if(data.flag){
					me.params = data.data;
					var obj = data.data;
					if(obj.addProfit != null){
			        	me.flag1 = true;
			        }else if(obj.differenceProfit != null){
			        	me.flag2 = true;
			        }
				}else{
					MainFrameUtil.alert({title:"警告",body:data.msg});
				}
			}
		})
	}
}); 
</script>
</body>
</html>