package com.hhwy.purchaseweb.forecast.phfworkcondition.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfWorkCondition;
import com.hhwy.purchaseweb.forecast.phfworkcondition.domain.PhfWorkConditionVo;

/**
 * IPhfWorkConditionService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfWorkConditionService{
	
	/**
	 * 分页获取对象PhfWorkCondition
	 * @param PhfWorkConditionVo
	 * @return QueryResult
	 */
	public QueryResult<PhfWorkCondition> getPhfWorkConditionByPage(PhfWorkConditionVo phfWorkConditionVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfWorkCondition列表
	 * @param PhfWorkConditionVo
	 * @return List
	 */
	public List<PhfWorkCondition> getPhfWorkConditionListByParams(PhfWorkConditionVo phfWorkConditionVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfWorkCondition数量
	 * @param PhfWorkConditionVo
	 * @return Integer
	 */
	public Integer getPhfWorkConditionCountByParams(PhfWorkConditionVo phfWorkConditionVo);
	
	/**
	 * 根据查询条件获取单个对象PhfWorkCondition
	 * @param PhfWorkConditionVo
	 * @return PhfWorkCondition
	 */
	public PhfWorkCondition getPhfWorkConditionOneByParams(PhfWorkConditionVo phfWorkConditionVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfWorkCondition
	 * @param ID
	 * @return PhfWorkCondition
	 */
	public PhfWorkCondition getPhfWorkConditionById(String id);
	
	/**
	 * 添加对象PhfWorkCondition
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfWorkCondition(PhfWorkCondition phfWorkCondition);
	
	/**
	 * 添加对象PhfWorkCondition列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfWorkConditionList(List<PhfWorkCondition> phfWorkConditionList);
	
	/**
	 * 更新对象PhfWorkCondition
	 * @param 实体对象
	 * @return PhfWorkCondition
	 */
	public void updatePhfWorkCondition(PhfWorkCondition phfWorkCondition);
	
	/**
	 * 删除对象PhfWorkCondition
	 * @param id数据组
	 */
	public void deletePhfWorkCondition(String[] ids);
	
	/**
	 * 工况信息维护
	 */
	public List<PhfWorkCondition> getWorkInfo(String ym)throws Exception;
	
	/**
	 * 保存工况信息
	 */
	public void saveWorkInfo(PhfWorkConditionVo phfWorkConditionVo);
}