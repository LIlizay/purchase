
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>购售电交易-月度购电管理委托电量明细</title>
	</head>
	<body>
	
<div id="proxyPqDetailPage" v-cloak style="height:100%">
	<mk-panel title="交易申报分段信息" line="true" height="100%" header="false">
		    <div class="row" style="height:100%">
		        <table id="proxyPqDetailGrid" style="height:100%"></table>
		    </div>
		</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editIndex = undefined;
var proxyPqDetailVue = new Vue({
    el: '#proxyPqDetailPage',
    data: {
    },
    ready:function(){
    	var that = this;
    	var id = "";
    	if(MainFrameUtil.getParams("phmdealinfo_detail") != undefined){
    		id = MainFrameUtil.getParams("phmdealinfo_detail").id;
    	}else if(MainFrameUtil.getParams("transactionlreport_detail") != undefined){
    		id = MainFrameUtil.getParams("transactionlreport_detail").id;
    	}else{
    		id = detailVue.sum.id;
    	}
    	
    	$("#proxyPqDetailGrid").datagrid({
        	url: basePath + 'phmTransactionReportDetailController/page',
        	queryParams:{planId:id},
            method: 'post',
            width:'100%',    
            striped : true,
            pagination : true,
            pageSize: 10,
		    pageList: [10, 20, 50, 100, 150, 200],
            rownumbers : true,
            singleSelect:true,
            fitColumns:true,
            nowrap: false,
            loadMsg:"请稍后",
            scrollbarSize:0,
            columns:[[
                        {field:'izName',title:'用户性质',width:'200',align:'center'},
                        {field:'consName',title:'用户名称',width:'200',align:'center'},
                        {field:'voltCodeName',title:'电压等级',width:'200',align:'center'},
                        {field:'elecTypeName',title:'用电类别',width:'200',align:'center'},
                        {field:'agrePq',title:'本次交易委托电量<br/>(兆瓦时)',width:'200',align:'center'}
                    ]],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            }
        });
    },
    methods:{
    }
})
</script>
</div>
</body>
</html>