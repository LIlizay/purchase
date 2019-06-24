
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>购售电交易-月度购电管理售电公司申报明细</title>
	</head>
	<body>
<div id="transactionReportDetailPage" v-cloak style="height:100%">
	<mk-panel title="交易申报列表" height="100%" line="true">
		    <div class="row" style="height:100%">
		        <table id="transactionReportDetailGrid" style="height:100%"></table>
		    </div>
		</mk-panel>
</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var transactionReportDetailVue = new Vue({
    el: '#transactionReportDetailPage',
    data: {
    	attornTypeFlag:false
    	
    },
    ready:function(){
    	var that = this;
    	
    	if(MainFrameUtil.getParams("plan_edit")){
			MainFrameUtil.setDialogButtons(that.getButtons(),"plan_edit");
		}else if(MainFrameUtil.getParams("plan_detail")){
			MainFrameUtil.setDialogButtons(that.getButtons(),"plan_detail");
		}
    	var width = "25%";
    	var widthStage = "22%";
    	if(detailVue.params.phmPurchasePlanMonth.tradeVariety == "03"){
    		width = "20%";
    		widthStage = "17%";
    		that.attornTypeFlag = true;
    	}
    	$("#transactionReportDetailGrid").datagrid({
        	url: basePath + 'phmTransactionReportDetailController/list/' + detailVue.sum.id,
            method: 'get',
            width:'100%',    
            striped : true,
            pagination : false,
            //rownumbers : true,
            singleSelect:true,
            checkOnSelect:true,
            fitColumns:true,
            //fit:true,
            nowrap: false,
            loadMsg:"请稍后",
            scrollbarSize:0,
            columns:[[
                      	{field:'check',checkbox:true},
                        {field:'stage',title:'分段',width:widthStage,align:'center',
                        	formatter: function(value,row,index){
                				return (index+1);
                			}
						},
						{field:'attornTypeName',title:'合同转让方向',width:width,align:'center'}, 
                        {field:'reportPq',title:'申报电量（兆瓦时）',width:width,align:'center'},
                        {field:'reportPrc',title:'申报电价（元/兆瓦时）',width:width,align:'center'},
                        {field:'voltCodeName',title:'电压等级',width:width,align:'center'}
                    ]],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            },
            onLoadSuccess: function(data){
            	var rows = data.rows;
            	if(rows != null && rows.length > 0){
            		that.calculate(false);
            	}
            	that.calReportPq();
            }
        });
    	if(!that.attornTypeFlag){
    		$("#transactionReportDetailGrid").datagrid('hideColumn','attornTypeName');
    	}
    },
    methods:{
    	calReportPq:function(){
    		$("#transactionReportDetailGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#transactionReportDetailGrid").datagrid("getEditor",{index:rowIndex,field:"reportPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			if(pq != null && pq != 0){
	    				detailVue.countParams.reportPq = parseFloat(detailVue.countParams.reportPq) - parseFloat($("#transactionReportDetailGrid").datagrid("getRows")[rowIndex].reportPq) + parseFloat(pq);
					}
	    		})
	    	});
    	},    	//按钮组
        getButtons:function(){
        	var me = this;
        	var type="";
        	if(MainFrameUtil.getParams("plan_edit")){
        		type="plan_edit";
        	}else if(MainFrameUtil.getParams("plan_detail")){
        		type="plan_detail";
        	}
        	var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog(type)}}];
            return buttons;
        },
         //flag: 是否删除未填写数据的行
         calculate: function(flag){
        	 var me = this;
        	 var rows = $("#transactionReportDetailGrid").datagrid("getRows");
 			 if(flag){		//如果需要删除未填写数据的行
	 			 for(var i= rows.length - 1; i > -1 ;i--){
	 				 var edPq = $('#transactionReportDetailGrid').datagrid('getEditor', {index:i,field:'reportPq'});
		       		 if(edPq != null){
		       			rows[i].reportPq = $(edPq.target).numberbox('getValue');
		       		 }
	 				var edVolt = $('#transactionReportDetailGrid').datagrid('getEditor',{index:i,field:'voltCode'});
		       		 if(edVolt != null){
		       			 rows[i].voltCode = $(edVolt.target).combobox('getValue');
		       		 }
	 				var edPrc = $('#transactionReportDetailGrid').datagrid('getEditor', {index:i,field:'reportPrc'});
	 				if(edPrc != null){
		       			rows[i].reportPrc = $(edPrc.target).numberbox('getValue');
		       		 }
	 				if(me.attornTypeFlag){
	 					var edAttorn = $('#transactionReportDetailGrid').datagrid('getEditor', {index:i,field:'attornType'});
		 				if(edAttorn != null){
			       			rows[i].attornType = $(edAttorn.target).combobox('getValue');
			       		 }
		 				var row = rows[i];
		 				if(row != null && (row.reportPq == null || row.reportPrc == 0 || row.reportPq == 0 || row.reportPrc == null) || row.attornType == null){
			         		 $('#transactionReportDetailGrid').datagrid('deleteRow', i);
		                 }
	 				}else{
	 					var row = rows[i];
		         		if(row != null && (row.reportPq == null || row.reportPrc == 0 || row.reportPq == 0 || row.reportPrc == null)){
			         		 $('#transactionReportDetailGrid').datagrid('deleteRow', i);
		                 }
	 				 }
	 				 
	 			 }
	 			 rows = $("#transactionReportDetailGrid").datagrid("getRows");
 			 }
        	 var reportTotal = 0;
        	 for(var i = 0; i < rows.length; i++){
        		 reportTotal += parseFloat((rows[i].reportPq == null || rows[i].reportPq=="") ? 0 : rows[i].reportPq);
        	 }
        	 detailVue.countParams.reportPq = reportTotal;
         }
    }
})
</script>
	</body>
</html>