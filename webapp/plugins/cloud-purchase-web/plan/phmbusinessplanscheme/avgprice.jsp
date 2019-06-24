<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="avgPriceBody">
	<div id="avgPriceGrid"></div>
	<div id="avgPriceDiv" style="width: 100%;height:250px"></div>
<script type="text/javascript">

$(window).resize(throll(function(){
	  if(avgPriceVue.char1!=null){
		  avgPriceVue.char1.resize();
	  }
},100));
function throll(fn,delay){
    var timer=null;
    return function(){
        clearTimeout(timer);
        timer=setTimeout(fn,delay);
    }
}
var avgPriceVue = new Vue({
	el:"#avgPriceBody",
	data:{
		year:'${param.year}',
		listMap:{},
		char1:null,
		rowsMap:["avgDealPrc","compDealAvgPrc","consDealAvgPrc"]
	},
	ready:function(){
		var me = this;
		ComponentUtil.buildGrid("purchase.plan.avgprice",$("#avgPriceGrid"),{
			url:basePath+"cloud-purchase-web/phmBusinessPlanSchemeController/getPhfTradingCenterDetail",
			queryParams:{year:me.year},
			method:"get",
			width:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:25px;font-size:12px;";
		    },
		    loadFilter:function(data){
		    	var rows = data.rows;
 		    	var listMap ={month:[]};
		    	for(var i in me.rowsMap){
		    		listMap[me.rowsMap[i]] = [];
		    	}
		    	for(var i in rows){
		    		var month = parseInt(rows[i].ym.substring(4,6))+"月";
		    		listMap.month.push(month);
		    		for(var j in me.rowsMap){
		    			listMap[me.rowsMap[j]].push(rows[i][me.rowsMap[j]]);
		    		}
		    	}
		    	me.listMap = listMap;
		    	return rows;
		    },
		    onLoadSuccess:function(data){
		    	me.setPriceChars();
		    }
	    })
	},
	methods:{
		monthFormatter:function(value,row,index){
			return parseInt(row.ym.substring(4,6))+"月";
		},
		setPriceChars:function(){
			this.char1 = $("#avgPriceDiv").createEcharts({
				title:{text:this.year+"年月度竞价交易成交均价情况"},
				legend: {
				 	data:["成交均价","售电公司成交均价","独立用户成交均价"],
			    },
			    xAxis: {
			        data: this.listMap.month
			    },
				series:[
				        {name: '成交均价',type: 'line', data:this.listMap.avgDealPrc},
				        {name: '售电公司成交均价',type: 'line', data:this.listMap.compDealAvgPrc},
				        {name: '独立用户成交均价',type: 'line', data:this.listMap.consDealAvgPrc},
				        ]
			},"line")
		}
	}
})
</script>
</div>