<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-用户管理平台编辑页面</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="consEditVue" v-cloak style="padding-bottom: 1px">
       <su-form  v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
       		<mk-form-panel title="" header="false" label_width="140px"  num="2">
              <mk-form-row>
             	 <!-- 省份 -->
	             <mk-form-col label="省份" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="true">
	                	<su-select placeholder="--请选择--" label-name="name" multiple="false"  name="provinceCode"
						:select-value.sync="params.province" url="${Config.baseURL}globalCache/queryProvinceList" ></su-select>
               	</mk-form-col>
	             <!-- 公司名称 -->
	            <mk-form-col label="公司名称" :class="{'display-none':!formFields.companyName.formHidden}" required_icon="true">
	           		<su-textbox :data.sync="params.companyName" type="text" name='companyName'></su-textbox>
           		</mk-form-col>
             </mk-form-row>
             <mk-form-row>
	              <!-- 用户类型 -->
	              <mk-form-col label="用户类型" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="true">
		          	<su-select placeholder="--请选择--" label-name="name" multiple="false"  name="consTypeCode"
						:select-value.sync="params.systemcompanyContract.consTypeCode" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_consType" ></su-select>
	            	</mk-form-col>
	              <!-- 系统版本 -->
	              <mk-form-col label="系统版本"  required_icon="true">
	              	<su-select placeholder="--请选择--" label-name="name" multiple="true" :select-value.sync="params.systemcompanyContract.versionCode" :disabled = "versionCodeFlag"
						name='versionCode' :data-source.sync="versionDataSource" ></su-select>
		          </mk-form-col>
              </mk-form-row>
              <mk-form-row>
	              <!-- 销售经理 -->
	              <mk-form-col label="销售经理" :class="{'display-none':!formFields.managerName.formHidden}"  required_icon="true">
	                    <su-textbox :data.sync="params.systemcompanyContract.managerName" type="text" name="managerName"></su-textbox>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 系统开通时间 -->
	              <mk-form-col label="系统开通时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcompanyContract.systemEffectiveDate" :disabled="systemEffectiveDateFlag"></su-datepicker>
		          </mk-form-col>
	              <!-- 系统停止时间 -->
	              <mk-form-col label="系统停止时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcompanyContract.systemExpiryDate" :disabled="systemExpiryDateFlag"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 合同开始时间 -->
	              <mk-form-col label="合同开始时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcompanyContract.effectiveDate" :disabled="effectiveDateFlag"></su-datepicker>
		          </mk-form-col>
	              <!-- 合同结束时间 -->
	              <mk-form-col label="合同结束时间"  required_icon="false">
	              	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.systemcompanyContract.expiryDate" :disabled="expiryDateFlag"></su-datepicker>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row>
	              <!-- 账号密码 -->
	              <mk-form-col label="账号密码"  required_icon="false"  colspan="2">
	              	<su-textbox :data.sync="params.systemcompanyContract.accountPassword" :disabled="accountPasswordFlag" ></su-textbox>
		          </mk-form-col>
	          </mk-form-row>
	          <mk-form-row height="80px">
	              <!-- 备注 -->
	              <mk-form-col label="备注"  required_icon="false"  colspan="2">
	              	<su-textbox type="textarea" rows="3" rols="10" :data.sync="params.systemcompanyContract.remark"></su-textbox>
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
                    			//			disabled
                    	versionCodeFlag: false,
                    	systemEffectiveDateFlag: false,  
                    	systemExpiryDateFlag: false,
                    	effectiveDateFlag: false, 
                    	expiryDateFlag: false,
                    	accountPasswordFlag: false,
                    			//			版本下拉数据
               			versionDataSource: [],
                    	initVersionList: [],
                    	easyVersionList: [],
                    	baseVersionList: [],
                    	//初始系统停止时间
                    	systemEndDate: null,
                    	params: {
                    		province: '',
                    		companyName	: null,
                    		systemcompanyContract :{
                    			id: null,
                    			consTypeCode: '',
                        		versionCode	: '',
                        		managerName	: null,
                        		systemEffectiveDate : null,
                        		systemExpiryDate	: null,
                        		effectiveDate	: null,
                        		expiryDate		: null,
                        		accountPassword	: null,
                        		remark	: null,
                        		orgNo : null,
                    		}
                    	},
                    	dataOption: {
                			rules: {
                				provinceCode: "required",
                				managerName:"required",
                				companyName:"required",
                				consTypeCode:"required",
                				versionCode:"required"
                			}
                		},
                		valid: false,

                    },
                    ready:function(){
                    	var me = this;
                    	//获取主页面传来的数据
                    	var row = MainFrameUtil.getParams().row;
                    	//系统停止时间 记录，用于对比
                    	me.systemEndDate = row[0].systemExpiryDate;
                    	//初始化按钮
                		MainFrameUtil.setDialogButtons([
                		    {text:"保存", type:"primary", handler:this.save},
                		    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
                		]);
                		//版本下拉数据资源
                		me.initVersionSelectData();
                		//页面赋值
                    	me.params.systemcompanyContract = row[0];
                    	me.params.province = row[0].province;
                    	me.params.companyName = row[0].companyName; 
                    	me.versionDataSource = row[0].versionCode.split(',');
                    },
                    methods: {
                    	//版本下拉数据资源
                    	initVersionSelectData: function(){
                    		var me = this;
                    		$.ajax({
                      	 		 url:"${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/platform_version",
                                type:"get",
                                async: false,
                                success:function(data){
                               	//版本 01-05 ，初始时版本只显示：极简版（就自己）、基础版（剩余所有可选），勾选了对应的在显示对应剩余的
                      	    		if(data != null && data.length > 0){
                          				for(var i in data){
                          					//基础版本
                          					if(data[i].value == '02'){
                          						me.initVersionList.push(data[i]);
                          					}
                          					//极简版
                          					if(data[i].value == '01'){
                          						me.initVersionList.push(data[i]);
                          						me.easyVersionList.push(data[i]);
                          					}
                          					// 不是极简版
                          					if(data[i].value != '01'){
                          						me.baseVersionList.push(data[i]);
                          					}
                          					//初始版本
                          					me.versionDataSource = me.initVersionList;
                      		 			}
                          			}
                                 }
                            	});
                    	},
                        save: function(){
	                        var me = this;
	                        this.$refs.form1.valid();
	                        if(this.valid===false){
	                    		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
	                    		return;
	                    	}
                   			if(me.params.systemcompanyContract.systemExpiryDate > this.systemEndDate){
                   				MainFrameUtil.alert({title:'提示',body:'系统停止时间只可改小不可改大，请发起续期流程更改系统停止时间。'});
   	                    		return;
                   			}
                   			if(me.params.systemcompanyContract.systemEffectiveDate > me.params.systemcompanyContract.systemExpiryDate){
                   				MainFrameUtil.alert({title:'提示',body:'系统开通时间不能大于系统停止时间！'});
	                    		return;
                   			}
                   			if(me.params.systemcompanyContract.effectiveDate > me.params.systemcompanyContract.expiryDate){
                   				MainFrameUtil.alert({title:'提示',body:'合同开始时间不能大于合同结束时间！'});
	                    		return;
                   			}
	                        if(me.params.systemcompanyContract.accountPassword != null && me.params.systemcompanyContract.accountPassword.length > 128){
	                        	MainFrameUtil.alert({title:'提示',body:'账号密码内容请在128字之内！'});
	                    		return;
	                        }
	                        if(me.params.systemcompanyContract.remark != null && me.params.systemcompanyContract.remark.length > 1024){
	                        	MainFrameUtil.alert({title:'提示',body:'备注内容请在1024字之内！'});
	                    		return;
	                        }
	                        var str = "";
	                        if(me.params.systemcompanyContract.systemExpiryDate != null && me.params.systemcompanyContract.systemExpiryDate != ''){
	                        	str = "系统停止时间保存后不可轻易修改，"
	                        }
	                    	MainFrameUtil.confirm({
	    				        title:"确认",
	    				        body: str +"是否确认保存？",
	    				        onClose:function(type){
	    				            if(type=="ok"){//确定
	    				            	  $.ajax({
	    		                               url : "${Config.baseURL}cloud-purchase-web/systemcompanyContractController/save",
	    		                               type : 'post',
	    		                               data: $.toJSON(me.params),
	    		                               contentType : 'application/json;charset=UTF-8',
	    		                               success : function(result) {
	    		                                      if(result.flag){
	    		                                             MainFrameUtil.alert({title:"提示",body:"保存成功！"});
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
                    	//用户类型
                    	"params.systemcompanyContract.consTypeCode": function(val){
                    		var me = this;
                    		//试用用户：则系统版本、系统开通、系统停止时间、合同开始结束时间、账号密码不可维护
                    		if(val == '04'){
                    			me.versionCodeFlag = true;
                    			me.systemEffectiveDateFlag = true;
                    			me.systemExpiryDateFlag = true;
                    			me.effectiveDateFlag = true;
                    			me.expiryDateFlag = true;
                    			me.accountPasswordFlag = true;
                    		}else{
                    			me.versionCodeFlag = false;
                    			me.systemEffectiveDateFlag = false;
                    			me.systemExpiryDateFlag = false;
                    			me.effectiveDateFlag = false;
                    			me.expiryDateFlag = false;
                    			me.accountPasswordFlag = false;
                    		}
                    	},
                    	//版本
                    	"params.systemcompanyContract.versionCode": function(val){
                    		var me = this;
                    		if(val == null || val == ''){
                    			me.versionDataSource = me.initVersionList;
                    		}else if(val.indexOf('01') >= 0){
                    			//极简版，剩下的选项不可选
                    			me.versionDataSource = me.easyVersionList;
                    		}else if(val.indexOf('02') >= 0){
                    			//基础版，那么可以叠加选择电量采集、大数据预测、网上营业厅；不选择基础版，则不可选择电量采集、大数据预测、网上营业厅
                    			me.versionDataSource = me.baseVersionList;
                    		}else if(val.indexOf('01') < 0 && val.indexOf('02') < 0){
                    			me.versionDataSource = this.initVersionList;
                    		}
                    	},
                    	//系统停止时间已维护，则不时间可改小！不可改大！
                    	"params.systemcompanyContract.systemExpiryDate": function(val){
                    		if(val != null && val != ''){
                    			if(val > this.systemEndDate){
                    				MainFrameUtil.alert({title:'提示',body:'系统停止时间只能改小不能改大！'});
    	                    		return;
                    			}
                    		}
                    		
                    	}
                    }
             });
       </script>
</div>
</body>
</html>