<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 消息标题 -->
			<su-form-group :label-name='formFields.title.label' col="4" class="form-control-row" label-align="right">
				 <su-textbox :data.sync="params.sellingSwMessage.title" ></su-textbox>
			</su-form-group>
			<!-- 发送时间 -->
			<su-form-group :label-name='formFields.sendDate.label' col="4" class="form-control-row" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.sellingSwMessage.sendDate" ></su-datepicker>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="公告信息维护列表" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		 	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
           <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
           <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	    </div>
	    <div id="listGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{sellingSwMessage:{
	    	title:"",
	    	sendDate:"",
	    }},
	},
	ready:function(){
		//查询字段名称加载
		ComponentUtil.getFormFields("selling.login.sellingswmessage",this);
		//列表数据加载
		ComponentUtil.buildGrid("selling.login.sellingswmessage",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/swMessageController/page',
			queryParams:this.params,
		    height:"100%",
		    method: 'post',
		    rownumbers: true,
		    pagination: true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
	},
	methods: {
		reset:function(){
			this.params.sellingSwMessage={title:"",sendDate:""};
		},
		
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		
		add:function(){
			var me = this;
			MainFrameUtil.openDialog({
	  			id:'messageAdd',
				href:'${Config.baseURL}view/cloud-purchase-web/login/sellingswmessage/add',
				iframe:true,
				modal:true,
				width:"80%",
				height:420,
				onClose:function(){
					 me.getDataGrid();
                 }
			});
		},
		
		//更新
		edit:function(){
			var me = this;
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows === null || rows.length === 0){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}else if(rows.length > 1){
				MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'messageEdit',
	  			params:{"id":rows[0].id},
				href:'${Config.baseURL}view/cloud-purchase-web/login/sellingswmessage/edit',
				iframe:true,
				modal:true,
				width:"80%",
				height:420,
				onClose:function(){
					 me.getDataGrid();
                }
			});
		},
		
		//渲染列表消息标题
    	titleRender : function(value,row,index){
            return "<a onclick=\"listVue.view('"+row.id+"')\">"+row.title+"</a>";
        },
      	//点击销售机会名称查看详细信息
        view:function(id){
            MainFrameUtil.openDialog({
            	id:"messageDetail",
                params:{"id":id},
                href:'${Config.baseURL}view/cloud-purchase-web/login/sellingswmessage/detail',
                modal:true,
                iframe:true,
                width:"80%",
                height:500,
                onClose:function(){
                    $("#zoneGrid").datagrid('reload');
                }
           })
        },
		
		del:function(){
			var me = this;
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows === null || rows.length === 0){
				MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认删除吗?",
      	        body:"删除后不可恢复",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		var ids = new Array();
      	        		for(var obj in rows){
      	        			ids.push(rows[obj].id);
      	        		}
      	        		$.ajax({
	      	  				url:basePath+"cloud-purchase-web/swMessageController/deleteSwMessage",
	      	  				type:"post",
		      	  			data:$.toJSON(ids),
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