<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-平台用户系统续期审批</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<%@include file="/plugins/business-core/static/headers/upload.jsp"%>
	<style type="text/css">
		.mk-add,.mk-select,.mk-remove{background-color:#F80000;display:none;}
	</style>
	
</head>
<body>
<div id="consEditVue" v-cloak style="padding-bottom: 1px">
       <su-form  v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
       		<mk-form-panel title="用户基本信息" header="true" label_width="140px"  num="2">
              <mk-form-row>
             	 <!-- 省份 -->
	             <mk-form-col label="省份" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="false">
	                	<su-select placeholder="--请选择--" label-name="name" multiple="false"  name="provinceCode" disabled="true"
						:select-value.sync="params.province" url="${Config.baseURL}globalCache/queryProvinceList" ></su-select>
               	</mk-form-col>
	             <!-- 公司名称 -->
	            <mk-form-col label="公司名称" :class="{'display-none':!formFields.elecTypeCode.formHidden}" required_icon="false">
	           		<su-textbox :data.sync="params.companyName" type="text" name='companyName' disabled="true"></su-textbox>
           		</mk-form-col>
             </mk-form-row>
             <mk-form-row>
	              <!-- 用户类型 -->
	              <mk-form-col label="用户类型" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="false">
		          	<su-select placeholder="--请选择--" label-name="name" multiple="false"  name="consTypeCode" disabled="true"
						:select-value.sync="params.systemcCompanyApproval.consTypeCode" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_consType" ></su-select>
	            	</mk-form-col>
	              <!-- 系统版本 -->
	              <mk-form-col label="系统版本"  required_icon="false">
	              	<su-select placeholder="--请选择--" label-name="name" multiple="true" :select-value.sync="params.systemcCompanyApproval.versionCode" disabled="true"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_version" name='versionCode' :data-source.sync="" ></su-select>
		          </mk-form-col>
              </mk-form-row>
              <mk-form-row>
	              <!-- 销售经理 -->
	              <mk-form-col label="销售经理" :class="{'display-none':!formFields.elecTypeCode.formHidden}"  required_icon="false">
	                    <su-textbox :data.sync="params.systemcCompanyApproval.managerName" type="text" name="managerName" disabled="true"></su-textbox>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel>
          <mk-form-panel title="系统开通及合同起止时间" header="true" label_width="140px"  num="2">
	          <mk-form-row>
	              <!-- 系统开通时间 -->
	              <mk-form-col label="系统开通时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.systemEffectiveDate" disabled="true"></su-datepicker>
		          </mk-form-col>
	              <!-- 系统停止时间 -->
	              <mk-form-col label="系统停止时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.systemExpiryDate" disabled="true"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 合同开始时间 -->
	              <mk-form-col label="合同开始时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.effectiveDate" disabled="true"></su-datepicker>
		          </mk-form-col>
	              <!-- 合同结束时间 -->
	              <mk-form-col label="合同结束时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.expiryDate" disabled="true"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel> 
          
           <mk-form-panel title="调整为" header="true" label_width="140px"  num="2">
	          <mk-form-row>
	              <!-- 系统开通时间 -->
	              <mk-form-col label="系统开通时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="systemEffectiveDateNew" disabled="true" :data.sync="params.systemcCompanyApproval.systemEffectiveDateNew" ></su-datepicker>
		          </mk-form-col>
	              <!-- 系统停止时间 -->
	              <mk-form-col label="系统停止时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD"  name="systemExpiryDateNew" disabled="true" :data.sync="params.systemcCompanyApproval.systemExpiryDateNew" ></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 合同开始时间 -->
	              <mk-form-col label="合同开始时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="effectiveDateNew" disabled="true" :data.sync="params.systemcCompanyApproval.effectiveDateNew" ></su-datepicker>
		          </mk-form-col>
	              <!-- 合同结束时间 -->
	              <mk-form-col label="合同结束时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="expiryDateNew" disabled="true" :data.sync="params.systemcCompanyApproval.expiryDateNew"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel> 
          
          <mk-form-panel title="续期原因" header="true" label_width="140px"  num="2">
       		  <mk-form-row height="80px">
	              <!-- 续费原因 -->
	              <mk-form-col label="续费原因"  required_icon="true"  colspan="2">
	              	<su-textbox type="textarea" rows="3" rols="10" disabled="true" name="cause" :data.sync="params.systemcCompanyApproval.cause"></su-textbox>
		          </mk-form-col>
              </mk-form-row>
           	<mk-form-row>
		    	 <!-- 附件 -->
		        <mk-form-col label="附件" required_icon="false" colspan="2">
					<!-- 上传文件 -->
                	<mk-multifile-upload disabled="disabled" :params="" :files="uploadParamsA2"
                	 v-ref:uploadfilemsgA show_upload="false" @upload-error="uploadError" @upload-success="uploadSuccess" 
                	 url='${Config.getConfig("file.service.url")}' maxcount=0 required="false"></mk-multifile-upload>
		        </mk-form-col>
	   		</mk-form-row>
          </mk-form-panel>    
       		<mk-form-panel title="审批意见" header="true" label_width="140px"  num="2">
              <mk-form-row>
	             	 <!-- 是否同意 -->
		             <mk-form-col label="是否同意" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="true">
		            	<su-select placeholder="--请选择--" label-name="name" multiple="false"  name="agreeSign" 
							:select-value.sync="params.systemcCompanyApproval.agreeSign" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_agreeType" ></su-select>
	       			 </mk-form-col>
	              <!-- 审批人 -->
	              <mk-form-col label="审批人" :class="{'display-none':!formFields.elecTypeCode.formHidden}"  required_icon="true">
	                    <su-textbox :data.sync="params.systemcCompanyApproval.approver" type="text" name="approver" disabled="true"></su-textbox>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 审批意见 -->
	              <mk-form-col label="审批意见" :class="{'display-none':!formFields.elecTypeCode.formHidden}" colspan="2">
	                    <su-textbox :data.sync="params.systemcCompanyApproval.opinion" type="text" ></su-textbox>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel>
       </su-form>
       <script type="text/javascript">
             var basePath = '${Config.baseURL}';
             //对应 js
             var consEditVue = new Vue({
                    el: '#consEditVue',
                    data: { 
                    	fileName: '',
                    	uploadParamsA2:[],
                    	params: {
                    		province: '',
                    		companyName	: null,
                    		systemcCompanyApproval :{
                    			id: null,
                    			domainId: null,
                    			consTypeCode: '',
                        		versionCode	: '',
                        		managerName	: null,
                        		systemEffectiveDate : null,
                        		systemExpiryDate	: null,
                        		effectiveDate	: null,
                        		expiryDate		: null,
                        		systemEffectiveDateNew : null,
                        		systemExpiryDateNew	: null,
                        		effectiveDateNew	: null,
                        		expiryDateNew		: null,
                        		approvalStatus		: '',//审批状态
                        		agreeSign			: '',	//是否同意
                        		approver  			: '',	//审批人
                    			cause: null,	//续费原因
                    			opinion: null,	//审批意见
                    			fileId: null,
                    			orgNo: null,
                    		}
                    	},
                    	//用于对比时间是否变更
                    	systemEffectiveDateNew : null,
                		systemExpiryDateNew	: null,
                		effectiveDateNew	: null,
                		expiryDateNew		: null,
                    	dataOption: {
                			rules: {
                				agreeSign	:"required",
               					approval	:"required",
                			}
                		},
                		valid: false,

                    },
                    ready:function(){
                    	var me = this;
                    	//获取主页面传来的数据
                    	var row = MainFrameUtil.getParams().row;
                    	//初始化按钮
                		MainFrameUtil.setDialogButtons([
                		    {text:"保存", type:"primary", handler: this.save},
                		    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
                		]);
                		//页面赋值
                    	me.params.systemcCompanyApproval = row[0];
                    	me.params.systemcCompanyApproval.approveStatus = '01';
                    	me.params.province = row[0].province;
                    	me.params.companyName = row[0].companyName;
                    	if(row[0].fileId != null && row[0].fileId != ''){
                    		var fileObj = eval('(' + row[0].fileId + ')');
                        	var arrStr = {
            						"id": fileObj.fileId,
            						"name": fileObj.fileName
            				};
            				me.uploadParamsA2.push(arrStr);
                    	}

                    	//获取当前登录人信息
                    	var userInfo = $.getLoginUserInfo(basePath);
                    	//审批人
                    	me.params.systemcCompanyApproval.approver = userInfo.userName;

                    },
                    methods: {
                        save: function(){
	                        var me = this;
	                    	this.$refs.form1.valid();
                			if(this.valid===false){
                				MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
                				return false
                			}
	                        if(me.params.systemcCompanyApproval.opinion != null && me.params.systemcCompanyApproval.opinion != '' && me.params.systemcCompanyApproval.opinion.length > 128){
	                        	MainFrameUtil.alert({title:'提示',body:'审批意见内容请在128字之内！'});
	                    		return;
	                        }
	                        
	                        if(me.params.systemcCompanyApproval.agreeSign == '01'){ //同意
	                        	me.params.systemcCompanyApproval.approvalStatus = '02'
	                        }else{
	                        	me.params.systemcCompanyApproval.approvalStatus = '03'
	                        }
	                        //无需更新 不制空会报错
	                        me.params.systemcCompanyApproval.createTime = null;
	                        
// 	                    	MainFrameUtil.confirm({
// 	    				        title:"确认",
// 	    				        body: "系统停止时间保存后不可轻易修改，是否确认保存？",
// 	    				        onClose:function(type){
// 	    				            if(type=="ok"){//确定
	    				            	  $.ajax({
	    		                               url : "${Config.baseURL}cloud-purchase-web/systemcompanyApprovalController",
	    		                               type : 'PUT',
	    		                               data: $.toJSON(me.params.systemcCompanyApproval),
	    		                               contentType : 'application/json;charset=UTF-8',
	    		                               success : function(result) {
	    		                                      if(result.flag){
	    		                                             MainFrameUtil.alert({title:"提示",body:"保存成功！"});
	    		                                             MainFrameUtil.closeDialog();
	    		                                      }else{
	    		                                             MainFrameUtil.alert({title:"提示",body:result.msg});
	    		                                      }
	    		                               },
	    		                               error : function() {
	    		                                      MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
	    		                               }
	    		                        });
// 	    				            }
// 	    				        }
// 	                    	})
                        },
                    },
                    watch:{
                    }
             });
       </script>
</div>
</body>
</html>