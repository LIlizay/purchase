<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>结算管理-月度收益结算方案详情</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="settlementList" v-cloak>
	<mk-panel title="" line="true" header="false">
		<!-- 结算管理 -->
	    <mk-form-panel title="">
	    	<mk-form-row >
		    	<!-- 交易年月 -->
				<mk-form-col label='交易年月' label-width="140px" label-align="right" required_icon="true">
					<su-datepicker format="YYYY-MM" :data.sync="ym"  disabled="disabled"></su-datepicker>
				</mk-form-col>
				 <!-- 用户数 -->
	             <mk-form-col :label="formFields.consNum.label" label-width="140px" label-align="right" >
	    			<su-textbox name="" :data.sync="formData.consNum" disabled></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
	            <mk-form-col :label="formFields.proxyPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.totalProxyPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 实际用电量（结算电量） -->
	             <mk-form-col label="结算电量" label-width="140px" label-align="right" >
	    			<su-textbox name="" :data.sync="formData.actualTotalPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
	            <!-- 总购电量 -->
	            <mk-form-col :label="formFields.totalPurPq.label"  label-width="140px" label-align="right" >
					 <su-textbox name="lctotalP" :data.sync="formData.totalPurchasePq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 偏差电量比例 -->
	            <mk-form-col :label="formFields.devPqProp2.label"  label-width="140px" label-align="right" >
					 <su-textbox name="lctotalP" :data.sync="formData.devPqProp" disabled :addon="{'show':true,'rightcontent':'%'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			
			
			<mk-form-row >
	           	<!-- 批发交易电费 -->
	            <mk-form-col label="批发交易电费" label-width="140px" label-align="right" >
	    			<su-textbox  disabled="disabled" name="" :data.sync="formData.costAmt" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				<!-- 批发交易电价 -->
	            <mk-form-col label="批发交易电价" label-width="140px" label-align="right" >
	    			<su-textbox  disabled="disabled" name="" :data.sync="formData.costPrc" disabled :addon="{'show':true,'rightcontent':'元/兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 售电公司偏差费用 -->
				<mk-form-col label="售电公司偏差费用" label-width="140px" label-align="right">
					<su-textbox :data.sync="formData.devDam" :addon="{'show':true,'rightcontent':' 元'}" disabled ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
	           	<!-- 零售交易电费 -->
	            <mk-form-col label="零售交易电费" label-width="140px" label-align="right" >
	    			<su-textbox name="" :data.sync="formData.incomeTotalAmt" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 零售交易平均电价 -->
	            <mk-form-col label="零售交易平均电价" label-width="140px" label-align="right" >
	    			<su-textbox name="" :data.sync="formData.incomePrc" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 用户偏差考核费用 -->
				<mk-form-col label="用户偏差考核费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="formData.consCheckedProTotal" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
	            <!-- 售电公司总利润formFields.compTotalProfit.label -->
	            <mk-form-col label="售电公司总利润"  label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="formData.companyPro" :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<!-- 批发市场购电支出明细-->
    <mk-panel title="批发市场购电支出明细" height="300" line="true">
        <div class="row" style="height:100%">
            <div id="costGrid"> </div>
        </div>
    </mk-panel>
	<!-- 零售市场售电收入明细 -->
    <mk-panel title="零售市场售电收入明细" height="500" line="true">
        <div class="userPqGrid" style="height:100%">
            <div id="userPqGrid"> </div>
        </div>
    </mk-panel>
