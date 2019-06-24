<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>合同管理-售电合同管理新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>


<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
</style>
</head>
<body>
<div id="ppaAddVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="用户信息" line="true">
			<mk-form-panel >
				<mk-form-row>
					<!-- 用户编号  -->
		            <mk-form-col :label="formFields.consName.label" required_icon="true" label-width="200px" col="4" label-align="right">
		                <mk-selectbox :url="url" height="620" width="50%" textfield="consName" propname="id" 
		                		:propvalue.sync="params.smPpa.consId" v-ref:consinfo name="consId"></mk-selectbox>
		            </mk-form-col>
					<!-- 电压等级  -->
		            <mk-form-col :label="formFields.voltCodeName.label" label-width="200px" col="4" label-align="right">
		                <su-textbox disabled="disabled" :data.sync="consInfo.voltCodeName"></su-textbox>
		            </mk-form-col>
					<!-- 用电类别  -->
		            <mk-form-col :label="formFields.elecTypeName.label" label-width="200px" col="4" label-align="right">
						<su-textbox disabled="disabled" :data.sync="consInfo.elecTypeName"></su-textbox>
		            </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 用电容量  -->
		             <mk-form-col :label="formFields.electricCapacity.label" label-width="200px" col="4" label-align="right">
		                 <su-textbox type="number" disabled="disabled" :data.sync="consInfo.electricCapacity" :addon="{'show':true,'rightcontent':'kVA'}"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>	
		<mk-panel title="合同基本信息"  line="true">	
			<mk-form-panel >
				<mk-form-row>
					<!-- 合同编号  -->
		             <mk-form-col :label="formFields.agreNo.label" label-width="200px" col="4" label-align="right">
		                 <su-textbox :data.sync="params.smPpa.agreNo"></su-textbox>
		             </mk-form-col>
					<!-- 合同名称  -->
		             <mk-form-col :label="formFields.agreName.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-textbox :data.sync="params.smPpa.agreName" name="agreName"></su-textbox>
		             </mk-form-col>
					<!-- 合同类型  -->
		             <%-- <mk-form-col :label="formFields.agreType.label" label-width="200px" col="4" label-align="right">
		                 <su-select :high="150" label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellElecContractType" :select-value.sync="params.smPpa.agreType" @su-change="" name="agreType"></su-select>
		             </mk-form-col> --%>
				</mk-form-row>
				<mk-form-row>
					<!-- 合同开始日期  -->
		             <mk-form-col :label="formFields.effectiveDate.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smPpa.effectiveDate" name="effectiveDate"></su-datepicker>
		             </mk-form-col>
					<!-- 合同结束日期  -->
		             <mk-form-col :label="formFields.expiryDate.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smPpa.expiryDate" name="expiryDate"></su-datepicker>
		             </mk-form-col>
		             <!-- 签订时间  -->
		             <mk-form-col :label="formFields.signDate.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smPpa.signDate" name="signDate"></su-datepicker>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 售电公司签约人  -->
		            <mk-form-col :label="formFields.partyB.label" label-width="200px" required_icon="true" col="4" label-align="right">
		            	<su-textbox :data.sync="params.smPpa.partyB" name="partyB"></su-textbox>
		            </mk-form-col>
					<!-- 客户签约人  -->
		             <mk-form-col :label="formFields.partyA.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-textbox :data.sync="params.smPpa.partyA" name="partyA"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row  height="auto">
		             <!-- 附件 -->
					<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" required_icon="true" colspan="3">
						<mk-multifile-upload :params="uploadParams1" :files.sync="fileInfos"   v-ref:uploadmulti  show_upload="false"   @upload-error="uploadError" 
		                     @upload-success="uploadSuccess"  url='${Config.getConfig("file.service.url")}' required="true" maxcount=20>
		                  </mk-multifile-upload>
					</mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="代理电量管理" line="true" height="auto" >
			<div slot="buttons" class="pull-right" style="text-align:right">
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="line-chart" v-on:click="forecast">负荷预测</su-button>
     	    </div>
			<mk-form-panel num = "2">
				<mk-form-row>
					<!-- 代理总电量  -->
		            <mk-form-col :label="formFields.proxyPq.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smPpa.proxyPq" name="proxyPq" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		            </mk-form-col>
					<!-- 分配方式  -->
		            <mk-form-col :label="formFields.distMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
			            <su-select label-name="name" 
			            		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_dividedType" :select-value.sync="params.smPpa.distMode" @su-change="" name="distMode" ></su-select>
		            </mk-form-col>
				</mk-form-row>  
			</mk-form-panel>
			<div id="ppaAddGrid" style="height:100%"></div>
		</mk-panel>				
		<mk-panel height="300px" header="false" :class="{'display-none':!mpGridShowFlag}">
			<div id="mpGrid" style="height:100%"></div>
		</mk-panel>	
		<mk-panel title="双边电量分成方式" line="true">
			<mk-form-panel label_width="150px">
				<mk-form-row>
					<!-- 分成方式 -->
		            <mk-form-col :label="formFields.diviCode.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideWayCode" :select-value.sync="params.smDistMode.diviCode" name="diviCode">
		                 </su-select>	
		            </mk-form-col>
					<!-- 结算模式  -->
					<div id = "divimode" style="display:inline">
			            <mk-form-col :label="formFields.settlementMode.label" label-width="200px" required_icon="true" col="4" label-align="right" >
			                 <su-select label-name="name" :data-source.sync="modeList"  @su-change="settleChange" :select-value.sync="params.smDistMode.settlementMode" name="settlementMode" ></su-select>
			            </mk-form-col>
					</div>
		            <div id = "remind" style="margin-left:100px;display: none">
		            	<font color="gray" size="1"  style="line-height:0.2px;">
		            		<i>&nbsp;&nbsp;&nbsp;&nbsp;价差为正：表示电力用户高于目录电价结算<br/>
		            		   &nbsp;&nbsp;&nbsp;&nbsp;价差为负：表示电力用户低于目录电价结算</i>
		            	</font>
		            </div>
				</mk-form-row>
				<!-- 分成方式的解释 -->
				<div id="diviCodeRemind">
		        	<mk-form-row>
						<!-- 分成方式的解释 -->
						<div id = "diviCodeRemindText" style="margin-left:50px;padding-top: 10px;height: 30px;">
						</div>
					</mk-form-row>
				</div>
				<!-- 保底选项相关字段 -->
				<div id="divi01">
		        	<mk-form-row>
						<!-- 保底协议价 -->
						<div id = "divi0101" style="display:inline">
				            <mk-form-col :label="formFields.agreAmt.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox type="number" :data.sync="params.smDistMode.agrePrc" :name="agrePrcFlag" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
						</div>
			            <!-- 分成基准 -->
			            <div id="divi0102" style="display:inline">
				            <mk-form-col :label="formFields.dividType.label" label-width="200px" required_icon="true" col="4" label-align="right">
				               <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideType" :select-value.sync="params.smDistMode.diviType" name="diviType" ></su-select>
				            </mk-form-col>
			            </div>
			            <!-- 分成基准值 -->
			            <div id="divi0103" style="display:inline">
				            <mk-form-col :label="formFields.dividValue.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox type="number" :data.sync="params.smDistMode.diviValue" name="diviValue" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
			            </div>
					</mk-form-row>
				</div>
				<!-- 代理服务费相关字段 -->
				<mk-form-row id="divi0301">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smDistMode.agent" name="agent" :addon.sync="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row id="divi0302">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smDistMode.agent" name="agent" :addon.sync="{'show':true,'rightcontent':'元'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<!-- 分成选项相关字段 -->
				<div id="divi02">
					<mk-form-row>
						<!-- 收益模式选择 -->
						<div id="divi0201" style="display:block">
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                 <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" 
				                 		:select-value.sync="params.smDistMode.profitMode" name="profitMode" >
				                 </su-select>
				            </mk-form-col>
						</div>
		        		<!-- 售电公司双边分成比例  -->
				        <mk-form-col :label="formFields.partyBLcProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
				            <su-textbox type="number" :data.sync="params.smDistMode.partyBLcProp" name="partyBLcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
						<!-- 用户双边分成比例 -->
			            <mk-form-col :label="formFields.partyALcProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
			                <su-textbox type="number" disabled="disabled" :data.sync="params.smDistMode.partyALcProp" name="partyALcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					
				</div>
			</mk-form-panel>
		</mk-panel>	
		<mk-panel title="竞价电量分成方式" line="true" v-cloak>
			<mk-form-panel label_width="150px">
				<mk-form-row>
					<!-- 分成方式 -->
		            <mk-form-col :label="formFields.diviCode.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideWayCode" :select-value.sync="params.smDistMode.bidDiviCode" name="bidDiviCode" >
		                 </su-select>	
		            </mk-form-col>
					<!-- 结算模式  -->
					<div id = "bidDivimode" style="display:inline">
			            <mk-form-col :label="formFields.settlementMode.label" label-width="200px" required_icon="true" col="4" label-align="right" >
			                 <su-select label-name="name" :data-source.sync="bidModeList"  @su-change="bidSettleChange" :select-value.sync="params.smDistMode.bidSettlementMode" name="bidSettlementMode" ></su-select>
			            </mk-form-col>
					</div>
		            <div id = "bidRemind" style="margin-left:100px;display: none">
		            	<font color="gray" size="1"  style="line-height:0.2px;">
		            		<i>&nbsp;&nbsp;&nbsp;&nbsp;价差为正：表示电力用户高于目录电价结算<br/>
		            		   &nbsp;&nbsp;&nbsp;&nbsp;价差为负：表示电力用户低于目录电价结算</i>
		            	</font>
		            </div>
				</mk-form-row>
				<!-- 分成方式的解释 -->
				<div id="bidDiviCodeRemind">
		        	<mk-form-row>
						<!-- 分成方式的解释 -->
						<div id = "bidDiviCodeRemindText" style="margin-left:50px;padding-top: 10px;height: 30px;">
						</div>
					</mk-form-row>
				</div>
				<!-- 保底选项相关字段 -->
				<div id="bidDivi01">
		        	<mk-form-row>
						<!-- 保底协议价 -->
						<div id = "bidDivi0101" style="display:inline">
				            <mk-form-col :label="formFields.agreAmt.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox type="number" :data.sync="params.smDistMode.bidAgrePrc" :name="bidAgrePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
						</div>
			            <!-- 分成基准 -->
			            <div id="bidDivi0102" style="display:inline">
				            <mk-form-col :label="formFields.dividType.label" label-width="200px" required_icon="true" col="4" label-align="right">
				               <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideType" :select-value.sync="params.smDistMode.bidDiviType" name="bidDiviType" ></su-select>
				            </mk-form-col>
			            </div>
			            <!-- 分成基准值 -->
			            <div id="bidDivi0103" style="display:inline">
				            <mk-form-col :label="formFields.dividValue.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox type="number" :data.sync="params.smDistMode.bidDiviValue" name="bidDiviValue" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
			            </div>
					</mk-form-row>
				</div>
				<!-- 代理服务费相关字段 -->
				<mk-form-row id="bidDivi0301">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smDistMode.bidAgent" name="bidAgent" :addon.sync="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row id="bidDivi0302">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smDistMode.bidAgent" name="bidAgent" :addon.sync="{'show':true,'rightcontent':'元'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<!-- 分成选项相关字段 -->
				<div id="bidDivi02">
					<mk-form-row>
						<!-- 收益模式选择 -->
						<div id="bidDivi0201" style="display:block">
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                 <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" 
				                 		:select-value.sync="params.smDistMode.bidProfitMode" name="bidProfitMode" >
				                 </su-select>
				            </mk-form-col>
						</div>
		        		 <!-- 售电公司竞价分成比例  -->
				        <mk-form-col :label="formFields.partyBBidProp.label" label-width="220px" required_icon="true"  col="4" label-align="right" >
				            <su-textbox type="number" :data.sync="params.smDistMode.partyBBidProp" name="partyBBidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
				         <!-- 用户竞价分成比例 -->
			            <mk-form-col :label="formFields.partyABidProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
			                <su-textbox type="number" disabled="disabled" :data.sync="params.smDistMode.partyABidProp" name="partyABidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
				</div>
			</mk-form-panel>
		</mk-panel>	
		<mk-panel title="用户偏差考核方式" line="true">
			<mk-form-panel>
				<mk-form-row>
					<!-- 生效条件  -->
			        <mk-form-col :label="formFields.effectTerm.label" label-width="200px" 
								colspan="3" :class="{'display-none':!formFields.effectTerm.formHidden}" col="4" label-align="right" >
			            <!-- <su-textbox disabled="disabled" :data.sync="consEffectTerm"></su-textbox> -->
			            <div id="consEffectTerm" style="padding-top: 5px;height: 30px;"></div>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!--  正偏差是否惩罚   -->
			        <mk-form-col :label="formFields.punishTypeUpper.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishTypeUpper.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishTypeUpper" name="punishTypeUpper" ></su-select>
			        </mk-form-col>
			        <!-- 正偏差惩罚类型 -->
			        <%-- <mk-form-col :label="formFields.punishRange.label" label-width="200px" required_icon="true" col="4" label-align="right">							
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishUpperRange" name="punishTypeUpper" >
		                 </su-select>
			        </mk-form-col> --%>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!consPunishTypeUpperFlag}">
					<!-- 正偏差允许范围（用电超出月申报电量阈值）  -->
			        <mk-form-col :label="formFields.upperThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.upperThreshold" :addon="{'show':true,'rightcontent':'%'}" name="upperThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 正偏差惩罚电价基准（惩罚电价基准）  -->
			        <mk-form-col :label="formFields.upperType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change="consUpperTypeChange" :select-value.sync="params.smAgrePunishCons.upperType" name="upperType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
			        </mk-form-col>
					<!--  正偏差惩罚比例值 （惩罚比例值）  -->
			        <mk-form-col :label="formFields.upperProp.label" label-width="200px" required_icon="true"
								v-if="!consUpperPrcFlag" :class="{'display-none':!formFields.upperProp.formHidden}" col="4" label-align="right">
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.upperProp" :addon="{'show':true,'rightcontent':'%'}" name="upperProp"></su-textbox>
			        </mk-form-col>
			        <!-- 正偏差惩罚协议价 -->
			            <mk-form-col :label="formFields.consUpperPrc.label" label-width="200px" required_icon="true"
									v-if="consUpperPrcFlag" :class="{'display-none':!formFields.consUpperPrc.formHidden}" col="4" label-align="right">
			                <su-textbox type="number" :data.sync="params.smAgrePunishCons.upperPrc" name="consUpperPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 负偏差是否惩罚  -->
			        <mk-form-col :label="formFields.punishTypeLower.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishTypeLower.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishTypeLower" name="punishTypeLower" >
		                 </su-select>
			        </mk-form-col>
			         <!-- 负偏差惩罚类型 -->
			        <%-- <mk-form-col :label="formFields.punishRange.label" label-width="200px" required_icon="true"  col="4" label-align="right">							
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishLowerRange" name="punishTypeUpper" >
		                 </su-select>
			        </mk-form-col> --%>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!consPunishTypeLowerFlag}">
					<!-- 负偏差允许范围（用电不足月申报电量阈值）  -->
			        <mk-form-col :label="formFields.lowerThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.lowerThreshold" :addon="{'show':true,'rightcontent':'%'}" name="lowerThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 负偏差惩罚电价基准（惩罚电价基准）  -->
			        <mk-form-col :label="formFields.lowerType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change="consLowerTypeChange" :select-value.sync="params.smAgrePunishCons.lowerType" name="lowerType" 
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
		            </mk-form-col>
					<!--  负偏差惩罚比例值 （惩罚比例值）  -->
			        <mk-form-col :label="formFields.lowerProp.label" label-width="200px" required_icon="true"
							v-if="!consLowerPrcFlag"	:class="{'display-none':!formFields.lowerProp.formHidden}" col="4" label-align="right">
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.lowerProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerProp"></su-textbox>
			        </mk-form-col>
			        <!-- 负偏差惩罚协议价 -->
			            <mk-form-col :label="formFields.consLowerPrc.label" label-width="200px" required_icon="true"
								v-if="consLowerPrcFlag" :class="{'display-none':!formFields.consLowerPrc.formHidden}" col="4" label-align="right">
			                <su-textbox type="number" :data.sync="params.smAgrePunishCons.lowerPrc" name="consLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>	
		<mk-panel title="售电公司违约惩罚方式" line="true">
			<mk-form-panel>
				<mk-form-row>
					<!-- 生效条件  -->
			        <mk-form-col :label="formFields.effectTerm.label" label-width="200px" required_icon="true"
								colspan="2" :class="{'display-none':!formFields.effectTerm.formHidden}" col="4" label-align="right">							
		                 <!-- <su-textbox disabled="disabled" :data.sync="compEffectTerm" name="compEffectTerm"></su-textbox> -->
		                 <div id="compEffectTerm" style="padding-top: 5px;height: 30px;"></div>
			        </mk-form-col>
			        <!-- 是否赔偿 -->
			        <mk-form-col :label="formFields.punishFlag.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishFlag.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change = "compPunishFlagChange"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_compensateFlag" :select-value.sync="params.smAgrePunishComp.punishFlag" name="punishFlag" ></su-select>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!compPunishFlag}">
					<!-- 购电不足月委托电量阈值  -->
			        <mk-form-col :label="formFields.compLowerThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.compLowerThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox type="number" :data.sync="params.smAgrePunishComp.lowerThreshold" :addon="{'show':true,'rightcontent':'%'}" name="compLowerThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 赔偿电价基准  -->
			        <mk-form-col :label="formFields.compLowerType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.compLowerType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change = "compLowerTypeChange" :select-value.sync="params.smAgrePunishComp.lowerType" name="compLowerType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
			        </mk-form-col>
					<!--  赔偿比例值  -->
			        <mk-form-col :label="formFields.compLowerProp.label" label-width="200px" required_icon="true"
								v-if="!compPunishFlag01" :class="{'display-none':!formFields.compLowerProp.formHidden}" col="4" label-align="right">
			            <su-textbox type="number" :data.sync="params.smAgrePunishComp.lowerProp" :addon="{'show':true,'rightcontent':'%'}" name="compLowerProp"></su-textbox>
			        </mk-form-col>
			        <!-- 赔偿协议价 -->
			            <mk-form-col :label="formFields.compLowerPrc.label" label-width="200px" required_icon="true"
									v-if="compPunishFlag01" :class="{'display-none':!formFields.compLowerPrc.formHidden}" col="4" label-align="right">
			                <su-textbox type="number" :data.sync="params.smAgrePunishComp.lowerPrc" name="compLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>	
	</su-form>
