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
        params:{
        	stage:null,
        	reportPq:null,
        	reportPrc:null
        },
        ym:null,
        dealPqTotal:0,
        //dealPrcTotal:0,
        ids:[],
        attornTypeFlag:false
    },
    ready:function(){
    	var me = this;
    	//获取购电计划id
    	me.params = $.parseJSON($.toJSON(MainFrameUtil.getParams("dealInput").params));
    	me.ym = MainFrameUtil.getParams("dealInput").ym;
    	var flag = MainFrameUtil.getParams("dealInput").flag;
    	if(flag){
    		me.attornTypeFlag = true;
    	}
    	//列表数据加载
        $("#dealInputGrid").datagrid({
        	url:basePath + "cloud-purchase-web/phmDealInfoController/dealList",
        	method:"post",
        	queryParams:{phmDealInfo:{planId:this.params.planId,reportDetailId:this.params.reportId}},
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
					{field:'dealPq',title:'成交电量（兆瓦时）',width:'32%',
                         editor:{
                        	 type:'numberbox',
                             options:{
                            	 precision:3,
                            	 min:0,
                            	 required:true
                     		 }
                     	 }
                     },
                      {field:'dealPrc',title:'成交电价（元/兆瓦时）',width:'32%',
                          editor:{
                         	 type:'numberbox',
                              options:{
                             	 precision:2,
                             	 min:0,
                             	required:true
                      		 }
                      	 }
                      },
                      {field:'producerId',title:'交易对象',width:'32%',
                    	  editor:{type:'combobox',options:{
 							 url:basePath+"cloud-purchase-web/phmPpaController/getPhcElecInfoList",
 					         method:'get',
 					         valueField:'id',
 					         textField:'elecName',
 					         editable:false, 
 					         panelHeight: 'auto',
 					         onChange:function(value){//改变时判断该电厂是否已存在，暂定不校验
 					        	 /* if(value){
 					        		 me.validateProducer(value);
 					        	 } */
 					         }
 					 }}},
                      {field:'traderName',title:'交易对象',width:'32%',editor:{type:'textbox',options:{maxlength:32,required:true}}}
                 ]],
                 onLoadSuccess:function(data){
                	 var rows = data.rows;
                	 for(var i=0; i<rows.length; i++){
                		 $("#dealInputGrid").datagrid("beginEdit",i);
                	 }
                 }
            });
        if(!me.attornTypeFlag){
    		$("#dealInputGrid").datagrid('hideColumn','traderName');
    	}else{
    		$("#dealInputGrid").datagrid('hideColumn','producerId');
    	}
    	
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
    	    		if(param.insertList.length == 0 && param.updateList.length == 0 && that.ids.length == 0){
    	    			MainFrameUtil.alert({title:"提示",body:"未做修改！"});
    	                return; 
    	    		}
    	    		if(that.dealPqTotal > that.params.reportPq){
    	    			MainFrameUtil.alert({title:"提示",body:"此段总成交电量不得大于申报电量！"});
    	    			return;
    	    		}
    	    		param["ids"] = that.ids;
    	    		$.ajax({
    	                url : basePath+"cloud-purchase-web/phmDealInfoController",
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
    	
    	//计算总成交电量
    	countDealPq:function(value){
    		var rows = $("#dealInputGrid").datagrid("getRows");
			var pq = 0;
			for(var i=0; i<rows.length; i++){
				var editors = $("#dealInputGrid").datagrid("getEditors",i);
				if(editors){
					for(var obj in editors){
	        			if(editors[obj].field == "dealPq"){
	        				var value = $(editors[obj].target).val();
	        				pq += Number(value);
	        			}
	        		}
				}
			}
    		this.dealPqTotal = pq;
    	},
    	
    	//计算总成交电价
		countDealPrc:function(value){
			var rows = $("#dealInputGrid").datagrid("getRows");
			var prc = 0;
			for(var i=0; i<rows.length; i++){
				var editors = $("#dealInputGrid").datagrid("getEditors",i);
				if(editors){
					for(var obj in editors){
	        			if(editors[obj].field == "dealPrc"){
	        				var value = $(editors[obj].target).val();
	        				prc += Number(value);
	        			}
	        		}
				}
			}
    		this.dealPrcTotal = prc;
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
    			if(rows[obj].id){
    				this.ids.push(rows[obj].id);
    			}
    		}
    	},
    	
    	//验证该电厂是否存在,暂定不校验
    	/* validateProducer:function(value){
    		var producerIdList = new Array();
    		var rows = $("#dealInputGrid").datagrid("getRows");
    		var firstEditor = $("#dealInputGrid").datagrid("getEditor",{index:0,field:"producerId"});
    		if(firstEditor){
    			var firseId = $(firstEditor.target).combobox("getValue");
        		producerIdList.push(firseId);
        		if(rows.length > 1){
        			for(var i=1; i<rows.length; i++){
            			var editor = $("#dealInputGrid").datagrid("getEditor",{index:i,field:"producerId"});
            			if(editor){
            				var producerId = $(editor.target).combobox("getValue");
                			for(var j=0; j<producerIdList.length; j++){
                				if(producerId == producerIdList[j]){
                					MainFrameUtil.alert({
                						title:"提示",
                						body:"该电厂已存在！",
                						onClose:function(){
                							$("#dealInputGrid").datagrid("updateRow",{index:i,row:{producerId:"",dealPq:"",dealPrc:""}});
                							$("#dealInputGrid").datagrid("beginEdit",i);
                						}
                					});
                	                return;
                				}
                			}
                			producerIdList.push(producerId);
            			}
            		}
        		}
    		}
    	}, */
    	
    	//验证表单信息是否完善
    	validateMessage:function(){
    		var me = this;
    		var param = new Object();
    		var insertList = new Array();
    		var updateList = new Array();
    		var rows =  $("#dealInputGrid").datagrid("getRows");
    		for(var i=0; i<rows.length; i++){
    			var object = new Object();
    			var value = null;
    			var editors = $("#dealInputGrid").datagrid("getEditors",i);
        		for(var obj in editors){
        			if(editors[obj].field == "producerId"){
        				value = $(editors[obj].target).combobox("getValue");
        			}else{
        				value = $(editors[obj].target).val();
        			}
        			object[editors[obj].field] = value;
        			object["ym"] = this.ym;
        			object["planId"] = this.params.planId;
        			object["reportDetailId"] = this.params.reportId;
        			object["subitemFlag"] = "0";
        			if(me.attornTypeFlag){
        				if(editors[obj].field != "producerId"){
        					if(!value){
                				$("#dealInputGrid").datagrid("selectRow",i);
                				return null;
                			}
        				}
        			}else{
        				if(editors[obj].field != "attornType" && editors[obj].field != "traderName" && editors[obj].field != "producerId"){
        					if(!value){
                				$("#dealInputGrid").datagrid("selectRow",i);
                				return null;
                			}
        				}
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
    },
    watch:{
    	//监听总成交电量要小于申报电量
    	'dealPqTotal':function(){
    		var me = this;
    		if(me.dealPqTotal > me.params.reportPq){
				MainFrameUtil.alert({title:"提示",body:"此段总成交电量不得大于申报电量！"});
    		}
    		
    	}
    }
})
</script>
</body>
</html>