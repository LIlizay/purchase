<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>档案管理-电厂档案信息查看</title>
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
			<!-- 电厂名称 -->
	        <mk-form-col :label="formFields.elecName.label" required_icon="true">
	        	<su-textbox :data.sync="params.phcElecInfo.elecName" name="elecName" disabled></su-textbox>
	        </mk-form-col>
			<!-- 公用/自备 -->
	        <mk-form-col :label="formFields.modeCode.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_modeCode" multiple="false" 
				:select-value.sync="params.phcElecInfo.modeCode" label-name="name" name="modeCode" disabled></su-select>
	        </mk-form-col>
	        <!-- 发电类别 -->
	        <mk-form-col :label="formFields.elecTypeCodeName.label" required_icon="true">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType" multiple="false" 
				:select-value.sync="params.phcElecInfo.elecTypeCode" label-name="name" name="elecTypeCode" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
			<!-- 总装机容量 -->
	        <mk-form-col :label="formFields.instCapForm.label">
	        	<su-textbox :data.sync="params.phcElecInfo.instCap" name="instCap" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled></su-textbox>
	        </mk-form-col>
			<!-- 交易中心注册时间 -->
	        <mk-form-col :label="formFields.registDate.label" >
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.registDate" name="registDate" disabled></su-datepicker>
	        </mk-form-col>
	        <!-- 所属发电集团 -->
	        <mk-form-col :label="formFields.blocIdName.label" >
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecGenerationGroup" multiple="false" 
				:select-value.sync="params.phcElecInfo.blocId" label-name="name" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
            <!-- 所属区域 省 -->
	        <mk-form-col :label="formFields.provinceCode.label" required_icon="true">
	        	<su-select url="${Config.baseURL}/globalCache/queryProvinceList/" multiple="false" 
				 :select-value.sync="params.phcElecInfo.provinceCode" name="provinceCode" label-name="name" disabled></su-select>
	        </mk-form-col>
			<!-- 市 -->
	        <mk-form-col :label="formFields.cityCode.label">
	        	<su-select :data-source="cityList" multiple="false"  :select-value.sync="params.phcElecInfo.cityCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 区/县 -->
	        <mk-form-col :label="formFields.countyCode.label">
	        	<su-select :data-source="countyList" multiple="false" :select-value.sync="params.phcElecInfo.countyCode" 
	        	label-name="name" disabled></su-select>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 是否统调电厂 -->
	        <mk-form-col :label="formFields.unifyFlag.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" multiple="false" 
				 :select-value.sync="params.phcElecInfo.unifyFlag" name="unifyFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
			<!-- 调度关系 -->
<!-- 	        <mk-form-col :label="formFields.schedulRelation.label" required_icon="true"> -->
<%-- 	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_schedulRelation" multiple="false"  --%>
<!-- 				 :select-value.sync="params.phcElecInfo.schedulRelation" name="schedulRelation" label-name="name" disabled></su-select> -->
<!-- 	        </mk-form-col> -->
	        <!-- 最低技术出力 -->
	        <mk-form-col :label="formFields.minTechnology.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.minTechnology" name="minTechnology" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled></su-textbox>
	        </mk-form-col>
			<!-- 最高技术出力 -->
	        <mk-form-col :label="formFields.maxTechnology.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.maxTechnology" name="maxTechnology" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
	        <!-- 最大电煤库存 -->
	        <mk-form-col :label="formFields.maxInventory.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.maxInventory" name="maxInventory" :addon="{'show':true,'rightcontent':'吨'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 是否网内电厂 -->
