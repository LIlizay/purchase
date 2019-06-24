<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="com.hhwy.framework.persistent.DAO"%>
<%@page import="com.hhwy.framework.container.AppContainer"%>
<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@page import="com.hhwy.fweb.cached.api.core.CacheClient"%>
<%@page import="com.hhwy.fweb.cached.api.core.CacheClientFactory"%>

<%@page import="java.text.ParseException"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Calendar"%>
<%@page import="com.hhwy.mainframe.utils.SystemConfigUtil"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String indexPath=ConfigHelper.getConfig("mainframe.index.path");
	request.setAttribute("indexPath", indexPath);
	
	String initialAddress=ConfigHelper.getConfig("mainframe.initial.address");
	
	String loginCode=ConfigHelper.getConfig("mainframe.login.code");
	
	if(loginCode==null){
		loginCode="true";
	}
	if(initialAddress==null){
		initialAddress="plugin-core/dict";
	}
	
	boolean loginCodeb="true".equals(loginCode.toLowerCase());
	
	request.setAttribute("loginCode", loginCodeb);
	request.setAttribute("initialAddress", initialAddress);
	//String fileService=ConfigHelper.getConfig("file.service.url");       //原调用方式
	String fileService=SystemConfigUtil.getConfig("file.service.url");
	//String logoImgId=ConfigHelper.getConfig("mainframe.logo.id");        //原调用方式
	String logoImgId=SystemConfigUtil.getConfig("mainframe.logo.id");
	String logoSrc = "static/login/img/logo.png";
	if(fileService != null && fileService.length() > 0 &&logoImgId != null && logoImgId.length() > 0)
		logoSrc = fileService + logoImgId;
	request.setAttribute("logoSrc",logoSrc);
	request.setAttribute("copyright", SystemConfigUtil.getConfig("mainframe.copyright.text"));
	String version = SystemConfigUtil.getConfig("mainframe.version.text") == null ? "V0.0.11" : SystemConfigUtil.getConfig("mainframe.version.text");
	request.setAttribute("version",version);	
	String scrollNote=SystemConfigUtil.getConfig("cloudselling.loginpage.scrollnote");	//首页滚动文字
	
	//系统到期判断 by-zhangzhao 2018/12/07
	String doamin = request.getServerName();//域名
	DAO<?> dao = AppContainer.getBean(DAO.class);
	List<Map<String, Object>> resultList = (List<Map<String, Object>>)dao.getBySql("systemcompanyContract.sql.getConsInfo", doamin);
	String expireFlag = "";
	if(resultList != null && resultList.size() > 0){
		Map<String, Object> map = resultList.get(0);
		System.out.println(map);
		if(!map.isEmpty()){
			//有系统停止日期时 才处理
			if(map.get("systemExpiryDate") != null && !"".equals(map.get("systemExpiryDate"))){
				//公司名称
				request.setAttribute("companyName", map.get("companyName").toString());
				//系统版本转换成中文
				List<Map<String, Object>> bySql = (List<Map<String, Object>>) dao.getBySql("systemcompanyContract.sql.getVersionSPCodeNameSql", null);
				Map<String, Object> versionMap = new HashMap<>();
				for(Map<String, Object> row : bySql){
					versionMap.put(row.get("value").toString(), row.get("name"));
				}
				String versionCode = map.get("versionCode").toString();
				if(versionCode != null && !"".equals(versionCode)){
					String[] split = versionCode.split(",");
					String versionName = "";
					//拼接页面展示，系统版本字段
					for(String code : split){
						if("".equals(versionName)){
							versionName += versionMap.get(code).toString();
						}else {
							versionName = versionName + "，" + versionMap.get(code).toString();
						}
					}
					request.setAttribute("versionName",versionName);
				}
				// 计算是否需要提示
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Calendar cal = Calendar.getInstance();
				//系统停止时间
				String endDate = map.get("systemExpiryDate").toString();	//YYYY-MM-dd
				request.setAttribute("systemExpiryDate", endDate);
				long parse = sdf.parse(endDate).getTime();
				int day = (int) ((cal.getTime().getTime() - parse) / (1000*3600*24));
				 if(day > 0){
					System.out.println("过期");
					expireFlag = "past";
					//更新
					dao.updateBySql("systemcompanyContract.sql.updateConsExpire", map.get("id").toString() );
					//清除缓存
					CacheClient redisCache = CacheClientFactory.getInstance().getResource();//封装好的redis接口
				    redisCache.del("companyDomainList");
				}else if(day <= 0 && day > -7){
					System.out.println("即将过期");
					expireFlag = "quick";
				}
			}
		}
	}
	request.setAttribute("expireFlag", expireFlag);
