<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户反馈-反馈记录</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	
	<div id="listVue"  v-cloak>
		<mk-top-bottom height="100%" top_height="auto">
			<mk-search-panel slot="top" deployable="false" title="">
				<mk-search-visible> 
	                <!-- 反馈时间 -->
			        <su-form-group :label-name="formFields.feedbackTime.label" class="form-control-row" 
			        		:class="{'display-none':!formFields.effectiveDate.searchHidden}" col="4" label-width="120px" label-align="right">
						<su-datepicker name="effectDate" :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="sSFeedback.feedbackTimeS" ></su-datepicker>
			        </su-form-group>
			         <!-- 反馈时间 -->
			        <su-form-group label-name="至" class="form-control-row" 
			        		:class="{'display-none':!formFields.effectiveDate.searchHidden}" col="4" label-width="120px" label-align="right">
						<su-datepicker name="effectDate" :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="sSFeedback.feedbackTimeE" ></su-datepicker>
			        </su-form-group>
	            	<!-- 满意程度 -->
	            	<su-form-group :label-name='formFields.consSatisfactionName.label'  class="form-control-row "
								:class="{'display-none':!formFields.voltCode.searchHidden}" label-width="120px" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :data-source.sync="voltCodeData" :select-value.sync="sSFeedback.consSatisfaction"
									url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_satisfaction" name="satisfaction"></su-select>
	                </su-form-group>
	            	 <!-- 是否解决 -->
					<su-form-group :label-name="formFields.solveName.label" class="form-control-row" label-width="120px" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="sSFeedback.solve"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag"></su-select>
					</su-form-group>
				</mk-search-visible>
			       
				<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
					<su-button s-type="primary"  v-on:click="search" class="btn-width-small">查询</su-button>
					<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
				</div>
			</mk-search-panel>
		 
			<mk-panel title="用电用户列表" line="true" v-cloak slot="bottom" height="450px">
			    <div class="col-xs-12 height-fill">
		       	 	<div id="feedbackGrid" style="height:400px"></div>
		        </div>
			</mk-panel>
		</mk-top-bottom>
	</div>	
		<script type="text/javascript">
			var basePath = "${Config.baseURL}";
			var listVue = new Vue({
				el : "#listVue",
				data:{
					formFields:{},
					sSFeedback : {
						feedbackTimeS : "",//用户起始反馈时间
						feedbackTimeE : "",//用户截至反馈时间
						consSatisfaction : null,//用户满意程度
						solve : null,//是否解决
						
					},
				},
				ready : function(){
					//查询字段名称加载
					ComponentUtil.getFormFields("selling.feedback",this);
					//初始化表格
					this.feedbackTree();
				},
				methods : {
					feedbackTree : function(){
						//列表数据加载
						 ComponentUtil.buildGrid("selling.feedback",$("#feedbackGrid"),{ 
							url: basePath + 'cloud-purchase-web/ssFeedbackController/page',
						    width:"100%",
						    scrollbarSize : 0,
						    method: 'post',
						    queryParams:this.sSFeedback,
					        striped:true,
						    rownumbers: true,
						    pagination: true,
						    singleSelect:true,
						    nowrap: true,
						    fitColumns:true,
						    pageSize: 10,
						    pageList: [10, 20, 50, 100, 150, 200],
						    rowStyler:function(idx,row){
						        return "height:35px;font-size:12px;";
						    },
						    columnsReplace:[
								{field:'company',title:'售电公司',hidden:true},
								{field:'consFeedback',title:'反馈人',hidden:true},
						 	]
					    }); 
					},
					//查询
					search : function(){
						$('#feedbackGrid').datagrid('options').queryParams = this.sSFeedback;  
					    $("#feedbackGrid").datagrid('reload'); 
					},
					//重置
					reset : function(){
						this.sSFeedback = {feedbackTimeS:'',feedbackTimeE:'',consSatisfaction:'',solve:''};
					},
					//反馈意见超链接 数据库配置方法
					ideaHref : function(value,row,text){
						if(value){
							if(value.length > 21){
								return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value.substr(0,22)+"..."+"</a>";
							} else{
								return "<a onclick=\"listVue.detail('"+row.id+"')\">"+value+"</a>";
							}
						}
						return '';
					},
					//管理员回复截取字数
					adminBackHref : function(value,row,text){
						if(value){
							if(value.length > 22){
								return value.substr(0,22)+"...";
							} else{
								return value;
							}
						}
						return '';
					},
					detail:function(id){
						//更新页面
				  		MainFrameUtil.openDialog({
				  			id:"detail",
				  			params:{id:id},  //MainFrameUtil.getParams()
							href:'${Config.baseURL}view/cloud-purchase-web/feedback/detail',
							title:'反馈详情',
							iframe:true,
							modal:true,
							maximizable:true,
							width:"80%",
							height:620
						});
					},

					
				},
				
				
				
			});
		
		</script>
		

</body>
</html>