<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style type="text/css">
.mk-form-table-title {
	color: #1aa4cf
}
.flag2{
	display: none
}
.btndiv{
	padding: 6px
}
 textarea{ resize:none;}
</style>
<div id="ruleBody">
	
<su-form 
		v-ref:form1 
		id="fms1" 
		offpos-style="true"
		:data-option="dataOption" 
		:set-defaults="setDefaults" 
		:data-validator.sync="valid" >
	<mk-panel title="偏差规则">
		<mk-form-panel title="" num="3">
			<mk-form-row >
				<!-- 规则名称  -->
				<mk-form-col :label="formFields.ruleName.label" required_icon="true">
					<su-textbox :data.sync="smRuleConfigure.ruleName" name="ruleName" ></su-textbox>
				</mk-form-col>
				<!-- 交割优先原则 -->
				<mk-form-col label="交割优先原则" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_settlementOrder&valueIn='01','02'" label-name="name"
	   					:select-value.sync="smRuleConfigure.priorityFlag" name="priorityFlag" ></su-select>
				</mk-form-col>
				<!-- 竞价优先原则 -->
				<mk-form-col label="竞价优先原则" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_principleFlag" label-name="name"
	   					:select-value.sync="smRuleConfigure.bidPriorityFlag" name="biddPriorityFlag" ></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 长协优先原则 -->
				<mk-form-col label="长协优先原则" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_principleFlag" label-name="name"
	   					:select-value.sync="smRuleConfigure.lcPriorityFlag" name="assoPriorityFlag" ></su-select>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="正偏差" num="3">
			<mk-form-row >
				<!-- 电价类型 -->
				<mk-form-col :label="formFields.pdevPrcType.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','03','04'" 
	   					:select-value.sync="smRuleConfigure.upperPrcType" name="pdevPrcType"  label-name="name"></su-select> <!-- @su-change="pdevFlagDisplay" -->
				</mk-form-col>
				<!-- 电价比例值 -->
				<mk-form-col label="电价比例值" v-if="!pdevPrcFlag" required_icon="true">
					<su-textbox :data.sync="smRuleConfigure.upperPrcProp" :addon="{'show':true,'rightcontent':'%'}"
					 name="pdevPrcStand"></su-textbox>
				</mk-form-col>
				<!-- 电价 -->
            	<mk-form-col label="电价" required_icon="true" v-if="pdevPrcFlag">
                	<su-textbox :data.sync="smRuleConfigure.upperPrc" name="pdevPrc" :addon="{'show':true,'rightcontent':'厘/千瓦时'}"></su-textbox>
            	</mk-form-col>
				<!-- 是否考核 -->
				<mk-form-col label="是否考核" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" label-name="name" 
	   				:select-value.sync="smRuleConfigure.upperCheckFlag"  name="pdevUpperFlag"></su-select> <!-- smRuleConfigure.mdevUpperFlag @su-change="upperFlagDisplay"  -->
				</mk-form-col>
			</mk-form-row>
			<mk-form-row :class="{'display-none':!upperFlag}">
				<!-- 允许偏差范围 -->
				<mk-form-col :label="formFields.allowPdevRange.label" required_icon="true">
					<su-textbox :data.sync="smRuleConfigure.upperPqProp" name="allowPdevRange" :addon="{'show':true,'rightcontent':'%'}">
					</su-textbox>
				</mk-form-col>
				<!-- 惩罚电价 -->
				<mk-form-col :label="formFields.pdevPunType.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','03','04'"  label-name="name" 
	   					:select-value.sync="smRuleConfigure.upperCheckPrcType" name="pdevPunType" ></su-select><!-- @su-change="upperTypeChange"  -->
				</mk-form-col>
				<!-- 惩罚电价比例 -->
				<mk-form-col :label="formFields.pdevPunPrcStand.label" v-if="!upperPrcFlag" required_icon="true">
					<su-textbox :data.sync="smRuleConfigure.upperCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}"
					 name="pdevPunPrcStand"></su-textbox>
				</mk-form-col>
				<!-- 正偏差惩罚协议价 -->
            	<mk-form-col label="惩罚协议价" required_icon="true" v-if="upperPrcFlag">
                	<su-textbox :data.sync="smRuleConfigure.upperCheckPrc" name="pdevUpperPrc" :addon="{'show':true,'rightcontent':'厘/千瓦时'}"></su-textbox>
            	</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-form-panel title="负偏差" num="3">
			<mk-form-row >
				<!-- 是否考核 -->
				<mk-form-col label="是否考核" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" label-name="name" 
	   				:select-value.sync="smRuleConfigure.lowerCheckFlag"  name="mdevLowerFlag"></su-select> <!-- @su-change="lowerFlagDisplay" -->
				</mk-form-col>
				<!-- 是否拆分考核 -->
				<mk-form-col :label="formFields.mdevSplitFlag.label" class="splitCheckFlagFlag" >
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" label-name="name" 
	   				:select-value.sync="smRuleConfigure.splitCheckFlag" name="mdevSplitFlag"></su-select><!-- :disabled.sync="changeFlag"  @su-change="changeflag" -->
				</mk-form-col>
				<div :class="{'display-none':!lowerFlag}">
					<div :class="{'display-none':splitFlag}">
						<!-- 允许偏差范围 -->
						<mk-form-col :label="formFields.allowMdevRange.label" required_icon="true">
							<su-textbox :data.sync="smRuleConfigure.lowerPqProp" :addon="{'show':true,'rightcontent':'%'}"
							name="allowMdevRange" ></su-textbox>
						</mk-form-col>
					</div>
				</div>
			</mk-form-row>
			<div :class="{'display-none':!lowerFlag}">
			<mk-form-row :class="{'display-none':splitFlag}">
				<!-- 惩罚电价 -->
				<mk-form-col :label="formFields.mdevPunType.label" required_icon="true">
	   				<su-select  url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','03','04'" label-name="name"
	   					:select-value.sync="smRuleConfigure.lowerCheckPrcType" name="mdevPunType"></su-select><!-- @su-change="lowerTypeChange" -->
				</mk-form-col>
				<!-- 惩罚电价比例 -->
				<mk-form-col :label="formFields.mdevPunPrcStand.label" v-if="!lowerPrcFlag" required_icon="true">
					<su-textbox :data.sync="smRuleConfigure.lowerCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}" 
					name="mdevPunPrcStand"></su-textbox>
				</mk-form-col>
				<!-- 负偏差惩罚协议价 -->
            	<mk-form-col label="惩罚协议价" required_icon="true" v-if="lowerPrcFlag">
                	<su-textbox :data.sync="smRuleConfigure.lowerCheckPrc" name="mdevLowerPrc" :addon="{'show':true,'rightcontent':'厘/千瓦时'}"></su-textbox>
            	</mk-form-col>
          		</mk-form-row>
            <mk-form-row :class="{'display-none':!splitFlag}">
           		<!-- 是否考核 -->
				<mk-form-col label="长协是否考核" required_icon="true" >
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" label-name="name" 
	   				:select-value.sync="mdevAssoFlag"  name="mdevAssoFlag"></su-select><!-- @su-change="assoFlagDisplay"  -->
				</mk-form-col>
				<div :class="{'display-none':!assoFlag}">
					<!-- 长协允许偏差范围 -->
					<mk-form-col label="长协偏差范围" required_icon="true">
						<su-textbox :data.sync="smRuleConfigure.lowerCheckLcPqProp" :addon="{'show':true,'rightcontent':'%'}"
						name="allowMdevRange" ></su-textbox>
					</mk-form-col>
					<!-- 长协惩罚电价 -->
					<mk-form-col :label="formFields.mdevAssoType.label" required_icon="true" >
		   				<su-select  url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','03','04'" label-name="name"
		   				:select-value.sync="smRuleConfigure.lowerCheckLcPrcType"  name="mdevAssoType" ></su-select><!-- @su-change="assoTypeChange" -->
					</mk-form-col>
				</div>
			</mk-form-row>
			<mk-form-row :class="{'display-none':!assoFlag}">
				<!-- 长协惩罚比例 -->
					<mk-form-col :label="formFields.mdevAssoPrcStand.label" v-if="!assoPrcFlag" required_icon="true">
						<su-textbox :data.sync="smRuleConfigure.lowerCheckLcPrcProp" :addon="{'show':true,'rightcontent':'%'}"
						name="mdevAssoPrcStand" ></su-textbox>
					</mk-form-col>
				<!-- 长协惩罚协议价 -->
	            	<mk-form-col label="长协惩罚协议价" required_icon="true" v-if="assoPrcFlag">
	                	<su-textbox :data.sync="smRuleConfigure.lowerCheckLcPrc" name="mdevAssoPrc" :addon="{'show':true,'rightcontent':'厘/千瓦时'}"></su-textbox>
	            	</mk-form-col>
            </mk-form-row>
			<mk-form-row  :class="{'display-none':!splitFlag}">
				<!-- 是否考核 -->
				<mk-form-col label="竞价是否考核" required_icon="true" >
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" label-name="name" 
	   				:select-value.sync="mdevBiddFlag" name="mdevBiddFlag"></su-select><!--  @su-change="biddFlagDisplay"  -->
				</mk-form-col>
				<div :class="{'display-none':!biddFlag}">
					<!-- 竞价允许偏差范围 -->
					<mk-form-col label="竞价偏差范围 " required_icon="true">
						<su-textbox :data.sync="smRuleConfigure.lowerCheckBidPqProp" :addon="{'show':true,'rightcontent':'%'}"
						name="allowMdevRange" ></su-textbox>
					</mk-form-col>
					<!-- 竞价惩罚电价 -->
					<mk-form-col :label="formFields.mdevBiddType.label" required_icon="true">
		   				<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_enaltyPrc&valueIn='01','02','03','04'" label-name="name"
		   					:select-value.sync="smRuleConfigure.lowerCheckBidPrcType"  name="mdevBiddType" ></su-select><!--  @su-change="biddTypeChange" -->
					</mk-form-col>
					<!-- 竞价惩罚比例 -->
					<mk-form-col :label="formFields.mdevBiddPrcStand.label" v-if="!biddPrcFlag" required_icon="true">
						<su-textbox :data.sync="smRuleConfigure.lowerCheckBidPrcProp" :addon="{'show':true,'rightcontent':'%'}" 
						name="mdevBiddPrcStand"></su-textbox>
					</mk-form-col>
					<!-- 竞价惩罚协议价 -->
	            	<mk-form-col label="竞价惩罚协议价" required_icon="true" v-if="biddPrcFlag">
	                	<su-textbox :data.sync="smRuleConfigure.lowerCheckBidPrc" name="mdevBiddPrc" :addon="{'show':true,'rightcontent':'厘/千瓦时'}"></su-textbox>
	            	</mk-form-col>
            	</div>
			</mk-form-row>
			</div>
		</mk-form-panel>
	</mk-panel>
	<mk-panel title="申报规则">
		<mk-form-panel title="" num="3">
			<mk-form-row >
				<!-- 申报分段 -->
				<mk-form-col :label="formFields.declSeg.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sectionType" label-name="name"
	   					:select-value.sync="smRuleConfigure.declSeg" name="declSeg" ></su-select>
				</mk-form-col>
				<!-- 最小报量单位-->
				<mk-form-col :label="formFields.minReportUnit.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_minDeclarePq" label-name="name"
	   					:select-value.sync="smRuleConfigure.minReportUnit"  name="minReportUnit"></su-select>
				</mk-form-col>
				<!-- 售方申报单元 -->
				<mk-form-col :label="formFields.sellDeclUnit.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_saleDeclareUnit"  label-name="name"
	   					:select-value.sync="smRuleConfigure.sellDeclUnit"  name="sellDeclUnit"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row >
				<!-- 报价方式 -->
				<mk-form-col :label="formFields.quotMode.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_declarePriceType" label-name="name"
	   					:select-value.sync="smRuleConfigure.quotMode"  name="quotMode"></su-select>
				</mk-form-col>
				<!-- 最小报价单位 -->
				<mk-form-col :label="formFields.minQuotUnit.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_minDeclarePrice"  label-name="name"
	   					:select-value.sync="smRuleConfigure.minQuotUnit" name="minQuotUnit"></su-select>
				</mk-form-col>
				<!-- 购方申报单元 -->
				<mk-form-col :label="formFields.buyDeclUnit.label" required_icon="true">
	   				<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellDeclareUnit" label-name="name"
	   					:select-value.sync="smRuleConfigure.buyDeclUnit" name="buyDeclUnit"  ></su-select>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<mk-panel title="竞价中标规则">
		<mk-form-panel title="" num="3">
			<mk-form-row >
				<!-- 中标规则算法 -->
				<mk-form-col :label="formFields.bidRuleAlgoId.label" required_icon="true">
	   				<su-select  label-name="name" name="bidRuleAlgoId" :data-source.sync="bidRuleAlgoIdList"
	   					:select-value.sync="smRuleConfigure.bidRuleAlgoId" ></su-select>
				</mk-form-col>
				<!-- <div class="col-xs-2 mk-form-column btndiv">
					<su-button s-type="primary" v-on:click="" >查看算法</su-button>
				</div> -->
			</mk-form-row>
			<mk-form-row height="90px">
				<!-- 算法说明 -->
				<mk-form-col :label="formFields.bidAlgoExpln.label" colspan="3">
	   				<su-textbox :data.sync="bidAlgoExpln" type="textarea" rows="3" disabled></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
	</mk-panel>
	<mk-panel title="竞价出清规则">
		<mk-form-panel title="" num="3">
			<mk-form-row >
				<!-- 出清规则算法 -->
				<mk-form-col :label="formFields.voidRuleAlgoId.label" required_icon="true">
	   				<su-select  label-name="name" name="voidRuleAlgoId" :data-source.sync="voidRuleAlgoIdList"
	   					:select-value.sync="smRuleConfigure.voidRuleAlgoId" ></su-select>
				</mk-form-col>
				<!-- <div class="col-xs-2 mk-form-column btndiv">
					<su-button s-type="primary" v-on:click="">查看算法</su-button>
				</div> -->
			</mk-form-row>
			<mk-form-row height="90px">
				<!-- 算法说明 -->
				<mk-form-col :label="formFields.voidAlgoExpln.label" colspan="3">
	   				<su-textbox :data.sync="voidAlgoExpln" type="textarea" rows="3" disabled></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<div  class="pull-right" style="text-align:right" v-cloak>
	        <su-button s-type="primary" class="btn-width-small" v-on:click="save">保存</su-button>
	        <su-button s-type="default" class="btn-width-small" v-on:click="delConfirm">删除</su-button>
	    </div>
	</mk-panel>
	</su-form>
