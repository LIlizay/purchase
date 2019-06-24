<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>管理员工具-交易中心数据维护编辑</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>

<body id="addDiv" v-cloak>
<su-form 
       v-ref:form1 
       offpos-style="true"
       id="fms1" 
       :data-option="dataOption" 
       :set-defaults="setDefaults" 
       :data-validator.sync="valid" >
<mk-panel title="交易中心发布数据维护" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 年月-->
            <mk-form-col :label="formFields.ym.label" required_icon="true" >
                <su-datepicker format="YYYYMM" :data.sync="queryParams.phfTradingCenter.ym" name="ymMust"></su-datepicker>
            </mk-form-col>
	
	         <!-- 供需比 -->
             <mk-form-col :label="formFields.supplyDemandRatio.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.supplyDemandRatio" name="supplyDemandRatioMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 总成交电量 -->
             <mk-form-col :label="formFields.totalDealPq2.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.totalDealPq" name="totalDealPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时 '}"></su-textbox>
            </mk-form-col>
           
            <!-- 成交平均价 -->
             <mk-form-col :label="formFields.avgDealPrc2.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.avgDealPrc" name="avgDealPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>

<mk-panel title="发电侧数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.elecNum.label" required_icon="true" >
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecNum" name="elecNumMust" ></su-textbox>
            </mk-form-col>
	
	         <!--成交数量 -->
             <mk-form-col :label="formFields.elecDealNum.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecDealNum" name="elecDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
           
          <mk-form-row>
            <!-- 未成交数量 -->
             <mk-form-col :label="formFields.elecUndealNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.elecUndealNum" name="elecUndealNumMust"></su-textbox>
            </mk-form-col>
            
            <!-- 申报最低电价 -->
             <mk-form-col :label="formFields.elecLowerPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecLowerPrc" name="elecLowerPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	     
	     <mk-form-row>
            <!-- 申报最高电价 -->
             <mk-form-col :label="formFields.elecUpperPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecUpperPrc" name="elecUpperPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 最高成交价 -->
             <mk-form-col :label="formFields.elecMaxPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecMaxPrc" name="elecMaxPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	    
	      <mk-form-row>
            <!-- 发电侧申报电量-->
             <mk-form-col :label="formFields.elecReportPq.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.elecReportPq" name="elecReportPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
	
	<mk-panel title="" line="false" height="200px">
	     <div class="pull-right" slot="buttons" style="text-align:right" v-cloak>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus"  v-on:click="addGrid(1)">新增</su-button>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o"  v-on:click="deleteGrid(1)">删除</su-button>
		 </div>
		<table id="elecGenerationGrid" height="200px"></table>
	</mk-panel>
</mk-panel>

<mk-panel title="售电公司数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.compNum.label" required_icon="true" >
                <su-textbox :data.sync="queryParams.phfTradingCenter.compNum" name="compNumMust"></su-textbox>
            </mk-form-col>
	
	         <!-- 成交数量 -->
             <mk-form-col :label="formFields.compDealNum.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.compDealNum" name="compDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
           
          <mk-form-row>
            <!-- 成交电量 -->
             <mk-form-col :label="formFields.compUndealNum.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.compUndealNum" name="compUndealNumMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 成交均价 -->
             <mk-form-col :label="formFields.compDealAvgPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.compDealAvgPrc" name="compDealAvgPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>
  
<mk-panel title="大用户数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 参与数量-->
            <mk-form-col :label="formFields.consNum.label" required_icon="true" >
                <su-textbox :data.sync="queryParams.phfTradingCenter.consNum" name="consNumMust"></su-textbox>
            </mk-form-col>
	
	         <!-- 成交数量 -->
             <mk-form-col :label="formFields.consDealNum.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.consDealNum" name="consDealNumMust"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 成交电量 -->
             <mk-form-col :label="formFields.consUndealNum.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.consUndealNum" name="consUndealNumMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 成交均价 -->
             <mk-form-col :label="formFields.consDealAvgPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.consDealAvgPrc" name="consDealAvgPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
</mk-panel>

