<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购售电交易-月度购电管理委托电量审修改（月交易）</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="examineVue" v-cloak>
	<div id="examin" style="height:100%">
		<su-form height="20%;" v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
			<mk-form-panel num="3">
				<mk-form-row>
					<!-- 交易品种-->
					<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params2.phmPurchasePlanMonth.tradeVariety" disabled="disabled"
		                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType"></su-select>
					</mk-form-col>
					<!-- 交易方式-->
					<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params2.phmPurchasePlanMonth.tradeMode"
		                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
					</mk-form-col>
					<!-- 交易周期  -->
					<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params2.phmPurchasePlanMonth.tradePeriod"
	                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
					</mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 交易时段  -->
					<mk-form-col :label="formFields.ym.label" required_icon="true">
						<su-datepicker :format ="ymFormat" :data.sync="params2.phmPurchasePlanMonth.ym" name="ym" disabled="disabled"></su-datepicker>
					</mk-form-col>
					<!-- 已成交电量 -->
					<mk-form-col :label="formFields.bidPqCount.label">
						<su-textbox :data.sync="countParams.dealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
					</mk-form-col>
					<!-- 委托电量 -->
					<mk-form-col :label="formFields.agrePqCount.label" colspan="1">
						<su-textbox :data.sync="countParams.agrePq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
					</mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</su-form>
		<mk-panel title="用户列表" line="true" height="60%">
		   	<div slot="buttons" class="pull-right" style="text-align:right" v-cloak">
		         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="mobile" @click="remind('saveMessage')">电量申报提醒</su-button>
		         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="undo" @click="remind('remindReportPq')">重报</su-button>
		         <su-button class="btn-operator" s-type="default" labeled="true" labeled="true" label-ico="bar-chart" @click="forecast">月度负荷预测</su-button>
		    </div>
		    <div class="row" id="examineGrid" height="100%"></div>
		</mk-panel>
		<mk-panel title="年度交易委托电量分月信息（兆瓦时）" line="true" height="40%">
		    <div class="row" id="yearPlanGrid" style="min-height:200px" height="100%"></div>
		</mk-panel>
    </div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var bigdataUrl = basePath.replace(/\/[a-zA-Z0-9]*\/$/,"/ibd/");
