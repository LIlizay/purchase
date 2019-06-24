<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>结算管理-历史用电信息</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>

</head>
<body id="elecListVue" class="height-fill">
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="历史用电信息查询" slot="top" deployable="false">
			
			<!-- 用户名称 -->
	        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
				<su-textbox :data.sync="params.consName"></su-textbox>
	        </su-form-group>
	        
	        <!-- 电压等级 -->
	        <su-form-group :label-name="formFields.voltCodeName.label" class="form-control-row "  col="4" 
	        		:class="{'display-none':!formFields.elecTypeCode.searchHidden}" label-width="100px" label-align="right">
	            <su-select clear-btn="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.voltCode"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" ></su-select>
	        </su-form-group> 
	        
	        <!-- 用电类别 -->
	        <su-form-group :label-name="formFields.elecTypeCodeName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.industryType.searchHidden}" label-width="100px" label-align="right">
	        	<su-select clear-btn="true" placeholder="--请选择--" label-name="name" :select-value.sync="params.elecTypeCode"
								url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" ></su-select>
	        </su-form-group>
	        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
   	<mk-panel title="用电信息列表" line="true" v-cloak slot="bottom" height="100%">
        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="download" v-on:click="downloadTem">模版下载</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-in" v-on:click="importExcel">导入</su-button>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="sign-out" v-on:click="download">导出</su-button>
        </div>
        <div id="eleConsGrid"></div>
    </mk-panel>
	    
	</mk-top-bottom>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var downLoadExcelUrl = basePath+'plugins/cloud-purchase-web/archives/elec/历史用电信息导入模版.xlsx';//导入的excel模板
