<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>新增算法配置</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="calcAddVue">
    <su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
<mk-panel title="新增算法配置" line="true">
    <mk-form-panel label-width="150px" class="mt_10" num="2">
            <mk-form-row >
                <!-- 算法名称 -->
                <mk-form-col  :label="formFields.calcName.label" required_icon="true">
                      <su-textbox name="calcName" :data.sync="params.smCalcConfigure.calcName"></su-textbox>
                </mk-form-col>
                <!-- 省 -->
				<mk-form-col :label="formFields.provinceName.label" :class="{'display-none':!formFields.provinceName.formHidden}" required_icon="true">
					<su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.province" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList" name="provinceName"></su-select>
				</mk-form-col>
            </mk-form-row>
            <mk-form-row >
                 <!--售电公司(库名) -->
                <mk-form-col :label="formFields.sellDbName.label" >
                    <su-select name="sellDbName" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.sellDb"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getSellDb"></su-select>
                </mk-form-col>
                 <!--脚本选择(编码) -->
                <mk-form-col :label="formFields.calcCodeName.label" required_icon="true">
                    <su-select search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.calcCode"
                      url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getRuleValue" name="calcCodeName"></su-select> <!-- :data-source="calcCodeList" -->
                </mk-form-col>
            </mk-form-row>
            <mk-form-row  height="90px">
                <!-- 算法说明 -->
                <mk-form-col  colspan="2" :label="formFields.calcContent.label" required_icon="true">
                    <su-textbox name="calcContent" type="textarea" rows="3" rols="10" placeholder="" :data.sync="params.smCalcConfigure.calcContent"></su-textbox>
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
var calcAddVue = new Vue({
    el: '#calcAddVue',
    data: {
    	formFields:{},
    	sellDbList:[],//
    	calcCodeList:[],//
    	params:{
    		smCalcConfigure:{
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
    	//设置按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"calcAdd");
    	//me.initCalcCode();
    	me.initGrid();
    	
    },
    methods:{
    	
    	//查询规则下拉框
    	initCalcCode : function(){
    		var me = this;
    		$.ajax({
    			url : basePath+"cloud-purchase-web/smCalcConfigureController/getRuleValue",
    			type : "get",
    			success : function(data){
    				me.calcCodeList = data;
    			}
    			
    		});
    		
    	},
    	
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
    	                            	 onChange:function(value){//
    	    					        	 if(value){
    	    					        		 
    	    					        	 }
    	    					         }
    	                     		 }
    	                     	 }
    	                     }]
            })
    		
    	},
    	
    	add:function(){
//     		$("#calcGrid").datagrid("appendRow",{a1:"a",c3:"c",d4:"d"});
//        		var index = $("#calcGrid").datagrid("getRows").length - 1;
//     		$("#calcGrid").datagrid("beginEdit",index);
    	},
    	
    	//删除
    	del:function(){
    		var rows = $("#calcGrid").datagrid("getSelections");
    		if(rows.length == 0){
    			MainFrameUtil.alert({title:"提示",body:"请选择删除的数据！"});
    		}
    		MainFrameUtil.confirm({
                title:"提示",
                body:"确认删除选中数据？",
                onClose:function(type){
                    if(type=="ok"){//确定
                    	for(var obj in rows){
                			var index = $("#calcGrid").datagrid("getRowIndex",rows[obj]);
                			$("#calcGrid").datagrid("deleteRow",index);
//                 			if(rows[obj].id){
//                 				me.ids.push(rows[obj].id);
//                 			}
                		}
//                     	this.countDealPq();
                    }
                }
            })
    		
    	},
    	
    	//验证表格信息是否完善
    	validateMessage:function(){
    		var me = this;
    		var rows =  $("#calcGrid").datagrid("getRows");
    		for(var i=0; i<rows.length; i++){
	  	    	if(!$('#calcGrid').datagrid('validateRow', i)){
	  	    		MainFrameUtil.alert({title:"警告",body:"还有未完成编辑的表格，请完成编辑或删除！"});
					return true;
	  	    	}
    		}
    		return false;
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
                		calcAddVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		calcAddVue.countyCodeList = data;
                	}
                }
			})
    	},
    	
    	//保存销售机会信息
        save:function(){
        	var me = this;
        	this.$refs.elepower.valid();
            if(this.valid===false){
                return false;
            }
            if(me.validateMessage()){
            	return;
            }
            me.praramsToJson();
        	$.ajax({
                url:basePath+"cloud-purchase-web/smCalcConfigureController",
                type:"post",
                data:$.toJSON(this.params.smCalcConfigure),
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                	if(data.flag){
                		 MainFrameUtil.alert({
                             title:"成功提示：",
                             body:data.msg,
                         });
                		 MainFrameUtil.closeDialog("calcAdd");
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
        
        praramsToJson : function(){
        	var me = this;
    		var rows =  $("#calcGrid").datagrid("getRows");
    		for(var i=0; i<rows.length; i++){
	  	    	$('#calcGrid').datagrid('endEdit', i);
    		}
    		rows = $("#calcGrid").datagrid("getRows");
    		var json = "";
    		for(var a in rows){
    			if(a == rows.length - 1){
    				json += rows[a].field + ":" + rows[a].value;
    			}else{
    				json += rows[a].field + ":" + rows[a].value + ",";
    			}
    		}
    		me.params.smCalcConfigure.calcParam = "{" + json + "}";
        },
        
        //按钮组
        getButtons:function(){
        	var buttons = [{text:"保存",type:"primary",handler:this.save},{text:"关闭",handler:function(){MainFrameUtil.closeDialog("calcAdd")}}];
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
    					$("#calcGrid").datagrid("loadData", data.rows);
    					for(var i in data.rows){
    						$("#calcGrid").datagrid("beginEdit",i);
    					}
    				}
    			}
    			
    		});
			
		},
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
