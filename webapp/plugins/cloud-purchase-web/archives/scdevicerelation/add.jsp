<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>用户设备管理-新增</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电用户信息管理">
			<mk-search-visible> 
				<!-- 设备编号 -->
				<su-form-group :label-name='formFields.deviceCode.label'  class="form-control-row " 
							:class="{'display-none':!formFields.deviceCode.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.deviceCode" type="text"></su-textbox>
                </su-form-group>
                <!-- 设备名称 -->
				<su-form-group :label-name='formFields.deviceName.label'  class="form-control-row " 
							:class="{'display-none':!formFields.deviceName.searchHidden}" col="4" label-align="right">
					<su-textbox :data.sync="params.name" type="text"></su-textbox>
                </su-form-group>
			</mk-search-visible>
		       
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="设备列表" line="true" v-cloak slot="bottom" height="100%">
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consVue = new Vue({
			el: '#consVue',
			data: {
				orgInfo : null,
				voltCodeData:[],
				formFields:{},
			    params:{
			    	deviceCode: null,
			    	name: null,
			    },
				//营销户号
				marketConsNo: null,
				//用户id
				consId: null,
				//设备编号
				deviceCode: null
			},
			ready:function(){
				//按钮初始化
				MainFrameUtil.setDialogButtons(this.getButtons(),"cons-add");

				//查询字段名称加载
				ComponentUtil.getFormFields("selling.device.relation",this);
				var row = MainFrameUtil.getParams("cons-add","row");
				this.consId = row.row[0].consId;
				this.marketConsNo = row.row[0].marketConsNo;
				this.params.deviceCode = row.row[0].code;
				//列表数据加载
				$("#consGrid").datagrid({ 
					url: basePath + 'cloud-purchase-web/scDeviceRelationController/addPage',
				    width:"100%",
				    scrollbarSize : 0,
				    method: 'post',
				    queryParams:this.params,
			        striped:true,
				    rownumbers: true,
				    pagination: true,
				    singleSelect:false,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    columns:[[    
				        {field:'ck',title:'ck',checkbox:true},    
				        {field:'code',title:'设备编号',width:100},    
				        {field:'name',title:'设备名称',width:100},
				        {field:'platformAddress',title:'平台地址',width:100}, 
				        {field:'devTypeName',title:'设备类型',width:100} 
				    ]],
				    onLoadSuccess : function(){
				    	$("#consGrid").datagrid("clearChecked");
				    }
			    }); 
			},
			methods: {
				reset: function(){
					this.params = {deviceCode: null, name: null};
				},
				getButtons:function(){
					var buttons = [{text:"保存 ",type:"primary",handler:this.save},
					               {text:"取消 ",handler:function(){MainFrameUtil.closeDialog("cons-add");}}];
					return buttons;
				},

				getDataGrid: function(){
					$('#consGrid').datagrid('options').queryParams = this.params;  
				    $("#consGrid").datagrid('reload'); 
				},
				save: function(){
					var me = this;
					var rows = $('#consGrid').datagrid('getChecked');
					if(rows == null || rows.length < 1){
						MainFrameUtil.alert({title:"提示",body:"请选择设备！"}); 
						return;
					}
					for(var i in rows){
						rows[i]["consId"] = me.consId;
						rows[i]["marketConsNo"] = me.marketConsNo;
						rows[i]["deviceId"] = rows[i].device;
					}
					$.ajax({
						url:basePath + 'cloud-purchase-web/scDeviceRelationController',
						type:'POST',
						data:$.toJSON(rows),
						contentType : 'application/json;charset=UTF-8',
						success: function(data){
							if(data.flag){
								MainFrameUtil.alert({title:"提示",body:"保存成功！"}); 
								MainFrameUtil.closeDialog("cons-add")
							}else{
								MainFrameUtil.alert({title:"提示",body: data.msg}); 
							}
						},
						error:function(data){
                			MainFrameUtil.alert({title:"错误",body:"请刷新重试！"}); 
                		}
						
					});
				}
			},
			watch : {
				
				
			}
			
		}); 
	</script>
</body>
</html>