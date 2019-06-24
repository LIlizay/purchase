<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈-反馈详情</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	<div id="listVue" v-cloak>
			<mk-panel title="反馈内容" line="true" v-cloak ">
			   <mk-form-panel label_width="160px">
					<mk-form-row>
						 <!-- 反馈时间 -->
			            <mk-form-col :label="formFields.feedbackTime.label":class="{'display-none':!formFields.effectiveDate.formHidden}">
				                <su-datepicker format="YYYY-MM-DD" :data.sync="sSFeedback.feedbackTime" name="feedbackTime" disabled="disabled"></su-datepicker>
				        </mk-form-col>
			             <!-- 满意程度 -->
			             <mk-form-col :label='formFields.consSatisfactionName.label' :class="{'display-none':!formFields.consSatisfactionName.formHidden}">
			                <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_satisfaction"
							:select-value.sync="sSFeedback.consSatisfaction" label-name="name" name="consSatisfaction" disabled="disabled"></su-select>
			            </mk-form-col>
		            </mk-form-row>
		            <mk-form-row height="150px">
		             <!-- 反馈意见 -->
		           	 	<mk-form-col :label="formFields.consIdea.label" colspan="3" :class="{'display-none':!formFields.consIdea.formHidden}">
			           		<su-textbox type="textarea" rows="7" rols="20" :data.sync="sSFeedback.consIdea" disabled></su-textbox>
			            </mk-form-col>
		            </mk-form-row>
				</mk-form-panel>
			</mk-panel>
			<!-- 附件 -->
			<mk-panel title="附件" line="true" min-height="150" v-cloak>
			    <div class="row" v-for="file in fileList" style="height: 15px; margin-top:10px;margin-bottom:15px;">
		       	 	<div style="heigth:40px ;margin-left:40px">
		       	 		<div style="float:left; width: 500px">{{file.name}}</div>
		       	 		<div style="float:left; width: 50px"><div style="display: {{isImg(file.name)}}"><a  href="javascript:void(0);"  v-on:click="imgView(file.id)">查看</a></div></div>
		       	 		<div style="float:left; width: 50px;display: {{isDownLoad(file.id)}}"><a href="${Config.getConfig('file.service.url')}/{{file.id}}">下载</a></div>
		       	 	</div>
		        </div>
			</mk-panel>
			
			<!-- 管理员回复 -->
			<mk-panel title="管理员回复" line="true" v-cloak>
			    <mk-form-panel label_width="160px">
			    	<mk-form-row height="150px">
		             <!-- 意见回复 -->
		           	 	<mk-form-col :label="formFields.adminAnswer.label" colspan="3" :class="{'display-none':!formFields.adminAnswer.formHidden}">
			           		<su-textbox type="textarea" rows="7" rols="20" :data.sync="sSFeedback.adminAnswer" disabled></su-textbox>
			            </mk-form-col>
		            </mk-form-row>
			    </mk-form-panel>
			</mk-panel>
	
	</div>
	<script type="text/javascript">
		var basePath = "${Config.baseURL}";
		var listVue = new Vue({
			el : "#listVue",
			data : {
				//文件列表，元素格式为{name:'aa.jpg',id:'kafiqerqwelkjh'}
				fileList : [],
				formFields : null,
				sSFeedback : {
					id : null,
					feedbackTime:'',
					consSatisfaction:'',
					consIdea:'',
					adminAnswer:'',
					fileIds: null,
				}
			},
			ready : function(){
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.feedback",this);
				//按钮初始化
				MainFrameUtil.setDialogButtons(this.getButtons(),"detail");
				this.sSFeedback.id = MainFrameUtil.getParams("detail").id;
				this.initData();
			},
			methods : {
				getButtons : function(){
					var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("detail");}}];
					return buttons;
				},
				initData : function(){
					var me = this;
					$.ajax({
						url: basePath + 'cloud-purchase-web/ssFeedbackController/'+me.sSFeedback.id,
						type:'get',
						success:function(data){
							console.log(data);
							me.sSFeedback = data.data;
							var fileIds = data.data.fileIds;
							//截取 xx: xx？ xx: xx
							var arrs = fileIds.split("?");
							//单个文件 xx: xx
							var arr = [];
							for(var i in arrs){
								arr = arrs[i].split(":");
								me.fileList.push({name: arr[1], id: arr[0]});
							}
							
						}
					});
					
				},
				//附件是否是图片格式
				isImg: function(fileName){
					if(fileName != null && fileName != "" && fileName.indexOf('.') != -1){
						var file = fileName.split('.');
						if(file[1].toLowerCase() == "jpg" || file[1].toLowerCase() == "jpeg" || file[1].toLowerCase() == "png" || file[1].toLowerCase() == "gif" ){
							return 'block';
						}
					}
					return 'none';
				},
				//是否需要显示下载文字
				isDownLoad : function(fileId){
					if(fileId != null && fileId != ""){
						return 'block';
					}
					return 'none';
				},
				//图片预览弹框
				imgView: function(fileId){
					MainFrameUtil.openDialog({
			  			id:"imgView",
			  			params:{id:fileId},  //MainFrameUtil.getParams()
						href:'${Config.baseURL}view/cloud-purchase-web/feedback/imgView',
						title:'图片预览',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620
					});
				}
				
			}
			
			
			
		});
		
	</script>
</body>
</html>