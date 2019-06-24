<html>
	<head>
		<title>档案管理-用户档案信息历史用电信息</title>
	</head>
	<body>
	
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="elecVue" v-cloak class="height-fill">
	<mk-top-bottom height="525px" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电用户信息管理">
			<mk-search-visible> 
				<!-- 年份 -->
				<su-form-group label-name='年份'  class="form-control-row "  col="4" label-align="right">
					<su-datepicker format="YYYY"  :data.sync="params.year" ></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="历史用电信息" line="true" slot="bottom" height="100%">
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
					consId:'',
					years:[]
				}
			},
			ready:function(){
				this.params.consId = MainFrameUtil.getParams("cons-detail").consId;
				//列表数据加载
				this.initGrid();
			},
			methods: {
				initGrid:function(){
					$("#elecGrid").datagrid({
						url:"${Config.baseURL}cloud-purchase-web/scConsElectricityController/getYearList",
						method : 'post',
						queryParams:this.params,
						contentType : 'application/json;charset=UTF-8',
						width:"100%",
						striped:true,
						fitColumns:true,
						//singleSelect:true,
						rownumbers:true,
						idField:'id',
						columns:[[
								{field:'ck',checkbox:true},
 								{field:'year',title:'年份',width:'5%',align:'center',
									formatter: function(value,row,index){
								       return '<a href="#" onclick="elecVue.yearDetail(\'' + value + '\')">'+value+'</a>' 
									}
								},
 								{field:'yearPq',title:'年度总用电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearPeakPq',title:'峰时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearPlainPq',title:'平时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearValleyPq',title:'谷时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearOverPeakPq',title:'尖峰电量<br>（兆瓦时）',width:'11%',align:'center'},
								{field:'yearDoublePq',title:'双蓄电量<br>（兆瓦时）',width:'11%',align:'center'},
								{field:'yearAmt',title:'年度总电费<br>（元）',width:"12%",align:'center'},
								{field:'avgPrc',title:'平均电度电价<br>（元/兆瓦时）',width:"13%",align:'center',
									formatter: function(value,row,index){
										if(row.yearAmt != 0 && row.yearAmt != '' && row.yearPq != 0 && row.yearPq != '' ){
											return (row.yearAmt / row.yearPq).toFixed(2);
										}else{
											return value;
										}
									}
								}
						]]
					});
				},
				getDataGrid:function(){
				    $("#elecGrid").datagrid('reload'); 
				},
				yearDetail:function(year){
					MainFrameUtil.openDialog({
			  			id:"year-detail",
			  			params:{consId:this.params.consId,year:year},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elec-year',
						iframe:true,
						modal:true,
						width:"80%",
						height:610,
						buttons:[{text:"关闭",type:'default',handler:function(btn,params){
			            	MainFrameUtil.closeDialog("year-detail");
			            }}]
					});
				}
			}
		}); 
	</script>
</div>
</body>
</html>