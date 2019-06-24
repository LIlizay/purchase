<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-购电计划制定编辑</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<style type="text/css">
</style>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="phPlanEditVue">
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel>
			<mk-form-row>
				<!-- 制定人 -->
				<mk-form-col :label="formFields.setters.label" required_icon="true">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.setters" type="text" disabled="disabled" ></su-textbox>
				</mk-form-col>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :disabled="ymDisable" :data.sync="params.phmPurchasePlanMonth.ym" name="ym" disabled="disabled"></su-datepicker>
				</mk-form-col>
				<!-- 计划名称  -->
				<mk-form-col :label="formFields.planName.label">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.planName" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</su-form>
	<mk-panel title="用户列表" line="true" slot="bottom" height="83%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="editCons">新增用户</su-button>
			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout">删除用户</su-button>
		</div>
		<div id="consGrid" class="col-xs-12" ></div>
	</mk-panel>
	<script type="text/javascript">
		function pagerFilter(data){
		    if (typeof data.length == 'number' && typeof data.splice == 'function'){    // 判断数据是否是数组
		        data = {
		            total: data.length,
		            rows: data
		        }
		    }
		    var dg = $(this);
		    var opts = dg.datagrid('options');
		    var pager = dg.datagrid('getPager');
		    if(opts.pageNumber==0){
		   		 opts.pageNumber=1;
		    }
		    pager.pagination({
		        onSelectPage: function(pageNum, pageSize){
		            opts.pageNumber = pageNum;
		            opts.pageSize = pageSize;
		            pager.pagination('refresh',{
		                pageNumber:pageNum,
		                pageSize:pageSize
		            });
		            dg.datagrid('loadData',data);
		        }
		    });
		    if (!data.originalRows){
		        data.originalRows = (data.rows);
		    }
		    var start = (opts.pageNumber-1)*parseInt(opts.pageSize);
		    var end = start + parseInt(opts.pageSize);
		    data.rows = (data.originalRows.slice(start, end));
		    return data;
		}
		
		var basePath = '${Config.baseURL}';
		//对应 js
		var phPlanEditVue=new Vue({
			el: '#phPlanEditVue',
			data: {
				ymFormat:null,
				firstLoadFlag:false,
				ymDisable: true,
				queryParams:{planId:''},
				formFields:{},
			    params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null},
			    	examineList:[],delIds:[]},
			},
			ready:function(){
				var me = this;
				MainFrameUtil.setDialogButtons(me.getButtons(),"plan_edit");
				//查询字段名称加载
				me.queryParams.planId = location.search.substr(8);
				//列表数据加载
				ComponentUtil.getFormFields("purchase.bid.consPlanRela",this);
				ComponentUtil.buildGrid("purchase.bid.consPlanRela",$("#consGrid"),{ 
// 					url: basePath + 'cloud-purchase-web/phmAgrePqExamineController/getPhmAgreExamineByPlanId',
					data:[],
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
				    },
				    onLoadSuccess:function(data){	    	
				    	if(!me.firstLoadFlag){
				    		me.getGridData();
				    	}
				    	me.firstLoadFlag=true;
				    }
			    }); 
			},
			methods: {
				initData:function(){
					var me = this;
					//初始化数据
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController/"+me.queryParams.planId,
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
				
				getGridData: function(){
					var me = this;
					var queryParams=$.extend(true,{},me.queryParams);
					queryParams["pagingFlag"]=false;
					$.ajax({
						url: basePath + 'cloud-purchase-web/phmAgrePqExamineController/getPhmAgreExamineByPlanId',
						type : 'post',
						data:$.toJSON(queryParams),
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data.flag){
								$('#consGrid').datagrid({loadFilter: pagerFilter}).datagrid('loadData',data.rows);
							}else{
								MainFrameUtil.alert({title:"失败提示",body:data.msg});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"刷新网页重试！"});
						}
					});
				},
				
				save: function(target){
					//按钮不可点击
	           		$(target).attr("disabled","disabled");
					//所有行
					var rows = $("#consGrid").datagrid('getData').originalRows; 
					var me = this;
		        	if(rows==null||rows.length==0){
						MainFrameUtil.alert({title:"提示",body:"请选择参与交易的合同用户！"});
						//按钮可点击
       					$(target).attr("disabled",false);
		        		return;
		        	}else{
		        		me.params.examineList = rows;
		        		if(me.params.phmPurchasePlanMonth.tradePeriod == "02"){
			        		me.params.phmPurchasePlanMonth.ym = me.params.phmPurchasePlanMonth.ym.replace("-","");
		        		}
		        		this.params.examineList = rows;
		        		me.savePlan("save",target);
		        	}
				},
				savePlan: function(val, target){
					var me = this;
					me.$refs.form1.valid();
					if(!me.valid){
		  	    		MainFrameUtil.alert({title:"提示",body:"有必填项未填！"});
						//按钮可点击
       					$(target).attr("disabled",false);
		  	    		return;
		  	    	}
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController",
						type : 'post',
						data:$.toJSON(this.params),
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data.flag){
								me.params.phmPurchasePlanMonth = data.data.phmPurchasePlanMonth;
								me.planId = data.data.phmPurchasePlanMonth.id;
								if(val == "save"){
									MainFrameUtil.alert({title:"提示",body:"保存成功！"});
									//按钮可点击
			       					$(target).attr("disabled",false);
								}else{
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
															MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
															MainFrameUtil.closeDialog("plan_edit")
														}else{
															MainFrameUtil.alert({title:"失败",body:data.msg});
														}
														//按钮可点击
								       					$(target).attr("disabled",false);
													}
												})
								            }else{
					    						//按钮可点击
					           					$(target).attr("disabled",false);
								            }
								        }
						    		});
								}
							}else{
								if(val == "save"){
									MainFrameUtil.alert({title:"提示",body:"保存失败！"});
								}else{
									MainFrameUtil.alert({title:"提示",body:"提交时保存失败！"});
								}
								//按钮可点击
		       					$(target).attr("disabled",false);
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"保存失败！"});
							//按钮可点击
	       					$(target).attr("disabled",false);
							return false;
						}
					});
				},
				submit: function(target){
					var me = this;
					//按钮不可点击
   					$(target).attr("disabled","disabled");
					var rows = $("#consGrid").datagrid('getData').originalRows; 
					if(rows==null||rows.length==0){
						MainFrameUtil.alert({title:"提示",body:"请选择参与交易的合同用户！"});
						//按钮可点击
       					$(target).attr("disabled",false);
		        		return;
		        	}else{
		        		me.params.examineList = rows;
		        		//先保存计划信息
		        		me.savePlan("commit", target);
		        	}
				},
				editCons: function(){
					var that = this;
					var rows = $("#consGrid").datagrid('getData').originalRows; 
					var checkedIds = [];
					for(var i = 0 ; i < rows.length ; i++){
						checkedIds.push(rows[i].consId);
					}
					//选择用户
			  		MainFrameUtil.openDialog({
			  			id:"consDialog",
			  			params:{singleSelect:false , checkedIds : checkedIds, smppaYm: that.params.phmPurchasePlanMonth.ym},
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/cons-dialog',
						iframe:true,
						modal:true,
						width:"50%",
						height:620,
						onClose:function(params){
							if(params != null && params.length > 0){
								for(var i in params){
									params[i].consId = params[i].id;//加个consId:xxx的map数据
									params[i].id = null;
									rows.push(params[i]);
								}
								$('#consGrid').datagrid({loadFilter: pagerFilter}).datagrid('loadData',rows);
							}
						}
					});
				},
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc", handler:function(btn,params){me.save(btn.target)}},
		        					{text:"提交",type:"primary", handler:function(btn,params){me.submit(btn.target)}},
		        	               {text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_edit")}}];
		        	
		        	
		            return buttons;
		        },
					//删除用户
				onlogout:function(){
					var me = this; 
					//所有行
	            	var rows = $("#consGrid").datagrid('getData').originalRows; 
	            	var selInfo = $('#consGrid').datagrid('getChecked');
					if(selInfo==null||selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}else{
						//选择行的consId
						var map= {};
						for(var i = 0; i<selInfo.length; i++){
							map[selInfo[i].consId]=selInfo[i].consId;
							me.params.delIds.push(selInfo[i].consId);
						}
						//循环所有行
						for(var i = rows.length-1 ; i >= 0; i--){
							if(map[rows[i].consId] != undefined){//该行被选中
								//从所有行数据中移除，选中的
								rows.splice(i,1);
							}
						}
						$('#consGrid').datagrid({loadFilter: pagerFilter}).datagrid('loadData',rows);
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