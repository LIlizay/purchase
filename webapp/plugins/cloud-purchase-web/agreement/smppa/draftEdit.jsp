<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
   <title>售电管理-售电合同管理合同维护</title>
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
</style>
</head>
<body>
<div id="ppaEditVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="合同维护" line="true">
			<mk-form-panel title="用户信息">
				<mk-form-row>
					<!-- 用户名称  -->
		            <mk-form-col :label="formFields.consName.label" required_icon="true" label-width="200px"
								:class="{'display-none':!formFields.consName.formHidden}" col="4" label-align="right">
		                <mk-selectbox :url="url" height="620" width="50%" textfield="consName" propname="id" 
		                		:propvalue.sync="params.smPpa.consId" :cacheurl="urlCache" :theobj.sync="consInfo" v-ref:consinfo name="consId"></mk-selectbox>
		            </mk-form-col>
					<!-- 用户名称  -->
		             <!-- <mk-form-col :label="formFields.consName.label" label-width="200px"
								:class="{'display-none':!formFields.consName.formHidden}" col="4" label-align="right">
		                 <div style="padding-top: 5px;height: 30px;">{{consInfo.consName}}</div>
		             </mk-form-col> -->
					<!-- 客户名称 -->
		             <!-- <mk-form-col :label="formFields.custName.label" label-width="200px"
								:class="{'display-none':!formFields.custName.formHidden}" col="4" label-align="right">
		             	 <div style="padding-top: 5px;height: 30px;">{{consInfo.custName}}</div>
		             </mk-form-col> -->
					<!-- 所属供电公司  -->
		            <!-- <mk-form-col :label="formFields.orgId.label" label-width="200px"
								:class="{'display-none':!formFields.orgId.formHidden}" col="4" label-align="right">
		                <div style="padding-top: 5px;height: 30px;">{{consInfo.orgName}}</div>
		            </mk-form-col> -->
		            <!-- 电压等级  -->
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
					<!-- 负荷性质  -->
		            <!-- <mk-form-col :label="formFields.lodeAttrName.label" label-width="200px"
								:class="{'display-none':!formFields.lodeAttrName.formHidden}" col="4" label-align="right">
		                <div style="padding-top: 5px;height: 30px;">{{consInfo.lodeAttrName}}</div>
		            </mk-form-col> -->
					<!-- 用电容量  -->
		             <mk-form-col :label="formFields.electricCapacity.label" label-width="200px"
								:class="{'display-none':!formFields.electricCapacity.formHidden}" col="4" label-align="right">
		                 <su-textbox type="number" disabled="disabled" :data.sync="consInfo.electricCapacity" :addon="{'show':true,'rightcontent':'kVA'}"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
				<!-- <mk-form-row>
					抄表周期 
		            <mk-form-col :label="formFields.recordingCycleName.label" label-width="200px"
								:class="{'display-none':!formFields.recordingCycleName.formHidden}" col="4" label-align="right">
		                 <div style="padding-top: 5px;height: 30px;">{{consInfo.recordingCycleName}}</div>
		             </mk-form-col>
				</mk-form-row> -->
			</mk-form-panel>
			<mk-form-panel title="合同基本信息">
				<mk-form-row>
					<!-- 合同编号  -->
		             <mk-form-col :label="formFields.agreNo.label" label-width="200px"
								:class="{'display-none':!formFields.agreNo.formHidden}" col="4" label-align="right">
		                 <su-textbox disabled="disabled" :data.sync="params.smPpa.agreNo"></su-textbox>
		             </mk-form-col>
					<!-- 合同名称  -->
		             <mk-form-col :label="formFields.agreName.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.agreName.formHidden}" col="4" label-align="right">
		                 <su-textbox :data.sync="params.smPpa.agreName" name="agreName"></su-textbox>
		             </mk-form-col>
					<!-- 合同类型  -->
		           <mk-form-col :label="formFields.agreType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.agreType.formHidden}" col="4" label-align="right">
		                 <su-select :high="150" label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellElecContractType" :select-value.sync="params.smPpa.agreType" @su-change="" name="agreType"></su-select>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 合同版本  -->
		             <%-- <mk-form-col :label="formFields.agreVer.label" label-width="200px" 
								:class="{'display-none':!formFields.agreVer.formHidden}" col="4" label-align="right">
		                 <su-select :high="150" label-name="name"
		                 		url="${Config.baseURL}cloud-purchase-web/sccontractVersionController/getVnByTypeCode" :select-value.sync="params.smPpa.agreVer" name="agreVer"></su-select>
		             </mk-form-col> --%>
					<!-- 售电方签约人  -->
		            <mk-form-col :label="formFields.partyB.label" label-width="200px" required_icon="true"
							:class="{'display-none':!formFields.partyB.formHidden}" col="4" label-align="right">
	<!-- 	                <mk-selectbox width="600px" :url="urluser"  propname="id" :propvalue.sync="params."  textfield="userName" :cacheurl='urlcachePsSignatory'></mk-selectbox> -->
		            	<su-textbox :data.sync="params.smPpa.partyB" name="partyB"></su-textbox>
		            </mk-form-col>
					<!-- 客户签约人  -->
		             <mk-form-col :label="formFields.partyA.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.partyA.formHidden}" col="4" label-align="right">
		                 <su-textbox :data.sync="params.smPpa.partyA" name="partyA"></su-textbox>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 签订时间  -->
		             <mk-form-col :label="formFields.signDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.signDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smPpa.signDate" name="signDate"></su-datepicker>
		             </mk-form-col>
					<!-- 生效日期  -->
		             <mk-form-col :label="formFields.effectiveDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.effectiveDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM" :data.sync="params.smPpa.effectiveDate" name="effectiveDate"></su-datepicker>
		             </mk-form-col>
					<!-- 失效日期  -->
		             <mk-form-col :label="formFields.expiryDate.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.expiryDate.formHidden}" col="4" label-align="right">
		                 <su-datepicker format="YYYY-MM" :data.sync="params.smPpa.expiryDate" name="expiryDate"></su-datepicker>
		             </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 合同录入人员 -->
