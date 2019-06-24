<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <html>
 	<head>
 		<title>监控平台-用户电量</title>
	<%@include file="scheader.jsp"%>
	<%@include file="../../../business-core/static/headers/echarts.jsp"%>
 	</head>
<body>
	<div id="userManager"  class="height-fill">
		<mk-left-right left_width="250px" height="100%" >
			<mk-panel header='false' slot="left" height="99%"  style="overflow-y: auto;">
				<ul id="user-tree" style="margin: 2% auto 2% auto;height:100%;overflow-y: auto;"></ul>
		    </mk-panel>
		    <mk-top-bottom slot="right"  height="100%" top_height="auto" style="padding-left:5px" >
					<mk-search-panel deployable="false" slot="top" height="50px">
					    <su-form-group   label-name="年月:"  class="form-control-row "  col="5" label-width="118px" label-align="right">
						  	<su-datepicker name="ccrq" :z-index="999999" id="ccrq61549_id" format="YYYY-MM"  :data.sync="params.month" ></su-datepicker>
						</su-form-group>
						<su-form-group label-name='是否抄表例日展示:'  class="form-control-row " col="5" label-width="118px" label-align="right">
		                    <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.usuallyDateFlag"
										url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag"></su-select>
		                </su-form-group>
						<!-- <su-form-group label-name="" class="form-control-row"  col="3" label-align="right">
							<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
					            <su-button s-type="primary"  @click="search" class="btn-width-small">查询</su-button>
				       		</div>
					    </su-form-group> -->
					</mk-search-panel>
					<div  slot="bottom" style="overflow-y: auto;height:100%;">
						<mk-panel title="电量计划/使用对照" line="true" slot="bottom" height="60%" >
							<ul _v-8ec06e50 class="nav nav-tabs su-tabs" id="tabs">
								<li :class=[flag==2?'active':''] _v-8ec06e50 data-bus='kindLineDay'>
									<a _v-8ec06e50 @click="changeBus('2')">曲线图(日用电量)</a>
								</li>
								<li :class=[flag==1?'active':''] _v-8ec06e50 data-bus='kindLine'>
									<a _v-8ec06e50 @click="changeBus('1')">曲线图(累计电量)</a>
								</li>
								<li :class=[flag==0?'active':''] _v-8ec06e50 data-bus='pieChart'>
									<a  _v-8ec06e50 @click="changeBus('0')">饼状图</a>
								</li>
							</ul>
							<div _v-8ec06e50 class="tab-content">
								<div _v-8ec06e50 class="fadein-transition">
									<div id="pieChart" style="width:100%; height: 100%"></div>
								</div>
							</div>
						</mk-panel>
						<mk-panel title="电量明细（电量单位：兆瓦时）" line="true" slot="bottom" height="80%">
							<!-- <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
					            <su-button class="btn-operator fa fa-share-square-o" s-type="default" labeled="true" v-on:click="exportData">导出</su-button>
					        </div> -->
					        <div id="powerDetailGrid"></div>
						</mk-panel>
					</div>
		    </mk-top-bottom>
	    </mk-left-right>
	</div>
