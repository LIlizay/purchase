<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-售电用户负荷预测</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body id="eleAnalysisVue" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="客户用电信息查询" slot="top" deployable="false">
			
			<!-- 客户名称 -->
	        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
				<su-textbox :data.sync="params.consName"></su-textbox>
	        </su-form-group>
	        
	        <!-- 用电类别 -->
	        <su-form-group :label-name="formFields.elecTypeCode.label" class="form-control-row "  col="4" 
	        		:class="{'display-none':!formFields.elecTypeCode.searchHidden}" label-width="100px" label-align="right">
	            <su-select clear-btn="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.queryDetail.elecTypeCode"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" ></su-select>
	        </su-form-group> 
	        
	        <!-- 用电行业分类 -->
	        <su-form-group :label-name="formFields.industryType.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.industryType.searchHidden}" label-width="100px" label-align="right">
	        	<su-select clear-btn="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.queryDetail.industryType"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_industryType" ></su-select>
	        </su-form-group>
	        
	        <!-- 年份 -->
	        <su-form-group :label-name="formFields.year.label" class="form-control-row "  col="4" 
	        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right">
	            <su-datepicker format="YYYY"  :data.sync="params.queryDetail.year" ></su-datepicker>
	        </su-form-group>
	        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
   	<mk-panel title="客户用电信息列表" line="true" v-cloak slot="bottom" height="100%">
        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="edit">新增</su-button>
             <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="add">编辑</su-button>
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
		formFields:{},
		consInfo:null,
		url:basePath + 'view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
		yearList:[],//年下拉框数据
    	params:{
    		consName:"",
    		queryDetail:{
    			consId:'',		//客户名称
    			lodeAttrCode:'',	//产业分类
        		elecTypeCode:'',	//用电类别
        		industryType:'',    //用电行业分类
        		year:'' 			//年份
    		}
    	},
    	tradeurl:basePath + 'view/cloud-purchase-web/static/jsp/trade',
        tradeproptext:''
	},
	ready:function(){
		var me = this;
	    $('#eleAnalysisGrid').datagrid({ 
	        url: basePath+'scElectricityInfoController/getTree', 
	        idField: 'consId', 
	        treeField: 'consName',
	        queryParams: this.params,
			method: 'POST',
			fitColumns: true,
			rownumbers: true,
		      rownumbers: true,
		      pagination: true,
		      nowrap: false,
		      pageSize: 10,
		      scrollbarSize : 0,
			singleSelect: true,
			checkOnSelect:true,
			width: "100%",
			height:"100%",
	        columns:[[ 
				{field:'consId',hidden:true},      
				{field:'ck',checkbox:"true"},      
	            {field:'consName',title:'用户名称',width:180},
	            {field:'year',title:'年份',width:180,align:'center',
	            	formatter:function(value,row,index){
	            		if(row.year != null && row.year != ''){
			        		var html="";
			        		html+= "<a class='left-btn' onclick=\"eleAnalysisVue.operation('"+row.consId+"','"+row.year+"')\">"+row.year+"</a>";
			        		return html;
	            		}else{
	            			return null;
	            		}
	            	}
	            },
// 	            {field:'lodeAttrCodeName',title:'负荷性质',width:180,align:'center'},
	            {field:'elecTypeCodeName',title:'用电类别',width:180,align:'center'},
	            {field:'industryTypeName',title:'行业分类',width:180,align:'center'},
	            {field:'totalPq',title:'总用电量(兆瓦时)',width:180,align:'center'}
// 	            {field:'detail',title:'查看明细',width:180,align:'center'
// 	            	formatter:function(value,row,index){
// 	            		if(row.year != null && row.year != ''){
// 			        		var html="";
// 			        		html+= "<a class='left-btn' onclick=\"eleAnalysisVue.operation('"+row.consId+"','"+row.year+"')\">查看</a>";
// 			        		return html;
// 	            		}else{
// 	            			return null;
// 	            		}
// 	            	}
// 	            }
	        ]]
	    });
		
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.crm.conselecquery", this);
	},
	methods: {
		//客户售电合同电量分析
		operation:function(consId,year){
			var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
	  		MainFrameUtil.openDialog({
	  			maximizable:true,
	  			params:{"consId":consId,"year":year},  //MainFrameUtil.getParams()
				href:'${Config.baseURL}view/cloud-purchase-web/crm/custelecquery/detail',
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
		getDataGrid:function(){
            $('#eleAnalysisGrid').datagrid('options').queryParams = this.params;  
            $('#eleAnalysisGrid').datagrid("reload");
		},
		//重置
		reset : function(){
			this.params = {
		    		consName:"",
		    		queryDetail:{
		    			consId:'',		//客户名称
		    			lodeAttrCode:'',	//产业分类
		        		elecTypeCode:'',	//用电类别
		        		industryType:'',    //用电行业分类
		        		year:'' 			//年份
		    		}
		    	};
		},
		//新增
		edit:function(){
        	var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
	  		MainFrameUtil.openDialog({
				href:'${Config.baseURL}view/cloud-purchase-web/crm/custelecquery/edit',
				title:'用电负荷预测',
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
		//编辑
		add : function(){
			var hight = window.screen.availHeight;
			var width = window.screen.availWidth;
			var rows = $('#eleAnalysisGrid').datagrid('getSelected');
	  		MainFrameUtil.openDialog({
				href:'${Config.baseURL}view/cloud-purchase-web/crm/custelecquery/add',
				params:{"consId":rows.consId,"year":rows.year}, 
				title:'用电负荷预测编辑',
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
		                		url:basePath + 'cloud-purchase-web/scElectricityInfoController',
		                		type:"put",
		                		data:$.toJSON({consId:rows.consId,year:rows.year}),
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
 		selectCons:function(){
 			var me = this;
 			MainFrameUtil.openDialog({
	  			id:'consDialog',
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
				iframe:true,
				modal:true,
				width:'50%',
				height:620,
				onClose:function(params){
					if(typeof(params[0])==="object"){
						me.consInfo = params[0];
						me.params.queryDetail.consId = params[0].id;
					}
				}
			});
 		}
	},
	watch:{
	}
}); 
</script>
</body>
</html>