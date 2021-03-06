<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>档案管理-供电公司档案</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="powerOrgPage" class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
	    <mk-search-panel title="供电公司查询" deployable="false" slot="top">
	        <!-- 供电公司名称 -->
	        <su-form-group :label-name="formFields.name.label" class="form-control-row" :class="{'display-none':!formFields.name.searchHidden}" col="4" label-width="120px" label-align="right">
	           	<su-textbox :data.sync="params.scOrgInfo.name"></su-textbox>
	        </su-form-group>
	        <!-- 所在省 -->
	        <su-form-group :label-name="formFields.provinceCode.label" class="form-control-row" :class="{'display-none':!formFields.provinceCode.searchHidden}" col="4" label-width="120px" label-align="right">
	            <su-select name="formProvinceCode" id="province" :select-value.sync="params.scOrgInfo.provinceCode" :high="150" label-name="name"
		            url="${Config.baseURL}globalCache/queryProvinceList" @su-change="setCityOrCountryList(true)"></su-select>
	        </su-form-group>
	        <!-- 所在市 -->
	        <su-form-group :label-name="formFields.cityCode.label" class="form-control-row" :class="{'display-none':!formFields.cityCode.searchHidden}" col="4" label-width="120px" label-align="right">
	            <su-select name="formCityCode" id="city" :data-source="cityCodeList" :data.sync="cityCodeVal" :high="150" 
		        	@su-change="selVal('cityCode')" label-name="name"></su-select>
	        </su-form-group>
	        <!-- 所在区县 -->
	        <su-form-group :label-name="formFields.countyCode.label" class="form-control-row" :class="{'display-none':!formFields.countyCode.searchHidden}" col="4" label-width="120px" label-align="right">
	            <su-select name="formCountyCode" id="county" :data-source="countyCodeList" :data.sync="countyCodeVal" :high="150" 
		        	@su-change="selVal('countyCode')" label-name="name"></su-select>
	        </su-form-group>
	
	        <div slot="buttons" class="pull-right " style="text-align:right">
	            <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
	            <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
	        </div>
	   	</mk-search-panel>
	   
	   	<mk-panel title="供电公司列表" line="true" v-cloak slot="bottom" height="100%">
	        <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
	            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="deletePowerOrg">删除</su-button>
	        </div>
	        <div id="powerOrgGrid"></div>
	    </mk-panel>
	</mk-top-bottom>
