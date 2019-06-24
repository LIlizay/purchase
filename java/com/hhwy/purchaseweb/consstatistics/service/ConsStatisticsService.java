package com.hhwy.purchaseweb.consstatistics.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.consstatistics.domain.ConsstatisticsVo;

public interface ConsStatisticsService {
	
	/**
	 * @Title: getInitData<br/>
	 * @Description:TODO(平台用户统计)<br/>
	 * @return
	 * List<Map<String,Object>>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月15日 下午1:40:45
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月15日 下午1:40:45
	 * @since  1.0.0
	 */
	
	
	//用户排名数据
	public List<Map<String,	Object>> getInitData();
	
	//平台用户明细
	public  QueryResult<Map<String, Object>> getConsDetail(ConsstatisticsVo params);
	
}
