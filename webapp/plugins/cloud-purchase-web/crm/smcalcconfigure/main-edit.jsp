<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="calcEditVue">
    <su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
<mk-panel title="算法配置" line="true">
    <mk-form-panel label-width="150px" class="mt_10" num="2">
            <mk-form-row >
                <!-- 算法名称 -->
                <mk-form-col  :label="formFields.calcName.label" required_icon="true">
                      <su-textbox name="calcName" :data.sync="params.smCalcConfigure.calcName"></su-textbox>
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
                    <su-select name="sellDbName" search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.sellDb"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getSellDb"></su-select>
                </mk-form-col>
                 <!--脚本选择(编码) -->
                <mk-form-col :label="formFields.calcCodeName.label" required_icon="true">
                    <su-select search="false" @su-change="changeflag" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.calcCode"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getRuleValue" name="calcCodeName"></su-select>
                </mk-form-col>
            </mk-form-row>
            <mk-form-row  height="90px">
                <!-- 算法说明 -->
                <mk-form-col  colspan="2" :label="formFields.calcContent.label" required_icon="true">
                    <su-textbox type="textarea" rows="3" rols="10" placeholder="" :data.sync="params.smCalcConfigure.calcContent" name="calcContent"></su-textbox>
                </mk-form-col>
            </mk-form-row>
    </mk-form-panel>

	<mk-panel title="算法配置列表" line="true">
		<div id="calcGrid" class="col-xs-12"></div>
		<div  class="pull-right" style="text-align:right" v-cloak>
		        <su-button s-type="primary" class="btn-width-small" v-on:click="save">保存</su-button>
		        <su-button s-type="default" class="btn-width-small" v-on:click="del">删除</su-button>
		</div>
	</mk-panel>
	</mk-panel>
</su-form>
</div>

