<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
   <title>合同管理-售电合同管理补充协议</title>
  <jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
  <jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
</head>
<body>
<div id="smAgreSup_listVue" class="height-fill" v-cloak>
	<mk-panel title="补充协议列表" line="true" height="500px" >
        <div id="smAgreSupGrid"></div>
	</mk-panel>
</div>
</body>
<script type="text/javascript">
 var basePath="${Config.baseURL}";
var smAgreSup_listVue = new Vue({
    el: '#smAgreSup_listVue',
    data: {
  	  	formFields:{},
  	  	params:{smAgreSup:{ppaId:null}}
    },
    ready:function(){
    	this.params.smAgreSup.ppaId = MainFrameUtil.getParams("sup-list").agreId;
    	
  	   	//初始化表格
		ComponentUtil.buildGrid("selling.agreement.smagresup",$("#smAgreSupGrid"),{ 
  		  	  url : basePath+'cloud-purchase-web/smAgreSupController/page',
  		      method:'post',
  		      queryParams:this.params,
  		      fitColumns:true,
  		      rownumbers:true,
	    	  singleSelect:true,
  			  pagination:true,
  		      fit:true,
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
    	//点击协议编号查看详细信息
    	messageByAgreNo :function(value,row,index){
    		return "<a onclick=\"smAgreSup_listVue.getSmAgreSup('"+row.id+"')\">"+row.agreNo+"</a>"
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
	    
	    getButtons:function(){
            //初始化按钮
            var buttons = [{text:"关闭 ",handler:function(){MainFrameUtil.closeDialog("sup-list");}}];
            return buttons;
        },
    }
}); 
</script>
</html>