<!-- 	        <mk-form-col :label="formFields.networkFlag.label" required_icon="true"> -->
<%-- 	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" multiple="false"   --%>
<!-- 				 :select-value.sync="params.phcElecInfo.networkFlag" name="networkFlag" label-name="name" disabled></su-select> -->
<!-- 	        </mk-form-col> -->
        </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="电厂三证信息">
		<mk-form-row>
            <!-- 税务登记号 -->
	        <mk-form-col :label="formFields.taxRegistNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.taxRegistNo" name="taxRegistNo" disabled></su-textbox>
	        </mk-form-col>
			<!-- 法人代表 -->
	        <mk-form-col :label="formFields.legalPerson.label">
	        	<su-textbox :data.sync="params.phcElecInfo.legalPerson" name="legalPerson" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 增值税率 -->
	        <mk-form-col :label="formFields.educationalTariff.label" >
	        	<su-textbox :data.sync="params.phcElecInfo.educationalTariff" :addon="{'show':true,'rightcontent':'吨'}" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 法定地址 -->
            <mk-form-col :label="formFields.registAddr.label" colspan="2" >
            	<su-textbox :data.sync="params.phcElecInfo.registAddr" name="registAddr" disabled></su-textbox>
            </mk-form-col>
            <!-- 许可证颁发机构 -->
	        <mk-form-col :label="formFields.issuingAgency.label">
	        	<su-textbox :data.sync="params.phcElecInfo.issuingAgency" name="issuingAgency" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 注册工商局所在地 -->
            <mk-form-col :label="formFields.saicAddr.label" colspan="3" >
            	<su-textbox :data.sync="params.phcElecInfo.saicAddr" disabled></su-textbox>
            </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 许可证编号 -->
	        <mk-form-col :label="formFields.permitNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.permitNo" name="permitNo" disabled></su-textbox>
	        </mk-form-col>
			<!-- 许可证失效时间 -->
	        <mk-form-col :label="formFields.expireDate.label" >
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.expireDate" disabled></su-datepicker>
	        </mk-form-col>
	        <!-- 工商局注册时间 -->
	        <mk-form-col :label="formFields.registDate2.label" >
	        	 <su-datepicker format="YYYY-MM-DD" :data.sync="params.phcElecInfo.registDate2" disabled></su-datepicker>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 开户银行 -->
	        <mk-form-col :label="formFields.bankName.label">
	        	<su-textbox :data.sync="params.phcElecInfo.bankName" name="bankName" disabled></su-textbox>
	        </mk-form-col>
			<!-- 开户行账号 -->
	        <mk-form-col :label="formFields.bankNo.label">
	        	<su-textbox :data.sync="params.phcElecInfo.bankNo" name="bankNo" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 开户名称 -->
	        <mk-form-col :label="formFields.accountName.label">
	        	<su-textbox :data.sync="params.phcElecInfo.accountName" name="accountName" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="电厂联系人信息">
		<mk-form-row>
            <!-- 联系人 -->
	        <mk-form-col :label="formFields.contName.label">
	        	<su-textbox :data.sync="params.phcContactsInfo.contName" name="contName" disabled></su-textbox>
	        </mk-form-col>
			<!-- 联系电话 -->
	        <mk-form-col :label="formFields.phone.label">
	        	<su-textbox :data.sync="params.phcContactsInfo.phone" name="phone" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 电子邮件 -->
	        <mk-form-col :label="formFields.eMail.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.eMail" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
        <mk-form-row>
        	<!-- 传真号码 -->
	        <mk-form-col :label="formFields.fax.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.fax" disabled></su-textbox>
	        </mk-form-col>
			<!-- 通讯地址 -->
	        <mk-form-col :label="formFields.addr.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.addr" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 邮政编码 -->
	        <mk-form-col :label="formFields.postcode.label" >
	        	<su-textbox :data.sync="params.phcContactsInfo.postcode" disabled></su-textbox>
	        </mk-form-col>
        </mk-form-row>
	</mk-form-panel>
</su-form>
<mk-panel title="机组信息列表" line="true" id="gridTitle">
    <div class="row">
        <table id="setGrid"></table>
    </div>
</mk-panel> 
<script type="text/javascript">
var basePath = '${Config.baseURL}';
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
		cityList:[],
		countyList:[],
		loadFlag:false 
	},
	ready:function(){
		var me  = this;
		/* var htm = $("#gridTitle .pull-left-line").html();
		htm = "<span style='color:red'>*</span>"+htm;
		$("#gridTitle .pull-left-line").html(htm); */
		var id = MainFrameUtil.getParams('detail').id;
		if(typeof(id)!=='undefined'){
			$.ajax({
				url:basePath + 'cloud-purchase-web/powerPlantController/'+id,
				type:"get",
				success:function(data){
					if(data.flag){
						var powerPlant = data.data;
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
	},
	methods:{
		geneNameFormatter:function(value,row,text){
			return "<a onclick=\"addVue.detail('"+row.id+"')\">"+value+"</a>"
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"setdetail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/setdetail',
				iframe:true,
				modal:true,
				width:1000,
				height:620,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('setdetail');
                    }
                }]
			});
		}
	},
	watch:{
		'params.phcElecInfo.provinceCode':function(value){
			var me = this;
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