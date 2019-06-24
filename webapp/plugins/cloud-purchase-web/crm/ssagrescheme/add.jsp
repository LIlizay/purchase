<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同管理-售电合同辅助新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
    .pos_btn{
    	margin:-35px 20px 5px 10px;
    }
</style>
</head>
<body id="schemeAddVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel title="合同收益模拟" label_width="140px">
			<mk-form-row>
				<!-- 用户名称 -->
				<mk-form-col :label="formFields.custName.label" :class="{'display-none':!formFields.custId.formHidden}" colspan="1" required_icon="true">
						<mk-selectbox :url="url" height="620" width="50%" v-ref:consinfo textfield="consName" propname="id" :propvalue.sync="consId" name="consName"></mk-selectbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="双边电量分成方式" label_width="140px">
			<mk-form-row>
				<!-- 分成方式 -->
				<mk-form-col :label="formFields.diviCodeName.label" :class="{'display-none':!formFields.diviCodeName.formHidden}" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.ssAgreScheme.diviCode"
						url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_divideWayCode&valueNotIn=06,07" name="diviCode"></su-select>
				</mk-form-col>
				<!-- 结算模式 -->
				<div id = "divimode" style="display:inline">
					<mk-form-col :label="formFields.settlementMode.label" :class="{'display-none':!formFields.settlementMode.formHidden}" required_icon="true">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.ssAgreScheme.settlementMode"
							url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01" name="settlementMode"></su-select>
					</mk-form-col>
				</div>
			</mk-form-row>
			<div id="divi01" :class="{'display-none':!divi01Flag}">
				<mk-form-row>
					<!-- 保底协议价 -->
					<mk-form-col class="agre" :label="formFields.agrePrc.label" :class="{'display-none':!formFields.agrePrc.formHidden}"required_icon="true">
						<su-textbox :data.sync="params.ssAgreScheme.agrePrc" type="number" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" name="agrePrc"></su-textbox>
					</mk-form-col>
				</mk-form-row>
			</div>
			<div id="divi02" :class="{'display-none':!divi02Flag}">
					<mk-form-row :class="{'display-none':!divi03Flag}">
						<div id="divi0201" :class="{'display-none':!divi0201Flag}">
							<!-- 分成电量比例 -->
				            <mk-form-col :label="formFields.pqSharingRatio.label" label-width="200px" required_icon="true"
										:class="{'display-none':!formFields.pqSharingRatio.formHidden}" col="4" label-align="right">
				                <su-textbox :data.sync="params.ssAgreScheme.pqSharingRatio" name="pqSharingRatio" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				            </mk-form-col>
		        		</div>
		        		<div id="divi0202" :class="{'display-none':!divi0202Flag}">
							<!-- 收益模式选择 -->
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true"
										:class="{'display-none':!formFields.profitMode.formHidden}" col="4" label-align="right">
		                 		<su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" 
		                 		:select-value.sync="params.ssAgreScheme.profitMode" name="profitMode" ></su-select>
				            </mk-form-col>
				        </div>
							<!-- 售方双边分成比例 -->
							<mk-form-col class="party" :label="formFields.partyBLcProp.label" :class="{'display-none':!formFields.partyBLcProp.formHidden}" required_icon="true">
								<su-textbox :data.sync="params.ssAgreScheme.partyBLcProp" type="number" :addon="{'show':true,'rightcontent':'%'}" name="partyBLcProp"></su-textbox>
							</mk-form-col>
							<!-- 用户双边分成比例 -->
							<mk-form-col class="party" :label="formFields.partyALcProp.label" :class="{'display-none':!formFields.partyALcProp.formHidden}" required_icon="true">
								<su-textbox :data.sync="params.ssAgreScheme.partyALcProp" disabled="disabled" type="number" :addon="{'show':true,'rightcontent':'%'}" name="partyALcProp"></su-textbox>
							</mk-form-col>
					</mk-form-row>
				<mk-form-row :class="{'display-none':!divi03Flag}">
				        <!-- 售电公司竞价分成比例  -->
				        <mk-form-col :label="formFields.partyBBidProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyBBidProp.formHidden}" col="4" label-align="right" >
				            <su-textbox :data.sync="params.ssAgreScheme.partyBBidProp" name="partyBBidProp" :addon="{'show':true,'rightcontent':'%'}" type="number"></su-textbox>
				        </mk-form-col>
				         <!-- 用户竞价分成比例 -->
			            <mk-form-col :label="formFields.partyABidProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyABidProp.formHidden}" col="4" label-align="right" >
			                <su-textbox disabled="disabled" :data.sync="params.ssAgreScheme.partyABidProp" name="partyABidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row :class="{'display-none':!divi0204Flag}">
						<!-- 售电公司分成比例  -->
				        <mk-form-col :label="formFields.partyBProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyBProp.formHidden}" col="4" label-align="right" >
				            <su-textbox :data.sync="partyBProp" name="partyBProp" :addon="{'show':true,'rightcontent':'%'}" type="number"></su-textbox>
				        </mk-form-col>
				         <!-- 用户分成比例 -->
			            <mk-form-col :label="formFields.partyAProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyAProp.formHidden}" col="4" label-align="right" >
			                <su-textbox disabled="disabled" :data.sync="partyAProp" name="partyAProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
			</div>
		</mk-form-panel>
		<mk-form-panel title="用户偏差考核方式" label_width="150px" >
			<mk-form-row>
				<!-- 生效条件  -->
		        <mk-form-col colspan="3" :label="formFields.effectTerm.label" label-width="200px" 
							:class="{'display-none':!formFields.effectTerm.formHidden}" col="4" label-align="right" >
		            <su-textbox disabled="disabled" :data.sync="consEffectTerm"></su-textbox>
		        </mk-form-col>
		    </mk-form-row>
		    <mk-form-row>
				<!-- 正偏差惩罚类型  -->
		        <mk-form-col :label="formFields.punishTypeUpper.label" :class="{'display-none':!formFields.punishTypeUpper.formHidden}" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.ssAgreScheme.punishTypeUpper"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" name="punishTypeUpper"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row :class="{'display-none':!consPunishTypeUpperFlag}">
				<!-- 正偏差允许范围（正偏差域值）  -->
			    <mk-form-col :label="formFields.punishUpperThreshold.label" :class="{'display-none':!formFields.punishUpperThreshold.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.punishUpperThreshold" type="number" :addon="{'show':true,'rightcontent':'%'}" name="punishUpperThreshold"></su-textbox>
				</mk-form-col>
				<!-- 惩罚基准值（正偏差惩罚电价基准）  -->
			    <mk-form-col :label="formFields.punishUpperType.label" :class="{'display-none':!formFields.punishUpperType.formHidden}" required_icon="true">
					<!-- <su-textbox :data.sync="params.ssAgreScheme.punishUpperType" type="number" name="punishUpperType"></su-textbox> -->
					<su-select label-name="name" @su-change="consUpperTypeChange" :select-value.sync="params.ssAgreScheme.punishUpperType" name="punishUpperType" 
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
				</mk-form-col>
				<!-- 惩罚比例（正偏差惩罚比例值）  -->
		        <mk-form-col :label="formFields.punishUpperProp.label" v-if="!consUpperPrcFlag" :class="{'display-none':!formFields.punishUpperProp.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.punishUpperProp" type="number" :addon="{'show':true,'rightcontent':'%'}" name="punishUpperProp"></su-textbox>
				</mk-form-col>
				<!-- 正偏差惩罚协议价 -->
	            <mk-form-col :label="formFields.upperPrc.label" label-width="200px" required_icon="true"
							v-if="consUpperPrcFlag" :class="{'display-none':!formFields.upperPrc.formHidden}" col="4" label-align="right">
	                <su-textbox :data.sync="params.ssAgreScheme.upperPrc" name="upperPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	            </mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 负偏差惩罚类型  -->
		        <mk-form-col :label="formFields.punishTypeLower.label" :class="{'display-none':!formFields.punishTypeLower.formHidden}" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.ssAgreScheme.punishTypeLower"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" name="punishTypeLower"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row :class="{'display-none':!consPunishTypeLowerFlag}">
				<!-- 负偏差允许范围（负偏差域值）  -->
			    <mk-form-col :label="formFields.punishLowerThreshold.label" :class="{'display-none':!formFields.punishLowerThreshold.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.punishLowerThreshold" type="number" :addon="{'show':true,'rightcontent':'%'}" name="punishLowerThreshold"></su-textbox>
				</mk-form-col>
				<!-- 惩罚基准值（负偏差惩罚电价基准）  -->
			    <mk-form-col :label="formFields.punishLowerType.label" :class="{'display-none':!formFields.punishLowerType.formHidden}" required_icon="true">
					<!-- <su-textbox :data.sync="params.ssAgreScheme.punishLowerType" type="number" name="punishLowerType"></su-textbox> -->
					<su-select label-name="name" @su-change="consLowerTypeChange" :select-value.sync="params.ssAgreScheme.punishLowerType" name="punishLowerType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
				</mk-form-col>
				<!-- 惩罚比例（负偏差惩罚比例值）  -->
		        <mk-form-col :label="formFields.punishLowerProp.label" v-if="!consLowerPrcFlag" :class="{'display-none':!formFields.punishLowerProp.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.punishLowerProp" type="number" :addon="{'show':true,'rightcontent':'%'}" name="punishLowerProp"></su-textbox>
				</mk-form-col>
				<!-- 负偏差惩罚协议价 -->
	            <mk-form-col :label="formFields.lowerPrc.label" label-width="200px" required_icon="true"
						v-if="consLowerPrcFlag" :class="{'display-none':!formFields.lowerPrc.formHidden}" col="4" label-align="right">
	                <su-textbox :data.sync="params.ssAgreScheme.lowerPrc" name="lowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	            </mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="售电公司违约惩罚方式" label_width="140px">
			<mk-form-row>
				<!-- 生效条件  -->
		        <mk-form-col colspan="2" :label="formFields.effectTerm.label" label-width="200px"
							:class="{'display-none':!formFields.effectTerm.formHidden}" col="4" label-align="right">							
	                 <su-textbox disabled="disabled" :data.sync="compEffectTerm" name="compEffectTerm"></su-textbox>
		        </mk-form-col>
		        <!-- 是否赔偿 -->
		        <mk-form-col :label="formFields.compensateFlag.label" label-width="200px" required_icon="true"
							:class="{'display-none':!formFields.compensateFlag.formHidden}" col="4" label-align="right">							
	                 <su-select label-name="name" @su-change = "compPunishFlagChange"
	                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_compensateFlag" :select-value.sync="params.ssAgreScheme.compensateFlag" name="compensateFlag" ></su-select>
		        </mk-form-col>
			</mk-form-row>
			<mk-form-row :class="{'display-none':!compPunishFlag}">
				<!-- 赔偿域值  -->
		        <mk-form-col :label="formFields.compensateThreshold.label" label-width="200px" required_icon="true"
							:class="{'display-none':!formFields.compensateThreshold.formHidden}" col="4" label-align="right" >
		            <su-textbox :data.sync="params.ssAgreScheme.compensateThreshold" :addon="{'show':true,'rightcontent':'%'}" name="compensateThreshold"></su-textbox>
		        </mk-form-col>
				<!-- 赔偿电价基准  -->
		        <mk-form-col :label="formFields.compensateType.label" label-width="200px" required_icon="true"
							:class="{'display-none':!formFields.compensateType.formHidden}" col="4" label-align="right">							
	                 <su-select label-name="name" @su-change = "compLowerTypeChange" :select-value.sync="params.ssAgreScheme.compensateType" name="compensateType"
	                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
		        </mk-form-col>
				<!--  赔偿比例值  -->
		        <mk-form-col :label="formFields.compensateProp.label" label-width="200px" required_icon="true"
							v-if="!compPunishFlag01" :class="{'display-none':!formFields.compensateProp.formHidden}" col="4" label-align="right">
		            <su-textbox :data.sync="params.ssAgreScheme.compensateProp" :addon="{'show':true,'rightcontent':'%'}" name="compensateProp"></su-textbox>
		        </mk-form-col>
		        <!-- 赔偿协议价 -->
	            <mk-form-col :label="formFields.compensatePrc.label" label-width="200px" required_icon="true"
							v-if="compPunishFlag01" :class="{'display-none':!formFields.compensatePrc.formHidden}" col="4" label-align="right">
	                <su-textbox :data.sync="params.ssAgreScheme.compensatePrc" name="compensatePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	            </mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="基础参数填写" label_width="140px">
			<mk-form-row>
				<!-- 合同电量  -->
				<mk-form-col :label="formFields.reportPq.label" :class="{'display-none':!formFields.reportPq.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.reportPq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}" name="reportPq"></su-textbox>
				</mk-form-col>
				<!-- 分配双边电量  -->
				<mk-form-col :label="formFields.lcPq.label" :class="{'display-none':!formFields.lcPq.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.lcPq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}" name="lcPq"></su-textbox>
				</mk-form-col>
				<!-- 双边价差  -->
				<mk-form-col :label="formFields.lcPrc.label" :class="{'display-none':!formFields.lcPrc.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.lcPrc" type="number" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" name="lcPrc"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 竞价中标电量  -->
				<mk-form-col :label="formFields.bidPq.label" :class="{'display-none':!formFields.bidPq.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.bidPq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}" name="bidPq"></su-textbox>
				</mk-form-col>
				<!-- 竞价价差  -->
				<mk-form-col :label="formFields.bidPrc.label" :class="{'display-none':!formFields.bidPrc.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.bidPrc" type="number" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" name="bidPrc"></su-textbox>
				</mk-form-col>
				<!-- 交割电量  -->
				<mk-form-col :label="formFields.delPq.label" :class="{'display-none':!formFields.delPq.formHidden}" required_icon="true">
					<su-textbox :data.sync="params.ssAgreScheme.delPq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}" name="delPq"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row style="text-align:right">
			 		<su-button style="width:100px" s-type="primary" class="btn-width-small" v-on:click="calculate">电费计算</su-button>
	                <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
			</mk-form-row>
		</mk-form-panel>
		<mk-panel title="收益方案" line="true" >
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="remove">删除</su-button>
		    </div>	    
		    <div id="schemeAddGrid" class="col-xs-12" style="height:300px"></div>
		</mk-panel>
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var schemeAddVue=new Vue({
			el: '#schemeAddVue',
			data: {	
				flag:false,//是否点击了计算
				//优惠上下限 discountUpperFlag:null,discountUpperAmt:null,discountLowerFlag:null,discountLowerAmt:null,
				contractParam:{
					smPpa:{id:null,consId:null,agreNo:null,agreName:null,agreType:null,agreStatus:"00",partyA:null,
	  	  				partyB:null,signDate:null,effectiveDate:null,expiryDate:null,fileId:null,
	  	  				proxyMode:null,proxyPq:null,distMode:null,jan:null,feb:null,mar:null,apr:null,
	  	  				may:null,jun:null,jul:null,aug:null,sept:null,oct:null,nov:null,dece:null,recorder:null,recordDate:null},
	  	  			smDistMode:{id:null,agreId:null,diviCode:null,settlementMode:null,agrePrc:null,partyBLcProp:null,
	  	  				partyALcProp:null,partyBBidProp:null,partyABidProp:null,companyId:null,departmentId:null,profitMode:null},
	  	  			smAgreMp:[],
	  	  			smAgrePunishCons:{id:null,agreId:null,punishTypeUpper:null,punishTypeLower:null,upperThreshold:null,upperType:null,upperProp:null,lowerThreshold:null,lowerType:null,
	  	  			lowerProp:null,upperPrc:null,lowerPrc:null,punishFlag:null,companyId:null,departmentId:null},
	  	  			smAgrePunishComp:{id:null,agreId:null,punishFlag:null,lowerThreshold:null,lowerType:null,lowerPrc:null,lowerProp:null,companyId:null,departmentId:null}
				},
				selectIndex : -1,
				schemeName : null,
				partyBBidProp:null,//售方竞价分成比例
		  		partyABidProp:null,//用户竞价分成比例
		  		partyBProp:null,//售方分成比例
		  		partyAProp:null,//用户分成比例
		  	  	divi01Flag:false, // 保底选项相关字段展示标志
		  	  	divi02Flag:false, // 分成选项相关字段展示标志
		  	  	divi0201Flag:false, // 参与分成电量比例展示标志
		  	  	divi0202Flag:false, // 收益模式选择展示标志
		  	  	divi03Flag:false, // 长协分成比例
		  	  	divi0204Flag:false,//分成比列的展示标志
		  	  	consLowerPrcFlag:false,//负偏差惩罚协议价展示标志
		  		consUpperPrcFlag:false,//正偏差惩罚协议价展示标志
		  		compPunishFlag:false,//售电公司惩罚展示标志
		  		compPunishFlag01:false,//售电公司惩罚基准展示标志
				consPunishTypeUpperFlag:false,//用户惩罚展示标志(正偏差)
				consPunishTypeLowerFlag:false,//用户惩罚展示标志（负偏差）
				calculatenum:null,
				formFields:{},
				removeIds:[],
				consId:null,
				consName:null,
				consInfo:null, //用户信息
				consEffectTerm:"电力用户交割电量不等于其市场电量",
		  	  	compEffectTerm:"当售电公司为电力用户购得月度电量小于电力用户月度委托电量时",
				url:basePath + 'view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox',
			    params:{ssAgreScheme:{
			    				id:null,
			    				consId:null,
			    				consName:null,
			    				schemeName:null,
			    				settlementMode:null,
			    				diviCode:null,
			    				agrePrc:null,
			    				partyBLcProp:null,
			    				partyALcProp:null,
			    				partyBBidProp:null,
			    				partyABidProp:null,
			    				punishTypeUpper:null,
			    				punishTypeLower:null,
			    				punishUpperThreshold:null,
			    				punishUpperType:null,
			    				punishUpperProp:null,
			    				punishLowerThreshold:null,
			    				punishLowerType:null,
			    				punishLowerProp:null,
			    				reportPq:null,
			    				lcPq:null,
			    				bidPrc:null,
			    				delPq:null,
			    				bidPq:null,
			    				lcPrc:null,
			    				transTotalPro:null,
			    				consPro:null,
			    				consCheckedPro:null,
			    				sellTotalPro:null,
			    				compensateFlag:null,
			    				compensateThreshold:null,
			    				compensateType:null,
			    				compensatePrc:null,
			    				compensateProp:null,
			    				profitMode:null,
			    				pqSharingRatio:null,
			    				upperPrc:null,
			    				lowerPrc:null,
			    				punishFlag:null,
			    				},
						},
			   
				//非空验证规则
		        dataOption: {
		            rules: {	
		            	consId:{required: !0},
	    				consName:{required: !""},
	    				settlementMode:{required: !0},
	    				diviCode:{required: !0},
	    				agrePrc:{required: !0},
	    				partyBLcProp:{required: !0,'max':100,'min':0},
	    				partyALcProp:{required: !0},
	    				partyBBidProp:{required: !0,'max':100,'min':0},
	    				partyABidProp:{required: !0},
	    				punishTypeUpper:{required: !0},
	    				punishTypeLower:{required: !0},
	    				punishUpperThreshold:{required: !0},
	    				punishUpperType:{required: !0},
	    				punishUpperProp:{required: !0},
	    				punishLowerThreshold:{required: !0},
	    				punishLowerType:{required: !0},
	    				punishLowerProp:{required: !0},
	    				reportPq:{required: !0},
	    				lcPq:{required: !0},
	    				bidPrc:{required: !0},
	    				delPq:{required: !0},
	    				bidPq:{required: !0},
	    				lcPrc:{required: !0},
	    				compensateFlag:{required: !0},
	    				compensateThreshold:{required: !0},
	    				compensateType:{required: !0},
	    				compensatePrc:{required: !0},
	    				compensateProp:{required: !0},
	    				profitMode:{required: !""},
	    				pqSharingRatio:{required: !0},
	    				upperPrc:{required: !0},
	    				lowerPrc:{required: !0},
	    				partyBProp:{required: !0,'max':100,'min':0},
	    				partyAProp:{required: !0},
		            }
		        },
		        saveDatas:{
 		        	removeIds:[],
		        	ssAgreSchemeList:[]
		        },
		        valid:false
			},
			ready:function(){
				$("#divimode").hide();
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.crm.ssagrescheme",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"schemeAdd");
				me.initGrid();
			},
			methods: {
				//赔偿电价基准
				compLowerTypeChange:function(){
					var me =this;
					if(me.params.ssAgreScheme.compensateType == "01"){
						me.compPunishFlag01 = true;
						me.params.ssAgreScheme.compensateProp = null;
					}else{
						me.compPunishFlag01 = false;
						me.params.ssAgreScheme.compensatePrc = null;
					}
				},
				//是否赔偿
				compPunishFlagChange:function(){
					var me = this;
					if(me.params.ssAgreScheme.compensateFlag == 1){
						me.compPunishFlag = true;
					}else{
						me.compPunishFlag = false;
						me.params.ssAgreScheme.compensateThreshold = null;
						me.params.ssAgreScheme.compensateType = "";
						me.params.ssAgreScheme.compensateProp = null;
						me.params.ssAgreScheme.compensatePrc = null;
					}
				},
				//负偏差
				consLowerTypeChange:function(){
					var me = this;
					if(me.params.ssAgreScheme.punishLowerType == "01"){
						me.consLowerPrcFlag = true;
						me.params.ssAgreScheme.punishLowerProp = null;
					}else{
						me.consLowerPrcFlag = false;
						me.params.ssAgreScheme.lowerPrc = null;
					}
				},
				//正偏差
				consUpperTypeChange:function(){
					var me = this;
					if(me.params.ssAgreScheme.punishUpperType == "01"){
						me.consUpperPrcFlag = true;
						me.params.ssAgreScheme.punishUpperProp = null;
					}else{
						me.consUpperPrcFlag = false;
						me.params.ssAgreScheme.upperPrc = null;
					}
				},
				
				//方案名称
				schemeNameCount:function(){
					var me = this;
					var rows = $("#schemeAddGrid").datagrid("getRows");
					if(rows[0] == null){
						me.params.ssAgreScheme.schemeName = "方案"+1;
					}else{
						me.params.ssAgreScheme.schemeName = "方案" + (parseInt(rows[0].schemeName.substr(2)) + 1);
					}
				},
				//计算方案
				calculate:function(){
					var me = this;
					//获取政府电价
					me.$refs.form1.valid();
		            if(me.valid===false){
		            	MainFrameUtil.alert({title:"提示",body:"您有必填项未填写！"}); 
		                return false;
		            }
		            if(me.consName == null || me.consName == ""){
		            	MainFrameUtil.alert({title:"提示",body:"请选择客户！"}); 
		            	return;
		            }
		          	//为竞价分成比列赋值
		    		var diviCode = this.params.ssAgreScheme.diviCode;
		            //如果方案名称不为空，视为修改
		            if(me.schemeName != null && me.schemeName != ""){
		            	me.flag = true;
		            	me.params.ssAgreScheme.consId = me.consId;
			            me.params.ssAgreScheme.consName = me.consName;
		            	$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/ssAgreSchemeController/calculate",
							type : 'post',
							data:$.toJSON(me.params.ssAgreScheme),
							contentType : 'application/json;charset=UTF-8',
							success : function(result) {
								if(result.flag){
									MainFrameUtil.alert({title:"提示",body:result.msg}); 
									$("#schemeAddGrid").datagrid("updateRow",{index:me.selectIndex,row:result.data});
									me.calculatenum = 1;
								}else{
									MainFrameUtil.alert({title:"提示",body:result.msg});
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"计算失败！"});
							}
						});
		            }else{
			            me.schemeNameCount();
			            me.params.ssAgreScheme.consId = me.consId;
			            me.params.ssAgreScheme.consName = me.consName;
						$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/ssAgreSchemeController/calculate",
							type : 'post',
							data:$.toJSON(me.params.ssAgreScheme),
							contentType : 'application/json;charset=UTF-8',
							success : function(result) {
								if(result.flag){
									MainFrameUtil.alert({title:"提示",body:"计算成功！"}); 
									$("#schemeAddGrid").datagrid("insertRow",{index:0,row:result.data});
									me.calculatenum = 1;
									me.flag = true;
									
								}else{
									MainFrameUtil.alert({title:"提示",body:result.msg});
								}
							},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"计算失败！"});
							}
						});
		            }
				},
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"转成正式合同",type:"primary",handler:me.changeToContract},{text:"保存",type:"primary",handler:me.commit},{text:"取消",handler:function(){MainFrameUtil.closeDialog("schemeAdd")}}];
		            return buttons;
		        },
		        
		        //转换为合同
		        changeToContract: function(){
		        	var me = this;
		        	if(me.consId==null||me.consId==''||me.consId=='undefined'||me.consId==undefined){
		    			MainFrameUtil.alert({title:"提示",body:"请选择用户名称！"}); 
		    			return;
		    		}
		        	if(!me.flag) {
		        		MainFrameUtil.alert({title:"提示",body:"请先进行电费计算！"}); 
		        		return;
		        	}
		        	//用户名称
		        	me.contractParam.smPpa.consId = me.consId;//用户名称
		        	//分成方式
		        	me.contractParam.smDistMode.settlementMode = me.params.ssAgreScheme.settlementMode;//结算方式
		        	me.contractParam.smDistMode.diviCode = me.params.ssAgreScheme.diviCode;//分成方式
		        	me.contractParam.smDistMode.agrePrc = me.params.ssAgreScheme.agrePrc; //保底协议价
		        	me.contractParam.smDistMode.profitMode = me.params.ssAgreScheme.profitMode; //收益模式
		        	me.contractParam.smDistMode.partyBLcProp = me.params.ssAgreScheme.partyBLcProp; //售方长协分成比例
		        	me.contractParam.smDistMode.partyALcProp = me.params.ssAgreScheme.partyALcProp; //用户长协分成比例
		        	me.contractParam.smDistMode.partyBBidProp = me.params.ssAgreScheme.partyBBidProp; //售方竞价分成比例
		        	me.contractParam.smDistMode.partyABidProp = me.params.ssAgreScheme.partyABidProp; //用户竞价分成比例
		        	
		        	//用户惩罚方式
		        	me.contractParam.smAgrePunishCons.punishTypeUpper = me.params.ssAgreScheme.punishTypeUpper;//用户惩罚类型(正偏差)
		        	me.contractParam.smAgrePunishCons.punishTypeLower = me.params.ssAgreScheme.punishTypeLower;//用户惩罚类型（负偏差）
		        	me.contractParam.smAgrePunishCons.upperThreshold = me.params.ssAgreScheme.punishUpperThreshold;//用电超出月申报电量阈值
		        	me.contractParam.smAgrePunishCons.upperType = me.params.ssAgreScheme.punishUpperType;//惩罚电价基准
		        	me.contractParam.smAgrePunishCons.upperProp = me.params.ssAgreScheme.punishUpperProp;//正偏差惩罚比例值
		        	
		        	me.contractParam.smAgrePunishCons.upperPrc = me.params.ssAgreScheme.upperPrc;//正偏差惩罚协议价
		        	me.contractParam.smAgrePunishCons.lowerThreshold = me.params.ssAgreScheme.punishLowerThreshold;//用电不足月申报电量阈值
		        	me.contractParam.smAgrePunishCons.lowerType = me.params.ssAgreScheme.punishLowerType;//惩罚电价基准
		        	me.contractParam.smAgrePunishCons.lowerProp = me.params.ssAgreScheme.punishLowerProp;//负偏差惩罚比例值 
		        	
		        	me.contractParam.smAgrePunishCons.lowerPrc = me.params.ssAgreScheme.lowerPrc;//负偏差惩罚协议价 
		        	//售电公司违约惩罚方式
		        	me.contractParam.smAgrePunishComp.punishFlag = me.params.ssAgreScheme.compensateFlag;//是否赔偿
		        	me.contractParam.smAgrePunishComp.lowerThreshold = me.params.ssAgreScheme.compensateThreshold;//赔偿阈值
		        	me.contractParam.smAgrePunishComp.lowerType = me.params.ssAgreScheme.compensateType;//赔偿电价基准
		        	me.contractParam.smAgrePunishComp.lowerProp =  me.params.ssAgreScheme.compensateProp;//赔偿比例值
		        	me.contractParam.smAgrePunishComp.lowerPrc =  me.params.ssAgreScheme.compensatePrc;//赔偿协议价
		        	//默认为选中售方不罚用户不罚
		        	me.contractParam.smAgrePunishCons.punishFlag = 0;
		        	//为竞价分成比列赋值
		        	var diviCode = me.contractParam.smDistMode.diviCode;
		        	if(diviCode == "01"){//保底
		        		me.contractParam.smDistMode.partyBLcProp = null;
		    			me.contractParam.smDistMode.partyALcdProp = null;
		    			me.contractParam.smDistMode.partyBBidProp = null;
		    			me.contractParam.smDistMode.partyABidProp = null;
		    		}
		    		//保存数据
		    		me.saveContract("POST");
		        },
		        
		      	//合同数据保存
		        saveContract:function(sbType){
		        	var me = this;
		        	var queryParams = {};
		        	$.extend(queryParams,me.contractParam);
		        	var  index = 0;
		        	if(me.selectIndex >= 0 ){
		        		index = me.selectIndex;
		        	} else{
		        		MainFrameUtil.alert({title:'提示',body:'请选择收益方案！'});
		        		return;
		        	}
		        	queryParams["ssAgreScheme"] =  $("#schemeAddGrid").datagrid("getRows")[index];
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/ssAgreSchemeController/changeToContract",
						type : sbType,
						data:$.toJSON(queryParams),
						contentType : 'application/json;charset=UTF-8',
						success : function(result) {
							if(result.flag){
								//MainFrameUtil.alert({title:"提示",body:"转换成功！"});
								 MainFrameUtil.customAlert({
								        title:"提示",
								        body:"转换成功!",
								        buttons:[{text:'继续完善售电合同',type:"continue",style:"default"},{text:'确定',type:"ok",style:"primary"}],
								        onClose:function(type){
								        	if(type=="continue"){//继续完善售电合同
								    			var uuid=Math.random();
								        		MainFrameUtil.openTabPage(uuid, basePath+"view/cloud-purchase-web/agreement/smppa/list"
								        				, "售电合同管理",false);//打开售电合同管理页签
								        		MainFrameUtil.closeDialog("schemeAdd");//关闭添加页面弹框
								            }
								        }
								    });
								
								me.contractParam.smPpa.id = result.data.smPpa.id;
								$("#schemeAddGrid").datagrid("updateRow",{index:index,row:result.data.ssAgreScheme});
							}else{
								MainFrameUtil.alert({title:"提示",body:"转换失败！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"保存失败！"});
						}
					});
		        },
				
				initGrid:function(){
					var me = this;
					 $("#schemeAddGrid").datagrid({
						width:"100%",
						singleSelect:true,
						checkOnSelect:true,
						onCheck:function(rowIndex,rowData){
							if(rowIndex == me.selectIndex){
								me.selectIndex = -1;
								$('#schemeAddGrid').datagrid('clearSelections');
								me.reset();
								me.schemeName = null;
							}else{
								for (var j in rowData) {
									if((j == "punishTypeUpper" || j == "punishTypeLower") && rowData[j] === null){
										me.params.ssAgreScheme[j] = "";
									}else{
										me.params.ssAgreScheme[j] = rowData[j];
									}
								}
								var diviCode = rowData.diviCode;
								if(diviCode == "05"){
									me.partyBProp = rowData.partyBBidProp;
									me.partyAProp = rowData.partyABidProp;
								}else if(diviCode == "03" || diviCode == "04"){
									me.partyBBidProp = rowData.partyBBidProp;
									me.partyABidProp = rowData.partyABidProp;
								}
								me.schemeName = rowData.schemeName;
								me.selectIndex = rowIndex;
								me.flag = true;
							}
						},
						columns:[[
								{field:'ck',checkbox:true,},
								{field:'schemeName',title:'方案',width:'10%'},
								{field:'sellTotalPro',title:'售电公司代理收益（元）',width:'20%',align:'center'},
 								{field:'transTotalPro',title:'售电公司购电收益（元）',width:'20%',align:'center'},
 								{field:'compensateAmt',title:'售电公司赔偿费用（元）',width:'20%',align:'center'},
 								{field:'consPro',title:'用户返还电费（元）',width:'14%',align:'center'},
								{field:'consCheckedPro',title:'用户考核电费（元）',width:'15%',align:'center'}
						]]
					});
				},
				
				remove:function(){
					var me = this;
					var row = $("#schemeAddGrid").datagrid("getSelections");
					if(row){
						 MainFrameUtil.confirm({
						        title:"确认",
						        body:"确认删除选中方案？",
						        onClose:function(type){
						            if(type=="ok"){//确定
						            	for(var a = 0;a<row.length;a++){
						            		if(row[a].id != null && row[a].id != ""){
						            			me.removeIds.push(row[a].id);
						            		}
											$('#schemeAddGrid').datagrid('deleteRow', $('#schemeAddGrid').datagrid('getRowIndex',row[a]));
											$('#schemeAddGrid').datagrid('clearSelections');
											me.reset();
						            	}
						            	me.calculatenum = 1;
						            }
						        }
						    });  
					}else{
						MainFrameUtil.alert({title:"提示",body:"请至少选择一条方案！"});
					}
				},
				//提交
				commit:function(){
					var me = this;
					
		            if(me.calculatenum == null || me.calculatenum == ""){
		            	MainFrameUtil.alert({
                            title:"提示：",
                            body:"客户方案没有新的计算结果，无需保存！",
                        });
		            	return;
		            }
 					me.saveDatas.removeIds = me.removeIds;
					me.saveDatas.ssAgreSchemeList = $("#schemeAddGrid").datagrid("getRows");
					MainFrameUtil.confirm({
		    	        title:"提示",
		    	        body:"确认保存操作吗？",
		    	        onClose:function(type){
		    	            if(type=="ok"){//确定
		    	            	$.ajax({
		    	                    url:basePath+"cloud-purchase-web/ssAgreSchemeController/commit",
		    	                    type:"post",
		    	                    data:$.toJSON(me.saveDatas),
		    	                    contentType:'application/json;charset=UTF-8',
		    	                    success : function(data) {
		    	                        if(data.flag == true){
		    	                             MainFrameUtil.alert({
		    	                                 title:"成功提示：",
		    	                                 body:data.msg,
		    	                             });
		    	                             MainFrameUtil.closeDialog("schemeAdd");
		    	                        }else{
		    	                            MainFrameUtil.alert({
		    	                                title:"失败提示：",
		    	                                body:data.msg,
		    	                            });
		    	                        }
		    	                    },
		    	                    error : function() {
		    	                        MainFrameUtil.alert({title:"网络失败提示",body:"添加失败！请刷新页面重试！"});
		    	                    }
		    	                }) 
		    	            }else if(type=="cancel"){//取消
		    	                
		    	            }

		    	        }
		    	    })
					
				},
				reset:function(){
					var me = this;
					me.flag = false;
					me.params= {ssAgreScheme:{
							consId:null,
	    					consName:null,
							//schemeName:"",
							settlementMode:"",
		    				diviCode:"",
		    				agrePrc:null,
		    				partyBLcProp:null,
		    				partyALcProp:null,
		    				partyBBidProp:null,
		    				partyABidProp:null,
		    				punishTypeUpper:"",
		    				punishTypeLower:"",
		    				punishUpperThreshold:null,
		    				punishUpperType:null,
		    				punishUpperProp:null,
		    				punishLowerThreshold:null,
		    				punishLowerType:null,
		    				punishLowerProp:null,
		    				reportPq:null,
		    				lcPq:null,
		    				bidPrc:null,
		    				delPq:null,
		    				bidPq:null,
		    				lcPrc:null,
		    				compensateFlag:"",
		    				compensateThreshold:null,
		    				compensateType:"",
		    				compensatePrc:null,
		    				compensateProp:null,
		    				profitMode:"",
		    				pqSharingRatio:null,
		    				upperPrc:null,
		    				lowerPrc:null,
		    				punishFlag:null,
					}};
					$('#schemeAddGrid').datagrid('clearSelections');
					me.selectIndex = -1;
					me.schemeName = null;
					me.partyBBidProp=null;
			  		me.partyABidProp=null;
			  		me.partyBProp=null;
			  		me.partyAProp=null;
				}, 
			},
			watch:{
				
				//1.给consName赋值
				//2.根据客户名称显示客户方案
				'consId':function(){
					var me = this;
					me.consInfo = me.$refs.consinfo.$data.allattr;
					me.consName = me.consInfo.consName;
					var consIdAjax = me.consId;
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/ssAgreSchemeController/getSsAgreSchemeListByConsId?consId="+consIdAjax,
						type : 'get',
						contentType : 'application/json;charset=UTF-8',
						success : function(result) {
							$("#schemeAddGrid").datagrid("loadData",result.rows);
						},
					});
					me.reset();
				},
				
				'params.ssAgreScheme.diviCode':function(value){
					var me = this;
		    		//分成方式监控
		    		switch(Number(me.params.ssAgreScheme.diviCode))
		    		{
			    		case 1://保底
			    			me.divi01Flag=true;
			    			me.divi02Flag=false;
			    			me.divi0201Flag=false;
			    			me.divi0202Flag=false;
			    			me.divi03Flag = false;
			    			me.divi0204Flag = false;
			    			$("#divimode").show();
			    			this.params.ssAgreScheme.partyBLcProp = null;
			    			this.params.ssAgreScheme.partyALcProp = null;
			    			this.partyBBidProp = null;
			    			this.partyABidProp = null;
			    			this.partyBProp = null;
			    			this.partyAProp = null;
			    			this.params.ssAgreScheme.pqSharingRatio = null;
			    			this.params.ssAgreScheme.profitMode = "";
			    			break;
			    		case 3://分成
			    			me.divi01Flag=false;
			    			me.divi02Flag=true;
			    			me.divi0201Flag=false;
			    			me.divi0202Flag=false;
			    			me.divi03Flag=true;
			    			me.divi0204Flag=false;
			    			$("#divimode").hide();
			    			this.params.ssAgreScheme.settlementMode = "";
			    			this.params.ssAgreScheme.agrePrc = null;
			    			this.params.ssAgreScheme.pqSharingRatio = null;
			    			this.params.ssAgreScheme.profitMode = "";
			    			this.partyBProp = null;
			    			this.partyAProp = null;
			    			break;
			    		case 4://保底或分成
			    			$("#divimode").show();
			    			me.divi01Flag=true;
			    			me.divi02Flag=true;
			    			me.divi0201Flag=false;
			    			me.divi0202Flag=true;
			    			me.divi03Flag=true;
			    			me.divi0204Flag=false;
			    			this.partyBProp = null;
			    			this.partyAProp = null;
			    			this.params.ssAgreScheme.pqSharingRatio = null;
			    			break;
			    		case 5://保底加分成
			    			$("#divimode").show();
			    			me.divi01Flag=true;
			    			me.divi02Flag=true;
			    			me.divi0201Flag=false;
			    			me.divi0202Flag=false;
			    			me.divi03Flag = true;
			    			me.divi0204Flag = false;
			    			this.params.ssAgreScheme.pqSharingRatio = null;
			    			this.params.ssAgreScheme.profitMode = "";
			    			this.params.ssAgreScheme.partyBLcProp = null;
			    			this.params.ssAgreScheme.partyALcProp = null;
			    			this.partyBBidProp = null;
			    			this.partyABidProp = null;
			    			break;
			    		default:
			    			$("#divimode").hide();
			    			me.divi01Flag=false;
			    			me.divi02Flag=false;
			    			me.divi0201Flag=false;
			    			me.divi0202Flag=false;
			    			me.divi03Flag=false;
			    			me.divi0204Flag=false;
			    			this.params.ssAgreScheme.agrePrc = null;
			    			this.params.ssAgreScheme.partyBLcProp = null;
			    			this.params.ssAgreScheme.partyALcProp = null;
			    			this.partyBBidProp = null;
			    			this.partyABidProp = null;
			    			this.params.ssAgreScheme.pqSharingRatio = null;
			    			this.params.ssAgreScheme.profitMode = "";
			    			this.partyBProp = null;
			    			this.partyAProp = null;
			    			break;
		    		}
				},
				
				'params.ssAgreScheme.partyBLcProp':function(){
					var me = this;
					if(me.params.ssAgreScheme.partyBLcProp != null && me.params.ssAgreScheme.partyBLcProp != ""){
						me.params.ssAgreScheme.partyALcProp = 100 - me.params.ssAgreScheme.partyBLcProp;						
					}else{
						me.params.ssAgreScheme.partyALcProp = null;
					}
				},
				'params.ssAgreScheme.partyBBidProp':function(){
					var me = this;
					if(me.params.ssAgreScheme.partyBBidProp != null && me.params.ssAgreScheme.partyBBidProp != ""){
						me.params.ssAgreScheme.partyABidProp = 100 - me.params.ssAgreScheme.partyBBidProp;						
					}else{
						me.params.ssAgreScheme.partyABidProp = null;
					}
				},
				'partyBBidProp':function(){
		    		if(this.partyBBidProp){
		    			this.partyABidProp = 100 - parseFloat(this.partyBBidProp);
		    		}
		    	},
		    	'partyBProp':function(){
		    		if(this.partyBProp){
		    			this.partyAProp = 100 - parseFloat(this.partyBProp);
		    		}
		    	},
		    	"params.ssAgreScheme.punishTypeUpper":function(){
		    		var me = this;
		    		if(me.params.ssAgreScheme.punishTypeUpper == 1){//正偏差惩罚
		    			me.consPunishTypeUpperFlag = true;
		    		}else{
		    			me.consPunishTypeUpperFlag = false;
		    			me.params.ssAgreScheme.punishUpperThreshold = null;
		    			me.params.ssAgreScheme.punishUpperType = "";
		    			me.params.ssAgreScheme.punishUpperProp = null;
		    			me.params.ssAgreScheme.upperPrc = null;
		    		}
		    	},
		    	"params.ssAgreScheme.punishTypeLower":function(){
		    		var me = this;
		    		if(me.params.ssAgreScheme.punishTypeLower == 1){//负偏差惩罚
		    			me.consPunishTypeLowerFlag = true;
		    		}else{
		    			me.consPunishTypeLowerFlag = false;
		    			me.params.ssAgreScheme.punishLowerThreshold = null;
		    			me.params.ssAgreScheme.punishLowerType = "";
		    			me.params.ssAgreScheme.punishLowerProp = null;
		    			me.params.ssAgreScheme.lowerPrc = null;
		    		}
		    	}
			} 
		});
	</script>
</body>
</html>