<script>
	var basePath = "${Config.baseURL}";
	var formVue = new Vue({
		el: 'body',
		data: {
			//这里的url应该只查询合同用户（根据搜索条件中年月查询此年月时的合同用户）
			url: basePath + 'view/cloud-purchase-web/monitoring-platform/common/cons-dialog-selectbox',
			flag:2,
			planPower:'0',
			params: {
				month:'',
				consId:'',
				usuallyDateFlag: '',
				formData: {
					page:1,
					rows:10
				}
			},
			pieData: []
		},
		ready: function() {
			var main = this;
			//是否抄表例日正式 默认为是
			main.params.usuallyDateFlag = '1';
			var today = new Date();
			var date = today.getFullYear() + "-" +(today.getMonth() > 8 ? today.getMonth()+1 : "0" + (today.getMonth()+1)) + "-" 
						+ (today.getDate() > 9 ? today.getDate() : "0" + today.getDate());		//yyyy-MM-dd格式
			main.params.month = date.substring(0,date.length-3);
			
			main.initUserTree();//初始化用户树
		},
		methods: {
			 //初始化树
			 initUserTree: function(){
				 var me = this;
				 //$('#user-tree').tree();
				 var tree = {
						 id:"",
						 text:"用户",
						 children:[],
						 state:"open",
						 type:"0"
				 }
				 $.ajax({
						url: basePath +'cloud-purchase-web/sConsDialogController/getConsListForMonitor',
						method: 'post',
						data:$.toJSON({"pagingFlag":false,"smppaYm": me.params.month}),
		                contentType : 'application/json;charset=UTF-8',
		                success : function(data) {
		                	if(data.flag == true){
		                		for(var i=0;i<data.rows.length;i++){
		 	                	   var json={"text":data.rows[i].consName,"checked":false,"consId":data.rows[i].id, "id":data.rows[i].id};
		 	                	   tree.children.push(json);
		 	                	}
		 	                	$('#user-tree').tree({
		 	    				 	data: [tree],
		 	    				 	onLoadSuccess: function(node, data){
		 	    				 		if(data != null && data[0].children[0] != null){
			 	    						// 查找一个节点并选择它
			 	    						var node = $('#user-tree').tree('find', data[0].children[0].id);
			 	    						if(node.target != null){
				 	    						$('#user-tree').tree('select', node.target);
				 	    						var node = $('#user-tree').tree('getSelected');
				 	    						me.params.consId = node.id;//用户id
			 	    						}
		 	    				 		}
		 	    				 	},
		 	    				 	onClick:function(node){
			 	  						 if(node.consId == null || node.consId == ''){
			 	  							 return;
			 	  						 }
			 	  						 me.params.consId = node.consId;
			 	  						 me.search();				
			 	  					},
			 	  					 
		 	    				});
		                	}else{
		                		MainFrameUtil.alert({title:"提示",body:"选择用户树，数据查询失败！"});
		                	}
		                },
		                error : function() {
		                	MainFrameUtil.alert({title:"失败提示",body:"网络连接错误,请刷新页面重试"});
		                }
		         });
			 },			
			//获取当前用户的当前月份用电情况
			search: function(){
				var main = this;
				var data = {
					ym : main.params.month.replace("-",""),
					consId : main.params.consId,
					usuallyDateFlag : main.params.usuallyDateFlag
				}
				$.ajax({
					url : basePath + 'smConsPowerViewController/getByUser',
					type : 'post',
					contentType : 'application/json;charset=UTF-8',
					data : $.toJSON(data),
					success : function(data){
						if(data.flag){
							main.pieData = data.data;
							main.planPower = 0;
							if(data.data.length > 0){
								//直接拿最后一条的累积计划电量当作用户本月的总计划电量，饼图用
								main.planPower = data.data[data.data.length-1].planPq;
								if(main.flag == "0"){
									main.getPieChartsData(data.data);
								}else if(main.flag == "1"){
									main.kindLineEchart(data.data);
								}else if(main.flag == "2"){
									main.kindLineEchartDay(data.data);
								}
							}else{
								MainFrameUtil.alert({
					            	title:"操作提示",
					            	body:"此用户本月无用电情况!"
						        });
								main.clearCharts();
							}
						main.initDatagrid(data.data);
						}else{
							MainFrameUtil.alert({title:"失败提示",body:"请刷新页面重试!"});
						}
					},
					error : function() {
					}
				});
			},
			//获取饼状图信息
			getPieChartsData: function(data){
				var main = this;
				//计划电量总数
				var planPower = main.planPower;
				if(planPower == null || planPower == ""){
					for(var i in data) {
						if(!isNaN(parseFloat(data[i].planPq))){
							if(planPower < parseFloat(data[i].planPq)){
								planPower = parseFloat(data[i].planPq)
							}
						}
					}
				}
				//这个月的累积用电量
				var powerTotal = 0;
				for(var i in data) {
					if(!isNaN(parseFloat(data[i].actualPq))){
						if(powerTotal < parseFloat(data[i].actualPq)){
							powerTotal = parseFloat(data[i].actualPq)
						}
					}
				}
				var pieChartData = [];
				//里圈饼状图数据来源
				var valueData1 = [];
				//外圈饼状图数据来源
				var valueData2 = [];
				//饼图颜色
				var colors = null;
				if(planPower > powerTotal){	//计划电量大于计划用电量
					pieChartData = ['已用电量(兆瓦时)','未用电量(兆瓦时)'];//,'总电量'
					var ispower = {value: powerTotal,name:'已用电量(兆瓦时)',selected:true};
					var nopower = {value: planPower-powerTotal,name:'未用电量(兆瓦时)'};
					valueData1.push(ispower);
					valueData1.push(nopower);
					colors = ['rgb(80,130,156)','rgb(129,185,190)'];
				}else if(planPower == powerTotal){	//计划电量等于计划用电量
					pieChartData = ['已用电量(兆瓦时)'];	//,'总电量'
					var ispower = {value: powerTotal,name:'已用电量(兆瓦时)',selected:true};
					valueData1.push(ispower);
					colors = ['rgb(80,130,156)'];
				}else if(planPower < powerTotal){	//计划电量小于已用电量
					pieChartData = ['总电量(兆瓦时)','已超电量(兆瓦时)'];
					
					if(powerTotal == null || powerTotal == ''){
						var planValue = {value: '',name:'总电量(兆瓦时)'};
						var isValue = {value: '',name:'已超电量(兆瓦时)',selected:true};
						var Value = {value: ''};
					}else{
						var planValue = {value: planPower,name:'总电量(兆瓦时)'};
						var isValue = {value: powerTotal-planPower,name:'已超电量(兆瓦时)',selected:true};
						var Value = {value: powerTotal};
						valueData1.push(planValue);
					}
					valueData2.push(isValue);
					valueData2.push(Value);
					colors = ['rgb(80,130,156)','rgb(221,91,92)','#fff'];
				}
				
				main.canvasData(pieChartData,valueData1,valueData2,colors);
			},
			//处理饼状图显示细节
			canvasData: function(pieChartData,valueData1,valueData2,colors){
				var main = this;
				var myChart = echarts.init(document.getElementById('pieChart'));
				myChart.clear();
				var option = {
					 tooltip : {
					        trigger: 'item',
					        formatter: "{a} <br/>{b} : {c} ({d}%)"
					    },
					    legend: {
					        orient : 'vertical',
					        x: '80%',
							y:'60%',
					        data:pieChartData
					    },
					    toolbox: {
					        show : true,
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
					    calculable : false,
					    series : [
					        {
					            name:'电量数据',
					            type:'pie',
					            selectedMode: 'single',
					            radius : [0, 70],
					            center:['50%','45%'],
					            // for funnel
					            x: '20%',
					            width: '40%',
					            funnelAlign: 'right',
					            max: 1548,
					            
					            itemStyle : {
					                normal : {
					                    label : {
					                        position : 'inner'
					                    },
					                    labelLine : {
					                        show : false
					                    }
					                }
					            },
					            data:valueData1
					        },
					        {
					            name:'电量数据',
					            type:'pie',
					            radius: [100, 80],
					            center:['50%','45%'],
					            // for funnel
					            x: '60%',
					            width: '35%',
					            funnelAlign: 'left',
					            max: 1048,
					            
					            data:valueData2
					        }
					    ]
				}
				if(colors != null ){
					option.color = colors;
				}
				myChart.setOption(option);
			},
			//饼状图，曲线图 标签跳转
			changeBus: function(num){
				var main = this;
				main.flag = num;
				if(num == "0"){
					main.getPieChartsData(main.pieData);
				}else if(num == "1"){
					main.kindLineEchart(main.pieData);
				}else if(num == "2"){
					main.kindLineEchartDay(main.pieData);
				}
			},
			//获取日用电量曲线图数据
			kindLineEchartDay: function(data){
				var main = this;
				//获取当前月份天数
				var xData = [];
				var monthDays = data.length;
				
				var isvalueData = [];	//实际用电量（累计用电量）
				var planValueData = [];	//计划用电量
				for(var i = 0; i < monthDays; i++){
					var day = data[i].ymd.substr(6,2);
					xData.push(day+'日');	//x轴数据
					planValueData.push(data[i].dayPlanPq);	//计划用电量
					isvalueData.push(data[i].dayActualPq);
				}
				main.handleKindLine(xData,planValueData,isvalueData);
			},
			//获取曲线图数据
			kindLineEchart: function(data){
				var main = this;
				//获取当前月份天数
				var xData = [];
				var monthDays = data.length;
				
				var isvalueData = [];	//实际用电量（累计用电量）
				var planValueData = [];	//计划用电量
				for(var i = 0; i < monthDays; i++){
					var day = data[i].ymd.substr(6,2);
					xData.push(day+'日');	//x轴数据
					planValueData.push(data[i].planPq);	//计划用电量
					isvalueData.push(data[i].actualPq);
				}
				main.handleKindLine(xData,planValueData,isvalueData);
			},
			//处理曲线图信息，用于展示
			handleKindLine: function(xData,planValueData,isvalueData){
				var main = this;
				var myChart = echarts.init(document.getElementById('pieChart'));
				myChart.clear();
				var option = {
			        title : {
				        text: '本月电量变化',
				        subtext: '电量数据'
				    },
				    tooltip : {
				        trigger: 'axis',
				        formatter: function (params, ticket, callback) {
                            //x轴名称 
                            var index = params[0].dataIndex;
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
                        	var date = $('#powerDetailGrid').datagrid("getRows")[index].ymd.substr(4,2);
                        	return date +name +'<br />' + seriesName;
                        }
				    },
				    legend: {
				        data:['计划用电量(兆瓦时)','已用总电量(兆瓦时)']
				    },
				    toolbox: {
				        show : true,
				        feature : {
				            mark : {show: false},
				            dataView : {show: false, readOnly: false},
				            magicType : {show: false, type: ['line', 'bar']},
				            restore : {show: false},
				            saveAsImage : {show: false}
				        }
				    },
				    calculable : true,
				    xAxis : [
				        {
				            type : 'category',
				            boundaryGap : false,
				            data : xData
				        }
				    ],
				    yAxis : [
				        {
				        	name : '单位：兆瓦时',type : 'value',
				        }
				    ],
				    series : [
				        {
				            name:'计划用电量(兆瓦时)',
				            type:'line',
				            data:planValueData,
				            markPoint : {
				                data : [
				                   /* {type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}*/
				                ]
				            },
				            markLine : {
				                data : [
				                    
				                ]
				            }
				        },
				        {
				            name:'已用总电量(兆瓦时)',
				            type:'line',
				            data:isvalueData,
				            markPoint : {
				                data : [
				                    /*{type : 'max', name: '最大值'},
				                    {type : 'min', name: '最小值'}*/
				                ]
				            },
				            markLine : {
				                data : [
				                    
				                ]
				            }
				        }
				    ]
				};
				
				myChart.setOption(option);
			},
			clearCharts: function(){
				var main = this;
				main.pieData=[];
				//清空饼状图数据
				var myChart1 = echarts.init(document.getElementById('pieChart'));
				if(main.flag == "0"){
					main.getPieChartsData([]);
				}else if(main.flag == "1"){
					main.kindLineEchart([]);
				}else if(main.flag == "2"){
					main.kindLineEchartDay([]);
				}
				//var option = {};
				//清空曲线图数据
				//var myChart2 = echarts.init(document.getElementById('kindLine'));
				//myChart1.setOption(option);
				//myChart2.setOption(option);
			},
			initDatagrid: function(data){
				$('#powerDetailGrid').datagrid({
					 data: data,
					 width:"100%",
					 rownumbers: true,
					 nowrap: false,
					 singleSelect:true,
					 fitColumns:true,
					 columns:[[
							{field:'ymd',title:'日期',width:80,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != "" && value.length == 8){
										return value.substr(0,4) + "-" + value.substr(4,2) + "-" +value.substr(6,2);
									}
								}
							},
							{field:'dayPlanPq',title:'计划日用电量(兆瓦时)',width:80,align:'center'},
							{field:'dayActualPq',title:'日用电量(兆瓦时)',width:80,align:'center'},
							{field:'dayDeviationRate',title:'日偏差率',width:80,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != ""){
										return value+"%";
									}
								}
							},
							{field:'planPq',title:'计划累计电量(兆瓦时)',width:80,align:'center'},
							{field:'actualPq',title:'累积用电量(兆瓦时)',width:80,align:'center'},
							{field:'deviationRate',title:'总偏差率',width:80,align:'center',
								formatter: function(value,row,index){
									if (value != null && value != ""){
										return value+"%";
									}
								}
							}]]
				});
			},
			exportData:function(){
				var main = this;
				
				var excelParams = {
					fileName: "电量报表.xlsx", //导出excel名字
					sheetParamsList: //参数集合
						[{
							className: "com.hhwy.purchaseweb.monitor.service.UsermanagerService", //要调用的类的名字
							methodName: "getClientDataByPage", //要调用的类的方法名
							queryParams: [{page:main.params.formData.page,rows:main.params.formData.rows,dataDate:main.params.month,consId:main.params.consId}], //分页查询的参数
							sheetName: "电量报表", //导出后的excel的sheet名
							excelHeaderConfig: "sc.monitor.collect", //对应com_field_config表中的配置
							excelHeaders: "" //不必填值，只需定义，固定写法
						}]
				};
	
				//导出页面
				MainFrameUtil.openDialog({
					id: 'excelDialog',
					params: {
						'excelParams': excelParams
					},
					href: basePath + 'view/business-core/dataprocess/excel',
					iframe: true,
					modal: true,
					width: "80%",
					height: 420,
					onClose: function() {
	
					}
				});
			}
		},
		watch:{
			'params.month': function(value){
				this.initUserTree();
				if(this.params.consId != null && this.params.consId != ''){
					this.search();
				}
			},
			'params.consId': function(){
				if(this.params.month != null && this.params.month !=''){
					this.search();
				}
			},
			'params.usuallyDateFlag': function(){
				if(this.params.consId != null && this.params.consId != '' && this.params.month != null && this.params.month !=''){
					this.search();
				}
			}
			
		},
	});
</script>
</body>
 </html>