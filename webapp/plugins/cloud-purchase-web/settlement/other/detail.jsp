<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>结算管理-月度收益结算方案详情</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="settlementList" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true":data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >	
	<mk-panel title="" line="true" header="false">
		<!-- 结算管理 -->
	    <mk-form-panel title="">
	    	<mk-form-row >
		    	<!-- 交易年月 -->
				<mk-form-col label='交易年月' label-width="140px" label-align="right" required_icon="true">
					<su-datepicker format="YYYY-MM" :data.sync="ym" disabled = "true"></su-datepicker>
				</mk-form-col>
				 <!-- 用户数 -->
	             <mk-form-col :label="formFields.consNum.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.consNum" disabled></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
	            <mk-form-col :label="formFields.proxyPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.totalProxyPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 实际用电量 -->
	             <mk-form-col :label="formFields.actualPq2.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.actualTotalPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
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
	           	<!-- 双边电量 -->
	            <mk-form-col label="双边电量" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.totalLcPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 已分配双边电量 -->
	            <mk-form-col :label="formFields.devideLcPq.label" label-width="140px" label-align="right" >
	    			<su-textbox  disabled="disabled" name="bidavgPr" :data.sync="formData.lcPqSum" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 双边加权平均价 -->
	             <mk-form-col label="双边加权平均价" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.lcPrcAvg" disabled :addon="{'show':true,'rightcontent':'元/兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 竞价成交电量 -->
	             <mk-form-col label="竞价电量" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.totalBidPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 已分配竞价电量 -->
	       		<mk-form-col :label="formFields.lcDealPq.label" label-width="140px" label-align="right">
	             	<su-textbox  disabled="disabled" name="proxyPq" :data.sync="formData.bidPqSum" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 竞价加权平均价 -->
	             <mk-form-col label="竞价加权平均价" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.bidPrcAvg" disabled :addon="{'show':true,'rightcontent':'元/兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 挂牌成交电量 -->
	            <mk-form-col label="挂牌电量" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.totalListedPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 已分配挂牌电量 -->
	       		<mk-form-col label="已分配挂牌电量" label-width="140px" label-align="right">
	             	<su-textbox  disabled="disabled" name="proxyPq" :data.sync="formData.listedPqSum" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 挂牌加权平均价 -->
	            <mk-form-col label="挂牌加权平均价" label-width="140px" label-align="right" >
	    			<su-textbox name="purTotal" :data.sync="formData.listedPrcAvg" disabled :addon="{'show':true,'rightcontent':'元/兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			
			<mk-form-row >
	            <!-- 售电公司总利润formFields.compTotalProfit.label -->
	            <mk-form-col label="售电公司总利润"  label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.companyPro" :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				
				<!-- 售电公司偏差费用 -->
				<mk-form-col label="售电公司偏差费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.devDam" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				
				<!-- 用户偏差考核费用 -->
				<mk-form-col label="用户偏差考核费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.consCheckedProTotal" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<!-- 零售市场售电收入明细 -->
    <mk-panel title="零售市场售电收入明细" height="300" line="true">
        <div class="userPqGrid" style="height:100%">
            <div id="userPqGrid"> </div>
        </div>
    </mk-panel>
</su-form>	
</div>
<script>
var basePath="${Config.baseURL}";
var editIndex = undefined;
// var editIndex2 = undefined;
var settlementListVue=new Vue({
    el: '#settlementList',
    data: {
    	//ym: yyyy-MM格式
    	ym:null,
    	formData:{
    		id:null,"ym":null,		//结算id及年月
    		consNum: null, totalProxyPq: null, actualTotalPq: null, totalPurchasePq: null, devPqProp: null, 
    		totalLcPq: null, lcPqSum: null, lcPrcAvg: null, 
    		totalBidPq: null, bidPqSum: null, bidPrcAvg: null,
    		totalListedPq: null, listedPqSum: null, listedPrcAvg: null,
    		
    		//"deliveryAllPq":null, 
    		consCheckedProTotal: null		//用户总偏差考核费用
    	},		
    	formFields:{},
    	saveData:{
    		settleId:null,
    		ym: null,
    		smPurchaseScheme:{id:null, settleId:null, schemeName:null, compProfit:null, consProfit:null, consCompensate:null},
    		smPurchaseSchemeDetailDetailList:[],
    		smCompanyProfit:{id:null,devDam:null,companyPro:null,consCheckedProTotal:null},
    		retailDetailOtherList:[]
    	}
    },
    ready:function(){
    	var that = this;
    	var ym = MainFrameUtil.getParams("schemeListDetailDialog").ym;
    	that.ym = ym.substring(0,4)+"-"+ym.substring(4);
	     	
    	//初始化form表单数据，并重新加载“零售市场售电收入明细”表格
    	//that.initFormData();
    	
      	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"schemeListDetailDialog");
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
    },
    watch: {
    	"ym": function(value){
    		this.initFormData();
    		this.initDatagrid();
    	},
    },
    methods: {
    	//初始化form表单数据，并重新加载方案表格
    	initFormData: function(){
    		var that = this;
    		//通过年月获取结算数据
        	$.ajax({				//不知这里为什么当时写的时候这里用的controller和add页面（同样是编辑页面）不一致，因为没有用户反映有bug所以暂时先不改  --by wzl
	       		url: '${Config.baseURL}/cloud-purchase-web/smSettlementMonthController/getFormDateBySettleIdOrYm?ym=' + that.ym,
	            method:'get',
	            async: false,
	            success:function(data){
	               if(data.flag ){
            	   		that.formData = data.data;
	               		if(that.formData.actualTotalPq!=null && that.formData.totalPurchasePq != 0){
	               			//偏差电量比例=（实际用电量-总购电量）/总购电量
	               			that.formData.devPqProp = (((parseFloat(that.formData.actualTotalPq)-parseFloat(that.formData.totalPurchasePq))/parseFloat(that.formData.totalPurchasePq))*100).toFixed(2);
	               		}
	                	that.saveData.settleId = that.formData.id;
	                	that.saveData.ym = that.formData.ym;
	                	that.saveData.smCompanyProfit.id = that.formData.companyId;
	                	that.saveData.smCompanyProfit.devDam = that.formData.devDam;
	                	that.saveData.smCompanyProfit.companyPro = that.formData.companyPro;
	                	that.saveData.smCompanyProfit.consCheckedProTotal = that.formData.consCheckedProTotal;
	                	//根据年月获取双边、竞价、挂牌三个加权平均价
	               		$.ajax({
	                   		url: '${Config.baseURL}/smPurchaseSchemeController/getLcBidListedAvgPrcByYm?ym=' + that.formData.ym,
	                        method:'get',
	                        success:function(data){
	                           if(data.flag && data.data != null){
	                       	   		that.formData.bidPrcAvg = data.data.bidPrcAvg;
	                       	   		that.formData.lcPrcAvg = data.data.lcPrcAvg;
	                       	   		that.formData.listedPrcAvg = data.data.listedPrcAvg;
	                       	   }else{
	                       			MainFrameUtil.alert({title:"提示",body:"加权平均价计算失败，请完善购电交易信息！"});
	                       	   }
	                        },
	                        error:function(){
	                       	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
	                        }
	               		});
                 	}else{
                         MainFrameUtil.alert({ title:"失败提示：", body:data.msg});
                    }
	            },
	            error:function(){
	           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
	            }
	   		});
    	},
    	initDatagrid:function(){
    		var that = this;
    		//用户月度电量分配列表
           	$("#userPqGrid").datagrid({
           		url: basePath+"cloud-purchase-web/settlementControllerOther/getRetailDetailByYm?ym=" + that.ym,
           		method: 'get',
                loadMsg:"请稍后",
                width:'100%',
                height:"100%",
                checkOnSelect : true,
                singleSelect:true,
                rownumbers:true,
    		    frozenColumns:[[
    				{field:'consName',title:'用户名称',width:100,align:'center'},
    				{field:'voltCodeName',title:'电压等级',width:100,align:'center'},
      			]],
                columns:[[
                	{field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'deliveryPq',title:'结算电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'deliveryPrc',title:'交易电价</br>（元/兆瓦时）',width:100,align:'center'},
    				{field:'deliveryCost',title:'交易电费</br>(元)',width:100,align:'center'},		//结算电费	s_m_consumer_profit表的字段    				
    				{field:'serviceAmt',title:'服务费</br>（元）',width:100,align:'center'},
    				{field:'consCheckedPro',title:'用户偏差考核</br>费用（元）',width:100,align:'center'},
    				{field:'totalPq',title:'分配总电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'lcPq',title:'分配双边电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'bidPq',title:'分配竞价电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'listedPq',title:'分配挂牌电量 </br>（兆瓦时）',width:100,align:'center'}
    		    ]],
        		loadFilter: function(data){
        			var oldRowDatas = data.rows;
                	//重新计算  分配总电量
                	for(var i=0;i<oldRowDatas.length;i++){
                		var oldRowData = oldRowDatas[i];
                		//计算 分配总电量
                		oldRowData.totalPq = 0;
                		if(oldRowData.lcPq!=null&&oldRowData.lcPq!=""){
                			oldRowData.totalPq = parseFloat(oldRowData.lcPq)
                		}
                		if(oldRowData.bidPq!=null&&oldRowData.bidPq!=""){
                			oldRowData.totalPq = oldRowData.totalPq  + parseFloat(oldRowData.bidPq)
                		}
                		if(oldRowData.listedPq!=null&&oldRowData.listedPq!=""){
                			oldRowData.totalPq = oldRowData.totalPq  + parseFloat(oldRowData.listedPq)
                		}
                	}
       				return data;
        		}
            });
    	},
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("schemeListDetailDialog")}}];
            return buttons;
        }
    }
});
</script>
</body>
</html>
