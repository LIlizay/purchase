<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>偏差考核预警-用户用电计划新增</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>	
<jsp:include page="/plugins/business-core/static/headers/upload.jsp"></jsp:include>
<style type="text/css">
	.datagrid-row-selected{
		background-color: #eeeeee;
		color:#000000;
	}
</style>
</head>
<body id="viewAddVue" v-cloak>
	<su-form v-ref:form1 id="fms1" offpos-style="true" :data-option="dataOption" :set-defaults="setDefaults" :data-validator.sync="valid" >
		<mk-panel title="用户用电计划新增" line="true" >
	    	<mk-form-panel label-width="140px" num="3">
	    		<mk-form-row >
	    			<!-- 用电年月 -->
		    		<mk-form-col label="用电年月" required_icon="true">
		    			<su-datepicker format="YYYY-MM" :data.sync="params.ym" name="ym"></su-datepicker>
		    		</mk-form-col>
		    	</mk-form-row>
	    	</mk-form-panel>
	    </mk-panel>
	    <mk-panel title="合同用户列表" line="true">
		    <div id="consGrid" class="col-xs-12"></div>
		</mk-panel>
	</su-form>
	<script type="text/javascript">
		var basePath = '${Config.baseURL}';
		//对应 js
		var viewAddVue =new Vue({
			el: '#viewAddVue',
			data: {	
				url : "",
				consFlag : false,
				formFields:{},
				fileName:"",
				editFlag : false,	//是否是编辑数据
				consInfo:null,
				ruleId:null,
				ruleName:null,
			    params:{
			    	ym : null
			    },
			   
				//非空验证规则
		        dataOption: {
		            rules: {	
		            	ym:{required: !0}
		            }
		        },
		        valid:false
			},
			ready:function(){
				var me = this;
				//查询字段名称加载
// 				ComponentUtil.getFormFields("selling.deviationcheck.smconsearlywarning",me);
				MainFrameUtil.setDialogButtons(me.getButtons(),"viewadd");
				me.initConsGrid();
				me.params.ym = me.initYm();
			},
			methods: {
				
				//初始化年月
				initYm : function(){
					var date = new Date();
					var month = date.getMonth()+1;
					if(month < 10){
						month = "0" + month;
					}
					return date.getFullYear() + "-" + month;
				},
				
				initConsGrid:function(){
					var me = this;
					$("#consGrid").datagrid({
						url: me.url, 
						method: 'post',
						queryParams:this.params,
						width:"100%",
						striped:true,
						fitColumns:false,
						pagination: true,
						pageSize: 20,
					    pageList: [20, 50, 100, 150, 200],
// 						singleSelect:true,
						rownumbers:true,
						idField:'consId',
						columns:[[
						        {field:'consId',hidden:true,align:'left'},
						        {field:'ck',checkbox:true},
 								{field:'consName',title:'用户名称',width:'24%',align:'left'},
								{field:'voltCodeName',title:'电压等级',width:'20%',align:'center'},
 								{field:'elecTypeCodeName',title:'用电类别',width:'20%',align:'center'},
 								{field:'electricCapacity',title:'用电容量',width:'20%',align:'center'},
 								{field:'agrePq',title:'合同电量',width:'15%',align:'center'},
						]]
					});
				},
				
				save : function(){
					var me = this;
		        	this.$refs.form1.valid();
		            if(this.valid===false){
		                return false;
		            }
		            var rows = $("#consGrid").datagrid("getChecked");
		            if(rows.length == null || rows.length == 0){
		            	MainFrameUtil.alert({
                            title : "提示：",
                            body : "请选择要新增收集记录的用户！",
                        });
		            	return;
		            }
		            var consIds = [];
		            var agrePqs = [];
		            for(var i in rows){
		            	consIds.push(rows[i].consId);
		            	agrePqs.push(rows[i].agrePq == "" ? null : rows[i].agrePq);
		            }
		            var param = {
		            		ym : me.params.ym.replace("-",""),
		            		consIds : consIds,
		            		agrePqs : agrePqs
		            }
		            $.messager.progress({title:"请等待",msg:"正在保存...",interval:100});
		            $.ajax({
		            	url : basePath+'smConsPowerViewController/save',
		            	type : "post",
		            	data : $.toJSON(param),
		            	contentType : 'application/json;charset=UTF-8',
		            	success : function(data){
		            		$.messager.progress('close');
		            		if(data.flag){
		                		 MainFrameUtil.alert({
		                             title:"成功提示：",
		                             body:data.msg,
		                         });
		                		 MainFrameUtil.closeDialog("viewadd");
		                	}else{
		                		MainFrameUtil.alert({
		                            title:"失败提示：",
		                            body:data.msg,
		                        });
		                	}
		            	}
		            	
		            });
				},
				
				//按钮组
		        getButtons:function(){
		        	var me = this;
		        	var buttons = [{text:"保存",type:"primary",handler:me.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("viewadd")}}];
		            return buttons;
		        }
			},
			watch:{
				
				"params.ym" : function(){
					var me = this;
					if(me.params.ym != null && me.params.ym != ""){
						$("#consGrid").datagrid("options").url = basePath+'smConsPowerViewController/getConsList';
						$("#consGrid").datagrid("reload");
					}
				}
				
			} 
		});
	</script>
</body>
</html>