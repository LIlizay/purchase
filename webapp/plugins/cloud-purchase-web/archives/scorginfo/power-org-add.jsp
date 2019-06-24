<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>档案管理-供电公司档案新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	<su-form v-ref:add_power_org offpos-style="true" id="fms1" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel num="2">
			<mk-form-row>
	            <!-- 供电公司名称 -->
	            <mk-form-col required_icon="true" :label="formFields.name.label" :class="{'display-none':!formFields.name.formHidden}" >             
	                <su-textbox name="formName" :data.sync="params.name"></su-textbox>
	            </mk-form-col>
	            <!-- 所在省： -->
	            <mk-form-col required_icon="true" :label="formFields.provinceCode.label" :class="{'display-none':!formFields.provinceCode.formHidden}" >             
	                <su-select name="formProvinceCode" id="province" :select-value.sync="params.provinceCode" :high="150" label-name="name"
	                       url="${Config.baseURL}globalCache/queryProvinceList" @su-change="setCityOrCountryList(true)"></su-select>
	            </mk-form-col>
	        </mk-form-row>
		    <mk-form-row>
	            <!-- 所在市： -->
	            <mk-form-col required_icon="true" :label="formFields.cityCode.label" :class="{'display-none':!formFields.cityCode.formHidden}">
	                <su-select name="formCityCode" id="city" :data-source="cityCodeList" :data.sync="cityCodeVal" :high="150" 
	                    @su-change="selVal('params.cityCode')" label-name="name"></su-select>
	            </mk-form-col>
	            <!-- 所在区县： -->
	            <mk-form-col required_icon="true" :label="formFields.countyCode.label" :class="{'display-none':!formFields.countyCode.formHidden}">
	                 <su-select name="formCountyCode" id="county" :data-source="countyCodeList" :data.sync="countyCodeVal" :high="150" 
	                    @su-change="selVal('params.countyCode')" label-name="name"></su-select>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	            <!-- 增值税名称 -->
	            <mk-form-col :label="formFields.vatName.label" :class="{'display-none':!formFields.vatName.formHidden}">
	                 <su-textbox :data.sync="params.vatName"></su-textbox>
	            </mk-form-col>
	            <!-- 纳税人识别号 -->
	            <mk-form-col   :label="formFields.vatNo.label" :class="{'display-none':!formFields.vatNo.formHidden}"> 
	                 <su-textbox :data.sync="params.vatNo"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	         	<!-- 开户行 -->
	            <mk-form-col   :label="formFields.bankName.label" :class="{'display-none':!formFields.bankName.formHidden}"> 
	                 <su-textbox :data.sync="params.bankName"></su-textbox>
	            </mk-form-col>
	             <!-- 开户行账号 -->
	            <mk-form-col   :label="formFields.vatAcct.label" :class="{'display-none':!formFields.vatAcct.formHidden}"> 
	                 <su-textbox :data.sync="params.vatAcct"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
	        <mk-form-row>
	         	<!-- 注册地址 -->
	            <mk-form-col   :label="formFields.regAddr.label" :class="{'display-none':!formFields.regAddr.formHidden}" colspan="2"> 
	                 <su-textbox :data.sync="params.regAddr"></su-textbox>
	            </mk-form-col>
	        </mk-form-row>
		</mk-form-panel>
	</su-form>
<script>
	var basePath="${Config.baseURL}";
	var addOrgVue=new Vue({
	    el: 'body',
	    data:{
	        formFields:{},
	        cityCodeList:[],// 行政区
	        cityCodeVal:[],// 行政区
	        countyCodeList:[],// 区（县）
	        countyCodeVal:[],// 区（县）
	        
	        params:{id:null,provinceCode:'',cityCode:'',countyCode:'',
	        	streetCode:'',villageCode:'',communityCode:'',plateNo:''},
    		dataOption: {
   				rules: {
   					formName:{required: !0},
   					formProvinceCode:{required: !0},
					formCityCode:{required: !0},
 					formCountyCode:{required: !0},
   				}
			},
			valid:false
	    },
	    ready:function(){
	    	ComponentUtil.getFormFields("selling.archives.powerOrg",this);
            MainFrameUtil.setDialogButtons(this.getButtons(),"addPowerOrgDialog");
        },
	    watch:{
	    	//监听市下拉列表的变化，改变是去重新加载区县的下拉数据
	        'cityCodeVal':function(value,oldVal){
	            this.setCityOrCountryList(false);
	        }
	    },
		methods : {
			//获取市或区县的下拉数据，若sign为true则获取市的下拉数据，若false则获取区县的下拉数据
			setCityOrCountryList:function(sign){
	            var code = this.params.provinceCode;   //获取刚选中的值
	            var url = '';
	            if(!sign){
	            	code = this.cityCodeVal[0];
	            	url = "/globalCache/queryCountyByParentCode/";
	            }else{
	            	url = "/globalCache/queryCityByParentCode/";
	            }
	            if(code != null && $.trim(code) != ""){        //若选中了省或市，则根据代码取得下拉数据
	            	$.ajax({
	                    url : basePath+url+code,
	                    type : 'get',
	                    success : function(data) {
	                    	if(sign){     //点击了省的下拉
	                    		addOrgVue.$set("cityCodeList", eval(data));
	                    	}else{        //点击了市的下拉
	                    		addOrgVue.$set("countyCodeList", eval(data));
	                    	}
	                    }
	                });
	            }else{         //取消选中，则清空下拉数据
	            	if(sign){       //如果取消选中的是省，则市、县的下拉数据都清空
                        addOrgVue.$set("cityCodeList", []);
                        addOrgVue.$set("cityCodeVal", []);
                    }
                    addOrgVue.$set("countyCodeList", []);
                    addOrgVue.$set("countyCodeVal", []);
	            }
	            if(sign){  //如果点击的是省，把县的下拉数据清空
	            	addOrgVue.$set("countyCodeList", []);
	            }
	        },
	        //下拉组件的赋值
	        selVal:function(key){
	            if(key.indexOf(".") != -1){
	                var keys = key.split(".");
	                addOrgVue.$set(key,eval("this."+keys[keys.length-1]+"Val[0]"));
	            }else{
	                addOrgVue.$set("params."+key,eval("this."+key+"Val[0]"));
	            }
	        },
	        getButtons:function(){
                var buttons=[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	/* 表单验证 */
                    	addOrgVue.$refs.add_power_org.valid();
        	            if(addOrgVue.valid===false){
        	                return false;
        	            };
        	            $.messager.progress();     //打开进度条
        	            $.ajax({
                            url : "${Config.baseURL}cloud-purchase-web/scOrgInfoController",
                            type : "post",
                            data:$.toJSON(addOrgVue.params),
            				contentType : 'application/json;charset=UTF-8',
                            success : function(data) {
                            	$.messager.progress('close');  //关闭进度条
                                if(data.flag == true){
                                	MainFrameUtil.alert({title:"成功提示",body:data.msg}); 
                                	MainFrameUtil.closeDialog("addPowerOrgDialog");
                                }else{
                                	MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
                                }
                            },
                            error : function() {
                            	$.messager.progress('close');  //关闭进度条
                            	MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
                            }
                        });
                    }
                },{
                    text:"取消",
                    handler:function(btn,params){
                        MainFrameUtil.closeDialog("addPowerOrgDialog");
                    }
                }];
                return buttons;
            }
		}
	});
</script>
</body>
</html>