</div>
<script>
var basePath="${Config.baseURL}";
var settlementListVue=new Vue({
    el: '#settlementList',
    data: {
    	formFields:{},
    	//ym: yyyy-MM格式
    	ym: null,
    	formData:{
    		id:null, ym: null,		//结算id及年月
    		consNum: null, totalProxyPq: null, actualTotalPq: null, totalPurchasePq: null, devPqProp: null, 
    		costAmt: null,costPrc: null,
    		incomeTotalAmt: null, incomePrc: null,
    		
    		companyProfitId:null,		//售电公司收益id
    		devDam:null,				//偏差违约金(售电公司偏差考核)
    		companyPro:null,			//售电公司总利润 
    		consCheckedProTotal: null,	//用户总偏差考核费用
    		
    		costDetailList: []			//批发交易结算明细 list
    	}	
    },
    ready:function(){
    	var that = this;
     	that.formData.id = MainFrameUtil.getParams("schemeListDetailDialog").id;
     	var ym = MainFrameUtil.getParams("schemeListDetailDialog").ym;
     	that.ym = ym.substring(0,4)+"-"+ym.substring(4);
     	
     	this.initDatagrid();
    	//初始化form表单数据，并重新加载“零售市场售电收入明细”表格
		this.initFormData();
     	
      	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"schemeListDetailDialog");
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
    },
    methods: {
    	//初始化form表单数据，并重新加载方案表格
    	initFormData: function(){
    		var that = this;
    		//通过年月获取结算数据
        	$.ajax({
	       		url: '${Config.baseURL}/cloud-purchase-web/settlementControllerFj/getFormAndCostDataByIdOrYm?settleId=' + that.formData.id,
	            method:'get',
	            async: false,
	            success:function(data){
	               if(data.flag ){
            	   		that.formData = data.data;
	                	$("#costGrid").datagrid("loadData",data.data.costDetailList);
	                	
            	   		//计算零售交易平均电价（零售交易平均电价=（零售交易电费+用户偏差费用）/结算电量，如果偏差费用为空取0）
            			that.formData.incomePrc = 0;
                    	if(that.formData.actualTotalPq != null && that.formData.actualTotalPq != 0 && that.formData.actualTotalPq != ""){
                    		that.formData.incomePrc = ( (
                    										parseFloat(that.formData.incomeTotalAmt) + parseFloat((that.formData.consCheckedProTotal == "" || that.formData.consCheckedProTotal == null) ? 0 : that.formData.consCheckedProTotal)
                    									)
                    									/ parseFloat(that.formData.actualTotalPq)).toFixed(2);
                    	}
                    	
            			//计算批发交易平均电价（（批发交易电费+售电公司偏差费用）/结算电量，如果偏差费用为空取0）
                    	that.formData.costPrc = 0;
                    	if(that.formData.actualTotalPq != null && that.formData.actualTotalPq != 0 && that.formData.actualTotalPq != ""){
                    		that.formData.costPrc = ((parseFloat(that.formData.costAmt) + parseFloat((that.formData.devDam == "" || that.formData.devDam == null) ? 0 : that.formData.devDam)) 
                    					/ parseFloat(that.formData.actualTotalPq)).toFixed(2);
                    	}
                 	}else{
                         MainFrameUtil.alert({ title:"失败提示：", body:data.msg});
                    }
	            },
	            error:function(){
	           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
	            }
	   		});
    	},
    	initDatagrid: function(){
    		var that = this;
    		//用户月度电量分配列表
           	$("#userPqGrid").datagrid({
           		url: basePath+"cloud-purchase-web/settlementControllerFj/getRetailDetailByIdOrYm?settleId=" + that.formData.id,
           		method: 'get',
                loadMsg:"请稍后",
                width:'100%',
                height:"100%",
                checkOnSelect : true,
                singleSelect:true,
                rownumbers:true,
                scrollbarSize: 10,
                fit:true,
                fitColumns:true,
    		    frozenColumns:[[
    				{field:'consName',title:'用户名称',width:100,align:'center'},
    				{field:'voltCodeName',title:'电压等级',width:100,align:'center'},
      			]],
                columns:[[
                	{field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'deliveryPq',title:'结算电量 </br>（兆瓦时）',width:100,align:'center'},		//取“实际用电量”actualPq
    				
    				{field:'deliveryPrc',title:'交易电价</br>（元/兆瓦时）',width:100,align:'center'},	//结算电价（合同加权平均价）
    				{field:'deliveryCost',title:'交易电费</br>(元)',width:100,align:'center'},		//结算电费	s_m_consumer_profit表的字段
    				
    				{field:'serviceAmt',title:'服务费</br>（元）',width:100,align:'center'},
    				{field:'consCheckedPro',title:'用户偏差考核</br>费用（元）',width:100,align:'center'},		//s_m_consumer_profit表的字段
    				{field:'agentProp',title:'代理比例（%）',width:100,align:'center'},		//s_m_consumer_profit表的字段
    				{field:'agentAmt',title:'代理费用</br>（元）',width:100,align:'center'},		//s_m_consumer_profit表的字段
    				
    				{field:'totalPq',title:'分配总电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'lcPq',title:'分配双边电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'bidPq',title:'分配竞价电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'listedPq',title:'分配挂牌电量 </br>（兆瓦时）',width:100,align:'center'}
    		    ]]
            });

        	//批发市场购电支出明细 列表
         	$("#costGrid").datagrid({
                loadMsg:"请稍后",
                striped : true,
                checkOnSelect : true,
                singleSelect:true,
                fit:true,
                fitColumns:true,
                nowrap:true,
                rownumbers:true,
                scrollbarSize: 10,
                width:'100%',    
                height:"100%",
                columns:[[
                	{field:'agreName',title:'合同名称',width:'30%',align:'center'},
   				    {field:'monthPq',title:'合同分月电量</br>(兆瓦时)',width:'20%',align:'center'},
   				    {field:'monthPrc',title:'合同电价</br>(元/兆瓦时)',width:'20%',align:'center'},
   				    {field:'deliveryPq',title:'结算电量</br>(兆瓦时)',width:'15%',align:'center'},
   				    {field:'deliveryAmt',title:'批发交易电费</br>(元)',width:'15%',align:'center'}
		     	]],
           	});
    	},
    	//按钮组
        getButtons: function(){
            var buttons = [{text:"取消",type:"default",handler:function(){MainFrameUtil.closeDialog("schemeListDetailDialog")}}];
            return buttons;
        }
    }
});
</script>
</body>
</html>
