<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>档案管理-用户档案关联</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="listBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel slot="top" deployable="false" >
		<mk-search-visible>
			<!-- 用户名称 -->
			<su-form-group :label-name='formFields.userName.label' col="4" class="form-control-row" label-align="right">
				 <su-textbox :data.sync="params.userName" ></su-textbox>
			</su-form-group>
			<!-- 用户账号 -->
			<su-form-group :label-name='formFields.loginName.label' col="4" class="form-control-row" label-align="right">
				<su-textbox :data.sync="params.loginName" ></su-textbox>
			</su-form-group>
			<!-- 关联状态 -->
			<su-form-group :label-name='formFields.relaFlag.label' class="form-control-row" col="4" label-align="right">
				 <su-select :data-source="relateStatusList" multiple="false" 
					:select-value.sync="params.relateStatus" label-name="name"></su-select>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="用户档案关联列表" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		 	<!-- <su-button class="btn-operator" s-type="default" labeled="true" label-ico="comment" v-on:click="remind">发送账户</su-button> -->
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="refresh" v-on:click="binding">绑定档案</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="relieve">解除绑定</su-button>
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
	    params:{swConsRegister:{},relateStatus:""},
	    relateStatusList:[
	       {value:"01",name:"未关联"},
	       {value:"02",name:"已关联"},
	     ],
	},
	ready:function(){
		//查询字段名称加载
		ComponentUtil.getFormFields("selling.login.swconsinfo",this);
		//列表数据加载
		ComponentUtil.buildGrid("selling.login.swconsinfo",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/swConsInfoController/relaPage',
			queryParams:this.params,
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
		relaFlagFormatter:function(value,row,text){
			if(value =='1'){
				return "已关联"
			}else{
				return "未关联"
			}
		},
		binding:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.examineStatus=="1"){
				MainFrameUtil.alert({title:"警告",body:"该账户已经被绑定！"});
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'add',
	  			params:{loginName:row.loginName},
				href:'${Config.baseURL}view/cloud-purchase-web/login/swconsinfo/add',
				iframe:true,
				modal:true,
				width:500,
				height:150,
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.save(function(){
	                    		me.getDataGrid();
	                    		MainFrameUtil.closeDialog('add');
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
		
		//提醒用户
		remind:function(){
			var me = this;
			var rows = $("#listGrid").datagrid("getChecked");
			if(rows == null || rows.length === 0){
				MainFrameUtil.alert({title:"提示：",body:"请选择提醒用户！"});
				return;
			}
			for(var i=0; i<rows.length; i++){
				var row = rows[i];
				console.log(row.relaFlag);
				if(row.relaFlag !="1"){
					MainFrameUtil.alert({title:"提示：",body:row.userName + "<br/>用户未关联档案！"});
					return;
				}
			}
			$.ajax({
				url:basePath+"cloud-purchase-web/swConsInfoController/remindMessage",
				type:"post",
				data:$.toJSON(rows),
				contentType:"application/json;charset=UTF-8",
				success:function(data){
					MainFrameUtil.alert({
                        title:"提示：",
                        body:data.msg,
                    })
				}
			});
		},
		
		relieve:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.examineStatus=="0"){
				MainFrameUtil.alert({title:"警告",body:"该账户没有被绑定！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要解除绑定选中记录吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		$.ajax({
	      	  				url:basePath+"cloud-purchase-web/swConsInfoController/" + row.id,
	      	  				type:"delete",
	      	  				success:function(data){
	      	  					if(data.flag){
	      	  						MainFrameUtil.alert({title:"提示",body:data.msg});
	      	  						listVue.getDataGrid();
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