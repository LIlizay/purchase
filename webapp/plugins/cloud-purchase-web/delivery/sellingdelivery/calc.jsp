<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-panel title="用户利润列表" line="true" height="100%">
<!-- 	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak> -->
<!--         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="calculator" v-on:click="calc">计算</su-button> -->
<!--     </div> -->
    <div id="listGrid" style="border:3px"></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
		status : null,
		url : "",
	    params:{smSellDelivery:{ym:null}},
	    total:0,
	},
	ready:function(){
		var me = this;
		
		MainFrameUtil.setDialogButtons(me.getButtons(),"plan_detail");
		me.status = MainFrameUtil.getParams("plan_detail").status;
		if(me.status == "00"){
			me.url = basePath + 'cloud-purchase-web/smSellDeliveryController/calcPage';
		}else {
			me.url = basePath + 'cloud-purchase-web/smSellDeliveryController/page';
		}
		me.params.smSellDelivery.ym = MainFrameUtil.getParams("plan_detail").ym;
		//列表数据加载
		ComponentUtil.buildGrid("selling.delivery.sellingdelivery",$("#listGrid"),{ 
			url: me.url,
			queryParams:this.params,
		    height:445,
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(data){
		    	me.total = data.total;
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("selling.delivery.sellingdelivery",this);
	},
	methods: {
		//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_detail")}}];
            return buttons;
        },
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		ymRender:function(value,row,text){
			if(value){
				return value.substr(0,4) + "-" + value.substr(4,2);
			}
		}
	}
}); 
</script>
</body>
</html>