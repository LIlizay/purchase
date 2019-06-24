<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div id="listBody" class="height-fill" v-cloak>
<mk-panel title="电价列表" >
<div v-for="params in paramList">
	<mk-form-panel title="" header="false" line="true">
	    <mk-form-row>
			<!-- 市  基本信息-->
	        <mk-form-col label="市">
	        	<su-select url="${Config.baseURL}/globalCache/queryCityByParentCode/${param.provinceId}" multiple="true" 
	        		:select-value.sync="paramList[$index].cityCodes1" name="cityCode" label-name="name"></su-select>
	        </mk-form-col>
	        <!-- 区/县 基本信息 -->
	        <mk-form-col label="区/县">
	        	<su-select :data-source="paramList[$index].countyList" multiple="true" :select-value.sync="paramList[$index].countyCodes1" name="countryCode" label-name="name"></su-select>
	        </mk-form-col>
	        <!-- 月份 -->
	        <mk-form-col label="月份">
	        	<su-select  multiple="true" :data-source="monthList" :select-value.sync="paramList[$index].effectMonths1" name="effectMonths" label-name="name"></su-select>
	        </mk-form-col>
        </mk-form-row>
	</mk-form-panel>
	<div style="height:440px">
	 <mk-panel title="" header="true" >
       <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
               <su-button class="btn-operator" s-type="default" labeled="true" label-ico="save" v-on:click="save($index)">保存</su-button>
               <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add()">新增</su-button>
               <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del($index)">删除</su-button>
       </div>
	   <div class="col-md-12" style="height:390px;overflow:auto;">
           <table id="listGrid{{params.gridIndex}}"></table>  
       </div>
	 </mk-panel>
	 </div>
