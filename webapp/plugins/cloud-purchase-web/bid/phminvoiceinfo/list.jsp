<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>结算管理-发票登记</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body id="invoiceVue" v-cloak>
		<mk-search-panel title="" height="20%" deployable="false" title="客户信息管理">
			<mk-search-visible>
				<!-- 起始时间 -->
				<su-form-group :label-name='formFields.bgnYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.bgnYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.bgnYm" name="bgnYm"></su-datepicker>
				</su-form-group>
				<!-- 结束时间 -->
				<su-form-group :label-name='formFields.endYm.label'  class="form-control-row " 
						:class="{'display-none':!formFields.endYm.searchHidden}" col="4" label-align="right">
					<su-datepicker format="YYYY-MM" :data.sync="params.endYm" name="endYm"></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="发票登记列表" line="true" height="50%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="reply">删除</su-button>
		    </div>
		    <div id="invoiceGrid" class="col-xs-12" ></div>
		</mk-panel>
		<su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
		<mk-panel title="发票登记信息" line="true" height="240px">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="save">发票登记</su-button>
		    </div>
			<mk-form-panel title="" label_width="140px" num="2">
	 		<mk-form-row>
	 			<!-- 开票时间-->
				<mk-form-col :label="formFields.invoiceDate.label" :class="{'display-none':!formFields.invoiceDate.formHidden}" required_icon="true">
					<su-datepicker format="YYYY-MM-DD" :data.sync="invoiceData.invoiceDate" name="invoiceDate"></su-datepicker>
				</mk-form-col>
				<!-- 开票人 -->
				<mk-form-col :label="formFields.drawer.label" :class="{'display-none':!formFields.drawerName.formHidden}" required_icon="true">
					<su-textbox :data.sync="invoiceData.drawer" name="drawer"></su-textbox>
				</mk-form-col>
	 		</mk-form-row>
	 		<mk-form-row>
	 			<!-- 发票代码 -->
				<mk-form-col :label="formFields.invoiceCode.label" :class="{'display-none':!formFields.invoiceCode.formHidden}" required_icon="true">
					<su-textbox :data.sync="invoiceData.invoiceCode" name="invoiceCode"></su-textbox>
				</mk-form-col>
				<!-- 发票号码 -->
				<mk-form-col :label="formFields.invoiceNumber.label" :class="{'display-none':!formFields.invoiceNumber.formHidden}" required_icon="true">
					<su-textbox :data.sync="invoiceData.invoiceNumber" name="invoiceNumber"></su-textbox>
				</mk-form-col>
	 		</mk-form-row>
	 		<mk-form-row>
	 			<!-- 附件上传 -->
				<mk-form-col :label="formFields.file.label" :class="{'display-none':!formFields.file.formHidden}}" label-width="100px" label-align="right" colspan="2" required_icon="true"> 
					<mk-file-upload :filename="fileName" v-ref:uploadfile @upload-error="uploadError" @upload-success="uploadSuccess" name="file" required="true"
	                	 url="${Config.getConfig('file.service.url')}">
	                </mk-file-upload>
				</mk-form-col>
	 		</mk-form-row>
	 	</mk-form-panel>
	 </mk-panel>
	 </su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var invoiceVue = new Vue({
			el: '#invoiceVue',
			data: {
				fileName:"",
				formFields:{},
				selectedFlag:false,//选择购电计划标志
				url:basePath+"view/cloud-purchase-web/bid/phminvoiceinfo/user-dialog",
				obj:null,//实体信息
				cacheurl:null,
			    params:{bgnYm:null,endYm:null},
				invoiceData:{
					id:null,
					invoiceDate:null,
					schemeId:null,
					drawer:null,
					invoiceCode:null,
					invoiceNumber:null,
					file:null
				},
				//非空验证规则
		    	dataOption: {
		            rules: {
		            	invoiceDate:{required: !0},
		            	invoiceCode:{required: !0},
		            	invoiceNumber:{required: !0},
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//列表数据加载
				ComponentUtil.buildGrid("purchase.bid.phminvoiceinfo",$("#invoiceGrid"),{ 
					url: basePath + 'cloud-purchase-web/phmInvoiceInfoController/page',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:me.params,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    singleSelect:true,
				    fitColumns:true,
				    //scrollbarSize:0,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onSelect:function(index,row){
				    	me.cacheurl = null;
				    	me.selectedFlag = true;
				    	me.setInvoiceData(row);
				    },
				    onLoadSuccess:function(data){
				    	me.resetInvoiceData();
				    }
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.bid.phminvoiceinfo",this);
			},
			methods: {
				fileIdFormatter:function(value,row,index){
					if(value != null && value != ""){
		            	var file = eval("("+value+")");
		            	var color = "";
		            	if(row.fileRecord === file.fileId){
		            		color = "style=\"color:red;text-decoration:none;\"";
		            	}
		            	var check = "";
		            	if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".pdf") == file.fileName.length-4){
		            		check = "<a href='javascript:void(0)' onclick='invoiceVue.pdfPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
		            	}
		        		else if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".jpg") == file.fileName.length-4){
		        			check = "<a href='javascript:void(0)' onclick='invoiceVue.jpgPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
		            	}
		            	else if(file.fileName != null && file.fileName != "" && file.fileName.lastIndexOf(".png") == file.fileName.length-4){
		            		check = "<a href='javascript:void(0)' onclick='invoiceVue.jpgPreview(\"" + file.fileId + "\",\"" + file.fileName + "\")'>查看</a>";
		            	}
		            	
		            	return  "<a target=\"view_frame\" href='${Config.getConfig('file.service.url')}/" + file.fileId + "'>下载</a>&nbsp;&nbsp;" + check;
		            }
				},
				//附件上传成功回调
		 	    uploadSuccess:function(data){
				    if(data){
				    	this.invoiceData.file = $.toJSON({"fileId":data[0].id,"fileName":data[0].fileName+data[0].extension});
				    	this.fileName = data[0].fileName+data[0].extension;
			  	    }
				    this.submit();
				    
		  	    },
		  		//附件上传失败回调
		  	    uploadError:function(data){
		  	    	$.messager.progress('close');   				 // 隐藏进度条
		  		    MainFrameUtil.alert({title:"警告",body:'上传失败'});//上传失败回调  
		  	    },
				pdfPreview:function(fileid ,name){
		    		var url= basePath+"view/business-core/pdfviewer/viewer?id="+fileid;
		    		MainFrameUtil.openDialog({
		                id:'pdfPreviewDialog',
		                iframe:true,
		                href: url,
		                title: name + ' 预览',
		                modal:true,
		                width:"80%",
		                height: 620,
		                maximizable:true,
		                buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('pdfPreviewDialog');
		                    }
		                }]
		            });		
				},	
				jpgPreview:function(id,name){
					MainFrameUtil.openDialog({
		                id:'imagePreviewDialog',
		                iframe:true,
		                params:{id:id,name:name},//传递参数.可以通过 MainFrameUtil.getParams() 获取
		                href: basePath+'/view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_01/imagePreview?id='+id+'&name='+name,
		                title:'图片预览',
		                modal:true,
		                width:"80%",
		                height: 620,
		                maximizable:true,
		                buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('imagePreviewDialog');
		                    }
		                }]
		            });
				},
				//重置
				reset:function(){
					this.params = {bgnYm:null,endYm:null};
				},
				//重置表单信息
				resetInvoiceData:function(){
					if(!this.obj){
						this.invoiceData={id:null,schemeId:null,invoiceDate:null,drawer:null,
								invoiceCode:null,invoiceNumber:null,file:null};
						this.cacheurl=null;
						this.selectedFlag = false;
					}
					this.obj = null;
				},
				reply:function(){
					var me = this;
					var selInfo = $('#invoiceGrid').datagrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
				    
				    MainFrameUtil.confirm({
				        title:"确认",
				        body:"确定删除发票登记信息？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	me.invoiceData = {id:null,invoiceDate:null,drawer:null,invoiceCode:null,invoiceNumber:null,file:null};
				            	me.invoiceData.id = selInfo[0].id;
				            	me.invoiceData.schemeId = selInfo[0].schemeId;
				            	me.submit();
				            	me.fileName = '';
				            }
				        }
		    		});
				},
				//查询
				getDataGrid:function(){
					var me = this;
					if(me.params.bgnYm != null && me.params.bgnYm != "" && me.params.endYm != null && me.params.endYm != ""){
						var s = parseInt(me.params.bgnYm.replace("-",""));
						var e = parseInt(me.params.endYm.replace("-",""));
						if(s > e){
							MainFrameUtil.alert({title:"提示",body:"起始日期不能大于结束日期！"});
					    	return;
						}
					}
					$('#invoiceGrid').datagrid('options').queryParams = this.params;  
				    $("#invoiceGrid").datagrid('load'); 
				},
				//设置发票登记数据
				setInvoiceData:function(data){
					this.invoiceData = data;
					if(data.file != null && data.file !=""){
						var obj = eval("("+data.file+")");
						this.fileName = "<a href='${Config.getConfig('file.service.url')}/"+obj.fileId+"'>"+obj.fileName+"</a>";
					}else{
						this.fileName = "";
					}
					
					if(data.drawer){
						this.cacheurl = basePath + "globalCache/queryUserById/" + data.drawer;
					}
				},
				
				//保存
		    	save:function(){
		    		var me = this;
		    		if(!me.selectedFlag){
						 MainFrameUtil.alert({title:"警告",body:"请选择要登记的发票"});
                   	 	return;
					}
					//验证表单信息
               		me.$refs.elepower.valid();
                   if(!me.valid){
                       return false;
                   }
                   if(!me.invoiceData.drawer){
                   	 MainFrameUtil.alert({title:"警告",body:"请选择发票人！"});
                   	 return;
                   }
                	//有否选择了文件
		    		if(me.$refs.uploadfile.valid()){//已选择文件
		    			me.$refs.uploadfile.validAndUpload();
		    		}else{
		    			return;
		    		}
		    	},
				//发票登记
				submit:function(){
					var me = this;
					$.ajax({
		                url : basePath+"cloud-purchase-web/phmInvoiceInfoController",
		                type : 'put',
		                data:$.toJSON(me.invoiceData), 
		                contentType : 'application/json;charset=UTF-8',
		                success : function(data) {
		                    if(data.flag){
		                    	me.obj = data.data;
		                    	$("#invoiceGrid").datagrid("reload");
		                        MainFrameUtil.alert({title:"成功提示",body:data.msg});
		                    }else{
		                        MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
		                    }
		                },
		                error : function() {
		                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
		                }
		            })
				}
			}
		}); 
	</script>
</body>
</html>