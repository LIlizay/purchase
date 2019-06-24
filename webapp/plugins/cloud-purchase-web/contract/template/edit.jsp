<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/upload.jsp"%>
<title>合同管理-售电合同模板编辑</title>
</head>
<body id="editBody">
<su-form 
	v-ref:form1 
	id="fms1" 
	offpos-style="true"
	:data-option="dataOption" 
	:set-defaults="setDefaults" 
	:data-validator.sync="valid" >
	<mk-form-panel title="售电合同模板编辑" num="2">
		<mk-form-row>
			<!-- 合同模板号 -->
	        <mk-form-col :label="formFields.templateVer.label" >
	        	<su-textbox :data.sync="params.templateVer" disabled></su-textbox>
	        </mk-form-col>
			<!-- 合同模板名称 -->
	        <mk-form-col :label="formFields.templateName.label" required_icon="true">
	        	<su-textbox :data.sync="params.templateName" name="templateName" ></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
		<mk-form-row>
			<!-- 合同类型 -->
	        <mk-form-col :label="formFields.templateTypeName.label" required_icon="true">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_sellElecContractType" multiple="false" 
				:select-value.sync="params.templateType" name="templateType" label-name="name" ></su-select>
	        </mk-form-col>
	          <!-- 创建日期 -->
	        <mk-form-col :label="formFields.createDate.label" >
	        	<su-textbox :data.sync="params.createDate" disabled></su-textbox>
	        </mk-form-col>
	         <!-- 合同模板附件 -->
	    </mk-form-row>
	    <mk-form-row>
	    	<mk-form-col :label="formFields.fileId.label" required_icon="true" colspan="2">
	        	<mk-file-upload v-ref:uploadfile required="true" @upload-error="uploadError" @upload-success="uploadSuccess" 
				url="${Config.getConfig('file.service.url')}" :filename="fileName"></mk-file-upload>
	        </mk-form-col>
	    </mk-form-row>
        <mk-form-row height="90px">
			<!-- 模板备注 -->
			<mk-form-col :label.sync="formFields.remarks.label" colspan="2">
   				<su-textbox :data.sync="params.remarks" type="textarea" rows="3"></su-textbox>
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
		params:{templateVer:'',templateName:'',templateType:'',remarks:''},
		fileName:'',
		dataOption: {
			rules: {
				templateName: "required",
				templateType:"required"
			}
		},
		valid: false
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('edit').id;
		var methods={"submit":this.submit};
        MainFrameUtil.setParams(methods,'edit');
        ComponentUtil.getFormFields("selling.contract.template",this);
        $.ajax({
        	url : basePath + "cloud-purchase-web/smAgreTemplateController/"+id,
        	type:'get',
        	success:function(data){
        		if(data.flag){
        			me.params = data.data;
        			var obj = eval('(' + data.data.fileId + ')');
        			var fileName = obj.fileName;
        			me.fileName = fileName;
        		}else{
        			MainFrameUtil.alert({ title:"错误", body:data.msg }); 
        		}
        	}
        })
	},
	methods:{
		submit:function(){
			this.$refs.form1.valid();
			if(this.valid===false){
				return false
			}
			this.$refs.uploadfile.validAndUpload();
		},
		uploadSuccess:function(datas){
			//取到相应的值并发送到后台
			if(datas!=null){
				var fileInfo ="{fileId:\'" + datas[0].id+"\',fileName:\'"+datas[0].fileName+datas[0].extension+"\'}";
				this.params.fileId = fileInfo;
			}
			this.save();
  		},
  		uploadError:function(event,msg){
  			 MainFrameUtil.alert({title:"错误", body:msg});  
 			 this.upload ="";
 		},
 		save:function(){
   			var me = this;
   	     	$.ajax({
				url : basePath + "cloud-purchase-web/smAgreTemplateController",
   	   			type : 'put',
   	   			data : $.toJSON(me.params),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({
   	   						title:"提示",
	   	        	        body:data.msg,
	   	        	        onClose:function(){
                    			MainFrameUtil.closeDialog('edit');
	   	        	        }
	   	        	    });
   	   				}else{
   	   					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
   	   				}
   	   			}
   	   		});
		},
		//获取格式化日期
        getFormatterDate:function(date){
        	var expiryDate = date.getFullYear()+"-";
			var month = date.getMonth()+1;
			var day = date.getDate();
			if(month<10){
				expiryDate+="0";
			}
			expiryDate+=month+"-";
			if(day<10){
				expiryDate+="0";
			}
			expiryDate+=day;
			return expiryDate;
        }
	}
})
</script>
</body>
</html>