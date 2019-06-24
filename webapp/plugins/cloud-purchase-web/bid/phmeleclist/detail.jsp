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
		params:{consId:'',ym:''}
	},
	ready:function(){
		var me = this;
		var obj = MainFrameUtil.getParams('detail');
		me.params.consId = obj.consId;
		me.consName = obj.consName;
		me.params.ym = obj.ym;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'detail');
        $('#editGrid').datagrid({
        	url:basePath+"cloud-purchase-web/phmElecListController/page",
        	methods:"post",
        	queryParams:{phmElecList:{consId: obj.consId,ym:obj.ym}},
			columns:[[
					{field:'meteringTypeName',title:'计量类型',width:100,align:"left",editor:{
					    type:'combobox',
					    options:{
					   	 editable:false,
					   	 url:basePath+'globalCache/queryCodeByKey/pcode/selling/purchase_meteringType',
					        method:'get',
					        valueField: "value",
					        textField: "name",
					        panelHeight:'100px',
					        required:true
					    }
					}},
					{field:'readTypeName',title:'示数',width:100,align:"center",editor:{
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
					{field:'meterNo',title:'表号',width:100,align:"right"},
					{field:'startNumber',title:'起码',width:100,align:"right"},
					{field:'endNumber',title:'止码',width:100,align:"right"},
					{field:'meterRate',title:'倍率',width:100,align:"right"},
					{field:'copyPq',title:'抄见电量',width:100,align:"right"},
					{field:'subtractForm',title:'减分表',width:100,align:"right"},
					{field:'transLoss',title:'变损',width:100,align:"right"},
					{field:'lineLoss',title:'线损',width:100,align:"right"},
					{field:'totalPq',title:'总电量',width:100,align:"right"},
					{field:'kwhPrc',title:'目录电价',width:100,align:"right"},
					{field:'kwhAmt',title:'目录电费',width:100,align:"right"},
					{field:'capDemand',title:'容需量',width:100,align:"right"},
					{field:'baseAmt',title:'基本电费',width:100,align:"right"},
					{field:'adjustAmt',title:'力调电费',width:100,align:"right"},
					{field:'repayAmt',title:'电铁还贷',width:100,align:"right"},
					{field:'replaceAmt',title:'代征合计',width:100,align:"right"},
					{field:'additionalAmt',title:'附加',width:100,align:"right"},
					{field:'ruralAmt',title:'农网',width:100,align:"right"},
					{field:'regenerateAmt',title:'可再生',width:100,align:"right"},
					{field:'agricultureAmt',title:'农维',width:100,align:"right"},
					{field:'elecSourceAmt',title:'电源',width:100,align:"right"},
					{field:'smallReservoirAmt',title:'小水库',width:100,align:"right"},
					{field:'largeReservoirAmt',title:'大中型水库',width:100,align:"right"},
					{field:'differenceAmt',title:'差别',width:100,align:"right"},
					{field:'specialAmt',title:'专项基金',width:100,align:"right"},
					{field:'threeGorgesAmt',title:'三峡',width:100,align:"right"},
					{field:'lossAmt',title:'输配电损耗加价',width:100,align:"right"},
					{field:'transPrc',title:'电度输配电价',width:100,align:"right"},
					{field:'totalAmt',title:'总电费',width:100,align:"right"}
			           ]],
           	height:"100%",
		    rownumbers: true,
		    pagination: false,
		    singleSelect:true,
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
	}
}); 
</script>
</body>
</html>