<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>结算管理-实际用电量录入实际用电量</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
	
	<style type="text/css">
		.datagrid-row-selected{
			background-color: #eeeeee;
			color:#000000;
		}
	</style>
</head>
<body >
<div id="addDeliveryVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-panel title="实际用电量录入" line="true" slot="top">
	  <mk-form-panel title="" num="3">
	        <mk-form-row >
	            <!--交易年月-->
	            <mk-form-col label="用电年月" :class="{'display-none':!formFields.ym.formHidden}"> 
	                <su-datepicker format="YYYY-MM" :data.sync="params.ym" name="ym" disabled="disabled"></su-datepicker>
	            </mk-form-col> 
	             <!-- 用户数量： -->
	            <mk-form-col label="用户数" :class="{'display-none':!formFields.consNum.formHidden}">  
	                 <su-textbox :addon="{'show':true,'rightcontent':'个'}" disabled="true" name="rotateCycle" :data.sync="params.consNum"></su-textbox>
	            </mk-form-col>
	             <!--委托电量-->
	            <mk-form-col label="委托电量" :class="{'display-none':!formFields.monthDeliveryPqFrom.formHidden}"> 
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="rotateCycle" :data.sync="params.reportPq"></su-textbox>
	            </mk-form-col> 
	       </mk-form-row> 
	        <mk-form-row >
	            <!--总交易电量-->
	            <mk-form-col label="总交易电量" :class="{'display-none':!formFields.bidReportPq.formHidden}"> 
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="rotateCycle" :data.sync="params.totalTranPq"></su-textbox>
	            </mk-form-col> 
	             <!-- 实际用电量： -->
	            <mk-form-col label="实际用电量" :class="{'display-none':!formFields.practicalPq.formHidden}">  
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" name="practicalPq" :data.sync="params.practicalPq"></su-textbox>
	            </mk-form-col>
	       		<!-- 工业电量： -->
	            <mk-form-col label="工业电量">  
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" :data.sync="params.industryPq"></su-textbox>
	            </mk-form-col>
	       	</mk-form-row>
        	<mk-form-row >
	            <!--商业电量-->
	            <mk-form-col label="商业电量"> 
	                 <su-textbox :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled="true" :data.sync="params.businessPq"></su-textbox>
	            </mk-form-col> 
	       	</mk-form-row>
	    </mk-form-panel> 
    </mk-panel>
    <mk-panel title="用户月度实际用电量列表" line="true" slot="bottom" height="100%">
        <div id="addDeliveryGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
