<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>管理员工具-长协交易数据维护编辑</title>
</head>
<body id="editBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="省内长协交易数据新增">
		<mk-form-row>
			<!-- 年份 -->
	        <mk-form-col :label="formFields.year.label" required_icon="true">
	        	<su-datepicker format="YYYY" :data.sync="phfLcDealInfo.year" name="year" disabled></su-datepicker>
	        </mk-form-col>
			<!-- 总成交电量 -->
	        <mk-form-col :label="formFields.dealPqForm.label" required_icon="true">
	        	<su-textbox :data.sync="phfLcDealInfo.dealPq" name="dealPq" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
	        </mk-form-col>
	        <!-- 成交均价 -->
	        <mk-form-col :label="formFields.dealPrcForm.label" required_icon="true">
	        	<su-textbox :data.sync="phfLcDealInfo.dealPrc" name="dealPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
</su-form>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editVue = new Vue({
	el:"#editBody",
	data:{
		formFields:{},
		phfLcDealInfo:{
			year:'',dealPq:'',dealPrc:''
		},
		dataOption: {
            rules: {  
            	year: "required",
            	dealPq: {required:!0,number:!0},
            	dealPrc:{required:!0,number:!0}
            }
        },
        valid: false
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('edit').id;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'edit');
        ComponentUtil.getFormFields("purchase.forecast.phflcdealinfo",this);
        $.ajax({
        	url:basePath+"cloud-purchase-web/phfLcDealInfoController/"+id,
        	type:"get",
        	success:function(data){
        		if(data.flag){
        			me.phfLcDealInfo = data.data;
        		}else{
        			MainFrameUtil.alert({title:"失败",body:data.msg});
        		}
        	}
        })
	},
	methods:{
		save:function(backFun){
			var me = this;
			this.$refs.form1.valid();
        	if(this.valid===false){
           		return false;
        	}
			$.ajax({
				url:basePath+"cloud-purchase-web/phfLcDealInfoController",
				type:'put',
				data:$.toJSON(me.phfLcDealInfo),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
	 				if(data.flag){
	 					MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
	 						backFun();
	 					}});
	 				}else{
	 					MainFrameUtil.alert({title:"失败",body:data.msg});
	 				}
	 			}
			})
		}
	}
})
</script>
</body>
</html>