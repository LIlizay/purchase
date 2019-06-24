package com.hhwy.purchaseweb.common.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.common.service.CommonService;

/**
 * 
 * <b>类 名 称：</b>CommonServiceImpl<br/>
 * <b>类 描 述：公共查询实现类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月19日 下午5:41:42<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Component("sell_commonService")
public class CommonServiceImpl implements CommonService{

public static final Logger log = LoggerFactory.getLogger(CommonServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 
	 * @Title: getAcUser
	 * @Description:(获取人员列表信息)
	 * @param params
	 * @return
	 * @throws Exception 
	 * QueryResult<Map<String,Object>>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年7月19日 下午5:43:49
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年7月19日 下午5:43:49
	 * @since  1.0.0
	 */
	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<Map<String,Object>> getAcUser(Map<String,String> params) throws Exception {
		QueryResult<Map<String,Object>> result = new QueryResult<Map<String,Object>>();
		String orgNo = SystemServiceUtil.getLoginUserInfo().getOrgId();
		params.put("orgNo", orgNo);
		List<Map<String,Object>> list = (List<Map<String, Object>>) dao.getBySql("common.sql.getAcUser", params);
		Object obj = dao.getOneBySQL("common.sql.getAcUserTotal", params);
		long total = obj == null ? 0:Integer.valueOf(obj.toString());
		result.setData(list);
		result.setTotal(total);
		return result;
	}
}
