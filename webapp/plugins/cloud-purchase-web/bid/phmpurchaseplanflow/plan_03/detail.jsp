<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-月度购电管理竞价交易成交详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/createEharts2.js"></script>
<link rel="stylesheet" type="text/css" href="${Config.baseURL}cloud-purchase-web/bid/phmpurchaseplanflow/img/plan.css"/>
<style type="text/css">
.datagrid-row-selected{
	color:#000000;
}
</style>
<title>成交信息录入</title>
</head>
<body id="examineVue" v-cloak style="height:100%">
	<div id = "dealinfo">
		<mk-form-panel num="3">
			<mk-form-row>
				<!-- 交易品种-->
				<mk-form-col :label="formFields.tradeVariety.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeVariety" disabled="disabled"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeVariety" name="tradeVarietyType"></su-select>
				</mk-form-col>
				<!-- 交易方式-->
				<mk-form-col :label="formFields.tradeMode.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradeMode"
	                              url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradeMode" name="tradeModeType" disabled="disabled"></su-select>
				</mk-form-col>
				<!-- 交易周期  -->
				<mk-form-col :label="formFields.tradePeriod.label" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.phmPurchasePlanMonth.tradePeriod"
                               url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_tradePeriod" name="tradePeriod" disabled="disabled"></su-select>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 交易时段  -->
				<mk-form-col :label="formFields.ym.label" required_icon="true">
					<su-datepicker :format ="ymFormat" :data.sync="params.phmPurchasePlanMonth.ym" name="ym" disabled="disabled"></su-datepicker>
				</mk-form-col>
				<!-- 已成交电量 -->
				<mk-form-col :label="formFields.dealPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.agrePq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 委托电量 -->
				<mk-form-col :label="formFields.reportPq.label" colspan="1">
					<su-textbox :data.sync="countParams.dealPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
			<mk-form-row>
				<!-- 申报电量  -->
				<mk-form-col :label="formFields.bidReportPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.reportPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
				<!-- 成交电量  -->
				<mk-form-col :label="formFields.bidReportPq.label" required_icon="true">
					<su-textbox :data.sync="countParams.newReportPq" disabled="disabled" :addon="{'show':true,'rightcontent':'兆瓦时'}" ></su-textbox>
				</mk-form-col>
			</mk-form-row>
		</mk-form-panel>
		<mk-panel title="成交信息" line="true" slot="bottom" height="360px">
	        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="dealInput">成交信息录入</su-button>
	        </div>
	        <div id="dealGrid_" class="col-xs-12" style="height:100%"></div>
	    </mk-panel>
		<!-- <div class="col-xs-12 pull-right form-right-margin mt_10" style="text-align:center;margin-top:10px">
		   <su-button  class="btn-width-small" s-type="primary"   v-on:click="submit">提交</su-button>		
		   <su-button  class="btn-width-small" s-type="warning"   v-on:click="reply">退回</su-button>	                    
		</div> -->
	</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
/* var paramArray = location.search.substr(1).split('&');
var ym = paramArray[1].substr(3).replace("-",""); */
var mineVue = new Vue({
    el: '#examineVue',
    data: {
        formFields:{},
        id:"",
        ym:"",
        params:{phmPurchasePlanMonth:{ym:null,planName:null,setters:null,planStatus:null,tradeMode:null,tradePeriod:null}},
		countParams:{agrePq:null,dealPq:null,reportPq:null},
    },
    ready:function(){
    	var me = this;
    	me.id = location.search.substr(8);
    	 
    	//列表数据加载
    	$("#dealAddGrid").datagrid({
    		url:basePath + "cloud-purchase-web/phmDealInfoController/getConsPlanByPage",
        	method:"post",
        	queryParams:{id:me.id},
            width:"100%",
            //height:280,
            striped : true,
            pagination : false,
            rownumbers: true,
            singleSelect:true,
            nowrap: false,
            fitColumns:true,
            loadMsg:"请稍后",
            scrollbarSize:0,
            columns:[[
                        {field:'stage',title:'分段',width:'8%',align:'center'},
                        {field:'reportPq',title:'申报电量（兆瓦时）',width:'15%',align:'left'},
                        {field:'reportPrc',title:'申报电价（元/兆瓦时）',width:'15%',align:'left'},
                        {field:'voltCodeName',title:'电压等级',width:'10%',align:'left'},
                        {field:'elecName',title:'成交电厂',width:'22%',align:'left'},
                        {field:'dealPq',title:'成交电量（兆瓦时）',width:'15%',align:'left'},
                        {field:'dealPrc',title:'成交电价（元/兆瓦时）',width:'15%',align:'left'}
                    ]],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            },
            onLoadSuccess:function(data){
            	if(data.rows != null && data.rows.length > 0){
            		me.mergeCells(data.rows);
            	}
            	me.calculate();
            }
        });
    	ComponentUtil.getFormFields("purchase.bid.transactionlreport",this); 
    },
    methods:{
    	//合并单元格
    	mergeCells :function(rows){
    		var model = new Object();
    		var numList = new Array();
    		var newId = rows[0].reportDetailId;
    		numList.push(1);
    		var num = 1;
    		for(var i=1; i <rows.length; i++){
    			if(rows[i].reportDetailId == newId){
    				num ++;
    			}else{
    				newId = rows[i].reportDetailId;
    				numList.push(num);
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
    		$("#dealAddGrid").datagrid("mergeCells",{
				index : index,
				field : "stage",
				rowspan : rowspan,
				colspan : 0
			});
			$("#dealAddGrid").datagrid("mergeCells",{
				index : index,
				field : "reportPq",
				rowspan : rowspan,
				colspan : 0
			});
			$("#dealAddGrid").datagrid("mergeCells",{
				index : index,
				field : "reportPrc",
				rowspan : rowspan,
				colspan : 0
			});
    	},
    	
    	//录入成交信息
    	dealInput:function(){
    		//var me = this;
    		var row = $("#dealAddGrid").datagrid("getSelected");
    		if(row == null){
    			 MainFrameUtil.alert({title:"提示",body:"请选择一行分段申报电量！"});
                 return;
    		}
    		MainFrameUtil.openDialog({
	  			id:"dealInput",
	  			params:row,
				href:'${Config.baseURL}view/cloud-purchase-web/bid/phmpurchaseplanflow/phmdealinfo/deal-add',
				iframe:true,
				title:"竞价成交信息录入编辑",
				modal:true,
				width:"80%",
				height:620,
				onClose:function(){
					$("#dealAddGrid").datagrid("reload");
					//me.calculate();
                }
			});
    	},
    	//删除字段渲染
    	rendererDel: function(index,value,row){
    		return "<a href='javascript:void(0)' style='text-decoration:none'>删除</a>";
    	},
    }
})
</script>
</body>
</html>