</div>
</mk-panel>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var provinceId = '${param.provinceId}';
$.extend(true,$.fn.datagrid.defaults.editors, {    
	numberbox: {
        setValue: function(target, value) {
        	$(target).numberbox("setValue", value);
        	target[0].initFlag = true;
        }  
    }    
});  
var listVue = new Vue({
	el: '#listBody',
	data: {
		formFields:{},
	    provinceId:'',
	    monthList:[
	    	{value:"01",name:"1月"},
	    	{value:"02",name:"2月"},
	    	{value:"03",name:"3月"},
	    	{value:"04",name:"4月"},
	    	{value:"05",name:"5月"},
	    	{value:"06",name:"6月"},
	    	{value:"07",name:"7月"},
	    	{value:"08",name:"8月"},
	    	{value:"09",name:"9月"},
	    	{value:"10",name:"10月"},
	    	{value:"11",name:"11月"},
	    	{value:"12",name:"12月"}
	    ],
	    initPrcInfoList: null,
	    paramList: [
// 	    	{provinceId:"", cityCodes:"", countyCodes:"", effectMonths:"",listSort: 0, smPrcInfoDetailList:[], gridIndex: 0},
	    ],
	    clipboardDataList: null, 		//模仿剪切板，其中保存复制的政府性基金及附加
	    clipboardDataTotal: 0,		//模仿剪切板，其中保存的是政府性基金及附加的数据加和
	    clickDatagrid: null,		//复制粘贴政府性基金及附加时所选中的表格dom对象
	    clickRowIndex: null,			//复制粘贴政府性基金及附加时所选中的行的下标
	    
	    columsMap:{kwhPlainPrc: true, kwhPeakPrc: true, kwhValleyPrc: true, kwhTopPrc: true, kwhDualPrc: true}			//需要绑定change事件的列名称的map
	},
	ready:function(){
		var that = this;
		this.provinceId = provinceId;
		that.getDataGrid();
		
		//查询字段名称加载
		//ComponentUtil.getFormFields("selling.delivery.price",this);
	},
	methods: {
		add:function(){
			var that = this;
			var gridIndex = this.paramList[this.paramList.length - 1].gridIndex + 1;
			var listSort = this.paramList[this.paramList.length - 1].listSort + 1;
			if(that.initPrcInfoList == null || that.initPrcInfoList.length == 0){
				$.ajax({
		            url : basePath+"smPrcInfoController/getInitPrcInfoList",
		            type : "get",
		            async: false,
		            contentType : 'application/json;charset=UTF-8',
		            success : function(data) {
		                if(data.flag){
		                	that.initPrcInfoList = data.data;
		                }else{
		                	MainFrameUtil.alert({title:"查询失败提示",body:data.msg});
		                }
		            },
		            error : function() {
		                MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
		            }
		    	});
			}
			var smPrcInfoList = $.parseJSON($.toJSON(that.initPrcInfoList));
			for(var i = 0; i < smPrcInfoList.length; i++){
				smPrcInfoList[i].gridIndex = gridIndex;
				smPrcInfoList[i].id = that.UUID();
			}
			this.paramList.push({countyList:[],provinceId: that.provinceId, cityCodes:"", countyCodes:"", effectMonths:"",gridIndex: gridIndex,listSort: listSort, smPrcInfoDetailList:smPrcInfoList});
			that.$nextTick(function(){
				ComponentUtil.buildGrid("selling.delivery.price",$("#listGrid"+gridIndex),{ 
					data: smPrcInfoList,
				    height:"100%",
				    rownumbers: true,
	//	 		    pagination: true,
				    singleSelect:true,
				    scrollbarSize:5,
				    nowrap: false,
				    fitColumns:true,
				    columnsReplace:[
    			    	//电度电价分时
    			    	{ field: 'kwhPlainPrc', title: '平', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
    			    		onChange: function(newValue,oldValue){
    			    			if(this.initFlag){
    			    				that.calculateRow(this);
    			    			}
    			    		} } } },
    					{ field: 'kwhPeakPrc', title: '峰', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
    			    		onChange: function(newValue,oldValue){
    			    			if(this.initFlag){
    			    				that.calculateRow(this);
    			    			}
    			    		} } } },
    					{ field: 'kwhValleyPrc', title: '谷', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
    			    		onChange: function(newValue,oldValue){
    			    			if(this.initFlag){
    			    				that.calculateRow(this);
    			    			}
    			    		}} } },
    					{ field: 'kwhTopPrc', title: '尖峰', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
    			    		onChange: function(newValue,oldValue){
    			    			if(this.initFlag){
    			    				that.calculateRow(this);
    			    			}
    			    		}} } },
    					{ field: 'kwhDualPrc', title: '双蓄', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
    			    		onChange: function(newValue,oldValue){
    			    			if(this.initFlag){
    			    				that.calculateRow(this);
    			    			}
    			    		}} } },

    					{ field: 'transPrc', title: '输配电价</br>(元/兆瓦时)', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'] } } }
    				],
    			    rowStyler:function(idx,row){
    			        return "height:35px;font-size:12px;";
    			    },
    			    onClickCell:function(index, field, value){
    			    	if(field == "plPrc"){
    			    		that.clickDatagrid = this;		//复制粘贴政府性基金及附加时所选中的表格dom对象
    			    		that.clickRowIndex = index;			//复制粘贴政府性基金及附加时所选中的行的下标
    			    	}
    			    },
    		        onLoadSuccess: function(data){
    		    		if (data != null && data.rows != null &&  data.rows.length > 0){
    		    			//合并单元格、打开编辑状态
    		    			var rows = data.rows;
    		    			var mergeOptions = new Array();
    		    			var index = 0
    		    			for(var j = 1; j < rows.length; j ++){
    		    				if(rows[index].elecTypeCode != rows[j].elecTypeCode || j == rows.length-1){	//如果和上面的用电类别不同
    		    					if(j - index > 1){
    		    						if(j == rows.length-1){
    		    							mergeOptions.push({index: index,
    			    							field:"elecTypeCodeName",
    			    							rowspan:j-index+1,
    			    							colspan:1});
    		    						}else{
    		    							mergeOptions.push({index: index,
    			    							field:"elecTypeCodeName",
    			    							rowspan:j-index,
    			    							colspan:1});
    			    						index = j;
    		    						}
    		    					}
    		    				}
    		    			}
    		    			//合并单元格
    		    			for(var k = 0; k < mergeOptions.length; k ++){
    		    				$(this).datagrid('mergeCells',mergeOptions[k]);
    		    			}
    		    			//打开各行的编辑状态
    		    			for(var j = 0; j < rows.length; j ++){
    		    				$(this).datagrid('beginEdit', j);
    		    			}
    		    		}
    		    	}
			    });
				that.$watch('paramList['+ (that.paramList.length-1) +'].cityCodes1', eval("(function(value){"
        				+"var me = this;"
        				+"if(value){"
        				+"	$.ajax({"
        				+"		url:basePath+'/globalCache/queryCountyByParentCode/'+value,"
        				+"		type:'get',"
        				+"		success:function(data){"
        				+"			me.paramList[" + (that.paramList.length-1) + "].countyList = data;"
        				+"		}"
        				+"	})"
        				+"}else{"
        				+"	me.paramList[" + (that.paramList.length-1) + "].countyList = [];"
        				+"  me.paramList[" + (that.paramList.length-1) + "].countyCodes = '';"
        				+"}"
        				+"})")
        			);
			});
		},
		save: function($index){
			var gridIndex = this.paramList[$index].gridIndex;
			var rows = $('#listGrid'+gridIndex).datagrid('getRows');
			for(var i = 0; i < rows.length ; i++){
				var row = rows[i];
				var transPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'transPrc'});
				
				var kwhPlainPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'kwhPlainPrc'});
				var kwhPeakPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'kwhPeakPrc'});
				var kwhValleyPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'kwhValleyPrc'});
				var kwhTopPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'kwhTopPrc'});
				var kwhDualPrcEd = $('#listGrid'+gridIndex).datagrid('getEditor', {index:i,field:'kwhDualPrc'});
				
				row.kwhPlainPrc = $(kwhPlainPrcEd.target).numberbox('getValue');
				row.kwhPeakPrc = $(kwhPeakPrcEd.target).numberbox('getValue');
				row.kwhValleyPrc = $(kwhValleyPrcEd.target).numberbox('getValue');
				row.kwhTopPrc = $(kwhTopPrcEd.target).numberbox('getValue');
				row.kwhDualPrc = $(kwhDualPrcEd.target).numberbox('getValue');
				
				row.cataPlainPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='cataPlainPrc']").text();
				row.cataPeakPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='cataPeakPrc']").text();
				row.cataValleyPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='cataValleyPrc']").text();
				row.cataTopPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='cataTopPrc']").text();
				row.cataDualPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='cataDualPrc']").text();
				//给政府性基金及附加、输配电价赋值
				row.plPrc = $('#listGrid'+gridIndex).datagrid('getPanel').find("div.datagrid-view2 tr.datagrid-row:eq(" + i +") td[field='plPrc']").text();
				row.transPrc = $(transPrcEd.target).numberbox('getValue');
			}
			this.paramList[$index].smPrcInfoDetailList = rows;
			this.paramList[$index].smPrcInfoList = $.parseJSON($.toJSON(rows));
			this.paramList[$index].provinceId = this.provinceId;
			this.paramList[$index].cityCodes = this.paramList[$index].cityCodes1;
			this.paramList[$index].countyCodes = this.paramList[$index].countyCodes1;
			this.paramList[$index].effectMonths = this.paramList[$index].effectMonths1;
            $.ajax({
                url : basePath+"smPrcInfoController",
                type : "POST",
                data:$.toJSON(this.paramList[$index]),      //只保存当前点击保存按钮的表格
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                	if(data.flag){
                		MainFrameUtil.alert({title:"成功提示",body:data.msg});
                    }else{
                    	MainFrameUtil.alert({title:"查询失败提示",body:data.msg});
                    }
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                }
        	});
		},
		del: function($index){
			var that = this;
			var ids = [];
			var detailList = this.paramList[$index].smPrcInfoDetailList;
			for(i = 0; i < detailList.length; i ++){
				ids.push(detailList[i].id);
			}
			$.ajax({
                url : basePath+"smPrcInfoController/delete",
                type : "delete",
                data:$.toJSON(ids),      //只保存当前点击保存按钮的表格
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                	if(data.flag){
                		MainFrameUtil.alert({title:"成功提示",body:data.msg});
                		if(that.paramList.length == 1){
                			that.add();
                		}
                		that.paramList.splice([$index],1);
                    }else{
                    	MainFrameUtil.alert({title:"失败提示",body:data.msg});
                    }
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                }
        	});
		},
		getDataGrid:function(){
			var that = this;
			$.ajax({
	            url : basePath+"smPrcInfoController/getPrcInfoVoGroup",
	            type : "POST",
	            data:$.toJSON({provinceId: provinceId,smPrcInfo: {provinceId: provinceId}}),
	            contentType : 'application/json;charset=UTF-8',
	            success : function(data) {
	                if(data.flag){
	                	if(data.data != null && data.data.length > 0){
	                		for(var i = 0; i < data.data.length; i++){
	                			data.data[i].gridIndex=i;
	                			if (data.data[i].smPrcInfoDetailList != null && data.data[i].smPrcInfoDetailList.length > 0){
	        						for(var j = 0; j < data.data[i].smPrcInfoDetailList.length; j ++){
	        							data.data[i].smPrcInfoDetailList[j].gridIndex = i;
	        							data.data[i].cityCodes1 = "";
	        							data.data[i].countyCodes1 = "";
	        							data.data[i].effectMonths1 = "";
	        							data.data[i].countyList = [];
	        		    			}
	        					}
	                			//that.paramList.splice(0,that.paramList.length);
	                			that.paramList.push(data.data[i]);
	                			that.$watch('paramList['+ i +'].cityCodes1', eval("(function(value){"
	                				+"var me = this;"
	                				+"if(value){"
	                				+"	$.ajax({"
	                				+"		url:basePath+'/globalCache/queryCountyByParentCode/'+value,"
	                				+"		type:'get',"
	                				+"		success:function(data){"
	                				+"			me.paramList[" + i + "].countyList = data;"
	                				+"		}"
	                				+"	})"
	                				+"}else{"
	                				+"	me.paramList[" + i + "].countyList = [];"
	                				+"  me.paramList[" + (that.paramList.length-1) + "].countyCodes = '';"
	                				+"}"
	                				+"})")
	                			);
	                		}
	                	}
	                	//that.paramList = data.data;
	                	//列表数据加载
	                	that.$nextTick(function(){
	                		
	                		for(var i = 0; i < that.paramList.length; i ++){
	                			var gridIndex = i;
	                			//给下拉框赋值
	                			this.paramList[i].cityCodes1 = this.paramList[i].cityCodes;
	                			this.paramList[i].countyCodes1 = this.paramList[i].countyCodes;
	                			this.paramList[i].effectMonths1 = this.paramList[i].effectMonths;
	                			//初始化表格
	                			ComponentUtil.buildGrid("selling.delivery.price",$("#listGrid"+gridIndex),{ 
	                				data: that.paramList[i].smPrcInfoDetailList,
	                			    height:"100%",
	                			    rownumbers: true,
//	                	 		    pagination: true,
	                			    singleSelect:true,
	                			    scrollbarSize:5,
	                			    nowrap: false,
	                			    fitColumns:true,
	                			    columnsReplace:[
	                			    	//电度电价分时
	                			    	{ field: 'kwhPlainPrc', title: '平', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
	                			    		onChange: function(newValue,oldValue){
	                			    			if(this.initFlag){
	                			    				that.calculateRow(this);
	                			    			}
	                			    		} } } },
	                					{ field: 'kwhPeakPrc', title: '峰', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
	                			    		onChange: function(newValue,oldValue){
	                			    			if(this.initFlag){
	                			    				that.calculateRow(this);
	                			    			}
	                			    		} } } },
	                					{ field: 'kwhValleyPrc', title: '谷', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
	                			    		onChange: function(newValue,oldValue){
	                			    			if(this.initFlag){
	                			    				that.calculateRow(this);
	                			    			}
	                			    		}} } },
	                					{ field: 'kwhTopPrc', title: '尖峰', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
	                			    		onChange: function(newValue,oldValue){
	                			    			if(this.initFlag){
	                			    				that.calculateRow(this);
	                			    			}
	                			    		}} } },
	                					{ field: 'kwhDualPrc', title: '双蓄', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'],
	                			    		onChange: function(newValue,oldValue){
	                			    			if(this.initFlag){
	                			    				that.calculateRow(this);
	                			    			}
	                			    		}} } },

	                					{ field: 'transPrc', title: '输配电价</br>(元/兆瓦时)', width: 80, editor: { type: 'numberbox', options: {precision:2,  validType:['length[0,9]'] } } }
	                				],
	                			    rowStyler:function(idx,row){
	                			        return "height:35px;font-size:12px;";
	                			    },
	                			    onClickCell:function(index, field, value){
	                			    	if(field == "plPrc"){
	                			    		that.clickDatagrid = this;		//复制粘贴政府性基金及附加时所选中的表格dom对象
	                			    		that.clickRowIndex = index;			//复制粘贴政府性基金及附加时所选中的行的下标
	                			    	}
	                			    },
	                		        onLoadSuccess: function(data){
	                		    		if (data != null && data.rows != null &&  data.rows.length > 0){
	                		    			//合并单元格、打开编辑状态
	                		    			var rows = data.rows;
	                		    			var mergeOptions = new Array();
	                		    			var index = 0
	                		    			for(var j = 1; j < rows.length; j ++){
	                		    				if(rows[index].elecTypeCode != rows[j].elecTypeCode || j == rows.length-1){	//如果和上面的用电类别不同
	                		    					if(j - index > 1){
	                		    						if(j == rows.length-1){
	                		    							mergeOptions.push({index: index,
	                			    							field:"elecTypeCodeName",
	                			    							rowspan:j-index+1,
	                			    							colspan:1});
	                		    						}else{
	                		    							mergeOptions.push({index: index,
	                			    							field:"elecTypeCodeName",
	                			    							rowspan:j-index,
	                			    							colspan:1});
	                			    						index = j;
	                		    						}
	                		    					}
	                		    				}
	                		    			}
	                		    			//合并单元格
	                		    			for(var k = 0; k < mergeOptions.length; k ++){
	                		    				$(this).datagrid('mergeCells',mergeOptions[k]);
	                		    			}
	                		    			//打开各行的编辑状态
	                		    			for(var j = 0; j < rows.length; j ++){
	                		    				$(this).datagrid('beginEdit', j);
	                		    			}
	                		    		}
	                		    	}
	                		    });
	                		}
	                	})
	            		
	                }else{
	                	MainFrameUtil.alert({title:"查询失败提示",body:data.msg});
	                }
	            },
	            error : function() {
	                MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
	            }
	    	});
		},
		calculateRow :function(editor){		//当填写电度电价后根据（目录电价=电度电价-政府基金及附加）公式得到目录电价
			var tr = $(editor).parents(".datagrid-row");
			var rowIndex = tr.prevAll(".datagrid-row").length;
			var tableId = tr.parents(".datagrid-view2").nextAll(".datagrid-f").attr("id");
			var kwhPlainPrcEd = $('#' + tableId).datagrid('getEditor', {index:rowIndex,field:'kwhPlainPrc'});
			var kwhPeakPrcEd = $('#' + tableId).datagrid('getEditor', {index:rowIndex,field:'kwhPeakPrc'});
			var kwhValleyPrcEd = $('#' + tableId).datagrid('getEditor', {index:rowIndex,field:'kwhValleyPrc'});
			var kwhTopPrcEd = $('#' + tableId).datagrid('getEditor', {index:rowIndex,field:'kwhTopPrc'});
			var kwhDualPrcEd = $('#' + tableId).datagrid('getEditor', {index:rowIndex,field:'kwhDualPrc'});
			
			var kwhPlainPrc = $(kwhPlainPrcEd.target).numberbox('getValue');
			var kwhPeakPrc = $(kwhPeakPrcEd.target).numberbox('getValue');
			var kwhValleyPrc = $(kwhValleyPrcEd.target).numberbox('getValue');
			var kwhTopPrc = $(kwhTopPrcEd.target).numberbox('getValue');
			var kwhDualPrc = $(kwhDualPrcEd.target).numberbox('getValue');
			
			var plPrc = parseFloat(tr.find("td[field='plPrc']").text());
			if(kwhPlainPrc != ""){
				tr.find("td[field='cataPlainPrc'] div").text((parseFloat(kwhPlainPrc) - plPrc).toFixed(2));
			}else{
				tr.find("td[field='cataPlainPrc'] div").text("");
			}
			if(kwhPeakPrc != ""){
				tr.find("td[field='cataPeakPrc'] div").text((parseFloat(kwhPeakPrc) - plPrc).toFixed(2));
			}else{
				tr.find("td[field='cataPeakPrc'] div").text("");
			}
			if(kwhValleyPrc != ""){
				tr.find("td[field='cataValleyPrc'] div").text((parseFloat(kwhValleyPrc) - plPrc).toFixed(2));
			}else{
				tr.find("td[field='cataValleyPrc'] div").text("");
			}
			if(kwhTopPrc != ""){
				tr.find("td[field='cataTopPrc'] div").text((parseFloat(kwhTopPrc) - plPrc).toFixed(2));
			}else{
				tr.find("td[field='cataTopPrc'] div").text("");
			}
			if(kwhDualPrc != ""){
				tr.find("td[field='cataDualPrc'] div").text((parseFloat(kwhDualPrc) - plPrc).toFixed(2));
			}else{
				tr.find("td[field='cataDualPrc'] div").text("");
			}
		},
		plPrcFormatter:function(value,row,text){
			return "<a onclick=\"listVue.detail('"+row.id+"',this)\">"+parseFloat(value).toFixed(2)+"</a>";
		},
		detail:function(id,cell){
			
			var that = this;
	  		MainFrameUtil.openDialog({
	  			id:"detail",
	  			params:{id:id},
				href:'${Config.baseURL}view/cloud-purchase-web/delivery/price/detail',
				iframe:true,
				modal:true,
				width:800,
				height:450,
                onClose: function(params) {
                	$(cell).text(parseFloat(params.total).toFixed(2));
                	that.calculateRow(cell);
                   //that.getDataGrid();
                }
			});
		},
		// Generate four random hex digits.
        S4:function() {
           return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
        },
        // Generate a pseudo-GUID by concatenating random hexadecimal.
        UUID:function() {
           return (this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4()+this.S4());
        },
        keydown :function(event){
        	if(document.activeElement.tagName == "BODY"){	//复制粘贴的目标不应该是input框
	        	var that = this;
		        var keyCode = event.keyCode; 
		        /* Ctrl+C 事件要在onKeydown事件中捕获 */ 
		        if(keyCode == "67" && event.ctrlKey){ //如果是复制操作（Ctrl+C）
			        // press Ctrl+C keyboard 
			        if(that.clickDatagrid == null || that.clickRowIndex == null){
			        	MainFrameUtil.alert({title:"复制失败提示",body:"请重新选择要复制的单元格！"});
			        }else{
			        	var id = $(that.clickDatagrid).datagrid("getRows")[that.clickRowIndex].id;
			        	$.ajax({
			                url : basePath+"smFundPrcInfoController/page",
			                type : "post",
			                data:$.toJSON({smFundPrcInfo:{prcId:id}}),
			                contentType : 'application/json;charset=UTF-8',
			                success : function(data) {
			                	if(data.flag){
				            	    that.clipboardDataList = $.parseJSON($.toJSON(data.rows));
							        that.clipboardDataTotal = $(that.clickDatagrid).datagrid("getPanel").find("div.datagrid-view2 tr.datagrid-row:eq(" + that.clickRowIndex +") td[field='plPrc'] a").text();
			                	}else{
			                		that.clipboardDataList = null;
				            	    that.clipboardDataTotal = 0;
				            	    MainFrameUtil.alert({title:"失败提示",body:"复制失败！"});
			                	}
			                },
			                error : function() {
			                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
			                }
			        	});
			        }
		        
	// 			    clipboardDataList: null, 		//模仿剪切板，其中保存复制的政府性基金及附加
	// 			    clipboardDataTotal: 0,		//模仿剪切板，其中保存的是政府性基金及附加的数据加和
	// 			    clickDatagrid: null,		//复制粘贴政府性基金及附加时所选中的表格dom对象
	// 			    clickRowIndex: null			//复制粘贴政府性基金及附加时所选中的行的下标
		        }else if(keyCode == "86" && event.ctrlKey){	 //如果是粘贴操作（Ctrl+V）
	        		if(that.clickDatagrid != null && that.clickRowIndex != null && that.clipboardDataList != null){
		        		var id = $(that.clickDatagrid).datagrid("getRows")[that.clickRowIndex].id;	//所选行的id，即为要粘贴目标电价记录的id
		        		if(that.clipboardDataList.length == 0){		//复制的是没有政府性基金及附加的单元格，即政府性基金及附加数值为0
		        			$.ajax({
		                        url: '${Config.baseURL}smFundPrcInfoController/deleteByPrcId/' + id,
		                        method:'delete',
		                        contentType : 'application/json;charset=UTF-8',
		                        success:function(data){
		                            if(data.flag){
		                                $(that.clickDatagrid).datagrid("getPanel").find("div.datagrid-view2 tr.datagrid-row:eq(" + that.clickRowIndex +") td[field='plPrc'] a").text(0);
		                                that.calculateRow($(that.clickDatagrid).datagrid("getPanel").find("div.datagrid-view2 tr.datagrid-row:eq(" + that.clickRowIndex +") td[field='plPrc']"));
		                            }else{
		                                MainFrameUtil.alert({title:"失败提示",body:data.msg});
		                            }
		                        },
		                        error:function(){
		                            MainFrameUtil.alert({title:"警告",body:"粘贴失败"});
		                        }
		                    });
		        		}else{
		        			for(var i = 0; i < that.clipboardDataList.length; i ++){
			        			var smFundPrcInfo = that.clipboardDataList[i];
			        			smFundPrcInfo.id = null;
			        			smFundPrcInfo.prcId = id;
			        		}
			        		$.ajax({
		                        url: '${Config.baseURL}smFundPrcInfoController/saveList',
		                        data: $.toJSON(that.clipboardDataList),
		                        method:'post',
		                        contentType : 'application/json;charset=UTF-8',
		                        success:function(data){
		                            if(data.flag){
		                                $(that.clickDatagrid).datagrid("getPanel").find("div.datagrid-view2 tr.datagrid-row:eq(" + that.clickRowIndex +") td[field='plPrc'] a").text(parseFloat(that.clipboardDataTotal).toFixed(2));
		                                that.calculateRow($(that.clickDatagrid).datagrid("getPanel").find("div.datagrid-view2 tr.datagrid-row:eq(" + that.clickRowIndex +") td[field='plPrc']"));
		                            }else{
		                                MainFrameUtil.alert({title:"失败提示",body:data.msg});
		                            }
		                        },
		                        error:function(){
		                            MainFrameUtil.alert({title:"警告",body:"粘贴失败"});
		                        }
		                    });
		        		}
		        	}
	        	}
	        }
        } 
	},
	watch:{
		/* 'params.phcElecInfo.cityCode':function(value){
			var me = this;
			if(me.countyFlag){
				me.params.phcElecInfo.countyCode = '';
			}else{
				me.cityFlag = true;
			}
			if(value){
				$.ajax({
					url:basePath+"/globalCache/queryCountyByParentCode/"+value,
					type:'get',
					success:function(data){
						me.countyList = data;
					}
				})
			}else{
				me.countyList = [];
			}
		} */
	}
}); 
</script>
</div>