%>

<!DOCTYPE>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	
	<!-- meta -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" type="image/x-icon" href="http://ysd.hhwy.org/cloud/favicon.ico"/>
	<title>登录</title>
	<% 
		//登录页面
		String loginStylePath = SystemConfigUtil.getConfig("mainframe.login.style");
		if(loginStylePath==null || "".equals(loginStylePath)){
		}else{
				request.setAttribute("loginStylePath", loginStylePath);
		} %>
	<script type="text/javascript">
	   var initialAddress="${initialAddress}";
	   var loginCode=${loginCode};
	   var viewBasePath="${Config.basePath}view/business-mainframe-web/";
	   var basePath="${Config.basePath}";
	   var indexPath="${indexPath}";
	   indexPath = basePath + (indexPath.substring(0,1) === '/' ?indexPath.substring(1):indexPath);
	   if(window !=top){  
	      top.location.href = location.href;  
	   } 
	</script>
	
	<script src="${Config.getConfig('static.file.url')}/lib/jquery/jquery.min.js"></script>
	
	<script type="text/javascript" src="${Config.getConfig('static.file.url')}/lib/jquery/jquery.json-2.2.js"></script>
	
	<script type="text/javascript" src="${Config.basePath}view/business-mainframe-web/modules/login/js/base64.js"></script>
	
	<script type="text/javascript" src="${Config.basePath}view/business-mainframe-web/modules/login/js/security.js"></script>
	
	<script type="text/javascript" src="${Config.basePath}view/business-mainframe-web/modules/login/js/login.js"></script>

	<!-- css -->
	<link type="text/css"  rel="stylesheet" href="static/login/css/bootstrap.min.css"/>
	<link type="text/css"  rel="stylesheet" href="static/login/css/ionicons.min.css"/>
	<link type="text/css"  rel="stylesheet" href="static/login/css/owl.carousel.css"/>
	<link type="text/css"  rel="stylesheet" href="static/login/css/owl.theme.css"/>
	<link type="text/css"  rel="stylesheet" href="static/login/css/animate.css"/>
	<link type="text/css"  rel="stylesheet" href="static/login/css/style.css"/>
	
	<!-- All the scripts -->

	<script type="text/javascript" src="static/login/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="static/login/js/wow.min.js"></script>
	<script type="text/javascript" src="static/login/js/owl.carousel.js"></script>
	<script type="text/javascript" src="static/login/js/script.js"></script>
	<style type="text/css">
		.mask {       
		position: absolute; top: 0px; filter: alpha(opacity=60); background-color: #777;     
		z-index: 10000; left: 0px;     
		opacity:0.5; -moz-opacity:0.5; 
		height: 100%;
		width: 100%;  
		display: none  
}
	</style>
</head>
<script type="text/javascript"> 
	function ScrollImgLeft() {
		var speed = 50;
		var scroll_begin = document.getElementById("scroll_begin");
		var scroll_end = document.getElementById("scroll_end");
		var scroll_div = document.getElementById("scroll_div");
		scroll_end.innerHTML = scroll_begin.innerHTML;
		function Marquee() {
			if (scroll_end.offsetWidth - scroll_div.scrollLeft <= 0)
				scroll_div.scrollLeft -= scroll_begin.offsetWidth;
			else
				scroll_div.scrollLeft++;
		}
		var MyMar = setInterval(Marquee, speed);
		scroll_div.onmouseover = function() {
			clearInterval(MyMar);
		}
		scroll_div.onmouseout = function() {
			MyMar = setInterval(Marquee, speed);
		}
	}
</script> 
<body id="home">
	<!-- ****************************** 到期提示 ************************** -->
	<div id="expireDialogs" style="display:none;position: fixed;z-index: 100000;background: rgb(248, 248, 248);width: 800px; height: 380px;left: 25%;top: 30%;
	box-shadow: 0 2px 2px 0 rgba(0, 0, 0, 0.14), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);border: 1px solid #cccccc;border-radius: 4px;">
		<div style="float: left;width:40%;height: 100%;text-align: center;padding-top: 7%;">
			<img src="static/login/img/clock.png" class=" ">
		</div>
		<div style="float: left;width:55%">
			<div style="display: table;width: 100%;overflow: hidden;height: 50%;    padding-top: 3%;">
				<div id="content" style="display: table-cell;   vertical-align: middle;	font-family: 微软雅黑;color: rgb(65, 63, 63);">
