<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div style="padding:20px;">
<div style="overflow: auto;height: 300px;">
<choose-user id="userComp" combobox="false" combotree="false" width="100%" height="546px" :options="options"></choose-user>

<!-- 	<choose-user  combobox="false" :option=""></choose-user>	 -->
</div>
</div>
<script type="text/javascript">
var orgNoVue = new Vue({
	el:'body',
	ready:function(){
		MainFrameUtil.setDialogButtons(this.getButtons(),"mk-power-up-dialog-id");
	},
	methods:{
		getButtons:function(){
			var that=this;
            var buttons=[{
            	text:"确定",
            	type:"primary",
                handler:function(btn,params){
                	var data = $('#userCompGrid').datagrid("getSelected");                	
                	if(data!=null){
                		console.log(data);
                		/* 获取节点id */
                		params.okHandler(data);
                		MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                	}else{
                		MainFrameUtil.alert({
                            title:"提示",
                            body:"请选择用户！"
                        }); 
                	}
                }
            },{
                text:"取消",
                type:"default",
                handler:function(btn,params){
                	MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                }
            }];
            return buttons;
		}
	}
})
</script>
</body>
</html>