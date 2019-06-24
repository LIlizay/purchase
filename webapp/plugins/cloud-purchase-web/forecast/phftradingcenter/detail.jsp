<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-交易中心数据维护详情</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>

<body id="addDiv" v-cloak>
<mk-panel title="交易中心发布数据维护" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 年月-->
            <mk-form-col :label="formFields.ym.label" required_icon="true" >
                <su-datepicker disabled="true" format="YYYYMM" :data.sync="queryParams.phfTradingCenter.ym" name="ymMust"></su-datepicker>
            </mk-form-col>
	
	         <!-- 供需比 -->
             <mk-form-col :label="formFields.supplyDemandRatio.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.supplyDemandRatio" name="supplyDemandRatioMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 总成交电量 -->
             <mk-form-col :label="formFields.totalDealPq2.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.totalDealPq" name="totalDealPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时 '}"></su-textbox>
            </mk-form-col>
           
            <!-- 成交平均价 -->
             <mk-form-col :label="formFields.avgDealPrc2.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.avgDealPrc" name="avgDealPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>

<mk-panel title="发电侧数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.elecNum.label" required_icon="true" >
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecNum" name="elecNumMust" ></su-textbox>
            </mk-form-col>
	
	         <!--成交数量 -->
             <mk-form-col :label="formFields.elecDealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecDealNum" name="elecDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
           
          <mk-form-row>
            <!-- 未成交数量 -->
             <mk-form-col :label="formFields.elecUndealNum.label" required_icon="true">
                <su-textbox disabled="true" disabled="true" :data.sync="queryParams.phfTradingCenter.elecUndealNum" name="elecUndealNumMust"></su-textbox>
            </mk-form-col>
            
            <!-- 申报最低电价 -->
             <mk-form-col :label="formFields.elecLowerPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecLowerPrc" name="elecLowerPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	     
	     <mk-form-row>
            <!-- 申报最高电价 -->
             <mk-form-col :label="formFields.elecUpperPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecUpperPrc" name="elecUpperPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 最高成交价 -->
             <mk-form-col :label="formFields.elecMaxPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecMaxPrc" name="elecMaxPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	    
	      <mk-form-row>
            <!-- 发电侧申报电量-->
             <mk-form-col :label="formFields.elecReportPq.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecReportPq" name="elecReportPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
   <table id="elecGenerationGrid"></table>
</mk-panel>

<mk-panel title="售电公司数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.compNum.label" required_icon="true" >
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.compNum" name="compNumMust"></su-textbox>
            </mk-form-col>
	
	         <!-- 成交数量 -->
             <mk-form-col :label="formFields.compDealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.compDealNum" name="compDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
           
          <mk-form-row>
            <!-- 成交电量 -->
             <mk-form-col :label="formFields.compUndealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.compUndealNum" name="compUndealNumMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 成交均价 -->
             <mk-form-col :label="formFields.compDealAvgPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.compDealAvgPrc" name="compDealAvgPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>
  
<mk-panel title="大用户数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.consNum.label" required_icon="true" >
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.consNum" name="consNumMust"></su-textbox>
            </mk-form-col>
	
	         <!-- 成交数量 -->
             <mk-form-col :label="formFields.consDealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.consDealNum" name="consDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 成交电量 -->
             <mk-form-col :label="formFields.consUndealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.consUndealNum" name="consUndealNumMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 成交均价 -->
             <mk-form-col :label="formFields.consDealAvgPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.consDealAvgPrc" name="consDealAvgPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>

<mk-panel title="用电侧数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 申报最高电价-->
            <mk-form-col :label="formFields.purcUpperPrc.label" required_icon="true" >
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcUpperPrc" name="purcUpperPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	
	         <!-- 申报最低电价 -->
             <mk-form-col :label="formFields.purcLowerPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcLowerPrc" name="purcLowerPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 最低成交价 -->
             <mk-form-col :label="formFields.purcMinPrc.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcMinPrc" name="purcMinPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 用电侧申报电量 -->
             <mk-form-col :label="formFields.purcReportPq.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcReportPq" name="purcReportPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	     
	     <mk-form-row>
            <!-- 参与总数 -->
             <mk-form-col :label="formFields.purcNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcNum" name="purcNumMust"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
	<table id="elecUseGrid"></table>
</mk-panel>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var centerId='${param.centerId}';
 var addVue=new Vue({
	el: '#addDiv',
	data: {
		formFields:{}, 
		queryParams:{"phfTradingCenter":{}}
	},
	methods: {
		getForecastInfo:function(){
			var me=this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phfTradingCenterController/getForecast/"+centerId,
				type:'get',
	 			success:function(data){
	 				me.queryParams.phfTradingCenter=data.data.phfTradingCenter;
	 				$('#elecGenerationGrid').datagrid('loadData',data.data.phfElecReportList);
	 				$('#elecUseGrid').datagrid('loadData', data.data.phfPurcReportList);
	 			}
			})
		}
	},
	ready:function(){
		var me=this;
		var methods={"confirmHandler":this.confirm};
        MainFrameUtil.setParams(methods);
        
        this.getForecastInfo();

		ComponentUtil.buildGrid("purchase.forecast.phfelecreport",$("#elecGenerationGrid"),{ 
	        height:'50%',
	        method: 'post',
	        rownumbers: true,
	        nowrap: true, 
	        fitColumns:true,
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        }
	    }); 
		
		ComponentUtil.buildGrid("purchase.forecast.phfpurcreport",$("#elecUseGrid"),{ 
	        height:'50%',
	        method: 'post',
	        rownumbers: true,
	        nowrap: true, 
	        fitColumns:true,
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        }
	    }); 
		ComponentUtil.getFormFields("purchase.forecast.phftradingcenter",this); 
	}
}); 
</script>
</body>
</html>