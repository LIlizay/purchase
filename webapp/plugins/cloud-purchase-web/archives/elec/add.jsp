<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>结算管理-历史用电信息新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
 <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>

<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
</style>
</head>
<body>
	<div id="elecAddVue" class="height-fill" v-cloak>
		<mk-top-bottom height="100%" top_height="auto">
			<mk-search-panel title="用户选择" slot="top" deployable="false">
				<!-- 客户名称 -->
				<su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
		                :class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right" required_icon="true">
		            <mk-trigger-text  :data.sync="consInfo.consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text>
		        </su-form-group>
				
				<!-- 用电类别 -->
		        <su-form-group :label-name="formFields.elecTypeCodeName.label" class="form-control-row "  col="4" 
		        		:class="{'display-none':!formFields.elecTypeCode.searchHidden}" label-width="100px" label-align="right">
		            <su-select placeholder="--请选择--" label-name="name" :select-value.sync="consInfo.elecTypeCode"
									url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" disabled="true"></su-select>
		        </su-form-group> 
		        
				<!-- 电压等级 -->
		        <su-form-group :label-name="formFields.voltCodeName.label" class="form-control-row" col="4" 
		        		:class="{'display-none':!formFields.industryType.searchHidden}" label-width="100px" label-align="right">
		        	<su-select disabled="true" placeholder="--请选择--" label-name="name" :select-value.sync="consInfo.voltCode"
										url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" ></su-select>	
		        </su-form-group>
		        
				<!-- 年份 -->
		        <su-form-group :label-name="formFields.year.label" class="form-control-row "  col="4" 
		        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right" required_icon="true">
		            <su-datepicker :max.sync="maxYear" :min.sync="minYear" format="YYYY"  :data.sync="params.year" ></su-datepicker>
		        </su-form-group>
	    	</mk-search-panel>
	    
			<mk-panel title="月份用电信息列表" slot="bottom" height="520px" line="true" >
			    <div id="monthGrid" class="col-xs-12" ></div>
			</mk-panel>	
		</mk-top-bottom>
	</div>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var elecAddVue=new Vue({
			el: '#elecAddVue',
			data: {	
				formFields:{},
				fileName:"",
				consInfo:null,
				data:[{month:"1月"},{month:"2月"},{month:"3月"},{month:"4月"},{month:"5月"},{month:"6月"},{month:"7月"},{month:"8月"},{month:"9月"},
							{month:"10月"},{month:"11月"},{month:"12月"},{month:'总计'}],
			    params:{
					year:"",
					consId:'',
					scConsElectricitieList:[],
				},
			    //验证规则
				dataOption: {
					rules: {
						year: "required"
					}
				},
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
			    ComponentUtil.getFormFields("selling.archive.elec", this);
				MainFrameUtil.setDialogButtons(this.getButtons(),"add");
				me.initGrid();
				
			},
			methods: {
				initGrid:function(){
					$("#monthGrid").datagrid({
						data:this.data,
						width:"100%",
						striped:true,
						fitColumns:true,
						singleSelect:true,
						rownumbers:true,
						onLoadSuccess:function(data){
 							var monthPqTotal = 0.000;
 							var monthAmtTotal = 0.00;
 							var monthAvgPrcTotal = 0.00;
 							for(var i =0;i<12;i++){
 								var practicalPq = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='practicalPq']").eq(i).children().text();
 								var totalAmt = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='totalAmt']").eq(i).children().text();
//  								var monthAvgPrc = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='monthAvgPrc']").eq(i).children().text();
 								//alert(practicalPq)
 								if(practicalPq!=null&&practicalPq!=''){
 									monthPqTotal += parseFloat(practicalPq);
 								}
 								if(totalAmt!=null&&totalAmt!=''){
 									monthAmtTotal += parseFloat(totalAmt);
 								}
 								
 							}
 							monthPqTotal = parseFloat(monthPqTotal).toFixed(3);
 							monthAmtTotal = parseFloat(monthAmtTotal).toFixed(2);
 							//计算平均电度电价
 							if(monthPqTotal != null && monthPqTotal != '' && monthPqTotal != 0 && monthAmtTotal != null && monthAmtTotal != '' && monthAmtTotal != 0){
								monthAvgPrcTotal = monthAmtTotal / monthPqTotal;
							}
 							monthAvgPrcTotal = parseFloat(monthAvgPrcTotal).toFixed(2);
 							$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='practicalPq']").last().children().text(monthPqTotal);
 							$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='totalAmt']").last().children().text(monthAmtTotal);
 							$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='monthAvgPrc']").last().children().text(monthAvgPrcTotal);
							
							for(var i = 0; i<12; i++){
								$('#monthGrid').datagrid('beginEdit',i);
							}
							$("#monthGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
								$(object).bind("blur",function(){
									var field = $(this).parents("td[field]").first().attr("field");
									var field1 = ["peakPq","plainPq","valleyPq","overPeakPq","doublePq"];
									var field2 = ["peakAmt","plainAmt","valleyAmt","otherAmt","overPeakAmt","doubleAmt","baseAmt","forceAmt","levyAmt"];
									var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
									var i = field1.indexOf(field,0);
									var rowSum = 0;
									var field3 = [];
									var sumField = '';
									if(i != -1){
										field3 = field1;
										sumField = 'practicalPq';
									}else{
										field3 = field2;
										sumField = 'totalAmt';
									}
									for(var a = 0; a< field3.length; a++){
										editor = $("#monthGrid").datagrid("getEditor",{index:rowIndex,field:field3[a]});
										var value = editor.actions.getValue(editor.target);
										if(value!=null&&value!=''){
											rowSum += parseFloat(value);
										}
									}
									//每行电费保留两位小数
									if(sumField == 'totalAmt'){
										rowSum = parseFloat(rowSum).toFixed(2);
									} else{
										rowSum = parseFloat(rowSum).toFixed(3);
									}
									$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='"+sumField+"']").eq(rowIndex).children().text(rowSum);
									$("#monthGrid").datagrid("getRows")[rowIndex][sumField] = rowSum;
									
									//获取总电量列
									var practicalPq = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='practicalPq']").eq(rowIndex).children().text();
									//获取总电费列
 								    var totalAmt = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='totalAmt']").eq(rowIndex).children().text();
									//计算平均电价
									var monthAvgPrc = 0.00;
	 								if(practicalPq != null && practicalPq != '' && practicalPq != 0 && totalAmt != null && totalAmt != '' && totalAmt != 0){
	 									monthAvgPrc = totalAmt / practicalPq;
	 								}
	 								monthAvgPrc = parseFloat(monthAvgPrc).toFixed(2);
									//赋值到平均电价列 
									$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='monthAvgPrc']").eq(rowIndex).children().text(monthAvgPrc==0.00?"":monthAvgPrc);
									
									var sumFieldTotal = 0;
									for(var i =0;i<12;i++){
										var value = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='"+sumField+"']").eq(i).children().text();
										if(value!=null&&value!=''){
											sumFieldTotal += parseFloat(value);
										}
									}
									//合计行 电费保留两位小数
									if(sumField == 'totalAmt'){
										sumFieldTotal = parseFloat(sumFieldTotal).toFixed(2);										
									}else{
										sumFieldTotal = parseFloat(sumFieldTotal).toFixed(3);
									}
									$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='"+sumField+"']").last().children().text(sumFieldTotal);

									//$('#monthGrid').datagrid('refreshRow', rowIndex);
									var sum = 0;
									for(var i =0;i<12;i++){
										editor = $("#monthGrid").datagrid("getEditor",{index:i,field:field});
										var value = editor.actions.getValue(editor.target);
										if(value!=null&&value!=''){
											sum += parseFloat(value);
										}
									}
									//电费保留两位小数
									if(sumField == 'totalAmt'){
										sum = parseFloat(sum).toFixed(2);										
									}else{
										sum = parseFloat(sum).toFixed(3);
									}
									
									$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field="+field+"]").last().children().text(sum);

									//获取总电量列
									var monthSumPq = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='practicalPq']").last().children().text();
									//获取总电费列
 								    var monthSumAmt = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='totalAmt']").last().children().text();
									//计算平均电价
									var monthSumAvgPrc = 0.00;
	 								if(monthSumPq != null && monthSumPq != '' && monthSumPq != 0 && monthSumAmt != null && monthSumAmt != '' && monthSumAmt != 0){
	 									monthSumAvgPrc = monthSumAmt / monthSumPq;
	 								}
	 								monthSumAvgPrc = parseFloat(monthSumAvgPrc).toFixed(2);
									//赋值到平均电价列 
									$("#monthGrid").datagrid("getPanel").find(".datagrid-row [field='monthAvgPrc']").last().children().text(monthSumAvgPrc==0.00?"":monthSumAvgPrc);
									
								})
							});
						},
						frozenColumns:[[{field:'month',title:'月份',width:'5%',align:'center'}]],
						columns:[[
								{field:'id',hidden:true},
								{field:'ym',hidden:true},
								{field:'consId',hidden:true},
								{field:'practicalPq',title:'月度总用电量<br>（兆瓦时）',width:'10%',align:'center'},
 								{field:'peakPq',title:'峰时电量<br>（兆瓦时）',width:'11%',align:'center',
									editor:{
										id:"peakPq",
										type:'numberbox',
										options:{
											precision:4,
										}
									}
								},
								{field:'plainPq',title:'平时电量<br>（兆瓦时）',width:'11%',align:'center',
									editor:{
										id:"plainPq",
										type:'numberbox',
										options:{
											precision:4,
										}
									}
								},
 								{field:'valleyPq',title:'谷时电量<br>（兆瓦时）',width:'11%',align:'center',
									editor:{
										id:"valleyPq",
										type:'numberbox',
										options:{
											precision:4,
										}
									}
								},
								{field:'overPeakPq',title:'尖峰电量<br>（兆瓦时）',width:'11%',align:'center',
									editor:{
										id:"overPeakPq",
										type:'numberbox',
										options:{
											precision:4,
										}
									}
								},
								{field:'doublePq',title:'双蓄电量<br>（兆瓦时）',width:'11%',align:'left',
									editor:{
										id:"doublePq",
										type:'numberbox',
										options:{
											precision:4,
										}
									}
								},
								{field:'totalAmt',title:'月度总电费<br>（元）',width:'10%',align:'center'},
								{field:'monthAvgPrc',title:'平均电度电价<br>（元/兆瓦时）',width:'10%',align:'center'},
								{field:'peakAmt',title:'峰时电费<br>（元）',width:'11%',align:'center',
									editor:{
										id:"peakAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'plainAmt',title:'平时电费<br>（元）',width:'11%',align:'center',
									editor:{  
										id:"plainAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'valleyAmt',title:'谷时电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"valleyAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'overPeakAmt',title:'尖峰电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"overPeakAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'doubleAmt',title:'双蓄电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"doubleAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'baseAmt',title:'基本电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"baseAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'forceAmt',title:'力调电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"forceAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'levyAmt',title:'代征电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"levyAmt",
										type:'numberbox',
										options:{
											precision:2,
										}
									}
								},
								{field:'otherAmt',title:'其他电费<br>（元）',width:"11%",align:'center',
									editor:{  
										id:"otherAmt",
										type:'numberbox',
										options:{
											precision:2,
											//required:true,
										}
									}
								}
						]]
					});
					

				},
				getButtons:function(){
					var buttons = [{text:"保存",type:"primary",handler:this.saveCertInfo},{text:"取消",handler:function(){MainFrameUtil.closeDialog("add")}}];
					return buttons;
				},
				saveCertInfo:function(){
					var me = this;
					if(me.params.consId == ""){
						MainFrameUtil.alert({title:"提示",body:"请选择用户！"});
		        		return;
					}
		        	if(me.params.year == ""){
		        		MainFrameUtil.alert({title:"提示",body:"请填写年份！"});
		        		return;
		        	}
					var pq = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field=practicalPq]").last().children().text();
					var amt = $("#monthGrid").datagrid("getPanel").find(".datagrid-row [field=totalAmt]").last().children().text();
					if(pq == 0 && amt == 0){
						MainFrameUtil.alert({title:"提示",body:"请添加至少一条记录"});
						return;
					}
					var rows = $('#monthGrid').datagrid('getRows');
					for(var b = 0; b<12; b++){
						$('#monthGrid').datagrid('endEdit',b);
					}
					var month = '';
					var intj = 0;
					for(var j = 0; j<rows.length; j ++){
						if(j<9){
							intj = parseInt(j)+1;
							month = '0'+intj;
						}else{
							intj = parseInt(j)+1;
							month = intj;
						}
						rows[j].ym = me.params.year+month+'';
						rows[j].consId = me.params.consId;
					}
					me.params.scConsElectricitieList = rows;
					me.params.scConsElectricitieList.pop();
					
					if(me.params.scConsElectricitieList[0].id == null || me.params.scConsElectricitieList[0].id == ''){
						me.save();
					}else{
						me.update();
					}
					
					
				},
				save:function(){ 
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/scConsElectricityController/saveList",
						type : 'post',
						data:$.toJSON(this.params),
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data){
								MainFrameUtil.alert({title:"提示",body:"保存成功！"});
 								MainFrameUtil.closeDialog("add");
							}else{
								MainFrameUtil.alert({title:"提示",body:"保存失败！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"保存失败！"});
						}
					});
				},
				selectCons:function(){
		 			var me = this;
		 			MainFrameUtil.openDialog({
			  			id:'consDialog',
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
						iframe:true,
						modal:true,
						width:'50%',
						height:620,
						onClose:function(params){
							if(typeof(params[0])==="object"){
								me.consInfo = params[0];
								me.params.consId = params[0].id;
							}
						}
					});
		 		},
				update:function(){ 
					$.ajax({
						url : "${Config.baseURL}cloud-purchase-web/scConsElectricityController/updateList",
						type : 'post',
						data:$.toJSON(this.params),
						contentType : 'application/json;charset=UTF-8',
						success : function(data) {
							if(data){
								MainFrameUtil.alert({title:"提示",body:"保存成功！"});
								MainFrameUtil.closeDialog("add");
							}else{
								MainFrameUtil.alert({title:"提示",body:"保存失败！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"保存失败！"});
						}
					});
				}
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
								$('#monthGrid').datagrid('loadData',rows);
							}
						}
					});
				},
				'params.consId': function(){
					if(this.params.year != null && this.params.year !=''){
						$.ajax({
							url : "${Config.baseURL}cloud-purchase-web/scConsElectricityController/getMonthList",
							type : 'post',
							data:$.toJSON(this.params),
							contentType : 'application/json;charset=UTF-8',
							success:function(data){
								if(data.flag == true && data.rows != null && data.rows.length > 0){
									var rows = data.rows;
									$('#monthGrid').datagrid('loadData',rows);
								}
							}
						});
					}
				}
			}
		});
	</script>
</body>
</html>