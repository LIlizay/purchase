<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>偏差考核预警-偏差预警信息详情</title>
<style type="text/css">
.calendar{
	display:block;
	width:30px;
	height:30px;
	background-color:#DEB887;
	position:relative;
	left:0px;
	top:0px;
	z-index:2;
}
input{
	width:100%;
}

</style>
</head>
<body id="warnAddVue">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="用电偏差信息" >
		<mk-form-row>
			<!-- 预警时间 -->
	        <mk-form-col :label="formFields.warningDate.label">
	        	<su-textbox :data.sync="params.warningDate" disabled="disabled"></su-textbox>
	        </mk-form-col>
	        <!-- 偏差类型 -->
	        <mk-form-col :label="formFields.warningTypeName.label" >
	        	<su-select placeholder="--请选择--" label-name="name" disabled="disabled" :select-value.sync="params.warningType"
				url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_deviation"></su-select>
	        </mk-form-col>
	        <!-- 预警级别 -->
	        <mk-form-col :label="formFields.warningGradeName.label">
	        	<su-select placeholder="--请选择--" label-name="name" disabled="disabled" :select-value.sync="params.warningGrade"
				url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass"></su-select>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
	         <!-- 计划累计用电量 -->
	        <mk-form-col :label="formFields.planPq.label">
	        	<su-textbox :data.sync="params.planPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
	        </mk-form-col>
	          <!-- 累计用电量 -->
	        <mk-form-col :label="formFields.actualPq.label">
	        	<su-textbox :data.sync="params.actualPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
	        </mk-form-col>
	    	<!-- 累计用电偏差 -->
<!-- 	        <mk-form-col :label="formFields.deviationPq.label"> -->
<!-- 	        	<su-textbox :data.sync="params.deviationPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox> -->
<!-- 	        </mk-form-col> -->
	        <!-- 累计用电偏差率 -->
	        <mk-form-col :label="formFields.deviationPropForm.label">
	        	<su-textbox :data.sync="params.deviationProp" disabled="disabled" :addon="{'show':true,'rightcontent':' % '}"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	    	 <!-- 计划日用电量 -->
	        <mk-form-col :label="formFields.dayPlanPq.label">
	        	<su-textbox :data.sync="params.dayPlanPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
	        </mk-form-col>
	          <!-- 日用电量 -->
	        <mk-form-col :label="formFields.dayActualPq.label">
	        	<su-textbox :data.sync="params.dayActualPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
	        </mk-form-col>
	    	<!-- 日用电偏差 -->
<!-- 	        <mk-form-col :label="formFields.dayDeviationPq.label"> -->
<!-- 	        	<su-textbox :data.sync="params.dayDeviationPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox> -->
<!-- 	        </mk-form-col> -->
	        <!-- 日用电偏差率 -->
	        <mk-form-col :label="formFields.dayDeviationProp.label">
	        	<su-textbox :data.sync="params.dayDeviationProp" disabled="disabled" :addon="{'show':true,'rightcontent':' % '}"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="合同电量信息" >
		<mk-form-row>
	        <!-- 月度合同电量 -->
	        <mk-form-col :label="formFields.agrePq.label">
	        	<su-textbox :data.sync="params.agrePq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
	        </mk-form-col>
	         <!-- 剩余电量 -->
	        <mk-form-col :label="formFields.surplusPq.label">
	        	<su-textbox :data.sync="params.surplusPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时 '}"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var warnAddVue = new Vue({
	el:"#warnAddVue",
	data:{
		formFields:{},
		params:{
			id:null,
			warningDate:null,
			ymd:null,
			warningGrade:null,
			warningType:null,
			planPq:null,
			actualPq:null,
			deviationProp:null,
			dayDeviationProp : null,
			dayActualPq : null,
			dayPlanPq : null,
			dayDeviationPq : null
		}
	},
	ready:function(){
        ComponentUtil.getFormFields("selling.deviationcheck.smdeviationinfo",this);
       	//获取值
        this.params.id = MainFrameUtil.getParams("warnAdd").id;
    	//初始化按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"warnAdd");
    	this.initData();
        
	},
	methods:{
		//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("warnAdd")}}];
            return buttons;
        },
		
		//初始化数据
		initData: function(){
			var me= this;
			$.ajax({
				url : basePath + "cloud-purchase-web/smDeviationInfoController/"+me.params.id,
   	   			type : 'get',
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					me.params = data.data;
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		})
		}
	}
})
</script>
</body>
</html>