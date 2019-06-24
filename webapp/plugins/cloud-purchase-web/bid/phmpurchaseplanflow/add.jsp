<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理购电计划制定新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
</head>
<body id="phPlanEditVue">
	<su-panel>
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
				<mk-form-col :label="formFields.setters.label" required_icon="true">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.setters" type="text" disabled="true" ></su-textbox>
				</mk-form-col>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" :disabled.sync="flag"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" :disabled.sync="flag"></su-select>
				</mk-form-col>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :disabled="ymDisable" :data.sync="params.phmPurchasePlanMonth.ym" name="ym" :disabled.sync="flag"></su-datepicker>
				</mk-form-col>
				<!-- 计划名称  -->
				<mk-form-col :label="formFields.planName.label">
					<su-textbox :data.sync="params.phmPurchasePlanMonth.planName" type="text"></su-textbox>
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
				savingFlag: false, //是否正在保存，如果正在保存，则不允许重复点击保存
				ymDisable: true,
				queryParams:{phmPlanConsRela:{planId:''}},
				formFields:{},
			    params:{phmPurchasePlanMonth:{id:null,ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null,planName:null},
			    		examineList:[]},
			    flag:false,
	    		//非空验证规则
		        dataOption: {
		            rules: {	
		            	ym:{required: !null},
		            	tradeVarietyType:{required: !null},
		            	tradeModeType:{required: !null},
		            	tradePeriod:{required: !null}
		            }
		        },
		        valid:false,
	    		deleteParams:[],
	    		datas:[]
			},
			ready:function(){
				var me = this;
				MainFrameUtil.setDialogButtons(me.getButtons(),"plan_add");
				//初始化数据
				var userInfo = $.getLoginUserInfo(basePath);
				me.params.phmPurchasePlanMonth.setters = userInfo.userName;
				//列表数据加载
				ComponentUtil.getFormFields("purchase.bid.consPlanRela",this);
				ComponentUtil.buildGrid("purchase.bid.consPlanRela",$("#consGrid"),{ 
				    width:"100%",
				    height:"100%",
				    method: 'post',
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
				if(MainFrameUtil.getParams("plan_add").flag == true){//标记总览页面新建交易，给出默认值
					me.flag = true;
					me.params.phmPurchasePlanMonth.tradePeriod = "02";
					me.params.phmPurchasePlanMonth.tradeMode = "01";
					var date = new Date();
					var year = date.getFullYear();
					var month= date.getMonth()+2;
					if(month < 10){
						me.params.phmPurchasePlanMonth.ym = year +"-0"+month;
					}else{
						me.params.phmPurchasePlanMonth.ym = year +"-"+month;
					}
				}
				$("#process1").on("click",function(){
					$('#consGrid').datagrid('reload');
				});
			},
			methods: {
				/* back:function(){
					window.location.href="${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/list";
				}, */
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc", handler:function(btn,params){me.save(btn.target)} },
    					{text:"提交",type:"primary", handler:function(btn,params){me.submit(btn.target)}},
    	               {text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_add")}}];
		            return buttons;
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
		        		me.savePlan("save", target);
		        	}
				},
				savePlan: function(val, target){
					var me = this;
					if(!me.savingFlag){
						me.savingFlag = true;
						me.$refs.form1.valid();
						if(!me.valid){
			  	    		MainFrameUtil.alert({title:"提示",body:"有必填项未填！"});
			  	    		//按钮可点击
	       					$(target).attr("disabled",false);
			  	    		return;
			  	    	}
						var params = $.parseJSON($.toJSON(me.params));
						if(params.phmPurchasePlanMonth.tradePeriod == "02"){
			        		params.phmPurchasePlanMonth.ym = params.phmPurchasePlanMonth.ym.replace("-","");
		        		}
						$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/phmPurchasePlanMonthController",
							type : 'post',
							data:$.toJSON(params),
							contentType : 'application/json;charset=UTF-8',
							success : function(data) {
								if(data.flag){
									me.params.phmPurchasePlanMonth.id = data.data.phmPurchasePlanMonth.id;
									me.planId = data.data.phmPurchasePlanMonth.id;
									var examineList = data.data.examineList;
									var consIdToIdMap = {};
									for(var i in examineList){
										consIdToIdMap[examineList[i].consId] = examineList[i].id;
									}
									var rows = $("#consGrid").datagrid('getData').originalRows; 
									for(var i in rows){
										rows[i].id = consIdToIdMap[rows[i].consId];
									}
									if(val == "save"){
										me.savingFlag = false;
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
															me.savingFlag = false;
															if(data.flag){
																MainFrameUtil.alert({title:"提示",body:data.msg});
																MainFrameUtil.setParams(true,"plan_add");//标记总览页面，提交后打开新页签，展示交易主列表
																MainFrameUtil.closeDialog("plan_add");
															}else{
																MainFrameUtil.alert({title:"失败",body:data.msg});
																//按钮可点击
										       					$(target).attr("disabled",false);
															}
														}
													})
									            }else{
									            	me.savingFlag = false;
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
					}
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
		        		me.savePlan("commit",target);
		        	}
				},
				editCons:function(){
					var me = this;
					var checkedIds = [];
					for(var i = 0 ; i < me.datas.length ; i++){
						checkedIds.push(me.datas[i].consId);
					}
					var month = me.params.phmPurchasePlanMonth.ym ;
					if(month == null || month == ''){
						MainFrameUtil.alert({title:"警告",body:"请选择计划时间！"});
				    	return;
					}
					month = month.replace("-","");
					//选择用户
			  		MainFrameUtil.openDialog({
			  			id:"consDialog",
			  			params:{singleSelect:false , checkedIds : checkedIds, smppaYm: month},
						href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/cons-dialog',
						iframe:true,
						modal:true,
						width:"50%",
						height:620,
						onClose:function(params){
							if(params != null && params.length > 0){
								for(var i in params){
									params[i].consId = params[i].id;
									params[i].id = null;
									me.datas.push(params[i]);
								}
								$('#consGrid').datagrid({loadFilter: pagerFilter}).datagrid('loadData',me.datas);
							}
						}
					});
				},
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
				'params.phmPurchasePlanMonth.tradePeriod':function(value){
					if(MainFrameUtil.getParams("plan_add").flag != true){
						this.params.phmPurchasePlanMonth.ym = "";
						if(value == "01"){//年
							this.ymDisable = false;
							this.ymFormat = "YYYY";
						}else if(value == "02"){//月
							this.ymDisable = false;
							this.ymFormat = "YYYY-MM";
						}else{
							this.ymDisable = true;
						}
					}
				},
				'params.phmPurchasePlanMonth.ym':function(){
					if(MainFrameUtil.getParams("plan_add").flag != true){
						var rows = $("#consGrid").datagrid('getData').originalRows;
						if(rows != null && rows.length > 0){
							for(var i = rows.length; i > 0; i--){
								$('#consGrid').datagrid('deleteRow',i-1);
							}
						}
					}
				}
			}
		});
	</script>
</body>
</html>