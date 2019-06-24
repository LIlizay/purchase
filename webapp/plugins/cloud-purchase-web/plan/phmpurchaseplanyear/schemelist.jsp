<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">、
 <title>购售电交易-年度购电计划名称详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="detailBody" v-cloak>
<mk-panel title="分解测算方案列表"   line="true" height="100%">
    <div id="detailGrid" ></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var schemeVue = new Vue({
	el: '#detailBody',
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('schemeList').id;
		ComponentUtil.buildGrid("purchase.plan.scheme",$("#detailGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmPhPlanYearSchemeController/page',
			queryParams:{phmPhPlanYearScheme:{planId:id}},
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
	},
	methods:{
		planNameFormatter:function(value,row,index){
			return "<a onclick=\"schemeVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/schemedetail',
				title:'年度电量分解测算方案明细',
				iframe:true,
				modal:true,
				width:800,
				height:500,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('detail');
                    }
                }]
			});
		},
	}
}); 
</script>
</body>
</html>