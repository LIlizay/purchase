<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
       <head>
             <title>监控平台-实时电量报表5/15分钟分钟</title>
       
</head>
<body>
<div id="minuteVue" v-cloak style="padding-bottom: 1px;height:auto;padding-left:  0;">
	<div v-if=" vFor != null && vFor.length > 0">
		<div v-for="name in vFor" id="userManager" class="height-fill" v-cloak>
				<mk-panel :title="name.name+' 15分钟电量信息（千瓦时）'" line="true" header="" height="400px">
				 	<div id="{{name.name}}Echart" style="height:100%;width:100%">
				 	</div>
				</mk-panel>
				<mk-panel header="false" line="true" height="350px">
			        <div id="{{name.name}}Table" style="height:100%;width:100%">
			        </div>
				</mk-panel>
		</div>
	</div>
	<div v-else>
		<div id="userManager" class="height-fill" v-cloak>
				<mk-panel title="15分钟电量信息（千瓦时）" line="true" header="" height="400px">
				 	<div id="Echart" style="height:100%;width:100%">
				 	</div>
				</mk-panel>
				<mk-panel header="false" ine="true" height="350px">
			        <div id="Table" style="height:100%;width:100%">
			        </div>
				</mk-panel>
		</div>
    </div>
	
       <script type="text/javascript">
       $(window).resize(throll(function(){
			 if(
				 minuteVue.minuteEchart!=null && $("#minuteVue").height()>60 && $("#minuteVue").width()>100){
				 	for(var i in minuteVue.minuteEchart){
				 		minuteVue.minuteEchart[i].resize();
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
             var minuteVue =new Vue({
                    el: '#minuteVue',
                    data: {
                    	minuteEchart: {},//Echart
                    	vFor:[],//循环样式需要
                    	
                    },
                    ready:function(){
                    	//全部用户
            			if(listVue.consId == null || listVue.consId == "" || typeof(listVue.consId) == "undefined"){
            				//多个营销户号
            				if(listVue.vFor.length > 1){
            					this.vFor = [];
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
                    		debugger;
                    		//无数据
                    		if(vFor == null || vFor.length < 1){
                    			this.vFor = [];
                    			this.$nextTick(function(){
	                    			this.initFifEchart("Echart", null, null);
	                    			this.initFifGrid("Table", null);
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
                    			var echartId = echartDataKey +'Echart';
                    			var tableId = echartDataKey +'Table';
                    			//用于图自适应
//                     			(me.fiveEchart).push(echartId);
                    			
                    			me.initFifEchart(echartId, data.fifMinTimeList, data[echartId]);              			
                    			me.initFifGrid(tableId, data[tableId]);              			
                    		}
                    	},
                    	initFifEchart: function(echartId, xAxis, data){
                    		debugger;
							this.minuteEchart[echartId] = echarts.init(document.getElementById(echartId));
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
							this.minuteEchart[echartId].setOption(option);
								
                    	},
                    	
                    	initFifGrid: function(tableId, data){
                    		//列表数据加载
	       					 ComponentUtil.buildGrid("selling.monitor.fifMinute",$("#"+tableId),{ 
// 	       						url: basePath + 'cloud-purchase-web/ssFeedbackController/page',
								data: data,
	       					    width:"99%",
	       					    scrollbarSize : 0,
	       					    method: 'post',
	       				        striped:true,
	       					    rownumbers: true,
	       					    pagination: true,
	       					    singleSelect:false,
	       					    nowrap: true,
	       					    fitColumns:true,
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