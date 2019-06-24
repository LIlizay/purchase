<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-用户管理平台-平台用户系统续期</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<%@include file="/plugins/business-core/static/headers/upload.jsp"%>
	
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
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.systemEffectiveDate" disabled="false"></su-datepicker>
		          </mk-form-col>
	              <!-- 系统停止时间 -->
	              <mk-form-col label="系统停止时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.systemExpiryDate" disabled="false"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 合同开始时间 -->
	              <mk-form-col label="合同开始时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.effectiveDate" disabled="false"></su-datepicker>
		          </mk-form-col>
	              <!-- 合同结束时间 -->
	              <mk-form-col label="合同结束时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcCompanyApproval.expiryDate" disabled="false"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel> 
          
           <mk-form-panel title="调整为" header="true" label_width="140px"  num="2">
	          <mk-form-row>
	              <!-- 系统开通时间 -->
	              <mk-form-col label="系统开通时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="systemEffectiveDateNew"  :data.sync="params.systemcCompanyApproval.systemEffectiveDateNew" ></su-datepicker>
		          </mk-form-col>
	              <!-- 系统停止时间 -->
	              <mk-form-col label="系统停止时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD"  name="systemExpiryDateNew" :data.sync="params.systemcCompanyApproval.systemExpiryDateNew" ></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 合同开始时间 -->
	              <mk-form-col label="合同开始时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="effectiveDateNew" :data.sync="params.systemcCompanyApproval.effectiveDateNew" ></su-datepicker>
		          </mk-form-col>
	              <!-- 合同结束时间 -->
	              <mk-form-col label="合同结束时间"  required_icon="true">
	              	 <su-datepicker format="YYYY-MM-DD" name="expiryDateNew" :data.sync="params.systemcCompanyApproval.expiryDateNew"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
          </mk-form-panel> 
          
          <mk-form-panel title="续期原因" header="true" label_width="140px"  num="2">
       		  <mk-form-row height="80px">
	              <!-- 续费原因 -->
	              <mk-form-col label="续费原因"  required_icon="true"  colspan="2">
	              	<su-textbox type="textarea" rows="3" rols="10" name="cause" :data.sync="params.systemcCompanyApproval.cause"></su-textbox>
		          </mk-form-col>
              </mk-form-row>
           	<mk-form-row>
		    	 <!-- 附件 -->
		        <mk-form-col label="附件" required_icon="false" colspan="2">
		        	<mk-file-upload v-ref:uploadfile required="false" @upload-error="uploadError" @upload-success="uploadSuccess" 
					url="${Config.getConfig('file.service.url')}" :filename="fileName"></mk-file-upload>
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
                    	params: {
                    		province: '',
                    		companyName	: null,
                    		systemcCompanyApproval :{
//                     			id: null,
                    			domainId: null,
                    			consTypeCode: '',
                        		versionCode	: '',
                        		managerName	: null,
                        		systemEffectiveDate : null,
                        		systemExpiryDate	: null,
                        		effectiveDate	: null,
                        		expiryDate		: null,
                        		systemEffectiveDateNew : '',
                        		systemExpiryDateNew	: '',
                        		effectiveDateNew	: '',
                        		expiryDateNew		: '',
                        		approvalStatus		: null,//审批状态
                    			cause: null,	//续费原因
                    			fileId: null,
                    		}
                    	},
                    	//用于对比时间是否变更
                    	systemEffectiveDateNew : null,
                		systemExpiryDateNew	: null,
                		effectiveDateNew	: null,
                		expiryDateNew		: null,
                    	dataOption: {
                			rules: {
                				systemEffectiveDateNew: "required",
                				systemExpiryDateNew:"required",
                				effectiveDateNew:"required",
                				expiryDateNew:"required",
                				cause: "required",
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
                		    {text:"发送", type:"primary", handler: this.submit},
                		    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
                		]);
                		//页面赋值
                    	me.params.systemcCompanyApproval = row[0];
                		
                		//复制到‘调整为’
                    	me.params.systemcCompanyApproval.systemEffectiveDateNew = me.params.systemcCompanyApproval.systemEffectiveDate; 
                    	me.params.systemcCompanyApproval.systemExpiryDateNew = me.params.systemcCompanyApproval.systemExpiryDate;
                    	me.params.systemcCompanyApproval.effectiveDateNew = me.params.systemcCompanyApproval.effectiveDate;
                    	me.params.systemcCompanyApproval.expiryDateNew = me.params.systemcCompanyApproval.expiryDate;
                		
                    	me.params.systemcCompanyApproval.domainId = row[0].id;
                    	me.params.systemcCompanyApproval.approvalStatus = '01';
                    	me.params.systemcCompanyApproval.id = null;
                    	me.params.province = row[0].province;
                    	me.params.companyName = row[0].companyName; 
                    },
                    methods: {
                    	submit: function(){
                			this.$refs.form1.valid();
                			if(this.valid===false){
                				MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
                				return false
                			}
                			this.$refs.uploadfile.validAndUpload();
                		},
                		uploadSuccess:function(datas){
                			//取到相应的值并发送到后台
                			if(datas!=null){
                				var fileInfo ="{'fileId':'"+datas[0].id+"','fileName':'"+datas[0].fileName+datas[0].extension+"'}";
                				this.params.systemcCompanyApproval.fileId = fileInfo;
                			}
                			this.save();
                  		},
                  		uploadError:function(event,msg){
                  			 MainFrameUtil.alert({title:"错误", body:msg});  
                 			 this.upload ="";
                 		},
                        save: function(){
	                        var me = this;
                   			if(me.params.systemcCompanyApproval.systemEffectiveDateNew == me.params.systemcCompanyApproval.systemEffectiveDate && 
                   					me.params.systemcCompanyApproval.systemExpiryDateNew == me.params.systemcCompanyApproval.systemExpiryDate &&
                   					me.params.systemcCompanyApproval.effectiveDateNew == me.params.systemcCompanyApproval.effectiveDate&&
                   					me.params.systemcCompanyApproval.expiryDateNew == me.params.systemcCompanyApproval.expiryDate		
                   					){
                   				MainFrameUtil.alert({title:'提示',body:'系统开通及合同起止时间未修改，不能发起续期申请！'});
	                    		return;
                   			}
                   			if(me.params.systemcCompanyApproval.systemEffectiveDateNew > me.params.systemcCompanyApproval.systemExpiryDateNew){
                   				MainFrameUtil.alert({title:'提示',body:'系统开通时间不能大于系统停止时间！'});
	                    		return;
                   			}
                   			if(me.params.systemcCompanyApproval.effectiveDateNew > me.params.systemcCompanyApproval.expiryDateNew){
                   				MainFrameUtil.alert({title:'提示',body:'合同开始时间不能大于合同结束时间！'});
	                    		return;
                   			}
	                        if(me.params.systemcCompanyApproval.cause.length > 256){
	                        	MainFrameUtil.alert({title:'提示',body:'续期原因内容请在256字之内！'});
	                    		return;
	                        }
	                    	MainFrameUtil.confirm({
	    				        title:"确认",
	    				        body: "是否确认发送？",
	    				        onClose:function(type){
	    				            if(type=="ok"){//确定
	    				            	  $.ajax({
	    		                               url : "${Config.baseURL}cloud-purchase-web/systemcompanyApprovalController",
	    		                               type : 'post',
	    		                               data: $.toJSON(me.params.systemcCompanyApproval),
	    		                               contentType : 'application/json;charset=UTF-8',
	    		                               success : function(result) {
	    		                                      if(result.flag){
	    		                                             MainFrameUtil.alert({title:"提示",body:"发送成功！"});
	    		                                             MainFrameUtil.closeDialog();
	    		                                      }else{
	    		                                             MainFrameUtil.alert({title:"提示",body:result.msg});
	    		                                      }
	    		                               },
	    		                               error : function() {
	    		                                      MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
	    		                               }
	    		                        });
	    				            }
	    				        }
	                    	})
                        },
                    },
                    watch:{
                    }
             });
       </script>
</div>
</body>
</html>