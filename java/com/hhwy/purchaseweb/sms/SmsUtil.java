package com.hhwy.purchaseweb.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SmsUtil {

	public static final Logger log = LoggerFactory.getLogger(SmsUtil.class);
	
	//连接超时时间
	public final static String DEFAULTCONNECTTIMEOUT = "10000";
	
	//读超时时间
	public final static String DEFAULTREADTIMEOUT = "10000";
	
	//短信API产品名称（短信产品名固定，无需修改）
	public static final String PRODUCT = "Dysmsapi";
	
	//短信API产品域名（接口地址固定，无需修改）
	public static final String DOMAIN = "dysmsapi.aliyuncs.com";
	
	//固定的AK，accessKeyId
	public static final String ACCESSKEYID = "LTAI27TNxB45jyCF";
	
	//固定的accessKeySecret
	public static final String ACCESSKEYSECRET = "4oJGnSpUsXGI3Sol4seEzygEn20dcL";
	
	//短信平台签名
	public static final String SIGNNAME = "晋能电力集团售电有限公司";
	
	//动态密码短信模版 ${number} 动态密码
	public static final String TEMPLATECODE94245025 = "SMS_94245025";
	
	//绑定帐号短信模版 ${loginName} 登录帐号
	public static final String TEMPLATECODE94110020 = "SMS_94110020";
	
	//审核不通过短信模版
	public static final String TEMPLATECODE94245024 = "SMS_94245024";
	
	//重置密码短信模版
	public static final String TEMPLATECODE94075021 = "SMS_94075021";
	
	//预警信息短信模版${time}时间；${power}实际用电量，${devitionProp}偏差率，${level}预警级别
	public static final String TEMPLATECODE94260014 = "SMS_94260014";
	
	//电量上报提醒短信模版${time}时间
	public static final String TEMPLATECODE94190022 = "SMS_94190022";
	
	//重报短信模版${ym}交易年月；${date}截至日期
	public static final String TEMPLATECODE94325011 = "SMS_94325011";
	
	//申报短信模版${ym}交易年月；${date}截至日期
	public static final String TEMPLATECODE94320024 = "SMS_94320024";
	
	/**
	 * 短信发送
	 * @param phones 电话号码集合
	 * @param messages 参数json集合，格式请参照标准的JSON协议，例如\r\n换行符替换成\\r\\n
	 */
	public static boolean sendMessage(String phones,String templateCode,String params){
//		try {
//			IAcsClient acsClient = getAcsClient();
//			if(acsClient!=null){
//				//组装请求对象
//				SendSmsRequest request = new SendSmsRequest();
//				//使用post提交
//				request.setMethod(MethodType.POST);
//				//必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,
//				//批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
//				request.setPhoneNumbers(phones);
//				//必填:短信签名-可在短信控制台中找到
//				request.setSignName(SIGNNAME);
//				//必填:短信模板-可在短信控制台中找到
//				request.setTemplateCode(templateCode);
//				//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
//				//友情提+示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,
//				//否则会导致JSON在服务端解析失败
//				if(!StringUtils.isEmpty(params)){
//					request.setTemplateParam(params);
//				}
//				//可选-上行短信扩展码(无特殊需求用户请忽略此字段)
//				//request.setSmsUpExtendCode("90997");
//				//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//				//request.setOutId("yourOutId");
//				//请求失败这里会抛ClientException异常
//				SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
//				if(sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
//					//请求成功
//					return true;
//				}else{
//					return false;
//				}
//			}else{
//				return false;
//			}
//		} catch(ClientException c){
//			log.error(c.getErrCode()+":"+c.getErrMsg());
//			return false;
//		} catch (Exception e) {
//			// TODO: handle exception
//			log.error(e.getMessage());
//			return false;
//		}
		return true;
	}
	
	/**
	 * 创建client
	 */
//	public static IAcsClient getAcsClient() throws Exception {
		//设置超时时间-可自行调整
//		System.setProperty("sun.net.client.defaultConnectTimeout", DEFAULTCONNECTTIMEOUT);
//		System.setProperty("sun.net.client.defaultReadTimeout", DEFAULTREADTIMEOUT);
//		//初始化ascClient,暂时不支持多region
//		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESSKEYID,ACCESSKEYSECRET);
//		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", PRODUCT, DOMAIN);
//		return new DefaultAcsClient(profile);
//	}
	
	 

}
