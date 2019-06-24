<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>晋能</title>
	</head>
	<body>
	
<div id="elecVue" v-cloak class="height-fill">
	<mk-top-bottom height="525px" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电信息">
			<mk-search-visible> 
				<!-- 年份 -->
				<su-form-group label-name='年份'  class="form-control-row "  col="4" label-align="right">
					<su-datepicker format="YYYY"  :data.sync="params.year" ></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="交易列表" line="true" slot="bottom" height="100%">
		    <div id="elecGrid" class="col-xs-12"  style="width:100%;height:100%"></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var elecVue = new Vue({
			el: '#elecVue',
			data:{
				params:{
					year:'',
					consId:''
				}
			},
			ready:function(){
				this.params.consId = MainFrameUtil.getParams("cons-detail").consId;
				//列表数据加载
				this.initGrid();
			},
			methods: {
				reset:function(){
					this.params.year = '';
				},
				detailFormatter:function(value,row,text){ //elecVue.detailFormatter
					return "<a onclick=\"elecVue.detail('"+row.year+"','"+elecVue.params.consId+"')\"> 查看 </a>";
				},
				detail:function(year, consId){
			  		MainFrameUtil.openDialog({
			  			id:"elecmonth-detail",
			  			params:{year:year,consId:consId},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elecmonth-detail',
						iframe:true,
						modal:true,
						width:1000,
						height:620,
						buttons:[{
		                    text:"关闭",
		                    handler:function(btn,params){
		                    	MainFrameUtil.closeDialog('elecmonth-detail');
		                    }
	                	}]
					});
				},
				initGrid:function(){
					//列表数据加载
					ComponentUtil.buildGrid("selling.archives.elecinfo",$("#elecGrid"),{
						url:basePath + 'cloud-purchase-web/scConsumerInfoController/elecYear',
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
				    $("#elecGrid").datagrid('reload'); 
				}
			}
		}); 
	</script>
</div>
</body>
</html>