var cloudsellingProvince = "${cloudsellingProvince}";		//数据库配置的省码
var mineVue = new Vue({
	el:"#examineVue",
	data:{
		mArray:[],
		yArray:[],
		formFields:{},
		examineDetail:{},
		message:"",//电量申报提醒信息
		reportPqMsg:"",//重报提醒信息
		planId:null,
		params:{planId:"",ym:""},
		params2:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null},
		//负荷预测标识
		forecastResFlag : true,
		//负荷预测url参数
		consIds : '',
		result : [],
		
		//存初始时编辑格数据 用于翻页校验
		initRowsList : [],
		pageNumber: null,
		initFlag: true,
		
		//需要向后台保存的“年度交易委托电量分月信息”表格数据,格式为consId -> List<YearPlanProxyPqDetail>
		saveYearPlanPqMap:{},
		initYearGrid : false	//是否正在加载“年度交易委托电量分月信息”表的数据
	},
	ready:function(){
		var me = this;
		MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
		me.planId = location.search.substr(8);
		//查询字段名称加载
	    ComponentUtil.getFormFields("purchase.bid.phmagrepqexamine",me);
		me.initData();
		me.getForecastFlag();
		
	},
	methods:{
		getForecastFlag:function(){
			var me = this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/getForeCastFlag",
				type:"get",
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                	//判断用户有无月度负荷预测按钮权限
                	if(data == null || data == ''){
                		me.forecastResFlag = false;
                	}
                }
			});
		},
		initGrid:function(){
			var me = this;
			ComponentUtil.buildGrid("purchase.bid.phmagrepqexamine",$("#examineGrid"),{
				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/getList",
				method:"post",
		    	queryParams:me.params,
	     		pagination:true,
	    		rownumbers:true,
			    nowrap: false,
			    singleSelect: true,
			    checkOnSelect: false,
			    selectOnCheck: false,
	    		fitColumns: false,
	    		pageSize: 10,
	    		frozenColumns:[[{field:'check',checkbox:true},{field:'consName',title:'用户名称',sort:2,width:150}]],
			    pageList: [10, 20, 50, 100, 150, 200],
	    		//frozenColumns:[[{field:'consName',title:'用户名称',sort:1}]],
	    		columnsReplace:[
	                {field:'monthA',title:me.mArray[0]},
	                {field:'monthB',title:me.mArray[1]},
	                {field:'monthC',title:me.mArray[2]},
	                {field:'yearA',title:me.yArray[0]},
	                {field:'yearB',title:me.yArray[1]},
	                {field:"newReportPq",editor:{type:'numberbox',options:{min:0,precision:3}}}
	            ],
	            onBeforeLoad : function(data){
		   			if(me.initFlag==true){ //初始化表格时renturn
			    		return true;
			    	}
		   			var newRowsList = [];//新编辑格数据
		   			var rows = $("#examineGrid").datagrid("getRows");
		   			//获取可编辑格的数据
		   			for(var i in rows){
		   				var ed = $('#examineGrid').datagrid('getEditor', {index:i,field:'newReportPq'});
			    		var meterReadPq = $(ed.target).numberbox('getValue');
			    		newRowsList.push(meterReadPq)
		   			}
					 var initRowsList = me.initRowsList;
					 var flag = false
	    			 for(var i=0 ;i < rows.length;i++){
    					 if(!(initRowsList[i] == null && newRowsList[i] == "") && (initRowsList[i] != parseFloat(newRowsList[i]))){
    						 flag=true;
    						 break;
	    				 }
	    			 }
			    	if(flag){
			    		MainFrameUtil.confirm({
			    			title:"警告",
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
	 		    onBeforeSelect: function(index, row){
	 		    	var selectedRow = $("#examineGrid").datagrid("getSelected");
	 		    	if(selectedRow != null && index == $("#examineGrid").datagrid("getRowIndex",selectedRow)){
	 		    		return false;
	 		    	}
	 		    	//判断分月电量之和是否大于总电量，若大于，则不允许选择其他用户
	 		    	var rows = $("#yearPlanGrid").datagrid("getRows");
	 		    	if(rows != null && rows.length > 0){
	 		    		for (var i = 0; i < rows.length; i++) {
							var yearRow = rows[i];
							var totalPq = parseFloat(yearRow.totalPq == null ? "0" : yearRow.totalPq);
							var total = parseFloat(yearRow.total == null ? "0" : yearRow.total);
							if(totalPq > total){
								MainFrameUtil.alert({title:"提示：",body: yearRow.compositeName + "交易合计电量不能大于总电量！"});
								return false;
							}
						}
	 		    	}
	       		},
	 		    onSelect: function(index, row){
	       			//加载“年度交易委托电量分月信息”表格信息
	       			var year = me.params2.phmPurchasePlanMonth.ym.substr(0,4);
	       			var consId = row.consId;
	       			if(me.saveYearPlanPqMap[consId] != null){
	       				$("#yearPlanGrid").datagrid("loadData",me.saveYearPlanPqMap[consId]);
	       			}else{
	       				$.ajax({
		    				url : basePath + "phmAgrePqExamineController/getYearBidDetailList?year="+year + "&consId=" + consId,
		    				type : 'get',
		    				contentType : 'application/json;charset=UTF-8',
		    				success : function(data) {
		    					if(data.flag && data.data != null){
	    							$("#yearPlanGrid").datagrid("loadData",data.data);
		    					}else{
		    						MainFrameUtil.alert({title:"提示",body: data.msg});
		    					}
		    				},
		    				error : function() {
		    					MainFrameUtil.alert({title:"提示",body:"查询失败！"});
		    				}
		    			});
	       			}
	       		},
                onLoadSuccess: function(data){
                	//获取列表页码
                	me.pageNumber = $("#examineGrid").datagrid("getPager").data("pagination").options.pageNumber;
		           	if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000"){
		           		$("#examineGrid").datagrid('hideColumn', 'bidPq');
		           	}
		           	$("#examineGrid").datagrid('hideColumn', 'agrePq');
	    			//表加载完成标识
	               	me.initFlag = false;
	    			me.initRowsList = [];
                	for(var i=0 ;i < data.rows.length;i++){
	       				$("#examineGrid").datagrid("beginEdit",i);
	       				//获取编辑格数据
	       				me.initRowsList.push(data.rows[i].newReportPq);
	       			}
                	me.calAgrePq();
                }
			});
			$("#yearPlanGrid").datagrid({
 				data: [ ],
		        height:"100%",
		        width:"100%",
		        rownumbers: true,
		        pagination: false,
		        fitColumns:true,
		        nowrap: false, 
		        striped: true,
		        singleSelect: false,
		        scrollbarSize:0,
		        fit: true,
		        rowStyler:function(idx,row){
		            return "height:35px;font-size:12px;";
		        },
		        columns:[[
	            	{field:'compositeName',title:'交易名称',width:150,align:'center'},
	            	{field:'consName',title:'用户名称',width:150,align:'center'},
	            	{field:'total',title:'总电量',width:100,align:'center',
	            		formatter: function(value,row,index){
	        				if (value == null || isNaN(value)){
	        					return '--';
	        				} else {
	        					return parseFloat(value).toFixed(3);
	        				}
	        			},
	        			editor:{type:'numberbox',options:{min:0,precision:3}}
					},
					{field:'totalPq',title:'合计电量',width:100,align:'center',
	            		formatter: function(value,row,index){
	        				if (value == null || isNaN(value)){
	        					return '--';
	        				} else {
	        					return parseFloat(value).toFixed(3);
	        				}
	        			}
					},
	            	{field:'jan',title:'1月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'feb',title:'2月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'mar',title:'3月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'apr',title:'4月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'may',title:'5月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'jun',title:'6月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'jul',title:'7月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'aug',title:'8月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'sept',title:'9月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'oct',title:'10月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'nov',title:'11月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					},
	            	{field:'dece',title:'12月',width:100,align:'center',editor:{type:'numberbox',options:{min:0,precision:3,onChange:function(newValue,oldValue){me.sum(this);}}},
	            		formatter: function(value,row,index){ if (value == null || isNaN(value)){ return '--'; } else { return parseFloat(value).toFixed(3); }}
					}
			    ]], 
			   	onLoadSuccess: function(data){
			   		me.initYearGrid = true;
			   		for(var i=0 ;i < data.rows.length;i++){
	       				$("#yearPlanGrid").datagrid("beginEdit",i);
	       			}
			   		me.initYearGrid = false;
			    }
		    }); 
		},
		//年度交易委托电量分月信息 表格行求和（合计电量列）
		sum: function(dom){
			var row = $(dom).parents("tr.datagrid-row").slice(0,1);
		    var inputRowIndex = row.prevAll().length;	//编辑的行index
		    
		    //==================计算横向12个月合计=====================
			var inputs = row.find("input.textbox-value");
			var totalPq = null;
			for(var i = 0; i < inputs.length; i ++){
				var field = $(inputs[i]).parents("td[field]").slice(0,1).attr("field");
				var value = $(inputs[i]).val();
				if(!this.initYearGrid){		//加载时触发的此方法不保存表格数据
					$("#yearPlanGrid").datagrid('getRows')[inputRowIndex][field] = value;
			    }
				if(i == 0){
					continue;
				}
				if(value != null && value != ""){
					value = parseFloat(value);
					if(totalPq != null){
						totalPq += value;
					}else{
						totalPq = value;
					}
				}
			}
			if(totalPq != null){
				totalPq  = totalPq.toFixed(3);
			}
			row.find("td[field='totalPq'] div.datagrid-cell").text(totalPq == null ? "":totalPq);
		    $("#yearPlanGrid").datagrid('getRows')[inputRowIndex].totalPq = totalPq; 
		    if(!this.initYearGrid){		//加载时触发的此方法不保存表格数据
		    	var consId = $("#yearPlanGrid").datagrid('getRows')[0].consId;
			    if(this.saveYearPlanPqMap[consId] == null){
				    this.saveYearPlanPqMap[consId] = $("#yearPlanGrid").datagrid('getRows');
			    }
		    }
		},
		loadData: function(page,rows,flag){
			var me = this;
			//翻页 数据不比对
			me.initFlag=true;
			if(flag){
				me.saveAgrePqs(null);
			}
			//刷新表格
			$("#examineGrid").datagrid("reload");
	    },
	    calAgrePq:function(){
	    	var me = this;
	    	$("#examineGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#examineGrid").datagrid("getEditor",{index:rowIndex,field:"newReportPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			if(pq != null && pq != ''){
	    				var oldAgrePq = $("#examineGrid").datagrid("getRows")[rowIndex].agrePq == null ? 0 : parseFloat($("#examineGrid").datagrid("getRows")[rowIndex].agrePq)
	    				var total = me.countParams.agrePq == null ? 0 : parseFloat(me.countParams.agrePq);
	    				me.countParams.agrePq = total - oldAgrePq + parseFloat(pq);
	    				$("#examineGrid").datagrid("getRows")[rowIndex].agrePq = pq;
					}
	    		})
	    	});
	    },
		initData:function(){
			var me = this;
			//初始化数据
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.planId,
				type : 'get',
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data.data!=null){
						me.params2.phmPurchasePlanMonth = data.data;
						if(data.extMap != null){
							if(data.extMap["agrePq"]!=null){
								me.countParams.agrePq = data.extMap["agrePq"];
							}
							if(data.extMap["dealPq"] != null){
								me.countParams.dealPq = data.extMap["dealPq"];
							}
						}
						me.params.ym = me.params2.phmPurchasePlanMonth.ym;
						me.params.planId = me.planId;
						me.calcDate(me.params2.phmPurchasePlanMonth.ym);	//计算得到表格中历史用电量的年月信息
						me.initGrid();
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"查询明细失败！"});
				}
			});
		},
		//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc", handler:function(btn,params){me.saveAgrePqs(btn.target)}},
			               {text:"提交",type:"primary",  handler:function(btn,params){me.submit(btn.target)}},
			               {text:"回退",type:"warning", bgcolor:"#ecaa55",handler:me.reply},
			               {text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_edit")}}];
        	
            return buttons;
        },
		calcDate: function(ym){
			var me = this;
			var year = parseInt(ym.substr(0,4));
			var month = parseInt(ym.substr(4,2));
			var yy = year;
			for(var i = 1 ;i < 4 ;i++){
				var y = year;
				var m = month-i;
				if(m<1){
					y -= 1;
					m += 12;
				}
				if(m<10) m="0"+m;
				me.mArray[i-1] = ""+y+m;
			}
			if(month<10) month = "0"+month; 
			year -= 1;
			me.yArray[0] = ""+year+month;
			year -= 1;
			me.yArray[1] = ""+year+month;
		},
		
		remind:function(type){
			var me = this;
			var rows = $("#examineGrid").datagrid("getChecked");
			if(rows == null || rows.length === 0){
				MainFrameUtil.alert({title:"提示：",body:"请选择提醒用户！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:"remind",
	  			params:{"rows":rows,"type":type,"ym":me.params.ym},
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/remind',
				iframe:true,
				modal:true,
				maximizable:true,
				width:500,
				height:320,
				onClose:function(){
					
				}
			}) 
		},
		download:function(value,row,index){ 
			if(value == null || value == ""){
				return "<a onclick=\"mineVue.upload('"+index+"')\">上传</a>";
            }else{
            	var file = eval("("+value+")");
            	var color = "";
            	if(row.fileRecord === file.fileId){
            		color = "style=\"color:red;text-decoration:none;\"";
            	}
            	var check = "";
            	if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".pdf") == file.fileName.length-4){
            		check = "<a href='javascript:void(0)' onclick='mineVue.pdfPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
            	}
        		else if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".jpg") == file.fileName.length-4){
        			check = "<a href='javascript:void(0)' onclick='mineVue.jpgPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
            	}
            	else if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".png") == file.fileName.length-4){
            		check = "<a href='javascript:void(0)' onclick='mineVue.jpgPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
            	}
            	
            	return "<a onclick=\"mineVue.upload('"+index+"')\">上传</a>&nbsp;&nbsp;" +
            		   "<a target=\"view_frame\" href='${Config.getConfig('file.service.url')}/" + file.fileId + "'>下载</a>&nbsp;&nbsp;" + 
            		   check;
            }
    	},
    	
    	pdfPreview:function(fileid ,name){
    		var url= basePath+"view/business-core/pdfviewer/viewer?id="+fileid;
    		MainFrameUtil.openDialog({
                id:'pdfPreviewDialog',
                iframe:true,
                href: url,
                title: name + ' 预览',
                modal:true,
                width:"80%",
                height: 620,
                maximizable:true,
                buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('pdfPreviewDialog');
                    }
                }]
            });		
		},	
		
		jpgPreview:function(id,name){
			MainFrameUtil.openDialog({
                id:'imagePreviewDialog',
                iframe:true,
                params:{id:id,name:name},//传递参数.可以通过 MainFrameUtil.getParams() 获取
                href: basePath+'/view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/imagePreview?id='+id+'&name='+name,
                title:'图片预览',
                modal:true,
                width:"80%",
                height: 620,
                maximizable:true,
                buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('imagePreviewDialog');
                    }
                }]
            });
		},
    	
    	upload : function(index){
    		var me = this;
    		MainFrameUtil.openDialog({
                id:'upload',
                href: '${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/upload',
                params : {},
                modal:true,
                iframe:true,
                title:'上传',
                width:"35%",
                height:500,
                onClose:function(params){
                    if(params != null && params.fileId){
                    	var rows = $('#examineGrid').datagrid('getRows');
                    	rows[index].fileId = params.fileId;
                    	//获取‘本次交易委托电量’编辑格的值
                   		var newReportPq= $($('#examineGrid').datagrid('getEditor', {index: index,field:'newReportPq'}).target).numberbox('getValue');
                    	$('#examineGrid').datagrid('refreshRow', parseInt(index));
                    	//为‘本次交易委托电量’编辑格 赋值
                    	rows[index].newReportPq = newReportPq;
                    	//上传成功后打开编辑框
    	       			$("#examineGrid").datagrid("beginEdit", parseInt(index));
                    }
                }
            })
    	},
    	
    	
    	reply:function(){
			var me = this;
		    var id = me.params.planId;
		    MainFrameUtil.confirm({
		        title:"确认",
		        body:"确定将计划退回？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.messager.progress({title:"请等待",msg:"正在退回...",interval:100});
		    			$.ajax({
		    				url:basePath+"cloud-purchase-web/phmPurchasePlanMonthController/recall",
		    				method:'put',
		    				data:$.toJSON({id:id , planStatus:"01"}),
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
		
    	dowFlag:function(fileId,id){
    		$.ajax({
    			url:basePath+"cloud-purchase-web/phmAgrePqExamineController/setFileRecord?fileRecord="+fileId+"&id="+id,
				type:"get",
				success:function(data){
					if(data == true){
						$("." +fileId+"").css("color","red");
			    		$("." +fileId+"").css("text-decoration","none");
					}
				}
    		});
    	},
    	
		forecast: function(){
			var me = this;
			if(this.forecastResFlag == false){
				MainFrameUtil.alert({title:"提示",body:" 您未购买此功能。"});
				return;
			}
			var me = this;
			var rows = $("#examineGrid").datagrid("getRows");
			var ym = me.params.ym;
			var getFlag = false;
			//查所有用户的id
			var queryParams = $.extend({},me.params);
			queryParams["pagingFlag"] = false;
			$.ajax({
				 url:  basePath + 'cloud-purchase-web/phmAgrePqExamineController/getList',
				 method: 'post',
				 data:$.toJSON(queryParams),
				 contentType : 'application/json;charset=UTF-8',
				 success: function(data){
					 for(var i=0 ; i < data.rows.length ; i++){
						 if(i == 0){
							 me.consIds = me.consIds + data.rows[i].consId;
						 }else{
							 me.consIds = me.consIds + ',' + data.rows[i].consId;
						 }
					 }
					 getFlag = true;
					 if(getFlag){
						 //调预测url
						$.ajax({
							url :bigdataUrl + "powerFore/LongAssAction!predictLongAssDlMonth.action?ym="+ym+"&consNo="+me.consIds+"&av=b7b7596fffc91a2bc016c1128df857fd",
							type: 'get',
							success: function(data){
								for(var i=0; i < rows.length; i++){
									for(var k = 0 ; k < data.length; k++){
										if(rows[i].consId == data[k].consNo){
											rows[i].forecastPq = data[i].dl;
											$('#examineGrid').datagrid('refreshRow',i);
											$('#examineGrid').datagrid('beginEdit',i);
										}
									}
								}
								//后台更新所需数据
								for(var arr in data){
									me.result.push(data[arr]);
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"网络异常，请刷新重试！"});
							}
						});
					 }
				 }
			});
			
			$.messager.progress('close');  //关闭进度条
		},
		//点击表格上的保存按钮
		saveAgrePqs: function(target){
			
			var me = this;
			me.endEdit();
			var rows = $("#examineGrid").datagrid("getRows");
			var flag = true;
			
			var yearPlanProxyPqDetailList = [];
			for(var consId in this.saveYearPlanPqMap){
				var detailList = this.saveYearPlanPqMap[consId];
				if(detailList != null && detailList.length > 0){
					for (var i = 0; i < detailList.length; i++) {
						var yearRow = detailList[i];
						var totalPq = parseFloat(yearRow.totalPq == null ? "0" : yearRow.totalPq);
						var total = parseFloat(yearRow.total == null ? "0" : yearRow.total);
						if(totalPq > total){
							MainFrameUtil.alert({title:"提示：",body: yearRow.compositeName + "交易合计电量不能大于总电量！"});
							return ;
						}
					}
					yearPlanProxyPqDetailList = yearPlanProxyPqDetailList.concat(detailList);
				}
			}
			if(target != null){
				//按钮不可点击
	       		$(target).attr("disabled","disabled");
			}
			$.messager.progress({title:"请等待",msg:"正在保存...",interval:100});
			if(flag){
				$.ajax({
					url:basePath+"cloud-purchase-web/phmAgrePqExamineController/saveAgrePqs",
					method:'post',
	                data:$.toJSON({phmAgrePqExamineDetailList: rows,forecastData: me.result,flag:false,yearPlanProxyPqDetailList: yearPlanProxyPqDetailList}),
	                contentType : 'application/json;charset=UTF-8',
					type:"json",
					success:function(data){
						if(data != null){
							MainFrameUtil.alert({title:"提示",body:data.msg});
							$.messager.progress('close');
							
						}else{
							$.messager.progress('close');
							MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						}
						if(target != null){
							//按钮可点击
	       					$(target).attr("disabled",false);
						}
					}
				});
			}
		
		},
		endEdit:function(){
			var rows = $("#examineGrid").datagrid("getRows");
			for(var rowIndex = 0 ; rowIndex < rows.length ; rowIndex++){
				editor = $("#examineGrid").datagrid("getEditor",{index:rowIndex,field:"newReportPq"});
				var pq = editor.actions.getValue(editor.target);
				rows[rowIndex].newReportPq = pq;
			}
		},
		//点击提交按钮
		submit: function(target){
			//按钮不可点击
			var me = this;
			me.endEdit();
			var rows = $("#examineGrid").datagrid("getRows");
			
			var yearPlanProxyPqDetailList = [];
			for(var consId in this.saveYearPlanPqMap){
				var detailList = this.saveYearPlanPqMap[consId];
				if(detailList != null && detailList.length > 0){
					for (var i = 0; i < detailList.length; i++) {
						var yearRow = detailList[i];
						var totalPq = parseFloat(yearRow.totalPq == null ? "0" : yearRow.totalPq);
						var total = parseFloat(yearRow.total == null ? "0" : yearRow.total);
						if(totalPq > total){
							MainFrameUtil.alert({title:"提示：",body: yearRow.compositeName + "交易合计电量不能大于总电量！"});
							return ;
						}
					}
					yearPlanProxyPqDetailList = yearPlanProxyPqDetailList.concat(detailList);
				}
			}
			
       		$(target).attr("disabled","disabled");
			$.messager.progress({title:"请等待",msg:"正在保存...",interval:100});
			$.ajax({
				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/saveAgrePqs",
				method:'post',
                data:$.toJSON({phmAgrePqExamineDetailList: rows,forecastData: me.result,flag:false,yearPlanProxyPqDetailList: yearPlanProxyPqDetailList}),
                contentType : 'application/json;charset=UTF-8',
				type:"json",
				success:function(data){
					if(data != null){
						$.messager.progress('close');
						var flag = null ;
						var content = "提交后计划将无法修改，确定提交吗？";
						for(var i = 0 ; i < rows.length ; i++){
							if(rows[i].fileId == null || rows[i].fileId == ""){
								flag = true;
							}
						}
						if(flag != null){
							content = "不是所有用户都有确认函，提交后计划将无法修改,是否提交？";
						}
						var complex = me.params.planId + me.params2.phmPurchasePlanMonth.tradeMode;
						MainFrameUtil.confirm({
					        title:"确认",
					        body:content,
					        onClose:function(type){
					            if(type=="ok"){//确定
					            	$.messager.progress({title:"请等待",msg:"正在提交...",interval:100});
					    			$.ajax({
					    				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/getNull?planId="+complex,
					    				method:'get',
					    				type:"json",
					    				success:function(data){
					    					if(data != null){
					    						MainFrameUtil.alert({title:"提示",body:data.msg});
					    						$.messager.progress('close');
					    						MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
					    						MainFrameUtil.closeDialog("plan_edit");
					    					}else{
					    						MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});//按钮可点击
					           					$(target).attr("disabled",false);
					    						return;
					    					}
					    					
					    				}
					    			});
					            }else{
		    						//按钮可点击
		           					$(target).attr("disabled",false);
					            }
					        }
			    		});
					}else{
						MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						//按钮可点击
       					$(target).attr("disabled",false);
				    	return;
					}
				}
			});
			
		}
	}
});
</script>
</body>
</html>