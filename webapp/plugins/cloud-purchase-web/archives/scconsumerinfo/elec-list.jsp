<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
	<head>
		<title>历史用电信息</title>
	</head>
<body>		
<div id="elecVue" v-cloak class="height-fill">
	<mk-top-bottom height="525px" top_height="auto">
		<mk-search-panel slot="top" deployable="false" title="用电用户信息管理">
			<mk-search-visible> 
				<!-- 年份 -->
				<su-form-group label-name='年份'  class="form-control-row "  col="4" label-align="right">
					<su-datepicker format="YYYY"  :data.sync="params.year" ></su-datepicker>
				</su-form-group>
			</mk-search-visible>
			<div slot="buttons" class="pull-right dashed_div col-md-12 mt_10" style="text-align:right;">
				<su-button s-type="primary"  v-on:click="getDataGrid" class="btn-width-small">查询</su-button>
			</div>
		</mk-search-panel>
		<mk-panel title="历史用电信息" line="true" slot="bottom" height="100%">
			<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="onlogout">删除</su-button>
		        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="download" v-on:click="downloadTem">模版下载</su-button>
			    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-in" v-on:click="importExcel">导入</su-button>
			    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button>
		    </div>
		    <div id="elecGrid" class="col-xs-12"  style="width:100%;height:100%"></div>
		</mk-panel>
	</mk-top-bottom>
	<script type="text/javascript">
		var downLoadExcelUrl = basePath+'plugins/cloud-purchase-web/archives/elec/历史用电信息导入模版.xlsx';//导入的excel模板
		var basePath = '${Config.baseURL}';
		//对应 js
		var elecVue = new Vue({
			el: '#elecVue',
			data:{
				params:{
					year:'',
					consId:'',
					years:[]
				}
			},
			ready:function(){
				if(MainFrameUtil.getParams("cons-add")){
					this.params.consId = MainFrameUtil.getParams("cons-add").consId;
				}else if(MainFrameUtil.getParams("cons-edit")){
					this.params.consId = MainFrameUtil.getParams("cons-edit").consId;
				}
				//列表数据加载
				this.initGrid();
			},
			methods: {
				initGrid:function(){
					$("#elecGrid").datagrid({
						url:"${Config.baseURL}cloud-purchase-web/scConsElectricityController/getYearList",
						method : 'post',
						queryParams:this.params,
						contentType : 'application/json;charset=UTF-8',
						width:"100%",
						striped:true,
						fitColumns:true,
						singleSelect:false,
						rownumbers:true,
						columns:[[
								{field:'ck',checkbox:true},
 								{field:'year',title:'年份',width:'5%',align:'center'},
 								{field:'yearPq',title:'年度总用电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearPeakPq',title:'峰时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearPlainPq',title:'平时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearValleyPq',title:'谷时电量<br>（兆瓦时）',width:'12%',align:'center'},
								{field:'yearOverPeakPq',title:'尖峰电量<br>（兆瓦时）',width:'11%',align:'center'},
								{field:'yearDoublePq',title:'双蓄电量<br>（兆瓦时）',width:'11%',align:'center'},
								{field:'yearAmt',title:'年度总电费<br>（元）',width:"12%",align:'center'},
								{field:'avgPrc',title:'平均电度电价<br>（元/兆瓦时）',width:"13%",align:'center',
									formatter: function(value,row,index){
										if(row.yearAmt != 0 && row.yearAmt != '' && row.yearPq != 0 && row.yearPq != '' ){
											return (row.yearAmt / row.yearPq).toFixed(2);
										}else{
											return value;
										}
									}
								}
								
						]]
					});
				},
				getDataGrid:function(){
				    $("#elecGrid").datagrid('reload'); 
				},
				add:function(){
					var date = new Date();
					var addyear = date.getFullYear();
					//添加页面
			  		MainFrameUtil.openDialog({
			  			params:{consId:this.params.consId,flag:false,year:addyear+""}, //consId:elecVue.consId
			  			id:"elec-add",
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elec-add',
						title:"历史用电信息新增",
						iframe:true,
						modal:true,
						width:"80%",
						height:610,
						onClose:function(){
							$("#elecGrid").datagrid('reload');
						}
					});
				},
				edit:function(){
					//更新页面
				    var selInfo = $('#elecGrid').datagrid('getChecked');
				    if(selInfo==null||selInfo.length==0){
				    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
				    }else if(selInfo.length>1){
				    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
				    	return;
				    }
			  		MainFrameUtil.openDialog({
			  			id:"elec-add",
			  			params:{consId:this.params.consId,year:selInfo[0].year,flag:true},
						href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elec-add',
						iframe:true,
						modal:true,
						width:"80%",
						height:610,
						onClose:function(){
							$("#elecGrid").datagrid('reload');
						}
					});
				},
				onlogout:function(){//注销（删除）
					var me = this;
	            	var selInfo = $('#elecGrid').datagrid('getChecked');
					if(selInfo.length<1){
						MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				    	return;
					}
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"该操作不能恢复，确定要注销选中的记录吗？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	var delyears = new Array();
				            	for(var obj in selInfo){
				            		delyears.push(selInfo[obj].year);
				            	}
								me.params.years = delyears;
				            	$.ajax({
			                		url:basePath + 'cloud-purchase-web/scConsElectricityController',
			                		type:"delete",
			                		data:$.toJSON(me.params),
									contentType : 'application/json;charset=UTF-8',
			                		success:function(data){
			                			if(data.flag){
			    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
			    	      					$("#elecGrid").datagrid('reload'); 
			    	      				}else{
			    	      					MainFrameUtil.alert({title:"错误",body:"删除失败！"});
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
				//下载模板
				downloadTem:function(){
		        	location.href = downLoadExcelUrl;
				}, 
				//Excel导入
				importExcel:function(){
					var me = this;
					 MainFrameUtil.openDialog({
		                id:'importExcelDialog',
		                href: '${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/importExcel',
		                modal:true,
		                iframe:true,
		                title:'历史用电量导入',
		                width:"35%",
		                height:500,
		                onClose:function(){
		                	me.getDataGrid();
		                }
		            })
				},
				//导出
				download:function(){
					var me = this;
					var selInfo = $('#elecGrid').datagrid('getChecked');
					MainFrameUtil.confirm({
				        title:"确认",
				        body:"是否导出？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				    			if(selInfo.length > 0){ //选择行
				    				var childIds = "";
				    				for(var i = 0 ; i < selInfo.length ; i++){
										childIds += me.params.consId + selInfo[i].year+ ",";
									}
				    				var str = "" + "*" + childIds.substr(0,childIds.length-1);
				    				var url = basePath + "scConsElectricityController/exportExcel?ids=" + str  + '&consName=' + ''
				    						+ "&voltCode=" + '' + "&elecTypeCode="+ '' + '&year=' + '' + '&consId=' + '';
				    			}else{//无选择
					    			var url = basePath + "scConsElectricityController/exportExcel?ids=" + ''  + '&consName=' + '' 
					    					+ "&voltCode=" + '' + "&elecTypeCode="+ '' + '&year=' + me.params.year + '&consId=' + me.params.consId;
				    			}
				    			// url有一些无用参数，因为用户档案-历史用电信息 导出需要的参数  共用一个方法 
				                location.href = url;
				            }
				        }
		    		});
				},
				
				
				
			}
		}); 
	</script>
</div>
</body>
</html>
