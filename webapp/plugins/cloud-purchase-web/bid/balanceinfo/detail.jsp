<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>结算管理-实际用电量录入实际用电量详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
	
	<style type="text/css">
		.datagrid-row-selected{
			background-color: #eeeeee;
			color:#000000;
		}
	</style>
</head>
<body >
<div id="detaiLDeliveryVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-panel title="实际用电量录入" line="true" slot="top">
	  <mk-form-panel title="" num="3">
	        <mk-form-row >
	            <!--交易年月-->
	            <mk-form-col label="用电年月" :class="{'display-none':!formFields.ym.formHidden}"> 
	                <su-datepicker format="YYYYMM" :data.sync="params.ym" name="ym" disabled="disabled"></su-datepicker>
	            </mk-form-col> 
	             <!-- 用户数量： -->
	            <mk-form-col label="用户数" :class="{'display-none':!formFields.consNum.formHidden}">  
	                 <su-textbox :addon="{'show':true,'rightcontent':'个'}" disabled="true" name="rotateCycle" :data.sync="params.consNum"></su-textbox>
	            </mk-form-col>
	             <!--委托电量-->
	            <mk-form-col label="委托电量" :class="{'display-none':!formFields.monthDeliveryPqFrom.formHidden}"> 
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="rotateCycle" :data.sync="params.reportPq"></su-textbox>
	            </mk-form-col> 
	       </mk-form-row> 
	        <mk-form-row >
	            <!--总交易电量-->
	            <mk-form-col label="总交易电量" :class="{'display-none':!formFields.bidReportPq.formHidden}"> 
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="rotateCycle" :data.sync="params.totalTranPq"></su-textbox>
	            </mk-form-col> 
	             <!-- 实际用电量： -->
	            <mk-form-col label="实际用电量" :class="{'display-none':!formFields.practicalPq.formHidden}">  
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="practicalPq" :data.sync="params.practicalPq"></su-textbox>
	            </mk-form-col>
	       </mk-form-row>
	    </mk-form-panel> 
    </mk-panel>
    <mk-panel title="用户月度实际用电量列表" line="true" slot="bottom" height="100%">
        <div id="addDeliveryGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
</mk-top-bottom>
</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
//对应 js
var detaiLDeliveryVue = new Vue({
	el: '#detaiLDeliveryVue',
	data: {
		formFields:{},
    	params:{
    		ym: null,			//年月
   			consNum: null,		//用户数
   			reportPq: null,		//委托电量
   			practicalPq: null,	//实际用电量
   			totalTranPq:null,	//总交易电量
    	}
	},
	ready:function(){
		var me = this;
		MainFrameUtil.setDialogButtons(this.getButtons(),'detaiLDeliveryVue');
		var ym = MainFrameUtil.getParams("detaiLDeliveryVue").ym;
		me.params.ym = ym;
		this.initGrid();
		this.initForm();
	},
	methods: {
		initForm: function(){
			var me = this;
			$.ajax({
				url:basePath+"scConsElectricityController/getPraPqTranYm/" + me.params.ym,
				method:'get',
	            contentType : 'application/json;charset=UTF-8',
				type:"json",
				success:function(data){
					if(data.flag){
						if(data.data != null){
							me.params.consNum = data.data.consNum;
							me.params.practicalPq = data.data.practicalPq;
							me.params.totalTranPq = data.data.totalTranPq;
							me.params.reportPq = data.data.reportPq;
							$.messager.progress('close');
						}else{//无查询数据
							me.params.consNum = null;
							me.params.practicalPq = null;
						}
					}else{
						MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						$.messager.progress('close');
				    	return;
					}
				},
        		error:function(data){
        			MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"}); 
        		}
			});
		},	
		initGrid: function(ym){
			var that = this;
		 	$('#addDeliveryGrid').datagrid({
	            method:'post',
	            url: basePath + 'scConsElectricityController/pqDataGrid',
	            queryParams: {startYm: that.params.ym},
	            height:"100%",
	            fitColumns:true,
	            rownumbers:true,
	            singleSelect:false,
	            pagination : true,
	            pageSize: 10,
	            pageList: [10, 20, 50, 100, 150, 200],
	            columns:[[
						{field:'consName',title:'用户名称',width:120,align:'left'},
						{field:'elecTypeCodeName',title:'用电类别',width:60,align:'center'},
						{field:'voltCodeName',title:'电压等级',width:60,align:'center'},
	            	    {field:'reportPq',title:'委托电量(兆瓦时)',width:100,align:'center'},
	            	    {field:'practicalPq',title:'实际用电量(兆瓦时)',width:100,align:'center'},
	      				{field:'peakPq',title:'峰时电量(兆瓦时)',width:100,align:'center',
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      				},
	      				{field:'plainPq',title:'平时电量(兆瓦时)',width:100,align:'center',
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}      					
	      				},
	      				{field:'valleyPq',title:'谷时电量(兆瓦时)',width:100,align:'center',
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}  					
	      				},
	      				{field:'overPeakPq',title:'尖峰电量(兆瓦时)',width:100,align:'center',
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      					
	      				},
	      				{field:'doublePq',title:'双蓄电量(兆瓦时)',width:100,align:'center',
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      				}
	    		]]
	        });
		},		
		//按钮组
		getButtons:function(){
			var that = this;
			var buttons = [
				{text:"关闭",handler:function(){MainFrameUtil.closeDialog('detaiLDeliveryVue')}}];
			return buttons;
		}
	} 
}); 
</script>
</body>
</html>
