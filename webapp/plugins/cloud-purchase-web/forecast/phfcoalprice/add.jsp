<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>参数维护-煤炭价格维护新增</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@include file="/plugins/business-core/static/headers/base.jsp"%>
</head>
<body id="addBody" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<div class="row row-margin-bottom" slot="top" >
		<mk-form-panel hearder="false" >
			<mk-form-row>
				<!-- 年月 -->
		        <mk-form-col :label="formFields.ym.label" colspan="3" required_icon="true">
		        	<su-datepicker :data.sync="ym" format="YYYYMM" ></su-datepicker>
		        </mk-form-col>
	    	</mk-form-row>
		</mk-form-panel>
	</div>
	<div class="row height-fill"  slot="bottom" >
		<mk-panel title="煤炭价格" height="100%" line="true">
		<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="plus" v-on:click="add">新增</su-button>
	        <su-button class="btn-operator" s-type="default" labeled="true" label-ico="trash-o" v-on:click="del">删除</su-button>
	    </div>
		    <div id="addGrid" ></div>
		</mk-panel>
	</div>
</mk-top-bottom>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var addVue = new Vue({
	el: '#addBody',
	data: {
		formFields:{},
		ym:''
	},
	ready:function(){
		var methods={"save":this.save};
        MainFrameUtil.setParams(methods,'add');
		//列表数据加载
		$("#addGrid").datagrid({ 
			columns:[[
			        {title:'ck',field:'ck',checkbox:true,width:80},
					{title:'种类',field:'coalType',width:80,align:'center',editor:{
					    type:'combobox',
					    options:{
					   	 editable:false,
					   	 url:basePath+'globalCache/queryCodeByKey/pcode/selling/sell_coalTypeCode',
					        method:'get',
					        valueField: "value",
					        textField: "name",
					        panelHeight:'100px',
					        required:true
					    }
					}},
					{title:'价格 （元/吨）',field:'price',width:80,align:'center',editor:{type:'numberbox',
						options:{
							precision:4,min:0,
							required:true
	                		}
						}},
			         ]],
		    height:265,
		    method: 'post',
		    rownumbers: false,
		    pagination: true,
		    nowrap: false,
		    fitColumns:true,
		    rowStyler:function(idx,row){
		        return "height:35px;font-size:12px;";
		    }
	    }); 
		//查询字段名称加载
		ComponentUtil.getFormFields("purchase.forecast.coalprice",this);
	},
	methods: {
		save:function(backFun){
	  		if(this.ym===''){
	  			MainFrameUtil.alert({title:"警告",body:"请选择年月！"});
	  			return;
	  		}
	  		var rows = $('#addGrid').datagrid('getRows');
	  		if(rows.length==0){
	  			MainFrameUtil.alert({title:"提示",body:"请填写煤炭信息"});
	  			return;
	  		}
	  		var tmp = [];
	  		//校验数据
	  		for(var i in rows){
	  			var flag = $('#addGrid').datagrid('validateRow',i);
	  			if(flag == false){
	  				MainFrameUtil.alert({title:"警告",body:"请完善第"+(parseInt(i)+1)+"行数据"});
	  				return;
	  			}
	  			$('#addGrid').datagrid('endEdit',i);
	  			$('#addGrid').datagrid('beginEdit',i);
	  			//校验重复
	  			if(tmp[rows[i].coalType]){
	  				MainFrameUtil.alert({title:"警告",body:"煤炭种类重复！"});
	  				return;
	  			}else{
	  				tmp[rows[i].coalType] = true;
	  			}
	  			rows[i].ym = this.ym;
	  		}
	  		$.ajax({
	  			url:basePath+"cloud-purchase-web/phfCoalPriceController",
	  			type:"post",
	  			data:$.toJSON({ym:this.ym,list:rows}),
        		contentType : 'application/json;charset=UTF-8',
				success:function(data){
					if(data.flag){
						MainFrameUtil.alert({title:"提示",body:data.msg,onClose:function(){
							backFun();
						}});
					}else{
						MainFrameUtil.alert({title:"错误",body:data.msg});
					}
				}
	  		})
		},
		add:function(){
			$('#addGrid').datagrid('appendRow',{});
			var index = $('#addGrid').datagrid('getRows').length-1;
			$('#addGrid').datagrid('beginEdit',index);
		},
		del:function(){
			var rows = $('#addGrid').datagrid('getChecked');
			if(rows.length>0){
				 MainFrameUtil.confirm({
				        title:"确认",
				        body:"删除后不可恢复，确认删除选中行？",
				        onClose:function(type){
				            if(type=="ok"){//确定
				            	while(rows.length>0){
				            		var index =  $('#addGrid').datagrid('getRowIndex',rows[0]);
				            		$('#addGrid').datagrid('deleteRow',index);
				            		rows.splice(0,1);
				            	}
				            }
				        }
				    });  
			}else{
				MainFrameUtil.alert({title:"提示",body:"请至少选择一条数据！"});
			}
		}
	}
}); 
</script>
</body>
</html>