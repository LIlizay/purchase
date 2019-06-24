
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head lang="en">
 <title>购售电交易-年度购电计划合同电量分解用户</title>
</head>
<body>

<div id="consBody">
	<mk-search-panel header="false" deployable="false">
	 	<mk-search-visible>
			<!-- 年份  -->
			<su-form-group label-name="年份"  col="8" label-width="50px" label-align="left">
		  		<su-datepicker format="YYYY" :data.sync="year" ></su-datepicker>
			</su-form-group>
			<su-form-group   col="4" label-width="0px" label-align="left">
				<su-button s-type="primary" class="btn-width-small"  v-on:click="search">查询</su-button>
			</su-form-group>
		</mk-search-visible>
	</mk-search-panel>
	<div id="useDiv" style="width: 100%;height:300px"></div>
<script type="text/javascript">
$(window).resize(throll(function(){
	  if(consVue.char1!=null){
		  consVue.char1.resize();
	  }
},100));
function throll(fn,delay){
var timer=null;
return function(){
    clearTimeout(timer);
    timer=setTimeout(fn,delay);
}
}
var consVue = new Vue({
	el:"#consBody",
	data:{
		year:'',
		char1:null,
		rowsMap:["useMaxPrc","useMinPrc","useMinBidPrc"]
	},
	ready:function(){
		var year = new Date().getFullYear();
		this.year = year;
		this.search();
	},
	methods:{
		search:function(){
			var me = this;
			if(this.year===''){
				MainFrameUtil.alert({title:"警告",body:"请选择年份"});
				return;
			}
			$.ajax({
				url:basePath+"cloud-purchase-web/phmHistoryReportPrcController/priceHistoryPage",
				type:"get",
				data:{year:this.year},
				success:function(data){
	    			//初始化数组
	    			var listMap = {};
	    			//初始化数组
	    			for(var i in me.rowsMap){
	    				listMap[me.rowsMap[i]] = [];
	    			}
	    			for(var i in data.rows){
	    				var obj = data.rows[i];
	    				for(var j in me.rowsMap){
	    					listMap[me.rowsMap[j]][parseInt(obj.month)-1] = obj[me.rowsMap[j]];
	    				}
	    			}
	    			me.setUseChars(listMap);
				}
			})
		},
		setUseChars:function(listMap){
			this.char1 = $("#useDiv").createEcharts({
				title:{text:this.year+"年电力交易中心月度交易用电侧报价情况"},
				legend: {
				 	data:["用电侧最高申报电价","用电侧最低申报电价","用电侧最低成交电价"],
			    },
			    xAxis: {
			        data: ['1月','2月','3月','4月','5月','6月','7月','8月','9月','10月','11月','12月']
			    },
				series:[
				        {name: '用电侧最高申报电价',type: 'line', data:listMap.useMaxPrc},
				        {name: '用电侧最低申报电价',type: 'line', data:listMap.useMinPrc},
				        {name: '用电侧最低成交电价',type: 'line', data:listMap.useMinBidPrc},
				        ]
			},"line")
		}
	}
})
</script>
</div>
</body>
</html>