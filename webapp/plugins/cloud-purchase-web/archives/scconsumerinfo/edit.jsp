<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>档案管理-用户档案编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
	li{
		 cursor:default
	}
</style>
</head>
<body id="consMainEditVue" v-cloak>
	<mk-tabpanel :selected.sync="initIndext" >
        <mk-tabpage index=0 header="基本信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/cons-edit" iframe="false"></mk-tabpage>
        <mk-tabpage index=1 header="历史用电信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/elec-list" iframe="false"></mk-tabpage>
    </mk-tabpanel>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consMainEditVue=new Vue({
			el: '#consMainEditVue',
			data: {	
				initIndext:0,//选中页的绑定属性
				consId:null
			},
			ready:function(){
				this.consId = MainFrameUtil.getParams("cons-edit").id;
				var methods = {consId:this.consId}; 
    			MainFrameUtil.setParams(methods,"cons-edit");
			},
			methods:{
				save:function(){
					consEditVue.saveConsumerInfo();
				}
			},
		    events:{
		        //监听选项卡切换事件
		        tabPanelSelectedChangeBefor:function(val){
		        	if(val.index == 0){    //无论何种情况“用户基本信息”都可用
		        		 MainFrameUtil.setDialogButtons([{text:"保存",type:"primary",handler:this.save},{text:"关闭",handler:function(){MainFrameUtil.closeDialog("cons-edit")}}],"cons-edit");
		        	}else{
		        		MainFrameUtil.setDialogButtons([{text:"关闭",handler:function(){MainFrameUtil.closeDialog("cons-edit")}}],"cons-edit");
		        	}
		        }
		    }
		});
	</script>
</body>
</html>