var elecListVue = new Vue({
	el: '#elecListVue',
	data: {
		formFields:{},
		consInfo:null,
    	params:{
    		consName:"",
    		voltCode:'',		//电压等级
    		elecTypeCode:'',	//用电类别
    	}
	},
	ready:function(){
		var me = this;
		//查询字段名称加载
	    ComponentUtil.getFormFields("selling.archive.elec", this);
		
	    $('#eleConsGrid').treegrid({ 
	        url: basePath+'scConsElectricityController/getTree', 
	        idField: 'consId', 
	        treeField: 'consName',
	        queryParams: me.params,
			method: 'POST',
			fitColumns: true,
			rownumbers: true,
		    rownumbers: true,
		    pagination: true,
		    nowrap: false,
		    pageSize: 10,
		    scrollbarSize : 0,
			singleSelect: false,
			checkOnSelect:true,
			pageSize: 10,
			pageList: [10, 20, 50, 100, 150, 200],
			width: "100%",
			height:"100%",
	        columns:[[ 
				{field:'consId',hidden:true},      
				{field:'ck',checkbox:"true"},      
	            {field:'consName',title:'用户名称',width:'19%',align:'left'},
	            {field:'voltCodeName',title:'电压等级',width:'17%',align:'center'},
	            {field:'elecTypeCodeName',title:'用电类别',width:'17%',align:'center'},
	            {field:'year',title:'年份',width:'10.5%',align:'center',
	            	formatter:function(value,row,index){
	            		if(row.year != null && row.year != ''){
			        		var url= "<a class='left-btn' onclick=\"elecListVue.detail('"+row._parentId+"','"+row.year+"')\">"+value+"</a>";
			        		return url;
	            		}else{
	            			return null;
	            		}
	            	}
	            },
	            {field:'totalPq',title:'总用电量(兆瓦时)',width:'17%',align:'center'}, 
	            {field:'totalAmt',title:'总用电费(元)',width:'17%',align:'center'}, 
	        ]],
	        onLoadSuccess : function(){
	        	var pageNumber = $("#eleConsGrid").datagrid("getPager").data("pagination").options.pageNumber;
		    	var pageSize = $("#eleConsGrid").datagrid("getPager").data("pagination").options.pageSize;
		    	var trow = $("#eleConsGrid").parent().find(".datagrid-view1 .datagrid-row .datagrid-td-rownumber .datagrid-cell-rownumber");
		    	for(var i in trow){
		    		trow[i].textContent = (parseInt(pageNumber) - 1) * parseInt(pageSize) + parseInt(i) + 1;
		    	}
	        	$("#eleConsGrid").datagrid("clearChecked");
	        },
	    });
	},
	methods: {
		//详情查看
		detail:function(id,year){
			MainFrameUtil.openDialog({
	  			id:"year-detail",
	  			params:{consId:id,year:year},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elec-year',
				iframe:true,
				modal:true,
				width:"80%",
				height:610,
				buttons:[{text:"关闭",type:'default',handler:function(btn,params){
	            	MainFrameUtil.closeDialog("year-detail");
	            }}]
			});
		},
		//查询列表信息
		getDataGrid:function(){
            $('#eleConsGrid').treegrid('options').queryParams = this.params;  
            $('#eleConsGrid').treegrid("load");
		},
		//重置
		reset : function(){
			this.params = {consName:"",consId:'',elecTypeCode:'',voltCode:''};
		},
		//新增
		add:function(){
			MainFrameUtil.openDialog({
	  			id:"add",
				href:'${Config.baseURL}view/cloud-purchase-web/archives/elec/add',
				iframe:true,
				modal:true,
				width:"80%",
				height:610,
				onClose:function(){
					$('#eleConsGrid').treegrid("reload");
				}
			}); 
		},
		download:function(){
			var me = this;
			var selInfo = $('#eleConsGrid').datagrid('getChecked');
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"是否导出？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	var childIds = "";
		    			var parentIds = '';
		    			if(selInfo.length > 0){
		    				for(var i = 0 ; i < selInfo.length ; i++){
			    				if(selInfo[i]._parentId == null || selInfo[i]._parentId == ""){
			    					parentIds += selInfo[i].consId+",";
			    				}else{
			    					childIds += selInfo[i].consId + ",";
			    				}
			    			}
			    			var str = parentIds.substr(0,parentIds.length-1) + "*" + childIds.substr(0,childIds.length-1); 
			    			//year\consId是无用参数，用户档案-历史用电信息 导出需要的参数  共用一个方法 
			    			var url = basePath + "scConsElectricityController/exportExcel?ids=" + str + '&consName=' + '' 
			    					+ "&voltCode=" +'' + "&elecTypeCode=" + '' + '&year=' + '' + '&consId=' + '';
		    			}else{
			    			var url = basePath + "scConsElectricityController/exportExcel?ids=" + ''  + '&consName=' + me.params.consName 
			    					+ "&voltCode=" + me.params.voltCode + "&elecTypeCode="+ me.params.elecTypeCode + '&year=' + '' + '&consId=' + '';
		    			}
		    			 
		                location.href = url;
		            }
		        }
    		});
		},
		//删除
		del:function(){
			var selInfo = $('#eleConsGrid').datagrid('getChecked');
			if(selInfo.length < 1){
		    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
		    	return;
		    }
			MainFrameUtil.confirm({
		        title:"确认",
		        body:"该操作不能恢复，确定要注销选中的记录吗？",
		        onClose:function(type){
		            if(type=="ok"){//确定
		            	$.ajax({
	                		url:basePath + 'cloud-purchase-web/scConsElectricityController',
	                		type:"delete",
	                		data:$.toJSON({treeList:selInfo}),
							contentType : 'application/json;charset=UTF-8',
	                		success:function(data){
	                			if(data.flag){
	    	      					MainFrameUtil.alert({title:"提示",body:"删除成功！"});
	    	      					if(selInfo._parentId == null || selInfo._parentId == ""){
	    	      						$('#eleConsGrid').treegrid("reload");
	    	      					}else{
	    	      						$('#eleConsGrid').treegrid("reload",selInfo._parentId);
	    	      					}
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
		//下载模板
		downloadTem:function(){
        	location.href = downLoadExcelUrl;
		}, 
		//编辑
		edit:function(){
			var selInfo = $('#eleConsGrid').datagrid('getChecked');
			if(selInfo==null||selInfo.length==0){
		    	MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
		    	return;
		    }else if(selInfo.length>1){
		    	MainFrameUtil.alert({title:"警告",body:"只能选择一条数据！"});
		    	return;
		    }
		    if(selInfo[0]._parentId == null || selInfo[0]._parentId == ""){
		    	MainFrameUtil.alert({title:"警告",body:"此数据不可编辑！"});
		    	return;
		    }
	  		MainFrameUtil.openDialog({
	  			id:"elec-add",
	  			params:{consId:selInfo[0]._parentId,year:selInfo[0].year,flag:true},
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/elec-add',
				iframe:true,
				modal:true,
				width:"80%",
				height:610,
				onClose:function(){
					$('#eleConsGrid').treegrid("reload",selInfo._parentId);
				}
			}); 
		}
	}
}); 
</script>
</body>
</html>