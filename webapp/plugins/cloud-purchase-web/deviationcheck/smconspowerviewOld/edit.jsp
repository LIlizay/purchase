<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<link href="${Config.baseURL}view/cloud-purchase-web/static/css/calendar.css" type="text/css" rel="stylesheet"/>
<title>偏差考核预警-用户用电计划编辑</title>
<style type="text/css">
table {
		border-collapse:separate;
		-webkit-border-horizontal-spacing: 5px;
	    -webkit-border-vertical-spacing: 5px;
	}
.calendar{
	display:block;
	width:30px;
	height:30px;
	background-color:#9adada;
	position:relative;
	left:0px;
	top:0px;
	z-index:2;
}
.upMonth{
	background-color:#F1F1F1;
}
.usedMonth{
	background-color:#F8F8FF;
}
.deviationStyle{
    display: inline-block;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    position: relative;
    top: 0;
    left: 60%;
}
input{
	width:100%;
}

img:hover{
	cursor:pointer;
}
.legend{
	font-family: MicrosoftYaHei;
	font-size: 12px;
	color: #4A4A4A;
	letter-spacing: 0;
	float:right;
}
.title{height:45px;text-align:center;font-family: MicrosoftYaHei;font-size:16px;line-height:45px;width:100%;}
</style>
</head>
<body id="conspowerAddVue">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="用户用电查看" num="2">
		<mk-form-row>
			<!-- 用户名 -->
	        <mk-form-col :label="formFields.consName.label" >
	        	<su-textbox :data.sync="params.consName" disabled="disabled"></su-textbox>
	        </mk-form-col>
			<!-- 合同电量 -->
	        <mk-form-col :label="formFields.agrePqFrom.label">
	        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="disabled"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
			<!-- 已完成电量 -->
	        <mk-form-col :label="formFields.actualPqFrom.label">
	        	<su-textbox :data.sync="params.actualPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="disabled"></su-textbox>
	        </mk-form-col>
	        <!-- 剩余电量 -->
	        <mk-form-col :label="formFields.surplusPq.label">
	        	<su-textbox :data.sync="params.surplusPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="disabled"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	<mk-panel title="用户用电量列表" line="true">
		<mk-form-panel title="">
	       	<div style="position:relative;height:45px;">
				<div id="title" class="title" style="position:absolute;">
				</div>
				<div class="title" style="position:absolute;right:50px">
					<span class="legend">单位：兆瓦时</span>
				</div>
			</div>
			<table id="conspower" border="0" cellspacing="10px" width="100%">
				<tr height="45px">
					<td class="weekendDate">星期日</td>
					<td class="workDate">星期一</td>
					<td class="workDate">星期二</td>
					<td class="workDate">星期三</td>
					<td class="workDate">星期四</td>
					<td class="workDate">星期五</td>
					<td class="weekendDate">星期六</td>
				</tr>
			</table>
		</mk-form-panel>
	</mk-panel>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var conspowerAddVue = new Vue({
	el:"#conspowerAddVue",
	data:{
		list:[],
		inputIds : null,
		formFields:{},
		currentPqFlag:false,//当前年月可上报电量标志
		id:null,//id
		params:{
			ym:null,
			planId:null,
			consId:null,
			consName:null,
			agrePq:null,
			actualPq:null,
			surplusPq:null,
		}
	},
	ready:function(){
        ComponentUtil.getFormFields("selling.deviationcheck.smconspowerview",this);
       	//获取值
        var row = MainFrameUtil.getParams("conspowerAdd").row;
       	console.log("row: ", row);
        this.params.consId = row.consId;
        this.params.planId = row.planId;
        this.params.consName = row.consName;
        this.params.agrePq = row.agrePq;
        this.params.actualPq = row.actualPq;
        this.params.ym = row.ym;
       
        
        this.initData();
     	//设置表格年月
    	var title = this.params.ym.substr(0,4) + "年" + parseInt(this.params.ym.substr(4,6)) + "月";
    	$("#title").text(title); 
    	//初始化按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"conspowerAdd");
        
	},
	methods:{
		//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"保存",type:"primary",handler:me.save},{text:"关闭",handler:function(){MainFrameUtil.closeDialog("conspowerAdd")}}];
            return buttons;
        },
		
		//初始化数据
		initData:function(){
			var me= this;
			$.ajax({
				url : basePath + "cloud-purchase-web/smConsPowerViewController/getSmConsPowerView",
   	   			type : 'post',
   	   			data : $.toJSON(me.params),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					if(data.data){
   	   						var list = data.data.list;
   	   						for(var a = list.length - 1; a >= 0; a--){
   	   							if(list[a].actualPq != null && list[a].actualPq != ""){
   	   								me.params.actualPq = Number(list[a].actualPq);
   	   								break;
   	   							}
   	   						}
	   	   					//剩余电量
	   	   			        var agrePq = 0;
	   	   			        if(!isNaN(Number(me.params.agrePq))){
	   	   			        	agrePq = Number(me.params.agrePq);
	   	   			        }
	   	   			        var actualPq = 0;
	   	   			        if(!isNaN(Number(me.params.actualPq))){
	   	   			        	actualPq = Number(me.params.actualPq);
	   	   			        }
	   	   			        var surplusPq = (agrePq - actualPq).toFixed(2);
	   	   			        surplusPq = surplusPq<0?0:surplusPq;
	   	   			 	 	me.params.surplusPq = surplusPq;
	   	   			  
   	   						me.setGridData(data.data);
   	   					}
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		})
		},
		//设置表格数据
		setGridData:function(data){
			var me = this;
			//当前月的天数
			var date = parseInt(data.date);
			//上一个月的天数
			var lastDate =parseInt(data.lastDate);
			
			var list = data.list;
			me.list = list;
			//获取当前月的第一天的星期
			var currentDate = list[0].ymd.substr(0,4) + "-" + list[0].ymd.substr(4,2) + "-" + list[0].ymd.substr(6);
			//处理偏差提醒
// 			var deviationList = data.deviationList;
// 			var deviationMap = {};
// 			for(var i in deviationList){
// 				var day = parseInt(deviationList[i].warningDate.substring(8,10));
// 				deviationMap[day] = deviationList[i];
// 			}
			var day = new Date(currentDate);
			var week = day.getDay();
			
			var html = "<tr style='vertical-align:top;height:90px'>";
			var len = 0;
			//前面填空
			for(var i=0; i<week; i++){//
				var day2 = new Date(currentDate);
				day2.setDate(day.getDate() - week + i);
				html += "<td class='everyDay'><span class='calendar upMonth'>" + (day2.getDate()) + "</span></td>";
				len++;
			}
			var ids = 0;
			//中间数据
			for(var i in list){
				ids++;
				var row = list[i];
				var planPq = row.planPq;
				var actualPq = row.actualPq;
				var dayPlanPq = row.dayPlanPq;
				var dayActualPq = row.dayActualPq;
				var deviationRate = row.deviationRate;
				var deviationClass = "<span class='calendar' >"+ parseInt(list[i].ymd.substr(6)) +"</span>";
				
				if(planPq === null){
					planPq = "";
				}
				if(actualPq === null){
					actualPq = "";
				}
				if(dayPlanPq === null){
					dayPlanPq = "";
				}
				if(dayActualPq === null){
					dayActualPq = "";
				}
// 				if(!deviationRate){
// 					deviationRate = "";
// 				}else{
// 					deviationRate = deviationRate+"%";
// 				}
				
				/* 偏差预警数据组装格式
				deviationMap = {
						"20180301" : {
							id : "123456",
							warningGrade : "01",
							warningDate : "2018-03-01"
						},
						"20180302" : {
							id : "123456",
							warningGrade : "01",
							warningDate : "2018-03-02"
						}
				}
				*/
// 				if(typeof(deviationMap[row.ymd])!=="undefined"){
// 					var obj = deviationMap[row.ymd];
// 					var color = "";
// 					if(obj.warningGrade = "01"){
// 						color = "yellow";
// 					}else if(obj.warningGrade = "02"){
// 						color = "orange";
// 					}else{
// 						color = "red";
// 					}
// 					deviationClass = "<span class='calendar' style='float:left'>"+parseInt(list[i].ymd.substr(6))
// 						+"</span><img class='deviationStyle' onClick=conspowerAddVue.deviationDetail('"+obj.id
// 						+"') src='../../static/images/"+color+".png' />";
// 				}
				if(len % 7 == 0){
					html += "</tr><tr style='vertical-align:top;height:90px'>";
				}
				html += "<td class='everyDay'>"+
							deviationClass+
							"<p style='margin-top:10px'>计划日用电量：" + "<input id = '"+ ids +"' oninput=\"conspowerAddVue.inputChange('"+ ids +"')\" style=\"width:100px\" type='number' min='0' value='" + dayPlanPq + "' />" + "</p>"+
							"<p style='margin-top:10px' id='sum"+ ids +"'>计划累计用电量：" + planPq + "</p>"+
							"<p style='margin-top:10px'>日用电量：" + dayActualPq + "</p>"+
							"<p style='margin-top:10px'>累计用电量：" + actualPq + "</p>" +  
						"</td>";
				len++;
				
			}
			me.inputIds = ids;
			//后面填空
			if(len % 7 != 0){
				var len1 = 7 - (len % 7);
				var lastDate = list[list.length - 1].ymd.substr(0,4) + "-" + list[list.length - 1].ymd.substr(4,2) + "-" + list[list.length - 1].ymd.substr(6);
				var lastDay = new Date(lastDate);
				for(var i = 0; i < len1; i++){
					var day2 = new Date(lastDate);
					day2.setDate(lastDay.getDate() + i + 1);
					html += "<td class='everyDay'><span class='calendar upMonth'>" + (day2.getDate()) + "</span></td>";
				}
			}
			html += "</tr>";
			$("#conspower").append(html);
			
		},
		deviationDetail:function(id){
			MainFrameUtil.openDialog({
	  			id:"warnAdd",
	  			params:{"id":id},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarninginfo/add',
				iframe:true,
				modal:true,
				width: "80%",
				height: 420,
			})
		},
		
		//保存
		save : function(){
			var me= this;
			var list = [];
			for(var i = 0; i < me.inputIds; i++){
				var dayPlanPq = $("#"+(i+1)).val();
				if(dayPlanPq == null || dayPlanPq == "" || isNaN(dayPlanPq)){
					MainFrameUtil.alert({ title:"提示", body:"计划电量分配不能为空,请完善数据！"}); 
					return;
				}
				dayPlanPq =  parseFloat(parseFloat(dayPlanPq).toFixed(3))
				var planPq = parseFloat(parseFloat($("#sum"+(i+1)).text().substr(8)).toFixed(3));
				var smDeviationInfo = {
						id : me.list[i].id,
						planPq : planPq,
						dayPlanPq : dayPlanPq,
						isDelete: 0
				}
				list.push(smDeviationInfo);
			}
			var param = {
					smConsPowerViews : list
			}
				me.saveData(param);
		},
		
		saveData : function(param){
			$.ajax({
				url : basePath + "cloud-purchase-web/smConsPowerViewController/updatePlanPq",
   	   			type : 'post',
   	   			data : $.toJSON(param),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data){
   	   					MainFrameUtil.alert({ title:"成功提示", body:data.msg}); 
   	   				}else{
   	   					MainFrameUtil.alert({ title:"失败提示", body:data.msg }); 
   	   				}
   	   			}
   	   		})
		},
		
		//初始化数据
		sendMessage:function(){
			var me= this;
			$.ajax({
				url : basePath + "cloud-purchase-web/smConsPowerViewController/sendMessage",
   	   			type : 'post',
   	   			data : me.params.consId,
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data){
   	   					MainFrameUtil.alert({ title:"提示", body:data }); 
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:"错误" }); 
   	   				}
   	   			}
   	   		})
		},
		
		inputChange : function(inputId){
			var me= this;
			var totalPq = 0;
			for(var i = 0; i < me.inputIds; i++){
				var planPq = $("#"+(i+1)).val();
				if(planPq != null && planPq != "" && !isNaN(planPq)){
					totalPq = parseFloat(planPq) + totalPq;
				}
				$("#sum" + (i+1)).text("计划累计用电量：" + parseFloat(totalPq.toFixed(3)));
			}
		}
	}
})
</script>
</body>
</html>