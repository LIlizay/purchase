<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>例日维护</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="meterVue" v-cloak>
	<mk-panel title="例日列表" line="true" slot="bottom" height="100%">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	    </div>
		
	    <div id="meterGrid" class="col-xs-12" ></div>
	</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editIndex = undefined;
//对应 js
var meterVue = new Vue({
	el: '#meterVue',
	data: {
		consId: null,
		//无consId时 记录保存数据，关闭时反给首页去保存
		saveList: [],
		usuallyDateCodeList:[],		//日期下拉
		usuallyDateCodeMap:{}		//日期map
	},
	ready:function(){
		var me = this;
		this.consId = MainFrameUtil.getParams('usuallyDateDialog').consId;
		var saveList = MainFrameUtil.getParams('usuallyDateDialog').saveList;
		if(saveList != null){
			this.saveList = saveList;
		}
		MainFrameUtil.setDialogButtons(this.getButtons(),"usuallyDateDialog");
		$.ajax({
			url: basePath+"globalCache/queryCodeByKey/pcode/selling/sell_usuallyDate",
            type: "get",
			async : false,
            success : function(data) {
            	if(data != null && data.length != 0){
	            	me.usuallyDateCodeList = data;
            		for (var i = 0; i < data.length; i++) {
            			me.usuallyDateCodeMap[data[i].value] = data[i].name;
					}
            	}
            },
            error : function() {
                MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                return;
            }
        });
		this.initGrid();
	},
	methods: {
		initGrid: function(){
			var me = this;
			//列表数据加载
		   	$("#meterGrid").datagrid({
           		url: basePath+"cloud-purchase-web/scConsDateController/page",
           		method: 'post',
           		queryParams: {consId: me.consId},
                width:'100%',
                height:"100%",
                pagination: true,
                singleSelect: true,
                rownumbers: true,
                nowrap: false,
                fitColumns: true,
                pageList: [10, 20, 50, 100, 150, 200],
  		      	scrollbarSize: 0,
                columns:[[
                	{field: 'ck',title: 'ck',checkbox: true,},
                	{field:'ym',title:'生效年月',width:100,align:'center',editor:{type:'datebox',options:{required:true, editable:false,
                		onShowPanel: function(){
    						var dateBox = this;
    						var tds = false;
    						var p = $(this).datebox('panel');
    					    p.find('span.calendar-text').trigger('click');
    					    p.find(".datebox-button-a").eq(0).hide();
    					    var span = p.find('span.calendar-text');
    					    //屏蔽选择今天的按钮
    					    p.find('.calendar-text').hide();
    					    //输入框原本可填，会触发事件，屏蔽掉
    					    p.find('.calendar-menu-year').attr("readonly","readonly");
    					    if (!tds){
    					    	setTimeout(function () {
    						        tds =p.find('div.calendar-menu-month-inner td');
    						        tds.click(function (e) {
    						          e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
    						          var year = /\d{4}/.exec(span.html())[0]//得到年份
    						          month = parseInt($(this).attr('abbr'), 10); //月份
    						          $(dateBox).datebox('hidePanel').datebox('setValue', year + month); //设置日期的值
    						        });
    						     });
    					    }
    				  	},
    				 	//配置parser，返回选择的日期
    		            parser: function (s) {
    		                if (!s) return new Date();
							// format 201801
    		                return new Date(parseInt(s.substring(0,4), 10), parseInt(s.substring(4,6), 10) - 1, 1);
    		            },
    		            //配置formatter，只返回年月 之前是这样的d.getFullYear() + '-' +(d.getMonth()); 
    		            formatter: function (d) { 
    		                var currentMonth = (d.getMonth()+1);
    		                var currentMonthStr = currentMonth < 10 ? ('0' + currentMonth) : (currentMonth + '');
    		                return d.getFullYear() +  currentMonthStr; 
    		            }
    				}}},
    				{field:'date',title:'抄表例日',width: 200,
						editor:{type:'combobox',options:{
						 	required: true,
						 	editable: false,
			           	 	data: me.usuallyDateCodeList,
			                valueField: 'value',
			                textField: 'name',
// 			                panelHeight:'auto',
					 	}},
	    				formatter: function(value,row,index){
							return me.usuallyDateCodeMap[value];
						}
    				},

    		    ]],
    		    //避免编辑一行时 选中其他行，点击保存后报错问题
    		    onBeforeSelect: function(index, row){
    		    	if(editIndex != undefined){
    		    		return false;
    		    	}
					return true;
    		    },
    		    onLoadSuccess:function(data){
    		    },
    		    loadFilter: function(data){
    				if (me.consId == null || me.consId == ""){
    					return me.saveList;
    				}else {
    					return data;
    				}
    			},
    		    onBeforeLoad : function(param){
    		    	//有编辑中的表格
    		    	if(editIndex != undefined){
    		    		MainFrameUtil.alert({title:"提示",body:"请先保存！"});
			    		MainFrameUtil.confirm({
			    			title:"警告",
					        body:"页面变更会导致当前页面修改数据丢失，请确认是否保存",
					        buttons:[{text:'保存',type:"ok",style:"primary"},{text:'不保存',type:"cancel",style:"default"}],
			    	        onClose:function(type){
			    	            if(type=="ok"){//确定
			    	            	me.save();		//先保存
			    	            }
			    	        }
			    	    });
			    	}
			    	return true;
    		    }
            }); 
		},
		//新增行
		add:function(){
			//有编辑状态的行
			if(editIndex != undefined){ 
				MainFrameUtil.alert({title:"提示",body:"请先保存！"});
				return;
			}
			//新增一行
			$('#meterGrid').datagrid('insertRow',{index: 0,row: {"ym":""}});
			//选中、打开 新增行
			$('#meterGrid').datagrid('beginEdit', 0);
			//取消勾选
			//$('#meterGrid').datagrid('uncheckAll');
			$('#meterGrid').datagrid('checkRow', 0);
			editIndex = 0;
		},
		//编辑 不能修改年月
		edit: function(){
			var me = this;
			var row = $('#meterGrid').datagrid('getSelected');
			if(row == null){
				MainFrameUtil.alert({title:"提示",body:"请先选择要编辑的行！"});
				return;
			}
			var index = $('#meterGrid').datagrid('getRowIndex',row);
			//有编辑状态的行
			if (editIndex != undefined && editIndex != index){
				MainFrameUtil.alert({title:"提示",body:"请先保存！"});
				return;
      			}
			var ym = row.ym;
			$('#meterGrid').datagrid('beginEdit', index);//打开第一行的编辑
			//生效年月禁止编辑
			var dd = $('#meterGrid').datagrid('getEditor', { index: index, field: 'ym' });
			$(dd.target).datebox('disable');

			//编辑时默认focus此编辑框
			var ed = $('#meterGrid').datagrid('getEditor', {index:index,field:"date"});
			($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
			editIndex = index;
		},
		//删除
		del: function(){
			var me = this;
			var row = $('#meterGrid').datagrid("getSelected");
			
// 			var index = $('#meterGrid').datagrid('getRowIndex',row);
// 			if(editIndex != undefined && ){
// 				//有编辑状态的行
// 				MainFrameUtil.alert({title:"提示",body:"请先保存！"});
// 				return;
// 			}
			if(row == null){
    			 MainFrameUtil.alert({title:"提示",body:"请选择删除行！"});
                 return;
    		}
			var index = $('#meterGrid').datagrid('getRowIndex',row);
			
			MainFrameUtil.confirm({
    	        title:"警告",
    	        body:"确认删除吗？删除后数据不可恢复！ ",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	var ym = row.ym;
    	            	if(me.consId != null && me.consId != '' && row.id != null && row.id!= ""){
    	            		//开进度条
    	    	           	$.messager.progress();
    	    				//保存和关闭按钮失效
    	    	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled","disabled");
    						//有consId ajax后台删除
    	    				$.ajax({
    	    					url: basePath+"scConsDateController/"+row.id,
    	                        type: 'delete',
    	                        success : function(data) {
    	                        	//关进度条
    	                        	$.messager.progress('close');
    	                        	//保存和关闭按钮生效
    	            	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled",false);
    	                        	if(data.flag){
    	                        		editIndex = undefined;
    	                        		//刷新表格
   	    								$("#meterGrid").datagrid("reload");
    	                       		 	MainFrameUtil.alert({title:"提示",body:"删除成功！"});
    	    		                    return;
    	                        	}else{
    	                       		 	MainFrameUtil.alert({title:"提示",body:"删除失败！"});
    	    		                    return;
    	                        	}
    	                        },
    	                        error : function() {
    	                        	//关进度条
    	                        	$.messager.progress('close');
    	                        	//保存和关闭按钮生效
    	            	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled",false);
    	    	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
    	    	                    return;
    	    	                }
    	                    });
   						}else{
   							//没有consId或者是新增的行
   							$('#meterGrid').datagrid("deleteRow",index);
   						}
    	            }else if(type=="cancel"){//取消
    	            }
    	        }
    	    })	
		},
		getButtons:function(){
        	var me = this;
               var buttons=[{
                   text:"保存",
                   type:"primary",
                   handler:function(btn,params){
                       me.save();
                   }
               },{
                   text:"关闭",
                   handler:function(btn,params){
                       MainFrameUtil.closeDialog("usuallyDateDialog");
                   }
               }];
               return buttons;
        },
        save: function(){	//编辑一条 保存一条
           	var me = this;
           	if(editIndex == undefined){ 
				MainFrameUtil.alert({title:"提示",body:"没有需要保存数据！"});
				return;
			}
           	if(!$('#meterGrid').datagrid('validateRow', editIndex)){
	    		MainFrameUtil.alert({title:"提示",body:"请填写必填项！"});
				return;
	    	}
			var rows = $('#meterGrid').datagrid("getRows");
			var row = rows[editIndex];
			
			//获取生效年月
           	var ed = $('#meterGrid').datagrid('getEditor', {index: editIndex,field: 'ym'});
           	var ym = $(ed.target).datebox('getValue');
           	
           	//验证生效年月的唯一
           	for (var i = 0; i < rows.length; i++) {
				if(i != editIndex && ym == rows[i].ym){
					MainFrameUtil.alert({title:"提示",body:"生效年月重复！"});
					return;
				}
			}
           	
           	//结束编辑
           	$('#meterGrid').datagrid('endEdit',editIndex);
           	//获取结束编辑后的数据行
           	var rowData = $('#meterGrid').datagrid('getRows')[editIndex];
           	editIndex = undefined;
           	//开进度条 $.messager.progress(); $.messager.progress('close');
			//有consId 时 直接ajax保存
			if(me.consId != null && me.consId != ''){
				MainFrameUtil.alert({title:"提示",body:"修改抄表例日后，监控平台电量以及偏差预警信息需要重新计算，计算时间较长，请耐心等待。"});
				//结束编辑时保存，一行一行保存
				var type = "post";
				if(rowData.id != null && rowData.id != ""){
					type = "put";
				}
				rowData.consId = me.consId;
				//开进度条
	           	$.messager.progress();
				//保存和关闭按钮失效
	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled","disabled");
				$.ajax({
					url: basePath+"scConsDateController",
                    type: type,
                    data: $.toJSON(rowData),
                    contentType : 'application/json;charset=UTF-8',
// 					async : false,
                    success : function(data) {
                    	//关进度条
                    	$.messager.progress('close');
                    	//保存和关闭按钮生效
        	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled",false);
                    	if(data.flag){
                    		//刷新表格
							$("#meterGrid").datagrid("reload");
                   		 	MainFrameUtil.alert({title:"提示",body:"保存成功！"});
		                    return;
                    	}else{
                   		 	MainFrameUtil.alert({title:"提示",body:"保存失败！"});
		                    return;
                    	}
                    },
                    error : function() {
                    	//关进度条
                    	$.messager.progress('close');
                    	//保存和关闭按钮生效
        	           	$("#usuallyDateDialog-window div.dialog-buttons button" , parent.document).attr("disabled",false);
	                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
	                    return;
	                }
                });
			}else{		//没有consId 时把整页数据返回给主页 去保存
				//记录保存数据
				me.saveList = $('#meterGrid').datagrid('getRows');
              	MainFrameUtil.getParams('usuallyDateDialog').saveList = me.saveList;
              	editIndex = undefined;
              	MainFrameUtil.alert({title:"提示",body:"保存成功！"});
			}
        }
	}
}); 
</script>
</body>
</html>