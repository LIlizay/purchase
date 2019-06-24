package com.hhwy.purchaseweb.sms.service;

import java.util.List;

import com.hhwy.purchase.domain.SwSms;

/**
 * 
 * <b>类 名 称：</b>SwSmsService<br/>
 * <b>类 描 述：短信信息记录接口</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年9月2日 下午5:35:03<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface SwSmsService {

	/**
	 * 
	 * @Title: saveSwSmsList
	 * @Description:(保存短信记录列表信息)
	 * @param List<SwSms> 
	 * void
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年9月2日 下午5:40:51
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年9月2日 下午5:40:51
	 * @since  1.0.0
	 */
	public void saveSwSmsList(List<SwSms> swSmsList);
	
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
	public void saveSwSms(SwSms swSms);
	
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
	public SwSms getSwSmsByParams(SwSms swSms) throws Exception;
	
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
	public void updateSwSms(SwSms swSms);
}
