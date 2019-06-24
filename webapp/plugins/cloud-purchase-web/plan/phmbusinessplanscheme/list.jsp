<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel  slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 开始年份 -->
			<su-form-group :label-name='formFields.startYear.label' col="4" label-align="right">
				 <su-datepicker format="YYYY" :data.sync="params.startYear" ></su-datepicker>
			</su-form-group>
			<!-- 截止年份 -->
			<su-form-group :label-name='formFields.endYear.label' col="4" label-align="right">
				 <su-datepicker format="YYYY" :data.sync="params.endYear" ></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="年度经营计划列表"  line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">利润分析</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="paper-plane" v-on:click="submit">提交</su-button>
  		</div>
	    <div id="listGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{}
	},
	ready:function(){
		var me = this;
		$("#listGrid").treegrid({
			url:basePath + 'cloud-purchase-web/phmBusinessPlanController/planPage',
			idField:"id",
			treeField:"name",
			queryParams:this.params,
			columns:[[
			        //  {title:"年份",field:"year",width:80,formatter:me.yearFormatter},
			          {title:"购电计划名称",field:"name",width:80,formatter:me.planFormatter},
			          {title:"制定人",field:"setters",width:80},
			          {title:"用户数",field:"consSum",width:80,formatter:me.yearFormatter},
			          {title:"计划状态",field:"planStatusName",width:80},
			          {title:"预测总合同电量（兆瓦时）",field:"agrePq",width:80},
			          {title:"长协电量 （兆瓦时）",field:"lcPq",width:80},
			          {title:"竞价电量 （兆瓦时）",field:"bidPq",width:80}
			          ]],
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: true,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess : function(){
		    	$("#listGrid").datagrid("clearChecked");
		    },
		    onBeforeExpand : function(row, param) {
				 $(this).treegrid('options').url = basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/getTreeList";
				 $(this).treegrid('options').queryParams ={phmBusinessPlanScheme:{planId:row.id}};
			 }
		})
		$('#listGrid').treegrid("getPager").pagination({
			 onSelectPage:function(pageNumber,pageSize){
				 $(this).pagination("loading");
				 $("#listGrid").treegrid('options').url = basePath + 'cloud-purchase-web/phmBusinessPlanController/planPage';
				 $("#listGrid").treegrid('options').queryParams = me.params;
				 $("#listGrid").treegrid('options').pageNumber =pageNumber;
				 $("#listGrid").treegrid('options').pageSize =pageSize;
				 $("#listGrid").treegrid('reload');
			 }
		 });
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.purchaseplanyear",this);
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
			$("#listGrid").treegrid('options').url = basePath + 'cloud-purchase-web/phmBusinessPlanController/planPage';
		    $("#listGrid").treegrid('load',this.params); 
		},
		edit:function(){
			var row = $("#listGrid").treegrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			if(row.planStatus!=="03"){
				MainFrameUtil.alert({title:"警告",body:"只有【已收集】状态的计划才能进行利润分析！"});
				return;
			}
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"edit",
	  			params:{planId:row.planId,year:row.year,planName:row.planName},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/main?year='+row.year,
				maximizable:true,
				iframe:true,
				modal:true,
				width:1000,
				height:620
			});
		},
		planFormatter:function(value,row,index){
			return "<span title="+value+"><a onclick=\"listVue.schemeList('"+row.planId+"','"+row.year+"','"+row.planName+"')\">"+value+"</a></span>";
		},
		schemeList:function(id,year,planName){
			MainFrameUtil.openDialog({
	  			id:"list",
	  			params:{planId:id,year:year,planName:planName},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/schemelist',
				iframe:true,
				modal:true,
				width:1000,
				height:600
			});
		},
		yearFormatter:function(value,row,index){
			return "<a onclick=\"listVue.detail('"+row.planId+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessconsrela/detail',
				iframe:true,
				modal:true,
				width:1000,
				height:620
			});
		},
		submit:function(){
			var me = this;
			var row = $("#listGrid").treegrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			if(row.planStatus!=="03"){
				MainFrameUtil.alert({title:"警告",body:"只有【已收集】状态的计划才能提交！"});
				return;
			}
			$.ajax({
				url : basePath + "cloud-purchase-web/phmBusinessPlanSchemeController/submit",
   	   			type : 'post',
   	   			data : row.id,
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
   	   					me.getDataGrid();
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		});
		}
	}
}); 
</script>
</body>
</html>