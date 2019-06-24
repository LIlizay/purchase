<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
 <title>购售电交易-年度购电计划合同电量分配</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<style type="text/css">
.inputDiv{
	height: 80%;
	width:90%;
	margin: 10px 10px 10px 15px
}
</style>
</head>
<body id="schemeBody" v-cloak>
<mk-panel title="双边电量分解测算方案制定" >
	<mk-form-panel title="">
		<mk-form-row>
			<!-- 合同总电量 -->
	        <mk-form-col :label="formFields.agrePqForm.label">
	        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 合同总销售额 -->
	        <mk-form-col :label="formFields.agreAmt.label">
	        	<su-textbox :data.sync="params.agreAmt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 平均代理单价 -->
	        <mk-form-col :label="formFields.avgPrc.label">
	        	<su-textbox :data.sync="params.avgPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 购电成本单价 -->
	        <mk-form-col :label="formFields.costPrc.label">
	        	<su-textbox :data.sync="saveParams.costPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 年度利润指标 -->
	        <mk-form-col :label="formFields.profitIndexForm.label" required_icon="true">
	        	<su-textbox :data.sync="saveParams.profitIndex" :addon="{'show':true,'rightcontent':'万元'}"></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>
<mk-panel title="双边电价" line="true">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="eye" v-on:click="getForest" :disabled.sync="btnFlag">电价预测</su-button>
	</div>
	<div style="height: 300px">
		<mk-left-right  left_width="65%"  height="100%">
			<mk-panel header="false" line="true" slot="left" height="99%">
				<div id="lcPrcGrid"></div>
			</mk-panel>
			<mk-panel header="false" line="true" slot="right" height="99%">
				<mk-form-panel title="预测电价（元/兆瓦时)" label_width="80px">
					<mk-form-row>
						<!-- 高风险 -->
				        <mk-form-col :label="formFields.high.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.lcUpperPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
			        </mk-form-row>
				    <mk-form-row>
				        <!-- 中风险 -->
				        <mk-form-col :label="formFields.middle.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.lcMiddlePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
				    </mk-form-row>
			        <mk-form-row>
				        <!-- 低风险 -->
				        <mk-form-col :label="formFields.low.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.lcLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
					</mk-form-row>
				</mk-form-panel>
				<mk-form-panel title="双边经验电价（元/兆瓦时)" >
					<mk-form-row style="border-left:1px solid #e1e1e1">
						<div class="inputDiv">
							<su-textbox :data.sync="saveParams.lcExperiencePrc"   :addon="{'show':true,'rightcontent':'元/兆瓦时'}" >
							</su-textbox>
						</div>
					</mk-form-row>
				</mk-form-panel>
			</mk-panel>
		</mk-left-right>
	</div>
</mk-panel>
<mk-panel title="竞价电价" >
	<div style="height: 480px">
		<mk-left-right  left_width="65%"  height="100%">
			<mk-panel header="false" line="true" slot="left" height="99%">
				<mk-tabpanel height="100%" auto_fit="true" selected="0">
					<mk-tabpage index=0 header="电厂" href="${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/elec" iframe="false"></mk-tabpage>
				 	<mk-tabpage index=1 header="用户" href="${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/cons" iframe="false"></mk-tabpage>
				</mk-tabpanel>
			</mk-panel>
			<mk-panel header="false" line="true" slot="right" height="99%">
				<mk-form-panel title="预测电价（元/兆瓦时)" label_width="80px">
					<mk-form-row>
						<!-- 高风险 -->
				        <mk-form-col :label="formFields.high.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.bidUpperPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
			        </mk-form-row>
				    <mk-form-row>
				        <!-- 中风险 -->
				        <mk-form-col :label="formFields.middle.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.bidMiddlePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
				    </mk-form-row>
			        <mk-form-row>
				        <!-- 低风险 -->
				        <mk-form-col :label="formFields.low.label" colspan="3">
				        	<su-textbox :data.sync="params.phmForecastPrc.bidLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
				        </mk-form-col>
					</mk-form-row>
				</mk-form-panel>
				<mk-form-panel title="竞价经验平均电价（元/兆瓦时)" >
					<mk-form-row style="border-left:1px solid #e1e1e1">
						<div class="inputDiv">
							<su-textbox :data.sync="saveParams.bidExperiencePrc" class="inputDiv"  :addon="{'show':true,'rightcontent':'元/兆瓦时'}" 
								></su-textbox>
						</div>
					</mk-form-row>
				</mk-form-panel>
			</mk-panel>
		</mk-left-right>
	</div>