</mk-top-bottom>
</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
//对应 js
var addDeliveryVue = new Vue({
	el: '#addDeliveryVue',
	data: {
		formFields:{},
		pageNumber:null,
		//初始当前页时 数据不比对标识
		initFlag: false,
		
		initRowsData:[],//比对翻页时数据是否更改
		firstLoadFlag: false,
		//总实际用电量的初始化值，即实际总实际用电量，与数据库数据保持一致。
		//初始和params里的practicalPq相等，但params里的是根据前台修改后的修改值。若翻页后不保存本页数据，则取initPracticalPq赋值params里的practicalPq
		initPracticalPq: null,	
    	params:{
    		ym: null,			//年月
   			consNum: null,		//用户数
   			reportPq: null,		//委托电量
   			practicalPq: null,	//实际用电量
   			totalTranPq: null,	//总交易电量
   			industryPq: null,	//工业电量
			businessPq: null	//商业电量
    	}
	},
	ready:function(){
		var me = this;
		MainFrameUtil.setDialogButtons(this.getButtons(),'addActualPqDialog');
		var smd = MainFrameUtil.getParams("addActualPqDialog").smd;
		me.params.ym = smd.ym;
		this.initGrid();
		this.initForm();
	},
	methods: {
		initForm: function(){
			var me = this;
			$.ajax({
				url:basePath+"scConsElectricityController/getPraPqTranYm/" + me.params.ym,
				method:'get',
	            contentType : 'application/json;charset=UTF-8',
				type:"json",
				success:function(data){
					if(data.flag){
						if(data.data != null){
							me.params.consNum = data.data.consNum;
							me.params.practicalPq = data.data.practicalPq;
							me.params.totalTranPq = data.data.totalTranPq;
							me.params.reportPq = data.data.reportPq;
							me.params.industryPq = data.data.industryPq;	//工业电量
							me.params.businessPq = data.data.businessPq;	//商业电量
							
							me.initPracticalPq = data.data.practicalPq;
						}else{//无查询数据
							me.params.consNum = null;
							me.params.practicalPq = null;
						}
					}else{
						MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						$.messager.progress('close');
				    	return;
					}
				}
			});
		},	
		initGrid: function(){
			var that = this;
		 	$('#addDeliveryGrid').datagrid({
	            method:'post',
	            url: basePath + 'scConsElectricityController/pqDataGrid',
	            queryParams: {startYm: that.params.ym},
	            height:"100%",
	            fitColumns:true,
	            rownumbers:true,
	            singleSelect:false,
	            pagination : true,
	            pageSize: 10,
	            pageList: [10, 20, 50, 100, 150, 200],
	            columns:[[
						{field:'consName',title:'用户名称',width:120,align:'left'},
						{field:'elecTypeCodeName',title:'用电类别',width:60,align:'center'},
						{field:'voltCodeName',title:'电压等级',width:60,align:'center'},
	            	    {field:'reportPq',title:'委托电量(兆瓦时)',width:100,align:'center'},
	            	    {field:'practicalPq',title:'实际用电量(兆瓦时)',width:100,align:'center'},
	      				{field:'peakPq',title:'峰时电量(兆瓦时)',width:100,align:'center',
	      					editor:{type:'numberbox',options:{precision:3,min:0,
	      						onChange:function(newValue,oldValue){
	      							that.calculate();
	      					}}},
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      				},
	      				{field:'plainPq',title:'平时电量(兆瓦时)',width:100,align:'center',
	      					editor:{type:'numberbox',options:{precision:3,min:0,
	      						onChange:function(newValue,oldValue){
	      							that.calculate();
	      					}}},
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}      					
	      				},
	      				{field:'valleyPq',title:'谷时电量(兆瓦时)',width:100,align:'center',
	      					editor:{type:'numberbox',options:{precision:3,min:0,
	      						onChange:function(newValue,oldValue){
	      							that.calculate();
	      					}}},
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}  					
	      				},
	      				{field:'overPeakPq',title:'尖峰电量(兆瓦时)',width:100,align:'center',
	      					editor:{type:'numberbox',options:{precision:3,min:0,
	      						onChange:function(newValue,oldValue){
	      							that.calculate();
	      					}}},
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      					
	      				},
	      				{field:'doublePq',title:'双蓄电量(兆瓦时)',width:100,align:'center',
	      					editor:{type:'numberbox',options:{precision:3,min:0,
	      						onChange:function(newValue,oldValue){
	      							that.calculate();
	      					}}},
	      					formatter:function(value,index,row){
		           				  if(value){
		           					  return parseFloat(value);
		           				  }
	         			  	}
	      				}
	      				
	    		]],
	    		onBeforeLoad : function(data){
					that.firstLoadFlag=false;
		   			if(that.initFlag==false){ //初始化表格时return
			    		return true;
			    	}
		   			var rows = $("#addDeliveryGrid").datagrid("getRows");
					for(var i in rows){
						$("#addDeliveryGrid").datagrid("endEdit",i);
					}
					rows = $.parseJSON($.toJSON($("#addDeliveryGrid").datagrid("getRows")));
					for(var i in rows){
						$("#addDeliveryGrid").datagrid("beginEdit",i);
					}
					 var initRowsData = that.initRowsData;
					 var flag = false
	    			 for(var i=0 ;i < rows.length;i++){
	    				if(flag) break;
	    				 var param = {"peakPq":"peakPq","plainPq":"plainPq","valleyPq":"valleyPq","overPeakPq":"overPeakPq","doublePq":"doublePq"};
	    				 for(var j in param){
	    					 if( !(initRowsData[i][j]==null && rows[i][j]==null) &&  !(initRowsData[i][j]==null && rows[i][j]=='')
	    							 && !(initRowsData[i][j]=='' && rows[i][j]==null) && (initRowsData[i][j] != rows[i][j])){
	    						 flag=true;
	    						 break;
	    					 }
	    				 }
	    			 }
			    	if(flag){
			    		MainFrameUtil.confirm({
			    			title:"警告",
					        body:"页面变更会导致当前页面修改数据丢失，请确认是否保存",
					        buttons:[{text:'保存',type:"ok",style:"primary"},{text:'取消',type:"cancel",style:"default"}],
			    	        onClose:function(type){
			    	            if(type=="ok"){//确定
			    	            	that.loadData(data.page,data.rows,true);
			    	            }else if(type=="cancel"){//取消
			    	            	that.loadData(that.pageNumber,data.rows,false);
			    	            }
			    	        }
			    	    });
			    		return false;
			    	}else{
			    		return true;
			    	}
	    			
	 		    },
	    		onLoadSuccess: function(data){
	    			//表加载完成标识
	               	that.initFlag = true;
	    			//获取列表页码
	            	that.pageNumber = $("#addDeliveryGrid" ).datagrid("getPager").data("pagination").options.pageNumber;
	    			that.initRowsData = $.parseJSON($.toJSON(data.rows));
	               	for(var i=0 ;i < data.rows.length;i++){
	       				$("#addDeliveryGrid").datagrid("beginEdit",i);
	       			}
	            	that.firstLoadFlag = true;
// 	               	that.calculate(true);
	             }
	        });
			
		},
		//计算实际用电量  走了两遍 导致报错
      	calculate :function(){
        	if(!this.firstLoadFlag){
        		return;
        	}
      		var rows = $("#addDeliveryGrid").datagrid("getRows");
	       	for(var i = 0; i < rows.length; i++){
				var peakPq= $($('#addDeliveryGrid').datagrid('getEditor', {index:i,field:'peakPq'}).target).numberbox('getValue');
				var plainPq = $($('#addDeliveryGrid').datagrid('getEditor', {index:i,field:'plainPq'}).target).numberbox('getValue');
				var valleyPq = $($('#addDeliveryGrid').datagrid('getEditor', {index:i,field:'valleyPq'}).target).numberbox('getValue');
				var overPeakPq =  $($('#addDeliveryGrid').datagrid('getEditor', {index:i,field:'overPeakPq'}).target).numberbox('getValue');
				var doublePq =  $($('#addDeliveryGrid').datagrid('getEditor', {index:i,field:'doublePq'}).target).numberbox('getValue');
			
				var practicalPq = 0;
	       		 
	       		var peak = parseFloat((peakPq == null || peakPq=="") ? 0  : peakPq);
	       		var plain = parseFloat((plainPq == null || plainPq=="") ? 0 : plainPq);
	       		var valley = parseFloat((valleyPq == null || valleyPq=="") ? 0 : valleyPq);
	       		var over = parseFloat((overPeakPq == null || overPeakPq=="") ? 0 : overPeakPq);
	       		var dob = parseFloat((doublePq == null || doublePq=="") ? 0 : doublePq);
	       			
       			practicalPq = peak + plain + valley + over + dob;
       			$("#addDeliveryGrid").datagrid("getPanel").find(".datagrid-row [field='practicalPq']").eq(i).find("div").text(practicalPq.toFixed(3));
       			
       			var practicalPqSource = parseFloat((rows[i].practicalPq == null || rows[i].practicalPq=="") ? 0  : rows[i].practicalPq);
       			var practicalPqTarget = parseFloat(practicalPq.toFixed(3));
       			if(practicalPqSource != practicalPqTarget){
       				if(this.params.practicalPq != null){
	       				this.params.practicalPq = (parseFloat(this.params.practicalPq) + practicalPqTarget - practicalPqSource).toFixed(3);
       				}else{
       					this.params.practicalPq = (practicalPqTarget - practicalPqSource).toFixed(3);
       				}
       			}
       			rows[i].practicalPq = practicalPq.toFixed(3);
	       	}
       },
       loadData: function(page,rows,flag){
			var me = this;
			//翻页 数据不比对
			me.initFlag=false;
			if(flag){
				me.save();
			}else{	//若不保存，则把总实际用电量恢复为原来的值
				this.params.practicalPq = this.initPracticalPq;
			}

			//列表刷新
			$('#addDeliveryGrid').datagrid("reload");
			
	    },
        //点击保存按钮
		save: function(){
			var me = this;
			if(!this.caculateBusinessPq(true)){
				return;
			}
			for(var i=0;i<$("#addDeliveryGrid").datagrid("getRows").length;i++){
				 $("#addDeliveryGrid").datagrid("endEdit",i);
			}
			var saveRowsData = $("#addDeliveryGrid").datagrid("getRows");
			//是否自动计算标识
			this.firstLoadFlag = false;
			var rows = [];
			for(var i=0;i<saveRowsData.length;i++){
				if((saveRowsData[i].id != null && saveRowsData[i].id != '') || saveRowsData[i].practicalPq > 0){
					rows.push(saveRowsData[i]);
				}
				$("#addDeliveryGrid").datagrid("beginEdit",i);
			}
			//是否自动计算标识
			this.firstLoadFlag = true;
			if(rows.length == 0){
				MainFrameUtil.alert({title:"提示",body:"请录入用户用电数据！"});
				return;
			}
			$.messager.progress({title:"请等待",msg:"正在保存...",interval:100});
			$.ajax({
				url:basePath+"scConsElectricityController/savePracticalPqJs",
				method:'put',
				data:$.toJSON({scConsElectricitieList: rows,smSettlementMonth:{ym: me.params.ym.replace("-",""), industryPq: me.params.industryPq, businessPq: me.params.businessPq}}),
                contentType : 'application/json;charset=UTF-8',
				type:"json",
				success:function(data){
					if(data.flag){
						if(data != null){
							MainFrameUtil.alert({title:"提示",body: "保存成功"});
							
							//表单数据刷新
							me.initForm();
							$.messager.progress('close');
						}else{
							MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
							$.messager.progress('close');
					    	return;
						}
					}else{
						MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"});
						$.messager.progress('close');
				    	return;
					}
				},
        		error:function(data){
        			MainFrameUtil.alert({title:"提示",body:"请刷新页面重试！"}); 
        		}
			});
		},
		//按钮组
		getButtons:function(){
			var that = this;
			var buttons = [
				{text:"保存",type:"primary",handler:function(btn,params){that.save()	}},
				{text:"取消",handler:function(){MainFrameUtil.closeDialog('addActualPqDialog')}}];
			return buttons;
		},
		caculateBusinessPq: function(flag){
			var practicalPq = this.params.practicalPq;	//总实际用电量
			var industryPq = this.params.industryPq;	//工业用电
			if(practicalPq === null || practicalPq === "" || industryPq === null || industryPq === ""){
				this.params.businessPq = null;
				return true;
			}
			practicalPq = parseFloat(this.params.practicalPq);	//总实际用电量
			industryPq = parseFloat(this.params.industryPq);	//工业用电
			if(flag && practicalPq < industryPq){
				this.params.businessPq = null;
				MainFrameUtil.alert({title:"提示",body:"工业电量必须小于等于实际用电量！"});
				return false;
			}else{
				this.params.businessPq = (practicalPq - industryPq).toFixed(3);
				return true;
			}
		}
	},
	watch:{
		'params.industryPq': function(){
			this.caculateBusinessPq(true);
		},
		'params.practicalPq': function(){
			this.caculateBusinessPq(false);
		}
	}
}); 
</script>
</body>
</html>
