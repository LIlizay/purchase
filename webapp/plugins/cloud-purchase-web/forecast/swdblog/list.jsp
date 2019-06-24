<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>参数维护-审计日志</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
  <style type="text/css">
	  body {
	  overflow: auto;
	  }
	  .left-btn{
        cursor: pointer;
    }
  </style>
</head>

<body>
<div id="phftradingcenterDiv" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">
 <!-- 第一个面板 -->
 <mk-search-panel deployable="false" slot="top">
    <mk-search-visible>
    	<!-- 公司名称 -->
		<su-form-group :label-name='formFields.companyName.label'  class="form-control-row " :class="{'display-none':!formFields.companyName.searchHidden}" col="4" label-align="right">
                 <su-textbox :data.sync="queryParams.companyName" type="text"></su-textbox>
       </su-form-group>
    
       <!-- 开始年月-->
       <su-form-group :label-name='formFields.startTime.label' class="form-control-row " :class="{'display-none':!formFields.startTime.searchHidden}" col="4" label-width="118px" label-align="right">
             <su-datepicker format="YYYY-MM-DD" :data.sync="queryParams.startTime"></su-datepicker>
       </su-form-group>
       <!-- 截止年月 --> 
       <su-form-group :label-name='formFields.endTime.label' class="form-control-row " :class="{'display-none':!formFields.endTime.searchHidden}" col="4" label-width="118px" label-align="center">
             <su-datepicker format="YYYY-MM-DD" :data.sync="queryParams.endTime"></su-datepicker>
       </su-form-group>
   </mk-search-visible>
   <div slot="buttons" class="pull-right mt_10 dashed_div">
         <su-button s-type="primary"  @click="searchFlow" class="btn-width-small">查询</su-button>
         <su-button s-type="default"  @click="resetFlow" class="btn-width-small">重置</su-button>
   </div>
 </mk-search-panel>

 <!-- 第二个面板 -->
 <mk-panel title="日志列表" line="true" slot="bottom" height="100%">
       <table id="dbLogGrid"></table>  
 </mk-panel>
</mk-top-bottom>
</div>
<script>
  var basePath = '${Config.baseURL}';
  
  var phftradingcenterVue=new Vue({
    el: '#phftradingcenterDiv',
    data:{
	      date:'',
	      formFields:{},  
          queryParams:{"startTime":null,"endTime":null}
     },
     methods: {
    	 //重置表单
    	 resetFlow:function(){
    		 this.queryParams={"startTime":null,"endTime":null}
    	 },
    	 searchFlow: function(){
    			$('#dbLogGrid').datagrid('options').queryParams = this.queryParams;  
			    $("#dbLogGrid").datagrid('load'); 
    	 }
     },	 
     ready:function(){
    	 var me=this;
    	 ComponentUtil.getFormFields("selling.forecast.swdblog",this);    
    	 ComponentUtil.buildGrid("selling.forecast.swdblog",$("#dbLogGrid"),{ 
    	    url:basePath+'swDbLogController/page',
    		queryParams:this.queryParams,
    		method : 'post',
   	        striped : true,
   	        pagination : true,
   	        checkOnSelect : true,
   	        fitColumns:true,
   	        fit:true,
   	        nowrap:true,
   	        width:'100%',    
   	        height:'auto',
   	     	idField:'id'
  	      }); 
      }
  })
</script>
</body>
</html>