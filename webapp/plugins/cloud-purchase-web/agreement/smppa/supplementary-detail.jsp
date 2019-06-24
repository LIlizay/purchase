	<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
   <title>合同管理-售电合同管理补充协议详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="smAgreSup_detailVue">
<div>
<mk-panel title="查看补充协议" line="true">
    <mk-form-panel  num="2">
        <mk-form-row>
            <!-- 协议名称  -->
             <mk-form-col :label="formFields.agreName.label" :class="{'display-none':!formFields.agreName.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.agreName" name="agreName" disabled=disabled required></su-textbox>
             </mk-form-col>
              <!-- 签订日期   -->
             <mk-form-col :label="formFields.signDate.label" :class="{'display-none':!formFields.signDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.signDate" disabled=disabled name="signDate" required ></su-datepicker>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 售电方签订人  -->
             <mk-form-col :label="formFields.sellParty.label" :class="{'display-none':!formFields.sellParty.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.sellParty" disabled=disabled name="sellParty" required></su-textbox>
             </mk-form-col>
              <!--  客户签订人   -->
             <mk-form-col :label="formFields.custParty.label" :class="{'display-none':!formFields.custParty.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.custParty" disabled=disabled name="custParty" required></su-textbox>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 开始时间   -->
             <mk-form-col :label="formFields.effectiveDate.label" :class="{'display-none':!formFields.effectiveDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.effectiveDate" disabled=disabled name="effectiveDate" required ></su-datepicker>
             </mk-form-col>
             <!-- 结束时间   -->
             <mk-form-col :label="formFields.expiryDate.label" :class="{'display-none':!formFields.expiryDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.expiryDate" disabled=disabled name="expiryDate" required ></su-datepicker>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
         	<!-- 协议状态  -->
             <mk-form-col :label="formFields.agreStatusName.label" :class="{'display-none':!formFields.agreStatusName.formHidden}">
                 <su-select label-name="name" :select-value.sync="params.smAgreSup.agreStatus" url="${Config.baseURL}/globalCache/queryCodeByKey/pcode/selling/sell_contractStatusCode" multiple="false" multiple="false" disabled=disabled></su-select>
             </mk-form-col>
            <!-- 协议附件 -->
             <mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}">
                  <div class="form-group" style="vertical-align: middle;line-height: 35px;background-color: white"> 
                    <strong id="fileName"></strong>
                 </div>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row height="240px">
            <!-- 协议文本内容 -->
             <mk-form-col :label="formFields.agreContent.label" :class="{'display-none':!formFields.agreContent.formHidden}" colspan="2" required_icon="true">
                  <su-textbox :data.sync="params.smAgreSup.agreContent" disabled=disabled type="textarea" rows="10" rols="10" name="agreContent"></su-textbox>
             </mk-form-col>
        </mk-form-row>
    </mk-form-panel>
</mk-panel>
	
</div>
<script>
var basePath="${Config.baseURL}";
var smAgreSup_detailVue = new Vue({
    el: '#smAgreSup_detailVue',
    data: {
  	  	formFields:{},
  	    params:{
  	    	smAgreSup:{
                id:"", //id
                agreNo:"", //协议编号
                agreName:"", //协议名称
                signDate:"", //签订日期
                sellParty:"", //售电方签订人
                custParty:"", //客户签订人
                agreStatus:"", //协议状态
                effectiveDate:"", //开始时间
                expiryDate:"", //结束时间
                fileId:"", //协议附件
                agreContent:"", //协议文本内容
                ppaId:"" //购电实体id
            }
  	    }
    },
    ready:function(){
  	  //初始化表单
  	  ComponentUtil.getFormFields("selling.agreement.smagresup",this);
  	  //获取id
  	  this.params.smAgreSup.id = MainFrameUtil.getParams("supplementary-detail").id;
  	  //获取表单信息
  	  this.getData();
  	  //设置按钮组
  	 MainFrameUtil.setDialogButtons(this.getButtons(),"supplementary-detail");
    },
    methods: {
    	//获取表单信息
    	getData:function(){
    		$.ajax({
                url:basePath+"cloud-purchase-web/smAgreSupController/"+this.params.smAgreSup.id,
                type:"get",
                success:function(data){
                    if(data.flag == true){
                    	smAgreSup_detailVue.params.smAgreSup = data.data;
                    	if(data.data.fileId !== null && data.data.fileId !== "" && data.data.fileId.indexOf(";") !== -1){
                    		var doc = data.data.fileId.split(";");
                    		$("#fileName").empty().append("<a title='"+ doc[1] + "' href='${Config.getConfig('file.service.url')}/"+doc[0]+"'>"+ doc[1]+ "</a>");
                    	}
                    }else{
                        MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
                    }
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
                }
           })
    	},
    	//按钮组
    	getButtons:function(){
            var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("supplementary-detail");}}];
            return buttons;
        },
    }
}); 
</script>
</body>

</html>
	