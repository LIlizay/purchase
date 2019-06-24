<html>
	<head>
	<title>暂弃档案管理-用户档案信息结算信息</title>
	</head>
	<body>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="elecAddVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-form-panel title="历史用电信息新增" label_width="140px">
			<mk-form-row>
				<!-- 年份 -->
				<mk-form-col label="年份">
					<su-datepicker format="YYYY"  :data.sync="params.year" ></su-datepicker>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-panel title="月份用电信息列表" line="true" >
		    <div id="monthGrid" class="col-xs-12" ></div>
		</mk-panel>	
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var elecAddVue=new Vue({
			el: '#elecAddVue',
			data: {	
				flag:false,
				formFields:{},
				fileName:"",
				data:[{month:"1月"},{month:"2月"},{month:"3月"},{month:"4月"},{month:"5月"},{month:"6月"},{month:"7月"},{month:"8月"},{month:"9月"},
							{month:"10月"},{month:"11月"},{month:"12月"},{month:'总计'}],
			    params:{
					year:"",
					consId:''
				},
			    //验证规则
				dataOption: {
					rules: {
						year: "required"
					}
				},
				valid: false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
				// ComponentUtil.getFormFields("selling.archives.sccertinfo",this);
				me.params.consId = MainFrameUtil.getParams("cons-detail").consId;
				me.initGrid();
				me.getYear();
				
			},
			methods: {
				getYear:function(){
					var me = this;
					var date = new Date();
					me.params.year = date.getFullYear();
				},
				
				initGrid:function(){
					$("#monthGrid").datagrid({
						data:this.data,
						width:"100%",
						striped:true,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						onLoadSuccess:function(data){
						},
						columns:[[
								{field:'id',hidden:true},
								{field:'ym',hidden:true},
								{field:'consId',hidden:true},
								{field:'month',title:'月份',width:'5%',align:'center'},
								{field:'monthPq',title:'月度总用电量<br>（兆瓦时）',width:'9%',align:'left'},
 								{field:'peakPq',title:'峰时电量<br>（兆瓦时）',width:'8%',align:'left'},
								{field:'plainPq',title:'平时电量<br>（兆瓦时）',width:'8%',align:'left'},
 								{field:'valleyPq',title:'谷时电量<br>（兆瓦时）',width:'8%',align:'left'},
								{field:'monthAmt',title:'月度总电费<br>（元）',width:'8%',align:'left'},
								{field:'peakAmt',title:'峰时电费<br>（元）',width:'7%',align:'left'},
								{field:'plainAmt',title:'平时电费<br>（元）',width:'7%',align:'left'},
								{field:'valleyAmt',title:'谷时电费<br>（元）',width:"7%",align:'left'},
								{field:'otherAmt',title:'其他电费<br>（元）',width:"7%",align:'left'},
								{field:'consElecProfit',title:'用户购电利润<br>（元）',width:"9%",align:'left'},
								{field:'consCheckAwt',title:'用户偏差考核电费<br>（元）',width:"12%",align:'left'},
								{field:'consProfit',title:'用户返利<br>（元）',width:"7%",align:'left'}
								
						]]
					});
					

				},
			},
			watch:{
				'params.year':function(value){
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/scConsElectricityController/getMonthList",
						type : 'post',
						data:$.toJSON(this.params),
						contentType : 'application/json;charset=UTF-8',
						success:function(data){
							if(data.flag == true && data.rows != null && data.rows.length > 0){
								var rows = data.rows;
								var rows1 = $('#monthGrid').datagrid('getRows');
								for(var i in rows){
									for(var key in rows[i]){
										rows1[i][key] = rows[i][key];
									}
									$('#monthGrid').datagrid('refreshRow', i);
								}
							}
						}
					});
					$.ajax({
						url : basePath + 'cloud-purchase-web/scConsumerInfoController/consProfit',
						type : "post",
						data : $.toJSON(this.params),
						contentType : 'application/json;charset=UTF-8',
						success:function(data1){
							if(data1 != null && data1.length > 0){
								var rows = $('#monthGrid').datagrid('getRows');
								for(var i in data1){
									rows[data1[i].inx].consElecProfit = data1[i].consElecProfit;
									rows[data1[i].inx].consCheckAwt = data1[i].consCheckAwt;
									rows[data1[i].inx].consProfit = data1[i].consProfit;
									$('#monthGrid').datagrid('refreshRow', data1[i].inx);
								}
							}
						}
					});
				}
			}
		});
	</script>
</div>

	</body>
</html>