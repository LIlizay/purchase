<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-交易中心数据维护</title>
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
       <!-- 开始年月-->
       <su-form-group :label-name='formFields.startTime.label' class="form-control-row " :class="{'display-none':!formFields.startTime.formHidden}" col="4" label-width="118px" label-align="right">
             <su-datepicker format="YYYY-MM" :data.sync="queryParams.startTime"></su-datepicker>
       </su-form-group>
       <!-- 截止年月 --> 
       <su-form-group :label-name='formFields.endTime.label' class="form-control-row " :class="{'display-none':!formFields.endTime.formHidden}" col="4" label-width="118px" label-align="center">
             <su-datepicker format="YYYY-MM" :data.sync="queryParams.endTime"></su-datepicker>
       </su-form-group>
   </mk-search-visible>
   
   <div slot="buttons" class="pull-right mt_10 dashed_div">
         <su-button s-type="primary"  @click="searchFlow" class="btn-width-small">查询</su-button>
         <su-button s-type="default"  @click="resetFlow" class="btn-width-small">重置</su-button>
   </div>
 </mk-search-panel>

 <!-- 第二个面板 -->
 <mk-panel title="交易中心发布信息列表" line="true" slot="bottom" height="100%">
       <table id="phftradingcenterGrid"></table>  
       <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" @click="addInfo">新增</su-button>
       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" @click="editInfo">编辑</su-button>
       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="remove" @click="deleteInfo">删除</su-button>
   </div>
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
    	 //表格添加链接
    	 display:function(value,row,index){
    		 //获取将要跳转的弹出框的URL
             return "<a class='left-btn' onclick=\"phftradingcenterVue.detailInfo('"+row.id+"')\">查看</a>";
         },
         
    	 //重置表单
    	 resetFlow:function(){
    		 this.queryParams={"startTime":null,"endTime":null}
    	 },
    	 
    	 //查询表单
    	 searchFlow:function(){
    		 if(this.queryParams.startTime!=null&&this.queryParams.startTime!=""&&
    				 this.queryParams.endTime!=null&&this.queryParams.endTime!=""
    				 &&this.queryParams.endTime<this.queryParams.startTime){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"截止时间必须大于开始时间"
                 })
                 return;
    		 }
    		 $('#phftradingcenterGrid').datagrid('options').url=basePath+'phfTradingCenterController/page';
             $('#phftradingcenterGrid').datagrid('load',this.queryParams); 
    	 },
    	 
    	 //新增
    	 addInfo:function(){
    		 var url=basePath+"/view/cloud-purchase-web/forecast/phftradingcenter/add";
    		 this.searchBusiTypeInfo(url,"交易中心数据新增");
    	 },
    	 
    	 //修改
    	 editInfo:function(){
    		 var phftradingcenterGrid=$('#phftradingcenterGrid').datagrid('getSelections');
    		 if(phftradingcenterGrid.length>1){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"只允许修改单行数据"
                 })
                 return;
    		 }
    		 var centerId=phftradingcenterGrid[0].id;
    		 var url=basePath+"/view/cloud-purchase-web/forecast/phftradingcenter/edit?centerId="+centerId;
    		 this.searchBusiTypeInfo(url,"交易中心数据编辑");
    	 },
    	 //删除
    	 deleteInfo:function(){
    		 var phftradingcenterGrid=$('#phftradingcenterGrid').datagrid('getSelections');
    		 if(phftradingcenterGrid.length==0){
    			 MainFrameUtil.alert({ title:"提示", body:"请选择删除数据" })
                 return;
    		 }
    		 MainFrameUtil.confirm({
 		        title:"确认",
 		        body:"删除后不可恢复，确认删除选中行？",
 		        onClose:function(type){
 		            if(type=="ok"){//确定
			             var ids="";
	 		      		 for(var i=0;i<phftradingcenterGrid.length;i++){
	 		      			 ids+=phftradingcenterGrid[i].id+",";
	 		      		 }
	 		      		 ids=ids.substring(0,ids.length-1);
	 		      		 $.ajax({
	 		  					url:basePath+"cloud-purchase-web/phfTradingCenterController/"+ids,
	 		  					type:'delete',
	 		  		 			success:function(data){
	 		  		 				$('#phftradingcenterGrid').datagrid('reload'); 
	 		  		 			}
 		  				 })
 		            }
 		        }
 		    }); 
    	 },
    	 //跳转到相应业务的详细信息
    	 searchBusiTypeInfo:function(url,title){
    		 var screenHeight=window.screen.availHeight;
    		 var screenWidth=window.screen.availWidth;
    		 MainFrameUtil.openDialog({
                 href:url,
                 iframe:true,
                 title:title,
                 modal:true,
                 maximizable:true,
                 width:screenWidth/1.2,
                 height:screenHeight/1.2,
                 callback:function(htmlObj){//渲染完成回调函数
                 },
                 onClose:function(params){//点击关闭回调函数
                	 $('#phftradingcenterGrid').datagrid('reload'); 
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
    	 },
    	 //查看详情
    	 detailInfo:function(id){
    		 var screenHeight=window.screen.availHeight;
    		 var screenWidth=window.screen.availWidth;
    		 MainFrameUtil.openDialog({
                 href:basePath+"/view/cloud-purchase-web/forecast/phftradingcenter/detail?centerId="+id,
                 title:'交易中心数据维护信息查看',
                 iframe:true,
                 maximizable:true,
                 modal:true,
                 width:screenWidth/1.2,
                 height:screenHeight/1.2,
                 callback:function(htmlObj){//渲染完成回调函数
                 },
                 onClose:function(params){//点击关闭回调函数
                 },
                 buttons:[{
                     text:"关闭",
                     handler:function(btn,param){
                    	 MainFrameUtil.closeDialog();
                     }
                 }] 
              }) 
    	 }
     },
     ready:function(){
    	 var me=this;
    	 ComponentUtil.buildGrid("purchase.forecast.phftradingcenter",$("#phftradingcenterGrid"),{ 
    	    url:basePath+'phfTradingCenterController/page',
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
    	  ComponentUtil.getFormFields("purchase.forecast.phftradingcenter",this);    
      }
  })
</script>
</body>
</html>