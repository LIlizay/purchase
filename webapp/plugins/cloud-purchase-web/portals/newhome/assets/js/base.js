//-----------------------------调用需要的基础路径---------------------------------

var groovyPath = "http://192.168.14.79/interface-platform-web/invoke/";
//退出后门户网站显示路径
var topUrl = "http://192.168.14.79/cloud-purchase-web/cloud-purchase-web/portals/newhome/";
//文件上传路径
var filePath = "http://tj.hhwy.org/fileservice/fileservice/fileext";
//电＋路径
var djUrl = "https://www.365power.cn/view/";
//工作人员登录路径
var loginUser="http://192.168.14.79/cloud-purchase-web/";
//用户登录路径
var staticUrl = "http://192.168.14.79/cloud-purchase-web/cloud-purchase-web/portals/loginuser/newmain.html";
//登录弹框
var dialogAA={ 
		id:"dialogAA",
		openDialog:function(href){
			var web = $(window.parent.parent.document);
			web.find(".mask").show();
			var dialogWidth = Number(document.documentElement.clientWidth);
			var dialogHeight = Number(document.documentElement.clientHeight);
			var left = 0;
			var top = 0;
			var height = 0;
			var width = 0;
			if(web.find("#dialogAA").length > 0){
				web.find("#dialogAA").show();
				if(href == "login.html"){//登录
					width = 450;
					height = 365;
				}else if(href == "register.html"){//注册
					width = 600;
					height = 680;
					registerIndex = 1;
				}
				left = ((dialogWidth-width)/2).toFixed(3);
				top = ((dialogHeight-height)/2).toFixed(3);
				web.find("#dialogAA").empty();
				web.find("#dialogAA").css("width",width+"px");
				web.find("#dialogAA").css("height",height+"px");
				web.find("#dialogAA").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe id="dialogCont" src="'+href+'" height="'+ height +'px" width="100%" frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" style="overflow:hidden;border:0px;margin:0px;"></iframe></div>');
			}else{
				if(href == "login.html"){//登录
					width = 450;
					height = 365;
				}else if(href == "register.html"){//注册
					width = 600;
					height = 680;
					registerIndex = 1;
				}
				left = ((dialogWidth-width)/2).toFixed(3);
				top = ((dialogHeight-height)/2).toFixed(3);
				web.find("body").append("<div id='dialogAA' style='border-radius:10px;width:"+ width +"px;height:"+ height +"px;border:0;' class='dialog'></div>");
				web.find("#dialogAA").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe src="'+href+'" height="'+ height +'" width="100%" frameborder="0" marginheight="0" marginwidth="0" frameborder="0" scrolling="no" style="overflow:hidden;border:0px;margin:0px;"></iframe></div>');
			}
			if(top < 0){
				top = 0;
			}
			if(left < 0){
				left = 0;
			}
			web.find(".dialog").css("left",left + "px");
			web.find(".dialog").css("top",top + "px");
			var height = web.find("body").height();
			web.find(".mask").css("height",height + "px");
		},
		closeDialog:function(){
			var web = $(window.parent.parent.document);
			web.find("#dialogAA").hide();
			web.find(".dialog").css("top","15%");
			web.find(".dialog").css("left","0%");
			var dis1 = web.find("#dialogData").css("display");
			var dis2 = web.find("#dialogAA").css("display");
			if(dis1 != "block" && dis2 != "block"){
				web.find(".mask").hide();
			}
		}
}
//消息提示框
var dialogPrompt={
		id:"dialogPrompt",
		openDialog:function(href){
			var web = $(window.parent.parent.document);
			var cont = web.find(".mask");
			web.find(".mask").show();
			web.find(".mask").css("z-index",1000005);
			var dialogWidth = Number(window.parent.parent.document.documentElement.clientWidth);
			var dialogHeight = Number(window.parent.parent.document.documentElement.clientHeight);
			var width = 350;
			var height = 230;
			var left = ((dialogWidth-width)/2).toFixed(3);
			var top = ((dialogHeight-height)/2).toFixed(3);
			if(top < 0){
				top = 0;
			}
			if(left < 0){
				left = 0;
			}
			if(web.find("#dialogPrompt").length > 0){
				web.find("#dialogPrompt").show();
				web.find("#dialogPrompt").empty();
				web.find("#dialogPrompt").css("width",width+"px");
				web.find("#dialogPrompt").css("height",height+"px");
				web.find("#dialogPrompt").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe id="dialogCont" src="'+href+'" height="'+ height +'px" width="100%" style="overflow: auto;border:0px"></iframe></div>');
			}else{
				web.find("body").append("<div id='dialogPrompt' style='border-radius:10px;width:"+ width +"px;height:"+ height +"px;border:0;' class='dialogPrompt'></div>");
				web.find("#dialogPrompt").append('<div style="width:'+ width +'px;height:'+ height +'px;text-align:center"><iframe src="'+href+'" height="'+ height +'" width="100%" style="overflow: auto;border:0px"></iframe></div>');
			}
			web.find(".dialogPrompt").css("left",left+"px").css("top",top+"px");
			var height = web.find("body").height();
			web.find(".mask").css("height",height + "px");
		},
		closeDialog:function(cont){
			var web = $(window.parent.parent.document);
			var cont = web.find(".mask");
			if(cont != null && cont.length > 0){
				web.find(".mask").css("z-index",9999);
				web.find("#dialogPrompt").hide();
				if(cont == "成功提示"){
					web.find("#dialogAA").hide();
				}
				var dis2 = web.find("#dialogAA").css("display");
				if(dis2 != "block"){
					web.find(".mask").hide();
				}
			}else{
				web.find(".mask").css("z-index",9999);
				web.find("#dialogPrompt").hide();
				if(cont == "成功提示"){
					web.find("#dialogAA").hide();
				}
				var dis2 = web.find("#dialogAA").css("display");
				if(dis2 != "block"){
					web.find(".mask").hide();
				}
			}
		}
}
$.extend({
	widget:function(){}
});