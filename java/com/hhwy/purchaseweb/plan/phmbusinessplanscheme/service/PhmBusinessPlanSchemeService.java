package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmBusinessPlanScheme;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhfTradingCenterDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.PhmBusinessPlanSchemeVo;
import com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain.SchemeVo;

/**
 * IPhmBusinessPlanSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmBusinessPlanSchemeService{
	
	/**
	 * 分页获取对象PhmBusinessPlanScheme
	 * @param PhmBusinessPlanSchemeVo
	 * @return QueryResult
	 */
	public QueryResult<PhmBusinessPlanScheme> getPhmBusinessPlanSchemeByPage(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmBusinessPlanScheme列表
	 * @param PhmBusinessPlanSchemeVo
	 * @return List
	 */
	public List<PhmBusinessPlanScheme> getPhmBusinessPlanSchemeListByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmBusinessPlanScheme数量
	 * @param PhmBusinessPlanSchemeVo
	 * @return Integer
	 */
	public Integer getPhmBusinessPlanSchemeCountByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo);
	
	/**
	 * 根据查询条件获取单个对象PhmBusinessPlanScheme
	 * @param PhmBusinessPlanSchemeVo
	 * @return PhmBusinessPlanScheme
	 */
	public PhmBusinessPlanScheme getPhmBusinessPlanSchemeOneByParams(PhmBusinessPlanSchemeVo phmBusinessPlanSchemeVo) throws Exception;
	
	/**
	 * 根据id获取树上列表
	 * getTreeList(描述这个方法的作用)<br/>
	 * @param planId
	 * @return 
	 * List<PhmBusinessPlanSchemeDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmBusinessPlanSchemeDetail> getTreeList(String planId);
	
	/**
	 * 根据年份获取成交平均价
	 * getPhfTradingCenterDetailByYear(描述这个方法的作用)<br/>
	 * @param year
	 * @return 
	 * List<PhfTradingCenterDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhfTradingCenterDetail> getPhfTradingCenterDetailByYear(String year);
	
	/**
	 * 根据ID获取对象PhmBusinessPlanScheme
	 * @param ID
	 * @return PhmBusinessPlanScheme
	 */
	public PhmBusinessPlanScheme getPhmBusinessPlanSchemeById(String id);
	
	/**
	 * 获取初始方案信息信息
	 * getInitScheme(描述这个方法的作用)<br/>
	 * @param planId
	 * @param year
	 * @return 
	 * SchemeVo
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public SchemeVo getInitScheme(String planId,String year) throws Exception;
	
	/**
	 * 计算利润
	 * calc(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanScheme 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void calc(PhmBusinessPlanScheme phmBusinessPlanScheme);
	
	/**
	 * 校验数据的完整
	 * check(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void check(String id);
	
	/**
	 * 添加对象PhmBusinessPlanScheme
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme);
	
	/**
	 * 保存
	 * save(描述这个方法的作用)<br/>
	 * @param phmBusinessPlanScheme 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void save(PhmBusinessPlanSchemeVo phmBusinessPlanScheme);
	
	/**
	 * 添加对象PhmBusinessPlanScheme列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessPlanSchemeList(List<PhmBusinessPlanScheme> phmBusinessPlanSchemeList);
	
	/**
	 * 更新对象PhmBusinessPlanScheme
	 * @param 实体对象
	 * @return PhmBusinessPlanScheme
	 */
	public void updatePhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme);
	
	/**
	 * 删除对象PhmBusinessPlanScheme
	 * @param id数据组
	 */
	public void deletePhmBusinessPlanScheme(String[] ids);

}