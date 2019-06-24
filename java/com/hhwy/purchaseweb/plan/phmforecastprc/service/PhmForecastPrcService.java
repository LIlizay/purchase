package com.hhwy.purchaseweb.plan.phmforecastprc.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmForecastPrc;
import com.hhwy.purchaseweb.plan.phmforecastprc.domain.PhmForecastPrcVo;

/**
 * IPhmForecastPrcService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmForecastPrcService{
	
	/**
	 * 分页获取对象PhmForecastPrc
	 * @param PhmForecastPrcVo
	 * @return QueryResult
	 */
	public QueryResult<PhmForecastPrc> getPhmForecastPrcByPage(PhmForecastPrcVo phmForecastPrcVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmForecastPrc列表
	 * @param PhmForecastPrcVo
	 * @return List
	 */
	public List<PhmForecastPrc> getPhmForecastPrcListByParams(PhmForecastPrcVo phmForecastPrcVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmForecastPrc数量
	 * @param PhmForecastPrcVo
	 * @return Integer
	 */
	public Integer getPhmForecastPrcCountByParams(PhmForecastPrcVo phmForecastPrcVo);
	
	/**
	 * 根据查询条件获取单个对象PhmForecastPrc
	 * @param PhmForecastPrcVo
	 * @return PhmForecastPrc
	 */
	public PhmForecastPrc getPhmForecastPrcOneByParams(PhmForecastPrcVo phmForecastPrcVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmForecastPrc
	 * @param ID
	 * @return PhmForecastPrc
	 */
	public PhmForecastPrc getPhmForecastPrcById(String id);
	
	/**
	 * 添加对象PhmForecastPrc
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmForecastPrc(PhmForecastPrc phmForecastPrc);
	
	/**
	 * 添加对象PhmForecastPrc列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmForecastPrcList(List<PhmForecastPrc> phmForecastPrcList);
	
	/**
	 * 更新对象PhmForecastPrc
	 * @param 实体对象
	 * @return PhmForecastPrc
	 */
	public void updatePhmForecastPrc(PhmForecastPrc phmForecastPrc);
	
	/**
	 * 删除对象PhmForecastPrc
	 * @param id数据组
	 */
	public void deletePhmForecastPrc(String[] ids);

}