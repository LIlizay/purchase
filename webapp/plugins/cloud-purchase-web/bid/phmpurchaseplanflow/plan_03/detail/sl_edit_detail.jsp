<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理竞价交易成交详情</title>
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
				<!-- 申报电量  -->
				<mk-form-col :label="formFields.bidReportPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.reportPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 成交电量  -->
				<mk-form-col :label="formFields.newDealPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.newDealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<div style="height:80%;">
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
        monthData:[],
        params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null,reportPq:null,newDealPq:null},
    },
    ready:function(){
    	var me = this;
    	if(MainFrameUtil.getParams("plan_edit")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
		}else if(MainFrameUtil.getParams("plan_detail")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_detail");
		}
    	me.id = location.search.substr(8);
    	$("#yearGrid").hide();
    	$("#monthGrid").hide();
    	ComponentUtil.getFormFields("purchase.bid.transactionlreport",this); 
    	me.initData();
    },
    methods:{
    	//按钮组
        getButtons:function(){
        	var me = this;
        	var type="";
        	if(MainFrameUtil.getParams("plan_edit")){
        		type="plan_edit";
        	}else if(MainFrameUtil.getParams("plan_detail")){
        		type="plan_detail";
        	}
        	var buttons = [{text:"关闭",handler:function()
        		{MainFrameUtil.closeDialog(type)}
        		}];
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
			$("#dealGrid").datagrid({
	    		url: basePath + 'phmDealInfoController/getSLList/',
	            method: 'post',
	            queryParams:{id:me.id},
	            width:'100%',    
	            striped : true,
	            pagination : true,
	            singleSelect:true,
	            checkOnSelect:true,
	            fitColumns:true,
	            nowrap:false,
	            loadMsg:"请稍后",
	            scrollbarSize:0,
	            columns:[[
							{field:'check',checkbox:true},
							{field:'stage',title:'分段',width:'7%',align:'center'},
							{field:'consName',title:'用户名称',width:'10%',align:'center'},
							{field:'elecTypeName',title:'用电类别',width:'10%',align:'center'}, 
							{field:'voltCodeName',title:'电压等级',width:'10%',align:'center'}, 
	                        {field:'reportPq',title:'申报电量<br>（兆瓦时）',width:'15%',align:'center'},
							{field:'reportPrc',title:'申报电价<br>（元/兆瓦时）',width:'10%',align:'center'}, 
							{field:'attornTypeName',title:'合同转让方向',width:'8%',align:'center'},
							{field:'dealPq',title:'成交电量<br>（兆瓦时）',width:'15%',align:'center',editor:{type:'numberbox',options:{required:true,precision:3}}}, 
	                        {field:'dealPrc',title:'成交电价<br>（元/兆瓦时）',width:'12%',align:'center',editor:{type:'numberbox',options:{required:true,precision:2}}},
	                        {field:'elecName',title:'交易对象',width:'28%',align:'center'},
	                        {field:'traderName',title:'交易对象',width:'15%',align:'center',editor:{type:'textbox',options:{required:true,maxlength:32}}},
							{field:'deliveryPrc',title:'结算电价<br>（元/兆瓦时）',width:'8%',align:'center',editor:{type:'numberbox',options:{required:true,precision:2}}} 
	                    ]],
	            rowStyler:function(idx,row){
	                return "height:35px;font-size:12px;";
	            },
	            onLoadSuccess: function(data){
	            	var rows = data.rows;
	            	var totalDealPq = 0;
	            	if(rows != null && rows.length > 0){
	            		me.mergeCells(data.rows);
		            	for(var i=0 ; i<rows.length; i++){
		            		totalDealPq += parseFloat(rows[i].dealPq == null ? 0 : rows[i].dealPq);
		            	}
		            	me.countParams.newDealPq = totalDealPq;
	            	}
	            	me.monthDetail();
	            },
	            onClickRow: function(index,row){
	            	if(me.monthDetail != null && me.monthDetail.length > 0){
						me.monthDetailGrid(row.consId);	//年度电量分月计划列表加载
	            	}
	            }
	        });
	    	if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	    		$("#dealGrid").datagrid('hideColumn','elecName');
	    	}else{
	    		$("#dealGrid").datagrid('hideColumn','attornTypeName');
	    		$("#dealGrid").datagrid('hideColumn','traderName');
	    	}
		},
		monthDetail:function(){
			var me = this;
			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){
				$("#yearGrid").show();
				var flag = "00";
				if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
					flag = "01";
				}
				$.ajax({
					url: basePath + 'phmDealInfoController/getYearDealInfo/'+me.id+flag,
		            method: 'get',
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag){
                        	var count = 1;
                        	for(var i in data.rows){
                        		if(data.rows[i].traderName == null){
                        			data.rows[i].traderName = data.rows[i].tradeMode + count;
                        			count ++;
                        		}
                        	}
                        	me.monthData = data.rows;
                        }
                    },
                    error : function() {
                    	$.messager.progress('close');  //关闭进度条
                        MainFrameUtil.alert({title:"网络失败提示",body:"获取年度电量分月计划失败，请刷新页面重试！"});
                    }
                });
    		}else{
    			$("#monthGrid").show();
    			$.ajax({
    				url: basePath + 'phmDealInfoController/getMonthDealInfo/'+me.params.phmPurchasePlanMonth.ym,
		            method: 'get',
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag){
                        	if(data.rows != null && data.rows.length > 0){
                        		var consRows = $("#dealGrid").datagrid('getRows');
                        		if(consRows != null && consRows.length > 0){
                        			var consMap = {};
                        			for(var i=0 ; i<consRows.length ; i++){
                        				consMap[consRows[i].consId] = consRows[i];
                        			}
                        			for(var j=0 ; j<data.rows.length ; j++){
                        				if(consMap[data.rows[j].consId] != undefined){
                        					me.monthData.push(data.rows[j]);
                        				}
                        			}
                        		}
                        	}
                        	if(me.monthData == null || me.monthData.length == 0){
      		            		$("#subGrid").hide();
      		    				$("#monthGrid").hide();
                        	}
                        }
                    },
                    error : function() {
                    	$.messager.progress('close');  //关闭进度条
                        MainFrameUtil.alert({title:"网络失败提示",body:"获取年度电量分月计划失败，请刷新页面重试！"});
                    }
                });
    		}
		},
		monthDetailGrid:function(consId){
			var me = this;
			me.initFlag = true;
			var datas = [];
			for(var i=0 ; i<me.monthData.length ; i++){
				if(me.monthData[i].consId == consId){
					datas.push(me.monthData[i]);
				}
			}
			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易，取本次交易
				$("#yearGrid").datagrid({
		            width:'100%',    
		            striped : true,
		            pagination : false,
		            singleSelect:true,
		            checkOnSelect:true,
		            fitColumns:true,
		            nowrap:false,
		            loadMsg:"请稍后",
		            scrollbarSize:0,
		            frozenColumns:[[
								{field:'consName',title:'用户名称',width:'15%',align:'center'},
								{field:'traderName',title:'交易对象',width:'15%',align:'center'}, 
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
                    data:datas,
		            rowStyler:function(idx,row){
		                return "height:35px;font-size:12px;";
		            },
		            onLoadSuccess: function(data){
		            	if(datas != null && datas.length > 0){
			            	var name = null;
			            	if(me.params.phmPurchasePlanMonth.tradeMode == "01"){
			            		name = "集中竞价";
			            	}else{
			            		name = "挂牌";
			            	}
		            		for(var i=0 ; i<datas.length ; i++){
		            			var m = i+1;
		            			if(datas[i].traderName == null || datas[i].traderName == ''){
			            			$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text(name+m);
			            			datas[i].traderName = name+m;
			            		}
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
				$("#monthGrid").datagrid({
		            width:'100%',    
		            striped : true,
		            pagination : false,
		            singleSelect:true,
		            checkOnSelect:true,
		            fitColumns:true,
		            nowrap:false,
		            loadMsg:"请稍后",
		            scrollbarSize:0,
		            frozenColumns:[[
								{field:'consName',title:'用户名称',width:'15%',align:'center'},
								{field:'tradeVariety',title:'交易品种',width:'10%',align:'center'},
								{field:'tradeMode',title:'交易方式',width:'10%',align:'center'},
								{field:'traderName',title:'交易对象',width:'15%',align:'center'}, 
								{field:'attornType',title:'合同转让方向',width:'8%',align:'center'},
								{field:'totalDealPq',title:'总交易电量',width:'12%',align:'center'}
		            ]],
		            columns:[columns],
		            data:datas,
		            rowStyler:function(idx,row){
		                return "height:35px;font-size:12px;";
		            }
		        });
			}
		},
		columnsEditor: function(rows){
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
    	
    	//合并单元格
    	mergeCells:function(rows){
    		console.log(rows);
    		var model = new Object();
    		var numList = new Array();
    		var newId = rows[0].consId;
    		numList.push(1);
    		var num = 1;
    		for(var i=1; i <rows.length; i++){
    			if(rows[i].consId == newId){
    				num ++;
    			}else{
    				newId = rows[i].consId;
    				numList.push(num);
    			}
    		}
    		numList.push(num);
    		if(numList.length > 2){
    			var index = 0;
    			var rowspan = 0;
    			for(var j=0; j<numList.length; j++){
    				var rowsIndex = (numList[j+1] - numList[j])+1;
    				this.setMergeCells(index,rowsIndex);
    				index += rowsIndex;
    			}
    		}else{//所有的相同
    			this.setMergeCells(0,rows.length);
    		}
    	},
    	//设置合并单元格
    	setMergeCells:function(index,rowspan){
    		$("#dealGrid").datagrid("mergeCells",{
				index : index,
				field : "consName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#dealGrid").datagrid("mergeCells",{
				index : index,
				field : "elecTypeCodeName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#dealGrid").datagrid("mergeCells",{
				index : index,
				field : "voltCodeName",
				rowspan : rowspan,
				colspan : 0
			});
    	},
    	calculate:function(flag){
    		var me = this;
    		var rows = $("#dealGrid").datagrid("getRows");
    		for(var i=0 ; i<rows.length ; i++){
    			var edPq = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPq'});
    			rows[i].dealPq = $(edPq.target).numberbox('getValue');
    			var edPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPrc'});
    			rows[i].dealPrc = $(edPrc.target).numberbox('getValue');
    			var edDeliveryPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'deliveryPrc'});
    			rows[i].deliveryPrc = $(edDeliveryPrc.target).numberbox('getValue');
				if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	    			var edTraderName = $('#dealGrid').datagrid('getEditor', {index:i,field:'traderName'});
	    			rows[i].traderName = $(edTraderName.target).textbox('getValue');
         		}else{
         			var edProducerId = $('#dealGrid').datagrid('getEditor', {index:i,field:'producerId'});
        			rows[i].producerId = $(edProducerId.target).combobox('getValue');
         		}
				if(flag){
	    			if(rows[i].dealPq == null || rows[i].dealPq == 0){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的成交电量未填！"});
	    				return false;
	    			}
	    			if(rows[i].dealPrc == null || rows[i].dealPrc == null){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的成交电价未填！"});
	    				return false;
	    			}
	    			if(rows[i].deliveryPrc == null || rows[i].deliveryPrc == 0){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的结算电价未填！"});
	    				return false;
	    			}
	    			if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	    				if(rows[i].traderName == null || rows[i].traderName == ''){
		    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的交易对象未填！"});
		    				return false;
		    			}
	    			}
    			}
    		}
    		return true;
    	},
    	
        	//可编辑表格数值变更触发方法
    	editorChange:function(){
    		var me = this;
    		$("#dealGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"dealPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			if(pq != null && pq != ''){
	    				me.countParams.newDealPq = me.countParams.newDealPq == null? 0 : me.countParams.newDealPq;
	    				var oldPq = $("#dealGrid").datagrid("getRows")[rowIndex].dealPq;
	    				me.countParams.newDealPq = parseFloat(me.countParams.newDealPq) - parseFloat(oldPq == null?0:oldPq) + parseFloat(pq == null?0:pq);
	    				$("#dealGrid").datagrid("getRows")[rowIndex].dealPq = pq;
					}
	    			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易列表同步
    					if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	    					var traderNameEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"traderName"});
	    					traderName = traderNameEditor.actions.getValue(traderNameEditor.target); 
	    					if(traderName != null && traderName !=''){
		    					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(rowIndex).children().text(traderName);
	    					}
    					}
	    				$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='totalDealPq']").eq(rowIndex).children().text(pq);
    					$("#yearGrid").datagrid('getRows')[rowIndex].totalDealPq = pq;
	    			}
	    		})
	    	});
    	}
    }
})
</script>
</body>
</html>
