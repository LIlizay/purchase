<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-售电用户负荷预测详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<%-- <script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts1.js"></script> --%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
</head>
<body  >
<div id="eleAnalysisAddVue" class="height-fill" v-cloak>
	<mk-search-panel title="客户售电合同电量分析" deployable="false">
		<!-- 客户名称 -->
		<su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
                :class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
            <mk-trigger-text disabled="disabled" :data.sync="params.scElectricityInfoDeatil.consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text>
        </su-form-group>
		
		<!-- 产业分类 -->
<!--         <su-form-group :label-name="formFields.lodeAttrCode.label" class="form-control-row "  col="4"  -->
<!--         		:class="{'display-none':!formFields.lodeAttrCode.searchHidden}" label-width="100px" label-align="right"> -->
<!--             <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scElectricityInfoDeatil.lodeAttrCode" -->
<%-- 				url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_lodeAttrCode" disabled="true"></su-select> --%>
<!--         </su-form-group>  -->
        
		<!-- 用电类别 -->
        <su-form-group :label-name="formFields.elecTypeCode.label" class="form-control-row "  col="4" 
        		:class="{'display-none':!formFields.elecTypeCode.searchHidden}" label-width="100px" label-align="right">
            <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scElectricityInfoDeatil.elecTypeCode"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" disabled="true"></su-select>
        </su-form-group> 
        
		<!-- 用电行业分类 -->
        <su-form-group :label-name="formFields.industryType.label" class="form-control-row" col="4" 
        		:class="{'display-none':!formFields.industryType.searchHidden}" label-width="100px" label-align="right">
        	<su-select disabled="true" clear-btn="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.scElectricityInfoDeatil.industryType"
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
        </div>
		<div id="line" class="col-xs-12 height-fill"></div>
    </mk-panel>
    
    <mk-panel title="电力用户信息分月列表" line="true" height="450px">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
<!-- 	        <su-button class="btn-operator" s-type="default" labeled="true" v-on:click="calculatePq">测算</su-button> -->
	    </div>
        <div id="eleAnalysisAddGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
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
		formFields:{},
		yearFlag:false,
		consIdFlag:false,
		consInfo:null,
		char1:null,
		maxYear:"",
		minYear:"",
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
		var me = this;
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.crm.conselecquery", this);
	    me.initYear();
	    me.ineleAnalysisAddGrid();
	    me.initTitle();
	    me.params.scElectricityInfoDeatil.consId = MainFrameUtil.getParams().consId;
	    //初始化按钮
		MainFrameUtil.setDialogButtons([
		    {text:"关闭", handler:function(){MainFrameUtil.closeDialog()}}
		]);
	},
	methods: {
		
		initTitle:function(){
			var me = this;
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearAPq').title = (parseInt(me.year)-3)+"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearBPq').title = (parseInt(me.year)-2)+"年月度总用电量<br>（兆瓦时）";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'yearCPq').title = (parseInt(me.year)-1)+"年月度总用电量<br>（兆瓦时）";
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
			    	var rows = $('#eleAnalysisAddGrid').datagrid('getRows');
					var sum = 0;
					for(var i = 0; i<13; i++){
						if(i != 12){
							if(rows[i].personForecastPq != null && rows[i].personForecastPq != ''){									
								sum = sum + parseInt(rows[i].personForecastPq);
							}
						}else{
							$('#eleAnalysisAddGrid').datagrid('updateRow', {
								index:i,
								row:{personForecastPq:sum}
							});
						}
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
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			        {field:'yearDPq',title:"当年电量",width:150,align:'center'},
			    ]]
			});
		},
		initLine:function(title, legendData, xAxisData, firstData, secondData, thirdData,fourData,lastData, nowData){
			 var list = [firstData,secondData,thirdData,fourData,lastData,nowData];
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

//	  			    yAxis : [
//	    			            {name : '兆瓦时',type : 'value'},
//	    			         	{name : '%',type : 'value'},
//	    			    ],
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
		//生成图形
// 		initLine:function(title, legendData, xAxisData, firstData, secondData, thirdData,fourData,lastData){
// /* 			var index = 0;//自定义series的位置
// 			var list = new Array();//图形数据\
// 			legendData = ["2014","2015"];
//  */			var list = [firstData,secondData,thirdData,fourData,lastData];
// 			//设置图形标题
// 			var lineTitle = textContent.getTitle();
// 			lineTitle.text = title;
// 			textContent.setContObject("title",lineTitle);
// 			//设置图列
// 			var lineLegend = textContent.getLegend();
// 			lineLegend.data = legendData;
// 			lineLegend.x = "20%";
// 			textContent.setContObject("legend",lineLegend);
// 			//设置xAxis
// 			var linexAxis = textContent.getxAxis();
// 			linexAxis.data = xAxisData;
// 			textContent.setContObject("xAxis",linexAxis);
// 			//设置网格
// 			var lineGrid = textContent.getGrid();
// 			lineGrid.width = "90%";
// 			lineGrid.height = 320;
// 			textContent.setContObject("grid",lineGrid);
// 			//设置series
// 			 var lineSeries = textContent.getSeries();
// 			lineSeries.lineStyle.normal.type = "dotted";
// 			textContent.setContObject("series",[4,3,lineSeries]);
// 			textContent.setContObject("series",[4,4,lineSeries]);
// 			//设置数据并生成图形
// 			var lineObj = textContent.getContObject();
// 			var dom = "line";
// 			var lineOptionData = list;
// 			textContent.getEcharts("line",null,null,lineObj,dom,lineOptionData);
// 		},
		
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
       	queryList:function(){
       		var me = this;
			if(me.params.scElectricityInfoDeatil.year != null && me.params.scElectricityInfoDeatil.year != ""){
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
       	}

	},
	watch:{
		'params.scElectricityInfoDeatil.consId':function(){
			var me = this;
			me.yearFlag = true;
			me.consIdFlag = true;
			me.queryList();
		},
	}
}); 
</script>
</body>
</html>