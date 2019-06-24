<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-交易规则配置政府基金详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="detailBody" v-cloak>
<mk-panel title="政府性基金及附加" line="true"  slot="bottom"  height="100%">
	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		<su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add()">新增</su-button>
		<su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deleteInfo()">删除</su-button>
    	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="save" v-on:click="endEditing()">保存</su-button>
    </div>
	<div id="listGrid" ></div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editRow = undefined;
var detailVue = new Vue({
	el: '#detailBody',
	data: {
		prcId:''
	},
	ready:function(){
		var that = this;
		var id = MainFrameUtil.getParams('detail').id;
		this.prcId = id;
		ComponentUtil.buildGrid("selling.delivery.pricedetail",$("#listGrid"),{ 
			url:basePath + 'smFundPrcInfoController/page',
			queryParams:{smFundPrcInfo:{prcId:id}},
		    height:"320",
		    method: 'post',
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    columnsReplace:[
							{ field:'check',title:'字段代码',width:'10%',checkbox:true},
		                    { field: 'elecType', title: '政府性基金及附加', width: 100, editor: { type: 'textbox', options: { required: true } } },
							{ field: 'sellPrc', title: '电价（元/兆瓦时）', width: 100, editor: { type: 'numberbox', options: {precision:4,min:0,required: true } } }],
	        onDblClickRow:function (rowIndex, rowData) {
	            if (editRow != undefined) {
            		MainFrameUtil.alert({title:"验证失败提示",body:"请填写完整信息！"});
	            }
	            if (editRow == undefined) {
	                $("#listGrid").datagrid('beginEdit', rowIndex);
	                editRow = rowIndex;
	            }
	            MainFrameUtil.getParams('detail').total = that.caculate();
	        },
	        onClickRow:function(rowIndex,rowData){
	            if (editRow != undefined) {
	            	 $("#listGrid").datagrid('endEdit', editRow);
	            }
	        },
	        onLoadSuccess: function(data){
	        	MainFrameUtil.getParams('detail').total = that.caculate();
	        },
	        onAfterEdit: function (rowIndex, rowData, changes) {
	        	if (editRow != undefined) {
					var row = $('#listGrid').datagrid('getRows')[editRow];
					var type = "PUT";
					if(row.id==null||row.id=="undefined"||row.id==undefined){
						type = "POST";
					}
		            $.ajax({
			                url : basePath+"smFundPrcInfoController",
			                type : type,
			                data:$.toJSON(row),      //只提交修改的行（月度购电计划明细）和月度购电计划信息
			                contentType : 'application/json;charset=UTF-8',
			                success : function(data) {
			                	row.id = data.data.id;
			                	MainFrameUtil.getParams('detail').total = that.caculate();
			                },
			                error : function() {
			                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
			                }
			        	});
				}
	            editRow = undefined;
	        } 
	    }); 
	},
	methods: {
		getDataGrid:function(){
			var that = this;
		    $("#listGrid").datagrid('load',{smFundPrcInfo:{prcId:that.prcId}}); 
		},
		endEditing:function(){
			var that = this;
			if (editRow != undefined) {
           	 	$("#listGrid").datagrid('endEdit', editRow);
           	 	if(editRow != undefined){
           	 		MainFrameUtil.alert({title:"验证失败提示",body:"请填写完整信息！"});
           	 	}else{
					MainFrameUtil.alert({title:"成功提示",body:"保存成功！"});
					MainFrameUtil.getParams('detail').total = that.caculate();
           	 	}
			}else{
				MainFrameUtil.alert({title:"成功提示",body:"保存成功！"});
				MainFrameUtil.getParams('detail').total = that.caculate();
			}
		},
		caculate: function(){
			var sum = 0.0;
			var rows = $("#listGrid").datagrid('getRows');
			for(var i = 0; i < rows.length; i++){
				var row = rows[i];
				if(row.sellPrc != null && !isNaN(row.sellPrc)){
					sum += parseFloat(row.sellPrc);
				}
			}
			return sum;
		},
		add:function(){
			var that = this;
			var newRow = {id:null,prcId:that.prcId,elecType:null,sellPrc:null}
			$("#listGrid").datagrid('appendRow', newRow);
		},
		deleteInfo:function(){
			var that = this;
			var rows = null;
	        rows = $("#listGrid").datagrid('getChecked');
	        if(rows.length>0){
	        	var row = rows[0];
	        	if(row.id==null||row.id=="undefined"){
	        		that.getDataGrid();
	        		editRow = undefined;
	        	}else{
	        		MainFrameUtil.confirm({
	                    title:"删除",
	                    body:'确认删除',
	                    onClose:function(type){
	                        if(type=="ok"){//确定
	                            $.ajax({
	                                url: '${Config.baseURL}smFundPrcInfoController/' + row.id,
	                                //data: $.toJSON(result),
	                                method:'DELETE',
	                                contentType : 'application/json;charset=UTF-8',
	                                success:function(data){
	                                    if(data.flag){
	                                        MainFrameUtil.alert({title:"成功提示",body:"删除成功"});
	                                        that.getDataGrid();
	                                    }else{
	                                        MainFrameUtil.alert({title:"失败提示",body:data.msg});
	                                    }
	                                },
	                                error:function(){
	                                    MainFrameUtil.alert({title:"警告",body:"删除档案信息失败"});
	                                }
	                            });
	                        }else if(type=="cancel"){return;}
	                    }
	                });
	        	}
	        }else{
	        	MainFrameUtil.alert({title:"提示",body:"请选择一条数据"});
	        }
        }
	}
}); 
</script>
</body>
</html>