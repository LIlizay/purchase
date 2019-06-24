<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>参数维护-生产计划维护</title>
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
<div id="phfconsproductionplanDiv" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">

 <mk-search-panel deployable="false" slot="top">
    <mk-search-visible>
       <!--用户名称-->
       <su-form-group :label-name='formFields.userName.label' class="form-control-row " col="4" label-width="118px" label-align="right">
              <mk-selectbox width="50%" height="620" url = "${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox"  propname="id"  :cacheurl="cacheurl3" 
	                disabled="true"  :propvalue.sync="params.phfConsProductionPlan.consId" textfield="consName"></mk-selectbox>
       </su-form-group>
    
       <!-- 年月-->
       <su-form-group :label-name='formFields.year.label' class="form-control-row " col="4" label-width="118px" label-align="right">
             <su-datepicker format="YYYY" :data.sync="params.phfConsProductionPlan.year"></su-datepicker>
       </su-form-group>
   </mk-search-visible>
   
   <div slot="buttons" class="pull-right mt_10 dashed_div">
         <su-button s-type="primary"  @click="searchInfo" class="btn-width-small">查询</su-button>
         <su-button s-type="default"  @click="rest" class="btn-width-small">重置</su-button>
   </div>
 </mk-search-panel>

 <mk-panel title="用户生产计划列表" line="true" slot="bottom" height="100%">
       <table id="phfconsproductionplanGrid"></table>  
       <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" @click="addInfo">新增</su-button>
	       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" @click="editInfo(null,null,null)">编辑</su-button>
	       <su-button class="btn-operator" s-type="default" labeled="true" label-ico="remove" @click="deleteInfo">删除</su-button>
       </div>
 </mk-panel>
 </mk-top-bottom>
