<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
       <head>
             <title>监控平台-实时电量报表5/15分钟分钟</title>
       
</head>
<body>
<div id="fiveVue" v-cloak style="padding-bottom: 1px;height:auto">
	<div v-if=" vFor != null && vFor.length > 0">
		<div v-for="name in vFor" id="five" class="height-fill" v-cloak>
				<mk-panel :title="name.name + ' 5分钟电量信息（千瓦时）'" line="true" header="" height="400px">
				 	<div id="{{name.name}}EchartFive" style="height:100%;width:100%">
				 	</div>
				</mk-panel>
				<mk-panel header="false" height="350px">
			        <div id="{{name.name}}TableFive" style="height:100%;width:100%">
			        </div>
				</mk-panel>
		</div>
	</div>
	<div v-else>
		<div id="userManager" class="height-fill" v-cloak>
				<mk-panel title="5分钟电量信息（千瓦时）" line="true" header="" height="400px">
				 	<div id="fiveEchart" style="height:100%;width:100%">
				 	</div>
				</mk-panel>
				<mk-panel header="false" ine="true" height="350px">
			        <div id="fiveTable" style="height:100%;width:100%">
			        </div>
				</mk-panel>
		</div>
    </div>
       <script type="text/javascript">
       $(window).resize(throll(function(){
			 if(
					 fiveVue.fiveEchartSize!=null && $("#fiveVue").height()>60 && $("#fiveVue").width()>100){
				 	for(var i in fiveVue.fiveEchartSize){
				 		fiveVue.fiveEchartSize[i].resize();
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
             //对应 js
             var fiveVue =new Vue({
                    el: '#fiveVue',
                    data: {
                    	fiveEchartSize: {},//Echart自适应
                    	vFor:[],//循环样式需要
                    },
                    ready:function(){
                    	//全部用户
            			if(listVue.consId == null || listVue.consId == "" || typeof(listVue.consId) == "undefined"){
            				//多个营销户号
            				if(listVue.vFor.length > 1){
            					this.vFor.push(listVue.vFor[0]);
            				}
            			}else{
	                    	this.vFor =  listVue.vFor;
            			}
                    	//样式加载完成后
            			this.$nextTick(function(){
	                    	this.readyFun(listVue.vFor, listVue.pageData, listVue.consId);
            			})
                    },
                    methods: {
                    	
                    	readyFun: function(vFor, pageData, consId){
                    		var me = this;
                    		if(vFor == null || vFor.length < 1){
                    			this.vFor = [];
                    			this.$nextTick(function(){
	                    			this.initFifEchart("fiveEchart", null, null);
	                    			this.initFiveGrid("fiveTable", null);
                            	})
                    		}else{
                    			//全部用户
                       			if(consId == null || consId == "" || typeof(consId) == "undefined"){
                       				this.vFor = [];
                       				//多个营销户号
                       				if(vFor.length > 1){
                       					vFor.splice(1,vFor.length-1);
                       					this.vFor.push(vFor[0]);
                       				}else if(vFor.length == 1){//一个营销户号
                       					me.vFor.push(vFor[0]);
                       				}
                       			}else{
                       				this.vFor =vFor;
                       			}
                        		this.$nextTick(function(){
        	                    	this.dataDispose(this.vFor, pageData);
                            	})
                    		}
                    	},
                    	dataDispose: function(numArr, data){
                    		var me = this;
                    		for(var i in numArr){
                    			var echartDataKey = numArr[i].name;
                    			var echartId = echartDataKey +'EchartFive';
                    			var tableId = echartDataKey +'TableFive';
                    			//用于图自适应
//                     			(me.fiveEchart).push(echartId);
                    			
                    			me.initFifEchart(echartId, data.fiveMinTimeList, data[echartId]);              			
                    			me.initFiveGrid(tableId, data[tableId]);              			
                    		}
                    	},
                    	initFifEchart: function(echartId, xAxis, data){
                    		var me = this;
                    		me.fiveEchartSize[echartId] = echarts.init(document.getElementById(echartId));
							option = {
								color:['#25c2f4'],
							     tooltip: {
							        trigger: 'axis',
							        axisPointer: {
							            type: 'cross'
							        }
							    },
							    xAxis: {
							        type: 'category',
							        data: xAxis
							    },
							    yAxis : [
							        {   
							            name: '电量',
							            type : 'value'
							        }
							    ],
							    series: [{
							        data: data,
							        type: 'line'
							    }]
							};
							me.fiveEchartSize[echartId].setOption(option);
                    	
                    	},
                    	
                    	initFiveGrid: function(tableId, data){
                    		//列表数据加载
	       					 ComponentUtil.buildGrid("selling.monitor.fiveMinute",$("#"+tableId),{ 
// 	       						url: basePath + 'cloud-purchase-web/ssFeedbackController/page',
	       					    width:"99%",
	       					    data: data,
	       					    scrollbarSize : 0,
	       					    method: 'post',
// 	       					    queryParams:this.,
	       				        striped:true,
	       					    rownumbers: true,
// 	       					    pagination: true,
	       					    singleSelect:false,
	       					    nowrap: true,
	       					    fitColumns: true,
	       					    pageSize: 24,
	       					    pageList: [24],
	       					    rowStyler:function(idx,row){
	       					        return "height:35px;font-size:12px;";
	       					    },
	       				   	 }); 
                    	}
                    	
                    },
                    watch:{
                    	
                    }
             });
       </script>
</div>
</body>
</html>