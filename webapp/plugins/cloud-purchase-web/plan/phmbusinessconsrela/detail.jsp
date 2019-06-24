<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="detailBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="年度购电计划新增" num="2">
		<mk-form-row>
			<!-- 制定人 -->
	        <mk-form-col :label="formFields.setters.label" required_icon="true">
	        	<su-textbox :data.sync="params.setters" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 年份 -->
	        <mk-form-col :label="formFields.year.label" required_icon="true">
	        	<su-datepicker :data.sync="params.year" format="YYYY" disabled></su-datepicker>
	        </mk-form-col>
    	</mk-form-row>
	</mk-form-panel>
	<mk-panel title="年度经营计划用户列表" slot="bottom" height="100%" line="true">
	    <div id="detailGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var detailVue = new Vue({
	el: '#detailBody',
	data: {
		formFields:{},
		params:{setters:'',year:''}
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams("detail").id;
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.consInfoRela",$("#detailGrid"),{
			url:basePath+"cloud-purchase-web/phmBusinessConsRelaController/getConsDetail/"+id,
			method:"get",
		    height:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    loadFilter:function(data){
		    	var obj = data.data;
				me.params = obj.phmBusinessPlan;
				return obj.consList;
		    },
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
	}
}); 
</script>
</body>
</html>