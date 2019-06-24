<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<%@include file="../../../business-core/static/headers/echarts.jsp"%>
<title>总览-年购电量</title>
</head>
<body id="yearSell" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel  slot="top" deployable="false" height="auto">
				<div style="height:40px;margin-top:15px;">
			        <!-- 合同开始日期 -->
			        <su-form-group label-name="年度" class="" col="4" label-width="120px" label-align="right" style="height:40px">
						<su-datepicker name="year" :z-index="999999" id="year" format="YYYY"  :data.sync="year" ></su-datepicker>
			        </su-form-group>
			         <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid" style="margin-left: 50%;">查询</su-button>
			    </div>
	</mk-search-panel>		    
	<mk-panel title="购电量分月明细"  line="true" v-cloak  slot="bottom" height="100%">
	    <div id="grid" style="height:100%;width:100%"></div>
	</mk-panel>
</mk-top-bottom>
	
	<script type="text/javascript">
		var basePath = "${Config.baseURL}";
		var form = new Vue({
			el : '#yearSell',
			data : {
				year : ''
			},
			ready : function(){
				
				var date = new Date();
				this.year = date.getFullYear();
				
				$("#grid").datagrid({
					url : basePath + 'cloud-purchase-web/overViewController/getPhMonLcBid',
					width : "100%",
				 	scrollbarSize : 0,
				    method: 'POST',
				    queryParams:{year:this.year},
			        striped:true,
				    nowrap: false,
				    fitColumns:true,
					singleSelect:true,
				    columns:[[
						{field:'ym',title:'月份',width:'5%',align:'center',rowspan:2,align:'center'},
				        {title:"双边协商交易电量",width:'28%',colspan:2},
				        {title:"竞价交易电量",width:'28%',colspan:2},
				        {title:"挂牌交易电量",width:'28%',colspan:2},
				        {field:'totalPq',title:"合计",width:'11%',rowspan:2,align:'center'},
				    ],[
					     {field:'yearLcPq',title:'年度双边',width:'14%',align:'center'},
					     {field:'monthLcPq',title:"月度双边",width:'14%',align:'center'},
					     {field:'yearBidPq',title:'年度竞价',width:'14%',align:'center'},
						 {field:'monthBidPq',title:"月度竞价",width:'14%',align:'center'},
						 {field:'yearGpPq',title:'年度挂牌',width:'14%',align:'center'},
						 {field:'monthGpPq',title:"月度挂牌",width:'14%',align:'center'}
				     ]],
				    rowStyler:function(idx,row){
			            return "height:30px;font-size:12px;";
			        },
				});
			},
			methods : {
				//查询列表信息
				getDataGrid : function(){
					 $("#grid").datagrid('reload',{year:this.year}); 
				},
				reset : function(){
					var date = new Date();
					this.year = date.getFullYear();
				} 
				
			}
			
			
		});
	</script>
	
	

</body>
</html>