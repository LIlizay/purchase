<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <title>售电管理-售电合同管理补充协议详情</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="smAgreSup_listVue" v-cloak>
<mk-panel title="补充协议列表" line="true" height="530px" >
     <div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add" resourceCode="cloud_selling_01030202agreement">新增协议</su-button>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit" resourceCode="cloud_selling_01030202agreement">编辑协议</su-button>
         <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del" resourceCode="cloud_selling_01030202agreement">删除协议</su-button>
     </div>
     <div id="smAgreSupGrid" class="col-xs-12" height="460px"></div>
</mk-panel>
</div>

  
</body>
<script type="text/javascript">
 var basePath="${Config.baseURL}";
var smAgreSup_listVue = new Vue({
    el: '#smAgreSup_listVue',
    data: {
  	  	formFields:{},
  	  	params:{
  	  		smAgreSup:{
  	  	        ppaId:null,
  	  	        sellParty:null, //售电方签订人
                custParty:null, //客户签订人
  	  		}
  	  	}
    },
    ready:function(){
    	//按钮权限 resourceCode
    	$.checkButtonAuthority(this);
    	
    	this.params.smAgreSup.ppaId = MainFrameUtil.getParams("sup-list").agreId;
    	this.params.smAgreSup.custParty = MainFrameUtil.getParams("sup-list").partyA;
        this.params.smAgreSup.sellParty = MainFrameUtil.getParams("sup-list").partyB;
  	   //初始化表格
  	   ComponentUtil.buildGrid("selling.agreement.smagresup",$("#smAgreSupGrid"),{ 
  		  	  url : basePath+'cloud-purchase-web/smAgreSupController/page',
  		      method:'post',
  		      queryParams:{smAgreSup:{ppaId:this.params.smAgreSup.ppaId}},
  		      width:"100%",
  		      rownumbers: true,
  		      pagination: true,
  		      nowrap: false,
              fitColumns:true,
              pageSize: 10,
              pageList: [10, 20, 50, 100, 150, 200],
              rowStyler:function(idx,row){
                return "height:35px;font-size:12px;";
              }
  	   });
  	  //设置按钮组
       MainFrameUtil.setDialogButtons(this.getButtons(),"sup-list");
    },
    methods: {
    	//初始化按钮
    	getButtons:function(){
            var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("sup-list");}}];
            return buttons;
        },
        
    	//点击协议名称查看详细信息
        agreNameRender :function(value,row,index){
    		return "<a onclick=\"smAgreSup_listVue.getSmAgreSup('"+row.id+"')\">"+row.agreName+"</a>"
    	},
        
	    //详细信息弹出框
	    getSmAgreSup:function(id){
	    	MainFrameUtil.openDialog({
                id:'supplementary-detail',
                params:{"id":id},
                href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/supplementary-detail',
                modal:true,
                iframe:true,
                width:"80%",
                height:620,
           })
	    },
	    
	    //新增协议
	    add:function(){ 
	    	var cont = this.params.smAgreSup;
	    	MainFrameUtil.openDialog({
                id:"sup-add",
                params:{agreId:cont.ppaId,partyA:cont.sellParty,partyB:cont.custParty},
                href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/supplementary-add',
                iframe:true,
                modal:true,
                width:"80%",
                height:620,
                onClose:function(){
                    $("#smAgreSupGrid").datagrid('reload');
                }
            })
	    	
	    },
	    
	    //编辑补充协议
	    edit:function(){
	    	var rows = $("#smAgreSupGrid").datagrid("getSelections");
            if(rows.length === 0 ){
                MainFrameUtil.alert({title:"提示",body:"请选择补充协议对象"}); 
                return;
            }
            if(rows.length > 1){
                MainFrameUtil.alert({title:"提示",body:"请选择补充协议对象"}); 
                return;
            }
            MainFrameUtil.openDialog({
                id:"supplementary-edit",
                params:{"id":rows[0].id},
                href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/supplementary-edit',
                iframe:true,
                modal:true,
                width:"80%",
                height:620,
                onClose:function(){
                    $("#smAgreSupGrid").datagrid('reload');
                }
            })
	    },
	    
	    //删除补充协议
	    del:function(){
	    	var rows = $('#smAgreSupGrid').datagrid('getSelections');
            if(rows.length<1){
                MainFrameUtil.alert({title:"警告",body:"请选择数据！"});
                return;
            }
            MainFrameUtil.confirm({
                title:"确认",
                body:"该操作不能恢复，确定要删除选中的记录吗？",
                onClose:function(type){
                    if(type=="ok"){//确定
                        var ids = new Array();
                        for(var obj in rows){
                            ids.push(rows[obj].id);
                        }
                        $.ajax({
                            url:basePath + 'cloud-purchase-web/smAgreSupController',
                            type:"delete",
                            data:$.toJSON(ids),
                            contentType : 'application/json;charset=UTF-8',
                            success:function(data){
                                if(data.flag){
                                    MainFrameUtil.alert({title:"提示",body:data.msg});
                                    $("#smAgreSupGrid").datagrid('reload');
                                }else{
                                    MainFrameUtil.alert({title:"错误",body:data.msg});
                                } 
                            },
                            error:function(data){
                                MainFrameUtil.alert({title:"错误",body:"删除失败！"}); 
                            }
                        })
                    }
                }
            })
	    }
    }
}); 
</script>
</html>
