$.fn.extend({
	createEcharts:function(obj,type){
		var dom = this[0];
		var option = {
				title:{
		            x:"center",//水平位置
		            y:"top",//垂直位置
		            itemGap:5 //主副标题纵向间隔，单位px，默认为10 number
				},
		        legend : {
		        	top:30,
		            x:"center",//水平位置 right center left
		            y:"top",//垂直位置 top center bottom
		            itemWidth:20 //图例图形宽度
		            
		        },
				tooltip: {
			        trigger: "axis"
			    },
			    series:[]
		}
		if(type==="pie"){
			option.tooltip.trigger = "item";
		}else if(type==="line"){
			option.tooltip.trigger = "axis";
			option.yAxis = {type: 'value', min:'dataMin'};
			option.xAxis = {boundaryGap: true };
		}else if(type==="bar"){
			option.tooltip.trigger = "axis";
			option.yAxis = {type: 'value'};
			option.xAxis = {boundaryGap: true };
		}else{
			
		}
		var color = ["#FF0000","#0CD7AA","#FF8000","#005CE6","#E522D6","#8000FF","#6ACC00","#666666"];
		for(var i in obj.series){
			var defaultSerise = null;
			if(obj.series[i].type === "pie"){
				defaultSerise = {radius: '60%',itemStyle: {
                    normal:{
                        label:{show: true, formatter: '{b} : ({d}%)' }, 
                        labelLine :{show:true} 
                    }
                }};
			}else if(obj.series[i].type === "line"){
				defaultSerise = {itemStyle:{normal:{label:{show:true}}},symbol:"rect"};
				if(i < color.length){
					defaultSerise.lineStyle = {};
					defaultSerise.lineStyle.normal = {};
					defaultSerise.lineStyle.normal.color = color[i];
					defaultSerise.itemStyle.normal.color = color[i];
				}
			}else{
				defaultSerise ={}
			}
			$.extend(true,defaultSerise,obj.series[i]);
			option.series.push(defaultSerise);
		}
		delete obj.series;
		$.extend(true,option,obj);
		var myChart = echarts.init(dom);
		myChart.setOption(option);
		return myChart;
    }
})