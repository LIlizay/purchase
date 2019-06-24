<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
	<title>用户设备管理-列表</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body id="consVue" v-cloak class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电用户信息管理">
			<mk-search-visible> 
				<!-- 用户名称 -->
				<su-form-group :label-name='formFields.consName.label'  class="form-control-row " 
							:class="{'display-none':!formFields.consName.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.consName" type="text"></su-textbox>
                </su-form-group>
                <!-- 用电类别 -->
				<su-form-group :label-name='formFields.elecTypeName.label'  class="form-control-row " 
							:class="{'display-none':!formFields.elecTypeName.searchHidden}" col="4" label-align="right">
                    <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.elecTypeCode"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" name="elecTypeCode"></su-select>
                </su-form-group>
               
            	<!-- 电压等级 -->
            	<su-form-group :label-name='formFields.voltCodeName.label'  class="form-control-row " col="4" label-align="right">
					<su-select placeholder="--请选择--" label-name="name" multiple="true" :data-source.sync="voltCodeData" :select-value.sync="params.voltCode" name="voltCode"></su-select>
                </su-form-group>
            	
				<!-- 营销户号 -->
				<su-form-group :label-name='formFields.marketConsNo.label'  class="form-control-row " 
							:class="{'display-none':!formFields.marketConsNo.searchHidden}" col="4" label-align="right">
                    <su-textbox :data.sync="params.marketConsNo" type="text"></su-textbox>
                </su-form-group>
				<!-- 用户类型 -->
				<su-form-group :label-name="formFields.consType.label" class="form-control-row" col="4" label-align="right">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.consType"
						url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_consType"></su-select>
				</su-form-group>
			</mk-search-visible>
		       
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
				<su-button s-type="default"  v-on:click="reset" class="btn-width-small">重置</su-button>
			</div>
		</mk-search-panel>
		 
		<mk-panel title="用电设备列表" line="true" v-cloak slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add" resourceCode="" >新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout" resourceCode="" >删除</su-button>
		    </div>
		
		    <div class="col-xs-12 height-fill">
	       	 	<table id="consGrid" style="height:100%"></table>
	        </div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var consVue = new Vue({
			el: '#consVue',
			data: {
				orgInfo : null,
				voltCodeData:[],
				formFields:{},
			    params:{
			    	consName: null,
			    	elecTypeCode: null,
			    	voltCode: null,
			    	marketConsNo: null,
			    	consId: null,
			    	consType:null
			    }
			},
			ready:function(){
				//列表数据加载
				ComponentUtil.buildTreeGrid("selling.device.relation",$("#consGrid"),{ 
					url: basePath + 'cloud-purchase-web/scDeviceRelationController/page',
				    width:"100%",
				    method: 'post',
				    queryParams:{},
				    idField:"treeId", 
				    treeField:"consName", 
			        striped:true,
				    rownumbers: true,
				    pagination: true,
				    singleSelect:false,
					checkOnSelect: true,
				    nowrap: false,
				    fitColumns:true,
				    pageSize: 10,
				    pageList: [10, 20, 50, 100, 150, 200],
				    rowStyler:function(idx,row){
				        return "height:35px;font-size:12px;";
				    },
				    onLoadSuccess : function(row, data){
				    	var pageNumber = $("#consGrid").datagrid("getPager").data("pagination").options.pageNumber;
				    	var pageSize = $("#consGrid").datagrid("getPager").data("pagination").options.pageSize;
				    	var trow = $("#consGrid").parent().find(".datagrid-view1 .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
				    	for(var i in trow){
				    		trow[i].textContent = (parseInt(pageNumber) - 1) * parseInt(pageSize) + parseInt(i) + 1;
				    	}
				    	$("#consGrid").datagrid("clearChecked");
				    },
			    }); 
				//查询字段名称加载
				ComponentUtil.getFormFields("selling.device.relation",this);
			},
			methods: {
				reset:function(){
					this.params = {consName:null,elecTypeCode:'',voltCode:'',marketConsNo:null};
				},
				getDataGrid:function(){
					$('#consGrid').treegrid('options').queryParams = this.params;  
				    $("#consGrid").treegrid('load'); 
				},
				add:function(){
					var selInfo = $('#consGrid').datagrid('getChecked');
					if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"提示",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"提示",body:"只能选择一条数据！"});
				    	return;
				    }
				    if(selInfo[0]._parentId != null && selInfo[0]._parentId != ""){
				    	MainFrameUtil.alert({title:"提示",body:"此数据不可新增！"});
				    	return;
				    }
					//添加页面
			  		MainFrameUtil.openDialog({
			  			id:"cons-add",
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scdevicerelation/add',
						title:'用户档案新增',
						params:{'row': selInfo},
						iframe:true,
						modal:true,
						width:"80%",
						maximizable:true,
						height:620,
						onClose:function(){
							$("#consGrid").treegrid('reload');
						}
					});
				},
				onlogout:function(){//注销（删除）
					var me = this;
	            	var selInfo = $('#consGrid').treegrid('getChecked');
					if(selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"是否确认删除，该操作不可恢复！",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	debugger
				            	var parentList = new Array();
				            	var childtenList = new Array();
				            	//判断 删除的是 根节点/子节点
				            	for(var row in selInfo){ 
				            		if(selInfo[row]._parentId != null){ //子节点
				            			childtenList.push({id:selInfo[row].id});
					            	}else{ //根
										var parent = $('#consGrid').treegrid('getChildren');
// 										childtenList.push($('#consGrid').getChildren(row.target));  //获取选中节点的子节点，并插入数组
 					            		parentList.push({
 					            						consId: selInfo[row].consId,
 					            						marketConsNo: selInfo[row].marketConsNo
 					            						});
 					            		}
				            	}
				            	var map = {};
				            	if(parentList != null && parentList.length > 0){
				            		map["parentList"]=parentList;
				            	}
				            	if(childtenList != null && childtenList.length > 0){
				            		map["childtenList"]=childtenList;
				            	}
				            	$.ajax({
			                		url:basePath + 'cloud-purchase-web/scDeviceRelationController/deleteTree',
			                		type:"delete",
			                		data:$.toJSON(map),
									contentType : 'application/json;charset=UTF-8',
			                		success:function(data){
			                			if(data.flag){
			    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
			    	      					me.getDataGrid();
			    	      				}else{
			    	      					MainFrameUtil.alert({title:"错误",body:data.msg});
			    	      				} 
			                		},
			                		error:function(data){
			                			MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
			                		}
			                	})
				            }
				        }
		    		});
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
			watch : {
				//监听用电类别,改变电压等级
				"params.elecTypeCode":function(value){
					var me = this;
					if(value == "100" || value == "400"){
						me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode&pCode.content5=" + value;
						me.getVoltCode(me.voltCodeUrl);
						me.voltCode = "";
					}else{
						me.voltCodeUrl = "${Config.baseURL}globalCache/queryCodeByParam?domain=selling&pCode.codeType=sell_psVoltCode";
						me.getVoltCode(me.voltCodeUrl);
						me.voltCode = "";
					}
				}
				
			}
			
		}); 
	</script>
</body>
</html>