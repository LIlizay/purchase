<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>合同管理-购电合同管理模板</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="购电合同模板管理" slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 合同模板号 -->
			<su-form-group :label-name='formFields.templateVer.label' class="form-control-row" col="4" label-align="right">
				 <su-textbox :data.sync="params.templateVer" ></su-textbox>
			</su-form-group>
			<!-- 合同模板名称 -->
			<su-form-group :label-name='formFields.templateName.label' class="form-control-row" col="4" label-align="right">
				<su-textbox :data.sync="params.templateName" ></su-textbox>
			</su-form-group>
			<!-- 合同类型 -->
			<su-form-group :label-name='formFields.templateTypeName.label' class="form-control-row" col="4" label-align="right">
				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_agreType" multiple="false" 
				:select-value.sync="params.templateType" label-name="name"></su-select>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="购电合同模板信息" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	       <!--  <su-button class="btn-operator" s-type="default" labeled="true" label-ico="refresh" v-on:click="effect">生效</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="invalid">失效</su-button> -->
	    </div>
	    <div id="listGrid" class="col-xs-12" ></div>
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
		//列表数据加载
		ComponentUtil.buildGrid("purchase.contract.template",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmAgreTemplateController/page',
			queryParams:{phmAgreTemplate:this.params},
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
		ComponentUtil.getFormFields("purchase.contract.template",this);
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',{phmAgreTemplate:this.params}); 
		},
		templateVerFormatter:function(value,row,text){
			return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		fileIdFormatter:function(value,row,text){
			if(value!=null&&value!=''){
				var obj = null;
				try {
					obj = eval('(' + value + ')');
					return "<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+obj.fileName+"</a>";
			　　} catch(error) {
			　　		return  null;
			　　}
			}
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/agreement/template/detail',
				title:'合同模版信息查看',	
				iframe:true,
				modal:true,
				width:800,
				height:410,
				buttons:[{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('detail');
                    }
                }]
			});
		},
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/agreement/template/add',
				title:'合同模版新增',
				iframe:true,
				modal:true,
				width:800,
				height:410,
				onClose:function(){
					me.getDataGrid();
				},
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.submit();
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('add');
	                    }
                }]
			});
		},
		edit:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.agreStatus != "01"){
				MainFrameUtil.alert({title:"警告",body:"只有【新增】状态的合同模板才能编辑！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'edit',
	  			params:{id:row.id},
				href:'${Config.baseURL}view/cloud-purchase-web/agreement/template/edit',
				title:'合同模版编辑',
				iframe:true,
				modal:true,
				width:800,
				height:410,
				onClose:function(){
					me.getDataGrid();
				},
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.submit();
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('edit');
	                    }
	                }]
			});
		},
		/* effect:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.agreStatus == "02"){
				MainFrameUtil.alert({title:"警告",body:"该合同版本已生效，不能再次生效！！"});
				return;
			}
			if(row.agreStatus == "03"){
				MainFrameUtil.alert({title:"警告",body:"该合同版本已失效，不能重新生效！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"确认生效该模板？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		$.ajax({
      	        			url: basePath + 'cloud-purchase-web/phmAgreTemplateController/effect/'+row.id,
      	        			type:'post',
      	        			success:function(data){
      	        				if(data.flag){
      	        					MainFrameUtil.alert({title:"提示",body:data.msg});
      	        					me.getDataGrid();
      	        				}else{
      	        					MainFrameUtil.alert({title:"失败",body:data.msg});
      	        				}
      	        			}
      	        		})
      	        	}
      	        }
  	    	}); 
		}, */
		/* invalid:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.agreStatus == "03"){
				MainFrameUtil.alert({title:"警告",body:"该合同版本已失效！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"确认失效该模板？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		$.ajax({
      	        			url: basePath + 'cloud-purchase-web/phmAgreTemplateController/invalid/'+row.id,
      	        			type:'post',
      	        			success:function(data){
      	        				if(data.flag){
      	        					MainFrameUtil.alert({title:"提示",body:data.msg});
      	        					me.getDataGrid();
      	        				}else{
      	        					MainFrameUtil.alert({title:"失败",body:data.msg});
      	        				}
      	        			}
      	        		})
      	        	}
      	        }
  	    	}); 
		}, */
		del:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要删除选中记录吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		$.ajax({
      	        			url: basePath + 'cloud-purchase-web/phmAgreTemplateController/'+row.id,
      	        			type:'delete',
      	        			success:function(data){
      	        				if(data.flag){
      	        					MainFrameUtil.alert({title:"提示",body:data.msg});
      	        					me.getDataGrid();
      	        					var deleteId = row.fileId.split(";")[0];
      	        					$.ajax({
                                		url:"${Config.getConfig('file.service.url')}"+deleteId,
                                		type:"delete"
                                	})
      	        				}else{
      	        					MainFrameUtil.alert({title:"失败",body:data.msg});
      	        				}
      	        			}
      	        		})
      	        	}
      	        }
  	    	}); 
		}
	}
}); 
</script>
</body>
</html>