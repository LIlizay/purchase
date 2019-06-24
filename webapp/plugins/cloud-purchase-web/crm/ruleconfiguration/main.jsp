<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="/plugins/business-core/static/headers/base.jsp"%>
<title>管理员工具-交易规则配置</title> 
</head>
<body onkeydown="mainVue.keydown(event);">
<mk-left-right  left_width="250px" height="100%">
	<mk-panel title="" slot="left"  header="false" height="99%">
		<div id="tree" style="margin: 2% auto 2% auto;height:100%;overflow-y: auto;"></div>
		<!-- 省份 -->
		<div id="province" class="easyui-menu" style="width:140px;">
		    <div onclick="mainVue.addRule()" >添加规则</div>
		</div>
		<!-- 受电点 -->
		<div id="rule" class="easyui-menu" style="width:140px;">
		    <div onclick="mainVue.effect()">生效</div>
		    <div onclick="mainVue.invalid()">失效</div>
		</div>
	</mk-panel>
	<mk-panel title="" slot="right" header="false" height="99%">
		<div style="height:100%;overflow: auto">
		 <mk-stackpanel :selected.sync="selectedPage" height="100%">
		 	<mk-stackpage index=0 header="规则配置" href="${Config.baseURL}view/cloud-purchase-web/crm/smcalcconfigure/main-edit" iframe="false" ></mk-stackpage>
		 	<mk-stackpage index=1 header="目录电价" :href="catapriceUrl" iframe="true" ></mk-stackpage>
		 	<mk-stackpage index=2 header="输配电价" :href="transdistprcUrl" iframe="true" ></mk-stackpage>
		 	<mk-stackpage index=3 header="电价管理" height="900px" :href="managePrUrl" iframe="false" ></mk-stackpage>
		 	
         </mk-stackpanel>
        </div>
	</mk-panel>
