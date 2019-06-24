<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>总览购售电交易-月度购电管理购电计划制定新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="phPlanEditVue">
	<su-panel>
		<div><su-button class="btn-operator" s-type="default" labeled="true" label-ico="angle-double-left" @click="back">返回</su-button></div>
		<div><hr style="margin:0px;height:10px"></div>
		<div id="process" style="height:60px;width:100%;margin-bottom:5px">
			<div id="process1" class="greenBlock" style="background-color:#21ac8d">
				<div style="width:94%;float:left;height:100%">
					<div class="planCons"></div>
				</div>
				<img src="img/arrow2.png" style="width:6%;float:left;height:60px"></img>
			</div>
			<div id="process2" class="grayBlock">
				<div style="width:94%;float:left;height:100%">
					<div class="examinGrayCons"></div>
				</div>
				<img src="img/arrow.png" style="width:6%;float:left;height:60px"></img>
			</div>
			<div id="process3" class="grayBlock">
				<div style="width:94%;float:left;height:100%">
					<div class="transactionGrayCons"></div>
				</div>
				<img src="img/arrow.png" style="width:6%;float:left;height:60px"></img>
			</div>
			<div id="process4" class="grayBlock" style="width:22%">
				<div class="phmdealinfoGrayCons" style="margin-left:15%"></div>
			</div>
		</div>
	</su-panel>
	
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel>
			<mk-form-row>
				<!-- 制定人 -->
				<mk-form-col :label="formFields.setters.label" :class="{'display-none':!formFields.setters.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.setters" type="text" disabled="disabled" ></su-textbox>
				</mk-form-col>
				<!-- 计划年月  -->
				<mk-form-col :label="formFields.ym.label" :class="{'display-none':!formFields.ym.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.ym" type="text" name="ym" disabled="disabled" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</su-form>
	<mk-panel title="用户列表" line="true" slot="bottom" height="70%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="editCons">新增用户</su-button>
			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout">删除用户</su-button>
		</div>
		<div id="consGrid" class="col-xs-12" ></div>
	</mk-panel>
	<div class="col-xs-12 pull-right form-right-margin mt_10" style="text-align:center;margin-top:10px">
        <su-button  class="btn-width-small" s-type="default"   v-on:click="save">保存</su-button>
        <su-button  class="btn-width-small" s-type="primary"   v-on:click="submit">提交</su-button>			                    
	</div>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var phPlanEditVue=new Vue({
			el: '#phPlanEditVue',
			data: {
				queryParams:{phmPlanConsRela:{planId:''}},
				formFields:{},
			    params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null},
	    				phmPlanConsRelaDetailList:[]},
	    		deleteParams:[]
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				me.queryParams.phmPlanConsRela.planId = '${param.id}';
				me.params.phmPurchasePlanMonth.ym = '${param.ym}'.replace("-","");
				//列表数据加载
				ComponentUtil.getFormFields("purchase.bid.consPlanRela",this);
				ComponentUtil.buildGrid("purchase.bid.consPlanRela",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/phmPlanConsRelaController/getPhmPlanConsRelaPageByPlanId',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:me.queryParams,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    }
			    }); 
				$("#process1").on("click",function(){
					$('#consGrid').datagrid('reload');
				});
			},
			methods: {
				initData:function(){
					var me = this;
					//初始化数据
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.queryParams.phmPlanConsRela.planId,
						type : 'get',
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data.data!=null){
								me.params.phmPurchasePlanMonth = data.data;
							}else{
								var userInfo = $.getLoginUserInfo(basePath);
								me.params.phmPurchasePlanMonth.setters = userInfo.userName;
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"查询明细失败！"});
						}
					});
				},
				back:function(){
					window.location.href="${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/list";
				},
				save:function(){
					var rows = $("#consGrid").datagrid('getRows'); 
					var me = this;
		        	if(rows==null||rows.length==0){
						MainFrameUtil.alert({title:"提示",body:"请选择参与交易的合同用户！"});
		        		return;
		        	}else{
		        		this.params.phmPlanConsRelaDetailList = rows;
		        		$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController",
							type : 'post',
							data:$.toJSON(this.params),
							contentType : 'application/json;charset=UTF-8',
							success : function(data) {
								if(data.flag){
									me.params.phmPurchasePlanMonth = data.data.phmPurchasePlanMonth;
									me.planId = data.data.phmPurchasePlanMonth.id;
									MainFrameUtil.alert({title:"提示",body:"保存成功！"}); 
								}else{
									MainFrameUtil.alert({title:"提示",body:"保存失败！"});
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"保存失败！"});
							}
						});
		        	}
				},
				submit:function(){
					var me = this;
					var rows = $("#consGrid").datagrid('getRows'); 
					if(rows==null||rows.length==0){
						MainFrameUtil.alert({title:"提示",body:"请选择参与交易的合同用户！"});
		        		return;
		        	}else{
		        		me.params.phmPlanConsRelaDetailList = rows;
		        		//先保存计划信息
		        		$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController",
							type : 'post',
							data:$.toJSON(this.params),
							contentType : 'application/json;charset=UTF-8',
							success : function(data) {
								if(data.flag){
									me.params.phmPurchasePlanMonth = data.data.phmPurchasePlanMonth;
									me.planId = data.data.phmPurchasePlanMonth.id;
									MainFrameUtil.confirm({
								        title:"确认",
								        body:"提交后计划将无法修改，确定提交吗？",
								        onClose:function(type){
								            if(type=="ok"){//确定
								            	$.ajax({
													url:basePath+"cloud-purchase-web/phmPurchasePlanMonthController/submit/"+me.planId,
													type:"post",
													success:function(data){
														if(data.flag){
															MainFrameUtil.alert({title:"提示",body:data.msg});
															me.back();
														}else{
															MainFrameUtil.alert({title:"失败",body:data.msg});
														}
													}
												})
								            }
								        }
						    		});
								}else{
									MainFrameUtil.alert({title:"提示",body:"提交时保存失败！"});
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"提交失败！"});
							}
						});
		        	}
				},
				editCons:function(){
					var that = this;
					var rows = $("#consGrid").datagrid('getRows');
					var checkedIds = [];
					for(var i = 0 ; i < rows.length ; i++){
						checkedIds.push(rows[i].consId);
					}
					//选择用户
			  		MainFrameUtil.openDialog({
			  			id:"consDialog",
			  			params:{singleSelect:false , checkedIds : checkedIds, smppaYm: that.params.phmPurchasePlanMonth.ym},
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanmonth/cons-dialog',
						iframe:true,
						modal:true,
						width:"50%",
						height:620,
						onClose:function(params){
							if(params.length&&params.length > 0){
								var datas = new Array();
								for(var key in params){
									var row = new Object();
									for(var obj in params[key]){
										if(obj=="id"){
											row["consId"] = params[key]["id"];
										}else{
											row[obj] = params[key][obj];
										}
									}
									datas.push(row);
								}
								$('#consGrid').datagrid('loadData',datas);   
							}
						}
					});
				},
				onlogout:function(){
					var me = this; 
					//删除用户
	            	var rows = $('#consGrid').datagrid('getRows');
	            	var selInfo = $('#consGrid').datagrid('getChecked');
					if(selInfo==null||selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}else{
						for(var key in selInfo){
							var index = 0;
							while(rows.length>0&&rows.length>index){
								if(rows[index]["consId"]==selInfo[key]["consId"]){
									rows.splice(index,1);
									break;
								}
								index++;
							}
						}
						$('#consGrid').datagrid('loadData',rows);
					}
				}
			},
			watch:{
				formFields:function(){
					this.initData();
				}
			}
		});
	</script>
</body>
</html>