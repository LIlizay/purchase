<%@page import="com.hhwy.framework.configure.ConfigHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head lang="en">
<title>偏差考核预警-表计示数管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
<script src="${Config.baseURL}view/cloud-purchase-web/static/js/datagrid-groupview.js"></script>
</head>
<body>
<div id="meterVue" class="height-fill" v-cloak>
<mk-top-bottom height="100%" top_height="auto">
	<mk-search-panel title="表计示数管理" slot="top" deployable="false">
			
			<!-- 用户名称 -->
	        <su-form-group :label-name="formFields.consName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.consName.searchHidden}" label-width="100px" label-align="right">
<!-- 				<mk-trigger-text :data.sync="params.consName" editable="false" @mk-trigger="selectCons" ></mk-trigger-text> -->
					<su-textbox :data.sync="params.consName"></su-textbox>
	        </su-form-group>
	        
	        <!-- 电压等级-->
	        <su-form-group :label-name="formFields.voltCodeName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.voltCodeName.searchHidden}" label-width="100px" label-align="right">
				 <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_psVoltCode" multiple="false" 
					:select-value.sync="params.voltCode" label-name="name"></su-select>
	        </su-form-group>
			
			
			<!-- 用电类别-->
	        <su-form-group :label-name="formFields.elecTypeCodeName.label" class="form-control-row" col="4" 
	        		:class="{'display-none':!formFields.elecTypeCodeName.searchHidden}" label-width="100px" label-align="right">
				 <su-select url="${Config.baseURL}globalCache/queryCodeByKey/pcode/selling/sell_elecTypeCode" multiple="false" 
					:select-value.sync="params.elecTypeCode" label-name="name"></su-select>
	        </su-form-group>
	        
         <div slot="buttons" class="pull-right " style="text-align:right">
             <su-button s-type="primary" class="btn-width-small" v-on:click="getDataGrid">查询</su-button>
             <su-button s-type="default" class="btn-width-small" v-on:click="reset">重置</su-button>
         </div>
         
    </mk-search-panel>
    
    <mk-panel title="表计示数列表" line="true" slot="bottom" height="100%">
    	<div slot="buttons" class="pull-right " style="text-align:right" v-cloak>
		    <su-button class="btn-operator" s-type="default" labeled="true" label-ico="edit" v-on:click="edit">表计示数维护</su-button>
		 </div>
        <div class="row"  style="height: 100%">
            <table id="meterCountGrid"></table>
        </div>
        
    </mk-panel>
	</mk-top-bottom>
