<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>晋能</title>
	</head>
	<body>
	
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<div id="elecMonthVue" v-cloak class="height-fill">
	<mk-top-bottom height="525px" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电信息">
			<mk-search-visible> 
				<!-- 年份 -->
				<su-form-group label-name='年份'  class="form-control-row "  col="4" label-align="right">
					<su-datepicker disabled="disabled" format="YYYY"  :data.sync="params.year" ></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="月份列表" line="true" slot="bottom" height="100%">
		    <div id="elecMonthGrid" class="col-xs-12"  style="width:100%;height:100%"></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var elecMonthVue = new Vue({
			el: '#elecMonthVue',
			data:{
				params:{
					year:'',
					consId:''
				}
			},
			ready:function(){
				this.params = MainFrameUtil.getParams("elecmonth-detail");
				//列表数据加载
				this.initGrid();
			},
			methods: {
				reset:function(){
					this.params.year = '';
				},
				initGrid:function(){
					//列表数据加载
					ComponentUtil.buildGrid("selling.archives.elecmonthinfo",$("#elecMonthGrid"),{
						url:basePath + 'cloud-purchase-web/scConsumerInfoController/elecMonth',
						queryParams:this.params,
					    width:"100%",
					    height:"100%",
					    method: 'post',
					    rownumbers: true,
					    //pagination: true,
					    singleSelect:true,
					    nowrap: false,
					    fitColumns:true,
					    rowStyler:function(idx,row){
					        return "height:35px;font-size:12px;";
					    }
				    });
					
				},
				getDataGrid:function(){
				    $("#elecMonthGrid").datagrid('reload'); 
				}
			}
		}); 
	</script>
</div>
</body>
</html>