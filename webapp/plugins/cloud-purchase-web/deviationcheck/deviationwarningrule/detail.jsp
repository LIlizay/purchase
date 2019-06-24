<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>偏差考核预警-偏差预警规则详情</title>
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
		<mk-panel title="偏差预警规则详情" line="true" height="225px">
	    	<mk-form-panel label-width="140px" num="3">
	    		<mk-form-row >
	    			<!-- 规则名称 -->
		    		<mk-form-col  :label="formFields.name.label" required_icon="true">
		    			<su-textbox disabled="disabled" :data.sync="params.smDeviationEarlyWarning.name" type="text" name="name"></su-textbox>
		    		</mk-form-col>
		    		<!-- 规则状态 -->
		    		<mk-form-col  :label="formFields.statusName.label" required_icon="true">
		    			<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationEarlyWarning.status"
							url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_contractVersStatus&valueIn='02','03'" name="status"></su-select>
		    		</mk-form-col>
		    		<!-- 预警频率 -->
		    		<mk-form-col  :label="formFields.frequency.label">
		    			<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationEarlyWarning.frequency"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnFrequency" ></su-select>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 参考文件 -->
					<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" colspan="2">
						<div class="form-group" style="margin:15px 0px 0px 15px !important"> 
                    		<strong id="fileName"></strong>
                    	</div>
					</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row height="65px">
		    		<!-- 预警规则说明 -->
	                <mk-form-col  colspan="3" :label="formFields.ruleExplain.label" :class="{'display-none':!formFields.ruleExplain.formHidden}">
	                    <su-textbox disabled="disabled" type="textarea" rows="2" rols="2" placeholder="" :data.sync="params.smDeviationEarlyWarning.ruleExplain"></su-textbox>
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
				statusFlag:false,
				mestatusFlag:false,
				formFields:{},
				fileName:"",
			    params:{
			    	smDeviationEarlyWarning:{
			    		id:null,
			    		name:null,
			    		status:null, //状态
			    		frequency:null, //频率
			    		fileId:null, //参考文件
			    		ruleExplain:null //规则说明
			    	},
			    	smWarningInfoList:[],
			    	delIds:[],
			    	updateList:[],
			    	addList:[]
			    },
			   
			  //非空验证规则
		        dataOption: {
		            rules: {	
		            	name:{required: !0},
		            	status:{required: !0}
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.deviationcheck.smdeviationearlywarning",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"ruledetail");
				me.initUpper();
				me.initLower();
				me.params.smDeviationEarlyWarning.id = MainFrameUtil.getParams("ruledetail").id;
				me.initData();
			},
			methods: {
				initGrid:function(){
					var me = this;
					console.log("me.params.smWarningInfoList",me.params.smWarningInfoList);
					if(me.params.smWarningInfoList != null && me.params.smWarningInfoList.length>0){
						var list = me.params.smWarningInfoList;
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
					if(me.params.smDeviationEarlyWarning.id != null && me.params.smDeviationEarlyWarning.id != ""){
						$.ajax({
							url:basePath+"cloud-purchase-web/smDeviationEarlyWarningController/"+me.params.smDeviationEarlyWarning.id,
			                type:"get",
			                success : function(data) {
			                    if(data.flag == true){
			                    	if(data.data.smDeviationEarlyWarning.status == "02"){
			                    		me.mestatusFlag = true;
			                    	}
			                    	me.params = data.data;
			                    	if(me.params.smDeviationEarlyWarning.fileId != null && me.params.smDeviationEarlyWarning.fileId != ""){
				                    	var file = eval("("+me.params.smDeviationEarlyWarning.fileId+")");
				                    	me.fileName = file.fileName;
				                    	$("#fileName").empty().append("<a href='${Config.getConfig('file.service.url')}" + file.fileId + "'>" + file.fileName +"</a>");
			                    	}
			                    	console.log(me.params.smWarningInfoList);
			                    	me.initGrid();
			                    }else{
			                        MainFrameUtil.alert({
			                            title:"å¤±è´¥æç¤º",
			                            body:data.msg,
			                        });
			                    }
			                },
			                error : function() {
			                    MainFrameUtil.alert({title:"ç½ç»å¤±è´¥æç¤º",body:"è¯·å·æ°é¡µé¢éè¯ï¼"});
			                }
						});
					}else{
						MainFrameUtil.alert({title:"æç¤º",body:"æ°æ®å¼å¸¸ï¼"});
						MainFrameUtil.closeDialog("ruleedit");
					}
				},
				upperremove:function(){
					var me = this;
					var row = $('#upperGrid').datagrid('getSelected');
					if(row){
						 MainFrameUtil.confirm({
							  title:"确认",
						        body:"确认删除选中行？",
						        onClose:function(type){
						            if(type=="ok"){//确定			
						            	if(row.id != null && row.id != ""){
							            	me.params.delIds.push(row.id);
						            	}
										$('#upperGrid').datagrid('deleteRow', $('#upperGrid').datagrid('getRowIndex',row));
										var ed = $('#upperGrid').datagrid('getEditor',{index:0,field:'minProp'});
										ed.target.numberbox({editable:true});
						            }
						        }
						    });  
					}else{
						MainFrameUtil.alert({title:"提示",body:"请至少选择一条数据！"});
					}
				},
				upperAppend:function(){
					$('#upperGrid').datagrid('appendRow',{});
					var length = $('#upperGrid').datagrid('getRows').length;
					var index = length-1;
					$('#upperGrid').datagrid('selectRow',index);
					$('#upperGrid').datagrid('beginEdit',index);
					if(length>1){
						var ed = $('#upperGrid').datagrid('getEditor',{index:index,field:'minProp'});
						ed.target.numberbox({editable:false});
						var uped = $('#upperGrid').datagrid('getEditor',{index:index-1,field:'maxProp'});
						$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
					}
				},
				lowerremove:function(){
					var row = $('#lowerGrid').datagrid('getSelected');
					if(row){
						 MainFrameUtil.confirm({
							 title:"确认",
						        body:"确认删除选中行？",
						        onClose:function(type){
						            if(type=="ok"){//确定		
						            	if(row.id != null && row.id != ""){
							            	me.params.delIds.push(row.id);
						            	}
										$('#lowerGrid').datagrid('deleteRow', $('#lowerGrid').datagrid('getRowIndex',row));
										var ed = $('#lowerGrid').datagrid('getEditor',{index:0,field:'minProp'});
										ed.target.numberbox({editable:true});
						            }
						        }
						    });  
					}else{
						MainFrameUtil.alert({title:"提示",body:"请至少选择一条数据！"});
					}
				},
				lowerAppend:function(){
					$('#lowerGrid').datagrid('appendRow',{});
					var length = $('#lowerGrid').datagrid('getRows').length;
					var index = length-1;
					$('#lowerGrid').datagrid('selectRow',index);
					$('#lowerGrid').datagrid('beginEdit',index);
					if(length>1){
						var ed = $('#lowerGrid').datagrid('getEditor',{index:index,field:'minProp'});
						ed.target.numberbox({editable:false});
						var uped = $('#lowerGrid').datagrid('getEditor',{index:index-1,field:'maxProp'});
						$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
					}
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
 								{field:'minProp',title:'偏差区间最小值（%）',width:'33%',align:'left'},
								{field:'maxProp',title:'偏差区间最大值（%）',width:'33%',align:'left'},
 								{field:'warnType',title:'预警级别',width:'33%',align:'left',
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
						        {field:'minProp',title:'偏差区间最小值（%）',width:'33%',align:'left'},
								{field:'maxProp',title:'偏差区间最大值（%）',width:'33%',align:'left'},
 								{field:'warnType',title:'预警级别',width:'33%',align:'left',
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
		  	    
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("ruledetail")}}];
		            return buttons;
		        }
			},
			watch:{
			} 
		});
	</script>
</body>
</html>