<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>反馈意见管理</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
	<div id="listVue"  v-cloak>
		<mk-top-bottom height="100%" top_height="auto">
			<mk-search-panel slot="top" deployable="false" title="">
				<mk-search-visible> 
	                <!-- 反馈时间 -->
			        <su-form-group :label-name="formFields.feedbackTime.label" class="form-control-row" 
			        		:class="{'display-none':!formFields.effectiveDate.searchHidden}" label-width="100px" col="4" label-width="120px" label-align="right">
						<su-datepicker name="effectDate" :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="sSFeedback.feedbackTimeS" ></su-datepicker>
			        </su-form-group>
			        <!-- 反馈时间 -->
			        <su-form-group label-name="至" class="form-control-row" 
			        		:class="{'display-none':!formFields.effectiveDate.searchHidden}" label-width="100px" col="4" label-width="120px" label-align="right">
						<su-datepicker name="effectDate" :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="sSFeedback.feedbackTimeE" ></su-datepicker>
			        </su-form-group>
	            	<!-- 满意程度 -->
	            	<su-form-group :label-name='formFields.consSatisfactionName.label' label-width="100px"  class="form-control-row "
								:class="{'display-none':!formFields.voltCode.searchHidden}" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :data-source.sync="voltCodeData" :select-value.sync="sSFeedback.consSatisfaction"
									url="${Config.baseURL}/globalCache/queryCodeByKey/pcode/selling/sell_satisfaction" name="satisfaction"></su-select>
	                </su-form-group>
	            	 <!-- 是否解决 -->
					<su-form-group :label-name="formFields.solveName.label" class="form-control-row" label-width="100px" col="4" label-align="right">
						<su-select placeholder="--请选择--" label-name="name" :select-value.sync="sSFeedback.solve"
							url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag"></su-select>
					</su-form-group>
					<!-- 公司 -->
					<su-form-group :label-name='formFields.company.label'  class="form-control-row " 
								:class="{'display-none':!formFields.company.searchHidden}" col="4" label-width="100px" label-align="right">
	                    <su-textbox :data.sync="sSFeedback.company" type="text"></su-textbox>
	                </su-form-group>
				</mk-search-visible>
			       
				<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
					<su-button s-type="primary"  v-on:click="search" class="btn-width-small">查询</su-button>
					<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
				</div>
			</mk-search-panel>
		 
			<mk-panel title="反馈意见列表" line="true" v-cloak slot="bottom" height="450px">
				<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		              <su-button class="btn-operator" s-type="default" labeled="true" label-ico="pencil-square-o" v-on:click="reply">回复</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="out">导出</su-button>
		            
	    	    </div>
			    <div class="col-xs-12 height-fill">
		       	 	<div id="feedbackGrid" style="height:400px"></div>
		        </div>
			</mk-panel>
		</mk-top-bottom>
	</div>
		
	<script type="text/javascript">
		var basePath = "${Config.baseURL}";
		var listVue = new Vue({
			el:"#listVue",
			data : {
				formFields:{},
				sSFeedback : {
					feedbackTimeS : "",//用户起始反馈时间
					feedbackTimeE : "",//用户截至反馈时间
					consSatisfaction : null,//用户满意程度
					solve : null,//是否解决
					company:  null,//售电公司
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
					    singleSelect:false,
					    nowrap: true,
					    fitColumns:true,
					    pageSize: 10,
					    pageList: [10, 20, 50, 100, 150, 200],
					    rowStyler:function(idx,row){
					        return "height:35px;font-size:12px;";
					    },
				    }); 
				},
				//查询
				search : function(){
					$('#feedbackGrid').datagrid('options').queryParams = this.sSFeedback;  
				    $("#feedbackGrid").datagrid('reload'); 
				},
				//重置
				reset : function(){
					this.sSFeedback = {feedbackTimeS:'',feedbackTimeE:'',consSatisfaction:'',solve:'',company:''};
				},
				//回复按钮
				reply : function(){
					var row = $("#feedbackGrid").datagrid("getSelected");
					//详情页面
			  		MainFrameUtil.openDialog({
			  			id:"reply",
			  			params:{id:row.id},  //MainFrameUtil.getParams()
						href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/feedbackmanage/reply',
						title:'反馈回复',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						onClose:function(){
							 $("#feedbackGrid").datagrid("reload"); 
						}
					});
									
				},
				//导出按钮
				out : function(){
					var me = this;
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"是否导出反馈意见？",
				        onClose:function(type){
				            if(type=="ok"){//确定
							var rows = $("#feedbackGrid").datagrid("getSelections");
							var ids = [];
							var param = new Object;
							if(rows != null && rows.length != 0){  //有选择导出数据
								for(var i=0; i<rows.length; i++){
									ids.push(rows[i].id);
								}
								param['ids'] =ids;
							} else{
								param = me.sSFeedback;
				            }
							param["pagingFlag"]=false;
							 var excelParams ={
						               fileName:"反馈意见列表.xlsx",                        
						               sheetParamsList:                                    
						                [{
						                   className: "com.hhwy.purchaseweb.ssfeedback.service.SsFeedbackService",
						                   methodName: "getSsFeedbackByPage",                    
						                   queryParams:[param,""],
						                   sheetName:"反馈意见",                         
						                   excelHeaders:"company,consIdea,adminAnswer,feedbackTime,consSatisfactionName,consFeedback,solveName",
						                   excelHeaderNames:"公司,反馈意见,管理员回复,反馈时间,满意程度,反馈人,是否解决"
						                }]
						            }; 
							 		//公共方法 不需更改
						            var exportExcelByColumnUrl = basePath + "exportExcelHandlerController/exportExcelByColumn";
						            var exportExcelPageUrl = basePath + "exportExcelHandlerController/exportExcel/";
						            $.ajax({
						                url : exportExcelByColumnUrl,
						                type : 'POST',
						                contentType : 'application/json;charset=UTF-8',
						                data: $.toJSON(excelParams),
						                success : function(data) {
						                     if(data.flag){
						                         var url = exportExcelPageUrl+data.data;
						                         location.href = url;
						                     }else{
						                         MainFrameUtil.alert({title:"提示",body:data.msg}); 
						                     }
						                },
						                error : function() {
						                    MainFrameUtil.alert({title:"提示",body:"导出失败！"});
						                }
						            });
				            }
				        }
					})     
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
					//详情页面
			  		MainFrameUtil.openDialog({
			  			id:"detail",
			  			params:{id:id},  //MainFrameUtil.getParams()
						href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/feedbackmanage/detail',
						title:'反馈回复详情',
						iframe:true,
						modal:true,
						maximizable:true,
						width:"80%",
						height:620,
						onClose:function(){
							 $("#feedbackGrid").datagrid("reload"); 
						}
					});
				},
				
			}
						
			
		});
	
	</script>
	
	
	
</body>
</html>