<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- 结算管理中的历史用电信息导入 -->
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>历史用电信息导入</title>
    <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
    <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
    <mk-panel title="导入excel" line="true" height="100%">
        <mk-form-panel>
            <mk-form-row  height="500" >
                <mk-form-col colspan="3" label="附件" auto_heigh="true">
	                <mk-file-upload :filename.sync="fileName"  v-ref:uploadfile  :upload.sync="upload" @select="select" @upload-error="uploadError" @upload-success="uploadSuccess" 
	                    @select="select" :url="uploadUrl"></mk-file-upload>
                </mk-form-col>
            </mk-form-row >
        </mk-form-panel>
        <mk-search-panel height="200px" deployable="false">
			<textarea class="form-control" rows="10"  placeholder="导入说明" disabled="disabled">导入说明：
				1、请先下载历史用电量模版 
				2、Excel模板中的列不得删除
				3、需要规范填写
				4、确认Excel中填写了有效数据
				5、请勿修改模板样式
			</textarea>

		</mk-search-panel>
        
    </mk-panel>
    
</body>
<script>
var basePath="${Config.baseURL}";
var importExcelVue = new Vue({
    el:'body',
    data:{
    	fileName:null,
    	upload: null,
        uploadUrl: basePath + "cloud-purchase-web/scConsElectricityController/import"
    },
    ready:function(){
        var that = this;
        MainFrameUtil.setDialogButtons(this.getButtons(),"importExcelDialog");
    },
    methods:{
    	select: function(data){
    	},
        //上传成功回调
        uploadSuccess:function(data){
            var that = this;
            //console.log($.toJSON(data));
            if(data.flag){
            	$.messager.progress('close');   // 如果提交成功则隐藏进度条
            	MainFrameUtil.alert({title:"成功提示",body:"导入成功！"});
            	MainFrameUtil.closeDialog("importExcelDialog");
            }else{
            	$.messager.progress('close');   
	            MainFrameUtil.alert({title:"失败提示",body:data.msg});
            }
        },
        //上传失败回调
        uploadError:function(data){
        	$.messager.progress('close');
              MainFrameUtil.alert({title:"警告",body:'导入失败'});
        },
        getButtons:function(){
            var that = this;
            var buttons=[{
                text:"导入",
                type:"primary",
                handler:function(btn,params){
                	$.messager.progress();
                    importExcelVue.$refs.uploadfile.validAndUpload();
                    params.importSign = true;
                }
            },{
                text:"取消",
                handler:function(btn,params){
                    params.importSign = false;
                    MainFrameUtil.closeDialog("importExcelDialog");
                }
            }];
            return buttons;
        }
    }
})
</script>
</html>
