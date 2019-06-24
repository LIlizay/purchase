<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算管理——结算单录入查看</title>
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
		    			<su-textbox :data.sync="params.phmEnterSettle.comProfit" disabled type="text" name="comProfit" :addon="{'show':true,'rightcontent':'元'}"></su-textbox>
		    		</mk-form-col>
		    	</mk-form-row>
		    	<mk-form-row >
		    		<!-- 售电公司偏差费用 -->
		    		<mk-form-col  :label="formFields.compCheck.label">
		    			<su-textbox :data.sync="params.phmEnterSettle.comCheck" disabled type="text" name="comCheck" :addon="{'show':true,'rightcontent':'元'}"></su-textbox>
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
				MainFrameUtil.setDialogButtons(me.getButtons(),"settle_detail");
				me.params.phmEnterSettle.id = MainFrameUtil.getParams("settle_detail").id;
				var ym = MainFrameUtil.getParams("settle_detail").ym.substr(0,4)+"-"+MainFrameUtil.getParams("settle_detail").ym.substr(4);
				me.params.phmEnterSettle.ym = ym;
				me.params.phmEnterSettle.comProfit = MainFrameUtil.getParams("settle_detail").compProfit;
				me.params.phmEnterSettle.comCheck = MainFrameUtil.getParams("settle_detail").compCheck;
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
					    onLoadSuccess: function(data){
					    	var reportPqTotal = 0;
					    	var actPqAll = 0 ;
					    	var checkPqAll = 0;
					    	var consCheckAll = 0;
					    	var rows =  $("#settleGrid").datagrid('getRows');
					    	for(var i = 0 ; i < rows.length ; i++){
					    		$("#settleGrid").datagrid('beginEdit', i);
					    		reportPqTotal += rows[i].reportPq;
					    		if(rows[i].actPq != null && rows[i].actPq != ''){
						    		var checkPq = rows[i].actPq - rows[i].reportPq;
						    		$("#settleGrid").datagrid("getPanel").find(".datagrid-row [field='consCheckPq']").eq(i).children().text(parseFloat(checkPq).toFixed(3));
						    		checkPqAll += rows[i].actPq - rows[i].reportPq;
					    		}
					    		actPqAll += rows[i].actPq;
					    		consCheckAll += rows[i].consCheck;
					    	}
					    	me.reportPq = parseFloat(reportPqTotal).toFixed(3);
					    	me.actPq = actPqAll;
					    	me.checkPq = parseFloat(checkPqAll).toFixed(3);
					    	me.consCheck = consCheckAll;
				        }
				    });
				},
				
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("settle_detail")}}];
		            return buttons;
		        }
			}
		});
	</script>
</body>
</html>