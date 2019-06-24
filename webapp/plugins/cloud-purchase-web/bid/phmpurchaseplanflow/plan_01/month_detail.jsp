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
<body id="examineVue" style="height:100%" v-cloak>
	<div id="examin" style="height:90%" >
		<su-form height="20%" v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
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
		<mk-panel title="用户列表" line="true" height="90%">
		    <div class="row" id="examineGrid" width="100%" style="height:100%" >
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
	    		frozenColumns:[[{field:'check',checkbox:true},{field:'consName',title:'用户名称',sort:2,width:150}]],
	    		fitColumns: true,
	    		pageSize: 10,
			    pageList: [10, 20, 50, 100, 150, 200],
	    		//frozenColumns:[[{field:'consName',title:'用户名称',sort:1}]],
	    		columnsReplace:[
	                {field:'monthA',title:me.mArray[0]},
	                {field:'monthB',title:me.mArray[1]},
	                {field:'monthC',title:me.mArray[2]},
	                {field:'yearA',title:me.yArray[0]},
	                {field:'yearB',title:me.yArray[1]},
	            ],
                onLoadSuccess: function(data){
                	me.flag = true;
		           	if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000"){
		           		$("#examineGrid").datagrid('hideColumn', 'bidPq');
		           	}
		           	$("#examineGrid").datagrid('hideColumn', 'newReportPq');
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
        getButtons:function(){
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
		download: function(value,row,index){ 
			if(value == null || value == ""){
				return;
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
    	
    	pdfPreview: function(fileid ,name){
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
		
		jpgPreview: function(id,name){
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
        calculate :function(newValue,oldValue){
        	newValue = (newValue == "" || newValue == null) ? 0 : newValue;
        	oldValue = (oldValue == "" || oldValue == null) ? 0 : oldValue;
        	if(this.flag == true){
        		var agrePqCount = (this.examineDetail.newReportPq == null || this.examineDetail.newReportPq == 0) ? "0" : this.examineDetail.newReportPq;
        		if(oldValue != ''){
        			var count = this.examineDetail.newReportPq == null ? "0" : this.examineDetail.newReportPq;
        			agrePqCount = parseFloat(count) - parseFloat(oldValue) + parseFloat(newValue);
        		}else{
        			if(this.examineDetail.newReportPq != null){
        				agrePqCount = parseFloat(this.examineDetail.newReportPq) + parseFloat(newValue);
        			}else{
        				agrePqCount = parseFloat(newValue);
        			}
        		}
        		this.examineDetail.agrePqCount = agrePqCount;
        		var rows = $("#examineGrid").datagrid("getRows");
	   	   		 for(var i = 0; i < rows.length; i++){
	   		       		 var ed = $('#examineGrid').datagrid('getEditor', {index:i,field:'newReportPq'});
	   		       		 var agrePq = 0;
	   		       		 if(ed != null){
	   		       			agrePq = $(ed.target).numberbox('getValue');
	   		       			rows[i].newReportPq = agrePq;
	   		       		 }
	   		       		 
	   		       }
        		this.editDetail = rows;
        	}
        }
	}
});
</script>
</body>
</html>