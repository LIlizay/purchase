<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员工具-知识库管理详情</title>
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
                           label-width="100px" label-align="right"> 
                            <su-textbox :data.sync="params.swKnowledge.title" disabled></su-textbox>
		            </mk-form-col> 
		        </mk-form-row>
		        
		         <mk-form-row>
		         	<!-- 所属省份  -->
	            <mk-form-col :label="formFields.provinceCodeName.label" :class="{'display-none':!formFields.provinceCode.formHidden}}" 
                          label-width="100px" label-align="right" :required_icon="!provinceCodeFlag">
				  <su-select placeholder="" label-name="name" :select-value.sync="params.swKnowledge.provinceCode" :high="150" 
	                	:data-source.sync="provinceList" name="provinceCode" disabled>
	               </su-select>
	            </mk-form-col>
	            
	            <!-- 知识分类 -->
	            <mk-form-col :label='formFields.knowledgeClassName.label' :class="{'display-none':!formFields.beginTime.formHidden}}">
	            	<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swKnowledge.knowledgeClass" :high="150"
		             	url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_knowledgeClass" name="knowledgeClass" disabled>
		            </su-select>
	            </mk-form-col>
	            
	       		</mk-form-row>
	       		
	       		<mk-form-row>
	       		<!-- 上传文件 -->
	            <mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}}" 
                           label-width="100px" label-align="right" colspan="2"> 
					 <div class="form-group" style="margin-top:2% !important"> 
                    	<strong id="fileName"></strong>
                    </div>
	            </mk-form-col>	
	            
	       	</mk-form-row>
		    </mk-form-panel>  
		</mk-panel>
		<div style="margin-top:15px; margin-right:3px">
			<div id="editor" height="300px">
			</div>
		</div>
	</su-form>
</div>
</body>
</html>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
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
            	knowledgeClass:{required: !0},
            	provinceCode:{required: !0},
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
    	MainFrameUtil.setDialogButtons(that.getButtons(),"detail");
    	//加载表单
        ComponentUtil.getFormFields("purchase.home.knowledge",that);
    	//获取id
    	that.params.swKnowledge.id = MainFrameUtil.getParams("detail").id;
    	//初始化表单数据
    	that.initData(that.params.swKnowledge.id);
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
             	    			var obj = eval('(' + data.data.fileId + ')');
             	    			$("#fileName").empty().append("<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+ obj.fileName + "</a>");
             	    		}
             	    		$("#editor").append(data.data.content);
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
    	
    	//按钮组
        getButtons:function(){
            var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("detail")}}];
            return buttons;
        }
    }
});
</script>
