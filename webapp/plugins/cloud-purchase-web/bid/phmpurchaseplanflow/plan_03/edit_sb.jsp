<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-成交信息录入 （双边协商方式）</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
<style type="text/css">
.datagrid-row-selected{
	color:#000000;
}
</style>
<title>成交信息录入</title>
</head>
<body id="examineVue" v-cloak style="height:100%">
	<div id = "dealinfo" style="height:90%">
		<mk-form-panel num="3" height="20%;">
			<mk-form-row>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety" disabled="disabled"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :data.sync="params.phmPurchasePlanMonth.ym" name="ym" disabled="disabled"></su-datepicker>
				</mk-form-col>
				<!-- 已成交电量 -->
				<mk-form-col :label="formFields.dealPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.dealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
				<mk-form-col :label="formFields.reportPq.label" colspan="1">
					<su-textbox :data.sync="countParams.agrePq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 成交电量  -->
				<mk-form-col :label="formFields.newDealPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.newDealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<div style="height:80%;">
			<mk-panel title="成交信息" line="true" slot="bottom" height="100%">
		        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" @click="deleteRow">删除</su-button>
		        </div>
		        <div id="dealGrid" class="col-xs-12" style="height:100%"></div>
		    </mk-panel>
	    </div>
	    <div id = "subGrid">
		    <mk-panel title="年度电量分月计划（兆瓦时）" line="true"  >
		        <div id="yearGrid" style="min-height:200px"></div>
		        <div id="monthGrid" style="min-height:200px"></div>
		    </mk-panel>
	    </div>
	</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var mineVue = new Vue({
    el: '#examineVue',
    data: {
        formFields:{},
        id:"",
        ym:"",
        params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null,reportPq:null,newDealPq:null},
		count:0,
		ids:[]
    },
    ready:function(){
    	var me = this;
    	MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
    	me.id = location.search.substr(8);
    	$("#subGrid").hide();
    	$("#yearGrid").hide();
    	$("#monthGrid").hide();
    	ComponentUtil.getFormFields("purchase.bid.transactionlreport",this); 
    	me.initData();
    },
    methods:{
    	//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc", handler:function(btn,params){me.save(btn.target)}},
	               {text:"提交",type:"primary", handler:function(btn,params){me.submit(btn.target)} },
	               {text:"回退",type:"warning", bgcolor:"#ecaa55",handler:me.reply},
	               {text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_edit")}}];
            return buttons;
        },
    	initData:function(){
			var me = this;
			//初始化数据
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.id,
				type : 'get',
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data.data!=null){
						me.params.phmPurchasePlanMonth = data.data;
						if(data.extMap != null){
	                		if(data.extMap["agrePq"] != null){
	                			me.countParams.agrePq = data.extMap["agrePq"];
	                		}
	                		if(data.extMap["dealPq"] != null){
								me.countParams.dealPq = data.extMap["dealPq"];
	                		}
	                		if(data.extMap["reportPq"] != null){
	                			me.countParams.reportPq = data.extMap["reportPq"];
	                		}
	                	}
						me.initDealGrid();		//列表数据加载
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"查询明细失败！"});
				}
			});
		},
		initDealGrid:function(){
			var me = this;
			me.count = 0;
			$("#dealGrid").datagrid({
	    		url: basePath + 'phmDealInfoController/getListSb/'+me.id,
	            method: 'get',
	            width:'100%',    
	            striped : true,
	            rownumbers: true,
	            pagination : false,
	            singleSelect:true,
	            checkOnSelect:true,
	            fitColumns:true,
	            nowrap:true,
	            loadMsg:"请稍后",
	            scrollbarSize:0,
	            columns:[[
							{field:'check',checkbox:true}, 
	                        {field:'producerId',title:'交易对象',width:'22%',align:'center',
								editor:{type:'combobox',options:{
		 							 url:basePath+"cloud-purchase-web/phmPpaController/getPhcElecInfoList",
		 					         method:'get',
		 					         valueField:'id',
		 					         textField:'elecName',
		 					         editable:false, 
		 					         panelHeight: '200px',
		 					         onSelect:function(data){
		 					        	//eval("(this)")
		 					        	var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
                                    	me.getGenerator(data,rowIndex);
                                    }
		 					 }}},
	                        {field:'traderName',title:'交易对象',align:'center',width:'22%',editor:{type:'textbox',options:{required:true,maxlength:32}}},
    						{field:'attornType',title:'合同转让方向',width:'25%',align:'center',
	                        	 editor:{type:'combobox',options:{
	 							 	required:true,
	 							 	editable:false,
	 			           		 	method:"get",
	 			                    valueField: 'value',
	 			                    textField: 'name',
	 			                    panelHeight:'auto',
	 			                    url: "${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_attornType",
	 			                    onSelect:function(data){
	 			                    	var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
                                    	me.attornChange(data,rowIndex);
	 			                    }
	 					 	}}},
                            {field:'generatorId',title:'机组',width:'25%',align:'center',
	 					 		editor:{type:'combobox',options:{
	 					         valueField:'id',
	 					         textField:'geneName',
	 					         editable:false, 
	 					         panelHeight: 'auto'
	 					 	}}},  
							{field:'dealPq',title:'成交电量（兆瓦时）',width:'25%',align:'center',
                            	editor:{type:'numberbox',options:{
                                    required:true,
                                    precision:3
                                }}
							}, 
	                        {field:'dealPrc',title:'成交电价（元/兆瓦时）',width:'25%',align:'center',
                                	editor:{type:'numberbox',options:{
                                        required:true,
                                        precision:2
                           }}},
	                    ]],
	            rowStyler:function(idx,row){
	                return "height:35px;font-size:12px;";
	            },
	            onLoadSuccess: function(data){
	            	var rows = data.rows;
	            	var totalDealPq = 0;
	            	for(var i=0 ; i<rows.length; i++){
	            		totalDealPq += parseFloat(rows[i].dealPq == null ? 0 : rows[i].dealPq);
	            		$("#dealGrid").datagrid('beginEdit',i);
	            	}
	            	me.countParams.newDealPq = totalDealPq.toFixed(3);
	            	me.calNewDealPq();
	            }
	        });
			me.monthDetailGrid();	//年度电量分月计划列表加载
	    	if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){//合同转让
	    		$("#dealGrid").datagrid('hideColumn','producerId');//下拉交易对象
	    		$("#dealGrid").datagrid('hideColumn','generatorId');//机组
	    	}else{
	    		$("#dealGrid").datagrid('hideColumn','attornType');//合同转让方向
	    		$("#dealGrid").datagrid('hideColumn','traderName');//填写的交易对象
	    		$("#dealGrid").datagrid('getColumnOption','attornType').editor.options={required: false};//隐藏必填列的同时去掉必填属性
	    		$("#dealGrid").datagrid('getColumnOption','traderName').editor.options={required: false};//隐藏必填列的同时去掉必填属性
	    	}
		},
		attornChange:function(data,rowIndex){
			var attornTypeName = data.name;
			if(this.params.phmPurchasePlanMonth.tradePeriod == "01"){
				if($("#yearGrid").datagrid("getRows").length-1 < rowIndex){//年交易新增行
					$("#yearGrid").datagrid("appendRow",{traderName:null,attornType:attornTypeName,totalDealPq:0,mon1:0,mon2:0,mon3:0,mon4:0,mon5:0,mon6:0,mon7:0,mon8:0,mon9:0,mon10:0,mon11:0,mon12:0});
					$("#yearGrid").datagrid('beginEdit',rowIndex);
				}else{
					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='attornType']").eq(rowIndex).children().text(attornTypeName);
				}
			}
		},
		getGenerator:function(data,rowIndex){ //根据电厂获取机组
			if(data != undefined && data.id != null && data.id !=''){
				$.ajax({
	        		 url : basePath + "cloud-purchase-web/powerPlantController/getGenerator/" + data.id,
	        		 type : 'GET',
	        		 contentType : 'application/json;charset=UTF-8',
	        		 success:function(data){
	         			var ed = $('#dealGrid').datagrid('getEditor', {index:rowIndex,field:'generatorId'});
	         			var flag = true;
	         			for(var i = 0 ; i < data.data.length ; i++){
	         				if(data.data[i].id == $('#dealGrid').datagrid('getRows')[rowIndex].generatorId){
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
			//年度交易同步月份拆分列表
			if(this.params.phmPurchasePlanMonth.tradePeriod == "01"){
				traderName = data.elecName;
				if($("#yearGrid").datagrid("getRows").length-1 < rowIndex){//年交易新增行
					$("#yearGrid").datagrid("appendRow",{traderName:traderName,attornType:null,totalDealPq:0,mon1:0,mon2:0,mon3:0,mon4:0,mon5:0,mon6:0,mon7:0,mon8:0,mon9:0,mon10:0,mon11:0,mon12:0});
					$("#yearGrid").datagrid('beginEdit',rowIndex);
				}else{
					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(rowIndex).children().text(traderName);
					$("#yearGrid").datagrid("getRows")[rowIndex].traderName = traderName;
				}
			}
		},
		monthDetailGrid:function(){
			var me = this;
			var flag = "00";
			if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
				flag = "01";
			}
			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易，取本次交易
				$("#subGrid").show();
				$("#yearGrid").show();
				$("#yearGrid").datagrid({
					url: basePath + 'phmDealInfoController/getSbYearDealInfo/'+me.id+flag,
		            method: 'get',
		            width:'100%',    
		            striped : true,
		            pagination : false,
		            singleSelect:true,
		            checkOnSelect:true,
		            fitColumns:true,
		            nowrap:true,
		            loadMsg:"请稍后",
		            scrollbarSize:0,
		            frozenColumns:[[
								{field:'traderName',title:'交易对象',width:'18%',align:'center'}, 
								{field:'attornType',title:'合同转让方向',width:'8%',align:'center'},
								{field:'totalDealPq',title:'总交易电量',width:'10%',align:'center'}
		            ]],
		            columns:[[
								{field:'mon1',title:'1月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon2',title:'2月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon3',title:'3月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon4',title:'4月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon5',title:'5月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon6',title:'6月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon7',title:'7月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon8',title:'8月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon9',title:'9月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon10',title:'10月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon11',title:'11月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}, 
								{field:'mon12',title:'12月',width:'10%',align:'center',editor:{type:'numberbox',options:{precision:3}}}
		                    ]],
		            rowStyler:function(idx,row){
		                return "height:35px;font-size:12px;";
		            },
		            onLoadSuccess: function(data){
		            	for(var i = 0 ; i < data.rows.length ; i++){
		            		var m = i+1
		            		if(data.rows[i].traderName == null || data.rows[i].traderName == ''){
		            			$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text("双边协商"+m);
		            			data.rows[i].traderName = "双边协商"+m;
		            		}
		            	}
		            	var rows = data.rows;
		            	if(rows != null && rows.length > 0){
		            		for(var i=0 ; i<rows.length ; i++){
		            			$("#yearGrid").datagrid('beginEdit',i);
		            		}
		            	}
		            }
		        });
				if(me.params.phmPurchasePlanMonth.tradeVariety != "03"){
		    		$("#yearGrid").datagrid('hideColumn','attornType');
		    	}
			}else{//月交易,取本年度 年交易已成交信息
				var rows = null;
				var columns = me.columnsEditor();
				$("#subGrid").show();
				$("#monthGrid").show();
				$("#monthGrid").datagrid({
					url: basePath + 'phmDealInfoController/getMonthDealInfo/'+me.params.phmPurchasePlanMonth.ym,
		            method: 'get',
		            width:'100%',    
		            striped : true,
		            pagination : false,
		            singleSelect:true,
		            checkOnSelect:true,
		            fitColumns:true,
		            nowrap:true,
		            loadMsg:"请稍后",
		            scrollbarSize:0,
		            frozenColumns:[[
								{field:'tradeVariety',title:'交易品种',width:'10%',align:'center'},
								{field:'tradeMode',title:'交易方式',width:'10%',align:'center'},
								{field:'traderName',title:'交易对象',width:'18%',align:'center'}, 
								{field:'attornType',title:'合同转让方向',width:'8%',align:'center'},
								{field:'totalDealPq',title:'总交易电量',width:'12%',align:'center'}
		            ]],
		            columns:[columns],
		            rowStyler:function(idx,row){
		                return "height:35px;font-size:12px;";
		            },
		            onLoadSuccess: function(data){
		            	if(data.rows != null && data.rows.length > 0){
		            		for(var i = 0 ; i < data.rows.length ; i++){
			            		var m = i+1
			            		if(data.rows[i].traderName == null || data.rows[i].traderName == ''){
			            			$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text("双边协商"+m);
			            			data.rows[i].traderName = "双边协商"+m;
			            		}
			            	}
			            	rows = data.rows;
			            	for(var i = 0 ; i < rows.length ; i++){
			            		$("#monthGrid").datagrid('beginEdit',i);
			            	}
		            	}else{
		            		$("#subGrid").hide();
		    				$("#monthGrid").hide();
		            	}
		            }
		        });
			}
		},
		columnsEditor:function(){
			var me = this;
			var ym = me.params.phmPurchasePlanMonth.ym;
			var columns = [];
			var month = ym.substr(ym.length-2);
			for(var i=1 ; i < 13 ; i++){
				var obj = {};
				obj["field"] = "mon"+i;
				obj["title"] = i+"月";
				obj["width"] = "10%";
				obj["align"] = "center";
				if(i >= Number(month)){
					var editor = {type:'numberbox',options:{precision:3}}
					obj["editor"] = editor;
				}
				columns.push(obj);
			}
			return columns;
		},
    	submit:function(target){
    		var me = this;
    		me.saveData(true, target);
        },
        reply:function(){
			var me = this;
		    MainFrameUtil.confirm({
		        title:"确认",
		        body:"确定将计划退回？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.messager.progress({title:"请等待",msg:"正在退回...",interval:100});
		    			$.ajax({
		    				url:basePath+"cloud-purchase-web/phmPurchasePlanMonthController/recall",
		    				method:'put',
		    				data:$.toJSON({id:me.id ,planStatus:"02"}),
		    				contentType : 'application/json;charset=UTF-8',
		    				success:function(data){
		    					if(data != null){
		    						$.messager.progress('close');
		    						MainFrameUtil.alert({title:"提示",body:data.msg});
		    						MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
		    						MainFrameUtil.closeDialog("plan_edit");
		    					}else{
		    						$.messager.progress('close');
		    						MainFrameUtil.alert({title:"提示",body:"退回失败，请刷新页面重试！"});
		    					}
		    				}
		    			});
		            }
		        }
    		});
		},
		save: function(target){
    		var me = this;
    		me.saveData(false, target);
    	},
    	saveData:function(flag, target){
    		var me = this;
			//按钮不可点击
       		$(target).attr("disabled","disabled");
    		var msg = me.calculate(true);
			var rows = $('#dealGrid').datagrid('getRows');
			
			if(rows == null || rows.length == 0){
         		MainFrameUtil.alert({title:"提示",body:"请先添加成交录入信息!"});
				//按钮可点击
				$(target).attr("disabled",false);
         		return;
         	}
			//列表必填
			if(msg == "dataNoAll"){
	    		MainFrameUtil.alert({title:"提示",body:"成交信息列表有必填项未填！"});
				//按钮可点击
				$(target).attr("disabled",false);
				return;
         	}
			for(var i = 0; i < rows.length; i++){
         		rows[i].planId = me.id;
         		rows[i].ym = me.params.phmPurchasePlanMonth.ym;
         	}
			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//保存年交易，同步保存
				var list = new Array();
				var yearRows = $("#yearGrid").datagrid("getRows");
    			for(var i=0; i<yearRows.length; i++){
    				var total = 0;
        			var object = yearRows[i];
        			var value = null;
        			var editors = $("#yearGrid").datagrid("getEditors",i);
            		for(var obj in editors){
            			value = $(editors[obj].target).val();
            			object[editors[obj].field] = value;
    	        		if(flag && !value){
            				$("#yearGrid").datagrid("selectRow",i);
            				MainFrameUtil.alert({title:"提示",body:"请完善年度电量分月计划后再提交！"});
            				//按钮可点击
            				$(target).attr("disabled",false);
            				return;
    	        		}
    	        		total += parseFloat(value == null? 0 : value);
            		}
            		if(total > yearRows[i].totalDealPq){
            			MainFrameUtil.alert({title:"提示",body: yearRows[i].traderName+"的分月电量之和已超过总交易电量，请重新填写分月计划！"});
            			//按钮可点击
        				$(target).attr("disabled",false);
        				return;
            		}
            		if(flag && total != yearRows[i].totalDealPq){
            			MainFrameUtil.alert({title:"提示",body: yearRows[i].traderName+"的分月电量之和与总交易电量不等，请重新填写分月计划后再提交！"});
            			//按钮可点击
        				$(target).attr("disabled",false);
        				return;
            		}
       				list.push(object);
        		}
        		$.ajax({
                    url : basePath+"cloud-purchase-web/phmDealInfoController/saveSbYearList",
                    type : "post",
                    data:$.toJSON({insertList:rows,ids:me.ids,monthList:list,id:me.id,ym:me.params.phmPurchasePlanMonth.ym}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag){
                        	if(!flag){
                            	MainFrameUtil.alert({title:"成功提示",body:data.msg});
                            	//按钮可点击
                				$(target).attr("disabled",false);
                        	}else{
                        		me.submitData(target);
                        	}
                        }else{
                            MainFrameUtil.alert({title:"失败提示",body:data.msg});
                          	//按钮可点击
            				$(target).attr("disabled",false);
                        }
                    },
                    error : function() {
                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                    	//按钮可点击
        				$(target).attr("disabled",false);
                    }
                });
			}else{//月交易，异步保存
				$.ajax({
					url : basePath+"cloud-purchase-web/phmDealInfoController",
	                type : "post",
	                data : $.toJSON({insertList:rows,ids:me.ids}),
	                contentType : 'application/json;charset=UTF-8',
	                success : function(data) {
	                    if(data.flag == true){
	                    	me.saveMonthDetail(flag, target);
	                    }else{
	                        MainFrameUtil.alert({title:"失败提示",body:data.msg});
	                     	//按钮可点击
		    				$(target).attr("disabled",false);
	                    }
	                },
	                error : function() {
	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
	                  	//按钮可点击
	    				$(target).attr("disabled",false);
	                }
	            });
			}
    	},
		//保存拆月信息, 保存月交易
    	saveMonthDetail:function(flag, target){
    		var me = this;
    		var list = new Array();
   			var rows = $("#monthGrid").datagrid("getRows");
   			var ym = me.params.phmPurchasePlanMonth.ym;
   			if(rows != null && rows.length > 0){
    			for(var i=0; i<rows.length; i++){
	   				var total = 0;
        			var object = rows[i];
        			var value = null;
        			var editors = $("#monthGrid").datagrid("getEditors",i);
            		for(var obj in editors){
            			value = $(editors[obj].target).val();
            			object[editors[obj].field] = value;
            			total += parseFloat(value == null? 0 : value);
            		}
            		for(var n = 1 ; n < Number(ym.substr(ym.length-2)) ; n++){
            			var title = "mon"+n;
            			var monthPq = rows[i][title]
            			total += parseFloat(monthPq == null? 0 : monthPq);
            		}
            		if(total > rows[i].totalDealPq){
            			MainFrameUtil.alert({title:"提示",body: rows[i].traderName+"的分月电量之和已超过总交易电量，请重新填写分月计划！"});
            			//按钮可点击
	    				$(target).attr("disabled",false);
        				return;
            		}
            		if(flag && total != rows[i].totalDealPq){
            			MainFrameUtil.alert({title:"提示",body: rows[i].traderName+"的分月电量之和与总交易电量不等，请重新填写分月计划后再提交！"});
            			//按钮可点击
	    				$(target).attr("disabled",false);
        				return;
            		}
       				list.push(object);
        		}
    			$.ajax({
                    url : basePath+"cloud-purchase-web/phmDealInfoController/saveSubMonthList",
                    type : "post",
                    data:$.toJSON({monthList:list,ym:me.params.phmPurchasePlanMonth.ym}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag){
                        	if(!flag){
	                            MainFrameUtil.alert({title:"成功提示",body:data.msg});
	                          	//按钮可点击
	    	    				$(target).attr("disabled",false);
                        	}else{
                        		me.submitData(target);
                        	}
                        }else{
                            MainFrameUtil.alert({title:"失败提示",body:data.msg});
                          	//按钮可点击
    	    				$(target).attr("disabled",false);
                        }
                    },
                    error : function() {
                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                      	//按钮可点击
	    				$(target).attr("disabled",false);
                    }
                });
   			}else{
   				if(flag){
   					me.submitData(target);
   				}else{
   					MainFrameUtil.alert({title:"成功提示",body:"信息保存成功!"});
   					//按钮可点击
    				$(target).attr("disabled",false);
   				}
   			}
    	},
    	submitData: function(target){
    		var me = this;
    		MainFrameUtil.confirm({
		        title:"确认",
		        body:"提交后数据不可修改，确认提交吗？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.ajax({
		    				url:basePath + 'cloud-purchase-web/phmDealInfoController/submit/' + me.id,
		    				type:"post",
		    				success:function(data){
		    					if(data.flag){
		    						MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
		    							MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
		    							MainFrameUtil.closeDialog("plan_edit");
		    						}}); 
		    					}else{
		    						MainFrameUtil.alert({title:"错误",body:data.msg}); 
			    					//按钮可点击
			    					$(target).attr("disabled",false);
		    					}
		    				}
		    			})
		            }else{
		            	//按钮可点击
		    			$(target).attr("disabled",false);
		            }
		        }
    		})
    	},
    	//新增
    	add:function(){
    		var that= this;
    		that.calculate(false);
   			var newRow = $("#dealGrid").datagrid("appendRow",{producerId:null,traderName:null,attornType:null,generatorId:null,dealPq:0,dealPrc:0});
   			var lastIndex = $("#dealGrid").datagrid("getRows").length - 1;
            $('#dealGrid').datagrid('selectRow', lastIndex).datagrid('beginEdit', lastIndex);
    		that.calNewDealPq();
    	},
    	calNewDealPq: function(){
    		var me = this;
    		$("#dealGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"dealPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			if(pq != null && pq != ''){
	    				me.countParams.newDealPq = me.countParams.newDealPq == null? 0 : me.countParams.newDealPq;
	    				var oldPq = $("#dealGrid").datagrid("getRows")[rowIndex].dealPq;
	    				var totalPq = parseFloat(me.countParams.newDealPq) - parseFloat(oldPq == null?0:oldPq) + parseFloat(pq);
	    				me.countParams.newDealPq = totalPq.toFixed(3);
	    				$("#dealGrid").datagrid("getRows")[rowIndex].dealPq = pq;
					}
	    			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易列表同步
    					if($("#yearGrid").datagrid("getRows").length-1 < rowIndex){//年交易新增行
    						if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
    							var traderNameEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"traderName"});
		    					traderName = traderNameEditor.actions.getValue(traderNameEditor.target); 
    							$("#yearGrid").datagrid("appendRow",{traderName:traderName,attornType:null,totalDealPq:pq,mon1:0,mon2:0,mon3:0,mon4:0,mon5:0,mon6:0,mon7:0,mon8:0,mon9:0,mon10:0,mon11:0,mon12:0});
    						}else{
		    					$("#yearGrid").datagrid("appendRow",{traderName:null,attornType:null,totalDealPq:pq,mon1:0,mon2:0,mon3:0,mon4:0,mon5:0,mon6:0,mon7:0,mon8:0,mon9:0,mon10:0,mon11:0,mon12:0});
    						}
	    					$("#yearGrid").datagrid('beginEdit',rowIndex);
	    				}else{
	    					if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
		    					var traderNameEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"traderName"});
		    					traderName = traderNameEditor.actions.getValue(traderNameEditor.target); 
		    					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(rowIndex).children().text(traderName);
	    					}
	    					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='totalDealPq']").eq(rowIndex).children().text(pq);
	    				}
    					$("#yearGrid").datagrid('getRows')[rowIndex].totalDealPq = pq;
	    			}
	    		})
	    	});
    	},
    	//删除
    	deleteRow: function(){
    		var that = this;
    		var row = $('#dealGrid').datagrid('getSelected');
    	    if(row == null){
    	    	MainFrameUtil.alert({title:"提示",body:"请选择需要删除的数据"});
    	    }else{
   	    	   MainFrameUtil.confirm({
   	 		        title:"确认",
   	 		        body:"该操作不能恢复，确定要删除选中记录吗？",
   	 		        onClose:function(type){
   	 		            if(type=="ok"){//确定
   	 		            	  var index = $('#dealGrid').datagrid("getRowIndex",row);
   	 		                 that.ids.push(row.id)
   	 		                 $('#dealGrid').datagrid("deleteRow",index);
   	 		                 if(that.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易列表同步
   	 		 	                $('#yearGrid').datagrid("deleteRow",index);
   	 		                 }
   	 		                 that.calculate(false);
   	 		            }
   	 		        }
   	     		});
    	    }
    	},
    	//flag: 是否删除未填写数据的行
        calculate:function(flag){
	       	 var me = this;
	       	 var rows = $("#dealGrid").datagrid("getRows");
			 if(flag){		//如果需要删除未填写数据的行
	 			 for(var i= rows.length - 1; i > -1 ;i--){
	 				//验证单元格必填
	 				if(!$('#dealGrid').datagrid('validateRow', i)){
	   					return "dataNoAll";
	   		    	}
	 				var edPq = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPq'});
	       			rows[i].dealPq = $(edPq.target).numberbox('getValue');
	 				var edPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPrc'});
		       		rows[i].dealPrc = $(edPrc.target).numberbox('getValue');
	 				if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	 					var edAttorn = $('#dealGrid').datagrid('getEditor', {index:i,field:'attornType'});
		 				if(edAttorn != null){
			       			rows[i].attornType = $(edAttorn.target).combobox('getValue');
			       		 }
		 				var edTraderName = $('#dealGrid').datagrid('getEditor', {index:i,field:'traderName'});
		 				if(edTraderName != null){
			       			rows[i].traderName = $(edTraderName.target).textbox('getValue');
			       		 }
		 				var row = rows[i];
	 				}else{
	 					var edGeneratorId = $('#dealGrid').datagrid('getEditor', {index:i,field:'generatorId'});
		 				if(edGeneratorId != null){
			       			rows[i].generatorId = $(edGeneratorId.target).combobox('getValue');
			       		 }
		 				var edProducerId = $('#dealGrid').datagrid('getEditor', {index:i,field:'producerId'});
		 				if(edProducerId != null){
			       			rows[i].producerId = $(edProducerId.target).combobox('getValue');
			       		 }
	 					var row = rows[i];
		         		if(row != null && (row.dealPrc == null || row.dealPrc == 0 || row.dealPq == 0 || row.dealPq == null || row.producerId == null)){
		         			return 'pqPrcZero';	
		                 }
	 				 }
	 				 
	 			 }
	 			 rows = $("#dealGrid").datagrid("getRows");
			 }
       	 var dealPqTotal = 0;
       	 for(var i = 0; i < rows.length; i++){
       		dealPqTotal += parseFloat((rows[i].dealPq == null || rows[i].dealPq=="") ? 0 : rows[i].dealPq);
       	 }
       	 me.countParams.newDealPq = dealPqTotal.toFixed(3);
        }
    }
})
</script>
</body>
</html>
