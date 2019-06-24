<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<title>合同执行情况</title>
</head>
<body id="detailBody" v-cloak>
<mk-form-panel title="售电合同电量分月执行情况" >
	<mk-form-row>
		<!-- 合同执行总体进度 -->
        <mk-form-col :label="formFields.implementation.label" colspan="3">
        	<su-progress ptype="success" label="true" :now="params.execute" pstriped="true" animated="true"></su-progress>
        </mk-form-col>
	</mk-form-row>
	<mk-form-row>
		<!-- 合同电量 -->
        <mk-form-col :label="formFields.agreePq.label" >
            <su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
        <!-- 已结算电量 -->
        <mk-form-col :label="formFields.delivryPq.label" >
            <su-textbox :data.sync="params.balancePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
        </mk-form-col>
		<!-- 剩余电量-->
        <mk-form-col :label="formFields.surplusPq.label" >
            <su-textbox :data.sync="params.remainderPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
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
		listMap:{
			agrePq:[],
			balancePq:[]
		},
		params:{
			execute : 0,
			agrePq : null,
			balancePq : null,
			remainderPq : null
		}
		
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('detail').id;
		ComponentUtil.getFormFields("purchase.agreement.implementation",this);
		me.getExecution(id);
		$('#monthGrid').datagrid({
			 url:basePath+"cloud-purchase-web/sellContractController/getAgreImplementation/"+id,
			 method:"get",
			 columns:[[
					{field:'name',title:'',width:80},
					{field:'1',title:'1月',width:80},
					{field:'2',title:'2月',width:80},
					{field:'3',title:'3月',width:80},
					{field:'4',title:'4月',width:80},
					{field:'5',title:'5月',width:80},
					{field:'6',title:'6月',width:80},
					{field:'7',title:'7月',width:80},
					{field:'8',title:'8月',width:80},
					{field:'9',title:'9月',width:80},
					{field:'10',title:'10月',width:80},
					{field:'11',title:'11月',width:80},
					{field:'12',title:'12月',width:80},
			           ]],
			 width:"100%",
			 nowrap: false,
			 singleSelect:true,
			 fitColumns:true,
			 loadFilter:function(data){
				 var filterData ={
						 agrePq:{name:"月度合同电量"},
						 deliveryPq:{name:"实际用电量"},
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
// 					 me.listMap.agrePq.push(obj.agrePq);
// 					 me.listMap.balancePq.push(obj.balancePq);
				 }
				 var list = [filterData.agrePq,filterData.deliveryPq,filterData.balancePq,filterData.deviationPq,filterData.deviationRate];
				 for(var i=1;i<=12;i++){
					 if(me.isNotNull(filterData.agrePq[i])){
						 me.listMap.agrePq.push(filterData.agrePq[i]);
					 }else{
						 me.listMap.agrePq.push(null);
					 }
					 if(me.isNotNull(filterData.balancePq[i])){
						 me.listMap.balancePq.push(filterData.balancePq[i]);
					 }else{
						 me.listMap.balancePq.push(null);
					 }
				 }
				 return list;
			 },
			 onLoadSuccess:function(){
				 me.setChars();
			 }
		})
		
	},
	methods:{
		
		getExecution:function(id){
			var me = this;
			if(id == null || id == ""){
				return;
			}
			$.ajax({
				url : basePath+"cloud-purchase-web/sellContractController/getExecution/"+id,
				type : "get",
				success : function(data){
					if(data != null){
						me.params = data;
					}
				}
			});
		},
		
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
				title:{text:"售电合同执行情况"},
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