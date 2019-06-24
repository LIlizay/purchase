<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>电厂名称选择（公共）</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="" slot="top" deployable="false" title="电厂信息维护">
		<mk-search-visible>
			<!-- 电厂名称 -->
			<su-form-group :label-name='formFields.elecName.label' col="4" label-align="right">
				 <su-textbox :data.sync="params.elecName" ></su-textbox>
			</su-form-group>
			<!-- 电厂类别 -->
			<su-form-group :label-name='formFields.elecTypeCodeName.label' col="4" label-align="right">
				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType" multiple="false" 
				:select-value.sync="params.elecTypeCode" label-name="name"></su-select>
			</su-form-group>
			<!-- 发电集团 -->
			<su-form-group :label-name='formFields.blocIdName.label' col="4" label-align="right">
				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecGenerationGroup" multiple="false" 
				:select-value.sync="params.blocId" label-name="name"></su-select>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="电厂信息列表" line="true" slot="bottom" height="100%">
	    <div id="listGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{},
	},
	ready:function(){
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'selectElec');
		//列表数据加载
		ComponentUtil.buildGrid("purchase.archives.powerplant",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/powerPlantController/page',
			queryParams:this.params,
		    width:"100%",
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.archives.powerplant",this);
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		elecNameFormatter:function(value,row,text){
			return value;
// 			return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/detail',
				iframe:true,
				modal:true,
				width:1000,
				height:620
			});
		},
		save:function(backFun){
			var row = $("#listGrid").datagrid('getSelected'); 
			if(row==null){
				MainFrameUtil.alert({title:"提示",body:"请选择电厂！"});
			}else{
				backFun(row);
			}
		}
	}
}); 
</script>
</body>
</html>