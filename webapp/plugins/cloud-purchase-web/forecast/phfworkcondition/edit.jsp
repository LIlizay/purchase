<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>参数维护-工况信息维护编辑</title>
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

<div id="editDiv" class="height-fill">
	 <mk-panel title="" line="true" header="false" height="100%">
	       <table id="editGrid"></table>  
	 </mk-panel>
</div>
<script>
  var basePath = '${Config.baseURL}';
  var editIndex = undefined;
  
  var editVue=new Vue({
    el: '#editDiv',
    data:{
	      date:'',
	      formFields:{}, 
     },
     methods: {
 		endEditing:function(){
			$('#editGrid').datagrid('endEdit', editIndex);
            editIndex = undefined;
 		},
 		confirm:function(){
 			this.endEditing();
 			var rowsData=$('#editGrid').datagrid('getRows');
			var params={"list":rowsData}; 	
// 			$.messager.progress();  // 显示进度条
			$.ajax({
				url:basePath+"cloud-purchase-web/phfWorkConditionController/saveWorkInfo",
				type:'post',
				data:$.toJSON(params),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
// 	 				$.messager.progress('close');   // 如果提交成功则隐藏进度条
	 				MainFrameUtil.closeDialog();
	 			}
			}) 		
 		}
     },
     ready:function(){
    	var me=this;
		var methods={"confirmHandler":this.confirm};
		var rowsData=MainFrameUtil.getParams().rowsData;
        MainFrameUtil.setParams(methods);
    	ComponentUtil.buildGrid("purchase.forecast.phfWorkCondition",$("#editGrid"),{ 
    		data:rowsData,
   	        striped : true,
   	        checkOnSelect : true,
   	        fitColumns:true,
   	        fit:true,
   	        nowrap:true,
   	        width:'100%',    
   	        height:'auto',
   	     	idField:'id',
   	     	columnsReplace:[
					{field:'temperature',title:'温度(°C)',
						editor:{id:"temperature",type:'numberbox',options:{precision:4,max:50,min:-50,}},
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }
						  }
					},
					{field:'humidity',title:'湿度(%rh)',
						editor:{id:"humidity",type:'numberbox',options:{precision:4,max:100,min:0,}},
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }
						  }
					},
					{field:'holidayFlag',title:'是否节假日',
						editor:{type:'combobox',options:{
		                	url: '${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag',
		                	method: "get",
		                	panelHeight: '50',
		                	valueField:'value',
		                	textField:'name',
							editable:false,
	                    }},
	                    formatter:function(value,row,index){
	                    	if(value=="0"){
	                    		return "否";
	                    	}else if(value=="1"){
	                    		return "是";
	                    	}
	                    }
					}
			],
			onClickCell: function(index, field, value){
    			if (editIndex != index){
    				editVue.endEditing();
   					$('#editGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
   					var ed = $('#editGrid').datagrid('getEditor', {index:index,field:field});
   					if(ed != null){		//若点击的是可编辑的单元格，则编辑时默认focus此编辑框
    					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
   					}
   					editIndex = index;
    			}
    		},
    		loadFilter: function(data){
    			if (data != null && data.length > 0){
    				for(var i = 0 ; i < data.length; i++){
    					var row = data[i];
    					if(row.holidayFlag == null || row.holidayFlag == ""){
    						var dateStr = row.ymd;
    						var year = parseInt(dateStr.substr(0,4));
    						var month = parseInt(dateStr.substr(4,2));
    						var day = parseInt(dateStr.substr(6,2));
	    					var date=new Date();
	    					date.setFullYear(year);
	    					date.setMonth(month-1);
	    					date.setDate(day);
	    					row.holidayFlag = (date.getDay() == 6 || date.getDay() == 0) ? 1 : 0;
    					}
    				}
    			}
   				return data;
    		}
  	      });
    	
   	    ComponentUtil.getFormFields("purchase.forecast.phfWorkCondition",this);    
      }
  })
</script>
</body>
</html>