</mk-panel>

<mk-panel title="系统分析" line="true">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="calculator" v-on:click="syscalc">系统计算</su-button>
	</div>
	<mk-form-panel title="" label_width = "110px">
		<mk-form-row>
			<!-- 合同总电量 -->
	        <mk-form-col :label="formFields.agrePqForm.label">
	        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 合同总销售额 -->
	        <mk-form-col :label="formFields.agreAmt.label">
	        	<su-textbox :data.sync="params.agreAmt" :addon="{'show':true,'rightcontent':'元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 年度利润指标 -->
	        <mk-form-col :label="formFields.profitIndexForm.label">
	        	<su-textbox :data.sync="saveParams.profitIndex" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 利润最小值 -->
	        <mk-form-col :label="formFields.minProfit.label">
	        	<su-textbox :data.sync="sysCalc.minProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 利润最大值 -->
	        <mk-form-col :label="formFields.maxProfit.label">
	        	<su-textbox :data.sync="sysCalc.maxProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row v-show = "flag1">
			 <!-- 附加利润最小值 -->
	        <mk-form-col :label="formFields.minAddProfit.label">
	        	<su-textbox :data.sync="sysCalc.minAddProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	         <!-- 附加利润最大值 -->
	        <mk-form-col :label="formFields.maxAddProfit.label">
	        	<su-textbox :data.sync="sysCalc.maxAddProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row v-show = "flag2">
			 <!-- 差值利润最小值 -->
	        <mk-form-col :label="formFields.minDifferenceProfit.label">
	        	<su-textbox :data.sync="sysCalc.minDifferenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	         <!-- 差值利润最大值 -->
	        <mk-form-col :label="formFields.maxDifferenceProfit.label">
	        	<su-textbox :data.sync="sysCalc.maxDifferenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价差值电量 -->
	        <mk-form-col :label="formFields.bidDifferencePq.label" >
	        	<su-textbox :data.sync="sysCalc.bidDifferencePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 双边电量 -->
	        <mk-form-col :label="formFields.lcPqForm.label">
	        	<su-textbox :data.sync="sysCalc.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	         <!-- 双边电价-->
	        <mk-form-col :label="formFields.lcPrcForm.label">
	        	<su-textbox :data.sync="sysCalc.lcPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 双边电量占比 -->
	        <mk-form-col :label="formFields.lcPropForm.label">
	        	<su-textbox :data.sync="sysCalc.lcProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 竞价电量 -->
	        <mk-form-col :label="formFields.bidPqForm.label">
	        	<su-textbox :data.sync="sysCalc.bidPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价电价 -->
	        <mk-form-col :label="formFields.bidPrcForm.label" >
	        	<su-textbox :data.sync="sysCalc.bidPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	         <!-- 竞价电量占比 -->
	        <mk-form-col :label="formFields.bidPropForm.label">
	        	<su-textbox :data.sync="sysCalc.bidProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>

<mk-panel title="方案制定" line="true">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="calculator" v-on:click="calc">计算</su-button>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="save">保存</su-button>
	</div>
	<mk-form-panel title="" label_width = "110px">
		<mk-form-row>
			<!-- 方案名称 -->
	        <mk-form-col :label="formFields.schemeName.label" colspan="3">
	        	<su-textbox :data.sync="saveParams.schemeName"  ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row v-show="flag2" >
			<div class = "col-xs-12 mk-form-column" style="padding: 13px">
				<su-radio :data.sync="radioSelect" label="差值利润调整方案" value="0" ></su-radio>
            	<su-radio :data.sync="radioSelect" label="双边电量调整方案" value="1" ></su-radio>
			</div>
		</mk-form-row>
		<mk-form-row>
			<!-- 附加利润 -->
	        <mk-form-col :label="formFields.addProfitForm.label" v-show="flag1" required_icon="true">
	        	<su-textbox :data.sync="saveParams.addProfit" :addon="{'show':true,'rightcontent':'万元'}"></su-textbox>
	        </mk-form-col>
			<!-- 差值利润 -->
	        <mk-form-col :label="formFields.differenceProfitForm.label" v-show="flag2&&radioSelect==0" required_icon="true" >
	        	<su-textbox :data.sync="saveParams.differenceProfit" :addon="{'show':true,'rightcontent':'万元'}" ></su-textbox>
	        </mk-form-col>
	        <!-- 利润 -->
	        <mk-form-col :label="formFields.profit.label" v-show="flag2&&radioSelect==1">
	        	<su-textbox :data.sync="profit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
			<!-- 年度利润指标 -->
	        <mk-form-col :label="formFields.profitIndexForm.label">
	        	<su-textbox :data.sync="saveParams.profitIndex" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价差值电量 -->
	        <mk-form-col :label="formFields.bidDifferencePq.label" v-show="flag2&&radioSelect==0">
	        	<su-textbox :data.sync="saveParams.bidDifferencePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 双边电量 -->
	        <mk-form-col :label="formFields.lcPqForm.label">
	        	<su-textbox :data.sync="saveParams.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" :disabled.sync="flag4"></su-textbox>
	        </mk-form-col>
	        <!-- 双边电价-->
	        <mk-form-col :label="formFields.lcPrcForm.label">
	        	<su-textbox :data.sync="saveParams.lcExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 双边电量占比 -->
	        <mk-form-col :label="formFields.lcPropForm.label">
	        	<su-textbox :data.sync="saveParams.lcProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 竞价电量 -->
	        <mk-form-col :label="formFields.bidPqForm.label">
	        	<su-textbox :data.sync="saveParams.bidPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价电价 -->
	        <mk-form-col :label="formFields.bidPrcForm.label" >
	        	<su-textbox :data.sync="saveParams.bidExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价电量占比 -->
	        <mk-form-col :label="formFields.bidPropForm.label">
	        	<su-textbox :data.sync="saveParams.bidProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>
