<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-售电用户负荷预测新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<%-- <script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts1.js"></script> --%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
</head>
<body  >
<div id="eleAnalysisAddVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="" slot="top" deployable="false">
		<!-- 年份 -->
        <su-form-group label-name="年月" class="form-control-row "  col="4" 
        		:class="{'display-none':!formFields.year.searchHidden}" label-width="100px" label-align="right">
            <su-datepicker :max.sync="maxYear" :min.sync="minYear" format="YYYY-MM"  :data.sync="params.ym" ></su-datepicker>
        </su-form-group>
    </mk-search-panel>
    
    <mk-panel title="月度电量预测结果（单位：兆瓦时）" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="line-chart" v-on:click="calculatePq">大数据测算</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button>
	    </div>
        <div id="eleAnalysisAddGrid" class="col-xs-12" style="height:90%"></div>
    </mk-panel>
</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var bigdataUrl = basePath.replace(/\/[a-zA-Z0-9]*\/$/,"/ibd/");

var eleAnalysisAddVue = new Vue({
	el: '#eleAnalysisAddVue',
	data: {
		//存初始时编辑格数据 用于翻页校验
		initRowsList : [],
		pageNumber: null,
		initFlag: true,
		params:{
			ym: null,
		}
	},
	ready:function(){
		var me = this;
		//初始表格
	    me.ineleAnalysisAddGrid();
	    //初始查询年月
	    me.initYear();
	    //初始化按钮
		MainFrameUtil.setDialogButtons([
		    {text:"保存", type:"primary", handler:this.saveData},
		    {text:"取消", handler:function(){MainFrameUtil.closeDialog()}}
		]);
	},
	methods: {
		initYear: function(){
			var me = this;
			var date = new Date;
			//年
			var year = date.getFullYear();
			//月
			var selectMonth = date.getMonth() + 1;
			//下月
			var downMonth = date.getMonth() + 2;
			if(downMonth > 12){
				downMonth = '01';
				year = year + 1;
			}
			//下个月日期 yyyyMM
			var upMonthName = year + '' +  downMonth;
			$.ajax({
				url : basePath+"scElectricityInfoController/checkNextMonthData",
				type : 'post',
				data:$.toJSON({ym: upMonthName}),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data){
						if(!data.data){ //下月无数据
							me.params.ym = year + '-' +  downMonth;
						}
					 	
					}else{
						MainFrameUtil.alert({title:"提示",body:"初始年月失败！"});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"初始年月失败！"});
				}
			});
		},
		//生成列表
		ineleAnalysisAddGrid: function(){
			var me = this;
			var me = this;
			$('#eleAnalysisAddGrid').datagrid({
// 			  	url: basePath+'scElectricityInfoController/getMonthPqForetData', 
// 		        queryParams: this.params,
				method: 'POST',
	    		width:"100%",
	    		 pageSize: 10,
	 		    pageList: [10, 20, 50, 100, 150, 200],
		        fitColumns:true,
			    singleSelect:true,
			    nowrap: false, 
			    pagination: true,
			    checkOnSelect:true,
			    columns:[[
					{field:'consId',hidden:true}, 
			        {field:'consName',title:'用户名称',width:210,align:'center'},    
			        {field:'voltCodeName',title:"电压等级",width:90,align:'center'},
			        {field:'upYearPracticalPq',title:"去年同期值",width:130,align:'center'},
			        {field:'upMonPracticalPq',title:"上期值",width:130,align:'center'},
			        {field:'dataForecastPq',title:"大数据预测电量",width:130,align:'center'},
			        {field:'personForecastPq',title:"人工核定电量",width:130,align:'center',
			        	editor:{
	                    	type:'numberbox',
	                    	options:{
	                        	precision:3,
	                        	onChange: function(newValue,oldValue){
	                        		if(!me.initFlag){
	                        			//当前单元格index
		    			    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
		    			    			newValue = newValue == "" ? 0 : parseFloat(newValue);
		    			    			oldValue = oldValue == "" ? 0 : parseFloat(oldValue);
		                        		var oldTotalValue = $("#eleAnalysisAddGrid").datagrid("getPanel").find(".datagrid-row [field='personForecastPq']").last().children().text();
		                        		oldTotalValue = oldTotalValue == "" ? 0 : parseFloat(oldTotalValue);
		                        		var value = oldTotalValue - oldValue + newValue;
		    			    		  	//赋值到合计
		     							$("#eleAnalysisAddGrid").datagrid("getPanel").find(".datagrid-row [field='personForecastPq']").last().children().text(value.toFixed(3));
									
	                        		}
	                        	}
	                        }
	                	},
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			        {field:'practicalPq',title:"实际电量",width:150,align:'center'},
			        {field:'remark',title:"备注",width:120,align:'center',
			        	editor:{
	                    	type:'textbox',
	                	},
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			    ]],
			    onLoadSuccess:function(data){
			    	//获取列表页码
                	me.pageNumber = $("#eleAnalysisAddGrid").datagrid("getPager").data("pagination").options.pageNumber;
			    	
			    	if(data.rows.length > 1){
			    		//合并单元格
				    	var rows = data.rows;
						$('#eleAnalysisAddGrid').datagrid('mergeCells',{index: data.rows.length-1,field:"consName",rowspan:0,colspan:2});
			    	}
			    	me.initRowsList = [];
			    	for(var i = 0; i < data.rows.length-1; i++){
						$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
						//获取编辑格数据
	       				me.initRowsList.push({'personForecastPq':data.rows[i].personForecastPq, 'remark': data.rows[i].remark});
					}
			    	//表加载完成标识
			    	me.initFlag = false;
			    },
			    onBeforeLoad : function(data){
			    	if(me.initFlag==true){ //初始化表格时renturn
			    		return true;
			    	}
			    	me.initFlag = true;
		    		var flag = false;
		    		var rows = $("#eleAnalysisAddGrid").datagrid("getRows");
		    		var newRowsList = [];//新编辑格数据
		    		for(var i = 0; i < rows.length-1; i++){
		    			var ed = $('#eleAnalysisAddGrid').datagrid('getEditor', {index:i,field:'personForecastPq'});
			    		var meterReadPq = $(ed.target).numberbox('getValue');
			    		
			    		var ed1 = $('#eleAnalysisAddGrid').datagrid('getEditor', {index:i,field:'remark'});
			    		var meterReadPq1 = $(ed1.target).textbox('getValue');
			    		newRowsList.push({'personForecastPq':meterReadPq, 'remark': meterReadPq1})
					}
		    		//比对数据是否是否变更
		    		for(var i = 0; i < rows.length-1; i++){
		    			var personForecastPq = me.initRowsList[i].personForecastPq == null || me.initRowsList[i].personForecastPq == '' ? '0': me.initRowsList[i].personForecastPq;
		    			var newPersonForecastPq = newRowsList[i].personForecastPq == null || newRowsList[i].personForecastPq == '' ? '0' : newRowsList[i].personForecastPq;
	    				if( parseFloat(personForecastPq) != parseFloat(newPersonForecastPq) ){
	    					 flag=true;
    						 break;
	    				}
	    				
	    				var remark = me.initRowsList[i].remark == null || me.initRowsList[i].remark == '' ? '' : me.initRowsList[i].remark;
	    				var newRemark = newRowsList[i].remark == null || newRowsList[i].remark == '' ? '' : newRowsList[i].remark;
	    				if(remark != newRemark){
	    					 flag=true;
    						 break;
		    			}
	    			 }
		    		if(flag){
			    		MainFrameUtil.confirm({
			    			title:"警告",
					        body:"页面变更会导致当前页面修改数据丢失，请确认是否保存",
					        buttons:[{text:'保存',type:"ok",style:"primary"},{text:'取消',type:"cancel",style:"default"}],
			    	        onClose:function(type){
			    	            if(type=="ok"){//确定
			    	            	me.loadData(data.page,data.rows,true);
			    	            }else if(type=="cancel"){//取消
			    	            	me.loadData(me.pageNumber,data.rows,false);
			    	            }
			    	        }
			    	    });
			    		return false;
			    	}else{
			    		return true;
			    	}
			    },
			  
			});
		},
		loadData: function(page,rows,flag){
			var me = this;
			//翻页 数据不比对
			me.initFlag=true;
			if(flag){
				me.saveData(false);
			}
			//刷新表格
			$("#eleAnalysisAddGrid").datagrid("reload");
	    },
		initTitle: function(){
			var me = this;
			var date = new Date;
			if(me.params.ym == null || me.params.ym == ''){
				return;
			}
			//选择年
			var year = me.params.ym.substring(0,4);
			//去年
			var upYear = year - 1;
			//选择月
			var selectMonth = parseInt( me.params.ym.substring(5,7));
			//下月
			var downMonth = parseInt( me.params.ym.substring(5,7)) + 1;
			if(downMonth > 12){
				downMonth = 1;
			}
			//去年同期
			var upYearName = upYear + "年" + selectMonth + "月电量";
			//上期值 上个月
			var a = selectMonth-1 < 1 ? upYear:year;
			var b = selectMonth-1 < 1 ? 12 :selectMonth-1;
			var upMonthName =  a + "年" + b + "月电量";
			
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'upYearPracticalPq').title = upYearName;
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'upMonPracticalPq').title = upMonthName;
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'dataForecastPq').title = year + '年' + selectMonth + "月</br>大数据预测电量";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'personForecastPq').title = year + '年' + selectMonth + "月</br>人工核定电量";
			$('#eleAnalysisAddGrid').datagrid('getColumnOption', 'practicalPq').title = year + '年' + selectMonth +"月</br>实际电量";
			$('#eleAnalysisAddGrid').datagrid();
		},
		saveData: function(saveButtonFlag){
			var me = this;
			var rows  = $("#eleAnalysisAddGrid").datagrid("getRows");
			for(var i=0; i<rows.length-1; i++){
				$('#eleAnalysisAddGrid').datagrid('endEdit', i);
			}
			 rows  = $("#eleAnalysisAddGrid").datagrid("getRows");
			if(rows.length < 1){
				MainFrameUtil.alert({title:"提示",body:"没有需要保存数据！"});
				return;
			}
			rows.pop();//去掉合计一行
			//同步方法，因为：loadData 方法中需要先保存后刷新表格
			$.ajax({
				url : basePath+"scElectricityInfoController/saveMonPqForeList",
				type : 'post',
				async : false,
				data:$.toJSON({"scConsElectricitieList": rows}),
				contentType : 'application/json;charset=UTF-8',
				success : function(data) {
					if(data){
						MainFrameUtil.alert({title:"提示",body:"保存成功！"});
						me.initFlag = true;  //初始标识，防止走onbeforLoad方法
						if(saveButtonFlag !== false){
							//刷新表格
							$("#eleAnalysisAddGrid").datagrid("reload");
						}
						for(var i=0; i<rows.length; i++){
							$('#eleAnalysisAddGrid').datagrid('beginEdit', i);
						}
					}else{
						MainFrameUtil.alert({title:"提示",body:"保存失败！"});
					}
				},
				error : function() {
					MainFrameUtil.alert({title:"提示",body:"保存失败！"});
				}
			});
		},
		//预测按钮
		calculatePq:function(){
			var me = this;
			var rows  = $("#eleAnalysisAddGrid").datagrid("getRows");
			if(rows.length < 1){
				MainFrameUtil.alert({title:"提示",body:"无需要预测用户！"});
				return;
			}
			//预测数据 页面显示
			for(var i=0; i<rows.length; i++){
				if(i != rows.length-1){
					//赋值到平均电价列 
					$("#eleAnalysisAddGrid").datagrid("getPanel").find(".datagrid-row [field='dataForecastPq']").eq(i).children().text(1.1);
				}else{
					//最后一行 合计
				}
			}
// 			$.ajax({
// 				url: bigdataUrl + "powerFore/LongAssAction!predictLongAssDl.action?year="+me.params.scElectricityInfoDeatil.year+"&consNo="+me.params.scElectricityInfoDeatil.consId+"&av=b7b7596fffc91a2bc016c1128df857fd",
// // 				url: "${Config.getConfig('selling.bigdata.url')}/powerFore/LongAssAction!predictLongAssDl.action?year=2018&consNo=4ddc5e74661f47ee89ecd40925bc0b97&av=b7b7596fffc91a2bc016c1128df857fd",
// 				type:"get",
// 				success:function(data){
// 					//预测数据 页面显示
// 					for(var i=0; i<rows.length; i++){
// 						if(i != rows.length-1){
// 							//赋值到平均电价列 
// 							$("#eleAnalysisAddGrid").datagrid("getPanel").find(".datagrid-row [field='dataForecastPq']").eq(i).children().text(1.1);
// 						}else{
// 							//最后一行 合计
// 						}
// 					}
// 				},
// 				error : function() {
// 					MainFrameUtil.alert({title:"提示",body:"网络异常，请刷新重试！"});
// 				}
// 			});
		},
       	queryList:function(){
       		var me = this;
       		//预测地址
       		//https://ysd.365power.cn/ibd//powerFore/LongAssAction!predictLongAssDl.action?year=2018&consNo=4ddc5e74661f47ee89ecd40925bc0b97&av=b7b7596fffc91a2bc016c1128df857fd
       				
			if(me.params.scElectricityInfoDeatil.year != null && me.params.scElectricityInfoDeatil.year != ""){
// 				me.initTitle();
				$.ajax({
					url:basePath+"scElectricityInfoController/getList",
					type:"post",
					data:$.toJSON(me.params),
	                contentType : 'application/json;charset=UTF-8',
					success:function(data){
						if(data.flag){
							$('#eleAnalysisAddGrid').datagrid('loadData', rows);
						}
					}
					
				});
			}
       	},
       	//导出
       	download:function(){
			var me = this;
			var me = this;
			var rows  = $("#eleAnalysisAddGrid").datagrid("getRows");
			if(rows.length < 1){
				MainFrameUtil.alert({title:"提示",body:"无导出数据！"});
				return;
			}
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"是否导出？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		    			var url = basePath + "scElectricityInfoController/exportExcel?ym=" + me.params.ym;
		                location.href = url;
		            }
		        }
    		});
		},
       
	},
	watch:{
		'params.ym':function(val){
			var me = this;
			if(val != null && val != ''){
				if($('#eleAnalysisAddGrid').datagrid('options').url == null || $('#eleAnalysisAddGrid').datagrid('options').url ==""){
					$('#eleAnalysisAddGrid').datagrid('options').url = basePath+'scElectricityInfoController/getMonthPqForetData';  
					$('#eleAnalysisAddGrid').datagrid('options').queryParams = this.params;  
		    	}
		    	me.initTitle();
			}
			
		}
	}
}); 
</script>
</body>
</html>