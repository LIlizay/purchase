	<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
   <title>售电管理-售电合同管理合同终止</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="smPpaStopVue" v-cloak>
<div>
<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
<mk-panel title="合同终止申请" line="true">
	<mk-form-panel title="合同信息">
		<mk-form-row>
			<!-- 客户名称  -->
            <mk-form-col :label="formFields.custName.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.custName}}</div>
            </mk-form-col>
			<!-- 用户名称  -->
             <mk-form-col :label="formFields.consName.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.consName}}</div>
             </mk-form-col>
			<!-- 所属供电公司 -->
             <mk-form-col :label="formFields.orgName.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.orgName}}</div>		
             </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 合同编号  -->
             <mk-form-col :label="formFields.agreNo.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.agreNo}}</div>
             </mk-form-col>
			<!-- 合同名称  -->
             <mk-form-col :label="formFields.agreName.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.agreName}}</div>
             </mk-form-col>
			<!-- 合同类型  -->
             <mk-form-col :label="formFields.agreType.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.agreType}}</div>
             </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 签订时间	  -->
             <mk-form-col :label="formFields.signDate.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.signDate}}</div>
             </mk-form-col>
			<!-- 开始日期  -->
             <mk-form-col :label="formFields.effectiveDate.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.effectiveDate}}</div>
             </mk-form-col>
			<!-- 终止日期  -->
             <mk-form-col :label="formFields.expiryDate.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.expiryDate}}</div>
             </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 合同状态	  -->
             <mk-form-col :label="formFields.agreStatus.label">
                <div style="padding-top: 5px;height: 30px;">{{agreInfo.agreStatusName}}</div>
             </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="合同终止信息">
		<mk-form-row>
			<!-- 终止日期  -->
             <mk-form-col :label="formFields.stopDate.label" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.stopDate" name="stopDate" ></su-datepicker>
             </mk-form-col>
			<!-- 是否已结算完毕  -->
             <mk-form-col :label="formFields.settlementFlag.label" required_icon="true">
		         <su-select :high="150" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" 
		              :select-value.sync="params.settlementFlag" name="settlementFlag" ></su-select>	
             </mk-form-col>
		</mk-form-row>
		<div :class="{'display-none':!applicantFlag}">
			<mk-form-row>
				<!--  申请人  -->
				<mk-form-col :label="formFields.applicant.label" label-width="120px" required_icon="true" col="4" label-align="right">
				    <su-textbox :data.sync="params.applicant" name="applicant" ></su-textbox>
				</mk-form-col>
				<!--  申请时间  -->
				<mk-form-col :label="formFields.applicationDate.label" label-width="120px" required_icon="true" col="4" label-align="right">
				    <su-datepicker format="YYYY-MM-DD" :data.sync="params.applicationDate"  name="applicationDate" ></su-datepicker>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row height="90px">
				<!-- 终止原因  -->
		        <mk-form-col :label="formFields.stopReason.label" colspan="3" required_icon="true">
		             <su-textbox type="textarea" rows="3" rols="10" :data.sync="params.stopReason" name="stopReason" ></su-textbox>
		        </mk-form-col>
			</mk-form-row>
		</div>
		<div :class="{'display-none':!auditorFlag}">
			<mk-form-row>
				<!--  审核人  -->
				<mk-form-col :label="formFields.auditor.label" label-width="120px" required_icon="true" col="4" label-align="right">
				    <su-textbox :data.sync="params.auditor" name="auditor" ></su-textbox>
				</mk-form-col>
				<!--  审核时间  -->
				<mk-form-col :label="formFields.auditTime.label" label-width="120px" required_icon="true" col="4" label-align="right">
				    <su-datepicker format="YYYY-MM-DD" :data.sync="params.auditTime"  name="auditTime" ></su-datepicker>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row height="90px">
				<!-- 审批意见 -->
		         <mk-form-col :label="formFields.auditOpinion.label" colspan="3" required_icon="true">
		              <su-textbox type="textarea" rows="3" rols="10" :data.sync="params.auditOpinion" name="auditOpinion" ></su-textbox>
		         </mk-form-col>
			</mk-form-row>
		</div>
	</mk-form-panel>
</mk-panel>
</su-form>
</div>
<script>
var basePath="${Config.baseURL}";
var smPpaStopVue = new Vue({
    el: '#smPpaStopVue',
    data: {
  	  	formFields:{},
  	  	agreInfo:{},
  	  	applicantFlag:true,
  	  	auditorFlag:false,
  	  	params:{agreId:null,stopDate:null,settlementFlag:null,
  	  		applicant:null,applicationDate:null,auditOpinion:null},
	  	dataOption: {
	        rules: {
	        	stopDate :"required",
	        	settlementFlag :"required",
	        	applicant :"required",
	        	applicationDate :"required", 
	        	auditOpinion :"required"
	        }
	    },
	    valid: false,
    },
    ready:function(){
  	    //初始化表单
  	    ComponentUtil.getFormFields("selling.agreement.smppastop",this);
		//按钮初始化
		MainFrameUtil.setDialogButtons(this.getButtons(),"stop-edit");
    },
    methods: {
		getButtons:function(){
			var buttons = [{text:"提交",type:"primary",handler:this.save},
			               {text:"取消 ",handler:function(){MainFrameUtil.closeDialog("stop-edit");}}];
			return buttons;
		},
		save:function(){
			//合同数据保存
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/smAgreStopController/",
				type : 'post',
				data:$.toJSON(this.params),
				contentType : 'application/json;charset=UTF-8',
				success : function(result) {
					if(result.flag){
						if(result.msg === "该合同已存在上报的购电计划不能终止！"){
							MainFrameUtil.alert({title:"提示",body:result.msg}); 
						}else{
							MainFrameUtil.alert({title:"提示",body:"提交成功！"}); 
							MainFrameUtil.closeDialog("stop-edit");
						}
					}else{
						MainFrameUtil.alert({title:"提示",body:"提交失败！"});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"提交失败！"});
				}
			});
		}
    },
    watch:{
    	formFields:function(){
    		this.agreInfo = MainFrameUtil.getParams("stop-edit");
    		this.params.agreId = this.agreInfo.id;
    	}
    }
}); 
</script>
</body>

</html>
	