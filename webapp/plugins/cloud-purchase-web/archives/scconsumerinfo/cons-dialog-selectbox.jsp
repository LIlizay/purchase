<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>选择用户（售电合同、合同辅助设计-合同方案、检测平台）</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel title="" slot="top" deployable="false" title="用电用户信息管理">
			<mk-search-visible> 
				<!-- 用户名称 -->
				<su-form-group :label-name='formFields.consName.label'  class="form-control-row " 
							:class="{'display-none':!formFields.consName.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.consName" type="text"  placeholder="" ></su-textbox>
                </su-form-group>
                <!-- 所属供电公司  -->
<!-- 				<su-form-group :label-name='formFields.orgId.label'  class="form-control-row " label-width="120px" -->
<!-- 							:class="{'display-none':!formFields.orgId.searchHidden}" col="4" label-align="right"> -->
<!-- 					<su-select label-name="name" :select-value.sync="params.orgId" :high="150" -->
<%-- 								url="${Config.baseURL}cloud-purchase-web/scOrgInfoController/getSelectOrgList" value-name="id"></su-select> --%>
<!--                 </su-form-group> -->
            	<!-- 用电类别 -->
				<su-form-group :label-name='formFields.elecTypeCode.label'  class="form-control-row " 
							:class="{'display-none':!formFields.elecTypeCode.searchHidden}" col="4" label-align="right">
                    <su-select label-name="name" :select-value.sync="params.elecTypeCode" :high="150"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" ></su-select>
                </su-form-group>
            	<!-- 电压等级 -->
				<su-form-group :label-name='formFields.voltCode.label'  class="form-control-row " 
							:class="{'display-none':!formFields.voltCode.searchHidden}" col="4" label-align="right">
                    <su-select label-name="name" :select-value.sync="params.voltCode" :high="150"
								:data-source.sync="voltCodeData" ></su-select>
                </su-form-group>
			</mk-search-visible>
		       
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="用电用户列表" line="true" slot="bottom" height="100%">
		    <div id="consGrid" class="col-xs-12" ></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consVue = new Vue({
			el: '#consVue',
			data: {
                voltCodeData:[],
                voltCodeUrl : "", // 电压等级url
				formFields:{},
			    params:{consName:null,elecTypeCode:null,voltCode:null,orgId:null,consType:null}
			},
			ready:function(){
				var consType = '${param.consType}';
				if(consType == '01' || consType == '02'){
					this.params.consType = consType;
				}
				var smppaYm = '${param.smppaYm}';
				if(smppaYm != null && smppaYm != '' && smppaYm != 'null'){
					this.params.smppaYm = smppaYm;
				}
				//列表数据加载
				ComponentUtil.buildGrid("purchase.common.consDialog",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/sConsDialogController/getConsList',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    singleSelect:true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    fit:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
			    }); 
						
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.common.consDialog",this);
				MainFrameUtil.setDialogButtons(this.getButtons(),"mk-power-up-dialog-id");
			},
			methods: {
				reset:function(){
					this.params.consName = null;
					this.params.elecTypeCode = '';
					this.params.voltCode = '';
					this.params.orgId = '';
				},
				getDataGrid:function(){
					$('#consGrid').datagrid('options').queryParams = this.params;  
				    $("#consGrid").datagrid('load'); 
				},
		        getButtons:function(){
	                var buttons=[{
	                    text:"确定",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	var row = $("#consGrid").datagrid('getSelected');
	                    	if(row == null){
	                    		MainFrameUtil.alert({title:"提示",body:"请选择用户！"});
	                    		return false;
	                    	}else{
	                    		params.okHandler(row);
		                    	MainFrameUtil.closeDialog("mk-power-up-dialog-id");
	                    	}
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                        MainFrameUtil.closeDialog("mk-power-up-dialog-id");
	                    }
	                }];
	                return buttons;
	            },
                getVoltCode:function(url){
                    var me = this;
                    $.ajax({
                           url:url,
                           type:"get",
                           success:function(data){
                                 me.voltCodeData = data;
                           }
                    });
             	},
	            
			},
			watch:{
			  "params.elecTypeCode":function(value){
                      var me = this;
                      if(value == "100" || value == "400"){
                             me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&pCode.content5=" + value;
                             me.getVoltCode(me.voltCodeUrl);
                             me.params.voltCode = "";
                      }else{
                             me.voltCodeUrl = "";
                             me.voltCodeData = [];
                             me.params.voltCode = "";
                      }
                },
			}
			
		}); 
	</script>
</body>
</html>
