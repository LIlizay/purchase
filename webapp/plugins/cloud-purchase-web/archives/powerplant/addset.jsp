<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>档案管理-电厂的档案新增/编辑机组</title>
</head>
<body id="addSetBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="机组基本信息">
		<mk-form-row>
			<!-- 机组名称 -->
	        <mk-form-col :label="formFields.geneName.label" required_icon="true">
	        	<su-textbox :data.sync="params.geneName" name="geneName" ></su-textbox>
	        </mk-form-col>
			<!-- 接入电压等级 -->
	        <mk-form-col :label="formFields.voltCodeName.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&valueIn='AC00102','AC00200','AC00660','AC00351','AC01101','AC02201','AC03301'" 
	        	multiple="false" :select-value.sync="params.voltCode" :select-name.sync="voltCodeName" name="voltCode" label-name="name"  ></su-select>
	        </mk-form-col>
	        <!-- 机组类型 -->
<!-- 	        <mk-form-col :label="formFields.typeCode.label" required_icon="true"> -->
<%-- 	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType" multiple="false"  --%>
<!-- 				:select-value.sync="params.typeCode" name="typeCode" label-name="name" ></su-select> -->
<!-- 	        </mk-form-col> -->
			<!-- 调度关系 -->
	        <mk-form-col :label="formFields.schedulRelationName.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_schedulRelation" multiple="false" label-name="name" 
				:select-value.sync="params.schedulRelation" :select-name.sync="schedulRelationName" name="schedulRelation" ></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 额定容量 -->
	        <mk-form-col :label="formFields.electricCapacityForm.label">
	        	<su-textbox :data.sync="params.electricCapacity" name="electricCapacity" :addon="{'show':true,'rightcontent':'兆瓦'}"></su-textbox>
	        </mk-form-col>
	        <!-- 利用小时数 -->
	        <mk-form-col :label="formFields.useHours.label" >
	        	<su-textbox :data.sync="params.useHours" name="useHours" :addon="{'show':true,'rightcontent':'小时'}"></su-textbox>
	        </mk-form-col>
			<!-- 是否竞价机组  -->
	        <mk-form-col :label="formFields.bidGeneFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.bidGeneFlag" label-name="name" ></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 机组最大出力 -->
	        <mk-form-col :label="formFields.maxOutput.label" >
	        	<su-textbox :data.sync="params.maxOutput" name="maxOutput" :addon="{'show':true,'rightcontent':'兆瓦'}"></su-textbox>
	        </mk-form-col>
	        <!-- 机组最小出力  -->
	        <mk-form-col :label="formFields.minOutput.label" >
	        	<su-textbox :data.sync="params.minOutput" name="minOutput" :addon="{'show':true,'rightcontent':'兆瓦'}"></su-textbox>
	        </mk-form-col>
			<!-- 能耗因子系数  -->
	        <mk-form-col :label="formFields.wastageCoefficient.label">
	        	<su-textbox :data.sync="params.wastageCoefficient" name="wastageCoefficient" ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 设计标准煤耗  -->
	        <mk-form-col :label="formFields.coalWastage.label" >
	        	<su-textbox :data.sync="params.coalWastage" name="coalWastage" :addon="{'show':true,'rightcontent':'克/千瓦时'}"></su-textbox>
	        </mk-form-col>
	        <!-- 是否网内机组  -->
