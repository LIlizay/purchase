<html>
	<head>
		<title>档案管理-用户档案信息基本信息</title>
	</head>
	<body>
	
	</body>
</html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
    .pos_btn{
    	margin:-35px 20px 5px 10px;
    }
</style>
<div id="consEditVue" v-cloak style="padding-bottom: 1px">
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel title="用户基本信息" label_width="140px">
			<mk-form-row>
				<!-- 用户名称 -->
				<mk-form-col :label="formFields.consName.label" :class="{'display-none':!formFields.consName.formHidden}" required_icon="true">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.consName" type="text" name="consName"></su-textbox>
				</mk-form-col>
				<!-- 用户类型 -->
				<mk-form-col :label="formFields.consType.label" :class="{'display-none':!formFields.consType.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.consType"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_consType" name="consType"></su-select>
				</mk-form-col>
				<!-- 公示序列号 -->
				<mk-form-col :label="formFields.dealSeq.label" :class="{'display-none':!formFields.dealSeq.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.dealSeq" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 行业分类 -->
				<mk-form-col :label="formFields.industryType.label" :class="{'display-none':!formFields.industryType.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.industryType"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_industryType" name="industryType"></su-select><!-- sell_industryType -->
				</mk-form-col>
				<!-- 所属供电公司 -->
				<mk-form-col :label="formFields.orgId.label" :class="{'display-none':!formFields.orgId.formHidden}">
					<mk-trigger-text disabled="disabled" :data.sync="orgInfo.name" editable="false" @mk-trigger="selectOrg" ></mk-trigger-text>
					
<!-- 					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.orgId" -->
<%-- 							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_orgCode" :name="orgId"></su-select> --%>
				</mk-form-col>
				<!-- 所属园区 -->
				<mk-form-col :label="formFields.industrialZone.label" :class="{'display-none':!formFields.industrialZone.formHidden}">
					<mk-trigger-text disabled="disabled" :data.sync="zoneInfo.izName" editable="false" @mk-trigger="selectZone" ></mk-trigger-text>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 省 -->
				<mk-form-col :label="formFields.provinceCode.label" :class="{'display-none':!formFields.provinceCode.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.provinceCode" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList" name="provinceCode"></su-select>
				</mk-form-col>
				<!-- 市 -->
				<mk-form-col :label="formFields.cityCode.label" :class="{'display-none':!formFields.cityCode.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.cityCode" :high="150"
						:data-source='cityCodeList' name="cityCode"></su-select>
				</mk-form-col>
				<!-- 区县 -->
				<mk-form-col :label="formFields.countyCode.label" :class="{'display-none':!formFields.countyCode.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.countyCode" :high="150"
						:data-source='countyCodeList' name="countyCode"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 是否完成电能信息采集系统 -->
				<mk-form-col :label="formFields.scadaFlag.label" :class="{'display-none':!formFields.scadaFlag.formHidden}">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.scadaFlag"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="scadaFlag"></su-select>
				</mk-form-col>
				
				<!-- 是否打包 -->
