<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>偏差考核预警-偏差预警规则新增</title>
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
		<mk-panel title="偏差预警规则新增" line="true" height="225px">
	    	<mk-form-panel label-width="140px" num="3">
	    		<mk-form-row >
	    			<!-- 规则名称 -->
		    		<mk-form-col  :label="formFields.name.label" required_icon="true">
		    			<su-textbox :data.sync="params.smDeviationEarlyWarning.name" type="text" name="name"></su-textbox>
		    		</mk-form-col>
		    		<!-- 规则状态 -->
		    		<mk-form-col  :label="formFields.statusName.label" required_icon="true">
		    			<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationEarlyWarning.status"
							url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_contractVersStatus&valueIn='02','03'" name="status"></su-select>
		    		</mk-form-col>
		    		<!-- 预警频率 -->
		    		<mk-form-col  :label="formFields.frequency.label">
		    			<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smDeviationEarlyWarning.frequency"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnFrequency" ></su-select>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 参考文件 -->
					<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" colspan="2">
						<mk-file-upload v-ref:uploadfile required="false" @upload-error="uploadError" @upload-success="uploadSuccess"
							url="${Config.getConfig('file.service.url')}"></mk-file-upload>
					</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row height="65px">
		    		<!-- 预警规则说明 -->
	                <mk-form-col  colspan="3" :label="formFields.ruleExplain.label" :class="{'display-none':!formFields.ruleExplain.formHidden}">
	                    <su-textbox type="textarea" rows="2" rols="2" placeholder="" :data.sync="params.smDeviationEarlyWarning.ruleExplain"></su-textbox>
	                </mk-form-col>
		    	</mk-form-row>
	    	</mk-form-panel>
	    </mk-panel>
	    <mk-panel title="正偏差预警规则" line="true">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="upperAppend">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="upperremove">删除</su-button>
		    </div>	    
		    <div id="upperGrid" class="col-xs-12" style="height:100px"></div>
		</mk-panel>
		<mk-panel title="负偏差预警规则" line="true" >
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="lowerAppend">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="lowerremove">删除</su-button>
		    </div>	    
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
				formFields:{},
			    params:{
			    	smDeviationEarlyWarning:{
			    		name:null,
			    		status:null, //状态
			    		frequency:null, //频率
			    		fileId:null, //参考文件
			    		ruleExplain:null //规则说明
			    	},
			    	smWarningInfoList:[]
			    },
			   
				//非空验证规则
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
				MainFrameUtil.setDialogButtons(me.getButtons(),"ruleadd");
				me.initUpper();
				me.initLower();
			},
			methods: {
				
				
				upperremove:function(){
					var row = $('#upperGrid').datagrid('getSelected');
					if(row){
						 MainFrameUtil.confirm({
						        title:"确认",
						        body:"确认删除选中行？",
						        onClose:function(type){
						            if(type=="ok"){//确定				            		
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
 								{field:'minProp',title:'偏差区间最小值（%）',width:'33%',align:'left',
									editor:{
										id:"minProp",
										type:'numberbox',
										options:{
											required:true,
											precision:2,
											min:0
										}
									}
								},
								{field:'maxProp',title:'偏差区间最大值（%）',width:'33%',align:'left',
									editor:{
										id:"maxProp",
										type:'numberbox',
										options:{
											required:true,
											precision:2,
											min:0,
											onChange:function(newValue,oldValue){
												var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
												console.log("rowIndex:",rowIndex);
												var length = $('#upperGrid').datagrid('getRows').length;
												if(rowIndex < length-1){
													var ed = $('#upperGrid').datagrid('getEditor',{index:parseInt(rowIndex)+1,field:'minProp'});
													var uped = $('#upperGrid').datagrid('getEditor',{index:parseInt(rowIndex),field:'maxProp'});
													$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
												}
											}
										}
									}
								},
 								{field:'warnType',title:'预警级别',width:'33%',align:'left',
									editor:{
										id:"warnType",
										type:'combobox',  
						                options:{
						                	url:"${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass",
						                	method: "get",
						                	panelHeight: '75',
						                	valueField:'value',
						                	textField:'name',
											required:true,
											editable:false
						                }
									},
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
						        {field:'minProp',title:'偏差区间最小值（%）',width:'33%',align:'left',
									editor:{
										id:"minProp",
										type:'numberbox',
										options:{
											required:true,
											precision:2,
											min:0
										}
									}
								},
								{field:'maxProp',title:'偏差区间最大值（%）',width:'33%',align:'left',
									editor:{
										id:"maxProp",
										type:'numberbox',
										options:{
											required:true,
											precision:2,
											min:0,
											onChange:function(newValue,oldValue){
												var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
												console.log("rowIndex:",rowIndex);
												var length = $('#lowerGrid').datagrid('getRows').length;
												if(rowIndex < length-1){
													var ed = $('#lowerGrid').datagrid('getEditor',{index:parseInt(rowIndex)+1,field:'minProp'});
													var uped = $('#lowerGrid').datagrid('getEditor',{index:parseInt(rowIndex),field:'maxProp'});
													$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
												}
											}
										}
									}
								},
 								{field:'warnType',title:'预警级别',width:'33%',align:'left',
									editor:{
										id:"warnType",
										type:'combobox',  
						                options:{
						                	url:"${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass",
						                	method: "get",
						                	panelHeight: '75',
						                	valueField:'value',
						                	textField:'name',
											required:true,
											editable:false
						                }
									},
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
				//附件上传成功回调
		 	    uploadSuccess:function(data){
		 	    	var me = this;
		 	    	console.log(data);
				    if(data){
		                var fileId = data[0].id;    //获取文件的id
		                var fileNameResult=data[0].fileName+data[0].extension;//获取文件名
		                me.params.smDeviationEarlyWarning.fileId = "{fileId:'"+fileId+"',fileName:'"+fileNameResult+"'}";
		            }
		                me.saveData();
		  	    },
		  		//附件上传失败回调
		  	    uploadError:function(data){
		  	    	$.messager.progress('close');   				 // 隐藏进度条
		  		    MainFrameUtil.alert({title:"警告",body:'上传失败'});//上传失败回调  
		  	    },
		  	    
		  	    save:function(){
		  	    	var me = this;
		  	    	me.$refs.form1.valid();
		        	if(me.valid===false){
		        		return;
		        	}
		        	var ulength = $('#upperGrid').datagrid('getRows').length;
		  	    	var llength = $('#lowerGrid').datagrid('getRows').length;
		  	    	
		  	    	for(var a = 0; a<ulength; a++){
		  	    		if(!$('#upperGrid').datagrid('validateRow', a)){
		  	    			MainFrameUtil.alert({title:"警告",body:"还有未完成编辑的正偏差规则，请完成编辑或删除！"});
							return;
		  	    		}
		  	    	}
		  	    	for(var b = 0; b<llength; b++){
		  	    		if(!$('#lowerGrid').datagrid('validateRow', b)){
		  	    			MainFrameUtil.alert({title:"警告",body:"还有未完成编辑的负偏差规则，请完成编辑或删除！"});
							return;
		  	    		}
		  	    	}
		  	    	for(var a = 0; a<ulength; a++){
		  	    		$('#upperGrid').datagrid('endEdit',a);
		  	    	}
		  	    	var upperRows = $('#upperGrid').datagrid('getRows');
		  	    	var urows = [];
		  	    	for(var i in upperRows){
		  	    		urows.push(upperRows[i]);
		  	    	}
		  	    	console.log("upperRows",upperRows);
		  	    	for(var i in upperRows){
		  	    		if(parseFloat(upperRows[i].minProp) > parseFloat(upperRows[i].maxProp)){
		  	    			MainFrameUtil.alert({title:"警告",body:"偏差区间最小值不能大于偏差区间最大值！"});
		  	    			$('#upperGrid').datagrid('loadData', { total: 0, rows: [] });
		  	    			for(var a = 0; a<ulength; a++){
			  	    			$('#upperGrid').datagrid('appendRow', urows[a]);
				  	    		$('#upperGrid').datagrid('beginEdit',a);
				  	    		if(a>0){
									var ed = $('#upperGrid').datagrid('getEditor',{index:a,field:'minProp'});
									ed.target.numberbox({editable:false});
								}
				  	    	}
							return;
		  	    		}
		  	    	}
		  	    	
		  	    	for(var b = 0; b<llength; b++){
		  	    		$('#lowerGrid').datagrid('endEdit', b);
		  	    	}
		  	    	var lowerRows = $('#lowerGrid').datagrid('getRows');
		  	    	var lrows = [];
		  	    	for(var j in lowerRows){
		  	    		lrows.push(lowerRows[j]);
		  	    	}
		  	    	for(var j in lowerRows){
		  	    		if(parseFloat(lowerRows[j].minProp) > parseFloat(lowerRows[j].maxProp)){
		  	    			MainFrameUtil.alert({title:"警告",body:"偏差区间最小值不能大于偏差区间最大值！"});
				 			$('#lowerGrid').datagrid('loadData', { total: 0, rows: [] }); 
		  	    			for(var b = 0; b<llength; b++){
			  	    			$('#lowerGrid').datagrid('appendRow', lrows[b]);
				  	    		$('#lowerGrid').datagrid('beginEdit', b);
				  	    		if(b>0){
									var ed = $('#upperGrid').datagrid('getEditor',{index:b,field:'minProp'});
									ed.target.numberbox({editable:false});
								}
				  	    	}
							return;
		  	    		}
		  	    	}
		  	    	
		  	    	if(me.statusFlag == true){
		  	    		MainFrameUtil.alert({title:"警告",body:"生效状态验证不通过，请选择失效状态！"});
		  	    		return;
		  	    	}
		            //验证是否选择文件
		            if(me.$refs.uploadfile.valid()){
		                me.$refs.uploadfile.validAndUpload();
		            }else{
		            	me.saveData();
		            }
		            
		  	    },
		  	    
		  	    saveData:function(){
		  	    	var me = this;
		  	    	var ulength = $('#upperGrid').datagrid('getRows').length;
		  	    	var llength = $('#lowerGrid').datagrid('getRows').length;
		  	    	for(var a = 0; a<ulength; a++){
		  	    		$('#upperGrid').datagrid('endEdit',a);
		  	    	}
		  	    	for(var b = 0; b<llength; b++){
		  	    		$('#lowerGrid').datagrid('endEdit', b);
		  	    	}
		  	    	var upperRows = $('#upperGrid').datagrid('getRows');
		  	    	var lowerRows = $('#lowerGrid').datagrid('getRows');
		  	    	for(var i in upperRows){
		  	    		upperRows[i].warnPrompt = "01";
		  	    	}
		  	    	for(var j in lowerRows){
		  	    		lowerRows[j].warnPrompt = "02";
		  	    	}
		  	    	me.params.smWarningInfoList = upperRows.concat(lowerRows);
		  	    	
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/smDeviationEarlyWarningController/saveList",
						type : 'post',
						data:$.toJSON(me.params),
						contentType : 'application/json;charset=UTF-8',
						success : function(result) {
							if(result.flag){
								MainFrameUtil.alert({title:"提示",body:"保存成功！"});
								MainFrameUtil.closeDialog("ruleadd");
							}else{
								MainFrameUtil.alert({title:"提示",body:"保存失败！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"保存失败！"});
						}
					});
		  	    },
				
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"保存",type:"primary",handler:me.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("ruleadd")}}];
		            return buttons;
		        }
			},
			watch:{
				"params.smDeviationEarlyWarning.status":function(){
					var me = this;
					if(me.params.smDeviationEarlyWarning.status == "02"){
						$.ajax({
							url:"${Config.baseURL}cloud-purchase-web/smDeviationEarlyWarningController/status",
							type:"get",
							success:function(data){
								if(data > 0){
									MainFrameUtil.alert({title:"警告",body:"已存在生效状态的规则，请选择失效状态！"});
									me.statusFlag = true;
								}else if(data == -1){
									MainFrameUtil.alert({title:"警告",body:"生效状态验证失败，请选择失效状态！"});
									me.statusFlag = true;									
								}
							}
						});
					}else{
						me.statusFlag = false;
					}
					
				}
			} 
		});
	</script>
</body>
</html>