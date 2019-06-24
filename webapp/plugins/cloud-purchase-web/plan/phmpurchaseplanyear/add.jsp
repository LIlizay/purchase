<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
 <title>购售电交易-年度购电计划新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="addBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="年度购电计划新增" num="2">
		<mk-form-row>
			<!-- 制定人 -->
	        <mk-form-col :label="formFields.setters.label" required_icon="true">
	        	<su-textbox :data.sync="params.setters"></su-textbox>
	        </mk-form-col>
	        <!-- 年份 -->
	        <mk-form-col :label="formFields.year.label" required_icon="true">
	        	<su-datepicker :data.sync="params.year" format="YYYY" ></su-datepicker>
	        </mk-form-col>
    	</mk-form-row>
	</mk-form-panel>
	<mk-panel title="年度购电用户列表" slot="bottom" height="100%" line="true">
	    <div id="addGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el: '#addBody',
	data: {
		formFields:{},
		params:{setters:'',year:''},
		loadFlag:false
	},
	ready:function(){
		var me = this;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'add');
        this.params.year = new Date().getFullYear();
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.consInfo",$("#addGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmPurchasePlanYearController/consPage',
			queryParams:{startYear:this.params.year},
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(){
		    	$("#addGrid").datagrid("options").onLoadSuccess = function(){};
		    	me.loadFlag = true;
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
	},
	methods: {
		save:function(backFun){
	  		if(this.params.setters==''){
	  			MainFrameUtil.alert({title:"警告",body:"请填写制定人"});
	  			return;
	  		}
	  		$.ajax({
				url : basePath + "cloud-purchase-web/phmPurchasePlanYearController",
   	   			type : 'post',
   	   			data : $.toJSON(this.params),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   				MainFrameUtil.alert({title:"警告",body:data.msg,onClose:function(){
						backFun();
					}});
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		});
		},
	},
	watch:{
		'params.year':function(){
			if(this.loadFlag){
				$("#addGrid").datagrid("load",{startYear:this.params.year});
			}
		}
	}
}); 
</script>
</body>
</html>