<!-- 					内容 -->
				</div>
			</div>
			<div style="margin-left: 70%;margin-top: 2%;text-align: center;">
				<img src="static/login/img/erweima.png" style="width: 90%;">
				<div style="">恒华配售电</div>
			</div>
		</div>
		<div style="margin-left: 0px;margin-right: 0px; background-color: #f8f8f8;height: 50px;border-top: 1px solid #e9e9e9;">
			<div style="margin-left: 45%;margin-top: 3%; ">
				<button id="closeDialog" style="font-size: 16px;height: 28px;padding: 0px 12px;background-color: #21ac8d;border-color: #21ac8d;color: #fff;border: 1px solid transparent;">
					关闭
				</button>
			</div>
		</div>
	</div>
	<!-- ****************************** Header ************************** -->
	<div class="mask" style="display: none"></div>
	<header class="sticky" id="header">
		<section class="container">
			<section class="row" id="logo_menu">
				<section class="col-xs-8">
<!-- 					<a class="logo" href="#"> -->
<%-- 						<%  if(logoSrc!=null && !"".equals(logoSrc)){ %> --%>
<%-- 						        <img src="${logoSrc}" width="350px" height="60px"/> --%>
<%-- 						<%  --%>
<!-- // 							}else if(loginStylePath==null || "".equals(loginStylePath)){ -->
<%-- 						%> --%>
<!-- 								<img src="static/login/img/logo.png"  width="350px" height="60px"/> -->
<%-- 						<%	}else{ %> --%>
<%-- 								<img src="${loginStylePath}/images/logo.png" width="350px" height="60px"/> --%>
<%-- 						<%	} %> --%>
<!-- 					</a> -->
					<a class="logo" href="#">
						<img src="static/login/img/logo.png"  width="350px" height="60px"/>
					</a>
				</section>
			</section>
		</section>
	</header>
	<!-- ****************************** Gallery Section ************************** -->

	<section id="loginbox" class="block">
		<section class="container">
		<a class="slidedown wow animated zoomIn" data-wow-delay="0s" href="#banner">
			<img width="40" height="55" src="static/login/img/arrow.png"/><!-- <i class="ion-ios-download-outline"></i> -->
		</a>
			<section class="row ">
				<div class="notes hidden-xs hidden-sm">
					<%  if(scrollNote!=null && !"".equals(scrollNote)){ %>
						<span class="noteicon"><img src="static/login/img/note-icon.png"/></span>
						<div class="scroll-text"> 
							<div style="width:900px;height:30px;margin:0 auto;white-space: nowrap;overflow:hidden;" id="scroll_div" class="scroll_div"> 
							<div id="scroll_begin"> 
							<%=scrollNote%>
							</div> 
							<div id="scroll_end"></div> 
							</div> 
							<script type="text/javascript">ScrollImgLeft();</script> 
						</div> 
					<% 
						}
					%>
				</div>
			</section>
			
			<section id="loginContent" class="row" style="text-align: center;">
				<div class="col-md-7 hidden-xs hidden-sm">
					<div class="hand-container" style="margin-left: 20px;">
					<img class="iphone-hand img_res wow animated zoomIn" data-wow-duration="1.2s" src="static/login/img/screenshot-1.png"></img>
					<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-sm-5 margin0">
					<div class="loginbox">
						<div class="login-tilte"><img src="static/login/img/login-title-bg.png"/></div>
						<div class="inputbox">
							<div class="inputbox1"><input type="text" id="username" class="form-control uname" value="请输入用户名" onfocus="if(this.value==defaultValue) {this.value='';this.type='text'}" onblur="if(!value) {value=defaultValue; this.type='text';}"/></div>
						  	<div class="inputbox2"><input type="text" id="password" class="form-control pword" value="请输入密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password'}" onblur="if(!value) {value=defaultValue; this.type='text';}"/></div>
						 	<!-- <div class="remember"><input type="checkbox" checked> 记住登录名</div> -->
						 	<!--<div class="login-save-username">
								<span class="icon checked"></span>记住用户名
							</div>-->
						  	<button class="btn" onclick="validateImgCode()">登录</button>
						  	<div class="clear" id="login-msg" style="height:12px;line-height:40px;text-align:center;color:red;"></div>
						</div>
					</div>
				</div>
			</section>
		</section>
	</section>
	<!-- ****************************** Banner ************************** -->

	<section id="banner" >
		<section class="container">
			<section class="row">
				<div class="col-md-6 hidden-xs hidden-sm">
					<div class="hand-container">
					<img class="iphone-hand img_res wow animated bounceInUp" data-wow-duration="1.2s" src="static/login/img/iphone_hand.png"></img>
					<div class="clearfix"></div>
					</div>
				</div>
				<div class="col-md-6">
					<div class="headings">
						<h1 class="wow animated fadeInDown">轻售电，更从容</h1>
						<p class="wow animated fadeInLeft">轻量级的云平台服务，串联购电侧、 电网侧、 需求侧多方主体， 实现电力能量流、 数据流、 价值流的传递，全面满足售电公司业务需求。</p>
						<div class="row">
							<div class="col-xs-6 col-sm-6 col-md-5">
								<div>
									<a href="javascript:void(0)" class="polo-btn store wow animated bounceInUp" id="androidQRCode">
										<img style="margin-bottom:3px" width="24" height="24" src="static/login/img/android.png"/><!-- <i class="ion-social-android"></i> --> Android</a>
								</div>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-5">
								<div>
									<a href="javascript:void(0)" class="polo-btn store wow animated bounceInUp" id="appleQRCode">
										<img style="margin-bottom:3px" width="24" height="24" src="static/login/img/apple.png"/><!-- <i class="ion-social-apple"></i> --> iPhone</a>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 云购电app二维码  display: none-->
				<img id="app1" style="position: fixed;z-index: 100000;display: none " width="500px" height="240px" src="static/login/img/phAndroidQRCodeApp.png"/>
				<img id="app2" style="position: fixed;z-index: 100000;display: none" width="500px" height="240px" src="static/login/img/phAppleQRCodeApp.png"/>
			</section>
		</section>
	</section>
	<!-- ****************************** character Section ************************** -->

	<section id="character" class="block">
		<section class="container">
			<section class="row">
				<div class="col-md-12">
					<div class="title-box">
						<h1 class="block-title wow animated rollIn">
							<span class="bb-top-left"></span>
							<span class="bb-bottom-left"></span>
							平台特点
							<span class="bb-top-right"></span>
							<span class="bb-bottom-right"></span>
						</h1>
					</div>
				</div>
			</section>
			<section class="row">
				<section class="col-md-3 col-sm-6">
					<div class="character-member wow animated fadeIn border-rr" data-wow-delay=="0.3s" style="background: #0CB0E0;">
						<img src="static/login/img/dev-1.png" class="img_res character-pic">
						<h2 class="wow animated fadeInDown" data-wow-delay=="0.7s">精准匹配规则</br/>支撑购电决策</h2>
						<p class="wow animated fadeIn" data-wow-delay=="0.7s">精准匹配各省电力交易规则，辅助售电公司进行购电决策，计算最优购电方案。</p>
					</div>
				</section>
				<section class="col-md-3 col-sm-6">
					<div class="character-member wow animated fadeIn border-rr" data-wow-delay=="0.3s" style="background: #9B9B9B;">
						<img src="static/login/img/dev-2.png" class="img_res character-pic">
						<h2 class="wow animated fadeInDown" data-wow-delay=="0.7s">多类合同模板<Br/>收益一览无余</h2>
						<p class="wow animated fadeIn" data-wow-delay=="0.7s">提供多种用户签约方式，分析各类合同收益情况，并支持后期售电公司收益结算。</p>
					</div>
				</section>
				<section class="col-md-3 col-sm-6">
					<div class="character-member wow animated fadeIn border-rr" data-wow-delay=="0.3s" style="background: #D58D16;">
						<img src="static/login/img/dev-3.png" class="img_res character-pic">
						<h2 class="wow animated fadeInDown" data-wow-delay=="0.7s">偏差可控<Br/>预警先行</h2>
						<p class="wow animated fadeIn" data-wow-delay=="0.7s">支持自动采集或手动上报用电数据，引入大数据分析技术制定电量偏差预警机制，应对偏差考核。</p>
					</div>
				</section>
				<section class="col-md-3 col-sm-6">
					<div class="character-member wow animated fadeIn border-rr" data-wow-delay=="0.3s"  style="background: #5DA60E;">
						<img src="static/login/img/dev-4.png" class="img_res character-pic">
						<h2 class="wow animated fadeInDown" data-wow-delay=="0.7s">实时监控<Br/>数据可视</h2>
						<p class="wow animated fadeIn" data-wow-delay=="0.7s">实时展示用户用电量、电流电压等数据信息，多种图表形式直观展示，各类数据一目了然。</p>
					</div>
				</section>
			</section>
		</section>
	</section>

	<!-- ****************************** Features Section ************************** -->

	<section id="features" class="block">
		<section class="container f-white">
			<section class="row">
				<div class="title-box"><h1 class="block-title wow animated rollIn">
				<span class="bb-top-left"></span>
				<span class="bb-bottom-left"></span>
				核心功能
				<span class="bb-top-right"></span>
				<span class="bb-bottom-right"></span>
				</h1></div>
			</section>
			
			<section class="row">
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.3s">
						<i class="icons"><img src="static/login/img/pic01.png"/></i>
						<h2>月度购电管理</h2>
						<p>面向双边协商、竞价等交易品种，嵌入负荷预测和电价预测功能，结合手机app，满足购电计划制定、竞价交易申报等主营业务活动需要。</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.3s">
						<i class="icons"><img src="static/login/img/pic02.png"/></i></i>
						<h2>合同辅助设计</h2>
						<p>提供多种用户签约方式，自定义结算方式、分成方式、违约惩罚方式，辅助分析各类合同收益情况，一键转为正式售电合同。</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.3s">
						<i class="icons"><img src="static/login/img/pic03.png"/></i></i>
						<h2>结算管理</h2>
						<p>精准匹配各省电力交易规则，结合合同签订规则，分析不同电量分配方案下售电公司收益情况，实现最优结算方案。</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.6s">
						<i class="icons"><img src="static/login/img/pic04.png"/></i></i>
						<h2>偏差预警</h2>
						<p>通过需求侧电能采集系统或用户手机移动应用上报用电数据，按照预先设定的偏差范围为售电公司及用户进行电量偏差预警，提醒用户合理用电，辅助售电公司应对偏差考核。</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.6s">
						<i class="icons"><img src="static/login/img/pic05.png"/></i></i>
						<h2>负荷预测</h2>
						<p>对用电量影响因素进行全面、精确的分析，选取行业、用户类型、季节、温湿度、节假日、市场变化、企业生产状况等指标，构建用电量因素影响模型，实现售电量的精准预测。</p>
					</div>
				</div>
				<div class="col-sm-6 col-md-4">
					<div class="feature-box wow animated flipInX" data-wow-delay="0.6s">
						<i class="icons"><img src="static/login/img/pic06.png"/></i>
						<h2>配电监测</h2>
						<p>采用物联网、云计算技术，通过电力大数据及分析，实现智能用电安全管理，全面监测用户运行数据和状态，分析、比对用户运行特点，为节能和大数据应用提供基础。</p>
					</div>
				</div>
			</section>
			<div class="clearfix"></div>
		</section>
	</section>
	<!-- ****************************** Contact Section ************************** -->

	<section id="contact">
			<section class="container contact-wrap">
				<section class="row">
					<div class="title-box"><h1 class="block-title wow animated rollIn">
					<span class="bb-top-left"></span>
					<span class="bb-bottom-left"></span>
					联系我们
					<span class="bb-top-right"></span>
					<span class="bb-bottom-right"></span>
					</h1></div>
				</section>
			</section>
			<section class="container">
				<section class="row">
					<div class="col-sm-8">
						<ul class="address-list">
							<li><i class="ion-ios-location"></i><span>地址：北京市东城区安定门外大街138号皇城国际中心A座12层</span></li>
							<li><i class="ion-ios-telephone"></i><span>电话：400-810-1190</span></li>
							<li><i class="ion-email"></i><span>邮箱：PSDservice@ieforever.com</span></li>
						</ul>
					</div>
					<div class="col-sm-4">
						<div class="sec-code">
							<div class="title-box-w">关注我们</div>
							
							<div class="QRCode">
								<img src="static/login/img/erweima.png"/></br>
								<img class="footL" src="static/login/img/foot1.png"/>
							</div>
							<div class="QRCode">
								<img src="static/login/img/WechatIMG49.jpeg" style="margin-left:15px;"/></br>
								<img class="footR" src="static/login/img/foot2.png"/>
							</div>
						</div>
					</div>
				</section>
			</section>
			<div class="clearfix"></div>
		</section>

		<!-- ****************************** Footer ************************** -->

		<section id="footer">
			<section class="container">
				<section class="row" style="text-align: center;font-size: 12px; color: #fff;">
					<p>
						Copyright © 2000-2015│<a target="_blank" href="http://psd.ieforever.com/">北京恒华伟业科技股份有限公司版权所有</a> 版本：V2.4
						</br>京ICP备10054994号-4&nbsp;&nbsp;
                		<a target="_blank" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=11010102001882" style="display:inline-block;text-decoration:none;height:20px;line-height:20px;">
                			<img src="images/beian.png" style="float:left;"/>京公网安备 11010102001882号
                		</a>
                		</br>恒华云提供计算服务
                	</p>
				</section>
			</section>
		</section>