</div>
<script type="text/javascript">
var basePath="${Config.baseURL}";
var powerOrgVue = new Vue({
	el: '#powerOrgPage',
	data: {
		formFields:{},
	 	cityCodeList:[],// 行政区
        cityCodeVal:[],// 行政区
        countyCodeList:[],// 区（县）
        countyCodeVal:[],// 区（县）
        
	    params:{scOrgInfo:{id:null,name:'',provinceCode:'',cityCode:'',countyCode:''}},
	},
	ready: function(){
	    ComponentUtil.buildGrid("selling.archives.powerOrg",$("#powerOrgGrid"),{ 
	        url: '${Config.baseURL}cloud-purchase-web/scOrgInfoController/page',
	        height:"100%",
	        method: 'post',
	        queryParams:this.params,
	        rownumbers: true,
	        pagination: true,
	        nowrap: false, 
	        pageSize: 10,
	        pageList: [10, 20, 50, 100, 150, 200],
	        fitColumns:true,
	        scrollbarSize:0,
	        fit:true,
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        } 
	    }); 
	    ComponentUtil.getFormFields("selling.archives.powerOrg",this);
	},
	 watch:{
    	//监听市下拉列表的变化，改变是去重新加载区县的下拉数据
        'cityCodeVal':function(value,oldVal){
            this.setCityOrCountryList(false);
        }
	},
	methods: {
		//获取市或区县的下拉数据，若sign为true则获取市的下拉数据，若false则获取区县的下拉数据
		setCityOrCountryList:function(sign){
            var code = this.params.scOrgInfo.provinceCode;   //获取刚选中的值
            var url = '';
            if(!sign){
            	code = this.cityCodeVal[0];
            	url = "/globalCache/queryCountyByParentCode/";
            }else{
            	url = "/globalCache/queryCityByParentCode/";
            }
            if(code != null && $.trim(code) != ""){        //若选中了省或市，则根据代码取得下拉数据
            	$.ajax({
                    url : basePath+url+code,
                    type : 'get',
                    success : function(data) {
                    	if(sign){     //点击了省的下拉
                    		powerOrgVue.$set("cityCodeList", eval(data));
                    	}else{        //点击了市的下拉
                    		powerOrgVue.$set("countyCodeList", eval(data));
                    	}
                    }
                });
            }else{         //取消选中，则清空下拉数据
            	if(sign){       //如果取消选中的是省，则市、县的下拉数据都清空
                    powerOrgVue.$set("cityCodeList", []);
                    powerOrgVue.$set("cityCodeVal", []);
                }
                powerOrgVue.$set("countyCodeList", []);
                powerOrgVue.$set("countyCodeVal", []);
            }
            if(sign){  //如果点击的是省，把县的下拉数据清空
            	powerOrgVue.$set("countyCodeList", []);
            }
        },
	    selVal:function(key){
	        this.$set("params.scOrgInfo."+key,eval("this."+key+"Val[0]"));
	    },
	    getDataGrid:function(){
	        $("#powerOrgGrid").datagrid('load');
	    },
	    reset: function(){
	    	this.params.scOrgInfo = {id:null,name:'',provinceCode:'',cityCode:'',countyCode:''};
	    },
	    add:function(){
	    	MainFrameUtil.openDialog({
        		id:"addPowerOrgDialog",
                iframe:true,//是否使用iframe加载
                href:'${Config.baseURL}view/plugins/cloud-purchase-web/archives/scorginfo/power-org-add',
                title:'供电公司档案新增',
                modal:true,
                maximizable:true,
                width:950,
                height:400,
                onClose:function(params){//点击关闭回调函数
                	$("#powerOrgGrid").datagrid('reload');
                }
            });
	    },
	    edit:function(){
	        //更新页面
	    	var row = $("#powerOrgGrid").datagrid("getChecked");
	        if(row.length>1){
	        	MainFrameUtil.alert({title:"提示",body:"只能选择一条数据！"}); 
	        	return;
	        }
            if(row.length < 1){
                MainFrameUtil.alert({title:"提示",body:"请先选择要修改的行！"}); 
            }else{
            	MainFrameUtil.openDialog({
            		id:"editPowerOrgDialog",
                    iframe:true,//是否使用iframe加载
                    href:'${Config.baseURL}view/plugins/cloud-purchase-web/archives/scorginfo/power-org-edit?powerOrgId='+row[0].id,
                    title:'供电公司档案编辑',
                    modal:true,
                    maximizable:true,
                    width:950,
                    height:400,
                    onClose:function(params){//点击关闭回调函数
                        $("#powerOrgGrid").datagrid('reload');      //重新加载列表数据
                    }
                });
            }
	    },
	    deletePowerOrg : function() {
	        var row = $("#powerOrgGrid").datagrid("getSelected");
	        if(row == null){
	        	MainFrameUtil.alert({title:"提示",body:"请先选择要删除的行！"}); 
	        }else{
	            MainFrameUtil.confirm({
	                title:"确认删除吗?",
	                body:"删除后不可恢复",
	                onClose:function(type){
	                    if(type == "ok"){
	                    	$.messager.progress();     //打开进度条
	                        $.ajax({
	                            url : "${Config.baseURL}cloud-purchase-web/scOrgInfoController/" + row.id,
	                            type : "delete",
	                            success : function(data) {
	                            	$.messager.progress('close');  //关闭进度条
	                                if(data.flag == true){
	                                	MainFrameUtil.alert({title:"成功提示",body:data.msg}); 
	                                    $("#powerOrgGrid").datagrid("reload");
	                                }else{
	                                	MainFrameUtil.alert({title:"失败提示",body:data.msg}); 
	                                    $("#powerOrgGrid").datagrid("reload");
	                                }
	                            },
	                            error : function() {
	                            	$.messager.progress('close');  //关闭进度条
	                            	MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"}); 
	                            }
	                        }); 
	                    }
	                }
	            }); 
	        }
	    },
		nameRender:function(value,row,text){
			if(value){
				return "<a onclick=\"powerOrgVue.detail('"+row.id+"')\">"+value+"</a>";
			}
		},
		detail:function(id){
			//详细信息
	  		MainFrameUtil.openDialog({
	  			id:"detailPowerOrgDialog",
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scorginfo/power-org-detail?powerOrgId='+id,
				title:'供电公司档案信息查看',
				iframe:true,
				maximizable:true,
				modal:true,
				width:"80%",
				height:570
			});
			
		}
	}
}); 
</script>
</body>
</html>