<mk-panel title="用电侧数据" height="">
	<mk-form-panel title="" num="2" margin_bottom="10px">
		 <mk-form-row>
	     	<!-- 申报最高电价-->
            <mk-form-col :label="formFields.purcUpperPrc.label" required_icon="true" >
                <su-textbox :data.sync="queryParams.phfTradingCenter.purcUpperPrc" name="purcUpperPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
	
	         <!-- 申报最低电价 -->
             <mk-form-col :label="formFields.purcLowerPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.purcLowerPrc" name="purcLowerPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
          </mk-form-row>
            
          <mk-form-row>
            <!-- 最低成交价 -->
             <mk-form-col :label="formFields.purcMinPrc.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.purcMinPrc" name="purcMinPrcMust" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox>
            </mk-form-col>
            
            <!-- 用电侧申报电量 -->
             <mk-form-col :label="formFields.purcReportPq.label" required_icon="true">
                <su-textbox :data.sync="queryParams.phfTradingCenter.purcReportPq" name="purcReportPqMust" :addon="{'show':true,'rightcontent':'亿千瓦时'}"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	     
	     <mk-form-row>
            <!-- 参与总数 -->
             <mk-form-col :label="formFields.purcNum.label" required_icon="true">
                <su-textbox disabled="true" :data.sync="queryParams.phfTradingCenter.purcNum" name="purcNumMust"></su-textbox>
            </mk-form-col>
	     </mk-form-row>
	</mk-form-panel>
	
	<mk-panel title="" line="false" height="200px">
	     <div class="pull-right" slot="buttons" style="text-align:right" v-cloak>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus"  v-on:click="addGrid(2)">新增</su-button>
	         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o"  v-on:click="deleteGrid(2)">删除</su-button>
		 </div>
		<table id="elecUseGrid" height="200px"></table>
	</mk-panel>
