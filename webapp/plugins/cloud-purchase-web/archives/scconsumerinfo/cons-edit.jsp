<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
       <head>
             <title>用户档案-用户档案编辑基本信息</title>
       
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
<body>
<div id="consEditVue" v-cloak style="padding-bottom: 1px">
       <su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
             <mk-form-panel title="用户基本信息" label_width="140px">
                    <mk-form-row>
                           <!-- 用户名称 -->
                           <mk-form-col :label="formFields.consName.label" :class="{'display-none':!formFields.consName.formHidden}" required_icon="true">
                                 <su-textbox :data.sync="params.scConsumerInfo.consName" type="text" name="consName"></su-textbox>
                           </mk-form-col>
                           <!-- 用户类型 -->
                           <mk-form-col :label="formFields.consType.label" :class="{'display-none':!formFields.consType.formHidden}" required_icon="true">
                                 <su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.consType"
                                        url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_consType" name="consType"></su-select>
                           </mk-form-col>
                            <!-- 公示序列号 -->
                           <mk-form-col :label="formFields.dealSeq.label" :class="{'display-none':!formFields.dealSeq.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.dealSeq" type="text"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 行业分类 -->
                           <mk-form-col :label="formFields.industryType.label" :class="{'display-none':!formFields.industryType.formHidden}" required_icon="true">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.industryType"
                                        url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_industryType" name="industryType"></su-select><!-- sell_industryType -->
                           </mk-form-col>
                           <!-- 所属供电公司 -->
                           <mk-form-col :label="formFields.orgId.label" :class="{'display-none':!formFields.orgId.formHidden}">
                                 <mk-trigger-text  :data.sync="orgInfo.name" editable="false" @mk-trigger="selectOrg" name="orgId"></mk-trigger-text>
<%--                             <mk-selectbox :cacheurl="cacheurl" url="${Config.baseURL}view/cloud-purchase-web/archives/scorginfo/org-dialog" height="500" width="800" v-ref:orginfo textfield="name" propname="id" :propvalue.sync="params.scConsumerInfo.orgId" :name="orgId"></mk-selectbox> --%>
<!--                             <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.orgId" -->
<%--                                           url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_orgCode" :name="orgId"></su-select> --%>
                           </mk-form-col>
                           <!-- 所属园区 -->
                           <mk-form-col :label="formFields.industrialZone.label" :class="{'display-none':!formFields.industrialZone.formHidden}">
                                 <mk-trigger-text  :data.sync="zoneInfo.izName" editable="false" @mk-trigger="selectZone" ></mk-trigger-text>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 省 -->
                           <mk-form-col :label="formFields.provinceCode.label" :class="{'display-none':!formFields.provinceCode.formHidden}" required_icon="true">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.provinceCode" :high="150"
                                        url="${Config.baseURL}globalCache/queryProvinceList" name="provinceCode"></su-select>
                           </mk-form-col>
                           <!-- 市 -->
                           <mk-form-col :label="formFields.cityCode.label" :class="{'display-none':!formFields.cityCode.formHidden}" required_icon="true">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.cityCode" :high="150"
                                        :data-source='cityCodeList' name="cityCode"></su-select>
                           </mk-form-col>
                           <!-- 区县 -->
                           <mk-form-col :label="formFields.countyCode.label" :class="{'display-none':!formFields.countyCode.formHidden}" required_icon="true">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.countyCode" :high="150"
                                        :data-source='countyCodeList' name="countyCode"></su-select>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                        <!-- 是否完成电能信息采集系统 -->
                        <mk-form-col :label="formFields.scadaFlag.label" :class="{'display-none':!formFields.scadaFlag.formHidden}">
                              <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.scadaFlag"
                                     url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="scadaFlag"></su-select>
                        </mk-form-col>
                       
                        <!-- 是否打包 -->
