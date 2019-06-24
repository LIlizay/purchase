<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>参数维护-工况信息维护</title>
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
<div id="phfworkconditionDiv" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">

 <mk-search-panel deployable="false" slot="top">
    <mk-search-visible>
       <!-- 年月-->
       <su-form-group :label-name='formFields.ym.label' class="form-control-row " col="4" label-width="118px" label-align="right">
             <su-datepicker format="YYYYMM" :data.sync="ym"></su-datepicker>
       </su-form-group>
       <su-button s-type="primary pull-right mt_10 dashed_div"  @click="searchInfo" class="btn-width-small">查询</su-button>
   </mk-search-visible>
 </mk-search-panel>

 <mk-panel title="工况信息列表" line="true" slot="bottom" height="100%">
       <table id="phfworkconditionGrid"></table>  
       <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
       		<su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" @click="searchBusiTypeInfo">工况信息维护</su-button>
   	   </div>
 </mk-panel>
 </mk-top-bottom>
</div>
<script>
  var basePath = '${Config.baseURL}';
  var editIndex = undefined;
  
  var phfworkconditionVue=new Vue({
    el: '#phfworkconditionDiv',
    data:{
	      date:'',
	      formFields:{}, 
	      ym:""
     },
     methods: {
    	 searchInfo:function(){
    		 if(this.ym==null||this.ym==""){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请输入年月"
                 })
                 return; 
    		 }
    		 $.ajax({
 	  			url:basePath+'phfWorkConditionController/getWorkInfo/'+this.ym,
 	  			type:"get",
 				success:function(data){
 					$('#phfworkconditionGrid').datagrid('loadData',data.data);
 				}
 	  		})
    	 },

    	 //详情弹框
    	 searchBusiTypeInfo:function(){
    		 var me=this;
    		 var screenHeight=window.screen.availHeight;
    		 var screenWidth=window.screen.availWidth;
    		 var rowsData=$('#phfworkconditionGrid').datagrid('getRows');
    		 MainFrameUtil.openDialog({
                 href:basePath+"/view/cloud-purchase-web/forecast/phfworkcondition/edit",
                 iframe:true,
                 params:{"rowsData":rowsData},
                 title:"工况信息维护",
                 modal:true,
                 maximizable:true,
                 width:screenWidth/1.2,
                 height:screenHeight/1.2,
                 callback:function(htmlObj){//渲染完成回调函数
                 },
                 onClose:function(params){//点击关闭回调函数
                	me.searchInfo();
                 },
                 buttons:[{
                     text:"保存",
                     icon:"add",
                     type:"primary",
                     handler:function(btn,param){
                    	 param.confirmHandler();
                     }
                 },{
                     text:"取消",
                     icon:"cancel",
                     type:"default",
                     handler:function(btn,param){
                    	 MainFrameUtil.closeDialog();
                     }
                 }] 
              }) 
    	 }
     },
     ready:function(){
    	 var me=this;
    	 $.ajax({
     		url:basePath + 'cloud-purchase-web/sell_commonController/getCurrentTime',
     		type:"get",
     		success:function(data){
				me.ym=data.data.substring(0,7).replace('-','');
				ComponentUtil.buildGrid("purchase.forecast.phfWorkCondition",$("#phfworkconditionGrid"),{ 
		   	        data:[],
					striped : true,
		   	        checkOnSelect : true,
		   	        fitColumns:true,
		   	        fit:true,
		   	        nowrap:true,
		   	        width:'100%',    
		   	        height:'auto',
		   	     	idField:'id',
		   	     	columnsReplace:[
   						{field:'holidayFlag',title:'是否节假日',
		                    formatter:function(value,row,index){
		                    	if(value=="0"){
		                    		return "否";
		                    	}else if(value=="1"){
		                    		return "是";
		                    	}
		                    }
   						}
					],
					onLoadSuccess:function(){
		   	     		$("#phfworkconditionGrid").datagrid("options").onLoadSuccess=function(){}
		   	     		phfworkconditionVue.searchInfo();
		   	        }
		  	      });
     		}
     	})
   	    ComponentUtil.getFormFields("purchase.forecast.phfWorkCondition",this);    
      }
  })
</script>
</body>
</html>