</div>
	
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var meterVue = new Vue({
	el: '#meterVue',
	data: {
		formFields:{},
		uuid:null,
    	params:{
    		consId:'',
    		consName:'',//用户名称
    		voltCode:'',//电压等级
    		elecTypeCode:''//用电类别
    	},
    	tradeurl:basePath + 'view/cloud-purchase-web/static/jsp/trade',
        tradeproptext:''
	},
	ready:function(){
		var me = this;
		ComponentUtil.buildGrid("selling.deviationcheck.metercount",$("#meterCountGrid"),{
	        url: basePath+'scMpCountController/page', 
			method: 'post',
			queryParams:this.params,
			width:"100%",
			height:"100%",
		    rownumbers: true,
		    pagination: true,
		    singleSelect:true,
		    nowrap: false,
		    fitColumns:true,
		    pageSize: 10,
		    pageList: [10, 20, 50, 100, 150, 200],
		    rowStyler:function(idx,row){	
		        return "height:35px;font-size:12px;";
		    },
		    onBeforeLoad:function(){
		    	$("#meterCountGrid").datagrid('options').striped = false;
		    },
		    onLoadSuccess:function(data){
				if(data.rows.length != 0){
					me.combine(data.rows);			//合并单元格
				}
			}
	    });
	    //查询字段名称加载
	    ComponentUtil.getFormFields("selling.deviationcheck.metercount", this);
	},

	methods: {
		//重置
		reset : function(){
			this.params.consName="";
			this.params.voltCode = "";
			this.params.elecTypeCode = "";
			this.params.consId = "";
		},
		
		//合并单元格
		combine: function(rows){
			var j = 0;
			var st = 0;
			var end = 0;
			var nameArr = [];
			for(j; j < rows.length; j++) { //循环行数
				if(j != 0) { //不是第一行
					if(rows[j].consName == rows[j - 1].consName  && rows[j].voltCodeName == rows[j - 1].voltCodeName 
							&& rows[j].elecTypeCodeName == rows[j - 1].elecTypeCodeName && rows.length - 1 != j) { //当前行==上一行 满足合并条件
						if(st == j - 1) { // 开始行index  == 上一行index 
							st = j - 1; // 开始行index= 上一行index
						}

					} else { //不满足合共条件
						end = j - 1; //结束行index = 上一行index
						if(j == rows.length - 1 && rows[j].consName == rows[j - 1].consName && rows[j].voltCodeName == rows[j - 1].voltCodeName &&
								rows[j].elecTypeCodeName == rows[j - 1].elecTypeCodeName) { // 最后一行 且 与上一行 满足合并条件
							
							end = j;
							nameArr.push({
								"index": st,
								"rowspan": end - st + 1,
								"field": "consName"
							}, {
								"index": st,
								"rowspan": end - st + 1,
								"field": "voltCodeName"
							}, {
								"index": st,
								"rowspan": end - st + 1,
								"field": "elecTypeCodeName"
							});
						} else if(st != end) { //开始index ！= 结束index
							nameArr.push({
								"index": st,
								"rowspan": end - st + 1,
								"field": "consName"
							}, {
								"index": st,
								"rowspan": end - st + 1,
								"field": "voltCodeName"
							}, {
								"index": st,
								"rowspan": end - st + 1,
								"field": "elecTypeCodeName"
							});
						}
						st = j; // 不满足条件 开始index = 当前行index
					}
				}
			}
			var i = 0;
			for(i; i < nameArr.length; i++) {
				$("#meterCountGrid").datagrid("mergeCells", {
					index: nameArr[i].index,
					field: nameArr[i].field,
					rowspan: nameArr[i].rowspan
				});
			}
		},	
		edit:function(){
			var me = this;
			var row = $("#meterCountGrid").datagrid("getSelected");
			if(row==null){
				MainFrameUtil.alert({title:"警告",body:"请选择一条数据！"});
				return;
			}
			if(row.meterNo == null || row.meterNo == "" || row.meterDigits == "" || row.meterDigits==null){
				//MainFrameUtil.alert({title:"警告",body:"请先在“用户档案”中维护计量点信息！"});
				if(!me.uuid){
					me.uuid=Math.random();
				}
				 MainFrameUtil.customAlert({
			        title:"警告",
			        body:"请先在“用户档案”中维护计量点信息!",
			        buttons:[{text:'完善计量点信息',type:"primary",style:"primary"},{text:'取消',type:"default",style:"default"}],
			        onClose:function(type){
			        	if(type=="primary"){//完善计量点信息
			        		MainFrameUtil.openTabPage(me.uuid, basePath+"view/cloud-purchase-web/archives/scconsumerinfo/list", "用户档案",false);//打开用户档案页签
			            }
			        }
			    });
				return;
			}
			MainFrameUtil.openDialog({
	  			id:'metercountEdit',
	  			params:{id:row.id},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/metercount/edit',
				title:'表计示数管理',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:620,
				onClose:function(){
					me.getDataGrid();
				},
				buttons:[{
	                    text:"保存",
	                    type:"primary",
	                    handler:function(btn,params){
	                    	params.confirmHandler(btn.target);
	                    }
	                },{
	                    text:"取消",
	                    handler:function(btn,params){
	                    	MainFrameUtil.closeDialog('metercountEdit');
	                    }
	                }]
			});
		},
		//查询
		getDataGrid:function(){
			$("#meterCountGrid").datagrid("reload");
		},
		//查看详情
		detailRender:function(value,row,index){
			return "<a onclick=\"meterVue.detail('"+row.id+"')\">"+value+"</a>";
		},
		detail:function(id){
			MainFrameUtil.openDialog({
	  			id:'detail',
	  			params:{id: id},
				href:'${Config.baseURL}view/cloud-purchase-web/deviationcheck/metercount/detail',
				title:'表计示数信息查看',
				iframe:true,
				modal:true,
				width:1000,
				maximizable:true,
				height:620,
				buttons:[{
	                    text:"关闭",
	                    handler:function(btn,params){
	                    	
	                    	MainFrameUtil.closeDialog('detail');
	                    }
	                }]
			});
		}
	}
}); 
</script>
</body>
</html>