<script type="text/javascript">
ProgressUtil.wait();  // 进度条开始
var basePath = '${Config.baseURL}';
var calcEditVue = new Vue({
    el: '#calcEditVue',
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
            	calcParam : "",
            	status : null
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
        me.initGrid();
    },
    methods:{
    	reset:function(){
    		var me = this;
    		me.params.smCalcConfigure = {
        			id : null,
        			calcName:"",//
                	province:"",
                	sellDb:"",//
                	calcCode:"",//
                	calcContent : null,
                	calcParam : "",
                	status : null
        	}
    	},
    	
    	initGrid : function(){
    		ComponentUtil.buildGrid("purchase.archives.smcalcconfigure2",$("#calcGrid"),{ 
                data:[],
                width:"100%",
//                 height:"100%",
//                 method: 'post',
                rownumbers: true,
//                 pagination: true,
                nowrap: false,
                fitColumns:true,
                pageSize: 10,
                pageList: [10, 20, 50, 100, 150, 200],
                rowStyler:function(idx,row){
                    return "height:35px;font-size:12px;";
                },
                onLoadSuccess : function(){
           			var rows =  $("#calcGrid").datagrid("getRows");
           			for(var i in rows){
   						$("#calcGrid").datagrid("beginEdit",i);
   					}
                	
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
    	
    	add: function(){
    		$("#calcGrid").datagrid("appendRow",{a1:"a",c3:"c",d4:"d"});
       		var index = $("#calcGrid").datagrid("getRows").length - 1;
    		$("#calcGrid").datagrid("beginEdit",index);
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
                		calcEditVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		calcEditVue.countyCodeList = data;
                	}
                }
			})
    	},
    	
    	//获取表单信息
        initData:function(id){
        	var me = this;
        	if(id == null || id == ""){
        		return;
        	}
        	if(id == "-1"){
        		$.ajax({
	                url:basePath+"cloud-purchase-web/smCalcConfigureController/"+id,
	                type:"get",
	                success : function(data) {
	                    if(data.flag){
	                    	var node = $("#tree").tree("getSelected");
	        				calcEditVue.params.smCalcConfigure = node.attributes;
	        				if(node.attributes.calcCode != null && node.attributes.calcCode != ""){
	        					calcEditVue.initGridData(node.attributes.calcCode);
	        				}
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
        		
        	}else{
	            $.ajax({
	                url:basePath+"cloud-purchase-web/smCalcConfigureController/"+id,
	                type:"get",
	                success : function(data) {
	                    if(data.flag){
	                    	me.params.smCalcConfigure = data.data;
	                    	calcEditVue.initGridData(calcEditVue.params.smCalcConfigure.calcCode);
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
        	}
        },
        
        //保存销售机会信息
        save:function(){
        	var me = this;
            if(me.params.smCalcConfigure.province == null || me.params.smCalcConfigure.province == ""){
            	MainFrameUtil.alert({
                    title:"提示：",
                    body:"请选择规则后再编辑！",
                });
            	return;
            }
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
                data:$.toJSON(me.params.smCalcConfigure),
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                    if(data.flag){
                        MainFrameUtil.alert({
                             title:"成功提示：",
                             body:data.msg,
                         });
                    	me.params.smCalcConfigure.id = data.data.id;
						var text = me.params.smCalcConfigure.calcName;
						var node = $('#tree').tree('getSelected');
					 	mainVue.updateNode(node,{text:text,id:data.data.id});
					 	var rows = $("#calcGrid").datagrid("getRows");
    					for(var i in rows){
    						$("#calcGrid").datagrid("beginEdit",i);
    					}
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
            var buttons = [{text:"保存",type:"primary",handler:this.save},{text:"关闭",handler:function(){MainFrameUtil.closeDialog("calcEdit")}}];
            return buttons;
        },
        initGridData : function(value){
        	var me = this;
        	$.ajax({
    			url : basePath+"cloud-purchase-web/smCalcConfigureController/getRuleParams",
    			type : "post",
    			data:value,
                contentType : 'application/json;charset=UTF-8',
    			success : function(data){
    				if(data.flag){
    					var rows = data.rows;
    					if(me.params.smCalcConfigure.calcParam != null && me.params.smCalcConfigure.calcParam != ""){
    						var obj = eval('(' + me.params.smCalcConfigure.calcParam + ')');
        					for(var a in obj){
        						for(var b in rows){
        							if(a == rows[b].field){
        								rows[b].value = parseFloat(obj[a]);
        							}
        						}
        					}
    					}
    					var rows1 = [];
    					for(var i in rows){
    						if(rows[i].field.substr(rows[i].field.length-2) != "Pq"){
    							rows1.push(rows[i]);
    						}
    					}
    					$("#calcGrid").datagrid("loadData", rows1);
    				}
    			}
    			
    		});
        },
        
        del:function(){
        	var me = this;
    		MainFrameUtil.confirm({
    	        title:"确认删除吗?",
    	        body:"删除后不可恢复",
    	        onClose:function(type){
    	            if(type=="ok"){//确定
    	            	var selected = $('#tree').tree('getSelected');
    	            	if(me.params.smCalcConfigure.id != null && me.params.smCalcConfigure.id != "" && me.params.smCalcConfigure.id != "-1"){
	    	            	var ids = new Array();
	    	            	ids.push(me.params.smCalcConfigure.id);
	    	            	$.ajax({
	    	                    url:basePath+"cloud-purchase-web/smCalcConfigureController",
	    	                    type:"delete",
	    	                    data:$.toJSON(ids),
	    	                    contentType : 'application/json;charset=UTF-8',
	    	                    success : function(data) {
	    	                        if(data.flag){
	    	                             MainFrameUtil.alert({
	    	                                 title:"成功提示：",
	    	                                 body:data.msg,
	    	                             });
	    	                             mainVue.deleteNode(selected);
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
    	            	}else{
    	            		mainVue.deleteNode(selected);
    	            	}
    	            }else if(type=="cancel"){//取消
    	                
    	            }

    	        }
    	    })
    	},
    	changeflag: function(){
			var me = this;
			if(typeof me.params.smCalcConfigure.calcCode != "undefined" && me.params.smCalcConfigure.calcCode != null && me.params.smCalcConfigure.calcCode != ""){
	    		me.initGridData(me.params.smCalcConfigure.calcCode);
			}else{
				$("#calcGrid").datagrid("loadData", {rows:[]});
			}
			
		}
    },
    
    watch:{
    	"formFields":function(){
			ProgressUtil.close(); //进度条结束
		},
    }
});
</script>
