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
					<su-textbox :data.sync="formData.devDam" :addon="{'show':true,'rightcontent':' 元'}" @su-change="changeDevDam(true)"></su-textbox>
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
					<su-textbox id="companyProId" :data.sync="formData.companyPro" :addon="{'show':true,'rightcontent':'元'}" ></su-textbox>
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
</su-form>	
</div>
<script>
var basePath="${Config.baseURL}";
var editIndex = undefined;
var settlementListVue=new Vue({
    el: '#settlementList',
    data: {
    	formFields:{},
    	//ym: yyyy-MM格式
    	ym: null,
    	flag: false,		//年月日期框是否可以编辑： false：可以编辑
    	
    	costPrc : 0,		//批发交易电价,不四舍五入的精确值， 用于计算零售市场收入明细中的服务费
    	incomePrc : 0,		//零售交易平均电价,不四舍五入的精确值， 用于计算零售市场收入明细中的服务费
    	
    	//serviceAmtTotal:0, //服务费总和 ，暂时不用计算，勿删
    	formData:{
    		id:null, ym: null,		//结算id及年月
    		consNum: null, totalProxyPq: null, actualTotalPq: null, totalPurchasePq: null, devPqProp: null, 
    		/* totalLcPq: null, lcPqSum: null, lcPrcAvg: null, 
    		totalBidPq: null, bidPqSum: null, bidPrcAvg: null,
    		totalListedPq: null, listedPqSum: null, listedPrcAvg: null, */
    		
    		//"deliveryAllPq":null, 
    		costAmt: null,costPrc: null,
    		incomeTotalAmt: null, incomePrc: null,
    		
    		companyProfitId:null,		//售电公司收益id
    		devDam:null,				//偏差违约金(售电公司偏差考核)
    		companyPro:null,			//售电公司总利润 
    		consCheckedProTotal: null,	//用户总偏差考核费用
    		
    		costDetailList: []			//批发交易结算明细 list
    	},		
    	saveData:{
    		settleId: null,
    		ym: null,
    		smPurchaseScheme:{id:null, settleId:null, schemeName:null, compProfit:null, consProfit:null, consCompensate:null},
    		smCompanyProfit:{id:null,devDam:null,companyPro:null,consCheckedProTotal:null},
    		retailDetailFjList:[],
    		
    		costDetailList: []			//批发交易结算明细 list
    	}
    },
    ready:function(){
    	var that = this;
     	if(MainFrameUtil.getParams("settleSchemeAddDialog").ym != undefined){
     		that.flag = true;
	     	var ym = MainFrameUtil.getParams("settleSchemeAddDialog").ym;
	     	that.ym = ym.substring(0,4)+"-"+ym.substring(4);
	     	
	     	that.formData.id = MainFrameUtil.getParams("settleSchemeAddDialog").id;
     	}else{
	    	//没有ym传入时，默认是本月
	    	var date = new Date();
	    	that.ym = date.getFullYear() + "-" + (date.getMonth() > 8 ? (date.getMonth()+1) : ("0" + (date.getMonth()+1)));
     	}
    	
      	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"settleSchemeAddDialog");
       	ComponentUtil.getFormFields("purchase.settlement.smpurchaseschemedetail",this);
       	$("#companyProId").focus(function(){
       		//有正在编辑的行，且不是本行
			if(editIndex != undefined){
				that.endEditing();
				//计算售电公司利润
				that.caculateCompanyPro();
			}
       	})
    },
    watch: {
    	"ym": function(value){
    		//this.serviceAmtTotal = 0;		//暂时不用计算，勿删
	    	//初始化form表单数据，并重新加载“批发市场购电支出明细”表格
    		this.initFormData();
    		//初始化零售市场表格
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
	       		url: '${Config.baseURL}/cloud-purchase-web/settlementControllerFj/getFormAndCostDataByIdOrYm?ym=' + that.ym,
	            method:'get',
	            async: false,
	            success:function(data){
	               if(data.flag ){
            	   		that.formData = data.data;
// 	               		if(that.formData.actualTotalPq!=null && that.formData.totalPurchasePq != 0){
// 	               			//偏差电量比例=（实际用电量-总购电量）/总购电量
// 	               			that.formData.devPqProp = (((parseFloat(that.formData.actualTotalPq)-parseFloat(that.formData.totalPurchasePq))/parseFloat(that.formData.totalPurchasePq))*100).toFixed(2);
// 	               		}
	                	
	                	//批发市场购电支出明细 列表
	                 	$("#costGrid").datagrid({
	                 		data: data.data.costDetailList,
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
	                	
	                	//主要是初始化时去计算批发交易电价
	                	that.changeDevDam(false);
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
           		url: basePath+"cloud-purchase-web/settlementControllerFj/getRetailDetailByIdOrYm?ym=" + that.ym,
           		method: 'get',
                loadMsg:"请稍后",
                width:'100%',
                height:"100%",
                checkOnSelect : true,
                singleSelect:true,
                rownumbers:true,
                scrollbarSize: 10,
//                 fit:true,
//                 fitColumns:true,
    		    frozenColumns:[[
    				{field:'consName',title:'用户名称',width:140,align:'center'},
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
    					/* editor:{type:'numberbox',options:{precision:2}}, */
//     					formatter:function(value,index,row){
//     						  if(value){
//     							  return parseFloat(value);
//     						  }
//     					}
    				},
    				{field:'consCheckedPro',title:'用户偏差考核</br>费用（元）',width:100,align:'center',		//s_m_consumer_profit表的字段
    					editor:{type:'numberbox',options:{precision:2}},
    					formatter:function(value,index,row){
    						  if(value){
    							  return parseFloat(value);
    						  }
    					}
    				},
    				{field:'agentProp',title:'代理比例（%）',width:100,align:'center',		//s_m_consumer_profit表的字段
    					editor:{type:'numberbox',options:{precision:2}}
    				},
    				{field:'agentAmt',title:'代理费用</br>（元）',width:100,align:'center',		//s_m_consumer_profit表的字段
    					/* editor:{type:'numberbox',options:{precision:2}}, */
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
        			//有正在编辑的行，且不是本行
        			if(editIndex != undefined && editIndex != index){
        				that.endEditing();
        				//计算售电公司利润
        				that.caculateCompanyPro();
    				}
        		},
        		onDblClickCell: function(index,field,value){
        			if (editIndex != index){
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
                	//重新计算  每个零售市场用户的电费
        			for(var i=0;i<oldRowDatas.length;i++){
                		/* var oldRowData = oldRowDatas[i];
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
                		} */
                		var oldRowData = oldRowDatas[i];
                		//计算交易电费
                		var deliveryPrc = oldRowData.deliveryPrc;
                		var deliveryPq = oldRowData.deliveryPq;
                		if(deliveryPrc != null && deliveryPrc != '' && deliveryPq != null && deliveryPq !=''){
                			var cost = parseFloat(deliveryPrc) * parseFloat(deliveryPq)
                			oldRowData.deliveryCost = cost.toFixed(2);
                		}
                	}
       				return data;
        		},
        		onLoadSuccess: function(data){
//         			for(var i in data.rows){
        				//暂时不用计算，勿删
        				//that.serviceAmtTotal += parseFloat((data.rows[i].serviceAmt != null && data.rows[i].serviceAmt != '') ? data.rows[i].serviceAmt : 0);
//         			}
        			//that.saveData.smCompanyProfit.companyPro += parseFloat(that.serviceAmtTotal);		//暂时不用计算，勿删
        			
        			that.endEditing();
        		}
            });
    	},
        //表格结束编辑
        endEditing: function(){
        	//改变旧行的数据
        	if(editIndex != undefined){
	        	var oldConsCheckedPro = $('#userPqGrid').datagrid('getRows')[editIndex].consCheckedPro;		//用户偏差考核费用
	        	var oldServiceAmt = $('#userPqGrid').datagrid('getRows')[editIndex].serviceAmt;				//服务费
	        	
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
            	/* var consCheckedPro = $('#userPqGrid').datagrid('getRows')[editIndex].consCheckedPro;
            	var consCheckedProTotal = (this.formData.consCheckedProTotal == null || this.formData.consCheckedProTotal == '') ? 0 : this.formData.consCheckedProTotal;
            	consCheckedProTotal = parseFloat(consCheckedProTotal) - parseFloat(((oldConsCheckedPro == null || oldConsCheckedPro == '') ? 0 : oldConsCheckedPro)) + parseFloat(((consCheckedPro == null || consCheckedPro == '') ? 0 : consCheckedPro));
            	this.formData.consCheckedProTotal = consCheckedProTotal.toFixed(2); */

            	//计算用户总服务费 //暂时不用计算，勿删
            	/* var serviceAmt = $('#userPqGrid').datagrid('getRows')[editIndex].serviceAmt;
            	this.serviceAmtTotal = parseFloat(this.serviceAmtTotal) - parseFloat((oldServiceAmt != null && oldServiceAmt != '') ? oldServiceAmt : 0) + parseFloat((serviceAmt != null && serviceAmt != '') ? serviceAmt : 0);
            	
            	//计算售电公司总利润（服务费之和-售电公司偏差费用+用户偏差考核费用）
            	var devDam = (this.saveData.smCompanyProfit.devDam != null && this.saveData.smCompanyProfit.devDam != '') ? this.saveData.smCompanyProfit.devDam : 0;
    			var consCheckedProTotal = (this.saveData.smCompanyProfit.consCheckedProTotal != null && this.saveData.smCompanyProfit.consCheckedProTotal != '') ? this.saveData.smCompanyProfit.consCheckedProTotal : 0;
            	this.saveData.smCompanyProfit.companyPro = parseFloat(this.serviceAmtTotal) - parseFloat(devDam) + parseFloat(consCheckedProTotal); */
            	$('#userPqGrid').datagrid('refreshRow', editIndex);
			}
        	
        	//计算总的 零售交易电费、用户偏差考核费用
        	var rows = $('#userPqGrid').datagrid('getRows');
        	//零售交易总电费
			var incomeTotalAmt = 0;
			var consCheckedProTotal = 0;
			for(var i in rows){
				//计算 零售交易总电费
				incomeTotalAmt += parseFloat((rows[i].deliveryCost != null && rows[i].deliveryCost != '') ? rows[i].deliveryCost : 0);
				//计算 用户偏差考核费用
				consCheckedProTotal += parseFloat((rows[i].consCheckedPro != null && rows[i].consCheckedPro != '') ? rows[i].consCheckedPro : 0);
			}
			this.formData.incomeTotalAmt = incomeTotalAmt.toFixed(2);
			this.formData.consCheckedProTotal = consCheckedProTotal.toFixed(2);
			
			
			//计算零售交易平均电价（零售交易平均电价=（零售交易电费+用户偏差费用）/结算电量，如果偏差费用为空取0）
			this.incomePrc = 0;
        	if(this.formData.actualTotalPq != null && this.formData.actualTotalPq != 0 && this.formData.actualTotalPq != ""){
        		this.incomePrc = (parseFloat(this.formData.incomeTotalAmt) + parseFloat(this.formData.consCheckedProTotal)) 
        										/ parseFloat(this.formData.actualTotalPq);
        	}
       		this.formData.incomePrc = this.incomePrc.toFixed(2);
        	
        	//计算每个用户的服务费（serviceAmt）、代理费用（agentAmt）
        	this.caculateConsAmt();
			
			
//             editIndex = undefined;
 		},
 		//计算每个用户的服务费（serviceAmt）、代理费用（agentAmt）
 		caculateConsAmt: function(){
 			var rows = $('#userPqGrid').datagrid('getRows');
 			if(this.formData.costPrc == null || this.formData.costPrc == ''){
 				return;
 			}
 			var costPrc = this.costPrc;				//批发交易电费
 			var incomePrc = this.incomePrc;		//零售交易电费
 			
			for(var i in rows){
				var row = rows[i];
				if(row.deliveryPq != null && row.deliveryPq !=""){
					//服务费=（零售交易均价-批发交易均价）*结算电量，如果小于0，则等于0
					row.serviceAmt = ((incomePrc - costPrc) * parseFloat(row.deliveryPq)).toFixed(2);
					if(parseFloat(row.serviceAmt) < 0){
						row.serviceAmt = '0';
					}
					//代理费用 = 服务费 * 代理比例
					if(row.agentProp != null && row.agentProp != ""){
						row.agentAmt = (parseFloat(row.serviceAmt) * parseFloat(row.agentProp) / 100).toFixed(2);
					}
					$('#userPqGrid').datagrid('refreshRow',i);
				}
			}
 			//上面的refreshRow会导致行号错误，所以在此重新设置行号
 			$(".userPqGrid .datagrid-cell-rownumber").each(function(index,element){
 			    $(this).text(index + 1);
 			});
 			editIndex = undefined;
		},
 		//计算售电公司利润 （售电公司利润=零售交易电费+用户偏差考核费用-批发交易电费-售电公司偏差费用）
 		caculateCompanyPro: function(){
 			var costAmt = 0;				//批发交易电费
 			var incomeTotalAmt = 0;			//零售交易电费
 			var devDam = 0;					//偏差违约金(售电公司偏差考核)
    		var consCheckedProTotal = 0;	//用户总偏差考核费用
    		if(this.formData.costAmt != null && this.formData.costAmt != ""){
    			costAmt = parseFloat(this.formData.costAmt);
        	}
    		if(this.formData.incomeTotalAmt != null && this.formData.incomeTotalAmt != ""){
    			incomeTotalAmt = parseFloat(this.formData.incomeTotalAmt);
        	}
    		if(this.formData.devDam != null && this.formData.devDam != ""){
    			devDam = parseFloat(this.formData.devDam);
        	}
    		if(this.formData.consCheckedProTotal != null && this.formData.consCheckedProTotal != ""){
    			consCheckedProTotal = parseFloat(this.formData.consCheckedProTotal);
        	}
    		//售电公司总利润 
    		this.formData.companyPro = (incomeTotalAmt + consCheckedProTotal - costAmt - devDam).toFixed(2);
		},
		//改变 售电公司偏差费用 ，触发更改 批发交易平均电价，然后更改零售交易结算明细表中的所有服务费和代理费用
		//flag: 是否级联去计算  零售交易结算明细表中的  所有服务费和代理费用； true: 计算
		changeDevDam: function(flag){
			this.costPrc = 0;
			//计算批发交易平均电价（（批发交易电费+售电公司偏差费用）/结算电量，如果偏差费用为空取0）
        	if(this.formData.actualTotalPq != null && this.formData.actualTotalPq != 0 && this.formData.actualTotalPq != ""){
        		this.costPrc = (parseFloat(this.formData.costAmt) + parseFloat((this.formData.devDam == "" || this.formData.devDam == null) ? 0 : this.formData.devDam)) 
        					/ parseFloat(this.formData.actualTotalPq);
        	}
       		this.formData.costPrc = this.costPrc.toFixed(2);
			if(flag){
				//更改零售交易结算明细表中的所有服务费和代理费用
				this.caculateConsAmt();
			}
		},
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"保存",id:"saveButton", type:"primary",handler:this.saveValidate},
                           {text:"取消",type:"default",handler:function(){MainFrameUtil.closeDialog("settleSchemeAddDialog")}}];
            return buttons;
        },
    	saveValidate : function(){
    	   if(this.ym==""||this.ym==null){
    		   MainFrameUtil.alert({title:"提示",body:"请选择年月"}); 
    		   return ;
    	   }
    	   if(editIndex != undefined){
    		   	this.endEditing();
				//计算售电公司利润
				this.caculateCompanyPro();
			}
    	   this.saveInfo();
    	},
    	saveInfo : function(){	
    		var that = this;
    		$(window.parent.document).find(".dialog-buttons button").each(function(index,element){
 			    $(this).attr("disabled","disabled");
 			});
//     		ProgressUtil.wait();  // 进度条开始
 			$.messager.progress();

	   		//给结算方案对象赋值
	   		that.saveData.settleId = that.formData.id;
        	that.saveData.ym = that.formData.ym;
        	
        	//给批发市场明细列表赋值
        	that.saveData.costDetailList = that.formData.costDetailList;
        	
    	    that.saveData.smPurchaseScheme.settleId= that.formData.id;
	   		that.saveData.smPurchaseScheme.consNum = that.formData.consNum;
	   		that.saveData.smPurchaseScheme.compProfit = that.formData.companyPro;
	   		that.saveData.smPurchaseScheme.deliveryPq = that.formData.actualTotalPq;
	   		that.saveData.smPurchaseScheme.totalProxyPq = that.formData.totalProxyPq;
	   		that.saveData.smPurchaseScheme.totalPurchasePq = that.formData.totalPurchasePq;
	   		
	   		that.saveData.smCompanyProfit.id = that.formData.companyProfitId;
	   		that.saveData.smCompanyProfit.devDam = that.formData.devDam;		//偏差违约金(售电公司偏差考核)
	   		that.saveData.smCompanyProfit.companyPro = that.formData.companyPro;	//总利润
	   		that.saveData.smCompanyProfit.consCheckedProTotal = that.formData.consCheckedProTotal;	//用户偏差考核总费用(元)
       		
	   		that.saveData.smCompanyProfit.proxyPq = that.formData.totalProxyPq;			//总代理电量
	   		that.saveData.smCompanyProfit.purTotalPq = that.formData.totalPurchasePq;	//总购电量
	   		that.saveData.smCompanyProfit.delTotalPq = that.formData.actualTotalPq;	//总交割电量（总结算电量）= 总实际用电量
	   		that.saveData.smCompanyProfit.devPq = that.formData.devPq;		//偏差电量
	   		that.saveData.smCompanyProfit.devPqProp = that.formData.devPqProp;		//偏差电量比例
	   		that.saveData.smCompanyProfit.companyCostTotal = that.formData.costAmt;	//批发市场购电支出 = 批发交易电费
	   		that.saveData.smCompanyProfit.consAmtTotal = that.formData.incomeTotalAmt;	//零售市场购电收入 = 零售交易电费
	   		
	   		
    	   //用户月度电量分配
    	   var rows = $("#userPqGrid").datagrid("getRows");
    	   that.saveData.retailDetailFjList = rows;
    	   $.ajax({
				url:basePath+"cloud-purchase-web/settlementControllerFj/saveSmPurchaseSchemeVo",
				type:'post',
				data:$.toJSON(that.saveData),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
// 	 				ProgressUtil.close(); //进度条结束
					//关闭进度条
               		$.messager.progress('close');

	 				$(window.parent.document).find(".dialog-buttons button").each(function(index,element){
	 	 			    $(this).removeAttr("disabled");
	 	 			});
	 				if(data.flag){
                		MainFrameUtil.alert({title:"成功提示",body:data.msg});
                		that.formData.id = data.data.settleId;
//                 		MainFrameUtil.setParams({settleId: data.data.settleId},"settleSchemeAddDialog");
//                 		MainFrameUtil.closeDialog("settleSchemeAddDialog");
                	}else{
                		MainFrameUtil.alert({title:"警告",body:data.msg});
                	}
	 			},
    		    error:function(){
    		    	ProgressUtil.close(); //进度条结束
	 				$(window.parent.document).find(".dialog-buttons button").each(function(index,element){
	 	 			    $(this).removeAttr("disabled");
	 	 			});
    		    	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"}); 
    		    }
			})
    	}
    }
});
</script>
</body>
</html>
