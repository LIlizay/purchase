<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
%>
<html>
<head>
<title>档案管理-电厂档案新增/编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="addBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="电厂基本信息">
		<mk-form-row>
			<!-- 电厂名称 基本信息 -->
	        <mk-form-col :label="formFields.elecName.label" required_icon="true">
	        	<su-textbox :data.sync="params.phcElecInfo.elecName" name="elecName" ></su-textbox>
	        </mk-form-col>
			<!-- 公用/自备 基本信息 -->
	        <mk-form-col :label="formFields.modeCode.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_modeCode" multiple="false" 
				:select-value.sync="params.phcElecInfo.modeCode" label-name="name" name="modeCode"></su-select>
	        </mk-form-col>
	        <!-- 发电类别 基本信息 -->
	        <mk-form-col :label="formFields.elecTypeCodeName.label" required_icon="true">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType" multiple="false" 
				:select-value.sync="params.phcElecInfo.elecTypeCode" label-name="name" name="elecTypeCode"></su-select>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
			<!-- 总装机容量  基本信息 -->
	        <mk-form-col :label="formFields.instCapForm.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.instCap" name="instCap" :addon="{'show':true,'rightcontent':'兆瓦'}" ></su-textbox>
	        </mk-form-col>
			<!-- 交易中心注册时间 -->
	        <mk-form-col :label="formFields.registDate.label">
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.registDate" name="registDate" ></su-datepicker>
	        </mk-form-col>
	        <!-- 所属发电集团 -->
	        <mk-form-col :label="formFields.blocIdName.label" >
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecGenerationGroup" multiple="false" 
				:select-value.sync="params.phcElecInfo.blocId" label-name="name"></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
            <!-- 所属区域 省  基本信息-->
	        <mk-form-col :label="formFields.provinceCode.label" required_icon="true">
	        	<su-select url="${Config.baseURL}/globalCache/queryProvinceList/" multiple="false" 
				 :select-value.sync="params.phcElecInfo.provinceCode" name="provinceCode" label-name="name"></su-select>
	        </mk-form-col>
			<!-- 市  基本信息-->
	        <mk-form-col :label="formFields.cityCode.label">
	        	<su-select :data-source="cityList" multiple="false" :select-value.sync="params.phcElecInfo.cityCode" name="cityCode" label-name="name"></su-select>
	        </mk-form-col>
	        <!-- 区/县 基本信息 -->
	        <mk-form-col :label="formFields.countyCode.label" >
	        	<su-select :data-source="countyList" multiple="false" :select-value.sync="params.phcElecInfo.countyCode" name="countryCode" label-name="name"></su-select>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 是否统调电厂 -->
	        <mk-form-col :label="formFields.unifyFlag.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" multiple="false" 
				 :select-value.sync="params.phcElecInfo.unifyFlag" name="unifyFlag" label-name="name"></su-select>
	        </mk-form-col>
			<!-- 调度关系 -->
<!-- 	        <mk-form-col :label="formFields.schedulRelation.label" required_icon="true"> -->
<%-- 	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_schedulRelation" multiple="false"  --%>
<!-- 				 :select-value.sync="params.phcElecInfo.schedulRelation" name="schedulRelation" label-name="name"></su-select> -->
<!-- 	        </mk-form-col> -->
	        <!-- 最低技术出力 -->
	        <mk-form-col :label="formFields.minTechnology.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.minTechnology" name="minTechnology" :addon="{'show':true,'rightcontent':'兆瓦'}" ></su-textbox>
	        </mk-form-col>
			<!-- 最高技术出力 -->
	        <mk-form-col :label="formFields.maxTechnology.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.maxTechnology" name="maxTechnology" :addon="{'show':true,'rightcontent':'兆瓦'}" ></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
	        <!-- 最大电煤库存 -->
	        <mk-form-col :label="formFields.maxInventory.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.maxInventory" name="maxInventory" :addon="{'show':true,'rightcontent':'吨'}" ></su-textbox>
	        </mk-form-col>
	        <!-- 是否网内电厂 -->
