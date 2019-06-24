<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>合同管理-购电合同管理发电单元新增</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="generatorAddVue" class="height-fill" v-cloak>
<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
	<mk-top-bottom height="100%" top_height="auto">	    
		<mk-panel title="发电单元分月交易电量" line="true" slot="top">
			<mk-form-panel title="">
				<mk-form-row>
					<!--合同总电量  -->
		           	<mk-form-col :label="formFields.agrePqForm.label" :class="{'display-none':!formFields.agrePq.formHidden}" required_icon="true">
		           	 	<su-textbox :data.sync="params.phmPpa.totaPq" type="number" name="totaPq" :addon="{'show':true,'rightcontent':'兆瓦时'}" @su-change="distChange"></su-textbox>
					</mk-form-col>
					<!-- 分解方式 -->
		            <mk-form-col :label="formFields.distributionMode.label":class="{'display-none':!formFields.distributionMode.formHidden}" required_icon="true">
		                <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_dividedType"
		                :select-value.sync="params.phmPpa.distributionMode" name="distributionMode"  label-name="name" @su-change="distChange"></su-select>
		            </mk-form-col>
					<!-- 保留小数-->
		            <mk-form-col :label="formFields.saveDecimal.label" :class="{'display-none':!formFields.saveDecimal.formHidden}" :required_icon="saveDecimalReqFlag">
		                <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_precisionType" 
		                :select-value.sync="params.phmPpa.saveDecimal" name="saveDecimal"  label-name="name" :disabled="saveDecimalFlag" @su-change="distChange"></su-select>
		            </mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="购电合同列表（兆瓦时）" line="true" slot="bottom" height="120">
	        <div id="generatorAddGrid"></div>
		</mk-panel>
		<mk-panel title="购电电价列表（元/兆瓦时）" line="true" slot="bottom" height="120">
	        <div id="priceAddGrid"></div>
		</mk-panel>
	</mk-top-bottom>
