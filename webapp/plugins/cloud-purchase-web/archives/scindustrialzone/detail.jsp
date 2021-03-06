<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>档案管理-园区档案详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="zoneDetailVue">
    <su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
<mk-panel title="园区维护详情" line="true">
    <mk-form-panel label-width="150px" class="mt_10" num="2">
            <mk-form-row >
                <!-- 园区名称 -->
                <mk-form-col  :label="formFields.izName.label" required_icon="true">
                      <su-textbox disabled="disabled" name="izName" :data.sync="params.scIndustrialZone.izName"></su-textbox>
                </mk-form-col>
                 <!-- 省 -->
				<mk-form-col :label="formFields.provinceCodeName.label" :class="{'display-none':!formFields.provinceCodeName.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.provinceCode" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList" name="provinceCode"></su-select>
				</mk-form-col>
            </mk-form-row>
            <mk-form-row >
                 <!--地级市 -->
                <mk-form-col :label="formFields.cityCodeName.label" required_icon="true">
                    <su-select disabled="disabled" name="cityCode" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.cityCode"
                     :data-source="cityCodeList"></su-select>
                </mk-form-col>
                 <!--地级市 -->
                <mk-form-col :label="formFields.countyCodeName.label" required_icon="true">
                    <su-select disabled="disabled" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.scIndustrialZone.countyCode"
                     :data-source="countyCodeList" name="countyCode"></su-select>
                </mk-form-col>
            </mk-form-row>
            <mk-form-row  height="90px">
                <!-- 园区描述 -->
                <mk-form-col  colspan="2" :label="formFields.izContent.label">
                    <su-textbox disabled="disabled" type="textarea" rows="3" rols="10" placeholder="" :data.sync="params.scIndustrialZone.izContent"></su-textbox>
                </mk-form-col>
            </mk-form-row>
    </mk-form-panel>
</mk-panel>
</su-form>
</div>
</body>
</html>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var zoneDetailVue = new Vue({
    el: '#zoneDetailVue',
    data: {
    	formFields:{},
        countyCodeList:[],//区县下拉框数据
        cityCodeList:[],//市下拉框数据
        params:{
            scIndustrialZone:{
                id:"",//id
                izName:"",//活动名称
                provinceCode:"",//省
                cityCode:"",//地级市
                countyCode:"",//区县
                izContent:"",//园区描述
            }
        },
        //非空验证规则
        dataOption: {
            rules: {
                izName:{required: !0},
                provinceCode:{required: !0},
                cityCode:{required: !0},
                countyCode:{required: !0}
            }
        },
        valid:false
    },
    ready:function(){
        
        //加载表单信息
        ComponentUtil.getFormFields("selling.crm.IndustrialZoneAdd",this);
        
        //获取id
        this.params.scIndustrialZone.id = MainFrameUtil.getParams("zoneDetail").id;
        //设置按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"zoneDetail");
        //初始化表单数据
        this.initData();
    },
    methods:{
        
        //获取表单信息
        initData:function(){
            $.ajax({
                url:basePath+"cloud-purchase-web/scIndustrialZoneController/"+this.params.scIndustrialZone.id,
                type:"get",
                success : function(data) {
                    if(data.flag){
                        zoneDetailVue.params.scIndustrialZone = data.data;
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
                		zoneDetailVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		zoneDetailVue.countyCodeList = data;
                	}
                }
			})
    	},
        
        //按钮组
        getButtons:function(){
            var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("zoneDetail")}}];
            return buttons;
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
