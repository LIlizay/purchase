<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>管理员工具-行业资讯管理详情</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8" src="${Config.baseURL}view/cloud-purchase-web/static/ueditor/ueditor.all.js"> </script >
	<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
<div id="html_editVue" v-cloak>
	<su-form v-ref:qwqe id="fms1122" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="信息维护" height="260px" line="true">
		     <mk-form-panel>
		        <mk-form-row >
		        	<!-- 信息标题-->
		            <mk-form-col :label="formFields.title.label" colspan="3" :class="{'display-none':!formFields.title.formHidden}}" 
                           label-width="100px" label-align="right"> 
                            <su-textbox :data.sync="params.swHtmlInfo.title"  disabled="disabled" ></su-textbox>
		            </mk-form-col> 
		        </mk-form-row>
		          <mk-form-row>
		         	<!-- 所属省份  -->
	            <mk-form-col :label="formFields.provinceCodeName.label" :class="{'display-none':!formFields.provinceCode.formHidden}}" 
                          label-width="100px" label-align="right" :required_icon="!provinceCodeFlag">
             	  <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.swHtmlInfo.provinceCode" :high="150" 
	                	:data-source.sync="provinceList" name="provinceCode" disabled></su-select>
	            </mk-form-col>
	            
	            <!-- 生效开始时间 -->
	            <mk-form-col :label='formFields.beginTime.label' :class="{'display-none':!formFields.beginTime.formHidden}}">
	            	<su-datepicker format="YYYY-MM-DD" :data.sync="params.swHtmlInfo.beginTime" disabled="disabled" ></su-datepicker>
	            </mk-form-col>
	            
	            <!-- 生效结束时间 -->
		        <mk-form-col :label='formFields.endTime.label' :class="{'display-none':!formFields.endTime.formHidden}}">
	            	<su-datepicker format="YYYY-MM-DD" :data.sync="params.swHtmlInfo.endTime" disabled="disabled" ></su-datepicker>
	            </mk-form-col>    
	            
	       		</mk-form-row>
	       		<mk-form-row>
	       			<!-- 上传文件 -->
				<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}}" 
                           label-width="100px" label-align="right" colspan="3"> 
					 <div class="form-group" style="margin-top:2% !important"> 
                    	<strong id="fileName"></strong>
                    </div>
	            </mk-form-col>	
	       		</mk-form-row>
	       		
	       		<mk-form-row>
	       		<!-- 标题图片 -->
				<mk-form-col :label="formFields.bgImage.label" :class="{'display-none':!formFields.bgImage.formHidden}}" 
                           label-width="100px" label-align="right" colspan="3"> 
                    <div class="form-group" style="margin-top:2% !important"> 
                    	<strong id="imgName"></strong>
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
        	swHtmlInfo:{
        		id:null,//id
        		title:"",//标题
        		releasePerson:"",//发布人
        		releaseTime:null,//发布时间
        		releaseState:"",//发布状态
        		content:"",//信息内容(url)
        		orgNo:"",
        		hotStatus:"01",//热点状态
        		htmUrl:"",//详细信息url
        		bgImage:"",//背景图url
        		fileId:"",//上传文件
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
        ComponentUtil.getFormFields("purchase.home.swhtmlinfo",that);
    	//获取id
    	that.params.swHtmlInfo.id = MainFrameUtil.getParams("detail").id;
    	//初始化表单数据
    	that.initData(that.params.swHtmlInfo.id);
    },
    methods: {
    	//初始化表单数据
    	initData:function(id){
    		var me = this;
    		$.ajax({
                url:basePath+"cloud-purchase-web/swHtmlInfoController/" + id,
                type:"GET",
                success:function(data){
             	    if(data.flag){
             	    	if(data.data){
             	    		me.params.swHtmlInfo = data.data;
             	    		if(data.data.fileId != null && data.data.fileId != ""){
             	    			var obj = eval('(' + data.data.fileId + ')');
             	    			$("#fileName").empty().append("<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+ obj.fileName + "</a>");
             	    		}
             	    		if(data.data.bgImage != null && data.data.bgImage != ""){
             	    			var obj = eval('(' + data.data.bgImage + ')');
             	    			$("#imgName").empty().append("<a href='${Config.getConfig('file.service.url')}/"+obj.imageId+"'>"+ obj.imageName + "</a>");
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
