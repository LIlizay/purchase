package com.hhwy.purchaseweb.plan.phmbusinessplan.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmBusinessPlan;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;

/**
 * IPhmBusinessPlanService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmBusinessPlanService{
	
	/**
	 * 分页获取对象PhmBusinessPlan
	 * @param PhmBusinessPlanVo
	 * @return QueryResult
	 */
	public QueryResult<PhmBusinessPlan> getPhmBusinessPlanByPage(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmBusinessPlan列表
	 * @param PhmBusinessPlanVo
	 * @return List
	 */
	public List<PhmBusinessPlan> getPhmBusinessPlanListByParams(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception;
	
	/**
	 * 分页获取经营计划详细
	 * getBusinessPlanDetailByPage(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanVo
	 * @return
	 * @throws Exception 
	 * QueryResult<PhmBusinessPlanDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public QueryResult<PhmBusinessPlanDetail> getBusinessPlanDetailByPage(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception;
	
	/**
	 * 根据查询条件获取经营计划详细
	 * getBusinessPlanDetail(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanVo
	 * @return
	 * @throws Exception 
	 * List<PhmBusinessPlanDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmBusinessPlanDetail> getBusinessPlanDetail(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlan数量
	 * @param PhmBusinessPlanVo
	 * @return Integer
	 */
	public Integer getPhmBusinessPlanCountByParams(PhmBusinessPlanVo phmBusinessPlanVo);
	
	/**
	 * 根据查询条件获取单个对象PhmBusinessPlan
	 * @param PhmBusinessPlanVo
	 * @return PhmBusinessPlan
	 */
	public PhmBusinessPlan getPhmBusinessPlanOneByParams(PhmBusinessPlanVo phmBusinessPlanVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmBusinessPlan
	 * @param ID
	 * @return PhmBusinessPlan
	 */
	public PhmBusinessPlan getPhmBusinessPlanById(String id);
	
	/**
	 * 获取购电计划
	 * getBusinessPlanById(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * PhmBusinessPlanVo
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public PhmBusinessPlanVo getBusinessPlanById(String id) throws Exception;
	
	/**
	 * 添加对象PhmBusinessPlan
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessPlan(PhmBusinessPlanVo phmBusinessPlanVo);
	
	/**
	 * 添加对象PhmBusinessPlan列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessPlanList(List<PhmBusinessPlan> phmBusinessPlanList);
	
	/**
	 * 更新对象PhmBusinessPlan
	 * @param 实体对象
	 * @return PhmBusinessPlan
	 */
	public void updatePhmBusinessPlan(PhmBusinessPlan phmBusinessPlan);
	
	/**
	 * 更新经营计划
	 * updateBusinessPlan(描述这个方法的作用)<br/>
	 * @param phmBusinessPlan 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void updateBusinessPlan(PhmBusinessPlanVo phmBusinessPlanVo);
	
	/**
	 * 删除对象PhmBusinessPlan
	 * @param id数据组
	 */
	public void deletePhmBusinessPlan(String[] ids);
	
	/**
	 * 删除经营计划
	 * deleteBusinessPlan(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void deleteBusinessPlan(String id);
	
	/**
     * 提交
     * submit(描述这个方法的作用)<br/>
     * @param id
     * @param state 
     * void
	 * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public void submit(String id,String state) throws Exception;
}