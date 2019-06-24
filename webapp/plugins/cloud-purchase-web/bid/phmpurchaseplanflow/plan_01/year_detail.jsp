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
<title>购售电交易-月度购电管理委托电量审修改（月交易）详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="examineVue" v-cloak>
	<div id="examin">
		<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
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
		<mk-panel title="用户列表" line="true">
		    <div class="row" id="examineGrid" width="100%" height="80%">
		    </div>
		</mk-panel>
		<mk-panel title="用户委托电量分月列表（兆瓦时）"  line="true">
		    <div class="row" id="subitem" width="100%" >
		    </div>
		</mk-panel>
    </div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
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
		flag:false,
		//负荷预测标识
		forecastResFlag : true,
		//负荷预测url参数
		consIds : '',
		result : [],
		editDetail:{},
		subData:[]
	},
	ready:function(){
		var me = this;
		if(MainFrameUtil.getParams("plan_edit")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
		}else if(MainFrameUtil.getParams("plan_detail")){
			MainFrameUtil.setDialogButtons(me.getButtons(),"plan_detail");
		}
		me.planId = location.search.substr(8);
		//查询字段名称加载
	    ComponentUtil.getFormFields("purchase.bid.phmagrepqexamine",me);
		me.initData();
		me.getForecastFlag();
	},
	methods:{
		getForecastFlag: function(){
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
			ComponentUtil.buildGrid("purchase.bid.phmagrepqexamineyear",$("#examineGrid"),{
				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/getYearList",
				method:"post",
		    	queryParams:me.params,
	     		pagination:true,
	    		rownumbers:true,
			    nowrap: false,
	    		fitColumns: false,
	    		frozenColumns:[[{field:'check',checkbox:true},{field:'consName',title:'用户名称',sort:2,width:150}]],
	    		pageSize: 10,
			    pageList: [10, 20, 50, 100, 150, 200],
	    		height:350,
	    		//frozenColumns:[[{field:'consName',title:'用户名称',sort:1}]],
	    		columnsReplace:[
					{field:'agrePqYA',title:me.yArray[0]},
					{field:'agrePqYB',title:me.yArray[1]},
					{field:'agrePqYC',title:me.yArray[2]},
	            ], 
                 onLoadSuccess: function(data){
                	me.flag = true;
                	if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000"){
                		$("#examineGrid").datagrid('hideColumn', 'bidPq');
                	}
                	if(data.extMap != null){
                		if(data.extMap["subMonth"] != null){
		                	me.subData = data.extMap["subMonth"];
                		}
                	}
                	$("#examineGrid").datagrid('hideColumn', 'newReportPq');
                	me.initSubGrid();
                } 
			});
			
		},
		initSubGrid:function(){
			$("#subitem").datagrid({
                width:"100%",
                height:400,
                rownumbers: true,
                pagination: false,
                nowrap: false, 
                singleSelect:true,
                scrollbarSize:0,
                fitColumns: false,
                frozenColumns:[[{field:'consName',title:'用户名称',sort:1,width:150}]],
                columns:[[
					 {field:'contName',title:'联系人',width:80},
                     {field:'contPhone',title:'联系电话',width:120},
                     {field:'jan',title:'1月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'feb',title:'2月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'mar',title:'3月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'apr',title:'4月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'may',title:'5月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'jun',title:'6月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'jul',title:'7月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'aug',title:'8月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'sept',title:'9月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'oct',title:'10月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'nov',title:'11月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}},
                     {field:'dece',title:'12月',width:80,editor:{type:'numberbox',options:{min:0,precision:3}}}
                 ]],
                 data:this.subData, 
                 rowStyler:function(idx,row){
                     return "height:35px;font-size:12px;";
                 }
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
	                		if(data.extMap["agrePq"] != null){
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
		calcDate: function(ym){
			var me = this;
			var year = parseInt(ym.substr(0,4));
			var yy = year;
			year -= 1;
			me.yArray[0] = ""+year;
			year -= 1;
			me.yArray[1] = ""+year;
			year -= 1;
			me.yArray[2] = ""+year;
		},
		
		download:function(value,row,index){ 
			if(value == null || value == ""){
				return ;
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
            	
            	return "<a target=\"view_frame\" href='${Config.getConfig('file.service.url')}/" + file.fileId + "'>下载</a>&nbsp;&nbsp;" + 
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
		getSubEditor:function(){
			var subRows = $("#subitem").datagrid("getRows");
			for(var i = 0; i < subRows.length; i++){
				var ed1 = $('#subitem').datagrid('getEditor', {index:i,field:'jan'});
				subRows[i].jan = $(ed1.target).numberbox('getValue');
				var ed2 = $('#subitem').datagrid('getEditor', {index:i,field:'feb'});
				subRows[i].feb = $(ed2.target).numberbox('getValue');
				var ed3 = $('#subitem').datagrid('getEditor', {index:i,field:'mar'});
				subRows[i].mar = $(ed3.target).numberbox('getValue');
				var ed4 = $('#subitem').datagrid('getEditor', {index:i,field:'apr'});
				subRows[i].apr = $(ed4.target).numberbox('getValue');
				var ed5 = $('#subitem').datagrid('getEditor', {index:i,field:'may'});
				subRows[i].may = $(ed5.target).numberbox('getValue');
				var ed6 = $('#subitem').datagrid('getEditor', {index:i,field:'jun'});
				subRows[i].jun = $(ed6.target).numberbox('getValue');
				var ed7 = $('#subitem').datagrid('getEditor', {index:i,field:'jul'});
				subRows[i].jul = $(ed7.target).numberbox('getValue');
				var ed8 = $('#subitem').datagrid('getEditor', {index:i,field:'aug'});
				subRows[i].aug = $(ed8.target).numberbox('getValue');
				var ed9 = $('#subitem').datagrid('getEditor', {index:i,field:'sept'});
				subRows[i].sept = $(ed9.target).numberbox('getValue');
				var ed10 = $('#subitem').datagrid('getEditor', {index:i,field:'oct'});
				subRows[i].oct = $(ed10.target).numberbox('getValue');
				var ed11 = $('#subitem').datagrid('getEditor', {index:i,field:'nov'});
				subRows[i].nov = $(ed11.target).numberbox('getValue');
				var ed12 = $('#subitem').datagrid('getEditor', {index:i,field:'dece'});
				subRows[i].dece = $(ed12.target).numberbox('getValue');
			}
		},
	}
});
</script>
</body>
</html>