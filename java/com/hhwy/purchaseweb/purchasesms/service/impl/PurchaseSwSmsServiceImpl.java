package com.hhwy.purchaseweb.purchasesms.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.purchaseweb.purchasesms.service.PurchaseSwSmsService;

/**
 * 
 * <b>类 名 称：</b>PurchaseSwSmsServiceImpl<br/>
 * <b>类 描 述：购电短息记录实现类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年9月5日 下午5:32:37<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component
public class PurchaseSwSmsServiceImpl implements PurchaseSwSmsService {
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
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
	public void saveSwSms(Map<String,Object> params) {
		String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		params.put("orgNo", orgNo);
		dao.saveBySql("purchasesms.sql.saveSwSms", params);
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
	@SuppressWarnings("unchecked")
	@Override
	public Map<String,Object> getSwSmsByParams(Map<String,Object> params) throws Exception {
		Parameter.isFilterData.set(true);
		Map<String,Object> list = (Map<String,Object>) dao.getOneBySQL("purchasesms.sql.getSwSmsByParams", params);
		Parameter.isFilterData.set(false);
		return list;
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
	public void updateSwSms(Map<String,Object> params) {
		dao.updateBySql("purchasesms.sql.updateSwSms", params);
	}
}
