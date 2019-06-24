<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>档案管理-电厂档案电厂信息机组详情</title>
</head>
<body id="setDetailBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="机组基本信息">
		<mk-form-row>
			<!-- 机组名称 -->
	        <mk-form-col :label="formFields.geneName.label" required_icon="true">
	        	<su-textbox :data.sync="params.geneName" name="geneName" disabled ></su-textbox>
	        </mk-form-col>
			<!-- 接入电压等级 -->
	        <mk-form-col :label="formFields.voltCodeName.label">
	        	<su-textbox :data.sync="params.voltCodeName" name="voltCode"  disabled ></su-textbox>
	        </mk-form-col>
			<!-- 调度关系 -->
	        <mk-form-col :label="formFields.schedulRelationName.label">
	        	<su-textbox  label-name="name" :data.sync="params.schedulRelationName" name="schedulRelation" disabled ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 额定容量 -->
	        <mk-form-col :label="formFields.electricCapacityForm.label">
	        	<su-textbox :data.sync="params.electricCapacity" name="electricCapacity" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled ></su-textbox>
	        </mk-form-col>
	        <!-- 利用小时数 -->
	        <mk-form-col :label="formFields.useHours.label" >
	        	<su-textbox :data.sync="params.useHours" name="useHours" :addon="{'show':true,'rightcontent':'小时'}" disabled ></su-textbox>
	        </mk-form-col>
			<!-- 是否竞价机组  -->
	        <mk-form-col :label="formFields.bidGeneFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.bidGeneFlag" label-name="name" disabled ></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 机组最大出力 -->
	        <mk-form-col :label="formFields.maxOutput.label" >
	        	<su-textbox :data.sync="params.maxOutput" name="maxOutput" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled ></su-textbox>
	        </mk-form-col>
	        <!-- 机组最小出力  -->
	        <mk-form-col :label="formFields.minOutput.label" >
	        	<su-textbox :data.sync="params.minOutput" name="minOutput" :addon="{'show':true,'rightcontent':'兆瓦'}" disabled ></su-textbox>
	        </mk-form-col>
			<!-- 能耗因子系数  -->
	        <mk-form-col :label="formFields.wastageCoefficient.label">
	        	<su-textbox :data.sync="params.wastageCoefficient" name="wastageCoefficient" disabled ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
	        <!-- 设计标准煤耗  -->
	        <mk-form-col :label="formFields.coalWastage.label" >
	        	<su-textbox :data.sync="params.coalWastage" name="coalWastage" :addon="{'show':true,'rightcontent':'克/千瓦时'}" disabled ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	<mk-form-panel title="机组环保信息">
		<mk-form-row>
			<!-- 是否环保机组  -->
	        <mk-form-col :label="formFields.protGeneFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.protGeneFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 是否脱硫  -->
	        <mk-form-col :label="formFields.desuFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.desuFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 是否脱硝  -->
	        <mk-form-col :label="formFields.deniFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.deniFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 是否除尘  -->
	        <mk-form-col :label="formFields.dustFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false" :select-value.sync="params.dustFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 是否碳捕捉  -->
	        <mk-form-col :label="formFields.carbFlag.label" >
	        	<su-select :data-source="sellFlagList" multiple="false"  :select-value.sync="params.carbFlag" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 脱硫电价  -->
	        <mk-form-col :label="formFields.desuPrc.label" >
	        	<su-textbox :data.sync="params.desuPrc" name="desuPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
		<mk-form-row>
			<!-- 脱硝电价  -->
	        <mk-form-col :label="formFields.deniPrc.label" >
	        	<su-textbox :data.sync="params.deniPrc" name="deniPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 除尘电价  -->
	        <mk-form-col :label="formFields.dustPrc.label" >
	        	<su-textbox :data.sync="params.dustPrc" name="dustPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 超低排放电价  -->
	        <mk-form-col :label="formFields.discPrc.label" >
	        	<su-textbox :data.sync="params.discPrc" name="discPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
	<div v-show="showFlag">
	<mk-form-panel title="关口计量点信息" label_width="150px">
		<mk-form-row>
			<!-- 关口计量点名称 -->
	        <mk-form-col :label="formFields.markMpName.label" >
	        	<su-textbox :data.sync="params.markMpName" disabled ></su-textbox>
	        </mk-form-col>
	        <!-- 关口计量点综合倍率  -->
	        <mk-form-col :label="formFields.markMpRate.label" >
	        	<su-textbox :data.sync="params.markMpRate"  disabled ></su-textbox>
	        </mk-form-col>
	        <!-- 关口计量点编号  -->
	        <mk-form-col :label="formFields.markMpNo.label" >
	        	<su-textbox :data.sync="params.markMpNo" disabled ></su-textbox>
	        </mk-form-col>
		</mk-form-row>
	</mk-form-panel>
	</div>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var setDetailVue = new Vue({
	el:"#setDetailBody",
	data:{
		formFields:{},
		params:{
			geneName:null,voltCode:null,typeCode:null,schedulRelation:null,electricCapacity:null,useHours:null,bidGeneFlag:null,maxOutput:null,minOutput:null,
			wastageCoefficient:null,coalWastage:null,innerFlag:null,protGeneFlag:null,desuFlag:null,deniFlag:null,dustFlag:null,carbFlag:null,desuPrc:null,
			deniPrc:null,dustPrc:null,discPrc:null,markMpName:null,markMpRate:null,markMpNo:null
		},
		voltCodeName:'',
		schedulRelationName:'',
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
		
		var id = MainFrameUtil.getParams('setdetail').id;
		if(typeof(id)!=='undefined'){
			$.ajax({
				url:basePath+'cloud-purchase-web/powerPlantController/getPhcElecInfoById/'+id,
				type:'get',
				success:function(data){
					me.params = data.data[0];
				}
			})
		}
        ComponentUtil.getFormFields("purchase.archives.powerplantset",this);
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