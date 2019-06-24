<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>参数维护-生产计划维护编辑</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>

<body id="editDiv" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel title="" num="2" slot="top">
		 <mk-form-row>
	     	<mk-form-col :label='formFields.userName.label' required_icon="true">
	            <su-textbox disabled="disabled" :data.sync="userName"></su-textbox>
       		 </mk-form-col>
    
	         <!-- 年月-->
	         <mk-form-col :label='formFields.year.label' required_icon="true">
	             <su-datepicker disabled="disabled" format="YYYY" :data.sync="year"></su-datepicker>
	         </mk-form-col>
         </mk-form-row>
	</mk-form-panel>
	<mk-panel title="" line="false" slot="bottom" height="100%">
		<table id="editGrid"></table>
	</mk-panel>
</mk-top-bottom>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editIndex = undefined;
var editIndex2 = undefined;

var editVue=new Vue({
	el: '#editDiv',
	data: {
		formFields:{}, 
		year: MainFrameUtil.getParams().year,
		userName: MainFrameUtil.getParams().consName,
        valid: false,
        consId: MainFrameUtil.getParams().consId,
        detailFlag: MainFrameUtil.getParams().detail,
	},
	methods: {
		confirm: function(){
			$('#editGrid').datagrid('endEdit',0);
			var selectedRows = $('#editGrid').datagrid('getRows');
			

           	for(var i=0;i<selectedRows.length;i++){
           		selectedRows[i].consId=this.consId;
           		selectedRows[i].year=this.year;
           	}
           	$.ajax({
				url:basePath+"cloud-purchase-web/phfConsProductionPlanController/updatePhfConsProductionPlanList",
				type:'post',
				data:$.toJSON(selectedRows),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
	 				if(data.flag){
		 				MainFrameUtil.closeDialog();
	 				}else{
	 					MainFrameUtil.alert({title:"提示",body:"保存失败！"});
	 				}
	 			},
	 			error:function(data){
        			MainFrameUtil.alert({title:"提示",body:"保存失败！"}); 
        		}
	 			
			})
		},
	},
	ready:function(){
		var me=this;
		var methods={"confirmHandler":this.confirm};
        MainFrameUtil.setParams(methods);
		ComponentUtil.buildGrid("purchase.forecast.phfConsProductionPlan",$("#editGrid"),{ 
	        url:basePath+"cloud-purchase-web/phfConsProductionPlanController/getPhfConsProductionPlanList",
	        queryParams:{"phfConsProductionPlan":{consId:this.consId, year: me.year}},
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
    		 onLoadSuccess: function(data){
    			 if(me.detailFlag != true){
	             	$('#editGrid').datagrid('beginEdit', 0);
    			 }
             }
	    }); 
		
		ComponentUtil.getFormFields("purchase.forecast.phfConsProductionPlan",this); 
	},
	watch:{
		  
    }
}); 
</script>
</body>
</html>