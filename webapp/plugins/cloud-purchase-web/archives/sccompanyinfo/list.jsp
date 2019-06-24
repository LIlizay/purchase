<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setAttribute("systemTitle", ConfigHelper.getConfig("system.title"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>售电公司信息管理</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body  >
<div id="companyVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="售电公司信息管理" slot="top" deployable="false">
	
         <su-form-group :label-name="formFields.companyName.label" class="form-control-row "  col="4" 
        		:class="{'display-none':!formFields.companyName.searchHidden}" label-width="100px" label-align="right">
        	 <su-textbox :data.sync="params.scCompanyInfo.companyName"></su-textbox>
         </su-form-group> 
	        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
    <mk-panel title="售电公司信息列表" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right" style="text-align:right">
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
        </div>
        <div id="companyGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
	</mk-top-bottom>
</div>

<div id="addItInfo"></div>
<div id="editItInfo"></div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var obj = {
	operation:function(value,row,index){
		var html="";
		html+= "<a class='left-btn' onclick=\"companyVue.operation('"+row.id+"')\">"+row.companyName+"</a>";
		return html;
    }
}
var companyVue = new Vue({
	el: '#companyVue',
	data: {
		formFields:{},
    	params:{
    		scCompanyInfo:{
    			companyName:null	//售电公司名称
    		}
    	}
	},
	ready:function(){
		//列表数据加载
	    ComponentUtil.buildGrid("selling.archives.scCompanyInfo", $("#companyGrid"),{ 
	    	url:basePath+"scCompanyInfoController/page",
	    	method:"POST",
	    	queryParams:this.params,
	        width:"100%",
	        fitColumns: true,
	        rownumbers: true,
	        pagination: true,
	        singleSelect:false,
	        nowrap: false, 
	        pageSize: 10,
	        pageList: [10, 20, 50, 100, 150, 200],
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        }
	    }); 
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.archives.scCompanyInfo", this);
	},
	methods: {
		//显示详细信息
		operation:function(id){
	  		MainFrameUtil.openDialog({
	  			params:{'id':id},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/sccompanyinfo/detail',
				iframe:true,
				modal:true,
				width:"80%",
				height:620,
				onClose:function(){
					companyVue.reset();
					companyVue.getDataGrid();
				}
			});
		},
		//查询列表信息
		getDataGrid:function(){
            $('#companyGrid').datagrid("reload");
		},
		//重置
		reset : function(){
			var me = this;
			me.params.scCompanyInfo.companyName = null;
		},
		//新增分成方式标准设置
		add : function(){
			MainFrameUtil.openDialog({
                href:'${Config.baseURL}view/cloud-purchase-web/archives/sccompanyinfo/add',
                modal:true,
                iframe:true,
                width:"80%",
                height:600,
                onClose:function(){
                	companyVue.reset();
                	$('#companyGrid').datagrid("load");//新增完信息后重新加载列表
                }
           })
		},
        //标记页面
        edit:function(){
        	//获取所有被选中的行
        	var rows = $('#companyGrid').datagrid("getChecked");
        	console.log("rows",rows);
        	//如果没有选择
        	if(rows.length==0){
        		MainFrameUtil.alert({
                    title:"",
                    body:"请选中一行售电公司信息！",
                    onClose:function(type){}
                });
                return;
        	};
        	MainFrameUtil.openDialog({
                params:{"id":rows[0].id},
                href:'${Config.baseURL}view/cloud-purchase-web/archives/sccompanyinfo/edit',
                modal:true,
                iframe:true,
                width:"80%",
                height:600,
                onClose:function(){
                	companyVue.reset();
                	$('#companyGrid').datagrid("load");//重新加载列表
                }
           })
        },
        del:function(){
        	//获取所有被选中的行
        	var rows = $('#companyGrid').datagrid("getChecked");
        	//如果没有选择
        	if(rows.length==0){
        		MainFrameUtil.alert({
                    title:"",
                    body:"请选中一行售电公司信息！",
                    onClose:function(type){}
                });
                return;
        	};
        	 MainFrameUtil.confirm({title:"确认删除吗?",body:"删除后不可恢复",
 	            onClose:function(type){
 	                if(type=="ok"){//确定
			        	var ids = '';
			        	for(var obj in rows){
			        		ids += rows[obj].id + ',';
			        	}
			        	//注意此时字符串是以 ',' 结尾的，后台进行处理
			        	console.log("ids : " + ids);
			        	$.ajax({
							url : basePath+"scCompanyInfoController/" + ids,
							type : 'DELETE',
							contentType : 'application/json;charset=UTF-8',
							success : function(data) {
								if(data.flag){
									$.messager.progress('close');   // 如果修改成功则隐藏进度条
									MainFrameUtil.alert({title:"提示",body:"删除成功！"});
									MainFrameUtil.closeDialog();
									companyVue.reset();
									$('#companyGrid').datagrid("load");
								}else{
									$.messager.progress('close');   // 如果修改失败则隐藏进度条
									MainFrameUtil.alert({title:"提示",body:"删除失败！"});
								}
							},
							error : function() {
								$.messager.progress('close');   // 如果修改失败则隐藏进度条
								MainFrameUtil.alert({title:"提示",body:"删除失败！"});
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