<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>偏差考核预警-表计示数查看</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body>
<div id="meterVue" class="height-fill" v-cloak>
<!-- <mk-top-bottom height="100%" top_height="auto"> -->
<su-form>
	<mk-form-panel>
		<mk-form-row>
			<!-- 用户名称 -->
	        <mk-form-col :label="formFields.consName.label">
	        	<su-textbox :data.sync="params.consName" disabled></su-textbox>
	        </mk-form-col>
			<!-- 电压等级 -->
	        <mk-form-col :label="formFields.voltCodeName.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" multiple="false" 
				:select-value.sync="params.voltCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 用电类别 -->
	        <mk-form-col :label="formFields.elecTypeCodeName.label" >
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" multiple="false" 
				:select-value.sync="params.elecTypeCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    
	    <mk-form-row>
			<!-- 电能表编号 -->
	        <mk-form-col :label="formFields.meterNo.label">
	        	<su-textbox :data.sync="params.meterNo" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 电能表倍率 -->
	        <mk-form-col :label="formFields.meterRate.label" >
	        	<su-textbox :data.sync="params.meterRate" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 电能表位数 -->
	        <mk-form-col :label="formFields.meterDigits.label">
	        	<su-textbox :data.sync="params.meterDigits" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	
	</su-form>
    
    <mk-panel title="表计示数列表" line="true" slot="bottom" height="100%">
        <div class="row"  style="height: 100%">
            <table id="countGrid"></table>
        </div>
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var meterVue = new Vue({
	el: '#meterVue',
	data: {
		formFields:{},
    	params:{},
    	queryparams:{id:''}
	},
	ready:function(){
		var me = this;
		me.params = MainFrameUtil.getParams('detail');
		me.queryparams.id = me.params.id;

		$("#countGrid").datagrid({
			url: basePath + 'scMpCountController/getByMpId',
			method: 'post',
			queryParams: me.queryparams, 
			width:"100%",
			height:"100%",
		    rownumbers: false,
		    pagination: true,
		    fitColumns:true,
		    pageSize: 20,
		    nowrap: false,
		    fitColumns:true,
		    pageList: [10, 20, 50, 100, 150, 200],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    columns:[[
		    	{field: 'ck',title: 'ck',checkbox: true,},
				{field:'meterReadPq',title:'抄表总示数',width: 200,align:'center'},
				{field:'meterReadDate',title:'抄表时间',width: 200,align:'center'},
				{field:'usuallyDateFlag',title:'是否抄表例日',width: 200,align:'center',
					formatter: function(value,row,index){
						if (value == "1"){
							return '是';
						} else {
							return '否';
						}
					},
					styler: function(value,row,index){
						if (value == "1"){
							return 'color:red;';		//background-color:#eeeeee;
						}else{
							return '';		//color:green;
						}
				}}
		    ]]
	    });
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.deviationcheck.metercount", this);
	}
}); 
</script>
</body>
</html>