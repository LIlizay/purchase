<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>人员选择</title>
	<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="userVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	    <mk-search-panel slot="top" deployable="false" line="true">
	        <!-- 人员姓名 -->
	        <su-form-group :label-name="formFields.userName.label" class="form-control-row" col="4" 
	                :class="{'display-none':!formFields.userName.searchHidden}" label-width="100px" label-align="right">
	            <su-textbox :data.sync="userName" ></su-textbox>
	        </su-form-group>
	         
	         <div slot="buttons" class="pull-right " style="text-align:right">
	              <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
	              <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
	        </div>
	    </mk-search-panel>
	    
	    <mk-panel title="人员列表" line="true"  slot="bottom" height="100%">
	       <div id="userGrid" class="col-xs-12" style="height:100%"></div>
	    </mk-panel>
    </mk-top-bottom>
</div>
</body>
</html>
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var userVue = new Vue({
    el: '#userVue',
    data: {
        formFields:{},
       	userName:null,
    },
    ready:function(){
    	//列表数据加载
        ComponentUtil.buildGrid("selling.crm.selectUser",$("#userGrid"),{ 
        	url: basePath + 'cloud-purchase-web/commonController/acuser',
            width:"100%",
            height:"100%",
            method: 'post',
            queryParams:this.userName,
		    singleSelect:true,
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
        //查询字段名称加载
        ComponentUtil.getFormFields("selling.crm.selectUser",this);
    	
		MainFrameUtil.setDialogButtons(this.getButtons(),"mk-power-up-dialog-id");
    },
    
    methods:{
    	reset:function(){
        	//重置
    		this.userName = null;
    	},
    	getDataGrid:function(){
        	//查询列表信息
			$('#userGrid').datagrid('options').queryParams = this.userName;  
		    $("#userGrid").datagrid('load'); 
    	},
        getButtons:function(){
            var buttons=[{
                text:"确定",
                type:"primary",
                handler:function(btn,params){
                	var row = $("#userGrid").datagrid('getSelected');
                	if(row == null){
                		MainFrameUtil.alert({title:"提示",body:"请选择客户！"});
                		return false;
                	}else{
                        params.okHandler(row);
                    	MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                	}
                }
            },{
                text:"取消",
                handler:function(btn,params){
                    MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                }
            }];
            return buttons;
        }
    }
})

</script>