<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<meta charset="UTF-8">
  <title>合同管理-购电合同管理详情</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<%@include file="/plugins/business-core/static/headers/upload.jsp"%>
	<style type="text/css">
	/* 隐藏多附件的图标 */
		.mk-fileupload-btn{
			display: none;
		}
	</style>
</head>
<body>

<div id="ppaAdd">
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="电厂信息" line="true">
			<mk-form-panel   height="95"  label_width="160px">
				<mk-form-row>
					<!-- 电厂名称-->
		            <mk-form-col   :label="formFields.elecName.label" :class="{'display-none':!formFields.elecName.formHidden}" required_icon="true">
		            		<mk-trigger-text  :data.sync="elecName" editable="false" disabled></mk-trigger-text>
<%-- 		                <su-select url="${Config.baseURL}cloud-purchase-web/phmPpaController/getPhcElecInfoList"  --%>
<!-- 		                :select-value.sync="params.phmPpa.elecId" name="elecId"  label-name="elecName" value-name="id" disabled></su-select> -->
		            </mk-form-col>
		              <!-- 发电类别 -->
		             <mk-form-col :label='formFields.elecTypeCodeName.label' :class="{'display-none':!formFields.elecType.formHidden}" required_icon="true">
		                <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType"
						:select-value.sync="elecTypeCode" label-name="name" name="elecTypeCode" disabled></su-select>
		            </mk-form-col>
		             <!-- 电力业务许可证编号（发电类）  -->
		            <mk-form-col :label="formFields.licenseNo.label" :class="{'display-none':!formFields.licenseNo.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.licenseNo" name="licenseNo" disabled></su-textbox>
		            </mk-form-col>
					
					
					
			</mk-form-panel>
		</mk-panel>
		<mk-panel  title="合同基本信息" line="true">
			<mk-form-panel height="325"  label_width="160px">
				<mk-form-row>
					<!-- 合同编号 -->
		            <mk-form-col   :label="formFields.agreNo.label":class="{'display-none':!formFields.agreNo.formHidden}" required_icon="false">
		                <su-textbox disabled="disabled" name="agreNo" :data.sync="params.phmPpa.agreNo"></su-textbox>
		            </mk-form-col>
		            <!-- 交易中心合同序列号 -->
		            <mk-form-col   :label="formFields.serialNo.label":class="{'display-none':!formFields.serialNo.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.serialNo" disabled></su-textbox>
		            </mk-form-col>
					<!-- 合同名称 -->
		            <mk-form-col :label="formFields.agreName.label" required_icon="true" :class="{'display-none':!formFields.agreName.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.agreName" name="agreName" disabled></su-textbox>
		            </mk-form-col>
				</mk-form-row>
				<mk-form-row>
					<!-- 合同类型-->
			    	 <mk-form-col :label="formFields.agreType.label" :class="{'display-none':!formFields.agreType.formHidden}">
		            	<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_agreType" 
		                :select-value.sync="params.phmPpa.agreType" name="agreType"  label-name="name" disabled></su-select>
		            </mk-form-col>
					
		            <!-- 合同电量  -->
		            <mk-form-col :label="formFields.agrePqForm.label" :class="{'display-none':!formFields.agrePq.formHidden}" required_icon="true">
		                <su-textbox :data.sync="params.phmPpa.agrePq" name="agrePq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}" disabled></su-textbox>
		            </mk-form-col>
		            <!--是否含税  -->
		           	<mk-form-col   :label="formFields.taxFlag.label">
		    			<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" :select-value.sync="params.phmPpa.taxFlag" 
		    				name="taxFlag"  label-name="name" disabled></su-select>
					</mk-form-col>
				</mk-form-row>
				<mk-form-row>
		             <!--合同开始日期  -->
		           	 <mk-form-col :label="formFields.bgnDate.label":class="{'display-none':!formFields.bgnDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.bgnDate" name="bgnDate" disabled></su-datepicker>
			            </mk-form-col>
		             <!--合同结束日期  -->
		           	 <mk-form-col :label="formFields.endDate.label":class="{'display-none':!formFields.endDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.endDate" name="endDate" disabled></su-datepicker>
			            </mk-form-col>
			            <!--合同签订日期  -->
		           	 <mk-form-col :label="formFields.signDate.label":class="{'display-none':!formFields.signDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.signDate" name="signDate" disabled></su-datepicker>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row>
		             <!--售电公司签订人  -->
		           	 <mk-form-col :label="formFields.partyB.label" :class="{'display-none':!formFields.partyB.formHidden}" required_icon="true">
	           		 	<su-textbox :data.sync="params.phmPpa.partyB" name="partyB"  disabled></su-textbox>
			         </mk-form-col>
			         <!--电厂签订人 -->
		           	 <mk-form-col :label="formFields.partyA.label" :class="{'display-none':!formFields.partyA.formHidden}" required_icon="true">
			            <su-textbox :data.sync="params.phmPpa.partyA" name="partyA" disabled></su-textbox>
			         </mk-form-col>
			         <!--备案时间  -->
		           	 <mk-form-col :label="formFields.recordDate.label":class="{'display-none':!formFields.recordDate.formHidden}">
			         	<su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.recordDate" name="recordDate" disabled></su-datepicker>
			          </mk-form-col>
					 </mk-form-row>
					 <mk-form-row>
		             <!--签订地点  -->
		           	 <mk-form-col :label="formFields.signAddr.label" colspan="3" :class="{'display-none':!formFields.signAddr.formHidden}">
			           		<su-textbox :data.sync="params.phmPpa.signAddr" disabled></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row height="auto">
						 <!--附件  -->
		           	 	<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" required_icon="true" colspan="3">
							<mk-multifile-upload :params="uploadParams1" :files.sync="fileInfos"   v-ref:uploadfile  show_upload="false"   @upload-error="uploadError" 
		                    disabled="disabled" @upload-success="uploadSuccess"  url='${Config.getConfig("file.service.url")}' required="true" maxcount=20>
		                  </mk-multifile-upload>
						</mk-form-col>
					</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="发电单元分月交易电量电价（兆瓦时、元/兆瓦时）" height="300px" line="true">
			<div id="pqGrid" style="height:100%"></div>
		</mk-panel>
