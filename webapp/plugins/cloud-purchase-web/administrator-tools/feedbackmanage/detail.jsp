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
						<!-- 售电公司 -->
						<mk-form-col :label="formFields.company.label" label-width="200px" :class="{'display-none':!formFields.company.formHidden}" col="4" label-align="right">
		                	<su-textbox disabled="disabled" :data.sync="sSFeedback.company"></su-textbox>
		           		</mk-form-col>
						<!-- 反馈用户  -->
			            <mk-form-col :label="formFields.consFeedback.label" label-width="200px" :class="{'display-none':!formFields.consFeedback.formHidden}" col="4" label-align="right">
							<su-textbox disabled="disabled" :data.sync="sSFeedback.consFeedback"></su-textbox>
			            </mk-form-col>
			        </mk-form-row>
			        <mk-form-row>    
						 <!-- 反馈时间 -->
			            <mk-form-col :label="formFields.feedbackTime.label":class="{'display-none':!formFields.effectiveDate.formHidden}" col="4">
				                <su-datepicker format="YYYY-MM-DD" :data.sync="sSFeedback.feedbackTime" name="feedbackTime" disabled="disabled"></su-datepicker>
				        </mk-form-col>
			             <!-- 满意程度 -->
			             <mk-form-col :label='formFields.consSatisfactionName.label' :class="{'display-none':!formFields.consSatisfactionName.formHidden}" col="4">
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
				formFields : null,
				//文件列表，元素格式为{name:'aa.jpg',id:'kafiqerqwelkjh'}
				fileList : [],
				sSFeedback : {
					id : null,
					feedbackTime:'',
					consSatisfaction:'',
					consIdea:'',
					adminAnswer:'',
					solve : null,//是否解决
				}
			},
			ready : function(){
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.feedback",this);
				//按钮初始化
				MainFrameUtil.setDialogButtons(this.getButtons(),"detail");
				this.sSFeedback.id = MainFrameUtil.getParams("detail").id;
				this.initData();
				console.log(this.sSFeedback.id)
			},
			methods : {
				getButtons : function(){
					var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("detail");}}];
					return buttons;
				},
				//初始页面数据
				initData : function(){
					var me = this;
					$.ajax({
						url: basePath + 'cloud-purchase-web/ssFeedbackController/'+me.sSFeedback.id,
						type:'get',
						success:function(data){
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
						href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/feedbackmanage/imgView',
						title:'图片预览',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620
					});
				},
// 				save : function(){
// 					var me = this;
// 					var type ="post";
// 					if(me.sSFeedback.id){
// 						type = "put";
// 					}
// 					me.sSFeedback.solve = '1';
// 					$.ajax({
// 						url : basePath + 'cloud-purchase-web/ssFeedbackController',
// 						type:type,
// 						data:$.toJSON(me.sSFeedback),
// 						contentType : 'application/json;charset=UTF-8',
// 						success:function(data){
// 							if(data.flag){
// 								MainFrameUtil.alert({ title:"提示", body:"回复成功！"}); 
// 							}else{
// 								MainFrameUtil.alert({ title:"提示", body:"回复失败！"});
// 							}
// 						}
// 					});
					
// 				}
				
			}
			
		});
		
	</script>
</body>
</html>