<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-知识库管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="knowledgeBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="" slot="top" deployable="false" title="电厂信息维护">
		<mk-search-visible>
			<!-- 所属省份 -->
            <su-form-group :label-name="formFields.provinceCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.provinceCodeName.searchHidden}" label-width="120px" label-align="right">
				<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swKnowledge.provinceCode" :high="150" 
                	:data-source.sync="provinceList" name="provinceCode">
                </su-select>
            </su-form-group> 
            
            <!-- 知识分类 -->
			<su-form-group :label-name='formFields.knowledgeClassName.label' col="4" label-align="right">
				<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swKnowledge.knowledgeClass :high="150"
	             	url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_knowledgeClass" name="knowledgeClass">
	            </su-select>
			</su-form-group>
			
			<!-- 知识标题 -->
			<su-form-group :label-name='formFields.title.label' col="4" label-align="right">
				<su-textbox :data.sync="params.swKnowledge.title"></su-textbox>
			</su-form-group>
			
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="知识列表" line="true" slot="bottom" height="100%">
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
	el: '#knowledgeBody',
	data: {
		formFields:{},
	    params:{
	    	swKnowledge:{
    			title:null,//标题
    			provinceCode:null,//省份
        		releaseTime:null,//发布时间
        		knowledgeClass:null
   			}
	    },
	    provinceList:[]
	},
	ready:function(){
		var that = this;
		$.ajax({
			url:basePath+'globalCache/queryProvinceList',
			type:'get',
			success:function(data){
				that.provinceList = data;
				that.provinceList.unshift({"name":"全国","value":"000000"});
			}
		})
		//列表数据加载
		ComponentUtil.buildGrid("purchase.home.knowledge",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/swKnowledgeController/page',
			queryParams:that.params,
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
		ComponentUtil.getFormFields("purchase.home.knowledge",that);
	},
	methods: {
		reset:function(){
			this.params.swKnowledge.title = "";
			this.params.swKnowledge.provinceCode = "";
			this.params.swKnowledge.knowledgeClass = "";
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		//点击客户名称查看详情信息
        renderertitle : function(value,row,index){
            return "<a onclick=\"listVue.view('"+row.id+"')\">"+row.title+"</a>";
        },
        //详情页面
        view:function(id){
            MainFrameUtil.openDialog({
            	id:"detail",
                params:{"id":id},
                href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/knowledge/detail',
                title:"知识信息查看",
                modal:true,
                iframe:true,
                maximizable:true,
                width:"80%",
                height:620,
           })
        },
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/knowledge/add',
				title:"知识信息新增",
				iframe:true,
				modal:true,
				//maximizable:true,
				width:"80%",
				height:620,
				maximizable:true,
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
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows == null || rows.length == 0){
	        	MainFrameUtil.alert({title:"提示",body:"请先选择要编辑的行！"}); 
	        	return;
	        }
			if(rows.length > 1){
	        	MainFrameUtil.alert({title:"提示",body:"只能选择一进行编辑！"}); 
	        	return;
	        }
        	MainFrameUtil.openDialog({
        		id:"edit",
				href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/knowledge/edit',
				title:"知识信息编辑",
				params:{'id':rows[0].id},
				iframe:true,
				modal:true,
				maximizable:true,
				width:"80%",
				height:620,
				onClose:function(){
					me.getDataGrid();
				}
			});
		},
		del:function(){
			var me = this;
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows == null || rows.length == 0){
	        	MainFrameUtil.alert({title:"提示",body:"请先选择要删除的行！"}); 
	        	return;
	        }
			var ids = new Array();
        	for(var obj in rows){
        		ids.push(rows[obj].id);
        	}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要删除选中记录吗？",
      	        onClose:function(type){
	    	        if(type == "ok"){
	                   $.ajax({
	                       url : basePath + "cloud-purchase-web/swKnowledgeController",
	                       type : "DELETE",
	                       data:$.toJSON(ids),
	                       contentType:'application/json;charset=UTF-8',  
	                       success : function(data) {
	                           if(data.flag){
	                           	MainFrameUtil.alert({title:"成功提示",body:data.msg}); 
	                               $("#listGrid").datagrid("reload");
	                           }else{
	                           	MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
	                           }
	                       },
	                       error : function() {
	                       	MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
	                       }
	                   });
		        	 }
      	        }
  	    	}); 
		}
	}
}); 
</script>
</body>
</html>