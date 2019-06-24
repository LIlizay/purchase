<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电交易山西、辽宁申报信息录入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="reportInputVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
	    <mk-panel title="" line="true" height="100%" slot="bottom">
	        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	        </div>
	        <div id="reportInputGrid" class="col-xs-12" ></div>
	    </mk-panel>
	</mk-top-bottom>
    
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var reportInputVue = new Vue({
    el: '#reportInputVue',
    data: {
        formFields:{},
        params:{},
        dealPqTotal:0,
        //dealPrcTotal:0,
        ids:[],
        attornTypeFlag:false,
    },
    ready:function(){
    	var me = this;
    	//获取购电计划id
    	me.params = $.parseJSON($.toJSON(MainFrameUtil.getParams("report_add").params));
    	var flag = MainFrameUtil.getParams("report_add").flag;
    	var width = "48%";
    	if(flag){
    		width = "32%";
    		me.attornTypeFlag = true;
    	}
    	//列表数据加载
        $("#reportInputGrid").datagrid({
        	url:basePath + "cloud-purchase-web/phmTransactionReportDetailController/slconslist",
        	method:"post",
        	queryParams:{phmTransactionReportDetail:me.params},
            width:"100%",
            height:"100%",
            rownumbers: true,
            pagination: false,
            nowrap: false,
            fitColumns:true,
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            },
            columns:[[
					{field:'check',checkbox:true},
					{field:'attornType',title:'合同转让方向',width:width,
						 editor:{type:'combobox',options:{
							 	required:true,
							 	editable:false,
			           		 	method:"get",
			                    valueField: 'value',
			                    textField: 'name',
			                    panelHeight:'auto',
			                    url: "${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_attornType"
					 }}},
					 {field:'reportPq',title:'申报电量（兆瓦时）',width:width,
                         editor:{
                        	
                        	 type:'numberbox',
                             options:{
                            	 required:true,
                            	 precision:3,
                            	 min:0,
                     		 }
                     	 }
                     },
                      {field:'reportPrc',title:'申报电价（元/兆瓦时）',width:width,
                          editor:{
                         	 type:'numberbox',
                              options:{
                            	 required:true,                            	  
                             	 precision:2,
                             	 min:0,
                      		 }
                      	 }
                      }
                 ]],
                 onLoadSuccess:function(data){
                	 var rows = data.rows;
                	 for(var i=0; i<rows.length; i++){
                		 $("#reportInputGrid").datagrid("beginEdit",i);
                	 }
                 }
            });
        if(!me.attornTypeFlag){
    		$("#reportInputGrid").datagrid('hideColumn','attornType');
    		//隐藏合同装让方向列时 ，取消必填校验
    		var tt=$('#reportInputGrid').datagrid('getColumnOption', 'attornType'); //通过列名获得此列
            tt.editor.options={required: false};
    	}
    	//按钮组
        MainFrameUtil.setDialogButtons(this.getButtons(),"report_add");
    },
    methods:{
    	//按钮组
    	getButtons:function(){
    		var that = this;
    		var buttons = [
    			{text:"保存",type:"primary",handler:function(){
    	    		//验证表单信息是否完善
    	    		var param = that.validateMessage();
    	    		if(param == null){
    	    			MainFrameUtil.alert({title:"提示",body:"请完善信息！"});
    	                return;
    	    		}
    	    		if(param == "pqPrcZero"){
    	    			MainFrameUtil.alert({title:"提示",body:"申报电量和申报电价必填且不能为0！"});
    					return;
    	    		}
    	    		if(param.insertList.length == 0 && param.updateList.length == 0 && that.ids.length == 0){
    	    			MainFrameUtil.alert({title:"提示",body:"未做修改！"});
    	                return; 
    	    		}
    	    		param["ids"] = that.ids;
    	    		param["tradePeriod"] = MainFrameUtil.getParams("report_add").tradePeriod;
    	    		$.ajax({
    	                url : basePath+"cloud-purchase-web/phmTransactionReportDetailController/consreport",
    	                type : "post",
    	                data:$.toJSON(param), //只提交新增和修改的行
    	                contentType : 'application/json;charset=UTF-8',
    	                success : function(data) {
    	                    if(data.flag){
    	                        MainFrameUtil.alert({title:"成功提示",body:data.msg});
    	                    	MainFrameUtil.closeDialog("report_add");
    	                    }else{
    	                        MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
    	                    }
    	                },
    	                error : function() {
    	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
    	                }
    	            });
    	    	}},
    		    {text:"取消",handler:function(){MainFrameUtil.closeDialog("report_add")}}];
            return buttons;
    	},
    	//新增
    	add:function(){
    		$("#reportInputGrid").datagrid("appendRow",{});
    		var index = $("#reportInputGrid").datagrid("getRows").length - 1;
    		$("#reportInputGrid").datagrid("beginEdit",index);
    	},
    	
    	//删除
    	del:function(){
    		var rows = $("#reportInputGrid").datagrid("getSelections");
    		for(var obj in rows){
    			var index = $("#reportInputGrid").datagrid("getRowIndex",rows[obj]);
    			$("#reportInputGrid").datagrid("deleteRow",index);
    			if(rows[obj].id){
    				this.ids.push(rows[obj].id);
    			}
    		}
    	},
    	//验证表单信息是否完善
    	validateMessage: function(){
    		var param = new Object();
    		var insertList = new Array();
    		var updateList = new Array();
    		var rows =  $("#reportInputGrid").datagrid("getRows");
    		for(var i=0; i<rows.length; i++){
    			//验证单元格必填
 				if(!$('#reportInputGrid').datagrid('validateRow', i)){
					return null;
		    	}
 				var reportPq = $($('#reportInputGrid').datagrid('getEditor', {index:i,field:'reportPq'}).target).numberbox('getValue');
 				var reportPrc = $($('#reportInputGrid').datagrid('getEditor', {index:i,field:'reportPrc'}).target).numberbox('getValue');
    			if(reportPq == null || reportPq == 0 || reportPrc == null || reportPrc == 0){
    				return 'pqPrcZero';
    			}
    			var object = new Object();
    			var value = null;
    			var editors = $("#reportInputGrid").datagrid("getEditors",i);
        		for(var obj in editors){
        			if(editors[obj].field == "attornType"){
        				value = $(editors[obj].target).combobox("getValue");
        			}else{
        				value = $(editors[obj].target).val();
        			}
        			object[editors[obj].field] = value;
        			object["ym"] = this.params.ym;
        			object["planId"] = this.params.planId;
        			object["consId"] = this.params.consId;
        			object["stage"] = i+1;
        			if(!value && !this.attornTypeFlag && editors[obj].field != "attornType"){
        				$("#reportInputGrid").datagrid("selectRow",i);
        				return null;
        			}
        			if(!value && this.attornTypeFlag){
        				$("#reportInputGrid").datagrid("selectRow",i);
        				return null;
        			}
        		}
        		
    			if(rows[i].id){
    				object["id"] = rows[i].id;
    				updateList.push(object);
    			}else{
    				insertList.push(object);
    			}
    		}
    		param["insertList"] = insertList;
    		param["updateList"] = updateList;
    		return param;
    	}
    }
})
</script>
</body>
</html>