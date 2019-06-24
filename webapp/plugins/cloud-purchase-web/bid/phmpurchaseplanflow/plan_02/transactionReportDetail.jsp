
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>购售电交易-月度购电管理售电公司申报明细</title>
	</head>
	<body>
<div id="transactionReportDetailPage" v-cloak style="height:100%">
	<mk-panel title="交易申报列表" height="100%" line="true">
		    <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" @click="add">新增</su-button>
		         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" @click="deleteRow">删除</su-button>
		    </div>
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
    	MainFrameUtil.setDialogButtons(that.getButtons(),"plan_edit");
    	var width = "25%";
    	var widthStage = "22%";
    	if(mineVue.params.phmPurchasePlanMonth.tradeVariety == "03"){
    		width = "20%";
    		widthStage = "17%";
    		that.attornTypeFlag = true;
    	}
    	$("#transactionReportDetailGrid").datagrid({
        	url: basePath + 'phmTransactionReportDetailController/list/' + mineVue.sum.id,
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
						{field:'attornType',title:'合同转让方向',width:width,align:'center',
							editor:{type:'combobox',options:{
			            		required:true,
			            		editable:false,
			           		 	method:"get",
			                    valueField: 'value',
			                    textField: 'name',
			                    panelHeight:'auto',
			                    url: "${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_attornType"
			                }}}, 
                        {field:'reportPq',title:'申报电量（兆瓦时）',width:width,align:'center',
                        	editor:{type:'numberbox',options:{
                                required:true,
                                min:0,
                                precision:3,
                                onChange:function(newValue,oldValue){
                                	that.calculate(false);
                                }
                            }}},
                        {field:'reportPrc',title:'申报电价（元/兆瓦时）',width:width,align:'center',
                        	editor:{type:'numberbox',options:{
                                required:true,
                                precision:2
                            }}},
                        {field:'voltCode',title:'电压等级',width:width,align:'center',
                        	editor:{type:'combobox',options:{
                        		required:false,
                        		editable:false,
                       		 	method:"get",
                                valueField: 'value',
                                textField: 'name',
                                panelHeight:'auto',
                                url: "${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode"
                            }}}
                    ]],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            },
            onLoadSuccess: function(data){
            	var rows = data.rows;
            	if(rows != null && rows.length > 0){
            		for(var i=0; i<rows.length; i++){
           			 	$("#transactionReportDetailGrid").datagrid('beginEdit',i);
           		 	}
            		that.calculate(false);
            	}
            	that.calReportPq();
            }
        });
    	if(!that.attornTypeFlag){
    		$("#transactionReportDetailGrid").datagrid('hideColumn','attornType');
    		 //隐藏合同装让方向列时 ，取消必填校验
    		var tt=$('#transactionReportDetailGrid').datagrid('getColumnOption', 'attornType'); //通过列名获得此列
            tt.editor.options={required: false};
    	}
    },
    methods:{
    	calReportPq: function(){
    		$("#transactionReportDetailGrid").datagrid("getPanel").find("[field] .validatebox-text").each(function(index,object){
	    		$(object).bind("blur",function(){
	    			var rowIndex = $(this).parents(".datagrid-row-editing").attr("datagrid-row-index");
	    			editor = $("#transactionReportDetailGrid").datagrid("getEditor",{index:rowIndex,field:"reportPq"});
	    			var pq = editor.actions.getValue(editor.target);
	    			if(pq != null && pq != 0){
	    				mineVue.countParams.reportPq = parseFloat(mineVue.countParams.reportPq) - parseFloat($("#transactionReportDetailGrid").datagrid("getRows")[rowIndex].reportPq) + parseFloat(pq);
					}
	    		})
	    	});
    	},
        //新增一个分段
        add:function(){
    		var that= this;
    		that.calculate(false);
   			var newRow = $("#transactionReportDetailGrid").datagrid("appendRow",{
   				stage: $("#transactionReportDetailGrid").datagrid("getRows") == null ? 0: $("#transactionReportDetailGrid").datagrid("getRows").length+1,
				reportPq: 0,
				reportPrc: 0
   			});
   			var lastIndex = $("#transactionReportDetailGrid").datagrid("getRows").length - 1;
            $('#transactionReportDetailGrid').datagrid('selectRow', lastIndex).datagrid('beginEdit', lastIndex);
    		that.calReportPq();
    	},
    	//删除
    	deleteRow:function(){
    		var that = this;
    		var row = $('#transactionReportDetailGrid').datagrid('getSelected');
    	    if(row == null){
    	    	MainFrameUtil.alert({title:"提示",body:"请选择需要删除的数据"});
    	    }else{
                var index = $('#transactionReportDetailGrid').datagrid("getRowIndex",row);
                $('#transactionReportDetailGrid').datagrid("deleteRow",index);
                //改变竞价申报和可申报电量的值
                that.calculate(false);
    	    }
    	},
    	//按钮组
        getButtons:function(){
        	var me = this;
        	var buttons = [{text:"保存",type:"warning",bgcolor:"#8fdecc", handler:function(btn,params){me.save(btn.target)}},
	               {text:"提交",type:"primary", handler:function(btn,params){me.submit(btn.target)}},
	               {text:"回退",type:"warning", bgcolor:"#ecaa55",handler:me.reply},
	               {text:"关闭",handler:function(){MainFrameUtil.closeDialog("plan_edit")}}];
        	
            return buttons;
        },
    	save: function(target){
         	var that = this;
         	var rows = $('#transactionReportDetailGrid').datagrid('getRows');
         	if(rows == null || rows.length == 0){
         		MainFrameUtil.alert({title:"提示",body:"请先添加分段信息!"});
         		return;
         	}
         	
         	//判断方法返回值
         	var msg = that.calculate(true);
         	if(msg == "dataNoAll"){
	    		MainFrameUtil.alert({title:"提示",body:"交易申报列表有必填项未填！"});
				return;
         	}
         	
         	if(mineVue.sum.id != null && mineVue.sum.id != ""){
         		//按钮不可点击
           		$(target).attr("disabled","disabled");
	         	for(var i = 0; i < rows.length; i++){
	         		var row = rows[i];
	         		row.planId = mineVue.sum.id;
	         		row.stage = i+1;
	         	}
         		//保存售电公司申报明细
                $.ajax({
                     url : basePath + "phmTransactionReportDetailController",
                     type : "put",
                     data : $.toJSON(rows),
                     contentType : 'application/json;charset=UTF-8',
                     success : function(data) {
                         //$.messager.progress('close');  //关闭进度条
                         if(data.flag == true){
                         	 MainFrameUtil.alert({title:"成功提示",body:"保存成功"});
                             $("#transactionReportDetailGrid").datagrid("reload");
                         }else{
                             MainFrameUtil.alert({title:"失败提示",body:data.msg});
                         }
                       	//按钮可点击
       					$(target).attr("disabled",false);
                     },
                     error : function() {
                         //$.messager.progress('close');  //关闭进度条
                         MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
 						//按钮可点击
       					$(target).attr("disabled",false);
                     }
                });
         	}
         },
         reply:function(){
 			var me = this;
 		    var id = mineVue.sum.id;
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
 		    						MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
 		    						MainFrameUtil.closeDialog("plan_edit");
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
         submit: function(target){
 			//按钮不可点击
 			$(target).attr("disabled","disabled");
 			//判断方法返回值
         	var msg = this.calculate(true);
         	if(msg == "dataNoAll"){
	    		MainFrameUtil.alert({title:"提示",body:"交易申报列表有必填项未填！"});
				//按钮可点击
				$(target).attr("disabled",false);
				return;
         	}
         	
 			//先验证是否添加了分段数据，然后再保存后提交
 			var rows = $('#transactionReportDetailGrid').datagrid('getRows');
 			if(rows == null || rows.length == 0){
          		MainFrameUtil.alert({title:"提示",body:"请先添加分段信息!"});
				//按钮可点击
				$(target).attr("disabled",false);
          		return;
          	}
 			for(var i = 0; i < rows.length; i++){
 				var ed = $('#transactionReportDetailGrid').datagrid('getEditor', {index:i,field:'reportPrc'});
 	       		if(ed != null){
 	       			rows[i].reportPrc = $(ed.target).numberbox('getValue');
 	       		}
          		var row = rows[i];
          		row.planId = mineVue.sum.id;
          		row.stage = i+1;
          	}
 			MainFrameUtil.confirm({
 		        title:"确认",
 		        body:"提交后计划将无法修改，确定提交吗？",
 		        onClose:function(type){
 		            if(type=="ok"){//确定
 		            	//保存售电公司申报明细
 		                $.ajax({
 		                     url : basePath + "phmTransactionReportDetailController",
 		                     type : "put",
 		                     data : $.toJSON(rows),
 		                     contentType : 'application/json;charset=UTF-8',
 		                     success : function(data) {
 		                         //$.messager.progress('close');  //关闭进度条
 		                         if(data.flag == true){
 		                             $("#transactionReportDetailGrid").datagrid("reload");
 		                             $.ajax({
 		                 				url:basePath+"cloud-purchase-web/phmTransactionReportDetailController/submit/"+mineVue.sum.id,
 		                 				type:'post',
 		                 				success:function(data){
 		                 					if(data.flag){
 		                 						MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
 		                 							MainFrameUtil.setParams(true,"plan_edit");//标记总览页面，提交后打开新页签，展示交易主列表
 		                 							MainFrameUtil.closeDialog("plan_edit");
 		                 						}});
 		                 					}else{
 		                 						MainFrameUtil.alert({title:"失败",body:data.msg});
 	 		                 					//按钮可点击
 	 		    		       					$(target).attr("disabled",false);
 		                 					}
 		                 				}
 		                 			})
 		                         }else{
 		                             MainFrameUtil.alert({title:"失败提示",body:data.msg});
 		                             //按钮可点击
 	 		       					$(target).attr("disabled",false);
 		                         }
 								
 		                     },
 		                     error : function() {
 		                         //$.messager.progress('close');  //关闭进度条
 		                         MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
 								//按钮可点击
 		       					$(target).attr("disabled",false);
 		                     }
 		                 });
 		            }else{
						//按钮可点击
       					$(target).attr("disabled",false);
 		            }
 		        }
     		});
 			
 		},
         //flag: 是否删除未填写数据的行
         calculate: function(flag){
        	 var me = this;
        	 var rows = $("#transactionReportDetailGrid").datagrid("getRows");
 			 if(flag){		//如果需要删除未填写数据的行
	 			 for(var i= rows.length - 1; i > -1 ;i--){
	 				 //验证单元格必填
	 				if(!$('#transactionReportDetailGrid').datagrid('validateRow', i)){
    					return "dataNoAll";
    		    	}
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
	 				}
	 			 }
	 			 rows = $("#transactionReportDetailGrid").datagrid("getRows");
 			 }
        	 var reportTotal = 0;
        	 for(var i = 0; i < rows.length; i++){
        		 reportTotal += parseFloat((rows[i].reportPq == null || rows[i].reportPq=="") ? 0 : rows[i].reportPq);
        	 }
        	 mineVue.countParams.reportPq = reportTotal;
         }
    }
})
</script>
	</body>
</html>