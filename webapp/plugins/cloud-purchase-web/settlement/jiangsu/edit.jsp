<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>结算管理-月度收益结算方案编辑</title>
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
					 <su-textbox name="lctotalP" :data.sync="saveData.smPurchaseScheme.schemeName"></su-textbox>
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
	            <mk-form-col :label="formFields.lcPq2.label" label-width="140px" label-align="right" >
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
	             <mk-form-col :label="formFields.bidDealPq.label" label-width="140px" label-align="right" >
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
	             <mk-form-col label="挂牌成交电量" label-width="140px" label-align="right" >
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
			<!-- <mk-form-row >
				未考核购电总收益
	            <mk-form-col :label="formFields.uncheckedTotalPro.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.compDealProfit" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				售电公司考核
	             <mk-form-col :label="formFields.companyCheck.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidtotalP" :data.sync="formData.compDeviationProfit" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				用户承担考核
	            <mk-form-col :label="formFields.consCheckedPro.label" label-width="140px" label-align="right" >
	    			<su-textbox name="bidavgPr" :data.sync="formData.consCheckedProTotal" disabled :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
	                      结算电量
	            <mk-form-col :label="formFields.settlementPq2.label"  label-width="140px" label-align="right" >
					 <su-textbox disabled="disabled" name="lctotalP" :data.sync="formData.deliveryAllPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row> -->
		</mk-form-panel>
	</mk-panel>
	<!-- 用户月度电量分配 -->
    <mk-panel title="用户月度电量分配" height="300" line="true">
        <div class="userPqGrid" style="height:100%">
            <div id="userPqGrid"> </div>
        </div>
    </mk-panel>
    <!-- 用户偏差费用计算 -->
    <mk-panel title="用户偏差费用计算" height="300" line="true">
        <div class="deviationAmtGrid" style="height:100%">
            <div id="deviationAmtGrid"> </div>
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
				<!-- 零售市场购电收入 -->
				<mk-form-col label="零售市场购电收入" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.consAmtTotal" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				<!-- 批发市场购电支出 -->
				<mk-form-col label="批发市场购电支出" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.companyCostTotal" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				
				<!-- 用户总净收益formFields.consTotalPro.label -->
				<!-- <mk-form-col label="用户总净收益" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smPurchaseScheme.consProfit" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col> -->
			</mk-form-row>
			<mk-form-row >
	            <!-- 赔偿用户费用formFields.consCompensateAmt.label-->
				<!-- <mk-form-col label="赔偿用户费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smPurchaseScheme.consCompensate" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col> -->
				<!-- 用户偏差考核费用 -->
				<mk-form-col label="用户偏差考核费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="formData.consCheckedProTotal" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
				<!-- 售电公司偏差费用 -->
				<mk-form-col label="售电公司偏差费用" label-width="140px" label-align="right">
					<su-textbox disabled="disabled" :data.sync="saveData.smCompanyProfit.devDam" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<!-- 用户收益明细 (零售市场售电收入明细)-->
    <mk-panel title="零售市场售电收入明细" height="300" line="true">
    	<div slot="buttons" class="pull-right" style="text-align:right">
            <su-button s-type="primary" @click="calculation" >收益计算</su-button>
        </div>
        <div class="row" style="height:230px">
            <div id="userIncomeGrid"> </div>
        </div>
    </mk-panel>
    
    <!-- 批发市场购电支出明细-->
    <mk-panel title="批发市场购电支出明细" height="300" line="true">
        <div class="row" style="height:230px">
            <div id="costGrid"> </div>
        </div>
    </mk-panel>
