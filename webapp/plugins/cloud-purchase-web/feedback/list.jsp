<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="listVue">
	 
	<mk-panel title="意见反馈" height="auto" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="seeMore()">查看更多反馈记录</su-button>
  		</div>
		<div style="text-align:center;font-size: 20px;font-weight: 700;margin-top: 20px;">感谢您对我们工作的支持，诚邀您对我们的服务做出评价，以便我们更好的成长，为您提供更优质的服务!</div>
			<!-- 单选 -->	   
			<div title="" height="100%" style="margin-top:20px">
		<!-- 		<cs-question-panel  v-for="ques in questionOption" sortable="false" editable="false"  :question.sync="ques"> </cs-question-panel> -->
				<cs-question-panel   editable="false" sortable="false" :question.sync="questionOption" > </cs-question-panel> 
			</div>
			
			<div style="height:30px;margin-left: 10px;margin-top:20px">
				<div style="font-size: 14px;float: left;width: 40px;height:100%;line-height: 30px;">
					<div style="color: red;width:10.51px;float:left">*</div>
					<div style="float:left">2</div>
				</div>
				<div style="font-size: 14px;font-weight: 700;float: left;line-height: 30px;">您对云售电平台有哪些意见或者建议？(限1000字内)</div>
			</div>
			<!-- 输入框 -->
			<div contenteditable="true" class="question-panel-text" style="background: none;"></div>
			
			<div style="height:30px;font-size: 14px;margin-left: 19px;margin-top:30px;">
				<div style="float:left;width:30px;height:100%;line-height:30px;">3</div>
				<div style="float:left;font-weight: 700;line-height: 30px;">十分感谢您的反馈，如果您的反馈中能够包括截图，可以帮助我们进行将更好的改进。不胜感激！</div>
			</div>
			<!--附件  -->
			<div style="margin-top:10px;width:95.799999%;margin-left: 30px;">
		  		<mk-form-panel  label_width="60px" height="auto">
		            <mk-form-row  height="auto" >
		                <mk-form-col colspan="3" label="附件" auto_heigh="true">
		                   <mk-multifile-upload :params="uploadParams1" :files.sync="fileInfos"   v-ref:uploadmulti  show_upload="false"   @upload-error="uploadError" 
                    		 @upload-success="uploadSuccess"  url='${Config.getConfig("file.service.url")}' required=false maxcount=20>
                		   </mk-multifile-upload>
		                </mk-form-col>
		            </mk-form-row >
		        </mk-form-panel>
			</div>	
			
			<div  style="text-align:center;padding-top:20px; padding-bottom: 10px">
		<!-- 	        <button class="btn btn-width-small btn-default" type="button">  保存  </button> -->
			        <button class="btn btn-width-small btn-primary" type="button" v-on:click="save">提交</button>	
		<!-- 	        <button class="btn btn-width-small btn-warning" type="button">  退回  </button>		                     -->
				</div>
						 
	</mk-panel>

</div>

