<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<meta charset="UTF-8">
  	<title>合同管理-购电合同管理编辑</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
	<%@include file="/plugins/business-core/static/headers/upload.jsp"%>
</head>
<body>
<div id="ppaAdd">
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="电厂信息" line="true">
			<mk-form-panel  height="95"  label_width="160px">
				<mk-form-row>
					<!-- 电厂名称-->
		            <mk-form-col   :label="formFields.elecName.label" :class="{'display-none':!formFields.elecName.formHidden}" required_icon="true">
		            	<mk-trigger-text  :data.sync="elecName" editable="false" @mk-trigger="selectElec" ></mk-trigger-text>
<%-- 		                <su-select url="${Config.baseURL}cloud-purchase-web/phmPpaController/getPhcElecInfoList" @su-change="elecIdChange" --%>
<!-- 		                :select-value.sync="params.phmPpa.elecId" name="elecId"  label-name="elecName" value-name="id"></su-select> -->
		            </mk-form-col>
		             <!-- 发电类别 -->
		             <mk-form-col :label='formFields.elecTypeCodeName.label' :class="{'display-none':!formFields.elecType.formHidden}" required_icon="true">
		                <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecType"
						:select-value.sync="elecTypeCode" label-name="name" name="elecTypeCode" disabled></su-select>
		            </mk-form-col>
		             <!-- 电力业务许可证编号（发电类）  -->
		            <mk-form-col :label="formFields.licenseNo.label" :class="{'display-none':!formFields.licenseNo.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.licenseNo" name="licenseNo"></su-textbox>
		            </mk-form-col>
			</mk-form-panel>
		</mk-panel>
		<mk-panel  title="合同基本信息" line="true">
			<mk-form-panel height="325"  label_width="160px">
			<mk-form-row>
					<!-- 合同编号 -->
		            <mk-form-col   :label="formFields.agreNo.label":class="{'display-none':!formFields.agreNo.formHidden}" required_icon="false">
		                <su-textbox name="agreNo" :data.sync="params.phmPpa.agreNo"></su-textbox>
		            </mk-form-col>
		            <!-- 交易中心合同序列号 -->
		            <mk-form-col   :label="formFields.serialNo.label":class="{'display-none':!formFields.serialNo.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.serialNo"></su-textbox>
		            </mk-form-col>
					<!-- 合同名称 -->
		            <mk-form-col :label="formFields.agreName.label" required_icon="true" :class="{'display-none':!formFields.agreName.formHidden}">
		                <su-textbox :data.sync="params.phmPpa.agreName" name="agreName" required></su-textbox>
		            </mk-form-col>
			</mk-form-row>
				<mk-form-row>
					<!-- 合同类型-->
			    	 <mk-form-col :label="formFields.agreType.label" :class="{'display-none':!formFields.agreType.formHidden}">
		            	<su-select url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_agreType"
		                :select-value.sync="params.phmPpa.agreType" name="agreType"  label-name="name"></su-select>
		            </mk-form-col>
					
		            <!-- 合同电量  -->
		            <mk-form-col :label="formFields.agrePqForm.label" :class="{'display-none':!formFields.agrePq.formHidden}" required_icon="true">
		                <su-textbox :data.sync="params.phmPpa.agrePq" name="agrePq" type="number" :addon="{'show':true,'rightcontent':'兆瓦时'}"></su-textbox>
		            </mk-form-col>
		            <!--是否含税  -->
		           	<mk-form-col   :label="formFields.taxFlag.label">
		    			<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_flag" :select-value.sync="params.phmPpa.taxFlag" name="taxFlag"  label-name="name"></su-select>
					</mk-form-col>
				</mk-form-row>
				<mk-form-row>
		             <!--合同开始日期  -->
		           	 <mk-form-col :label="formFields.bgnDate.label":class="{'display-none':!formFields.bgnDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.bgnDate" name="bgnDate"></su-datepicker>
			            </mk-form-col>
		             <!--合同结束日期  -->
		           	 <mk-form-col :label="formFields.endDate.label":class="{'display-none':!formFields.endDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.endDate" name="endDate"></su-datepicker>
			            </mk-form-col>
			            <!--合同签订日期  -->
		           	 <mk-form-col :label="formFields.signDate.label":class="{'display-none':!formFields.signDate.formHidden}" required_icon="true">
			                <su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.signDate" name="signDate"></su-datepicker>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row>
		            
		             <!--售电公司签订人  -->
		           	 <mk-form-col :label="formFields.partyB.label" :class="{'display-none':!formFields.partyB.formHidden}" required_icon="true">
	           		 	<su-textbox :data.sync="params.phmPpa.partyB" name="partyB" ></su-textbox>
			         </mk-form-col>
			         <!--电厂签订人 -->
		           	 <mk-form-col :label="formFields.partyA.label" :class="{'display-none':!formFields.partyA.formHidden}" required_icon="true">
			            <su-textbox :data.sync="params.phmPpa.partyA" name="partyA"></su-textbox>
			         </mk-form-col>
			          <!--备案时间  -->
		           	 <mk-form-col :label="formFields.recordDate.label":class="{'display-none':!formFields.recordDate.formHidden}" >
			         	<su-datepicker format="YYYY-MM-DD" :data.sync="params.phmPpa.recordDate" ></su-datepicker>
			          </mk-form-col>
					 </mk-form-row>
					 <mk-form-row>
		             <!--签订地点  -->
		           	 <mk-form-col :label="formFields.signAddr.label" colspan="3" :class="{'display-none':!formFields.signAddr.formHidden}">
			           		<su-textbox :data.sync="params.phmPpa.signAddr" ></su-textbox>
			            </mk-form-col>
					</mk-form-row>
					<mk-form-row height="auto">
						 <!--附件  -->
		           	 	<mk-form-col :label="formFields.fileId.label" :class="{'display-none':!formFields.fileId.formHidden}" required_icon="true" colspan="3">
							<mk-multifile-upload :params="uploadParams1" :files.sync="fileInfos"   v-ref:uploadfile  show_upload="false"   @upload-error="uploadError" 
		                     @upload-success="uploadSuccess"  url='${Config.getConfig("file.service.url")}' required="true" maxcount=20>
		                  </mk-multifile-upload>
						</mk-form-col>
					</mk-form-row>
			</mk-form-panel>
		</mk-panel>
		<mk-panel title="发电单元分月交易电量电价（兆瓦时、元/兆瓦时）" height="300px" line="true">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
            	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
            	<su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
        	</div>
			<div id="pqGrid" style="height:100%"></div>
		</mk-panel>
