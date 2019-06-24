<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>购售电交易-月度购电管理委托电量审核电量交易申报提醒</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
</head>
<body>
<mk-panel :title.sync="title" line="true" height="auto">
	<su-textbox :data.sync="params.remindInfo" type="textarea" rows="7"></su-textbox>
</mk-panel> 
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var ym = '${param.ym}';
var mainVue = new Vue({
	el:"body",
	data:{
		title:"",
		formFields:{},
		type:"",//消息提示类型
		params:{
			title:"",
			ym:"",
			planId:"",
			remindInfo:"",
			receivePerson:"",
			phmAgrePqExamineDetailList:[],//用户列表信息
		}
	},
	ready:function(){
		var me = this;
		me.params.phmAgrePqExamineDetailList = MainFrameUtil.getParams("remind").rows;
		me.type = MainFrameUtil.getParams("remind").type;
		me.params.ym = MainFrameUtil.getParams("remind").ym;
		me.initRemindInfo();
		//按钮组
        MainFrameUtil.setDialogButtons(me.getButtons(),"remind");
	},
	methods:{
		
		initRemindInfo:function(){
			var me = this;
			var ym = me.params.ym;
			var y = ym.substr(0,4);
			var info;
			var date = new Date();
			month = parseInt(date.getMonth())+1;
			if(me.type == "saveMessage"){
				me.title = "电量申报提醒";
				if(ym.length > 4){
					var m = parseInt(ym.substr(4,2));
					var y1 = y, m1 = m - 1;
					if(m1<1){
						y1 -= 1;
						m1 += 12;
					}
					info = "尊敬的用户，您"+y+"年"+m+"月的交易电量尚未申报，请您于本月17日（"+y1+"年"+m1+"月17日）24时前申报交易电量并上传交易委托协议，感谢您的配合。";
				}else{
					info = "尊敬的用户，您"+y+"年的交易电量尚未申报，请您于本月17日（"+y+"年"+month+"月17日）24时前申报交易电量并上传交易委托协议，感谢您的配合。";
				}
			}else if(me.type == "remindReportPq"){
				me.title = "电量重报提醒";
				if(ym.length > 4){
					var m = parseInt(ym.substr(4,2));
					var y1 = y, m1 = m - 1;
					if(m1<1){
						y1 -= 1;
						m1 += 12;
					}
					info = "尊敬的用户，您"+y+"年"+m+"月申报的交易电量存在错误，请您于本月17日（"+y1+"年"+m1+"月17日）24时前重新申报电量并上传交易委托协议，感谢您的配合。";
				}else{
					info = "尊敬的用户，您"+y+"年的交易电量尚未申报，请您于本月17日（"+y+"年"+month+"月17日）24时前申报交易电量并上传交易委托协议，感谢您的配合。";
				}
			}
			me.params.remindInfo = info;
			me.params.title = me.title;
		},
		save:function(){
			var me = this;
			var url = "";
			if(me.params.remindInfo == null && me.params.remindInfo == ""){
				MainFrameUtil.alert({
                    title:"提示：",
                    body:"提示信息不能为空！",
                });
            	return;
			}
			if(me.type == "saveMessage"){
				url = "saveMessage";
			}else if(me.type == "remindReportPq"){
				url = "remindReportPq";
			}
			$.ajax({
				url:basePath+"cloud-purchase-web/phmAgrePqExamineController/" + url,
				type:"post",
				data:$.toJSON(me.params),
				contentType:"application/json;charset=UTF-8",
				success:function(data){
					MainFrameUtil.alert({
                        title:"提示：",
                        body:data.msg,
                    });
					MainFrameUtil.closeDialog("remind");
				},
                error : function() {
                	MainFrameUtil.alert({title:"失败提示",body:"网络连接错误,请刷新页面重试"});
                	MainFrameUtil.closeDialog("remind");
                }
			});
		},
		//按钮组
    	getButtons:function(){
    		var buttons = [{text:"确定",type:"primary",handler:this.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("remind")}}];
            return buttons;
    	}
	}
})
</script>
</body>
</html>