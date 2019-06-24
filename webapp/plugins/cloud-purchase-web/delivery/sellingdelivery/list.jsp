<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel deployable="false" >
		<mk-search-visible>
			<!-- 用户名称 -->
			<su-form-group :label-name='formFields.consName.label' col="4" label-align="right">
				 <su-textbox :data.sync="params.consName" ></su-textbox>
			</su-form-group>
			<!-- 开始年月 -->
			<su-form-group :label-name='formFields.startYm.label' col="4" label-align="right">
				 <su-datepicker format="YYYY-MM" :data.sync="params.startYm" ></su-datepicker>
			</su-form-group>
			<!-- 结算年月 -->
			<su-form-group :label-name='formFields.endYm.label' col="4" label-align="right">
				 <su-datepicker format="YYYY-MM" :data.sync="params.endYm" ></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="用户利润列表" line="true" slot="bottom" height="100%">
	    <div id="listGrid" class="" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{
	    	smSellDelivery:{status : "02"},
	    	startYm : null,
	    	endYm : null
	    }
	},
	ready:function(){
		//列表数据加载
		ComponentUtil.buildGrid("selling.delivery.sellingdelivery",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/smSellDeliveryController/page',
			queryParams:this.params,
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    });
		//查询字段名称加载
		ComponentUtil.getFormFields("selling.delivery.sellingdelivery",this);
	},
	methods: {
		reset:function(){
			this.params = {
			    	smSellDelivery:{status : "02"},
			    	startYm : null,
			    	endYm : null
			    };
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