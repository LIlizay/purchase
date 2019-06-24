package com.hhwy.purchaseweb.agreement.phmgenepqdist.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.purchaseweb.agreement.phmgenepqdist.domain.PhmGenePqDistVo;

/**
 * IPhmGenePqDistService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmGenePqDistService{
	
	/**
	 * 分页获取对象PhmGenePqDist
	 * @param PhmGenePqDistVo
	 * @return QueryResult
	 */
	public QueryResult<PhmGenePqDist> getPhmGenePqDistByPage(PhmGenePqDistVo phmGenePqDistVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmGenePqDist列表
	 * @param PhmGenePqDistVo
	 * @return List
	 */
	public List<PhmGenePqDist> getPhmGenePqDistListByParams(PhmGenePqDistVo phmGenePqDistVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmGenePqDist数量
	 * @param PhmGenePqDistVo
	 * @return Integer
	 */
	public Integer getPhmGenePqDistCountByParams(PhmGenePqDistVo phmGenePqDistVo);
	
	/**
	 * 根据查询条件获取单个对象PhmGenePqDist
	 * @param PhmGenePqDistVo
	 * @return PhmGenePqDist
	 */
	public PhmGenePqDist getPhmGenePqDistOneByParams(PhmGenePqDistVo phmGenePqDistVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmGenePqDist
	 * @param ID
	 * @return PhmGenePqDist
	 */
	public PhmGenePqDist getPhmGenePqDistById(String id);
	
	/**
	 * 添加对象PhmGenePqDist
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmGenePqDist(PhmGenePqDist phmGenePqDist);
	
	/**
	 * 添加对象PhmGenePqDist列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmGenePqDistList(List<PhmGenePqDist> phmGenePqDistList);
	
	/**
	 * 更新对象PhmGenePqDist
	 * @param 实体对象
	 * @return PhmGenePqDist
	 */
	public void updatePhmGenePqDist(PhmGenePqDist phmGenePqDist);
	
	/**
	 * 删除对象PhmGenePqDist
	 * @param id数据组
	 */
	public void deletePhmGenePqDist(String[] ids);

}