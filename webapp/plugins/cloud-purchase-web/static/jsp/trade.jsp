<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div style="padding:15px;">
	<!-- 查询 -->
	<div class="input-group col-md-4">
         <input type="text" v-model="searchText" class="form-control" placeholder="查询">
         <input type="hidden" @keyup.enter="search"/>
         <span class="input-group-btn">
             <button class="btn btn-primary" type="button" @click="search" >查询</button>
         </span>
     </div>
     <!-- 树 -->
	<div style="overflow: auto;height: 350px;">
		<ul id="orgTree" class="easyui-tree" ></ul>
	</div>
</div>
<!-- <div id="menu" class="easyui-menu" style="width:120px;">
	<div onclick="" data-options="iconCls:'icon-add'">Append</div>
	<div onclick="" data-options="iconCls:'icon-remove'">Remove</div>
</div> -->
<script type="text/javascript">
var basePath = '${Config.baseURL}';
var test = new Vue({
	el:"body",
	ready:function(){
		me=this;
		var treeData;
		$.ajax({
			url:basePath+"globalCache/queryTreeByParam?pCode.codeType=tradecode&orderbyParam=-DISP_SN desc",
			type:'get',
			success:function(data){
				me.treeData = data;
			},
		})
		MainFrameUtil.setDialogButtons(this.getButtons(),"mk-power-up-dialog-id");
	},
	data:{
		treeData:[],
		flag:true,
		searchText:""
	},
	methods:{
		getButtons:function(){
			var that=this;
            var buttons=[{
            	text:"确定",
            	type:"primary",
                handler:function(btn,params){
                	var treeNode = $('#orgTree').tree("getSelected");
                	if(treeNode!=null){
                		console.log(treeNode);
                		var treeTrade = $('#orgTree').tree("getSelected");
                		params.okHandler(treeTrade);
                		MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                	}else{
                		MainFrameUtil.alert({
                            title:"提示",
                            body:"请选择行业分类！"
                        }); 
                	}
                }
            },{
                text:"取消",
                type:"default",
                handler:function(btn,params){
                	MainFrameUtil.closeDialog("mk-power-up-dialog-id");
                }
            }];
            return buttons;
		},
		//点击搜索方法
		search:function(){
			var value = this.searchText;
			var treeData = this.treeData;
    		if(value==""){
    			$('#orgTree').tree("loadData",treeData);   
    		}else{
    			var s = this.searchTree(treeData,value);
	    		$('#orgTree').tree("loadData",s); 
	    		$('#orgTree').tree("expandAll");  
    		}
		},
		//递归查找树
		searchTree:function(array,value){
			var orgTree = [];
			for(var o in array){
				if(array[o].children){
					var children = this.searchTree(array[o].children,value);
					if(children.length>0||array[o].text.indexOf(value)!=-1){
						var s = this.copy(array[o]);
						if(children.length>0){
							s.children = children;
						}else{
							delete s["state"];
						}
						orgTree.push(s);
					}
				}else{
					if(array[o].text.indexOf(value)!=-1){
						orgTree.push(this.copy(array[o]));
					}
				}
			}
			return orgTree;
		},
		//复制数据
		copy:function(source){
			var result = {};
			for (var key in source) {
				if(key=="value"||key=="text"||key=="state"){
					result[key] = source[key]
				}
		     }
			return result
		},
		recursion:function(array,list){
			for(var o in array){
				if(array[o].content5!="1"){
					list.push(array[o].domId);
				}
				if(array[o].children){
					this.recursion(array[o].children,list);
				}
			}
		}
	},
	watch:{
		'treeData':function(value,oldVal){
			me=this;
			$('#orgTree').tree({
				data:value,
				animate:true,
				/* onLoadSuccess:function(node, data){
					$('.tree-checkbox').unbind("click");
 					var s =[];
 					me.recursion(data,s);
 					$.each(s,function(){
 						$("#"+this.toString()).children().remove('.tree-checkbox');
 					}) 
				},
				onSelect : function(node){
					var cknodes = $(this).tree("getChecked");
					for(var i = 0 ; i < cknodes.length ; i++){
						$(this).tree("uncheck", cknodes[i].target);
					}
					//再选中改节点
					$(this).tree("check", node.target);
				} */
			})
		}
	}
})
</script>
</body>
</html>