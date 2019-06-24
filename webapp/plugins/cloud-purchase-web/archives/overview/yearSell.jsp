<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<link type="text/css"  rel="stylesheet" href="../../static/css/overView.css"/>
<title>总览-年售电量</title>
</head>
<body id="yearSell">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel  slot="top" deployable="false" height="auto">
					<div style="height:40px;margin-top:15px;">
				        <!-- 合同开始日期 -->
				        <su-form-group label-name="年度" class="" col="4" label-width="120px" label-align="right" style="height:40px">
							<su-datepicker name="year" :z-index="999999" id="year" format="YYYY"  :data.sync="year" ></su-datepicker>
				        </su-form-group>
				         <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid" style="margin-left: 50%;">查询</su-button>
<!-- 				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button> -->
				    </div>
		</mk-search-panel>

		<mk-panel title="售电量分月明细（单位：兆瓦时）"  line="true" v-cloak  slot="bottom" height="100%">
		    <div id="grid1" style="height:20%;width:100%"></div>
		    <div id="grid2" style="height:20%;width:100%"></div>
		    <div style="height:60%;width:100%;margin-top:35px"><span class="title">总购电量 : <span id="totalPq"></span> （兆瓦时）</span></div>
		</mk-panel>
	</mk-top-bottom>
	
	
	<script type="text/javascript">
		
		var basePath = "${Config.baseURL}";
		var yearSell = new Vue({
			el : "#yearSell",
			data : {
				year : '',
			},
			ready : function(){
				var date = new Date();
				this.year = date.getFullYear();
				this.initData();
			},
			methods : {
				//列表初始化查询
				initData:function(){
					var me = this;
					$.ajax({
						url : basePath + 'cloud-purchase-web/overViewController/getSellMonLcBid',
						type : 'POST',
						data:$.toJSON({year:me.year}), 
						contentType : 'application/json;charset=UTF-8',
						success : function(data){
							if(data.flag){
								var totalPq = 0;
								for(var i = 1 ; i < 13 ; i++){
									var title = null;
									if(i<10){
										title = "mon0"+i;
									}else{
										title = "mon"+i;
									}
									totalPq += parseFloat(data.data[0][title]);
								}
								$("#totalPq").html(totalPq);
								//列表数据加载
								me.initGrid("#grid1",data.data,1);
								me.initGrid("#grid2",data.data,2);
							}else{
								MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
							}
						},
						error : function() {
							MainFrameUtil.alert({title:"提示",body:"网络错误，请刷新页面重试！"});
						}
					});
				},
				//加载表信息
				initGrid : function(name,data,type){
					var columns = [];
					if(type == 1){
						for(var i = 1 ; i < 7 ; i++){
							columns.push({field:'mon0'+i,title:i+'月',width:'16.5%',align:'center'});
						}
					}else{
						for(var i = 7 ; i < 13 ; i++){
							if(i<10){
								columns.push({field:'mon0'+i,title:i+'月',width:'16.5%',align:'center'});
							}
							else{
								columns.push({field:'mon'+i,title:i+'月',width:'16.5%',align:'center'});
							}
						}
					}
					$(name).datagrid({
						data:data,
					    width:"100%",
					    scrollbarSize : 0,
				        striped:true,
					    nowrap: false,
					    fitColumns:true,
						singleSelect:true,
					    columns:[columns],
					    rowStyler:function(idx,row){
				            return "height:33px;font-size:12px;";
				        } 
				    }); 
				},
				//查询列表信息
				getDataGrid : function(){
					 this.initData();
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