</body>
<script type="text/javascript"> 
	$(document).ready(function(e){
		var t=window.innerHeight;
		$("#loginbox").height(t-130+"px");
		if(t > 640){
			$("#loginContent").css("padding-top",(t-640)/2+"px");
		}
	});
	$(window).load(function(){
		//初始计算宽 高
		var dialogWidth = window.screen.width;
		var dialogHeight =  window.screen.height ;
		var mh = Number(document.documentElement.clientHeight);
		//安卓app二维码
		$("#app1").css("top",(dialogHeight -240) / 2+"px").css("left",(dialogWidth-500) / 2+"px");
		//苹果app二维码
		$("#app2").css("top",(dialogHeight -240) / 2+"px").css("left",(dialogWidth-500) / 2+"px");
		//遮罩		
		$(".mask").css("height",mh+"px");
// 		提示页面样式未完成  需要删除数据库一个字段 清理缓存
		var expireFlag = '${expireFlag}';
		if(expireFlag!=null && "" != expireFlag){ 
			var companyName = '${companyName}';
			var systemExpiryDate = '${systemExpiryDate}';
			var versionName = '${versionName}';
			var time = systemExpiryDate.substring(0,4) + '年' + systemExpiryDate.substring(5,7) + '月' + systemExpiryDate.substring(8,10);
			//显示 提示框
			$("#expireDialogs").show();
			//显示遮罩
			$(".mask").show();
			//禁止滚动
			$('body').css("overflow",'hidden');
			if("past" == expireFlag){ 
												//过期 
				$("#content").html(
						'<div style="font-size: 20px;font-weight: bold;">尊敬的'+companyName+':</div>'+
						'<div style="margin-top: 4%;font-size: 15px;">&emsp;您购买的云售电平台（'
						+ versionName +'）已于<span style="color:red">' + time + '日</span>到期，如需续费请联系销售经理或拨打客服电话400-810-1190。给您带来的不便敬请谅解。</div>'
				);
				
				$("#closeDialog").hide();
			}else if("quick" == expireFlag){
												//即将过期 关闭提示页面后，可以继续登录系统。
					$("#content").html(
						'<div style="font-size: 20px;font-weight: bold;">尊敬的'+companyName+':</div>'+
						'<div style="margin-top: 4%;font-size: 15px;">&emsp;您购买的云售电平台（'
						+ versionName +'）将于<span style="color:red">' + time + '日</span>到期，如需续费请联系销售经理或拨打客服电话400-810-1190。</div>'
				);
				//到期提示  关闭按钮
				$("#closeDialog").click(function(e){
					$('body').css("overflow",'auto');	//显示滚动条
					$("#expireDialogs").hide();			//弹框隐藏
					$(".mask").hide();					//遮罩隐藏
					
					//显示安卓二维码
					$("#androidQRCode").click(function(e){
						event.stopPropagation();
						$("#app1").show();
						$(".mask").show();

					})
					//显示苹果二维码
					$("#appleQRCode").click(function(e){
						event.stopPropagation();
						$("#app2").show();
						$(".mask").show();

					})
					//点击任意地方，隐藏二维码和遮罩
					$("body").click(function(e){
						$("#app2").hide();
						$("#expireDialogs").hide();//弹框隐藏
						$("#app1").hide();
						$(".mask").hide();
					})
				});
				
			} 
		}else{
			//显示安卓二维码
			$("#androidQRCode").click(function(e){
				event.stopPropagation();
				$("#app1").show();
				$(".mask").show();

			})
			//显示苹果二维码
			$("#appleQRCode").click(function(e){
				event.stopPropagation();
				$("#app2").show();
				$(".mask").show();

			})
			//点击任意地方，隐藏二维码和遮罩
			$("body").click(function(e){
				$("#app2").hide();
				$("#app1").hide();
				$(".mask").hide();
			})
		}
		

	});
		
</script>
</html>
