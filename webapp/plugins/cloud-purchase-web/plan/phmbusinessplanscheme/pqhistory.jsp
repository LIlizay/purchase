<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="pqHistoryBody" style="height: 100%">
	<div id="pqHistoryGrid">
<script type="text/javascript">
new Vue({
	el:"#pqHistoryBody",
	data:{
		year:'${param.year}',
		indexList:[]
	},
	ready:function(){
		var me = this;
		ComponentUtil.buildGrid("purchase.plan.pqhistory",$("#pqHistoryGrid"),{
			url:basePath+"cloud-purchase-web/phmHistoryReportPqController/getPqHistoryByYear",
			queryParams:{year:me.year},
			method:"get",
		    height:"100%",
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
		    	var dataMap = {};
		    	for(var i in rows){
		    		var obj = rows[i];
		    		if(typeof(dataMap[obj.ym])==="undefined"){
		    			dataMap[obj.ym] = { "01":[], "02":[] };
		    		}
		    		var objData = null;
		    		if(obj.statisticType == "01"){
		    			objData = {month:parseInt(obj.ym.substring(4,6))+"月",pqTypea:obj.pqType,pqPropa:obj.pqProp}
		    		}else if(obj.statisticType == "02"){
		    			objData = {month:parseInt(obj.ym.substring(4,6))+"月",pqTypeb:obj.pqType,pqPropb:obj.pqProp}
		    		}
		    		dataMap[obj.ym][obj.statisticType].push(objData);
		    	}
		    	var list = [];
		    	for(var i in dataMap){
		    		var elecList = dataMap[i]["01"];
		    		var consList = dataMap[i]["02"];
		    		var length = dataMap[i]["01"].length>dataMap[i]["02"].length?dataMap[i]["01"].length:dataMap[i]["02"].length;
		    		for(var j =0 ;j<length;j++){
		    			var elecDetail = me.getObj(elecList[j]);
		    			var consDetail = me.getObj(consList[j]);
		    			var newObj = $.extend(elecDetail,consDetail);
		    			list.push(newObj);
		    		}
		    		me.indexList.push(length);
		    	}
		    	return list;
		    },
		    onLoadSuccess:function(data){
		    	var index = 0;
		    	for(var i in me.indexList){
		    		var length = me.indexList[i];
		    		$(this).datagrid("mergeCells",{index:index,field:"month",rowspan:length})
		    		index += length;
		    	}
		    }
	    })
	},
	methods:{
		getObj:function(obj){
			if(typeof(obj)==="undefined"){
				return {};
			}else{
				return obj;
			}
		}
	}
})
</script>
</div>