</su-form>
</div>
<script type="text/javascript">
	//手动添加与取消指定列的编辑器
	$.extend($.fn.datagrid.methods, {
        addEditor : function(jq, param) {
            if (param instanceof Array) {
                $.each(param, function(index, item) {
                    var e = $(jq).datagrid('getColumnOption', item.field);
                    e.editor = item.editor;
                });
            } else {
                var e = $(jq).datagrid('getColumnOption', param.field);
                e.editor = param.editor;
            }
        },
        removeEditor : function(jq, param) {
            if (param instanceof Array) {
                $.each(param, function(index, item) {
                    var e = $(jq).datagrid('getColumnOption', item.field);
                    e.editor = {};
                });
            } else {
                var e = $(jq).datagrid('getColumnOption', param);
                e.editor = {};
            }
        }
    });
	var basePath="${Config.baseURL}";
	var generatorAddVue = new Vue({
	    el: '#generatorAddVue',
	    data: {
	  	  	formFields:{},
	  	  	elecId:null,
	  	  	row: null,//数据
	  	  	saveDecimalFlag:false,//保留小数位编辑标志
	  	  	saveDecimalReqFlag:true,//保留
	  	  	geneName:null,//发电单元名称
	  	  	params:{
		  	  	phmPpa:{
		  	  		distributionMode:"",//分解方式
		  	  		saveDecimal:"",//保留小数
		  	  		totaPq:"",//总电量
		  	  	}
	  	  	},
	  		//验证规则
			dataOption: {
				rules: {
					distributionMode: "required",
					saveDecimal: "required",
					totaPq: "required",
				}
			},
		    valid: false,
		    dialogParams: null
	    },
	    ready:function(){
	    	var me = this;
	    	me.dialogParams = MainFrameUtil.getParams("generatorAdd");
	       //按钮初始化
		   MainFrameUtil.setDialogButtons(me.getButtons(),"generatorAdd");
	       //获取数据 
	       var rows =  me.dialogParams.row;
	       var priceRow = [{type:"合同电价"}];
	       if(rows!=null&&rows.length>0){
	    	   me.row = rows[0];
	    	   priceRow =  [rows[1]];
	       }
	       me.elecId = me.dialogParams.elecId;
	       
	       me.params.phmPpa = me.row;
	  	   //初始化表单
	  	  ComponentUtil.getFormFields("purchase.agreement.phmppa",me);
		  	  
	  	   //初始化表格
	  	  ComponentUtil.buildGrid("purchase.agreement.phmgeneratormonthpq",$("#generatorAddGrid"),{
	  		      rownumbers:false,
	  		      nowrap: false,
	  		      fitColumns:true,
	  		      fit:true,
	  		      data:[],
	  		      rowStyler:function(idx,row){
	  		          return "height:35px;font-size:12px;";
	  		      },
	  		      columnsReplace:[
					{field:'geneName',title:'发电单元名称',hidden:true},
					{field:'type',hidden:true},
					{field:'generatorId',title:'发电单元名称',
						 editor:{type:'combobox',options:{
						   url:basePath+"cloud-purchase-web/powerPlantController/getPhcElecInfoList?elecId=" + me.elecId,
					       method:'get',
					       valueField:'value',
					       textField:'name',
					       editable:false, 
					       panelHeight: 'auto',
					       onSelect:function(param){
					    	   generatorAddVue.geneName = param.name;
					       }
					}}},
					{field:'jan',title:'1月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					},
					 {field:'feb',title:'2月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'mar',title:'3月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						 }
					 },
					 {field:'apr',title:'4月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'may',title:'5月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'jun',title:'6月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'jul',title:'7月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'aug',title:'8月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'sep',title:'9月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'oct',title:'10月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						  }
					 },
					 {field:'nov',title:'11月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						}
					 },
					 {field:'dec',title:'12月',
						
						formatter:function(value,index,row){
							  if(value){
								  return parseFloat(value);
							  }else{
								  return value;
							  }
						}}       
	  		      ],
	  		      onLoadSuccess:function(data){
				  	if(generatorAddVue.row){
			  			var obj = generatorAddVue.row;
			  			$("#generatorAddGrid").datagrid("insertRow",{index:0,row:obj});
			  			if(obj.distributionMode == "02"){
			  				//添加编辑器
			    		    generatorAddVue.editEditor("addEditor");
			    			//锁定
			    			generatorAddVue.saveDecimalFlag = true;
			    			//设置非必填
			    			generatorAddVue.saveDecimalReqFlag = false;
							$("#generatorAddGrid").datagrid("beginEdit",0); 
			  			}else{
			  				//删除编辑器
			    		    generatorAddVue.editEditor("removeEditor");
			  				$("#generatorAddGrid").datagrid("beginEdit",0); 
			  			}
			  			generatorAddVue.row = null;
				  	}
	  		      } 
	  		  })
	  		  $('#priceAddGrid').datagrid({
				 data:priceRow,
				 columns:[[
						{field:'type',title:' ',width:80},
						{field:'jan',title:'1月',width:80,editor:me.getPriceEditor(1)},
						{field:'feb',title:'2月',width:80,editor:me.getPriceEditor(2)},
						{field:'mar',title:'3月',width:80,editor:me.getPriceEditor(3)},
						{field:'apr',title:'4月',width:80,editor:me.getPriceEditor(4)},
						{field:'may',title:'5月',width:80,editor:me.getPriceEditor(5)},
						{field:'jun',title:'6月',width:80,editor:me.getPriceEditor(6)},
						{field:'jul',title:'7月',width:80,editor:me.getPriceEditor(7)},
						{field:'aug',title:'8月',width:80,editor:me.getPriceEditor(8)},
						{field:'sep',title:'9月',width:80,editor:me.getPriceEditor(9)},
						{field:'oct',title:'10月',width:80,editor:me.getPriceEditor(10)},
						{field:'nov',title:'11月',width:80,editor:me.getPriceEditor(11)},
						{field:'dec',title:'12月',width:80,editor:me.getPriceEditor(12)},
				           ]],
				 nowrap: false,
				 fitColumns:true,
				 onLoadSuccess:function(){
					 $('#priceAddGrid').datagrid("options").onLoadSuccess=function(){};
					 $('#priceAddGrid').datagrid("beginEdit",0);
				 }
			})
	    },
	    methods: {
	    	//设置按钮
	    	getButtons:function(){
				var buttons = [{text:"保存 ",type:"primary",handler:this.save},{text:"取消 ",handler:function(){
					MainFrameUtil.setParams({row:null},"generatorAdd");
					MainFrameUtil.closeDialog("generatorAdd");
					}}];
				return buttons;
			},
	    	
	    	//设置分解方式
	    	distChange:function(){
	    		var that = this;
	    		if(!this.row){
	    			if(this.params.phmPpa.distributionMode == "01"){//等比自动分解
		    			//取消锁定
		    			if(this.saveDecimalFlag){
		    				this.saveDecimalFlag = false;
		    			}
		    			//设置必填
		    			if(!this.saveDecimalReqFlag){
		    				this.saveDecimalReqFlag = true;
		    			}
		    			//判断是否存在合同电量
		    			if(!this.params.phmPpa.totaPq){
		    				MainFrameUtil.alert({title:"信息",body:"请填写合同电量！"});
		    				this.params.phmPpa.distributionMode = "";
							return;
		    			}
		    			var editor = $("#generatorAddGrid").datagrid("getEditor",{index:0,field:"generatorId"});
		    			var generatorId = "";
		    			if(editor){
		    				generatorId = $(editor.target).combobox("getValue");
			    			 $("#generatorAddGrid").datagrid("endEdit",0);
			    			//删除编辑器
			    		    this.editEditor("removeEditor");
		    			}else{
		    				//删除编辑器
			    		    this.editEditor("removeEditor");
		    			}
		    			var totalPq = Number(this.params.phmPpa.totaPq);//合同电量
		    			var rows = new Array();
		    			var obj = new Object();
		    			var dec = 0;//小数位
		    			if(this.params.phmPpa.saveDecimal){
		    				dec = parseInt(this.params.phmPpa.saveDecimal);
		    			}
		    			var monthMap = ["jan" ,"feb" ,"mar" ,"apr" ,"may" ,"jun" ,"jul" ,"aug" ,"sep" ,"oct" ,"nov" ,"dec"];
		    			var monthCount = that.dialogParams.endMonth - that.dialogParams.bgnMonth + 1;
						var pqAvg = (totalPq/monthCount).toFixed(dec);//每月平均电量
						var pqLast = (totalPq - pqAvg*(monthCount - 1)).toFixed(dec);
						obj["generatorId"] = generatorId;
						for(var i = 0; i < monthCount; i++){
							obj[monthMap[i + that.dialogParams.bgnMonth - 1]] = pqAvg;
						}
						obj[monthMap[that.dialogParams.endMonth - 1]] = pqLast;
						rows.push(obj);
						$("#generatorAddGrid").datagrid("loadData",rows);
						$("#generatorAddGrid").datagrid("beginEdit",0);
		    		}else if(this.params.phmPpa.distributionMode == "02"){//手动分解
		    			if(!this.saveDecimalFlag){
		    				//锁定
			    			this.saveDecimalFlag = true;
			    			//设置非必填
			    			this.saveDecimalReqFlag = false;
			    			//清空保留小数位
			    			this.params.phmPpa.saveDecimal = "";
			    			var rows = $("#generatorAddGrid").datagrid("getRows");
			    			//添加编辑器
			    			this.editEditor("addEditor");
			    			if(rows != null && rows.length != 0){
			    				$("#generatorAddGrid").datagrid("endEdit",0);
			    				//添加编辑器
				    			//this.editEditor("addEditor");
			    				$("#generatorAddGrid").datagrid("beginEdit",0);
			    			}else{
			    				var obj = new Object();
			    				$("#generatorAddGrid").datagrid("insertRow",{index:0,row:obj});
			    				$("#generatorAddGrid").datagrid("beginEdit",0);
			    			}
		    			}
		    		}
	    		}
	    	},
	    	getPriceEditor: function(month){
	    		var that = this;
	    		var cont = {type:'numberbox',options:{precision:2,min:0,required:true}};
	    		 if(month >= that.dialogParams.bgnMonth && month <= that.dialogParams.endMonth){
 	        		return cont;
 	        	}else{
 	        		return null;
 	        	}
	    	},
	    	getEditor: function(month){
	    		var that = this;
	    		var cont = {
		    			type:'numberbox',options:{precision:4,min:0},
						formatter:function(value,index,row){
						  if(value){
							  return parseFloat(value);
						  }else{
							  return value;
						  }
						}
					 };
	    		 if(month >= that.dialogParams.bgnMonth && month <= that.dialogParams.endMonth){
 	        		return cont;
 	        	}else{
 	        		return null;
 	        	}
	    	},
	    	//修改编辑器
	    	editEditor:function(method){
	    		var that = this;
    			$("#generatorAddGrid").datagrid(method,[
    			{field:'jan',
    	        	editor :that.getEditor(1)},
    	        {field:'feb',
	    	        editor : that.getEditor(2)},
    	        {field:'mar',
	    	        editor : that.getEditor(3)},
    	        {field:'apr',
	    	        editor : that.getEditor(4)},
	   	        {field:'may',
	    	        editor : that.getEditor(5)},
    	        {field:'jun',
	    	        editor : that.getEditor(6)},
    	        {field:'jul',
	    	        editor : that.getEditor(7)},
    	        {field:'aug',
	    	        editor : that.getEditor(8)},
    	        {field:'sep',
	    	        editor : that.getEditor(9)},
    	        {field:'oct',
	    	        editor : that.getEditor(10)},
    	        {field:'nov',
	    	        editor : that.getEditor(11)},
    	        {field:'dec',
	    	        editor : that.getEditor(12)},
    			]);
	    	},
	    	
	    	//验证合同电量信息
			validatePqGrid:function(){
				var pqData = new Object();
				var editors = $("#generatorAddGrid").datagrid("getEditors",0);
				var row = $("#generatorAddGrid").datagrid("getRows")[0];
				for(var obj in editors){
					var value = null;
					if(editors[obj].field == "generatorId"){
						value = $(editors[obj].target).combobox("getValue");
					}else{
						value = $(editors[obj].target).val();
					}
					if(!value){
						return null;
					}
					pqData[editors[obj].field] = value;
				}
				if(this.params.phmPpa.distributionMode == "01"){
					var generatorId = pqData["generatorId"];
					pqData = row;
					pqData["generatorId"] = generatorId;
				}
				pqData["distributionMode"] = this.params.phmPpa.distributionMode;
				if(!this.params.phmPpa.saveDecimal){
					this.params.phmPpa.saveDecimal = null;
				}
				pqData["saveDecimal"] = this.params.phmPpa.saveDecimal;
				pqData["totaPq"] = this.params.phmPpa.totaPq;
				pqData["geneName"] = this.geneName;
				pqData["type"] = "合同分月电量";
				return pqData;
			},
			
			//验证合同电量分配是否合理
			validatePqDist:function(row){
				var flag = false;
				var saveDecimal = 0;
				if(this.params.phmPpa.distributionMode == "02"){
					saveDecimal = 4;
				}
				var countPq = 0;
				for(var obj in row){//求分配电量的和
					var pq = Number(row[obj]);
					if(obj != "generatorId" && obj != "distributionMode" && obj != "saveDecimal" && obj != "totaPq" && obj != "geneName"){
						if(!isNaN(pq)){
							countPq += pq;
						}
					}
				}
				if(this.params.phmPpa.saveDecimal){
					saveDecimal = parseInt(this.params.phmPpa.saveDecimal);
				}
				if(Number(this.params.phmPpa.totaPq).toFixed(saveDecimal) == countPq.toFixed(saveDecimal)){
					flag = true;
				}
				return flag;
			},
			
			save:function(){
				var me = this;
				//验证合同电量信息
				me.$refs.form1.valid();
	        	if(me.valid===false){
	        		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'})
	        		return false;
	        	}
				var pqRow = me.validatePqGrid();
				if(pqRow === null){
					MainFrameUtil.alert({title:"提示",body:"请完成发电单元分月交易电量信息！"});
					return;
				}
				//验证合同电量是否分配完
				if(!me.validatePqDist(pqRow)){
					MainFrameUtil.alert({title:"提示",body:"电量分配不合理！"});
					return;
				} 
				//校验电价
				var flag = $('#priceAddGrid').datagrid('validateRow',0);
				if(!flag){
					MainFrameUtil.alert({title:"提示",body:"请完善电价信息！"});
					return;
				}
				$('#priceAddGrid').datagrid('endEdit',0)
				var priceRow = $('#priceAddGrid').datagrid('getRows');
				var newRow = [];
				newRow.push(pqRow);
				newRow.push(priceRow[0]);
				MainFrameUtil.setParams({row:newRow},"generatorAdd");
             	MainFrameUtil.closeDialog("generatorAdd");
			}
	    }
	}); 
</script>
</body>
</html>
