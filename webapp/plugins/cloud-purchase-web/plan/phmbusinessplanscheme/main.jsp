<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
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
<body id="mainBody" v-cloak>
<mk-panel title="经营利润分析" >
	<mk-form-panel title="" num="2">
		<mk-form-row>
			<!-- 计划名称 -->
	        <mk-form-col :label="formFields.planName.label" colspan="2">
	        	<su-textbox :data.sync="planName"   disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 预测合同总电量 -->
	        <mk-form-col :label="formFields.agrePqForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.agrePq" :addon="{'show':true,'rightcontent':'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 预测合同总销售额 -->
	        <mk-form-col :label="formFields.agreAmtForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.agreAmt" :addon="{'show':true,'rightcontent':'&nbsp;&nbsp;&nbsp;元'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 预测平均代理单价 -->
	        <mk-form-col :label="formFields.avgPrcForm.label">
	        	<su-textbox :data.sync="params.phmBusinessPlanScheme.avgPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
			<!-- 利润指标 -->
			<mk-form-col :label="formFields.profitNorm.label">
	        	<su-textbox :data.sync="saveParams.profitNorm" :addon="{'show':true,'rightcontent':'万元'}" ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>
<mk-panel title="长协电价" line="true">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="eye" v-on:click="" :disabled.sync="btnFlag">电价预测</su-button>
	</div>
	<div style="height: 400px">
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
				<mk-form-panel title="电量占比" label_width="100px">
					<mk-form-row >
						<!-- 长协电量占比 -->
				        <mk-form-col :label="formFields.lcPropForm.label" colspan="3">
				        	<su-textbox :data.sync="saveParams.lcProp"  :addon="{'show':true,'rightcontent':'%'}" ></su-textbox>
				        </mk-form-col>
					</mk-form-row>
				</mk-form-panel>
				<mk-form-panel title="长协经验电价（元/兆瓦时)" label_width="100px">
					<mk-form-row>
						<!-- 长协经验电价 -->
				        <mk-form-col :label="formFields.lcExperiencePrc.label" colspan="3">
				        	<su-textbox :data.sync="saveParams.lcExperiencePrc"  :addon="{'show':true,'rightcontent':'元/兆瓦时'}" ></su-textbox>
				        </mk-form-col>
					</mk-form-row>
				</mk-form-panel>
			</mk-panel>
		</mk-left-right>
	</div>
</mk-panel>
<mk-panel title="竞价电价" >
	<mk-tabpanel height="420px" auto_fit="true"  selected="0" >
		<mk-tabpage index=0 header="成交平均价" href="${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/avgprice?year=${param.year}" iframe="false"></mk-tabpage>
	 	<mk-tabpage index=1 header="不同报价区间电量占比" href="${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/pqhistory?year=${param.year}" iframe="false"></mk-tabpage>
	 	<mk-tabpage index=2 header="报价成交情况" href="${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/pricehistory?year=${param.year}" iframe="false"></mk-tabpage>
	</mk-tabpanel>
	<mk-left-right  left_width="50%"  height="200px">
		<mk-form-panel slot="left" title="预测电价（元/兆瓦时)" >
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
		<div slot="right" class="row row-margin-bottom">
			<mk-form-panel title="电量占比" >
				<mk-form-row >
					<!-- 竞价电量占比 -->
			        <mk-form-col :label="formFields.bidPropForm.label" colspan="3">
			        	<su-textbox :data.sync="saveParams.bidProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
			        </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
			<mk-form-panel title="经验平均电价（元/兆瓦时)" >
				<mk-form-row>
					<!-- 竞价经验平均电价 -->
			        <mk-form-col :label="formFields.bidExperiencePrc.label" colspan="3">
			        	<su-textbox :data.sync="saveParams.bidExperiencePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</div>
	</mk-left-right>
</mk-panel>
<mk-panel title="分析结果" line="true">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="calculator" v-on:click="calc">计算</su-button>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="save">保存</su-button>
	</div>
	<mk-form-panel title="" label_width = "100px">
		<mk-form-row>
			<!-- 方案名称 -->
	        <mk-form-col :label="formFields.schemeName.label" colspan="3">
	        	<su-textbox :data.sync="saveParams.schemeName"  ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 长协电量 -->
	        <mk-form-col :label="formFields.lcPqForm.label">
	        	<su-textbox :data.sync="saveParams.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 长协电价-->
	        <mk-form-col :label="formFields.lcPrc.label">
	        	<su-textbox :data.sync="saveParams.lcPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 长协电量占比 -->
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
	        <mk-form-col :label="formFields.bidPrc.label" >
	        	<su-textbox :data.sync="saveParams.bidPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 竞价电量占比 -->
	        <mk-form-col :label="formFields.bidPropForm.label">
	        	<su-textbox :data.sync="saveParams.bidProp" :addon="{'show':true,'rightcontent':'%'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
	        <!-- 利润 -->
	        <mk-form-col :label="formFields.profitForm.label" >
	        	<su-textbox :data.sync="saveParams.profit" :addon="{'show':true,'rightcontent':'&nbsp;&nbsp;&nbsp;万元'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 差值利润 -->
	        <mk-form-col :label="formFields.dvalueProfitForm.label" >
	        	<su-textbox :data.sync="saveParams.dvalueProfit" :addon="{'show':true,'rightcontent':'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;万元'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
