<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-年度购电管理双边电量分月</title>
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
	<mk-panel title="双边协商电量分月列表"  line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">电量分解</su-button>
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
		ComponentUtil.buildGrid("purchase.planphmphplanyearscheme",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmPhPlanYearSchemeController/detailPage',
			queryParams:this.params,
		    width:"100%",
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: true,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.planphmphplanyearscheme",this);
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		formatter: function(value,row,index){  
			return "<span title="+value+"><a onclick=\"listVue.detail('"+row.id+"','"+row.lcPq+"','"+row.agrePq+"','"
					+row.addProfit+"','"+row.differenceProfit+"')\">"+value+"</a></span>";
		},
		detail:function(id,lcPq,agrePq,addProfit,differenceProfit){
			addProfit = addProfit=="null"?null:addProfit;
			differenceProfit = differenceProfit=="null"?null:differenceProfit;
			var obj = {id:id,lcPq:lcPq,agrePq:agrePq,addProfit:addProfit,differenceProfit:differenceProfit};
			MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:obj,
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmphplanyearscheme/detail',
				title:'双边电量分月信息查看',
				iframe:true,
				maximizable:true,
				modal:true,
				width:1000,
				height:570,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('detail');
                    }
                }]
			});
		},
		edit:function(){
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"edit",
	  			params:row,
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmphplanyearscheme/edit',
				title:'双边协商电量分解',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:570,
				buttons:[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	params.save(function(){
                    		MainFrameUtil.closeDialog('edit');
                    		me.getDataGrid();
                    	});
                    }
                },{
                    text:"取消",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('edit');
                    }
                }]
			});
		},
		yearFormatter:function(value,row,index){
			return "<a onclick=\"listVue.yearDetail('"+row.planId+"')\">"+value+"</a>";
		},
		yearDetail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/detail',
				title:'年度购电计划详细信息查看',
				iframe:true,
				maximizable:true,
				modal:true,
				width:1000,
				height:620,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('detail');
                    }
                }]
			});
		}
	}
}); 
</script>
</body>
</html>