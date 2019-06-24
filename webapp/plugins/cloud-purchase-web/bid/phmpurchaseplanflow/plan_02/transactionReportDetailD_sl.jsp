
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
    	var width = "15%";
    	if(detailVue.params.phmPurchasePlanMonth.tradeVariety == "03"){
    		width = "10%";
    		that.attornTypeFlag = true;
    	}
    	$("#transactionReportDetailGrid").datagrid({
        	url: basePath + 'phmTransactionReportDetailController/conslist/',
            method: 'post',
            queryParams:{planId:detailVue.sum.id},
            width:'100%',    
            striped : true,
            pagination : true,
            //rownumbers : true,
            singleSelect:true,
            checkOnSelect:true,
            fitColumns:true,
            pageSize: 10,
		    pageList: [10, 20, 50, 100, 150, 200],
            //fit:true,
            nowrap: false,
            loadMsg:"请稍后",
            scrollbarSize:0,
            columns:[[
						{field:'check',checkbox:true},
						{field:'stage',title:'分段',width:'7%',align:'center'},
                        {field:'consName',title:'用户名称',width:'20%',align:'center'},
						{field:'elecTypeName',title:'用电类别',width:'10%',align:'center'}, 
						{field:'voltCodeName',title:'电压等级',width:'10%',align:'center'}, 
						{field:'agrePq',title:'本次交易委托电量<br>（兆瓦时）',width:'20%',align:'center'}, 
						{field:'attornTypeName',title:'合同转让方向',width:width,align:'center'},
                        {field:'reportPq',title:'申报电量<br>（兆瓦时）',width:width,align:'center'},
                        {field:'reportPrc',title:'申报电价<br>（元/兆瓦时）',width:width,align:'center'}
                    ]],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            },
            onLoadSuccess: function(data){
            	var rows = data.rows;
            	if(rows != null && rows.length > 0){
            		that.mergeCells(data.rows);
            		that.calculate(false);
            	}
            }
        });
    	if(!that.attornTypeFlag){
    		$("#transactionReportDetailGrid").datagrid('hideColumn','attornTypeName');
    	}
    },
    methods:{
    	//合并单元格
    	mergeCells: function(rows){
    		var model = new Object();
    		var numList = new Array();
    		var newId = rows[0].consId;
    		numList.push(1);
    		var num = 1;
    		var count = 1;
    		$("#transactionReportDetailGrid").datagrid("getPanel").find(".datagrid-row [field='stage']").eq(0).children().text("1");
    		for(var i=1; i <rows.length; i++){
    			if(rows[i].consId == newId){
    				num ++;
    				count++;
    			}else{
    				newId = rows[i].consId;
    				numList.push(num);
    				count = 1;
    			}
    			if($("#transactionReportDetailGrid").datagrid("getRows")[i].stage == null || $("#transactionReportDetailGrid").datagrid("getRows")[i].stage == 0){
	    			$("#transactionReportDetailGrid").datagrid("getPanel").find(".datagrid-row [field='stage']").eq(i).children().text(count);
    			}
    		}
    		numList.push(num);
    		if(numList.length > 2){
    			var index = 0;
    			var rowspan = 0;
    			for(var j=0; j<numList.length; j++){
    				var rowsIndex = (numList[j+1] - numList[j])+1;
    				this.setMergeCells(index,rowsIndex);
    				index += rowsIndex;
    			}
    		}else{//所有的相同
    			this.setMergeCells(0,rows.length);
    		}
    	},
    	//设置合并单元格
    	setMergeCells:function(index,rowspan){
			$("#transactionReportDetailGrid").datagrid("mergeCells",{
				index : index,
				field : "consName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#transactionReportDetailGrid").datagrid("mergeCells",{
				index : index,
				field : "elecTypeName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#transactionReportDetailGrid").datagrid("mergeCells",{
				index : index,
				field : "voltCodeName",
				rowspan : rowspan,
				colspan : 0
			});
			$("#transactionReportDetailGrid").datagrid("mergeCells",{
				index : index,
				field : "agrePq",
				rowspan : rowspan,
				colspan : 0
			});
    	},
        //新增一个分段
        add:function(){
        	var row = $("#transactionReportDetailGrid").datagrid("getSelected");
    		if(row == null){
    			 MainFrameUtil.alert({title:"提示",body:"请选择要申报电量的用户！"});
                 return;
    		}
    		MainFrameUtil.openDialog({
	  			id:"report_add",
	  			params:{params:row,flag:this.attornTypeFlag},
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/plan_02/reportadd',
				iframe:true,
				title:"申报信息录入",
				modal:true,
				width:"80%",
				height:620,
				onClose:function(){
					$("#transactionReportDetailGrid").datagrid("reload");
					//me.calculate();
                }
			});
    	},
    	//按钮组
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
         reply:function(){
 			var me = this;
 		    var id = detailVue.sum.id;
 		    MainFrameUtil.confirm({
 		        title:"确认",
 		        body:"确定将计划退回？",
 		        onClose:function(type){
 		            if(type=="ok"){//确定
 		            	$.messager.progress({title:"请等待",msg:"正在退回...",interval:100});
 		    			$.ajax({
 		    				url:basePath+"cloud-purchase-web/phmPurchasePlanMonthController/recall",
 		    				method:'put',
 		    				data:$.toJSON({id:id , planStatus:"02"}),
 		    				contentType : 'application/json;charset=UTF-8',
 		    				success:function(data){
 		    					if(data != null){
 		    						$.messager.progress('close');
 		    						MainFrameUtil.alert({title:"提示",body:data.msg});
 		    						detailVue.back();
 		    					}else{
 		    						$.messager.progress('close');
 		    						MainFrameUtil.alert({title:"提示",body:"退回失败，请刷新页面重试！"});
 		    					}
 		    				}
 		    			});
 		            }
 		        }
     		});
 		},
         submit:function(){
 			//先验证是否添加了分段数据，然后再保存后提交
 			var rows = $('#transactionReportDetailGrid').datagrid('getRows');
 			for(var i = 0; i < rows.length; i++){
 	       		if(rows[i].reportPq == null){
 	       			MainFrameUtil.alert({title:"提示",body:"请先添加&quot;"+rows[i].consName+"&quot;用户的申报信息!"});
 	       			return;
 	       		}
          	}
 			MainFrameUtil.confirm({
 		        title:"确认",
 		        body:"提交后计划将无法修改，确定提交吗？",
 		        onClose:function(type){
 		            if(type=="ok"){//确定
 		            	 $.ajax({
               				url:basePath+"cloud-purchase-web/phmTransactionReportDetailController/submit/"+detailVue.sum.id,
               				type:'post',
               				success:function(data){
               					if(data.flag){
               						MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
               							MainFrameUtil.closeDialog("plan_detail");
               						}});
               					}else{
               						MainFrameUtil.alert({title:"失败",body:data.msg});
               					}
               				}
               			})
 		            }
 		        }
     		});
 			
 		},
         //flag: 是否删除未填写数据的行
         calculate:function(flag){
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