<!-- 	        <mk-form-col :label="formFields.innerFlag.label" required_icon="true"> -->
<!-- 	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.innerFlag" name="innerFlag"  -->
<!-- 	        	label-name="name" ></su-select> -->
<!-- 	        </mk-form-col> -->
	    </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="机组环保信息">
		<mk-form-row>
			<!-- 是否环保机组  -->
	        <mk-form-col :label="formFields.protGeneFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.protGeneFlag" label-name="name" ></su-select>
	        </mk-form-col>
	        <!-- 是否脱硫  -->
	        <mk-form-col :label="formFields.desuFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.desuFlag" label-name="name" ></su-select>
	        </mk-form-col>
	        <!-- 是否脱硝  -->
	        <mk-form-col :label="formFields.deniFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.deniFlag" label-name="name" ></su-select>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 是否除尘  -->
	        <mk-form-col :label="formFields.dustFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.dustFlag" label-name="name" ></su-select>
	        </mk-form-col>
	        <!-- 是否碳捕捉  -->
	        <mk-form-col :label="formFields.carbFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false"  :select-value.sync="params.carbFlag" label-name="name" ></su-select>
	        </mk-form-col>
	        <!-- 脱硫电价  -->
	        <mk-form-col :label="formFields.desuPrc.label" >
	        	<su-textbox :data.sync="params.desuPrc" name="desuPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 脱硝电价  -->
	        <mk-form-col :label="formFields.deniPrc.label" >
	        	<su-textbox :data.sync="params.deniPrc" name="deniPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	        </mk-form-col>
	        <!-- 除尘电价  -->
	        <mk-form-col :label="formFields.dustPrc.label" >
	        	<su-textbox :data.sync="params.dustPrc" name="dustPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	        </mk-form-col>
	        <!-- 超低排放电价  -->
	        <mk-form-col :label="formFields.discPrc.label" >
	        	<su-textbox :data.sync="params.discPrc" name="discPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
	<div v-show="showFlag">
	<mk-form-panel title="关口计量点信息" label_width="150px">
		<mk-form-row>
			<!-- 关口计量点名称 -->
	        <mk-form-col :label="formFields.markMpName.label" >
	        	<su-textbox :data.sync="params.markMpName"></su-textbox>
	        </mk-form-col>
	        <!-- 关口计量点综合倍率  -->
	        <mk-form-col :label="formFields.markMpRate.label" >
	        	<su-textbox :data.sync="params.markMpRate" ></su-textbox>
	        </mk-form-col>
	        <!-- 关口计量点编号  -->
	        <mk-form-col :label="formFields.markMpNo.label" >
	        	<su-textbox :data.sync="params.markMpNo" ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
	</div>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
$.validator.addMethod(     
	"isNum",        
	function(value, element)        
	{            
	        var tel = /^([^0\D]\d*)?(\d?\.\d+)?0?$/;  
	        return this.optional(element) || (tel.test(value));     
	}     
	, "请填写正确格式的数字！"
);  
var mainVue = new Vue({
	el:"#addSetBody",
	data:{
		formFields:{},
		params:{
			geneName:null,voltCode:null,typeCode:null,schedulRelation:null,electricCapacity:null,useHours:null,bidGeneFlag:null,maxOutput:null,minOutput:null,
			wastageCoefficient:null,coalWastage:null,innerFlag:null,protGeneFlag:null,desuFlag:null,deniFlag:null,dustFlag:null,carbFlag:null,desuPrc:null,
			deniPrc:null,dustPrc:null,discPrc:null,markMpName:null,markMpRate:null,markMpNo:null
		},
		voltCodeName:'',
		schedulRelationName:'',
		dataOption: {
			rules: {
				geneName : "required",
				typeCode : "required",
				electricCapacity:{isNum:!0},
				innerFlag : "required",
				useHours:{isNum:!0},
				maxOutput:{isNum:!0},
				minOutput:{isNum:!0},
				wastageCoefficient:{isNum:!0},
				coalWastage:{isNum:!0},
				desuPrc:{isNum:!0},
				deniPrc:{isNum:!0},
				dustPrc:{isNum:!0},
				discPrc:{isNum:!0}
			}
		},
		valid: false,
		showFlag:false,
		sellFlagList:[]
	},
	ready:function(){
		var me  = this;
		$.ajax({
			url:basePath+'globalCache/queryCodeByKey/pcode/selling/sell_flag',
			type:'get',
			success:function(data){
				me.sellFlagList = data;
			}
		})
		var obj = MainFrameUtil.getParams('addSet');
		var row =  obj.row;
		if(typeof(row)!=='undefined'){
			this.params = eval('('+$.toJSON(row)+')'); 
		}
		var methods={"save":me.save};
        MainFrameUtil.setParams(methods,'addSet');
        ComponentUtil.getFormFields("purchase.archives.powerplantset",this);
	},
	methods:{
		save:function(){
			this.$refs.form1.valid();
        	if(this.valid===false){
        		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
           		return false;
        	}
        	if(this.voltCodeName!=null&&this.voltCodeName!=''){
        		this.params.voltCodeName = this.voltCodeName;
        	}
			if(this.schedulRelationName!=null&&this.schedulRelationName!=''){
				this.params.schedulRelationName = this.schedulRelationName;
        	}
			if(this.params.innerFlag == "0"){
				this.params.markMpName =null;
				this.params.markMpRate =null;
				this.params.markMpNo =null;
			}
        	return this.params;
		}
	},
	watch:{
		'params.innerFlag':function(value){
			if(value=="1"){
				this.showFlag = true;
			}else{
				this.showFlag = false;
			}
		}
	}
})
</script>
</body>
</html>