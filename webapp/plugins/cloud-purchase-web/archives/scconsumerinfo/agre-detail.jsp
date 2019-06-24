<html>
	<head>
	<title>暂弃档案管理-用户档案信息合同信息</title>
	</head>
	<body>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <div id="ppaVue" class="height-fill" v-cloak>
	<mk-top-bottom height="100%" top_height="auto">	    
		<mk-search-panel title="购电合同查询" deployable="false" slot="top">
		        <!-- 合同名称 -->
		        <su-form-group :label-name="formFields.agreName.label" class="form-control-row" 
		        		:class="{'display-none':!formFields.agreName.searchHidden}" col="4" label-width="120px" label-align="right">
		            <su-textbox :data.sync="params.smPpa.agreName"></su-textbox>
		        </su-form-group>  
		        <div class="pull-right" style="text-align:right">
	       			<su-button s-type="primary"  @click="getDataGrid" class="btn-width-small">查询</su-button>
	  			</div>
	   	</mk-search-panel>
		<mk-panel title="售电合同列表" line="true" slot="bottom" height="100%">
	        <div id="ppaGrid"></div>
		</mk-panel>
	</mk-top-bottom>
</div>
<script type="text/javascript">
	var basePath="${Config.baseURL}";
	var ppaVue = new Vue({
	    el: '#ppaVue',
	    data: {
	  	  	formFields:{},
	  	  	params:{smPpa:{agreNo:null,agreName:null,agreType:null,agreStatus:null,effectiveDate:null,expiryDate:null},
	  	  			consName:null,consNo:null,custName:null},		//,orgId:null
	    },
	    ready:function(){
	      this.params.consName = MainFrameUtil.getParams("cons-detail").consName;
	    	//初始化表单
	      ComponentUtil.getFormFields("selling.agreement.smppa",this);
	  	   //初始化表格
	  	  ComponentUtil.buildGrid("selling.agreement.smppa",$("#ppaGrid"),{ 
	  		  	  url : basePath+'cloud-purchase-web/smPpaController/page',
	  		      method:'post',
				  queryParams:this.params,
	  		      rownumbers: true,
	  		      pagination: true,
	  		      nowrap: false,
	  		      pageSize: 10,
	  		      pageList: [10, 20, 50, 100, 150, 200],
	  		      fitColumns:true,
	  		      fit:true,
	  		      scrollbarSize:0,
	  		      rowStyler:function(idx,row){
	  		          return "height:35px;font-size:12px;";
	  		      }
	  		  });
	    },
	    methods: {
			getDataGrid:function(){
				$('#ppaGrid').datagrid('options').queryParams = this.params;  
			    $("#ppaGrid").datagrid('load'); 
			},
			agreNoRender:function(value,row,text){
				if(value){
					return "<a onclick=\"ppaVue.detail('"+row.id+"')\">"+value+"</a>";
				}
			},
			detail:function(id){
				//更新页面
		  		MainFrameUtil.openDialog({
		  			id:"ppa-detail",
		  			params:{id:id},
					href:'${Config.baseURL}view/cloud-purchase-web/agreement/smppa/detail',
					iframe:true,
					modal:true,
					width:"80%",
					height:620
				});
			}
	    }
	}); 
</script>
</body>
</html>