<!--                         <mk-form-col :label="formFields.scPackage.label" :class="{'display-none':!formFields.scPackage.formHidden}"> -->
<!--                               <su-select placeholder="--请选择--" :disabled="isPackageFlag" :select-value.sync="params.scConsumerInfo.scPackage" height="auto" -->
<!--                                      :data-source='scPackageList'  name="scPackage"> -->
<!--                               </su-select> -->
<!--                         </mk-form-col> -->
						 <!-- 市场状态 -->
                         <mk-form-col :label="formFields.marketStatusName.label" :class="{'display-none':!formFields.marketStatusName.formHidden}" required_icon="false">
                               <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.marketStatus"
                                      url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_marketStatusCode" name="marketStatus"></su-select>
                         </mk-form-col>
                          <!-- 是否直购电用户 -->
                         <mk-form-col :label="formFields.directPowerName.label" :class="{'display-none':!formFields.directPowerName.formHidden}" required_icon="false">
                               <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.directPower"
                                      url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" name="directPower"></su-select>
                         </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 用电地址 -->
                           <mk-form-col :label="formFields.elecAddr.label" :class="{'display-none':!formFields.elecAddr.formHidden}" colspan="3">
                                 <su-textbox :data.sync="params.scConsumerInfo.elecAddr" type="text"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
             </mk-form-panel>
             <mk-form-panel title="用电信息" label_width="140px" num="2">
                    <mk-form-row>
                           <!-- 用电容量 -->
                           <mk-form-col :label="formFields.electricCapacity.label" :class="{'display-none':!formFields.electricCapacity.formHidden}" required_icon="true">
                                 <su-textbox :addon="{'show':true,'rightcontent':'kVA'}" :data.sync="params.scConsumerInfo.electricCapacity" type="number" name="electricCapacity"></su-textbox>
                           </mk-form-col>
                           <!-- 用电类别 -->
                           <mk-form-col :label="formFields.elecTypeCode.label" :class="{'display-none':!formFields.elecTypeCode.formHidden}" required_icon="true">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.elecTypeCode"
                                        url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" name="elecTypeCode"></su-select>
                           </mk-form-col>
                           
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 电压等级 -->
                           <mk-form-col :label="formFields.voltCode.label" :class="{'display-none':!formFields.voltCode.formHidden}" required_icon="true">
                                 <su-select placeholder="请先选择用电类别" label-name="name" :select-value.sync="params.scConsumerInfo.voltCode"
                                        :data-source.sync="voltCodeData" name="voltCode"></su-select>
                           </mk-form-col>
                           
                           <!-- 安排年度上限 -->
                           <mk-form-col :label="formFields.upperPq.label" :class="{'display-none':!formFields.upperPq.formHidden}">
                                 <su-textbox :addon="{'show':true,'rightcontent':'亿千瓦时'}" :data.sync="params.scConsumerInfo.upperPq" type="number"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 负荷性质 -->
                           <mk-form-col :label="formFields.lodeAttrCode.label" :class="{'display-none':!formFields.lodeAttrCode.formHidden}">
                                 <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scConsumerInfo.lodeAttrCode"
                                        url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_lodeAttrCode"></su-select>
                           </mk-form-col>
                           <!-- 抄表例日 -->
                           <mk-form-col label="抄表例日">
                                <mk-trigger-text  :data.sync="params.usuallyDateName" editable="false" @mk-trigger="manageUsuallyDate" name="usuallyDateName"></mk-trigger-text>
                           </mk-form-col>
                    </mk-form-row>
             </mk-form-panel>    
       
             <mk-panel title="计量点信息" line="true" >
                    <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
                     <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="append">新增</su-button>
                     <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="remove">删除</su-button>
                 </div>      
                 <div id="mpInfoGrid" class="col-xs-12"></div>
             </mk-panel>
             <mk-form-panel title="商务信息" label_width="140px" num="2">
                    <mk-form-row>
                           <!-- 营业执照注册号 -->
                           <mk-form-col :label="formFields.registrationNo.label" :class="{'display-none':!formFields.registrationNo.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.registrationNo" type="text" name="registrationNo"></su-textbox>
                           </mk-form-col>
                           <!-- 税务登记证号 -->
                           <mk-form-col :label="formFields.vatNo.label" :class="{'display-none':!formFields.vatNo.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.vatNo" type="text" name="vatNo"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 组织机构代码 -->
                           <mk-form-col :label="formFields.orgCode.label" :class="{'display-none':!formFields.orgCode.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.orgCode" type="text" name="orgCode"></su-textbox>
                           </mk-form-col>
                           <!-- 法人名称 -->
                           <mk-form-col :label="formFields.legalAgent.label" :class="{'display-none':!formFields.legalAgent.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.legalAgent" type="text" name="legalAgent"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 法人代表名称 -->
                           <mk-form-col :label="formFields.authAgent.label" :class="{'display-none':!formFields.authAgent.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.authAgent" type="text" name="authAgent"></su-textbox>
                           </mk-form-col>
                           <!-- 企业注册地址 -->
                           <mk-form-col :label="formFields.registeredAddress.label" :class="{'display-none':!formFields.registeredAddress.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.registeredAddress" type="text" name="registeredAddress"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 开户银行 -->
                           <mk-form-col :label="formFields.bankName.label" :class="{'display-none':!formFields.bankName.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.bankName" type="text" name="bankName"></su-textbox>
                           </mk-form-col>
                           <!-- 开户名称 -->
                           <mk-form-col :label="formFields.countName.label" :class="{'display-none':!formFields.countName.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.countName" type="text" name="countName"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 开户账号 -->
                           <mk-form-col :label="formFields.countNo.label" :class="{'display-none':!formFields.countNo.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.countNo" type="text" name="countNo"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
             </mk-form-panel>
             <mk-form-panel title="联系人信息" label_width="140px" num="2">
                    <mk-form-row>
                           <!-- 联系人姓名 -->
                           <mk-form-col :label="formFields.contNameP.label" :class="{'display-none':!formFields.contNameP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.contName" type="text" name="contNameP"></su-textbox>
                           </mk-form-col>
                           <!-- 职务 -->
                           <mk-form-col :label="formFields.postP.label" :class="{'display-none':!formFields.postP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.post" type="text"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 手机号 -->
                           <mk-form-col :label="formFields.phoneP.label" :class="{'display-none':!formFields.phoneP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.phone" type="text" name="phoneP"></su-textbox>
                           </mk-form-col>
                           <!-- 办公电话 -->
                           <mk-form-col :label="formFields.officePhoneP.label" :class="{'display-none':!formFields.officePhoneP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.officePhone" type="text" name="officePhoneP"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 传真号 -->
                           <mk-form-col :label="formFields.faxP.label" :class="{'display-none':!formFields.faxP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.fax" type="text"></su-textbox>
                           </mk-form-col>
                           <!-- 电子邮箱 -->
                           <mk-form-col :label="formFields.eMailP.label" :class="{'display-none':!formFields.eMailP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.eMail" type="text" name="eMailP"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 通信地址 -->
                           <mk-form-col :label="formFields.addrP.label" :class="{'display-none':!formFields.addrP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.addr" type="text"></su-textbox>
                           </mk-form-col>
                           <!-- 邮政编码 -->
                           <mk-form-col :label="formFields.postcodeP.label" :class="{'display-none':!formFields.postcodeP.formHidden}">
                                 <su-textbox :data.sync="params.scContactsInfo.postcode" type="text" name="postcodeP"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
             </mk-form-panel>
             <mk-form-panel title="售电公司服务人员信息" label_width="140px" num="2">
                    <mk-form-row>
                           <!-- 负责人姓名 -->
                           <mk-form-col :label="formFields.sellPerson.label" :class="{'display-none':!formFields.sellPerson.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.sellPerson" type="text" name="sellPerson"></su-textbox>
                           </mk-form-col>
                           <!-- 职务 -->
                           <mk-form-col :label="formFields.sellPost.label" :class="{'display-none':!formFields.sellPost.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.sellPost" type="text"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 手机号 -->
                           <mk-form-col :label="formFields.sellPhone.label" :class="{'display-none':!formFields.sellPhone.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.sellPhone" type="text" name="sellPhone"></su-textbox>
                           </mk-form-col>
                           <!-- 办公电话 -->
                           <mk-form-col :label="formFields.officePhone.label" :class="{'display-none':!formFields.officePhone.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.officePhone" type="text" name="officePhone"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
                    <mk-form-row>
                           <!-- 传真号 -->
                           <mk-form-col :label="formFields.faxNo.label" :class="{'display-none':!formFields.faxNo.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.faxNo" type="text"></su-textbox>
                           </mk-form-col>
                           <!-- 电子邮箱 -->
                           <mk-form-col :label="formFields.sellEMail.label" :class="{'display-none':!formFields.sellEMail.formHidden}">
                                 <su-textbox :data.sync="params.scConsumerInfo.sellEMail" type="text" name="sellEMail"></su-textbox>
                           </mk-form-col>
                    </mk-form-row>
             </mk-form-panel>
             <mk-panel title="添加上级单位" line="true" >
                    <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
                     <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="selectCons">选择</su-button>
                     <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="delRela">删除上级单位</su-button>
                 </div>      
                 <div id="connectedGrid" class="col-xs-12"></div>
             </mk-panel>
       </su-form>
       <script type="text/javascript">
             var basePath = '${Config.baseURL}';
             //对应 js
             var consEditVue=new Vue({
                    el: '#consEditVue',
                    data: {      
                           scPackageList : [{"value":"1","label":"是","checked":null},{"value":"0","label":"否","checked":null}],
                           isPackageFlag : true,
                           voltCodeData:[],
                           voltCodeUrl : "", // 电压等级url
                           voltCode : "",
                           cacheurl : "",
                           cityCodeList:[],
                           countyCodeList:[],
                           formFields:{},
                           urlCache:"",
                           zoneInfo:null,
                           orgInfo : null,
                           consTypeFlag:false,
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
                                 ids : null,
                                 isPackageDisable:"disabled",
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
                                 //consIds:[],无用参数，删除--by 王泽鲁
                          //addIds:[],
                          //delConsId:[],
                                 mpList:null,
                                 usuallyDateName: null
                           },
                        //验证规则
                           dataOption: {
                                 rules: {
                                        vatNo:{maxlength:32},
                                  registeredAddress:{maxlength:256},
                                  sellPhone:{maxlength:16,isMobile:!0},
                                  consName:{required: !""},
                                  bankName:{maxlength:256},
                                  countyCode:{required: !""},
                                  industryType:{required: !""},
                                  elecTypeCode:{required: !""},
                                  voltCode:{required: !""},
                                  provinceCode:{required: !""},
                                  orgCode:{maxlength:32},
                                  legalAgent:{maxlength:32},
                                  countNo:{maxlength:32},
                                  consType:{required: !""},
                                  registrationNo:{maxlength:32},
                                  cityCode:{required: !""},
                                  upperPq:{required: !""},
                                  authAgent:{maxlength:32},
                                  consName:{required: !""},
                                  sellPerson:{maxlength:16},
                                  countName:{maxlength:256},
                                  electricCapacity:{required: !""},
                                  contNameP:{maxlength:16},
                                  phoneP:{isMobile:!0 },
                                  postP:{maxlength:32},
                                  eMailP:{email:!0},
                                  sellEMail:{email:!0},
                                  officePhoneP:{isPhone:!0},
                                  officePhone:{isPhone:!0},
                                  postcodeP:{isZipCode:!0},
                                  phoneP2:{isMobile:!0 },
                                 },
                                 messages:{
                                        registrationNo:{maxlength:"最多输入32个字"},
                                        vatNo:{maxlength:"最多输入32个字"},
                                        orgCode:{maxlength:"最多输入32个字"},
                                        legalAgent:{maxlength:"最多输入32个字"},
                                        authAgent:{maxlength:"最多输入32个字"},
                                        registeredAddress:{maxlength:"最多输入256个字"},
                                        bankName:{maxlength:"最多输入256个字"},
                                        countName:{maxlength:"最多输入256个字"},
                                        countNo:{maxlength:"最多输入32个字"},
                                        sellPerson:{maxlength:"最多输入16个字"},
                                        sellPhone:{maxlength:"最多输入16个字"},
                                        contNameP:{maxlength:"最多输入16个字"},
                                 }
                           },
                           valid: false
                    },
                    ready:function(){
                           var me = this;
                           //查询字段名称加载
                           ComponentUtil.getFormFields("selling.archives.scconsumerinfo",me);
                           me.initconsAddGrid();
                           me.initconnectedGrid();
                           me.consTypeFlag = false;
                           $.validator.addMethod(
                                     "isMobile",function(value, element) {
                                               var length = value.length;
                                               var mobile = /^(((17[0-9]{1})|(13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;
                                               return this.optional(element) || (length == 11 && mobile.test(value));
                                        },
                                     "请输入正确的手机号"
                           );
                    },
                    methods: {
                           delRela:function(){
                                 var rows = $('#connectedGrid').datagrid("getRows");             //0行或1行
                                 if(rows != null && rows.length != 0){
                                        this.params.scConsumerInfo.parentId = null;
                                        this.params.scConsumerInfo.consPath = null;
                                   $('#connectedGrid').datagrid('deleteRow', 0); 
                                 }
                           },
                           initData:function(id){
                                 var me = this;
                                 //初始化数据  下方监听调用
                                 $.ajax({
                                        url : '${Config.baseURL}cloud-purchase-web/scConsumerInfoController/'+id,
                                        type : 'get',
                                        success : function(result) {
                                               if(result.flag){ 
                                                      consEditVue.$set('params',eval(result.data));
                                                     me.voltCode = result.data.scConsumerInfo.voltCode;
//                                                   me.cacheurl = "${Config.baseURL}cloud-purchase-web/scOrgInfoController/" + result.data.scConsumerInfo.orgId;
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
                                                    //$("#lengthFlag").attr("disabled","true");
                                                    me.isPackageFlag = false;
                                                     }
                                                      $('#mpInfoGrid').datagrid('loadData',result.data.mpList);
                                                      consEditVue.startEditing();
                                                     consMainEditVue.consId = result.data.scConsumerInfo.id;
                                                      if(result.data.parentConsInfoDetail !=  null && result.data.parentConsInfoDetail.id != null){
                                                            $('#connectedGrid').datagrid('appendRow', result.data.parentConsInfoDetail);
                                                     }
                                               }else{
                                            	   MainFrameUtil.alert({title:"失败提示！",body: result.msg});
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
                                                      {field:'marketConsNo',title:'营销户号',width:'18%',align:'left',
                                                          editor:{
                                                                 id:"marketConsNo",
                                                                 type:'textbox',
                                                                 options:{
                                                                	 	required:true,
                                                                        validType:"length[0,32]"
                                                                 }
                                                          }
                                                     },
                                                     {field:'mpNo',title:'计量点编号',width:'18%',align:'center',
                                                            editor:{
                                                                   id:"mpNo",
                                                                   type:'textbox',
                                                                   options:{
                                                                          validType:"length[0,64]"
                                                                   }
                                                            }
                                                     },
                                                     {field:'mpName',title:'计量名称',width:'18%',align:'center',
                                                            editor:{
                                                                   id:"mpName",
                                                                   type:'textbox',
                                                                   options:{
                                                                          validType:"length[0,32]"
                                                                   }
                                                            }
                                                     },
                                                     {field:'meterNo',title:'电能表编号',width:'18%',align:'center',
                                                            editor:{
                                                                   id:"meterNo",
                                                                   type:'textbox',
                                                                   options:{
                                                                          validType:"length[0,64]"
                                                                   }
                                                            }
                                                     },
                                                      {field:'meterRate',title:'电能表倍率',width:'18%',align:'center',
                                                            editor:{
                                                                   id:"meterRate",
                                                                   type:'textbox',
                                                                   options:{
                                                                          validType:"length[0,8]"
                                                                   }
                                                            }
                                                     },
                     								{field:'meterDigits',title:'电能表位数',width:'9%',align:'center',
                     									editor:{
                     										id:"meterDigits ",
                     										type:'numberbox',
                     										options:{
                     											precision:1,
                     											max:9.9
                     										}
                     									}
                     								}
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
                           getEditData:function(index){
                                 var row =  $('#mpInfoGrid').datagrid("getRows")[index];
                                 var editors = $('#mpInfoGrid').datagrid('getEditors', index);
                                 var object = new Object();
                                 for(var obj in editors){
                                        var editor = editors[obj];
                                        if(editor.type == "textbox" || editor.type == "numberbox"){
                                               object[editor.field] = $(editor.target).val();
                                        }else if(editor.type == "combobox"){
                                               object[editor.field] = $(editor.target).combobox('getValue');
                                        }else if(editor.type == "datebox"){
                                               object[editor.field] = $(editor.target).parent().find("input[type='hidden']").val();
                                        }
                                 }
                                 if(row.id){
                                        object["id"] = row.id;
                                 }
                                 return object;
                           },
                           startEditing:function(){
                                 var rows = $("#mpInfoGrid").datagrid("getRows");
                                 for(var obj in rows){
                                        var index = $('#mpInfoGrid').datagrid('getRowIndex', rows[obj]);
                                        $('#mpInfoGrid').datagrid('beginEdit',index);
                                 }
                                 $('#mpInfoGrid').datagrid('selectRow',0);
                           },
                           endEditing:function(){
                                 var rows = $("#mpInfoGrid").datagrid("getRows");
                                 var datas = new Array();
                                 var me = this;
                                        
                                 /* 验证计量点信息填全了吗 */
                                 for(var obj in rows){
                                        var index = $('#mpInfoGrid').datagrid('getRowIndex', rows[obj]);
                                        if($('#mpInfoGrid').datagrid('validateRow', index)){
                                               var data = this.getEditData(index);
                                               datas.push(data);
                                        }else{
                                               MainFrameUtil.alert({title:"警告",body:"还有未完成编辑的计量点信息，请完成编辑或删除！"});
                                               return false;
                                        }
                                 }
                                 
                                 /* 验证 电能表编号 + 营销户号 重复 */
                                 var marketConsNo = new Object;
                                 for(var index in rows){
	                                 var marketKey = null;
                                	 var marketEd = $('#mpInfoGrid').datagrid('getEditor', {index:index,field:'marketConsNo'});
                                	 marketKey = $(marketEd.target).textbox('getValue');
                                     var eds = $('#mpInfoGrid').datagrid('getEditor', {index:index,field:'meterNo'});
                                     meterNoKey = $(eds.target).textbox('getValue');
                                     if(meterNoKey != null && meterNoKey != ''){
                                    	 marketKey = marketKey + meterNoKey;
                                     }
                                     if(marketKey !=null && marketKey!= '' && typeof marketConsNo[marketKey] != "undefined" && meterNoKey != null && meterNoKey != ''){
                                    	 MainFrameUtil.alert({title:"警告",body:"营销户号和电能表编号重复！"});
                                         return; 
                                     }else if(marketKey !=null && marketKey!= '' && typeof marketConsNo[marketKey] != "undefined" ){
                                    	 MainFrameUtil.alert({title:"警告",body:"营销户号重复！"});
                                         return; 
                                     }
                                	 
                                     marketConsNo[marketKey] = marketKey;
                                 }
                                 this.$set("params.mpList",datas);
                                 return true;
                           },
                           append:function(){
                                        $('#mpInfoGrid').datagrid('appendRow',{});
                                        var index = $('#mpInfoGrid').datagrid('getRows').length-1;
                                        $('#mpInfoGrid').datagrid('selectRow',index);
                                        $('#mpInfoGrid').datagrid('beginEdit',index);
                           },
                           remove:function(){
                             var me = this;
                             var row = $('#mpInfoGrid').datagrid('getSelected');
                                 if(row){
                                    MainFrameUtil.confirm({
                                         title:"确认",
                                         body:"删除后不可恢复，确认删除选中行？",
                                         onClose:function(type){
                                             if(type=="ok"){//确定
                                                if(row.consId != null && row.consId != ""){
                                                       $.ajax({
                                                             url : "${Config.baseURL}cloud-purchase-web/scMpInfoController/" +row.id,
                                                             type : 'delete',
                                                             //data:$.toJSON(this.params),
                                                             contentType : 'application/json;charset=UTF-8',
                                                             success : function(result) {
                                                                if(result.flag){
                                                                       //consMainEditVue.consId = result.data.id;
                                                                       MainFrameUtil.alert({title:"提示",body:"删除成功！"});
                                                                       $('#mpInfoGrid').datagrid('deleteRow', $('#mpInfoGrid').datagrid('getRowIndex',row));
                                                                }else{
                                                                       MainFrameUtil.alert({title:"提示",body:result.msg});
                                                                }
                                                             },
                                                             error : function() {
                                                                  MainFrameUtil.alert({title:"提示",body:"删除失败！"});
                                                             }
                                                      });
                                                }else{//没保存在删除
                                                       $('#mpInfoGrid').datagrid('deleteRow', $('#mpInfoGrid').datagrid('getRowIndex',row));
                                                }
                                             }
                                          }
                                       }); 
                                 }else{
                                        MainFrameUtil.alert({title:"提示",body:"请至少选择一条数据！"});
                                 }
                           },
                           saveConsumerInfo:function(){
                              consEditVue.$refs.form1.valid();
	                          if(consEditVue.valid===false){
                                 MainFrameUtil.alert({title:"提示",body:"您有必填项未填写！"});
                                 return;
	                          }else {
                                 if(consEditVue.endEditing()){
                                        consEditVue.save();
                                 }
                              }
                           },
                           save: function(){
                        	   if((this.params.scConsumerInfo.dealSeq) != null && (this.params.scConsumerInfo.dealSeq).length > 32){
                        		   MainFrameUtil.alert({title:"提示",body:"公示序列号最多输入32位！"});
                                   return;
                        	   }
                                 var me = this;
                                 if(me.params.scConsumerInfo.scadaFlag == ''){
                                        me.params.scConsumerInfo.scadaFlag = null;
                                 }
                                 $.ajax({
                                        url : "${Config.baseURL}cloud-purchase-web/scConsumerInfoController",
                                        type : 'put',
                                        data:$.toJSON(this.params),
                                        contentType : 'application/json;charset=UTF-8',
                                        success : function(result) {
                                               if(result.flag){
                                            	   $('#mpInfoGrid').datagrid('loadData',result.data.mpList);
                                                   consEditVue.startEditing();
                                                   me.params.mpList = result.data.mpList;
                                                      MainFrameUtil.alert({title:"提示",body:"更新成功！"});
                                               }else{
                                                      MainFrameUtil.alert({title:"提示",body:result.msg});
                                               }
                                        },
                                        error : function() {
                                               MainFrameUtil.alert({title:"提示",body:"更新失败！"});
                                        }
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
                           },
                           
                           
                           selectCons:function(){
                                 var me = this;
                                 var rows = $('#connectedGrid').datagrid('getRows');
                                 if(rows != null && rows.length > 0){
                                        MainFrameUtil.alert({title:"提示",body:"已存在父级单位，请删除后选择！"});
                                        return;
                                 }
                                 MainFrameUtil.openDialog({
                                        id:'consDialog',
                                        href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
                                        params:{"singleSelect":true,excludePathId : me.params.scConsumerInfo.id},
                                        iframe:true,
                                        modal:true,
                                        width:'50%',
                                        height:620,
                                        onClose:function(params){
                                               if(params != null && params.length > 0){
                                                     var row = params[0];
                                                      me.params.scConsumerInfo.parentId = row.id;
                                                      me.params.scConsumerInfo.consPath = ((row.consPath == null || row.consPath == "") ? "" : (row.consPath + ",") ) + row.id;
                                                      $('#connectedGrid').datagrid('appendRow', params[0]);
                                               }
                                        }
                                 });
                          },
                          selectZone:function(){
                                 var me = this;
                                 MainFrameUtil.openDialog({
                                        id:'zoneDialog',
                                        href:'${Config.baseURL}view/cloud-purchase-web/archives/scindustrialzone/zone-dialog',
                                        iframe:true,
                                        modal:true,
                                        width:800,
                                        height:520,
                                        onClose:function(params){
                                               if(typeof(params[0])==="object"){
                                                     me.zoneInfo = params[0];
                                                     if(me.zoneInfo.izName != null && me.zoneInfo.izName !=""){
                                                            me.isPackageFlag = false;
                                                     }else{
                                                            me.isPackageFlag = true;
                                                     }
                                                      me.params.scConsumerInfo.industrialZone = params[0].id;
                                               }
                                        }
                                 });
                          },
                          selectOrg:function(){
                                 var me = this;
                                 MainFrameUtil.openDialog({
                                        id:'mk-power-up-dialog-id',
                                        href:'${Config.baseURL}view/cloud-purchase-web/archives/scorginfo/org-dialog',
                                        iframe:true,
                                        modal:true,
                                        width:800,
                                        height:520,
                                        onClose:function(params){
                                               if(typeof(params)==="object"){
													me.orgInfo = params;
													me.params.scConsumerInfo.orgId = params.id;
                                               }
                                        }
                                 });
                          },
                          getVoltCode:function(url){
                                 var me = this;
                                 $.ajax({
                                        url:url,
                                        type:"get",
                                        success:function(data){
                                              me.voltCodeData = data;
                                        }
                                 });
                          },
                          //选择抄表例日
                          manageUsuallyDate: function(){
                        	  var me = this;
	                          MainFrameUtil.openDialog({
	                                 id:'usuallyDateDialog',
	                                 href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/usually_date_dialog',
	                                 params:{"consId": consMainEditVue.consId, saveList: []},
	                                 iframe:true,
	                                 modal:true,
	                                 width:800,
	                                 height:520,
	                                 maximizable:true,
	                                 onClose:function(params){
	                                 }
	                          });  
                          }
                    },
                    watch:{
                           "params.scConsumerInfo.elecTypeCode":function(value){
                                 var me = this;
                                 if(value == "100" || value == "400"){
                                        me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&pCode.content5=" + value;
                                        me.getVoltCode(me.voltCodeUrl);
                                        if(me.voltCode != ""){
                                               me.params.scConsumerInfo.voltCode = me.voltCode;
                                               me.voltCode = "";
                                        }else{
                                               me.params.scConsumerInfo.voltCode = "";
                                        }
                                 }else{
                                        me.voltCodeUrl = "";
                                        me.voltCodeData = [];
                                        me.params.scConsumerInfo.voltCode = "";
                                 }
                           },
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
                                  this.initData(MainFrameUtil.getParams("cons-edit").consId);
                           }
                    }
             });
       </script>
</div>
</body>
</html>