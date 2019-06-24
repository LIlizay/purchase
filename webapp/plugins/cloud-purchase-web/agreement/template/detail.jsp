<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>合同管理-购电合同管理模板详情</title>
</head>
<body id="detailBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="电厂基本信息" num="2">
		<mk-form-row>
			<!-- 合同模板号 -->
	        <mk-form-col :label="formFields.templateVer.label" >
	        	<su-textbox :data.sync="params.templateVer" disabled></su-textbox>
	        </mk-form-col>
			<!-- 合同模板名称 -->
	        <mk-form-col :label="formFields.templateName.label" required_icon="true">
	        	<su-textbox :data.sync="params.templateName" name="templateName" disabled ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
			<!-- 合同类型 -->
	        <mk-form-col :label="formFields.templateTypeName.label" required_icon="true">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_agreType" multiple="false" 
				:select-value.sync="params.templateType" name="templateType" label-name="name" disabled></su-select>
	        </mk-form-col>
	         <!-- 创建日期 -->
	        <mk-form-col :label="formFields.createDate.label" >
	        	<su-textbox :data.sync="params.createDate" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	    	 <mk-form-col :label="formFields.fileId.label" required_icon="true" colspan="2">
	        	<su-textbox :data.sync="fileName" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
        <mk-form-row height="90px">
			<!-- 模板备注 -->
			<mk-form-col :label.sync="formFields.remarks.label" colspan="2">
   				<su-textbox :data.sync="params.remarks" type="textarea" rows="3" disabled></su-textbox>
			</mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el:"#detailBody",
	data:{
		formFields:{},
		params:{templateVer:'',templateName:'',templateType:'',remarks:'',createDate:''},
		fileName:''
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('detail').id;
        ComponentUtil.getFormFields("purchase.contract.template",this);
        $.ajax({
        	url : basePath + "cloud-purchase-web/phmAgreTemplateController/"+id,
        	type:'get',
        	success:function(data){
        		if(data.flag){
        			me.params = data.data;
        			var obj = eval('(' + data.data.fileId + ')');
        			var fileName = obj.fileName;
        			me.fileName = fileName;
        		}else{
        			MainFrameUtil.alert({ title:"错误", body:data.msg }); 
        		}
        	}
        })
	}
})
</script>
</body>
</html>