</div>
<script type="text/javascript">
//自定义的验证规则，在dataOption 验证规则里调用
$.validator.addMethod(     
        "isNum",        
        function(value, element)        
        {            
                var tel = /^([^0\D]\d*)?(\d?\.\d+)?0?$/;  
                return this.optional(element) || (tel.test(value));     
        }     
        , "请填写正确格式的数字！"            
);  
var ruleVue = new Vue({
	el:"#ruleBody",
	data:{
		mdevAssoFlag:"",//长协考核字段转存
		mdevBiddFlag:"",//竞价考核字段转存
		//priorityFlag //竞价长协优先
		//pdevPrc 电价
		//pdevPrcStand 电价比例值
		//biddPriorityFlag//竞价优先原则
		//assoPriorityFlag//长协优先原则
		//splitFlagLowerFlag:false,
		pdevPrcFlag:false,//电价类型
		splitFlag:false,//拆分考核
		biddFlag:false,//竞价是否考核
		assoFlag:false,//长协是否考核
		biddPrcFlag:false,//竞价惩罚协议价
		assoPrcFlag:false,//长协惩罚协议价
		lowerFlag:false,//负偏差是否考核
		upperFlag:false,//正偏差是否考核标识
		lowerPrcFlag:false,//负偏差惩罚协议价
		upperPrcFlag:false,//正偏差惩罚协议价展示标志
		//changeFlag:false,
		formFields:{},
		smRuleConfigure:{},
		dataOption: {
			rules: {
				ruleName: {required: !0},
				allowPdevRange: { required: !0, isNum:!0 },
				pdevPunType: { required: !0 },
				pdevPunPrcStand: { required: !0, isNum:!0 },	
				allowMdevRange: { required: !0, isNum:!0 },
				//mdevSplitFlag: { required: !0 },
				mdevPunType: { required: !0 },
				mdevPunPrcStand: { required: !0, isNum:!0 },
				mdevAssoType: { required: !0 },
				mdevAssoPrcStand: { required: !0, isNum:!0 },
				mdevBiddPrcStand: { required: !0, isNum:!0 },
				declSeg: { required: !0 },
				minReportUnit: { required: !0 },
				sellDeclUnit: { required: !0 },
				quotMode: { required: !0 },
				minQuotUnit: { required: !0 },
				buyDeclUnit: { required: !0 },
				bidRuleAlgoId: { required: !0 },
				voidRuleAlgoId: { required: !0 },
				pdevPrcType: { required: !0 },
				pdevPrcStand: { required: !0 },
				pdevUpperFlag: { required: !0 },
				pdevUpperPrc: { required: !0 },
				mdevLowerFlag: { required: !0 },
				mdevAssoFlag: { required: !0 },
				mdevBiddFlag: { required: !0 },
				mdevLowerPrc: { required: !0 },
				mdevAssoPrc: { required: !0 },
				mdevBiddPrc: { required: !0 },
				biddPriorityFlag: { required: !0 },
				assoPriorityFlag: { required: !0 },
				pdevPrc: { required: !0 },
				priorityFlag: { required: !0 },
				mdevBiddType: { required: !0 }
			}
		},
		valid: false,
		bidAlgoExpln:"",
		voidAlgoExpln:"",
		voidRuleAlgoIdList:[],
		bidRuleAlgoIdList:[],
		voidRuleAlgoIdObject:{},
		bidRuleAlgoIdObject:{}
	},
	ready:function(){
		ComponentUtil.getFormFields("selling.simulation.ruleconfiguration",this);
		this.getSelectDataSource("sell_clearRuleAlgorithm","voidRuleAlgoIdList","voidRuleAlgoIdObject");
		this.getSelectDataSource("sell_bidRuleAlgorithm","bidRuleAlgoIdList","bidRuleAlgoIdObject");
		$(".splitCheckFlagFlag").hide();
	},
	methods:{
		reset:function(){
			ruleVue.smRuleConfigure = {
					id: null,
					provinceId: null,
					ruleName:null,
					ruleStatus: null,
					upperPqProp: null,
					pdevPrc: null,
					upperCheckPrcType: "",
					upperCheckPrcProp: "",
					lowerPqProp: "",
					splitCheckFlag: null,
					mdevPrc: "",
					lowerCheckPrcType: "",
					lowerCheckPrcProp: "",
					lowerCheckLcPrcType: "",
					lowerCheckLcPrcProp: "",
					lowerCheckBidPrcType: "",
					lowerCheckBidPrcProp: "",
					declSeg: "",
					minReportUnit: "",
					sellDeclUnit: "",
					quotMode: "",
					minQuotUnit: "",
					buyDeclUnit: "",
					bidRuleAlgoId: "",
					bidAlgoExpln: "",
					voidRuleAlgoId: "",
					voidAlgoExpln: "",
					pdevprctype: "",
					pdevprcstand: "",
					upperCheckFlag: "",
					upperCheckPrc: "",
					lowerCheckFlag: "",
					mdevassoflag: "",
					mdevbiddflag: "",
					lowerCheckPrc: "",
					lowerCheckLcPrc: "",
					lowerCheckBidPrc: "",
					biddpriorityflag: "",
					assopriorityflag: "",
					priorityflag: "",
					lowerCheckBidPqProp:"",
					lowerCheckLcPqProp:""
			};
			ruleVue.mdevAssoFlag = "";//长协考核字段转存
			ruleVue.mdevBiddFlag = "";//竞价考核字段转存
		},
		show:function(id){
			if(id){
				$.ajax({
           			url:basePath + "cloud-purchase-web/smRuleConfigureController/"+id,
           			type:"get",
           			success:function(data){
           				if(data.flag){
           					console.log("success");
           					ruleVue.reset();
           					ruleVue.smRuleConfigure = data.data;
           					ruleVue.mdevAssoFlag = data.data.lowerCheckLcFlag;//长协考核字段转存
           					ruleVue.mdevBiddFlag = data.data.lowerCheckBidFlag;//竞价考核字段转存
//             					console.log("smRuleConfigure:::");
//             					console.log(ruleVue.smRuleConfigure.splitCheckFlag);
//             					console.log(ruleVue.smRuleConfigure.priorityFlag);
           				}
           			}
           		})
			}else{
				ruleVue.smRuleConfigure.id = null;
				ruleVue.smRuleConfigure.ruleName = mainVue.name;
				ruleVue.smRuleConfigure.provinceId = mainVue.provinceId;
				ruleVue.smRuleConfigure.ruleStatus = mainVue.ruleStatus;
			}
		},
		save:function(){
			var me = this;
			this.$refs.form1.valid();
        	if(this.valid===false){
        		return false;
        	}
        	if(me.mdevAssoFlag == ""){
        		me.smRuleConfigure.lowerCheckLcFlag = null;
        	}else{
	        	me.smRuleConfigure.lowerCheckLcFlag = me.mdevAssoFlag;//长协考核字段转存
        	}
        	if(me.mdevBiddFlag == ""){
        		me.smRuleConfigure.lowerCheckBidFlag = null;
        	}else{
        		me.smRuleConfigure.lowerCheckBidFlag = me.mdevBiddFlag;//竞价考核字段转存
        	}
			$.ajax({
				url:basePath+"cloud-purchase-web/smRuleConfigureController",
				type:"post",
				contentType : 'application/json;charset=UTF-8',
				data:$.toJSON(me.smRuleConfigure),
				success:function(data){
					if(data.data){
						ruleVue.smRuleConfigure = data.data;
						var text = ruleVue.smRuleConfigure.ruleName;
						var node = $('#tree').tree('getSelected');
					 	mainVue.updateNode(node,{text:text,id:data.data.id});
					 	MainFrameUtil.alert({ title:"提示", body:data.msg }); 
					}else{
						MainFrameUtil.alert({ title:"错误", body:data.msg }); 
					}
				}
			})
		},
		delConfirm:function(){
			MainFrameUtil.confirm({
				title:"确认",
		        body:"该操作不能恢复，确定要删除本记录吗？",
		        onClose:function(type){
		        	if(type=="ok"){
		        		ruleVue.del();
		        	}
		        }
			})
		},
		del:function(){
			var selected = $('#tree').tree('getSelected');
			if(ruleVue.smRuleConfigure.id){
				$.ajax({
					url:basePath+"cloud-purchase-web/smRuleConfigureController/"+ruleVue.smRuleConfigure.id,
					type:"delete",
					success:function(data){
						if(data.flag){
							MainFrameUtil.alert({ title:"提示", body:data.msg }); 
							mainVue.deleteNode(selected);
						}else{
							MainFrameUtil.alert({ title:"错误", body:data.msg }); 
						}
					}
				})
			}else{
				mainVue.deleteNode(selected);
			}
		},
		/* changeflag:function(data){
			
		}, */
		getSelectDataSource:function(codeType,dataSource,Object){
			$.ajax({
				url:basePath+"globalCache/queryCodeByKey/pcode/selling/"+codeType,
				type:'get',
				success:function(data){
					ruleVue[dataSource] = data;
					for(var o in data){
						ruleVue[Object][data[o].value]=data[o].content5;
					}
					
				}
			})
		}
	},
	watch:{
		'smRuleConfigure.bidRuleAlgoId':function(value){
			this.bidAlgoExpln = this.bidRuleAlgoIdObject[value];
		},
		'smRuleConfigure.voidRuleAlgoId':function(value){
			this.voidAlgoExpln =this.voidRuleAlgoIdObject[value] ;
		},
		'smRuleConfigure.upperPrcType':function(){
			var me = this;
			if(me.smRuleConfigure.upperPrcType == "01"){
				me.pdevPrcFlag = true;
				me.smRuleConfigure.upperPrcProp = null;
			}else{
				me.pdevPrcFlag = false;
				me.smRuleConfigure.upperPrc = null;
			}
		},
		'smRuleConfigure.upperCheckFlag':function(){
			var me = this;
			if(me.smRuleConfigure.upperCheckFlag == 1){
				me.upperFlag = true;
			}else{
				me.upperFlag = false;
				me.smRuleConfigure.upperPqProp = "";
				me.smRuleConfigure.upperCheckPrcType = "";
				me.smRuleConfigure.upperCheckPrcProp = "";
				me.smRuleConfigure.upperCheckPrc = "";
			}
		},
		'smRuleConfigure.upperCheckPrcType':function(){
			var me = this;
			if(me.smRuleConfigure.upperCheckPrcType == "01"){
				me.upperPrcFlag = true;
				me.smRuleConfigure.upperCheckPrcProp = null;
			}else{
				me.upperPrcFlag = false;
				me.smRuleConfigure.upperCheckPrc = null;
			}
		},
		'smRuleConfigure.lowerCheckFlag':function(){
			var me = this;
			if(me.smRuleConfigure.lowerCheckFlag == 1){
				me.lowerFlag = true;
				//me.splitFlagLowerFlag = false;
				me.changeFlag = true;
				$(".splitCheckFlagFlag").show();
			}else{
				me.lowerFlag = false;
				//me.changeFlag = false;
				me.splitFlag = false;
				$(".splitCheckFlagFlag").hide();
				//me.splitFlagLowerFlag = true;
				me.smRuleConfigure.splitCheckFlag = null;
				me.smRuleConfigure.lowerCheckPrcType = "";
				me.smRuleConfigure.lowerCheckPrcProp = "";
				me.smRuleConfigure.lowerPqProp = null;
				me.smRuleConfigure.lowerCheckPrc = "";
			}
		},
		'smRuleConfigure.splitCheckFlag':function(){
			var me = this;
			if(me.smRuleConfigure.splitCheckFlag == 1){
				me.smRuleConfigure.lowerCheckPrcType = "";
				me.smRuleConfigure.lowerCheckPrcProp = "";
				me.splitFlag = true;
				//me.splitFlagLowerFlag = true;
			}else{
				me.mdevAssoFlag = "";
 				me.smRuleConfigure.lowerCheckLcPrcType = "";
 				me.smRuleConfigure.lowerCheckLcPrcProp = "";
				me.mdevBiddFlag = "";
 				me.smRuleConfigure.lowerCheckBidPrcType = "";
 				me.smRuleConfigure.lowerCheckBidPrcProp = "";
 				me.smRuleConfigure.lowerCheckLcPqProp = "";
 				me.smRuleConfigure.lowerCheckBidPqProp = "";
 				me.splitFlag = false;
 				me.splitFlagLowerFlag = false;
			}
		},
		'smRuleConfigure.lowerCheckPrcType':function(){
			var me = this;
			if(me.smRuleConfigure.lowerCheckPrcType == "01"){
				me.lowerPrcFlag = true;
				me.smRuleConfigure.lowerCheckPrcProp = null;
			}else{
				me.lowerPrcFlag = false;
				me.smRuleConfigure.lowerCheckPrc = null;
			}
		},
		'mdevAssoFlag':function(){
			var me = this;
			if(me.mdevAssoFlag == "1"){
				me.assoFlag = true;
			}else{
				me.assoFlag = false;
				me.smRuleConfigure.lowerCheckLcPrcType = "";
				me.smRuleConfigure.lowerCheckLcPrcProp = "";
				me.smRuleConfigure.lowerCheckLcPqProp = "";
			}
		},
		'smRuleConfigure.lowerCheckLcPrcType':function(){
			var me = this;
			if(me.smRuleConfigure.lowerCheckLcPrcType == "01"){
				me.assoPrcFlag = true;
				me.smRuleConfigure.lowerCheckLcPrcProp = null;
			}else{
				me.assoPrcFlag = false;
				me.smRuleConfigure.lowerCheckLcPrc = null;
			}
		},
		'mdevBiddFlag':function(){
			var me = this;
			if(me.mdevBiddFlag == 1){
				me.biddFlag = true;
			}else{
				me.biddFlag = false;
				me.smRuleConfigure.lowerCheckBidPrcType = "";
				me.smRuleConfigure.lowerCheckBidPrcProp = "";
 				me.smRuleConfigure.lowerCheckBidPqProp = "";
			}
		},
		'smRuleConfigure.lowerCheckBidPrcType':function(){
			var me = this;
			if(me.smRuleConfigure.lowerCheckBidPrcType == "01"){
				me.biddPrcFlag = true;
				me.smRuleConfigure.lowerCheckBidPrcProp = null;
			}else{
				me.biddPrcFlag = false;
				me.smRuleConfigure.lowerCheckBidPrc = null;
			}
		}
	}
	
})
	
</script>