<!-- 				<mk-form-col :label="formFields.scPackage.label" :class="{'display-none':!formFields.scPackage.formHidden}"> -->
<!-- 					<su-select placeholder="" disabled="disabled" :select-value.sync="params.scConsumerInfo.scPackage" height="auto" -->
<!-- 						:data-source='scPackageList'  name="scPackage"> -->
<!-- 					</su-select> -->
<!-- 				</mk-form-col> -->

				<!-- 市场状态 -->
                <mk-form-col :label="formFields.marketStatusName.label" :class="{'display-none':!formFields.marketStatusName.formHidden}" required_icon="false">
                      <su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.marketStatus"
                             url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_marketStatusCode" name="marketStatus"></su-select>
                </mk-form-col>
                 <!-- 是否直购电用户 -->
                <mk-form-col :label="formFields.directPowerName.label" :class="{'display-none':!formFields.directPowerName.formHidden}" required_icon="false">
                      <su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.directPower"
                             url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="directPower"></su-select>
                </mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 用电地址 -->
				<mk-form-col :label="formFields.elecAddr.label" :class="{'display-none':!formFields.elecAddr.formHidden}" colspan="3">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.elecAddr" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		
		<mk-form-panel title="用电信息" label_width="140px" num="2">
			<mk-form-row>
				<!-- 用电容量 -->
				<mk-form-col :label="formFields.electricCapacity.label" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="true">
					<su-textbox disabled="disabled" :addon="{'show':true,'rightcontent':'kVA'}" :data.sync="params.scConsumerInfo.electricCapacity" type="number" name="electricCapacity"></su-textbox>
				</mk-form-col>
				<!-- 用电类别 -->
				<mk-form-col :label="formFields.elecTypeCode.label" :class="{'display-none':!formFields.elecTypeCode.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.elecTypeCode"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" name="elecTypeCode"></su-select>
				</mk-form-col>
				
			</mk-form-row>
			<mk-form-row>
				<!-- 电压等级 -->
				<mk-form-col :label="formFields.voltCode.label" :class="{'display-none':!formFields.voltCode.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.voltCode"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" name="voltCode"></su-select>
				</mk-form-col>
				
				<!-- 安排年度上限 -->
				<mk-form-col :label="formFields.upperPq.label" :class="{'display-none':!formFields.upperPq.formHidden}">
					<su-textbox disabled="disabled" :addon="{'show':true,'rightcontent':'亿千瓦时'}" :data.sync="params.scConsumerInfo.upperPq" type="number"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 负荷性质 -->
				<mk-form-col :label="formFields.lodeAttrCode.label" :class="{'display-none':!formFields.lodeAttrCode.formHidden}">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.lodeAttrCode"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_lodeAttrCode"></su-select> 
				</mk-form-col>
				<!-- 抄表例日 -->
                <mk-form-col label="抄表例日">
                   	<mk-trigger-text disabled="disabled" :data.sync="params.usuallyDateName" editable="false"></mk-trigger-text>
                </mk-form-col>
			</mk-form-row>
		</mk-form-panel>	
	
		<mk-panel title="计量点信息" line="true" >    
		    <div id="mpInfoGrid" class="col-xs-12" style="height:300px"></div>
		</mk-panel>
		<mk-form-panel title="商务信息" label_width="140px" num="2">
			<mk-form-row>
				<!-- 营业执照注册号 -->
				<mk-form-col :label="formFields.registrationNo.label" :class="{'display-none':!formFields.registrationNo.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.registrationNo" type="text" name="registrationNo"></su-textbox>
				</mk-form-col>
				<!-- 税务登记证号 -->
				<mk-form-col :label="formFields.vatNo.label" :class="{'display-none':!formFields.vatNo.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.vatNo" type="text" name="vatNo"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 组织机构代码 -->
				<mk-form-col :label="formFields.orgCode.label" :class="{'display-none':!formFields.orgCode.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.orgCode" type="text" name="orgCode"></su-textbox>
				</mk-form-col>
				<!-- 法人名称 -->
				<mk-form-col :label="formFields.legalAgent.label" :class="{'display-none':!formFields.legalAgent.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.legalAgent" type="text" name="legalAgent"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 法人代表名称 -->
				<mk-form-col :label="formFields.authAgent.label" :class="{'display-none':!formFields.authAgent.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.authAgent" type="text" name="authAgent"></su-textbox>
				</mk-form-col>
				<!-- 企业注册地址 -->
				<mk-form-col :label="formFields.registeredAddress.label" :class="{'display-none':!formFields.registeredAddress.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.registeredAddress" type="text" name="registeredAddress"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 开户银行 -->
				<mk-form-col :label="formFields.bankName.label" :class="{'display-none':!formFields.bankName.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.bankName" type="text" name="bankName"></su-textbox>
				</mk-form-col>
				<!-- 开户名称 -->
				<mk-form-col :label="formFields.countName.label" :class="{'display-none':!formFields.countName.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.countName" type="text" name="countName"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 开户账号 -->
				<mk-form-col :label="formFields.countNo.label" :class="{'display-none':!formFields.countNo.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.countNo" type="text" name="countNo"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		
		<mk-form-panel title="联系人信息" label_width="140px" num="2">
			<mk-form-row>
				<!-- 联系人姓名 -->
				<mk-form-col :label="formFields.contNameP.label" :class="{'display-none':!formFields.contNameP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.contName" type="text" name="contNameP"></su-textbox>
				</mk-form-col>
				<!-- 职务 -->
				<mk-form-col :label="formFields.postP.label" :class="{'display-none':!formFields.postP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.post" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 手机号 -->
				<mk-form-col :label="formFields.phoneP.label" :class="{'display-none':!formFields.phoneP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.phone" type="text" name="phoneP"></su-textbox>
				</mk-form-col>
				<!-- 办公电话 -->
				<mk-form-col :label="formFields.officePhoneP.label" :class="{'display-none':!formFields.officePhoneP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.officePhone" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 传真号 -->
				<mk-form-col :label="formFields.faxP.label" :class="{'display-none':!formFields.faxP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.fax" type="text"></su-textbox>
				</mk-form-col>
				<!-- 电子邮箱 -->
				<mk-form-col :label="formFields.eMailP.label" :class="{'display-none':!formFields.eMailP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.eMail" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 通信地址 -->
				<mk-form-col :label="formFields.addrP.label" :class="{'display-none':!formFields.addrP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.addr" type="text"></su-textbox>
				</mk-form-col>
				<!-- 邮政编码 -->
				<mk-form-col :label="formFields.postcodeP.label" :class="{'display-none':!formFields.postcodeP.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scContactsInfo.postcode" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="售电公司服务人员信息" label_width="140px" num="2">
			<mk-form-row>
				<!-- 联系人姓名 -->
				<mk-form-col :label="formFields.sellPerson.label" :class="{'display-none':!formFields.sellPerson.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.sellPerson" type="text" name="sellPerson"></su-textbox>
				</mk-form-col>
				<!-- 职务 -->
				<mk-form-col :label="formFields.sellPost.label" :class="{'display-none':!formFields.sellPost.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.sellPost" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 手机号 -->
				<mk-form-col :label="formFields.sellPhone.label" :class="{'display-none':!formFields.sellPhone.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.sellPhone" type="text" name="sellPhone"></su-textbox>
				</mk-form-col>
				<!-- 办公电话 -->
				<mk-form-col :label="formFields.officePhone.label" :class="{'display-none':!formFields.officePhone.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.officePhone" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 传真号 -->
				<mk-form-col :label="formFields.faxNo.label" :class="{'display-none':!formFields.faxNo.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.faxNo" type="text"></su-textbox>
				</mk-form-col>
				<!-- 电子邮箱 -->
				<mk-form-col :label="formFields.sellEMail.label" :class="{'display-none':!formFields.sellEMail.formHidden}">
					<su-textbox disabled="disabled" :data.sync="params.scConsumerInfo.sellEMail" type="text"></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-panel title="添加上级单位" line="true" >    
		    <div id="connectedGrid" class="col-xs-12" style="height:300px"></div>
		</mk-panel>
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consEditVue=new Vue({
			el: '#consEditVue',
			data: {
				scPackageList : [{"value":"1","label":"是","checked":null},{"value":"0","label":"否","checked":null}],
				cityCodeList:[],
				countyCodeList:[],
				formFields:{},
				urlCache:"",
				zoneInfo:null,
				
				orgInfo : null,
			    params:{scConsumerInfo:{
			    			id:null,
					    	vatNo:null,
					    	elecAddr:null,
					    	registeredAddress:null,
					    	sellPhone:null,
					    	consName:null,
					    	bankName:null,
					    	countyCode:null,
					    	industryType:null,
					    	elecTypeCode:null,
					    	officePhone:null,
					    	faxNo:null,
					    	voltCode:null,
					    	provinceCode:null,
					    	lodeAttrCode:null,
					    	orgCode:null,
					    	legalAgent:null,
					    	countNo:null,
					    	sellEMail:null,
					    	sellPost:null,
					    	orgId:null,
					    	consType:null,
					    	registrationNo:null,
					    	cityCode:null,
					    	upperPq:null,
					    	authAgent:null,
					    	consName:null,
					    	sellPerson:null,
					    	countName:null,
					    	electricCapacity:null,
					    	industrialZone:null,
					    	scadaFlag:null,
					    	dealSeq:null,
				    		parentId:null,
					    	consPath:null,
					    	directPower:null,
                            marketStatus:null
					    	
    					},
			    		scContactsInfo:{
			    			fax:null,
			    			addr:null,
			    			eMail:null,
			    			contName:null,
			    			phone:null,
			    			postcode:null,
			    			officePhone:null,
			    			post:null
			    		},
						mpList:null,
						usuallyDateName: null
					},
				valid: false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.archives.scconsumerinfo",me);
				me.initconsAddGrid();
				me.initconnectedGrid();
			},
			methods: {
				initData:function(id){
					var me = this;
					//初始化数据  下方监听调用
					$.ajax({
						url : '${Config.baseURL}cloud-purchase-web/scConsumerInfoController/'+id,
						type : 'get',
						success : function(result) {
							if(result.flag){  
								consEditVue.$set('params',eval(result.data));
								if(me.params.scConsumerInfo.orgId != null && me.params.scConsumerInfo.orgId != ""){
									$.ajax({
						                url:basePath+"cloud-purchase-web/scOrgInfoController/"+me.params.scConsumerInfo.orgId,
						                type:"get",
						                success : function(orgdata) {
						                    if(orgdata.flag){
						                    	me.orgInfo = orgdata.data;
						                    }
						                }
						            })
								}
								if(me.params.scConsumerInfo.industrialZone != null && me.params.scConsumerInfo.industrialZone != ""){
									$.ajax({
						                url:basePath+"cloud-purchase-web/scIndustrialZoneController/"+me.params.scConsumerInfo.industrialZone,
						                type:"get",
						                success : function(zonedata) {
						                    if(zonedata.flag){
						                    	me.zoneInfo = zonedata.data;
						                    }
						                }
						            })
								}
								$('#mpInfoGrid').datagrid('loadData',result.data.mpList);
								consMainDetailVue.consId = result.data.scConsumerInfo.id;
								if(result.data.parentConsInfoDetail !=  null && result.data.parentConsInfoDetail.id != null){
									$('#connectedGrid').datagrid('appendRow', result.data.parentConsInfoDetail);
								}
							}
						}
					});
				},
				initconsAddGrid:function(){
					$("#mpInfoGrid").datagrid({
						width:"100%",
						height:200,
						striped:true,
						//fitColumns:false,
						singleSelect:true,
						rownumbers:true,
						idField:'id',
						columns:[[
								{field:'ck',checkbox:"true"}, 
						        {field:'id',hidden:true,align:'left'},
								{field:'consId',hidden:true,align:'left'},
								{field:'marketConsNo',title:'营销户号',width:'18%',align:'left'},
 								{field:'mpNo',title:'计量点编号',width:'18%',align:'center'},
								{field:'mpName',title:'计量名称',width:'18%',align:'center'},
 								{field:'meterNo',title:'电能表编号',width:'18%',align:'center'},
								{field:'meterRate',title:'电能表倍率',width:'18%',align:'center'},
								{field:'meterDigits',title:'电能表位数',width:'9%',align:'center'}
						]]
					});
				},
				initconnectedGrid:function(){
					$("#connectedGrid").datagrid({
						//width:"100%",
						height:200,
						striped:true,
						//fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						idField:'id',
						columns:[[
						        {field:'id',hidden:true,align:'left'},
 								{field:'consName',title:'关联用户名称',width:'33%',align:'left'},
 								{field:'voltCodeName',title:'电压等级',width:'33%',align:'center'},
								{field:'elecTypeName',title:'用电类别',width:'33%',align:'center'}
						]]
					});
				},
				closeDialog:function(){
					MainFrameUtil.closeDialog("cons-edit");
				},
				getCodeList:function(flag,code){
					var me =this;
                    var url = '';
                    if(flag){
                        url = "/globalCache/queryCityByParentCode/"
                    }else{
                        url = "/globalCache/queryCountyByParentCode/"
                    }
                    $.ajax({
                        url : basePath+url+code,
	                    type : 'get',
	                    success : function(data) {
	                    	if(flag){     //点击了省的下拉
	                    		me.cityCodeList = data;
	                    		if(data === null || data.length == 0){
	                    			me.countyCodeList = [];
	                    		}    
	                    	}else{        //点击了市的下拉
	                    		me.countyCodeList = data;
	                    	}
	                    }
	                });
				}
			},
			watch:{
				'params.scConsumerInfo.provinceCode':function(value){
					if(value){
						this.getCodeList(true,value);
					}else{
						this.cityCodeList = [];
					}
				},
				'params.scConsumerInfo.cityCode':function(value){
					if(value){
						this.getCodeList(false,value);
					}else{
						this.countyCodeList = [];
					}
				},
				formFields:function(){
					this.initData(MainFrameUtil.getParams("cons-detail").consId);
				}
			}
		});
	</script>
</div>
