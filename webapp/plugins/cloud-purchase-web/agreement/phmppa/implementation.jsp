<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<title>合同管理-购电合同管理合同状态</title>
</head>
<body id="detailBody" v-cloak>
<mk-form-panel title="购电合同电量分月执行情况" >
	<mk-form-row>
		<!-- 合同执行总体进度 -->
        <mk-form-col :label="formFields.implementation.label" colspan="3">
        	<su-progress ptype="success" label="true" :now="0" pstriped="true" animated="true"></su-progress>
        </mk-form-col>
	</mk-form-row>
	<mk-form-row>
		<!-- 合同总电量 -->
        <mk-form-col :label="formFields.agreePq.label" >
            <su-textbox :data.sync="phmppa.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 已结算电量 -->
        <mk-form-col :label="formFields.delivryPq.label" >
            <su-textbox :data.sync="" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
		<!-- 剩余电量-->
        <mk-form-col :label="formFields.surplusPq.label" >
            <su-textbox :data.sync="" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
	</mk-form-row>
</mk-form-panel>
<mk-panel  header="false">
    <div class="row">
        <table id="monthGrid"></table>
    </div>
</mk-panel> 
<div id="contractDiv" style="width: 100%;height:400px;"></div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var detailVue = new Vue({
	el:'#detailBody',
	data:{
		formFields:{},
		phmppa:{},
		listMap:{
			agrePq:[],
			balancePq:[]
		}
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('detail').id;
		$.ajax({
    		url:basePath + 'cloud-purchase-web/phmPpaController/' + id,
    		type:"get",
			contentType : 'application/json;charset=UTF-8',
    		success:function(data){
    			if(data.flag){
  					me.phmppa = data.data;
  				}else{
  					MainFrameUtil.alert({title:"错误",body:"查询失败！"});
  				} 
    		},
    		error:function(data){
    			MainFrameUtil.alert({title:"错误",body:"查询失败！"}); 
    		}
    	})
		ComponentUtil.getFormFields("purchase.agreement.implementation",this);
		$('#monthGrid').datagrid({
			 url:basePath+"cloud-purchase-web/phmPpaController/getImplementation",
			 queryParams:{id:id},
			 method:"get",
			 columns:[[
					{field:'itemName',title:'',width:80},
					{field:'jan',title:'1月',width:80},
					{field:'feb',title:'2月',width:80},
					{field:'mar',title:'3月',width:80},
					{field:'apr',title:'4月',width:80},
					{field:'may',title:'5月',width:80},
					{field:'jun',title:'6月',width:80},
					{field:'jul',title:'7月',width:80},
					{field:'aug',title:'8月',width:80},
					{field:'sep',title:'9月',width:80},
					{field:'oct',title:'10月',width:80},
					{field:'nov',title:'11月',width:80},
					{field:'dec',title:'12月',width:80},
			           ]],
			 width:"100%",
			 nowrap: false,
			 singleSelect:true,
			 fitColumns:true,
			 //loadFilter:function(data){
				 /* var filterData ={
						 agrePq:{name:"合同分月电量"},
						 balancePq:{name:"结算电量"},
						 deviationPq:{name:"偏差电量"},
						 deviationRate:{name:"偏差率"},
				 }
				 
				 for(var i in data.data){
					 var obj = data.data[i];
					 if(obj==null){
						 continue;
					 }
					 for(var j in filterData){
						 filterData[j][obj.month] = obj[j];
					 }
				 }
				 var list = [filterData.agrePq,filterData.balancePq,filterData.deviationPq,filterData.deviationRate]; */
// 				 for(var i=1;i<=12;i++){
// 					 if(me.isNotNull(filterData.agrePq[i])){
// 						 me.listMap.agrePq.push(filterData.agrePq[i]);
// 					 }else{
// 						 me.listMap.agrePq.push(null);
// 					 }
// 					 if(me.isNotNull(filterData.balancePq[i])){
// 						 me.listMap.balancePq.push(filterData.balancePq[i]);
// 					 }else{
// 						 me.listMap.balancePq.push(null);
// 					 }
// 				 }
				 //return list;
			 //},
			 onLoadSuccess:function(){
				 me.setChars();
			 }
		})
		
	},
	methods:{
		isNotNull:function(value){
			if(typeof(value)=="undefined"||value==null||value==''){
				return false;
			}else{
				return true;
			}
		},
		setChars:function(){
			var me = this;
			$("#contractDiv").createEcharts({
				title:{text:"购电合同执行情况"},
				legend: {
				 	data:["月度合同电量（兆瓦时）","月度结算电量（兆瓦时）"],
			    },
			    xAxis: {
			        data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			    },
				series:[
				        {name: '月度合同电量（兆瓦时）',type: 'bar', data:me.listMap.agrePq},
				        {name: '月度结算电量（兆瓦时）',type: 'bar', data:me.listMap.balancePq},
				        ]
			},"bar") 
			
		}
	}
})
</script>
</body>
</html>