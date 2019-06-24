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
			<!-- 用户类型 -->
			<su-form-group :label-name='formFields.consTypeName.label' col="4" class="form-control-row" label-align="right">
				 <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_registerConsType" multiple="false" 
					:select-value.sync="params.swConsRegister.consType" label-name="name"></su-select>
			</su-form-group>
			<!-- 手机号码 -->
			<su-form-group :label-name='formFields.phone.label' col="4" class="form-control-row" label-align="right">
				 <su-textbox :data.sync="params.swConsRegister.phone" ></su-textbox>
			</su-form-group>
			<!-- 注册日期 -->
			<su-form-group :label-name='formFields.registerDate.label' col="4" class="form-control-row" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.startDate" ></su-datepicker>
			</su-form-group>
			<!-- 至 -->
			<su-form-group :label-name='formFields.to.label' col="4" class="form-control-row" label-align="right">
				<su-datepicker format="YYYY-MM-DD" :data.sync="params.endDate" ></su-datepicker>
			</su-form-group>
			<!-- 审核状态 -->
			<su-form-group :label-name='formFields.examineStatusName.label' class="form-control-row" col="4" label-align="right">
				 <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_examineStatus" multiple="false" 
					:select-value.sync="params.swConsRegister.examineStatus" label-name="name"></su-select>
			</su-form-group>
			<!-- 用户名称 -->
			<su-form-group :label-name='formFields.consName.label' col="4" class="form-control-row" label-align="right">
				<su-textbox :data.sync="params.swConsRegister.consName" ></su-textbox>
			</su-form-group>
		</mk-search-visible>
		<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
			<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
		</div>
	</mk-search-panel>
	<mk-panel title="用户注册列表" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="refresh" v-on:click="effect">审核通过</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="invalid">审核不通过</su-button>
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
	    params:{swConsRegister:{}},
	},
	ready:function(){
		//查询字段名称加载
		ComponentUtil.getFormFields("selling.login.swconsregister",this);
		//列表数据加载
		ComponentUtil.buildGrid("selling.login.swconsregister",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/swConsRegisterController/page',
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
			this.params = {swConsRegister:{}};
		},
		getDataGrid:function(){
		    $("#listGrid").datagrid('load',this.params); 
		},
		consNameFormatter:function(value,row,text){
			return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/login/swconsregister/detail',
				iframe:true,
				modal:true,
				width:800,
				height:450
			});
		},
		fileIdFormatter:function(value,row,text){
			if(value!=null&&value!=''){
				var obj = eval('(' + value + ')');
				return "<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+obj.fileName+"</a>";
			}
		},
		effect:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.examineStatus=="1"){
				MainFrameUtil.alert({title:"警告",body:"该用户已经被审核！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"确认审核通过吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
	   	        		$.ajax({
	      	  				url:basePath+"cloud-purchase-web/swConsRegisterController/approvalExamine/" + row.id,
	      	  				type:"post",
	      	  				success:function(data){
	      	  					if(data.flag){
	      	  						MainFrameUtil.alert({title:"提示",body:data.msg});
	      	  						me.getDataGrid()
	      	  					}else{
	      	  						MainFrameUtil.alert({title:"失败",body:data.msg});
	      	  					}
	      	  				}
	      	  			})
      	        	}
      	        }
  	    	});
		},
		invalid:function(){
			var me = this;
			var row = $("#listGrid").datagrid("getChecked");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.examineStatus=="1"){
				MainFrameUtil.alert({title:"警告",body:"该用户已经被审核！"});
				return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"确认审核不通过吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
	   	        		$.ajax({
	      	  				url:basePath+"cloud-purchase-web/swConsRegisterController/unApprovalExamine",
	      	  				type:"post",
		      	  			data:$.toJSON(row),
		    				contentType:"application/json;charset=UTF-8",
	      	  				success:function(data){
	      	  					if(data.flag){
	      	  						MainFrameUtil.alert({title:"提示",body:data.msg});
	      	  						me.getDataGrid()
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