<mk-panel :title="planName" height="400px"  line="true">
    <div id="listGrid" ></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var schemeVue = new Vue({
	el: '#schemeBody',
	data: {
		formFields:{},
		radioSelect:"0",
		btnFlag:true,//控制电价预测是否能使用
		flag1:false,//控制附加利润显示
		flag2:false,//控制差值利润显示
		flag4:"true",//控制双边电量是否可编辑
		flag5:true,//判断是否进行了系统计算
		planName:'',
		id:'',
		year:'',
		sysCalc:{
			minProfit:'',maxProfit:'',minAddProfit:'',maxAddProfit:'',minDifferenceProfit:'',maxDifferenceProfit:'',lcPq:'',lcPrc:'',lcProp:'',
			bidPq:'',bidPrc:'',bidProp:'',bidDifferencePq:''
		},
	    params:{
	    	agrePq:'',agreAmt:'',avgPrc:'',
	    	phmForecastPrc:{
	    		lcUpperPrc:'',lcMiddlePrc:'',lcLowerPrc:'',bidUpperPrc:'',bidMiddlePrc:'',bidLowerPrc:''
	    	}
	    },
	    saveParams:{
	    	costPrc:'',lcExperiencePrc:'',bidExperiencePrc:'',schemeName:'',lcPq:'',lcPrc:'',lcProp:'',bidPq:'',bidPrc:'',bidProp:'',
	    	profitIndex:'',addProfit:'',differenceProfit:''
	    },
	    saveParams2:null,//保存切换差值利润时数据
	    profit:'' //双边电量整理方案的利润
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('scheme');
		this.id = obj.id;
		this.year = obj.year;
		this.planName = obj.planName+"分解测算方案";
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.scheme",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmPhPlanYearSchemeController/page',
			queryParams:{phmPhPlanYearScheme:{planId:this.id}},
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.scheme",this);
		this.initData(obj.year);
		ComponentUtil.buildGrid("purchase.plan.lcprc",$("#lcPrcGrid"),{ 
			url:basePath + 'cloud-purchase-web/phfLcDealInfoController/getPhfLcDealInfoByYear/'+obj.year,
		    height:"100%",
		    method:'get',
		    rownumbers: false,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
	},
	methods: {
		initData:function(year){
			var me = this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmPurchasePlanYearController/getInitScheme/"+year,
				type:"get",
				success:function(data){
					if(data.flag){
						me.params = data.data;
						if(data.data.phmForecastPrc ==null){
							me.btnFlag = false;
						}
					}else{
						MainFrameUtil.alert({title:"错误",body:data.msg});
					}
				}
			})
		},
		getForest:function(){
			var me = this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmForecastPrcController/getPhmForecastPrc/"+this.year,
				type:"get",
				success:function(data){
					if(data.flag){
						if(data.data==null){
							MainFrameUtil.alert({title:"错误",body:"获取预测电价失败！"});
						}else{
							MainFrameUtil.alert({title:"提示",body:"获取预测电价成功！"});
							me.params.phmForecastPrc = data.data;
						}
					}else{
						MainFrameUtil.alert({title:"错误",body:data.msg});
					}
				}
			})
		},
		syscalc:function(){
			var me = this;
			if(this.saveParams.profitIndex==''){
				MainFrameUtil.alert({title:"警告",body:"请输入年度利润指标！"});
				return;
			}
			if(this.saveParams.lcExperiencePrc==''){
				MainFrameUtil.alert({title:"警告",body:"请输入双边经验电价！"});
				return;
			}
			if(this.saveParams.bidExperiencePrc==''){
				MainFrameUtil.alert({title:"警告",body:"请输入竞价经验平均电价！"});
				return;
			}
			this.saveParams.agrePq = this.params.agrePq;
			this.saveParams.agreAmt = this.params.agreAmt;
			this.saveParams.avgPrc = this.params.avgPrc;
			this.saveParams.lcPrc = this.saveParams.lcExperiencePrc;
			this.saveParams.bidPrc = this.saveParams.bidExperiencePrc;
			//清空分析结果数据
			this.radioSelect = "0";
			this.saveParams2 = null;
			this.profit = null;
			me.reset();
			$.ajax({
				url:basePath+"cloud-purchase-web/phmPhPlanYearSchemeController/sysCalc",
				type : 'post',
   	   			data : $.toJSON(me.saveParams),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
   	   					me.sysCalc = data.data;
   	   					me.flag5 = false;
   	   					if(data.data.minProfit>me.saveParams.profitIndex){
   	   						me.flag1 = true;
   	   						me.flag2 = false;
   	   					}else if(data.data.maxProfit<me.saveParams.profitIndex){
	   						me.flag1 = false;
	   						me.flag2 = true;
   	   					}else{
	   	   					me.flag1 = false;
	   						me.flag2 = false;
	   						me.saveParams.lcPq = data.data.lcPq;
	   						me.saveParams.lcProp = data.data.lcProp;
	   						me.saveParams.bidPq = data.data.bidPq;
	   						me.saveParams.bidProp = data.data.bidProp;
   	   					}
   	   				}else{
   	   					MainFrameUtil.alert({title:"错误",body:data.msg});
   	   				}
   	   			}
			})
		},
		calc:function(){
			var calcFlag = false;//是否计算利润
			var me = this;
			if(me.flag5){
				MainFrameUtil.alert({title:"错误",body:"请先进行系统计算！"});
				return;
			}
			if(me.flag1){
				if(this.saveParams.addProfit==''){
					MainFrameUtil.alert({title:"错误",body:"请输入附加利润！"});
					return;
				}
				if(this.checkNumber(this.saveParams.addProfit)){
					MainFrameUtil.alert({title:"错误",body:"请输入正确 的数字！"});
					return;
				}
				var addProfit = parseFloat(this.saveParams.addProfit);
				if(addProfit<parseFloat(this.sysCalc.minAddProfit)||addProfit>parseFloat(this.sysCalc.maxAddProfit)){
					MainFrameUtil.alert({title:"错误",
						body:"附加利润范围必须在最小附加利润【"+this.sysCalc.minAddProfit+"】与最大附加利润【"+this.sysCalc.maxAddProfit+"】之间！"});
					return;
				}
			}
			if(me.flag2){
				if(this.radioSelect == "0"){
					if(this.saveParams.differenceProfit==''){
						MainFrameUtil.alert({title:"错误",body:"请输入差值利润！"});
						return;
					}
					if(this.checkNumber(this.saveParams.differenceProfit)){
						MainFrameUtil.alert({title:"错误",body:"请输入正确 的数字！"});
						return;
					}
					var differenceProfit = parseFloat(this.saveParams.differenceProfit);
					if(differenceProfit>parseFloat(this.sysCalc.minDifferenceProfit)){
						MainFrameUtil.alert({title:"错误",body:"差值利润必须小于差值利润最小值【"+this.sysCalc.minDifferenceProfit+"】！"})
						return;
					}
				}else{
					if(this.saveParams.lcPq==''){
						MainFrameUtil.alert({title:"错误",body:"请输入双边电量！"});
						return;
					}
					if(this.checkNumber(this.saveParams.lcPq)){
						MainFrameUtil.alert({title:"错误",body:"请输入正确的数字！"});
						return;
					}
					var lcPq = parseFloat(this.saveParams.lcPq);
					if(lcPq > parseFloat(this.saveParams.agrePq)){
						MainFrameUtil.alert({title:"错误",body:"双边电量必须小于合同电量【"+this.saveParams.agrePq+"】"});
						return;
					}
					calcFlag = true;
				}
			}
			$.ajax({
				url:basePath+"cloud-purchase-web/phmPhPlanYearSchemeController/calc",
				type : 'post',
   	   			data : $.toJSON(me.saveParams),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
   	   					me.saveParams = data.data;
   	   					if(calcFlag){
   	   						var sum = data.data.bidPrc*data.data.bidPq+data.data.lcPrc*data.data.lcPq;
   	   						me.profit = ((data.data.agreAmt - sum)/10000).toFixed(2);
   	   					}
   	   				}else{
   	   					MainFrameUtil.alert({title:"错误",body:data.msg});
   	   				}
   	   			}
			})
		},
		add:function(){
			this.saveParams = {
			    	costPrc:'',lcExperiencePrc:'',bidExperiencePrc:'',schemeName:'',lcPq:'',lcPrc:'',lcProp:'',bidPq:'',bidPrc:'',bidProp:'',lcFinalProp:'',
			    	bidFinalProp:'',profitIndex:''
		    };
			this.sysCalc = {
				minProfit:'',maxProfit:'',minAddProfit:'',maxAddProfit:'',minDifferenceProfit:'',maxDifferenceProfit:'',lcPq:'',lcPrc:'',lcProp:'',
				bidPq:'',bidPrc:'',bidProp:'',bidDifferencePq:''
			};
			this.saveParams2 = null;
			this.profit = null;
		},
		reset:function(){
			var list = ["costPrc","lcPq","lcProp","bidPq","bidProp","addProfit","differenceProfit","bidDifferencePq"]
			for(var i in list){
				this.saveParams[list[i]] = '';
			}
		},
		save:function(){
			var me = this;
			if(me.flag5){
				MainFrameUtil.alert({title:"警告",body:"请先进行系统计算！"});
				return;
			}
			if(me.saveParams.costPrc==''){
				MainFrameUtil.alert({title:"警告",body:"请先计算完成再保存！"});
				return ;
			}
			me.saveParams.id = null;
			me.saveParams.planId = me.id;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmPhPlanYearSchemeController",
				type : 'post',
   	   			data : $.toJSON({year:me.year,phmPhPlanYearScheme:me.saveParams}),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
   	   					$("#listGrid").datagrid("reload");
   	   					//me.add();
   	   				}else{
   	   					MainFrameUtil.alert({title:"错误",body:data.msg});
   	   				}
   	   			}
			})
		},
		checkProp:function(value){
			var regex =new RegExp("^(([0-9]+[\.]?[0-9]{1,2})|[1-9])*$");
			var flag=regex.test(value);  
			if(flag){
				var prop = parseFloat(value);
	 			if(prop>=0&&prop<=100){
	 				return true
	 			}
			}
			return false;
		},
		checkNumber:function(value){
			var regex =new RegExp("^(([0-9]+[\.]?[0-9]{1,2})|[1-9])*$");
			return !regex.test(value);
		},
		checkProp:function(value){
			var regex =new RegExp("^(([0-9]+[\.]?[0-9]{1,2})|[1-9])*$");
			var flag=regex.test(value);  
			if(flag){
				var prop = parseFloat(value);
	 			if(prop>=0&&prop<=100){
	 				return true
	 			}
			}
			return false;
		},
		planNameFormatter:function(value,row,index){
			return "<a onclick=\"schemeVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmpurchaseplanyear/schemedetail',
				iframe:true,
				modal:true,
				width:800,
				height:500
			});
		},
	},
	watch:{
		radioSelect:function(value){
			if(this.saveParams2 == null){
				this.saveParams2 = eval('(' + $.toJSON(this.saveParams) + ')');
				this.reset();
			}else{
				var params = this.saveParams;
				this.saveParams = this.saveParams2;
				this.saveParams2 = params;
			}
			if(value==0){
				this.flag4 = "true";
			}else{
				this.flag4 = false;
			}
		},
		'saveParams.profitIndex':function(value,oldVal){
			if(value!=oldVal){
				this.flag5 = true;
			}
		},
		'saveParams.lcExperiencePrc':function(value,oldVal){
			if(value!=oldVal){
				this.flag5 = true;
			}
		},
		'saveParams.bidExperiencePrc':function(value,oldVal){
			if(value!=oldVal){
				this.flag5 = true;
			}
		}
	}
}); 
</script>
</body>
</html>