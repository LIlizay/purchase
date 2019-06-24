<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员工具-知识库管理编辑</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.all.js"> </script >
	<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
<div id="html_editVue" v-cloak>
	<su-form v-ref:qwqe id="fms1122" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="知识维护" height="220px" line="true">
		     <mk-form-panel num="2">
		        <mk-form-row >
		        	<!-- 知识标题-->
		            <mk-form-col :label="formFields.title.label" colspan="2" :class="{'display-none':!formFields.title.formHidden}}" 
                           label-width="100px" label-align="right" required_icon="true"> 
                            <su-textbox :data.sync="params.swKnowledge.title" ></su-textbox>
		            </mk-form-col> 
		        </mk-form-row>
		         <mk-form-row>
		         	<!-- 所属省份  -->
	            <mk-form-col :label="formFields.provinceCodeName.label" :class="{'display-none':!formFields.provinceCode.formHidden}}" 
                          label-width="100px" label-align="right" required_icon="true">
				   <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swKnowledge.provinceCode" :high="150"
							url="${Config.baseURL}globalCache/queryProvinceList" name="provinceCode">
					</su-select>
	            </mk-form-col>
	            
	            <!-- 知识分类 -->
	            <mk-form-col :label='formFields.knowledgeClassName.label' :class="{'display-none':!formFields.beginTime.formHidden}}" required_icon="true">
	            	<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swKnowledge.knowledgeClass" :high="150"
		             	url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_knowledgeClass" name="knowledgeClass">
		            </su-select>
	            </mk-form-col>
	            
	       		</mk-form-row>
	       		<mk-form-row>
	       			<!-- 上传文件 -->
				<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}}" 
                           label-width="100px" label-align="right" colspan="2"> 
					<mk-file-upload :filename="fileName" required="true" v-ref:uploadfile @upload-error="uploadError" @upload-success="uploadSuccess"
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
    el: '#html_editVue',
    data: {
    	formFields:{},
		cacheurl:"",//回显路径
		fileName:"",//上传文件
		fileFlag:false,//上传文件flag
		provinceCodeFlag:true,//所属类别
        params:{
        	swKnowledge:{
        		id:null,//id
        		title:"",//标题
        		knowledgeClass:"",
        		content:"",//信息内容(url)
        		htmUrl:"",//详细信息url
        		bgImage:"",//背景图url
        		fileId:"",//文件
        		provinceCode:"",//
        	},
        	content:"",//编辑器内容
        },
        dataOption: {//非空验证规则
            rules: {
            	releaseTime:{required: !0},
            	provinceCode:{required: !0},
            }
        },
        valid:false
    },
    ready:function(){
    	//设置按钮组
    	MainFrameUtil.setDialogButtons(this.getButtons(),"edit");
    	//加载表单
        ComponentUtil.getFormFields("purchase.home.knowledge",this);
    	//获取id
    	this.params.swKnowledge.id = MainFrameUtil.getParams("edit").id;
    	//初始化表单数据
    	this.initData(this.params.swKnowledge.id);
    },
    methods: {
    	
    	//初始化表单数据
    	initData:function(id){
    		var me = this;
    		$.ajax({
                url:basePath+"cloud-purchase-web/swKnowledgeController/" + id,
                type:"GET",
                success:function(data){
             	    if(data.flag){
             	    	if(data.data){
             	    		me.params.swKnowledge = data.data;
             	    		if(data.data.fileId != null && data.data.fileId != ""){
             	    			var obj = eval("("+data.data.fileId+")");
             	    			me.fileName = obj.fileName;
             	    		}
             	    		setTimeout(function(){ue.setContent(data.data.content,false);},500);
             	    	}
             	    }else{
             	    	MainFrameUtil.alert({title:"提示",body:data.msg});
             	    }
                },
                error:function(){
               	    MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
                }
           }) 
    	},
    	
    	//附件上传成功回调
 	    uploadSuccess:function(data){
		    if(data){
		    	 this.params.swKnowledge.fileId= $.toJSON({"fileId":data[0].id,"fileName":data[0].fileName+data[0].extension});
		    }
		    this.submit();
  	    },
  		//附件上传失败回调
  	    uploadError:function(data){
  		    MainFrameUtil.alert({title:"警告",body:'上传失败'});//上传失败回调  
  	    },
    	
    	//保存
    	save:function(){
    		var me = this;
    		//非空验证
            me.$refs.qwqe.valid();
            if(me.valid===false){
                return false;
            };
          	//有否选择了文件
    		if(!me.$refs.uploadfile.valid()){//未选择文件
    			me.submit();
    		}else{//已选择文件
    			me.$refs.uploadfile.validAndUpload();
    		}
    	},
    	
    	//保存信息
    	submit:function(){
    		var me = this;
    		//获取编辑器的内容
           	//设置图片
             var content = $(ue.getContent());
             //找到图片所处位置
             content.each(function(){
             	var p = content.find("p:contains(image)");
             	var div = $(p.text());
             	p.empty();
             	p.html(div);
             });
             me.params.swKnowledge.content = ue.getContent();
     		 $.ajax({
                  url:basePath+"cloud-purchase-web/swKnowledgeController",
                  type:"put",
                  data:$.toJSON(me.params),
                  contentType:'application/json;charset=UTF-8',  
                  success:function(data){
                 	 if(data.flag){
                 		 MainFrameUtil.alert({title:"成功提示",body:data.msg});
                 		MainFrameUtil.closeDialog("edit");
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
            var buttons = [{text:"保存",type:"primary",handler:this.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("edit")}}];
            return buttons;
        }
    }
});
</script>
