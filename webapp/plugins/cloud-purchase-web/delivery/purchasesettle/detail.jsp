<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>查看结算记录</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<style>
		#contentDiv .su_menu_topbar{
		display: none;
		}
	</style>
</head>
<body>
<div id="settlementList" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true":data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >	
	<mk-panel title="" line="true" header="false">
		<!-- 结算管理 -->
	    <mk-form-panel title="">
	    	<mk-form-row >
	            <!-- 方案名称 -->
	            <mk-form-col :label="formFields.schemeName.label"  label-width="140px" label-align="right" >
					 <su-textbox name="lctotalP" :data.sync="saveData.smPurchaseScheme.schemeName" disabled></su-textbox>
				</mk-form-col>
				 <!-- 用户数 -->
	             <mk-form-col :label="formFields.consNum.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.consNum" disabled></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
	            <mk-form-col :label="formFields.proxyPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.reportPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
	            <!-- 总购电量 -->
	            <mk-form-col :label="formFields.totalPurPq.label"  label-width="140px" label-align="right" >
					 <su-textbox name="lctotalP" :data.sync="formData.purchasePq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				 <!-- 实际用电量 -->
	             <mk-form-col :label="formFields.actualPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.actualTotalPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 未考核购电总收益 -->
	            <mk-form-col :label="formFields.uncheckedTotalPro.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.compDealProfit" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
	            <!-- 偏差电量比例 -->
	            <mk-form-col :label="formFields.devPqProp2.label"  label-width="140px" label-align="right" >
					 <su-textbox name="lctotalP" :data.sync="formData.devPqProp" disabled :addon="{'show':true,'rightcontent':'%'}" ></su-textbox>
				</mk-form-col>
				 <!-- 售电公司考核 -->
	             <mk-form-col :label="formFields.companyCheck.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.compDeviationProfit" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				<!-- 用户承担考核 -->
	            <mk-form-col :label="formFields.consCheckedPro.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.consCheckedProTotal" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
	            <!-- 结算电量 -->
	            <mk-form-col :label="formFields.settlementPq2.label"  label-width="140px" label-align="right" >
					 <su-textbox disabled="disabled" name="lctotalP" :data.sync="formData.delivryAllPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				 <!-- 双边电量 -->
	             <mk-form-col :label="formFields.lcPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.ppaPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 已分配双边电量 -->
	            <mk-form-col :label="formFields.devideLcPq.label" label-width="140px" label-align="right" >
	    			<su-textbox  disabled="disabled" name="bidavgPr" :data.sync="formData.ppaPqSum" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 竞价成交电量 -->
	             <mk-form-col :label="formFields.bidDealPq.label" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.dealPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 已分配竞价电量 -->
	       		<mk-form-col :label="formFields.lcDealPq.label" label-width="140px" label-align="right">
	             	<su-textbox  disabled="disabled" name="proxyPq" :data.sync="formData.dealPqAll" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<!-- 用户月度电量分配 -->
    <mk-panel title="用户月度电量分配" height="300" line="true">
        <div class="userPqGrid" style="height:200px">
            <div id="userPqGrid"> </div>
        </div>
    </mk-panel>
    <mk-panel title="月度结算明细" line="true">
		<!--     月度结算明细 -->
	    <mk-form-panel title=""> 
			 <mk-form-row >
	            <!-- 售电公司总利润formFields.compTotalProfit.label -->
	            <mk-form-col label="售电公司总利润"  label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.companyPro" :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				<!-- 用户总净收益formFields.consTotalPro.label -->
				<mk-form-col label="用户总净收益" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smPurchaseScheme.consProfit" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				<!-- 赔偿用户费用formFields.consCompensateAmt.label-->
				<mk-form-col label="赔偿用户费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smPurchaseScheme.consCompensate" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<!--     用户收益明细 -->
    <mk-panel title="用户收益明细 " height="300" line="true">
    	<!-- <div slot="buttons" class="pull-right" style="text-align:right">
            <su-button s-type="primary" @click="calculation" >收益计算</su-button>
        </div> -->
        <div class="row" style="height:230px">
            <div id="userIncomeGrid"> </div>
        </div>
    </mk-panel>
    
