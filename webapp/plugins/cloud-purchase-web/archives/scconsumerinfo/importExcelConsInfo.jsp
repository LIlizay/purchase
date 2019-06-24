<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <title>用户信息导入</title>
    <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
    <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
    <mk-panel title="导入excel" line="true" height="100%">
        <mk-form-panel>
            <mk-form-row  height="500" >
                <mk-form-col colspan="3" label="附件" auto_heigh="true">
	                <mk-file-upload :filename.sync="fileName"  v-ref:uploadfile  :upload.sync="upload" @select="select" 
	                	@upload-error="uploadError" @upload-success="uploadSuccess"  :url="uploadUrl"></mk-file-upload>
                </mk-form-col>
            </mk-form-row >
        </mk-form-panel>
        <mk-search-panel height="200px" deployable="false">
			<textarea class="form-control" rows="10"  placeholder="导入说明" disabled="disabled">导入说明：
  1、请先下载用户档案导入模版；        
  2、Excel模板中的前三行和所有列不得删除 ；                                         
  3、需要按照模板中的提示规范填写  ；                  
  4、省、市、区（县）字段需要与系统中保持一致；
  5、不允许一个文件中有用户名称、电压等级两个字段完全重复的数据，否则将无法导入；
  6、若一个用户有多个计量点，请维护多行计量点数据，其他字段留空，系统将自动向上匹配最近的用户信息；
  7、上级单位暂不支持导入，如需维护上级单位请在页面中维护；
  8、新数据将覆盖旧数据，请确认数据无误后导入。
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
        uploadUrl: basePath + "cloud-purchase-web/scConsumerInfoController/import"
    },
    ready:function(){
        var that = this;
        MainFrameUtil.setDialogButtons(this.getButtons(),"import_excel_dialog");
    },
    methods:{
    	select: function(data){
    	},
        //上传成功回调
        uploadSuccess:function(data){
            var that = this;
            //console.log($.toJSON(data));
            if(data.flag){
            	//$.messager.progress('close');   // 如果提交成功则隐藏进度条
            	MainFrameUtil.alert({title:"成功提示",body:"导入成功！"});
            	MainFrameUtil.closeDialog("import_excel_dialog");
            }else{
            	//$.messager.progress('close');   
	            MainFrameUtil.alert({title:"失败提示",body:data.msg});
            }
        },
        //上传失败回调
        uploadError:function(data){
        	//$.messager.progress('close');
              MainFrameUtil.alert({title:"警告",body:'导入失败'});
        },
        getButtons:function(){
            var that = this;
            var buttons=[{
                text:"导入",
                type:"primary",
                handler:function(btn,params){
                	//$.messager.progress();
                    importExcelVue.$refs.uploadfile.validAndUpload();
                    params.importSign = true;
                }
            },{
                text:"取消",
                handler:function(btn,params){
                    params.importSign = false;
                    MainFrameUtil.closeDialog("import_excel_dialog");
                }
            }];
            return buttons;
        }
    }
})
</script>
</html>
