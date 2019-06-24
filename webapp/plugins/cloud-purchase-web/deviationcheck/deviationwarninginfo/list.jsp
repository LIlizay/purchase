<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>偏差考核预警-偏差预警信息</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="warnVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="偏差预警信息查询" slot="top" deployable="false">
			
			<!-- 用户名称 -->
	        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
<!-- 				<mk-trigger-text :data.sync="consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text> -->
				<su-textbox :data.sync="params.consName" type="text"></su-textbox>
	        </su-form-group>
	        
	        <!-- 年月-->
	        <su-form-group :label-name="formFields.ym.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.ym.searchHidden}" label-width="100px" label-align="right">
				 <su-datepicker format="YYYY-MM" :data.sync="params.ym" ></su-datepicker>
	        </su-form-group>
	        
	        <!-- 预警类型-->
	        <su-form-group :label-name="formFields.warningTypeName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.warningTypeName.searchHidden}" label-width="100px" label-align="right">
	        	<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationInfo.warningType"
				url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_deviation"></su-select>
	        </su-form-group>
	        
	        <!-- 预警级别-->
	        <su-form-group :label-name="formFields.warningGradeName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.warningGradeName.searchHidden}" label-width="100px" label-align="right">
				 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationInfo.warningGrade"
				url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass"></su-select>
	        </su-form-group>
				        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
    <mk-panel title="预警信息列表" line="true" slot="bottom" height="100%">
        <div class="row"  style="height: 100%">
            <table id="warnGrid"></table>
        </div>
        
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var warnVue = new Vue({
	el: '#warnVue',
	data: {
		formFields:{},
		consName:null,
    	params:{
     		ym:'',//年月
   			consName:null,
    		smDeviationInfo:{
    			consId:"",
    			warningGrade:"",
    			warningType:"",
    		}
    	},
	},
	ready:function(){
		var me = this;
		me.params.ym = me.initYm();
		ComponentUtil.buildGrid("selling.deviationcheck.smdeviationinfo",$("#warnGrid"),{
	        url: basePath+'smDeviationInfoController/page', 
			method: 'post',
			queryParams:this.params,
			width:"100%",
			height:"100%",
		    rownumbers: true,
		    pagination: true,
		    nowrap: false,
		    fitColumns:true,
		    pageSize: 10,
		    pageList: [10, 20, 50, 100, 150, 200],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    });
		
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.deviationcheck.smdeviationinfo", this);
	},
	methods: {
		//重置
		reset : function(){
			this.params.ym = "";
			this.params.smDeviationInfo={consId:"",warningGrade:"",warningType:""};
			this.params.consName = "";
		},
		//查询
		getDataGrid:function(){
			$("#warnGrid").datagrid("reload");
		},
		//初始化年月
		initYm:function(){
			var date = new Date();
			var month = date.getMonth()+1;
			if(month < 10){
				month = "0" + month;
			}
			return date.getFullYear() + "-" + month;
		},
		//选择用户
		selectCons: function(){
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
						me.params.smDeviationInfo.consId = params[0].id;
						me.consName = params[0].consName;
					}
				}
			})
 		},
		//查看详情
		detailRender:function(value,row,index){
			return "<a onclick='warnVue.add(\""+row.id+"\")'>查看</a>";
		},
		//编辑
		add:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"warnAdd",
	  			params:{"id":id},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/deviationwarninginfo/add',
				iframe:true,
				modal:true,
				width: "80%",
				height: 420,
			})
		}
	}
})
</script>
</body>
</html>