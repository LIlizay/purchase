<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择园区（公共）</title>
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
            singleSelect:true,
            pagination: true,
            nowrap: false,
            fitColumns:true,
            pageSize: 10,
            pageList: [10, 20, 50, 100, 150, 200],
            rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
            }
        });
        MainFrameUtil.setDialogButtons(this.getButtons(),"zoneDialog");
    },
    methods:{
    	 getButtons:function(){
             var buttons=[{
                 text:"确定",
                 type:"primary",
                 handler:function(btn,params){
                 	var row = $("#zoneGrid").datagrid('getChecked');
                 	if(row.length == 0){
                 		MainFrameUtil.alert({title:"提示",body:"请选择园区！"});
                 		return false;
                 	}else{
                 		MainFrameUtil.setParams(row,"zoneDialog");
	                    	MainFrameUtil.closeDialog("zoneDialog");
                 	}
                 }
             },{
                 text:"取消",
                 handler:function(btn,params){
                     MainFrameUtil.closeDialog("zoneDialog");
                 }
             }];
             return buttons;
         },
        
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
                modal:true,
                iframe:true,
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