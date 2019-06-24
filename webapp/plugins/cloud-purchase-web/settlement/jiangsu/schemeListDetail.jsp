<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<title>结算管理-月度收益结算添加</title>
</head>
<body id="sellSettlementVue" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="售电结算管理" slot="top" deployable="false" line="true" >
        	<!-- 交易年月 -->
			<su-form-group label-name='交易年月'  class="form-control-row "  col="4" label-align="right" >
				<su-datepicker format="YYYY-MM" :data.sync="formData.ym" disabled></su-datepicker>
			</su-form-group>
			<!-- 用户数  -->
			<su-form-group label-name='用户数 '  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.consNum" disabled></su-textbox>
			</su-form-group>
			<!-- 委托电量  -->
			<su-form-group label-name='委托电量'  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.totalProxyPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
			<!-- 实际用电量  -->
			<su-form-group label-name='实际用电量'  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.actualTotalPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
			<!-- 总购电量  -->
			<su-form-group label-name='总购电量'  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.totalPurchasePq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
			
			<!-- 偏差电量比例 -->
			<su-form-group label-name='偏差电量比例 '  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.devPqProp" disabled :addon="{'show':true,'rightcontent':'%'}" ></su-textbox>
			</su-form-group>
			<!-- 双边电量 -->
			<su-form-group label-name='双边电量 '  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.totalLcPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
			<!-- 竞价成交电量 -->
			<su-form-group label-name='竞价电量 '  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.totalBidPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
			<!-- 竞价成交电量 -->
			<su-form-group label-name='挂牌电量 '  class="form-control-row " col="4" label-align="right">
				<su-textbox :data.sync="formData.totalListedPq" disabled :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
			</su-form-group>
    </mk-search-panel>
    
    <mk-panel title="结算方案列表" line="true"  slot="bottom" height="100%">
        <div id="schemeGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
</mk-top-bottom>
</body>
</html>
<script type="text/javascript">
    var basePath = '${Config.baseURL}';
    var sellSettlementVue = new Vue({
    	el: '#sellSettlementVue',
        data: {
        	formData:{id:"",ym:"", consNum:0,totalProxyPq:0, actualTotalPq:0, totalPurchasePq:0, devPqProp:0,
        		totalLcPq:0, totalBidPq:0, totalListedPq:0}
        },
        ready:function(){
        	var that = this;
        	that.formData.id = MainFrameUtil.getParams("schemeListDetailDialog").id;
        	that.formData.ym = MainFrameUtil.getParams("schemeListDetailDialog").ym.substr(0,4) +"-" 
        				+ MainFrameUtil.getParams("schemeListDetailDialog").ym.substr(4,2); 
        	
        	MainFrameUtil.setDialogButtons(this.getButtons(),'schemeListDetailDialog');
        	
        	//列表数据加载
            $("#schemeGrid").datagrid({
                width:"100%",
                rownumbers: true,
                pagination: true,
//                 singleSelect:true,
				url:basePath + "smPurchaseSchemeController/page",
                method:"post",
                queryParams:{smPurchaseScheme:{settleId: that.formData.id}},
                nowrap: false,
                fitColumns:true,
                pageSize: 10,
                pageList: [10, 20, 50, 100, 150, 200],
                loadMsg:"请稍后",
                scrollbarSize:10,
                columns:[[
                    {field:'schemeName',title:'方案名称',width:100, align:'center',
                    	formatter: function(value,row,index){
             				return "<a href='#' onclick=\"sellSettlementVue.viewDetail('"+row.id+ "," + value + "')\">" + value + "</a>";
             			}
                    },
                    {field:'schemeStatus',title:'状态',width:100, align:'center',
                  	  	formatter: function(value,row,index){
             				if (value == "1"){
             					return "归档";
             				} else {
             					return "草稿";
             				}
             			}
  					},
                    {field:'compProfit',title:'售电公司总利润<br>(元)',width:100, align:'center'},
                    {field:'devDam',title:'售电公司偏差考核<br>(元)',width:100, align:'center'},
                   /*  {field:'consCompensate',title:'赔偿用户费用<br>(元)',width:100, align:'center'}, */
                    {field:'consProfit',title:'总节省电费<br>(元)',width:100, align:'center'}
                ]],
                rowStyler:function(idx,row){
                    return "height:35px;font-size:12px;";
                }
            }); 
        	
        	//初始化form表单数据，并重新加载方案表格
            that.initData();
        },
        methods:{
        	initData: function(){
        		var that = this;
        		//通过年月获取结算数据
            	$.ajax({
    	       		url: '${Config.baseURL}/cloud-purchase-web/smSettlementMonthController/getFormDateBySettleIdOrYm?settleId=' + that.formData.id,
    	            method:'get',
    	            async: false,
    	            success:function(data){
    	               if(data.flag ){
                	   		that.formData = data.data;
    	               		if(that.formData.actualTotalPq!=null && that.formData.totalPurchasePq != 0){
    	               			that.formData.devPqProp = (((parseFloat(that.formData.actualTotalPq)-parseFloat(that.formData.totalPurchasePq))/parseFloat(that.formData.totalPurchasePq))*100).toFixed(2);
    	               		}
                     	}else{
                             MainFrameUtil.alert({ title:"失败提示：", body:data.msg});
                        }
    	            },
    	            error:function(){
    	           	 	MainFrameUtil.alert({title:"提示",body:"刷新网络重试"});
    	            }
    	   		});
        	},
        	
            viewDetail: function(obj){
            	var that = this;
            	var cont = obj.split(",");
            	that.formData.schemeId = cont[0];
            	that.formData.schemeName = cont[1];
            	var rows = $("#schemeGrid").datagrid("getRows");
            	var scheme = {};
            	for(var i = 0; i < rows.length; i ++){
            		var row = rows[i];
            		if(cont[0] == row.id){
            			scheme = row;
            		}
            	}
                MainFrameUtil.openDialog({
                    id:"sellSettlementDetail",
                    params:{scheme: $.parseJSON($.toJSON(scheme)), formData: $.parseJSON($.toJSON(that.formData))},
                    iframe:true,//是否使用iframe加载
                    href:'${Config.baseURL}view/cloud-purchase-web/settlement/jiangsu/schemeDetail',
                    modal:true,
                    width:"80%",
                    maximizable:true,
                    height:620
                });
            },
            
          	//按钮组
    		getButtons:function(){
    			var me = this;
    			var buttons = [
    				{text:"关闭",handler:function(){MainFrameUtil.closeDialog('schemeListDetailDialog')}}];
    			return buttons;
    		}
        }
    })
</script>