<!-- 		             <mk-form-col :label="formFields.recorder.label" label-width="200px" -->
<!-- 								:class="{'display-none':!formFields.recorder.formHidden}" col="4" label-align="right"> -->
<!-- 		                 <su-textbox disabled="disabled" :data.sync="params.smPpa.recorder"></su-textbox> -->
<!-- 		             </mk-form-col> -->
		             <!-- 附件 -->
					<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" required_icon="true" colspan="2">
						<mk-file-upload required="false" :filename.sync="fileName" v-ref:uploadfile @upload-error="uploadError" 
								@upload-success="uploadSuccess" url="${Config.getConfig('file.service.url')}"></mk-file-upload>
					</mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="代理电量管理" line="true" height="auto" >
			<mk-form-panel num="2">
				<mk-form-row>
					<%-- <!-- 代理方式  -->
			        <mk-form-col :label="formFields.proxyMode.label" label-width="200px"
								:class="{'display-none':!formFields.proxyMode.formHidden}" col="4" label-align="right">
			            <su-select :high="150" label-name="name"
			            		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_proxyModeCode" :select-value.sync="params.smPpa.proxyMode" @su-change="" ></su-select>
			        </mk-form-col> --%>
					<!-- 代理总电量  -->
		            <mk-form-col :label="formFields.proxyPq.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.proxyPq.formHidden}" col="4" label-align="right">
		                <su-textbox type="number" :data.sync="params.smPpa.proxyPq" name="proxyPq" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		            </mk-form-col>
					<!-- 分配方式  -->
		            <mk-form-col :label="formFields.distMode.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.distMode.formHidden}" col="4" label-align="right">
			            <su-select label-name="name" 
			            		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_dividedType" :select-value.sync="params.smPpa.distMode" @su-change="" name="distMode" ></su-select>
		            </mk-form-col>
				</mk-form-row>  
			</mk-form-panel>
		</mk-panel>			
		<mk-panel height="160px" header="false">
			<div id="ppaEditGrid" style="height:100%"></div>
		</mk-panel>				
		<mk-panel height="300px" header="false" :class="{'display-none':!mpGridShowFlag}">
			<div id="mpGrid" style="height:100%"></div>
		</mk-panel>	
		<mk-panel title="分成方式管理" line="true">
			<mk-form-panel>
				<mk-form-row>
					<!-- 结算模式  -->
		            <mk-form-col :label="formFields.settlementMode.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.settlementMode.formHidden}" col="4" label-align="right" >
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_settlementModeCode" :select-value.sync="params.smDistMode.settlementMode" name="settlementMode" ></su-select>
		            </mk-form-col>
					<!-- 分成方式 -->
		            <mk-form-col :label="formFields.diviCode.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.diviCode.formHidden}" col="4" label-align="right">
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_divideWayCode" :select-value.sync="params.smDistMode.diviCode" name="diviCode" ></su-select>	
		            </mk-form-col>
					<!-- 是否优惠上限 -->
<!-- 		            <mk-form-col :label="formFields.discountFlag.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.discountFlag.formHidden}" col="4" label-align="right"> -->
<!-- 		                 <su-select label-name="name" :disabled.sync="disEditFlag" -->
<%-- 		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" :select-value.sync="params.smDistMode.discountUpperFlag" name="discountUpperFlag" ></su-select>	 --%>
<!-- 		            </mk-form-col> -->
				</mk-form-row>
<!-- 				<mk-form-row> -->
					<!-- 优惠上限金额  -->
<!-- 		            <mk-form-col :label="formFields.discountAmt.label" label-width="200px" :required_icon="discountPlayFlag" -->
<!-- 								:class="{'display-none':!formFields.discountAmt.formHidden}" col="4" label-align="right" > -->
<!-- 		                <su-textbox type="number" :data.sync="params.smDistMode.discountUpperAmt" name="discountUpperAmt" -->
<!-- 		                		:addon="{'show':true,'rightcontent':'元'}" :disabled.sync="disAmtEditFlag"></su-textbox> -->
<!-- 		            </mk-form-col> -->
		            <!-- 是否优惠下限  -->
