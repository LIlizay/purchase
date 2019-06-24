<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购售电交易-用户预警预测新增</title>
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
	    	<mk-form-panel label-width="140px" num="2">
	    		<mk-form-row >
	    			<!-- 规则类型标识 -->
		    		<mk-form-col :label="formFields.ruleFlag.label" required_icon="true">
		    			<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smConsEarlyWarning.ruleFlag"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="ruleFlag"></su-select>
		    		</mk-form-col>
		    		<!-- 用户名称 -->
	    			<mk-form-col v-if="!consFlag" :label="formFields.consName.label" required_icon="true">
	    				<mk-trigger-text  :data.sync="consInfo.consName" editable="false" @mk-trigger="selectCons"></mk-trigger-text>
	    			</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 规则名称 -->
		    		<mk-form-col  :label="formFields.name.label" required_icon="true">
		    			<su-textbox :data.sync="params.smConsEarlyWarning.name" type="text" name="name"></su-textbox>
		    		</mk-form-col>
		    		<!-- 预警频率 -->
		    		<mk-form-col  :label="formFields.frequencyName.label">
		    			<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smConsEarlyWarning.frequency"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnFrequency" ></su-select>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row height="65px">
		    		<!-- 预警规则说明 -->
	                <mk-form-col  colspan="2" :label="formFields.ruleExplain.label" :class="{'display-none':!formFields.ruleExplain.formHidden}">
	                    <su-textbox type="textarea" rows="2" rols="2" placeholder="" :data.sync="params.smConsEarlyWarning.ruleExplain"></su-textbox>
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
				consFlag : false,
				formFields:{},
				fileName:"",
				editFlag : false,	//是否是编辑数据
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
		            	name:{required: !0},
			    		ruleFlag:{required: !0}
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.deviationcheck.smconsearlywarning",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"ruleadd");
				me.initUpper();
				me.initLower();
			},
			methods: {
				
				selectCons:function(){
		 			var me = this;
		 			MainFrameUtil.openDialog({
			  			id:'consDialog',
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
						iframe:true,
						modal:true,
						width:'50%',
						height:620,
						onClose:function(params){
							if(typeof(params[0])==="object"){
								me.editFlag = false;
								me.consInfo = params[0];
								me.params.smConsEarlyWarning.consId = params[0].id;
								me.getDataByConsId(params[0].id);
							}
						}
					});
		 		},
		 		
				getDataByConsId:function(consId){
					var me = this;
					if(consId == null || consId == ""){
						return;
					}
					console.log("consId: ", consId);
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/smConsEarlyWarningController/getDataByConsId",
						type : 'post',
						data:consId,
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data.flag){
								if(data.data != null){
									console.log("data:", data.data);
									me.editFlag = true;
									me.params = data.data;
			                    	me.initGrid();
								}
							}else{
								MainFrameUtil.alert({title:"提示",body:"查询用户数据失败，请刷新页面重试！"});
								MainFrameUtil.closeDialog("ruleadd");
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"查询用户数据失败，请刷新页面重试！"});
							MainFrameUtil.closeDialog("ruleadd");
						}
					});
				},
		 		
		 		initGrid:function(){
					var me = this;
					console.log("me.params.smConsWarningInfoList",me.params.smConsWarningInfoList);
					$('#upperGrid').datagrid('loadData', []);
					$('#lowerGrid').datagrid('loadData', []);
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
							for(var i = 0; i < upperList.length; i++){
								$('#upperGrid').datagrid('appendRow', upperList[i]);
								$('#upperGrid').datagrid('beginEdit',i);
// 								if(i>0){
// 									var ed = $('#upperGrid').datagrid('getEditor',{index:i,field:'minProp'});
// 									ed.target.numberbox({editable:false});
// 								}
							}
						}
						if(lowerList != null && lowerList.length > 0){							
							for(var j = 0; j<lowerList.length; j++){
								$('#lowerGrid').datagrid('appendRow', lowerList[j]);
								$('#lowerGrid').datagrid('beginEdit',j);
// 								if(j>0){
// 									var ed = $('#lowerGrid').datagrid('getEditor',{index:j,field:'minProp'});
// 									ed.target.numberbox({editable:false});
// 								}
							}
						}
						
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
// 										var ed = $('#upperGrid').datagrid('getEditor',{index:0,field:'minProp'});
// 										ed.target.numberbox({editable:true});
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
// 					if(length>1){
// 						var ed = $('#upperGrid').datagrid('getEditor',{index:index,field:'minProp'});
// 						ed.target.numberbox({editable:false});
// 						var uped = $('#upperGrid').datagrid('getEditor',{index:index-1,field:'maxProp'});
// 						$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
// 					}
				},
				lowerremove:function(){
					var me = this;
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
// 										var ed = $('#lowerGrid').datagrid('getEditor',{index:0,field:'minProp'});
// 										ed.target.numberbox({editable:true});
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
// 					if(length>1){
// 						var ed = $('#lowerGrid').datagrid('getEditor',{index:index,field:'minProp'});
// 						ed.target.numberbox({editable:false});
// 						var uped = $('#lowerGrid').datagrid('getEditor',{index:index-1,field:'maxProp'});
// 						$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
// 					}
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
 								{field:'minProp',title:'<span style="color:#F00">*</span> 偏差预警阈值（%）',width:'50%',align:'left',
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
								{field:'maxProp',hidden:true,title:'偏差区间最大值（%）',width:'33%',align:'left',
// 									editor:{
// 										id:"maxProp",
// 										type:'numberbox',
// 										options:{
// 											required:true,
// 											precision:2,
// 											min:0,
// 											onChange:function(newValue,oldValue){
// 												var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
// 												console.log("rowIndex:",rowIndex);
// 												var length = $('#upperGrid').datagrid('getRows').length;
// 												if(rowIndex < length-1){
// 													var ed = $('#upperGrid').datagrid('getEditor',{index:parseInt(rowIndex)+1,field:'minProp'});
// 													var uped = $('#upperGrid').datagrid('getEditor',{index:parseInt(rowIndex),field:'maxProp'});
// 													$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
// 												}
// 											}
// 										}
// 									}
								},
 								{field:'warnType',title:'预警级别',width:'49%',align:'left',
									editor:{
										id:"warnType",
										type:'combobox',  
						                options:{
						                	url:"${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass",
						                	method: "get",
						                	panelHeight: '75',
						                	valueField:'value',
						                	textField:'name',
// 											required:true,
						                	editable: false
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
						        {field:'minProp',title:'<span style="color:#F00">*</span> 偏差预警阈值（%）',width:'50%',align:'left',
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
								{field:'maxProp',hidden:true,title:'偏差区间最大值（%）',width:'33%',align:'left',
// 									editor:{
// 										id:"maxProp",
// 										type:'numberbox',
// 										options:{
// 											required:true,
// 											precision:2,
// 											min:0,
// 											onChange:function(newValue,oldValue){
// 												var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
// 												console.log("rowIndex:",rowIndex);
// 												var length = $('#lowerGrid').datagrid('getRows').length;
// 												if(rowIndex < length-1){
// 													var ed = $('#lowerGrid').datagrid('getEditor',{index:parseInt(rowIndex)+1,field:'minProp'});
// 													var uped = $('#lowerGrid').datagrid('getEditor',{index:parseInt(rowIndex),field:'maxProp'});
// 													$(ed.target).numberbox('setValue', $(uped.target).numberbox('getValue'));
// 												}
// 											}
// 										}
// 									}
								},
 								{field:'warnType',title:'预警级别',width:'49%',align:'left',
									editor:{
										id:"warnType",
										type:'combobox',  
						                options:{
						                	url:"${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_warnClass",
						                	method: "get",
						                	panelHeight: '75',
						                	valueField:'value',
						                	textField:'name',
											//required:true,
											editable: false
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
				
		  	    save:function(){
		  	    	var me = this;
		  	    	if(me.consFlag == false && (me.consInfo == null || me.consInfo == "")){
		  	    		MainFrameUtil.alert({title:"警告",body:"请选择用户！"});
		  	    		return;
		  	    	}
		  	    	me.$refs.form1.valid();
		        	if(me.valid===false){
		        		return;
		        	}
		        	
		        	var ulength = $('#upperGrid').datagrid('getRows').length;
		  	    	var llength = $('#lowerGrid').datagrid('getRows').length;
		  	    	
		  	    	if(ulength < 1 && llength < 1){
		  	    		MainFrameUtil.alert({title:"提示",body:"请添加偏差预警规则！"});
						return;
		  	    	}
		  	    	
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
		  	    	
		  	    	
// 		  	    	for(var a = 0; a<ulength; a++){
// 		  	    		$('#upperGrid').datagrid('endEdit',a);
// 		  	    	}
// 		  	    	var upperRows = $('#upperGrid').datagrid('getRows');
// 		  	    	var urows = [];
// 		  	    	for(var i in upperRows){
// 		  	    		urows.push(upperRows[i]);
// 		  	    	}
// 		  	    	for(var i in upperRows){
// 		  	    		if(parseFloat(upperRows[i].minProp) > parseFloat(upperRows[i].maxProp)){
// 		  	    			MainFrameUtil.alert({title:"警告",body:"偏差区间最小值不能大于偏差区间最大值！"});
// 		  	    			$('#upperGrid').datagrid('loadData', { total: 0, rows: [] });
// 		  	    			for(var a = 0; a<ulength; a++){
// 			  	    			$('#upperGrid').datagrid('appendRow', urows[a]);
// 				  	    		$('#upperGrid').datagrid('beginEdit',a);
// 				  	    		if(a>0){
// 									var ed = $('#upperGrid').datagrid('getEditor',{index:a,field:'minProp'});
// 									ed.target.numberbox({editable:false});
// 								}
// 				  	    	}
// 							return;
// 		  	    		}
// 		  	    	}
		  	    	
// 		  	    	for(var b = 0; b<llength; b++){
// 		  	    		$('#lowerGrid').datagrid('endEdit', b);
// 		  	    	}
// 		  	    	var lowerRows = $('#lowerGrid').datagrid('getRows');
// 		  	    	var lrows = [];
// 		  	    	for(var j in lowerRows){
// 		  	    		lrows.push(lowerRows[j]);
// 		  	    	}
// 		  	    	for(var j in lowerRows){
// 		  	    		if(parseFloat(lowerRows[j].minProp) > parseFloat(lowerRows[j].maxProp)){
// 		  	    			MainFrameUtil.alert({title:"警告",body:"偏差区间最小值不能大于偏差区间最大值！"});
// 				 			$('#lowerGrid').datagrid('loadData', { total: 0, rows: [] }); 
// 		  	    			for(var b = 0; b<llength; b++){
// 			  	    			$('#lowerGrid').datagrid('appendRow', lrows[b]);
// 				  	    		$('#lowerGrid').datagrid('beginEdit', b);
// 				  	    		if(b>0){
// 									var ed = $('#upperGrid').datagrid('getEditor',{index:b,field:'minProp'});
// 									ed.target.numberbox({editable:false});
// 								}
// 				  	    	}
// 							return;
// 		  	    		}
// 		  	    	}
		            
	            	if(me.editFlag == false){		            		
		            	me.saveData();
	            	}else{
	            		me.updateData();
	            	}
		  	    },
		  	    
		  	    updateData:function(){
		  	    	var me = this;
		  	    	me.params.addList = [];
		  	    	me.params.updateList = [];
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
		  	    		if(upperRows[i].id == null || upperRows[i].id == ""){		  	    			
		  	    			upperRows[i].warnPrompt = "01";
		  	    			me.params.addList.push(upperRows[i]);
		  	    		}else{
		  	    			me.params.updateList.push(upperRows[i]);
		  	    		}
		  	    	}
		  	    	for(var j in lowerRows){
		  	    		if(lowerRows[j].id == null || lowerRows[j].id == ""){
			  	    		lowerRows[j].warnPrompt = "02";
			  	    		me.params.addList.push(lowerRows[j]);
		  	    		}else{
		  	    			me.params.updateList.push(lowerRows[j]);
		  	    		}
		  	    	}
		  	    	
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/smConsEarlyWarningController/updateList",
						type : 'post',
						data:$.toJSON(me.params),
						contentType : 'application/json;charset=UTF-8',
						success : function(result) {
							if(result.flag){
								MainFrameUtil.alert({title:"提示",body:"更新成功！"});
								MainFrameUtil.closeDialog("ruleadd");
							}else{
								MainFrameUtil.alert({title:"提示",body:"更新失败！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"更新失败！"});
						}
					});
		  	    },
		  	    
		  	    saveData:function(){
		  	    	var me = this;
// 		  	    	me.params.smConsEarlyWarning.consId = me.consInfo.id;
// 		  	    	me.params.smConsEarlyWarning.ruleId = me.ruleName;
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
		  	    	me.params.smConsWarningInfoList = upperRows.concat(lowerRows);
		  	    	
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/smConsEarlyWarningController/saveList",
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
				"params.smConsEarlyWarning.ruleFlag" : function(value){
					var me = this;
					if(value == "1"){
						me.consFlag = true;
						me.params.smConsEarlyWarning.consId = null;
						me.consInfo = null;
						
						$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/smConsEarlyWarningController/getDefaultRule",
							type : 'post',
							contentType : 'application/json;charset=UTF-8',
							success : function(data) {
								if(data.flag){
									if(data.data != null){
										me.editFlag = true;
										me.params = data.data;
				                    	me.initGrid();
									}
								}else{
									MainFrameUtil.alert({title:"提示",body:"查询默认规则失败，请刷新页面重试！"});
									MainFrameUtil.closeDialog("ruleadd");
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"查询默认规则失败，请刷新页面重试！"});
								MainFrameUtil.closeDialog("ruleadd");
							}
						});
					}else{
						me.consFlag = false;
					}
				}
			} 
		});
	</script>
</body>
</html>