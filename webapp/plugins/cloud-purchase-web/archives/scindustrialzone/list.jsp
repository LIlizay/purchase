<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>档案管理-园区档案</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="zoneVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
    <mk-search-panel title="园区维护" slot="top" deployable="false" label-width="120px">
        <mk-search-visible>
            <!-- 活动名称 -->
            <su-form-group :label-name="formFields.izName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.izName.searchHidden}" label-width="120px" label-align="right">
                <su-textbox :data.sync="params.scIndustrialZone.izName" ></su-textbox>
            </su-form-group>
             <!-- 省-->
            <su-form-group :label-name="formFields.provinceCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.provinceCodeName.searchHidden}" label-width="120px" label-align="right">
                <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.provinceCode" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList" name="provinceCode"></su-select>
            </su-form-group> 
            <!-- 地级市-->
            <su-form-group :label-name="formFields.cityCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.cityCodeName.searchHidden}" label-width="120px" label-align="right">
                <su-select search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.cityCode" :data-source="cityCodeList"></su-select>
            </su-form-group>
            <!-- 区县-->
            <su-form-group :label-name="formFields.countyCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.countyCodeName.searchHidden}" label-width="120px" label-align="right">
                <su-select search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.countyCode"
                     :data-source="countyCodeList" name="countyCode"></su-select>
            </su-form-group>
        </mk-search-visible>
            
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
    </mk-search-panel>
    
    <mk-panel title="园区维护列表" line="true" slot="bottom" height="100%">
        <div slot="buttons" class="pull-right" style="text-align:right">
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
        </div>
        <div id="zoneGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
    </mk-top-bottom>
</div>
</body>
</html>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var zoneVue = new Vue({
    el: '#zoneVue',
    data: {
    	formFields:{},
    	countyCodeList:[],//区县下拉框数据
    	cityCodeList:[],//市下拉框数据
    	params:{
            scIndustrialZone:{
            	izName:"",//活动名称
            	provinceCode:"",
            	cityCode:"",//活动类型
            	countyCode:"",//销售人员
            }
        }
    },
    ready:function(){
    	
    	//加载表单信息
        ComponentUtil.getFormFields("selling.crm.IndustrialZoneAdd",this);
    	//加载列表信息
        ComponentUtil.buildGrid("selling.crm.IndustrialZoneAdd",$("#zoneGrid"),{ 
            url: basePath + 'cloud-purchase-web/scIndustrialZoneController/page',
            width:"100%",
            height:"100%",
            method: 'post',
            queryParams:this.params,
            rownumbers: true,
            pagination: true,
            nowrap: false,
            fitColumns:true,
            pageSize: 10,
            pageList: [10, 20, 50, 100, 150, 200],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            }
        })
    },
    methods:{
        
    	//获取下拉框的码值
    	initCode:function(code,type){
    		var url = "";
    		if(type == "cityCode"){
    			url = "globalCache/queryCityByParentCode/" + code;
        	}else if(type == "countyCode"){
        		url = "globalCache/queryCountyByParentCode/" + code;
        	}
    		$.ajax({
                url:basePath+url,
                type:"get",
                success : function(data) {
                	if(type == "cityCode"){
                		zoneVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		zoneVue.countyCodeList = data;
                	}
                }
			})
    	},
    	
    	//新增销售机会管理
    	add : function(){
    		 MainFrameUtil.openDialog({ 
    			 id:"zoneAdd",
                 href:'${Config.baseURL}view/cloud-purchase-web/archives/scindustrialzone/add',
                 title:'园区新增',
                 iframe:true,
                 modal:true,
                 maximizable:true,
                 width:"80%",
                 height:500,
                 onClose:function(){
                     $("#zoneGrid").datagrid('load');
                 }
             });
    	},
    	
    	//编辑销售机会管理
    	edit : function(){
    		var rows = $('#zoneGrid').datagrid("getSelections");
    		if(rows.length == 0){
    			 MainFrameUtil.alert({
                     title:"提示信息：",
                     body:"请选择一条数据！",
                 });
                 return;
    		}else if(rows.length >1){
    			 MainFrameUtil.alert({
                     title:"提示信息：",
                     body:"只能选择一条数据！",
                 });
                 return;
    		}
    		MainFrameUtil.openDialog({ 
    			id:"zoneEdit",
    			params:{id:rows[0].id},
                href:'${Config.baseURL}view/cloud-purchase-web/archives/scindustrialzone/edit',
                title:'园区编辑',
                iframe:true,
                modal:true,
                maximizable:true,
                width:"80%",
                height:500,
                onClose:function(){
                    $("#zoneGrid").datagrid('reload');
                }
            })
    	},
    	
    	//批量删除
    	del:function(){
    		var rows = $('#zoneGrid').datagrid("getSelections");
    		if(rows.length == 0 ){
    			 MainFrameUtil.alert({
                     title:"提示信息：",
                     body:"请选择删除对象",
                 });
                 return;
    		}
    		MainFrameUtil.confirm({
    	        title:"确认删除吗?",
    	        body:"删除后不可恢复",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	var ids = new Array();
    	            	for(var i=0; i<rows.length; i++){ //获取每行id
    	            		ids.push(rows[i].id);
    	            	}
    	            	$.ajax({
    	                    url:basePath+"cloud-purchase-web/scIndustrialZoneController",
    	                    type:"delete",
    	                    data:$.toJSON(ids),
    	                    contentType : 'application/json;charset=UTF-8',
    	                    success : function(data) {
    	                        if(data.flag){
    	                             MainFrameUtil.alert({
    	                                 title:"成功提示：",
    	                                 body:data.msg,
    	                             });
    	                             $("#zoneGrid").datagrid('reload');
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
    	
    	//渲染列表izName字段
    	izNameRender : function(value,row,index){
            return "<a onclick=\"zoneVue.view('"+row.id+"')\">"+row.izName+"</a>";
        },
      	//点击销售机会名称查看详细信息
        view:function(id){
            MainFrameUtil.openDialog({
            	id:"zoneDetail",
                params:{"id":id},
                href:'${Config.baseURL}view/cloud-purchase-web/archives/scindustrialzone/detail',
                title:'园区信息查看',
                modal:true,
                iframe:true,
                maximizable:true,
                width:"80%",
                height:500,
                onClose:function(){
                    $("#zoneGrid").datagrid('reload');
                }
           })
        },
    	
    	//查询
    	getDataGrid:function(){
    		$('#zoneGrid').datagrid("load");
    	},
    	
    	//重置
    	reset:function(){
    		this.params.scIndustrialZone = {izName:"",provinceCode:"",cityCode:"",countyCode:""}
    	}
    },
    
    watch:{
    	'params.scIndustrialZone.provinceCode':function(value){
			if(value){
                this.initCode(value,"cityCode");
            }else{
                this.cityCodeList = [];
            }
		},
		'params.scIndustrialZone.cityCode':function(value){
			if(value){
                this.initCode(value,"countyCode");
            }else{
                this.countyCodeList = [];
            }
		},
    }
});
</script>