<!-- 	        <mk-form-col :label="formFields.networkFlag.label" required_icon="true"> -->
<%-- 	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" multiple="false"  --%>
<!-- 				 :select-value.sync="params.phcElecInfo.networkFlag" name="networkFlag" label-name="name"></su-select> -->
<!-- 	        </mk-form-col> -->
        </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="电厂三证信息">
		<mk-form-row>
            <!-- 税务登记号 -->
	        <mk-form-col :label="formFields.taxRegistNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.taxRegistNo" name="taxRegistNo"></su-textbox>
	        </mk-form-col>
			<!-- 法人代表 -->
	        <mk-form-col :label="formFields.legalPerson.label">
	        	<su-textbox :data.sync="params.phcElecInfo.legalPerson" name="legalPerson"></su-textbox>
	        </mk-form-col>
	        <!-- 增值税率 -->
	        <mk-form-col :label="formFields.educationalTariff.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.educationalTariff" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 法定地址 -->
            <mk-form-col :label="formFields.registAddr.label" colspan="2">
            	<su-textbox :data.sync="params.phcElecInfo.registAddr" name="registAddr"></su-textbox>
            </mk-form-col>
            <!-- 许可证颁发机构 -->
	        <mk-form-col :label="formFields.issuingAgency.label">
	        	<su-textbox :data.sync="params.phcElecInfo.issuingAgency" name="issuingAgency"></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 注册工商局所在地 -->
            <mk-form-col :label="formFields.saicAddr.label" colspan="3" >
            	<su-textbox :data.sync="params.phcElecInfo.saicAddr" ></su-textbox>
            </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 许可证编号 -->
	        <mk-form-col :label="formFields.permitNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.permitNo" name="permitNo"></su-textbox>
	        </mk-form-col>
			<!-- 许可证失效时间 -->
	        <mk-form-col :label="formFields.expireDate.label" >
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.expireDate" ></su-datepicker>
	        </mk-form-col>
	        <!-- 工商局注册时间 -->
	        <mk-form-col :label="formFields.registDate2.label" >
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.registDate2" ></su-datepicker>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 开户银行 -->
	        <mk-form-col :label="formFields.bankName.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.bankName" name="bankName"></su-textbox>
	        </mk-form-col>
			<!-- 开户行账号 -->
	        <mk-form-col :label="formFields.bankNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.bankNo" name="bankNo"></su-textbox>
	        </mk-form-col>
	        <!-- 开户名称 -->
	        <mk-form-col :label="formFields.accountName.label">
	        	<su-textbox :data.sync="params.phcElecInfo.accountName" name="accountName"></su-textbox>
	        </mk-form-col>
        </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="电厂联系人信息">
		<mk-form-row>
            <!-- 联系人 -->
	        <mk-form-col :label="formFields.contName.label">
	        	<su-textbox :data.sync="params.phcContactsInfo.contName" name="contName"></su-textbox>
	        </mk-form-col>
			<!-- 联系电话 -->
	        <mk-form-col :label="formFields.phone.label">
	        	<su-textbox :data.sync="params.phcContactsInfo.phone" name="phone"></su-textbox>
	        </mk-form-col>
	        <!-- 电子邮件 -->
	        <mk-form-col :label="formFields.eMail.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.eMail" ></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
        	<!-- 传真号码 -->
	        <mk-form-col :label="formFields.fax.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.fax" ></su-textbox>
	        </mk-form-col>
			<!-- 通讯地址 -->
	        <mk-form-col :label="formFields.addr.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.addr" ></su-textbox>
	        </mk-form-col>
	        <!-- 邮政编码 -->
	        <mk-form-col :label="formFields.postcode.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.postcode" ></su-textbox>
	        </mk-form-col>
        </mk-form-row>
	</mk-form-panel>
