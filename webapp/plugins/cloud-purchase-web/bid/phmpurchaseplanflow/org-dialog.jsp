<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>购电管理-月度购电计划制定新增供电公司弹出框</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="powerOrgPage" class="height-fill">
	<mk-top-bottom height="100%" top_height="auto">
	    <mk-search-panel title="供电公司查询" deployable="false" slot="top">
	        <!-- 供电公司名称 -->
	        <su-form-group :label-name="formFields.name.label" class="form-control-row" :class="{'display-none':!formFields.name.searchHidden}" col="4" label-width="120px" label-align="right">
	           	<su-textbox :data.sync="params.name"></su-textbox>
	        </su-form-group>
	        <!-- 所在省 -->
	        <su-form-group :label-name="formFields.provinceCode.label" class="form-control-row" :class="{'display-none':!formFields.provinceCode.searchHidden}" col="4" label-width="120px" label-align="right">
	            <su-select name="formProvinceCode" id="province" :select-value.sync="params.provinceCode" :high="150" label-name="name"
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
        
	    params:{id:null,name:'',provinceCode:'',cityCode:'',countyCode:''},
	},
	ready: function(){
	    ComponentUtil.buildGrid("selling.archives.powerOrg",$("#powerOrgGrid"),{ 
	        url: '${Config.baseURL}sConsDialogController/orgPage',
	        height:"100%",
	        method: 'post',
	        queryParams:this.params,
	        rownumbers: true,
	        pagination: true,
	        singleSelect:true,
	        nowrap: false, 
	        pageSize: 10,
	        pageList: [10, 20, 50, 100, 150, 200],
	        fitColumns:true,
	        scrollbarSize:0,
	        fit:true,
	        rowStyler:function(idx,row){
	            return "height:35px;font-size:12px;";
	        },
	        columnsReplace:[
	        	{field:"vatName",hidden:true},
	        	{field:"vatNo",hidden:true},
	        	{field:"bankName",hidden:true},
	        	{field:"vatAcct",hidden:true},
	        	{field:"regAddr",hidden:true}
	        ]
	    }); 
	    ComponentUtil.getFormFields("selling.archives.powerOrg",this);
	    MainFrameUtil.setDialogButtons(this.getButtons(),"mk-power-up-dialog-id");
	},
	 watch:{
    	//监听市下拉列表的变化，改变是去重新加载区县的下拉数据
        'cityCodeVal':function(value,oldVal){
            this.setCityOrCountryList(false);
        }
	},
	methods: {
		
		getButtons:function(){
            var buttons=[{
                text:"确定",
                type:"primary",
                handler:function(btn,params){
                	var row = $("#powerOrgGrid").datagrid('getSelected');
                	if(row.length == 0){
                		MainFrameUtil.alert({title:"提示",body:"请选择供电公司！"});
                		return false;
                	}else{
//                 		params.okHandler(row);
                		MainFrameUtil.setParams(row,"mk-power-up-dialog-id");
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
        
		//获取市或区县的下拉数据，若sign为true则获取市的下拉数据，若false则获取区县的下拉数据
		setCityOrCountryList:function(sign){
            var code = this.params.provinceCode;   //获取刚选中的值
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
	        this.$set("params."+key,eval("this."+key+"Val[0]"));
	    },
	    getDataGrid: function(){
	        $("#powerOrgGrid").datagrid('load',this.params);
	    },
	    reset: function(){
	    	this.params = {id:null,name:'',provinceCode:'',cityCode:'',countyCode:''};
	    },
	    
		nameRender:function(value,row,text){
			return value;
		}
	}
}); 
</script>
</body>
</html>