<!-- 		<mk-panel  title="合同偏差处理"  line="true"> -->
<!-- 			<mk-form-panel label_width="160px"> -->
<!-- 				<mk-form-row> -->
<!-- 					售电公司正偏差 -->
<!-- 		           	<mk-form-col :label="formFields.upperCheckFlag.label" :class="{'display-none':!formFields.upperCheckFlag.formHidden}" required_icon="true"> -->
<%-- 		    			<su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag"  name="upperCheckFlag"  --%>
<!-- 		    			:select-value.sync="params.phmAgreDeviation.upperCheckFlag"@su-change="upperCheckFlagChange" disabled></su-select> -->
<!-- 					</mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row :class="{'display-none':!upperCheckFlag}"> -->
<!-- 					允许正偏差范围  -->
<!-- 			        <mk-form-col :label="formFields.upperPqProp.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.upperPqProp.formHidden}" label-align="right" > -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.upperPqProp" :addon="{'show':true,'rightcontent':'%'}" name="upperPqProp" disabled></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 					惩罚电价基准  -->
<!-- 			        <mk-form-col :label="formFields.upperType.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.upperType.formHidden}" label-align="right">							 -->
<!-- 		                 <su-select label-name="name" @su-change="upperTypeChange" :select-value.sync="params.phmAgreDeviation.upperCheckPrcType" name="upperCheckPrcType" -->
<%-- 		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_punishPriceBaseCode" disabled></su-select> --%>
<!-- 			        </mk-form-col> -->
<!-- 					 惩罚比例值  -->
<!-- 			        <mk-form-col :label="formFields.upperCheckPrcProp.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="!upperPrcFlag" :class="{'display-none':!formFields.upperCheckPrcProp.formHidden}" label-align="right"> -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.upperCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}" name="upperCheckPrcProp" disabled></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 			        正偏差惩罚协议价 -->
<!-- 			            <mk-form-col :label="formFields.upperCheckPrc.label" label-width="200px" required_icon="true" -->
<!-- 									v-if="upperPrcFlag" :class="{'display-none':!formFields.upperCheckPrc.formHidden}" label-align="right"> -->
<!-- 			                <su-textbox :data.sync="params.phmAgreDeviation.upperCheckPrc" name="upperCheckPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox> -->
<!-- 			            </mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row> -->
<!-- 				售电公司负偏差  -->
<!-- 		           	<mk-form-col :label="formFields.lowerCheckFlag.label" required_icon="true"> -->
<%-- 		    			<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.phmAgreDeviation.lowerCheckFlag" --%>
<!-- 		    			 name="lowerCheckFlag"  label-name="name" @su-change="lowerCheckFlagChange" disabled></su-select> -->
<!-- 					</mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row :class="{'display-none':!lowerCheckFlag}"> -->
<!-- 					允许负偏差范围  -->
<!-- 			        <mk-form-col :label="formFields.lowerPqProp.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.lowerPqProp.formHidden}" label-align="right" > -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.lowerPqProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerPqProp" disabled></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 					惩罚电价基准  -->
<!-- 			        <mk-form-col :label="formFields.lowerType.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.lowerType.formHidden}" label-align="right">							 -->
<!-- 		                 <su-select label-name="name" @su-change="lowerTypeChange" :select-value.sync="params.phmAgreDeviation.lowerCheckPrcType" name="lowerCheckPrcType" -->
<%-- 		                 		url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/purchase_punishPriceBaseCode" disabled></su-select> --%>
<!-- 			        </mk-form-col> -->
<!-- 					 惩罚比例值  -->
<!-- 			        <mk-form-col :label="formFields.lowerCheckPrcProp.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="!lowerPrcFlag" :class="{'display-none':!formFields.lowerCheckPrcProp.formHidden}" label-align="right"> -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.lowerCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerCheckPrcProp" disabled></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 			         负偏差惩罚协议价 -->
<!-- 			            <mk-form-col :label="formFields.lowerCheckPrc.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="lowerPrcFlag" :class="{'display-none':!formFields.lowerCheckPrc.formHidden}" label-align="right"> -->
<!-- 			                <su-textbox :data.sync="params.phmAgreDeviation.lowerCheckPrc" name="lowerCheckPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}" disabled></su-textbox> -->
<!-- 			            </mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 			</mk-form-panel> -->
<!-- 		</mk-panel> -->
		<mk-panel title="违约信息" line="true">
			<mk-form-panel  label_width="160px">
				<mk-form-row height="80px">
		            <!--备注  -->
		           	<mk-form-col :label="formFields.violExplain.label" colspan="3">
		    			<su-textbox type="textarea" rows="3" rols="10" :data.sync="params.phmPpa.violExplain" disabled></su-textbox>
					</mk-form-col>
				</mk-form-row>
			</mk-form-panel>
		</mk-panel>
	</su-form>
