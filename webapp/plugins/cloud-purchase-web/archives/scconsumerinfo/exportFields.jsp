<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户档案导出字段选择</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>
</head>
<body>
<div id="exportFieldsVue" class="height-fill" v-cloak>
    <mk-panel title="导出字段" line="true" slot="bottom" height="100%">
        <div class="row"  style="height: 100%">
            <table id="fieldsGrid"></table>
        </div>
        
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var exportFieldsVue = new Vue({
	el: '#exportFieldsVue',
	data: {
    	queryparams:{ScConsumerInfoVo:{scConsumerInfo:{},consIds: null},flag:false},
	},
	ready:function(){
		var me = this;
		var params = MainFrameUtil.getParams('export-field');
		//数组，为手动选择要导出的数据
		me.queryparams.ScConsumerInfoVo = MainFrameUtil.getParams('export-field');
		var methods={"confirmHandler":this.confirm};
		MainFrameUtil.setParams(methods,"export-field");
		$("#fieldsGrid").datagrid({
            width:"100%",
            height:"100%",
            method: 'post',
            rownumbers: true,
            pagination: false,
            nowrap: false, 
            singleSelect:false,
            fitColumns:true,
            columns:[[
                 {field:'ck',checkbox :true},
                 {field:'name',title:'字段名称',width:80},
                 {field:'code',hidden:true}
             ]],
            data: [
           		{name:'用户名称',code:'consName'},{name:'用户类型',code:'consTypeName'},{name:'公示序列号',code:'dealSeq'},{name:'用电行业分类',code:'industryTypeName'},
           		{name:'所属供电公司',code:'orgName'},{name:'所属园区',code:'industrialZoneName'},{name:'省',code:'provinceName'},{name:'市',code:'cityName'},{name:'区/县',code:'countyName'},
           		{name:'是否完成信息采集',code:'scadaFlagName'},{name:'市场状态',code:'marketStatusName'},{name:'是否直购电用户',code:'directPowerName'},{name:'用电地址',code:'elecAddr'},{name:'营业执照注册号',code:'registrationNo'},
           		{name:'税务登记号',code:'vatNo'},{name:'组织机构代码',code:'orgCode'},{name:'法人名称',code:'legalAgent'},{name:'法人代表名称',code:'authAgent'},
           		{name:'企业注册地址',code:'registeredAddress'},{name:'开户银行',code:'bankName'},{name:'开户名称',code:'countName'},{name:'开户账号',code:'countNo'},{name:'用电容量（KVA)',code:'electricCapacity'},
           		{name:'用电类别',code:'elecTypeName'},{name:'电压等级',code:'voltCodeName'},{name:'负荷性质',code:'lodeAttrName'},{name:'安排年度上限（亿千瓦时）',code:'upperPq'},{name:'营销户号',code:'marketConsNo'},
           		{name:'计量点编号',code:'mpNo'},{name:'计量点名称',code:'mpName'},{name:'电能表编号',code:'meterNo'},{name:'电能表倍率',code:'meterRate'},{name:'电能表位数',code:'meterDigits'},
           		{name:'上级单位名称',code:'parentName'},{name:'上级单位用电类别',code:'parentElecTypeName'},{name:'上级单位电压等级',code:'parentVoltCodeName'},{name:'联系人名称',code:'contName'},{name:'职务（联系人）',code:'post'},
           		{name:'手机号（联系人）',code:'phone'},{name:'办公电话（联系人）',code:'officePhone2'},{name:'传真号（联系人）',code:'fax'},{name:'电子邮箱（联系人）',code:'eMail'},{name:'通信地址（联系人）',code:'addr'},
           		{name:'邮政编码（联系人）',code:'postcode'},{name:'客户经理姓名',code:'sellPerson'},{name:'职务（客户经理）',code:'sellPost'},{name:'手机号（客户经理）',code:'sellPhone'},{name:'办公室电话（客户经理）',code:'officePhone'},
           		{name:'传真号（客户经理）',code:'faxNo'},{name:'电子邮箱（客户经理）',code:'sellEMail'}
           	],
             rowStyler:function(idx,row){
                 return "height:35px;font-size:12px;";
             }
    	});
		$("#fieldsGrid").datagrid('checkAll');
	},
	methods: {
		confirm:function(){
			var me = this;
			var fields = $("#fieldsGrid").datagrid("getChecked");
			if(fields.length == 0){
				MainFrameUtil.alert({title:"警告",body:"请选择要导出字段！"});
				return;
			}
			$.messager.progress();
			var headers = "";
			var headerNames = "";
			for(var i=0 ; i<fields.length ; i++){
				if(fields[i].code == "mpName" || fields[i].code == "mpNo" || fields[i].code == "meterNo" || fields[i].code == "meterRate" || fields[i].code == "meterDigits"){
					me.queryparams.flag = true;
				}
				headers += fields[i].code + ",";
				headerNames += fields[i].name + ",";
			}
			var queryParams = $.parseJSON($.toJSON(me.queryparams));
			var excelParams = {
                    fileName:"用户档案信息导出.xlsx",
                    sheetParamsList:[{
                        className: "com.hhwy.purchaseweb.archives.scconsumerinfo.service.ScConsumerInfoService",
                        methodName:"exportExcel",
                        queryParams:[me.queryparams.ScConsumerInfoVo,me.queryparams.flag],
                        sheetName:"用户档案信息",
                        excelHeaders:headers.substr(0,headers.length-1),                        
                        excelHeaderNames:headerNames.substr(0,headerNames.length-1) 
                    }]
                };
			$.ajax({
                url : basePath+'exportExcelHandlerController/exportExcelByColumn',//配置列导出excel,
                type : 'POST',
                contentType : 'application/json;charset=UTF-8',
                data: $.toJSON(excelParams),
                success : function(data) {
                     if(data.flag){
                         var url = basePath + "exportExcelHandlerController/exportExcel/"+data.data;
                         location.href = url;
                         $.messager.progress('close');
                         MainFrameUtil.alert({title:"提示",body:"导出成功！",onClose:function(){MainFrameUtil.closeDialog("export-field");}});
                     }else{
                         $.messager.progress('close');
                         MainFrameUtil.alert({title:"提示",body:data.msg}); 
                     }
                },
                
                error : function() {
                	$.messager.progress('close');
                    MainFrameUtil.alert({title:"提示",body:"导出失败！"});
                }
            });
			
		}
	}
}); 
</script>
</body>
</html>