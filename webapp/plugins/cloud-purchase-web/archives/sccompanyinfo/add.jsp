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
	                <su-textbox name="name" :data.sync="params.companyName"></su-textbox>
	            </mk-form-col>
	            <!-- 法定代表人： -->
	            <mk-form-col required_icon="true" :label="formFields.legalAgent.label" :class="{'display-none':!formFields.legalAgent.formHidden}" >             
	                <su-textbox name="legalAgent" :data.sync="params.legalAgent"></su-textbox>
	            </mk-form-col>
	            <!-- 授权代理人： -->
	            <mk-form-col required_icon="true" :label="formFields.authAgent.label" :class="{'display-none':!formFields.authAgent.formHidden}">
	               	<su-textbox name="authAgent" :data.sync="params.authAgent"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	        	<!-- 法定地址 -->
	            <mk-form-col required_icon="true" :label="formFields.addr.label" :class="{'display-none':!formFields.addr.formHidden}" colspan="3">             
	                <su-textbox name="addr" :data.sync="params.addr"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	            <!-- 电话： -->
	            <mk-form-col required_icon="true" :label="formFields.mobile.label" :class="{'display-none':!formFields.mobile.formHidden}" >             
	                <su-textbox name="mobile" :data.sync="params.mobile"></su-textbox>
	            </mk-form-col>
	            <!-- 传真： -->
	            <mk-form-col :label="formFields.fax.label" :class="{'display-none':!formFields.fax.formHidden}">
	               	<su-textbox :data.sync="params.fax"></su-textbox>
	            </mk-form-col>
	            <!-- 邮编 -->
	            <mk-form-col :label="formFields.postcode.label" :class="{'display-none':!formFields.postcode.formHidden}">
	                 <su-textbox :data.sync="params.postcode"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	            <!-- 开户银行 -->
	            <mk-form-col required_icon="true" :label="formFields.bankName.label" :class="{'display-none':!formFields.bankName.formHidden}"> 
	                 <su-textbox name="bankName" :data.sync="params.bankName"></su-textbox>
	            </mk-form-col>
	            <!-- 银行帐号 -->
	            <mk-form-col required_icon="true" :label="formFields.bankNo.label" :class="{'display-none':!formFields.bankNo.formHidden}"> 
	                 <su-textbox name="bankNo" :data.sync="params.bankNo"></su-textbox>
	            </mk-form-col>
	         	<!-- 税务登记证号-->
	            <mk-form-col required_icon="true" :label="formFields.vatNo.label" :class="{'display-none':!formFields.vatNo.formHidden}"> 
	                 <su-textbox name="vatNo" :data.sync="params.vatNo"></su-textbox>
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
       		},
    		dataOption: {
   				rules: {
   					name:{required: !0},
   					legalAgent:{required: !0},
   					authAgent:{required: !0},
   					addr:{required: !0},
   					mobile:{required: !0},
   					fax:{required: !0},
   					postcode:{required: !0},
   					bankName:{required: !0},
   					bankNo:{required: !0},
   					vatNo:{required: !0}
   				}
			},
			valid:false
	    },
	    ready:function(){
	    	ComponentUtil.getFormFields("selling.archives.scCompanyInfo",this);
	    	//初始化时添加建档、取消按钮
			MainFrameUtil.setDialogButtons([
			    {text:"保存", type:"primary", handler:this.save},
			    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
			]);
        },
		methods : {
			save:function(){
               	/* 表单验证 */
               	scCompanyInfoVue.$refs.company_info.valid();
   	            if(scCompanyInfoVue.valid===false){
   	                return false;
   	            };
   	            var phone = this.validatePhone(this.params.mobile);
   	            var mobile = this.validataMobile(this.params.mobile);
   	            if(phone !== null && mobile !== null){
   	            	MainFrameUtil.alert({title:"提示",body:"电话格式不正确"});
   	            	return;
   	            }
   	            if(!this.validateFaxNo(this.params.fax)){		//验证传真
   	            	return;
   	            }
   	            if(!this.validatepostcode(this.params.postcode)){//验证邮编
   	            	return;
   	            }
   	            if(!this.validateBankno(this.params.bankNo)){	//验证银行账号
   	            	return;
   	            }
//    	            if(!this.validateVatNo(this.params.vatNo)){		//验证税务登记账号
//    	            	return;
//    	            }
   	            $.messager.progress();     //打开进度条
   	            $.ajax({
                    url : "${Config.baseURL}scCompanyInfoController",
                    type : "POST",
                    data:$.toJSON(scCompanyInfoVue.params),
       				contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                       	$.messager.progress('close');  //关闭进度条
                        if(data.flag){
                        	MainFrameUtil.alert({title:"成功提示",body:data.msg}); 
                           	MainFrameUtil.closeDialog();
                        }else{
                           	MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
                        }
                    },
                    error : function() {
                       	$.messager.progress('close');  //关闭进度条
                       	MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
                    }
                });
            },
            validatePhone:function(value){	//验证电话号码
            	var flag = null;
            	var testPhone = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
            	if(!testPhone){
            		flag = "电话格式不正确";
            	}
            	return flag;
            },
            validataMobile:function(value){	//验证手机号码
            	var flag = null;
            	var testMobile = /^(13|14|15|17|18)\d{9}$/i.test(value);
            	if(!testMobile){
            		flag = "电话格式不正确";
            	}
            	return flag;
            },
            validateFaxNo:function(value){	//验证传真
            	if(value == null || value == "") return true;
            	var testFaxNo = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
            	if(!testFaxNo){
            		MainFrameUtil.alert({title:"提示",body:"传真号码不正确"}); 
            		return false;
            	}
            	return true;
            },
            validatepostcode:function(value){	//验证邮编
            	if(value == null || value == "") return true;
            	var testpostcode = /^[0-9]\d{5}$/i.test(value);
            	if(!testpostcode){
            		MainFrameUtil.alert({title:"提示",body:"邮政编码格式不正确"}); 
            		return false;
            	}
            	return true;
            },
            validateVatNo:function(value){		//验证税务登记账号
            	var testVatNo = /^\d{15}$/g.test(value);
            	if(!testVatNo){
            		MainFrameUtil.alert({title:"提示",body:"税务登记账号格式不正确"}); 
            		return false;
            	}
            	return true;
            },
            validateBankno:function(bankno){	//验证银行账号
            	var testBankno = /^\d{16}|\d{19}$/.test(bankno);
            	if(!testBankno){
            		MainFrameUtil.alert({title:"提示",body:"银行账号格式不正确"}); 
            		return false;
            	}
            	return true;
            }
		}
	});
</script>
</body>
</html>