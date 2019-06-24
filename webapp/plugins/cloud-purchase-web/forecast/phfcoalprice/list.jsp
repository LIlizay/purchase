<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>参数维护-煤炭价格维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
</head>
<body id="listBody" v-cloak>
<mk-search-panel deployable="false" >
	<mk-search-visible>
		<!-- 开始年月 -->
		<su-form-group :label-name='formFields.startYm.label' col="4" label-align="right">
			 <su-datepicker format="YYYYMM" :data.sync="params.startYm" ></su-datepicker>
		</su-form-group>
		<!-- 截止年月 -->
		<su-form-group :label-name='formFields.endYm.label' col="4" label-align="right">
			 <su-datepicker format="YYYYMM" :data.sync="params.endYm" ></su-datepicker>
		</su-form-group>
		<!-- 种类 -->
		<su-form-group :label-name='formFields.coalTypeName.label' col="4" label-align="right">
			<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_coalTypeCode" multiple="false" 
				:select-value.sync="params.phfCoalPrice.coalType" label-name="name" ></su-select>
		</su-form-group>
	</mk-search-visible>
	<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
		<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
		<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
	</div>
</mk-search-panel>
<mk-panel title="煤炭价格列表" line="true" >
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
  		</div>
    <div id="listGrid" ></div>
</mk-panel>
<div id="listCharts" style="height: 500px;width:100%"></div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    params:{
	    	startYm:'',endYm:'',
	    	phfCoalPrice:{}
		},
		now:'',
		lastYear:''
	},
	ready:function(){
		var now = new Date();
		this.now  = this.getFormatterDate(now);
		now.setFullYear(now.getFullYear()-1);
		this.lastYear = this.getFormatterDate(now);
		this.reset();
		//列表数据加载
		ComponentUtil.buildGrid("purchase.forecast.coalprice",$("#listGrid"),{ 
			url:basePath + 'cloud-purchase-web/phfCoalPriceController/page',
			queryParams:this.params,
		    height:320,
		    method: 'post',
		    idField:"id",
		    rownumbers: true,
		    pagination: true,
		    singleSelect:false,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.forecast.coalprice",this);
		this.getData();
	},
	methods: {
		reset:function(){
			this.params = {
				startYm:this.lastYear,endYm:this.now,
		    	phfCoalPrice:{}
			};
		},
		getDataGrid:function(){
			if(this.params.startYm==''||this.params.endYm==''){
				MainFrameUtil.alert({title:"警告",body:"请选择开始年月和截止年月！"});
				return;
			}
			if(parseInt(this.params.startYm) > parseInt(this.params.endYm)){
				MainFrameUtil.alert({title:"警告",body:"开始年月必须小于截止年月！"});
				return;
			}
		    $("#listGrid").datagrid('load',this.params); 
		    this.getData();
		},
		add:function(){
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"add",
				href:'${Config.baseURL}view/cloud-purchase-web/forecast/phfcoalprice/add',
				title:'煤炭价格新增',
				iframe:true,
				modal:true,
				width:800,
				height:500,
				buttons:[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	params.save(function(){
                    		MainFrameUtil.closeDialog('add');
                    		me.getDataGrid();
                    	});
                    }
                },{
                    text:"取消",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('add');
                    }
                }]
			});
		},
		del:function(){
			var me = this;
			var rows =  $("#listGrid").datagrid('getSelections'); 
			if(rows.length==0){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据"});
				return;
			}
			 MainFrameUtil.confirm({
		        title:"确认",
		        body:"删除后不可恢复，确认删除选中行？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	var ids = "";
		            	for(var i in rows){
		            		ids += rows[i].id+","
		            	}
		            	$.ajax({
		    	  			url:basePath+"cloud-purchase-web/phfCoalPriceController/"+ids,
		    	  			type:"delete",
		    				success:function(data){
		    					if(data.flag){
		    						MainFrameUtil.alert({title:"提示",body:data.msg});
		    						me.getDataGrid();
		    					}else{
		    						MainFrameUtil.alert({title:"错误",body:data.msg});
		    					}
		    				}
		    	  		})
		            }
		        }
		    }); 
		},
		//获取格式化日期
        getFormatterDate:function(date){
        	var expiryDate = date.getFullYear();
			var month = date.getMonth()+1;
			if(month<10){
				expiryDate+="0";
			}
			expiryDate += ("" + month);
			return expiryDate;
        },
        getData:function(){
        	var me = this;
        	$.ajax({
        		url:basePath + 'cloud-purchase-web/phfCoalPriceController/getList',
        		type:"post",
        		data:$.toJSON(this.params),
        		contentType : 'application/json;charset=UTF-8',
        		success:function(data){
        			var listData = data.data;
        			var xData = [];  //x轴数据
        			var lastYm = '';
        			var dataMap = {};
        			for(var i in listData){
        				var obj = listData[i];
        				//处理年月  早x轴
        				if(obj.ym!==lastYm){
        					lastYm = obj.ym;
        					xData.push(lastYm);
        				}
        				//处理数据
        				if(typeof(dataMap[obj.coalTypeName])==="undefined"){
        					dataMap[obj.coalTypeName] = [];
        				}
        				dataMap[obj.coalTypeName].push(obj.price);
        			}
        			me.setEchars(xData,dataMap);
        		}
        	})
        },
        setEchars:function(xAxisData,dataMap){
        	var serise = [];
        	var legendData = [];
        	for(var i in dataMap){
        		legendData.push(i);
        		serise.push({name:i,type:'line',data:dataMap[i]});
        	}
        	$("#listCharts").createEcharts({
				title:{text:"煤炭价格走势图"},
				legend: {
				 	data:legendData,
			    },
			    xAxis: {
			        data:xAxisData
			    },
				series:serise
			},"line")
        }
	}
}); 
</script>
</body>
</html>