<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员工具-行业资讯管理新增</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.all.js"> </script >
	<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
<div id="html_addVue" v-cloak>
	<su-form v-ref:qwqe id="fms1122" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="信息维护" height="260px" line="true">
		     <mk-form-panel>
		        <mk-form-row >
		        	<!-- 信息标题-->
		            <mk-form-col :label="formFields.title.label" colspan="3" :class="{'display-none':!formFields.title.formHidden}}" 
                           label-width="100px" label-align="right" required_icon="true"> 
                            <su-textbox :data.sync="params.swHtmlInfo.title" ></su-textbox>
		            </mk-form-col> 
		        </mk-form-row>
		         <mk-form-row>
		         	<!-- 所属省份  -->
	            <mk-form-col :label="formFields.provinceCodeName.label" :class="{'display-none':!formFields.provinceCode.formHidden}}" 
                          label-width="100px" label-align="right" required_icon="true">
             	  <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swHtmlInfo.provinceCode" :high="150" 
	                	:data-source.sync="provinceList" name="provinceCode"></su-select>
	            </mk-form-col>
	            
	            <!-- 生效开始时间 -->
	            <mk-form-col :label='formFields.beginTime.label' :class="{'display-none':!formFields.beginTime.formHidden}}" required_icon="true">
	            	<su-datepicker format="YYYY-MM-DD" :data.sync="params.swHtmlInfo.beginTime" ></su-datepicker>
	            </mk-form-col>
	            
	            <!-- 生效结束时间 -->
		        <mk-form-col :label='formFields.endTime.label' :class="{'display-none':!formFields.endTime.formHidden}}" required_icon="true">
	            	<su-datepicker format="YYYY-MM-DD" :data.sync="params.swHtmlInfo.endTime" ></su-datepicker>
	            </mk-form-col>    
	            
	       		</mk-form-row>
	       		<mk-form-row>
	       			<!-- 上传文件 -->
				<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}}" 
                           label-width="100px" label-align="right" colspan="3"> 
						<mk-file-upload :filename="fileName" required="false" v-ref:uploadfile @upload-error="uploadError" @upload-success="uploadSuccess"
	                	 url="${Config.getConfig('file.service.url')}"></mk-file-upload>
	            </mk-form-col>	
	       		</mk-form-row>
	       		
	       		<mk-form-row>
	       		<!-- 标题图片 -->
				<mk-form-col :label="formFields.bgImage.label" :class="{'display-none':!formFields.bgImage.formHidden}}" 
                           label-width="100px" label-align="right" colspan="3" required_icon="true"> 
						<mk-file-upload :filename="fileName" required="true" v-ref:uploadimg @upload-error="uploadImgError" @upload-success="uploadImgSuccess"
	                	 url="${Config.getConfig('file.service.url')}"></mk-file-upload>
	            </mk-form-col>	
	       		</mk-form-row>
		    </mk-form-panel>  
		</mk-panel>
		<div style="margin-top:10px; margin-right:3px">
			<div>
				<!-- 加载编辑器的容器 -->
		    	<script id="editor" name="content" type="text/plain"></script>
			</div>
		</div>
	</su-form>
</div>
</body>
</html>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var ue = UE.getEditor('editor',{initialFrameHeight:230});
var htmlVue = new Vue({
    el: '#html_addVue',
    data: {
    	formFields:{},
		fileName:"",//上传文件
		provinceCodeFlag:true,//所属类别
        params:{
        	swHtmlInfo:{
        		id:null,//id
        		title:"",//标题
        		releasePerson:"",//发布人
        		releaseTime:null,//发布时间
        		releaseState:"",//发布状态
        		beginTime: null,
        		endTime : null,
        		content:"",//信息内容(url)
        		orgNo:"",//供电单位编号
        		hotStatus:"01",//热点状态
        		htmUrl:"",//详细信息url
        		bgImage:"",//标题图片
        		fileId:"",//文件
        		provinceCode:"",//
        	},
        	content:"",//编辑器的内容
        },
        dataOption: {//非空验证规则
            rules: {
            	releaseTime:{required: !0},
            	provinceCode:{required: !0}
            }
        },
        valid:false,
        provinceList:[]
    },
    ready:function(){
    	var that = this;
		$.ajax({
			url:basePath+'globalCache/queryProvinceList',
			type:'get',
			success:function(data){
				that.provinceList = data;
				that.provinceList.unshift({"name":"全国","value":"000000"});
			}
		})
    	//设置按钮组
    	MainFrameUtil.setDialogButtons(that.getButtons(),"add");
    	//加载表单
        ComponentUtil.getFormFields("purchase.home.swhtmlinfo",that);
    },
    methods: {
    	//附件上传成功回调
 	    uploadSuccess:function(data){
		    if(data){
		    	this.params.swHtmlInfo.fileId= $.toJSON({"fileId":data[0].id,"fileName":data[0].fileName+data[0].extension});
	  	    }
		    this.submit();
  	    },
  	  	uploadImgSuccess:function(data){
	  		if(data){
	  			this.params.swHtmlInfo.bgImage = $.toJSON({"imageId":data[0].id,"imageName":data[0].fileName+data[0].extension});
	  		}  
	  		//有否选择了文件
	  		if(!this.$refs.uploadfile.valid()){//未选择文件
	  			this.submit();
	  		}else{//已选择文件
	  			this.$refs.uploadfile.validAndUpload();
	  		}
  	  	},
  		//附件上传失败回调
  	    uploadError:function(data){
  	    	$.messager.progress('close');   				 // 隐藏进度条
  		    MainFrameUtil.alert({title:"警告",body:'附件上传失败'});//上传失败回调  
  	    },
  		//图片上传失败回调
  	    uploadImgError:function(data){
  	    	$.messager.progress('close');   				 // 隐藏进度条
  		    MainFrameUtil.alert({title:"警告",body:'图片上传失败'});//上传失败回调  
  	    },
    	//保存
    	save:function(){
    		var me = this;
    		//非空验证
            me.$refs.qwqe.valid();
            if(!me.valid){
                return false;
            };
          //有否选择了标题图片
    		if(!me.$refs.uploadimg.valid()){//未选择标题图片
    			MainFrameUtil.alert({title:"失败提示",body:"请上传标题图片"});
    			return false;
    		}else{//已选择图片
    			me.$refs.uploadimg.validAndUpload();
    		}
    	},
    	
    	//保存信息
    	submit:function(){
    		var me = this;
    		var type = "post";
            if(me.params.swHtmlInfo.id){//如果存在记录则更新
            	type = "put";
            }
            //获取编辑器的内容
            me.params.swHtmlInfo.content = ue.getContent();
    		 $.ajax({
                 url:basePath+"cloud-purchase-web/swHtmlInfoController",
                 type:type,
                 data:$.toJSON(me.params),
                 contentType:'application/json;charset=UTF-8',  
                 success:function(data){
                	 if(data.flag){
                		 if(type == "post"){
                			 me.params.swHtmlInfo = data.data;
                		 }
                		 MainFrameUtil.alert({title:"成功提示",body:data.msg});
                		 MainFrameUtil.closeDialog("add");
                	 }else{
                		 MainFrameUtil.alert({title:"失败提示",body:data.mag});
                	 }
                 },
                 error:function(){
                	 MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
                 }
            }) 
    	},
    	
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"保存",type:"primary",handler:this.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("add")}}];
            return buttons;
        }
    },
});
</script>
