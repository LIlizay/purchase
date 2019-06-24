<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-行业资讯管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="industryInformationBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="" slot="top" deployable="false" title="电厂信息维护">
		<mk-search-visible>
			<!-- 所属省份 -->
            <su-form-group :label-name="formFields.provinceCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.provinceCodeName.searchHidden}" label-width="120px" label-align="right">
                <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swHtmlInfo.provinceCode" :high="150" 
                	:data-source.sync="provinceList" name="provinceCode"></su-select>
            </su-form-group> 
			<!-- 资讯标题 -->
			<su-form-group :label-name='formFields.searchTitle.label' col="4" label-align="right">
				<su-textbox :data.sync="params.swHtmlInfo.title"></su-textbox>
			</su-form-group>
			<!-- 发布时间 -->
			<su-form-group :label-name='formFields.releaseTime.label' col="4" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.swHtmlInfo.releaseTime"></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="行业资讯列表" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="send" v-on:click="release">发布</su-button>
	    </div>
	    <div id="listGrid" class="col-xs-12" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#industryInformationBody',
	data: {
		formFields:{},
	    params:{
	    	swHtmlInfo:{
    			title:null,//标题
    			provinceCode:null,//省份
        		releaseTime:null,//发布时间
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
		ComponentUtil.buildGrid("purchase.home.swhtmlinfo",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/swHtmlInfoController/page',
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
		ComponentUtil.getFormFields("purchase.home.swhtmlinfo",that);
	},
	methods: {
		reset:function(){
			this.params.swHtmlInfo.title = "";
			this.params.swHtmlInfo.provinceCode = "";
			this.params.swHtmlInfo.releaseTime = "";
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
                href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/industryInformation/detail',
                modal:true,
                iframe:true,
                title:"行业资讯信息查看",
                maximizable:true,
                width:"80%",
                height:620
           })
        },
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'add',
				href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/industryInformation/add',
				iframe:true,
				title:"行业资讯信息新增",
				modal:true,
				maximizable:true,
				width:"80%",
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
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows == null || rows.length == 0){
	        	MainFrameUtil.alert({title:"提示",body:"请先选择要编辑的行！"}); 
	        	return;
	        }
			if(rows.length > 1){
	        	MainFrameUtil.alert({title:"提示",body:"只能选择一进行编辑！"}); 
	        	return;
	        }
			//已发布状态不可编辑
// 	        if(rows[0].releaseState == "02"){
// 	        	MainFrameUtil.alert({title:"提示",body:"已发布，不能编辑！"}); 
// 	        	return;
// 	        }
        	MainFrameUtil.openDialog({
        		id:"edit",
				href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/industryInformation/edit',
				title:"行业资讯信息编辑",
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
        		if(rows[obj].releaseState == "02"){
        			var index = $("#listGrid").datagrid("getRowIndex",rows[obj]);
        			$("#listGrid").datagrid("selectRow",index);
        			MainFrameUtil.alert({title:"提示",body:"第" + (index+1) + "行已发布，不能删除！"}); 
    	        	return;
        		}
        		ids.push(rows[obj].id);
        	}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要删除选中记录吗？",
      	        onClose:function(type){
	    	        if(type == "ok"){
	                   $.ajax({
	                       url : basePath + "cloud-purchase-web/swHtmlInfoController",
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
		},
		//发布网站信息维护列表信息
		release:function(){//flag:true发布；flag:false取消发布
			//获取选中的行
			var rows = $("#listGrid").datagrid("getSelections");
	        if(rows == null || rows.length == 0){
	        	MainFrameUtil.alert({title:"提示",body:"请先选择要发布的行！"}); 
	        	return;
	        }
	        var ids = new Array();
       		//发布的数据不允许发布
  			for(var obj in rows){
  				if(rows[obj].releaseState == "02"){
  					var index = $("#listGrid").datagrid("getRowIndex",rows[obj]);
          			$("#listGrid").datagrid("selectRow",index);
          			MainFrameUtil.alert({title:"提示",body:"第" + (index+1) + "行已发布,<br/>不能重复发布"}); 
      	        	return;
          		}
  				ids.push(rows[obj].id);
  			}
       		
        	 $.ajax({
                 url : basePath + "cloud-purchase-web/swHtmlInfoController/releaseseSwHtmlInfo" ,
                 type : "post",
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
</script>
</body>
</html>