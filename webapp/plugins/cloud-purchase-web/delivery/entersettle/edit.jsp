<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算管理——结算单录入编辑</title>
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
		<mk-panel title="售电公司收益" line="true">
	    	<mk-form-panel label_width="180px" num="2">
	    		<mk-form-row >
	    			<!-- 购电年月 -->
		    		<mk-form-col :label="formFields.ym.label" required_icon="true">
		    			<su-datepicker format="YYYY-MM" :data.sync="params.phmEnterSettle.ym" disabled name="ym"></su-datepicker>
		    		</mk-form-col>
		    		<!-- 售电公司购电收益 -->
	    			<mk-form-col  :label="formFields.comProfit.label" required_icon="true">
		    			<su-textbox :data.sync="params.phmEnterSettle.comProfit" type="text" name="comProfit" :addon="{'show':true,'rightcontent':'元'}"></su-textbox>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 售电公司偏差费用 -->
		    		<mk-form-col  :label="formFields.compCheck.label">
		    			<su-textbox :data.sync="params.phmEnterSettle.comCheck" type="text" name="comCheck" :addon="{'show':true,'rightcontent':'元'}"></su-textbox>
		    		</mk-form-col>
	    			<!-- 用户偏差费用 -->
	    			<mk-form-col  :label="formFields.consCheck.label">
		    			<su-textbox :data.sync="consCheck" disabled type="text" name="consCheck" :addon="{'show':true,'rightcontent':'元'}"></su-textbox>
		    		</mk-form-col>
		    	</mk-form-row>
	    	</mk-form-panel>
	    </mk-panel>
	    <mk-panel title="售电公司购售电量" line="true">
	    	<mk-form-panel label_width="180px" num="2">
	    		<mk-form-row >
	    			<!-- 总购电量 -->
		    		<mk-form-col :label="formFields.purchasePq.label">
		    			<su-textbox :data.sync="purchasePq" disabled type="text" name="name" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		    		</mk-form-col>
		    		<!-- 用户委托电量 -->
	    			<mk-form-col  :label="formFields.reportPq.label">
		    			<su-textbox :data.sync="reportPq" disabled type="text" name="name" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 用户实际用电量 -->
		    		<mk-form-col :label="formFields.actPq.label">
		    			<su-textbox :data.sync="actPq" disabled type="text" name="name" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		    		</mk-form-col>
	    			<!-- 总偏差电量 -->
	    			<mk-form-col  :label="formFields.checkPq.label">
		    			<su-textbox :data.sync="checkPq" disabled type="text" name="name" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		    		</mk-form-col>
		    	</mk-form-row>
	    	</mk-form-panel>
		</mk-panel>
		<mk-panel title="用户结算明细" line="true" >
		    <div id="settleGrid" class="col-xs-12" style="height:500px"></div>
		</mk-panel>
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var ruleAddVue =new Vue({
			el: '#ruleAddVue',
			data: {	
				formFields:{},
				purchasePq:null,
				reportPq:null,
				actPq:null,
				checkPq:null,
				consCheck:null,
			    params:{
			    	phmEnterSettle:{id:null,ym:null,comProfit:null,comCheck:null},
			    	phmConsSettleRelaList:[]
			    },
				//非空验证规则
		        dataOption: {
		            rules: {	
		            	ym:{required: !null},
		            	comProfit:{required: !0,'max':99999999999999.99}
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.delivery.settleform",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"settle_edit");
				me.params.phmEnterSettle.id = MainFrameUtil.getParams("settle_edit").id;
				var ym = MainFrameUtil.getParams("settle_edit").ym.substr(0,4)+"-"+MainFrameUtil.getParams("settle_edit").ym.substr(4);
				me.params.phmEnterSettle.ym = ym;
				me.params.phmEnterSettle.comProfit = MainFrameUtil.getParams("settle_edit").compProfit;
				me.params.phmEnterSettle.comCheck = MainFrameUtil.getParams("settle_edit").compCheck;
				me.initGrid(me.params.phmEnterSettle.id);
			},
			methods: {
		 		initGrid:function(id){
		 			var me = this;
		 			ComponentUtil.buildGrid("purchase.delivery.enterdetail",$("#settleGrid"),{ 
						url: basePath + 'cloud-purchase-web/enterSettleController/getSettleDetailById',
					    width:"100%",
					    height:"270px",
					    method: 'post',
					    queryParams:me.params,
					    singleSelect:true,
					    rownumbers: true,
					    nowrap: false,
					    fitColumns:true,
					    rowStyler:function(idx,row){
					        return "height:35px;font-size:12px;";
					    },
					    loadFilter:function(data){
					    	console.log(data.data);
					    	if(data.flag){
					    		me.purchasePq = data.extMap["purchasePq"];
					    		return data.data;
					    	}else{
					    		MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
					    	}
					    },
					    columnsReplace:[
										{ field:'settlePrc',title:'结算电价<br/>（元/兆瓦时）', editor: {type:'numberbox',options:{precision:2}} },
										{ field:'consCheck',title:'偏差费用<br/>（元）', editor: {type:'numberbox',options:{precision:2}} },
										{ field:'prc',title:'结算电费<br/>（元）', editor: {type:'numberbox',options:{precision:2}} },
										{ field:'profit',title:'服务费<br/>（元）', editor: {type:'numberbox',options:{precision:2}} },
										{ field:'agent',title:'代理人', editor: {type:'textbox'} },
										{ field:'agentPrc',title:'代理费用<br/>（元）', editor: {type:'numberbox',options:{precision:2}} },
					    ],
					    onLoadSuccess: function(data){
					    	var reportPqTotal = 0;
					    	var actPqAll = 0 ;
					    	var checkPqAll = 0;
					    	var consCheckAll = 0;
					    	var rows =  $("#settleGrid").datagrid('getRows');
					    	for(var i = 0 ; i < rows.length ; i++){
					    		$("#settleGrid").datagrid('beginEdit', i);
					    		reportPqTotal += rows[i].reportPq;
					    		var checkPq = rows[i].actPq - rows[i].reportPq;
					    		$("#settleGrid").datagrid("getPanel").find(".datagrid-row [field='consCheckPq']").eq(i).children().text(parseFloat(checkPq).toFixed(3));
					    		actPqAll += rows[i].actPq;
					    		checkPqAll += rows[i].actPq - rows[i].reportPq;
					    		consCheckAll += rows[i].consCheck;
					    	}
					    	me.reportPq = parseFloat(reportPqTotal).toFixed(3);
					    	me.actPq = actPqAll;
					    	me.checkPq = parseFloat(checkPqAll).toFixed(3);
					    	me.consCheck = consCheckAll;
					    	$("#settleGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
					    		$(object).bind("blur",function(){
					    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
					    			var checkEditor = $("#settleGrid").datagrid("getEditor",{index:rowIndex,field:"consCheck"});
					    			var checkValue = checkEditor.actions.getValue(checkEditor.target);
					    			if(checkValue != null && checkValue != ''){
					    				var consCheckTotal = 0.00;
					    				for(var i = 0 ; i < rows.length ; i++){
											var consCheckEditor = $("#settleGrid").datagrid("getEditor",{index:i,field:"consCheck"});
											var check =  consCheckEditor.actions.getValue(consCheckEditor.target);
											if(check != null && check != ''){
												consCheckTotal = consCheckTotal + parseFloat(check);
											}
										}
					    				me.consCheck = parseFloat(consCheckTotal).toFixed(2);
					    			}
					    		})
					    	});
				        },
				    });
				},
				
		  	    save:function(){
		  	    	var me = this;
		  	    	me.$refs.form1.valid();
		  	    	if(!me.valid){
		  	    		MainFrameUtil.alert({title:"提示",body:"有必填项未填！"});
		  	    		return;
		  	    	}
		  	    	for (var i=0 ; i< $("#settleGrid").datagrid('getRows').length ; i++){
		  	    		$('#settleGrid').datagrid('endEdit',i);
		  	    	}
		  	    	var rows = $("#settleGrid").datagrid('getRows');
		  	    	for(var i=0 ; i<rows.length ; i++){
		  	    		delete rows[i]["consCheckPq"];
		  	    		delete rows[i]["reportPq"];
		  	    	}
		  	    	me.params.phmConsSettleRelaList = rows;
		  	    	$.ajax({
						url: basePath + 'cloud-purchase-web/enterSettleController',
						type: 'post',
						contentType : 'application/json;charset=UTF-8',
						data : $.toJSON(me.params),
						success: function(data) {
							if(data.flag){
								MainFrameUtil.closeDialog("settle_edit");
							}else{
								MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
							}
						},
						error: function() {
						}
					});
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
								MainFrameUtil.closeDialog("settle_edit");
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
		        	var buttons = [{text:"保存",type:"primary",handler:me.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("settle_edit")}}];
		            return buttons;
		        }
			}
		});
	</script>
</body>
</html>