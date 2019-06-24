<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-月度电量预测</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body id="eleAnalysisVue" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="客户用电信息查询" slot="top" deployable="false">
	        <!-- 开始年月 -->
	        <su-form-group label-name="开始年月" class="form-control-row "  col="4" 
	        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right">
	            <su-datepicker format="YYYY-MM"  :data.sync="params.startTime" ></su-datepicker>
	        </su-form-group>
	        <!-- 结束年月 -->
	        <su-form-group label-name="结束年月" class="form-control-row "  col="4" 
	        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right">
	            <su-datepicker format="YYYY-MM"  :data.sync="params.endTime" ></su-datepicker>
	        </su-form-group>
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
   	<mk-panel title="客户用电信息列表" line="true" v-cloak slot="bottom" height="100%">
        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
             <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deleteC">删除</su-button>
        </div>
        <div id="eleAnalysisGrid"></div>
    </mk-panel>
	    
	</mk-top-bottom>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var eleAnalysisVue = new Vue({
	el: '#eleAnalysisVue',
	data: {
		url:basePath + 'view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
    	params:{
    		startTime: null,
    		endTime: null,
    	},
    	tradeurl:basePath + 'view/cloud-purchase-web/static/jsp/trade',
        tradeproptext:''
	},
	ready:function(){
		var me = this;
	    $('#eleAnalysisGrid').datagrid({ 
	        url: basePath+'scElectricityInfoController/getListData', 
			method: 'POST',
	        queryParams: this.params,
			fitColumns: true,
			rownumbers: true,
		    pagination: true,
		    nowrap: false,
		    pageSize: 10,
// 			singleSelect: true,
			width: "100%",
			height:"100%",
	        columns:[[ 
				{field:'ck',checkbox:"true"},      
	            {field:'ym',title:'年月',width:80,align:'center',
	            	formatter:function(value,row,index){
	            		if(row.ym != null && row.ym != ''){
			        		var html="";
			        		html+= "<a class='left-btn' onclick=\"eleAnalysisVue.detail('"+row.ym+"')\">"+row.ymName+"</a>";
			        		return html;
	            		}else{
	            			return null;
	            		}
	            	}
	            },
	            {field:'upYearPracticalPq',title:'同期用电量<br/>（兆瓦时）',width:80,align:'center'},
	            {field:'upMonPracticalPq',title:'上期用电量<br/>（兆瓦时）',width:80,align:'center'},
	            {field:'personForecastPq',title:'预测用电量<br/>（兆瓦时）',width:80,align:'center'},
	            {field:'practicalPq',title:'实际用电量<br/>（兆瓦时）',width:80,align:'center'},
	        ]]
	    });
		
	},
	methods: {
		//客户售电合同电量分析
		detail: function(ym){
			var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
	  		MainFrameUtil.openDialog({
	  			maximizable:true,
	  			params:{"ym": ym},  //MainFrameUtil.getParams()
				href:'${Config.baseURL}view/cloud-purchase-web/crm/month-pq-forecast/detail',
				title:'用电明细查看',
				iframe:true,
				modal:true,
				width: width/1.3,
				height: hight/1.3,
				onClose:function(){//关闭弹框重置查询参数，查询列表
					eleAnalysisVue.reset();
				}
			});
		},
		//查询列表信息
		getDataGrid: function(){
            $('#eleAnalysisGrid').datagrid('options').queryParams = this.params;  
            $('#eleAnalysisGrid').datagrid("reload");
		},
		//重置
		reset : function(){
			this.params = {
		    		startTime : null,
		    		endTime : null,
		    	};
		},
		//编辑
		edit: function(){
			var rows = $('#eleAnalysisGrid').datagrid('getChecked');
			if(rows.length != 1){
				MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
				return;
			}
			var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
	  		MainFrameUtil.openDialog({
				href:'${Config.baseURL}view/cloud-purchase-web/crm/month-pq-forecast/edit',
				params:{"ym": rows[0].ym}, 
				title:'月度电量预测编辑',
				iframe:true,
				maximizable:true,
				modal:true,
				maximizable:true,
				width: width/1.3,
				height: hight/1.3,
				onClose:function(){
					$("#eleAnalysisGrid").datagrid('reload');
				}
			});
		},
		//新增
		add : function(){
	  		var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
	  		MainFrameUtil.openDialog({
				href:'${Config.baseURL}view/cloud-purchase-web/crm/month-pq-forecast/add',
				title:'月度电量预测新增',
				iframe:true,
				maximizable:true,
				modal:true,
				maximizable:true,
				width: width/1.3,
				height: hight/1.3,
				onClose:function(){
					$("#eleAnalysisGrid").datagrid('reload');
				}
			});
		},
		deleteC : function(){
			var rows = $('#eleAnalysisGrid').datagrid('getSelected');
	        var me = this;
			if(rows == null){
				MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
				return;
			}
			MainFrameUtil.confirm({
				title:"确认",
		        body:"该操作不能恢复，确定要删除选中的记录吗？",
		        onClose:function(type){
		        	 if(type=="ok"){//确定
			            	$.ajax({
		                		url:basePath + 'cloud-purchase-web/scElectricityInfoController/updateMonthPqFore',
		                		type:"post",
		                		data:$.toJSON({ym:rows.ym}),
								contentType : 'application/json;charset=UTF-8',
		                		success:function(data){
		                			if(data.flag){
		    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
		    	      				}else{
		    	      					MainFrameUtil.alert({title:"错误",body:data.msg});
		    	      				}
		                			$("#eleAnalysisGrid").datagrid('reload');
		                		},
		                		error:function(data){
		                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
		                			$("#eleAnalysisGrid").datagrid('reload');
		                		}
		                	})
			            }
		        }
    		});
			
		},
	},
	watch:{
	}
}); 
</script>
</body>
</html>