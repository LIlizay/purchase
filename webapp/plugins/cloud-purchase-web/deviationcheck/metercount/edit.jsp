<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>偏差考核预警-表计示数管理示数维护</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body>
<div id="meterVue" class="height-fill" v-cloak>
<!-- <mk-top-bottom height="100%" top_height="auto"> -->
<su-form>
	<mk-form-panel>
		<mk-form-row>
			<!-- 用户名称 -->
	        <mk-form-col :label="formFields.consName.label">
	        	<su-textbox :data.sync="params.consName" disabled></su-textbox>
	        </mk-form-col>
			<!-- 电压等级 -->
	        <mk-form-col :label="formFields.voltCodeName.label">
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" multiple="false" 
				:select-value.sync="params.voltCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	        <!-- 用电类别 -->
	        <mk-form-col :label="formFields.elecTypeCodeName.label" >
	        	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" multiple="false" 
				:select-value.sync="params.elecTypeCode" label-name="name" disabled></su-select>
	        </mk-form-col>
	    </mk-form-row>
	    
	    <mk-form-row>
			<!-- 电能表编号 -->
	        <mk-form-col :label="formFields.meterNo.label">
	        	<su-textbox :data.sync="params.meterNo" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 电能表倍率 -->
	        <mk-form-col :label="formFields.meterRate.label" >
	        	<su-textbox :data.sync="params.meterRate" disabled></su-textbox>
	        </mk-form-col>
	        <!-- 电能表位数 -->
	        <mk-form-col :label="formFields.meterDigits.label">
	        	<su-textbox :data.sync="params.meterDigits" disabled></su-textbox>
	        </mk-form-col>
	    </mk-form-row>
	</mk-form-panel>
	
	</su-form>
    
    <mk-panel title="表计示数列表" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		 </div>
        <div class="row"  style="height: 100%">
            <table id="countGrid"></table>
        </div>
        
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var meterVue = new Vue({
	el: '#meterVue',
	data: {
		formFields:{},
    	params:{},
    	queryparams:{id:''},
    	oldList:[],
    	initFlag: false,
    	pageNumber: null		//当前的页码
	},
	ready:function(){
		var me = this;
		me.params = MainFrameUtil.getParams('metercountEdit');
		me.queryparams.id = me.params.id;
		MainFrameUtil.setParams({"confirmHandler":this.save},"metercountEdit");
		
// 		ComponentUtil.buildGrid("selling.deviationcheck.mpcount",
		$("#countGrid").datagrid({
			url: basePath + 'scMpCountController/getByMpId',
			method: 'post',
			queryParams: me.queryparams, 
			width:"100%",
			height:"100%",
		    rownumbers: false,
		    pagination: true,
		    fitColumns:true,
		    pageSize: 20,
		    nowrap: false,
		    fitColumns:true,
		    pageList: [10, 20, 50, 100, 150, 200],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onBeforeLoad : function(param){
		    	if(me.isLoad||me.initFlag==false){
		    		me.isLoad=false;
		    		return true;
		    	}
		    	//判断翻页前是否需要保存
		    	me.refreshAll();
		    	var changeRows = me.getChangeRows();
		    	if(!(changeRows.addRows.length == 0 && changeRows.updateRows.length == 0 && changeRows.deleteRows.length == 0)){
		    		MainFrameUtil.confirm({
		    			title:"警告",
				        body:"页面变更会导致当前页面修改数据丢失，请确认是否保存",
				        buttons:[{text:'保存',type:"ok",style:"primary"},{text:'取消',type:"cancel",style:"default"}],
		    	        onClose:function(type){
		    	            if(type=="ok"){//确定
		    	    			me.isLoad=true;
	    	    				me.save();
		    	    			$("#countGrid").datagrid("reload");
		    	            }else if(type=="cancel"){//取消
		    	    			me.isLoad=true;
		    	    			$("#countGrid").datagrid("reload");
		    	            }
		    	        }
		    	    });
		    		return false;
		    	}else{
		    		return true;
		    	}
		    },
		   	onLoadSuccess:function(data){
		   	 	me.pageNumber = $("#countGrid" ).datagrid("getPager" ).data("pagination" ).options.pageNumber;
		   		me.initFlag = true;
// 		    	me.params = data.extMap["mpInfo"];
		   		me.oldList = $.parseJSON($.toJSON(data.rows));
		    	var rows = $('#countGrid').datagrid('getRows');
		    	for(var i in rows){
			    	$('#countGrid').datagrid('beginEdit', i);
		    	}
		    },
		    columns:[[
		    	{field: 'ck',title: 'ck',checkbox: true,},
				{field:'meterReadPq',title:'抄表总示数',width: 200,align:'center',editor:{type:'numberbox',options:{
                    max:999999999.9999,
                    precision:4
                }}},
				{field:'meterReadDate',title:'抄表时间',width: 200,align:'center',editor:{type:'datetimebox',options:{required:true, editable:false,
					onShowPanel: function(){
						var now = new Date();
						var d1 = new Date(now.getFullYear(), now.getMonth(), now.getDate());
						$(this).datebox('calendar').calendar({
							validator: function(date){
								return date<=d1;
							}
						});
				    }
				}}},
				{field:'usuallyDateFlag',title:'是否抄表例日',width: 200,align:'center',
					formatter: function(value,row,index){
						if (value == "1"){
							return '是';
						} else {
							return '否';
						}
					},
					styler: function(value,row,index){
						if (value == "1"){
							return 'color:red;';		//background-color:#eeeeee;
						}else{
							return '';		//color:green;
						}
				}}
		    ]]
		    
	    });
		
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.deviationcheck.metercount", this);
	},
	methods: {
		save: function(btn){
			//开进度条
           	$.messager.progress();
			var me = this;
			//执行ajax标识
			var flag = true;
			//校验查表日期
			var map = new Object;
			//校验抄表例日 
			var readMap = new Object;
			var rows = $('#countGrid').datagrid('getRows');
			this.refreshAll();
			for(var a in rows){
				if(!$('#countGrid').datagrid('validateRow', a)){
					$.messager.progress('close');
					$('#countGrid').datagrid('selectRow', a);
					flag = false;
		    		MainFrameUtil.alert({title:"提示",body:"抄表时间为必填！"});
					return;
		    	}
				if((rows[a].meterReadPq == null || rows[a].meterReadPq =="")&&(rows[a].usuallyDateFlag ==null || rows[a].usuallyDateFlag == "" ) ){
					$.messager.progress('close');
					$('#countGrid').datagrid('selectRow', a);
					MainFrameUtil.alert({title:"提示",body:"第"+ (parseInt(a) + 1) + "行抄表总示数和是否抄表例日需要填写至少一项！"});
					return;
				}
// 				if((rows[a].meterReadPq == null || rows[a].meterReadPq =="")&&(rows[a].usuallyDateFlag == '0' ) ){
// 					$.messager.progress('close');
// 					$('#countGrid').datagrid('selectRow', a);
// 					MainFrameUtil.alert({title:"提示",body:"第"+ (parseInt(a) + 1) + "行非抄表例日抄表总示数为必填！"});
// 					return;
// 				}
				//校验抄表日期重复
				if(typeof map[rows[a].meterReadDate.substring(0,10)] != "undefined"){
					$.messager.progress('close');
					$('#countGrid').datagrid('selectRow', a);
					flag = false;
					MainFrameUtil.alert({title:"提示",body:"抄表日期重复！"});
					return;
				}
				map[rows[a].meterReadDate.substring(0,10)] = rows[a].meterReadDate.substring(0,10);
				//抄表例日重复验证
				if(rows[a].usuallyDateFlag == "1"){
					if(typeof readMap[rows[a].meterReadDate.substring(0,7)] != "undefined"){
						$.messager.progress('close');
						$('#countGrid').datagrid('selectRow', a);
						flag = false;
						MainFrameUtil.alert({title:"提示",body:rows[a].meterReadDate.substring(0,7)+"月抄表例日有重复，请检查并在用户档案中修改！"});
						return;
					}
					readMap[rows[a].meterReadDate.substring(0,7)] = rows[a].meterReadDate.substring(0,7);
				}
				
			}
			var changeRows = me.getChangeRows();
			if(changeRows.addRows.length == 0 && changeRows.updateRows.length == 0 && changeRows.deleteRows.length == 0){
				$.messager.progress('close');
				MainFrameUtil.alert({title:"提示",body:"没有更改数据，无需保存！"});
				return;
			}
           	var params = {
           			"consId" : me.params.consId,
           			"meterRate" : me.params.meterRate,
           			"id": me.params.id,
           			"addRows" : changeRows.addRows,
           			"updateRows" : changeRows.updateRows,
           			"oldRows" : changeRows.oldRows ,
           			"delRows" : changeRows.deleteRows
           	};
           	if(flag){
              	//按钮不可点击
           		$(btn).attr("disabled","disabled");
	           	$.ajax({
					url:basePath+"scMpCountController/saveChangeCountVo",
					type:'post',
					data:$.toJSON(params),
		 			contentType : 'application/json;charset=UTF-8',
		 			success:function(data){
		 				$.messager.progress('close');
		 				//按钮可点击
 						$(btn).attr("disabled",false);
		 				if(data.flag){
		 					if(data.flag){
		 						/* if(data.data != null){
		 							for(var i = 0 ; i < data.data.length ; i++){
		 								$('#countGrid').datagrid('endEdit', i);
		 								$('#countGrid').datagrid('updateRow',{
		 		 							index: i,
		 		 							row: data.data[i]
		 		 						});
		 								$('#countGrid').datagrid('beginEdit', i);
		 							}
		 						} */
		 						MainFrameUtil.alert({title:"提示",body:data.msg/* ,onClose:function(){MainFrameUtil.closeDialog("metercountEdit");} */});
		 					}else{
		 						MainFrameUtil.alert({title:"提示",body:data.msg});
		 					}
	 						me.isLoad=true;
			 				$("#countGrid").datagrid("reload");
						}else{
							MainFrameUtil.alert({title:"失败",body:data.msg});
						}
		 			},
	                error : function() {
	                	$.messager.progress('close');
		 				//按钮可点击
 						$(btn).attr("disabled",false);
	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
	                }
				});
			}
		},
		//数据变更记录
		getChangeRows :function(){
			var me = this;
			var changeRows = {"addRows":[],"deleteRows":[],"updateRows":[],"oldRows":[]};
			changeRows.deleteRows =  $('#countGrid').datagrid('getChanges','deleted');
			var rows = $('#countGrid').datagrid('getRows');
           	for(var i = 0 ; i < rows.length ; i++){
           		if(rows[i].id != undefined){
           			for(var j = 0 ; j < me.oldList.length ; j++){
           				if(rows[i].id == me.oldList[j].id){
           					if(rows[i].meterReadPq!=me.oldList[j].meterReadPq||rows[i].meterReadDate!=me.oldList[j].meterReadDate||rows[i].usuallyDateFlag!=me.oldList[j].usuallyDateFlag){
           						changeRows.oldRows.push(me.oldList[j]);
           						changeRows.updateRows.push(rows[i]);
           					}
           				}
           			}
           		}else{
           			rows[i].mpId = this.params.id;
           			changeRows.addRows.push(rows[i]);
           		}
           	}
			return changeRows;
		},
		
		//刷新datagrid数据
		refreshAll: function(){
			var rows = $('#countGrid').datagrid('getRows');
	    	for(var i in rows){
	    		// 获取日期输入框编辑器并更改它的值
	    		var ed = $('#countGrid').datagrid('getEditor', {index:i,field:'meterReadPq'});
	    		var ed1 = $('#countGrid').datagrid('getEditor', {index:i,field:'meterReadDate'});
	    		rows[i].meterReadPq = $(ed.target).numberbox('getValue');
	    		rows[i].meterReadDate = $(ed1.target).datetimebox('getValue');
	    	}
		},
		del: function(){
			var me = this;
			var rows = $('#countGrid').datagrid("getSelections");
    		if(rows.length == 0 ){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请选择删除对象！",
                 });
                 return;
    		}
    		MainFrameUtil.confirm({
    	        title:"警告",
    	        body:"确认删除吗？删除后数据不可恢复！ ",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	for(var i=0; i<rows.length; i++){ 
    	            		$('#countGrid').datagrid('deleteRow', $('#countGrid').datagrid('getRowIndex',rows[i]));
    	            	}
    	            }else if(type=="cancel"){//取消
    	            }
    	        }
    	    })	
		},
		
		//新增行
		add:function(){
			if($('#countGrid').datagrid('getRows').length > 0){ //列表中存在记录
				$('#countGrid').datagrid('endEdit', 0);//关闭第一行的编辑
				$('#countGrid').datagrid('beginEdit', 0);//打开第一行的编辑
				$('#countGrid').datagrid('unselectAll');
				var firstRow = $('#countGrid').datagrid('getRows')[0];
				if(firstRow.meterReadDate != null && firstRow.meterReadDate != ""){//第一行的记录不为空
					$('#countGrid').datagrid('insertRow',{index: 0,row: {"meterReadPq":""}});
                }
			}else{
				$('#countGrid').datagrid('insertRow',{index: 0,row: {"meterReadPq":""}});
			}
			$('#countGrid').datagrid('selectRow', 0);
			$('#countGrid').datagrid('beginEdit', 0);//打开第一行的编辑
			//获取编辑行进行编辑
			$('#countGrid').datagrid('beginEdit', 0);//打开第一行的编辑
		}
	}
}); 
</script>
</body>
</html>