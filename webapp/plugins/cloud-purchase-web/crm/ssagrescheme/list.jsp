<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    request.setAttribute("systemTitle", ConfigHelper.getConfig("system.title"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>合同管理-售电合同辅助设计</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body  >

<div id="schemeVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="客户合同模拟查询" slot="top" deployable="false">
        <!-- 用户名称 -->
       <su-form-group :label-name="formFields.custName.label" class="form-control-row" col="4" 
        		:class="{'display-none':!formFields.custName.searchHidden}" label-width="100px" label-align="right">
			<su-textbox :data.sync="params.ssAgreScheme.consName"></su-textbox>
        </su-form-group>
        <div slot="buttons" class="pull-right " style="text-align:right">
            <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
            <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
       	</div>
    </mk-search-panel>
    <mk-panel title="合同模拟方案列表" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right" style="text-align:right">
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
    		<su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="out">导出</su-button>
		</div>
		<div class="col-xs-12 height-fill">
        <table id="schemeGrid" style="height:100%"></table>
        </div>
    </mk-panel>
	</mk-top-bottom>
</div>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var schemeVue = new Vue({
	el: '#schemeVue',
	ready:function(){
		var me = this;
	    me.initGrid();
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.crm.ssagrescheme",me);
	},
	data: {
		consId:null,
		consName:null,
		consInfo:null, //用户信息
		//url:basePath + 'view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog-selectbox',
		selectIndex:-1,
		formFields:{},
    	params:{
    		ssAgreScheme:{
    			//consId:"",
    			consName:"",//客户名称 
    		}
    	},
	},
	methods:{
		initGrid:function(){
			var me = this;
			$('#schemeGrid').treegrid({
				url:basePath+"cloud-purchase-web/ssAgreSchemeController/treepage",
			    method:"post",
			    width: "100%",
			    scrollbarSize : 0,
			    idField:"id", 
			    treeField:"consName", 
			    fitColumns: true,
		        pagination: true,
		        singleSelect:false,
		        nowrap: false, 
		        striped:true,
		        pageSize: 10,
		        pageList: [10, 20, 50, 100, 150, 200],
		        onLoadSuccess:function(row, data){
		        	me.selectIndex = -1;
					$('#schemeGrid').treegrid('clearSelections');
		        },
		        onCheck:function(rowIndex,rowData){
					if(rowIndex == me.selectIndex){
						me.selectIndex = -1;
						$('#schemeGrid').treegrid('clearSelections');
						$('#schemeGrid').treegrid("refreshRow");
					}else{
						me.selectIndex = rowIndex;
					}
				},
			    columns:[[
			        {field:'ck',title:'ck',checkbox:true,},   
			        {field:'id',hidden:true},
			        {field:'consName',title:'客户名称',width:'25%',align:'left'},    
			        {field:'schemeName',title:'方案名称',width:'6%',align:'center',
			        	formatter: function(value,row,index){
			        		if(value && row.schemeName){
			    				return "<a onclick=\"schemeVue.detail('"+row.id+"')\">"+value+"</a>";
			    			}else{
	 			    				return value;
	 			    			}
				        	}},
			        {field:'createTime',title:'模拟时间',width:'8%',align:'center',
			        	formatter: function(value,row,index){
			        		if(value){
			    				var date = new Date(value);
			    				var month = date.getMonth()+1;
			    				var day = date.getDate();
			    				if(month<10){
			    					if(day<10){
			    						return date.getFullYear()+"-0"+month+"-0"+date.getDate();
			    					}else{
			    						return date.getFullYear()+"-0"+month+"-"+date.getDate();
			    					}
			    				}else{
			    					if(day<10){
			    						return date.getFullYear()+"-"+month+"-0"+date.getDate();
			    					}else{
			    						return date.getFullYear()+"-"+month+"-"+date.getDate();
			    					}
			    				}
			    			}
	        			}
			        },
			        {field:'diviCodeName',title:'分成方式',width:'7%',align:'center'},  
			        {field:'sellTotalPro',title:'售电公司代理收益<br/>(元)',width:'11%',align:'center'},
			        {field:'transTotalPro',title:'售电公司购电收益<br/>(元)',width:'11%',align:'center'},
			        {field:'compensateAmt',title:'售电公司赔偿费用<br/>(元)',width:'11%',align:'center'},
			        {field:'consPro',title:'用户返还电费<br/>(元)',width:'10%',align:'center'},
			        {field:'consCheckedPro',title:'用户考核电费<br/>(元)',width:'9%',align:'center'}    
			    ]]
			});
		},
		//查询列表信息
		getDataGrid:function(){
			var me = this;
            $('#schemeGrid').treegrid('options').queryParams = me.params; 
            console.log($('#schemeGrid').treegrid('options'));
            $('#schemeGrid').treegrid("reload");
		},
		//重置
		reset:function(){
			schemeVue.params.ssAgreScheme = {consId:"",}
			schemeVue.consId = null;
			schemeVue.params.createTime = "";
		},
		//显示日期格式
		rendererTime:function(value,row,index){
			if(value){
				var date = new Date(value);
				var month = date.getMonth()+1;
				var day = date.getDate();
				if(month<10){
					if(day<10){
						return date.getFullYear()+"-0"+month+"-0"+date.getDate();
					}else{
						return date.getFullYear()+"-0"+month+"-"+date.getDate();
					}
				}else{
					if(day<10){
						return date.getFullYear()+"-"+month+"-0"+date.getDate();
					}else{
						return date.getFullYear()+"-"+month+"-"+date.getDate();
					}
				}
			}
		},
		//导出----------------------------------------------------------------------------
		out : function(){
			var rows = $('#schemeGrid').treegrid("getSelections");
			var str = '';
			if(rows != null && rows.length != 0){  //有无选择导出数据
				for(var i=0;i < rows.length ; i++){ //获得选中行ID
					if(rows[i].schemeName != null && rows[i].schemeName != ''){ //有无选择有效数据
						str += rows[i].id + ',';//选中的档案ID	
						var me = this;
		            	
					} else{
						MainFrameUtil.alert({
		                    title:"提示：",
		                    body:"请选择有效导出数据",
		               	 });
						return;
						}
					}
				str = str.substr(0,str.length-1);
				var url = basePath + "ssAgreSchemeController/outExcel?ids=" + str; //导出跳转页面 ajax下载不好使
	            location.href = url;
			
			} else{
                MainFrameUtil.alert({
                    title:"提示：",
                    body:"请选择导出数据",
                });
            }
			 
		},
		
		//添加
		add : function(){
			MainFrameUtil.openDialog({
				id:'schemeAdd',
                href:'${Config.baseURL}view/cloud-purchase-web/crm/ssagrescheme/add',
                modal:true,
                iframe:true,
                width:"80%",
                height:600,
                maximizable:true,
                onClose:function(){
                	$('#schemeGrid').treegrid("reload"); //新增完信息后重新加载列表
                }
           })
		},
        //编辑
        edit:function(){
        	var rows = $('#schemeGrid').treegrid("getSelections");
        	if(rows.length == 0){
        		MainFrameUtil.alert({
                    title:"提示：",
                    body:"请选择一条数据！",
                    onClose:function(type){
                    }
                });
                return;
        	}
        	var chRow = $('#schemeGrid').treegrid("getChildren",rows[0].id);
        	if((chRow != null && chRow.length > 0) || rows[0].state == "closed"){
        		MainFrameUtil.alert({
                    title:"提示：",
                    body:"此数据不可编辑！",
                    onClose:function(type){
                    }
                });
                return;
        	}
        	
        	if(rows.length==0){
        		MainFrameUtil.alert({
                    title:"提示：",
                    body:"请选择编辑的数据！",
                    onClose:function(type){
                    }
                });
                return;
        	}else if(rows.length >1){
   			 	MainFrameUtil.alert({
	                 title:"提示：",
	                 body:"只能选择一个编辑对象！",
	             });
	             return;
			};
        	MainFrameUtil.openDialog({
                id:'schemeEdit',
                params:{"id":rows[0].id},
                href:'${Config.baseURL}view/cloud-purchase-web/crm/ssagrescheme/edit',
                modal:true,
                iframe:true,
                maximizable:true,
                width:"80%",
                height:600,
                onClose:function(){
                	$('#schemeGrid').treegrid("reload");
                }
           })
        },
        consNameRender:function(value,row,text){
			if(value){
				return "<a onclick=\"schemeVue.detail('"+row.id+"')\">"+value+"</a>";
			}
		},
        detail:function(id){
			//详细信息
	  		MainFrameUtil.openDialog({
	  			id:"schemeDetail",
	  			params:{"id":id},
				href:'${Config.baseURL}view/cloud-purchase-web/crm/ssagrescheme/detail',
				iframe:true,
				modal:true,
				maximizable:true,
				width:"80%",
				height:600,
				onClose:function(){
                	$('#schemeGrid').treegrid("reload");
                }
			});
		},
        
      	//批量删除
    	del:function(){
    		var rows = $('#schemeGrid').treegrid("getSelections");
    		
        	if(rows.length == 0){
        		MainFrameUtil.alert({
                    title:"提示：",
                    body:"请选择一条数据！",
                    onClose:function(type){
                    }
                });
                return;
        	}
    		var chRow = $('#schemeGrid').treegrid("getChildren",rows[0].id);
        	if((chRow != null && chRow.length > 0) || rows[0].state == "closed"){
        		MainFrameUtil.alert({
                    title:"提示：",
                    body:"此数据不可删除！",
                    onClose:function(type){
                    }
                });
                return;
        		
        	}
    		if(rows.length == 0 ){
    			 MainFrameUtil.alert({
                     title:"提示",
                     body:"请选择删除对象！",
                 });
                 return;
    		}
    		MainFrameUtil.confirm({
    	        title:"警告",
    	        body:"确认删除吗？删除后数据不可恢复！ ",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	var ids = new Array();
    	            	for(var i=0; i<rows.length; i++){ //获取每行id
    	            		ids.push(rows[i].id);
    	            	}
    	            	$.ajax({
    	                    url:basePath+"cloud-purchase-web/ssAgreSchemeController/",
    	                    type:"delete",
    	                    data:$.toJSON(ids),
    	                    contentType : 'application/json;charset=UTF-8',
    	                    success : function(data) {
    	                        if(data.flag == true){
    	                             MainFrameUtil.alert({
    	                                 title:"成功提示：",
    	                                 body:data.msg,
    	                             });
    	                             $("#schemeGrid").treegrid('reload');
    	                        }else{
    	                            MainFrameUtil.alert({
    	                                title:"失败提示：",
    	                                body:data.msg,
    	                            });
    	                        }
    	                    },
    	                    error : function() {
    	                        MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
    	                    }
    	                }) 
    	            }else if(type=="cancel"){//取消
    	                
    	            }

    	        }
    	    })
    	},
	},
	watch:{
		'consId':function(){
			var me = this;
			me.consInfo = me.$refs.consinfo.$data.allattr;
			me.params.ssAgreScheme.consId = me.consId;
		}
	}
}); 
</script>
</body>
</html>