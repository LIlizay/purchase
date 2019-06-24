//存放option的变量
var contObject = {title:null,timeline:null,toolbox:null,tooltip:null,legend:null,grid:null,xAxis:[],yAxis:[],series:[]};
var textContent = {
//生成eCharts对象   optionData:自定义数据源
			    getEcharts:function(type,url,urlParams,content,dom,optionData){//type:生成图形类型；url访问数据路径；urlParams:后台访问参数；content:生成图表的对象；dom:图形放置位置的id
			        var option;
			        if(type === null || type === ""){
			            MainFrameUtil.alert({title:"失败提示",body:"请设置图形的类型"});
			            return;
			        }
			        if(type === "bar" || type === "line"){
			            option = {title:null,/* timeline:null, */toolbox:null,tooltip : null,legend: null,grid: null,xAxis : null,yAxis : null,
			                    series :null};
			        }else if(type === "pie"){
			            option = {title:null,/* timeline:null, */toolbox:null,tooltip : null,legend: null,grid: null,series :null};
			        }
			        var me = this; 
			        if(url !== null && url !== ""){
			            //根据url获取数据
			            $.ajax({
			                url:url,
			                type:"post",
			                data:$.toJSON(urlParams),//urlParams：应包含legend.data，返回data包含：xdataList:x轴数据
			                contentType:'application/json;charset=UTF-8',  //dataSourceList系列
			                success:function(data){
			                   if(data.flag == true && data.data !== null){
			                       option.title = content.title;//设置title
			                       //option.timeline = content.timeline;//设置timeline
			                       option.toolbox = content.toolbox;//设置toolbox
			                       option.tooltip = content.tooltip;//设置tooltip
			                       option.legend = content.legend;//设置legend
			                       option.grid = content.grid;//设置grid
			                       //按图标类型设置图标数据
			                       var legend = content.legend.data;//图列数据
			                       if(type === "bar" || type === "line" || type === "scatter"){
			                           var xdataList =  [data.data.xdataList]; //xAxis轴数据
			                           var dataSource = data.data.dataSource;//图形数据
			                           var dataModel= [content.xAxis,content.yAxis,content.series]; //自定义的xAxis 、yAxis、series数据
			                           var result = me.getAngleMessage(xdataList,dataSource,legend,dataModel,type);
			                           option.xAxis = result[0];
			                           option.yAxis = result[1];
			                           option.series = result[2];
			                       }else if(type === "pie"){
			                           var title = content.title.text;
			                           var dataSource = data.data.dataSource;
			                           var dataModel = content.series;
			                           var result = me.getPieMessage(dataSource,legend,title,dataModel,type);
			                           option.series = result; 
			                       }
			                       // 基于准备好的dom，初始化echarts实例
			                       var myChart = echarts.init(document.getElementById(dom));
			                       // 绘制图表
			                       myChart.setOption(option);
			                   }else{
			                       MainFrameUtil.alert({title:"失败提示",body:data.msg});
			                   }
			                },
			                 error:function() {
			                     MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
			                 }
			            })
			        }else{//如果不请求后台获取，则通过自己定义的方式赋值
			            option.title = content.title;//设置title
			            //option.timeline = content.timeline;//设置timeline
			            option.toolbox = content.toolbox;//设置toolbox
			            option.tooltip = content.tooltip;//设置tooltip
			            option.legend = content.legend;//设置legend
			            option.grid = content.grid;//设置grid
			            if(type === "bar" || type === "line" || type === "scatter"){
//			                for(var i=0; i<content.xAxis.length; i++){
//			                    if(content.xAxis[i].data === null || content.xAxis[i].data.length<1){
			 //                       MainFrameUtil.alert({title:"失败提示",body:"请设置图形的数据"});
			 //                       return;
			 //                   }
//			                }
			                if(optionData === null || optionData.length<1){
			                    MainFrameUtil.alert({title:"失败提示",body:"请设置图形的数据"});
			                    return;
			                }
			                var xAxisObj = this.getAxisObj(content.xAxis);
			                var yAxisObj = this.getAxisObj(content.yAxis);
			                if(content.series.length == 1){
			                	 for(var j=1; j< content.legend.data.length; j++){
			                		 content.series.push(content.series[0]);
			                	 }
			                }
			                for(var obj in content.series){
			                	content.series[obj] = this.getSeriesObj("angle",content.series[obj],type);
			                	if(content.series[obj].type === null || content.series[obj].type === ""){
			                		content.series[obj].type=type;
			                	}
			                	if(content.series[obj].name === null || content.series[obj].name === ""){
			                		if(obj > content.legend.data.length -1){
				                		content.series[obj].name = content.legend.data[content.legend.data.length -1];
				                	}else{
				                		content.series[obj].name = content.legend.data[obj];
				                	}
			                	}
			                	if(content.series[obj].data === null || content.series[obj].data.length < 1){
			                		content.series[obj].data = optionData[obj];
			                	}
			                }
			                //如果图列的内容唯一，则stack为null
			                if(content.legend.data.length < 2){
			                    seriesObj.stack = null;
			                }
			                option.xAxis = xAxisObj;//设置xAxis
			                option.yAxis = yAxisObj;//设置yAxis
			                option.series = content.series;//设置series
			                // 基于准备好的dom，初始化echarts实例
			                var myChart = echarts.init(document.getElementById(dom));
			                // 绘制图表
			                myChart.setOption(option);
			            }else if(type === "pie"){
			                if(optionData === null || optionData.length <1){
			                    MainFrameUtil.alert({title:"失败提示",body:"请设置图形的数据"});
			                    return;
			                }
			                for(var obj in content.series){
			                	content.series[obj] = this.getSeriesObj("round",content.series[obj],type);
			                }
			                //生产series
			                var seriesArray = new Array();
			                for(var j=0; j< content.legend.data.length; j++){
			                    seriesArray.push({value:optionData[j],name:content.legend.data[j]});
			                }
			                if(content.series[0].name === null || content.series[0].name === ""){
			                	content.series[0].name = content.title.text;
			                }
			                if(content.series[0].type === null || content.series[0].type === ""){
			                	content.series[0].type = type;
			                }
			                if(content.series[0].data === null || content.series[0].data.length < 1){
			                	content.series[0].data = seriesArray;
			                }
			                option.series = content.series[0];//设置series
			                // 基于准备好的dom，初始化echarts实例
			                var myChart = echarts.init(document.getElementById(dom));
			                // 绘制图表
			                myChart.setOption(option);
			            }
			        }
			    },
			    
			    //对整体对象赋值   type:要设置的对象如"title" or "tooltip"类型;obj:要设置对象内容
			    setContObject:function(type,obj){
			        switch(type){
			            case "title":
			                contObject.title = obj;
			                break;
			            case "tooltip":
			                contObject.tooltip = obj;
			                   break;
			            case "timeline":
			                contObject.timeline = obj;
			                   break; 
			            case "toolbox":
			                contObject.toolbox = obj;
			                   break;
			            case "legend":
			                contObject.legend = obj;
			                   break;
			            case "grid":
			                contObject.grid = obj;
			                   break;
			            case "xAxis":
			                contObject.xAxis.push(obj);
			                   break;
			            case "yAxis":
			                contObject.yAxis.push(obj);
			                   break;
			            case "series":
							var cont = obj;
							if(obj[0] != 0){
								if(contObject.series.length == obj[0]){
									contObject.series[obj[1]] = obj[2];
								}else{
									for(var i=0; i < obj[0] ; i++){
										contObject.series.push(this.getSeries());
									}
									contObject.series[obj[1]] = obj[2];
								}
							}
							break;
			        }
			    },
			    
			    //获取对象
			    getContObject:function(){
			        var obj = new Object();
			        if(contObject.title === null){//未对其进行设置时采用默认对象
			            obj["title"] = this.getTitle();
			        }else{
			            obj["title"] = contObject.title;
			        }
			        if(contObject.timeline === null){
			            obj["timeline"] = this.getTimeline();
			        }else{
			            obj["timeline"] = contObject.timeline;
			        }
			        if(contObject.toolbox === null){
			               obj["toolbox"] = this.getToolbox();
			           }else{
			            obj["toolbox"] = contObject.toolbox;
			           }
			        if(contObject.tooltip === null){
			               obj["tooltip"] = this.getTooltip();
			           }else{
			            obj["tooltip"] = contObject.tooltip;
			           }
			        if(contObject.legend === null){
			               obj["legend"] = this.getLegend();
			         }else{
			            obj["legend"] = contObject.legend;
			         }
			        if(contObject.grid === null){
			            obj["grid"] = this.getGrid();
			        }else{
			         obj["grid"] = contObject.grid;
			        }
			        if(contObject.xAxis === null || contObject.xAxis.length == 0){
			            var xAxis = new Array();
			            xAxis.push(this.getxAxis());
			            obj["xAxis"] = xAxis;
			        }else{
			         obj["xAxis"] = contObject.xAxis;
			        }
			        if(contObject.yAxis === null || contObject.yAxis.length == 0){
			            var yAxis = new Array();
			            yAxis.push(this.getyAxis());
			            obj["yAxis"] = yAxis;
			        }else{
			            obj["yAxis"] = contObject.yAxis;
			        }
			        if(contObject.series === null || contObject.series.length == 0){
			            obj["series"] = [this.getSeries()];
			        }else{
			         obj["series"] = contObject.series;
			        }
			        this.rest();//清空contObject
			        return obj;
			    },
			    
			    //清空contObject
			    rest:function(){
			        contObject = {title:null,timeline:null,toolbox:null,tooltip:null,legend:null,grid:null,xAxis:[],yAxis:[],series:[]}
			    },
			    
			    //获取默认title对象 标题
			    getTitle:function(){
			        var titleResult={
			            text:"",//主标题
			            subtext:"",//子标题
			            x:"left",//水平位置
			            y:"top",//垂直位置
			            link:"",//主标题超链接
			            target:"",//超链接打开方式：self or blank
			            sublink:"",//副标题文本超链接
			            subtarget:null,//指定窗口打开副标题超链接，支持'self' | 'blank'
			            textAlign:null,//水平对齐方式，默认根据x设置自动调整，可选为： left' | 'right' | 'center
			            backgroundColor:"rgba(0,0,0,0)",//  标题背景颜色，默认透明
			            borderColor:"ccc",//标题边框颜色
			            borderWidth:0,//标题边框线宽，单位px，默认为0（无边框）
			            padding:5,//标题内边距，单位px，默认各方向内边距为5 number Array
			            itemGap:5,//主副标题纵向间隔，单位px，默认为10 number
			            textStyle:{//主标题文本样式
			                fontSize:18,
			                fontWeight:"bolder",
			                color:"#333"
			            },
			            subtextStyle:{color:"#aaa"},//副标题文本样式
			        };
			        return titleResult;
			    },
			    
			    //获取默认的timeline对象：时间轴
			    getTimeline:function(){//bar/scatter/pie/map
			        var timeline = {
			            show:false,//显示
			            type:"time",//时间轴类型 时间轴间隔根据时间跨度自动计算 time number
			            notMerge:false, //时间轴上多个option切换时是否进行merge操作
			            realtime:true,//拖拽或点击改变时间轴是否实时显示
			            x:80,//时间轴左上角横坐标，数值单位px，支持百分比（字符串）
			            y:null,//时间轴左上角纵坐标，数值单位px，支持百分比（字符串）
			            x2:80,//时间轴右下角横坐标，数值单位px，支持百分比（字符串）
			            y2:0,//时间轴右下角纵坐标，数值单位px，支持百分比（字符串）
			            width:'50%', //时间轴宽度 支持百分比（字符串），如'50%'
			            height:50,//时间轴高度，数值单位px，支持百分比
			            backgroundColor:"rgba(0,0,0,0)",//背景颜色，默认透明
			            borderWidth:0,//边框线宽
			            borderColor:"#ccc",//边框颜色
			            padding:5,//内边距，单位px，默认各方向内边距为5，接受数组分别设定上右下左边距 number Array
			            controlPosition:"left",//播放控制器位置 left right none
			            autoPlay:false,//是否自动播放
			            loop:true,//是否循环播放
			            playInterval:2000,//播放时间间隔，单位ms
			            lineStyle:{//时间轴轴线样式，lineStyle控制线条样式
			                color:"#666",
			                width: 1,
			                type:"dashed"
			            },
			            label:{//时间轴标签文本
			                show: true,
			                interval: "auto",
			                rotate: 0,//旋转角度，默认为0，不旋转，正值为逆时针，负值为顺时针，可选为：-90 ~ 90
			                formatter: null,// 间隔名称格式器：{string}（Template） | {Function} 
			                textStyle: {
			                    color: "#333"
			                }
			            },
			            checkpointStyle:{//时间轴当前点
			                symbol : "auto",//当前点symbol，默认随轴上的symbol 
			                symbolSize : "auto",//当前点symbol大小，默认随轴上symbol大小 
			                color : "auto",//当前点symbol颜色，默认为随当前点颜色，可指定具体颜色，如无则为'#1e90ff'
			                borderColor : "auto",//当前点symbol边线颜色 
			                borderWidth : "auto",//当前点symbol边线宽度
			                label: {//label同上
			                    show: false,
			                    textStyle: {
			                        color: "auto"
			                    }
			                }
			            },
			            controlStyle:{//时间轴控制器样式
			                itemSize: 15,//按钮大小
			                itemGap: 5,//按钮间隔
			                normal : {//正常
			                    color : "#333"
			                },
			                emphasis : {//高亮
			                    color : "#1e90ff"
			                }
			            },
			            symbol:"emptyDiamond",//轴点symbol，同serie.symbol
			            symbolSize:4,//轴点symbol，同serie.symbolSize
			            currentIndex:0,//当前索引位置，对应options数组，用于指定显示特定系列
			            data:null,//   时间轴列表，同时也是轴label内容
			        }
			        return timeline;
			    },
			    
			    //获取默认的xAxis对象 x轴坐标
			    getxAxis:function(){
			        var xAxisResult = {
			            type:"category",//坐标轴类型  category:类目型； value：数值型； time:时间型  log
			            show:true,//显示
			            zlevel:0,//一级层叠控制
			            z:0,//二级层叠控制
			            position:"bottom",//坐标轴类型 bottom left top right
			            name:"",//坐标轴名称(数值型、时间型)
			            nameLocation:"end",//坐标轴名称位置(数值型、时间型) start end
			            nameTextStyle:null,//坐标轴名称文字样式，默认取全局配置，颜色跟随axisLine主色，可设(数值型、时间型)
			            boundaryGap:false,//类目起始和结束两端空白策略(类目型)
			            min:null,//指定的最小值(数值型、时间型) 
			            max:null,//指定的最大值(数值型、时间型)
			            scale:false,//脱离0值比例(数值型、时间型)
			            splitNumber:null,//分割段数，不指定时根据min、max算法调整(数值型、时间型)
			            logLabelBase:null,//axis.type === 'log'时生效。指定时，axisLabel显示为指数形式，如指定为4时，axisLabel可显示为4²、4³。
			            logPositive:null,//axis.type === 'log'时生效。指明是否使用反向log数轴（从而支持value为负值）
			            axisLine:{//坐标轴线，默认显示
			                show:true,//是否显示
			                onZero:true,//定位到垂直方向的0值坐标上
			                lineStyle:{color:"#48b",width:2,type:"solid"},//属性lineStyle控制线条样式
			            },
			            axisTick:{//坐标轴小标记
			                show:true,//true（类目轴） false（数值轴和时间轴）
			                interval:"auto",//小标记显示挑选间隔(类目型) string | number | function
			                onGap:null,//小标记是否显示为间隔(类目型)，默认等于boundaryGap
			                inside:false,//小标记是否显示为在grid内部，默认在外部
			                length:5,//属性length控制线长
			                lineStyle:{color:"#333",width:1},//属性lineStyle控制线条样式
			            },
			            axisLabel:{//坐标轴文本标签
			                show:true,//是否显示
			                interval:"auto",//标签显示挑选间隔(类目型) string | number | function
			                rotate:0,//标签旋转角度，默认为0，不旋转，正值为逆时针，负值为顺时针，可选为：-90 ~ 90
			                margin:8,//坐标轴文本标签与坐标轴的间距 单位px
			                clickable:false,//坐标轴文本标签是否可点击
			                formatter:null,//间隔名称格式器 String function
			                textStyle:{color:"#333"},//文本样式
			            },
			            splitLine:{//分隔线
			                show:true,//显示
			                onGap:null,//分隔线是否显示为间隔，默认等于boundaryGap(类目型)
			                lineStyle:{color:["#ccc"],width:1,type:"solid"},//属性lineStyle控制线条样式
			            },
			            splitArea:{//分隔区域
			                show:true,//显示
			                onGap:null,//分隔区域是否显示为间隔，默认等于boundaryGap(类目型)
			                areaStyle:{color:["rgba(250,250,250,0)","rgba(200,200,200,0)"]},//控制区域样式，颜色数组实现间隔变换
			            }, 
			            data:null,//类目列表，同时也是label内容
			        };
			        return xAxisResult;
			    },
			    
			    //获取默认的yAxis对象 y轴坐标
			    getyAxis:function(){
			        var yAxisResult = {
			            type:"value",//坐标轴类型  category:类目型； value：数值型； time:时间型  log
			            show:true,//显示  
			            zlevel:0,//一级层叠控制
			            z:0,//二级层叠控制
			            position:"left",//坐标轴类型 bottom left top right
			            name:"",//坐标轴名称(数值型、时间型)
			            nameLocation:"end",//坐标轴名称位置(数值型、时间型) start end
			            nameTextStyle:null,//坐标轴名称文字样式，默认取全局配置，颜色跟随axisLine主色，可设(数值型、时间型)
			            boundaryGap:[0,0],//坐标轴两端空白策略，数组内数值代表百分比(数值型、时间型)
			            min:null,//指定的最小值(数值型、时间型) 
			            max:null,//指定的最大值(数值型、时间型)
			            scale:false,//脱离0值比例(数值型、时间型)
			            splitNumber:null,//分割段数，不指定时根据min、max算法调整(数值型、时间型)
			            logLabelBase:null,//axis.type === 'log'时生效。指定时，axisLabel显示为指数形式，如指定为4时，axisLabel可显示为4²、4³。
			            logPositive:null,//axis.type === 'log'时生效。指明是否使用反向log数轴（从而支持value为负值）
			            axisLine:{//坐标轴线，默认显示
			                show:true,//是否显示
			                onZero:true,//定位到垂直方向的0值坐标上
			                lineStyle:{color:"#48b",width:2,type:"solid"},//属性lineStyle控制线条样式
			            },
			            axisTick:{//坐标轴小标记
			                show:false,//true（类目轴） false（数值轴和时间轴）
			                interval:"auto",//小标记显示挑选间隔(类目型) string | number | function
			                onGap:null,//小标记是否显示为间隔(类目型)，默认等于boundaryGap
			                inside:false,//小标记是否显示为在grid内部，默认在外部
			                length:5,//属性length控制线长
			                lineStyle:{color:"#333",width:1},//属性lineStyle控制线条样式
			            },
			            axisLabel:{//坐标轴文本标签
			                show:true,//是否显示
			                interval:"auto",//标签显示挑选间隔(类目型) string | number | function
			                rotate:0,//标签旋转角度，默认为0，不旋转，正值为逆时针，负值为顺时针，可选为：-90 ~ 90
			                margin:8,//坐标轴文本标签与坐标轴的间距 单位px
			                clickable:false,//坐标轴文本标签是否可点击
			                formatter:null,//间隔名称格式器 String function
			                textStyle:{color:"#333"},//文本样式
			            },
			            splitLine:{//分隔线
			                show:true,//显示
			                onGap:null,//分隔线是否显示为间隔，默认等于boundaryGap(类目型)
			                lineStyle:{color:["#ccc"],width:1,type:"solid"},//属性lineStyle控制线条样式
			            },
			            splitArea:{//分隔区域
			                show:true,//显示
			                onGap:null,//分隔区域是否显示为间隔，默认等于boundaryGap(类目型)
			                areaStyle:{color:["rgba(250,250,250,0)"]},//控制区域样式，颜色数组实现间隔变换
			            }, 
			            data:null,//类目列表，同时也是label内容
			        };
			        return yAxisResult;
			    },
			    
			    //获取默认的series对象  数据内容
			    getSeries:function(){
			        var seriesResult = {
			            zlevel:0,// 一级层叠控制
			            z:0,//二级层叠控制
			            type:null,//图表类型 line bar scatter k pie radar chord force map
			            name:null,//系列名称 如启用legend，该值将被legend.data索引相关
			            tooltip:null,//提示框样式，仅对本系列有效，如不设则用option.tooltip
			            clickable:true,//数据图形是否可点击，默认开启，如果没有click事件响应可以关闭
			            itemStyle:null,//图形样式 
			            data:null,//数据{Array}
			            markPoint:null,//标注 {Array}
			            markLine:null,//标线{Array} 
			            //直角系
			            stack:null,//多组数据的堆积图时使用(折线图、柱状图)
			            step:false,//是否是阶梯线图(折线图)
			            xAxisIndex:0, //xAxis坐标轴数组的索引 number(折线图、柱状图、散点图、k线图)
			            yAxisIndex:0, //yAxis坐标轴数组的索引(折线图、柱状图、散点图、k线图)
			            barGap:"30%",//柱间距离 number String(柱状图)
			            barCategoryGap:"20%",//类目间柱形距离number String(柱状图)
			            barMinHeight:0,//柱条最小高度，可用于防止某item的值过小而影响交互 number (柱状图)
			            barWidth:50,//柱条（K线蜡烛）宽度，不设时自适应 number (柱状图、k线图)
			            barMaxWidth:250,//柱条（K线蜡烛）最大宽度，不设时自适应 (柱状图、k线图)
			            symbol:"emptyCircle",//标志图形类型，默认自动选择  (折线图、散点图)
			            symbolSize:6,//标志图形大小 (折线图 2、散点图 4) number function Array
			            showSymbol:true,
			            symbolRotate:null,//标志图形旋转角度[-180,180] (折线图、散点图)
			            showAllSymbol:false,//标志图形默认只有主轴显示（随主轴标签间隔隐藏策略） (折线图)
			            smooth:false,//平滑曲线显示，smooth为true时lineStyle不支持虚线  (折线图)
			            dataFilter:"nearst",//可选 'nearest', 'min', 'max', 'average' (折线图)
			            large:false,//启动大规模散点图  (散点图)
			            largeThreshold:2000,//大规模散点图自动切换阀值，large为true下有效  (散点图)
			            lineStyle:{
			                    normal:{
			                        type:'solid'	//虚线部分
			                    }
			             },
			            //legendHoverLink:true,//是否启用图例（legend）hover时的联动响应（高亮显示） (折线图、柱状图、散点图)
			            //饼图
			            center:["50%","60%"],//["50%","50%"]
			            radius:["0","55%"],//半径，支持绝对值（px）和百分比  number Array
			            startAngle:90,//开始角度 number
			            minAngle:0,//最小角度
			            roseType:null,//南丁格尔玫瑰图模式，'radius'（半径） | 'area'（面积）
			            clockWise:true,//显示是否顺时针
			            selectedOffset:10,//选中是扇区偏移量
			            selectedMode:null,//boolean string 或single，multiple
			            legendHoverLink:true,//是否启用图例  boolean
			        };
			        return seriesResult;
			    },
			    
			    //获取默认的grid对象 直角坐标系内绘图网格
			    getGrid:function(){
			        var gridResult = {
			            zlevel:0,//一级层叠控制
			            z:0,//二级层叠控制
			            x:50,// 直角坐标系内绘图网格左上角横坐标 单位px 也可以 %
			            y:50,//直角坐标系内绘图网格左上角坐标 单位px 也可以 %
			            x2:0,//直角坐标系内绘图网格右下角横坐标  单位px 也可以 %
			            y2:0,//直角坐标系内绘图网格右下角纵坐标 单位px 也可以 %
			            width:200,//网格宽度
			            height:200,//网格高度  
			            backgroundColor:"rgba(0,0,0,1.0)",//背景颜色，默认透明。
			            borderWidth:1,//边框线宽
			            borderColor:"#ccc",//边框颜色。
			        }
			        return gridResult;
			    },
			    
			    //获取默认的toolbox的对象：工具箱
			    getToolbox:function(){
			        var toolboxResult={
			            show:false, //工具箱的显示true false
			            zlevel:0,//一级层叠控制
			            z:6,//二级层叠控制
			            orient:"horizontal",//布局方式，默认为水平布局，可选为：'horizontal' | 'vertical'
			            x:"right", //水平位置 right center left
			            y:"top",//垂直位置top center bottom
			            backgroundColor:"rgba(0,0,0,0)",//工具箱背景颜色，默认透明
			            borderColor:"#ccc",//工具箱边框颜色
			            borderWidth:0,//工具箱边框线宽，单位px，默认为0（无边框）
			            padding:5,//工具箱内边距，单位px number Array
			            itemGap:10,//各item的间隔
			            itemSize:16,//工具箱icon大小，单位（px）
			            color:["#1e90ff","#22bb22","#4b0082","#d2691e"],//工具箱icon颜色序列，循环使用 Array
			            disableColor:"#ddd",//禁用颜色定义
			            effectiveColor:"red",//生效颜色定义
			            showTitle:true,//是否显示工具箱文字提示，默认启用
			            feature:{//启用功能
			                mark : {//辅助线标志
			                    show : false,//显示
			                    title : {//辅助线的名称
			                        mark : '辅助线开关',//第一个辅助线
			                        markUndo : '删除辅助线',//第二个辅助线
			                        markClear : '清空辅助线'//第三个辅助线
			                    },
			                    lineStyle : {//辅助线的样式
			                        width : 2,//宽
			                        color : '#1e90ff',//颜色
			                        type : 'dashed'//类型
			                    }
			                },
			                dataZoom : {//框选区域缩放
			                    show : false,//显示
			                    title : {//框选区域缩放名称
			                        dataZoom : '区域缩放',//第一个框选区域缩放名称
			                        dataZoomReset : '区域缩放后退'//第二个框选区域缩放名称
			                    }
			                },
			                dataView : {//数据视图
			                    show : false,//显示
			                    title : '数据视图',//名称
			                    readOnly: false,//true:只读 ； false:编辑
			                    optionToContent:function(){},//自主编排数据视图的显示内容
			                    contentToOption:function(){},//readOnly为false时,会出现刷新按钮
			                    lang: ['数据视图', '关闭', '刷新']//数据视图上的话术
			                },
			                magicType: {//动态类型切换
			                    show : false,//显示
			                    title : {//标题
			                        line : '折线图切换',
			                        bar : '柱形图切换',
			                        stack : '堆积',
			                        tiled : '平铺',
			                        force: '力导向布局图切换',
			                        chord: '和弦图切换',
			                        pie: '饼图切换',
			                        funnel: '漏斗图切换'
			                    },
			                    option: {//可传入切换是动态修改的配置
			                        // line: {...},
			                    },
			                    type : []//切换的内容 {Array} type ['line', 'bar', 'stack', 'tiled', 'force', 'chord', 'pie', 'funnel']
			                },
			                restore : {//还原，复位原始图表
			                    show : false,
			                    title : '还原'
			                },
			                saveAsImage : {//保存图片
			                    show : false,
			                    title : '保存为图片',
			                    type : 'png', //图片的类型
			                    lang : ['点击保存']//保存话术
			                }
			            }
			        }
			        return toolboxResult;
			    },
			    
			    //获取默认的tooltip对象 提示框
			    getTooltip:function(){
			        var tooltipResult = {
			            show:true,
			            zlevel:1,//一级层叠控制
			            z:8,//二级层叠控制
			            showContent:true,//内容显示策略，只触发不显示内容则 为false
			            trigger:"axis",//触发类型 item：数据触发； axis：
			            position:null,//位置指定 可赋值[x,y] 或function([x, y]) {return [newX,newY]}
			            formatter:null,//内容格式器 String 或 function
			            islandFormatter:"{a} < br/>{b} : {c}",//拖拽重计算，数据格式器 String 或 function
			            showDelay:20,//显示延迟 ms
			            hideDelay:100,//隐藏延迟，单位ms
			            transitionDuration:0.4,//动画变换时长，单位s
			            enterable:false,//鼠标是否可进入详情气泡中，默认为false
			            backgroundColor:"rgba(0,0,0,0.7)",//提示背景颜色，默认为透明度为0.7的黑色
			            borderColor:"#333",//提示边框颜色
			            borderRadius:4,//提示边框圆角，单位px，默认为4
			            borderWidth:0,//提示边框线宽，单位px，默认为0（无边框）
			            padding:5,//提示内边距，单位px，默认各方向内边距为5 number Array
			            textStyle:{color:"#fff"},//文本样式，默认为白色字体
			            axisPointer:{//坐标轴指示器
			                type: 'line',//'line' | 'cross' | 'shadow' | 'none'(无) 指定type后对应style生效
			                lineStyle: {
			                    color: '#48b',
			                    width: 2,
			                    type: 'solid'
			                },
			                crossStyle: {
			                    color: '#1e90ff',
			                    width: 1,
			                    type: 'dashed'
			                },
			                shadowStyle: {
			                    color: 'rgba(150,150,150,0.3)',
			                    width: 'auto',
			                    type: 'default'
			                }
			            }
			        }
			        return tooltipResult;
			    },
			    
			    //获取默认的legend对象 图列
			    getLegend:function(){
			        var legendResult = {
			            show:true,//显示true false
			            zlevel:0,//一级层叠控制
			            z:4,//二级层叠控制
			            orient:"horizontal",//布局方式 horizontal：水平；vertical：垂直
			            x:"center",//水平位置 right center left
			            y:"top",//垂直位置 top center bottom
			            backgroundColor:"rgba(0,0,0,0)",//图例背景颜色，默认透明
			            borderColor:"#ccc",//图例边框颜色
			            borderWidth:0,//图例边框线宽，单位px，默认为0（无边框）
			            padding:5,//图例内边距，单位px，默认各方向内边距为5 number Array
			            itemGap:10,//各个item之间的间隔，单位px
			            itemWidth:20,//图例图形宽度
			            itemHeight:14,//图例图形高度
			            textStyle:{color:"#333"},//默认只设定了图例文字颜色
			            formatter:null,//文本格式器 String function
			            selectedMode:true,//选择模式 true false 或 single，multiple
			            selected:null,//配置默认选中状态
			            data:[],//图例内容数组
			        }
			        return legendResult;
			    },
			    
			    //获取AxisObj
			    getAxisObj:function(dataSource){
			        var AxisObj = new Array();
			        for(var i=0; i< dataSource.length; i++){
			            var data = dataSource[i];
			            var obj = new Object();
			            if(data.type === "value" || data.type === "time"){ //判断xAxis的类型是数值型，时间型
			                obj["name"] = data.name;
			                obj["nameLocation"] = data.nameLocation;
			                if(typeof data.nameTextStyle == "boolean"){
			                    obj["nameTextStyle"] = [0,0];
			                }else{
			                    obj["nameTextStyle"] = data.nameTextStyle;
			                }
			                obj["boundaryGap"] = data.boundaryGap;
			                obj["min"] = data.min;
			                obj["max"] = data.max;
			                obj["scale"] = data.scale;
			                obj["splitNumber"] = data.splitNumber;
			                obj["axisTick"] = {show:false,inside:data.axisTick.inside,length:data.axisTick.length,lineStyle:data.axisTick.lineStyle};
			                obj["axisLabel"] = {
			                    show:data.axisLabel.show,
			                    rotate:data.axisLabel.rotate,
			                    margin:data.axisLabel.margin,
			                    clickable:data.axisLabel.clickable,
			                    formatter:data.axisLabel.formatter,
			                    textStyle:data.axisLabel.textStyle,
			                };
			                obj["splitLine"] = {show:data.splitLine.show,lineStyle:data.splitLine.lineStyle};
			                obj["splitArea"] = {show:data.splitArea.show,areaStyle:data.splitArea.areaStyle};
			            }else if(data.type === "category"){//类目型
			                obj["data"] = data.data;
			                if(typeof data.boundaryGap == "boolean"){
			                    obj["caboundaryGap"] = data.boundaryGap;
			                }else{
			                    obj["caboundaryGap"] = false;
			                }
			                obj["axisTick"] = {show:true,interval:data.axisTick.interval,onGap:data.axisTick.onGap,inside:data.axisTick.inside,length:data.axisTick.length,lineStyle:data.axisTick.lineStyle};
			                obj["axisLabel"] = {
			                    show:data.axisLabel.show,
			                    interval:data.axisLabel.interval,
			                    rotate:data.axisLabel.rotate,
			                    margin:data.axisLabel.margin,
			                    clickable:data.axisLabel.clickable,
			                    formatter:data.axisLabel.formatter,
			                    textStyle:data.axisLabel.textStyle,
			                };
			                obj["splitLine"] = {show:data.splitLine.show,onGap:data.splitLine.onGap,lineStyle:data.splitLine.lineStyle};
			                obj["splitArea"] = {show:data.splitArea.show,onGap:data.splitArea.onGap,areaStyle:data.splitArea.areaStyle};
			            }else if(data.type === "log"){
			                obj["logLabelBase"] = data.logLabelBase;
			                obj["logPositive"] = data.logPositive;
			                obj["axisTick"] = {show:false,inside:data.axisTick.inside,length:data.axisTick.length,lineStyle:data.axisTick.lineStyle};
			                obj["axisLabel"] = {
			                    show:data.axisLabel.show,
			                    rotate:data.axisLabel.rotate,
			                    margin:data.axisLabel.margin,
			                    clickable:data.axisLabel.clickable,
			                    formatter:data.axisLabel.formatter,
			                    textStyle:data.axisLabel.textStyle,
			                };
			                obj["splitLine"] = {show:data.splitLine.show,lineStyle:data.splitLine.lineStyle};
			                obj["splitArea"] = {show:data.splitArea.show,areaStyle:data.splitArea.areaStyle};
			            }
			            obj["type"] = data.type;
			            obj["show"] = data.show;
			            obj["position"] = data.position;
			            obj["axisLine"] = data.axisLine;
			            obj["zlevel"] = data.zlevel;
			            obj["z"] = data.z;
			            AxisObj.push(obj);
			        }
			        return AxisObj;
			    },
			    
			    //获取seriesObj
			    getSeriesObj:function(type,dataModel,ecType){//type：图形类型；data：serie数据；ecType：具体图形
			        var obj = new Object();
			        if(type === "angle"){ //图形类型为直角系
			            if(ecType === "scatter" || ecType === "bar" || ecType === "line"){
			                obj["xAxisIndex"] = dataModel.xAxisIndex;
			                obj["yAxisIndex"] = dataModel.yAxisIndex;
			                obj["legendHoverLink"] = dataModel.legendHoverLink;
			                if(ecType === "bar" || ecType === "line"){
			                    obj["stack"] = dataModel.stack;
			                    if(ecType === "bar"){
			                        obj["barGap"] = dataModel.barGap;
			                        obj["barCategoryGap"] = dataModel.barCategoryGap;
			                        obj["barMinHeight"] = dataModel.barMinHeight;
			                        obj["barWidth"] = dataModel.barWidth;
			                        obj["barMaxWidth"] = dataModel.barMaxWidth;
			                    }
			                    if(ecType === "line"){
			                        obj["showAllSymbol"] = dataModel.showAllSymbol;
			                        obj["smooth"] = dataModel.smooth;
			                        obj["dataFilter"] = dataModel.dataFilter;
			                        obj["step"] = dataModel.step;
			                        obj["lineStyle"] = dataModel.lineStyle;
			                        obj["showSymbol"] = dataModel.showSymbol;
			                    }
			                }
			                if(ecType === "scatter" || ecType === "line"){
			                    obj["symbol"] = dataModel.symbol;
			                    obj["symbolRotate"] = dataModel.symbolRotate;
			                    if(ecType === "scatter"){
			                        obj["large"] = dataModel.large;
			                        obj["largeThreshold"] = dataModel.largeThreshold;
			                        if(typeof dataModel.symbolSize == "number"){
			                            obj["symbolSize"] = 4;
			                        }else{
			                            obj["symbolSize"] = dataModel.symbolSize;
			                        }
			                    }else{
			                        if(typeof dataModel.symbolSize == "number"){
			                            obj["symbolSize"] = 2;
			                        }else{
			                            obj["symbolSize"] = dataModel.symbolSize;
			                        }
			                    }
			                }
			            }
			        }else if(type === "round"){//图形类型为饼图
			            obj["center"] = dataModel.center;
			            obj["radius"] = dataModel.radius;
			            obj["startAngle"] = dataModel.startAngle;
			            obj["minAngle"] = dataModel.minAngle;
			            obj["selectedMode"] = dataModel.selectedMode;
			            obj["legendHoverLink"] = dataModel.legendHoverLink;
			        }
			        obj["zlevel"] = dataModel.zlevel;
			        obj["z"] = dataModel.z;
			        obj["type"] = dataModel.type;
			        obj["name"] = dataModel.name;
			        obj["tooltip"] = dataModel.tooltip;
			        obj["clickable"] = dataModel.clickable;
			        obj["itemStyle"] = dataModel.itemStyle;
			        obj["data"] = dataModel.data;
			        obj["markPoint"] = dataModel.markPoint;
			        obj["markLine"] = dataModel.markLine;
			        return obj;
			    },
			    
			    //生成直角系数据信息
			    getAngleMessage:function(xdataList,dataSource,legend,dataModel,type){
			        var result = new Array();
			        //根据类型设置相关属性
			        var xAxisObj = this.getAxisObj(dataModel[0]);
			        for(var i=0; i<xAxisObj.length; i++){
			            if(xAxisObj[i].type === "category"){
			                xAxisObj[i].data = xdataList[i];
			            }
			        }
			        var yAxisObj = this.getAxisObj(dataModel[1]);
			        var seriesObj = this.getSeriesObj("angle",dataModel[2],type);
			        //如果不是多组数据的堆积图时，stack为null
			        if(legend.length < 1){
			            seriesObj.stack = null;
			        }
			        var xAxis =xAxisObj;
			        var yAxis = yAxisObj;
			        var series = new Array();
			        for(var i=0; i<legend.length; i++){
			            var contObj = {};
			            $.extend(contObj,seriesObj);
			            contObj.type=type;
			            contObj.name = legend[i];
			            contObj.data = dataSource[i];
			            series.push(contObj);
			        } 
			        result.push(xAxis);
			        result.push(yAxis);
			        result.push(series);
			        return result;
			    },
			    
			    //生成饼状图信息
			    getPieMessage:function(dataSource,legend,title,dataModel,type){
			        var seriesObj = this.getSeriesObj("round",dataModel,type);
			        var content = new Array();
			        for(var i=0; i<legend.length; i++){
			            content.push({value:dataSource[i],name:legend[i]});
			        }
			        seriesObj.name = title;
			        seriesObj.type = type;
			        seriesObj.data = content;
			        var series = [seriesObj];
			        return series;
			    }
}
