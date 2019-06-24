<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>参数维护-生产计划维护新增</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>

<body id="addDiv" v-cloak>
<su-form 
       v-ref:form1 
       offpos-style="true"
       id="fms1" 
       :data-option="dataOption" 
       :set-defaults="setDefaults" 
       :data-validator.sync="valid" >

<mk-panel header="false" height="400px">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<mk-form-col :label='formFields.userName.label' required_icon="true">
	            <mk-selectbox width="50%" height="620" url = "${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox"  propname="id"  :cacheurl="cacheurl3" 
	                disabled="true" name="userNameMust" :propvalue.sync="userName" textfield="consName"></mk-selectbox>
       		 </mk-form-col>
    
	         <!-- 年月-->
	         <mk-form-col :label='formFields.year.label' required_icon="true">
	             <su-datepicker name="yearMust" format="YYYY" :data.sync="year"></su-datepicker>
	         </mk-form-col>
         </mk-form-row>
         
	</mk-form-panel>
	
	<mk-panel title="" line="false" height="330px">
	     <div class="pull-right" slot="buttons" style="text-align:right" v-cloak>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus"  v-on:click="addGrid()">新增</su-button>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o"  v-on:click="deleteGrid()">删除</su-button>
		 </div>
		<table id="addGrid" height="330px"></table>
	</mk-panel>
