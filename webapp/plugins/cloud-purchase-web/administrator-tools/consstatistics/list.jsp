<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>管理员工具-平台用户明细</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="listVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="平台用户明细">
			<mk-search-visible> 
            	<!-- 省份 -->
            	<su-form-group :label-name="formFields.provinceName.label" class="form-control-row "
							:class="{'display-none':!formFields.provinceName.searchHidden}" label-width="120px" col="4" label-align="right">
					<su-select placeholder="--请选择--" label-name="name" multiple="true" :select-value.sync="params.orgNo"
								url="${Config.baseURL}/globalCache/queryProvinceList/"></su-select>
                </su-form-group>
             	 <!-- 系统开通时间 -->
		        <su-form-group :label-name="formFields.date.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.date.searchHidden}" col="4" label-width="120px" label-align="right">
					<su-datepicker :z-index="999999" id="effectDate" format="YYYY-MM-DD"  :data.sync="params.startDate" ></su-datepicker>
		        </su-form-group>
		        <!-- 至 -->
		        <su-form-group label-name="至" class="form-control-row" col="4" label-width="120px" label-align="right">
		            <su-datepicker :z-index="999999" id="expiryDate" format="YYYY-MM-DD"  :data.sync="params.endDate" ></su-datepicker>
		        </su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="平台用户明细列表" line="true" v-cloak slot="bottom" height="100%">
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consDetailGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var listVue = new Vue({
			el: '#listVue',
			data: {
				formFields:{},
				params:{
			    	orgNo:null,
			    	startDate: null,
			    	endDate: null,
			    }
			},
			ready:function(){
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.tool.consstatistics",this);
				 //按钮组
                MainFrameUtil.setDialogButtons([
                	{
                        text:"取消",
                        handler:function(btn,params){
                            MainFrameUtil.closeDialog();
                        }
                    }
                ]);
				//列表数据加载
				ComponentUtil.buildGrid("purchase.tool.consstatistics",$("#consDetailGrid"),{ 
					url: basePath + 'cloud-purchase-web/consStatisticsController/page',
					width:"100%",
				    scrollbarSize : 0,
				    method: 'post',
				    queryParams:this.params,
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
			methods: {
				reset:function(){
					this.params = {startDate:null,endDate:null,orgNo:""};
				},
				hrefRender: function(value,row,text){
					if(value){
						return "<a href='http://"+value+"' target='view_window'>"+value+"</a>";
					}
				},
				getDataGrid:function(){
					$('#consDetailGrid').datagrid('options').queryParams = this.params;  
				    $("#consDetailGrid").datagrid('reload'); 
				},
				
				
			},
			
		}); 
	</script>
</body>
</html>