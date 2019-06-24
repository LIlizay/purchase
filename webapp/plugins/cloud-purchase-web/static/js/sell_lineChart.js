
		/**
		 * 
		 */
		function initCharts(viewreport,width,height,data,data1){
			if(!viewreport){
				return;
			}
			viewreport.style.cssText = "position: relative;";
			//创建canvas对象
			var a_canvas = document.createElement("canvas");
			a_canvas.id = "a_canvas";
			a_canvas.width = width;
			a_canvas.height = height;
			a_canvas.style.cssText = "background-color: transparent;position: absolute;z-index: 501;";
			viewreport.appendChild(a_canvas);
			var bbox =a_canvas.getBoundingClientRect();
			//获取绘图环境对象
			var context = a_canvas.getContext("2d");
			context.font = "14px Arial";
				
//			var data = [{y:-100,x:2600},
//						{y:-137,x:2134},
//						{y:-169,x:1446},
//						{y:-201,x:820}];
//				
//			var data1 = [{y:-287,x:1000},
//						{y:-227,x:1789},
//						{y:-189,x:2116},
//						{y:-132,x:2513}];
				
			//刻度分割数
			var scaleNumX = 5;
				
			//刻度分割数
			var scaleNumY = 5;
				
			//圆点半径
			var radius = 2;
				
			//x坐标轴偏差量，预留坐标显示空间
			var deviateX = 40;
				
			//y坐标轴偏差量，预留坐标显示空间
			var deviateY = 50;
				
			//x轴最大值
			var max_h = getMax_H(data,3);

			//y轴最大值
			var max_v = getMax_V(data,2);
			
			//x轴最大值
			var max_h1 = getMax_H(data1,3);
			
			//y轴最大值
			var max_v1 = getMax_V(data1,2);
			
			if(max_h1>max_h){
				max_h = max_h1;
			}
			if(Math.abs(max_v1)>Math.abs(max_v)){
				max_v = max_v1;
			}

			// 将数据换算为坐标    
			var points = getPoint(data,max_h,max_v,a_canvas.width,a_canvas.height,deviateX,deviateY);
				
			// 将数据换算为坐标    
			var points1 = getPoint(data1,max_h,max_v,a_canvas.width,a_canvas.height,deviateX,deviateY);
				
			var step = getStep(points,deviateX,deviateY);
				
			var step1 = getStep(points1,deviateX,deviateY);
				
			//绘制折线
			drawLine(step,context,"red",2);
			
			//绘制折线
			drawLine(step1,context,"blue",2);

			//添加数据点
			drawPoint(points,radius,"#000","#fff",context);
				
			//添加数据点
			drawPoint(points1,radius,"#000","#fff",context);
				
			//添加文字    
			drawTooltip(points,data,"#000",null,context);
				
			//添加文字    
			drawTooltip(points1,data1,"#000",null,context);
				
			drawAxis(context,deviateX,deviateY,scaleNumX,scaleNumY,max_h,max_v); 
				
			//添加鼠标移入效果
			a_canvas.addEventListener("mousemove",function(e){
				drawTooltip(points,data,null,"#000000",e,"b_canvas",bbox.left,bbox.top);
				drawTooltip(points1,data1,null,"#000000",e,"c_canvas",bbox.left,bbox.top);
			});
		}
		
		//计算数据最大值附近的10的倍数作为坐标点最大值
		function getMaxIntegerForData(max,deviateNum){
			var maxStr = max.toString();
			var digit = maxStr.length;
			if(digit>deviateNum){
				//x轴最大值数字位数大于3位需要特殊处理
				var i = digit-deviateNum;//10的倍数的保留位，用于补0计算
				var num = maxStr.substring(i-1,i);
				if(num<9){
					if(i!=1){
						maxStr = maxStr.substring(0,i-1)+(Number(num)+1);
					}else{
						maxStr = (Number(num)+1).toString();
					}
				}else{
					maxStr = "10";	
				}
				
				var deviateNumStr = "1";
				for(var i=0;i<deviateNum;i++){
					maxStr = maxStr + "0";
					deviateNumStr = deviateNumStr + "0";
				}
				if(Number(maxStr)<max){
					max = Number(maxStr) + Number(deviateNumStr);
				}else{
					max = Number(maxStr);
				}
			}else{
				//小于等于3位，最大值定义为1000
				var deviateNumStr = "1";
				for(var i=0;i<deviateNum;i++){
					deviateNumStr = deviateNumStr + "0";
				}
				max = Number(deviateNumStr);
			}
			return max;
		}
		
		//获取x轴最大点				
		function getMax_H(data,deviateNum){
			var max_h = 0;
			for(var key in data){
				max_h = max_h + data[key].x;
			}
			return getMaxIntegerForData(Math.abs(max_h),deviateNum);
		}
		
		//获取y轴最大点				
		function getMax_V(data,deviateNum){
			var max_v = 0;
			for(var key in data){
				if(Math.abs(data[key].y)>Math.abs(max_v)){
					max_v = data[key].y;
				}
			}
			if(max_v<0){			
				return -getMaxIntegerForData(Math.abs(max_v),deviateNum);
			}else{
				return getMaxIntegerForData(Math.abs(max_v),deviateNum);
			}
		}
		
		//获取数据坐标点集合
		function getPoint(data,max_h,max_v,width,height,deviateX,deviateY){
			var points = [];
			for(var key in data){
				var px = data[key].x/max_h*(width-deviateX*2);
				var py = data[key].y/max_v*(height-deviateY*2)+deviateY;
				if(key>0){
					px = px + points[key-1].x;
				}else{
					px = px + deviateX;
				}
				points.push({
					"x": px,
					"y": py
				});
			}
			return points;
		}
				
		//绘制数据节点
		function drawPoint(points,radius,strokeStyle,fillStyle,context){
			for(var i in points) {
				var p = points[i];
				context.beginPath();
				context.arc(p.x, p.y, radius, 0, 2 * Math.PI);
				//实心圆    
//				context.fillStyle = "#000";
				//空心圆    
				context.strokeStyle = strokeStyle;
				context.stroke();
				context.fillStyle = fillStyle;
				context.fill();
			}
		}
		
		//根据数据源计算各个线段的起止点
		function getStep(points,deviateX,deviateY){
			var step = [];
			var len = points.length;
			for(var key in points){
				var dataX = points[key].x;
				var dataY = points[key].y;
				if(key==0){
					var segmenth = {startPoint:{x:deviateX,y:dataY},endPoint:{x:dataX,y:dataY}};
					step.push(segmenth);
				}else{
					var lastDataX = points[key-1].x;
					var segmenth = {startPoint:{x:lastDataX,y:dataY},endPoint:{x:dataX,y:dataY}};
					step.push(segmenth);
				}
				if(key<(len-1)){
					var nextDataY = points[Number(key)+1].y;
					var segmenth = {startPoint:{x:dataX,y:dataY},endPoint:{x:dataX,y:nextDataY}};
					step.push(segmenth);
				}
			}
			return step;
		}
				
		//绘制线段
		function drawLine(step,context,color,lineWidth){
			context.beginPath();
			for(var key in step){
				var startPoint = step[key].startPoint;
				var endPoint = step[key].endPoint;
				context.moveTo(startPoint.x, startPoint.y);
				context.lineTo(endPoint.x, endPoint.y);
			}
			context.lineWidth = lineWidth;
			context.strokeStyle = color;
			context.stroke();
		}
				
		function drawAxis(context,deviateX,deviateY,scaleNumX,scaleNumY,max_h,max_v){
			//坐标轴描绘
			context.beginPath();
			//x轴 
			context.moveTo(deviateX,deviateY);
			context.lineTo(a_canvas.width-deviateX+20, deviateY);
	
			//y轴
			context.moveTo(deviateX, deviateY);
			context.lineTo(deviateX, a_canvas.height-deviateY+20);
	
			//坐标轴粗细及颜色
			context.lineWidth = 2;
			context.strokeStyle = "#000000";
			context.stroke(); 
					
			for(var i=0;i<scaleNumX;i++) {
				//绘制x轴坐标点 
				var x = (i+1)/scaleNumX*(a_canvas.width-deviateX*2);
				context.beginPath();
				context.moveTo(x+deviateX, deviateY);
				context.lineTo(x+deviateX, deviateY+3 );
				context.lineWidth = 2;
				//添加x轴文字
				context.fillStyle = "black";
				context.fillText((i+1)/scaleNumX*max_h, x+20, deviateY-5);
				context.strokeStyle = "#000";
				context.stroke();
			}
					
			for(var i=0;i<scaleNumY;i++) {
				//绘制y轴坐标点   
				var y = (i+1)/scaleNumY*(a_canvas.height-deviateY*2);
				context.beginPath();
				context.moveTo(deviateX, y+deviateY);
				context.lineTo(deviateX+3, y+deviateY);
				context.lineWidth = 2;
				//添加y轴文字 
				context.fillStyle = "black";
				context.fillText((i+1)/scaleNumY*max_v, 0, y+deviateY+5);
				context.strokeStyle = "#000";
				context.stroke();
			}
					
		}
				
		//绘制数据节点
		function drawTooltip(points,data,tooltip,fillStyle,e,id,left,top){
			for(var key in points){
				var upperLimitX = points[key].x+left+3;
				var lowerLimitX = points[key].x+left-3;
				var upperLimitY = points[key].y+top+3;
				var lowerLimitY = points[key].y+top-3;
				var b_canvas = document.getElementById(id);
				if(b_canvas!=null){
					if(e.x<lowerLimitX||e.x>upperLimitX
						||e.y>upperLimitY||e.y<lowerLimitY){
						b_canvas.parentNode.removeChild(b_canvas);
					}
				}else{
					if(e.x>=lowerLimitX&&e.x<=upperLimitX
						&&e.y<=upperLimitY&&e.y>=lowerLimitY){
						var canvasPoint = document.createElement("canvas");
						canvasPoint.id = id;
						canvasPoint.height = 40;
						canvasPoint.style.cssText = "background-color: #e0e0e0;position: absolute;"+
							"z-index: 1000;left:"+e.x+";top: "+e.y+";";
						a_canvas.parentNode.appendChild(canvasPoint);
						var contextPoint = canvasPoint.getContext("2d");
						contextPoint.beginPath();    
						contextPoint.fillStyle = fillStyle;
						if(tooltip!=null&&tooltip[key]!=null&&tooltip[key]!=""){
							canvasPoint.width = 180;
							contextPoint.font = "14px Arial";
							contextPoint.fillText(tooltip[key], 10, 20);
						}else{
							var text = "电价："+data[key].y+"；电量："+data[key].x;
							canvasPoint.width = 180;
							contextPoint.font = "14px Arial";
							contextPoint.fillText(text, 10,20);
						}
						contextPoint.fill();
						break;
					}
				}
			}
		}