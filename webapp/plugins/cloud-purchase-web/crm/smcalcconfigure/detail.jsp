<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看算法配置</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="calcDetailVue">
    <su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
<mk-panel title="查看算法配置" line="true">
    <mk-form-panel label-width="150px" class="mt_10" num="2">
            <mk-form-row >
                <!-- 算法名称 -->
                <mk-form-col  :label="formFields.calcName.label" required_icon="true">
                      <su-textbox disabled="disabled" name="calcName" :data.sync="params.smCalcConfigure.calcName"></su-textbox>
                </mk-form-col>
                <!-- 省 -->
				<mk-form-col :label="formFields.provinceName.label" :class="{'display-none':!formFields.provinceName.formHidden}" required_icon="true">
					<su-select disabled="disabled" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.province" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList" name="provinceName"></su-select>
				</mk-form-col>
            </mk-form-row>
            <mk-form-row >
                 <!--售电公司(库名) -->
                <mk-form-col :label="formFields.sellDbName.label">
                    <su-select disabled="disabled" name="sellDbName" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.sellDb"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getSellDb"></su-select>
                </mk-form-col>
                 <!--脚本选择(编码) -->
                <mk-form-col :label="formFields.calcCodeName.label" required_icon="true">
                    <su-select disabled="disabled" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.calcCode"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getRuleValue" name="calcCodeName"></su-select>
                </mk-form-col>
            </mk-form-row>
            <mk-form-row  height="90px">
                <!-- 算法说明 -->
                <mk-form-col  colspan="2" :label="formFields.calcContent.label" required_icon="true">
                    <su-textbox disabled="disabled" type="textarea" rows="3" rols="10" placeholder="" :data.sync="params.smCalcConfigure.calcContent"></su-textbox>
                </mk-form-col>
            </mk-form-row>
    </mk-form-panel>
</mk-panel>
	<mk-panel title="算法配置列表" line="true" height="300px">
<!-- 		<div slot="buttons" class="pull-right" style="text-align:right"> -->
<!-- 			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button> -->
<!-- 			<su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button> -->
<!-- 		</div> -->
		<div id="calcGrid" class="col-xs-12" style="height:100%"></div>
	</mk-panel>
</su-form>
</div>
</body>
</html>

<script type="text/javascript">
ProgressUtil.wait();  // 进度条开始
var basePath = '${Config.baseURL}';
var calcDetailVue = new Vue({
    el: '#calcDetailVue',
    data: {
    	formFields:{},
    	sellDbList:[{value:"01",name:"库名测试1"},{value:"02",name:"库名测试2"}],//
    	calcCodeList:[{value:"01",name:"编码测试1"},{value:"02",name:"编码测试2"}],//
    	params:{
    		smCalcConfigure:{
    			id : null,
    			calcName:"",//
            	province:"",
            	sellDb:"",//
            	calcCode:"",//
            	calcContent : null,
            	calcParam : ""
            }
    	},
        //非空验证规则
        dataOption: {
            rules: {
            	calcName:{required: !0},
            	provinceName:{required: !0},
            	calcContent:{required: !0},
            	calcCodeName:{required: !0}
            }
        },
        valid:false
    },
    ready:function(){
    	var me = this;
        //加载表单信息
        ComponentUtil.getFormFields("purchase.archives.smcalcconfigure",this);
        
        //获取id
        this.params.smCalcConfigure.id = MainFrameUtil.getParams("calcDetail").id;
        //设置按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"calcDetail");
        me.initGrid();
        //初始化表单数据
        this.initData();
    },
    methods:{
    	
    	initGrid : function(){
    		ComponentUtil.buildGrid("purchase.archives.smcalcconfigure2",$("#calcGrid"),{ 
                data:[],
                width:"100%",
                height:"100%",
                method: 'post',
                rownumbers: true,
//                 pagination: true,
                nowrap: false,
                fitColumns:true,
                pageSize: 10,
                pageList: [10, 20, 50, 100, 150, 200],
                rowStyler:function(idx,row){
                    return "height:35px;font-size:12px;";
                },
                columnsReplace:[{field:'value',title:'参数值',
    	                         editor:{
    	                        	 type:'numberbox',
    	                             options:{
    	                            	 required:true,
    	                            	 precision:2,
    	                            	 //min:0,
    	                            	 onChange:function(value){//计算总成交电量
    	    					        	 if(value){
    	    					        		 
    	    					        	 }
    	    					         }
    	                     		 }
    	                     	 }
    	                     }]
            })
    		
    	},
        
        //获取表单信息
        initData:function(){
        	var me = this;
            $.ajax({
                url:basePath+"cloud-purchase-web/smCalcConfigureController/"+me.params.smCalcConfigure.id,
                type:"get",
                success : function(data) {
                    if(data.flag){
                    	me.params.smCalcConfigure = data.data;
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
                		calcDetailVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		calcDetailVue.countyCodeList = data;
                	}
                }
			})
    	},
        
        //按钮组
        getButtons:function(){
            var buttons = [{text:"关闭",handler:function(){MainFrameUtil.closeDialog("calcDetail")}}];
            return buttons;
        }
    },
    
    watch:{
    	"formFields":function(){
			ProgressUtil.close(); //进度条结束
		},
		'params.smCalcConfigure.calcCode':function(value){
    		var me = this;
    		$.ajax({
    			url : basePath+"cloud-purchase-web/smCalcConfigureController/getRuleParams",
    			type : "post",
    			data:value,
                contentType : 'application/json;charset=UTF-8',
    			success : function(data){
    				if(data.flag){
    					var rows = data.rows;
    					var obj = eval('(' + me.params.smCalcConfigure.calcParam + ')');
    					for(var a in obj){
    						for(var b in rows){
    							if(a == rows[b].field){
    								rows[b].value = parseFloat(obj[a]);
    								break;
    							}
    						}
    					}
    					$("#calcGrid").datagrid("loadData", rows);
//     					for(var i in rows){
//     						$("#calcGrid").datagrid("beginEdit",i);
//     					}
    				}
    			}
    			
    		});
			
		},
//     	'params.smCalcConfigure.provinceCode':function(value){
// 			if(value){
//                 this.initCode(value,"cityCode");
//             }else{
//                 this.cityCodeList = [];
//             }
// 		},
// 		'params.smCalcConfigure.cityCode':function(value){
// 			if(value){
//                 this.initCode(value,"countyCode");
//             }else{
//                 this.countyCodeList = [];
//             }
// 		},
    }
});
</script>
