package com.hhwy.purchaseweb.plan.phmphplanyearscheme.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPhPlanYearScheme;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.CalcVo;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeDetail;
import com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain.PhmPhPlanYearSchemeVo;

/**
 * IPhmPhPlanYearSchemeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmPhPlanYearSchemeService{
	
	/**
	 * 分页获取对象PhmPhPlanYearScheme
	 * @param PhmPhPlanYearSchemeVo
	 * @return QueryResult
	 */
	public QueryResult<PhmPhPlanYearScheme> getPhmPhPlanYearSchemeByPage(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmPhPlanYearScheme列表
	 * @param PhmPhPlanYearSchemeVo
	 * @return List
	 */
	public List<PhmPhPlanYearScheme> getPhmPhPlanYearSchemeListByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmPhPlanYearScheme数量
	 * @param PhmPhPlanYearSchemeVo
	 * @return Integer
	 */
	public Integer getPhmPhPlanYearSchemeCountByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo);
	
	/**
	 * 根据查询条件获取单个对象PhmPhPlanYearScheme
	 * @param PhmPhPlanYearSchemeVo
	 * @return PhmPhPlanYearScheme
	 */
	public PhmPhPlanYearScheme getPhmPhPlanYearSchemeOneByParams(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmPhPlanYearScheme
	 * @param ID
	 * @return PhmPhPlanYearScheme
	 */
	public PhmPhPlanYearScheme getPhmPhPlanYearSchemeById(String id);
	
	/**
	 * 分页获取方案详细
	 * getSchemeDetailByPage(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearSchemeVo
	 * @return
	 * @throws Exception 
	 * QueryResult<PhmPhPlanYearSchemeDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public QueryResult<PhmPhPlanYearSchemeDetail> getSchemeDetailByPage(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo) throws Exception;
	
	/**
	 * 获取方案详细列表
	 * getSchemeDetailList(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearSchemeVo
	 * @return 
	 * List<PhmPhPlanYearSchemeDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmPhPlanYearSchemeDetail> getSchemeDetailList(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo);
	
	/**
	 * 获取方案详细数量
	 * getSchemeDetailCount(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearSchemeVo
	 * @return 
	 * Integer
	 * @exception 
	 * @since  1.0.0
	 */
	public Integer getSchemeDetailCount(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo);
	
	/**
	 * 添加对象PhmPhPlanYearScheme
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPhPlanYearScheme(PhmPhPlanYearSchemeVo phmPhPlanYearSchemeVo);
	
	/**
	 * 添加对象PhmPhPlanYearScheme列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPhPlanYearSchemeList(List<PhmPhPlanYearScheme> phmPhPlanYearSchemeList);
	
	/**
	 * 更新对象PhmPhPlanYearScheme
	 * @param 实体对象
	 * @return PhmPhPlanYearScheme
	 */
	public void updatePhmPhPlanYearScheme(PhmPhPlanYearScheme phmPhPlanYearScheme);
	
	/**
	 * 删除对象PhmPhPlanYearScheme
	 * @param id数据组
	 */
	public void deletePhmPhPlanYearScheme(String[] ids);

	/**
     * 系统计算
     * sysCalc(描述这个方法的作用)<br/>
     * @param phmPhPlanYearScheme 
     * void
     * @exception 
     * @since  1.0.0
     */
    public CalcVo sysCalc(PhmPhPlanYearScheme phmPhPlanYearScheme);
    
	/**
	 * 计算
	 * calc(描述这个方法的作用)<br/>
	 * @param phmPhPlanYearScheme 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void calc(PhmPhPlanYearScheme phmPhPlanYearScheme);
}