<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-成交信息录入 （双边协商方式）详情</title>
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
		<div style="height:65%;">
			<mk-panel title="成交信息" line="true" height="100%">
		        <div id="dealGrid" class="row" style="height:100%"></div>
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
    	if(MainFrameUtil.getParams("plan_edit")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
		}else if(MainFrameUtil.getParams("plan_detail")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_detail");
		}
    	me.id = location.search.substr(8);
    	$("#subGrid").hide();
    	$("#yearGrid").hide();
    	$("#monthGrid").hide();
    	ComponentUtil.getFormFields("purchase.bid.transactionlreport",this); 
    	me.initData();
    },
    methods:{
    	//按钮组
        getButtons: function(){
        	var me = this;
        	var type="";
        	if(MainFrameUtil.getParams("plan_edit")){
        		type="plan_edit";
        	}else if(MainFrameUtil.getParams("plan_detail")){
        		type="plan_detail";
        	}
        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog(type)}}];
            return buttons;
        },
    	initData: function(){
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
	                        {field:'elecName',title:'交易对象',width:'22%',align:'center'},
	                        {field:'traderName',title:'交易对象',align:'center',width:'22%'},
    						{field:'attornTypeName',title:'合同转让方向',width:'25%',align:'center'},
                            {field:'geneName',title:'机组',width:'25%',align:'center'},  
							{field:'dealPq',title:'成交电量（兆瓦时）',width:'25%',align:'center'}, 
	                        {field:'dealPrc',title:'成交电价（元/兆瓦时）',width:'25%',align:'center'},
	                    ]],
	            rowStyler:function(idx,row){
	                return "height:35px;font-size:12px;";
	            },
	            onLoadSuccess: function(data){
	            	var rows = data.rows;
	            	var totalDealPq = 0;
	            	for(var i=0 ; i<rows.length; i++){
	            		totalDealPq += parseFloat(rows[i].dealPq == null ? 0 : rows[i].dealPq);
	            	}
	            	me.countParams.newDealPq = totalDealPq;
	            	me.calNewDealPq();
	            }
	        });
			me.monthDetailGrid();	//年度电量分月计划列表加载
	    	if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){//合同转让
	    		$("#dealGrid").datagrid('hideColumn','elecName');//下拉交易对象
	    		$("#dealGrid").datagrid('hideColumn','geneName');//机组
	    	}else{
	    		$("#dealGrid").datagrid('hideColumn','attornTypeName');//合同转让方向
	    		$("#dealGrid").datagrid('hideColumn','traderName');
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
    	submit:function(){
    		var me = this;
    		me.saveData(true);
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
	    				me.countParams.newDealPq = parseFloat(me.countParams.newDealPq) - parseFloat(oldPq == null?0:oldPq) + parseFloat(pq);
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
    }
})
</script>
</body>
</html>
