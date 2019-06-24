package com.hhwy.purchaseweb.plan.phmlcdist.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmLcDist;
import com.hhwy.purchaseweb.plan.phmlcdist.domain.PhmLcDistVo;

/**
 * IPhmLcDistService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmLcDistService{
	
	/**
	 * 分页获取对象PhmLcDist
	 * @param PhmLcDistVo
	 * @return QueryResult
	 */
	public QueryResult<PhmLcDist> getPhmLcDistByPage(PhmLcDistVo phmLcDistVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmLcDist列表
	 * @param PhmLcDistVo
	 * @return List
	 */
	public List<PhmLcDist> getPhmLcDistListByParams(PhmLcDistVo phmLcDistVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmLcDist数量
	 * @param PhmLcDistVo
	 * @return Integer
	 */
	public Integer getPhmLcDistCountByParams(PhmLcDistVo phmLcDistVo);
	
	/**
	 * 根据查询条件获取单个对象PhmLcDist
	 * @param PhmLcDistVo
	 * @return PhmLcDist
	 */
	public PhmLcDist getPhmLcDistOneByParams(PhmLcDistVo phmLcDistVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmLcDist
	 * @param ID
	 * @return PhmLcDist
	 */
	public PhmLcDist getPhmLcDistById(String id);
	
	/**
	 * 获取长协电量分月
	 * getAgreMonthPq(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * List<PhmLcDist>
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmLcDist> getAgreMonthPq(String id) throws Exception;
	
	/**
	 * 添加对象PhmLcDist
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmLcDist(PhmLcDist phmLcDist);
	
	/**
	 * 添加对象PhmLcDist列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmLcDistList(List<PhmLcDist> phmLcDistList);
	
	/**
	 * 更新对象PhmLcDist
	 * @param 实体对象
	 * @return PhmLcDist
	 */
	public void updatePhmLcDist(PhmLcDist phmLcDist);
	
	/**
	 * 删除对象PhmLcDist
	 * @param id数据组
	 */
	public void deletePhmLcDist(String[] ids);

	/**
	 * 获取合同追踪情况
	 * getTrackInfo(描述这个方法的作用)<br/>
	 * @param schemeId
	 * @return 
	 * List<Map<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Map<String,Object>> getTrackInfo(String schemeId);
	
}