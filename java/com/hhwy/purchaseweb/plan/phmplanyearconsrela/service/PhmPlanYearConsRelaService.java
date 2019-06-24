package com.hhwy.purchaseweb.plan.phmplanyearconsrela.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPlanYearConsRela;
import com.hhwy.purchaseweb.plan.phmplanyearconsrela.domain.PhmPlanYearConsRelaVo;

/**
 * IPhmPlanYearConsRelaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmPlanYearConsRelaService{
	
	/**
	 * 分页获取对象PhmPlanYearConsRela
	 * @param PhmPlanYearConsRelaVo
	 * @return QueryResult
	 */
	public QueryResult<PhmPlanYearConsRela> getPhmPlanYearConsRelaByPage(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmPlanYearConsRela列表
	 * @param PhmPlanYearConsRelaVo
	 * @return List
	 */
	public List<PhmPlanYearConsRela> getPhmPlanYearConsRelaListByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmPlanYearConsRela数量
	 * @param PhmPlanYearConsRelaVo
	 * @return Integer
	 */
	public Integer getPhmPlanYearConsRelaCountByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo);
	
	/**
	 * 根据查询条件获取单个对象PhmPlanYearConsRela
	 * @param PhmPlanYearConsRelaVo
	 * @return PhmPlanYearConsRela
	 */
	public PhmPlanYearConsRela getPhmPlanYearConsRelaOneByParams(PhmPlanYearConsRelaVo phmPlanYearConsRelaVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmPlanYearConsRela
	 * @param ID
	 * @return PhmPlanYearConsRela
	 */
	public PhmPlanYearConsRela getPhmPlanYearConsRelaById(String id);
	
	/**
	 * 添加对象PhmPlanYearConsRela
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPlanYearConsRela(PhmPlanYearConsRela phmPlanYearConsRela);
	
	/**
	 * 添加对象PhmPlanYearConsRela列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPlanYearConsRelaList(List<PhmPlanYearConsRela> phmPlanYearConsRelaList);
	
	/**
	 * 更新对象PhmPlanYearConsRela
	 * @param 实体对象
	 * @return PhmPlanYearConsRela
	 */
	public void updatePhmPlanYearConsRela(PhmPlanYearConsRela phmPlanYearConsRela);
	
	/**
	 * 删除对象PhmPlanYearConsRela
	 * @param id数据组
	 */
	public void deletePhmPlanYearConsRela(String[] ids);

	/**
	 * @Title: deletePhmPlanYearConsRelaByPlanId
	 * @Description: 根据年度购电计划id删除对象PhmPlanYearConsRela
	 * @param ids 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年12月15日 下午2:28:37
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年12月15日 下午2:28:37
	 * @since  1.0.0
	 */
	public void deletePhmPlanYearConsRelaByPlanId(String planId);

}