</su-form>
<mk-panel title="机组信息列表" line="true"  id="gridTitle">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" @click="addSet">新增机组</su-button>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" @click="editSet">编辑机组</su-button>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" @click="delSet">删除机组</su-button>
    </div>
    <div class="row">
        <table id="setGrid"></table>
    </div>
</mk-panel> 
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var cloudsellingProvince = "${cloudsellingProvince}";		//数据库配置的省码
$.validator.addMethod(     
	"isNum",        
	function(value, element)        
	{            
	        var tel = /^([^0\D]\d*)?(\d?\.\d+)?0?$/;  
	        return this.optional(element) || (tel.test(value));     
	}     
	, "请填写正确格式的数字！"
);  
var addVue = new Vue({
	el:"#addBody",
	data:{
		formFields:{},
		params:{
			phcElecInfo:{
				elecName:null,modeCode:null,elecTypeCode:null,instCap:null,registDate:null,blocId:null,provinceCode:null,cityCode:null,countyCode:null,
				unifyFlag:null,schedulRelation:null,minTechnology:null,maxTechnology:null,maxInventory:null,networkFlag:null,taxRegistNo:null,
				legalPerson:null,educationalTariff:null,registAddr:null,issuingAgency:null,saicAddr:null,permitNo:null,expireDate:null,
				registDate2:null,bankName:null,bankNo:null,accountName:null
			},
			phcContactsInfo:{
				contName:null,phone:null,eMail:null,fax:null,addr:null,postcode:null
			},
		},
		list:[],
		deleteList:[],
		dataOption: {
			rules: {
				elecName: "required",
				elecTypeCode:"required",
				instCap: {
					isNum:!0
				},
				provinceCode:"required",
				schedulRelation:"required",
				networkFlag:"required",
				minTechnology:{isNum:!0},
				maxTechnology:{isNum:!0},
				maxInventory:{isNum:!0}
			}
		},
		valid: false,
		cityList:[],
		countyList:[],
		cityFlag:true,//是否把市置空
		countyFlag:true, //是否把县置空
		loadFlag:false //表格是否加载完成
	},
	ready:function(){
		var me  = this;
		var id = MainFrameUtil.getParams('add').id;
		if(typeof(id)!=='undefined'){
			$.ajax({
				url:basePath + 'cloud-purchase-web/powerPlantController/'+id,
				type:"get",
				success:function(data){
					if(data.flag){
						var powerPlant = data.data;
						me.cityFlag = false;
        				me.countyFlag = false;
						me.params.phcElecInfo = powerPlant.phcElecInfo;
						me.params.phcContactsInfo = powerPlant.phcContactsInfo;
						me.list = powerPlant.detailList;
						if(me.loadFlag){
							$("#setGrid").datagrid("loadData",me.list);
						}
					}else{
						MainFrameUtil.alert({title:"失败",body:data.msg});
					}
				}
			})
		}
		var methods={"save":me.save};
        MainFrameUtil.setParams(methods,'add');
        ComponentUtil.getFormFields("purchase.archives.powerplant",this);
        ComponentUtil.buildGrid("purchase.archives.powerplantset",$("#setGrid"),{ 
        	data:[],
		    height:350,
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    onLoadSuccess:function(){
		    	$("#setGrid").datagrid("options").onLoadSuccess = function(){};
		    	me.loadFlag = true;
		    	$("#setGrid").datagrid("loadData",me.list);
		    }
	    }); 
      //system_config取省码，如果没有，取当前登录人orgCode。如果还取不到，取山东
        //省 初始值
		if(cloudsellingProvince == null || ''== cloudsellingProvince){
			var userInfo = $.getLoginUserInfo(basePath);
			if(userInfo.orgCode != null && userInfo.orgCode !=''){
				this.params.phcElecInfo.provinceCode = (userInfo.orgCode).substr(0,2) + '0000';
			}else{
				//山东
				this.params.phcElecInfo.provinceCode = '370000';
			}
			
		}else{
			this.params.phcElecInfo.provinceCode = cloudsellingProvince;
		}
	},
	methods:{
		save:function(){
			var me = this;
			this.$refs.form1.valid();
			if(this.valid===false){
        		MainFrameUtil.alert({title:"提示",body:"您有必填项未填写！"})
           		return false;
        	}
			if(!this.validatepostcode(me.params.phcContactsInfo.postcode)){//验证邮编
	           	return;
	        }
        	this.params.list = me.list;
        	this.params.deleteList = me.deleteList;
        	$.ajax({
        		url: basePath + 'cloud-purchase-web/powerPlantController',
        		type:'post',
        		data:$.toJSON(this.params),
        		contentType : 'application/json;charset=UTF-8',
        		success:function(data){
        			if(data.flag){
        				MainFrameUtil.alert({title:"提示",body:data.msg});
        				var params = data.data;
        				me.params.phcElecInfo.id = params.phcElecInfo.id;
        				me.params.phcContactsInfo.id = params.phcContactsInfo.id;
        				for(var i in me.params.list){
        					me.list[i].id = params.list[i].id;
        				}
        				me.deleteList = [];
        			}else{
        				MainFrameUtil.alert({title:"失败",body:data.msg});
        			}
        		}
        	})
        	
		},
		addSet:function(){
			var networkFlag = '';
			if(this.params.phcElecInfo.networkFlag=="1"){
				networkFlag = "1"
			}
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'addSet',
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/addset',
				iframe:true,
				modal:true,
				width:1000,
				height:600,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	var setData = params.save();
							if(setData){
								MainFrameUtil.closeDialog('addSet');
								me.list.unshift(setData);
								$("#setGrid").datagrid("loadData",me.list);
	                    	}
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('addSet');
	                    }
	                }]
			});
		},
		editSet:function(){
			var me = this;
			var row = $("#setGrid").datagrid("getSelected");
			if(row === null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			var index = $("#setGrid").datagrid("getRowIndex",row);
			MainFrameUtil.openDialog({
	  			id:'addSet',
	  			params:{row:row},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/addset',
				iframe:true,
				modal:true,
				width:1000,
				height:600,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	var params = params.save();
							if(params){
								MainFrameUtil.closeDialog('addSet');
								me.list[index] = params;
								$("#setGrid").datagrid("loadData",me.list);
	                    	}
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('addSet');
	                    }
	                }]
			});
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
		delSet:function(){
			var me = this;
			var row = $("#setGrid").datagrid("getSelected");
			if(row === null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			var index = $("#setGrid").datagrid("getRowIndex",row);
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要删除选中记录吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		var row = me.list.splice(index,1);
      	        		if(row[0].id){
      	        			me.deleteList.push(row[0].id);
      	        		}
      	        		$("#setGrid").datagrid("loadData",me.list);
      	        	}
      	        }
  	    	}); 
		}
	},
	watch:{
		'params.phcElecInfo.provinceCode':function(value){
			var me = this;
			if(me.cityFlag){
				me.params.phcElecInfo.cityCode = null;
			}else{
				me.cityFlag = true;
			}
			if(value){
				$.ajax({
					url:basePath+"/globalCache/queryCityByParentCode/"+value,
					type:'get',
					success:function(data){
						me.cityList = data;
					}
				})
			}else{
				me.cityList = [];
			}
		},
		'params.phcElecInfo.cityCode':function(value){
			var me = this;
			if(me.countyFlag){
				me.params.phcElecInfo.countyCode = '';
			}else{
				me.cityFlag = true;
			}
			if(value){
				$.ajax({
					url:basePath+"/globalCache/queryCountyByParentCode/"+value,
					type:'get',
					success:function(data){
						me.countyList = data;
					}
				})
			}else{
				me.countyList = [];
			}
		}
	}
})
</script>
</body>
</html>