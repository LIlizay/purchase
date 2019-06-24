<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="priceHistoryBody">
	<div id="priceHistoryGrid"></div>
	<div id="prcProDiv" style="width: 100%;height:250px"></div>
	<div id="prcUseDiv" style="width: 100%;height:250px"></div>
<script type="text/javascript">

$(window).resize(throll(function(){
	  if(priceHistoryVue.char1!=null){
		  priceHistoryVue.char1.resize();
	  }
	  if(priceHistoryVue.char2!=null){
		  priceHistoryVue.char2.resize();
	  }
},100));
function throll(fn,delay){
    var timer=null;
    return function(){
        clearTimeout(timer);
        timer=setTimeout(fn,delay);
    }
}
var priceHistoryVue = new Vue({
	el:"#priceHistoryBody",
	data:{
		year:'${param.year}',
		listMap:{},
		rowsMap:["prodMinPrc","prodMaxPrc","prodMaxBidPrc","useMaxPrc","useMinPrc","useMinBidPrc"],
		monthList:[],
		char1:null,
		char2:null
	},
	ready:function(){
		var me = this;
		ComponentUtil.buildGrid("purchase.plan.pricehistory",$("#priceHistoryGrid"),{
			url:basePath+"cloud-purchase-web/phmHistoryReportPrcController/page",
			queryParams:{year:me.year,pagingFlag:false},
			method:"post",
			width:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    loadFilter:function(data){
		    	var rows = data.rows;
		    	var listMap ={};
		    	for(var i in me.rowsMap){
		    		listMap[me.rowsMap[i]] = [];
		    	}
		    	for(var i in rows){
		    		var month = parseInt(rows[i].ym.substring(4,6))+"月";
		    		rows[i].month = month;
		    		me.monthList.push(month)
		    		for(var j in me.rowsMap){
		    			listMap[me.rowsMap[j]].push(rows[i][me.rowsMap[j]]);
		    		}
		    	}
		    	me.listMap = listMap;
		    	return rows;
		    },
		    onLoadSuccess:function(data){
		    	me.setProChars();
		    	me.setUseChars();
		    }
	    })
	},
	methods:{
		setProChars:function(){
			this.char1 = $("#prcProDiv").createEcharts({
				title:{text:"月度竞价交易发电侧报价情况"},
				legend: {
				 	data:["发电侧最低申报电价","发电侧最高申报电价","发电侧最高成交电价"],
			    },
			    xAxis: {
			        data:this.monthList
			    },
				series:[
				        {name: '发电侧最低申报电价',type: 'line', data:this.listMap.prodMinPrc},
				        {name: '发电侧最高申报电价',type: 'line', data:this.listMap.prodMaxPrc},
				        {name: '发电侧最高成交电价',type: 'line', data:this.listMap.prodMaxBidPrc},
				        ]
			},"line")
		},
		setUseChars:function(){
			this.char2 = $("#prcUseDiv").createEcharts({
				title:{text:"月度竞价交易用电侧报价情况"},
				legend: {
				 	data:["用电侧最高申报电价","用电侧最低申报电价","用电侧最低成交电价"],
			    },
			    xAxis: {
			        data: this.monthList
			    },
				series:[
				        {name: '用电侧最高申报电价',type: 'line', data:this.listMap.useMaxPrc},
				        {name: '用电侧最低申报电价',type: 'line', data:this.listMap.useMinPrc},
				        {name: '用电侧最低成交电价',type: 'line', data:this.listMap.useMinBidPrc},
				        ]
			},"line")
		}
	}
})
</script>
</div>