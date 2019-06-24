<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-售电用户负荷预测新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<%-- <script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts1.js"></script> --%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
</head>
<body  >
<div id="eleAnalysisAddVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="客户售电合同电量分析" slot="top" deployable="false">
		<!-- 客户名称 -->
		<su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
                :class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
            <mk-trigger-text  disabled="disabled" :data.sync="params.scElectricityInfoDeatil.consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text>
        </su-form-group>
		
		<!-- 用电类别 -->
        <su-form-group :label-name="formFields.elecTypeCode.label" class="form-control-row "  col="4" 
        		:class="{'display-none':!formFields.elecTypeCode.searchHidden}" label-width="100px" label-align="right">
            <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scElectricityInfoDeatil.elecTypeCode"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" disabled="true"></su-select>
        </su-form-group> 
        
		<!-- 用电行业分类 -->
        <su-form-group :label-name="formFields.industryType.label" class="form-control-row" col="4" 
        		:class="{'display-none':!formFields.industryType.searchHidden}" label-width="100px" label-align="right">
        	<su-select disabled="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.scElectricityInfoDeatil.industryType"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_industryType" ></su-select>	
        </su-form-group>
        
		<!-- 年份 -->
        <su-form-group :label-name="formFields.year.label" class="form-control-row "  col="4" 
        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right">
            <su-datepicker disabled="disabled" :max.sync="maxYear" :min.sync="minYear" format="YYYY"  :data.sync="params.scElectricityInfoDeatil.year" ></su-datepicker>
        </su-form-group>
    </mk-search-panel>
    
    <mk-panel title="" slot="bottom" height="520px">
    	<div slot="buttons" class="pull-right" style="text-align:right">
<!--             <su-button class="fa fa-calculator" s-type="default" labeled="true" v-on:click="calculate">计算预测用电量</su-button> -->
        </div>
		<div id="line" class="col-xs-12 height-fill" style="height:470px"></div>
    </mk-panel>
    
    <mk-panel title="客户用电信息分月列表" line="true" slot="bottom" height="450px">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="line-chart" v-on:click="calculatePq">测算</su-button>
	    </div>
        <div id="eleAnalysisAddGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var bigdataUrl = basePath.replace(/\/[a-zA-Z0-9]*\/$/,"/ibd/");
// $.extend($.fn.datagrid.defaults.editors, {   
// 	numberbox: {   
//         init: function(container, options){   
//             var input = $('<input type="text" class="datagrid-editable-input">').appendTo(container);   
//             return input;   
//         },   
//         getValue: function(target){   
//             return $(target).val();   
//         },   
//         setValue: function(target, value){
//         	if(value == null || value == ""){
//         		$(target).val("");  
//         	}else{
// 	            $(target).val(parseFloat(value));   
//         	}
//         },  
//         resize: function(target, width){   
//             var input = $(target);   
//             if ($.boxModel == true){   
//                 input.width(width - (input.outerWidth() - input.width()));   
//             } else {   
//                 input.width(width);   
//             }   
//         }   
//     }   
// }); 

$(window).resize(throll(function(){
		if(eleAnalysisAddVue.char1!==null){
			eleAnalysisAddVue.char1.resize();
		}
},100));

function throll(fn,delay){
    var timer=null;
    return function(){
        clearTimeout(timer);
        timer=setTimeout(fn,delay);
    }
}