</mk-left-right>
            
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var mainVue = new Vue({
	el: 'body',
	data:{
		 selectFlag : false,
		 ruleId : null,
		 formFields:{},
		 catapriceUrl:"",
		 transdistprcUrl:"",
		 managePrUrl:"",
		 catBaseUrl:"${Config.baseURL}view/business-common/accounting/cataprice/catalogPric?tableCode=02&orgNo=",//目录电价路径
		 traBaseUrl:"${Config.baseURL}view/business-common/accounting/transdistprc/prcManage?tableCode=02&orgNo=",//输配电价路径
		 managePriceUrl:"${Config.baseURL}view/cloud-purchase-web/delivery/price/list?tableCode=02&provinceId=",
		 smRuleConfigure:{pdevPrc:""},
		 selectedPage:-1,
		 name:"",
		 provinceId:"",
		 ruleStatus:"01",
	},
	events : {
    	pageLoaded : function(obj){
    		var node = $("#tree").tree("getSelected");
    		var nodeType = node.nodeType;
    		//规则
    		if(nodeType==="rule"){
    			mainVue.showRule(node);
    		//目录电价
    		}else if(nodeType==="catePrc"){
    			var obj = $('#tree').tree('getParent',node.target);
    			mainVue.catapriceUrl = mainVue.catapriceUrl + obj.provinceId;
    			//addNewEditVue.sellingMeBillParaVer.id = node.id;
    		//输配电价
    		}else if(nodeType==="transPrc"){
    			var obj = $('#tree').tree('getParent',node.target);
    			mainVue.catapriceUrl = mainVue.catapriceUrl + obj.provinceId;
    			//detailVerVue.id = node.id;
    		}else if(nodeType==="managePrice"){
    			var obj = $('#tree').tree('getParent',node.target);
    			mainVue.managePrUrl = mainVue.managePriceUrl + obj.provinceId;
    			//detailVerVue.id = node.id;
    		}
    	}
    },
	ready:function(){
		$("#tree").tree({
			//data:[{text:"省份",state:"closed",nodeType:"root"},],
			url:basePath + "smCalcConfigureController/getTree?nodeType=root&provinceId=",
			animate:true,
			method:"get",
			onContextMenu : function(e,node){
                e.preventDefault();
                // 选择节点
                $('#tree').tree('select',node.target);
                var nodeType = node.nodeType;
                if(nodeType==="province"){
                	$('#province').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }else if(nodeType==="rule"){
                	$('#rule').menu('show', {
                        left: e.pageX,
                        top: e.pageY
                    });
                }
            },
            onExpand:function(node){
            	if(node.nodeType==="province"){
            		var childrens = $('#tree').tree('getChildren',node.target);
            		for(var node in childrens){
            			if(childrens[node].ruleStatus==="02"){
            				$(childrens[node].target).find(".tree-title").css("color","red");
            			}
            		}
            	}
            },
            onBeforeSelect:function(){
            	var node = $('#tree').tree('getSelected');
    		 	if(node&&node.nodeType==="rule"){
//     				$('#tree').tree('update', {
//     					target: node.target,
//     					attributes:null
//     				});
					calcEditVue.praramsToJson();
    			}
            },
            onBeforeExpand:function(node){
            	var nodeType = node.nodeType;
            	var provinceId = node.provinceId;
            	$('#tree').tree('options').url = basePath + "smCalcConfigureController/getTree?nodeType="+nodeType+"&provinceId="+provinceId;
            },
            onSelect:function(node){
            	if(node.nodeType === "rule"){
            		mainVue.selectedPage = 0;
            		if(typeof calcEditVue === "undefined"){
            			return;
            		}
            		mainVue.showRule(node);
            		
            		if(mainVue.selectFlag){
            			mainVue.selectFlag = false;
//             			$('#tree').tree('select', node.target);
            			mainVue.showRule(node);
            		}
            		
                //目录电价
    			}else if(node.nodeType==="catePrc"){
    				mainVue.selectedPage = 1;
    				mainVue.selectFlag = true;
    				var obj = $('#tree').tree('getParent',node.target);
    				mainVue.catapriceUrl = mainVue.catBaseUrl + obj.provinceId;
    				/* if(typeof addNewEditVue === "undefined"){
            			return;
            		}
    				addNewEditVue.getData(node.id); */
        		//输配电价
        		}else if(node.nodeType==="transPrc"){
        			mainVue.selectedPage = 2;
        			mainVue.selectFlag = true;
        			var obj = $('#tree').tree('getParent',node.target);
        			mainVue.transdistprcUrl = mainVue.traBaseUrl + obj.provinceId;
        			/* if(typeof detailVerVue === "undefined"){
            			return;
            		}
        			detailVerVue.getData(node.id); */
        		}else if(node.nodeType==="managePrice"){
        			mainVue.selectedPage = 3;
        			mainVue.selectFlag = true;
        			var obj = $('#tree').tree('getParent',node.target);
        			mainVue.managePrUrl = mainVue.managePriceUrl + obj.provinceId;
        			/* if(typeof detailVerVue === "undefined"){
            			return;
            		}
        			detailVerVue.getData(node.id); */
        		}
            	
            }
		})
	},
	methods:{
		addRule:function(){
			var selected = $('#tree').tree('getSelected');
			var name = new Date().getFullYear()+"-"+selected.text+"-电力交易规则";
			this.name = name;
			this.provinceId = selected.provinceId;
			$('#tree').tree('append',{
                parent: selected.target,
                data: [{
                	id : "-1",
                    text : name,
                    state : 'open',
                    nodeType : "rule",
                    ruleStatus:"01",
                    provinceId:selected.provinceId,
                    attributes:{province : selected.provinceId, status : "01", calcName : name, calcParam : "", calcCode : ""},
                    children :[],
                }]
            }); 
		},
		deleteNode:function(selected){
			$('#tree').tree('remove',selected.target);
			calcEditVue.reset();
		},
		updateNode:function(node,data){
			var me = this;
			if(node){
				data.target = node.target;
				$('#tree').tree('update',data);
				var node1 = $("#tree").tree("getSelected");
				me.showRule(node1);
			}
		},
		showRule:function(node){
			var me = this;
        	calcEditVue.initData(node.id);
		},
        //生效
        effect:function(){
        	var selected = $('#tree').tree('getSelected');
        	if(selected.ruleStatus=="02"){
        		MainFrameUtil.alert({ title:"警告", body:"该规则已经生效" }); 
        		return;
        	}
        	var id = selected.id;
        	if(id){
        		var proId = selected.provinceId;
        		$.ajax({
        			url:basePath + "smCalcConfigureController/effect/" + proId +"/"+id,
        			type:"post",
        			success:function(data){
        				if(data.flag){
        					mainVue.updateNode(selected,{ruleStatus:"02"});
        					$(selected.target).find(".tree-title").css("color","red");
        					MainFrameUtil.alert({ title:"提示", body:data.msg }); 
        				}else{
        					MainFrameUtil.alert({ title:"警告", body:data.msg }); 
        				}
        			}
        		});
        	}else{
        		MainFrameUtil.alert({ title:"警告", body:"请先保存该规则" }); 
        	}
        },
        //失效
        invalid:function(){
        	var selected = $('#tree').tree('getSelected');
        	if(selected.ruleStatus=="03"){
        		MainFrameUtil.alert({ title:"警告", body:"该规则已经失效" }); 
        		return;
        	}
        	var id = selected.id;
        	if(id){
        		$.ajax({
        			url:basePath+"smCalcConfigureController/invalid/"+id,
        			type:"post",
        			success:function(data){
        				if(data.flag){
        					mainVue.updateNode(selected,{ruleStatus:"03"});
        					$(selected.target).find(".tree-title").css("color","black");
        					MainFrameUtil.alert({ title:"提示", body:data.msg }); 
        				}else{
        					MainFrameUtil.alert({ title:"错误", body:data.msg }); 
        				}
        			}
        		})
        	}else{
        		MainFrameUtil.alert({ title:"警告", body:"请先保存该规则" }); 
        	}
        },
        keydown :function(event){ 
        	if(mainVue.selectedPage == 3 && typeof(listVue) != "undefined"){
        		listVue.keydown(event);
        	}
        } 
	}	 
}); 
</script>
</body>
</html>