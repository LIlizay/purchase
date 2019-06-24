<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公告信息维护</title>
<jsp:include page="/plugins/business-core/static/headers/base.jsp"></jsp:include>
</head>
<body>
<div id="messageEditVue">
    <su-form 
        v-ref:elepower
        offpos-style="true"
        id="fms1" 
        :data-option="dataOption" 
        :set-defaults="setDefaults" 
        :data-validator.sync="valid" >
<mk-panel title="公告信息维护" line="true">
    <mk-form-panel label-width="150px" class="mt_10" num="2">
            <mk-form-row >
                <!-- 消息标题-->
                <mk-form-col  :label="formFields.title.label" required_icon="true">
                      <su-textbox name="title" :data.sync="params.sellingSwMessage.title"></su-textbox>
                </mk-form-col>
                <!--发送时间 -->
                <mk-form-col :label="formFields.sendDate.label" required_icon="true">
                   <su-datepicker name="sendDate" format="YYYY-MM-DD" :data.sync="params.sellingSwMessage.sendDate" ></su-datepicker>
                </mk-form-col>
            </mk-form-row>
            <mk-form-row  height="90px">
                <!-- 消息内容 -->
                <mk-form-col  colspan="2" :label="formFields.content.label" required_icon="true">
                    <su-textbox name="content" type="textarea" rows="3" rols="10" placeholder="" :data.sync="params.sellingSwMessage.content"></su-textbox>
                </mk-form-col>
            </mk-form-row>
    </mk-form-panel>
</mk-panel>
</su-form>
</div>
</body>
</html>

<script type="text/javascript">
var basePath = '${Config.baseURL}';
var messageEditVue = new Vue({
    el: '#messageEditVue',
    data: {
    	formFields:{},
    	params:{
    		sellingSwMessage:{
    			id:"",
    			title:"",
    			content:"",
    			sendDate:"",
            }
    	},
    	//非空验证规则
        dataOption: {
            rules: {
            	title:{required: !0},
            	content:{required: !0},
            	sendDate:{required: !0}
            }
        },
        valid:false
    	
    },
    ready:function(){
    	
    	//加载表单信息
        ComponentUtil.getFormFields("selling.login.sellingswmessage",this);
    	//设置按钮
        MainFrameUtil.setDialogButtons(this.getButtons(),"messageEdit");
        this.params.sellingSwMessage.id = MainFrameUtil.getParams("messageEdit").id;
        
        //初始化数据
        this.initData();
    },
    methods:{
    	
    	//初始化数据
    	initData:function(){
    		var me = this;
    		$.ajax({
                url:basePath+"cloud-purchase-web/swMessageController/" + me.params.sellingSwMessage.id,
                type:"get",
                success : function(data) {
                	if(data.flag){
                		 me.params.sellingSwMessage = data.data;
                	}else{
                		MainFrameUtil.alert({
                            title:"失败提示：",
                            body:data.msg,
                        });
                	}
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                }
            })
    	},
    	
    	//保存销售机会信息
        save:function(){
        	this.$refs.elepower.valid();
            if(this.valid===false){
                return false;
            }
        	$.ajax({
                url:basePath+"cloud-purchase-web/swMessageController",
                type:"put",
                data:$.toJSON(this.params.sellingSwMessage),
                contentType : 'application/json;charset=UTF-8',
                success : function(data) {
                	if(data.flag){
                		 MainFrameUtil.alert({
                             title:"成功提示：",
                             body:data.msg,
                         });
                		 MainFrameUtil.closeDialog("messageEdit");
                	}else{
                		MainFrameUtil.alert({
                            title:"失败提示：",
                            body:data.msg,
                        });
                	}
                },
                error : function() {
                    MainFrameUtil.alert({title:"网络失败提示",body:"请刷新页面重试！"});
                }
            })
        },
        
        //按钮组
        getButtons:function(){
        	var buttons = [{text:"保存",type:"primary",handler:this.save},{text:"取消",handler:function(){MainFrameUtil.closeDialog("messageEdit")}}];
            return buttons;
        }
    }
});
</script>
