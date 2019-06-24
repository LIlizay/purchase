<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>结算管理-月度收益结算方案新增</title>
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
					<su-datepicker format="YYYY-MM" :data.sync="ym" :disabled.sync = "flag"></su-datepicker>
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
					<su-textbox :data.sync="saveData.smCompanyProfit.companyPro" :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
				</mk-form-col>
				
				<!-- 售电公司偏差费用 -->
				<mk-form-col label="售电公司偏差费用" label-width="140px" label-align="right">
					<su-textbox :data.sync="saveData.smCompanyProfit.devDam" :addon="{'show':true,'rightcontent':' 元'}" ></su-textbox>
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
    	flag:false,
    	ym:null,
    	//serviceAmtTotal:0, //服务费总和 ，暂时不用计算，勿删
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
     	if(MainFrameUtil.getParams("settleSchemeAddDialog").ym != undefined){
     		that.flag = true;
	     	var ym = MainFrameUtil.getParams("settleSchemeAddDialog").ym;
	     	that.ym = ym.substring(0,4)+"-"+ym.substring(4);
	     	
     	}else{
	    	//没有ym传入时，默认是本月
	    	var date = new Date();
	    	that.ym = date.getFullYear() + "-" + (date.getMonth() > 8 ? (date.getMonth()+1) : ("0" + (date.getMonth()+1)));
     	}
    	//初始化form表单数据，并重新加载“零售市场售电收入明细”表格
    	//that.initFormData();
    	
      	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"settleSchemeAddDialog");
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
    },
    watch: {
    	"ym": function(value){
    		//this.serviceAmtTotal = 0;		//暂时不用计算，勿删
    		this.initFormData();
    		this.initDatagrid();
    	},
    	/* "saveData.smCompanyProfit.devDam":function(value){
    		var serviceAmtTotal = (this.serviceAmtTotal != null && this.serviceAmtTotal != '') ? this.serviceAmtTotal : 0;
    		var devDam = (this.saveData.smCompanyProfit.devDam != null && this.saveData.smCompanyProfit.devDam != '') ? this.saveData.smCompanyProfit.devDam : 0;
    		var consCheckedProTotal = (this.saveData.smCompanyProfit.consCheckedProTotal != null && this.saveData.smCompanyProfit.consCheckedProTotal != '') ? this.saveData.smCompanyProfit.consCheckedProTotal : 0;
    		this.saveData.smCompanyProfit.companyPro = parseFloat(serviceAmtTotal) - parseFloat(devDam) + parseFloat(consCheckedProTotal);
    	} 暂时不用计算，勿删 */
    },
    methods: {
    	//初始化form表单数据，并重新加载方案表格
    	initFormData: function(){
    		var that = this;
    		//通过年月获取结算数据
        	$.ajax({
	       		url: '${Config.baseURL}/cloud-purchase-web/settlementControllerOther/getFormDateBySettleIdOrYm?ym=' + that.ym,
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
                fit:true,
                fitColumns:true,
    		    frozenColumns:[[
    				{field:'consName',title:'用户名称',width:100,align:'center'},
    				{field:'voltCodeName',title:'电压等级',width:100,align:'center'},
      			]],
                columns:[[
                	{field:'proxyPq',title:'委托电量 </br>（兆瓦时）',width:100,align:'center',
    					//editor:{id:"proxyPq",type:'numberbox',options:{precision:4}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					}
    				},
    				{field:'deliveryPq',title:'结算电量 </br>（兆瓦时）',width:100,align:'center',		//取“实际用电量”actualPq
    					//editor:{id:"actualPq",type:'numberbox',options:{precision:4}},
    					formatter:function(value,index,row){
    						if(value){
    							return parseFloat(value);
    						}
    					}
    				},
    				{field:'deliveryPrc',title:'交易电价</br>（元/兆瓦时）',width:100,align:'center',		//结算电价（合同加权平均价）
    					editor:{type:'numberbox',options:{precision:2}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					}
    				},
    				{field:'deliveryCost',title:'交易电费</br>(元)',width:100,align:'center'},		//结算电费	s_m_consumer_profit表的字段
    				
    				{field:'serviceAmt',title:'服务费</br>（元）',width:100,align:'center',			//
    					editor:{type:'numberbox',options:{precision:2}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					}
    				},
    				{field:'consCheckedPro',title:'用户偏差考核</br>费用（元）',width:100,align:'center',		//s_m_consumer_profit表的字段
    					editor:{type:'numberbox',options:{precision:2}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					}
    				},
    				 
    				{field:'totalPq',title:'分配总电量 </br>（兆瓦时）',width:100,align:'center'},
    				{field:'lcPq',title:'分配双边电量 </br>（兆瓦时）',width:100,align:'center',
    					editor:{id:"lcPq",type:'numberbox',options:{precision:4}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					  }},
    				{field:'bidPq',title:'分配竞价电量 </br>（兆瓦时）',width:100,align:'center',
    					editor:{id:"bidPq",type:'numberbox',options:{precision:4}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					  }
    				 },
    				 {field:'listedPq',title:'分配挂牌电量 </br>（兆瓦时）',width:100,align:'center',
    					editor:{id:"listedPq",type:'numberbox',options:{precision:4}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					  }
    				 }
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
        		},
        		onLoadSuccess:function(data){
        			for(var i in data.rows){
        				
        				//暂时不用计算，勿删
        				//that.serviceAmtTotal += parseFloat((data.rows[i].serviceAmt != null && data.rows[i].serviceAmt != '') ? data.rows[i].serviceAmt : 0);
        			}
        			//that.saveData.smCompanyProfit.companyPro += parseFloat(that.serviceAmtTotal);		//暂时不用计算，勿删
        			that.endEditing();
        		}
            });
    	},
        //表格结束编辑
        endEditing: function(){
        	//改变旧行的数据
        	if(editIndex!=undefined){
	        	var oldConsCheckedPro = $('#userPqGrid').datagrid('getRows')[editIndex].consCheckedPro;
	        	var oldServiceAmt = $('#userPqGrid').datagrid('getRows')[editIndex].serviceAmt;
	        	
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
        		oldRowData.totalPq = oldRowData.totalPq.toFixed(3);
        		//计算交易电费
        		var deliveryPrc = $('#userPqGrid').datagrid('getRows')[editIndex].deliveryPrc;
        		var deliveryPq = $('#userPqGrid').datagrid('getRows')[editIndex].deliveryPq;
        		if(deliveryPrc != null && deliveryPrc != '' && deliveryPq != null && deliveryPq !=''){
        			var cost = parseFloat(deliveryPrc) * parseFloat(deliveryPq)
        			oldRowData.deliveryCost = cost.toFixed(2);
        		}
        		
        		//计算用户偏差考核总费用
            	var consCheckedPro = $('#userPqGrid').datagrid('getRows')[editIndex].consCheckedPro;
            	var consCheckedProTotal = (this.saveData.smCompanyProfit.consCheckedProTotal == null || this.saveData.smCompanyProfit.consCheckedProTotal == '') ? 0 : this.saveData.smCompanyProfit.consCheckedProTotal;
            	consCheckedProTotal = parseFloat(consCheckedProTotal) - parseFloat(((oldConsCheckedPro == null || oldConsCheckedPro == '') ? 0 : oldConsCheckedPro)) + parseFloat(((consCheckedPro == null || consCheckedPro == '') ? 0 : consCheckedPro));
            	this.saveData.smCompanyProfit.consCheckedProTotal = consCheckedProTotal.toFixed(2);

            	//计算用户总服务费 //暂时不用计算，勿删
            	/* var serviceAmt = $('#userPqGrid').datagrid('getRows')[editIndex].serviceAmt;
            	this.serviceAmtTotal = parseFloat(this.serviceAmtTotal) - parseFloat((oldServiceAmt != null && oldServiceAmt != '') ? oldServiceAmt : 0) + parseFloat((serviceAmt != null && serviceAmt != '') ? serviceAmt : 0);
            	
            	//计算售电公司总利润（服务费之和-售电公司偏差费用+用户偏差考核费用）
            	var devDam = (this.saveData.smCompanyProfit.devDam != null && this.saveData.smCompanyProfit.devDam != '') ? this.saveData.smCompanyProfit.devDam : 0;
    			var consCheckedProTotal = (this.saveData.smCompanyProfit.consCheckedProTotal != null && this.saveData.smCompanyProfit.consCheckedProTotal != '') ? this.saveData.smCompanyProfit.consCheckedProTotal : 0;
            	this.saveData.smCompanyProfit.companyPro = parseFloat(this.serviceAmtTotal) - parseFloat(devDam) + parseFloat(consCheckedProTotal); */
            	$('#userPqGrid').datagrid('refreshRow', editIndex);
			}
        	//计算总的长协电量和竞价电量、挂牌电量
        	var allData=$('#userPqGrid').datagrid('getRows');
        	this.formData.lcPqSum=0;
        	this.formData.bidPqSum=0;
        	this.formData.listedPqSum=0;
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
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"保存",type:"primary",handler:this.saveValidate},
                           {text:"取消",handler:function(){MainFrameUtil.closeDialog("settleSchemeAddDialog")}}];
            return buttons;
        },
    	saveValidate : function(){
    	   var that = this;
    	   if(this.saveData.ym==""||this.saveData.ym==null){
    		   MainFrameUtil.alert({title:"提示",body:"请选择年月"}); 
    		   return ;
    	   }
    	   this.endEditing();
           that.saveInfo($.parseJSON($.toJSON(that.saveData)));
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
	   		
	   		saveData.smCompanyProfit.id = that.saveData.smCompanyProfit.id;
	   		saveData.smCompanyProfit.devDam = that.saveData.smCompanyProfit.devDam;
	   		saveData.smCompanyProfit.companyPro = that.saveData.smCompanyProfit.companyPro;
	   		saveData.smCompanyProfit.consCheckedProTotal = that.saveData.smCompanyProfit.consCheckedProTotal;
       		
    	   //用户月度电量分配
    	   var rows = $("#userPqGrid").datagrid("getRows");
    	   saveData.retailDetailOtherList = rows;
    	   saveData.smPurchaseScheme.settleId=this.formData.id;
    	   $.ajax({
				url:basePath+"cloud-purchase-web/settlementControllerOther/saveSmPurchaseSchemeVo",
				type:'post',
				data:$.toJSON(saveData),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
	 				if(data.flag){
                		MainFrameUtil.alert({title:"成功提示",body:data.msg});
                		MainFrameUtil.setParams({settleId: data.data.settleId},"sellSettlementAdd");
                		MainFrameUtil.closeDialog("sellSettlementAdd");
                	}else{
                		MainFrameUtil.alert({title:"警告",body:data.msg});
                	}
	 			},
    		    error:function(){
    		    	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"}); 
    		    }
			})
    	}
    }
});
</script>
</body>
</html>