</mk-panel>
<mk-panel title="经营利润分析方案" height="400px"  line="true">
    <div id="mainGrid" ></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var mainVue = new Vue({
	el: '#mainBody',
	data: {
		formFields:{},
		btnFlag:true,//控制电价预测是否能使用
		planName:'',
		planId:'',
		year:'',
		params:{
			phmBusinessPlanScheme:{agrePq:'',agreAmt:'',avgPrc:''},
			phmForecastPrc:{ lcUpperPrc:'',lcMiddlePrc:'',lcLowerPrc:'',bidUpperPrc:'',bidMiddlePrc:'',bidLowerPrc:'' }
		},
		saveParams:{
			lcPq:'',lcPrc:'',lcProp:'',bidPq:'',bidPrc:'',bidProp:'',profit:'',lcExperiencePrc:'',dvalueProfit:'',profitNorm:''
		}
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('edit');
		var year = obj.year;
		me.planName = obj.planName;
		me.year = year;
		var planId = obj.planId;
		me.planId = planId;
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.phmbusinessplanscheme",this);
		
		ComponentUtil.buildGrid("purchase.plan.phmbusinessplanscheme",$("#mainGrid"),{ 
			url:basePath + 'cloud-purchase-web/phmBusinessPlanSchemeController/page',
			queryParams:{phmBusinessPlanScheme:{planId:planId}},
		    height:"100%",
		    method:'post',
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		$.ajax({
			url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/getInitScheme",
			type:"get",
			data:{planId:planId,year:year},
			success:function(data){
				me.params= data.data;
				if(data.data.phmForecastPrc ==null){
					me.btnFlag = false;
				}
			}
		})
		
		ComponentUtil.buildGrid("purchase.plan.lcprc",$("#lcPrcGrid"),{ 
			url:basePath + 'cloud-purchase-web/phfLcDealInfoController/getPhfLcDealInfoByYear/'+year,
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
		add:function(){
			this.saveParams={
				lcPq:'',lcPrc:'',lcProp:'',bidPq:'',bidPrc:'',bidProp:'',profit:'',lcExperiencePrc:'',dvalueProfit:''
			}
		},
		calc:function(){
			var me = this;
			if(this.isNull(this.saveParams.profitNorm)){
				MainFrameUtil.alert({title:"警告",body:"请输入利润指标！"});
				return ;
			}
			if(this.isNull(this.saveParams.lcExperiencePrc)){
				MainFrameUtil.alert({title:"警告",body:"请输入长协经验电价！"});
				return ;
			}
			if(this.isNull(this.saveParams.bidExperiencePrc)){
				MainFrameUtil.alert({title:"警告",body:"请输入竞价经验电价！"});
				return ;
			}
			if(this.isNull(this.saveParams.lcProp)){
				MainFrameUtil.alert({title:"警告",body:"请输入长协电量占比！"});
				return ;
			}
			if(!this.checkProp(this.saveParams.lcProp)){
				MainFrameUtil.alert({title:"警告",body:"请输入正确的长协电量占比！"});
				return ;
			}
			this.saveParams.agrePq = this.params.phmBusinessPlanScheme.agrePq;
			this.saveParams.agreAmt = this.params.phmBusinessPlanScheme.agreAmt;
			this.saveParams.avgPrc = this.params.phmBusinessPlanScheme.avgPrc;
			this.saveParams.lcPrc = this.saveParams.lcExperiencePrc;
			this.saveParams.bidPrc =this.saveParams.bidExperiencePrc;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/calc",
				type : 'post',
   	   			data : $.toJSON(me.saveParams),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
   	   					me.saveParams = data.data;
   	   				}else{
   	   					MainFrameUtil.alert({title:"错误",body:data.msg});
   	   				}
   	   			}
			})
		},
		save:function(){
			var me = this;
			if(this.isNull(this.saveParams.lcPq)){
				MainFrameUtil.alert({title:"警告",body:"请先计算后再保存！"});
				return ;
			}
			this.saveParams.planId = this.planId;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/save",
				type:"post",
				data : $.toJSON({year:me.year,phmBusinessPlanScheme:me.saveParams}),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({title:"提示",body:data.msg});
  	   					me.add();
   	   					$("#mainGrid").datagrid("reload");
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
		isNull:function(value){
			if(typeof(value)==="undefined"||value==null||value==''){
				return true;
			}else{
				return false;
			}
		},
		planNameFormatter:function(value,row,index){
			return "<a onclick=\"mainVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/plan/phmbusinessplanscheme/schemedetail',
				iframe:true,
				modal:true,
				width:800,
				height:500
			});
		},
	},
	watch:{
		'saveParams.lcProp':function(value){
			if(this.checkProp(value)){
				this.saveParams.bidProp = 100 - value;
			}
		}
	}
}); 
</script>
</body>
</html>