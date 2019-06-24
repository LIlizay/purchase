<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>购售电交易-年度购电管理双边电量分月方案详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="editBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<div class="row row-margin-bottom" slot="top" >
		<mk-form-panel title="">
			<mk-form-row>
				<!-- 长协电量 -->
		        <mk-form-col :label="formFields.lcPqForm.label" >
		        	<su-textbox :data.sync="params.lcPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 合同总电量 -->
		        <mk-form-col :label="formFields.agrePq.label" >
		        	<su-textbox :data.sync="params.agrePq" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 附加利润 -->
		        <mk-form-col :label="formFields.addProfitForm.label" v-if="flag1">
		        	<su-textbox :data.sync="params.addProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
		        </mk-form-col>
		        <!-- 差值利润 -->
		        <mk-form-col :label="formFields.differenceProfitForm.label" v-if="flag2">
		        	<su-textbox :data.sync="params.differenceProfit" :addon="{'show':true,'rightcontent':'万元'}" disabled></su-textbox>
		        </mk-form-col>
		   	</mk-form-row>
		</mk-form-panel>
	</div>
	<mk-panel title="" slot="bottom" height="100%" line="true">
	    <div id="editGrid" ></div>
	</mk-panel>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editVue = new Vue({
	el: '#editBody',
	data: {
		formFields:{},
		flag1:false,//控制附加利润
		flag2:false,//控制差值利润
		params:{
			lcPq:'',agrePq:'',dvaluePq:'',addProfit:'',differenceProfit:''
		}
	},
	ready:function(){
		var obj = MainFrameUtil.getParams('detail');
		this.params = obj;
		if(obj.addProfit != null){
	        	this.flag1 = true;
        }else if(obj.differenceProfit != null){
        	this.flag2 = true;
        }
		//列表数据加载
		ComponentUtil.buildGrid("purchase.plan.phmlcdist",$("#editGrid"),{
			url:basePath + 'cloud-purchase-web/phmLcDistController/getAgreMonthPq',
			method:"get",
			queryParams:{id:obj.id},
		    height:"100%",
		    rownumbers: false,
		    pagination: false,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    columnsReplace:[
		                   {field:'editLcPq',title:'双边电量分解调整（兆瓦时）',width:120,align:'right',editor:{type:'numberbox',options:{precision:4,min:0,}}}
		                    ],
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.plan.scheme",this);
	},
	methods: {
		ymFormatter:function(value,row,index){
			return parseInt(value.substring(4,6))+"月";
		}
	}
}); 
</script>
</body>
</html>