</div>
<script>
var basePath="${Config.baseURL}";
var bigdataUrl = basePath.replace(/\/[a-zA-Z0-9]*\/$/,"/ibd/");

var ppaAddVue = new Vue({
    el: '#ppaAddVue',
    data: {
    	uploadParams1:{groupId:""},		//上传组件的参数
	  	fileInfos: [], //多附件
	  	forecastResFlag : true, //负荷预测权限标识    	
	  	agrePrcFlag:'argePrc',//双边 保底协议价的验证规则
    	bidAgrePrcFlag:'bidArgePrc',//竞价 保底协议价的验证规则
    	saveFlag:"",
  	  	formFields:{},
  	  	mpGridShowFlag:true, //计量点表格展示标志
  	  	mpGridSuccess:false, //计量点表格初始化标志
  	    consLowerPrcFlag:false,//负偏差惩罚协议价展示标志
  		consUpperPrcFlag:false,//正偏差惩罚协议价展示标志
  		compPunishFlag:false,//售电公司惩罚展示标志
  		compPunishFlag01:false,//售电公司惩罚基准展示标志
  		consPunishTypeUpperFlag: false,//用户正偏差惩罚展示标志
  		consPunishTypeLowerFlag: false,//用户负偏差惩罚展示标志
  	  	consInfo:null, //用户信息
  	  	consEffectTerm:"电力用户交割电量不等于其市场电量",
  	  	compEffectTerm:"当售电公司为电力用户购得月度电量小于电力用户月度委托电量时",
  	  	discountLowerAmtFlag:false,//直接优惠下限金额编辑标志
  	  	url:basePath + "view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox",
  		modeList:[],//双边结算模式
	  	bidModeList:[],//竞价结算模式
  	  	//合同状态为默认添加 
  	  	
  	  	params:{smPpa:{id:null,consId:null,agreNo:null,agreName:null,agreType:null,agreStatus:"04",partyA:null,
  	  				partyB:null,signDate:null,effectiveDate:null,expiryDate:null,fileId:null,
  	  				proxyMode:null,proxyPq:null,distMode:null,jan:null,feb:null,mar:null,apr:null,
  	  				may:null,jun:null,jul:null,aug:null,sept:null,oct:null,nov:null,dece:null,recordDate:null},
  	  			smDistMode:{id:null,agreId:null,diviCode:null,settlementMode:null,agrePrc:null,partyBLcProp:null,partyALcProp:null,
  	  				partyBBidProp:null,profitMode:null,partyABidProp:null,companyId:null,diviType:null,diviValue:null,agent:null,bidDiviCode:null,bidSettlementMode:null,bidAgrePrc:null,bidDiviType:null,
  	  				bidDiviValue:null,bidAgent:null,bidProfitMode:null},
  	  			smAgreMp:[],
  	  			smAgrePunishCons:{id:null,agreId:null,punishTypeUpper:null,punishTypeLower:null,upperThreshold:null,upperType:null,upperProp:null,lowerThreshold:null,lowerType:null,
  	  			lowerProp:null,upperPrc:null,lowerPrc:null,punishFlag:null,companyId:null,departmentId:null},
  	  			smAgrePunishComp:{id:null,agreId:null,punishFlag:null,lowerThreshold:null,lowerType:null,lowerPrc:null,lowerProp:null,companyId:null,departmentId:null}
  	  	},
	  	dataOption: {
	  		rules: {
	  			consId:"required",
	  			agreName:"required",
	  			partyA:"required",
	  			partyB:"required",
	  			signDate:"required",
	  			effectiveDate:"required",
	  			expiryDate:"required",//失效时间
	  			distMode:"required",//分配
	  			proxyPq:{
	  				'required':true,
            		'min':0,
	  			},//代理总电量 
	  			diviCode:"required",//双边分成方式
	  			bidDiviCode:"required",//竞价分成方式
	  			diviType:"required",
	  			bidDdiviType:"required",
	  			settlementMode:"required",//双边结算模式
	  			bidSettlementMode:"required",//竞价结算模式
	  			diviValue:{
	  				'required':true,
	  				'max':9999.99
	  			},
	  			bidDiviValue:{
	  				'required':true,
	  				'max':9999.99
	  			},
	  			agent:{
	  				'required':true,
	  				'max':99999999.9999
	  			},
	  			bidAgent:{
	  				'required':true,
	  				'max':99999999.9999
	  			},
	  			agrePrc:{
	  				'required':true,
	  			},//保底协议价：价差模式
	  			agrePrc1:{
					'required':true,
	  				min:0,
	  			},//保底协议价：电价模式
	  			bidAgrePrc:{
	  				'required':true,
	  			},//竞价   保底协议价：价差模式
	  			bidAgrePrc1:{
					'required':true,
	  				min:0,
	  			},//竞价  保底协议价：电价模式
	  			profitMode:"required",//双边 收益模式选择
	  			bidProfitMode:"required",//竞价 收益模式选择
	  			pqSharingRatio:{
	  				'required':true,
            		'max':100,
            		'min':0,
	  			},//分成电量比例
	  			partyBLcProp:{
	  				'required':true,
	  				'max':100,
	  				'min':0
	  			},//售方长协分成比例
	  			partyALcProp:{
	  				'required':true,
	  				'max':100,
	  				'min':0
	  			},//用户长协分成比例
	  			partyBBidProp:{
	  				'required':true,
	  				'max':100,
	  				'min':0,
	  			},//售方竞价分成比例
	  			partyABidProp:{
	  				'required':true,
	  				'max':100,
	  				'min':0,
	  			},//用户竞价分成比例
	  			punishTypeUpper:"required",//正偏差惩罚类型
	  			punishTypeLower:"required",//负偏差惩罚类型
	  			upperThreshold:{
	  				'required':true,
	  				'max':100,
	  				'min':0,
	  			},//正偏差允许范围
	  			upperType:"required",//惩罚电价基准
	  			upperProp:{
	  				'required':true,
	  				'max':999.99,
	  				'min':0,
	  			},//惩罚比例值
	  			lowerThreshold:{
	  				'required':true,
	  				'max':100,
	  				'min':0,
	  			},//负偏差允许范围 
	  			lowerType:"required",//惩罚电价基准
	  			lowerProp:{
	  				'required':true,
	  				'max':999.99,
	  				'min':0,
	  			},//惩罚比例值
	  			consLowerPrc:"required",//负偏差惩罚协议价
	  			consUpperPrc:"required",//正偏差惩罚协议价
	  			punishFlag:"required",//是否赔偿
	  			compLowerThreshold:{
	  				'required':true,
	  				'max':100,
	  				'min':0,
	  			},//购电不足月委托电量
	  			compLowerType:"required",//赔偿电价基准
	  			compLowerProp:{
	  				'required':true,
	  				'max':999.99,
	  				'min':0,
	  			},//赔偿比例值
	  			compLowerPrc:"required",//赔偿协议价
	        }
	    },
	    valid: false,
    },
    ready:function(){
    	//双边分成方式
    	$("#remind").hide();
    	$("#diviCodeRemind").hide();
    	$("#divimode").hide();
    	$("#divi01").hide();
    	$("#divi0101").hide();
    	$("#divi0102").hide();
    	$("#divi0103").hide();
    	$("#divi02").hide();
    	$("#divi0201").hide();
    	$("#divi0301").hide();
    	$("#divi0302").hide();
    	//竞价分成方式
    	$("#bidRemind").hide();
    	$("#bidDiviCodeRemind").hide();
    	$("#bidDivimode").hide();
    	$("#bidDivi01").hide();
    	$("#bidDivi0101").hide();
    	$("#bidDivi0102").hide();
    	$("#bidDivi0103").hide();
    	$("#bidDivi02").hide();
    	$("#bidDivi0201").hide();
    	$("#bidDivi0301").hide();
    	$("#bidDivi0302").hide();
		//按钮初始化
		MainFrameUtil.setDialogButtons(this.getButtons(),"ppa-add");
		//查询用户有无负荷预测权限
		this.forecastRes(); 
  		//初始化表单
		ComponentUtil.getFormFields("selling.agreement.smPpa",this);
		this.initPpaAddGrid();
		this.mpGridShowFlag = false;
		this.params.smPpa.proxyMode = "01";
		this.getLogin();
		this.params.smPpa.recordDate = this.getDate();
		$("#consEffectTerm").html("电力用户交割电量不等于其市场电量");
		$("#compEffectTerm").html("当售电公司为电力用户购得月度电量小于电力用户月度委托电量时");
    },
    methods: {
		getButtons:function(){
			var buttons = [{text:"保存 ",type:"primary",handler:this.savePpaInfo},
			               {text:"取消 ",handler:function(){MainFrameUtil.closeDialog("ppa-add");}}];
			return buttons;
		},
		//查询登录用户有无负荷预测权限
		forecastRes: function(){
			var me = this;
			$.ajax({
				url : basePath+"cloud-purchase-web/smPpaController/forecastRes",
				type : "get",
				success : function(data){
					if(data.flag){
						//判断用户有无月度负荷预测按钮权限
	                	if(data.data == null || data.data == ''){
	                		me.forecastResFlag = false;
	                	}
					}
				} 
				
			})
		},
		//双边分成模式改变
		settleChange: function(){
			if(this.params.smDistMode.settlementMode == "02"){
				this.agrePrcFlag = 'agrePrc1';	
				$("#remind").hide();
			}else if(this.params.smDistMode.settlementMode == "03"){//按电量收取
				$("#divi0301").show();
				$("#divi0302").hide();
				$("#remind").hide();
			}else if(this.params.smDistMode.settlementMode == "04"){//一次性收取
				$("#divi0301").hide();
				$("#divi0302").show();
				$("#remind").hide();
			}else if(this.params.smDistMode.settlementMode == ""){
				$("#divi0301").hide();
				$("#divi0302").hide();
				$("#remind").hide();
			}else{
				this.agrePrcFlag = 'agrePrc';
				$("#remind").show();
			}	
			
		},//保底协议价验证
		//竞价分成模式改变
		bidSettleChange: function(){
			this.bidLoadFlag = this.bidLoadFlag+1;
			if(this.bidLoadFlag != 1){
				this.params.smDistMode.bidAgrePrc = null;
			}
			if(this.params.smDistMode.bidSettlementMode == "02"){
				this.bidAgrePrcFlag = 'bidAgrePrc1';	
				$("#bidRemind").hide();
			}else if(this.params.smDistMode.bidSettlementMode == "03"){//按电量收取
				$("#bidDivi0301").show();
				$("#bidDivi0302").hide();
				$("#bidRemind").hide();
			}else if(this.params.smDistMode.bidSettlementMode == "04"){//一次性收取
				$("#bidDivi0301").hide();
				$("#bidDivi0302").show();
				$("#bidRemind").hide();
			}else if(this.params.smDistMode.bidSettlementMode == ""){
				$("#bidDivi0301").hide();
				$("#bidDivi0302").hide();
				$("#bidRemind").hide();
			}else{
				this.bidAgrePrcFlag = 'bidAgrePrc';
				$("#bidRemind").show();
			}
		},//保底协议价验证
		consUpperTypeChange:function(){
			if(this.params.smAgrePunishCons.upperType == "01"){
				this.consUpperPrcFlag = true;
				this.params.smAgrePunishCons.upperProp = null;
			}else{
				this.consUpperPrcFlag = false;
				this.params.smAgrePunishCons.upperPrc = null;
			}
		},
		
		compPunishFlagChange:function(){
			if(this.params.smAgrePunishComp.punishFlag == 1){
				this.compPunishFlag = true;
			}else{
				this.compPunishFlag = false;
				this.params.smAgrePunishComp.lowerProp = null;
				this.params.smAgrePunishComp.lowerThreshold = null;
				this.params.smAgrePunishComp.lowerType = "";
			}
		},
		
		compLowerTypeChange:function(){
			if(this.params.smAgrePunishComp.lowerType == "01"){
				this.compPunishFlag01 = true;
				this.params.smAgrePunishComp.lowerProp = null;
			}else{
				this.compPunishFlag01 = false;
				this.params.smAgrePunishComp.lowerPrc = null;
			}
		},
		
		consLowerTypeChange:function(){
			if(this.params.smAgrePunishCons.lowerType == "01"){
				this.consLowerPrcFlag = true;
				this.params.smAgrePunishCons.lowerProp = null;
			}else{
				this.consLowerPrcFlag = false;
				this.params.smAgrePunishCons.lowerPrc = null;
			}
		},
		
		//获取当前登录人
        getLogin:function(){
        	this.params.smPpa.recorder = $.getLoginUserInfo(basePath).loginName;
        },
        
        //获取当前登录时间
        getDate:function(){
        	var date = new Date();
        	var month = date.getMonth() + 1;
        	var day = date.getDate();
        	if(month < 10){
        		month = "0" + month;
        	}
        	if(day < 10){
        		day = "0" + day;
        	}
        	return date.getFullYear() + "-" + month + "-" + day;
        },
      	//上传成功回调
        uploadSuccess: function(data){
        	$.messager.progress('close');  //关闭进度条
            var that = this;
            //复制一份文件列表
            var fileList = $.parseJSON($.toJSON(that.fileInfos));
            //去除新增的文件信息，
            for(var i = fileList.length - 1; i > -1 ; i-- ){
                if(fileList[i].genNew){   //如果是新增的文件，则删除
                    fileList.splice(i,1);
                }
            }
            if(data!=null && typeof(data) != "string" && !data.failure){
                for(var i = 0; i < data.length; i++){
                    var result=data[i];
                    var fileId = result.id;    //获取文件的id
                    var fileName=result.fileName+result.extension;//获取文件名
                    fileList.push({id: fileId, name: fileName});
                }
            }
            that.fileInfos.splice(0,that.fileInfos.length);
            //更新组件绑定的文件列表变量，并生成数据库用的String数据（多个附件用“?”隔开，每个附件的名字和id用“:”隔开）
            var fileInfoStr = "";
            for(var i = 0; i < fileList.length; i++){
                that.fileInfos.push(fileList[i]);
                if(i == 0){
                    fileInfoStr += fileList[i].id + ":" + fileList[i].name;
                }else{
                    fileInfoStr += "?" +fileList[i].id + ":" + fileList[i].name;
                }
            }
          	//验证是否选择并上传了文件,如果是则设置文件id和文件名
            if(data){
                ppaAddVue.params.smPpa.fileId = fileInfoStr;
                if(this.params.smPpa.id){
                	this.update();
                }else{
                	this.save();
                }
            }else{
            	 if(this.params.smPpa.id){
                 	this.update();
                 }else{
                 	this.save();
                 }
            }            
        },
        //上传失败回调
        uploadError:function(data){	 //文件上传失败执行的方法
        	$.messager.progress('close');  //关闭进度条
            MainFrameUtil.alert({title:"警告",body:'上传失败'});
        },
    	initPpaAddGrid:function(){
    		//初始化代理电量表格
    		$("#ppaAddGrid").datagrid({
				width:"100%",
				height:200,
				singleSelect:true,
			    nowrap: false,
				fitColumns:true,
				fit:true,
			    rowStyler:function(idx,row){
			        return "height:35px;font-size:12px;";
			    },
				columns:[[{field:'',title:'按月分配(兆瓦时)',align:'center',colspan:12}],
				        [{field:'jan',title:'1月',align:'left',width:12,
							editor:{id:"jan",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
				        },
						 {field:'feb',title:'2月',align:'left',width:12,
							editor:{id:"feb",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'mar',title:'3月',align:'left',width:12,
							editor:{id:"mar",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'apr',title:'4月',align:'left',width:12,
							editor:{id:"apr",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'may',title:'5月',align:'left',width:12,
							editor:{id:"may",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'jun',title:'6月',align:'left',width:12,
							editor:{id:"jun",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'jul',title:'7月',align:'left',width:12,
							editor:{id:"jul",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'aug',title:'8月',align:'left',width:12,
							editor:{id:"aug",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'sept',title:'9月',align:'left',width:12,
							editor:{id:"sept",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'oct',title:'10月',align:'left',width:12,
							editor:{id:"oct",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'nov',title:'11月',align:'left',width:12,
							editor:{id:"nov",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}
						 },
						 {field:'dece',title:'12月',align:'left',width:12,
							editor:{id:"dece",type:'numberbox',options:{precision:3,min:0}},
							formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}}]]
			});
    	},
    	initMpGrid:function(){
    		if(this.mpGridSuccess){
    			this.mpGridShowFlag = true;
    			$("#mpGrid").datagrid("options").queryParams = {consId:this.params.smPpa.consId,agreId:''};
    			$("#mpGrid").datagrid("load");
    		}else{
    			this.mpGridShowFlag = true;
    			this.mpGridSuccess = true;
        		//初始化用户计量点表格
        		$("#mpGrid").datagrid({
        			url:basePath+"cloud-purchase-web/smAgreMpController/getScMpInfoList",
        			method:"post",
        			queryParams:{consId:this.params.smPpa.consId,agreId:''},
    				width:"100%",
    				height:400,
    				singleSelect:true,
    			    nowrap: false,
    			    rownumbers: true,
    				fitColumns:true,
    				fit:true,
    			    rowStyler:function(idx,row){
    			        return "height:35px;font-size:12px;";
    			    },
    				columns:[[{field:'',title:'按计量点分配(兆瓦时)',align:'center',colspan:8}],
    				        [{field:'mpId',hidden:true,width:12},
    				         {field:'mpNo',title:'计量点编号',align:'left',width:12},
    						 {field:'voltCodeName',title:'接入电压等级',align:'left',width:12},
    						 {field:'meterNo',title:'电能表编号',align:'left',width:12},
    						 {field:'elecSortName',title:'用电类别',align:'left',width:12},
    						 {field:'catPrcName',title:'执行电价',align:'left',width:12},
    						 {field:'fluctuatesFlagName',title:'是否执行峰谷',align:'left',width:12},
    						 {field:'proxyPq',title:'协议电量（兆瓦时）',align:'left',width:12,
    							editor:{id:"proxyPq",type:'numberbox',options:{required:true}}}]],
    				onLoadSuccess:function(){
        	    		var rows = $("#mpGrid").datagrid("getRows");
        	    		for(var index in rows){
        	    			$("#mpGrid").datagrid("beginEdit",index);
        	    		}
            			$(window).resize();
    				}
    			});
    		}
    	},
		savePpaInfo:function(){
			this.$refs.form1.valid();
        	if(this.valid===false){
        		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
        		return;
        	}
        	if(this.params.smPpa.consId === null || this.params.smPpa.consId === ""){
				MainFrameUtil.alert({title:"提示",body:"请选择用户编号!"});
				return;
			}
            //验证是否选择文件
            if(this.$refs.uploadmulti.valid()){
            	$.messager.progress({title:"请等待",msg:"正在保存...",interval:100});
                this.$refs.uploadmulti.validAndUpload();
            }else{
                MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
                return;
            }
		},
		
		getProxyPq:function(){
			var editors = $("#ppaAddGrid").datagrid("getEditors",0);
			if(editors === null ||editors.length == 0){
				return true;
			}
			var num = 0;
			for(var obj in editors){
				if(!isNaN(Number($(editors[obj].target).val()))){
					num += Number($(editors[obj].target).val());
				}
			}
			if(num.toFixed(4) == Number(this.params.smPpa.proxyPq).toFixed(4)){
				$("#ppaAddGrid").datagrid("endEdit",0);
				$('#ppaAddGrid').datagrid('beginEdit',0);
				return true;
			}
			return false;
		},
		
    	save:function(){
    		var me = this;
    		if(me.params.smPpa.distMode == "02"){//手动分解
	    		//代理电量分月 与 合同日期是否匹配
				var agreStart = parseInt((me.params.smPpa.effectiveDate).substring(5,7));//开始日期
				var agreOver = parseInt((me.params.smPpa.expiryDate).substring(5,7));//失效日期
	    		var eds = $('#ppaAddGrid').datagrid('getEditors', 0);
				var map={"jan":"1","feb":"2","mar":"3","apr":"4","may":"5","jun":"6","jul":"7","aug":"8","sept":"9","oct":"10","nov":"11","dece":"12"};
				if(map[eds[0].field] != agreStart || map[eds[eds.length-1].field] != agreOver){
					MainFrameUtil.alert({title:"提示",body:"代理电量分配不合理！"});
	    			return;
				}
    		}
    		//取消编辑并验证分配是否合理
    		if(!this.getProxyPq()){
    			MainFrameUtil.alert({title:"提示",body:"代理电量分配不合理！"});
    			return;
    		}
    		//代理电量按月划分数据
    		var smPpa = this.params.smPpa;
    		var row = $("#ppaAddGrid").datagrid("getRows")[0];
    		for(var obj in row){
    			smPpa[obj] = row[obj];
    		}
    		//计量点代理电量划分
    		if(this.params.smPpa.proxyMode=='02'){
    			this.params.smAgreMp = this.getEditorData("mpGrid");
    		}
    		//验证合同开始日期与结束日期不能跨年
     		if(me.params.smPpa.effectiveDate.substr(0,4) != me.params.smPpa.expiryDate.substr(0,4)){
     			MainFrameUtil.alert({title:"提示",body:"合同开始日期和合同结束日期不允许跨年！"});
     			return;
     		}
        	//合同数据保存
     		$.messager.progress({title:"请等待",msg:"正在提交...",interval:100});
			//--MainFrameUtil.closeDialog("dealAdd");
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/smPpaController",
				type : 'post',
				data:$.toJSON(me.params),
				contentType : 'application/json;charset=UTF-8',
				success : function(result) {
					$.messager.progress('close');
					if(result.flag){
						MainFrameUtil.alert({title:"提示",body:"保存成功！"}); 
						ppaAddVue.params.smPpa.id = result.data.smPpa.id;
						ppaAddVue.params.smPpa.agreNo = result.data.smPpa.agreNo;
						ppaAddVue.params.smDistMode.id = result.data.smDistMode.id;
						ppaAddVue.params.smDistMode.agreId = result.data.smDistMode.agreId;
						ppaAddVue.params.smAgrePunishCons.id = result.data.smAgrePunishCons.id;
						ppaAddVue.params.smAgrePunishCons.agreId = result.data.smAgrePunishCons.agreId;
						ppaAddVue.params.smAgrePunishComp.id = result.data.smAgrePunishComp.id;
						ppaAddVue.params.smAgrePunishComp.agreId = result.data.smAgrePunishComp.agreId;
						if(result.data.smAgreMp !== null && result.data.smAgreMp.length != 0){
							$("#mpGrid").datagrid("loadData",result.data.smAgreMp);
						}
						ppaAddVue.saveFlag = "true";
					}else{
						MainFrameUtil.alert({title:"提示",body:result.msg});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"保存失败！"});
				}
			});
        	
    	},
    	//提交
		approval:function(){
			var that = this;
			if(that.params.smPpa.agreStatus == "01"){
				MainFrameUtil.alert({title:"提示",body:"该合同已提交！"});
				return;
			}
			that.savePpaInfo();
			if(ppaAddVue.saveFlag == "true"){
				$.ajax({
					url : "${Config.baseURL}cloud-purchase-web/smPpaController/approval?id=" + that.params.smPpa.id,
					type : 'get',
					success : function(result) {
						if(result.flag){
							if(result.msg == "请添加记录！" || result.msg == "该用户已存在正常合同信息！"){
								MainFrameUtil.alert({title:"成功提示",body:result.msg});
							}else{
								MainFrameUtil.alert({title:"成功提示",body:result.msg});
		                        MainFrameUtil.closeDialog("ppa-add");
							}
						}else{
							MainFrameUtil.alert({title:"失败提示",body:result.msg});
						}
					},
					error : function() {
						MainFrameUtil.alert({title:"失败提示",body:"请刷新页面重试！"});
					}
				});
			}
		},
    	update: function(){
    		var me = this;
    		//取消编辑并验证分配是否合理
    		if(!me.getProxyPq()){
    			MainFrameUtil.alert({title:"提示",body:"代理电量分配不合理！"});
    			return;
    		}
    		//代理电量按月划分数据
    		var smPpa = this.params.smPpa;
    		var row = $("#ppaAddGrid").datagrid("getRows")[0];
    		for(var obj in row){
    			smPpa[obj] = row[obj];
    		}
    		//计量点代理电量划分
    		if(this.params.smPpa.distMode=='02'){
        		var obj = new Array();
    			var mpDatas = $("#mpGrid").datagrid("getRows");
    			for(var key in mpDatas){
    				if(mpDatas[key].proxyPq){
            			obj.push({mpId:mpDatas[key].id,proxyPq:mpDatas[key].proxyPq});
    				}else{
    					MainFrameUtil.alert({title:"提示",body:"请填写计量点代理电量！"});
    					return;
    				}
    			}
    			this.params.smAgreMp = obj;
    		}
        	//合同数据保存
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/smPpaController",
				type : 'put',
				data:$.toJSON(this.params),
				contentType : 'application/json;charset=UTF-8',
				success : function(result) {
					if(result.flag){
						MainFrameUtil.alert({title:"成功提示",body:result.msg}); 
					}else{
						MainFrameUtil.alert({title:"失败提示",body:result.msg});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"失败提示",body:"请刷新页面重试！"});
				}
			});
    	},
    	distFunc:function(){
    		
    		//代理电量分解
    		var proxyPq = ppaAddVue.params.smPpa.proxyPq;
    		if(!proxyPq){
    			MainFrameUtil.alert({title:"提示",body:"请填写代理电量！"});
    		}
    		//自动分解
			var row = new Object();
    		var month = ["jan","feb","mar","apr","may","jun","jul","aug","sept","oct","nov","dece" ];
    		
    		var yearExpiry = ppaAddVue.params.smPpa.expiryDate;
    		//月份差计算
    		yearExpiry = yearExpiry.substr(0,4);
    		
    		
    		var yearEffective = ppaAddVue.params.smPpa.effectiveDate.substr(0,4);
    		var monthExpiry = ppaAddVue.params.smPpa.expiryDate.substr(5,2);
    		var	monthEffective = ppaAddVue.params.smPpa.effectiveDate.substr(5,2);
    		//var num = (yearExpiry-yearEffective)*12 + (monthExpiry - monthEffective);
    		var num = monthExpiry - monthEffective;		//不跨年，所以直接用月份相减
			var monPq = (proxyPq/(num+1)).toFixed(3);
			var lastMonPq = (proxyPq - monPq*num).toFixed(3);
			
			for(var i = 0 ; i < 12 ; i++){
				var mon = month[i];
				if((i+1) < monthEffective || (i+1) > monthExpiry ){
					row[mon] = null;
				}else{
					row[mon] = monPq;
				}
			}
			
			var lastMon = month[monthExpiry-1];
			row[lastMon] = lastMonPq;
			
			var rows = $("#ppaAddGrid").datagrid("getRows");
			if(rows.length==0){
				$('#ppaAddGrid').datagrid('appendRow', row); 
			}else{
				rows[0] = row;
				$('#ppaAddGrid').datagrid('refreshRow', 0); 
			}
    	},
    	getEditorData:function(id){
    		//获取未保存的datagrid数据
    		var rows = $("#"+id).datagrid("getRows");
    		for(var index in rows){
    			var editors = $("#"+id).datagrid("getEditors",index);
    			for(var key in editors){
    				if(editors[key].type == "combobox"){
    	    			rows[index][editors[key].field] = editors[key].target.combobox('getValue');
    				}else{
        				rows[index][editors[key].field] = $(editors[key].target).val();
    				}
    				if(rows[index][editors[key].field]==""||rows[index][editors[key].field]==null){
    					MainFrameUtil.alert({title:"提示",body:"请填写"+editors[key].title});
    					return;
    				}
    			}
    		}
    		return rows;
    	},
    	//负荷预测
    	forecast : function(){
    		if(!this.forecastResFlag){
    			MainFrameUtil.alert({title:"提示",body:" 您未购买此功能。"});
				return;
    		}
    		if(this.params.smPpa.consId === null || this.params.smPpa.consId === ""){
				MainFrameUtil.alert({title:"提示",body:"请选择用户名称!"});
				return;
			}
    		 if(this.params.smPpa.expiryDate == null || this.params.smPpa.expiryDate == "" || this.params.smPpa.effectiveDate == null || this.params.smPpa.effectiveDate == "" ){
     			MainFrameUtil.alert({title:"提示",body:"请填写合同开始日期和合同结束日期！"}); 
     			return;
 		    }
    		var me = this;
    		var year = (this.params.smPpa.expiryDate).substring(0,4);
    		var consId = me.params.smPpa.consId;
    		
    		$.ajax({
    			url : basePath+"smPpaController/forecast",
    			type : 'post',
				data:$.toJSON(me.params.smPpa),
				contentType : 'application/json;charset=UTF-8',
				success : function(data){
					if(data.data){
						//手动分解
						me.params.smPpa.distMode = "02";
						//代理电量分月赋值
						me.setRow(data.data);
						//代理总电量
						me.params.smPpa.proxyPq = data.data.total;
					}else{
						//人工核定没有数据 取大数据预测的
						$.ajax({
			    			url: bigdataUrl + "/powerFore/LongAssAction!predictLongAssDl.action?year="+year+"&consNo="+consId+"&av=b7b7596fffc91a2bc016c1128df857fd",
			    			type: 'get',
			    			success: function(data){
			    				if(data){
			    					var content={"01":"jan","02":"feb","03":"mar","04":"apr","05":"may","06":"jun","07":"jul","08":"aug","09":"sept","10":"oct","11":"nov","12":"dece"};
			    					//存预测数据
			    					var result = {};
			    					var num = null;
			    					for(var i in data){
			    						if(me.params.smPpa.effectiveDate.substring(5,7) <= i.substring(4,6) && i.substring(4,6) <= (me.params.smPpa.expiryDate).substring(5,7)){
			    							//预测数据
			    							result[content[i.substring(4,6)]]=data[i];
			    							//代理总电量
			    							num += data[i];
			    						}
			    					}
				    				//手动分解
									me.params.smPpa.distMode = "02";
									//代理电量分月赋值
									me.setRow(result);
									//代理总电量
									me.params.smPpa.proxyPq = num;
			    				}else{
									MainFrameUtil.alert({title:"提示",body:"预测失败！请先维护用户历史电量、用户生产计划等基础数据！"}); 
					     			return;
								}
			    			},
							error : function() {
								MainFrameUtil.alert({title:"提示",body:"网络异常，请刷新重试！"});
								return;
							}
			    		});
					}
				},error : function() {
					MainFrameUtil.alert({title:"提示",body:"网络异常，请刷新重试！"});
					return;
				}

    		});
    	},
    	setRow: function(content){
			var row = new Object();
			var rows = new Array();
			row["jan"] = content.jan;
			row["feb"] = content.feb;
			row["mar"] = content.mar;
			row["apr"] = content.apr;
			row["may"] = content.may;
			row["jun"] = content.jun;
			row["jul"] = content.jul;
			row["aug"] = content.aug;
			row["sept"] = content.sept;
			row["oct"] = content.oct;
			row["nov"] = content.nov;
			row["dece"] = content.dece;
			rows.push(row);
			$('#ppaAddGrid').datagrid('loadData',rows);
		},
    	valueChange:function(){
   			//01 自动分解
    		//var yearExpiry = ppaAddVue.params.smPpa.expiryDate;
    		if(ppaAddVue.params.smPpa.expiryDate ==  null){
				return;
    		}
   			if(this.params.smPpa.distMode == "01"){
       			var editors = $("#ppaAddGrid").datagrid("getEditors",0);
       			if(editors !== null && editors.length != 0){
       				$('#ppaAddGrid').datagrid('endEdit',0);
       			}
       			this.distFunc();  
       		}else if(this.params.smPpa.distMode == "02"){
       			//月份差计算
        		var yearExpiry = ppaAddVue.params.smPpa.expiryDate.substr(0,4);
        		var yearEffective = ppaAddVue.params.smPpa.effectiveDate.substr(0,4);
        		var monthExpiry = ppaAddVue.params.smPpa.expiryDate.substr(5,2);
        		var	monthEffective = ppaAddVue.params.smPpa.effectiveDate.substr(5,2);
        		var num = (yearExpiry-yearEffective)*12 + (monthExpiry - monthEffective);
        		var month = ["jan","feb","mar","apr","may","jun","jul","aug","sept","oct","nov","dece" ];
        		
        		var index = ppaAddVue.params.smPpa.effectiveDate.substr(5,2) - 1;
    			for(var i = 0 ; i < 12 ; i++){
    				var mon = month[i];
    				if(i>=index && i < index+num+1 ){
    					$('#ppaAddGrid').datagrid('getColumnOption', mon).editor={id:mon,type:'numberbox',options:{precision:3,min:0}};
    				}else{
						$('#ppaAddGrid').datagrid('getColumnOption', mon).editor = undefined;
    				}
    			}
        		
       			var rows = $("#ppaAddGrid").datagrid("getRows");
       			if(rows.length==0){
       				$('#ppaAddGrid').datagrid('appendRow',{});
       			}
       			$('#ppaAddGrid').datagrid('selectRow',0);
       			$('#ppaAddGrid').datagrid('beginEdit',0);  
       		}
    	}
    },
    watch:{
    	'params.smPpa.consId':function(){
    		ppaAddVue.consInfo = ppaAddVue.$refs.consinfo.$data.allattr;
    		if(this.params.smPpa.proxyMode == '02'){
    			if(this.params.smPpa.consId){
        			this.initMpGrid();
    			}else{
    				MainFrameUtil.alert({title:"警告",body:"请选择用户！"}); 
    			}
    		}else{
    			this.mpGridShowFlag = false;
    		}
    	},
    	'params.smPpa.proxyMode':function(){ 
    		//02 计量点代理
    		if(this.params.smPpa.proxyMode == '02'){
    			if(this.params.smPpa.consId){
        			this.initMpGrid();
    			}else{
    				MainFrameUtil.alert({title:"警告",body:"请选择用户！"}); 
    			}
    		}else{
    			this.mpGridShowFlag = false;
    		} 
    	},
    	"params.smAgrePunishCons.punishTypeUpper":function(){
    		if(this.params.smAgrePunishCons.punishTypeUpper == 1){//正偏差惩罚类型：不惩罚
    			this.consPunishTypeUpperFlag = true;
    		}else{
    			this.consPunishTypeUpperFlag = false;
    			this.params.smAgrePunishCons.upperThreshold = null;
    			this.params.smAgrePunishCons.upperType = "";
    			this.params.smAgrePunishCons.upperProp = null;
    		}
    	},
    	"params.smAgrePunishCons.punishTypeLower":function(){
    		if(this.params.smAgrePunishCons.punishTypeLower == 1){//负偏差惩罚类型：不惩罚
    			this.consPunishTypeLowerFlag = true;
    		}else{
    			this.consPunishTypeLowerFlag = false;
    			this.params.smAgrePunishCons.lowerThreshold = null;
    			this.params.smAgrePunishCons.lowerType = "";
    			this.params.smAgrePunishCons.lowerProp = null;
    		}
    	},
    	'params.smPpa.distMode':function(){    
    		if(this.params.smPpa.expiryDate == null || this.params.smPpa.expiryDate == "" || this.params.smPpa.effectiveDate == null || this.params.smPpa.effectiveDate == "" ){
    			MainFrameUtil.alert({title:"提示",body:"请填写合同开始日期和合同结束日期！"}); 
    			return;
			}
    		this.valueChange();
    	},
    	'params.smPpa.effectiveDate':function(){    
    		if(this.params.smPpa.expiryDate == null || this.params.smPpa.expiryDate == "" || this.params.smPpa.effectiveDate == null || this.params.smPpa.effectiveDate == "" ){
    			return;
			}
   			this.valueChange();
    	},
    	'params.smPpa.proxyPq':function(){    
    		if(this.params.smPpa.expiryDate == null || this.params.smPpa.expiryDate == "" || this.params.smPpa.effectiveDate == null || this.params.smPpa.effectiveDate == "" ){
    			MainFrameUtil.alert({title:"提示",body:"请填写合同开始日期和合同结束日期！"}); 
    			return;
			}
   			this.valueChange();
    	},
    	'params.smPpa.expiryDate':function(){    
    		if(this.params.smPpa.expiryDate == null || this.params.smPpa.expiryDate == "" || this.params.smPpa.effectiveDate == null || this.params.smPpa.effectiveDate == "" ){
    			return;
			}
   			this.valueChange();
    	},
    	'params.smDistMode.diviCode':function(){ 
    		var me = this;
    		var modeUrl = "";
    		//分成方式监控
    		switch(Number(me.params.smDistMode.diviCode))
    		{
	    		case 1://保底
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("用户电费以固定价格或价差结算，与市场价不联动。");
	    			$("#divi01").show();
	    			$("#divi0101").show();
	    			$("#divimode").show();
	    			$("#divi0102").hide();
	    			$("#divi0103").hide();
	    			$("#divi02").hide();
	    	    	$("#divi0201").hide();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    	    	if(this.params.smDistMode.settlementMode == null || this.params.smDistMode.settlementMode == ""){
	    	    		this.params.smDistMode.settlementMode = '02';
	    	    	}
	    			this.params.smDistMode.partyBLcProp = null;		//售方双边分成比例
	    			this.params.smDistMode.partyALcProp = null; 	//用户双边分成比例
	    			this.params.smDistMode.agent = null;			//代理服务费
	    			this.params.smDistMode.profitMode = "";			//收益模式
	    			me.params.smDistMode.diviType = "";				//分成基准
	    			me.params.smDistMode.diviValue = "";			//分成基准值
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 3://分成
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("用户按约定比例直接分享结算费用。");
	    			$("#divimode").hide();
	    			$("#divi01").show();
	    			$("#divi0101").hide();
	    			$("#divi0102").show();
	    			$("#divi02").show();
	    	    	$("#divi0201").hide();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    			if(me.params.smDistMode.diviType != "03"){
		    			$("#divi0103").hide();
	    			}
	    			if(me.params.smDistMode.diviType == null || me.params.smDistMode.diviType == ""){
	    	    		me.params.smDistMode.diviType = "01";
	    	    	}
	    	    	this.params.smDistMode.agent = null;			//代理服务费
	    			this.params.smDistMode.agrePrc = null;			//保底协议价
	    			this.params.smDistMode.profitMode = "";			//收益模式
	    			this.params.smDistMode.settlementMode = "";		//结算模式
	    			break;
	    		case 4://保底或分成
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("用户收益或售电公司收益为收益费用乘以分成比例与保底费用之中较大者。");
	    			$("#divimode").show();
	    			$("#divi01").show();
	    			$("#divi0101").show();
	    			$("#divi0102").show();
	    			$("#divi02").show();
	    	    	$("#divi0201").show();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    			if(me.params.smDistMode.diviType != "03"){
		    			$("#divi0103").hide();
	    			}
	    			if(me.params.smDistMode.diviType == null || me.params.smDistMode.diviType == ""){
	    	    		me.params.smDistMode.diviType = "01";
	    	    	}
	    			if(this.params.smDistMode.settlementMode == null || this.params.smDistMode.settlementMode == ""){
	    	    		this.params.smDistMode.settlementMode = '02';
	    	    	}
	    			this.params.smDistMode.agent = null;		 //代理服务费
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 5://固定保底加分成
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("用户获益不低于保底优惠，超过保底部分按约定比例额外分成。");
	    			$("#divimode").show();
	    			$("#divi01").show();
	    			$("#divi0101").show();
	    			$("#divi0102").hide();
	    			$("#divi0103").hide();
	    			$("#divi02").show();
	    	    	$("#divi0201").hide();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    	    	if(this.params.smDistMode.settlementMode == null || this.params.smDistMode.settlementMode == ""){
	    	    		this.params.smDistMode.settlementMode = '02';
	    	    	}
	    			me.params.smDistMode.profitMode = "";	//收益模式
	    			me.params.smDistMode.diviType = "" 		//分成基准
	    			me.params.smDistMode.diviValue = "";	//分成基准值
	    		    this.params.smDistMode.agent = null;	//代理服务费
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 7:// 联动保底加分成
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("用户所获保底收益随市场成交价格联动，低于约定价格用户享有所有收益，超过保底部分再进行额外分成。");
	    			$("#divimode").hide();
	    			$("#divi01").show();
	    			$("#divi0101").show();
	    			$("#divi0102").hide();
	    			$("#divi0103").hide();
	    			$("#divi02").show();
	    	    	$("#divi0201").hide();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    			me.params.smDistMode.settlementMode = "";	//结算模式
	    			me.params.smDistMode.profitMode = "";	//收益模式
	    			me.params.smDistMode.diviType = "" 		//分成基准
	    			me.params.smDistMode.diviValue = "";	//分成基准值
	    		    this.params.smDistMode.agent = null;	//代理服务费
	    			modeUrl = "";
	    			break;
	    		case 6://代理服务费
	    			$("#diviCodeRemind").show();
	    			$("#diviCodeRemindText").text("售电公司收取固定代理服务费用，或约定度电代理单价，按电量收取服务费用。");
	    			$("#divimode").show();
	    			$("#divi01").hide();
	    			$("#divi0101").hide();
	    			$("#divi0102").hide();
	    			$("#divi0103").hide();
	    			$("#divi02").hide();
	    	    	$("#divi0201").hide();
	    	    	$("#divi0301").hide();
	    	    	$("#divi0302").hide();
	    	    	if(this.params.smDistMode.settlementMode == null || this.params.smDistMode.settlementMode == ""){
	    	    		this.params.smDistMode.settlementMode = '02';
	    	    	}
	    	    	this.params.smDistMode.agrePrc = null;		//保底协议价
	    			this.params.smDistMode.profitMode = "";		//收益模式
	    			this.params.smDistMode.partyBLcProp = null; //售方双边分成比例
	    			this.params.smDistMode.partyALcProp = null; //用户双边分成比例
	    			me.params.smDistMode.diviType = "";			//分成基准
	    			me.params.smDistMode.diviValue = "";		//分成基准值
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=02";
	    			break;
	    		default:
	    			$("#diviCodeRemind").hide();
	    			$("#divimode").hide();
	    			$("#divi01").hide();
	    			$("#divi0101").hide();
	    			$("#divi0102").hide();
	    			$("#divi0103").hide();
		   			$("#divi02").hide();
		   	    	$("#divi0201").hide();
		   	    	$("#divi0301").hide();
		   	    	$("#divi0302").hide();
	    			this.params.smDistMode.agrePrc = null;
	    			this.params.smDistMode.partyBLcProp = null;	//售方双边分成比例
	    			this.params.smDistMode.partyALcProp = null; //用户双边分成比例
	    			this.params.smDistMode.agent = null;		//代理服务费
	    			this.params.smDistMode.profitMode = "";		//收益模式
	    			me.params.smDistMode.diviType = "";			//分成基准
	    			me.params.smDistMode.diviValue = "";		//分成基准值
	    			this.params.smDistMode.settlementMode = "";	//结算模式
	    			break;
    		}
    		if(modeUrl == ""){
    			me.modeList = [];
    		}else{
    			$.ajax({
     				url: modeUrl,
     				type:"get",
     				success:function(data){
     					me.modeList = data;
     				}
     			});
    		}
    	},
    	'params.smDistMode.diviType':function(){
    		var me = this;
    		if(me.params.smDistMode.diviType == "03"){
    			$("#divi0103").show();
    		}else{
    			$("#divi0103").hide();
    			me.params.smDistMode.diviValue == "";
    		}
    	},
    	'params.smDistMode.bidDiviCode': function(){ 
    		var me = this;
    		var modeUrl = "";
    		//分成方式监控
    		switch(Number(me.params.smDistMode.bidDiviCode))
    		{
	    		case 1://保底
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("用户电费以固定价格或价差结算，与市场价不联动。");
	    			$("#bidDivi01").show();
	    			$("#bidDivi0101").show();
	    			$("#bidDivimode").show();
	    			$("#bidDivi0102").hide();
	    			$("#bidDivi0103").hide();
	    			$("#bidDivi02").hide();
	    	    	$("#bidDivi0201").hide();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    	    	if(this.params.smDistMode.bidSettlementMode == null || this.params.smDistMode.bidSettlementMode == ""){
	    	    		this.params.smDistMode.bidSettlementMode = '02';
	    	    	}
	    			this.params.smDistMode.bidAgent = null;			//代理服务费
	    			this.params.smDistMode.partyBBidProp = null;						//售方竞价分成比例
	    			this.params.smDistMode.partyABidProp = null;						//用户竞价分成比例
	    			this.params.smDistMode.bidProfitMode = "";			//收益模式
	    			me.params.smDistMode.bidDiviType = "";				//分成基准
	    			me.params.smDistMode.bidDiviValue = "";			//分成基准值
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 3://分成
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("用户按约定比例直接分享结算费用。");
	    			$("#bidDivimode").hide();
	    			$("#bidDivi01").show();
	    			$("#bidDivi0101").hide();
	    			$("#bidDivi0102").show();
	    			$("#bidDivi02").show();
	    	    	$("#bidDivi0201").hide();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    			if(me.params.smDistMode.bidDiviType != "03"){
		    			$("#bidDivi0103").hide();
	    			}
	    			if(me.params.smDistMode.bidDiviType == null || me.params.smDistMode.bidDiviType == ""){
	    	    		me.params.smDistMode.bidDiviType = "01";
	    	    	}
	    	    	this.params.smDistMode.bidAgent = null;			//代理服务费
	    			this.params.smDistMode.bidAgrePrc = null;			//保底协议价
	    			this.params.smDistMode.bidProfitMode = "";			//收益模式
	    			this.params.smDistMode.bidSettlementMode = "";		//结算模式
	    			break;
	    		case 4://保底或分成
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("用户收益或售电公司收益为收益费用乘以分成比例与保底费用之中较大者。");
	    			$("#bidDivimode").show();
	    			$("#bidDivi01").show();
	    			$("#bidDivi0101").show();
	    			$("#bidDivi0102").show();
	    			$("#bidDivi02").show();
	    	    	$("#bidDivi0201").show();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    			if(me.params.smDistMode.diviType != "03"){
		    			$("#bidDivi0103").hide();
	    			}
	    			if(me.params.smDistMode.bidDiviType == null || me.params.smDistMode.bidDiviType == ""){
	    	    		me.params.smDistMode.bidDiviType = "01";
	    	    	}
	    			if(this.params.smDistMode.bidSettlementMode == null || this.params.smDistMode.bidSettlementMode == ""){
	    	    		this.params.smDistMode.bidSettlementMode = '02';
	    	    	}
	    			this.params.smDistMode.bidAgent = null;		 //代理服务费
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 5://固定保底加分成
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("用户获益不低于保底优惠，超过保底部分按约定比例额外分成。");
	    			$("#bidDivimode").show();
	    			$("#bidDivi01").show();
	    			$("#bidDivi0101").show();
	    			$("#bidDivi0102").hide();
	    			$("#bidDivi0103").hide();
	    			$("#bidDivi02").show();
	    	    	$("#bidDivi0201").hide();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    	    	if(this.params.smDistMode.bidSettlementMode == null || this.params.smDistMode.bidSettlementMode == ""){
	    	    		this.params.smDistMode.bidSettlementMode = '02';
	    	    	}
	    			me.params.smDistMode.bidProfitMode = "";	//收益模式
	    			me.params.smDistMode.bidDiviType = "" 		//分成基准
	    			me.params.smDistMode.bidDiviValue = "";	//分成基准值
	    		    this.params.smDistMode.bidAgent = null;	//代理服务费
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=01";
	    			break;
	    		case 7:// 联动保底加分成
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("用户所获保底收益随市场成交价格联动，低于约定价格用户享有所有收益，超过保底部分再进行额外分成。");
	    			$("#bidDivimode").hide();
	    			$("#bidDivi01").show();
	    			$("#bidDivi0101").show();
	    			$("#bidDivi0102").hide();
	    			$("#bidDivi0103").hide();
	    			$("#bidDivi02").show();
	    	    	$("#bidDivi0201").hide();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    	    	me.params.smDistMode.bidProfitMode = "";	//收益模式
	    			me.params.smDistMode.bidDiviType = "" 		//分成基准
	    			me.params.smDistMode.bidDiviValue = "";	//分成基准值
	    		    this.params.smDistMode.bidAgent = null;	//代理服务费
	    			me.params.smDistMode.bidSettlementMode = "";	//结算模式
	    			modeUrl = "";
	    			break;
	    		case 6://代理服务费
	    			$("#bidDiviCodeRemind").show();
	    			$("#bidDiviCodeRemindText").text("售电公司收取固定代理服务费用，或约定度电代理单价，按电量收取服务费用。");
	    			$("#bidDivimode").show();
	    			$("#bidDivi01").hide();
	    			$("#bidDivi0101").hide();
	    			$("#bidDivi0102").hide();
	    			$("#bidDivi0103").hide();
	    			$("#bidDivi02").hide();
	    	    	$("#bidDivi0201").hide();
	    	    	$("#bidDivi0301").hide();
	    	    	$("#bidDivi0302").hide();
	    	    	if(this.params.smDistMode.bidSettlementMode == null || this.params.smDistMode.bidSettlementMode == ""){
	    	    		this.params.smDistMode.bidSettlementMode = '02';
	    	    	}
	    	    	this.params.smDistMode.bidAgrePrc = null;		//保底协议价
	    			this.params.smDistMode.bidProfitMode = "";		//收益模式
	    			this.params.smDistMode.partyBBidProp = null;					//售方竞价分成比例
	    			this.params.smDistMode.partyABidProp = null;					//用户竞价分成比例
	    			me.params.smDistMode.bidDiviType = "";			//分成基准
	    			me.params.smDistMode.bidDiviValue = "";		//分成基准值
	    			modeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementModeCode&pCode.content1=02";
	    			break;
	    		default:
	    			$("#bidDiviCodeRemind").hide();
	    			$("#bidDivimode").hide();
	    			$("#bidDivi01").hide();
	    			$("#bidDivi0101").hide();
	    			$("#bidDivi0102").hide();
	    			$("#bidDivi0103").hide();
		   			$("#bidDivi02").hide();
		   	    	$("#bidDivi0201").hide();
		   	    	$("#bidDivi0301").hide();
		   	    	$("#bidDivi0302").hide();
	    			this.params.smDistMode.bidAgrePrc = null;
	    			this.params.smDistMode.bidAgent = null;		//代理服务费
	    			this.params.smDistMode.partyBBidProp = null;					//售方竞价分成比例
	    			this.params.smDistMode.partyABidProp = null;					//用户竞价分成比例
	    			this.params.smDistMode.bidProfitMode = "";		//收益模式
	    			me.params.smDistMode.bidDiviType = "";			//分成基准
	    			me.params.smDistMode.bidDiviValue = "";		//分成基准值
	    			this.params.smDistMode.bidSettlementMode = "";	//结算模式
	    			break;
    		}
    		if(modeUrl == ""){
    			me.bidModeList = [];
    		}else{
    			$.ajax({
     				url:modeUrl,
     				type:"get",
     				success:function(data){
     					me.bidModeList = data;
     				}
     			});
    		}
    	},
    	'params.smDistMode.bidDiviType':function(){
    		var me = this;
    		if(me.params.smDistMode.bidDiviType == "03"){
    			$("#bidDivi0103").show();
    		}else{
    			$("#bidDivi0103").hide();
    			me.params.smDistMode.bidDiviValue == "";
    		}
    	},
    	'params.smDistMode.partyBLcProp':function(){
    		if(this.params.smDistMode.partyBLcProp){
    			this.params.smDistMode.partyALcProp = 100 - parseFloat(this.params.smDistMode.partyBLcProp);
    		}
    	},
    	'params.smDistMode.partyBBidProp':function(){
    		if(this.params.smDistMode.partyBBidProp){
    			this.params.smDistMode.partyABidProp = 100 - parseFloat(this.params.smDistMode.partyBBidProp);
    		}
    	}
    	
    }
}); 
</script>
</body>

</html>
	