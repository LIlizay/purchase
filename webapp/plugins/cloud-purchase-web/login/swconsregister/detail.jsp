<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>售电公司审核详情</title>
</head>
<body id="detailBody">
<mk-panel title="售电公司审核详情" >
	<mk-form-panel title="注册信息">
		<mk-form-row>
			<!-- 企业名称 -->
	        <mk-form-col :label="formFields.enterpriseName.label" colspan="3">
	        	<su-textbox :data.sync="params.consName" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
			<!-- 企业营业执照注册号 -->
	        <mk-form-col :label="formFields.registerNo.label" colspan="3">
	        	<su-textbox :data.sync="params.registerNo" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row v-show="flag1">
			<!-- 所属售电公司名称 -->
	        <mk-form-col :label="formFields.companyId.label" colspan="3">
	        	<su-textbox :data.sync="companyName" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
			<!-- 附件 -->
	        <mk-form-col :label="formFields.fileId.label" colspan="3">
	        	<span id="fileInfo" style="line-height: 32px"></span>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row v-show="flag2">
			<!-- 电压等级 -->
	        <mk-form-col :label="formFields.voltCode.label" colspan="3">
	        	 <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" multiple="false" 
					:select-value.sync="params.voltCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row v-show="flag2">
			<!-- 用电类别 -->
	        <mk-form-col :label="formFields.elecTypeCode.label" colspan="3">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" multiple="false" 
					:select-value.sync="params.elecTypeCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
			<!-- 企业地址 -->
	        <mk-form-col :label="formFields.enterpriseAddr.label" colspan="3">
	        	<su-textbox :data.sync="params.addr" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
			<!-- 企业联系人 -->
	        <mk-form-col :label="formFields.enterpriseContact.label" colspan="3">
	        	<su-textbox :data.sync="params.contName" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	    <mk-form-row>
			<!-- 联系人电话 -->
	        <mk-form-col :label="formFields.contactPhone.label" colspan="3">
	        	<su-textbox :data.sync="params.phone" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	<mk-form-panel>
		<div id="checkInfo"></div>
	</mk-form-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el:"#detailBody",
	data:{
		formFields:{},
		flag1:false,
		flag2:false,
		params:{consName:'',registerNo:'',addr:'',contName:'',phone:''},
		fileName:'',
		companyName:''
	},
	ready:function(){
		var me = this;
		var id = MainFrameUtil.getParams('detail').id;
        ComponentUtil.getFormFields("selling.login.swconsregister",this);
        $.ajax({
        	url : basePath + "cloud-purchase-web/swConsRegisterController/"+id,
        	type:'get',
        	success:function(data){
        		if(data.flag){
        			me.params = data.data;
        			if(data.data.consType == "02"){
        				me.flag1 = true;
        				me.flag2 = true;
        				if(data.data.companyId!=null && data.data.companyId!=''){
        					$.ajax({
	       		                url : basePath+"cloud-purchase-web/scCompanyInfoController/" + data.data.companyId,
	       		                type : 'get',
	       		                success : function(comdata) {
	       		                	me.companyName = comdata.data.companyName;
	       		                }
	       		         	});
        				}
        			}else if(data.data.consType == "03"){
        				me.flag2 = true;
        			}
    				if(data.data.fileId!=null&&data.data.fileId!=''){
    					var obj = eval('(' + data.data.fileId + ')');
    					var fileHtml = "<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+obj.fileName+"</a>";
	        			$("#fileInfo").html(fileHtml);
    				}
        	        $.ajax({
        	        	url : basePath + "cloud-purchase-web/swConsRegisterController/getEnterpriseInfo",
        	        	type:'post',
        	        	data:data.data.registerNo,
        	        	success:function(result){
        	        		if(result){
        	        			var html = result.html;
        	        			var snapshortUrl = result.snapshortUrl;
        	        			//法人代表
        	        			var name = $($(html).find("td a")[0]).html();
        	        			//企业地址
        	        			var addr = $($(html).find("td a")[2]).parent().html().split("<a")[0].trim();
        	        			$("#checkInfo").html($(html).html());
        	        			$($("#checkInfo").find("td a")[0]).parent().html(name);
        	        			$($("#checkInfo").find("td a")[0]).parent().html(addr);
//         	        			$("#checkInfo").find("a").removeAttr("href");
        	        			if(snapshortUrl){
            	        			$($("#checkInfo").find("a")[0]).attr("href",snapshortUrl);
            	        			var numArray = snapshortUrl.split(".");
            	        			var num = numArray.length;
            	        			$($("#checkInfo").find("a")[0]).attr("download",data.data.consName+"."+numArray[num-1]);
        	        			}else{
            	        			$($("#checkInfo").find("a")[0]).click(function(){
            	        				MainFrameUtil.alert({title:"提示",body:"未生成工商官网信息快照！"});
            	        			});
        	        			}
        	        		}else{
        	        			MainFrameUtil.alert({ title:"提示", body:"查无数据！" }); 
        	        		}
        	        	}
        	        });
        		}else{
        			MainFrameUtil.alert({ title:"错误", body:data.msg }); 
        		}
        	}
        });
	}
})
</script>
</body>
</html>