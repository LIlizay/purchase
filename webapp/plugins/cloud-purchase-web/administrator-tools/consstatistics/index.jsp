<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-平台用户统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="../../../business-core/static/headers/echarts.jsp"%>
<style type="text/css">
	td{
		text-align: center
	}
</style>
</head>
<body id="listVue" v-cloak>
	<div  style="width: 100%;height: 96%">
		<div style="width: 100%;text-align: right;padding-right: 4%">
			<a href=""  v-on:click="detail">查看更多>></a>
		</div>
		<div style="float: left;height: 100%;width: 75%">
			<mk-panel title=""  detail="true" height="99%">
				<div style="height: 100%;width: 100%;" id="echart"></div>
			</mk-panel>
		</div>
		<div style="float: left;height: 100%;width: 25%" >
			<mk-panel title=""  detail="true" height="99%">
				<div style="height: 100%;width: 100%;overflow:auto">
					<table border="1"  width="100%">
						<tr>
						    <td width="20%">序号</td>
						    <td>省份</td>
						    <td width="20%">用户数</td>
						</tr>
						<tr v-for="data in dataList">
							<td>{{data.num}}</td>
							<td>{{data.name}}</td>
							<td>{{data.total}}</td>
						</tr>
						<tr>
							<td colspan="2">合计</td>
							<td id="totalCons"></td>
						</tr>
					</table>
					
				</div>
			</mk-panel>
		</div>
	</div>
<script type="text/javascript">
$(window).resize(throll(function(){
	 if(
				listVue.echart!=null&&
		   $("#listVue").height()>60&&
		   $("#listVue").width()>100){
			listVue.echart.resize();
		}
	},100));
function throll(fn,delay){
var timer=null;
return function(){
   clearTimeout(timer);
   timer=setTimeout(fn,delay);
}
}  

var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listVue',
	data: {
		echart: null,
		dataList: null,
		
	},
	ready:function(){
		var me = this;
		$.ajax({
			url: basePath + 'consStatisticsController/getInitData',
			type: 'get',
			success: function(data){
				me.initEchart(data.data);
			}
		})
	},
	methods: {
		initEchart: function(data){
			this.dataList=[];
			var totalConsNum = 0;
			for(var i in data){
				data[i]["num"]= parseInt(i)+1;
				totalConsNum += parseInt(data[i].total != null && data[i].total != '' ? data[i].total : 0);
				this.dataList.push(data[i]);
			}
			$("#totalCons").text(totalConsNum);
			var length = [];
			var barData = [];
			for(var i in data){
				length.push(data[i].name);
				barData.push(data[i].total);
			}
			this.echart = echarts.init(document.getElementById('echart'));
			option = {
				    tooltip: {
				        trigger: 'axis',
				        axisPointer: {
				            type: 'cross',
				            crossStyle: {
				                color: '#999'
				            }
				        }
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis: [
				        {
				            type: 'category',
				            data: length,
				            axisPointer: {
				                type: 'shadow'
				            },
				            axisLabel:{  
		                        interval:0,  
		                        rotate:40,//倾斜度 -90 至 90 默认为0  
		                    },  
				        }
				    ],
				    yAxis: [
				        {
				            type: 'value',
				            name: '用户数',
				        }
				    ],
				    series: [
				        {
				            name:'用户数',
				            type:'bar',
				            barMaxWidth:30,
				            data: barData,
				            itemStyle: {
				                 normal: {
				                    color: new echarts.graphic.LinearGradient(
				                        0, 0, 0, 1,
				                        [
				                            {offset: 0, color: '#83bff6'},
				                            {offset: 0.5, color: '#188df0'},
				                            {offset: 1, color: '#188df0'}
				                        ]
				                    )
				                },
				            },
				        }
				    ]
				};
			this.echart.setOption(option);
		},
		detail: function(){
			var me = this;
			MainFrameUtil.openDialog({
				href:'${Config.baseURL}view/cloud-purchase-web/administrator-tools/consstatistics/list',
				title:'平台用户明细',
				iframe:true,
				modal:true,
				maximizable:true,
				width:"80%",
				height:620
			});

		}
		
	}
}); 
</script>
</body>
</html>