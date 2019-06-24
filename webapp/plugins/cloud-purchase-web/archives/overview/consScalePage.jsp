<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="../../../business-core/static/headers/echarts.jsp"%>
<link type="text/css"  rel="stylesheet" href="../../static/css/consScalePage.css"/>
<title>总览-用户规模</title>
</head>

<body>
	<div id="overviewVue" style="height:100%">
			<div style="width:100%;height:100px;padding:0px;">
				<div class="num-box" style="padding-right:8px;">	
					<div class="topBlock" style="background-color:rgb(78, 189, 165);color:white;">
						<span class="leftBlock">
							<span class="cpmpactCons"></span>
							<span class="fontStyle">合同用户</span>
						</span>
						<span class="numberStyle" id="agreCons"></span>
						<span class="agreBg"></span>
					</div>
				</div>
				<div class="num-box" style="padding-left:4px;padding-right:4px">
					<div class="topBlock" style="background-color:rgb(12, 176, 224);color:white;">
						<span class="leftBlock">
							<span class="thinkCons"></span>
							<span class="fontStyle">意向用户</span>
						</span>
						<span class="numberStyle" id="thinkCons"></span>
						<span class="agreBg" ></span>
					</div>
				</div>
				<div class="num-box" style="padding-left:8px;">
					<div class="topBlock" style="background-color:rgb(245, 166, 35);color:white;">
						<span class="leftBlock">
							<span class="company"></span>
							<span class="fontStyle">发电企业</span>
						</span>
						<span class="numberStyle" id="getElecCom"></span>
						<span class="agreBg" ></span>
					</div>
				</div>
		    </div>
<!-- 第二块 -->		 
			<div class="secondDiv" style="">
				<div class="block" style="padding-right:6px;">
					<div class="distance" style="">
						<div class="top" style="">
							<span class="title">用户分类</span>
							<span class="unit" style="background-color:rgb(78,189,165)">单位：个</span>
						</div>
						<div id="consSortDiv" class="echartStyle"></div>
					</div>
				</div>
				
				<div class="block" style="padding-left:6px;">
					<div class="distance" style="">
						<div class="top" style="">
							<span class="title">用户电压等级</span>
							<span class="unit" style="background-color:rgb(245,166,35)">单位：个</span>
						</div>
						<div id="consVoltageDiv" class="echartStyle"></div>
					</div>
				</div>
			</div>
