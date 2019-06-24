<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
<title>售电公司审核详情</title>
</head>
<body id="detailBody">
<mk-form-panel title="">
	<mk-form-row>
		<!-- 用户名称 -->
        <mk-form-col :label="formFields.userName.label" colspan="3">
        	<mk-trigger-text  :data.sync="consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text>
        </mk-form-col>
	</mk-form-row>
<mk-form-panel title="">
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el:"#detailBody",
	data:{
		formFields:{},
		loginName:'',
		consName:'',
		consId:''
	},
	ready:function(){
		ComponentUtil.getFormFields("selling.login.swconsinfo",this);
		this.loginName =  MainFrameUtil.getParams('add').loginName;
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'add');
	},
	methods:{
		selectCons:function(){
			var me = this;
 			MainFrameUtil.openDialog({
	  			id:'consDialog',
				href:'${Config.baseURL}view/cloud-purchase-web/archives/scconsumerinfo/cons-dialog',
				iframe:true,
				modal:true,
				width:'50%',
				height:620,
				onClose:function(params){
					if(typeof(params[0])==="object"){
						me.consId = params[0].id;
						me.consName = params[0].consName;
					}
				}
			});
		},
		save:function(backFun){
			if(this.consId==""){
				MainFrameUtil.alert({title:"警告",body:"请选择用户！"});
				return;
			}
			$.ajax({
				url : basePath + "cloud-purchase-web/swConsInfoController/binding",
   	   			type : 'post',
   	   			data : $.toJSON({loginName:this.loginName,consId:this.consId}),
   	   			contentType : 'application/json;charset=UTF-8',
   	   			success : function(data){
   	   				if(data.flag){
   	   					MainFrameUtil.alert({ title:"成功提示", body:data.msg }); 
   	   					backFun();
   	   				}else{
   	   					MainFrameUtil.alert({ title:"失败提示", body:data.msg }); 
   	   				}
   	   			}
   	   		});
		}
	}
})
</script>
</body>
</html>