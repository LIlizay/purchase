package com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqVo;

/**
 * IPhmGeneratorMonthPqService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmGeneratorMonthPqService{
	
	/**
	 * 分页获取对象PhmGeneratorMonthPq
	 * @param PhmGeneratorMonthPqVo
	 * @return QueryResult
	 */
	public QueryResult<PhmGeneratorMonthPqDetail> getPhmGeneratorMonthPqByPage(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmGeneratorMonthPq列表
	 * @param PhmGeneratorMonthPqVo
	 * @return List
	 */
	public List<PhmGeneratorMonthPqDetail> getPhmGeneratorMonthPqListByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmGeneratorMonthPq数量
	 * @param PhmGeneratorMonthPqVo
	 * @return Integer
	 */
	public Integer getPhmGeneratorMonthPqCountByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo);
	
	/**
	 * 根据查询条件获取单个对象PhmGeneratorMonthPq
	 * @param PhmGeneratorMonthPqVo
	 * @return PhmGeneratorMonthPq
	 */
	public PhmGeneratorMonthPqDetail getPhmGeneratorMonthPqOneByParams(PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmGeneratorMonthPq
	 * @param ID
	 * @return PhmGeneratorMonthPq
	 */
	public PhmGeneratorMonthPq getPhmGeneratorMonthPqById(String id);
	
	/**
	 * 添加对象PhmGeneratorMonthPq
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmGeneratorMonthPq(PhmGeneratorMonthPq phmGeneratorMonthPq);
	
	/**
	 * 添加对象PhmGeneratorMonthPq列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmGeneratorMonthPqList(List<PhmGeneratorMonthPq> phmGeneratorMonthPqList);
	
	/**
	 * 更新对象PhmGeneratorMonthPq
	 * @param 实体对象
	 * @return PhmGeneratorMonthPq
	 */
	public void updatePhmGeneratorMonthPq(PhmGeneratorMonthPq phmGeneratorMonthPq);
	
	/**
	 * 删除对象PhmGeneratorMonthPq
	 * @param id数据组
	 */
	public void deletePhmGeneratorMonthPq(String[] ids);
}