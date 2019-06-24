package com.hhwy.purchaseweb.purchasesms.service;

import java.util.Map;

/**
 * 
 * <b>类 名 称：</b>PurchaseSwSmsService<br/>
 * <b>类 描 述：购电短信记录接口</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年9月5日 下午5:32:16<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface PurchaseSwSmsService {
	
	/**
	 * 
	 * @Title: saveSwSms
	 * @Description:(保存短信记录信息)
	 * @param swSms 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月4日 上午10:43:04
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月4日 上午10:43:04
	 * @since  1.0.0
	 */
	public void saveSwSms(Map<String,Object> params);
	
	/**
	 * 
	 * @Title: getSwSmsByParams
	 * @Description:(查询当前时间当前用户发送短信记录数)
	 * @param swSms
	 * @return 
	 * Integer
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月4日 上午10:51:28
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月4日 上午10:51:28
	 * @since  1.0.0
	 */
	public Map<String,Object> getSwSmsByParams(Map<String,Object> params) throws Exception;
	
	/**
	 * 
	 * @Title: updateSwSms
	 * @Description:(更新短信信息表)
	 * @param swSms 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月4日 下午9:45:34
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月4日 下午9:45:34
	 * @since  1.0.0
	 */
	public void updateSwSms(Map<String,Object> params);
}