var eleAnalysisAddVue = new Vue({
	el: '#eleAnalysisAddVue',
	data: {
		forecastResFlag : true, //负荷预测权限标识    			
		calcFlag : false,
		formFields:{},
		yearFlag:false,
		consIdFlag:false,
		consInfo:null,
		maxYear:"",
		minYear:"",
		char1:null,
		url:basePath + 'view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
		yearList:[],//年下拉框数据
		year:"", //表格列重命名使用
    	params:{
    		scConsElectricitieList:[],
    		scElectricityInfoDeatil:{
    			consId:'',			//客户 ID    		
        		consName:'',		//客户名称
        		lodeAttrCode:'',	//产业分类
        		elecTypeCode:'',	//用电类别
        		industryType:'',    //用电行业分类
        		year:null 			//年份
    		}
    	},
    	charts:{//图形数据
    		text:'',//图形标题 
    		legendData:null, //图列数据
    		xAxisData:null,//水平轴数据
			firstTitle:'', //第一个图列标题
			firstChartsData:null,//第一个图列的数据 
			secondTitle:'',//第二个图列的标题
			secondChartsData:null,//第二个图列的数据
			thirdTitle:'',//第三个图例的标题
			thirdChartsData:null, //第三个图例的数据
			fourTitle:'',
			fourChartsData:null,
			lastTitle:'', //最后一个图例的标题
			lastChartsData:null,//最后一个图列的数据
			nowTitle: null,//新增 当前年份图例
			nowChartsData: null,//新增 当前年份图例
    	},
		url1:basePath + 'view/cloud-purchase-web/static/jsp/trade',
		proptext1:'',
		tradeurl:""
	},
	ready:function(){
		//查询用户有无负荷预测权限
		this.forecastRes(); 
		var me = this;
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.crm.conselecquery", this);
	    me.initYear();
	    me.ineleAnalysisAddGrid();
	    me.initTitle();
	    me.params.scElectricityInfoDeatil.consId = MainFrameUtil.getParams().consId;
	    //初始化按钮
		MainFrameUtil.setDialogButtons([
		    {text:"保存", type:"primary", handler:this.saveCalPq},
		    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
		]);
	    me.readyInitLine();
	},
	methods: {
		//查询登录用户有无负荷预测权限
		forecastRes: function(){
			var me = this;
			$.ajax({
				url : basePath+"scElectricityInfoController/forecastRes",
				type : "get",
				success : function(data){
					if(data.flag){
						//判断用户有无月度负荷预测按钮权限
	                	if(data.data == null || data.data == ''){
	                		me.forecastResFlag = false;
	                	}
					}
				} 
				
			})
		},		
		saveCalPq:function(){
			var me = this;
			if(me.params.scElectricityInfoDeatil.consId == null || me.params.scElectricityInfoDeatil.consId == ""){
				MainFrameUtil.alert({title:"提示",body:"请选择用户！"});
				return;
			}
			if(me.params.scElectricityInfoDeatil.year == null || me.params.scElectricityInfoDeatil.year == ""){
				MainFrameUtil.alert({title:"提示",body:"请选择年份！"});
				return;
			}
			for(var b = 0; b<12; b++){
				$('#eleAnalysisAddGrid').datagrid('endEdit',b);
			}
			var rows = $('#eleAnalysisAddGrid').datagrid('getRows');
			if(rows == null || rows.length < 13){
				MainFrameUtil.alert({title:"提示",body:"列表数据为空！"});
				return;
			}
			var pqFlag = false;
			for(var i = 0 ; i < rows.length ; i++){
				if("" != rows[i].personForecastPq && null != rows[i].personForecastPq){
					pqFlag = true;
				}
				$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
			}
			if(!pqFlag){
				MainFrameUtil.alert({title:"提示",body:"请输入预测数据！"});
				return;
			}
			var month = '';
			var personForecastPqList = [];
			var intj = 0;
			for(var j = 0; j<rows.length-1; j++){
				var scConsElectricitie = { 
						id:null,
						consId:null,
						ym:null,
						dataForecastPq:null,
						personForecastPq:null
				};
				if(rows[j].id != null && rows[j].id != ""){
					scConsElectricitie.id = rows[j].id;
					scConsElectricitie.dataForecastPq = rows[j].dataForecastPq;
					scConsElectricitie.personForecastPq = rows[j].personForecastPq;
				}else{
					if(j<9){
						intj = parseInt(j)+1;
						month = '0'+intj;
					}else{
						intj = parseInt(j)+1;
						month = intj;
					}
					scConsElectricitie.ym = me.params.scElectricityInfoDeatil.year+month+'';
					scConsElectricitie.consId = me.params.scElectricityInfoDeatil.consId;
					scConsElectricitie.dataForecastPq = rows[j].dataForecastPq;
					scConsElectricitie.personForecastPq = rows[j].personForecastPq;
				}
				personForecastPqList.push(rows[j].personForecastPq);
				me.params.scConsElectricitieList.push(scConsElectricitie);
			}
			me.charts.lastChartsData = personForecastPqList;
    		
    		//生成图形
	    	me.initLine(me.charts.text, 
	    			me.charts.legendData, 
	    			me.charts.xAxisData, 
	    			me.charts.firstChartsData, 
	    			me.charts.secondChartsData,
	    			me.charts.thirdChartsData, 
	    			me.charts.fourChartsData,
	    			me.charts.lastChartsData,
	    			me.charts.nowChartsData);
    		if(me.params.scConsElectricitieList != null && me.params.scConsElectricitieList.length > 0){
				if(me.params.scConsElectricitieList[0].id == null || me.params.scConsElectricitieList[0].id == ''){
					me.saveData();
				}else{
					me.updateData();
				}
    		}
		},
		
		saveData:function(){
			var me = this;
			$.ajax({
				url : basePath+"scElectricityInfoController/saveList",
				type : 'post',
				data:$.toJSON(me.params),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data){
						MainFrameUtil.alert({title:"提示",body:"保存成功！"}); 
						var rows = data.rows;
						var sum = 0.00000;
						for(var i = 0; i<13; i++){
							if(i != 12){
								if(rows[i].personForecastPq != null && rows[i].personForecastPq != ''){									
									sum = sum + parseInt(rows[i].personForecastPq);
								}
								$('#eleAnalysisAddGrid').datagrid('updateRow', {
									index:i,
									row:{id:rows[i].id}
								});
								$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
							}else{
								$('#eleAnalysisAddGrid').datagrid('updateRow', {
									index:i,
									row:{personForecastPq:sum.toFixed(3)}
								});
							}
						}
						me.params.scConsElectricitieList = [];
						//MainFrameUtil.closeDialog("edit");
					}else{
						MainFrameUtil.alert({title:"提示",body:"保存失败！"});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"保存失败！"});
				}
			});
		},
		
		updateData:function(){
			var me = this;
			$.ajax({
				url : basePath+"scElectricityInfoController/updateList",
				type : 'post',
				data:$.toJSON(this.params),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data){
						MainFrameUtil.alert({title:"提示",body:"更新成功！"}); 
						var rows = $('#eleAnalysisAddGrid').datagrid('getRows');
						var sum = 0;
						for(var i = 0; i<13; i++){
							if(i != 12){
								if(rows[i].personForecastPq != null && rows[i].personForecastPq != ''){									
									sum = sum + parseInt(rows[i].personForecastPq);
								}
								$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
							}else{
								$('#eleAnalysisAddGrid').datagrid('updateRow', {
									index:i,
									row:{personForecastPq:sum}
								});
							}
						}
						me.params.scConsElectricitieList = [];
						//MainFrameUtil.closeDialog("edit");
					}else{
						MainFrameUtil.alert({title:"提示",body:"更新失败！"});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"更新失败！"});
				}
			});
		},
		
		calculatePq:function(){
			var me = this;
			//测算权限验证
			if(!this.forecastResFlag){
    			MainFrameUtil.alert({title:"提示",body:" 您未购买此功能。"});
				return;
    		}
			if(me.calcFlag == true){
				return;
			}
			if(me.params.scElectricityInfoDeatil.consId == null || me.params.scElectricityInfoDeatil.consId == ""){
				MainFrameUtil.alert({title:"提示",body:"请选择用户！"});
				return;
			}
			if(me.params.scElectricityInfoDeatil.year == null || me.params.scElectricityInfoDeatil.year == ""){
				MainFrameUtil.alert({title:"提示",body:"请选择年份！"});
				return;
			}
			if(me.params.scElectricityInfoDeatil.year != null && me.params.scElectricityInfoDeatil.year != ""){
				var consId = me.params.scElectricityInfoDeatil.consId;
				var year = me.params.scElectricityInfoDeatil.year;
					$.ajax({
						url:bigdataUrl + "powerFore/LongAssAction!predictLongAssDl.action?year="+me.params.scElectricityInfoDeatil.year+"&consNo="+me.params.scElectricityInfoDeatil.consId+"&av=b7b7596fffc91a2bc016c1128df857fd",
// 						url: "${Config.getConfig('selling.bigdata.url')}/powerFore/LongAssAction!predictLongAssDl.action?year=2018&consNo=4ddc5e74661f47ee89ecd40925bc0b97&av=b7b7596fffc91a2bc016c1128df857fd",
						type:"get",
						success:function(data){
							if(data != '' && data != null){
								var forecastMonth = ['201801','201802','201803','201804','201805','201806','201807','201808','201809','201810','201811','201812','2018'];
								var foreData = new Object;
								var dataForecastPqList = [];//结果
								for(var i=0; i<forecastMonth.length; i++){
									//echart图数据集
									dataForecastPqList.push(data[forecastMonth[i]]);
									$('#eleAnalysisAddGrid').datagrid('endEdit', i);
									//表格赋值
									$('#eleAnalysisAddGrid').datagrid('updateRow', {
										index:i,
										row:{
											dataForecastPq:dataForecastPqList[i]
										}
									});
									if(i != 12){
										$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
									}
								}
								if(dataForecastPqList.length = 13){
									dataForecastPqList.pop();							
								}
								me.charts.fourChartsData = dataForecastPqList;
								//生成图形
						    	me.initLine(me.charts.text, 
						    			me.charts.legendData, 
						    			me.charts.xAxisData, 
						    			me.charts.firstChartsData, 
						    			me.charts.secondChartsData,
						    			me.charts.thirdChartsData, 
						    			me.charts.fourChartsData,
						    			me.charts.lastChartsData,
						    			me.charts.nowChartsData);
								
								
						    	me.calcFlag = true;
								
							}else{
								MainFrameUtil.alert({title:"提示：",body:"预测失败！请先维护用户历史电量、用户生产计划等基础数据！"});
		 					}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"网络异常，请刷新重试！"});
						}
					});
				
			}
		},
		
		initTitle: function(){
			var me = this;
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearAPq').title = (me.year != null && me.year != "" ? (parseInt(me.year)-3) : "") +"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearBPq').title = (me.year != null && me.year != "" ? (parseInt(me.year)-2) : "") +"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearCPq').title = (me.year != null && me.year != "" ? (parseInt(me.year)-1) : "") +"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'dataForecastPq').title = me.year+"年大数据预测电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'personForecastPq').title = me.year+"年人工核定电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearDPq').title = me.year+"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid();
		},
		
		initYear:function(){
			var me = this;
			var year = MainFrameUtil.getParams().year;
			me.params.scElectricityInfoDeatil.year = year;
			me.year = year;
		},
		
		//生成列表
		ineleAnalysisAddGrid:function(){
			var me = this;
			$('#eleAnalysisAddGrid').datagrid({
	    		width:"100%",
		        fitColumns:true,
			    singleSelect:true,
			    nowrap: false, 
			    checkOnSelect:true,
			    onLoadSuccess:function(){
			    	for(var i = 0; i < 12; i++){
						$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
					}
			    },
			    columns:[[
					{field:'id',hidden:true}, 
			        {field:'month',title:'月份',width:30,align:'center'},    
			        {field:'yearAPq',title:"上上上年电量",width:120,align:'center'},
			        {field:'yearBPq',title:"上上年电量",width:120,align:'center'},
			        {field:'yearCPq',title:"上年电量",width:120,align:'center'},
			        {field:'dataForecastPq',title:"大数据预测电量<br>（兆瓦时）",width:120,align:'center'},
			        {field:'personForecastPq',title:"人工核定电量<br>（兆瓦时）",width:120,align:'center',
			        	editor:{
	                    	type:'numberbox',
	                        options:{
	                        	precision:3
	                        }
	                	},
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			        {field:'yearDPq',title:"当年电量",width:150,align:'center'},
			    ]]
			});
		},
		initLine:function(title, legendData, xAxisData, firstData, secondData, thirdData,fourData,lastData, nowData){
			 var list = [firstData,secondData,thirdData,fourData,lastData, nowData];
			 this.char1 = $("#line").createEcharts({
	  				title:{text:title},
	  				legend: {
	  				 	data:legendData,
	  				 	itemHeight: 10,
	  			    },
	  			    xAxis: {
	  			    	data: xAxisData
	  			    },
	  			 	 grid:{
	  		    	    y:90
	  		        },
	  				series:[
	  				        {name: legendData[0],type: 'line',data:firstData, itemStyle:{normal:{label:{show:false}}}},
	  				        {name: legendData[1],type: 'line',data:secondData, itemStyle:{normal:{label:{show:false}}}},
	  				        {name: legendData[2],type: 'line',data:thirdData, itemStyle:{normal:{label:{show:false}}}},
	  				        {name: legendData[3],type: 'line',data:fourData, itemStyle:{normal:{label:{show:false}}}},
	  				        {name: legendData[4],type: 'line',data:lastData, itemStyle:{normal:{label:{show:false}}}},
	  				      {name: legendData[5],type: 'line',data:nowData, itemStyle:{normal:{label:{show:false}}}}
			        ]
  			},"line")
		},
		
		//求和并控制精度
		accAdd:function(arg1,arg2){ 
        	var r1,r2,m; 
        	try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0} 
        	try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0} 
        	m=Math.pow(10,Math.max(r1,r2)) 
        	return (arg1*m+arg2*m)/m 
       	},
       	initEcharts:function(){
       		var me = this;
       		$("#line").createEcharts({
       			title:{
       				text:me.charts.text,
       				textStyle:{
       					fontSize: 14,
       				}
       			},
       			legend: {
       			 	data:me.charts.legendData,
       		    },
       		    xAxis: {
       		        data: me.charts.xAxisData,
       		    },
       			series:[
				        
				        ],
       		},"line");
       	},
       	
       	readyInitLine:function(){
       		var me = this;
       		var text = '用电情况展示';
    		var firstTitle = (parseInt(me.year) - 3) + "年月度总用电量（兆瓦时）";
			var secondTitle = (parseInt(me.year) - 2) + "年月度总用电量（兆瓦时）";
			var thirdTitle = (parseInt(me.year) - 1) + "年月度总用电量（兆瓦时）";
    		var fourTitle = me.year+"年大数据预测电量（兆瓦时）";
    		var lastTitle = me.year+"年人工核定电量（兆瓦时）";
    		var nowTitle = (me.year) + "年月度总用电量（兆瓦时）";
    		var legendData = [firstTitle, secondTitle, thirdTitle, fourTitle, lastTitle];
    		var xAxisData = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
    		var yearAPqList = [];
    		var yearBPqList = [];
    		var yearCPqList = [];
    		var dataForecastPqList = [];
    		var personForecastPqList = [];
    		var yearDPqList = [];
    		for(var i = 0; i < 12; i++){
	    			yearAPqList.push('');
	    			yearBPqList.push('');
	    			yearCPqList.push('');
	    			dataForecastPqList.push('');
	    			personForecastPqList.push('');
	    			yearDPqList.push('');
    		}
    		
    		//生成图形
	    	me.initLine(text, 
	    			    legendData, 
	    			    xAxisData, 
	    			    yearAPqList, 
	    			    yearBPqList,
	    			    yearCPqList, 
	    			    dataForecastPqList,
	    			    personForecastPqList,
	    			    yearDPqList);
       	},
       	
       	queryList:function(){
       		var me = this;
			if(me.params.scElectricityInfoDeatil.year != null && me.params.scElectricityInfoDeatil.year != ""){
				me.initTitle();
				var consId = me.params.scElectricityInfoDeatil.consId;
				var year = me.params.scElectricityInfoDeatil.year;
				var params = {
						"consId":consId,
						"year":year
				}
				$.ajax({
					url:basePath+"scElectricityInfoController/getList",
					type:"post",
					data:$.toJSON(params),
	                contentType : 'application/json;charset=UTF-8',
					success:function(data){
						if(data.flag){
							var rows = data.rows;
							var text = '用电情况展示';
				    		var firstTitle = (parseInt(me.year) - 3) + "年月度总用电量（兆瓦时）";
							var secondTitle = (parseInt(me.year) - 2) + "年月度总用电量（兆瓦时）";
							var thirdTitle = (parseInt(me.year) - 1) + "年月度总用电量（兆瓦时）";
				    		var fourTitle = me.year+"年大数据预测电量（兆瓦时）";
				    		var lastTitle = me.year+"年人工核定电量（兆瓦时）";
				    		var nowTitle = (me.year) + "年月度总用电量（兆瓦时）";
				    		var legendData = [firstTitle, secondTitle, thirdTitle, fourTitle, lastTitle, nowTitle];
				    		var xAxisData = ["1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月"];
				    		var yearAPqList = [];
				    		var yearBPqList = [];
				    		var yearCPqList = [];
				    		var dataForecastPqList = [];
				    		var personForecastPqList = [];
				    		var yearDPqList = [];
				    		for(var i in rows){
				    			if(i < 12){
					    			yearAPqList.push((rows[i].yearAPq == null || rows[i].yearAPq == ''? '' : rows[i].yearAPq));
					    			yearBPqList.push((rows[i].yearBPq == null || rows[i].yearBPq == ''? '' : rows[i].yearBPq));
					    			yearCPqList.push((rows[i].yearCPq == null || rows[i].yearCPq == ''? '' : rows[i].yearCPq));
					    			dataForecastPqList.push((rows[i].dataForecastPq == null || rows[i].dataForecastPq == ''? '' : rows[i].dataForecastPq));
					    			personForecastPqList.push((rows[i].personForecastPq == null || rows[i].personForecastPq == ''? '' : rows[i].personForecastPq));
					    			yearDPqList.push((rows[i].yearDPq == null || rows[i].yearDPq == ''? '' : rows[i].yearDPq));
				    			}
				    		}
				    		me.charts.text = text;
				    		me.charts.legendData = legendData;
				    		me.charts.xAxisData = xAxisData;
				    		me.charts.firstTitle = firstTitle;
				    		me.charts.secondTitle = secondTitle;
				    		me.charts.thirdTitle = thirdTitle;
				    		me.charts.fourTitle = fourTitle;
				    		me.charts.lastTitle = lastTitle;
				    		me.charts.nowTitle = nowTitle;
				    		
				    		me.charts.firstChartsData = yearAPqList;
				    		me.charts.secondChartsData = yearBPqList;
				    		me.charts.thirdChartsData = yearCPqList;
				    		me.charts.fourChartsData = dataForecastPqList;
				    		me.charts.lastChartsData = personForecastPqList;
				    		me.charts.nowChartsData = yearDPqList;
				    		//me.initEcharts();
				    		//生成图形
					    	me.initLine(text, 
					    			    legendData, 
					    			    xAxisData, 
					    			    yearAPqList, 
					    			    yearBPqList,
					    			    yearCPqList, 
					    			    dataForecastPqList,
					    			    personForecastPqList,
					    			    yearDPqList);
				    		
							$('#eleAnalysisAddGrid').datagrid('loadData', rows);
						}
					}
					
				});
				if(me.consIdFlag){
					$.ajax({
						url:basePath+"scElectricityInfoController/getConsInfo",
						type:"post",
						data:$.toJSON(params),
		                contentType : 'application/json;charset=UTF-8',
						success:function(data){
							console.log(data);
							if(data.flag){
								if(data.data != null){
									me.params.scElectricityInfoDeatil.consName = data.data.consName;
									me.params.scElectricityInfoDeatil.lodeAttrCode = data.data.lodeAttrCode;
									me.params.scElectricityInfoDeatil.elecTypeCode = data.data.elecTypeCode;
									me.params.scElectricityInfoDeatil.industryType = data.data.industryType;
								}
							}
						}
					});
				}
				
			}
       	},
       	selectCons:function(){
 			var me = this;
 			MainFrameUtil.openDialog({
	  			id:'consDialog',
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
				iframe:true,
				modal:true,
				width:'50%',
				height:620,
				onClose:function(params){
					if(typeof(params[0])==="object"){
						me.consInfo = params[0];
						me.params.scElectricityInfoDeatil.consId = params[0].id;
					}
				}
			});
 		}

	},
	watch:{
		'params.scElectricityInfoDeatil.consId':function(){
			var me = this;
			me.yearFlag = true;
			me.consIdFlag = true;
			me.queryList();
		},
		'params.scElectricityInfoDeatil.year':function(){
			var me = this;
			if(me.yearFlag){
				me.consIdFlag = false;
				me.year = me.params.scElectricityInfoDeatil.year;
				me.queryList();
				
			}
		}
	}
}); 
</script>
</body>
</html>