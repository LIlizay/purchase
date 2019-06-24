<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理竞价交易成交编辑</title>
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
		<mk-form-panel  height="20%;"  num="3" >
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
		        <div id="yearGrid"  style="min-height:200px"></div>
		        <div id="monthGrid"  style="min-height:200px"></div>
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
		attornTypeFlag:false,
		monthData:[],
		initFlag:false,
		pageNumber:null,//用于翻页提示的，存当前页码
		initPageFlag: false,
		initRowsData:[],//比对翻页时数据是否更改，存当前页数据
    },
    ready:function(){
    	var me = this;
    	MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
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
        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc",handler:me.save},
			               {text:"提交",type:"primary",handler:me.submit},
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
			$("#dealGrid").datagrid({
	    		url: basePath + 'phmDealInfoController/getSLList/',
	            method: 'post',
	            queryParams:{id:me.id},
	            width:'100%',    
	            striped : true,
	            pagination : true,
	            checkOnSelect:true,
	            singleSelect : true,
	            fitColumns:true,
	            pageSize: 10,
			    pageList: [10, 20, 50, 100, 150, 200],
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
	                        {field:'producerId',title:'交易对象',width:'15%',align:'center',
                               	editor:{type:'combobox',options:{
  		 							 url:basePath+"cloud-purchase-web/phmPpaController/getPhcElecInfoList",
  		 					         method:'get',
  		 					         valueField:'id',
  		 					         textField:'elecName',
  		 					         editable:false, 
  		 					         panelHeight: '200px',
  		 					      	 onSelect:function(data){
		 					        	var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
		 					        	var rowId = $("#dealGrid").datagrid('getRows')[rowIndex].id;
		 					        	//年度交易同步月份拆分列表
		 					        	var flag = true;
		 								if(me.params.phmPurchasePlanMonth.tradePeriod == "01"  && me.initFlag){
		 									for(var i=0 ; i < $("#yearGrid").datagrid('getRows').length; i++){
		 										if($("#yearGrid").datagrid('getRows')[i].id == rowId){
		 											flag = false;
		 											traderName = data.elecName;
				 									$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text(traderName);
				 									$("#yearGrid").datagrid('getRows')[i].traderName = traderName;
				 									break;
		 										}
		 									}
		 								}
		 								if(flag && me.params.phmPurchasePlanMonth.tradePeriod == "01"){
		 									for(var i=0 ; i < me.monthData.length ; i++){
		 				    					if(me.monthData[i].id == rowId){
		 				    						me.monthData[i].traderName = data.elecName;
		 				    						break;
		 				    					}
		 				    				}
		 								}
                                  }
   		 					 }}},
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
		            		$("#dealGrid").datagrid('beginEdit',i);
		            	}
		            	me.countParams.newDealPq = totalDealPq.toFixed(3);
		            	me.editorChange();
		            	//获取列表页码
		            	me.initPageFlag = true;
		            	me.pageNumber = $("#dealGrid" ).datagrid("getPager").data("pagination").options.pageNumber;
		    			me.initRowsData = $.parseJSON($.toJSON(data.rows));
	            	}
	            	me.monthDetail();
	            },
	            onBeforeLoad : function(data){
	            	//初始化时
	            	if(me.initPageFlag != true){
	            		return;
	            	}
		   			var rows = $("#dealGrid").datagrid("getRows");
		   			//对编辑表格刷新值
					for(var i in rows){
		    			var edPq = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPq'});
		    			if( edPq != null && edPq != ""){
			    			rows[i].dealPq = $(edPq.target).numberbox('getValue');
		    			}
		    			var edPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPrc'});
						if( edPrc != null && edPrc != ""){
			    			rows[i].dealPrc = $(edPrc.target).numberbox('getValue');
		    			}
		    			var producerId  = $('#dealGrid').datagrid('getEditor', {index:i,field:'producerId'});
						if( producerId != null && producerId != ""){
			    			rows[i].producerId  = $(producerId .target).combobox('getValue');
		    			}
		    			var traderName  = $('#dealGrid').datagrid('getEditor', {index:i,field:'traderName'});
						if( traderName != null && traderName != ""){
			    			rows[i].traderName  = $(traderName .target).textbox('getValue');
		    			}
		    			var deliveryPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'deliveryPrc'});
						if( deliveryPrc != null && deliveryPrc != ""){
			    			rows[i].deliveryPrc = $(deliveryPrc.target).numberbox('getValue');
		    			}
					}
		   			//获取最新数据
					rows = $.parseJSON($.toJSON($("#dealGrid").datagrid("getRows")));
					 var flag = false;
    				 var param = {"dealPq":"dealPq","dealPrc":"dealPrc","producerId":"producerId","deliveryPrc":"deliveryPrc","traderName":"traderName"};
    				 //比对数据
	    			 for(var i=0 ;i < rows.length;i++){
	    				if(flag) break;
	    				 for(var j in param){
	    					 if( !(me.initRowsData[i][j]==null && rows[i][j]==null) &&  !(me.initRowsData[i][j]==null && rows[i][j]=='')
	    							 && !(me.initRowsData[i][j]=='' && rows[i][j]==null) && (me.initRowsData[i][j] != rows[i][j])){
	    						 flag=true;
	    						 break;
	    					 }
	    				 }
	    			 }
			    	if(flag){
			    		MainFrameUtil.confirm({
			    			title:"提示",
					        body:"页面变更会导致当前页面修改数据丢失，请确认是否保存",
					        buttons:[{text:'保存',type:"ok",style:"primary"},{text:'取消',type:"cancel",style:"default"}],
			    	        onClose:function(type){
			    	            if(type=="ok"){//确定
			    	            	me.loadData(data.page,data.rows,true);
			    	            }else if(type=="cancel"){//取消
			    	            	me.loadData(me.pageNumber,data.rows,false);
			    	            }
			    	        }
			    	    });
			    		return false;
			    	}else{
			    		return true;
			    	}
	    			
	 		    },
	            onClickRow: function(index,row){
	            	if(me.monthDetail != null && me.monthDetail.length > 0){
	            		if(me.initFlag){
		            		if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){
		            			for(var i=0 ; i<$("#yearGrid").datagrid('getRows').length; i++){
			            			$("#yearGrid").datagrid('endEdit',i);
		            			}
		            			for(var j=0 ; j<$("#yearGrid").datagrid('getRows').length; j++){
			            			var totalPq = $("#yearGrid").datagrid('getRows')[j].totalDealPq;
			            			var total = 0;
			            			for(var m=1 ; m<13 ; m++){
			            				var title = "mon"+m;
			            				var value =  $("#yearGrid").datagrid('getRows')[j][title];
			            				total += parseFloat((value == null || value == '')? 0 : value);
				            			if(total > totalPq){
				                			MainFrameUtil.alert({title:"提示",body: $("#yearGrid").datagrid('getRows')[j].consName+"和"+$("#yearGrid").datagrid('getRows')[j].traderName+"交易的分月电量之和已超过总交易电量，请重新填写分月计划！"});
				                			for(var k=0 ; k<$("#yearGrid").datagrid('getRows').length; k++){
						            			$("#yearGrid").datagrid('beginEdit',k);
					            			}
				                			return;
				                		}
			            			}
		            			}
			            	}else{
			            		for(var i=0 ; i<$("#monthGrid").datagrid('getRows').length; i++){
			            			$("#monthGrid").datagrid('endEdit',i);
		            			}
			            		for(var j=0 ; j<$("#monthGrid").datagrid('getRows').length; j++){
			            			var totalPq = $("#monthGrid").datagrid('getRows')[j].totalDealPq;
			            			var total = 0;
			            			for(var m=1 ; m<13 ; m++){
			            				var title = "mon"+m;
			            				var value =  $("#monthGrid").datagrid('getRows')[j][title];
			            				total += parseFloat((value == null || value == '')? 0 : value);
				            			if(total > totalPq){
				                			MainFrameUtil.alert({title:"提示",body: $("#monthGrid").datagrid('getRows')[j].consName+"和"+$("#monthGrid").datagrid('getRows')[j].traderName+"交易的分月电量之和已超过总交易电量，请重新填写分月计划！"});
				                			for(var k=0 ; k<$("#monthGrid").datagrid('getRows').length; k++){
						            			$("#monthGrid").datagrid('beginEdit',k);
					            			}
				                			return;
				                		}
			            			}
		            			}
			            	}
	            		}
						me.monthDetailGrid(row.consId);	//年度电量分月计划列表加载
	            	}
	            }
	        });
	    	if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
	    		me.attornTypeFlag = true;
	    		$("#dealGrid").datagrid('hideColumn','producerId');
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
		            			$("#yearGrid").datagrid('beginEdit',i);
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
		            },
		            onLoadSuccess: function(data){
		            	if(datas != null && datas.length > 0){
			            	for(var i = 0 ; i < datas.length ; i++){
			            		$("#monthGrid").datagrid('beginEdit',i);
			            	}
		            	}
		            }
		        });
			}
		},
		columnsEditor:function(rows){
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
    		var flag = me.calculate(true);
    		if(flag){
    			me.saveData(true);
    		}else{
    			$.messager.progress('close');  //关闭进度条
	    		return;
    		}
        },
        submitData:function(){
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
		    						$.messager.progress('close');  //关闭进度条
		    						MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
		    							MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
		    							MainFrameUtil.closeDialog("plan_edit");
		    					}}); 
		    					}else{
		    						$.messager.progress('close');  //关闭进度条
		    						MainFrameUtil.alert({title:"错误",body:data.msg}); 
		    					}
		    				}
		    			})
		            }
		        }
    		})

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
		    				data:$.toJSON({id:me.id , planStatus:"03"}),
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
    	calculate: function(flag){
    		var me = this;
    		me.endEdit();
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
	    			if(rows[i].dealPq == null || rows[i].dealPq === ''){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的成交电量未填！"});
	    				return false;
	    			}
	    			if(rows[i].dealPrc == null || rows[i].dealPrc === ''){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的成交电价未填！"});
	    				return false;
	    			}
	    			if(rows[i].deliveryPrc == null || rows[i].deliveryPrc === ''){
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
    	endEdit:function(){
    		var me = this;
    		if(me.monthData != null && me.monthData.length > 0 && me.initFlag){
    			if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//年交易
    				for(var i = 0 ; i < $("#yearGrid").datagrid('getRows').length ; i++){
    					$("#yearGrid").datagrid('endEdit',i);
    					$("#yearGrid").datagrid('beginEdit',i);
    				}
    			}else{
    				for(var i = 0 ; i < $("#monthGrid").datagrid('getRows').length ; i++){
	    				$("#monthGrid").datagrid('endEdit',i);
						$("#monthGrid").datagrid('beginEdit',i);
    				}
    			}
    		}
    	},
    	save:function(){
    		var me = this;
    		$.messager.progress();
    		var flag = me.calculate(false);
    		if(flag){
    			me.saveData(false);
    		}else{
    			$.messager.progress('close');  //关闭进度条
	    		return;
    		}
    	},
    	saveData:function(flag){
    		var me = this;
    		var rows = $('#dealGrid').datagrid('getRows');
			var list = new Array();
			if(me.monthData != null && me.monthData.length > 0){
	   			for(var i=0; i < me.monthData.length; i++){
	   				var monthFlag = true; //标记年分项是否有数据填写
	   				var total = 0;
	       			var object = me.monthData[i];
	    			for(var m=1 ; m<13 ; m++){
	    				var title = "mon"+m;
	    				var value =  me.monthData[i][title];
	    				if(value != null && value !== ''){
	    					monthFlag = false;
	    				}
	    				total += parseFloat((value == null || value == '')? 0 : value);
		    			if(total > me.monthData[i].totalDealPq){
		        			MainFrameUtil.alert({title:"提示",body: me.monthData[i].consName+"和"+me.monthData[i].traderName+"交易的分月电量之和已超过总交易电量，请重新填写分月计划！"});
		        			$.messager.progress('close');  //关闭进度条
		        			return;
		        		}
	    			}
	    			if(flag && monthFlag){
	    				MainFrameUtil.alert({title:"提示",body:"请完善年度电量分月计划后再提交！"});
           				$.messager.progress('close');  //关闭进度条
           				return;
	    			}
	           		if(flag && total != me.monthData[i].totalDealPq){
	           			MainFrameUtil.alert({title:"提示",body:  me.monthData[i].consName+"和"+me.monthData[i].traderName+"交易的分月电量之和与总交易电量不等，请重新填写分月计划后再提交！"});
	           			$.messager.progress('close');  //关闭进度条
	       				return;
	           		}
	      			list.push(object);
	       		}
			}
    		if(me.params.phmPurchasePlanMonth.tradePeriod == "01"){//保存年交易，同步保存
    			$.ajax({
    				url : basePath+"cloud-purchase-web/phmDealInfoController/SLYearSave",
                    type : "post",
                    data : $.toJSON({ym:me.params.phmPurchasePlanMonth.ym,id:me.id,slList:rows,monthList:list}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag == true){
	                        $.messager.progress('close');  //关闭进度条
	                        if(!flag){
	                        	MainFrameUtil.alert({title:"提示",body:"保存成功！"});
	                        	//避免reload时数据比对
	                        	me.initPageFlag = false;
	                        	//列表刷新
	                			$('#dealGrid').datagrid("reload");
// 	                        	me.initDealGrid();
	                        }else{
	                        	me.submitData();
	                        }
                        }else{
                        	$.messager.progress('close');  //关闭进度条
                            MainFrameUtil.alert({title:"失败提示",body:data.msg});
                        }
                    },
                    error : function() {
                        $.messager.progress('close');  //关闭进度条
                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                    }
                });
			}else{//月交易，异步保存
				$.ajax({
    				url : basePath+"cloud-purchase-web/phmDealInfoController/SLSave",
                    type : "post",
                    data : $.toJSON({ym:me.params.phmPurchasePlanMonth.ym,id:me.id,slList:rows}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag == true){
                        	me.saveMonthDetail(flag,list);
                        }else{
                        	$.messager.progress('close');  //关闭进度条
                            MainFrameUtil.alert({title:"失败提示",body:data.msg});
                        }
                    },
                    error : function() {
                        $.messager.progress('close');  //关闭进度条
                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                    }
                });
			}
    	},
    	//保存拆月信息
    	saveMonthDetail:function(flag,list){
    		var me = this;
    		var ym = me.params.phmPurchasePlanMonth.ym;
    		if(list != null && list.length > 0){
    			$.ajax({
                    url : basePath+"cloud-purchase-web/phmDealInfoController/saveSubMonthList",
                    type : "post",
                    data:$.toJSON({monthList:list,ym:me.params.phmPurchasePlanMonth.ym}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag){
                        	if(!flag){
                        		$.messager.progress('close');  //关闭进度条
	                            MainFrameUtil.alert({title:"成功提示",body:data.msg});
	                            //me.initDealGrid();
                        	}else{
                        		me.submitData();
                        	}
                        }else{
                            $.messager.progress('close');  //关闭进度条
                            MainFrameUtil.alert({title:"失败提示",body:data.msg});
                        }
                    },
                    error : function() {
                    	$.messager.progress('close');  //关闭进度条
                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                    }
                });
   			}else{
   				if(flag){
   					me.submitData();
   				}else{
   					$.messager.progress('close');  //关闭进度条
   					MainFrameUtil.alert({title:"成功提示",body:"信息保存成功!"});
   				}
   			}
    	},
    	//可编辑表格数值变更触发方法
    	editorChange:function(){
    		var me = this;
    		$("#dealGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"dealPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			var rowId = $("#dealGrid").datagrid('getRows')[rowIndex].id;
	    			if(pq != null && pq != ''){
	    				me.countParams.newDealPq = me.countParams.newDealPq == null? 0 : me.countParams.newDealPq;
	    				var oldPq = $("#dealGrid").datagrid("getRows")[rowIndex].dealPq;
	    				var totalPq = parseFloat(me.countParams.newDealPq) - parseFloat(oldPq == null?0:oldPq) + parseFloat(pq == null?0:pq);
	    				me.countParams.newDealPq = totalPq.toFixed(3);
	    				$("#dealGrid").datagrid("getRows")[rowIndex].dealPq = pq;
					}
	    			var flag = true;
	    			if(me.params.phmPurchasePlanMonth.tradePeriod == "01" && me.initFlag){//年交易列表同步
	    				for(var i=0 ; i < $("#yearGrid").datagrid('getRows').length; i++){
		    				if( $("#yearGrid").datagrid('getRows')[i].id == rowId){
		    					flag = false;
		    					if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
			    					var traderNameEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"traderName"});
			    					traderName = traderNameEditor.actions.getValue(traderNameEditor.target); 
			    					if(traderName != null && traderName !=''){
				    					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text(traderName);
				    					$("#yearGrid").datagrid('getRows')[i].traderName = traderName;
			    					}
		    					}
		    					$("#yearGrid").datagrid('getRows')[i].totalDealPq = pq;
		    					$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='totalDealPq']").eq(i).children().text(pq);
		    					break;
		    				}
		    			}
	    			}
	    			if(flag && me.params.phmPurchasePlanMonth.tradePeriod == "01"){
	    				for(var i=0 ; i < me.monthData.length ; i++){
	    					if(me.monthData[i].id == rowId){
	    						me.monthData[i].totalDealPq = pq;
	    						if(me.params.phmPurchasePlanMonth.tradeVariety == "03"){
			    					var traderNameEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"traderName"});
			    					traderName = traderNameEditor.actions.getValue(traderNameEditor.target); 
			    					if(traderName != null && traderName !=''){
				    					me.monthData[i].traderName = traderName;
			    					}
		    					}
	    						break;
	    					}
	    				}
	    			}
	    			//山西辽宁用户结算电价计算
	    			var prcEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"dealPrc"});
	    			var prc = prcEditor.actions.getValue(prcEditor.target);
	    			if(prc != null && prc != "" && prc != $("#dealGrid").datagrid("getRows")[rowIndex].dealPrc){
	    				$("#dealGrid").datagrid("getRows")[rowIndex].dealPrc = prc;
	    				var consId = $("#dealGrid").datagrid("getRows")[rowIndex].consId;
	    				$.ajax({
	        				url : basePath+"cloud-purchase-web/phmDealInfoController/calDeliveryPrc",
	                        type : "post",
	                        data : $.toJSON({ym:me.params.phmPurchasePlanMonth.ym,consId:consId,dealPrc:prc,tradeMode:me.params.phmPurchasePlanMonth.tradeMode}),
	                        contentType : 'application/json;charset=UTF-8',
	                        success : function(data) {
	                            if(data.flag == true){
	                            	$("#dealGrid").datagrid("getRows")[rowIndex].deliveryPrc = data.data;
	                            	var dealPrcEditor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"deliveryPrc"});
	                            	dealPrcEditor.actions.setValue(dealPrcEditor.target,data.data);
	                            }else{
	                                MainFrameUtil.alert({title:"失败提示",body:data.msg});
	                            }
	                        },
	                        error : function() {
	                            $.messager.progress('close');  //关闭进度条
	                            MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
	                        }
	                    });
	    			}
	    		})
	    	});
    	},
    	//确认保存翻页
    	loadData: function(page,rows,flag){
			var me = this;
			//翻页 数据不比对
			me.initPageFlag=false;
			if(flag){
				me.saveData(false);
			}
			//列表刷新
			$('#dealGrid').datagrid("reload");
			
	    },
    }
})
</script>
</body>
</html>
