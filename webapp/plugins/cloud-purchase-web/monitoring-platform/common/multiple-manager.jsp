<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
	<head>
		<title>监控平台-综合电量</title>
		<%@include file="scheader.jsp"%>
		<%@include file="../../../business-core/static/headers/echarts.jsp"%>
	</head>
<body>
	<%-- <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include> --%>
	<div id="userManager" class="height-fill" v-cloak>
		<mk-top-bottom height="100%" top_height="auto">
			<mk-search-panel height="50px" deployable="false" slot="top">
			    <su-form-group   label-name="年月 ："  class="form-control-row" col="4" label-width="118px" label-align="right">
				  	<su-datepicker name="ccrq" :z-index="999999" id="ccrq61549_id" format="YYYY-MM"  :data.sync="params.month" ></su-datepicker>
				</su-form-group>
				
				<su-form-group   label-name="用电类别："  class="form-control-row" col="4" label-width="118px" label-align="right">
				  	<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.elecTypeCode"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" name="elecTypeCode"></su-select>
				</su-form-group>
				
				<su-form-group   label-name="电压等级："  class="form-control-row" col="4" label-width="118px" label-align="right">
				  	<su-select placeholder="请先选择用电类别" label-name="name" :select-value.sync="params.voltCode"
							:data-source.sync="voltCodeData" name="voltCode"></su-select>
				</su-form-group>
				<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
					<su-button s-type="primary"  @click="search" class="btn-width-small">查询</su-button>
					<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
				</div>
			</mk-search-panel>
			<mk-panel title="电量信息" line="true" slot="bottom" height="130px">
				<mk-form-panel  num="3">
					<mk-form-row>
						<mk-form-col label="本月成交电量">
							<su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" :data.sync="formData.purchasePq"></su-textbox>
				        </mk-form-col>
				        <mk-form-col label="本月已用总电量">
							<su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" :data.sync="formData.powerTotal"></su-textbox>
				        </mk-form-col>
				        <mk-form-col label="总偏差率">
							<su-textbox :addon="{'show':true,'rightcontent':'%'}" disabled="true" :data.sync="formData.deviationRateTotal"></su-textbox>
				        </mk-form-col>
					</mk-form-row>
					<mk-form-row>
						<mk-form-col label="本月代理总电量">
							<su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" :data.sync="formData.planPower"></su-textbox>
				        </mk-form-col>
						<mk-form-col label="预测全月电量">
							<su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" :data.sync="formData.forecastPq"></su-textbox>
				        </mk-form-col>
				        <mk-form-col label="预测偏差率">
							<su-textbox :addon="{'show':true,'rightcontent':'%'}" disabled="true" :data.sync="formData.forecastRate"></su-textbox>
				        </mk-form-col>		
					</mk-form-row>
				</mk-form-panel>
			</mk-panel>	
			<mk-panel title="电量计划/使用对照" line="true" slot="bottom" height="70%" ">
				<ul _v-8ec06e50 class="nav nav-tabs su-tabs" id="tabs">
					<li :class=[pieLineEchartFlag==2?'active':''] _v-8ec06e50 data-bus='kindLineDay'>
						<a _v-8ec06e50 @click="changeBus('2')">曲线图(日用电量)</a>
					</li>
					<li :class="[pieLineEchartFlag==1?'active':'']" _v-8ec06e50 data-bus='kindLine'>
						<a _v-8ec06e50 @click="changeBus('1')">曲线图(累计电量)</a>
					</li>
					<li :class="[pieLineEchartFlag==0?'active':'']" _v-8ec06e50 data-bus='pieChart'>
						<a  _v-8ec06e50 @click="changeBus('0')">饼状图</a>
					</li>
				</ul>
				<div _v-8ec06e50 class="tab-content">
					<div _v-8ec06e50 class="fadein-transition">
						<div id="pieChart" style="width:100%; height: 100%;"></div>
					</div>
				</div>
			</mk-panel>
			<mk-panel title="电量明细（电量单位：兆瓦时）" line="true" slot="bottom" height="80%">
				<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		            <su-button class="btn-operator fa fa-share-square-o" s-type="default" labeled="true" v-on:click="exportData">导出</su-button>
		        </div>
		        <div id="tableGrid"></div>
			</mk-panel>
		</mk-top-bottom>
	</div>
