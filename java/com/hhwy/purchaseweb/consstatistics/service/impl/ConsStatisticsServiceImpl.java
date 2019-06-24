package com.hhwy.purchaseweb.consstatistics.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.consstatistics.domain.ConsstatisticsVo;
import com.hhwy.purchaseweb.consstatistics.service.ConsStatisticsService;

@Component
public class ConsStatisticsServiceImpl implements ConsStatisticsService{

	public static final Logger log = LoggerFactory.getLogger(ConsStatisticsServiceImpl.class);
	
	@Autowired
	DAO<?> dao;

	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Description:TODO(平台用户统计)<br/>
	 * @return
	 * List<Map<String,Object>>
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年5月15日 下午1:40:45
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年5月15日 下午1:40:45
	 * @since  1.0.0
	 */
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getInitData() {
		List<Map<String, Object>> bySql = (List<Map<String, Object>>) dao.getBySql("consStatistics.sql.getConsStatisticsData", null);
		return bySql;
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryResult<Map<String, Object>> getConsDetail(ConsstatisticsVo params) {
		QueryResult<Map<String, Object>> queryResult = new QueryResult<Map<String, Object>>();
		if(params.getOrgNo() != null && !"".equals(params.getOrgNo())){
			String[] item = (params.getOrgNo()).split(",");
			params.setItem(item);
		}
		long total = getConsDetailNum(params);
		List<Map<String, Object>> bySql = (List<Map<String, Object>>) dao.getBySql("consStatistics.sql.getConsDetail", params);
		queryResult.setTotal(total);
		queryResult.setData(bySql);
		return queryResult;
	}
	
	public Integer getConsDetailNum(ConsstatisticsVo params) {
		Object result = dao.getOneBySQL("consStatistics.sql.getConsDetailNum",params);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
		
	}
}
