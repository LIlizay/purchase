<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body>
<mk-panel title="经营利润" >
	<mk-form-panel title="" num="3">
		<mk-form-row>
			<!-- 计划名称 -->
	        <mk-form-col :label="formFields.planName.label" colspan="2">
	        	<su-textbox :data.sync="planName"   disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 预测合同总电量 -->
	        <mk-form-col :label="formFields.agrePqForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 预测合同总销售额 -->
	        <mk-form-col :label="formFields.agreAmtForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.agreAmt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 预测平均代理单价 -->
	        <mk-form-col :label="formFields.avgPrcForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.avgPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>
<mk-panel title="经营利润方案" height="400px"  line="true">
    <div id="listGrid" ></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var mainVue = new Vue({
	el: 'body',
	data: {
		formFields:{},
		planName:'',
		params:{
			phmBusinessPlanScheme:{agrePq:'',agreAmt:'',avgPrc:''},
		},
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('list');
		me.planName = obj.planName;
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.phmbusinessplanscheme",this);
		ComponentUtil.buildGrid("purchase.plan.phmbusinessplanscheme",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmBusinessPlanSchemeController/page',
			queryParams:{phmBusinessPlanScheme:{planId:obj.planId}},
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		$.ajax({
			url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/getInitScheme",
			type:"get",
			data:{planId:obj.planId,year:obj.year},
			success:function(data){
				me.params= data.data;
			}
		})
	},
	methods:{
		planNameFormatter:function(value,row,index){
			return "<a onclick=\"mainVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/schemedetail',
				iframe:true,
				modal:true,
				width:800,
				height:500
			});
		},
	}
})

</script>
</body>
</html>