<script>
	$(window).resize(throll(function(){
	 if(
			 formVue.myChart2!=null&&
		   $("#userManager").height()>60&&
		   $("#userManager").width()>100){
		 	formVue.myChart2.resize();
		}
	 if(
			 formVue.myChart3!=null&&
		   $("#userManager").height()>60&&
		   $("#userManager").width()>100){
		 	formVue.myChart3.resize();
		}
	},100));
	function throll(fn,delay){
	    var timer=null;
	    return function(){
	        clearTimeout(timer);
	        timer=setTimeout(fn,delay);
	    }
	}  
	
	var basePath = "${Config.baseURL}";
	var formVue = new Vue({
		el: 'body',
		data: {
			voltCodeData:[],		//电压等级码表
			voltCodeUrl : "", // 电压等级url
			myChart2:null,
			myChart3:null,
			pieLineEchartFlag:2,
			formData:{purchasePq :0,planPower :0,powerTotal :0, deviationRateTotal: 0,forecastPq:0,forecastRate:0},		//当月综合电量数据
			
			//planPower: 0,		//当月申报电量
			powerTotal:'',
			params: {
				month: '',		//例：2018-03
				elecTypeCode: '',
				voltCode: '',
				formData: {
					page: 1,
					rows: 10
				}
			},
			lineData: [],
			pieData: null
		},
		ready: function() {
			var main = this;
			var today = new Date();
			var date = today.getFullYear() + "-" +(today.getMonth() > 8 ? today.getMonth()+1 : "0" + (today.getMonth()+1)) + "-" 
						+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate());		//yyyy-MM-dd格式
			main.params.month = date.substring(0,date.length-3); //例：2018-03
			main.getClient();
			main.initTableGrid();
			main.getTotalData();
		},
		methods: {
			//获取当月综合电量数据
			getTotalData: function(){
				var main = this;
				var queryParameters = {
						ym: main.params.month,
						elecTypeCode: main.params.elecTypeCode,
						voltCode: main.params.voltCode
					};
				$.ajax({
		   			url: '${Config.baseURL}/smConsPowerViewController/getTotalPqDataByParams',
		            method:'post',
		            contentType : 'application/json;charset=UTF-8',
		            data: $.toJSON(queryParameters),
		            success:function(data){
		                if(data !== null && data.flag){
			                main.formData.purchasePq = parseFloat(data.data.purchasePq).toFixed(3);
			                main.formData.forecastPq = parseFloat(data.data.forecastPq == null ? 0 : data.data.forecastPq);
							var planPower = parseFloat(data.data.planPqTotal == null ? 0 : data.data.planPqTotal);
			                //预测全月用电量-代理电量）/代理电量*100%
			                if(planPower != 0){
				                main.formData.forecastRate = ((parseFloat(main.formData.forecastPq) - parseFloat(planPower))/planPower * 100).toFixed(2);
			                }
							var powerTotal = parseFloat(data.data.actualPqTotal == null ? 0 : data.data.actualPqTotal);
							
							main.formData.deviationRateTotal = parseFloat(data.data.deviationRateTotal == null ? 0 : data.data.deviationRateTotal);
							
							main.formData.planPower = planPower.toFixed(3);
							main.formData.powerTotal = powerTotal.toFixed(3);
							if(main.pieLineEchartFlag == "0"){
								main.getPieChartsData();
							}
		           	    }else{
		           		   MainFrameUtil.alert({title:"提示",body:data.msg});
		           	    }
		            },
		            error:function(){
		           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
		            }
				});
			},
			//获取当前当前搜索条件下的用电情况
			getClient: function() {
				var main = this;
				var data = {
					ym: main.params.month,
					elecTypeCode: main.params.elecTypeCode,
					voltCode: main.params.voltCode
				};
				$.ajax({
					url: basePath + 'smConsPowerViewController/getDayPqByMonth',
					type: 'post',
					contentType : 'application/json;charset=UTF-8',
					data : $.toJSON(data),
					success: function(data) {
						if(data.flag){
							if(data.data != null && data.data.length > 0) {
								main.lineData = data.data;
								if(main.pieLineEchartFlag == "1"){
									main.kindLineEchart(main.lineData);
								}else if(main.pieLineEchartFlag == "2"){
									main.kindLineEchartDay(main.lineData);
								}
							} else {
								main.clearCharts();
								MainFrameUtil.alert({title: "操作提示",body: "本月无用电情况!"});
							}
						}else{
							MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						}
					},
					error: function() {
					}
				});
			},
			//查询
			search: function() {
				var main = this;
				main.getClient();
				main.getTotalData();
				var queryParameters = {
					ym: main.params.month,
					elecTypeCode: main.params.elecTypeCode,
					voltCode: main.params.voltCode
				};
				$('#tableGrid').datagrid("load",queryParameters);
			},
			//重置
			reset: function(){
				var main = this;
				var today = new Date();
				var date = today.getFullYear() + "-" +(today.getMonth() > 8 ? today.getMonth()+1 : "0" + (today.getMonth()+1)) + "-" 
							+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate());		//yyyy-MM-dd格式
				main.params.month = date.substring(0,date.length-3); //例：2018-03
				main.params.elecTypeCode = '';
				main.params.voltCode = '';
			},
			//创建表格信息
			initTableGrid: function() {
				var main = this;
				var queryParameters = {
					ym: main.params.month,
					elecTypeCode: main.params.elecTypeCode,
					voltCode: main.params.voltCode
				};
				$('#tableGrid').datagrid({
					url: basePath + 'smConsPowerViewController/getByMonth',
					method: 'post',
					height:'100%',
					striped: true,
					remoteSort: true,
					//默认单选
					singleSelect: true,
					//序号
					scrollbarSize: 0,
					rownumbers: true,
					pagination: true,
					pageNumber: main.params.formData.page,
					pageSize: main.params.formData.rows,
					fitColumns: true,
					loadMsg: "正在加载数据",
					queryParams: queryParameters,
					columns:[[
							{field:'consName',title:'用户',width:80,align: "center"},
							{field:'elecTypeName',title:'用电类别',width:60,align:'center'},
							{field:'voltCodeName',title:'电压等级',width:60,align:'center'},
							{field:'date',title:'起止日期',width:80,align:'center',
								formatter: function(value,row,index){
									if(row.minDate == null && row.maxDate == null){
										return "";
									}
									return row.minDate + "--" + row.maxDate;
								}},
							{field:'planPq',title:'代理电量<br>(兆瓦时)',width:60,align:'center'},
							{field:'planPqToday',title:'计划累计用电量<br>(兆瓦时)',width:60,align:'center'},
							{field:'actualPq',title:'累计用电量<br>(兆瓦时)',width:60,align:'center'},
							/* {field:'completeRate',title:'完成度',width:60,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != ""){
										return value+"%";
									}
								}}, */
							{field:'deviationRate',title:'偏差率',width:60,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != ""){
										return value+"%";
									}
								}
							},
							{field:'forecastPq',title:'预测全月用电量<br>(兆瓦时)',width:60,align:'center'},
							{field:'forecastRate',title:'预测偏差率',width:60,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != ""){
										return value+"%";
									}
								}
							},
						]],
					pageList: [10, 20, 30, 40, 50],
					onLoadSuccess:function(data){
						if(data.rows.length > 1){
				    		//合并单元格
					    	var rows = data.rows;
							$('#tableGrid').datagrid('mergeCells',{index: data.rows.length-1,field:"consName",rowspan:0,colspan:4});
				    	}
					}
				});
			},
			//获取饼状图信息
			getPieChartsData: function() {
				var main = this;
				//计划电量总数
				var planPower = parseFloat(parseFloat(main.formData.planPower).toFixed(3));
				//使用电量总数
				var powerTotal =  parseFloat(parseFloat(main.formData.powerTotal).toFixed(3));
				
				//标签数据
				var pieChartData = [];
				//里圈饼状图数据来源
				var valueData1 = [];
				//外圈饼状图数据来源
				var valueData2 = [];
				//饼图颜色
				var colors = null;
				if(planPower > powerTotal) {
					pieChartData = ['已用电量(兆瓦时)', '未用电量(兆瓦时)', '代理总电量(兆瓦时)'];
					var ispower = {
						value: powerTotal,
						name: '已用电量(兆瓦时)',
						selected: true
					};
					var nopower = {
						value: (planPower - powerTotal).toFixed(3),
						name: '未用电量(兆瓦时)'
					};
					var planValue = {
						value: planPower,
						name: '代理总电量(兆瓦时)'
					};
					valueData1.push(ispower);
					valueData1.push(nopower);
					//valueData2.push(planValue);
					colors = ['rgb(80,130,156)','rgb(129,185,190)'];
				} else if(planPower == powerTotal) {
					pieChartData = ['已用电量(兆瓦时)', '代理总电量(兆瓦时)'];
					var ispower = {
						value: powerTotal,
						name: '已用电量(兆瓦时)',
						selected: true
					};
					var planValue = {
						value: planPower,
						name: '代理总电量(兆瓦时)'
					};
					valueData1.push(ispower);
					//valueData2.push(planValue);
					colors = ['rgb(80,130,156)'];
				} else if(planPower < powerTotal) {
					pieChartData = ['代理总电量(兆瓦时)', '已超电量(兆瓦时)', '已用电量(兆瓦时)'];
					var planValue = {
						value: planPower,
						name: '代理总电量(兆瓦时)'
					};
					var isValue = {
						value: (powerTotal - planPower).toFixed(3),
						name: '已超电量(兆瓦时)',
						selected: true
					};
					var Value = {
						value: powerTotal,
						name: ''
					};
					valueData1.push(planValue);
					valueData2.push(isValue);
					valueData2.push(Value);
					colors = ['rgb(80,130,156)','rgb(221,91,92)','#fff'];
				}
				main.canvasData(pieChartData, valueData1, valueData2,colors);
			},
			//处理饼状图显示细节
			canvasData: function(pieChartData, valueData1, valueData2,colors) {
				var main = this;
				main.myChart2 = echarts.init(document.getElementById('pieChart'));
				main.myChart2.clear();
				option = {
					tooltip: {
						trigger: 'item',
						formatter: "{a} <br/>{b} : {c} ({d}%)"
					},
					legend: {
						orient: 'vertical',
						x: '80%',
						y:'60%',
						data: pieChartData
					},
					toolbox: {
						show: true,
						feature : {
				            mark : {show: false},
				            dataView : {show: false, readOnly: false},
				            magicType : {
				                show: true, 
				                type: ['pie', 'funnel']
				            },
				            restore : {show: false},
				            saveAsImage : {show: false}
				        }
					},
					calculable: false,
					series: [{
							name: '电量数据',
							type: 'pie',
							selectedMode: 'single',
							radius: [0, 70],
							 center : ['49.5%', '46%'],
							// for funnel
							x: '23%',
							width: '40%',
							funnelAlign: 'right',
							max: 1548,

							itemStyle: {
								normal: {
									label: {
										position: 'inner'
									},
									labelLine: {
										show: false
									}
								}
							},
							data: valueData1
						},
						{
							name: '电量数据',
							type: 'pie',
							radius: [110, 90],
							 center : ['50%', '44%'],
							// for funnel
							x: '60%',
							width: '35%',
							funnelAlign: 'left',
							max: 1048,

							data: valueData2
						}
					]
				}
				if(colors != null ){
					option.color = colors;
				}
				main.myChart2.setOption(option);
			},
			//饼状图，曲线图标签跳转
			changeBus: function(num) {
				var main = this;
				main.pieLineEchartFlag = num;
				if(num == "0"){
					main.getPieChartsData();
				}else if(num == "1"){
					main.kindLineEchart(main.lineData);
				}else if(num == "2"){
					main.kindLineEchartDay(main.lineData);
				}
			},
			//获取日用电量曲线图数据
			kindLineEchartDay: function(data) {
				var main = this;
				//获取当前月份天数
				var xData = [];
				//Echart 触碰显示的   mm-dd
				var tootopVlaue = [];
				var monthDays = data.length;
				var isvalueData = [];	//实际用电量（累计用电量）
				var planValueData = [];	//计划用电量
				for(var i = 0; i < monthDays; i++){
					var month = data[i].ymd.substring(4,6);
					var day = data[i].ymd.substr(6,2);
					xData.push(day+'日');	//x轴数据
					planValueData.push(data[i].dayPlanPq);	//计划用电量
					isvalueData.push(data[i].dayActualPq);
					tootopVlaue.push(month + '-' + day);//MM-DD
				}
				main.handleKindLine(xData, tootopVlaue, planValueData, isvalueData);
			},
			//获取累计用电量曲线图数据
			kindLineEchart: function(data) {
				var main = this;
				//获取当前月份天数
				var xData = [];
				var monthDays = data.length;
				//Echart 触碰显示的   mm-dd
				var tootopVlaue = [];
				
				var isvalueData = [];	//实际用电量（累计用电量）
				var planValueData = [];	//计划用电量
				for(var i = 0; i < monthDays; i++){
					var month = data[i].ymd.substring(4,6);
					var day = data[i].ymd.substr(6,2);//tootip显示值
					xData.push(day+'日');	//x轴数据
					planValueData.push(data[i].planPq);	//计划用电量
					isvalueData.push(data[i].actualPq);
					tootopVlaue.push(month + '-' + day);//MM-DD
				}
				main.handleKindLine(xData, tootopVlaue, planValueData, isvalueData);
			},
			//处理曲线图信息，用于展示
			handleKindLine: function(xData, tootopVlaue, planValueData, isvalueData) {
				var main = this;
				main.myChart3 = echarts.init(document.getElementById('pieChart'));
				main.myChart3.clear();
				var option = {
					title: {
						text: '本月电量变化',
						subtext: '电量数据'
					},
					tooltip: {
						trigger: 'axis',
						formatter: function (params, ticket, callback) {
                            //x轴名称 
                            var name = params[0].name;
                            var seriesName = "";
                        	for(var j in params){
                        		if(params[j].value == undefined){
                        			params[j].value = 0;
                        		}
                    			seriesName += params[j].seriesName + ':' +params[j].value + '<br />';
                        	}
                        	if(name == ''){
                        		name = '';
                        	}else{
                        		name = '-'+name;
                        	}
                        	var date = main.params.month.substring(5,main.params.month.length);
                        	return tootopVlaue[params[0].dataIndex] +'<br />' + seriesName;
                        }
					},
					legend: {
						data: ['计划用电量(兆瓦时)', '已用总电量(兆瓦时)']
					},
					toolbox: {
						show: true,
						feature: {
							mark: {
								show: false
							},
							dataView: {
								show: false,
								readOnly: false
							},
							magicType: {
								show: false,
								type: ['line', 'bar']
							},
							restore: {
								show: false
							},
							saveAsImage: {
								show: false
							}
						}
					},
					calculable: true,
					xAxis: [{
						type: 'category',
						boundaryGap: false,
						data: xData
					}],
					yAxis: [{
						name : '单位：兆瓦时',type: 'value',
					}],
					series: [{
							name: '计划用电量(兆瓦时)',
							type: 'line',
							data: planValueData,
							markPoint: {
								data: [{
										/*type: 'max',
										name: '最大值'*/
									},
									{
										/*type: 'min',
										name: '最小值'*/
									}
								]
							},
							markLine: {
								data: [

								]
							}
						},
						{
							name: '已用总电量(兆瓦时)',
							type: 'line',
							data: isvalueData,
							markPoint: {
								data: [{
										/*type: 'max',
										name: '最大值'*/
									},
									{
										/*type: 'min',
										name: '最小值'*/
									}
								]
							},
							markLine: {
								data: [

								]
							}
						}
					]
				};
				main.myChart3.setOption(option);
			},
			clearCharts: function() {
				var main = this;
				
				//清空饼状图数据
				var myChart1 = echarts.init(document.getElementById('pieChart'));
				if(main.pieLineEchartFlag == "0"){
					main.getPieChartsData([]);
				}else if(main.pieLineEchartFlag == "1"){
					main.kindLineEchart([]);
				}else if(main.pieLineEchartFlag == "2"){
					main.kindLineEchartDay([]);
				}
				main.lineData=[];
				//清空饼状图数据
// 				main.myChart2 = echarts.init(document.getElementById('pieChart'));
//  				main.myChart2.clear();
			},
			exportData: function() {
				var me = this;
				var url = basePath + "smConsPowerViewController/exportExcel?month=" + me.params.month + '&elecTypeCode=' + me.params.elecTypeCode + "&voltCode=" + me.params.voltCode ;
				 location.href = url;
			},
	 		getVoltCode: function(url){
	 			var me = this;
	 			$.ajax({
	 				url:url,
	 				type:"get",
	 				success:function(data){
	 					me.voltCodeData = data;
	 				}
	 			});
	 		}
		},
		watch : {
			"params.elecTypeCode":function(value){
				var me = this;
				if(value == "100" || value == "400"){
					me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&pCode.content5=" + value;
					me.getVoltCode(me.voltCodeUrl);
					me.params.voltCode = "";
				}else{
					me.voltCodeUrl = "";
					me.voltCodeData = [];
					me.params.voltCode = "";
				}
			}
		}
	});
</script>
</body>
</html>