<!-- 		<mk-panel  title="合同偏差处理"  line="true"> -->
<!-- 			<mk-form-panel label_width="160px"> -->
<!-- 				<mk-form-row> -->
<!-- 					售电公司正偏差 -->
<!-- 		           	<mk-form-col :label="formFields.upperCheckFlag.label" :class="{'display-none':!formFields.upperCheckFlag.formHidden}" required_icon="true"> -->
<%-- 		    			<su-select label-name="name" url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag"  --%>
<!-- 		    			:select-value.sync="params.phmAgreDeviation.upperCheckFlag" name="upperCheckFlag" @su-change="upperCheckFlagChange"></su-select> -->
<!-- 					</mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row :class="{'display-none':!upperCheckFlag}"> -->
<!-- 					允许正偏差范围  -->
<!-- 			        <mk-form-col :label="formFields.upperPqProp.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.upperPqProp.formHidden}" label-align="right" > -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.upperPqProp" :addon="{'show':true,'rightcontent':'%'}" name="upperPqProp"></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 					惩罚电价基准  -->
<!-- 			        <mk-form-col :label="formFields.upperType.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.upperType.formHidden}" label-align="right">							 -->
<!-- 		                 <su-select label-name="name" @su-change="upperTypeChange" :select-value.sync="params.phmAgreDeviation.upperCheckPrcType" name="upperCheckPrcType" -->
<%-- 		                 url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_punishPriceBaseCode&valueIn='01','03'"></su-select> --%>
<!-- 			        </mk-form-col> -->
<!-- 					 惩罚比例值  -->
<!-- 			        <mk-form-col :label="formFields.upperCheckPrcProp.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="!upperPrcFlag" :class="{'display-none':!formFields.upperCheckPrcProp.formHidden}" label-align="right"> -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.upperCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}" name="upperCheckPrcProp"></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 			        正偏差惩罚协议价 -->
<!-- 			            <mk-form-col :label="formFields.upperCheckPrc.label" label-width="200px" required_icon="true" -->
<!-- 									v-if="upperPrcFlag" :class="{'display-none':!formFields.upperCheckPrc.formHidden}" label-align="right"> -->
<!-- 			                <su-textbox :data.sync="params.phmAgreDeviation.upperCheckPrc" name="upperCheckPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox> -->
<!-- 			            </mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row> -->
<!-- 				售电公司负偏差  -->
<!-- 		           	<mk-form-col :label="formFields.lowerCheckFlag.label" required_icon="true"> -->
<%-- 		    			<su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_punishFlag" :select-value.sync="params.phmAgreDeviation.lowerCheckFlag" --%>
<!-- 		    			 name="lowerCheckFlag"  label-name="name" @su-change="lowerCheckFlagChange"></su-select> -->
<!-- 					</mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 				<mk-form-row :class="{'display-none':!lowerCheckFlag}"> -->
<!-- 					允许负偏差范围  -->
<!-- 			        <mk-form-col :label="formFields.lowerPqProp.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.lowerPqProp.formHidden}" label-align="right" > -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.lowerPqProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerPqProp"></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 					惩罚电价基准  -->
<!-- 			        <mk-form-col :label="formFields.lowerType.label" label-width="200px" required_icon="true" -->
<!-- 								:class="{'display-none':!formFields.lowerType.formHidden}" label-align="right">							 -->
<!-- 		                 <su-select label-name="name" @su-change="lowerTypeChange" :select-value.sync="params.phmAgreDeviation.lowerCheckPrcType" name="lowerCheckPrcType" -->
<%-- 		                 url="${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=purchase_punishPriceBaseCode&valueIn='01','02'"></su-select> --%>
<!-- 			        </mk-form-col> -->
<!-- 					 惩罚比例值  -->
<!-- 			        <mk-form-col :label="formFields.lowerCheckPrcProp.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="!lowerPrcFlag" :class="{'display-none':!formFields.lowerCheckPrcProp.formHidden}" label-align="right"> -->
<!-- 			            <su-textbox :data.sync="params.phmAgreDeviation.lowerCheckPrcProp" :addon="{'show':true,'rightcontent':'%'}" name="lowerCheckPrcProp"></su-textbox> -->
<!-- 			        </mk-form-col> -->
<!-- 			         负偏差惩罚协议价 -->
<!-- 			            <mk-form-col :label="formFields.lowerCheckPrc.label" label-width="200px" required_icon="true" -->
<!-- 								v-if="lowerPrcFlag" :class="{'display-none':!formFields.lowerCheckPrc.formHidden}" label-align="right"> -->
<!-- 			                <su-textbox :data.sync="params.phmAgreDeviation.lowerCheckPrc" name="lowerCheckPrc" :addon="{'show':true,'rightcontent':'元/兆瓦时'}"></su-textbox> -->
<!-- 			            </mk-form-col> -->
<!-- 				</mk-form-row> -->
<!-- 			</mk-form-panel> -->
<!-- 		</mk-panel> -->
		<mk-panel title="违约信息" line="true">
			<mk-form-panel  label_width="160px">
				<mk-form-row height="80px">
		            <!--备注  -->
		           	<mk-form-col :label="formFields.violExplain.label" colspan="3">
		    			<su-textbox type="textarea" rows="3" rols="10" :data.sync="params.phmPpa.violExplain"></su-textbox>
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
        elecName:"",  //电厂名称
        elecTypeCode:"",	//发电类别
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
        		bgnDate:"",//合同开始日期
        		endDate:"",//合同结束日期
        		taxFlag:null,//是否含税
        		signAddr:"",//签订地点
        		violExplain:"",//备注
        		elecId:"",//电厂名称
        		partyA:"",//售方签订人
        		agrePq:"",//合同电量
        		unTreatmentMethod:"",//当用电方未完成合同电量时
        		partyB:"",//购方签订人
        		recordDate:"",//备案时间
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
        },
        //验证规则
		dataOption: {
			rules: {
        		treatmentMethod: "required",
        		signDate: "required",
        		endDate: "required",
        		elecId: "required",
        		partyA: "required",
        		agrePq: "required",
        		agreStatus: "required",
        		unTreatmentMethod: "required",
        		partyB: "required",
        		bgnDate: "required",
        		agreName: "required",
        		distributionMode: "required",
        		saveDecimal: "required",
        		serialNo: "required",
        		upperCheckFlag: "required",
        		upperPqProp: "required",
        		upperCheckPrcType: "required",
        		upperCheckPrcProp: "required",
        		upperCheckPrc: "required",
        		lowerCheckFlag: "required",
        		lowerPqProp: "required",
        		lowerCheckPrcType: "required",
        		lowerCheckPrcProp: "required",
        		lowerCheckPrc: "required",
			}
		},
	    valid: false
    },
    ready:function(){
    	//初始化表单
		ComponentUtil.getFormFields("purchase.agreement.phmppa",this);
		this.params.phmPpa.id = MainFrameUtil.getParams("ppaEdit").id;
  	  	MainFrameUtil.setDialogButtons([{text:"保存",type:"primary",handler:this.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("ppaEdit")}}],"ppaEdit");
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
 	  			 	
 	  			 	//合同电量分月总和  赋给合同电量
 	  			 	me.sumPqGridAsAgrePq();
 	  		   }
    		 });
    	},
    	
    	//合同电量分月总和  赋给合同电量
    	sumPqGridAsAgrePq:function(){
    		var me = this;
    		var rows = $("#pqGrid").datagrid("getRows");
			 	var totalPq = 0;
			 	var param = ["jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec"];
			 	for(var i=0; i<rows.length;i++){
			 		var row = rows[i];
			 		if((i+1)%2 !== 0){
	  			 		for(var j in param){
	  			 			var key = param[j];
	  			 			var value = row[key];
  			 			if(!isNaN(Number(value))){
  			 				totalPq += Number(value);
  			 			}
	  			 		}
			 		}
			 	}
			 totalPq = totalPq.toFixed(4);
				me.params.phmPpa.agrePq = totalPq;
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
    			this.lowerPrcFlag = false;
    			this.params.phmAgreDeviation.lowerCheckPrc ="";
    		}
    	},
    	
    	//添加发电单元分月交易电量
    	add:function(){
    		var me = this;
    		var elecId = me.params.phmPpa.elecId;
    		if(!elecId){
    			MainFrameUtil.alert({title:"提示",body:"请选择电厂！"});
				return;
    		}
    		if(me.params.phmPpa.bgnDate == null || me.params.phmPpa.bgnDate == "" 
				|| me.params.phmPpa.endDate == null || me.params.phmPpa.endDate == ""){
				MainFrameUtil.alert({title:"提示",body:"请先填写合同开始及结束时间！"});
				return;
			}
    		var bgnMonth = parseInt(me.params.phmPpa.bgnDate.split("-")[1]);
    		var endMonth = parseInt(me.params.phmPpa.endDate.split("-")[1]);
    		MainFrameUtil.openDialog({
		  			id:"generatorAdd",
		  			params:{"row":null,"elecId":elecId, bgnMonth: bgnMonth, endMonth:endMonth},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/generator-add',
					iframe:true,
					modal:true,
					width:"80%",
					height:480,
					onClose:function(newRow){
						if(newRow.row==null){
							return;
						}
						var params = newRow.row[0];
						//回调数据添加到表格中
						if(params.generatorId !== undefined && params.generatorId){
							var rows = $("#pqGrid").datagrid("getRows");
							for(var i=0;i<rows.length;i=i+2){
								var row = rows[i];
								if(row.generatorId == params.generatorId){
									MainFrameUtil.alert({title:"提示",body:"该发电单元已存在！"});
									return;
								}
							}
							$("#pqGrid").datagrid("insertRow",{index:0,row:newRow.row[0]});
							$("#pqGrid").datagrid("insertRow",{index:1,row:newRow.row[1]});
							$("#pqGrid").datagrid("mergeCells",{index:0,field:"geneName",rowspan:"2"});
							$("#pqGrid").datagrid("unselectAll");
						}
						me.sumPqGridAsAgrePq();
					}
				})
    	},
    	
    	//编辑
    	edit:function(){
    		var rows =$("#pqGrid").datagrid('getSelections');
    		var me = this;
    		if(rows == null || rows.length != 2){
    			MainFrameUtil.alert({title:"提示",body:"请选择一条修改的数据！"});
				return;
    		}
    		var elecId = this.params.phmPpa.elecId;
    		var obj1 =  JSON.parse(JSON.stringify(rows[0]));
    		var obj2 =  JSON.parse(JSON.stringify(rows[1]));
    		var newRows = [obj1,obj2];
    		var generatorId = rows[0].generatorId;
    		var bgnMonth = parseInt(me.params.phmPpa.bgnDate.split("-")[1]);
    		var endMonth = parseInt(me.params.phmPpa.endDate.split("-")[1]);
    		MainFrameUtil.openDialog({
	  			id:"generatorAdd",
	  			params:{row:newRows,elecId:elecId,endMonth:endMonth,bgnMonth:bgnMonth},
				href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/generator-add',
				iframe:true,
				modal:true,
				width:"80%",
				height:480,
				onClose:function(params){
					if(params.row==null){
						return;
					}
					if(params.row[0].generatorId != generatorId){
						var formRows = $("#pqGrid").datagrid('getRows');
						for(var i=0;i<formRows.length;i=i+2){
							if(formRows[i].generatorId == params.row[0].generatorId){
								MainFrameUtil.alert({title:"提示",body:"该发电单元已存在！"});
								return;
							}
						}
					}
					//回调数据添加到表格中
					var index = $("#pqGrid").datagrid("getRowIndex",rows[0]);
					$("#pqGrid").datagrid("updateRow",{index:index,row:params.row[0]});
					$("#pqGrid").datagrid("updateRow",{index:index+1,row:params.row[1]});
					$("#pqGrid").datagrid("mergeCells",{index:index,field:"geneName",rowspan:"2"});
					$("#pqGrid").datagrid("unselectAll");
		  			me.sumPqGridAsAgrePq();
				}
			})
    	},
    	
    	del:function(){
    		var rows =$("#pqGrid").datagrid('getSelections');
    		if(rows == null || rows.length != 2){
    			MainFrameUtil.alert({title:"提示",body:"请选择数据！"});
				return;
    		}
    		MainFrameUtil.confirm({
		        title:"确认",
		        body:"该操作不能恢复，确定要删除选中的记录吗？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	var index = $("#pqGrid").datagrid("getRowIndex",rows[0]);
		        		$("#pqGrid").datagrid("deleteRow",index);
		        		$("#pqGrid").datagrid("deleteRow",index);
		        		MainFrameUtil.alert({title:"提示",body:"删除成功！"});
		            }
		        }
    		})
    		
    	},
		uploadSuccess: function(data){//上传成功回调
			$.messager.progress('close');  //关闭进度条
            var that = this;
            //复制一份文件列表
            var fileList = $.parseJSON($.toJSON(that.fileInfos));
            //去除新增的文件信息，
            for(var i = fileList.length - 1; i > -1 ; i-- ){
                if(fileList[i].genNew){   //如果是新增的文件，则删除
                    fileList.splice(i,1);
                }
            }
            if(data!=null && typeof(data) != "string" && !data.failure){
                for(var i = 0; i < data.length; i++){
                    var result=data[i];
                    var fileId = result.id;    //获取文件的id
                    var fileName=result.fileName+result.extension;//获取文件名
                    fileList.push({id: fileId, name: fileName});
                }
            }
            that.fileInfos.splice(0,that.fileInfos.length);
            //更新组件绑定的文件列表变量，并生成数据库用的String数据（多个附件用“?”隔开，每个附件的名字和id用“:”隔开）
            var fileInfoStr = "";
            for(var i = 0; i < fileList.length; i++){
                that.fileInfos.push(fileList[i]);
                if(i == 0){
                    fileInfoStr += fileList[i].id + ":" + fileList[i].name;
                }else{
                    fileInfoStr += "?" +fileList[i].id + ":" + fileList[i].name;
                }
            }
	    	this.params.phmPpa.fileId = fileInfoStr;
    	  	this.submit();
		},
		uploadError:function(data){
			$.messager.progress('close');  //关闭进度条
            MainFrameUtil.alert({title:"警告",body:'上传失败'});
		},
		
		//处理添加电量
		getPqRows:function(){
			var me = this;
			var rows = $("#pqGrid").datagrid('getRows');
			var monthMap = {"jan":"01","feb":"02","mar":"03","apr":"04","may":"05","jun":"06","jul":"07","aug":"08","sep":"09","oct":"10","nov":"11","dec":"12"};
			var year = this.params.phmPpa.bgnDate.split("-")[0];
			var phmGeneratorMonthPqList = [];
			var phmGenePqDistList = [];
			for(var i=0;i<rows.length;i=i+2){
				var qpRow = rows[i];
				var priceRow = rows[i+1];
				for(var j in monthMap){
					var phmGeneratorMonthPq = {};
					phmGeneratorMonthPq.ym = year+monthMap[j];
					phmGeneratorMonthPq.generatorId = qpRow.generatorId;
					phmGeneratorMonthPq.generatorPq = qpRow[j];
					phmGeneratorMonthPq.generatorPrc = priceRow[j];
					phmGeneratorMonthPq.agreId = me.params.phmPpa.id;
					phmGeneratorMonthPqList.push(phmGeneratorMonthPq);
				}
				
				var phmGenePqDist={generatorId:qpRow.generatorId,totaPq:qpRow.totaPq,distributionMode:qpRow.distributionMode,agreId:me.params.phmPpa.id};
				if(qpRow.saveDecimal!=""){
					phmGenePqDist.saveDecimal = qpRow.saveDecimal;
				}
				phmGenePqDistList.push(phmGenePqDist);
			}
			this.params.phmGeneratorMonthPqList = phmGeneratorMonthPqList;
			this.params.phmGenePqDistList = phmGenePqDistList;
		},
		
		//保存
		submit:function(){
			var me = this;
			//获取发电单元分月交易电量
			//因为数据库是int类型
			if(me.params.phmPpa.taxFlag == ''){
				me.params.phmPpa.taxFlag = null;
			}
			this.getPqRows();
			var type = "post";
			if(me.params.phmPpa.id){
				type = "put";
			}
			$.ajax({
        		url:basePath + 'cloud-purchase-web/phmPpaController',
        		type:type,
        		data:$.toJSON(me.params),
				contentType : 'application/json;charset=UTF-8',
        		success:function(data){
        			if(data.flag){
        				if(data.msg == "在该有效期内已存在该电厂合同！"){
    						MainFrameUtil.alert({title:"提示",body:data.msg});
    						return;
    					}
        				if(type == "post"){
        					me.params.phmPpa.id = data.data.phmPpa.id;
        					me.params.phmPpa.agreNo = data.data.phmPpa.agreNo;
        				}
      					MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(type){MainFrameUtil.closeDialog("ppaEdit");}});
      				}else{
      					MainFrameUtil.alert({title:"错误",body:data.msg});
      				} 
        		},
        		error:function(data){
        			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
        		}
        	})
		},
		
		//改变电厂则清空发电单元信息
		elecIdChange:function(){
			if(this.loadFlag){
				$("#pqGrid").datagrid("loadData",[]);
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
		
        save : function() {
          	this.$refs.form1.valid();
        	if(this.valid===false){
        		MainFrameUtil.alert({title:'提示',body:'您有必填项未填写！'});
        		return false;
        	}
        	//验证合同时间
			if(this.params.phmPpa.endDate < this.params.phmPpa.bgnDate){
				MainFrameUtil.alert({title:"提示",body:"合同日期不正确！"});
				return;
			}
			//验证合同日期不能跨年
			if((this.params.phmPpa.bgnDate).substring(0,5) != (this.params.phmPpa.endDate).substring(0,5)){
				MainFrameUtil.alert({title:"提示",body:"合同开始日期与合同结束日期不允许跨年！"});
				return;
			}
			//校验是否有合同电价
        	var rows = $("#pqGrid").datagrid('getRows');
			if(rows.length > 0){
	        	//校验电价
	        	var totalPq = 0;
	        	for(var i=0;i<rows.length;i=i+2){
	        		console.log(rows[i].totaPq);
	        		totalPq += parseFloat(rows[i].totaPq);
	        	}
	        	if(totalPq != this.params.phmPpa.agrePq){
	        		MainFrameUtil.alert({title:"提示",body:"机组总电量于合同电量不相等！"});
					return;
	        	}
			}
        	 //验证是否选择文件
       	   	if(this.$refs.uploadfile.valid()){
       		   this.$refs.uploadfile.validAndUpload();
       	   	}	
		},
		
		selectElec:function(){
			var me = this;
 			MainFrameUtil.openDialog({
	  			id:'selectElec',
				href:'${Config.baseURL}view/cloud-purchase-web/agreement/phmppa/selectelec',
				iframe:true,
				modal:true,
				width:1000,
				height:520,
				buttons:[{
                    text:"保存",
                    type:"primary",
                    handler:function(btn,params){
                    	params.save(function(row){
                    		me.elecName = row.elecName;
                    		me.params.phmPpa.elecId = row.id;
                    		me.elecIdChange();
                    		MainFrameUtil.closeDialog('selectElec');
                    	});
                    }
                },{
                    text:"关闭",
                    handler:function(btn,params){
                    	MainFrameUtil.closeDialog('selectElec');
                    }
                }]
			});
		}
	},
	watch:{
		'params.phmPpa.bgnDate':function(newValue,oldValue){
			//合同的开始与结束日期不允许跨年
			if((this.params.phmPpa.bgnDate).substring(0,5) != (this.params.phmPpa.endDate).substring(0,5)){
				MainFrameUtil.alert({title:"提示",body:"合同开始日期与合同结束日期不允许跨年！"});
			}
			//更改合同开始日期，清空交易电量电价表
			if(oldValue.substring(0,7) != newValue.substring(0,7)){
				try{
					$("#pqGrid").datagrid('loadData',[]);
				}catch(e){
				}
			}
			
		},
		'params.phmPpa.endDate':function(newValue,oldValue){
			//合同的开始与结束日期不允许跨年
			if((this.params.phmPpa.bgnDate).substring(0,5) != (this.params.phmPpa.endDate).substring(0,5)){
				MainFrameUtil.alert({title:"提示",body:"合同开始日期与合同结束日期不允许跨年！"});
			}
			//更改合同结束日期，清空交易电量电价表
			if(oldValue.substring(0,7) != newValue.substring(0,7)){
				try{
					$("#pqGrid").datagrid('loadData',[]);
				}catch(e){
				}
			}
		}
	}
	
});
</script>
</body>
</html>