</div>
<script>
var basePath="${Config.baseURL}";
var ppaAddVue=new Vue({
    el: '#ppaAdd',
    data:{
        formFields:{},
        uploadParams1:{groupId:""},		//上传组件的参数
  		fileInfos: [], //多附件
        fileName:"",
        elecName:"",
        elecTypeCode:"",//发电类别
        saveDecimalFlag:false,//保留小数位编辑标志
        saveDecimalReqFlag:true,//保留小数位必填标志
        obj:null,//电量电价已保存信息
        upperCheckFlag:false,//正偏差惩罚展示标志
        lowerCheckFlag:false,//负偏差惩罚展示标志
        upperPrcFlag:false,//正偏差电价展示标志
        lowerPrcFlag:false,//正偏差电价展示标志
        selectFlag:true,//控制选择表格事件
        loadFlag:false,//判断表格是否加载完成
        params:{
        	phmPpa:{
        		id:null,
        		serialNo:"",//交易中心合同序列号
        		agreType:"",//合同类型
        		treatmentMethod:"",//当用电方超用合同电量时
        		signDate:"",//合同签订日期
        		endDate:"",//合同结束日期
        		signAddr:"",//签订地点
        		violExplain:"",//备注
        		elecId:"",//电厂名称
        		partyA:"",//售方签订人
        		agrePq:"",//合同电量
        		unTreatmentMethod:"",//当用电方未完成合同电量时
        		partyB:"",//购方签订人
        		recordDate:"",//备案时间
        		bgnDate:"",//合同开始日期
        		agreName:"",//合同名称
        		agreNo:"",//合同编号
        		fileId:"",//附件
        		recorder:"",//合同录入人员
        	},
        	phmAgreDeviation:{
        		upperCheckFlag:"",//售电公司正偏差
        		upperPqProp:"",//允许正偏差范围
        		upperCheckPrcType:"",//惩罚基准值
        		upperCheckPrcProp:"",//惩罚比例
        		upperCheckPrc:"",//正偏差考核电价
        		lowerCheckFlag:"",//售电公司负偏差
        		lowerPqProp:"",//允许负偏差范围
        		lowerCheckPrcType:"",//惩罚基准值
        		lowerCheckPrcProp:"",//惩罚比例
        		lowerCheckPrc:"",//负偏差考核电价
        	},
        	phmGeneratorMonthPqList:[],//合同机组电量分月信息
        	phmGenePqDistList:[],//合同机组电量分配方式信息
        }
    },
    ready:function(){
    	//初始化表单
		ComponentUtil.getFormFields("purchase.agreement.phmppa",this);
		this.params.phmPpa.id = MainFrameUtil.getParams("ppaDetail").id;
  	  	MainFrameUtil.setDialogButtons([{text:"取消",handler:function(){MainFrameUtil.closeDialog("ppaEdit")}}],"ppaEdit");
  		//初始化数据
  	  	this.initData();
	},
    methods : {
    	//初始化数据
    	initData:function(){
    		var me = this;
    		$.ajax({
        		url:basePath + 'cloud-purchase-web/phmPpaController/' + me.params.phmPpa.id,
        		type:"get",
        		success:function(data){
        			if(data.flag){
        				if(data.data){
          					me.params.phmPpa = data.data;
          					me.params.phmAgreDeviation = data.data.phmAgreDeviationDetail;
          					var file = data.data.fileId;
          					if(file != null && file != ''){
          						//单附件和多附件保存格式不同的
          						try{
          							if( eval('(' +file + ')').fileName != null ||  eval('(' +file + ')').fileName != ''){ //不是单附件的格式
          								me.fileInfos.push({id :  eval('(' +file + ')').fileId , name :  eval('(' +file + ')').fileName});
          							}
          						}catch(err){
          							var attachments = file.split("?");
              						for(var i=0; i < attachments.length; i++){
              							var attachment = attachments[i];
    									var fields = attachment.split(":");
    									if(fields.length == 2){
    										me.fileInfos.push({id : fields[0] , name : fields[1]});
    									}		
              						}
          						}
          					}
          					me.elecName = data.data.elecName;
          					me.elecTypeCode = data.data.elecTypeCode;
          					me.setGridData(data.data.phmGeneratorMonthPqDetailList);
        				}
      				}else{
      					MainFrameUtil.alert({title:"错误",body:data.msg});
      				} 
        		},
        		error:function(data){
        			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
        		}
        	})
    	},
    	
    	//初始化列表
    	initGrid:function(list){
    		var me = this;
    		ComponentUtil.buildGrid("purchase.agreement.phmgeneratormonthpq",$("#pqGrid"),{
    			data:[],
    			width:"100%",
 				singleSelect:false,
 			    nowrap: false,
 				fitColumns:true,
 	  		    rowStyler:function(idx,row){
 	  		          return "height:25px;font-size:12px;";
 	  		    },
 	  		 	columnsReplace:[
					{field:'generatorId',title:'发电单元名称',hidden:true},           
 	  		    ],
 	  		    onSelect:function(index,rows){
 	  		    	if(me.selectFlag){
 	  		    		me.selectFlag = false;
 	  		    		$("#pqGrid").datagrid("unselectAll");
 	 	  		    	if(index%2==0){
 	 	  		    		$("#pqGrid").datagrid("selectRow",index);
 	 	  		    		$("#pqGrid").datagrid("selectRow",index+1);
 	 	  		    	}else{
 	 	  		    		$("#pqGrid").datagrid("selectRow",index);
 	 	  		    		$("#pqGrid").datagrid("selectRow",index-1);
 	 	  		    	}
 	 	  		    	me.selectFlag = true;
 	  		    	}
 	  		   },
 	  		   onUnselect:function(index,rows){
	  				$("#pqGrid").datagrid("unselectAll");
 	  		   },
 	  		   onLoadSuccess:function(){
 	  			 	$("#pqGrid").datagrid("options").onLoadSuccess=function(){};
 	  			 	me.loadFlag = true;
 	  			 	for(var i=0;i<list.length;i=i+2){
						$("#pqGrid").datagrid("insertRow",{index:i,row:list[i]});
						$("#pqGrid").datagrid("insertRow",{index:i+1,row:list[i+1]});
 	  			 		$("#pqGrid").datagrid("mergeCells",{index:i,field:"geneName",rowspan:"2"});
 	  			 	}
 	  		   }
    		 });
    	},
    	//售电公司正偏差change
    	upperCheckFlagChange:function(){
    		if(this.params.phmAgreDeviation.upperCheckFlag == 1){
    			this.upperCheckFlag = true;
    		}else{
    			this.upperCheckFlag = false;
    			this.params.phmAgreDeviation.upperPqProp = "";
    			this.params.phmAgreDeviation.upperCheckPrcType = "";
    			this.params.phmAgreDeviation.upperCheckPrcProp ="";
    		}
    	},
    	
    	//惩罚基准值(正偏差change)
    	upperTypeChange:function(){
    		if(this.params.phmAgreDeviation.upperCheckPrcType == "01"){//人工录入
    			this.upperPrcFlag = true;
    			this.params.phmAgreDeviation.upperCheckPrcProp ="";
    		}else{
    			this.upperPrcFlag = false;
    			this.params.phmAgreDeviation.upperCheckPrc ="";
    		}
    	},
    	
    	//售电公司负偏差change
    	lowerCheckFlagChange:function(){
    		if(this.params.phmAgreDeviation.lowerCheckFlag == 1){
    			this.lowerCheckFlag = true;
    		}else{
    			this.lowerCheckFlag = false;
    			this.params.phmAgreDeviation.lowerPqProp = "";
    			this.params.phmAgreDeviation.lowerCheckPrcType = "";
    			this.params.phmAgreDeviation.lowerCheckPrcProp ="";
    		}
    	},
    	
    	//惩罚基准值(负偏差change)
    	lowerTypeChange:function(){
    		if(this.params.phmAgreDeviation.lowerCheckPrcType == "01"){//人工录入
    			this.lowerPrcFlag = true;
    			this.params.phmAgreDeviation.lowerCheckPrcProp ="";
    		}else{
    			this.upperPrcFlag = false;
    			this.params.phmAgreDeviation.lowerCheckPrc ="";
    		}
    	},
    	
		setGridData:function(list){
			var pqMap = {};
			var prcMap = {};
			for(var i in list){
				var obj = list[i];
				var generatorId = obj.generatorId;
				if(typeof(pqMap[generatorId])==="undefined"){
					pqMap[generatorId] = {generatorId:generatorId,geneName:obj.geneName,type:"合同电量分月",distributionMode: obj.distributionMode,
							totaPq:obj.totaPq,saveDecimal: obj.saveDecimal};
					prcMap[generatorId] = {type:"合同电价"};
				}
				pqMap[generatorId][obj.ym] = obj.generatorPq;
				prcMap[generatorId][obj.ym] = obj.generatorPrc;
			}
			var list = [];
			for(var i in pqMap){
				list.push(pqMap[i]);
				list.push(prcMap[i]);
			}
			this.initGrid(list);
		},
		
	}
});
</script>
</body>
</html>