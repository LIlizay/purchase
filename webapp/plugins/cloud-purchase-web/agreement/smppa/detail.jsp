<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>合同管理-售电合同管理详情</title>
  <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
  <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
	#consPunishFlag{
		margin:15px 0px 0px 70px
	}
	/* 隐藏多附件的图标 */
		.mk-fileupload-btn{
			display: none;
		}
</style>
</head>
<body>
<div id="ppaDetailVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="用户信息" line="true">
			<mk-form-panel >
				<mk-form-row>
					<!-- 用户名称 -->
		            <mk-form-col :label="formFields.consName.label" required_icon="true" label-width="200px"
								:class="{'display-none':!formFields.consName.formHidden}" col="4" label-align="right">
		                <mk-selectbox disabled="disabled" :url="url" height="500" width="800" textfield="consName" propname="id" 
		                		:propvalue.sync="params.smPpa.consId" :cacheurl="urlCache" :theobj.sync="consInfo" v-ref:consinfo name="consId"></mk-selectbox>
		            </mk-form-col>
		            <mk-form-col :label="formFields.voltCodeName.label" label-width="200px"
								:class="{'display-none':!formFields.voltCodeName.formHidden}" col="4" label-align="right">
		                <su-textbox disabled="disabled" :data.sync="consInfo.voltCodeName"></su-textbox>
		            </mk-form-col>
					<!-- 用电类别  -->
		            <mk-form-col :label="formFields.elecTypeName.label" label-width="200px"
								:class="{'display-none':!formFields.elecTypeName.formHidden}" col="4" label-align="right">
						<su-textbox disabled="disabled" :data.sync="consInfo.elecTypeName"></su-textbox>
		            </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 用电容量  -->
		             <mk-form-col :label="formFields.electricCapacity.label" label-width="200px"
								:class="{'display-none':!formFields.electricCapacity.formHidden}" col="4" label-align="right">
		                 <su-textbox type="number" disabled="disabled" :data.sync="consInfo.electricCapacity" :addon="{'show':true,'rightcontent':'kVA'}"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="合同基本信息" line="true">
			<mk-form-panel>
				<mk-form-row>
					<!-- 合同编号  -->
		             <mk-form-col :label="formFields.agreNo.label" label-width="200px"
								:class="{'display-none':!formFields.agreNo.formHidden}" col="4" label-align="right">
		                 <su-textbox disabled="disabled" :data.sync="params.smPpa.agreNo"></su-textbox>
		             </mk-form-col>
					<!-- 合同名称  -->
		             <mk-form-col :label="formFields.agreName.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.agreName.formHidden}" col="4" label-align="right">
		                 <su-textbox disabled="disabled" :data.sync="params.smPpa.agreName" name="agreName"></su-textbox>
		             </mk-form-col>
					<!-- 合同类型  -->
		             <%-- <mk-form-col :label="formFields.agreType.label" label-width="200px"
								:class="{'display-none':!formFields.agreType.formHidden}" col="4" label-align="right">
		                 <su-select disabled="disabled" :high="150" label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellElecContractType" :select-value.sync="params.smPpa.agreType" @su-change="" name="agreType"></su-select>
		             </mk-form-col> --%>
				</mk-form-row>
				<mk-form-row>
					<!-- 生效日期  -->
		             <mk-form-col :label="formFields.effectiveDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.effectiveDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker disabled="disabled" format="YYYY-MM" :data.sync="params.smPpa.effectiveDate" name="effectiveDate"></su-datepicker>
		             </mk-form-col>
					<!-- 失效日期  -->
		             <mk-form-col :label="formFields.expiryDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.expiryDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker disabled="disabled" format="YYYY-MM" :data.sync="params.smPpa.expiryDate" name="expiryDate"></su-datepicker>
		             </mk-form-col>
		             <!-- 签订时间  -->
		             <mk-form-col :label="formFields.signDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.signDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker disabled="disabled" format="YYYY-MM-DD" :data.sync="params.smPpa.signDate" name="signDate"></su-datepicker>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 售电方签约人  -->
		            <mk-form-col :label="formFields.partyB.label" label-width="200px" required_icon="true" col="4" label-align="right">
		            	<su-textbox disabled="disabled" :data.sync="params.smPpa.partyB" name="partyB"></su-textbox>
		            </mk-form-col>
					<!-- 客户签约人  -->
		             <mk-form-col :label="formFields.partyA.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-textbox disabled="disabled" :data.sync="params.smPpa.partyA" name="partyA"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row height="auto">
		             <!-- 附件 -->
					<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" required_icon="true" colspan="3">
						<mk-multifile-upload :params="uploadParams1" :files.sync="fileInfos"   v-ref:uploadmulti  show_upload="false"   @upload-error="uploadError" 
		                disabled="disabled" @upload-success="uploadSuccess"  url='${Config.getConfig("file.service.url")}' required="true" maxcount=20>
		                  </mk-multifile-upload>
					</mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="代理电量管理" line="true" height="auto" >
			<mk-form-panel num="2">
				<mk-form-row>
					<!-- 代理总电量  -->
		            <mk-form-col :label="formFields.proxyPq.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox disabled="disabled" :data.sync="params.smPpa.proxyPq" name="proxyPq" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		            </mk-form-col>
					<!-- 分配方式  -->
		            <mk-form-col :label="formFields.distMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
			            <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_dividedType" :select-value.sync="params.smPpa.distMode" @su-change="" name="distMode" ></su-select>
		            </mk-form-col>
				</mk-form-row>  
			</mk-form-panel>
			<div id="ppaDetailGrid" style="height:100%"></div>
		</mk-panel>				
		<mk-panel height="300px" header="false" :class="{'display-none':!mpGridShowFlag}">
			<div id="mpGrid" style="height:100%"></div>
		</mk-panel>	
		<mk-panel title="双边电量分成方式" line="true">
			<mk-form-panel label_width="150px">
				<mk-form-row>
					<!-- 分成方式 -->
					<mk-form-col :label="formFields.diviCode.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                 <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideWayCode" :select-value.sync="params.smDistMode.diviCode" name="diviCode" >
		                 </su-select>	
		            </mk-form-col>
		            <!-- 结算模式  -->
					<div id = "divimode" style="display:inline">
			            <mk-form-col :label="formFields.settlementMode.label" label-width="200px" required_icon="true" col="4" label-align="right" >
			                 <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_settlementModeCode" @su-change="settleChange" :select-value.sync="params.smDistMode.settlementMode" name="settlementMode" ></su-select>
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
				                <su-textbox disabled="disabled" :data.sync="params.smDistMode.agrePrc" name="agrePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
			            </div>
			             <!-- 分成基准 -->
			            <div id="divi0102" style="display:inline">
				            <mk-form-col :label="formFields.dividType.label" label-width="200px" required_icon="true" col="4" label-align="right">
				               <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideType" :select-value.sync="params.smDistMode.diviType" name="diviType" ></su-select>
				            </mk-form-col>
			            </div>
			            <!-- 分成基准值 -->
			            <div id="divi0103" style="display:inline">
				            <mk-form-col :label="formFields.dividValue.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.diviValue" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
			            </div>
					</mk-form-row>
		       </div>
		       <!-- 代理服务费相关字段 -->
				<mk-form-row id="divi0301">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.agent" :addon.sync="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row id="divi0302">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.agent" :addon.sync="{'show':true,'rightcontent':'元'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
		        <!-- 分成选项相关字段 -->
		        <div id="divi02">
		        	<mk-form-row>
		        		<!-- 收益模式选择 -->
						<div id="divi0201" style="display:block">
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                 <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" :select-value.sync="params.smDistMode.profitMode" name="profitMode" >
				                 </su-select>
				            </mk-form-col>
						</div>
						<!-- 售电公司双边分成比例  -->
				        <mk-form-col :label="formFields.partyBLcProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
				            <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.partyBLcProp" name="partyBLcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
				        <!-- 用户双边分成比例 -->
			            <mk-form-col :label="formFields.partyALcProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
			                <su-textbox disabled="disabled" type="number" disabled="disabled" :data.sync="params.smDistMode.partyALcProp" name="partyALcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
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
		                 <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideWayCode" :select-value.sync="params.smDistMode.bidDiviCode" name="bidDiviCode" >
		                 </su-select>	
		            </mk-form-col>
					<!-- 结算模式  -->
					<div id = "bidDivimode" style="display:inline">
			            <mk-form-col :label="formFields.settlementMode.label" label-width="200px" required_icon="true" col="4" label-align="right" >
			                 <su-select disabled="disabled" label-name="name" :data-source.sync="bidModeList"  @su-change="bidSettleChange" :select-value.sync="params.smDistMode.bidSettlementMode" name="bidSettlementMode" ></su-select>
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
				                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.bidAgrePrc" :name="bidAgrePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
						</div>
			            <!-- 分成基准 -->
			            <div id="bidDivi0102" style="display:inline">
				            <mk-form-col :label="formFields.dividType.label" label-width="200px" required_icon="true" col="4" label-align="right">
				               <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideType" :select-value.sync="params.smDistMode.bidDiviType" name="bidDiviType" ></su-select>
				            </mk-form-col>
			            </div>
			            <!-- 分成基准值 -->
			            <div id="bidDivi0103" style="display:inline">
				            <mk-form-col :label="formFields.dividValue.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.bidDiviValue" name="bidDiviValue" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
				            </mk-form-col>
			            </div>
					</mk-form-row>
				</div>
				<!-- 代理服务费相关字段 -->
				<mk-form-row id="bidDivi0301">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.bidAgent" name="bidAgent" :addon.sync="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row id="bidDivi0302">
					<mk-form-col :label="formFields.agent.label" label-width="200px" required_icon="true" col="4" label-align="right">
		                <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.bidAgent" name="bidAgent" :addon.sync="{'show':true,'rightcontent':'元'}"></su-textbox>
			        </mk-form-col>
				</mk-form-row>
				<!-- 分成选项相关字段 -->
				<div id="bidDivi02">
					<mk-form-row>
						<!-- 收益模式选择 -->
						<div id="bidDivi0201" style="display:block">
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true" col="4" label-align="right">
				                 <su-select disabled="disabled" label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" 
				                 		:select-value.sync="params.smDistMode.bidProfitMode" name="bidProfitMode" >
				                 </su-select>
				            </mk-form-col>
						</div>
		        		 <!-- 售电公司竞价分成比例  -->
				        <mk-form-col :label="formFields.partyBBidProp.label" label-width="220px" required_icon="true"  col="4" label-align="right" >
				            <su-textbox disabled="disabled" type="number" :data.sync="params.smDistMode.partyBBidProp" name="partyBBidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
				         <!-- 用户竞价分成比例 -->
			            <mk-form-col :label="formFields.partyABidProp.label" label-width="220px" required_icon="true" col="4" label-align="right" >
			                <su-textbox disabled="disabled" type="number" disabled="disabled" :data.sync="params.smDistMode.partyABidProp" name="partyABidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
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
					<!--  正偏差惩罚类型   -->
			        <mk-form-col :label="formFields.punishTypeUpper.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishTypeUpper.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" disabled="disabled"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishTypeUpper" name="punishTypeUpper" ></su-select>
			        </mk-form-col>
			        <!--  售方不罚用户不罚 -->
			        <!-- <input type="checkbox" id="consPunishFlag" value="consPunishFlag" name="consPunishFlag"> 售方不罚用户不罚</input> -->
				</mk-form-row>
				<mk-form-row :class="{'display-none':!consPunishTypeUpperFlag}">
					<!-- 用电超出月申报电量阈值  -->
			        <mk-form-col :label="formFields.upperThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.upperThreshold" :addon="{'show':true,'rightcontent':'%'}" name="upperThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 惩罚电价基准  -->
			        <mk-form-col :label="formFields.upperType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperType.formHidden}" col="4" label-align="right">							
		                 <su-select disabled="disabled" label-name="name" @su-change="consUpperTypeChange" :select-value.sync="params.smAgrePunishCons.upperType" name="upperType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
			        </mk-form-col>
					<!--  惩罚比例值  -->
			        <mk-form-col :label="formFields.upperProp.label" label-width="200px" required_icon="true"
								v-if="!consUpperPrcFlag" :class="{'display-none':!formFields.upperProp.formHidden}" col="4" label-align="right">
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.upperProp" :addon="{'show':true,'rightcontent':'%'}" name="upperProp"></su-textbox>
			        </mk-form-col>
			        <!-- 正偏差惩罚协议价 -->
			            <mk-form-col :label="formFields.consUpperPrc.label" label-width="200px" required_icon="true"
									v-if="consUpperPrcFlag" :class="{'display-none':!formFields.consUpperPrc.formHidden}" col="4" label-align="right">
			                <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.upperPrc" name="consUpperPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 合同电量负偏差  -->
			        <mk-form-col :label="formFields.punishTypeLower.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishTypeLower.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" disabled="disabled"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishTypeLower" name="punishTypeLower" ></su-select>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!consPunishTypeLowerFlag}">
					<!-- 用电不足月申报电量阈值  -->
			        <mk-form-col :label="formFields.lowerThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.lowerThreshold" :addon="{'show':true,'rightcontent':'%'}" name="lowerThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 惩罚电价基准  -->
			        <mk-form-col :label="formFields.lowerType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerType.formHidden}" col="4" label-align="right">							
		                 <su-select disabled="disabled" label-name="name" @su-change="consLowerTypeChange" :select-value.sync="params.smAgrePunishCons.lowerType" name="lowerType" 
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
		            </mk-form-col>
					<!--  惩罚比例值  -->
			        <mk-form-col :label="formFields.lowerProp.label" label-width="200px" required_icon="true"
							v-if="!consLowerPrcFlag"	:class="{'display-none':!formFields.lowerProp.formHidden}" col="4" label-align="right">
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.lowerProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerProp"></su-textbox>
			        </mk-form-col>
			        <!-- 负偏差惩罚协议价 -->
			            <mk-form-col :label="formFields.consLowerPrc.label" label-width="200px" required_icon="true"
								v-if="consLowerPrcFlag" :class="{'display-none':!formFields.consLowerPrc.formHidden}" col="4" label-align="right">
			                <su-textbox disabled="disabled" :data.sync="params.smAgrePunishCons.lowerPrc" name="consLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
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
		                 <su-select disabled="disabled" label-name="name" @su-change = "compPunishFlagChange"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_compensateFlag" :select-value.sync="params.smAgrePunishComp.punishFlag" name="punishFlag" ></su-select>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!compPunishFlag}">
					<!-- 购电不足月委托电量阈值  -->
			        <mk-form-col :label="formFields.compLowerThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.compLowerThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishComp.lowerThreshold" :addon="{'show':true,'rightcontent':'%'}" name="compLowerThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 赔偿电价基准  -->
			        <mk-form-col :label="formFields.compLowerType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.compLowerType.formHidden}" col="4" label-align="right">							
		                 <su-select disabled="disabled" label-name="name" @su-change = "compLowerTypeChange" :select-value.sync="params.smAgrePunishComp.lowerType" name="compLowerType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc"></su-select>
			        </mk-form-col>
					<!--  赔偿比例值  -->
			        <mk-form-col :label="formFields.compLowerProp.label" label-width="200px" required_icon="true"
								v-if="!compPunishFlag01" :class="{'display-none':!formFields.compLowerProp.formHidden}" col="4" label-align="right">
			            <su-textbox disabled="disabled" :data.sync="params.smAgrePunishComp.lowerProp" :addon="{'show':true,'rightcontent':'%'}" name="compLowerProp"></su-textbox>
			        </mk-form-col>
			        <!-- 赔偿协议价 -->
			            <mk-form-col :label="formFields.compLowerPrc.label" label-width="200px" required_icon="true"
									v-if="compPunishFlag01" :class="{'display-none':!formFields.compLowerPrc.formHidden}" col="4" label-align="right">
			                <su-textbox disabled="disabled" :data.sync="params.smAgrePunishComp.lowerPrc" name="compLowerPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>	
	</su-form>
</div>
<script>
var basePath="${Config.baseURL}";
var ppaDetailVue = new Vue({
    el: '#ppaDetailVue',
    data: {
    	formFields:{},
    	uploadParams1:{groupId:""},		//上传组件的参数
	  	fileInfos: [], //多附件
  	  	fileName:"",
  	  	mpGridShowFlag:true, //计量点表格展示标志
	  	mpGridSuccess:false, //计量点表格初始化标志
	    consLowerPrcFlag:false,//负偏差惩罚协议价展示标志
		consUpperPrcFlag:false,//正偏差惩罚协议价展示标志
		compPunishFlag:false,//售电公司惩罚展示标志
		compPunishFlag01:false,//售电公司惩罚基准展示标志
  		consPunishTypeFlag:false,//用户惩罚展示标志
  		consPunishTypeUpperFlag: false,//用户正偏差惩罚展示标志
  		consPunishTypeLowerFlag: false,//用户负偏差惩罚展示标志
		consInfo:{}, //用户信息
		consEffectTerm:"电力用户交割电量不等于其市场电量",
  	  	compEffectTerm:"当售电公司为电力用户购得月度电量小于电力用户月度委托电量时",
	  	discountLowerAmtFlag:false,//直接优惠下限金额编辑标志
	  	url:basePath + "view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox",
	  	modeList:[],//双边结算模式
	  	bidModeList:[],//竞价结算模式
  	  	urlCache:"",
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
  	  			lowerProp:null,upperPrc:null,lowerPrc:null,punishFlag:null,companyId:null},
  	  			smAgrePunishComp:{id:null,agreId:null,punishFlag:null,lowerThreshold:null,lowerType:null,lowerPrc:null,lowerProp:null,companyId:null}
  	  	},
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
		MainFrameUtil.setDialogButtons(this.getButtons(),"ppa-detail");
  		//初始化表单
		ComponentUtil.getFormFields("selling.agreement.smPpa",this);
 		this.initPpaDetailGrid();
		this.mpGridShowFlag = false;
    },
    methods: {
		getButtons:function(){
			var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("ppa-detail");}}];
			return buttons;
		},
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
		distFunc:function(){
			//代理电量分解
    		var proxyPq = this.params.smPpa.proxyPq;
    		if(!proxyPq){
    			MainFrameUtil.alert({title:"提示",body:"请填写代理电量！"});
    		}
    		//自动分解
			var row = new Object();
			var month = ["jan","feb","mar","apr","may","jun","jul","aug","sept","oct","nov","dece" ];
    		//月份差计算
    		var monthExpiry = this.params.smPpa.expiryDate.substr(5,2);
    		var	monthEffective = this.params.smPpa.effectiveDate.substr(5,2);
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
			var rows = $("#ppaDetailGrid").datagrid("getRows");
			if(rows.length==0){
				$('#ppaDetailGrid').datagrid('appendRow', row); 
			}else{
				rows[0] = row;
				$('#ppaDetailGrid').datagrid('refreshRow', 0); 
			}
    	},
		initData:function(id){
			var me = this;
			//初始化数据
			$.ajax({
				url : '${Config.baseURL}cloud-purchase-web/smPpaController/'+id,
				type : 'get',
				success : function(result) {
					if(result.flag){
						if(result.data){
							if(result.data.smPpa !== null){
								if(result.data.smPpa.distMode != "01"){
									me.setRow(result.data.smPpa);
								}
							}
							me.params.smDistMode = result.data.smDistMode;
							me.params.smAgrePunishCons = result.data.smAgrePunishCons;
							me.params.smAgrePunishComp = result.data.smAgrePunishComp;
							if(result.data.smDistMode != null){
								//竞价分成方式
								var bidDdiviCode = result.data.smDistMode.bidDiviCode;
								if(bidDdiviCode == "03" || bidDdiviCode == "04" ||bidDdiviCode == "05"){
									me.partyBBidProp = result.data.smDistMode.partyBBidProp;
									me.partyABidProp = result.data.smDistMode.partyABidProp;
								}
							}
							if(result.data.smPpa !==  null && result.data.smPpa.consId !== null && result.data.smPpa.consId !== ""){
								me.urlCache = "${Config.baseURL}cloud-purchase-web/sConsDialogController/getConsInfoById/"+result.data.smPpa.consId;
							}
							if(result.data.smPpa!== null && result.data.smPpa.fileId){
								//单附件和多附件保存格式不同的
								try{
	          						if(eval('(' +result.data.smPpa.fileId + ')').fileName != null ||  eval('(' +result.data.smPpa.fileId + ')').fileName != ''){ //不是单附件的格式
		          						me.fileInfos.push({id :  eval('(' +result.data.smPpa.fileId + ')').fileId , name :  eval('(' +result.data.smPpa.fileId + ')').fileName});
	          						}	
          						}catch(err){ //单附件的格式
          							var attachments = (result.data.smPpa.fileId).split("?");
									for(var i=0; i < attachments.length; i++){
										var attachment = attachments[i];
										var fields = attachment.split(":");
										if(fields.length == 2){
											me.fileInfos.push({id : fields[0] , name : fields[1]});
										}									
									}
          						}	
								
							}
							if(result.data.smAgrePunishCons !== null && result.data.smAgrePunishCons.punishFlag !== null && result.data.smAgrePunishCons.punishFlag !== ""){
								if(result.data.smAgrePunishCons.punishFlag == 1){
									$("#consPunishFlag").attr("checked","checked");
								}
							}
							me.params.smPpa = result.data.smPpa;
						}
					}
				}
			});
		},
		
		setRow:function(content){
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
			$('#ppaDetailGrid').datagrid('loadData',rows);
		},
		
    	initPpaDetailGrid:function(){
    		//初始化代理电量表格
    		$("#ppaDetailGrid").datagrid({
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
				        	formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	
				        },
						 {field:'feb',title:'2月',align:'left',width:12,
				        	formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	
						 },
						 {field:'mar',title:'3月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'apr',title:'4月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'may',title:'5月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'jun',title:'6月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'jul',title:'7月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'aug',title:'8月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'sept',title:'9月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'oct',title:'10月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'nov',title:'11月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 },
						 {field:'dece',title:'12月',align:'left',width:12,
							 formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	           			  	}	 
						 }]]
			});
    	},
    	//双边分成模式改变
    	settleChange: function(){
			if(this.params.smDistMode.settlementMode == "02"){
				$("#remind").hide();
			}else if(this.params.smDistMode.settlementMode == "01" && this.params.smDistMode.diviCode == "03"){
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
				$("#remind").show();
			}	
		},
		
		//竞价分成模式改变
		bidSettleChange: function(){
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
		
    	initMpGrid:function(){
    		if(this.mpGridSuccess){
    			this.mpGridShowFlag = true;
    			$("#mpGrid").datagrid("options").queryParams = {consId:this.params.smPpa.consId,agreId:this.params.smPpa.id};
    			$("#mpGrid").datagrid("load");
    		}else{
    			this.mpGridShowFlag = true;
    			this.mpGridSuccess = true;
        		//初始化用户计量点表格
        		$("#mpGrid").datagrid({
        			url:basePath+"cloud-purchase-web/smAgreMpController/getScMpInfoList",
        			method:"post",
        			queryParams:{consId:this.params.smPpa.consId,agreId:this.params.smPpa.id},
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
    				        [{field:'id',hidden:true,width:12},
    				         {field:'mpNo',title:'计量点编号',align:'left',width:12},
    						 {field:'voltCodeName',title:'接入电压等级',align:'left',width:12},
    						 {field:'meterNo',title:'电能表编号',align:'left',width:12},
    						 {field:'elecSortName',title:'用电类别',align:'left',width:12},
    						 {field:'catPrcName',title:'执行电价',align:'left',width:12},
    						 {field:'fluctuatesFlagName',title:'是否执行峰谷',align:'left',width:12},
    						 {field:'proxyPq',title:'协议电量（兆瓦时）',align:'left',width:12,
    							editor:{id:"proxyPq",type:'numberbox',options:{required:true}}}]],
    				onLoadSuccess:function(){
            			$(window).resize();
    				}
    			});
    		}
    	}
    },
    watch:{
    	'params.smPpa.consId':function(){
    		this.consInfo = this.$refs.consinfo.$data.allattr;
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
    			$("#consPunishFlag").attr("disabled",false);
    			this.consPunishTypeUpperFlag = true;
    		}else{
    			$("#consPunishFlag").attr("checked",false);
    			$("#consPunishFlag").attr("disabled","disabled");
    			this.consPunishTypeUpperFlag = false;
    			this.params.smAgrePunishCons.upperThreshold = null;
    			this.params.smAgrePunishCons.upperType = "";
    			this.params.smAgrePunishCons.upperProp = null;
    		}
    	},
    	"params.smAgrePunishCons.punishTypeLower":function(){
    		if(this.params.smAgrePunishCons.punishTypeLower == 1){//负偏差惩罚类型：不惩罚
    			$("#consPunishFlag").attr("disabled",false);
    			this.consPunishTypeLowerFlag = true;
    		}else{
    			$("#consPunishFlag").attr("checked",false);
    			$("#consPunishFlag").attr("disabled","disabled");
    			this.consPunishTypeLowerFlag = false;
    			this.params.smAgrePunishCons.lowerThreshold = null;
    			this.params.smAgrePunishCons.lowerType = "";
    			this.params.smAgrePunishCons.lowerProp = null;
    		}
    	},
    	'params.smPpa.distMode':function(){    
    		//01 自动分解
    		if(this.params.smPpa.distMode == "01"){
    			this.distFunc();  
    		}else{
    			var rows = $("#ppaDetailGrid").datagrid("getRows");
    			if(rows.length==0){
    				$('#ppaDetailGrid').datagrid('appendRow',{});
    			}
    		}
    	},
		formFields:function(){
			this.initData(MainFrameUtil.getParams("ppa-detail").id);
			$("#consEffectTerm").html("电力用户交割电量不等于其市场电量");
			$("#compEffectTerm").html("当售电公司为电力用户购得月度电量小于电力用户月度委托电量时");
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
    			//this.params.smDistMode.profitMode = "";
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
    			/* this.params.smDistMode.profitMode = "";
    			this.params.smDistMode.partyBLcProp = null;
    			this.params.smDistMode.partyALcProp = null;
    			this.partyBBidProp = null;
    			this.partyABidProp = null; */
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
    			/* this.params.smDistMode.agrePrc = null;
    			this.params.smDistMode.partyBLcProp = null;
    			this.params.smDistMode.partyALcProp = null;
    			this.partyBBidProp = null;
    			this.partyABidProp = null;
    			this.params.smDistMode.profitMode = ""; */
    			break;
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
    	'params.smDistMode.diviType':function(){
    		var me = this;
    		if(me.params.smDistMode.diviType == "03"){
    			$("#divi0103").show();
    		}else{
    			$("#divi0103").hide();
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
    }
}); 
</script>
</body>

</html>