</mk-panel>
</su-form>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var editIndex = undefined;
var editIndex2 = undefined;
$.validator.addMethod(     
		"isRate",        
		function(value, element){            
		    var values=value.split(":");
		    var tel = /^([^0\D]\d*)?(\d?\.\d+)?0?$/;  
		    return this.optional(element) || ((tel.test(parseInt(values[0]))&&tel.test(parseInt(values[1]))));     
		},
		"请输入【数字】:【数字】格式"
); 
$.validator.addMethod(     
        "isNum",        
        function(value, element)        
        {            
                var tel = /^([1-9]\d*|[0]{1,1})$/;  
                return this.optional(element) || (tel.test(value));     
        }     
        , "请填写正确格式的正整数！"            
); 
$.validator.addMethod(     
        "compareNum",        
        function(value, element)        
        {       
            var elecNum = parseInt(addVue.queryParams.phfTradingCenter.elecNum);
            var val = parseInt(value);
            return this.optional(element) || (elecNum >= val);     
        }     
        , "参与数量必须大于或等于成交数量！"            
);  
var centerId='${param.centerId}';
 var addVue=new Vue({
	el: '#addDiv',
	data: {
		formFields:{}, 
		numRegax : /^([1-9]\d*|[0]{1,1})$/,
		queryParams:{"phfTradingCenter":{"elecUndealNum":null,"purcNum":null}},
		dataOption: {
            rules: {  
            	ymMust: "required",
            	supplyDemandRatioMust: {required:!0,isRate:!0},
            	totalDealPqMust: {required:!0,number:!0},
            	avgDealPrcMust: {required:!0,number:!0},  
            	elecReportPqMust: {required:!0,number:!0},
                elecNumMust: {required:!0,isNum:!0},
                elecDealNumMust: {required:!0,isNum:!0,compareNum:!0},
                elecUndealNumMust: {required:!0,number:!0},
                elecLowerPrcMust: {required:!0,number:!0},
                elecUpperPrcMust: {required:!0,number:!0},
                elecMaxPrcMust: {required:!0,number:!0},
                compNumMust: {required:!0,isNum:!0},
                compDealNumMust: {required:!0,isNum:!0},
                compUndealNumMust: {required:!0,number:!0},
                compDealAvgPrcMust: {required:!0,number:!0},
                consNumMust: {required:!0,isNum:!0},
                consDealNumMust: {required:!0,isNum:!0},
                consUndealNumMust: {required:!0,number:!0},
                consDealAvgPrcMust: {required:!0,number:!0},
                purcUpperPrcMust: {required:!0,number:!0},
                purcLowerPrcMust: {required:!0,number:!0},
                purcMinPrcMust: {required:!0,number:!0},
                purcReportPqMust: {required:!0,number:!0},
                purcNumMust: {required:!0,number:!0}
            }
        },
        valid: false
	},
	methods: {
		confirm:function(){
			this.endEditing("elecGenerationGrid");
			this.endEditing("elecUseGrid");
			var elecGenerationGrid=$('#elecGenerationGrid').datagrid('getRows');
			var elecUseGrid=$('#elecUseGrid').datagrid('getRows');
			var sum=0;
			for(var i=0;i<elecGenerationGrid.length;i++){
				sum=sum+parseFloat(elecGenerationGrid[i].intervalProp);
				if(sum>100){
					MainFrameUtil.alert({
                        title:"提示",
                        body:"发电侧数据区间占比超出100%"
                    })
                    return;
				}
			}
			var sum2=0;
			for(var i=0;i<elecUseGrid.length;i++){
				sum2=sum2+parseFloat(elecUseGrid[i].intervalProp);
				if(sum2>100){
					MainFrameUtil.alert({
                        title:"提示",
                        body:"用电侧数据区间占比超出100%"
                    })
                    return;
				}
			}
			this.$refs.form1.valid();
			this.queryParams.phfElecReportList=elecGenerationGrid;
			this.queryParams.phfPurcReportList=elecUseGrid;
            if(this.valid==true){
            	//验证成交数是否小于参与数
            	if(parseInt(this.queryParams.phfTradingCenter.elecNum) < parseInt(this.queryParams.phfTradingCenter.elecDealNum)){//发电侧
            		MainFrameUtil.alert({title:"提示",body:"发电侧成交数应小于参与数！"})
                    return;
            	}
            	if(parseInt(this.queryParams.phfTradingCenter.compNum) < parseInt(this.queryParams.phfTradingCenter.compDealNum)){//发电侧
            		MainFrameUtil.alert({title:"提示",body:"售电公司成交数应小于参与数！"})
                    return;
            	}
            	if(parseInt(this.queryParams.phfTradingCenter.consNum) < parseInt(this.queryParams.phfTradingCenter.consDealNum)){//发电侧
            		MainFrameUtil.alert({title:"提示",body:"大用户成交数应小于参与数！"})
                    return;
            	}
            	$.ajax({
					url:basePath+"cloud-purchase-web/phfTradingCenterController/updateForecastInfo",
					type:'post',
					data:$.toJSON(this.queryParams),
		 			contentType : 'application/json;charset=UTF-8',
		 			success:function(data){
		 				if(data.data=="1"){
		 					MainFrameUtil.alert({
		 	                     title:"提示",
		 	                     body:"当前月份数据已经存在"
		 	                 })
		 	                 return;
		 				}
		 				MainFrameUtil.closeDialog();
		 			}
				})
            }
		},

		//取消编辑
		endEditing:function(gridName){
			if(gridName=="elecGenerationGrid"&&editIndex!=undefined){
				$('#elecGenerationGrid').datagrid('endEdit', editIndex);
                editIndex = undefined;
			}else if(gridName=="elecUseGrid"&&editIndex2!=undefined){
				$('#elecUseGrid').datagrid('endEdit', editIndex2);
                editIndex2 = undefined;
			}
		},
		
		addGrid:function(num){
			var gridName="";
			if(num=="1"){
				gridName="elecGenerationGrid";
			}else if(num=="2"){
				gridName="elecUseGrid";
			}
			this.endEditing(gridName);//取消正在编辑的行
			
			var lastIndex = $('#'+gridName).datagrid('getRows').length-1;
			if(lastIndex !=-1){ //列表中存在记录
				var lastRow = $('#'+gridName).datagrid('getRows')[lastIndex];
				if(lastRow.reportingInterval == null || lastRow.reportingInterval == ""
						||lastRow.intervalProp==null||lastRow.intervalProp==""){//最后行的记录为空
                    $('#'+gridName).datagrid('selectRow', lastIndex);
                    MainFrameUtil.alert({title:"提示",body:"请先完善当前行信息"});
                    return;
                }
			}
			
			$('#'+gridName).datagrid('appendRow',{"reportingInterval":""});
			//获取编辑行进行编辑
			if(gridName=="elecGenerationGrid"){
				editIndex = $('#elecGenerationGrid').datagrid('getRows').length-1;
	            $('#elecGenerationGrid').datagrid('beginEdit', editIndex);
			}else if(gridName=="elecUseGrid"){
				editIndex2 = $('#elecUseGrid').datagrid('getRows').length-1;
	            $('#elecUseGrid').datagrid('beginEdit', editIndex2);
			}
		},
		
		deleteGrid:function(num){
			if(num=="1"){
				$('#elecGenerationGrid').datagrid('deleteRow', editIndex);
			}else if(num=="2"){
				$('#elecUseGrid').datagrid('deleteRow', editIndex2);
			}
		},
		
		getForecastInfo:function(){
			var me=this;
			$.ajax({
				url:basePath+"cloud-purchase-web/phfTradingCenterController/getForecast/"+centerId,
				type:'get',
	 			success:function(data){
	 				me.queryParams.phfTradingCenter=data.data.phfTradingCenter;
	 				$('#elecGenerationGrid').datagrid('loadData',data.data.phfElecReportList);
	 				$('#elecUseGrid').datagrid('loadData', data.data.phfPurcReportList);
	 			}
			})
		}
	},
	ready:function(){
		var me=this;
		var methods={"confirmHandler":this.confirm};
        MainFrameUtil.setParams(methods);
        
        this.getForecastInfo();
		
		ComponentUtil.buildGrid("purchase.forecast.phfelecreport",$("#elecGenerationGrid"),{ 
	        height:'50%',
	        method: 'post',
	        rownumbers: true,
	        nowrap: true, 
	        fitColumns:true,
	        columnsReplace:[
	        	{field:'reportingInterval',title:'申报区间(元/兆瓦时)',
	             editor:{
		                  type:'text'
	                    }
	        	},
	        	{field:'intervalProp',title:'区间占比(%)',
		             editor:{
			                  type:'numberbox',
			                  options:{precision:4,min:0}
		                    }
		        }
	        ],
            onClickCell: function(index, field, value){
    			if (editIndex != index){
    				addVue.endEditing("elecGenerationGrid");
   					$('#elecGenerationGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
   					var ed = $('#elecGenerationGrid').datagrid('getEditor', {index:index,field:field});
   					if(ed != null){		//若点击的是可编辑的单元格，则编辑时默认focus此编辑框
    					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
   					}
   					editIndex = index;
    			}
    		}
	    }); 
		
		ComponentUtil.buildGrid("purchase.forecast.phfpurcreport",$("#elecUseGrid"),{ 
	        height:'50%',
	        method: 'post',
	        rownumbers: true,
	        nowrap: true, 
	        fitColumns:true,
	        columnsReplace:[
   	        	{field:'reportingInterval',title:'申报区间(元/兆瓦时)',
   	             editor:{
   		                  type:'text'
   	                    }
   	        	},
   	        	{field:'intervalProp',title:'区间占比(%)',
   		             editor:{
   			                  type:'numberbox',
   			                  options:{precision:4,min:0}
   		                    }
   		        }
	        ],
            onClickCell: function(index, field, value){
    			if (editIndex2 != index){
    				addVue.endEditing("elecUseGrid");
   					$('#elecUseGrid').datagrid('selectRow', index).datagrid('beginEdit', index);
   					var ed = $('#elecUseGrid').datagrid('getEditor', {index:index,field:field});
   					if(ed != null){		//若点击的是可编辑的单元格，则编辑时默认focus此编辑框
    					($(ed.target).data('textbox') ? $(ed.target).textbox('textbox') : $(ed.target)).focus();
   					}
   					editIndex2 = index;
    			}
    		}
	    }); 
		ComponentUtil.getFormFields("purchase.forecast.phftradingcenter",this); 
	},
	watch:{
		 "queryParams.phfTradingCenter.elecNum":function(val){
			  var other=this.queryParams.phfTradingCenter.elecDealNum;
			  if(val!=null&&val!=""&&other!=null&&other!=""){
				  if(this.numRegax.test(val)&&this.numRegax.test(other)){
					  this.queryParams.phfTradingCenter.elecUndealNum=val-other;
				  }
			  }
		  },
		  "queryParams.phfTradingCenter.elecDealNum":function(val){
			  var other=this.queryParams.phfTradingCenter.elecNum;
			  if(val!=null&&val!=""&&other!=null&&other!=""){
				  if(this.numRegax.test(val)&&this.numRegax.test(other)){
					  this.queryParams.phfTradingCenter.elecUndealNum=other-val;
				  }
			  } 
		  },
		  "queryParams.phfTradingCenter.compNum":function(val){
			  var other=this.queryParams.phfTradingCenter.consNum;
			  if(val!=null&&val!=""&&other!=null&&other!=""){
				  if(this.numRegax.test(val)&&this.numRegax.test(other)){
					  this.queryParams.phfTradingCenter.purcNum=parseInt(val)+parseInt(other);
				  }
			  }
		  },
		  "queryParams.phfTradingCenter.consNum":function(val){
			  var other=this.queryParams.phfTradingCenter.compNum;
			  if(val!=null&&val!=""&&other!=null&&other!=""){
				  if(this.numRegax.test(val)&&this.numRegax.test(other)){
					  this.queryParams.phfTradingCenter.purcNum=parseInt(val)+parseInt(other);
				  }
			  } 
		  }
    }
}); 
</script>
</body>
</html>