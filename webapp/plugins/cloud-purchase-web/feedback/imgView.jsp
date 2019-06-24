<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈-图片预览</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	<div id="imagePrev" style="text-align: center;">
		<img style="max-height: 520px; max-width: 1170px;" src="${Config.getConfig('file.service.url')}/{{param.id}}"/> 
	</div>
	<script>
    var basePath="${Config.baseURL}";
    var imagePreview = new Vue({
        el: '#imagePrev',
        data:{
            param:{id:null},
        },
        ready:function(){
            var that = this;
            that.param.id = MainFrameUtil.getParams("imgView").id;
            console.log(that.param.id)
          //按钮初始化
			MainFrameUtil.setDialogButtons(that.getButtons(),"imgView");
        },
        methods : {
        	getButtons : function(){
				var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("imgView");}}];
				return buttons;
			}
        	
        }
    });
</script>
	
</body>
</html>