</su-form>	
</div>
<script>
var basePath="${Config.baseURL}";
var editIndex = undefined;
var editIndex2 = undefined;
var settlementListVue=new Vue({
    el: '#settlementList',
    data: {
    	//ym: yyyy-MM格式
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
    	countFlag: true,//计算标志
    	divideWayCodeList:[],//选择分成模式下拉框数据
    	deviationCheckCodeList:[],//偏差考核方式（偏差是否考核）下拉框数据
    	saveData:{
    		settleId: null,
    		ym: null,
    		smPurchaseScheme:{id:null, settleId: null, schemeName:null, compProfit:null, consProfit:null, consCompensate:null},
    		smPurchaseSchemeDetailDetailList:[],
    		smConsDeviationAmtList:[],
    		smCompanyProfit:{},
   			smConsumerProfitList:[],
   			smCompanyCostDetailList: []
    	}
    },
    ready:function(){
    	var that = this;
    	that.formData = MainFrameUtil.getParams("sellSettlementEdit").formData;
    	that.saveData.smPurchaseScheme = MainFrameUtil.getParams("sellSettlementEdit").scheme;
    	that.saveData.ym = that.saveData.smPurchaseScheme.ym;
    	that.saveData.settleId = that.saveData.smPurchaseScheme.settleId;
    	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"sellSettlementEdit");
    	//获取选择分成模式下拉框的内容
   		$.ajax({
       		url: '${Config.baseURL}/globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_divideWayCode',
            method:'get',
            async: false,
            success:function(data){
               if(data !== null){
           	   		that.divideWayCodeList = data;
           	   }
            },
            error:function(){
           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
            }
   		});
    	//获取偏差考核方式（偏差是否考核）下拉框的内容
   		$.ajax({
       		url: '${Config.baseURL}/globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_deviationCheckCode',
            method:'get',
            async: false,
            success:function(data){
               if(data !== null){
           	   		that.deviationCheckCodeList = data;
           	   }
            },
            error:function(){
           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
            }
   		});
   		
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
   		
    	//用户月度电量分配列表
       	$("#userPqGrid").datagrid({
       		url: basePath+"cloud-purchase-web/smPurchaseSchemeDetailController/getConsInfoByYmOrSchemeId?schemeId=" + that.saveData.smPurchaseScheme.id,
       		method: 'get',
            loadMsg:"请稍后",
            width:'100%',
            height:"100%",
            checkOnSelect : true,
            singleSelect:true,
            rownumbers:true,
            frozenColumns:[[
		    	{field:'ck',title:'选择',checkbox: true,},
				{field:'consName',title:'用户名称',width:100,align:'center'},
				{field:'voltCodeName',title:'电压等级',width:100,align:'center'},

  			]],
            columns:[
            	[
                    {field:'',title:'双边电量分成方式',width:320,align:'center',colspan:4},
                    {field:'',title:'竞价电量分成方式',width:320,align:'center',colspan:4},
                    {field:'',title:'电量分配',width:480,align:'center',colspan:6},
                    {field:'',title:'结算信息',width:160,align:'center',colspan:4}
                ],
            	[
				{field:'diviCode',title:'分成模式',width:80,align:'center',
					editor:{type:'combobox',options:{
                        valueField: 'value',
                        textField: 'name',
                        data: that.divideWayCodeList
                    }},
					formatter:function(value,index,row){
						for(var i = 0 ; i < that.divideWayCodeList.length; i++){
							if(value == that.divideWayCodeList[i].value){
								return that.divideWayCodeList[i].name;
							}
						}
            	  	}
				},
				{field:'agrePrc',title:'保底协议价 </br>（元/兆瓦时）',width:80,align:'center',
					editor:{id:"agrePrc",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'diviValue',title:'分成基准值 </br>（元/兆瓦时）',width:80,align:'center',
					editor:{id:"diviValue",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'partyALcProp',title:'用户双边</br>分成比例 （%）',width:80,align:'center',
					editor:{id:"partyALcProp",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				
				{field:'bidDiviCode',title:'分成模式',width:80,align:'center',
					editor:{type:'combobox',options:{
                        valueField: 'value',
                        textField: 'name',
                        data: that.divideWayCodeList
                    }},
					formatter:function(value,index,row){
						for(var i = 0 ; i < that.divideWayCodeList.length; i++){
							if(value == that.divideWayCodeList[i].value){
								return that.divideWayCodeList[i].name;
							}
						}
            	  	} 
				},
				{field:'bidAgrePrc',title:'保底协议价 </br>（元/兆瓦时）',width:80,align:'center',
					editor:{id:"bidAgrePrc",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'bidDiviValue',title:'分成基准值 </br>（元/兆瓦时）',width:80,align:'center',
					editor:{id:"bidDiviValue",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'partyABidProp',title:'用户竞价</br>分成比例 （%）',width:80,align:'center',
					editor:{id:"partyABidProp",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				 
				 {field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:80,align:'center',
					//editor:{id:"proxyPq",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					}
				},
				{field:'totalPq',title:'分配总电量 </br>（兆瓦时）',width:80,align:'center'},
				{field:'lcPq',title:'分配双边电量 </br>（兆瓦时）',width:80,align:'center',
					editor:{id:"lcPq",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }},
				{field:'bidPq',title:'分配竞价电量 </br>（兆瓦时）',width:80,align:'center',
					editor:{id:"bidPq",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				 {field:'listedPq',title:'分配挂牌电量 </br>（兆瓦时）',width:80,align:'center',
					editor:{id:"listedPq",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				{field:'actualPq',title:'实际用电量 </br>（兆瓦时）',width:80,align:'center',
					//editor:{id:"actualPq",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				 },
				/* {field:'deliveryPq',title:'结算电量</br>（兆瓦时）',width:'8%',align:'center'}, */
				{field:'deliveryPrc',title:'合同加权平均价</br>（元/兆瓦时）',width:80,align:'center',
					editor:{type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					}
				},
				{field:'calDeliveryPrc',title:'合同加权平均价计算值',width:100,align:'center',hidden:true},
				{field:'serviceAmt',title:'服务费用</br>（元）',width:80,align:'center',
					editor:{type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					}
				},
				{field:'calServiceAmt',title:'服务费用计算值',width:100,align:'center',hidden:true},
				/* {field:'devPqProp',title:'偏差电量比例 </br>（%）',width:'8%',align:'center'} */
		    ]],
			onClickCell: function(index, field, value){
    			if (editIndex != index){
    				settlementListVue.endEditing();
   					$('#userPqGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
   					var ed = $('#userPqGrid').datagrid('getEditor', {index:index,field:field});
   					if(ed != null){		//若点击的是可编辑的单元格，则编辑时默认focus此编辑框
    					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
   					}
   					editIndex = index;
    			}
    		},
    		onLoadSuccess:function(data){
    			that.initDatas();
    			//that.endEditing();
    		}
        });
    	
    	//用户偏差费用计算表
       	$("#deviationAmtGrid").datagrid({
       		url: basePath+"cloud-purchase-web/smConsDeviationAmtController/getConsDeviAmtByYmOrSchemeId?schemeId=" + that.saveData.smPurchaseScheme.id,
       		method: 'get',
            loadMsg:"请稍后",
            width:'100%',
            height:"100%",
            checkOnSelect : true,
            singleSelect:true,
            rownumbers:true,
            frozenColumns:[[
		    	{field:'ck',title:'选择',checkbox: true,},
				{field:'consName',title:'用户名称',width:100,align:'center'},
				{field:'voltCodeName',title:'电压等级',width:100,align:'center'},

  			]],
            columns:[[
				{field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:'8%',align:'center',
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
				{field:'devPqProp',title:'偏差电量比例 </br>（%）',width:'8%',align:'center'},
				{field:'punishType',title:'偏差是否考核',width:'6%',align:'center',
					editor:{type:'combobox',options:{
                        valueField: 'value',
                        textField: 'name',
                        data: that.deviationCheckCodeList
                    }},
					formatter:function(value,index,row){
						for(var i = 0 ; i < that.deviationCheckCodeList.length; i++){
							if(value == that.deviationCheckCodeList[i].value){
								return that.deviationCheckCodeList[i].name;
							}
						}
            	  	}
				},
				{field:'lowerThreshold',title:'少用考核比例</br>（%）',width:'8%',align:'center',
					editor:{id:"lowerThreshold",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'lowerPrc',title:'少用考核价格</br>（元/兆瓦时）',width:'8%',align:'center',
					editor:{id:"lowerPrc",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'lowerDevAmt',title:'少用考核金额</br>（元）',width:'8%',align:'center',
					formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }
					  }
				},
				{field:'upperThreshold1',title:'多用1段考核比例</br>（%）',width:'8%',align:'center',
					editor:{id:"upperThreshold1",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				},
				{field:'upperPrc1',title:'1段考核价格</br>（元/兆瓦时）',width:'8%',align:'center',
					editor:{id:"upperPrc1",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				},
				{field:'upperDevAmt1',title:'多用1段考核金额</br>（元）',width:'8%',align:'center',
					/* editor:{id:"upperDevAmt1",type:'numberbox',options:{precision:4}}, */
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				},
				{field:'upperThreshold2',title:'多用2段考核比例</br>（%）',width:'8%',align:'center',
					editor:{id:"upperThreshold2",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				},
				{field:'upperPrc2',title:'2段考核价格</br>（元/兆瓦时）',width:'8%',align:'center',
					editor:{id:"upperPrc2",type:'numberbox',options:{precision:4}},
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				},
				{field:'upperDevAmt2',title:'多用2段考核金额</br>（元）',width:'8%',align:'center',
					/* editor:{id:"upperDevAmt2",type:'numberbox',options:{precision:4}}, */
					formatter:function(value,index,row){
						if(value){
							return parseFloat(value);
						}
					}
				}
		    ]],
			onClickCell: function(index, field, value){
    			if (editIndex2 != index){
    				index = settlementListVue.endEditing2(index);
    				//如果验证通过，则编辑点击行，否则继续编辑
   					$('#deviationAmtGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
   					var ed = $('#deviationAmtGrid').datagrid('getEditor', {index:index,field:field});
   					if(ed != null){		//若点击的是可编辑的单元格，则编辑时默认focus此编辑框
       					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
   					}
   					editIndex2 = index;
    			}
    		}
        });
    	
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
       	
    	//用户收益(零售市场售电收入明细)列表
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
                       {field:'',title:'',width:'18%',align:'center',colspan:1},
                       {field:'consFee',title:'用户费用（元）',width:'18%',align:'center',colspan:7},
                       /* {field:'compFee',title:'售电公司费用（元）',width:'18%',align:'center',colspan:1} */
                     ],
                     [
      				   {field:'consName',title:'用户名称',width:'15%',align:'center'},
      				   /* {field:'diviCode',title:'分成模式',width:'15%',align:'center',
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
      	            	  }}, */
      				   {field:'deliveryPrc',title:'结算电价</br>(元/兆瓦时)',width:'12%',align:'center'},
      				   {field:'marketizePq',title:'市场化交易结算电量</br>(兆瓦时)',width:'15%',align:'center'},
      				   {field:'nmarketizePq',title:'按非市场电价结算电量</br>(兆瓦时)',width:'15%',align:'center'},
      				   {field:'deliveryCost',title:'结算电费</br>(元)',width:'12%',align:'center'},
      				   {field:'consTotalPro',title:'总节省电费</br>(元)',width:'12%',align:'center'},
      				   {field:'consUncheckedPro',title:'差额电费</br>(元)',width:'10%',align:'center'},
      				   {field:'consCheckedPro',title:'偏差考核费用</br>(元)',width:'10%',align:'center'},
      				
      				   /* {field:'consCompensate',title:'赔偿用户费用</br>(元)',width:'14%',align:'center'} */
      				   /* {field:'compUncheckedPro',title:'未考核收益',width:'14%',align:'center'} */
				     ]]
       	});
    	//批发市场购电支出明细 列表
     	$("#costGrid").datagrid({
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
            columns:[[
      				   {field:'agreName',title:'合同名称',width:'30%',align:'center'},
      				   {field:'monthPq',title:'合同分月电量</br>(兆瓦时)',width:'20%',align:'center'},
      				   {field:'monthPrc',title:'合同电价</br>(元/兆瓦时)',width:'20%',align:'center'},
      				   {field:'deliveryPq',title:'结算电量</br>(兆瓦时)',width:'15%',align:'center'},
      				   {field:'deliveryAmt',title:'结算电费</br>(元)',width:'15%',align:'center'}
				     ]]
       	});
     	$.ajax({
   			url: '${Config.baseURL}/smPurchaseSchemeControllerJs/getSmPurchaseSchemeVoBySchemeId/'+ that.saveData.smPurchaseScheme.id,
            method:'GET',
            success:function(data){
                if(data !== null && data.flag){
                	$("#userIncomeGrid").datagrid("loadData",data.data.smConsumerProfitDetailList);
                	$("#userPqGrid").datagrid("loadData",data.data.smPurchaseSchemeDetailDetailList);
                	$("#costGrid").datagrid("loadData",data.data.smCompanyCostDetailList);
                	if(data.data.smCompanyProfit != null){
	                	that.saveData.smCompanyProfit = data.data.smCompanyProfit;
                	}
                	
                	that.saveData.smPurchaseSchemeDetailDetailList = data.data.smPurchaseSchemeDetailDetailList;
                	that.saveData.smCompanyCostDetailList = data.data.smCompanyCostDetailList;
                	that.saveData.smConsumerProfitList = data.data.smConsumerProfitList;
                	
                	
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
	                that.saveData.smPurchaseScheme.compProfit = that.saveData.smCompanyProfit.companyPro;
                	that.formData.consCheckedProTotal = consCheckedProTotal;
                	that.saveData.smPurchaseScheme.consProfit = consTotalPro;
                	that.saveData.smPurchaseScheme.consCompensate = payConsMoney;
           	    }else{
           		   MainFrameUtil.alert({title:"提示",body:data.msg});
           	    }
            },
            error:function(){
           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
            }
		});
    },
    methods: {
    	
		initDatas: function(){
        	var oldRowDatas = $('#userPqGrid').datagrid('getRows');
        	//重新计算 偏差电量比例 和 分配总电量
        	for(var i=0;i<oldRowDatas.length;i++){
        		var oldRowData = oldRowDatas[i];
        		oldRowData.deliveryPq = oldRowData.actualPq; //	江苏用户结算电量 = 实际用电量
        		/* if(oldRowData.actualPq!=null&&oldRowData.proxyPq!=null&&oldRowData.actualPq!=""&&oldRowData.proxyPq!=""){
    				if(parseFloat(oldRowData.proxyPq)!=0){
    					//偏差电量比例
    					oldRowData.devPqProp=((parseFloat(oldRowData.actualPq)-parseFloat(oldRowData.proxyPq))/parseFloat(oldRowData.proxyPq)*100).toFixed(2);	
    				}
        		} */
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
        		$('#userPqGrid').datagrid('refreshRow', i);
        	}
        	//计算总的长协电量和竞价电量、挂牌电量
        	var allData=$('#userPqGrid').datagrid('getRows');
        	this.formData.lcPqSum=0;
        	this.formData.bidPqSum=0;
        	this.formData.listedPqSum=0;
//         	this.formData.deliveryAllPq=0;
        	for(var i=0;i<allData.length;i++){
        		if(allData[i].lcPq!=null&&allData[i].lcPq!=""){
        			this.formData.lcPqSum+=parseFloat(allData[i].lcPq);
        		}
        		if(allData[i].bidPq!=null&&allData[i].bidPq!=""){
        			this.formData.bidPqSum+=parseFloat(allData[i].bidPq);
        		}
        		if(allData[i].listedPq!=null&&allData[i].listedPq!=""){
        			this.formData.listedPqSum+=parseFloat(allData[i].listedPq);
        		}
        		//计算总结算电量
//         		if(allData[i].deliveryPq!=null&&allData[i].deliveryPq!=""){
//         			this.formData.deliveryAllPq+=parseFloat(allData[i].deliveryPq);
//         		}
        	}
        	//设置精确度，防止多位小数出现
        	if(this.formData.lcPqSum !=null && this.formData.lcPqSum != ""){
    			this.formData.lcPqSum = this.formData.lcPqSum.toFixed(3);
    		}
        	if(this.formData.bidPqSum !=null && this.formData.bidPqSum != ""){
    			this.formData.bidPqSum = this.formData.bidPqSum.toFixed(3);
    		}
        	if(this.formData.listedPqSum !=null && this.formData.listedPqSum != ""){
    			this.formData.listedPqSum = this.formData.listedPqSum.toFixed(3);
    		}
        },
        //计算结算电价及服务费用
        calculateDeliveryPrc: function(rowIndex){
        	if(rowIndex == null){
        		return;
        	}
        	var ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'deliveryPrc'});
        	var ed1 = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'serviceAmt'});
        	var deliveryPrc = $(ed.target).numberbox('getValue');
        	var serviceAmt = $(ed1.target).numberbox('getValue');
        	var that = this;
        	var params = {};
        	params.ym = that.formData.ym;//年月 yyyy-MM格式
        	params.consId = $('#userPqGrid').datagrid('getRows')[rowIndex].consId;		//用户id
        	
        	//分配长协电量
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'lcPq'});
        	params.lcPq = $(ed.target).numberbox('getValue');
        	if(params.lcPq > 0 && (that.formData.totalLcPq == "" || that.formData.totalLcPq == null || that.formData.totalLcPq == 0)
        			&& (that.formData.lcPrcAvg == "" || that.formData.lcPrcAvg == null || that.formData.lcPrcAvg == 0)){
        		MainFrameUtil.alert({title:"提示",body:"双边交易信息不全，不允许分配双边电量！"});
        		return;
        	}
        	//分配竞价电量
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'bidPq'});
        	params.bidPq = $(ed.target).numberbox('getValue');
        	if(params.bidPq > 0 && (that.formData.totalBidPq == "" || that.formData.totalBidPq == null || that.formData.totalBidPq == 0)
        			&& (that.formData.bidPrcAvg == "" || that.formData.bidPrcAvg == null || that.formData.bidPrcAvg == 0)){
        		MainFrameUtil.alert({title:"提示",body:"竞价交易信息不全，不允许分配竞价电量！"});
        		return;
        	}
        	//分配挂牌电量
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'listedPq'});
        	params.listedPq = $(ed.target).numberbox('getValue');
        	if(params.listedPq > 0 && (that.formData.totalListedPq == "" || that.formData.totalListedPq == null || that.formData.totalListedPq == 0)
        			&& (that.formData.listedPrcAvg == "" || that.formData.listedPrcAvg == null || that.formData.listedPrcAvg == 0)){
        		MainFrameUtil.alert({title:"提示",body:"挂牌交易信息不全，不允许分配挂牌电量！"});
        		return;
        	}

        	
        	//双边分成模式
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'diviCode'});
        	params.diviCode = $(ed.target).combobox('getValue');
        	//双边保底协议价 </br>（元/兆瓦时）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'agrePrc'});
        	params.agrePrc = $(ed.target).numberbox('getValue');
        	//双边分成基准值（元/兆瓦时）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'diviValue'});
        	params.diviValue = $(ed.target).numberbox('getValue');
        	//用户双边分成比例 （%）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'partyALcProp'});
        	params.partyALcProp = $(ed.target).numberbox('getValue');
        	
        	
        	//竞价分成模式
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'bidDiviCode'});
        	params.bidDiviCode = $(ed.target).combobox('getValue');
        	//竞价保底协议价 </br>（元/兆瓦时）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'bidAgrePrc'});
        	params.bidAgrePrc = $(ed.target).numberbox('getValue');
        	//竞价分成基准值（元/兆瓦时）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'bidDiviValue'});
        	params.bidDiviValue = $(ed.target).numberbox('getValue');
        	//用户竞价</br>分成比例 （%）
        	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'partyABidProp'});
        	params.partyABidProp = $(ed.target).numberbox('getValue');
        	
        	//如果分配电量都为空
			if((params.lcPq == null || params.lcPq == "" || params.lcPq == 0) 
					&& (params.bidPq == null || params.bidPq == "" || params.bidPq == 0)
					&& (params.listedPq == null || params.listedPq == "" || params.listedPq == 0) ){
				return;
			}
			//判断结算电价是否为用户手填
			ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'deliveryPrc'});
			var deliveryPrc = $(ed.target).numberbox('getValue');									//加权平均价当前显示值
        	var calDeliveryPrc = $('#userPqGrid').datagrid("getRows")[rowIndex].calDeliveryPrc;		//加权平均价上次计算值
        	var deliveryPrcOld = $('#userPqGrid').datagrid("getRows")[rowIndex].deliveryPrc;		//加权平均价上次值
        	var flag1 = false;
        	var deliveryFlag = true;
        	if(deliveryPrc != null && deliveryPrc !== "" && deliveryPrc == deliveryPrcOld){
        		if(calDeliveryPrc != null && calDeliveryPrc !== "" && parseFloat(calDeliveryPrc) != parseFloat(deliveryPrc)){
        			flag1 = true;
        		}
        		if(!(calDeliveryPrc != null && calDeliveryPrc !== "")){
        			flag1 = true;
        		}
        	}
        	if(deliveryPrc != null && deliveryPrc !== "" && deliveryPrc != deliveryPrcOld){
        		deliveryFlag = false;
        	}
        	
        	//判断服务费用是否为用户手填
        	var calServiceAmt = $('#userPqGrid').datagrid("getRows")[rowIndex].calServiceAmt;	//服务费上次计算值
        	var serviceAmtOld = $('#userPqGrid').datagrid("getRows")[rowIndex].serviceAmt;		//服务费上次值
        	ed1 = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'serviceAmt'});
        	var serviceAmt = $(ed1.target).numberbox('getValue');   							//服务费当前显示值
        	var serviceFlag = true;
       		var flag2 = false;
        	if(serviceAmt != null && serviceAmt !== "" && serviceAmt == serviceAmtOld){
        		if(calServiceAmt != null && calServiceAmt !== "" && parseFloat(calServiceAmt) != parseFloat(serviceAmt)){
        			flag2 = true;
        		}
        		if(!(calServiceAmt != null && calServiceAmt !== "")){
        			flag2 = true;
        		}
        	}
        	if(serviceAmt != null && serviceAmt !== "" && serviceAmt != serviceAmtOld){
        		serviceFlag = false;
        	}
       		if(flag1){
           		MainFrameUtil.confirm({
   			        title:"确认",
   			        body:"合同加权平均价已变更，是否覆盖之前维护的合同加权平均价数据？",
   			        onClose:function(type){
   			            if(type=="ok"){//确定
   			            	deliveryFlag = true;
   			            	if(flag2){
   				        		MainFrameUtil.confirm({
   							        title:"确认",
   							        body:"服务费用已变更，是否覆盖之前维护的服务费用数据？",
   							        onClose:function(type){
   							            if(type=="ok"){//确定
   							            	serviceFlag = true;
   							            	that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
   							            }else{
   							            	serviceFlag = false;
   							            	that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
   							            }
   							        }
   					    		});
   				        	}else{
   				        		that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
   				        	}
   			            }else{
   			            	deliveryFlag = false;
   			            	if(flag2){
   				        		MainFrameUtil.confirm({
   							        title:"确认",
   							        body:"服务费用已变更，是否覆盖之前维护的服务费用数据？",
   							        onClose:function(type){
   							            if(type=="ok"){//确定
   							            	serviceFlag = true;
   							            	that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
   							            }else{
   							            	serviceFlag = false;
   							            }
   							        }
   					    		});
   				        	}else{
   				        		that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
   				        	}
   			            }
   			        }
   	    		});
           	}else{
           		if(flag2){
	        		MainFrameUtil.confirm({
				        title:"确认",
				        body:"服务费用已变更，是否覆盖之前维护的服务费用数据？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	serviceFlag = true;
				        	that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
				            }else{
				            	serviceFlag = false;
				            }
				        }
		    		});
	        	}else{
	        		that.calDeliveryAndService(deliveryFlag,serviceFlag,rowIndex,params);
	        	}
           	}
        },
        calDeliveryAndService:function(deliveryFlag,serviceFlag,rowIndex,params){
        	$('#userPqGrid').datagrid('beginEdit', rowIndex);
           	ed = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'deliveryPrc'});
           	ed1 = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'serviceAmt'});
           	ed3 = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'diviValue'});
           	ed4 = $('#userPqGrid').datagrid('getEditor', {index:rowIndex,field:'bidDiviValue'});
        	$.ajax({
 				url: basePath+"cloud-purchase-web/settlementControllerJs/calculateDeliveryPrc",
       			method:'post',
                data:$.toJSON(params),
                async: false,
                contentType : 'application/json;charset=UTF-8',
                success:function(data){
                    if(data.flag){
                    	//结算电价输入框赋值
                    	console.log($('#userPqGrid').datagrid('getEditor', {index:rowIndex}));
                    	var diviValue = $(ed3.target).numberbox('getValue');
                    	var bidDiviValue = $(ed4.target).numberbox('getValue');
                    	
                    	//赋值结算电价,判断结算电价是否为用户手填
                    	var calDeliveryPrc = $('#userPqGrid').datagrid("getRows")[rowIndex].calDeliveryPrc;
                    	$('#userPqGrid').datagrid("getRows")[rowIndex].calDeliveryPrc = data.data.deliveryPrc;
                    	$('#userPqGrid').datagrid("getRows")[rowIndex].calServiceAmt = data.data.serviceAmt;
                    	if(deliveryFlag){
                    		$(ed.target).numberbox('setValue',data.data.deliveryPrc);
                    	}
                    	//赋值服务费
                    	if(serviceFlag){
                    		$(ed1.target).numberbox('setValue',data.data.serviceAmt);
                    	}
                    	//赋值双边结算基准值
                    	if(!(diviValue != null && diviValue != "" && diviValue != 0)){
                    		$(ed3.target).numberbox('setValue',data.data.diviValue);
                    	}
                    	//赋值竞价结算基准值
                    	if(!(bidDiviValue != null && bidDiviValue != "" && bidDiviValue != 0)){
                    		$(ed4.target).numberbox('setValue',data.data.bidDiviValue);
                    	}
                	}else{
                		MainFrameUtil.alert({title:"失败提示",body:data.msg});
                	}
                },
                error:function(){
               	 MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
                }
    		})
    		$('#userPqGrid').datagrid('endEdit', rowIndex);
        },
        //表格结束编辑
        endEditing: function(){
        	//改变旧行的数据
        	if(editIndex!=undefined){
	        	this.calculateDeliveryPrc(editIndex);
	        	$('#userPqGrid').datagrid('endEdit', editIndex);
        		var oldRowDatas = $('#userPqGrid').datagrid('getRows');
        		var oldRowData = oldRowDatas[editIndex];
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

        		/* if(oldRowData.actualPq!=null&&oldRowData.proxyPq!=null&&oldRowData.actualPq!=""&&oldRowData.proxyPq!=""){
        			oldRowData.deliveryPq=parseFloat(oldRowData.actualPq)-parseFloat(oldRowData.proxyPq)>0?parseFloat(oldRowData.proxyPq):parseFloat(oldRowData.actualPq);
    				if(parseFloat(oldRowData.proxyPq)!=0){
    					//偏差电量比例
    					oldRowData.devPqProp=((parseFloat(oldRowData.actualPq)-parseFloat(oldRowData.proxyPq))/parseFloat(oldRowData.proxyPq)*100).toFixed(2);	
    				}
        		} */
        		$('#userPqGrid').datagrid('refreshRow', editIndex);
        		this.countFlag = false;
			}
        	//计算总的长协电量和竞价电量、挂牌电量
        	var allData=$('#userPqGrid').datagrid('getRows');
        	this.formData.lcPqSum=0;
        	this.formData.bidPqSum=0;
        	this.formData.listedPqSum=0;
//         	this.formData.deliveryAllPq=0;
        	for(var i=0;i<allData.length;i++){
        		if(allData[i].lcPq!=null&&allData[i].lcPq!=""){
        			this.formData.lcPqSum+=parseFloat(allData[i].lcPq);
        		}
        		if(allData[i].bidPq!=null&&allData[i].bidPq!=""){
        			this.formData.bidPqSum+=parseFloat(allData[i].bidPq);
        		}
        		if(allData[i].listedPq!=null&&allData[i].listedPq!=""){
        			this.formData.listedPqSum+=parseFloat(allData[i].listedPq);
        		}
        		//计算总结算电量
//         		if(allData[i].deliveryPq!=null&&allData[i].deliveryPq!=""){
//         			this.formData.deliveryAllPq+=parseFloat(allData[i].deliveryPq);
//         		}
        	}
        	//设置精确度，防止多位小数出现
        	if(this.formData.lcPqSum !=null && this.formData.lcPqSum != ""){
    			this.formData.lcPqSum = this.formData.lcPqSum.toFixed(3);
    		}
        	if(this.formData.bidPqSum !=null && this.formData.bidPqSum != ""){
    			this.formData.bidPqSum = this.formData.bidPqSum.toFixed(3);
    		}
        	if(this.formData.listedPqSum !=null && this.formData.listedPqSum != ""){
    			this.formData.listedPqSum = this.formData.listedPqSum.toFixed(3);
    		}
            editIndex = undefined;
 		},
 		//用户偏差费用计算表格的结束编辑,先重新获取委托电量和实际用电量，然后再计算偏差考核
 		endEditing2: function(index){
 			var that = this;
        	if(editIndex2 != undefined){
        		//先校验输入框内容
        		$('#deviationAmtGrid').datagrid('endEdit', editIndex2);
        		var oldRowDatas = $('#deviationAmtGrid').datagrid('getRows');
        		var oldRowData = oldRowDatas[editIndex2];
        		oldRowData.ym = that.formData.ym
        		//根据一行数据去计算各项考核金额
     			$.ajax({
     				url: basePath+"cloud-purchase-web/smConsDeviationAmtController/calculateDeviationCheckAmt",
           			method:'post',
                    data:$.toJSON(oldRowData),
                    async: false,
                    contentType : 'application/json;charset=UTF-8',
                    success:function(data){
                        if(data.flag){
	                    	oldRowDatas[editIndex2] = data.data;
                    	}else{
                    		//验证未通过，则还是编辑本行
                    		index = editIndex2;
                    		MainFrameUtil.alert({title:"失败提示",body:data.msg});
                    	}
                    },
                    error:function(){
                   	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
                    }
        		});
        		$('#deviationAmtGrid').datagrid('refreshRow', editIndex2);
        		this.countFlag = false;
			}
            editIndex2 = undefined;
            return index;
 		},
      	//点击“计算收益”按钮
    	calculation: function(){
    		var that = this;
    		that.endEditing();
    		that.endEditing2();
    		//把用户用户偏差费用计算表中计算出的偏差费用赋值到用户收益详情中
    		//先把信息放入map中，然后根据consId查找并赋值
    		var deviAmtDatas = $('#deviationAmtGrid').datagrid('getRows');
    		var consCheckedProMap = {};
    		var upperThreshold1Map = {};
    		for(var i = 0; i < deviAmtDatas.length; i ++){
    			var row =  deviAmtDatas[i];
    			//偏差考核费用
    			consCheckedProMap[row.consId] = (parseFloat(row.lowerDevAmt) + parseFloat(row.upperDevAmt1) + parseFloat(row.upperDevAmt2)).toFixed(2);
    			upperThreshold1Map[row.consId] = row.upperThreshold1;
    		}
    		var rows = $("#userPqGrid").datagrid("getRows");
    		var smConsumerProfitDetailList = new Array();
    		for(var i = 0; i < rows.length; i ++){
    			var row =  rows[i];
    			if(row.deliveryPrc == null || row.deliveryPrc =="" ){
   					$("#userPqGrid").datagrid("selectRow",i);
   					MainFrameUtil.alert({title:"提示",body:"请先完善用户月度电量分配表第"+ (i+1) + "行信息"});
   	    			return;
   				}
    			var smConsumerProfitDetail = {};
    			smConsumerProfitDetail.consId = row.consId;
    			smConsumerProfitDetail.consName = row.consName;
    			smConsumerProfitDetail.proxyPq = row.proxyPq;		//月度申报电量
   				smConsumerProfitDetail.lcDistPq	= row.lcPq;			//月度长协电量（与分配长协相等）
   				smConsumerProfitDetail.bidDistPq = row.bidPq;				//竞价分配电量
   				smConsumerProfitDetail.listedPq = row.listedPq;				//分配挂牌电量
   				smConsumerProfitDetail.distTotalPq = row.totalPq;				//分配总电量
   				smConsumerProfitDetail.consDelPq = row.actualPq;			//月度交割电量，用户实际用电量

//    				smConsumerProfitDetail.diviCode = row.diviCode;			//分成模式
//    				smConsumerProfitDetail.agrePrc = row.agrePrc;				//保底协议价 
//    				smConsumerProfitDetail.partyALcProp = row.partyALcProp;		//用户长协分成比例
//    				smConsumerProfitDetail.partyABidProp = row.partyABidProp;		//用户竞价分成比例
   				
   				//结算电价应该是加上服务费用后的价格
   				if(row.serviceAmt == null || row.serviceAmt == '' || row.serviceAmt == 0){
	   				smConsumerProfitDetail.deliveryPrc = row.deliveryPrc;		//结算电价
   				}else{
   					var deliveryPrc = parseFloat(row.deliveryPrc) + parseFloat(row.serviceAmt)/parseFloat(row.totalPq);
   					smConsumerProfitDetail.deliveryPrc = deliveryPrc.toFixed(2);
   				}
   				
   				smConsumerProfitDetail.consElecDev = parseFloat(row.actualPq) - parseFloat(row.proxyPq);	//偏差电量
   				smConsumerProfitDetail.consCheckedPro = consCheckedProMap[row.consId];			//偏差考核费用
   				smConsumerProfitDetail.upperThreshold1 = upperThreshold1Map[row.consId];		//正偏差1段域值
   				
    			smConsumerProfitDetailList.push(smConsumerProfitDetail);
    		}
    		
    		var cal = this.formData;
//        	   	if(cal.totalBidPq != null && cal.bidPqSum != null && cal.totalBidPq < cal.bidPqSum){
//        		   MainFrameUtil.alert({title:"提示",body:"已分配竞价电量不能大于竞价成交电量"}); 
//       		       return ;
//        	   	}
//        	   	if(cal.totalLcPq !=null && cal.lcPqSum != null && cal.totalLcPq < cal.lcPqSum){
//    				MainFrameUtil.alert({title:"提示",body:"已分配双边电量不能大于双边电量"}); 
//        		    return ;
//        	   	}
    		var param = {settleId:that.formData.id, ym: that.formData.ym ,
    				smCompanyProfit: this.saveData.smCompanyProfit, 
    				smConsumerProfitDetailList: smConsumerProfitDetailList};
    		$.ajax({
 				url: basePath+"cloud-purchase-web/settlementControllerJs/caculateProfit",
       			method:'post',
                data:$.toJSON(param),
                contentType : 'application/json;charset=UTF-8',
                success:function(data){
                    if(data.flag){
                    	that.saveData.smCompanyProfit = data.data.smCompanyProfit;
                    	that.saveData.smConsumerProfitList = data.data.smConsumerProfitDetailList;
                    	that.saveData.smCompanyCostDetailList = data.data.smCompanyCostDetailList;
                    	
                    	$("#userIncomeGrid").datagrid("loadData",data.data.smConsumerProfitDetailList);
                    	$("#costGrid").datagrid("loadData",data.data.smCompanyCostDetailList);
                    	var rows = data.data.smConsumerProfitDetailList;
                    	//用户总净收益
                    	var consTotalPro = 0;
                    	//赔偿用户费用
                    	var payConsMoney = 0;
                    	for(var i=0; i < rows.length; i ++){
                    		consTotalPro += parseFloat(rows[i].consTotalPro);
                    		payConsMoney += parseFloat(rows[i].consCompensate);
                    	}
                    	//用户总净收益
                    	that.saveData.smPurchaseScheme.consProfit = consTotalPro;
                    	//赔偿用户费用
                    	that.saveData.smPurchaseScheme.consCompensate = payConsMoney;
                    	
                    	//用户偏差考核总费用
                    	that.formData.consCheckedProTotal = that.saveData.smCompanyProfit.consCheckedProTotal;
                    	//售电公司总利润 
                    	that.saveData.smPurchaseScheme.compProfit = that.saveData.smCompanyProfit.companyPro;
                    	
                    	that.countFlag = true;
                    	MainFrameUtil.alert({title:"成功提示",body:data.msg});
                	}else{
                		MainFrameUtil.alert({title:"失败提示",body:data.msg});
                	}
                },
                error:function(){
               	 MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
                }
    		});
    	},
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"保存",type:"primary",handler:this.saveValidate},
                           {text:"取消",handler:function(){MainFrameUtil.closeDialog("sellSettlementEdit")}}];
            return buttons;
        },
        saveValidate : function(){
     	   var that = this;
     	   if(this.saveData.smPurchaseScheme.schemeName==""||this.saveData.smPurchaseScheme.schemeName==null){
     		   MainFrameUtil.alert({title:"提示",body:"请填写方案名称"}); 
     		   return ;
     	   }
     	   this.endEditing();
     	   this.endEditing2();
     	   if(!this.countFlag){
     		   MainFrameUtil.alert({title:"提示",body:"请重新计算收益！"}); 
     		   
     		   MainFrameUtil.confirm({
 					title:"提示",
 					body:"未(重新)计算收益，继续保存?",
 		            onClose:function(type){
 		                if(type=="ok"){//确定
 		                	var saveData = $.parseJSON($.toJSON(that.saveData));
 		                	saveData.smCompanyProfit = null;
 		                	saveData.smConsumerProfitList = [];
 	                	    saveData.smCompanyCostDetailList = [];
 	                	    that.saveInfo(saveData);
 		                }
 		            }
 		        });
     	   }else{
 	           that.saveInfo($.parseJSON($.toJSON(that.saveData)));
     	   }
     	},
     	saveInfo : function(saveData){	
     		var that = this;
 	   		//给结算方案对象赋值
 	   		saveData.smPurchaseScheme.consNum = that.formData.consNum;
 	   		saveData.smPurchaseScheme.deliveryPq = that.formData.actualTotalPq;
 	   		saveData.smPurchaseScheme.totalProxyPq = that.formData.totalProxyPq;
 	   		saveData.smPurchaseScheme.totalPurchasePq = that.formData.totalPurchasePq;
 	   		saveData.smPurchaseScheme.totalLcPq = that.formData.totalLcPq;
 	   		saveData.smPurchaseScheme.totalBidPq = that.formData.totalBidPq;
 	   		saveData.smPurchaseScheme.totalListedPq = that.formData.totalListedPq;
        		
 	   		//用户偏差费用
     	   var smConsDeviationAmtList = $("#deviationAmtGrid").datagrid("getRows");
     	   saveData.smConsDeviationAmtList=smConsDeviationAmtList;
     	   
     	   //用户月度电量分配
     	   var rows = $("#userPqGrid").datagrid("getRows");
     	   saveData.smPurchaseSchemeDetailList=rows; 
     	   saveData.smPurchaseScheme.settleId=this.formData.id;
     	   
     	   //saveData.smCompanyProfit = this.params.smCompanyProfit;
     	   //saveData.smCompanyProfit.devDam = this.formData.compDeviationProfit;//售电公司偏差考核
     	   //saveData.smConsumerProfitList = $("#userIncomeGrid").datagrid("getRows");
     	   
//      	   var cal = this.formData
//      	   if(cal.totalBidPq != null && cal.bidPqSum != null && cal.totalBidPq < cal.bidPqSum){
//      		   MainFrameUtil.alert({title:"提示",body:"已分配竞价电量不能大于竞价成交电量"}); 
//     		       return ;
//      	   }
//      	   if(cal.totalLcPq !=null && cal.lcPqSum != null && cal.totalLcPq < cal.lcPqSum){
//  				MainFrameUtil.alert({title:"提示",body:"已分配双边电量不能大于双边电量"}); 
//      		    return ;
//      	   }
			//开进度条
        	$.messager.progress();
			//保存和关闭按钮失效
           	$("#sellSettlementEdit-window div.dialog-buttons button" , parent.document).attr("disabled","disabled");
     	   $.ajax({
 				url:basePath+"cloud-purchase-web/smPurchaseSchemeControllerJs/saveSmPurchaseSchemeVo",
 				type:'post',
 				data:$.toJSON(saveData),
 	 			contentType : 'application/json;charset=UTF-8',
 	 			success:function(data){
 	 				//关进度条
                	$.messager.progress('close');
                	//保存和关闭按钮生效
    	           	$("#sellSettlementEdit-window div.dialog-buttons button" , parent.document).attr("disabled",false);
 	 				if(data.flag){
                 		MainFrameUtil.alert({title:"成功提示",body:data.msg});
                 		MainFrameUtil.setParams({settleId: data.data.settleId},"sellSettlementEdit");
                 		MainFrameUtil.closeDialog("sellSettlementEdit");
                 	}else{
                 		MainFrameUtil.alert({title:"警告",body:data.msg});
                 	}
 	 			},
     		    error:function(){
     		    	//关进度条
                	$.messager.progress('close');
                	//保存和关闭按钮生效
    	           	$("#sellSettlementEdit-window div.dialog-buttons button" , parent.document).attr("disabled",false);
     		    	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"}); 
     		    }
 			})
     	}
    }
});
</script>
</body>
</html>
