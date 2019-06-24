<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%
	String cloudsellingProvince=SystemConfigUtil.getConfig("cloudselling.province");//直接从数据库（分库）中获取，而不是从缓存中，缓存中的是主库的配置信息
	request.setAttribute("cloudsellingProvince", cloudsellingProvince);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-购电交易编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<style>
body{
	overflow-x:hidden;
	overflow-y:hidden;
	background:white;
}
</style>
<body id="phPlanEditVue">
	<div><hr style="margin:0px;height:10px"></div>
	<div id="process" style="height:60px;width:100%;margin-bottom:5px">
		<div id="process1" class="greenBlock">
			<div style="width:94%;float:left;height:100%">
				<div id="cons1" class="planCons"></div>
			</div>
			<img id="img1" src="img/arrow2.png" style="width:6%;float:left;height:60px"></img>
		</div>
		<div id="process2" class="grayBlock">
			<div style="width:94%;float:left;height:100%">
				<div id="cons2" class="examinGrayCons"></div>
			</div>
			<img id="img2" src="img/arrow.png" style="width:6%;float:left;height:60px"></img>
		</div>
		<div id="process3" class="grayBlock">
			<div style="width:94%;float:left;height:100%">
				<div id="cons3" class="transactionGrayCons"></div>
			</div>
			<img id="img3" src="img/arrow.png" style="width:6%;float:left;height:60px"></img>
		</div>
		<div id="process4" class="grayBlock" style="width:22%">
			<div id="cons4" class="phmdealinfoGrayCons" style="margin-left:15%"></div>
		</div>
	</div>
	<div style="width:auto; height:calc(100% - 80px); ">
		<iframe id="contentFrameId" frameborder="no" border="0" width="100%" height="100%" ></iframe>
	</div>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		var cloudsellingProvince = "${cloudsellingProvince}";		//数据库配置的省码
		var paramArray = location.search.substr(1).split('&');
		//对应 js
		var phPlanEditVue=new Vue({
			el: '#phPlanEditVue',
			data: {
				planId:null,
				planStatus:null,
				ym:null,
				mode:null		//02 ：双边协商交易方式
			},
			ready:function(){
				var me = this;
				me.planId = MainFrameUtil.getParams("plan_edit").planId;
				me.planStatus = MainFrameUtil.getParams("plan_edit").planStatus;
				me.ym = MainFrameUtil.getParams("plan_edit").ym;
				me.mode = MainFrameUtil.getParams("plan_edit").mode;
				me.initDivCss();
				me.initFrame();
			},
			methods: {
				initDivCss:function(){
					var me = this;
					switch(me.planStatus){
						case "00" :{ //计划制定
							document.getElementById("process1").style.backgroundColor = "#21ac8d";
							$("#process1").on("click",function(){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_00/edit?planId="+me.planId;
							});
							break;
						}
						case "01" :{ //电量审核
							me.process2();
							document.getElementById("process2").style.backgroundColor ="#21ac8d";
							$("#process2").on("click",function(){
								document.getElementById("process1").style.backgroundColor = "#8fdecc";
								document.getElementById("process2").style.backgroundColor = "#21ac8d";
								$('#img1').attr('src',"img/darkgreenarrow.png");
								if(me.ym.length > 4){
									document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/month_edit?planId="+me.planId;
								}else{
									document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/year_edit?planId="+me.planId;
								}
							});
							break;
						}
						case "02" :{//交易申报
							me.process2();
							me.process3();
							document.getElementById("process3").style.backgroundColor ="#21ac8d";
							
							$("#process3").on("click",function(){
								document.getElementById("process3").style.backgroundColor = "#21ac8d";
								document.getElementById("process2").style.backgroundColor = "#8fdecc";
								document.getElementById("process1").style.backgroundColor = "#8fdecc";
								$('#img1').attr('src',"img/arrow.png");
								$('#img2').attr('src',"img/darkgreenarrow.png");
								if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000" && cloudsellingProvince != "410000"){
									document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/edit?planId="+me.planId;
								}else{
									document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/edit?planId="+me.planId+"sl";
								}
							});
							break;
						}
						case "03" :{//成交确认
							me.process2();
							me.process3();
							me.process4();
							document.getElementById("process4").style.backgroundColor ="#21ac8d";
							break;
						}
						default :{//已完成
							me.process2();
							me.process3();
							me.process4();
							document.getElementById("process4").style.backgroundColor ="#21ac8d";
							break;
						}
					}
				},
				initFrame: function(){
					var me = this;
					if(me.planStatus == "00"){
						document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_00/edit?planId="+me.planId;
					}else if(me.planStatus == "01"){
						if(me.ym.length > 4){
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/month_edit?planId="+me.planId;
						}else{
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/year_edit?planId="+me.planId;
						}
					}else if(me.planStatus == "02"){
						if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000" && cloudsellingProvince != "410000"){
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/edit?planId="+me.planId;
						}else{
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/edit?planId="+me.planId+"sl";
						}
					}else{
						if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000" && cloudsellingProvince != "410000"){
							if(me.mode == "02"){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/edit_sb?planId="+me.planId;
							}else{
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/edit?planId="+me.planId;
							}
						}else{
							if(me.mode == "02"){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/sl_edit_sb?planId="+me.planId;
							}else{
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/sl_edit?planId="+me.planId;
							}
						}
					}
				},
				process2: function(){
					var me = this;
					$('#img1').attr('src',"img/darkgreenarrow.png");
					$('#img2').attr('src',"img/arrow2.png");
					document.getElementById("process2").className="greenBlock";
					document.getElementById("cons2").className="examinCons";
					$("#process1").on("click",function(){
						document.getElementById("process2").style.backgroundColor = "#8fdecc";
						document.getElementById("process1").style.backgroundColor = "#21ac8d";
						$('#img1').attr('src',"img/lightgreenarrow.png");
						document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_00/detail?planId="+me.planId;
					});
				},
				process3:function(){
					var me = this;
					$('#img1').attr('src',"img/arrow.png");
					if(me.mode != "02"){
						$('#img2').attr('src',"img/darkgreenarrow.png");
						$('#img3').attr('src',"img/arrow2.png");
						document.getElementById("process3").className="greenBlock";
						document.getElementById("cons3").className="transactionCons";
					}
					$("#process1").on("click",function(){
						if(me.mode != "02"){
							document.getElementById("process3").style.backgroundColor = "#8fdecc";
							$('#img2').attr('src',"img/arrow.png");
						}
						document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_00/detail?planId="+me.planId;
					});
					$("#process2").on("click",function(){
						if(me.mode != "02"){
							document.getElementById("process3").style.backgroundColor = "#8fdecc";
							$('#img2').attr('src',"img/lightgreenarrow.png");
						}
						document.getElementById("process2").style.backgroundColor = "#21ac8d";
						document.getElementById("process1").style.backgroundColor = "#8fdecc";
						$('#img1').attr('src',"img/darkgreenarrow.png");
						if(me.ym.length > 4){
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/month_detail?planId="+me.planId;
						}else{
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/year_detail?planId="+me.planId;
						}
					});
				},
				process4:function(){
					var me = this;
					document.getElementById("process4").className="greenBlock";
					document.getElementById("cons4").className="phmdealinfoCons";
					$('#img3').attr('src',"img/darkgreenarrow.png");
					$("#process1").on("click",function(){
						document.getElementById("process4").style.backgroundColor = "#8fdecc";
						$('#img3').attr('src',"img/lightgreenarrow.png");
						if(me.mode != "02"){
							$('#img3').attr('src',"img/arrow.png");
						}
						document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_00/detail?planId="+me.planId;
					});
					$("#process2").on("click",function(){
						document.getElementById("process4").style.backgroundColor = "#8fdecc";
						$('#img3').attr('src',"img/lightgreenarrow.png");
						if(me.mode != "02"){
							$('#img3').attr('src',"img/arrow.png");
						}
						if(me.ym.length > 4){
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/month_detail?planId="+me.planId;
						}else{
							document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/year_detail?planId="+me.planId;
						}
					});
					if(me.mode != "02"){
						$('#img2').attr('src',"img/arrow.png");
						$("#process3").on("click",function(){
							document.getElementById("process4").style.backgroundColor = "#8fdecc";
							document.getElementById("process3").style.backgroundColor = "#21ac8d";
							document.getElementById("process2").style.backgroundColor = "#8fdecc";
							document.getElementById("process1").style.backgroundColor = "#8fdecc";
							$('#img1').attr('src',"img/arrow.png");
							$('#img2').attr('src',"img/darkgreenarrow.png");
							$('#img3').attr('src',"img/lightgreenarrow.png");
							if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000" && cloudsellingProvince != "410000"){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/detail?planId="+me.planId;
							}else{
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/detail?planId="+me.planId+"sl";
							}
						});
					}
					$("#process4").on("click",function(){
						document.getElementById("process4").style.backgroundColor = "#21ac8d";
						if(me.mode != "02"){
							document.getElementById("process3").style.backgroundColor = "#8fdecc";
							$('#img2').attr('src',"img/arrow.png");
						}
						document.getElementById("process2").style.backgroundColor = "#8fdecc";
						document.getElementById("process1").style.backgroundColor = "#8fdecc";
						$('#img1').attr('src',"img/arrow.png");
						$('#img3').attr('src',"img/darkgreenarrow.png");
						if(cloudsellingProvince != "140000" && cloudsellingProvince != "210000" && cloudsellingProvince != "410000"){
							if(me.mode == "02"){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/edit_sb?planId="+me.planId;
							}else{
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/edit?planId="+me.planId;
							}
						}else{
							if(me.mode == "02"){
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/sl_edit_sb?planId="+me.planId;
							}else{
								document.getElementById("contentFrameId").src = "${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_03/sl_edit?planId="+me.planId;
							}
						}
					});
				}
			}
		});
	</script>
</body>
</html>