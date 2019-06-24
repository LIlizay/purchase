<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>档案管理-电厂档案</title>
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
			<!-- 发电类别 -->
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
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
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
		ComponentUtil.buildGrid("purchase.archives.powerplant",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/powerPlantController/page',
			queryParams:this.params,
		    width:"100%",
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:false,
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
			return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/detail',
				title:'电厂信息查看',
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
		},
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/add',
				title:'电厂信息新增',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:620,
				onClose:function(){
					me.getDataGrid();
				},
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save();
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
			var row = $("#listGrid").datagrid("getChecked");
			if(row.length>1){
	        	MainFrameUtil.alert({title:"提示",body:"只能选择一条数据！"}); 
	        	return;
	        }
			if(row.length < 1){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'add',
	  			params:{id:row[0].id},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/powerplant/add',
				title:'电厂信息编辑',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:620,
				onClose:function(){
					me.getDataGrid();
				},
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save();
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('add');
	                    }
	                }]
			});
		},
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
      	        			url: basePath + 'cloud-purchase-web/powerPlantController/'+row.id,
      	        			type:'delete',
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
		}
	}
}); 
</script>
</body>
</html>