<!-- 		            <mk-form-col :label="formFields.discountLowerFlag.label " label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.discountLowerFlag.formHidden}" col="4" label-align="right" > -->
<!-- 		                <su-select label-name="name" :disabled.sync="discountLowerFlag" -->
<%-- 		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag"  --%>
<!-- 		                 		:select-value.sync="params.smDistMode.discountLowerFlag" name="discountLowerFlag" ></su-select>	 -->
<!-- 		            </mk-form-col> -->
		            <!-- 优惠下限金额  -->
<!-- 		            <mk-form-col :label="formFields.discountLowerAmt.label" label-width="200px" :required_icon="discountLowerPlayFlag" -->
<!-- 								:class="{'display-none':!formFields.discountLowerAmt.formHidden}" col="4" label-align="right" > -->
<!-- 		                <su-textbox type="number" :data.sync="params.smDistMode.discountLowerAmt" name="discountLowerAmt" -->
<!-- 		                		:addon="{'show':true,'rightcontent':'元'}" :disabled.sync="discountLowerAmtFlag"></su-textbox> -->
<!-- 		            </mk-form-col> --> 
<!-- 		        </mk-form-row> -->
		        <!-- 保底选项相关字段 -->
		        <div id="divi01" :class="{'display-none':!divi01Flag}">
		        	<mk-form-row>
						<!-- 保底协议价 -->
			            <mk-form-col :label="formFields.agreAmt.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.agreAmt.formHidden}" col="4" label-align="right">
			                <su-textbox type="number" :data.sync="params.smDistMode.agrePrc" name="agrePrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
		        </div>
		        <!-- 分成选项相关字段 -->
		        <div id="divi02" :class="{'display-none':!divi02Flag}">
		        	<mk-form-row :class="{'display-none':!divi0203Flag}">
		        		<div id="divi0201" :class="{'display-none':!divi0201Flag}">
							<!-- 分成电量比例 -->
				            <mk-form-col :label="formFields.pqSharingRatio.label" label-width="200px" required_icon="true"
										:class="{'display-none':!formFields.pqSharingRatio.formHidden}" col="4" label-align="right">
				                <su-textbox type="number" :data.sync="params.smDistMode.pqSharingRatio" name="pqSharingRatio" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				            </mk-form-col>
		        		</div>
		        		<div id="divi0202" :class="{'display-none':!divi0202Flag}">
							<!-- 收益模式选择 -->
				            <mk-form-col :label="formFields.profitMode.label" label-width="200px" required_icon="true"
										:class="{'display-none':!formFields.profitMode.formHidden}" col="4" label-align="right">
		                 <su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_earningsMode" 
		                 		:select-value.sync="params.smDistMode.profitMode" name="profitMode" ></su-select>
				            </mk-form-col>
		        		</div>
		        		<!-- 售电公司分成比例  -->
				        <mk-form-col :label="formFields.partyBLcProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyBLcProp.formHidden}" col="4" label-align="right" >
				            <su-textbox type="number" :data.sync="params.smDistMode.partyBLcProp" name="partyBLcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
						<!-- 用户长协分成比例 -->
			            <mk-form-col :label="formFields.partyALcProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyALcProp.formHidden}" col="4" label-align="right" >
			                <su-textbox type="number" disabled="disabled" :data.sync="params.smDistMode.partyALcProp" name="partyALcProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row :class="{'display-none':!divi0203Flag}">
				        <!-- 售电公司竞价分成比例  -->
				        <mk-form-col :label="formFields.partyBBidProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyBBidProp.formHidden}" col="4" label-align="right" >
				            <su-textbox type="number" :data.sync="partyBBidProp" name="partyBBidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
				         <!-- 用户竞价分成比例 -->
			            <mk-form-col :label="formFields.partyABidProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyABidProp.formHidden}" col="4" label-align="right" >
			                <su-textbox type="number" disabled="disabled" :data.sync="partyABidProp" name="partyABidProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row :class="{'display-none':!divi0204Flag}">
						<!-- 售电公司分成比例  -->
				        <mk-form-col :label="formFields.partyBProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyBProp.formHidden}" col="4" label-align="right" >
				            <su-textbox type="number" :data.sync="partyBProp" name="partyBProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
				        </mk-form-col>
				         <!-- 用户分成比例 -->
			            <mk-form-col :label="formFields.partyAProp.label" label-width="200px" required_icon="true"
									:class="{'display-none':!formFields.partyAProp.formHidden}" col="4" label-align="right" >
			                <su-textbox type="number" disabled="disabled" :data.sync="partyAProp" name="partyAProp" :addon="{'show':true,'rightcontent':'%'}"></su-textbox>
			            </mk-form-col>
					</mk-form-row>
		        </div>
			</mk-form-panel>
		</mk-panel>	
		<mk-panel title="用户违约惩罚方式" line="true">
			<mk-form-panel>
				<mk-form-row>
					<!-- 生效条件  -->
			        <mk-form-col :label="formFields.effectTerm.label" label-width="200px" 
								colspan="3" :class="{'display-none':!formFields.effectTerm.formHidden}" col="4" label-align="right" >
			            <!-- <su-textbox disabled="disabled" :data.sync="consEffectTerm"></su-textbox> -->
			            <div style="padding-top: 5px;height: 30px;">{{consEffectTerm}}</div>
			        </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 电力用户  -->
			        <mk-form-col :label="formFields.punishType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.punishType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name"
		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.smAgrePunishCons.punishType" name="punishType" ></su-select>
			        </mk-form-col>
			        <!--  售方不罚用户不罚 -->
			        <input type="checkbox" id="consPunishFlag" value="consPunishFlag" name="consPunishFlag"> 售方不罚用户不罚</input>
				</mk-form-row>
				<mk-form-row :class="{'display-none':!consPunishTypeFlag}">
					<!-- 用电超出月申报电量阈值  -->
			        <mk-form-col :label="formFields.upperThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.upperThreshold" :addon="{'show':true,'rightcontent':'%'}" name="upperThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 惩罚电价基准  -->
			        <mk-form-col :label="formFields.upperType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.upperType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change="consUpperTypeChange" :select-value.sync="params.smAgrePunishCons.upperType" name="upperType"
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','04'"></su-select>
			        </mk-form-col>
					<!--  惩罚比例值  -->
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
				<mk-form-row :class="{'display-none':!consPunishTypeFlag}">
					<!-- 用电不足月申报电量阈值  -->
			        <mk-form-col :label="formFields.lowerThreshold.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerThreshold.formHidden}" col="4" label-align="right" >
			            <su-textbox type="number" :data.sync="params.smAgrePunishCons.lowerThreshold" :addon="{'show':true,'rightcontent':'%'}" name="lowerThreshold"></su-textbox>
			        </mk-form-col>
					<!-- 惩罚电价基准  -->
			        <mk-form-col :label="formFields.lowerType.label" label-width="200px" required_icon="true"
								:class="{'display-none':!formFields.lowerType.formHidden}" col="4" label-align="right">							
		                 <su-select label-name="name" @su-change="consLowerTypeChange" :select-value.sync="params.smAgrePunishCons.lowerType" name="lowerType" 
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','04'"></su-select>
		            </mk-form-col>
					<!--  惩罚比例值  -->
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
		                 <div style="padding-top: 5px;height: 30px;">{{compEffectTerm}}</div>
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
		                 		url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','04'"></su-select>
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
var ppaEditVue = new Vue({
    el: '#ppaEditVue',
    data: {
  	  	formFields:{},
  	  	fileName:"",
  	  	partyBBidProp:null,//售方竞价分成比例
		partyABidProp:null,//用户竞价分成比例
		partyBProp:null,//售方分成比例
		partyAProp:null,//用户分成比例
  	  	mpGridShowFlag:true, //计量点表格展示标志
	  	mpGridSuccess:false, //计量点表格初始化标志
// 	  	disEditFlag:false, //是否直接优惠上限编辑标志
// 	  	disAmtEditFlag:false, //直接优惠上限金额编辑标志
	  	divi01Flag:false, // 保底选项相关字段展示标志
	  	divi02Flag:false, // 分成选项相关字段展示标志
	  	divi0201Flag:false, // 参与分成电量比例展示标志
	  	divi0202Flag:false, // 收益模式选择展示标志
	  	divi0203Flag:false,//保底加分成展示标志
	  	divi0204Flag:false,//分成比列的展示标志
	    consLowerPrcFlag:false,//负偏差惩罚协议价展示标志
		consUpperPrcFlag:false,//正偏差惩罚协议价展示标志
		compPunishFlag:false,//售电公司惩罚展示标志
		compPunishFlag01:false,//售电公司惩罚基准展示标志
// 		discountPlayFlag:true,//上限必填标志
//   	discountLowerPlayFlag:true,//下限必填标志
  		consPunishTypeFlag:false,//用户惩罚展示标志
		consInfo:{}, //用户信息
		consEffectTerm:"电力用户交割电量不等于其市场电量",
  	  	compEffectTerm:"当售电公司为电力用户购得月度电量小于电力用户月度委托电量时",
	  	discountLowerAmtFlag:false,//直接优惠下限金额编辑标志
	  	url:basePath + "view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox",
  	  	urlCache:"",
  		//合同状态为默认添加 ，recorder:null 合同录入人属性移除；
  		//discountUpperFlag:null,discountUpperAmt:null,discountLowerFlag:null,discountLowerAmt:null, 分成上限下限属性移除
  	  	params:{smPpa:{id:null,consId:null,agreNo:null,agreName:null,agreType:null,agreStatus:"04",partyA:null,
  	  				partyB:null,signDate:null,effectiveDate:null,expiryDate:null,fileId:null,
  	  				proxyMode:null,proxyPq:null,distMode:null,jan:null,feb:null,mar:null,apr:null,
  	  				may:null,jun:null,jul:null,aug:null,sept:null,oct:null,nov:null,dece:null,recordDate:null},
  	  				
  	  			smDistMode:{id:null,agreId:null,diviCode:null,settlementMode:null,agrePrc:null,partyBLcProp:null,partyALcProp:null,partyBBidProp:null,partyABidProp:null,companyId:null,departmentId:null,},
  	  			smAgreMp:[],
  	  			smAgrePunishCons:{id:null,agreId:null,punishType:null,upperThreshold:null,upperType:null,upperProp:null,lowerThreshold:null,lowerType:null,
  	  			lowerProp:null,upperPrc:null,lowerPrc:null,punishFlag:null,companyId:null,departmentId:null},
  	  			smAgrePunishComp:{id:null,agreId:null,punishFlag:null,lowerThreshold:null,lowerType:null,lowerPrc:null,lowerProp:null,companyId:null,departmentId:null}
  	  	},
	  	dataOption: {
	  		rules: {
	  			consId:"required",
	  			agreName:"required",
	  			agreType:"required",
	  			partyA:"required",
	  			partyB:"required",
	  			signDate:"required",
	  			effectiveDate:"required",
	  			expiryDate:"required",//失效时间
	  			distMode:"required",//分配
	  			proxyPq:"required",//代理总电量 
	  			diviCode:"required",//分成方式
	  			settlementMode:"required",//结算模式
// 	  			discountUpperFlag:"required",//是否优惠上限
// 	  			discountUpperAmt:"required",//优惠上限金额
// 	  			discountLowerAmt:"required",//优惠下限金额
// 	  			discountLowerFlag:"required",//是否优惠下限
	  			agrePrc:"required",//保底协议价
	  			profitMode:"required",//收益模式选择
	  			pqSharingRatio:{
	  				'required':true,
            		'max':100,
	  			},//分成电量比例
	  			partyBLcProp:{
	  				'required':true,
	  				'max':100,
	  			},//售方长协分成比例
	  			partyALcProp:{
	  				'required':true,
	  				'max':100,
	  			},//用户长协分成比例
	  			partyBBidProp:{
	  				'required':true,
	  				'max':100,
	  			},//售方竞价分成比例
	  			partyABidProp:{
	  				'required':true,
	  				'max':100,
	  			},//用户竞价分成比例
	  			punishType:"required",//电力用户 
	  			upperThreshold:{
	  				'required':true,
	  			},//正偏差允许范围
	  			upperType:"required",//惩罚电价基准
	  			upperProp:{
	  				'required':true,
	  				'max':999.99,
	  			},//惩罚比例值
	  			lowerThreshold:{
	  				'required':true,
	  				'max':100,
	  			},//负偏差允许范围 
	  			lowerType:"required",//惩罚电价基准
	  			lowerProp:{
	  				'required':true,
	  				'max':999.99,
	  			},//惩罚比例值
	  			consLowerPrc:"required",//负偏差惩罚协议价
	  			consUpperPrc:"required",//正偏差惩罚协议价
	  			punishFlag:"required",//是否赔偿
	  			compLowerThreshold:{
	  				'required':true,
	  				'max':100,
	  			},//购电不足月委托电量
	  			compLowerType:"required",//赔偿电价基准
	  			compLowerProp:{
	  				'required':true,
	  				'max':999.99,
	  			},//赔偿比例值
	  			compLowerPrc:"required",//赔偿协议价
	  			partyBProp:{
	  				'required':true,
	  				'max':100,
	  			},//售方分成比例
	  			partyAProp:{
	  				'required':true,
	  				'max':100,
	  			},//用户分成比例
	        }
	    },
	    valid: false
    },
    ready:function(){
		//按钮初始化
		MainFrameUtil.setDialogButtons(this.getButtons(),"ppa-draft-edit");
  		//初始化表单
		ComponentUtil.getFormFields("selling.agreement.smPpa",this);
		this.initppaEditGrid();
		this.mpGridShowFlag = false;
		//this.params.smPpa.consId = MainFrameUtil.getParams("ppa-draft-edit").id;
    },
    methods: {
		getButtons:function(){
			var buttons = [{text:"保存 ",type:"primary",handler:this.savePpaInfo},
			               {text:"提交",type:"primary",handler:this.approval},
			               {text:"取消 ",handler:function(){MainFrameUtil.closeDialog("ppa-draft-edit");}}];
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
		
		initData:function(id){
			//初始化数据
			$.ajax({
				url : '${Config.baseURL}cloud-purchase-web/smPpaController/'+id,
				type : 'get',
				success : function(result) {
					console.log("result",result);
					if(result.flag){
						if(result.data){
							if(result.data.smPpa !== null){
								if(result.data.smPpa.distMode != "01"){
									ppaEditVue.setRow(result.data.smPpa);
								}
							}
							ppaEditVue.params.smDistMode = result.data.smDistMode;
							ppaEditVue.params.smAgrePunishCons = result.data.smAgrePunishCons;
							ppaEditVue.params.smAgrePunishComp = result.data.smAgrePunishComp;
							if(result.data.smDistMode != null){
								var diviCode = result.data.smDistMode.diviCode;
								if(diviCode == "05"){
									ppaEditVue.partyBProp = result.data.smDistMode.partyBBidProp;
									ppaEditVue.partyAProp = result.data.smDistMode.partyABidProp;
								}else if(diviCode == "03" || diviCode == "04"){
									ppaEditVue.partyBBidProp = result.data.smDistMode.partyBBidProp;
									ppaEditVue.partyABidProp = result.data.smDistMode.partyABidProp;
								}
							}
							if(result.data.smPpa !== null && result.data.smPpa.consId !== null && result.data.smPpa.consId !== ""){
								ppaEditVue.urlCache = "${Config.baseURL}cloud-purchase-web/sConsDialogController/getConsInfoById/"+result.data.smPpa.consId;
							}
							if(result.data.smPpa !== null && result.data.smPpa.fileId !== null && result.data.smPpa.fileId !== ""){
								var obj = eval("("+result.data.smPpa.fileId+")");
								ppaEditVue.fileName = obj.fileName;
							}
							if(result.data.smAgrePunishCons !== null && result.data.smAgrePunishCons.punishFlag !== null && result.data.smAgrePunishCons.punishFlag !== ""){
								if(result.data.smAgrePunishCons.punishFlag == 1){
									$("#consPunishFlag").attr("checked","checked");
								}
							}
							ppaEditVue.params.smPpa = result.data.smPpa;
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
			$('#ppaEditGrid').datagrid('loadData',rows);
		},
		
        uploadSuccess: function(data) {  //上传成功执行的方法
        	//验证是否选择并上传了文件,如果是则设置文件id和文件名
            if(data){
                var fileId = data[0].id;    //获取文件的id
                var fileNameResult=data[0].fileName+data[0].extension;//获取文件名
                ppaEditVue.params.smPpa.fileId = "{fileId:'"+fileId+"',fileName:'"+fileNameResult+"'}";
            }
            this.save();
        },
        uploadError: function(data) {  //文件上传失败执行的方法
            MainFrameUtil.alert({title:"警告",body:'上传失败'}); 
        },
    	initppaEditGrid:function(){
    		//初始化代理电量表格
    		$("#ppaEditGrid").datagrid({
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
								editor:{id:"jan",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
					        },
							 {field:'feb',title:'2月',align:'left',width:12,
								editor:{id:"feb",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'mar',title:'3月',align:'left',width:12,
								editor:{id:"mar",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'apr',title:'4月',align:'left',width:12,
								editor:{id:"apr",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'may',title:'5月',align:'left',width:12,
								editor:{id:"may",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'jun',title:'6月',align:'left',width:12,
								editor:{id:"jun",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'jul',title:'7月',align:'left',width:12,
								editor:{id:"jul",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'aug',title:'8月',align:'left',width:12,
								editor:{id:"aug",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'sept',title:'9月',align:'left',width:12,
								editor:{id:"sept",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'oct',title:'10月',align:'left',width:12,
								editor:{id:"oct",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'nov',title:'11月',align:'left',width:12,
								editor:{id:"nov",type:'numberbox',options:{precision:4,min:0,}},
								formatter:function(value,index,row){
			           				  if(value){
			           					  return parseFloat(value);
			           				  }
		           			  	}
							 },
							 {field:'dece',title:'12月',align:'left',width:12,
								editor:{id:"dece",type:'numberbox',options:{precision:4,min:0,}},
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
			this.params.smPpa.agreStatus="04";
			if(this.params.smPpa.consId === null || this.params.smPpa.consId === ""){
				MainFrameUtil.alert({title:"提示",body:"请选择用户编号!"});
				return;
			}
			this.$refs.form1.valid();
        	if(this.valid===false){
        		return;
        	}
            //验证是否选择文件
            if(this.$refs.uploadfile.valid()){
                this.$refs.uploadfile.validAndUpload();
            }else{
                return;
            }
		},
		
		getProxyPq:function(){
			var editors = $("#ppaEditGrid").datagrid("getEditors",0);
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
				$("#ppaEditGrid").datagrid("endEdit",0);
				return true;
			}
			return false;
		},
		//提交
		approval:function(){
			/* if(this.params.smPpa.id === null ||  this.params.smPpa.id === ""){
				MainFrameUtil.alert({title:"提示",body:"请保存信息！"});
				return;
			} */
			this.savePpaInfo();
			if(this.params.smPpa.agreStatus == "01"){
				MainFrameUtil.alert({title:"提示",body:"该合同已提交！"});
				return;
			}
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/smPpaController/approval?id=" + this.params.smPpa.id,
				type : 'get',
				success : function(result) {
					if(result.flag){
						if(result.msg == "请添加记录！" || result.msg == "该用户已存在正常合同信息！"){
							MainFrameUtil.alert({title:"成功提示",body:result.msg});
						}else{
							MainFrameUtil.alert({title:"成功提示",body:result.msg});
	                        MainFrameUtil.closeDialog("ppa-draft-edit");
						}
					}else{
						MainFrameUtil.alert({title:"失败提示",body:result.msg});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"失败提示",body:"请刷新页面重试！"});
				}
			});
		},
		
    	save:function(){
    		var me = this;
    		//取消编辑并验证分配是否合理
    		if(!me.getProxyPq()){
    			MainFrameUtil.alert({title:"提示",body:"代理电量分配不合理！"});
    			return;
    		}
    		//代理电量按月划分数据
    		var smPpa = this.params.smPpa;
    		var row = $("#ppaEditGrid").datagrid("getRows")[0];
    		for(var obj in row){
    			smPpa[obj] = row[obj];
    		}
    		//计量点代理电量划分
    		if(this.params.smPpa.proxyMode=='02'){
    			this.params.smAgreMp = this.getEditorData("mpGrid");
    		}
    		//判断是否选中售方不罚用户不罚
    		if($("#consPunishFlag").is(':checked')){
    			this.params.smAgrePunishCons.punishFlag = 1;
    		}else{
    			this.params.smAgrePunishCons.punishFlag = 0;
    		}
    		//为竞价分成比列赋值
    		var diviCode = this.params.smDistMode.diviCode;
    		if(diviCode == "05"){//保底加分成
    			this.params.smDistMode.partyBBidProp = this.partyBProp;
    			this.params.smDistMode.partyABidProp = this.partyAProp;
    		}else if(diviCode == "03" || diviCode == "04"){
    			this.params.smDistMode.partyBBidProp = this.partyBBidProp;
    			this.params.smDistMode.partyABidProp = this.partyABidProp;
    		}else{
    			this.params.smDistMode.partyBBidProp = null;
    			this.params.smDistMode.partyABidProp = null;
    		}
        	//合同数据保存
			$.ajax({
				url : "${Config.baseURL}cloud-purchase-web/smPpaController/",
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
    		var proxyPq = this.params.smPpa.proxyPq;
    		if(!proxyPq){
    			MainFrameUtil.alert({title:"提示",body:"请填写代理电量！"});
    		}
    		//自动分解
			var row = new Object();
			var month = ["jan","feb","mar","apr","may","jun","jul","aug","sept","oct","nov","dece" ];
    		//月份差计算
    		var yearExpiry = ppaAddVue.params.smPpa.expiryDate.substr(0,4);
    		var yearEffective = ppaAddVue.params.smPpa.effectiveDate.substr(0,4);
    		var monthExpiry = ppaAddVue.params.smPpa.expiryDate.substr(5,2);
    		var	monthEffective = ppaAddVue.params.smPpa.effectiveDate.substr(5,2);
    		var num = (yearExpiry-yearEffective)*12 + (monthExpiry - monthEffective);
			var monPq = (proxyPq/(num+1)).toFixed(0);
			var lastMonPq = proxyPq - monPq*num;
			
			var index = ppaAddVue.params.smPpa.effectiveDate.substr(5,2) - 1;
			for(var i = 0 ; i < num ; i++){
				var mon = month[index+i];
				row[mon] = monPq;
			}
			var lastMon = month[index+num];
			row[lastMon] = lastMonPq;
			
			var rows = $("#ppaEditGrid").datagrid("getRows");
			if(rows.length==0){
				$('#ppaEditGrid').datagrid('appendRow', row); 
			}else{
				rows[0] = row;
				$('#ppaEditGrid').datagrid('refreshRow', 0); 
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
    	valueChange:function(){
   			//01 自动分解
   			if(this.params.smPpa.distMode == "01"){
       			var editors = $("#ppaAddGrid").datagrid("getEditors",0);
       			if(editors !== null && editors.length != 0){
       				$('#ppaAddGrid').datagrid('endEdit',0);
       			}
       			this.distFunc();  
       		}else{
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
    					$('#ppaAddGrid').datagrid('getColumnOption', mon).editor={id:mon,type:'numberbox',options:{precision:4,min:0,}};
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
    	"params.smAgrePunishCons.punishType":function(){
    		if(this.params.smAgrePunishCons.punishType == 1){//不惩罚
    			$("#consPunishFlag").attr("disabled",false);
    			this.consPunishTypeFlag = true;
    		}else{
    			$("#consPunishFlag").attr("checked",false);
    			$("#consPunishFlag").attr("disabled","disabled");
    			this.consPunishTypeFlag = false;
    			this.params.smAgrePunishCons.upperThreshold = null;
    			this.params.smAgrePunishCons.upperType = "";
    			this.params.smAgrePunishCons.upperProp = null;
    			this.params.smAgrePunishCons.lowerThreshold = null;
    			this.params.smAgrePunishCons.lowerType = "";
    			this.params.smAgrePunishCons.lowerProp = null;
    		}
    	},
    	'params.smPpa.distMode':function(){
    		this.valueChange();
    	},
    	'params.smPpa.effectiveDate':function(){    
    		if(this.params.smPpa.distMode != null){
    			this.valueChange();
    		}
    	},
    	'params.smPpa.proxyPq':function(){    
    		if(this.params.smPpa.distMode != null){
    			this.valueChange();
    		}
    	},
    	'params.smPpa.expiryDate':function(){    
    		if(this.params.smPpa.distMode != null){
    			this.valueChange();
    		}
    	},
		formFields:function(){
			this.initData(MainFrameUtil.getParams("ppa-draft-edit").id);
		},
		'params.smDistMode.diviCode':function(){ 
    		var me = this;
    		//分成方式监控
    		switch(Number(me.params.smDistMode.diviCode))
    		{
	    		case 1://保底
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=true;
	    			me.divi02Flag=false;
	    			me.divi0201Flag=false;
	    			me.divi0202Flag=false;
	    			me.divi0203Flag = false;
	    			me.divi0204Flag = false;
	    			this.params.smDistMode.partyBLcProp = null;
	    			this.params.smDistMode.partyALcProp = null;
	    			this.partyBBidProp = null;
	    			this.partyABidProp = null;
	    			this.partyBProp = null;
	    			this.partyAProp = null;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			this.params.smDistMode.profitMode = "";
	    			break;
	    		case 3://分成
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=false;
	    			me.divi02Flag=true;
	    			me.divi0201Flag=false;
	    			me.divi0202Flag=false;
	    			me.divi0203Flag=true;
	    			me.divi0204Flag=false;
	    			this.params.smDistMode.agrePrc = null;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			this.params.smDistMode.profitMode = "";
	    			this.partyBProp = null;
	    			this.partyAProp = null;
	    			break;
	    		case 4://保底或分成
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=true;
	    			me.divi02Flag=true;
	    			me.divi0201Flag=false;
	    			me.divi0202Flag=true;
	    			me.divi0203Flag=true;
	    			me.divi0204Flag=false;
	    			this.partyBProp = null;
	    			this.partyAProp = null;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			break;
	    		case 5://保底加分成
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=true;
	    			me.divi02Flag=true;
	    			me.divi0201Flag=true;
	    			me.divi0202Flag=false;
	    			me.divi0203Flag = false;
	    			me.divi0204Flag = true;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			this.params.smDistMode.profitMode = "";
	    			this.params.smDistMode.partyBLcProp = null;
	    			this.params.smDistMode.partyALcProp = null;
	    			this.partyBBidProp = null;
	    			this.partyABidProp = null;
	    			break;
	    		case 6://长协保底加竞价分成
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=true;
	    			me.divi02Flag=true;
	    			me.divi0201Flag=true;
	    			me.divi0202Flag=false;
	    			me.divi0203Flag = false;
	    			me.divi0204Flag = true;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			this.params.smDistMode.profitMode = "";
	    			this.params.smDistMode.partyBLcProp = null;
	    			this.params.smDistMode.partyALcProp = null;
	    			this.partyBBidProp = null;
	    			this.partyABidProp = null;
	    			break;
	    		default:
// 	    			me.disEditFlag=false;
	    			me.divi01Flag=false;
	    			me.divi02Flag=false;
	    			me.divi0201Flag=false;
	    			me.divi0202Flag=false;
	    			me.divi0203Flag=false;
	    			me.divi0204Flag=false;
	    			this.params.smDistMode.agrePrc = null;
	    			this.params.smDistMode.partyBLcProp = null;
	    			this.params.smDistMode.partyALcProp = null;
	    			this.partyBBidProp = null;
	    			this.partyABidProp = null;
	    			this.params.smDistMode.pqSharingRatio = null;
	    			this.params.smDistMode.profitMode = "";
	    			this.partyBProp = null;
	    			this.partyAProp = null;
	    			break;
    		}
    	},
    	'params.smDistMode.partyBLcProp':function(){
    		if(this.params.smDistMode.partyBLcProp){
    			this.params.smDistMode.partyALcProp = 100 - parseFloat(this.params.smDistMode.partyBLcProp);
    		}
    	},
    	'partyBBidProp':function(){
    		if(this.partyBBidProp){
    			this.partyABidProp = 100 - parseFloat(this.partyBBidProp);
    		}
    	}
//     	,
//     	'partyBProp':function(){
//     		if(this.partyBProp){
//     			this.partyAProp = 100 - parseFloat(this.partyBProp);
//     		}
//     	},
//     	'params.smDistMode.discountUpperFlag':function(){ 
//     		if(this.params.smDistMode.discountUpperFlag == 1){
//     			this.disAmtEditFlag = false;
//     			this.discountPlayFlag = true;
//     		}else{
//     			this.disAmtEditFlag = "disabled";
//     			this.discountPlayFlag = false;
//     			this.params.smDistMode.discountUpperAmt = null;
//     		}
//     	},
//     	"params.smDistMode.discountLowerFlag":function(){
//     		if(this.params.smDistMode.discountLowerFlag == 1){
//     			this.discountLowerAmtFlag = false;
//     			this.discountLowerPlayFlag = true;
//     		}else{
//     			this.discountLowerAmtFlag = "disabled";
//     			this.discountLowerPlayFlag = false;
//     			this.params.smDistMode.discountLowerAmt = null;
//     		}
//     	}
    }
}); 
</script>
</body>

</html>