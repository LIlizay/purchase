<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>售电公司信息</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	<su-form v-ref:company_info 
			 offpos-style="true" 
			 id="fms1" 
			 :data-option="dataOption" 
			 :set-defaults="setDefaults" 
			 :data-validator.sync="valid" >
		<mk-form-panel label_width="150px" class="mt_10" title="基本信息">
			<mk-form-row>
	            <!-- 售电公司名称 -->
	            <mk-form-col required_icon="true" :label="formFields.companyName.label" :class="{'display-none':!formFields.companyName.formHidden}" >             
	                <su-textbox name="name" :data.sync="params.companyName" disabled="true"></su-textbox>
	            </mk-form-col>
	            <!-- 法定代表人： -->
	            <mk-form-col required_icon="true" :label="formFields.legalAgent.label" :class="{'display-none':!formFields.legalAgent.formHidden}" >             
	                <su-textbox name="legalAgent" :data.sync="params.legalAgent" disabled="true"></su-textbox>
	            </mk-form-col>
	            <!-- 授权代理人： -->
	            <mk-form-col required_icon="true" :label="formFields.authAgent.label" :class="{'display-none':!formFields.authAgent.formHidden}">
	               	<su-textbox name="authAgent" :data.sync="params.authAgent" disabled="true"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	        	<!-- 法定地址 -->
	            <mk-form-col required_icon="true" :label="formFields.addr.label" :class="{'display-none':!formFields.addr.formHidden}" colspan="3">             
	                <su-textbox name="addr" :data.sync="params.addr" disabled="true"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	            <!-- 电话： -->
	            <mk-form-col required_icon="true" :label="formFields.mobile.label" :class="{'display-none':!formFields.mobile.formHidden}" >             
	                <su-textbox name="mobile" :data.sync="params.mobile" disabled="true"></su-textbox>
	            </mk-form-col>
	            <!-- 传真： -->
	            <mk-form-col :label="formFields.fax.label" :class="{'display-none':!formFields.fax.formHidden}">
	               	<su-textbox :data.sync="params.fax" disabled="true"></su-textbox>
	            </mk-form-col>
	            <!-- 邮编 -->
	            <mk-form-col :label="formFields.postcode.label" :class="{'display-none':!formFields.postcode.formHidden}">
	                 <su-textbox :data.sync="params.postcode" disabled="true"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	            <!-- 开户银行 -->
	            <mk-form-col required_icon="true" :label="formFields.bankName.label" :class="{'display-none':!formFields.bankName.formHidden}"> 
	                 <su-textbox name="bankName" :data.sync="params.bankName" disabled="true"></su-textbox>
	            </mk-form-col>
	            <!-- 银行帐号 -->
	            <mk-form-col required_icon="true" :label="formFields.bankNo.label" :class="{'display-none':!formFields.bankNo.formHidden}"> 
	                 <su-textbox name="bankNo" :data.sync="params.bankNo" disabled="true"></su-textbox>
	            </mk-form-col>
	         	<!-- 税务登记证号-->
	            <mk-form-col required_icon="true" :label="formFields.vatNo.label" :class="{'display-none':!formFields.vatNo.formHidden}"> 
	                 <su-textbox name="vatNo" :data.sync="params.vatNo" disabled="true"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
		</mk-form-panel>
	</su-form>
<script>
	var basePath="${Config.baseURL}";
	var scCompanyInfoVue=new Vue({
	    el: 'body',
	    data:{
	        formFields:{},
	        params:{
	        	companyName:null,
	        	legalAgent:null,
	        	authAgent:null,
	        	addr:null,
	        	mobile:null,
	        	fax:null,
	        	postcode:null,
	        	bankName:null, 
	        	bankNo:null,
	        	vatNo:null
        	}
	    },
	    ready:function(){
	    	
	    	ComponentUtil.getFormFields("selling.archives.scCompanyInfo",this);
			MainFrameUtil.setDialogButtons([
			    {text:"关闭", handler:function(){MainFrameUtil.closeDialog()}}
			]);
	    	
            var that = this;
            var id = MainFrameUtil.getParams().id;
            $.messager.progress();  // 显示进度条
            
            $.ajax({
                url : "${Config.baseURL}scCompanyInfoController/" + id,
                type : 'GET',
                dataType: 'json',
                success : function(data) {
                	$.messager.progress('close');   // 如果提交成功则隐藏进度条
                    if(data.flag == true){
                        that.params = data.data;
                    }else{
                    	MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
                    }
                },
                error : function() {
                	$.messager.progress('close');   //隐藏进度条
                	MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
                }
            });
        }
	});
</script>
</body>
</html>