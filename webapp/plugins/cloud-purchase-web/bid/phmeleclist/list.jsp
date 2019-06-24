<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>晋能售电-电费清单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="" slot="top" deployable="false" title="电厂信息维护">
		<mk-search-visible>
			<!-- 用户名称 -->
			<su-form-group :label-name='formFields.consName.label' col="4" label-align="right">
				 <su-textbox :data.sync="params.consName" ></su-textbox>
			</su-form-group>
			<!-- 交易年月 -->
			<su-form-group :label-name='formFields.ym.label' col="4" label-align="right">
				 <su-datepicker format="YYYYMM" :data.sync="params.ym" ></su-datepicker>
			</su-form-group>
			<!-- 电压等级 -->
			<su-form-group :label-name='formFields.voltCodeName.label' col="4" label-align="right">
				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" multiple="false" 
				:select-value.sync="params.voltCode" label-name="name"></su-select>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="用户信息列表" line="true" slot="bottom" height="100%">
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
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.bid.phmeleclist",this);
		//列表数据加载
		ComponentUtil.buildGrid("purchase.bid.phmeleclist",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmElecListController/phmElecListDetailPage',
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
	},
	methods: {
		reset:function(){
			this.params = {};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		elecNameFormatter:function(value,row,text){
			return "<a onclick=\"listVue.detail('"+row.consId+"','"+row.consName+"','"+row.ym+"')\">"+value+"</a>";
		},
		detail:function(consId,consName,ym){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{consId:consId,consName:consName,ym:ym},
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmeleclist/detail',
				iframe:true,
				modal:true,
				width:1000,
				height:620
			});
		},
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmeleclist/add',
				iframe:true,
				modal:true,
				width:1000,
				height:620,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save(function(){
	                    		MainFrameUtil.closeDialog('add');
	                    		me.getDataGrid();
	                    	});
	                    }
	                },{
	                    text:"关闭",
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
			MainFrameUtil.openDialog({
	  			id:'edit',
	  			params:{consId:row.consId,consName:row.consName,ym:row.ym},
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmeleclist/edit',
				iframe:true,
				modal:true,
				width:1000,
				height:620,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save(function(){
	                    		MainFrameUtil.closeDialog('edit');
	                    		me.getDataGrid();
	                    	})
	                    }
	                },{
	                    text:"关闭",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('edit');
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
      	        			url: basePath + 'cloud-purchase-web/phmElecListController/delete',
      	        			type:'delete',
      	        			data:$.toJSON({consId:row.consId,ym:row.ym}),
      	  	 				contentType : 'application/json;charset=UTF-8',
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