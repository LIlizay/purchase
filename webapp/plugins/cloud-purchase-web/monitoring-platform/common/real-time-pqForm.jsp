<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/plugins/business-core/static/headers/base.jsp"%>
<%@include file="/plugins/business-core/static/headers/echarts.jsp"%>
<title>监控平台-实时电量报表</title> 
</head>
<body id="listVue">
<mk-left-right left_width="250px" height="100%">
  	<mk-panel  slot="left" height="99%"  header='false'>
		<div id="user-tree" style="margin: 2% auto 2% auto;height:100%;"></div>
    </mk-panel>
    <mk-top-bottom slot="right"  height="100%" top_height="auto" style="padding-left:5px">
		<mk-search-panel slot="top" deployable="false">
			<mk-search-visible>
				<su-form-group label-name='时间' class="form-control-row" col="4" label-width="118px" label-align="right">
		            <su-datepicker format="YYYY-MM-DD" :data.sync="params.startTime"></su-datepicker>
		       	</su-form-group>
			</mk-search-visible>
		</mk-search-panel>
		<mk-panel header="false" slot="bottom" height="100%" topline="false"  style="overflow:auto;padding-left: 5px;">
			<mk-tabpanel :selected.sync="initIndext" style="padding-left:0">
		        <mk-tabpage index=0 header="15分钟" href="${Config.baseURL}view/plugins/cloud-purchase-web/monitoring-platform/common/fifteen-minute" iframe="false" style="padding-left:0"></mk-tabpage>
		        <mk-tabpage index=1 header="5分钟"  href="${Config.baseURL}view/plugins/cloud-purchase-web/monitoring-platform/common/five-minute"  iframe="false" style="padding-left:0"></mk-tabpage>
	   		</mk-tabpanel>
		</mk-panel>
    </mk-top-bottom>
</mk-left-right>


            
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listVue',
	data:{
		initIndext:0,//选中页的绑定属性
		nowDate: null,//当前 年月日
		consId: null,
		params:{
			startTime: null,
		},
		vFor: [],//循环div用
		pageData: null,//table页数据
	},

	ready:function(){
		var me=this;
		var date = new Date();
		var y = date.getFullYear();
	    var m = date.getMonth()+1;
        var d = date.getDate();
        this.nowDate =  y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		this.params.startTime = this.nowDate;
		this.initUserTree();
	},
	methods:{
		 //初始化树
		 initUserTree:function(){
			 var me = this;
			 $('#user-tree').tree();
			 var tree = {
					 id:"",
					 text:"全部用户",
					 children:[],
					 state:"open",
					 type:"0"
			 }
			 $.ajax({
					url: basePath +'cloud-purchase-web/scDeviceRelationController/getConsTree',
					method: 'get',
// 					data:$.toJSON({"pagingFlag":false}),
	                contentType : 'application/json;charset=UTF-8',
	                success : function(data) {
	                	if(data.flag == true){
	                		for(var i=0;i<data.data.length;i++){
	 	                	   var json={"text":data.data[i].consName,"checked":false,"consId":data.data[i].consId, };
	 	                	   tree.children.push(json);
	 	                	}
	 	                	$('#user-tree').tree('append',{
	 	    				 	data: tree
	 	    				});
	                	}else{
	                		MainFrameUtil.alert({title:"提示",body:"选择用户树，数据查询失败！"});
	                	}
	                },
	                error : function() {
	                	MainFrameUtil.alert({title:"失败提示",body:"网络连接错误,请刷新页面重试"})
	                }
	         });
			 $('#user-tree').tree({
				 onClick:function(node){
					 var time = me.params.startTime;
					 //node.consId==""//全部用户
					 me.consId=node.consId;
					 me.search();				
				 }
	         })
		 },
		 
		 search: function(){
			 var me = this;
			//开进度条
           	$.messager.progress();
			 $.ajax({
					url: basePath +'cloud-purchase-web/scDeviceRelationController/getRealTimePower',
					method: 'POST',
					data:$.toJSON({"consId": me.consId, "ymd": me.params.startTime}),
	                contentType : 'application/json;charset=UTF-8',
	                success : function(data) {
	                	if(data.flag == true){
	                		if(data.data != null){
		                		me.pageData = data.data;
								me.vFor = [];//清空 要不然会一直累积
								for(var i in data.data.vFor){
									me.vFor.push({name:data.data.vFor[i]});
								}
								//15分钟
								if(typeof minuteVue != 'undefined'){
									var vFor = [];
									for(var i in data.data.vFor){
										vFor.push({name:data.data.vFor[i]});
									}
									minuteVue.readyFun(vFor, data.data, me.consId);
								}
								 //5分钟
								 if(typeof fiveVue != 'undefined'){
									var ff = [];
									for(var i in data.data.vFor){
										ff.push({name:data.data.vFor[i]});
									}
									fiveVue.readyFun(ff, data.data, me.consId);
								 }
	                		}
	                		//关闭进度条
	                		$.messager.progress('close');
	                	}else{
	                		//关闭进度条
	                		$.messager.progress('close');
	                		MainFrameUtil.alert({title:"提示",body:"电量信息查询失败！"});
	                	}
	                },
	                error : function() {
	                	//关闭进度条
                		$.messager.progress('close');
	                	MainFrameUtil.alert({title:"失败提示",body:"网络连接错误,请刷新页面重试"})
	                }
	         });
			 
			 
		 }
		 
		

	},
 	events:{
        //监听选项卡切换事件
        tabPanelSelectedChangeBefor: function(val){
	 		var me = this;
        	if(val.index == 0){    
        		return;
        	}else{
//         		MainFrameUtil.setParams({vFof:me.vFor,data:me.pageData},"five-page");
//         		fiveVue.vFor = me.vFor;
// 				 fiveVue.initFiveGrid();
        	}
        }
    },

	watch: {
		"params.startTime": function(val){
			this.search();
		}
	}
}); 
</script>
</body>
</html>