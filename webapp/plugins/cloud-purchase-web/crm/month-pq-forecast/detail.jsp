<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>购售电交易-售电用户负荷预测详情</title>
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
	            <su-datepicker :max.sync="maxYear" :min.sync="minYear" format="YYYY-MM"  :data.sync="params.ym" disabled='true'></su-datepicker>
	        </su-form-group>
	    </mk-search-panel>
	    
	    <mk-panel title="月度电量预测结果（单位：兆瓦时）" line="true" slot="bottom" height="100%">
	    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
<!-- 		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="line-chart" v-on:click="calculatePq" disabled='true'>大数据测算</su-button> -->
		  	 	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button>
		    </div>
	        <div id="eleAnalysisAddGrid" class="col-xs-12" style="height:90%"></div>
	    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var eleAnalysisAddVue = new Vue({
	el: '#eleAnalysisAddVue',
	data: {
		params: {
			ym: null
		}
	},
	ready:function(){
		var me = this;
	    var ym = MainFrameUtil.getParams().ym;
		me.params.ym = ym.substring(0,4) + '-' + ym.substring(4,6)    	
	    me.ineleAnalysisAddGrid();
	    me.initTitle();
	    //初始化按钮
		MainFrameUtil.setDialogButtons([
		    {text:"关闭", handler:function(){MainFrameUtil.closeDialog()}}
		]);
	},
	methods: {
		//生成列表
		ineleAnalysisAddGrid: function(){
			var me = this;
			$('#eleAnalysisAddGrid').datagrid({
			  	url: basePath+'scElectricityInfoController/getMonthPqForetData', 
		        queryParams: this.params,
				method: 'POST',
	    		width:"100%",
		        fitColumns:true,
			    singleSelect:true,
			    nowrap: false, 
			    pagination: true,
			    checkOnSelect:true,
			    onLoadSuccess:function(data){
			    	if(data.rows.length > 1){
			    		//合并单元格
				    	var rows = data.rows;
						$('#eleAnalysisAddGrid').datagrid('mergeCells',{index: data.rows.length-1,field:"consName",rowspan:0,colspan:2});
			    	}
			    },
			    columns:[[
					{field:'consId',hidden:true}, 
			        {field:'consName',title:'用户名称',width:210,align:'center'},    
			        {field:'voltCodeName',title:"电压等级",width:90,align:'center'},
			        {field:'upYearPracticalPq',title:"去年同期值",width:130,align:'center'},
			        {field:'upMonPracticalPq',title:"上期值",width:130,align:'center'},
			        {field:'dataForecastPq',title:"大数据预测电量",width:130,align:'center'},
			        {field:'personForecastPq',title:"人工核定电量",width:130,align:'center',
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			        {field:'practicalPq',title:"实际电量",width:130,align:'center'},
			        {field:'remark',title:"备注",width:120,align:'center',
			        	editor:{
	                    	type:'text',
	                	},
	                	styler: function(value,row,index){
							return 'color:black;';
						}
			        },
			    ]],
			});
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
		
	}
}); 
</script>
</body>
</html>