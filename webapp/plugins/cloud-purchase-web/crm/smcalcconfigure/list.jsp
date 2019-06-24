<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>算法配置维护</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="calcVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
    <mk-search-panel title="算法配置维护" slot="top" deployable="false" label-width="120px">
        <mk-search-visible>
            <!-- 算法名称 -->
            <su-form-group :label-name="formFields.calcName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.calcName.searchHidden}" label-width="120px" label-align="right">
                <su-textbox :data.sync="params.smCalcConfigure.calcName" ></su-textbox>
            </su-form-group>
             <!-- 省-->
            <su-form-group :label-name="formFields.provinceName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.provinceName.searchHidden}" label-width="120px" label-align="right">
                <su-select placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.province" :high="150"
						url="${Config.baseURL}globalCache/queryProvinceList"></su-select>
            </su-form-group> 
            <!-- 售电公司(库名)-->
            <su-form-group :label-name="formFields.sellDbName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.sellDbName.searchHidden}" label-width="120px" label-align="right">
                <su-select search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.sellDb" url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getSellDb"></su-select>
            </su-form-group>
            <!-- 脚本选择(编码)-->
            <su-form-group :label-name="formFields.calcCodeName.label" class="form-control-row "  col="4" 
                    :class="{'display-none':!formFields.calcCodeName.searchHidden}" label-width="120px" label-align="right">
                <su-select search="false" placeholder="--请选择--" label-name="name" :select-value.sync="params.smCalcConfigure.calcCode"
                     url="${Config.baseURL}cloud-purchase-web/smCalcConfigureController/getRuleValue"></su-select>
            </su-form-group>
        </mk-search-visible>
            
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
    </mk-search-panel>
    
    <mk-panel title="算法配置列表" line="true" slot="bottom" height="100%">
        <div slot="buttons" class="pull-right" style="text-align:right">
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">编辑</su-button>
            <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
        </div>
        <div id="calcGrid" class="col-xs-12" style="height:100%"></div>
    </mk-panel>
    </mk-top-bottom>
</div>
</body>
</html>

<script type="text/javascript">
ProgressUtil.wait();  // 进度条开始
var basePath = '${Config.baseURL}';
var calcVue = new Vue({
    el: '#calcVue',
    data: {
    	formFields:{},
    	sellDbList:[{value:"01",name:"库名测试1"},{value:"02",name:"库名测试2"}],//
    	calcCodeList:[{value:"01",name:"编码测试1"},{value:"02",name:"编码测试2"}],//
    	params:{
            smCalcConfigure:{
            	calcName:"",//
            	province:"",
            	sellDb:"",//
            	calcCode:"",//
            }
        }
    },
    ready:function(){
    	//加载表单信息
        ComponentUtil.getFormFields("purchase.archives.smcalcconfigure",this);
    	//加载列表信息
        ComponentUtil.buildGrid("purchase.archives.smcalcconfigure",$("#calcGrid"),{ 
            url: basePath + 'cloud-purchase-web/smCalcConfigureController/page',
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
                		calcVue.cityCodeList = data;
                	}else if(type == "countyCode"){
                		calcVue.countyCodeList = data;
                	}
                }
			})
    	},
    	
    	//新增销售机会管理
    	add : function(){
    		 MainFrameUtil.openDialog({ 
    			 id:"calcAdd",
                 href:'${Config.baseURL}view/cloud-purchase-web/crm/smcalcconfigure/add',
                 iframe:true,
                 modal:true,
                 maximizable:true,
                 width:"80%",
                 height:620,
                 onClose:function(){
                     $("#calcGrid").datagrid('load');
                 }
             });
    	},
    	
    	//编辑销售机会管理
    	edit : function(){
    		var rows = $('#calcGrid').datagrid("getSelections");
    		if(rows.length == 0){
    			 MainFrameUtil.alert({
                     title:"提示信息：",
                     body:"请选择编辑对象",
                 });
                 return;
    		}else if(rows.length >1){
    			 MainFrameUtil.alert({
                     title:"提示信息：",
                     body:"只能选择一个编辑对象",
                 });
                 return;
    		}
    		MainFrameUtil.openDialog({ 
    			id:"calcEdit",
    			params:{id:rows[0].id},
                href:'${Config.baseURL}view/cloud-purchase-web/crm/smcalcconfigure/edit',
                iframe:true,
                modal:true,
                maximizable:true,
                width:"80%",
                height:620,
                onClose:function(){
                    $("#calcGrid").datagrid('reload');
                }
            })
    	},
    	
    	//批量删除
    	del:function(){
    		var rows = $('#calcGrid').datagrid("getSelections");
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
    	                             $("#calcGrid").datagrid('reload');
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
    	calcNameRender : function(value,row,index){
            return "<a onclick=\"calcVue.view('"+row.id+"')\">"+row.calcName+"</a>";
        },
      	//点击销售机会名称查看详细信息
        view:function(id){
            MainFrameUtil.openDialog({
            	id:"calcDetail",
                params:{"id":id},
                href:'${Config.baseURL}view/cloud-purchase-web/crm/smcalcconfigure/detail',
                modal:true,
                iframe:true,
                maximizable:true,
                width:"80%",
                height:620,
                onClose:function(){
                    $("#calcGrid").datagrid('reload');
                }
           })
        },
    	
    	//查询
    	getDataGrid:function(){
    		$('#calcGrid').datagrid("load");
    	},
    	
    	//重置
    	reset:function(){
    		this.params.smCalcConfigure = {
   				calcName:"",//
               	province:"",
               	sellDb:"",//
               	calcCode:""//
        	}
    	}
    },
    
    watch:{
    	"formFields":function(){
			ProgressUtil.close(); //进度条结束
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