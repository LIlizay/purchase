<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>档案管理-用户档案信息查看</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
</style>
</head>
<body id="consMainDetailVue" v-cloak>
	<mk-tabpanel :selected.sync="initIndext" >
        <mk-tabpage index=0 header="基本信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/cons-detail" iframe="false"></mk-tabpage>
        <%-- <mk-tabpage index=1 header="合同信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/agre-detail" iframe="false"></mk-tabpage> --%>
         <mk-tabpage index=2 header="历史用电信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/elec-detail" iframe="false"></mk-tabpage>
<%--         <mk-tabpage index=3 header="结算信息" href="${Config.baseURL}view/plugins/cloud-purchase-web/archives/scconsumerinfo/balance-detail" iframe="false"></mk-tabpage> --%>
    </mk-tabpanel>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consMainDetailVue=new Vue({
			el: '#consMainDetailVue',
			data: {	
				initIndext:0,//选中页的绑定属性
				consId:null
			},
		    events:{
		        //监听选项卡切换事件
		        tabPanelSelectedChangeBefor:function(val){
		        	if(val.index == 0){    //无论何种情况“用户基本信息”都可用
		        		return;
		        	}else{
		        		MainFrameUtil.setParams({consId:this.consId},"elecinfo-detail");
		        	}
		        }
		    }
		});
	</script>
</body>
</html>