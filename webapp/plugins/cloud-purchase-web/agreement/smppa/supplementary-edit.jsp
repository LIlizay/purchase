	<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
   <title>合同管理-售电合同管理补充协议编辑</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
 <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>


</head>
<body id="smAgreSup_editVue">
<div>
<su-form v-ref:supplementary offpos-style="true" id="fms1" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
<mk-panel title="编辑补充协议" line="true">
    <mk-form-panel  num="2">
        <mk-form-row>
            <!-- 协议名称  -->
             <mk-form-col :label="formFields.agreName.label" :class="{'display-none':!formFields.agreName.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.agreName" name="agreName"  required></su-textbox>
             </mk-form-col>
             <!-- 签订日期   -->
             <mk-form-col :label="formFields.signDate.label" :class="{'display-none':!formFields.signDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.signDate"  name="signDate" required ></su-datepicker>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 售电方签订人  -->
             <mk-form-col :label="formFields.sellParty.label" :class="{'display-none':!formFields.sellParty.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.sellParty" name="sellParty" required></su-textbox>
             </mk-form-col>
              <!--  客户签订人   -->
             <mk-form-col :label="formFields.custParty.label" :class="{'display-none':!formFields.custParty.formHidden}" required_icon="true">
                 <su-textbox :data.sync="params.smAgreSup.custParty" name="custParty" required></su-textbox>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
            <!-- 开始时间   -->
             <mk-form-col :label="formFields.effectiveDate.label" :class="{'display-none':!formFields.effectiveDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.effectiveDate"  name="effectiveDate" required ></su-datepicker>
             </mk-form-col>
             <!-- 结束时间   -->
             <mk-form-col :label="formFields.expiryDate.label" :class="{'display-none':!formFields.expiryDate.formHidden}" required_icon="true">
                 <su-datepicker format="YYYY-MM-DD" :data.sync="params.smAgreSup.expiryDate"  name="expiryDate" required ></su-datepicker>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row>
        <!-- 协议状态  -->
             <mk-form-col :label="formFields.agreStatusName.label" :class="{'display-none':!formFields.agreStatusName.formHidden}">
                 <su-select label-name="name" :select-value.sync="params.smAgreSup.agreStatus" url="${Config.baseURL}/globalCache/queryCodeByKey/pcode/selling/sell_contractStatusCode" multiple="false" disabled=disabled></su-select>
             </mk-form-col>
            <!-- 协议附件 -->
             <mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}">
                 <mk-file-upload :filename="fileName" v-ref:uploadfile @upload-error="uploadError" @upload-success="uploadSuccess"></mk-file-upload>
             </mk-form-col>
        </mk-form-row>
        <mk-form-row height="240px">
            <!-- 协议文本内容 -->
             <mk-form-col :label="formFields.agreContent.label" :class="{'display-none':!formFields.agreContent.formHidden}" colspan="2" required_icon="true">
                  <su-textbox :data.sync="params.smAgreSup.agreContent" type="textarea" rows="10" rols="10" name="agreContent"></su-textbox>
             </mk-form-col>
        </mk-form-row>
    </mk-form-panel>
</mk-panel>
</su-form>
</div>
<script>
var basePath="${Config.baseURL}";
var smAgreSup_editVue = new Vue({
    el: '#smAgreSup_editVue',
    data: {
  	  	formFields:{},
  	    fileName:"",
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
	    },
        dataOption:{
        	rules: {
                agreName: "required",
                signDate: "required",
                sellParty:"required",
                custParty:"required",
                effectiveDate:"required",
                expiryDate:"required",
                agreContent:"required"
                  
            }
        },
        valid: false,
    },
    ready:function(){
  	    //初始化表单
    	ComponentUtil.getFormFields("selling.agreement.smagresup",this);
    	//获取id
        this.params.smAgreSup.id = MainFrameUtil.getParams("supplementary-edit").id;
        //获取表单信息
        this.getData();
        //设置按钮组
        MainFrameUtil.setDialogButtons(this.getButtons(),"supplementary-edit");
  	  
    },
    methods: {
    	//初始化按钮
    	getButtons:function(){
            var buttons = [{text:"保存",type:"primary",handler:this.save},
                           {text:"取消 ",handler:function(){MainFrameUtil.closeDialog("supplementary-edit");}}];
            return buttons;
        },
    	
    	//获取表单信息
        getData:function(){
            $.ajax({
                url:basePath+"cloud-purchase-web/smAgreSupController/"+this.params.smAgreSup.id,
                type:"get",
                success:function(data){
                    if(data.flag == true){
                        smAgreSup_editVue.params.smAgreSup = data.data;
                        if(data.data.fileId !== null && data.data.fileId !== "" && data.data.fileId.indexOf(";") !== -1){
                        	smAgreSup_editVue.fileName = data.data.fileId.split(";")[1]
                        }else{
                        	smAgreSup_editVue.fileName = ""
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
        
        //点击保存按钮
        save: function(){
            this.$refs.supplementary.valid();
            if(this.valid===false){
        		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
        		return;
        	}
            if(this.valid===true){
            	if(this.params.smAgreSup.effectiveDate > this.params.smAgreSup.expiryDate){
                    MainFrameUtil.alert({title:"提示",body:"开始时间不能大于结束时间"});
                    return;
                }
            	//协议状态
            	var date = new Date();
                var nowYMD = date.getFullYear() + '-' + ((date.getMonth() + 1)<10?'0'+(date.getMonth() + 1):(date.getMonth() + 1)) + '-' + date.getDate();
                if(this.params.smAgreSup.effectiveDate <= nowYMD && nowYMD <= this.params.smAgreSup.expiryDate){//合同开始日期 >= 今天 && 今天 <= 结束 : 进行中 
                	 this.params.smAgreSup.agreStatus = '02';
                }else if(this.params.smAgreSup.expiryDate < nowYMD){//结束 < 今天  状态: 已完成
                	 this.params.smAgreSup.agreStatus = '03';
                }else{
                	this.params.smAgreSup.agreStatus = '01';// 未开始
                }
                
                this.$refs.uploadfile.validAndUpload()
            }
        },
        
        
        //保存补充协议
        submit: function(){
            var type = "post";
            if(this.params.smAgreSup.id !=="" && this.params.smAgreSup.id !== null){
                type = "put"
            }
            $.ajax({
                url:basePath+"cloud-purchase-web/smAgreSupController",
                type:type,
                data:$.toJSON(smAgreSup_editVue.params.smAgreSup),
                contentType: 'application/json;charset=UTF-8',                                 
                success:function(data){
                    if(data.flag == true){
                        if(type ==="post"){
                            smAgreSup_editVue.params.smAgreSup = data.data;
                        }
                        MainFrameUtil.alert({title:"成功提示",body:data.msg});
                        MainFrameUtil.closeDialog("supplementary-edit")
                    }else{
                        MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
                    }
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
                }
           })
        },
        
        //上传图片
        uploadSuccess:function(datas){
            if(datas!=null){
                //批文字段保存上传文件的id,name
                var fileInfo = datas[0].id+";"+datas[0].fileName+datas[0].extension;
                this.params.smAgreSup.fileId=fileInfo;
            }else if(this.fileName==""){
                //若文件为空，则保存空字符串
                this.params.smAgreSup.fileId="";
            }
            this.submit();
        },
    
        uploadError:function(){
            MainFrameUtil.alert({title:"错误",body:"上传文件失败"}); 
        }
    }
}); 
</script>
</body>

</html>
	