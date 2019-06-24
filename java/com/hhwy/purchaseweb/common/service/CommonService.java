package com.hhwy.purchaseweb.common.service;

import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;

/**
 * 
 * <b>类 名 称：</b>CommonService<br/>
 * <b>类 描 述：公共查询接口</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月19日 下午5:41:25<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface CommonService {
	
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
	public QueryResult<Map<String,Object>> getAcUser(Map<String,String> params) throws Exception;

}
