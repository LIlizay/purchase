package com.hhwy.purchaseweb.forecast.phfconsproductionplan.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfConsProductionPlan;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanDetail;
import com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain.PhfConsProductionPlanVo;

/**
 * IPhfConsProductionPlanService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfConsProductionPlanService{
	
	/**
	 * 分页获取对象PhfConsProductionPlan
	 * @param PhfConsProductionPlanVo
	 * @return QueryResult
	 */
	public QueryResult<PhfConsProductionPlanDetail> getPhfConsProductionPlanByPage(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfConsProductionPlan列表
	 * @param PhfConsProductionPlanVo
	 * @return List
	 */
	public List<PhfConsProductionPlanDetail> getPhfConsProductionPlanListByParams(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfConsProductionPlan数量
	 * @param PhfConsProductionPlanVo
	 * @return Integer
	 */
	public Integer getPhfConsProductionPlanCountByParams(PhfConsProductionPlanVo phfConsProductionPlanVo);
	
	/**
	 * 根据查询条件获取单个对象PhfConsProductionPlan
	 * @param PhfConsProductionPlanVo
	 * @return PhfConsProductionPlan
	 */
	public PhfConsProductionPlanDetail getPhfConsProductionPlanOneByParams(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfConsProductionPlan
	 * @param ID
	 * @return PhfConsProductionPlan
	 */
	public PhfConsProductionPlan getPhfConsProductionPlanById(String id);
	
	/**
	 * 添加对象PhfConsProductionPlan
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfConsProductionPlan(PhfConsProductionPlan phfConsProductionPlan);
	
	/**
	 * 添加对象PhfConsProductionPlan列表
	 * @param 实体对象
	 * @return null
	 */
	public String savePhfConsProductionPlanList(List<PhfConsProductionPlan> phfConsProductionPlanList)throws Exception;
	
	/**
	 * 更新对象PhfConsProductionPlan
	 * @param 实体对象
	 * @return PhfConsProductionPlan
	 */
	public void updatePhfConsProductionPlan(PhfConsProductionPlan phfConsProductionPlan);
	
	/**
	 * 删除对象PhfConsProductionPlan
	 * @param id数据组
	 */
	public void deletePhfConsProductionPlan(String[] ids);
	
	/**
	 * 删除用户信息
	 */
	public void deleteConsInfo(PhfConsProductionPlan phfConsProductionPlan);
	
	/**
	 * 查询父节点信息
	 */
	public QueryResult<PhfConsProductionPlanDetail> getParentNode(PhfConsProductionPlanVo phfConsProductionPlanVo) throws Exception;
	
	/**
	 * 更新用户及产品信息
	 */
	public void updatePhfConsProductionPlanList(List<PhfConsProductionPlan> phfConsProductionPlanList);
}