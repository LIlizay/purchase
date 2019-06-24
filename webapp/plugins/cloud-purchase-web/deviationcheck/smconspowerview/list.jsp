<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>偏差考核预警-用户用电计划</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body>
<div id="conspowerVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="用户用电量查询" slot="top" deployable="false">
			
			<!-- 用户名称 -->
	        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
				<su-textbox :data.sync="params.consName" type="text"></su-textbox>
	        </su-form-group>
	        
	        <!-- 年份-->
	        <su-form-group :label-name="formFields.ym.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.ym.searchHidden}" label-width="100px" label-align="right">
				 <su-datepicker format="YYYY-MM" :data.sync="params.ym" ></su-datepicker>
	        </su-form-group>
				        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
    <mk-panel title="用户用电计划列表" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		</div>
        <div class="row"  style="height: 100%">
            <table id="conspowerGrid"></table>
        </div>
        
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var conspowerVue = new Vue({
	el: '#conspowerVue',
	data: {
		formFields:{},
		consName:null,
    	params:{
   			consName:'',//用户名称
     		ym:''//年月
    	}
	},
	ready:function(){
		var me = this;
		me.params.ym = me.initYm();
		ComponentUtil.buildGrid("selling.deviationcheck.smconspowerview",$("#conspowerGrid"),{
	        url: basePath+'smConsPowerViewController/page', 
			method: 'post',
			queryParams: this.params,
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
		    },
		    columnsReplace:[{field:'detail',hidden:true}]
	    });
		
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.deviationcheck.smconspowerview", this);
	},
	methods: {
		//重置
		reset : function(){
			this.params.consName="";
			this.params.ym = "";
			this.consName = "";
		},
		//查询
		getDataGrid:function(){
			$("#conspowerGrid").datagrid("reload");
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
		/* selectCons: function(){
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
						me.params.consId = params[0].id;
						me.consName = params[0].consName;
					}
				}
			})
 		}, */
 		
 		add: function(){
 			var me = this;
 			MainFrameUtil.openDialog({
	  			id:"viewadd",
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/smconspowerview/add',
// 				title:'用户用电量收集记录新增',
				iframe:true,
				modal:true,
				width: "80%",
				height: 620,
				onClose:function(){
					$("#conspowerGrid").datagrid("reload");
				}
			})
 		},
 		
		//查看详情
		detailRender: function(value,row,index){
			return "<a onclick=\"conspowerVue.detail("+index+")\">"+value+"</a>";
		},
		//编辑
		edit: function(){
			var rows = $('#conspowerGrid').datagrid('getChecked');
			if(rows==null||rows.length==0){
				MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
				return;
			}else if(rows.length>1){
				MainFrameUtil.alert({title:"提示",body:"只能选择一条数据！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:"conspowerAdd",
	  			params:{"row": $.parseJSON($.toJSON(rows[0]))},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/smconspowerview/edit',
				title:'用户用电计划信息编辑',
				iframe:true,
				modal:true,
				width: "80%",
				height: 620,
				onClose:function(){
					$("#conspowerGrid").datagrid("reload");
				}
			})
		},
		del: function(){
			var rows = $('#conspowerGrid').datagrid('getChecked');
			if(rows==null||rows.length==0){
				MainFrameUtil.alert({title:"提示",body:"请选择要删除的数据！"});
				return;
			}
			MainFrameUtil.confirm({
    	        title:"警告",
    	        body:"确认删除吗？删除后数据不可恢复！ ",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	var delParams = [];
    	    			for(var i in rows){
    	    				var param = {
    	    						consId : rows[i].consId,
    	    						ym : rows[i].ym
    	    				};
    	    				delParams.push(param);
    	    			}
    	    			$.ajax({
    	    				url : basePath + "cloud-purchase-web/smConsPowerViewController/del",
    	       	   			type : 'post',
    	       	   			data : $.toJSON(delParams),
    	       	   			contentType : 'application/json;charset=UTF-8',
    	       	   			success : function(data){
    	       	   				if(data){
    	       	   					MainFrameUtil.alert({ title:"成功提示", body:data.msg}); 
    	       	   				}else{
    	       	   					MainFrameUtil.alert({ title:"失败提示", body:data.msg }); 
    	       	   				}
    	       	   				$("#conspowerGrid").datagrid('reload');
    	       	   			}
    	       	   		})
    	            }
    	        }
    	    })	
		},
		detail: function(index){
			var row = $("#conspowerGrid").datagrid("getRows")[index];
			MainFrameUtil.openDialog({
	  			id:"conspowerAdd",
	  			params:{"row": $.parseJSON($.toJSON(row))},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/smconspowerview/detail',
				title:'用户用电计划信息查看',
				iframe:true,
				modal:true,
				width: "80%",
				height: 620,
				onClose:function(){
					$("#conspowerGrid").datagrid("reload");
				}
			})
		}
	}
}); 
</script>
</body>
</html>