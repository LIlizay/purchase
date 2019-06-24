//-----------------------------调用需要的基础路径---------------------------------

var groovyPath = "http://192.168.14.79/interface-platform-web/invoke/";
//退出后门户网站显示路径
var basePath = "http://192.168.14.79/cloud-purchase-web/cloud-purchase-web/portals/loginuser/newmain.html";
//文件上传路径
var filePath = "http://tj.hhwy.org/fileservice/fileservice/fileext";
//外网路径
//退出后门户网站显示路径
var topUrl = "http://192.168.14.79/cloud-purchase-web/cloud-purchase-web/portals/newhome/main.html";
//登录参数
var loginObject={consId:"",loginName:"",userName:"",org_id:"",local:"",name:"",};
//列表对象
var tableOption=null;
//方法
$.extend({
	getLoginUser:function(){
		return window.parent.parent.loginObject;
	},
	//查询列表数据
	initList:function(url,method,queryParams){
		var result = null;
		if(method.toLocaleLowerCase() == "post"){
			$.ajax({
				url : url,
				type : method,
				async:false, 
				data:$.toJSON(queryParams),
		        contentType:'application/json;charset=UTF-8',  
		        success : function(data) {
					if(data.message){
						MainFrameUtil.alert({title:"失败提示",body:"查询失败！"});
					}else{
						result = new Object();
						if(data.value){
							result["data"] = data.value.datas;
							result["totalPage"] = data.value.totalPage;
						}else{
							result["data"]=[];
							result["totalPage"]=0;
						}
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
				}
			});
		}
		return result;
	},
	//设置右侧页面的高度
	initpageCss:function(){
		var web = $(window.parent.parent.document);
		var webHeight = Number(window.parent.parent.document.documentElement.clientHeight);
		var dialogWidth = Number(window.parent.parent.document.documentElement.clientWidth);
		var dialogHeight =webHeight;
		web.find("#wrapper").css("height",webHeight.toFixed(3) + "px").find(".navbar-side")
		.css("height",(webHeight-60).toFixed(3)+"px").css("overflow","hidden");
		web.find("#page-wrapper").css("height",(webHeight-60).toFixed(3)+"px");
		//重置页面高度
		var page = $(".row");
		if(page && page.length > 0){
			if(webHeight < 500) webHeight= 500;
			page.css("height",(webHeight-90).toFixed(3)+"px");
		}
		//对弹框进行重新定位
		if(web.find("#dialog").length > 0 && web.find("#dialog").css("display") != "none"){
			var width = Number(web.find("#dialog").css("width").replace("px",""));
			var height = Number(web.find("#dialog").css("height").replace("px",""));
			web.find("#dialog").css("top",((dialogHeight-height)/2).toFixed(3)+"px").css("left",((dialogWidth-width)/2).toFixed(3)+"px");
		}
		//对弹框进行重新定位
		if(web.find("#dialogPrompt").length > 0 && web.find("#dialogPrompt").css("display") != "none"){
			var width = Number(web.find("#dialogPrompt").css("width").replace("px",""));
			var height = Number(web.find("#dialogPrompt").css("height").replace("px",""));
			web.find("#dialogPrompt").css("top",((dialogHeight-height)/2).toFixed(3)+"px").css("left",((dialogWidth-width)/2).toFixed(3)+"px");
		}
		//设置列表样式
		if($(".grid-table") != null && $(".grid-table").length > 0 && $(".grid-table").next().find(".grid-cont") && $(".grid-table").next().find(".grid-cont").length > 0){
			$(".grid-table").each(function(){
				var width=(Number($(this).css("width").replace("px",""))-15).toFixed(8);
				$(this).next().find(".grid-cont").css("width",width+"px");
			});
		}
	},
	initTableCss:function(src){//计算表格高度
		var rowHeight = 0;
		var iframe = $(window.parent.parent.document).find("iframe[src='"+src+".html']");
		if($(".row") && $(".row").length > 0){
			rowHeight = Number($(".row").css("height").replace("px",""));
		}else if(iframe && iframe.length > 0){
			rowHeight = Number(iframe.css("height").replace("px",""))-3;
		}
		var height = rowHeight;
		if($(".panel-heading") && $(".panel-heading").length > 0){
			height = height-Number($(".panel-heading").css("height").replace("px",""))-1;
		}
		if($(".panel-body") && $(".panel-body").length > 1){
			height -= Number($(".panel-body").first().css("height").replace("px",""));
		}
		height -= 16;
		if($(".foot_paging") != null && $(".foot_paging").length > 0){//判断是否存在分页
			height -= 77;
		}
		$(".table-responsive").css("height",height.toFixed(8)+"px");
	},
	//设置iframs加载页面
	initIframe:function(){
		//获取参数
		var url = "";
		var requestParams = window.document.location.search.substr(1).split('&'); 
		if(requestParams[0].split("=")[0] != "local"){//判断是否为初始状态
			loginObject.local = "index";
			loginObject.consId = decodeURI(requestParams[0].split("=")[1]);
			loginObject.loginName = decodeURI(requestParams[1].split("=")[1]);
			loginObject.userName = decodeURI(requestParams[2].split("=")[1]);
			loginObject.org_id = decodeURI(requestParams[3].split("=")[1]);
		}else{
			loginObject.local = decodeURI(requestParams[0].split("=")[1]);
			loginObject.consId = decodeURI(requestParams[1].split("=")[1]);
			loginObject.loginName = decodeURI(requestParams[2].split("=")[1]);
			loginObject.userName = decodeURI(requestParams[3].split("=")[1]);
			loginObject.org_id = decodeURI(requestParams[4].split("=")[1]);
		}
		//设置菜单选中样式
		$("#"+loginObject.local+"Menu").addClass("active-menu");
		var third = $("#"+loginObject.local+"Menu").parent("li").parent("ul.nav-third-level");
		var second = $("#"+loginObject.local+"Menu").parent("li").parent("ul.nav-second-level");
		if(third && third.length > 0){//当前栏目呈级
			third.addClass("in").parent("li").parent("ul.nav-second-level").addClass("in");
		}else if(second && second.length > 0){
			second.addClass("in");
		}
		//加载iframe页面
		 $("#iframe").attr("src",loginObject.local+".html");
	},
	//获取时间
	initDate:function(obj){
		var result="";
		var date = new Date();
		var month = date.getMonth()+1;
		var day = date.getDate();
		if(month < 10){
			month = "0"+month;
		}
		if(obj.index == "current"){
			result = date.getFullYear() + "-" + month;
		}
		if(obj.type == "date"){
			if(day < 10){
				day = "0" + day;
			}
			result += "-" + day;
		}
		return result;
	},
	//生成echarts图
	initEcharts:function(dom,type,title,typeList,contList,xList){
		var legendList = new Array();
		var seriesList = new Array();
		if(typeList.length > 1){
			legendList = typeList;
		}
		for(var i=0; i<contList.length; i++){
			var obj = {name: typeList[i],type: type, data:contList[i],itemStyle:{normal:{label:{show:false}}}};
			seriesList.push(obj);
		}
		var myChart = $("#" + dom).createEcharts({
			title:{
				text:title,
				textStyle:{
					fontSize: 14,
				}
			},
			tooltip: {
				trigger: 'axis',
				axisPointer: { // 坐标轴指示器，坐标轴触发有效
					type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
				}
			},
			legend: {
			 	data:legendList,
		    },
		    grid:{
		    	y:60
		    },
		    xAxis: {
		        data: xList,
		    },
			series:seriesList,
		},type);
		return myChart;
	},
	//非空验证
	validateRequired:function(option){
		var result=new Object();
		for(var i in option){
			var val = "";
    		if($("#"+i).length > 0 && $("#" + i + " input").length > 0){
    			 val = $("#" + i + " input").val();
    		}else if($("#" + i + " select").length > 0){
    			val = $("#" + i + " select").val();
    		}
    		var me = $("#"+i);
			if((val === null || val === "") && (me.css("display") != "none" && me.parent("tr").css("display") != "none") && me.prev().find(".f-red").length > 0 && me.prev().find(".f-red").text().trim() == "*"){//判断是否为必填项
				result=me.prev().text().replace("*","").replace(":","").replace("：","").trim();
				break;
			}
			if(val !== null && val !== "") result[i] = val;
		}
		return result;
	}
});
$.fn.extend({
	createtable:function(option,selectParams){
		if(typeof option == "string"){
			if(tableOption){
				option = tableOption.load(tableOption,selectParams);
			}
		}else{
			$(this).empty();//清空
			var url = option.url;
			var data = null;
			var result = null;
			if(url){//请求后台查询数据
				result = $.initList(url,option.method,option.queryParams);
				data = result.data;
			}else{
				data = option.data;
				result = data;
			}
			if(option.onLoadBefore) data = option.onLoadBefore(data);//调用onLoadBefore方法对数据进行处理,返回处理的数组
			//生成列表-生成表头
			var column = option.column;
			var columnList = new Array();//数据列表
			var columnObject = new Object();//字段数据
			var columnWithObject = new Object();//字段宽度
			var titleHtml = "";
			var columnSize = 0;
			for(var i in column){
				var obj = column[i];
				var bg = "";
				if((parseInt(i)+1)%2 !== 0) bg = "class='bg-gray'";
				titleHtml += "<tr "+bg+">";
				if(option.rownumbers && i == 0){
					titleHtml += "<td rosspan='"+column.length+"' class='num' style='text-align:center;width: 40px;'>序号</td>";
				}
				for(var j in obj){
					var style="text-align:center;";
					if(obj[j].align) style = "text-align:" + obj[j].align+";";
					if(obj[j].hidden) style += "display:none;";
					var spans = "";
					var colspan = obj[j].colspan;
					var rowspan = obj[j].rowspan;
					if(colspan) spans += " colspan='"+colspan+"'";
					if(rowspan) spans += " rowspan='"+rowspan+"'";
					var colWidth = Number(obj[j].width);
					var title = obj[j].title;
					if(!colspan){//不跨列
						style += "width:" + colWidth + "px";
						columnList.push(obj[j]);
						if(title.indexOf("<br/>") >= 0){
							columnObject[title.replace("<br/>","")] = obj[j];
						}else{
							columnObject[title] = obj[j];
						}
						columnWithObject[obj[j].field]=colWidth;
						columnSize += colWidth;
					}
					titleHtml += "<td "+spans+" class='"+obj[j].field+"' style='"+style+"'>"+title+"</td>";
				}
				titleHtml +="</tr>";
			}
			$(this).append(titleHtml);
			//设置标题并计算单元格的宽度
			var fitColumns = option.fitColumns;
			if(fitColumns === undefined) fitColumns=true;
			if(option.height) $(this).css("height",option.height);
			$(this).css("width","100%");
			var titleWidth = Number($(this).css("width").replace("px",""));
			var otherTd = 0;
			var lastText = $(this).find("tr td[colspan!='colspan']").last().text();
			if(fitColumns){//按百分比计算各单元格的宽度
				$(this).find("tr td").each(function(){
					//判断该单元格是否跨列，不跨列则设置宽度
					if(!$(this).css("colspan") && !$(this).hasClass("num")){
						var tdWidth = (columnObject[$(this).text()].width/columnSize*(titleWidth-52)).toFixed(8);
						if($(this).text() != lastText){
							$(this).css("width",(tdWidth-18) + "px");
							otherTd += Number(tdWidth);
							columnWithObject[columnObject[$(this).text()].field]=tdWidth-18;
						}
					}
				});
				//最后单元格宽度设置
				columnWithObject[columnObject[lastText].field]=(titleWidth-otherTd-64).toFixed(3);
				$(this).find("tr td[colspan!='colspan']").last().css("width",(titleWidth-otherTd-64).toFixed(3)+"px");
			}else{//设置列表的宽度
				$(this).css("width",(columnSize+columnList.length*18+40-15) + "px").parent(".grid-table").css("width",(columnSize+columnList.length*18+40) + "px")
				.prev(".bg-info").css("width",(columnSize+columnList.length*18+40) + "px").parent(".table-responsive").css("overflow-x","auto");
			}
			//生成内容
			var gridId = $(this).attr("id")+"Cont";
			if($("#"+gridId) == null || $("#"+gridId).length == 0){
				var width=Number($(this).parent(".grid-table").css("width").replace("px",""));
				$(this).parent(".grid-table").after("<div id='"+gridId+"' style='width:"+width+"px'><div class='grid-cont' style='width:"+(width-15)+"px'><table class='table table-bordered table-hover'></table></div></div>");
			}
			var contHtml="";
			for(var i in data){
				var cont = data[i];
				contHtml += "<tr";
				//设置样式
				if(option.rowStyler !== undefined){
					var trStyle = option.rowStyler(i,cont);
					if(trStyle) contHtml += " style='"+ trStyle +"'";
				}
				if(column.length%2 != 0 && (parseInt(i)+1)%2 == 0){
					contHtml += " class='bg-light-gray'>";
				}else if(column.length%2 == 0 && (parseInt(i)+1)%2 != 0){
					contHtml += " class='bg-light-gray'>";
				}else{
					contHtml += ">";
				}
				if(option.rownumbers){
					var widthStyle="";
					if(i == 0){
						widthStyle="width:40px";
					}
					contHtml += "<td class='num' style='text-align:center;"+widthStyle+"'>"+(parseInt(i)+1)+"</td>";
				}
				for(var j in columnList){
					var colObj = columnList[j];
					var style="text-align:center;";
					if(colObj.align) style = "text-align:" + colObj.align+";";
					if(colObj.hidden) style += "display:none;";
					var content = cont[colObj.field];
					if(content === null || content === undefined || (typeof content == "string" && content.indexOf("null") >= 0)) content="";
					if(colObj.formatter !== undefined){
						content = colObj.formatter(content,cont,i);
					}
					if(i == 0){
						style += "width:"+columnWithObject[colObj.field]+"px";
					}
					contHtml += "<td class='"+colObj.field+"' style='"+style+"'>"+content+"</td>";
				}
				contHtml += "</tr>";
			}
			contHtml += "";
			$("#"+gridId+" table").empty().append(contHtml);
			//设置tbody高度
			var height=(Number($(this).parent(".grid-table").parent("div").css("height").replace("px",""))-35-Number($(this).css("height").replace("px",""))).toFixed(8);
			if(!fitColumns) height -=15;
			$("#"+gridId).css("height",height+"px").css("overflow","auto");
			//添加数据加载后的方法
			if(option.onLoadSuccess !== undefined){
				option.onLoadSuccess(result);
			}
			var me = $(this);
			option["load"] = function(option,selectParams){
				var selectResult = $.initList(url,option.method,selectParams);
				var selectData = selectResult.data;
				if(option.onLoadBefore) selectData = option.onLoadBefore(selectData);//调用onLoadBefore方法对数据进行处理,返回处理的数组
				//生成内容
				var contHtml = "";
				for(var i in selectData){
					var cont = selectData[i];
					contHtml += "<tr";
					//设置样式
					if(option.rowStyler !== undefined){
						var trStyle = option.rowStyler(i,cont);
						if(trStyle) contHtml += " style='"+ trStyle +"'";
					}
					if(column.length%2 != 0 && (parseInt(i)+1)%2 == 0){
						contHtml += " class='bg-light-gray'>";
					}else if(column.length%2 == 0 && (parseInt(i)+1)%2 != 0){
						contHtml += " class='bg-light-gray'>";
					}else{
						contHtml += ">";
					}
					if(option.rownumbers){
						var widthStyle="";
						if(i == 0){
							widthStyle="width:40px";
						}
						contHtml += "<td class='num' style='text-align:center;"+widthStyle+"'>"+(parseInt(i)+1)+"</td>";
					}
					for(var j in columnList){
						var colObj = columnList[j];
						var style="text-align:center;";
						if(colObj.align) style = "text-align:" + colObj.align+";";
						if(colObj.hidden) style += "display:none;";
						var content = cont[colObj.field];
						if(content === null || content === undefined || (typeof content == "string" && content.indexOf("null") >= 0)) content="";
						if(colObj.formatter !== undefined){
							content = colObj.formatter(content,cont,i);
						}
						if(i == 0){
							style += "width:"+columnWithObject[colObj.field]+"px";
						}
						contHtml += "<td class='"+colObj.field+"' style='"+style+"'>"+content+"</td>";
					}
					contHtml += "</tr>";
				}
				$("#"+gridId+" table").empty().append(contHtml);
				//设置tbody高度
				var height=(Number($(me).parent(".grid-table").parent("div").css("height").replace("px",""))-35-Number(me.css("height").replace("px",""))).toFixed(8);
				if(!fitColumns) height -=15;
				$("#"+gridId).css("height",height+"px").css("overflow","auto");
				//添加数据加载后的方法
				if(option.onLoadSuccess !== undefined){
					option.onLoadSuccess(selectResult);
				}
				return option;
			};
			tableOption = option;
		}
		return option;
	}
});
//弹框
var MainFrameUtil={
	commonOption:{},
	commonAlertOption:{},
	openDialog:function(option){//打开弹框
		var commonOption = {
			id:"dialog-common-up",
			params:null,
			herf:null,
			maximizable:false,
			width:"90%",
			height:620,
			onClose:function(){}
		};
		option = $.extend(true,commonOption,option);
		window.parent.parent.MainFrameUtil.commonOption[option.id]=option;
		var web = $(window.parent.parent.document);
		//判断是否存在弹框
		var zIndex = parseInt(web.find(".mask").css("z-index"));
		if(!zIndex) zIndex=1000;
		var pageIndex = zIndex+2;
		if(web.find(".mask").css("display") == "block"){
			zIndex += 3;
			pageIndex += 3;
		}
		//计算宽、高
		var widthData="";
		var heightData = "";
		var dialogWidth = Number(window.parent.parent.document.documentElement.clientWidth);
		var dialogHeight = Number(window.parent.parent.document.documentElement.clientHeight);
		var width = option.width;
		if(typeof width== "string"){
			width = (Number(width.replace("%",""))/100*dialogWidth).toFixed(2);
			widthData = option.width;
		}else{
			widthData =  option.width+"px";
		}
		var height = option.height;
		if(typeof height== "string"){
			height = (Number(height.replace("%",""))/100*dialogHeight).toFixed(2);
			heightData = option.height;
		}else{
			heightData = option.height+"px";
		}
		var left = ((dialogWidth-width)/2).toFixed(3);
		var top = ((dialogHeight-height)/2).toFixed(3);
		if(top < 0) top = 0;
		if(left < 0) left = 0;
		var id = option.id;
		var maxType="none";
		if(option.maximizable) maxType = "block";
		if(web.find("#"+id).length > 0){//因存在弹框
			web.find("#"+id).show();
			web.find("#"+id+"iframe").attr("src",option.herf);
		}else{
			web.find("body").append("<div id='"+id+"' style='overflow:hidden;width:"+widthData+";height:"+heightData+";border:0;border-radius:5px;z-index:"+pageIndex+";' class='dialog'></div>");
			height = height-50;
			var ifHeight = height - 30; 
			web.find("#"+id).append('<div style="width: 100%;height:'+height+'px;text-align:center">'+
					'<div style="width:100%;height:24px;padding-top: 6px;"><a style="margin-right: 10px;" onClick="MainFrameUtil.closeDialog(\''+id+'\')" href="javascript:void(0)" class="closebox"></a><span style="display:'+ maxType +'" onClick="MainFrameUtil.maxDialog(this,\''+id+'\')" class="maxDialog"></span></div>'
					+'<iframe src="'+option.herf+
			'" height="'+ ifHeight +'px" width="99%" id="#'+id+'iframe" style="overflow: auto;border:1px solid #F0EEEC;border-radius: 5px;"></iframe></div>');
			web.find("#"+id).append('<div style="background:white;height: 50px;width: 100%;text-align:center;">'
			+'<button class="dialogBtn CommonBtn" onclick="MainFrameUtil.closeDialog(\''+id+'\')">关闭</button></div></div>');
		}
		//弹框定位
		web.find("#"+id).css("left",left+"px").css("top",top+"px");
		var height = web.find("body").height();
		web.find(".mask").css("height",height + "px");
		web.find(".mask").css("z-index",zIndex);
		web.find(".mask").show();
	},
	setDialogButtons:function(buts,id){//重新定义弹框按钮
		var web = $(window.parent.parent.document);
		var div = web.find("#"+id).find("button").parent("div");
		web.find("#"+id).find("button").remove();
		var btn = "";
		for(var i in buts){
			var obj = buts[i];
			var type = "CommonBtn";
			var handler="";
			var style="style='margin-left:5px;'";
			if(obj.type) type=obj.type;
			btn += "<button "+style+" class='dialogBtn "+type+"' "+handler+">"+obj.text+"</button>";
		}
		div.append(btn);
		//设置时间
		for(var i in buts){
			var obj = buts[i];
			if(obj.handler){
				div.find("button").eq(i).bind("click",obj.handler);
			}
		}
	},
	closeDialog:function(id){//关闭弹框
		var web = $(window.parent.parent.document);
		web.find("#"+id).hide();
		//判断是否存在
		var zIndex = parseInt(web.find(".mask").css("z-index"));
		if(!zIndex) zIndex=1000;
		if(zIndex != 1000){
			zIndex -= 3;
		}else{
			web.find(".mask").hide();
		}
		web.find(".mask").css("z-index",zIndex);
		if(window.parent.parent.MainFrameUtil.commonOption[id] && typeof window.parent.parent.MainFrameUtil.commonOption[id].onClose != "undefined"){
			window.parent.parent.MainFrameUtil.commonOption[id].onClose();
		}
		web.find("#"+id).remove();
	},
	maxDialog:function(obj,id){//弹框最大最小化
		var web = $(window.parent.parent.document);
		var option=window.parent.parent.MainFrameUtil.commonOption[id];
		var widthData="";
		var heightData = "";
		var left = "";
		var top = "";
		var width = option.width;
		var height = option.height;
		var dialogWidth = Number(window.parent.parent.document.documentElement.clientWidth);
		var dialogHeight = Number(window.parent.parent.document.documentElement.clientHeight);
		if($(obj).hasClass("maxDialog")){//最小化
			left = 0;
			top = 0;
			widthData=dialogWidth+"px";
			heightData=dialogHeight+"px";
			width = widthData;
			height = dialogHeight;
			$(obj).removeClass("maxDialog").addClass("minDialog");
		}else if($(obj).hasClass("minDialog")){//最大化
			if(typeof width== "string"){
				width = (Number(width.replace("%",""))/100*dialogWidth).toFixed(2);
				widthData = option.width;
			}else{
				widthData = option.width+"px";
			}
			if(typeof height== "string"){
				height = (Number(height.replace("%",""))/100*dialogHeight).toFixed(2);
				heightData = height;
			}else{
				heightData = option.height+"px";
			}
			var left = ((dialogWidth-width)/2).toFixed(3);
			var top = ((dialogHeight-height)/2).toFixed(3);
			if(top < 0) top = 0;
			if(left < 0) left = 0;
			$(obj).removeClass("minDialog").addClass("maxDialog");
		}
		web.find("#"+id).css("top",top+"px").css("left",left+"px").css("height",heightData).css("width",widthData);
		web.find("#"+id+" > div").first().css("height",(height-50)+"px");
		web.find("#"+id+" iframe").css("height",(height-50-30)+"px");
	},
	getParams:function(id){//获取数据
		if(!id) id="dialog-common-up";
		return window.parent.parent.MainFrameUtil.commonOption[id].params;
	},
	setParams:function(param,id){//设置数据
		if(!id) id="dialog-common-up";
		if(!param){
			window.parent.parent.MainFrameUtil.commonOption[id].params=null;
		}else{
			window.parent.parent.MainFrameUtil.commonOption[id].params=$.extend(true,window.parent.parent.MainFrameUtil.commonOption[id].params,param);
		}
	},
	alert:function(option){//消息提示框
		window.parent.parent.MainFrameUtil.commonAlertOption["dialogPrompt"]=option;
		var web = $(window.parent.parent.document);
		//判断是否存在弹框
		var zIndex = parseInt(web.find(".mask").css("z-index"));
		if(!zIndex) zIndex=1000;
		var pageIndex = zIndex+3;
		if(web.find(".mask").css("display") == "block"){
			zIndex += 3;
			pageIndex += 3;
		}
		var dialogWidth = Number(window.parent.parent.document.documentElement.clientWidth);
		var dialogHeight = Number(window.parent.parent.document.documentElement.clientHeight);
		var width = 350;
		var height = 230;
		var left = ((dialogWidth-width)/2).toFixed(3);
		var top = ((dialogHeight-height)/2).toFixed(3);
		if(top < 0) top = 0;
		if(left < 0) left = 0;
		if(web.find("#dialogPrompt").length > 0){
			web.find("#dialogPrompt").empty();
			web.find("#dialogPrompt").css("width",width+"px");
			web.find("#dialogPrompt").css("height",height+"px");
			web.find("#dialogPrompt").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe id="dialogCont" src="message-prompt.html" height="'+ height +'px" width="100%" style="overflow: auto;border:0px"></iframe></div>');
			web.find("#dialogPrompt").show();
		}else{
			web.find("body").append("<div id='dialogPrompt' style='border-radius:10px;width:"+ width +"px;height:"+ height +"px;border:0;' class='dialogPrompt'></div>");
			web.find("#dialogPrompt").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe src="message-prompt.html" height="'+ height +'" width="100%" style="overflow: auto;border:0px"></iframe></div>');
		}
		web.find(".dialogPrompt").css("left",left+"px").css("top",top+"px");
		var height = web.find("body").height();
		web.find(".mask").css("height",height + "px");
		web.find(".dialogPrompt").css("z-index",pageIndex);
		web.find(".mask").css("z-index",zIndex);
		web.find(".mask").show();
	},
	closeAlertDialog:function(){
		var web = $(window.parent.parent.document);
		web.find("#dialogPrompt").hide();
		//判断是否存在
		var zIndex = parseInt(web.find(".mask").css("z-index"));
		if(zIndex != 1000){
			zIndex -= 3;
		}else{
			web.find(".mask").hide();
		}
		web.find(".dialogPrompt").css("z-index",1003);
		web.find(".mask").css("z-index",zIndex);
		if(window.parent.parent.MainFrameUtil.commonAlertOption["dialogPrompt"] && typeof window.parent.parent.MainFrameUtil.commonAlertOption["dialogPrompt"].onClose != "undefined"){
			window.parent.parent.MainFrameUtil.commonAlertOption["dialogPrompt"].onClose();
		}
	}
};

//甚至分页对象
var pageUtil={
	commonObject:{},
	bindClick:function(option,dom){
		var object = {
				totalPage:0,//总页数
				nowIndex:1,//当前页
				pageSize:10,//每页数
		};
		pageUtil.commonObject[dom]=object;
		$(dom).find("li").first().find("a").bind("click",function(){pageUtil.clickPage(option,dom,'last')});
		$(dom).find("li").last().find("a").bind("click",function(){pageUtil.clickPage(option,dom,'next')});
		$(dom).find("li").eq(1).find("a").bind("click",function(){pageUtil.clickPage(option,dom,1)});
		$(dom).find("li").eq(2).find("a").bind("click",function(){pageUtil.clickPage(option,dom,2)});
		$(dom).find("li").eq(3).find("a").bind("click",function(){pageUtil.clickPage(option,dom,3)});
		$(dom).find("li").eq(4).find("a").bind("click",function(){pageUtil.clickPage(option,dom,4)});
		$(dom).find("li").eq(5).find("a").bind("click",function(){pageUtil.clickPage(option,dom,5)});
		$(dom).find("li").eq(6).find("a").bind("click",function(){pageUtil.clickPage(option,dom,6)});
	},
	clickPage:function(option,dom,type){
		if(type == "last"){
			if($(dom).find("li").eq(0).hasClass("disabled")) return;
			pageUtil.commonObject[dom].nowIndex--;
		}else if(type == "next"){
			if($(dom).find("li").eq(7).hasClass("disabled")) return;
			pageUtil.commonObject[dom].nowIndex++;
		}else{
			if($(dom).find("li").eq(type).hasClass("disabled") || $(dom).find("li").eq(type).hasClass("active")) return;
			pageUtil.commonObject[dom].nowIndex = parseInt(type);
		}
		//设置页信息
		$(dom+"Page").html("共"+pageUtil.commonObject[dom].totalPage+"页，当前第"+pageUtil.commonObject[dom].nowIndex+"页");
		pageUtil.setPageCss(dom);
		if(option && option.handler) option.handler(pageUtil.commonObject[dom]);
	},
	setpage:function(param,dom){
		pageUtil.commonObject[dom].totalPage = param.totalPage;
		//设置页信息
		if(pageUtil.commonObject[dom].nowIndex > param.totalPage){
			pageUtil.commonObject[dom].nowIndex = param.totalPage;
		}
		$(dom+"Page").html("共"+pageUtil.commonObject[dom].totalPage+"页，当前第"+pageUtil.commonObject[dom].nowIndex+"页");
		pageUtil.setPageCss(dom);
	},
	setPageCss:function(dom){
		$(dom).find(".disabled").removeClass("disabled");
		for(var i=pageUtil.commonObject[dom].totalPage+1;i<7;i++){
			$(dom).find("li").eq(i).addClass("disabled");
		}
		//判断当前页
		$(dom).find(".active").removeClass("active");
		if($(dom).find("li").eq(pageUtil.commonObject[dom].nowIndex).length > 0 && 0 < pageUtil.commonObject[dom].nowIndex && pageUtil.commonObject[dom].nowIndex < 7 && !$(dom).find("li").eq(pageUtil.commonObject[dom].nowIndex).hasClass("disabled")){
			$(dom).find("li").eq(pageUtil.commonObject[dom].nowIndex).addClass("active");
		}
		//设置上一页、下一页
		if(pageUtil.commonObject[dom].nowIndex == 1 || pageUtil.commonObject[dom].totalPage == 0){
			$(dom).find("li").eq(0).addClass("disabled");
		}
		if(pageUtil.commonObject[dom].nowIndex == pageUtil.commonObject[dom].totalPage || pageUtil.commonObject[dom].totalPage == 0){
			$(dom).find("li").eq(7).addClass("disabled");
		}
	},
	resetPage:function(dom){
		pageUtil.commonObject[dom].nowIndex=1;
		pageUtil.commonObject[dom].pageSize=10;
	},
	getPage:function(dom){
		return pageUtil.commonObject[dom];
	}
}