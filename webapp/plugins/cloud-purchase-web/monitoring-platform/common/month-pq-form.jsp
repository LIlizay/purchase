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
<mk-left-right left_width="250px" height="100%" >
  	<mk-panel header='false' slot="left" height="99%"  style="overflow-y: auto;">
		<ul id="user-tree" style="margin: 2% auto 2% auto;height:100%;overflow-y: auto;"></ul>
    </mk-panel>
    <mk-top-bottom slot="right"  height="100%" top_height="auto" style="padding-left:5px" >
		<mk-search-panel slot="top" deployable="false">
			<mk-search-visible>
				<su-form-group label-name='时间' class="form-control-row" col="4" label-width="118px" label-align="right">
		            <su-datepicker format="YYYY" :data.sync="params.startTime"></su-datepicker>
		       	</su-form-group>
			</mk-search-visible>
		</mk-search-panel>
		<div  slot="bottom" style="overflow-y: auto;height:100%;">
			<div v-for="name in vFor">
				<mk-panel :title="name+' 月电量信息（兆瓦时）'" header="" height="400px">
					<div id="{{name}}Echart" style="height:100%;width:100%">
				 	</div>
	    		</mk-panel>
				<mk-panel header="false" height="350px">
					<div id="{{name}}Grid"  style="height:100%;width:100%">
			    	</div>
	    		</mk-panel>
			</div>
			<div v-show="vforFlag">
				<mk-panel title="月电量信息（兆瓦时）" header="" height="400px">
					<div id="Echart" style="height:100%;width:100%">
				 	</div>
	    		</mk-panel>
				<mk-panel header="false" height="350px">
					<div id="Grid"  style="height:100%;width:100%">
			    	</div>
	    		</mk-panel>
			</div>	
		</div>	
    </mk-top-bottom>
</mk-left-right>


            
<script type="text/javascript">
$(window).resize(throll(function(){
	 if(
			 listVue.dayPqEchart!=null && $("#listVue").height()>60 && $("#listVue").width()>100){
		 	for(var i in listVue.dayPqEchart){
		 		listVue.dayPqEchart[i].resize();
		 	}
		}
},100));
function throll(fn,delay){
   var timer=null;
   return function(){
       clearTimeout(timer);
       timer=setTimeout(fn,delay);
   }
} 
var basePath = '${Config.baseURL}';
var listVue = new Vue({
	el: '#listVue',
	data:{
		dayPqEchart: {},
		initIndext:0,//选中页的绑定属性
		vforFlag: true,//是否显示无数据时的样式
		nowDate: null,//当前 年月日
		consId: null,
		vFor: null,//循环样式需要
		params:{
			startTime: null,
		}
	},

	ready:function(){
		var date = new Date();
		var y = date.getFullYear();
// 	    var m = date.getMonth()+1;
//         var d = date.getDate();
//         this.nowDate =  y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
		this.nowDate = y;
		this.params.startTime = y;
		
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
//					data:$.toJSON({"pagingFlag":false}),
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
					 me.consId=node.consId;
					 me.search();				
				 }
	         })
		 },
		 
		 initGrid: function(gridId, gridData){
			//列表数据加载
				 ComponentUtil.buildGrid("selling.monitor.daypqform",$("#"+gridId),{ 
				    width:"100%",
				    data: gridData,
				    method: 'post',
			        striped:true,
				    rownumbers: true,
				    singleSelect:false,
				    nowrap: true,
				    fitColumns:true,
				    pageSize: 10,
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    columnsReplace:[   
				        {field:'device',title:'月份'},    
				    ],
			   	 }); 
		 },
		 
		 search: function(){
			//开进度条
           	$.messager.progress();
			 var me = this;
			 $.ajax({
					url: basePath +'cloud-purchase-web/scDeviceRelationController/getMonthPqForm',
					method: 'POST',
					data:$.toJSON({"consId": me.consId, "year": me.params.startTime}),
	                contentType : 'application/json;charset=UTF-8',
	                success : function(data) {
	                	if(data.flag == true){
	                		if(data.data != null){
		                		var vFor = data.data.vFor;
		                		//全部用户
                       			if(me.consId == null || me.consId == "" || typeof(me.consId) == "undefined"){
                       				me.vFor = [];
                       				//多个营销户号
                       				if(vFor.length > 1){
                       					vFor.splice(1,vFor.length-1);
                       					me.vFor.push(vFor[0]);
                       				}else if(vFor.length == 1){//一个营销户号
                       					me.vFor.push(vFor[0]);
                       				}
                       			}else{
                       				me.vFor =vFor;
                       			}
		                	
		                		me.$nextTick(function(){
			                		if(vFor != null && vFor.length > 0){
			                			me.vforFlag = false;
			                			var echart =data.data.echart;
				                		var table = data.data.table;
				                		for(var i in vFor){
				                			me.initEchart(vFor[i]+"Echart", data.data.echartXList, echart[i])
				                			me.initGrid(vFor[i]+"Grid",table[i]);
				                		}
			                		}else{
			                			me.initEchart("Echart", data.data.echartXList, [null, null, null, null, null])
			                			me.initGrid("Grid",null);
			                		}
		                		})
		                		
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
		 },
		 
		 initEchart: function(echartId, echartXList,echartData){
			this.dayPqEchart[echartId] =echarts.init(document.getElementById(echartId));
			option = {
				    color: ['#27aa8d','#a4ebdd','#7dd1c0','#cccccc','#e0b167'],
				    tooltip : {
				        trigger: 'axis',
				        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
				            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
				        }
				    },
				    legend: {
				        data:['峰','平','谷','尖峰','低谷']
				    },
				    grid: {
				        left: '3%',
				        right: '4%',
				        bottom: '3%',
				        containLabel: true
				    },
				    xAxis : [
				        {
				            type : 'category',
				            data : echartXList,
				        }
				    ],
				    yAxis : [
				        {   
				            name: '电量',
				            type : 'value'
				        }
				    ],
				    series : [
				        {
				            name:'峰',
				            type:'bar',
				            stack: '电量',
				            barMaxWidth: '30%',
				            data: echartData[0]
				        },
				         {
				            name:'平',
				            stack: '电量',
				            type:'bar',
				            data:echartData[1]
				        },
				        {
				            name:'谷',
				            type:'bar',
				            stack: '电量',
				            data: echartData[2]
				        },
				        {
				            name:'尖峰',
				            stack: '电量',
				             type:'bar',
				            data: echartData[3]
				        },
				        {
				            name:'低谷',
				            type:'bar',
				            stack: '电量',
				            data: echartData[4]
				        },
				    ]
				};
				this.dayPqEchart[echartId].setOption(option);
     	
     	},
		 
		

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