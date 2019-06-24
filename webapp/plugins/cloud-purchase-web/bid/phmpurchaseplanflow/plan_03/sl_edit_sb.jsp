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
		<mk-form-panel height="20%;" num="3">
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
			<mk-panel title="成交信息" line="true" height="100%">
		        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="dealInput">成交信息录入</su-button>
		        </div>
		        <div class="row" id="dealGrid" height="100%"></div>
		    </mk-panel>
		</div>
	   <div id = "subGrid">
		    <mk-panel title="年度电量分月计划（兆瓦时）" line="true">
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
        oldDatas:[],
        params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null,reportPq:null,newDealPq:null},
		attornTypeFlag:false,
		monthData:[],
		initFlag:false,
		pageNumber:null,//用于翻页提示的，存当前页码
		initRowsData:[],//比对翻页时数据是否更改，存当前页数据
		initPageFlag: false,//初始页标识，翻页提示用
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
				url: basePath + 'phmDealInfoController/getSLListSb/',
	            method: 'post',
	            queryParams:{id:me.id,ym:me.params.phmPurchasePlanMonth.ym},
	            width:'100%',    
	            striped : true,
	            rownumbers: true,
	            pagination : true,
	            singleSelect:false,
	            checkOnSelect:true,
	            fitColumns:true,
	            pageSize: 10,
			    pageList: [10, 20, 50, 100, 150, 200],
	            nowrap:false,
	            loadMsg:"请稍后",
	            scrollbarSize:0,
	            columns:[[
							{field:'check',checkbox:true},
							{field:'consName',title:'用户名称',width:'10%',align:'center'},
							{field:'elecTypeName',title:'用电类别',width:'10%',align:'center'}, 
							{field:'voltCodeName',title:'电压等级',width:'10%',align:'center'}, 
   		 					{field:'traderName',title:'交易对象',width:'15%',align:'center'},
                            {field:'geneName',title:'机组',width:'8%',align:'center'},    
	 					 	{field:'attornTypeName',title:'合同转让方向',width:'8%',align:'center'},
    	                    {field:'dealPrc',title:'成交电价<br>（元/兆瓦时）',width:'12%',align:'center'},
							{field:'dealPq',title:'成交电量<br>（兆瓦时）',width:'15%',align:'center',editor:{type:'numberbox',options:{required:true,precision:3}}}, 
	                        {field:'deliveryPrc',title:'结算电价<br>（兆瓦时）',width:'15%',align:'center',editor:{type:'numberbox',options:{required:true,precision:2}}}
	                    ]],
	            rowStyler:function(idx,row){
	                return "height:35px;font-size:12px;";
	            },
	            loadFilter: function(data){
	            	if(data.rows != null && data.rows.length > 0 && me.oldDatas != null && me.oldDatas.length > 0){
	            		for(var i in data.rows){
	            			for(var j in me.oldDatas){
	            				if(data.rows[i].id == me.oldDatas[j].id){
	            					data.rows[i].dealPq = me.oldDatas[j].dealPq;
	            					break;
	            				}
	            			}
	            		}
	            	}
            		return data;
	            },
	            onLoadSuccess: function(data){
	            	var rows = data.rows;
	            	var totalDealPq = 0;
	            	if(rows != null && rows.length > 0){
	            		me.mergeCells(data.rows);
	            		for(var i=0 ; i<rows.length; i++){
	            			totalDealPq += parseFloat(rows[i].dealPq == null ? 0 : rows[i].dealPq);
	            			if(rows[i].dealPrc != null && rows[i].dealPrc !='' ){
			            		$("#dealGrid").datagrid('beginEdit',i);
	            			}
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
						if(edPq != null && edPq != ''){
			    			rows[i].dealPq = $(edPq.target).numberbox('getValue');
						}
		    			var edPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'deliveryPrc'});
		    			if(edPrc != null && edPrc !=""){
			    			rows[i].deliveryPrc = $(edPrc.target).numberbox('getValue');
		    			}
					}
					//获取最新数据
					rows = $.parseJSON($.toJSON($("#dealGrid").datagrid("getRows")));
					 var flag = false
					 var param = {"dealPq":"dealPq","deliveryPrc":"deliveryPrc"};
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
	            onClickRow:function(index,row){
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
	    		//隐藏机组列
	    		$("#dealGrid").datagrid('hideColumn','geneName');
	    	}else{
	    		$("#dealGrid").datagrid('hideColumn','attornTypeName');
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
					url: basePath + 'phmDealInfoController/getSlSbYearDealInfo/'+me.id+flag,
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
                        		if(me.oldDatas != null && me.oldDatas.length > 0){
                        			for(var j in me.oldDatas){
                        				if(data.rows[i].id == me.oldDatas[j].id){
                        					data.rows[i].totalDealPq = me.oldDatas[j].dealPq;
        	            					break;
                        				}
                        			}
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
		            		for(var i=0 ; i<datas.length ; i++){
		            			var m = i+1;
		            			$("#yearGrid").datagrid('beginEdit',i);
		            			if(datas[i].traderName == null || datas[i].traderName == ''){
			            			$("#yearGrid").datagrid("getPanel").find(".datagrid-row [field='traderName']").eq(i).children().text("双边协商"+m);
			            			 datas[i].traderName = "双边协商"+m;
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
        save:function(){
    		var me = this;
    		$.messager.progress();
    		me.endEdit();
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
                    data : $.toJSON({ym:me.params.phmPurchasePlanMonth.ym,id:me.id,slList:rows,monthList:list,flag:true}),
                    contentType : 'application/json;charset=UTF-8',
                    success : function(data) {
                        if(data.flag == true){
	                        $.messager.progress('close');  //关闭进度条
	                        if(!flag){
	                        	MainFrameUtil.alert({title:"提示",body:"保存成功！"});
	                        	//避免reload时数据比对
	                        	me.initPageFlag = false;
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
		    				data:$.toJSON({id:me.id , planStatus:"02"}),
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
				field : "check",
				rowspan : rowspan,
				colspan : 0
			});
    		$("#dealGrid").datagrid("mergeCells",{
				index : index,
				field : "consName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#dealGrid").datagrid("mergeCells",{
				index : index,
				field : "elecTypeName",
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
    	
    	//录入成交信息
    	dealInput:function(){
    		var me = this;
    		var rows = $("#dealGrid").datagrid("getChecked");
    		me.oldDatas = $.parseJSON($.toJSON( $("#dealGrid").datagrid("getRows")));
    		if(rows.length == 0){
    			 MainFrameUtil.alert({title:"提示",body:"请选择录入用户！"});
                 return;
    		}
    		MainFrameUtil.openDialog({
	  			id:"dealInput",
	  			params:{planId:me.id,params:rows,flag:me.attornTypeFlag,ym:me.params.phmPurchasePlanMonth.ym},
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/dealsl-add',
				iframe:true,
				title:"成交信息录入",
				modal:true,
				width:"80%",
				height:620,
				onClose:function(){
					me.initPageFlag = false;
					me.initDealGrid();
                }
			});
    	},
    	//可编辑表格数值变更触发方法
    	editorChange:function(){
    		var me = this;
    		$("#dealGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#dealGrid").datagrid("getEditor",{index:rowIndex,field:"dealPq"});
	    			if(editor != null){
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
		    			if(me.params.phmPurchasePlanMonth.tradePeriod == "01" && me.initFlag){
			    			for(var i=0 ; i < $("#yearGrid").datagrid('getRows').length; i++){
			    				if( $("#yearGrid").datagrid('getRows')[i].id == rowId){
			    					flag = false;
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
		    						break;
		    					}
		    				}
		    			}
	    			}
	    		})
	    	});
    	},
    	calculate:function(flag){
    		var me = this;
    		me.endEdit();
    		var rows = $("#dealGrid").datagrid("getRows");
    		for(var i=0 ; i<rows.length ; i++){
    			var edPq = $('#dealGrid').datagrid('getEditor', {index:i,field:'dealPq'});
    			if(edPq != null){
	    			rows[i].dealPq = $(edPq.target).numberbox('getValue');
    			}
    			var edDeliveryPrc = $('#dealGrid').datagrid('getEditor', {index:i,field:'deliveryPrc'});
    			if(edDeliveryPrc != null){
	    			rows[i].deliveryPrc = $(edDeliveryPrc.target).numberbox('getValue');
    			}
				if(flag){
	    			if(rows[i].dealPq == null || rows[i].dealPq === ''){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的成交电量未填！"});
	    				return false;
	    			}
	    			if(rows[i].deliveryPrc == null || rows[i].deliveryPrc === ''){
	    				MainFrameUtil.alert({title:"警告",body:rows[i].consName+"的结算电价未填！"});
	    				return false;
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
    	//确认保存翻页
    	loadData: function(page,rows,flag){
			var me = this;
			//翻页 数据不比对
			me.initPageFlag=false;
			if(flag){
				me.calculate(false);
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