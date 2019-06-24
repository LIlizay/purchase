<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="../../../business-core/static/headers/echarts.jsp"%>
	<link type="text/css"  rel="stylesheet" href="../../static/css/overView.css"/>
<title>总览</title>
</head>
<body>
	<div id="listVue" style="height:100%">
		
		<div class="col-xs-12 nums">
			<div class="row" style="height:100%;width:100% ">
				<div class="num-box" style="padding-right:8px">
					<div class="num-box-inner">
						<div class="top">
							<span class="title" style="color:rgb(78,189,165)">
								年累计购电量
								<div v-on:click="getYearPhDeail()" id="firstTIcon" class="icon" style="background-color: rgb(78,189,165);" >
									<div class="btn-label" style="text-align: center;">
										<img src="../../static/images/purchasePq/topIcon.png" style="height:15px;width:15px">
									</div>
								</div>
							</span>
							<span class="unit" style="background-color: #1db394">单位：兆瓦时</span>
						</div>
						<div class="bottom">
							<!-- 总量 -->
								<div  class="body" id="phMYearPlan" style="color:#1db394;"></div>
								<div class="topSubtitle"  ><img src="../../static/images/line1.png"></div>
								<div class="topRightData" style="">
									<!-- 购电 双边 -->
									<span id="topLDataDoubleName">双边</span></br>
									<span style="color:rgb(29,179,148)" id="topLDataDouble"></span>
								</div>
								<div class="topSubtitle"  ><img src="../../static/images/line1.png"></div>
								<div class="topRightData" style="">
									<!-- 购电 竞价 -->
									<span id="topLDataBidName">竞价</span></br>
									<span style="color:rgb(29,179,148)" id="topLDataBid"></span>
								</div>
						</div>
					</div>
					
				</div>
				<div class="num-box" style="padding-right:4.5px;padding-left:4.5px">
					<div class="num-box-inner">
						<div class="top">
							<span class="title"  style="color: rgb(245,166,35)">
								年累计售电量
								<div v-on:click="getSellMonLcBid('monLcBid')" id="secondTIcon" class="icon" style="background-color: rgb(245,166,35);">
									<div class="btn-label" style="text-align: center;">
										<img src="../../static/images/purchasePq/topIcon.png" style="height:15px;width:15px">
									</div>
								</div>
							</span>
							<span class="unit" style="background-color: rgb(245,166,35)">单位：兆瓦时</span>
						</div>
						<div class="bottom">
							<!-- 总量 -->
							<div  class="body" id="sellMYearPlan" style="color:rgb(245,166,35);"></div>
						</div>
					</div>
					
				</div>
				<div class="num-box" style="padding-left:8px;padding-right:1px">
					
					<div class="num-box-inner">
						<div class="top">
							<span class="title" style="color: rgb(12,176,224)">
								用户规模
								<div v-on:click="consScalePage('scale')" id="thirdTIcon" class="icon" style="background-color: rgb(12,176,224);">
									<div class="btn-label" style="text-align: center;">
										<img src="../../static/images/purchasePq/topIcon.png" style="height:15px;width:15px">
									</div>
								</div>
							</span>
							<span class="unit" style="background-color: rgb(12,176,224)">单位：个</span>
						</div>
						<div class="bottom">
							<!-- 总量 -->
							<div  class="body" id="userScaleSum" style="color:rgb(12,176,224);"></div>
								<div class="topSubtitle"  ><img src="../../static/images/line1.png"></div>
								<div class="topRightData" style="">
									<!-- 大工业 -->
									<span id="bigIndustryName">大工业用电</span></br>
									<span style="color:rgb(12,176,224)" id="bigIndustry"></span>
								</div>
								<div class="topSubtitle"  ><img src="../../static/images/line1.png"></div>
								<div class="topRightData" style="">
									<!-- 一般工商业 -->
									<span id="comIndustryName">一般工商业</span></br>
									<span style="color:rgb(12,176,224)" id="comIndustry"></span>
								</div>
						</div>
					</div>
				</div>
			</div>
	</div>
	<div class="col-xs-12 secondDiv">
			<div class="col-xs-8 secTranStatus">
				<div class="top">
					<span class="title">
						<span id="yearPh"></span>年<span id="monthPh"></span>月购电交易情况
					</span>
					<span class="unit" style="background-color: #1db394">电量单位： 兆瓦时</span>
				</div>
				
				<div style="height:90%;width:100%">
					<!-- 上方小图标 -->
					<div class="topIcon" style="height:50%;width:96%;text-align:center;padding-top:20px;border-bottom: 1px dashed #DDDDDD;margin: 0% 0% 0% 2%;">
							<div class="monthPhPq"><img src="../../static/images/purchasePq/01purchasePlan-gray.png" ><span class="monthPhPqText">购电计划制定</span></div>	
							<div class="arrows"><img src="../../static/images/purchasePq/arrows-gray.png"></div>
							<div class="monthPhPq"><img src="../../static/images/purchasePq/02reportPq-gray.png" > <span class="monthPhPqText">委托电量审核</span></div>
							<div class="arrows"><img src="../../static/images/purchasePq/arrows-gray.png"></div>
							<div class="monthPhPq"><img src="../../static/images/purchasePq/03bidDeal-gray.png" ><span class="monthPhPqText">交易信息申报</span></div>
							<div class="arrows"><img src="../../static/images/purchasePq/arrows-gray.png"></div>
							<div class="monthPhPq"><img src="../../static/images/purchasePq/04bidBargain-gray.png" ><span class="monthPhPqText">成交信息确认</span></div>
					</div>
					<div style="height:160px;padding-top: 30px;">
						<!-- 左方数据 用户委托电量-->
						<div class="monthPhPlanBD" style="">
							<span id="consReportName">用户委托电量</span></br>
							<span id="consReportSum" style="color: #01aba9;font-size: 30px;font-weight: bold;"></span>
						</div>
						<!-- 间隔线 -->
						<span style="float:left;margin-left:12px;"><img src="../../static/images/line1.png"></span>
						<!-- 左方数据 总购电量-->
						<div class="monthPhPlanBD" style="">
							<span id="sumPurchasePqName">总购电量</span></br>
							<span id="sumPurchasePq"  style="color: #01aba9;font-size: 30px;font-weight: bold;" ></span>
						</div>
						<!-- 右方echart条 -->
						<div  style="height:100%;width:47%;float:left;margin-left:30px;text-align:center" >	
							<div id="secondEchart" style="height:90px;width:100%;margin-top: 10px;text-align:center"></div>
						</div>
					</div>
				</div>
				
			</div>
			<div class="col-xs-4 secCommonApp">
				<div style="background-color:white;width:100%;height:100%;width: calc(100% - 12px);	margin-left: 12px;">			
					<div class="top">
						<span class="title">常用应用</span>
					</div>
					<div style="height:90%;width:100%;">
						<div class="rightApp" style="height:90%;width:100%;margin-top:30px;float:left;">
							<div style="width:96%;height: 40%;margin: auto;">
								<div class="appBlock"><span class="commonApp" v-on:click="consFile()"><img src="../../static/images/app/consFile.png"></span><span class="appImgText">用户档案</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="agreDesign()"><img src="../../static/images/app/agreDesign.png"></span><span class="appImgText">合同设计</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="sellAgre()"><img src="../../static/images/app/sellAgre.png"></span><span class="appImgText">售电合同</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="purchaseAgre()"><img src="../../static/images/app/purchaseAgre.png"></span><span class="appImgText">购电合同</span></div>
							</div>
							<!-- topApp 外发光查找的节点 -->
							<div class="topApp" style="width:96%;height: 40%;margin: auto;">	
								<div class="appBlock"><span class="commonApp" v-on:click="monthPurchase()"><img alt="" src="../../static/images/app/monthPurchase.png"></span><span class="appImgText">购电交易</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="profitClose()"><img alt="" src="../../static/images/app/profitClose.png"></span><span class="appImgText">收益结算</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="loadForecase()"><img alt="" src="../../static/images/app/loadForecase.png"></span><span class="appImgText">负荷预测</span></div>
								<div class="appBlock"><span class="commonApp" v-on:click="deviationWarn()"><img alt="" src="../../static/images/app/deviationWarn.png"></span><span class="appImgText">偏差预警</span></div>
							</div>	
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 thirdDiv">
			<div class="shortStatus">
				<div class="top">
					<span class="title"><span id="yearDeviation"></span>年<span id="monthDeviation"></span>月用电偏差情况</span>
				</div>
				<div style="width:100%;height:100%;">
					<div style="width:100%;height:80px;text-align: center" >
						<div  style="width:100%;height:40px;margin-top: 10px;">
							<div class="shortStatusText" style="width:50%">偏差用户数</div>
							<span class="shortStatusTextR" style="width:50%" >{{deviationInfo.consCount}}</span>
						</div>
						<div style="width:100%;height:40px;">
							<div class="shortStatusText" style="width:50%">用电偏差率</div>
							<span class="shortStatusTextR" style="width:50%">{{deviationInfo.deviationRate}}</span>
						</div>
					</div>
					<div id="deviationWarnGauge" style="height:70%;width:100%;">
					</div>
				</div>
								
			</div>
			<div class="outSellPowerProfit">
				<div class="sellPowerProfit">
					<div class="top">
						<span id="sellProfit" class="title">售电利润</span>
					</div>
					<div id="sellPowerEchart" style="width:100%;height:100%">
					</div>	
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 fourthDiv">
			<div class="sellPowerCount">
				<div class="top">
					<span class="title">交易电量统计</span>
				</div>
				<div  style="width: 100%; height: 90%">
					<div id="powerCountEchart" style="width: 98%; height: 96%;margin-top:10px;margin: auto;"></div>
				</div>
			</div>
		</div>
		
		<div class="col-xs-12 bottomDiv">
			<div class="col-xs-5 bottomEchart1" id="panel">
				<div class="top">
					<span class="title">用户合同电量前十排名</span>
					<span class="unit" style="background-color: #1db394">电量单位： 兆瓦时</span>
				</div>
				<div id="bottomDivEchartL" style="width:90%;height:80%;margin-left:5%;margin-top:5%"></div>
			</div>
			
			<div class="col-xs-5 bottomEchart2" id="panel">
				<div class="top">
					<span class="title">电厂合同电量前十排名</span>
					<span class="unit" style="background-color: #1db394">电量单位： 兆瓦时</span>
				</div>
				<div id="bottomDivEchartR"  style="width:100%;height:90%"></div>
			</div>
			
			<div class="col-xs-2 bottomText" id="panel">
				<div class="text1">
					<div class="top">
						<span class="title">消息公告</span>
					</div>
					<div>
						
					</div>
				</div>
				<div class="text2">
					<div class="top">
						<span class="title">我的待办</span>
					</div>
					<div>
						
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
			 $(window).resize(throll(function(){
				 
				 if(listVue.myChart6!=null&& $("#listVue").height()>60 && $("#listVue").width()>100){
				 	listVue.myChart6.resize();
				 }
				 if(
							listVue.myChart1!=null&&
							listVue.myEchart2!=null&&
							listVue.myEchart3!=null&&
							listVue.myChart4!=null&&
							listVue.myEchart5!=null&&
					   $("#listVue").height()>60&&
					   $("#listVue").width()>100){
						listVue.myChart1.resize();
						listVue.myEchart2.resize();
						listVue.myEchart3.resize();
						listVue.myChart4.resize();
						listVue.myEchart5.resize();
						/*  //仪表盘
						 var gaugeMargin = $("#deviationWarnGauge").css("margin-left");
						 if(gaugeMargin == "0px"){
							 $("#deviationWarnGauge").css("margin-left",'18px'); 
						 }else{
							 $("#deviationWarnGauge").css("margin-left",'0px'); 
						 } */
					}
				},100));
		function throll(fn,delay){
		    var timer=null;
		    return function(){
		        clearTimeout(timer);
		        timer=setTimeout(fn,delay);
		    }
		}  
		var cloudsellingProvince = "${cloudsellingProvince}";		//数据库配置的省码
		var basePath = "${Config.baseURL}";	
		var listVue = new Vue({
			el : "#listVue",
			data : {
				deviationInfo: {consCount: 0, deviationRate: "0%"},		//用电偏差情况
				//权限数组
				arrResource : [],
				//登录用户ID
				userId:null,
				uuid : {
						//控制打开页签的唯一
						cons:null,
						agre:null,
						sell:null,
						ph:null,
						mp:null,
						pc:null,
						lf:null,
						dw:null,
						scale:null,
						monLcBid : null,
						yearPhDeail : null,
						//月购电交易情况
						phPlane:null,
						reportPq:null,
						monthBid:null,
						bidDeal:null
					},
				//用户合同电量排名
				myChart1:null,
				//电厂合同电量排名
				myEchart2: null,
				//交易电量统计
				myEchart3: null,
				//售电利润
				myChart4: null,
				//月偏差仪表
				myEchart5: null,
				//月购电交易情况
				myChart6: null
			},
			ready : function(){
				//获取登录用户ID
				var userInfo = $.getLoginUserInfo(basePath);
				this.userId = userInfo.id;
				//月购电显示日期
				var myDate = new Date();
				var yearPh = myDate.getFullYear();
				var monthPh= myDate.getMonth()+2;
				if(monthPh == 13){
					monthPh = 1;
					yearPh = yearPh + 1 ;
				}
				$("#yearPh").html(yearPh);
				$("#monthPh").html(monthPh);
				//偏差情况显示日期
				var dDate = new Date();
				var yearD = myDate.getFullYear();
				var monthD= myDate.getMonth()+1;
				$("#yearDeviation").html(yearD);
				$("#monthDeviation").html(monthD);
				this.getDeviationInfo();
				//数据
				this.getOverViewData();
				//最上小箭头改颜色
				this.mouseTopIcon();
			},
			methods : {
				//获取当月的偏差用户数和总用电偏差率
				getDeviationInfo: function(){
					var me = this;
					$.ajax({
						url : basePath + 'overViewController/getDeviationInfo',
						type : 'get',
						success : function(data){
							if(data.flag){
								me.deviationInfo.consCount = data.data.consCount;
								me.deviationInfo.deviationRate = data.data.deviationRate + "%";
								//仪表盘
								me.deviationWarnGauge(data.data.deviationRate);
							}else{
								MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"网络错误，请刷新页面重试！"});
						}
					});
				},
				//初始数据
				getOverViewData:function(){
					var me = this;
					$.ajax({
						url : basePath + 'overViewController/getOverViewData/'+me.userId,
						type : 'get',
						success : function(data){
							if(data.flag){
								//用户权限
								me.userResource(data.data);
								//交易电量统计
								me.fourthDivEchart(data.data.dealElecCount);
								//年累计购电量
								me.phMYearPlan(data.data);
								//年累计售电量
								me.sellPower(data.data.sellPower);
								//用户规模
								me.userScale(data.data);
								/* secondDiv */
								//月购电交易状态 图
								me.getPurchasePqStatus(data.data);
								//月购电交易情况
								me.purchasePq(data.data); 
								//售电利润
								me.thirdEchart(data.data);
								//电厂合同电量排名
								me.bottomDivEchartR(data.data);
								//用户合同电量排名
								me.bottomDivEchartL(data.data);
							}else{
								MainFrameUtil.alert({title:"提示",body:data.msg});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"网络错误，请刷新页面重试！"});
						}
					});
				},
				mouseTopIcon : function(){
					//年购 箭头
					$("#firstTIcon").mouseover(function(){
						$(this).css("background-color","#1d8d74");
					});
					$("#firstTIcon").mouseout(function(){
						$(this).css("background-color","rgb(33, 172, 141)");
					});
					//年售 箭头
					$("#secondTIcon").mouseover(function(){
						$(this).css("background-color","rgb(218, 142, 15)");
					});
					$("#secondTIcon").mouseout(function(){
						$(this).css("background-color","rgb(245,166,35)");
					});
					//用户规模 箭头
					$("#thirdTIcon").mouseover(function(){
						$(this).css("background-color","rgb(7, 157, 202)");
					});
					$("#thirdTIcon").mouseout(function(){
						$(this).css("background-color","rgb(12,176,224)");
					});
				},
				userResource : function(data){
					var me = this;
					var userResource = data.userResource; //数组
					//'用户档案','合同辅助设计','售电合同管理','购电合同管理','月度购电管理','月度收益结算','用户负荷预测','偏差预警信息','售电利润柱状图'
					var param = {"view/cloud-purchase-web/archives/scconsumerinfo/list":1,"view/cloud-purchase-web/crm/ssagrescheme/list":2,"view/cloud-purchase-web/agreement/smppa/list":3,"view/cloud-purchase-web/agreement/phmppa/list":4
							,"view/cloud-purchase-web/bid/phmpurchaseplanflow/list":5,"view/cloud-purchase-web/settlement/list":6,"view/cloud-purchase-web/crm/custelecquery/list":7,"view/cloud-purchase-web/deviationcheck/deviationwarninginfo/list":8,"overViewSellProBar":9};
					var map = {};
					for(var i=0 ;i<userResource.length ;i++){
						var obj = userResource[i];
						map[obj] = obj;
					}
					
					for(var i in param){
						var cont = param[i];
						if(map[i]){
							if(map[i] != 'overViewSellProBar'){//售电柱状图URL
								$(".appBlock").eq(cont-1).find(".commonApp").css("background-color","#21ac8d").css("cursor","pointer");
								$(".appBlock").eq(cont-1).find(".commonApp").mouseover(function(){
									$(this).css("background-color","#1d8d74");
								});
								$(".appBlock").eq(cont-1).find(".commonApp").mouseout(function(){
									$(this).css("background-color","rgb(33, 172, 141)");
								});
								/* .commonApp:hover{
						   			background-color: #1d8d74;
						   		}  */
							}
							me.arrResource.push(map[i]);
						}
					}
					
				},
						/* ----------bottomDiv---------- */
				//电厂合同电量排名
				bottomDivEchartR:function(data){
					this.myEchart2 = echarts.init(document.getElementById('bottomDivEchartR'));
					var seriesData = new Array();
					var rows = data.elecAgreRank.agrePq;
					var names = data.elecAgreRank.shortName;
					var nameMap = data.elecAgreRank.elecName;
					for(var i in rows){
						var obj = {value:rows[i],name:names[i]};
						seriesData.push(obj);
					} 
					option = {
							color : ['rgb(0,175,157)','rgb(255,199,55)','rgb(135,195,237)','rgb(0,125,207)','rgb(255,96,95)'],
							tooltip : {
						        trigger: 'item',
						        formatter: function (params, ticket, callback) {
		                            var name = params.name;
		                            var lastName = nameMap[name];
		                            var seriesName = '电量 :' +params.data.value + '兆瓦时';
		                        	return lastName +'<br />' + seriesName;
		                        }
						    },
						    series : [
						        {
						            type: 'pie',
						            radius : '65%',
						            center: ['50%', '50%'],
						            selectedMode: 'single',
						            data:seriesData,
						            itemStyle: {
						                emphasis: {
						                    shadowBlur: 10,
						                    shadowOffsetX: 0,
						                    shadowColor: 'rgba(0, 0, 0, 0.5)'
						                }
						            }
						        }
						    ]
						}; 
	
					this.myEchart2.setOption(option);
				},
				
				//用户合同电量排名
				bottomDivEchartL:function(data){
					this.myChart1 = echarts.init(document.getElementById('bottomDivEchartL'));
					var nname = data.consAgreRank.shortName;
					if(data.consAgreRank.shortName.length == 0){
						nname =  ['用户1','用户2','用户3','用户4','用户5','用户6','用户7'];
					}
					var nameMap = data.consAgreRank.consName;
						option = {
								 tooltip : {
								        trigger: 'axis',
								        axisPointer: {
											crossStyle: {
												color: '#999'
											}
										},
								        formatter: function (params, ticket, callback) {
				                            //x轴名称 
				                            var name = params[0].name;
				                            var lastName = nameMap[(params[0].dataIndex)+'.'+name];
				                            var seriesName = "";
				                        	for(var j in params){
				                        		if(params[j].value == undefined){
				                        			params[j].value = 0;
				                        		}
				                    			seriesName += params[j].seriesName + ':' +params[j].value + '兆瓦时<br />';
				                        	}
				                        	return lastName +'<br />' + seriesName;
				                        }

								    },
						    color: ['rgb(18,163,170)'],
						    angleAxis: {
						        type: 'category',
						        data: nname,
						        z: 10,
						        axisLabel:{
						            interval: 0,//标签设置为全部显示
						            //换行显示图例
						            formatter:function(nname){
						            var newParamsName = "";// 最终拼接成的字符串
						                var paramsNameNumber = nname.length;// 实际标签的个数
						                var provideNumber = 4;// 每行能显示的字的个数
						            	 // 换行的话，需要显示几行，向上取整
						                var rowNumber = Math.ceil(paramsNameNumber / provideNumber);
						                // 条件等同于rowNumber>1
						                if (paramsNameNumber > provideNumber) {
						                    for (var p = 0; p < rowNumber; p++) {
						                        var tempStr = "";// 表示每一次截取的字符串
						                        var start = p * provideNumber;// 开始截取的位置
						                        var end = start + provideNumber;// 结束截取的位置
						                        // 此处特殊处理最后一行的索引值
						                        if (p == rowNumber - 1) {
						                            // 最后一次不换行
						                            tempStr = nname.substring(start, paramsNameNumber);
						                        } else {
						                            // 每一次拼接字符串并换行
						                            tempStr = nname.substring(start, end) + "\n";
						                        }
						                        newParamsName += tempStr;// 最终拼成的字符串
						                    }
						                } else {
						                    // 将旧标签的值赋给新标签
						                    newParamsName = nname;
						                }
						                //将最终的字符串返回
						                return newParamsName
						            }
						        },
						    },
						    radiusAxis: {
						    },
						    polar: {
						    },
						    series: [ {
						        type: 'bar',
						        data: data.consAgreRank.proxyPq,
						        coordinateSystem: 'polar',
						        name: '电量',
						        stack: 'a'
						    }],
						    
						};
					this.myChart1.setOption(option);
						
				},
						/* ----------thirdDiv---------- */
				//月偏差仪表
				deviationWarnGauge : function(deviationRate){
					var deviation = 5.0;
					if(cloudsellingProvince == "350000"){	//福建省
						deviation = 3.0;
			 		}
					
					var floatRate = parseFloat(deviationRate);
					floatRate = floatRate < 0 ? (-floatRate) : floatRate;
					var deviationRateTens = parseInt(floatRate/10) + 1;
					var maxData = parseInt(deviationRateTens * 10);
					var rate1 = ((maxData - deviation)/(2 * maxData)).toFixed(2);
					var rate2 = (1 - rate1).toFixed(2);
					this.myEchart5 = echarts.init(document.getElementById("deviationWarnGauge"))
					option = {
					    tooltip : {
					        formatter: "{a} <br/>{b} : {c}%"
					    },
					    series: [
					        {  
// 					        	 radius : '60%', //图标大小
// 					        	startAngle: 180,
// 					            endAngle: 0,
// 					        	center:[150,120], 
					        	max: maxData,
					        	min: -maxData,
					            name: '偏差率',
					            type: 'gauge',
					            pointer:{//指针
					                width:4,
					                length:'60%'
					            },
					            splitLine: {           // 分隔线
                                    length:16,         // 属性length控制线长
                            	 },
					      	 	axisLine: {     
				                     lineStyle: {       // 属性lineStyle控制线条样式  
				                         color: [[rate1, '#F6A623'], [rate2, '#01aba9'], [1, '#F6A623']],
				                         width:20,
				                     }  
				                },   
					            detail: {formatter:'{value}%',show : false},
					            data: [{value: deviationRate , name: '' }],
					            axisLabel : {distance:7}//数值与图的间距
					        }
					    ]
					};

					this.myEchart5.setOption(option);
					
				},
				//售电利润图
				thirdEchart:function(data){
					this.myChart4 = echarts.init(document.getElementById("sellPowerEchart"));
					//数据
					var data1;
					//判断有无 “售电利润柱状图”权限
					if(this.arrResource.indexOf('overViewSellProBar') < 0){
						data1 = 0;	
					}else{
						data1 = data.phProfit.compProfit;
						var totalProfit = 0 ;
						for(var i in data1){
							var profit = (data1[i] != null && data1[i] != '') ? data1[i] : 0;
							totalProfit += parseFloat(profit);
						}
						$("#sellProfit").text($("#yearPh").text()+"年售电总利润"+totalProfit.toFixed(2)+"元");
					}
						
					//有数据的年月
					var phYearMonth = data.phProfit.yearMonthPf;
					//增长率
					var groupRate = data.phProfit.groupRate;
					if( phYearMonth.length == 0){
						phYearMonth = ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月'];
					}
					option = {
						    color: ['rgb(34,173,142)','rgb(0,129,201)'],
						    tooltip: {
						        trigger: 'axis',
						        formatter: function (params) {
						        	var profit = params[0].data == "" ? '': params[0].data + '元';
						        	var rate   = params[1].data == "" ? '': params[1].data + '%';
						        	return params[0].axisValue + ' :</br>' 
						        		+ '&nbsp' + params[0].seriesName + ' : ' + profit
						        		+ '</br>'
						        		+ '&nbsp' + params[1].seriesName + ' : ' + rate; 
					     		}
					    	},
						    grid: {
						        bottom: '20%',
						    },
						    xAxis: [
						        {
						            type: 'category',
						            data: phYearMonth,
						            axisPointer: {
						                type: 'shadow'
						            }
						        }
						    ],
						    yAxis: [
						        {
						            type: 'value',
						            name: '利润(元)',
						            axisLabel: {show: true},
						            
						        },
						        {
						            type: 'value',
						            name: '增长率(%)',
						            axisLabel: {show: true},
					                 axisTick: {show: false},
					                 splitLine: {show: false},
						        }
						    ],
						    series: [
						        { 
						            barMaxWidth : 60,
									name: '利润',
									type: 'bar',
									data: data1
						            
						        },
						        
						        {
						            name:'增长率',
						            type:'line',
						            yAxisIndex: 1,
						            data:groupRate
						        }
						    ]
						};
					this.myChart4.setOption(option);
				},
				/* ----------secondDiv---------- */
				//下月购电交易情况
				purchasePq : function(data){
					//用户委托电量
					var reportPq = 0;
					//竞价 计算总购电量的
					var monthDealPq = 0;
					//双边 计算总购电量的
					var monthGeneratorPq = 0;
					//总购电量
					var  purchasePq = 0;
					
					if( data.purchasePq.length > 0){
						if(data.purchasePq[0].reportPq != null){
							reportPq =  data.purchasePq[0].reportPq;
						}
						if(data.purchasePq[0].monthDealPq != null){
							monthDealPq = data.purchasePq[0].monthDealPq;
						}
						if(data.purchasePq[0].monthGeneratorPq != null){
							monthGeneratorPq =  data.purchasePq[0].monthGeneratorPq;
						}
						if(data.purchasePq[0].purchasePq != null){
							purchasePq = data.purchasePq[0].purchasePq;
						}
						$("#consReportSum").html(reportPq); 
						$("#sumPurchasePq").html(purchasePq);
						//月购电小图
						this.secondEchart(data);
					} else{
						$("#consReportSum").html(reportPq); 
						$("#sumPurchasePq").html(purchasePq);
					}
					
					
				},
				turnto:function(id,ym,status,step,tradeMode){
					//step标识触发操作的步骤
					var me = this;
					//判断“月度购电管理”权限
					/* if(this.arrResource.indexOf('view/cloud-purchase-web/bid/phmpurchaseplanflow/list') < 0){
						return;
					} */
					if(!me.uuid.phPlane){
						me.uuid.phPlane=Math.random();
					} 
					var title = "成交信息确认";
				    if(status == "00"){
				    	title = "购电计划制定";
				    }
				    if(status == "01"){
				    	title = "委托电量审核";
				    }
				    if(status == "02"){
				    	title = "交易信息申报";
				    }
				    if(id.length == 6){
					    MainFrameUtil.openDialog({
				  			id:"plan_add",
							href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/add',
							params:{flag:true},
							title:'购电计划制定',
							maximizable:true,
							iframe:true,
							modal:true,
							width:"80%",
							height:630,
							onClose:function(flag){
								 window.location.reload();
								 if(flag == true){
									var path = '${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/list';
									MainFrameUtil.openTabPage(me.uuid.phPlane,path,"购电交易管理",false);
								 }
							}
						});
				    }else{
						MainFrameUtil.openDialog({
				  			id:"plan_edit",
				  			params:{planId:id,planStatus:status,ym:ym,mode:tradeMode,step:step},
							href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/overview',
							title: title,
							maximizable:true,
							iframe:true,
							modal:true,
							width:"80%",
							height:630,
							onClose:function(flag){
								 window.location.reload();
								 if(flag == true){
									var path = '${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/list';
									MainFrameUtil.openTabPage(me.uuid.phPlane,path,"购电交易管理",false);
								 }
							}
						});
				    }
				},
				//下月购电交易状态图
				getPurchasePqStatus : function(data){
					var me = this;
					var ym = data.getPurchasePqStatus.ym;
					var status = data.getPurchasePqStatus.planStatus;
					var tradeMode = data.getPurchasePqStatus.tradeMode;
					var id = "";
					if(data.getPurchasePqStatus.count == 0){
						id = ym;
					}else{
						id = data.getPurchasePqStatus.id;
					}
					//有无月度购电权限
					if(me.arrResource.indexOf('view/cloud-purchase-web/bid/phmpurchaseplanflow/list') >= 0 ){
						if(parseInt(status) < 4){
							$(".topApp  div:nth-child(1) span").first().css("box-shadow","0 0 10px 5px rgb(164,231,224)");
						}
						if(parseInt(status) == 0){	//未制定——>购电计划制定
							$(".topIcon  div:nth-child(1) img").first().css("cursor","pointer");
							$(".topIcon  div:nth-child(1) img").click(function(){me.turnto(id,ym,"00",1,tradeMode);});
						}
						if(parseInt(status) == 1){	//已制定——>月度委托电量审核
							$(".topIcon div:nth-child(1) img").attr("src","../../static/images/purchasePq/01purchasePlan.png");
							$(".topIcon  div:nth-child(1) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(1) img").click(function(){me.turnto(id,ym,"01",1,tradeMode);});
							
							$(".topIcon  div:nth-child(3) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(3) img").click(function(){me.turnto(id,ym,"01",2,tradeMode);});
						}
						if(parseInt(status) == 2){	//已审核——>交易申报
							$(".topIcon div:nth-child(1) img").attr("src","../../static/images/purchasePq/01purchasePlan.png");
							$(".topIcon div:nth-child(2) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(3) img").attr("src","../../static/images/purchasePq/02reportPq.png");
							
							$(".topIcon  div:nth-child(1) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(1) img").click(function(){me.turnto(id,ym,"02",1,tradeMode);});
							
							$(".topIcon  div:nth-child(3) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(3) img").click(function(){me.turnto(id,ym,"02",2,tradeMode);});
							
							$(".topIcon  div:nth-child(5) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(5) img").click(function(){me.turnto(id,ym,"02",3,tradeMode);});
						}
						if(parseInt(status) == 3){	//已申报——>竞价成交
							$(".topIcon div:nth-child(1) img").attr("src","../../static/images/purchasePq/01purchasePlan.png");
							$(".topIcon div:nth-child(2) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(3) img").attr("src","../../static/images/purchasePq/02reportPq.png");
							$(".topIcon div:nth-child(4) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(5) img").attr("src","../../static/images/purchasePq/03bidDeal.png");
	
							$(".topIcon  div:nth-child(1) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(1) img").click(function(){me.turnto(id,ym,"03",1,tradeMode);});
							
							$(".topIcon  div:nth-child(3) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(3) img").click(function(){me.turnto(id,ym,"03",2,tradeMode);});
							
							$(".topIcon  div:nth-child(5) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(5) img").click(function(){me.turnto(id,ym,"03",3,tradeMode);});
							
							$(".topIcon  div:nth-child(7) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(7) img").click(function(){me.turnto(id,ym,"03",4,tradeMode);});
						
						}
						if(parseInt(status) >= 4){//已成交
							$(".topIcon div:nth-child(1) img").attr("src","../../static/images/purchasePq/01purchasePlan.png");
							$(".topIcon div:nth-child(2) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(3) img").attr("src","../../static/images/purchasePq/02reportPq.png");
							$(".topIcon div:nth-child(4) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(5) img").attr("src","../../static/images/purchasePq/03bidDeal.png");
							$(".topIcon div:nth-child(6) img").attr("src","../../static/images/purchasePq/arrows.png");
							$(".topIcon div:nth-child(7) img").attr("src","../../static/images/purchasePq/04bidBargain.png");
							
							$(".topIcon  div:nth-child(1) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(1) img").click(function(){me.turnto(id,ym,"all",1,tradeMode);});
							$(".topIcon  div:nth-child(3) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(3) img").click(function(){me.turnto(id,ym,"all",2,tradeMode);});
							$(".topIcon  div:nth-child(5) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(5) img").click(function(){me.turnto(id,ym,"all",3,tradeMode);});
							$(".topIcon  div:nth-child(7) img").css("cursor","pointer");
							$(".topIcon  div:nth-child(7) img").click(function(){me.turnto(id,ym,"all",4,tradeMode);});
						}
					}
					//没有月度购电权限  交易情况图样式
					if(me.arrResource.indexOf('view/cloud-purchase-web/bid/phmpurchaseplanflow/list') < 0){
						$(".monthPhPq img").css("cursor","auto");
					} 
				},
				//月购电交易情况  
				secondEchart : function(data){
					//双边 竞价 挂牌
					if(parseInt(data.purchasePq[0].monthDealPq) == 0 && parseInt(data.purchasePq[0].monthGeneratorPq) == 0 
							&& parseInt(data.purchasePq[0].hangPq) == 0){
						return;
					}
					var monthDealPqShow = true;
					var monthGeneratorPqShow = true;
					var hangPqShow = true;
					if(parseInt(data.purchasePq[0].monthDealPq) == 0){
						monthDealPqShow = false;
					}
					if(parseInt(data.purchasePq[0].monthGeneratorPq) == 0){
						monthGeneratorPqShow = false;
					}
					if(parseInt(data.purchasePq[0].hangPq) == 0){
						hangPqShow = false;
					}
					this.myChart6 = echarts.init(document.getElementById("secondEchart"));
					option = {  //--------------单位没加上
					    tooltip : {
					        trigger: 'axis',
// 					        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
// 					            type : 'false'        // 默认为直线，可选为：'line' | 'shadow'
// 					        },
					    	formatter: function (params) {
					        	var bidPq = params[0].data == "" ? '--': params[0].data + '元';
					        	var lsPq   = params[1].data == "" ? '--': params[1].data + '元';
					        	var listedPq   = params[2].data == "" ? '--': params[2].data + '元';
					        	var result = '<span style="display: inline-block; margin-right: 5px; border-radius: 10px; width: 9px; height: 9px; background-color: rgb(72, 184, 220);"></span>'
					        		 + params[0].seriesName + ': ' +bidPq
					        		+'<br> <span style="display: inline-block; margin-right: 5px; border-radius: 10px; width: 9px; height: 9px; background-color: rgb(141, 214, 199);"></span>'
					        		+ params[1].seriesName + ': ' +lsPq
					        		+'<br><span style="display: inline-block; margin-right: 5px; border-radius: 10px; width: 9px; height: 9px; background-color: #00b590;"></span>'
					        		+ params[2].seriesName + ': ' +listedPq;
					        	return result;
				     		}
					    },
					    legend: {
					        y : '33%',
					        x: 'center',
					        data: [data.purchasePq[0].monthDealPqName,data.purchasePq[0].monthGeneratorPqName,data.purchasePq[0].hangPqName]
					    },
					    grid: {
					        left: '3%',
					        height : '10%',
					        right: '4%',
					        bottom: '75%',
					        containLabel: true
					    },
					    xAxis:  {
					         show: false,
					        type: 'value'
					    },
					    yAxis: {
					        
					        show: false,
					        type: 'category',
					        data: ['']
					    },
					    series: [
					        {
					        	itemStyle:{ //条颜色
                                    normal:{
                                        color:'rgb(72,184,220)'
                                    }
                                },
                                label: {
                                    normal: {
                                        show: monthDealPqShow,
                                    }
                                },
					        	barWidth : 30,
					            name: data.purchasePq[0].monthDealPqName,
					            type: 'bar',
					            stack: '总量',
					            data: [data.purchasePq[0].monthDealPq]
					        },
					        {
					        	 itemStyle:{
	                                    normal:{
	                                        color:'rgb(141,214,199)'
	                                    }
	                                },
	                             label: {
	                                    normal: {
	                                        show: monthGeneratorPqShow,
	                                    }
	                             },
	                            barWidth : 30,
					            name: data.purchasePq[0].monthGeneratorPqName,
					            type: 'bar',
					            stack: '总量',
					            data: [data.purchasePq[0].monthGeneratorPq]
					        },
					        {
					        	 itemStyle:{
	                                    normal:{
	                                        color:'#00b590'
	                                    }
	                                },
	                             label: {
	                                    normal: {
	                                        show: hangPqShow,
	                                    }
	                             },
	                            barWidth : 30,
					            name: data.purchasePq[0].hangPqName,
					            type: 'bar',
					            stack: '总量',
					            data: [data.purchasePq[0].hangPq]
					        }
					    ]
					};
					this.myChart6.setOption(option);
					
				},
				/* ----------firstDiv---------- */
				//年累计售电量数据
				sellPower : function(data){
					var total = data[0].totalPq;
					$("#sellMYearPlan").html(total)
				},
				//年累计购电量数据
				phMYearPlan : function(data){
					//双边
					var lcPq = 0;
					//竞价
					var bidPq = 0;
					var gpPq = 0;
					if(data.phMPpaAgrePq != null){
						//双边
						if(data.phMPpaAgrePq.lcPq != null || data.phMPpaAgrePq.lcPq != ''){
							lcPq = data.phMPpaAgrePq.lcPq;
						}
						//竞价
						if(data.phMPpaAgrePq.bidPq != null || data.phMPpaAgrePq.bidPq != ''){
							bidPq = data.phMPpaAgrePq.bidPq;
						}
						if(data.phMPpaAgrePq.gpPq != null || data.phMPpaAgrePq.gpPq != ''){
							gpPq = data.phMPpaAgrePq.gpPq;
						}
					}
					$("#topLDataDouble").html(lcPq);
					$("#topLDataBid").html(bidPq);
					var total = lcPq + bidPq + gpPq;
					$("#phMYearPlan").html(total);
					
				},
				//用户规模
				userScale : function(data){
					var bigIndustry = 0;
					//大工业
					if(data.userScale.length > 0){
						bigIndustry = data.userScale[0].total;
					}
					$("#bigIndustry").html(bigIndustry);
					//一般工业
					var comIndustry = 0;
					if(data.userScale.length > 1){
						comIndustry = data.userScale[1].total;
					}
					$("#comIndustry").html(comIndustry);
					//总
					var userScaleSum = bigIndustry+comIndustry;
					$("#userScaleSum").html(userScaleSum);
				},
				/* ----------fourDiv---------- */
				//交易电量统计
				fourthDivEchart:function(dealElecCount){
					this.myEchart3 = echarts.init(document.getElementById('powerCountEchart'));
					var legendName = new Array();
					var yearMonthPf = new Array();
					var reportObj = new Object(); 
					var purchaseObj = new Object();
					var delivryPqObj = new Object();
					var reportPq = new Array();
					var purchasePq = new Array();
					var delivryPq = new Array();
					//上个月日期
					var nowdays = new Date();  
					var year = nowdays.getFullYear(); 
					var month = nowdays.getMonth();
					if(month === 0){
						year = year-1;
						month = 12;
					}
					var befYearMonth = year+""+month;
					
					if(dealElecCount.yearMonthPf.length == 0){
						yearMonthPf.push('1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月');
					}
					legendName.push(dealElecCount.reportPqName[0],dealElecCount.purchasePqName[0],dealElecCount.delivryPqName[0]);
						for(var i in dealElecCount.yearMonthPf){
							yearMonthPf.push(dealElecCount.yearMonthPf[i])
							reportPq.push(dealElecCount.reportPq[i]);
							purchasePq.push(dealElecCount.purchasePq[i]);
							delivryPq.push(dealElecCount.delivryPq[i]);
							//上个月收益是否结算 
							if(dealElecCount.yearMonthPf[i] == befYearMonth){
								if(dealElecCount.delivryPq[i] == "0.000"){
									$(".topApp  div:nth-child(2) span").first().css("box-shadow","0 0 10px 5px rgb(164,231,224)");
								}
							}  
							
						}
							reportObj = {barMaxWidth : "30%",name : dealElecCount.reportPqName[0] ,type : "bar" ,data : reportPq, barMaxWidth:30,barGap:0};
							purchaseObj = {barMaxWidth : "30%",name : dealElecCount.purchasePqName[0] ,type : "bar" ,data : purchasePq, barMaxWidth:30,barGap:0};
							delivryPqObj = {barMaxWidth : "30%",name : dealElecCount.delivryPqName[0] ,type : "bar" ,data : delivryPq, barMaxWidth:30,barGap:0};
							
					option = {
							 color :['#bababa','#00bb96','#0081c9'],
						    tooltip : {
						        trigger: 'axis',
// 						        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
// 						            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
// 						        }
								formatter: function (params) {
						    		var agre = params[0].data == "" ? '': params[0].data + '兆瓦时';
						        	var phPq   = params[1].data == "" ? '': params[1].data + '兆瓦时';
						        	var poxPq   = params[1].data == "" ? '': params[1].data + '兆瓦时';
						        	return params[0].axisValue + ' :</br>' 
						        		+ '&nbsp' + params[0].seriesName + ' : ' + agre
						        		+ '</br>'
						        		+ '&nbsp' + params[1].seriesName + ' : ' + phPq
						        		+ '</br>'
						        		+ '&nbsp' + params[2].seriesName + ' : ' + poxPq; 
					     		}
						    },
						    legend: {
						    	x:"right",
						        data:legendName,
						        y : 10,
						    },
						    grid: {
						        left: '3%',
						        right: '4%',
						        bottom: '1%',
						        height:'80%',
						        containLabel: true
						    },
						    xAxis : [
						        {
						            type : 'category',
						            data : yearMonthPf
						        }
						    ],
						    yAxis : [
						        {
						        	type : 'value',
						        	name: '（兆瓦时）',
						            min: 0,
						        }
						    ],
						    series : [
						              
								reportObj,
								purchaseObj,
								delivryPqObj
						        
						    ]
						};
					this.myEchart3.setOption(option);
				},
				/* =================APP页面跳转================= */
				//用户档案   
				consFile : function(){
					//用于权限管理 是否可打开页签
					if(this.arrResource.indexOf('view/cloud-purchase-web/archives/scconsumerinfo/list') < 0){
						return;
					}
					if(!this.uuid.cons){
						this.uuid.cons=Math.random();
					}	
		    		MainFrameUtil.openTabPage(this.uuid.cons, basePath+"/view/cloud-purchase-web/archives/scconsumerinfo/list", "用户档案",false);
				},
				//合同辅助设计
				agreDesign : function(){
					//用于权限管理 是否可打开页签
					if(this.arrResource.indexOf('view/cloud-purchase-web/crm/ssagrescheme/list') < 0){
						return;
					}
					//uuid 唯一
					if(!this.uuid.agre){
						this.uuid.agre=Math.random();
					}
					MainFrameUtil.openTabPage(this.uuid.agre, basePath+"view/cloud-purchase-web/crm/ssagrescheme/list", "合同辅助设计",false);
				},
				//购电合同
				purchaseAgre : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/agreement/phmppa/list') < 0){
						return;
					}
					if(!this.uuid.ph){
						this.uuid.ph=Math.random();
					}
					MainFrameUtil.openTabPage(this.uuid.ph, basePath+"view/cloud-purchase-web/agreement/phmppa/list", "购电合同管理",false);
				},
				//售电合同
				sellAgre : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/agreement/smppa/list') < 0){
						return;
					}
					if(!this.uuid.sell){
						this.uuid.sell=Math.random();
					}					
		    		MainFrameUtil.openTabPage(this.uuid.sell, basePath+"/view/cloud-purchase-web/agreement/smppa/list", "售电合同管理",false);
				},
				//月度购电
				monthPurchase : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/bid/phmpurchaseplanflow/list') < 0){
						return;
					}
					if(!this.uuid.mp){
						this.uuid.mp=Math.random();
					}					
					MainFrameUtil.openTabPage(this.uuid.mp, basePath+"view/cloud-purchase-web/bid/phmpurchaseplanflow/list", "购电交易管理",false);
				},
				//收益结算
				profitClose : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/settlement/list') < 0){
						return;
					}
					if(!this.uuid.pc){
						this.uuid.pc=Math.random();
					}
					MainFrameUtil.openTabPage(this.uuid.pc, basePath+"view/cloud-purchase-web/settlement/list", "月度收益结算",false);
				},
				//负荷预测
				loadForecase : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/crm/custelecquery/list') < 0){
						return;
					}
					if(!this.uuid.lf){
						this.uuid.lf=Math.random();
					}
		    		MainFrameUtil.openTabPage(this.uuid.lf, basePath+"/view/cloud-purchase-web/crm/custelecquery/list", "用户负荷预测",false);
				},
				//偏差预警
				deviationWarn : function(){
					if(this.arrResource.indexOf('view/cloud-purchase-web/deviationcheck/deviationwarninginfo/list') < 0){
						return;
					}
					if(!this.uuid.dw){
						this.uuid.dw=Math.random();
					}
		    		MainFrameUtil.openTabPage(this.uuid.dw, basePath+"/view/cloud-purchase-web/deviationcheck/deviationwarninginfo/list", "偏差预警信息",false);
				},
				
				/* ==================TOP页面跳转================= */
				consScalePage : function(){
					if(!this.uuid.scale){
						this.uuid.scale=Math.random();
					}
		    		MainFrameUtil.openTabPage(this.uuid.scale, basePath+"/view/cloud-purchase-web/archives/overview/consScalePage", "用户规模明细信息",false);
				},
				getSellMonLcBid : function(){
					if(!this.uuid.monLcBid){
						this.uuid.monLcBid=Math.random();
					}
		    		MainFrameUtil.openTabPage(this.uuid.monLcBid, basePath+"/view/cloud-purchase-web/archives/overview/yearSell", "售电量分月明细",false);
				},
				getYearPhDeail : function(){
					if(!this.uuid.yearPhDeail){
						this.uuid.yearPhDeail=Math.random();
					}
		    		MainFrameUtil.openTabPage(this.uuid.yearPhDeail, basePath+"/view/cloud-purchase-web/archives/overview/yearPh", "购电量分月明细",false);
				}
				
			}
			
			
		});
	</script>
</body>
</html>