<!-- 最下面块 -->
			<div class="bottomDiv">
				<div style="height:40px;margin-top:40px;">
			        <!-- 合同开始日期 -->
			        <su-form-group label-name="年度" class="" col="4" label-width="120px" label-align="right" style="height:40px">
						<su-datepicker name="year" :z-index="999999" id="year" format="YYYY"  :data.sync="year" ></su-datepicker>
			        </su-form-group>
			         <su-button s-type="primary" class="btn-width-small" v-on:click="getConsBar" style="margin-left: 50%;">查询</su-button>
			    </div>
				
				<div class="consData" style="">
					<div style="width:25%;height:100%;float:left;">
						<span style="margin-right:30%">总用户</span><span id="sumCons"></span>个,
					</div>
					<div style="width:25%;height:100%;float:left;">
						<span style="margin-right:30%">本年新增</span><span id="yearAdd"></span>个,
					</div>
					<div style="width:25%;height:100%;float:left;">
						<span style="margin-right:30%">本月新增</span><span id="monthAdd"></span>个,
					</div>
					<div style="width:25%;height:100%;float:left;" >
						<span style="margin-right:30%">上月新增</span><span id="upMonthAdd"></span>个
					</div>
				
				</div>
				<div style="height:350px;width:100%">
					<div id="baeEchart" class="barStyle" style="height:100%;width:100%">
					</div>
				</div>
			</div>
	</div>
	
	<script type="text/javascript">
		 $(window).resize(throll(function(){
			if(overviewVue.myChart1!=null&&
				overviewVue.myChart2!=null&&
				overviewVue.myChart3!=null&&
			   $("#overviewVue").height()>60&&
			   $("#overviewVue").width()>100){
				overviewVue.myChart1.resize();
				overviewVue.myChart2.resize();
				overviewVue.myChart3.resize();
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
		var overviewVue = new Vue({
			el : '#overviewVue',
			data : {
				myChart1 : null,
				myChart2 : null,
				myChart3 : null,
				year : null,
		
			},
			ready : function() {
				var me = this;
				var date = new Date();
				this.year = date.getFullYear();
				//数据加载
				$.ajax({
					url : basePath + "cloud-purchase-web/overViewController/getConsScale",
					type : 'get',
					success : function(data){
						//上方三块数据
						me.topTitle(data);
						//用户分类 环图
						me.consSortDiv(data);
						//电压分类 环图
						me.consVoltageDiv(data);
					}
				});
				me.getConsBar();
				
			},
			methods : {
				
				topTitle : function(data){
					//合同用户
					var agre = 0;
					//意向用户
					var think = 0;
					for(var i=0; i<(data.data.getSumConsType).length; i++){
						if((data.data.getSumConsType[i]).type == "01"){
							agre = data.data.getSumConsType[i].consType;
							$("#agreCons").html(agre);
						}
						if((data.data.getSumConsType[i]).type =="02"){
							think = data.data.getSumConsType[i].consType;
							$("#thinkCons").html(think);
						}
					}
					//发电企业
					var elec = data.data.getElecCom == null ? 0 : data.data.getElecCom;
					$("#getElecCom").html(elec);
				},
				//用户分类 环图
				consSortDiv : function(data){
					var me = this;
					//大工业
					var big = 0;
					//一般工业
					var common = 0;
					if(data.data.getUserScale.length > 0 && data.data.getUserScale[0]){
						big = data.data.getUserScale[0].total == null ? 0 : data.data.getUserScale[0].total;
					}
					if(data.data.getUserScale.length > 0 &&  data.data.getUserScale[1]){
						common = data.data.getUserScale[1].total == null ? 0 : data.data.getUserScale[1].total;
					}
					me.myChart1 = echarts.init(document.getElementById("consSortDiv"));
					option = {
							color :['rgb(0, 187, 150)','rgb(0, 129, 201)'],
							tooltip: {
						        trigger: 'item',
						        formatter: "{a} <br/>{b}: {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						        x: '70%',
						        y: '50%',
						        data:['大工业','一般工商业']
						        
						    },
						    series: [
						        {
						            show: false,
						            name:'用户类别',
						            type:'pie',
						            radius: ['50%', '70%'],
						            center: ['50%', '50%'],
						            avoidLabelOverlap: false,
						            label: {
						                normal: {
						                    show: false
						                },
						            },
						            labelLine: {
						                normal: {
						                    show: false
						                }
						            },
						            data:[
						                {value:big, name:'大工业'},
						                {value:common, name:'一般工商业'},
						               
						            ]
						        }
						    ]
						};
					me.myChart1.setOption(option);
				},
				//电压等级 环图
				consVoltageDiv : function(data){
					var me = this;
					//电压用户数量
					var voltCount = data.data.voltCount == null ? 0 : data.data.voltCount;
					//用户名
					var voltName = data.data.voltName == null ? "" : data.data.voltName;
					
					var list = [];
					for(var i=0 ;i<voltName.length ;i++){
						list.push({value:voltCount[i],name:voltName[i]});
						
					}
					me.myChart2 = echarts.init(document.getElementById("consVoltageDiv"));
					option = {
							color : ['rgb(0,175,157)','rgb(255,199,55)','rgb(135,195,237)','rgb(0,125,207)','rgb(255,96,95)'],
							tooltip: {
						        trigger: 'item',
						        formatter: "{a} <br/>{b}: {c} ({d}%)"
						    },
						    legend: {
						        orient: 'vertical',
						        x: '70%',
						        y: '30%',
						        data:voltName,
						    },
						    series: [
						        {
						            show: false,
						            name:'电压',
						            type:'pie',
						            radius: ['50%', '70%'],
						            avoidLabelOverlap: false,
						            label: {
						                normal: {
						                    show: false
						                },
						            },
						            labelLine: {
						                normal: {
						                    show: false
						                }
						            },
						            data:list
						        }
						    ]
						};
					me.myChart2.setOption(option);
				},
				//柱状图
				baeEchart:function(getConsSum,upYearConsSum){
					var me = this;
					//每月总用户
					var list = new Array();
					//每月增减用户
					var reduce = new Array();
					//蓝图数据
					var blueBar =new Array();
					var obj = getConsSum;
					var aa = ["one","two","three","four","five","six","seven","eight","nine","ten","eleven","twelve"];
					//把数据 正序
					for(var i in aa){
						list.push(obj[aa[i]]);
					}
					//计算增减用户数量
					for(var i in list){
						if(i==0){
							reduce.push(list[i] - upYearConsSum[0].twelve);
							blueBar.push(upYearConsSum[0].twelve);
						} else{
							if(list[i]-list[i-1] == 0){
								reduce.push('');
								blueBar.push(list[i]);
							} else{
								reduce.push(list[i]-list[i-1]);
								blueBar.push(list[i] - reduce[i]);
							}
						}
					}
					var date =  new Date();
					var month = date.getMonth()+1;
					//最大用户数
					var maxConsNum = 0;
					for(var i in list){
						if(i==0){
							continue;
						}else{
							if(list[i] > list[i-1]){
								maxConsNum =list[i];
							}
						}
					}
					//总用户
					$("#sumCons").html(list[11]);
					//本年新增 
					var yearAddCons = 0;
					for(var i in reduce){
							if(reduce[i] != ''){
								yearAddCons += reduce[i];
							}
					}
					$("#yearAdd").html(yearAddCons);
					//本月新增用户
					var monthAddCons;
					if(month == 1){
						monthAddCons = list[0] - upYearConsSum[0].twelve;
					}else{
						monthAddCons = reduce[month-1] == ''? 0:reduce[month-1];
					}
					$("#monthAdd").html(monthAddCons);
					//上月新增                                                    
					if(month == 1){
						$("#upMonthAdd").html(upYearConsSum[0].twelve - upYearConsSum[0].eleven);						
					} else{
						var upMonthAdd = reduce[month-2] == ''? 0 :reduce[month-2];
						$("#upMonthAdd").html(upMonthAdd);
					}
					me.myChart3 = echarts.init(document.getElementById('baeEchart'));
					option = {
 						    color :['#12a3aa','#bababa'],
							tooltip : {
						        trigger: 'axis',
						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
						        }
						    },
						    legend: {
						        data:['累计','新增'],
						        x:"80%",
						        y : 10,
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '3%',
						        containLabel: true
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
						        }
						    ],
						    yAxis : [
						        {
						            type : 'value'
						        }
						    ],
						    series : [
						    
						        {	
						        	barMaxWidth :'40%',
						            name:'累计',
						            type:'bar',
						           label: {
						                normal: {
						                    show: true,
						                }
						            },
						            stack: 'sum',
						            data:blueBar
						        },
						        {
						        	barMaxWidth :'40%',
						        	name:'新增',
						            type:'bar',
						           label: {
						                normal: {
						                    show: true,
						                }
						            },
						            stack: 'sum',
						            data:reduce
						        }
						    ]
						};

					me.myChart3.setOption(option);
				},
				//柱状图 数据
				getConsBar : function(){
					var me = this;
					$.ajax({
						url : basePath + "cloud-purchase-web/overViewController/getConsBar/"+me.year,
						type : 'get',
						success : function(data){
							//绘柱状图
							me.baeEchart(data.data.getConsSum,data.data.upYearConsSum);
						}
					});
					
				}
				},
		
		
		});
	</script>


</body>
</html>
















