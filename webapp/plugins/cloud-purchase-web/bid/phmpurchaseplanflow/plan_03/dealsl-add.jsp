<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理竞价交易成交成交信息录入</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="dealInputVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
	    <mk-panel title="成交信息录入列表" line="true" height="100%" slot="bottom">
	        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	        </div>
	        <div id="dealInputGrid" class="col-xs-12" style="height:100%"></div>
	    </mk-panel>
	</mk-top-bottom>
    
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var dealInputVue = new Vue({
    el: '#dealInputVue',
    data: {
        formFields:{},
        ym:null,
        consIds:[],
        planId:null,
        ids:[],
        attornTypeFlag:false
    },
    ready:function(){
    	var me = this;
    	//获取购电计划id
    	me.planId = MainFrameUtil.getParams("dealInput").planId;
    	me.ym = MainFrameUtil.getParams("dealInput").ym;
    	var flag = MainFrameUtil.getParams("dealInput").flag;
    	if(flag){
    		me.attornTypeFlag = true;
    	}
    	var rows = $.parseJSON($.toJSON(MainFrameUtil.getParams("dealInput").params));
    	for(var i=0 ; i<rows.length ; i++){
	    	me.consIds.push(rows[i].consId);
    	}
    	//列表数据加载
        $("#dealInputGrid").datagrid({
        	url:basePath + "cloud-purchase-web/phmDealInfoController/slSbDealList",
        	method:"post",
        	queryParams:{phmDealInfo:{planId:me.planId},consIds:me.consIds,flag:flag},
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
                    {field:'dealPrc',title:'成交电价（元/兆瓦时）',width:'32%',editor:{type:'numberbox',options:{required:true,precision:2, min:0}}},
                    {field:'producerId',title:'交易对象',width:'33%',
                    	  editor:{type:'combobox',options:{
 							 url:basePath+"cloud-purchase-web/phmPpaController/getPhcElecInfoList",
 					         method:'get',
 					         valueField:'id',
 					         textField:'elecName',
 					         editable:false, 
 					         panelHeight: '200px',
 					       	 required:true,
 					         onSelect:function(data){
 					        	var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
                            	me.getGenerator(data,rowIndex);
                             }
 					 }}},
 					{field:'generatorId',title:'机组',width:'32%',align:'center',
					 		editor:{type:'combobox',options:{
					         valueField:'id',
					         textField:'geneName',
					         editable:false, 
					         panelHeight: 'auto'
					 	}}},
                    {field:'traderName',title:'交易对象',width:'33%',editor:{type:'textbox',options:{required:true,maxlength:32}}},
                    {field:'attornType',title:'合同转让方向',width:'32%',align:'center',
	                   	 editor:{type:'combobox',options:{
							 	required:true,
			           		 	method:"get",
			                    valueField: 'value',
			                    textField: 'name',
			                    panelHeight:'auto',
			                    required:true,
			                    url: "${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_attornType",
					 	}}}
                 ]],
                 onLoadSuccess:function(data){
                	 var rows = data.rows;
                	 me.ids = data.data;
                	 for(var i=0; i<rows.length; i++){
                		 $("#dealInputGrid").datagrid("beginEdit",i);
                	 }
                	if(!me.attornTypeFlag){
                 		$("#dealInputGrid").datagrid('hideColumn','traderName');
                 		$("#dealInputGrid").datagrid('hideColumn','attornType');
                 	}else{
                 		$("#dealInputGrid").datagrid('hideColumn','producerId');
                 		$("#dealInputGrid").datagrid('hideColumn','generatorId');
                 	}
                 }
            });
        
    	
    	//按钮组
        MainFrameUtil.setDialogButtons(this.getButtons(),"dealInput");
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
    	    		param["ids"] = that.ids;
    	    		param["flag"] = true;
    	    		$.ajax({
    	                url : basePath+"cloud-purchase-web/phmDealInfoController/SLTradeSave",
    	                type : "post",
    	                data:$.toJSON(param), //只提交新增和修改的行
    	                contentType : 'application/json;charset=UTF-8',
    	                success : function(data) {
    	                    if(data.flag){
    	                    	MainFrameUtil.closeDialog("dealInput");
    	                        MainFrameUtil.alert({title:"成功提示",body:data.msg});
    	                    }else{
    	                        MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
    	                    }
    	                },
    	                error : function() {
    	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
    	                }
    	            });
    	    	}},
    		    {text:"取消",handler:function(){MainFrameUtil.closeDialog("dealInput")}}];
            return buttons;
    	},
    	getGenerator:function(data,rowIndex){ //根据电厂获取机组
			if(data != undefined && data.id != null && data.id !=''){
				$.ajax({
	        		 url : basePath + "cloud-purchase-web/powerPlantController/getGenerator/" + data.id,
	        		 type : 'GET',
	        		 contentType : 'application/json;charset=UTF-8',
	        		 success:function(data){
	         			var ed = $('#dealInputGrid').datagrid('getEditor', {index:rowIndex,field:'generatorId'});
	         			var flag = true;
	         			for(var i = 0 ; i < data.data.length ; i++){
	         				if(data.data[i].id == $('#dealInputGrid').datagrid('getRows')[rowIndex].generatorId){
	         					flag = false;
	         					break;
	         				}
	         			}
	         			if(flag){
		         			$(ed.target).combobox('clear');
	         			}
	         			$(ed.target).combobox('loadData', data.data);
	        		 }
	        	 });
			}
		},
    	
    	//新增
    	add:function(){
    		$("#dealInputGrid").datagrid("appendRow",{});
    		var index = $("#dealInputGrid").datagrid("getRows").length - 1;
    		$("#dealInputGrid").datagrid("beginEdit",index);
    	},
    	
    	//删除
    	del:function(){
    		var rows = $("#dealInputGrid").datagrid("getSelections");
    		if(rows.length == 0){
    			MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
                return;
    		}
    		for(var obj in rows){
    			var index = $("#dealInputGrid").datagrid("getRowIndex",rows[obj]);
    			$("#dealInputGrid").datagrid("deleteRow",index);
    		}
    	},
    	
    	//验证表单信息是否完善
    	validateMessage:function(){
    		var me = this;
    		var param = new Object();
    		var insertList = new Array();
    		var updateList = new Array();
    		var rows =  $("#dealInputGrid").datagrid("getRows");
    		for(var i=0; i<rows.length; i++){
    			for(var j=0 ; j < me.consIds.length ; j++){
	    			var object = new Object();
	    			var value = null;
	    			var editors = $("#dealInputGrid").datagrid("getEditors",i);
	        		for(var obj in editors){
	        			if(editors[obj].field == "producerId" || editors[obj].field == "generatorId"|| editors[obj].field == "attornType"){
	        				value = $(editors[obj].target).combobox("getValue");
	        			}else{
	        				value = $(editors[obj].target).val();
	        			}
	        			object[editors[obj].field] = value;
	        			object["ym"] = me.ym;
	        			object["planId"] = me.planId;
	        			object["consId"] = me.consIds[j]; 
	        			object["subitemFlag"] = "0";
	        			if(!value && me.attornTypeFlag && editors[obj].field != "producerId" && editors[obj].field != "generatorId"){
	           				$("#dealInputGrid").datagrid("selectRow",i);
	           				return null;
	        			}
	        			if(!value && !me.attornTypeFlag && (editors[obj].field == "producerId" || editors[obj].field == "dealPrc")){
	           				$("#dealInputGrid").datagrid("selectRow",i);
	           				return null;
	        			}
	        		}
    				insertList.push(object);
    			}
    		}
    		param["insertList"] = insertList;
    		return param;
    	}
    }
})
</script>
</body>
</html>