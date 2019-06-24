<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图片预览</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
</head>
<%

	String fileServiceUrl=ConfigHelper.getConfig("file.service.url");
		
		request.setAttribute("fileUrl", fileServiceUrl);
	
	
%>
<body>

	<div id="imagePrev" style="width: 100%;height: 100%">
		<img  src="${fileUrl}${param.id}" style="width:100%;height:100%"/> 
	</div>
	
	      
</body>
<script>
    var basePath="${Config.baseURL}";
    var imagePreview = new Vue({
        el: '#imagePrev',
        data:{
            param:{id:null,name:''},
        },
        ready:function(){
            var that = this;
            that.param.id = MainFrameUtil.getParams("imagePreviewDialog").id;
            
            
        }
    });
</script>
</html>