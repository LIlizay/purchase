<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>用户选择（公共）</title>
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
<!-- 					<mk-trigger-text  :data.sync="orgInfo.name" editable="false" @mk-trigger="selectOrg" ></mk-trigger-text> -->
<!-- 					<su-select label-name="name" :select-value.sync="params.orgId" :high="150" -->
<%-- 								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_orgCode"></su-select> --%>
<!--                 </su-form-group> -->
            	<!-- 用电类别 -->
				<su-form-group :label-name='formFields.elecTypeCode.label'  class="form-control-row " 
							:class="{'display-none':!formFields.elecTypeCode.searchHidden}" col="4" label-align="right">
                    <su-select label-name="name" :select-value.sync="params.elecTypeCode" :high="auto"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" ></su-select>
                </su-form-group>
            	<!-- 电压等级 -->
				<su-form-group :label-name='formFields.voltCode.label'  class="form-control-row " 
							:class="{'display-none':!formFields.voltCode.searchHidden}" col="4" label-align="right">
                    <su-select label-name="name" :select-value.sync="params.voltCode" :high="150" :data-source.sync="voltCodeData" name="voltCode"></su-select>
                </su-form-group>
                
                <!-- 省 -->
<!-- 				<su-form-group :label-name='formFields.provinceCode.label'  class="form-control-row " label-width="120px" -->
<!-- 							:class="{'display-none':!formFields.provinceCode.searchHidden}" col="4" label-align="right"> -->
<!-- 					<su-select label-name="name" :select-value.sync="params.provinceCode" :high="150"  -->
<%-- 								url="${Config.baseURL}globalCache/queryProvinceList" ></su-select> --%>
<!--                 </su-form-group> -->
                
                <!-- 市  -->
<!-- 				<su-form-group :label-name='formFields.cityCode.label'  class="form-control-row "   -->
<!-- 							:class="{'display-none':!formFields.cityCode.searchHidden}" col="4" label-align="right"> -->
<!--                     <su-select label-name="name" :select-value.sync="params.cityCode" :high="150"  -->
<!-- 								:data-source='cityCodeList' ></su-select> -->
<!--                 </su-form-group> -->
                
                <!-- 区县  -->
<!-- 				<su-form-group :label-name='formFields.countyCode.label'  class="form-control-row "  -->
<!-- 							:class="{'display-none':!formFields.countyCode.searchHidden}" col="4" label-align="right"> -->
<!--                     <su-select label-name="name" :select-value.sync="params.countyCode" :high="150" -->
<!-- 								:data-source='countyCodeList' ></su-select> -->
<!--                 </su-form-group> -->
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
				orgInfo : {},
				formFields:{},
				cityCodeList:[],
				countyCodeList:[],
			    params:{consName:null,elecTypeCode:null,voltCode:null,orgId:null,consType:null,provinceCode:null,cityCode:null,countyCode:null},
			    excludePathId: null		//数据中的consPath字段含有excludePathId的不在列表中显示
			},
			ready:function(){
				var that = this;
				var obj = MainFrameUtil.getParams("consDialog");
				var consType = obj.consType;
				this.excludePathId = obj.excludePathId;
				if(consType){
					this.params.consType = consType;
				}
				//turn为单选，false为多选
				var singleSelect = obj.singleSelect;
				if(typeof(singleSelect)!=="boolean"){
					singleSelect = true;
				}
				//列表数据加载
				ComponentUtil.buildGrid("purchase.common.consDialog2",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/sConsDialogController/getConsList',
				    width:"100%",
				    height:"100%",
				    method: 'post',
				    queryParams:this.params,
				    rownumbers: true,
				    pagination: true,
				    nowrap: false,
				    fitColumns:true,
				    singleSelect:singleSelect,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    loadFilter: function(data){
						if (data != null && data.rows != null && data.rows.length != 0 
								&& that.excludePathId != null &&that.excludePathId.length != 0){
							var rowsNew = [];
							for(var i in data.rows){
								if(data.rows[i].id != that.excludePathId){
									if(data.rows[i].consPath == null || (data.rows[i].consPath != null && data.rows[i].consPath.indexOf(that.excludePathId) == -1)){
										rowsNew.push(data.rows[i]);
									}
								}
							}
							data.rows = rowsNew;
						}
						return data;
					}
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("purchase.common.consDialog2",this);
				MainFrameUtil.setDialogButtons(this.getButtons(),"consDialog");
			},
			methods: {
				selectOrg:function(){
		 			var me = this;
		 			MainFrameUtil.openDialog({
			  			id:'mk-power-up-dialog-id',
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scorginfo/org-dialog',
						iframe:true,
						modal:true,
						width:800,
						height:520,
						onClose:function(params){
							if(typeof(params)==="object"){
								me.orgInfo = params;
								me.params.orgId = params.id;
							}
						}
					});
		 		},
				reset:function(){
					this.params.orgId = "";
					this.orgInfo.name = "";
					this.params.consName = "";
					this.params.elecTypeCode = "";
					this.params.voltCode = "";
					this.params.elecTypeCode = "";
					this.params.voltCode = "";
					this.params.consType = "";
					this.params.provinceCode = "";
					this.params.cityCode = "";
					this.params.countyCode = "";
					
				},
				getDataGrid:function(){
					$('#consGrid').datagrid('options').queryParams = this.params;  
				    $("#consGrid").datagrid('load'); 
				},
				getCodeList:function(flag,code){
					var me =this;
                    var url = '';
                    if(flag){
                        url = "/globalCache/queryCityByParentCode/";
                        this.params.cityCode = "";
                    }else{
                        url = "/globalCache/queryCountyByParentCode/";
                        this.params.countyCode = "";
                    }
                    $.ajax({
                        url : basePath+url+code,
	                    type : 'get',
	                    success : function(data) {
	                    	if(flag){     //点击了省的下拉
	                    		me.cityCodeList = data;
	                    		if(data === null || data.length == 0){
	                    			me.countyCodeList = [];
	                    		}    
	                    	}else{        //点击了市的下拉
	                    		me.countyCodeList = data;
	                    	}
	                    }
	                });
				},
		        getButtons:function(){
	                var buttons=[{
	                    text:"确定",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	var rows = $("#consGrid").datagrid('getChecked');
	                    	if(rows.length == 0){
	                    		MainFrameUtil.alert({title:"提示",body:"请选择用户！"});
	                    		return false;
	                    	}else{
	                    		MainFrameUtil.setParams(rows,"consDialog");
		                    	MainFrameUtil.closeDialog("consDialog");
	                    	}
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                        MainFrameUtil.closeDialog("consDialog");
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
                                 console.log("data:",data);
                                 me.voltCodeData = data;
                           }
                    });
             }
			},
			watch:{
				'params.elecTypeCode':function(value){
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
				'params.provinceCode':function(value){
					if(value){
						this.getCodeList(true,value);
					}else{
						this.cityCodeList = [];
					}
				},
				'params.cityCode':function(value){
					if(value){
						this.getCodeList(false,value);
					}else{
						this.countyCodeList = [];
					}
				}
			}
		}); 
	</script>
</body>
</html>