</div>
<script>
  var basePath = '${Config.baseURL}';
  
  var phfconsproductionplanVue=new Vue({
    el: '#phfconsproductionplanDiv',
    data:{
	      date:'',
	      formFields:{}, 
	      params:{"phfConsProductionPlan":{"consId":"","year":""}}
     },
     methods: {
    	 //查询
    	 searchInfo:function(){
    		 if(this.params.phfConsProductionPlan.year==null||this.params.phfConsProductionPlan.year==""){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请输入年份"
                 })
                 return; 
    		 }
    		 $.ajax({
 	  			url:basePath + 'cloud-purchase-web/phfConsProductionPlanController/page',
 	  			type:"post",
 	  			data:$.toJSON(this.params),
 	  			contentType : 'application/json;charset=UTF-8',
 				success:function(data){
 					$('#phfconsproductionplanGrid').datagrid('loadData',data.rows);
 				}
 	  		})
    	 },
		 //重置
    	 rest:function(){
    		 this.params.phfConsProductionPlan.consId="";
    		 var date=new Date;
    		 var year=date.getFullYear(); 
    		 this.params.phfConsProductionPlan.year=year; 
    	 },
    	 
    	 //新增
    	 addInfo:function(){
    		var title="用户生产计划信息新增";
    		var url=basePath+"/view/cloud-purchase-web/forecast/phfconsproductionplan/add";
    		this.searchBusiTypeInfo(url,title,"");
    	 },
    	 
    	 //  编辑/详情页面 参数（是否为详情页面标识，用户id,用户名）
	     editInfo: function(detail,consId,consName){
	    	 var me=this;
    		 var selected= $("#phfconsproductionplanGrid").datagrid("getChecked");
    		 if(selected==null){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请选择一行数据"
                 })
                 return;
    		 }
    		 if(detail == true){
    			 var title="用户生产计划信息明细页面";
    			 var data={"consId":consId,"consName":consName,"year":me.params.phfConsProductionPlan.year,"detail":detail};
    		 }else{
		    	 var title="用户生产计划信息编辑";
	    		 var data={"consId":selected[0].consId,"consName":selected[0].consName,"year":me.params.phfConsProductionPlan.year,"detail":detail};
    		 }
    		 var url=basePath+"/view/cloud-purchase-web/forecast/phfconsproductionplan/edit";
    		 this.searchBusiTypeInfo(url,title,data);
    	 },
    	 
    	 //删除
    	 deleteInfo:function(){
    		var me=this;
    		var selected= $("#phfconsproductionplanGrid").treegrid("getSelected");
    		if(selected==null){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请选择一行数据"
                 })
                 return;
    		}
    		var url="";
    		if(selected.productName==null||selected.productName==""){//父节点
    			MainFrameUtil.confirm({
                    title:"提示",
                    body:"是否删除该用户所有信息?",
                    onClose:function(type){
                        if(type=="ok"){//确定
                        	$.ajax({
                 	  			url:basePath + 'cloud-purchase-web/phfConsProductionPlanController/deleteConsInfo',
                 	  			type:"delete",
                 	  			data:$.toJSON({"consId":selected.consId,"year":selected.year}),
                 	  			contentType : 'application/json;charset=UTF-8',
                 				success:function(data){
                 					me.searchInfo();
                 				}
                 	  		})
                        }else if(type=="cancel"){//取消
                            return;
                        }
                    }
                });
    		}else{//子节点
    			MainFrameUtil.confirm({
                    title:"提示",
                    body:"是否删除选择的产品?",
                    onClose:function(type){
                        if(type=="ok"){//确定
                        	$.ajax({
                 	  			url:basePath + 'cloud-purchase-web/phfConsProductionPlanController/'+selected.id,
                 	  			type:"delete",
                 	  			contentType : 'application/json;charset=UTF-8',
                 				success:function(data){
                 					me.searchInfo();
                 				}
                 	  		})
                        }else if(type=="cancel"){//取消
                            return;
                        }
                    }
                })
    		}
    	 },
    	 //详情弹框
    	 searchBusiTypeInfo:function(url,title,data){
    		 var me=this;
    		 var screenHeight=window.screen.availHeight;
    		 var screenWidth=window.screen.availWidth;
    		 MainFrameUtil.openDialog({
                 href:url,
                 params:data,
                 iframe:true,
                 title:title,
                 modal:true,
                 maximizable:true,
                 width:screenWidth/1.2,
                 height:"500px",
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
				me.params.phfConsProductionPlan.year=data.data.substring(0,4);
				$("#phfconsproductionplanGrid").datagrid({
					url:basePath + 'cloud-purchase-web/phfConsProductionPlanController/page',
					queryParams:me.params,
					method:'post',
					columns:[[
					          {title:"用户名称",field:"consName",rowspan:2,width:80,
					        	  formatter: function(value,row,index){
				  		    		 if(value){
				  		    			 return "<a href='javascript:void(0)'  onclick=\"phfconsproductionplanVue.editInfo(true,'"+row.consId+"','"+row.consName+"')\">"+value+"</a>";
				  		    		 }
				  		    		 return "";
				  		    	 }},
					          {title:"产能系数",field:"params",colspan:12,width:50,align:'center'},
					          ],[
					        	  {title:"1月",field:"jan",width:50,align:'center'},
						          {title:"2月",field:"feb",width:50,align:'center'},
						          {title:"3月",field:"mar",width:50,align:'center'},
						          {title:"4月",field:"apr",width:50,align:'center'},
						          {title:"5月",field:"may",width:50,align:'center'},
						          {title:"6月",field:"jun",width:50,align:'center'},
						          {title:"7月",field:"jul",width:50,align:'center'},
						          {title:"8月",field:"aug",width:50,align:'center'},
						          {title:"9月",field:"sept",width:50,align:'center'},
						          {title:"10月",field:"oct",width:50,align:'center'},
						          {title:"11月",field:"nov",width:50,align:'center'},
						          {title:"12月",field:"dece",width:50,align:'center'}
					          ]],
				    height:"100%",
				    rownumbers: true,
				    pagination: true,
				    singleSelect:true,
				    nowrap: false,
				    singleSelect:true,
				    fitColumns:true,
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onLoadSuccess : function(rows){
				    	$("#phfconsproductionplanGrid").datagrid("clearChecked");

				    },
				    onBeforeExpand : function(row, param) {
						 $(this).treegrid('options').url = basePath+"cloud-purchase-web/phfConsProductionPlanController/getPhfConsProductionPlanList";
						 $(this).treegrid('options').queryParams ={"phfConsProductionPlan":{consId:row.consId, year: me.params.phfConsProductionPlan.year}};
					 }
				})
				
				$('#phfconsproductionplanGrid').treegrid("getPager").pagination({
					 onSelectPage:function(pageNumber,pageSize){
						 $(this).pagination("loading");
						 $("#phfconsproductionplanGrid").treegrid('options').url = basePath + 'cloud-purchase-web/phfConsProductionPlanController/getParentNode';
						 $("#phfconsproductionplanGrid").treegrid('options').queryParams = me.params;
						 $("#phfconsproductionplanGrid").treegrid('options').pageNumber =pageNumber;
						 $("#phfconsproductionplanGrid").treegrid('options').pageSize =pageSize;
						 $("#phfconsproductionplanGrid").treegrid('reload');
					 }
				});
     		}
     	})
   	    ComponentUtil.getFormFields("purchase.forecast.phfConsProductionPlan",this);    
      }
  })
</script>
</body>
</html>