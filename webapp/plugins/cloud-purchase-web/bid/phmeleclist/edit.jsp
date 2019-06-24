<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>晋能售电-电费清单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="editBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-form-panel slot="top" title="电费清单新增" num="2">
		<mk-form-row>
			<!-- 用户名称 -->
	        <mk-form-col :label="formFields.consName.label" required_icon="true">
	        	<mk-trigger-text  :data.sync="consName" editable="false" @mk-trigger="selectCons" disabled></mk-trigger-text>
	        </mk-form-col>
	        <!-- 年月 -->
	        <mk-form-col :label="formFields.ym.label" required_icon="true">
	        	<su-datepicker :data.sync="params.ym" format="YYYYMM" disabled></su-datepicker>
	        </mk-form-col>
    	</mk-form-row>
    	<mk-form-row>
    		 <!-- 营销户号 -->
	        <mk-form-col :label="formFields.marketUserNo.label">
	        	 <su-textbox :data.sync="params.marketConsNo" disabled></su-textbox>
	        </mk-form-col>
    	</mk-form-row>
	</mk-form-panel>
	<mk-panel title="电费清单信息" slot="bottom" height="100%" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	    </div>
	    <div id="editGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editVue = new Vue({
	el: '#editBody',
	data: {
		formFields:{},
		consName:'',
		params:{consId:'',ym:'',marketConsNo:''}
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('edit');
		me.params.consId = obj.consId;
		me.consName = obj.consName;
		me.params.ym = obj.ym;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'edit');
        $('#editGrid').datagrid({
        	url:basePath+"cloud-purchase-web/phmElecListController/page",
        	methods:"post",
        	queryParams:{phmElecList:{consId: obj.consId,ym:obj.ym}},
			columns:[[
					{field:'ck',title:'ck',checkbox:true},
					{field:'meteringType',title:'计量类型',width:100,align:"left",editor:{
					    type:'combobox',
					    options:{
					   	 editable:false,
					   	 url:basePath+'globalCache/queryCodeByKey/pcode/selling/purchase_meteringType',
					        method:'get',
					        valueField: "value",
					        textField: "name",
					        panelHeight:'100px',
					        required:true,
					        onChange:function(value,oldVal){
					        	if(value == "01"){
					        		var rowIndex = $(this).parents(".datagrid-row").attr("datagrid-row-index");
					        		var editor = $("#editGrid").datagrid("getEditor",{index:rowIndex,field:'meterNo'});
			               	 	    var dom = $(editor.target);
			               	 		dom.val(me.meterNo);
				               	 	dom[0].disabled = "disabled";
					        	}else if(oldVal == "01" ){
					        		var rowIndex = $(this).parents(".datagrid-row").attr("datagrid-row-index");
					        		var editor = $("#editGrid").datagrid("getEditor",{index:rowIndex,field:'meterNo'});
			               	 	    var dom = $(editor.target);
			               	 	    dom.removeAttr("disabled");
					        	}
					        }
					    }
					}},
					{field:'readType',title:'示数',width:100,align:"center",editor:{
					    type:'combobox',
					    options:{
					   	 editable:false,
					   	 url:basePath+'globalCache/queryCodeByKey/pcode/selling/purchase_readType',
					        method:'get',
					        valueField: "value",
					        textField: "name",
					        panelHeight:'100px',
					        required:true
					    }
					}},
					{field:'meterNo',title:'表号',width:100,align:"right",editor:{type:'text'}},
					{field:'startNumber',title:'起码',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'endNumber',title:'止码',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'meterRate',title:'倍率',width:100,align:"right",editor:{type:'text'}},
					{field:'copyPq',title:'抄见电量',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'subtractForm',title:'减分表',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'transLoss',title:'变损',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'lineLoss',title:'线损',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'totalPq',title:'总电量',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'kwhPrc',title:'目录电价',width:100,align:"right",editor:{type:'numberbox',options:{precision:4,min:0}}},
					{field:'kwhAmt',title:'目录电费',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'capDemand',title:'容需量',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'baseAmt',title:'基本电费',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'adjustAmt',title:'力调电费',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'repayAmt',title:'电铁还贷',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'replaceAmt',title:'代征合计',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'additionalAmt',title:'附加',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'ruralAmt',title:'农网',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'regenerateAmt',title:'可再生',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'agricultureAmt',title:'农维',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'elecSourceAmt',title:'电源',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'smallReservoirAmt',title:'小水库',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'largeReservoirAmt',title:'大中型水库',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'differenceAmt',title:'差别',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'specialAmt',title:'专项基金',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'threeGorgesAmt',title:'三峡',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}},
					{field:'lossAmt',title:'输配电损耗加价',width:100,align:"right",editor:{type:'numberbox',options:{precision:4,min:0}}},
					{field:'transPrc',title:'电度输配电价',width:100,align:"right",editor:{type:'numberbox',options:{precision:4,min:0}}},
					{field:'totalAmt',title:'总电费',width:100,align:"right",editor:{type:'numberbox',options:{precision:2,min:0}}}
			           ]],
           	height:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:false,
		    nowrap: false,
		    fitColumns:false,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    },
		    onLoadSuccess:function(data){
		    	me.beginEdit(data.rows.length);
		    }
		})
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.bid.phmeleclist",this);
        me.getMpNo(obj.consId);
        $.ajax({
        	url:basePath+"cloud-purchase-web/sConsDialogController/getConsInfoById/"+ obj.consId,
        	type:"get",
        	success:function(data){
        		me.params.marketConsNo = data.marketConsNo;
        	}
        })
	},
	methods: {
		save:function(backFun){
			var me = this;
	  		if(me.params.consId==''){
	  			MainFrameUtil.alert({title:"警告",body:"请选择用户！"});
	  			return;
	  		}
	  		if(me.params.ym==''){
	  			MainFrameUtil.alert({title:"警告",body:"请选择交易年月！"});
	  			return;
	  		}
	  		var total =  $('#editGrid').datagrid("getRows").length;
	  		for(var i=0;i<total;i++){
	  			var flag = $('#editGrid').datagrid("validateRow",i);
	  			if(!flag){
	  				MainFrameUtil.alert({title:"警告",body:"第【"+(i+1)+"】行数据不完整，请填写相关细信息"});
	  				me.beginEdit(i);
	  				return;
	  			}
	  			$('#editGrid').datagrid("endEdit",i);
	  		}
	  		var rows =  $('#editGrid').datagrid("getRows");
	  		var flag = false;//总计标志， 一个年月只有一条总计数据
	  		var flag2 = false; //校验交易模拟表
	  		var flag3 = false; //实抄主表，总
	  		var checkMap = {};
	  		for(var i in rows){
	  			var obj = rows[i];
	  			//校验重复
	  			if(typeof(checkMap[obj.meteringType])==="undefined"){
	  				checkMap[obj.meteringType] = {};
	  				checkMap[obj.meteringType][obj.readType] = {};
	  				checkMap[obj.meteringType][obj.readType][obj.meterNo] = true;
	  			}else if(typeof(checkMap[obj.meteringType][obj.readType]==="undefined")){
	  				checkMap[obj.meteringType][obj.readType] = {};
	  				checkMap[obj.meteringType][obj.readType][obj.meterNo] = true;
	  			}else if(checkMap[obj.meteringType][obj.readType][obj.meterNo]==="undefined"){
	  				checkMap[obj.meteringType][obj.readType][obj.meterNo] = true;
	  			}else{
	  				MainFrameUtil.alert({title:"警告",body:"同一个计量类型，示数，表号的数据只能有一条数据！"});
	  				me.beginEdit(total);
		  			return;
	  			}
	  			if(obj.meteringType === "01" && obj.readType === "01"){
	  				flag3 = true;
	  				if(obj.meterNo == null || obj.meterNo == ''){
	  					MainFrameUtil.alert({title:"警告",body:"【实抄主表】【总】必须填写表号！"});
		  				me.beginEdit(total);
			  			return;
	  				}
	  				if(obj.endNumber == null || obj.endNumber == ''){
	  					MainFrameUtil.alert({title:"警告",body:"【实抄主表】【总】必须填写止码！"});
		  				me.beginEdit(total);
			  			return;
	  				}
	  			}
	  			if(obj.meteringType === "04"){
	  				flag2 = true;
	  				if(obj.totalPq==null||obj.totalPq==''){
	  					MainFrameUtil.alert({title:"警告",body:"【交易模拟表】必须填写总电量！"});
		  				me.beginEdit(total);
			  			return;
	  				}
	  				if(obj.kwhPrc==null||obj.kwhPrc==''){
	  					MainFrameUtil.alert({title:"警告",body:"【交易模拟表】必须填写目录电价！"});
		  				me.beginEdit(total);
			  			return;
	  				}
	  			}
	  			if(obj.meteringType === "05"){
	  				if(flag){
		  				MainFrameUtil.alert({title:"警告",body:"只能添加一条【合计】数据！"});
		  				me.beginEdit(total);
			  			return;
		  			}else{
		  				if(obj.totalPq==null||obj.totalPq==''){
		  					MainFrameUtil.alert({title:"警告",body:"【合计】必须填写总电量！"});
			  				me.beginEdit(total);
				  			return;
		  				}
		  				if(obj.totalAmt==null||obj.totalAmt==''){
		  					MainFrameUtil.alert({title:"警告",body:"【合计】必须填写总电费！"});
			  				me.beginEdit(total);
				  			return;
		  				}
		  				flag = true;
		  			}
	  			}
	  			rows[i].ym = me.params.ym;
	  			rows[i].consId = me.params.consId;
	  		}
	  		if(!flag3){
	  			MainFrameUtil.alert({title:"警告",body:"请添加【实抄主表】【总】示数 信息！"});
	  			me.beginEdit(total);
	  			return;
	  		}
	  		if(!flag2){
	  			MainFrameUtil.alert({title:"警告",body:"请【添加交易模拟表】信息！"});
	  			me.beginEdit(total);
	  			return;
	  		}
	  		if(!flag){
	  			MainFrameUtil.alert({title:"警告",body:"请添加【合计】信息！"});
	  			me.beginEdit(total);
	  			return;
	  		}
	  		$.ajax({
				url:basePath+"cloud-purchase-web/phmElecListController/saveList",
	 			type:'put',
	 			data:$.toJSON(rows),
	 			contentType : 'application/json;charset=UTF-8',
	 			success:function(data){
	 				if(data.flag){
	 					backFun();
	 				}else{
	 					me.beginEdit(total);
	 					MainFrameUtil.alert({title:"错误",body:data.msg});
	 				}
	 			}
			})
		},
		beginEdit:function(length){
			for(var i=0;i<length;i++){
				$('#editGrid').datagrid("beginEdit",i);
			}
		},
		del:function(){
			var rows = $('#editGrid').datagrid("getChecked");
			if(rows.length==0){
				MainFrameUtil.alert({title:"警告",body:"请选择需要删除的记录！"});
	  			return;
			}
			MainFrameUtil.confirm({
      	        title:"确认",
      	        body:"该操作不能恢复，确定要删除选中记录吗？",
      	        onClose:function(type){
      	        	if(type=='ok'){
      	        		for(var i in rows){
      	        			var index = $('#editGrid').datagrid("getRowIndex",rows[i]);
      	        			$('#editGrid').datagrid("deleteRow",index);
      	        		}
      	        		MainFrameUtil.alert({title:"提示",body:"删除成功！"});
      	        	}
      	        }
  	    	}); 
			
		},
		selectCons:function(){
			var me = this;
	  		MainFrameUtil.openDialog({
	  			id:"consDialog",
	  			href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanmonth/cons-dialog',
				iframe:true,
				modal:true,
				width:'50%',
				height:620,
				onClose:function(params){
					if(typeof(params[0])==="object"){
						me.consName = params[0].consName;
						me.params.consId = params[0].consId;
						me.params.marketConsNo = params[0].marketConsNo;
						me.getMpNo(params[0].consId);
					}
				}
			});
		},
		add:function(){
			 $('#editGrid').datagrid("insertRow",{index:0,row:{}});
			 $('#editGrid').datagrid("beginEdit",0);
		},
		getMpNo:function(consId){
			var me = this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phmElecListController/getMeterNo/"+consId,
				type:"get",
				success:function(data){
					me.meterNo = data.data;
				}
			})
		}
	}
}); 
</script>
</body>
</html>