<script type="text/javascript">
	var basePath = "${Config.baseURL}";	
	var listVue = new Vue({
		el : "#listVue",
		data : {
			//附件上传的
			title:"管理附件",
// 	        erecordId: null,
	        fileInfos:[],
	        
			seeMoreUUID : null,
			valid : false,
			sSFeedback : {
				consSatisfaction : null,//用户满意程度
				consIdea : "",//用户反馈意见
				fileIds:"",//附件
				feedbackTime : null,//反馈时间
				solve : null, //是否解决
			},
			 questionOption:{
			    	requared:true,
			    	multi:false,
			    	title:" 整体来说，您对云售电平台的使用是否满意：",
			    	sort:"1",
			    	items:[]
			}, 
		},
		ready : function(){
			this.initData();
			
// 			console.log(MainFrameUtil.getParams("manageAttachmentDialog"));
			var that = this;
// 	        this.fileInfos = MainFrameUtil.getParams("manageAttachmentDialog").fileInfo;
// 	        this.erecordId = MainFrameUtil.getParams("manageAttachmentDialog").id;
// 	        var status = MainFrameUtil.getParams("manageAttachmentDialog").status;
// 	        var isDeleted = MainFrameUtil.getParams("manageAttachmentDialog").isDeleted;
// 	        if(status == "03"){
// 	            this.title = "此档案已作废，已禁止更新附件！";
// 	        }else if(isDeleted == 0){
// 	            this.title = "此档案已被删除，已禁止更新附件！";
// 	        }else{
// 	            MainFrameUtil.setDialogButtons(this.getButtons(),"manageAttachmentDialog");
// 	        }
		},
		methods : {
			
			//初始码表
			initData:function(){
				var main = this;
				$.ajax({
					url: basePath + 'cloud-purchase-web/ssFeedbackController/getConsSatisfaction',
					type:'get',
					success:function(data){
						main.questionOption.items = data.data;
					}
					
				});
			},
			
			//提交方法
			save:function(){
				var main = this;
				
				//获取单选满意度的值
				for(var i in main.questionOption.items){
					if(main.questionOption.items[i].checked){
						main.sSFeedback.consSatisfaction = main.questionOption.items[i].value;
					} 
				}
				//验证单选必填
				if(main.sSFeedback.consSatisfaction == null){
					MainFrameUtil.alert({title:"提示",body:"请您选择：对云售电平台的使用的满意程度。"});
					return;
				}
				//验证字数
				if($(".question-panel-text").text().length > 1000){
					MainFrameUtil.alert({title:"提示",body:"您对云售电平台有哪些意见或者建议<br/>限1000字内！"});
					return;
				}
				//验证意见必填
				main.sSFeedback.consIdea = $(".question-panel-text").text();
				if(main.sSFeedback.consIdea == "" || main.sSFeedback.consIdea == null){
					MainFrameUtil.alert({title:"提示",body:"请您填写对云售电平台有哪些意见或者建议!"});
					return;
				}
				 //验证是否选择文件
	       	   	if(this.$refs.uploadmulti.valid()){
	       		   this.$refs.uploadmulti.validAndUpload();
	       	   	}	
				
			},
			
			//上传成功回调
			uploadSuccess:function(data){
				console.log(data);
				var that = this; 
				//生成数据库用的String数据（多个附件用“?”隔开，每个附件的名字和id用“:”隔开）
				var fileInfoStr = "";
				if(data!=null && typeof(data) != "string" && !data.failure){
	                for(var i = 0; i < data.length; i++){
	                    var result=data[i];
	                    var fileId = result.id;    //获取文件的id
	                    var fileName=result.fileName+result.extension;//获取文件名+后缀
	                    //拼接文件字符串
	                    if(i == 0){
		                    fileInfoStr += fileId + ":" + fileName;
		                }else{
		                    fileInfoStr += "?" + fileId + ":" + fileName;
		                }
	                }
	            }
	            that.sSFeedback.fileIds = fileInfoStr;
                 //提交后台
            	that.submit();
			},
			//上传失败回调  
			uploadError:function(data){
				$.messager.progress('close');  //关闭进度条
	              MainFrameUtil.alert({title:"警告",body:'上传失败'});
			},
			
			submit:function(){
				var main = this;
				main.sSFeedback.solve = '0';
				
				MainFrameUtil.confirm({
		        title:"确认",
		        body:"提交后数据不可删除，是否确认？",
		        onClose:function(type){
		            if(type=="ok"){//确定
					$.messager.progress({title:"请等待",msg:"正在提交...",interval:100});
					$.ajax({
						url : basePath + 'cloud-purchase-web/ssFeedbackController',
						type:'post',
						data:$.toJSON(main.sSFeedback),
		   	   			contentType : 'application/json;charset=UTF-8',
						success : function(data){
							$.messager.progress('close');
							if(data.flag){
								MainFrameUtil.alert({title:"提示",body:"您的反馈意见已经成功提交！</br>我们会仔细阅读认真听取您的意见，并尽快回复您的意见。感谢您一如既往地支持云售电平台！"});
								//清空页面数据
								main.fileInfos = [];
								$(".question-panel-text").text('');
								//获取单选满意度的值
								for(var i in main.questionOption.items){
									main.questionOption.items[i].checked = false;
								}
							}else{
								MainFrameUtil.alert({title:"提示",body:data.msg});
							}
						},
	    				error : function() {
	    					$.messager.progress('close');
	    					MainFrameUtil.alert({title:"提示",body:"保存失败！"});
	    				}
					});
		            }
		        }
    		})
			},
			
			//查看更多反馈记录 页面跳转
			seeMore : function(){
				if(!this.seeMoreUUID){
					this.seeMoreUUID=Math.random();
				}
				MainFrameUtil.openTabPage(this.seeMoreUUID, basePath+"/view/cloud-purchase-web/feedback/seemore", "反馈记录",false);
			},
				
		},
		
		});
	
</script>

</body>
</html>