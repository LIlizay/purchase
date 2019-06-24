<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>上传</title>
    <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
    <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
    <mk-panel title="" line="true" height="100%">
        <mk-form-panel label_width="60px">
            <mk-form-row  height="500" >
                <mk-form-col colspan="3" label-width="500px" label="上传" auto_heigh="true">
	                <mk-file-upload v-ref:uploadfile @upload-error="uploadError" @upload-success="uploadSuccess" 
				url="${Config.getConfig('file.service.url')}"></mk-file-upload>
                </mk-form-col>
            </mk-form-row >
        </mk-form-panel>
<!--         <mk-search-panel height="200px" deployable="false"> -->
<!-- 			<textarea class="form-control" rows="10"  placeholder="导入说明" disabled="disabled"> -->
<!-- 			</textarea> -->

<!-- 		</mk-search-panel> -->
        
    </mk-panel>
    
</body>
<script>
var basePath="${Config.baseURL}";
var uploadVue = new Vue({
    el:'body',
    data:{
    	fileId : null,
    	fileName:null,
    	upload: null,
    },
    ready:function(){
        var me = this;
//         me.row = MainFrameUtil.getParams('upload').row
        MainFrameUtil.setDialogButtons(me.getButtons(),"upload");
    },
    methods:{
    	select: function(data){
    	},
        //上传成功回调
        uploadSuccess:function(data){
            var me = this;
            $.messager.progress('close');
            if(data != null){
				var fileInfo ="{'fileId':'"+data[0].id+"','fileName':'"+data[0].fileName+data[0].extension+"'}";
				me.fileId = fileInfo;
			}
            var fileId = {"fileId" : me.fileId};
            MainFrameUtil.setParams(fileId,"upload");
            MainFrameUtil.closeDialog("upload");
        },
        //上传失败回调
        uploadError:function(data){
        	$.messager.progress('close');
              MainFrameUtil.alert({title:"错误",body:'导入失败!'});
        },
        getButtons:function(){
            var me = this;
            var buttons=[{
                text:"保存",
                type:"primary",
                handler:function(btn,params){
                	$.messager.progress();
                	uploadVue.$refs.uploadfile.validAndUpload();
                }
            },{
                text:"取消",
                handler:function(btn,params){
                    params.fileId = null;
                    MainFrameUtil.closeDialog("upload");
                }
            }];
            return buttons;
        }
    }
})
</script>
</html>