</mk-panel>
</su-form>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editIndex = undefined;
var editIndex2 = undefined;
 var addVue=new Vue({
	el: '#addDiv',
	data: {
		formFields:{}, 
		year:"",
		userName:null,
		dataOption: {
            rules: {  
            	yearMust: "required",
            	userNameMust:"required"
            }
        },
        valid: false
	},
	ready:function(){
		var me=this;
		var methods={"confirmHandler":this.confirm};
        MainFrameUtil.setParams(methods);
        $.ajax({
     		url:basePath + 'cloud-purchase-web/sell_commonController/getCurrentTime',
     		type:"get",
     		success:function(data){
				me.year=data.data.substring(0,4);
     		}
        })
        
		ComponentUtil.buildGrid("purchase.forecast.phfConsProductionPlan",$("#addGrid"),{ 
	        height:'100%',
	        method: 'post',
	        rownumbers: true,
	        nowrap: true, 
	        fitColumns:true,
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        },
	        columnsReplace:[
    		        {field:'jan',title:'1月',
   		             editor:{
   		              	type:'numberbox',options:{precision:2,min:0,}
   		                    },
	                 formatter: function(value,row,index){
	                	 if(value){
          					  return parseFloat(value);
          				  }else{
          					  return value;
          				  }	
	                 }
   		        	},
   		        	{field:'feb',title:'2月',
   		             editor:{
   			                  type:'numberbox',options:{precision:2,min:0,}
   		                    },
	                 formatter: function(value,row,index){
	                	 if(value){
          					  return parseFloat(value);
          				  }else{
          					  return value;
          				  }	
	                 }
  		        	},
   		        	{field:'mar',title:'3月',
   		             editor:{
   			                  type:'numberbox',options:{precision:2,min:0,}
   		              		 },
	                 formatter: function(value,row,index){
	                	 if(value){
          					  return parseFloat(value);
          				  }else{
          					  return value;
          				  }	
	                 }
   	   		         },
   	   		         {field:'apr',title:'4月',
   	    		      editor:{
   			                  type:'numberbox',options:{precision:2,min:0,}
   		                     },
	                   formatter: function(value,row,index){
	                	 if(value){
          					  return parseFloat(value);
          				  }else{
          					  return value;
          			 	  }	
	                    }
   		        	  },
   		        	  {field:'may',title:'5月',
   	   		           editor:{
   			                  type:'numberbox',options:{precision:2,min:0,}
   	   		               	  },
   	  	                 formatter: function(value,row,index){
   		                	 if(value){
   	          					  return parseFloat(value);
   	          				  }else{
   	          					  return value;
   	          				  }	
   		                 }
   	   		        	},
    	   		       {field:'jun',title:'6月',
    		             editor:{
    			                  type:'numberbox',options:{precision:2,min:0,}
    		                    },
		                 formatter: function(value,row,index){
		                	 if(value){
	          					  return parseFloat(value);
	          				  }else{
	          					  return value;
	          				  }	
		                 }
    		        	},
    		        	{field:'jul',title:'7月',
    	   		             editor:{
    	   			                  type:'numberbox',options:{precision:2,min:0,}
    	   		                    },
   		                     formatter: function(value,row,index){
	   		                	 if(value){
	   	          					  return parseFloat(value);
	   	          				  }else{
	   	          					  return value;
	   	          				  }	
   		                 }
    	   		         },
    	   		         {field:'aug',title:'8月',
    	    		             editor:{
    	    			                  type:'numberbox',options:{precision:2,min:0,}
    	    		                    },
    			                 formatter: function(value,row,index){
    			                	 if(value){
    		          					  return parseFloat(value);
    		          				  }else{
    		          					  return value;
    		          				  }	
    			                 }
    	    		      },
    		        	  {field:'sept',title:'9月',
    	   		             editor:{
    	   			                  type:'numberbox',options:{precision:2,min:0,}
    	   		                    },
   	   		                   formatter: function(value,row,index){
   	   		                	 if(value){
   	   	          					  return parseFloat(value);
   	   	          				  }else{
   	   	          					  return value;
   	   	          				  }	
   	   		                   }
    	   		        	},
   	    	   		       {field:'oct',title:'10月',
   	    		               editor:{
   	    			                  type:'numberbox',options:{precision:2,min:0,}
   	    		                      },
    			                 formatter: function(value,row,index){
    			                	 if(value){
    		          					  return parseFloat(value);
    		          				  }else{
    		          					  return value;
    		          				  }	
    			                 }
   	    		        	},
  	    		        	{field:'nov',title:'11月',
  	    	   		             editor:{
  	    	   			                  type:'numberbox',options:{precision:2,min:0,}
  	    	   		                    },
    	   		                 formatter: function(value,row,index){
    	  	                	     if(value){
    	            					  return parseFloat(value);
    	            				  }else{
    	            					  return value;
    	            				  }	
    	  	                      }
  	    	   		         },
   	    	   		         {field:'dece',title:'12月',
	   	    		             editor:{
	   	    			                  type:'numberbox',options:{precision:2,min:0,}
    		                      },
    		 	                 formatter: function(value,row,index){
    			                	 if(value){
    		          					  return parseFloat(value);
    		          				  }else{
    		          					  return value;
    		          				  }	
    			                 }
   	    		        	 }
      	    	    	    	    	    		        	
   	        ],
	    }); 
		
		ComponentUtil.getFormFields("purchase.forecast.phfConsProductionPlan",this); 
	},
	methods: {
		confirm:function(){
			this.$refs.form1.valid();
			$('#addGrid').datagrid('endEdit', 0);
			var selectedRows = $('#addGrid').datagrid('getRows');
			debugger;
            if(this.valid==true){
            	for(var i=0;i<selectedRows.length;i++){
            		selectedRows[i].consId=this.userName;
            		selectedRows[i].year=this.year;
            	}
            	$.ajax({
					url:basePath+"cloud-purchase-web/phfConsProductionPlanController/savePhfConsProductionPlanList",
					type:'post',
					data:$.toJSON(selectedRows),
		 			contentType : 'application/json;charset=UTF-8',
		 			success:function(data){
		 				if(data.data=="1"){
		 					MainFrameUtil.alert({
		 	                     title:"提示",
		 	                     body:"当前年份数据已经存在"
		 	                 })
		 	                 return;
		 				}
		 				MainFrameUtil.closeDialog();
		 			}
				})
            }
		},
		addGrid:function(){
			var rows = $('#addGrid').datagrid('getRows').length;
			if(rows > 0){ //列表中存在记录
				 MainFrameUtil.alert({
                     title:"提示",
                     body:"只能添加一条数据"
                 })
                 return;
			}else{
				$('#addGrid').datagrid('appendRow',{"productName":""});
	            $('#addGrid').datagrid('beginEdit', 0);
			}
		},
		
		deleteGrid:function(num){
			$('#addGrid').datagrid('deleteRow', 0);
			editIndex2=undefined;
		}
	},
	watch:{
		  
    }
}); 
</script>
</body>
</html>