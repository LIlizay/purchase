<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购售电交易-用户预警预测详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
</style>
</head>
<body id="ruleAddVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="偏差预警规则查看" line="true" height="225px">
	    	<mk-form-panel label-width="140px" num="2">
	    		<mk-form-row >
	    			<!-- 规则类型标识 -->
		    		<mk-form-col :label="formFields.ruleFlag.label" required_icon="true">
		    			<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.smConsEarlyWarning.ruleFlag"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="ruleFlag"></su-select>
		    		</mk-form-col>
		    		<!-- 用户名称 -->
	    			<mk-form-col v-if="!consFlag" :label="formFields.consName.label" required_icon="true">
	    				<mk-trigger-text disabled="disabled" :data.sync="consInfo.consName" editable="false" @mk-trigger="selectCons"></mk-trigger-text>
	    			</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 规则名称 -->
		    		<mk-form-col  :label="formFields.name.label" required_icon="true">
		    			<su-textbox disabled="disabled" :data.sync="params.smConsEarlyWarning.name" type="text" name="name"></su-textbox>
		    		</mk-form-col>
		    		<!-- 预警频率 -->
		    		<mk-form-col  :label="formFields.frequencyName.label">
		    			<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.smConsEarlyWarning.frequency"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnFrequency" ></su-select>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row height="65px">
		    		<!-- 预警规则说明 -->
	                <mk-form-col  colspan="2" :label="formFields.ruleExplain.label" :class="{'display-none':!formFields.ruleExplain.formHidden}">
	                    <su-textbox disabled="disabled" type="textarea" rows="2" rols="2" placeholder="" :data.sync="params.smConsEarlyWarning.ruleExplain"></su-textbox>
	                </mk-form-col>
		    	</mk-form-row>
	    	</mk-form-panel>
	    </mk-panel>
	    <mk-panel title="正偏差预警规则" line="true">   
		    <div id="upperGrid" class="col-xs-12" style="height:100px"></div>
		</mk-panel>
		<mk-panel title="负偏差预警规则" line="true" >    
		    <div id="lowerGrid" class="col-xs-12" style="height:100px"></div>
		</mk-panel>
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var ruleAddVue =new Vue({
			el: '#ruleAddVue',
			data: {	
				consFlag : false,
				formFields:{},
				fileName:"",
				consInfo:null,
				ruleId:null,
				ruleName:null,
			    params:{
			    	smConsEarlyWarning:{
			    		consId:null,
			    		ruleFlag:null,
			    		name:null,
			    		frequency:null, //频率
			    		ruleExplain:null //规则说明
			    	},
			    	smConsWarningInfoList:[],
			    	delIds:[],
			    	updateList:[],
			    	addList:[]
			    },
			   
				//非空验证规则
		        dataOption: {
		            rules: {	
		            	name:{required: !0}
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.deviationcheck.smconsearlywarning",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"ruledetail");
				me.initUpper();
				me.initLower();
				me.params.smConsEarlyWarning.id = MainFrameUtil.getParams("ruledetail").id;
				me.initData();
			},
			methods: {
				
				initGrid:function(){
					var me = this;
					console.log("me.params.smConsWarningInfoList",me.params.smConsWarningInfoList);
					if(me.params.smConsWarningInfoList != null && me.params.smConsWarningInfoList.length>0){
						var list = me.params.smConsWarningInfoList;
						var upperList = [];
						var lowerList = [];
						for(var i in list){
							if(list[i].warnPrompt == "01"){
								upperList.push(list[i]);
							}else if(list[i].warnPrompt == "02"){
								lowerList.push(list[i]);
							}
						}
						if(upperList != null && upperList.length > 0){							
							$('#upperGrid').datagrid('loadData', upperList);
						}
						if(lowerList != null && lowerList.length > 0){		
							$('#lowerGrid').datagrid('loadData', lowerList);
						}
						
					}
				},
				initData:function(){
					var me = this;
					if(me.params.smConsEarlyWarning.id != null && me.params.smConsEarlyWarning.id != ""){
						$.ajax({
							url:basePath+"cloud-purchase-web/smConsEarlyWarningController/"+me.params.smConsEarlyWarning.id,
			                type:"get",
			                success : function(data) {
			                    if(data.flag == true){
			                    	me.params = data.data;
			                    	if(me.params.smConsEarlyWarning.ruleFlag == "0"){
				                    	me.initCons(me.params.smConsEarlyWarning.consId);
			                    	}
			                    	me.initGrid();
			                    }else{
			                        MainFrameUtil.alert({
			                            title:"失败提示",
			                            body:data.msg,
			                        });
			                    }
			                },
			                error : function() {
			                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
			                }
						});
					}else{
						MainFrameUtil.alert({title:"提示",body:"编辑数据异常！"});
						MainFrameUtil.closeDialog("ruleedit");
					}
				},
				
				initCons:function(consId){
					var me = this;
					$.ajax({
						url:basePath+"cloud-purchase-web/sConsDialogController/getConsInfoById/"+consId,
						type:"get",
						success:function(data){
							me.consInfo = data;
						}
					});
				},
				
				initUpper:function(){
					$("#upperGrid").datagrid({
						width:"100%",
						height:150,
						striped:true,
						//fitColumns:false,
						singleSelect:true,
						rownumbers:true,
						idField:'id',
						columns:[[
						        {field:'id',hidden:true,align:'left'},
						        {field:'warnPrompt',hidden:true,align:'left'},
						        {field:'ck',checkbox:true},
 								{field:'minProp',title:'偏差预警阈值（%）',width:'50%',align:'left'},
								{field:'maxProp',hidden:true,title:'偏差区间最大值（%）',width:'33%',align:'left'},
 								{field:'warnType',title:'预警级别',width:'49%',align:'left',
									formatter: function(value,row,index){
										if (value == '01'){
											return "黄色预警";
										}else if(value == "02"){
											return "橙色预警";
										}else if(value == "03"){
											return "红色预警";
										}
									}

								}
						]]
					});
				},
				initLower:function(){
					$("#lowerGrid").datagrid({
						width:"100%",
						height:150,
						striped:true,
						//fitColumns:false,
						singleSelect:true,
						rownumbers:true,
						idField:'id',
						columns:[[
						        {field:'id',hidden:true,align:'left'},
						        {field:'warnPrompt',hidden:true,align:'left'},
						        {field:'ck',checkbox:true},
						        {field:'minProp',title:'偏差预警阈值（%）',width:'50%',align:'left'},
								{field:'maxProp',hidden:true,title:'偏差区间最大值（%）',width:'33%',align:'left'},
 								{field:'warnType',title:'预警级别',width:'49%',align:'left',
									formatter: function(value,row,index){
										if (value == '01'){
											return "黄色预警";
										}else if(value == "02"){
											return "橙色预警";
										}else if(value == "03"){
											return "红色预警";
										}
									}

								}
						]]
					});
				},
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("ruledetail")}}];
		            return buttons;
		        }
			},
			watch:{
				"params.smConsEarlyWarning.ruleFlag" : function(value){
					var me = this;
					if(value == "1"){
						me.consFlag = true;
					}else{
						me.consFlag = false;
					}
				}
			} 
		});
	</script>
</body>
</html>