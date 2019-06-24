package com.hhwy.purchaseweb.sms.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchase.domain.SwSms;
import com.hhwy.purchaseweb.sms.service.SwSmsService;

@Component
public class SwSmsServiceImpl implements SwSmsService{
	
public static final Logger log = LoggerFactory.getLogger(SwSmsServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}

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
	@Override
	public void saveSwSmsList(List<SwSms> swSmsList) {
		dao.saveList(swSmsList);
	}

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
	@Override
	public void saveSwSms(SwSms swSms) {
		dao.save(swSms);
	}

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
	@Override
	public SwSms getSwSmsByParams(SwSms swSms) throws Exception {
		return (SwSms) dao.getOneBySQL("swsms.sql.getSwSmsByParams", swSms);
	}

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
	@Override
	public void updateSwSms(SwSms swSms) {
		dao.update(swSms);
	}
	
}