</su-form>	
</div>
<script>
var basePath="${Config.baseURL}";
var editIndex = undefined;
var settlementListVue=new Vue({
    el: '#settlementList',
    data: {
    	formData:{"ppaPqSum":null,"dealPqAll":null,"delivryAllPq":null, consCheckedProTotal:null,compDealProfit:null, compDeviationProfit:null},
    	formFields:{},
    	dataFlag:false,//数据存在标志
    	//saveFlag:false,//保存标志
    	dataMsg:[null,null],//提示信息
    	params:{
    		smCompanyProfit:{id:null,lcTotalPq:null,lcAvgPrc:null,proxyPq:null,bidTotalPq:null,bidAvgPrc:null,
    			purTotalPq:null,delTotalPq:null,devPqProp:null,devDam:null,companyPro:null,schemeId:null,ym:null,companyId:null,devPq:null,payConsMoney:null},
   			smConsumerProfitList:[]
    	},
    	saveData:{
    		smPurchaseScheme:{id:null, planId:null, schemeName:null, compProfit:null, consProfit:null, consCompensate:null},
    		smPurchaseSchemeDetailDetailList:[],
    		smCompanyProfit:{},
   			smConsumerProfitList:[]
    	}
    },
    ready:function(){
    	var me = this;
    	
    	me.formData = MainFrameUtil.getParams("sellSettlementDetail");
    	me.saveData.smPurchaseScheme.schemeName = me.formData.schemeName;
    	console.log($.toJSON(me.formData))
    	if(me.formData.actualTotalPq!=null&&me.formData.dealPq!=null&&me.formData.dealPq!=null&&me.formData.dealPq!=0){
			me.$set("formData.devPqProp",(((me.formData.actualTotalPq-me.formData.dealPq)/me.formData.dealPq)*100).toFixed(2));
		}
		$.ajax({
                url:basePath+"cloud-purchase-web/smCompanyProfitController/countCompanyProfit?planId="+ this.formData.id,
                type:"get",
                success : function(data) {
                	if(data&&data.data){
	                	me.formData.compDealProfit = data.data.compDealProfit;
	                	me.formData.compDeviationProfit = data.data.compDeviationProfit;
                	}
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                }
            });
    	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"sellSettlementDetail");
    	
    	//用户月度电量分配列表
       	$("#userPqGrid").datagrid({
       		url: basePath+"cloud-purchase-web/smPurchaseSchemeDetailController/getConsInfoByPlanIdOrSchemeId?schemeId=" + me.formData.schemeId,
       		method: 'post',
            loadMsg:"请稍后",
            width:'100%',    
            height:"100%",
            checkOnSelect : true,
            singleSelect:true,
            rownumbers:true,
            columns:[[
				{field:'consName',title:'用户名称',width:'15%',align:'center'},
				{field:'diviCode',title:'分成模式',width:'6%',align:'center',
					formatter:function(value,index,row){
	            		  if(value == "01"){
	            			  return "保底";
	            		  }else if(value == "03"){
	            			  return "分成";
	            		  }else if(value == "04"){
	            			  return "保底或分成";
	            		  }else if(value == "05"){
	            			  return "保底加分成";
	            		  }else{
	            			  return value;
	            		  }
	            	  } 
				},
				{field:'agrePrc',title:'保底协议价 </br>（元/兆瓦时）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'partyBLcProp',title:'用户双边</br>分成比例 （%）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'partyABidProp',title:'用户竞价</br>分成比例 （%）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				 {field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					}
				},
				{field:'totalPq',title:'分配总电量 </br>（兆瓦时）',width:'8%',align:'center'},
				{field:'lcPq',title:'分配双边电量 </br>（兆瓦时）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }},
				{field:'bidPq',title:'分配竞价电量 </br>（兆瓦时）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				{field:'actualPq',title:'实际用电量 </br>（兆瓦时）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				{field:'delivryPq',title:'结算电量</br>（兆瓦时）',width:'8%',align:'center'},
				{field:'devPqProp',title:'偏差电量比例 </br>（%）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value)*100;
						  }
					  }}
		    ]],
		    onLoadSuccess: function(data){
				me.endEditing();
			}
        });
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
       	
    	//用户收益列表
     	$("#userIncomeGrid").datagrid({
            striped : true,
            checkOnSelect : true,
            singleSelect:true,
            fit:true,
            fitColumns:true,
            nowrap:true,
            rownumbers:true,
            loadMsg:"请稍后",
            width:'100%',    
            height:"100%",
            columns:[
                     [
                       {field:'',title:'',width:'18%',align:'center',colspan:2},
                       {field:'consFee',title:'用户费用（元）',width:'18%',align:'center',colspan:3},
                       {field:'compFee',title:'售电公司费用（元）',width:'18%',align:'center',colspan:2}
                     ],
                     [
      				   {field:'consName',title:'用户名称',width:'15%',align:'center'},
      				   {field:'diviCode',title:'分成模式',width:'15%',align:'center',
      						formatter:function(value,index,row){
      	            		  if(value == "01"){
      	            			  return "保底";
      	            		  }else if(value == "03"){
      	            			  return "分成";
      	            		  }else if(value == "04"){
      	            			  return "保底或分成";
      	            		  }else if(value == "05"){
      	            			  return "保底加分成";
      	            		  }else{
      	            			  return value;
      	            		  }
      	            	  }},
      				   {field:'consTotalPro',title:'净收益',width:'14%',align:'center'},
      				   {field:'consUncheckedPro',title:'未考核收益',width:'14%',align:'center'},
      				   {field:'consCheckedPro',title:'承担偏差考核',width:'14%',align:'center'},
      				
      				   {field:'consCompensate',title:'赔偿用户费用',width:'14%',align:'center'},
      				   {field:'compUncheckedPro',title:'未考核收益',width:'14%',align:'center'}
				     ]]
       	});
     	$.ajax({
   			url: '${Config.baseURL}/smCompanyProfitController/getVoBySchemeId/'+ me.formData.schemeId,
            method:'GET',
            success:function(data){
                if(data !== null && data.flag){
                	$("#userIncomeGrid").datagrid("loadData",data.data.smConsumerProfitDetailList);
                	me.saveData.smCompanyProfit = data.data.smCompanyProfit;
                	
                	var rows = data.data.smConsumerProfitDetailList;
                	//用户承担考核总额
                	var consCheckedProTotal = 0;
                	//用户总净收益
                	var consTotalPro = 0;
                	//赔偿用户费用
                	var payConsMoney = 0;
                	for(var i=0; i < rows.length; i ++){
                		consCheckedProTotal += parseFloat(rows[i].consCheckedPro);
                		consTotalPro += parseFloat(rows[i].consTotalPro);
                		payConsMoney += parseFloat(rows[i].consCompensate== null? 0 : rows[i].consCompensate );
                	}
                	//售电公司总利润
                	me.saveData.smPurchaseScheme.compProfit = me.params.smCompanyProfit.companyPro;
                	me.formData.consCheckedProTotal = consCheckedProTotal;
                	me.saveData.smPurchaseScheme.consProfit = consTotalPro;
                	me.saveData.smPurchaseScheme.consCompensate = payConsMoney;
           	    }else{
           		   MainFrameUtil.alert({title:"提示",body:data.msg});
           	    }
            },
            error:function(){
           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
            }
		})
    },
    methods: {
    	//按钮组
        getButtons:function(){
            var buttons = [
                           {text:"关闭",handler:function(){MainFrameUtil.closeDialog("sellSettlementDetail")}}];
            return buttons;
        },
        //表格结束编辑
        endEditing:function(){
        	//计算总的长协电量和竞价电量
        	var allData=$('#userPqGrid').datagrid('getRows');
        	this.formData.ppaPqSum=0;
        	this.formData.dealPqAll=0;
        	this.formData.delivryAllPq=0;
        	for(var i=0;i<allData.length;i++){
        		if(allData[i].lcPq!=null&&allData[i].lcPq!=""){
        			this.formData.ppaPqSum+=parseFloat(allData[i].lcPq);
        		}
        		if(allData[i].bidPq!=null&&allData[i].bidPq!=""){
        			this.formData.dealPqAll+=parseFloat(allData[i].bidPq);
        		}
        		if(allData[i].delivryPq!=null&&allData[i].delivryPq!=""){
        			this.formData.delivryAllPq+=parseFloat(allData[i].delivryPq);
        		}
        	}
 		}
    }
});
</script>
</body>

</html>
