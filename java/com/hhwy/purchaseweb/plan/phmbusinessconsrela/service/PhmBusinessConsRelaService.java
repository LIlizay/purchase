package com.hhwy.purchaseweb.plan.phmbusinessconsrela.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmBusinessConsRela;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PhmBusinessConsRelaVo;
import com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain.PurchasingPowerDetail;
import com.hhwy.purchaseweb.plan.phmbusinessplan.domain.PhmBusinessPlanVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;

/**
 * IPhmBusinessConsRelaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmBusinessConsRelaService{
	
	/**
	 * 分页获取对象PhmBusinessConsRela
	 * @param PhmBusinessConsRelaVo
	 * @return QueryResult
	 */
	public QueryResult<PhmBusinessConsRela> getPhmBusinessConsRelaByPage(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmBusinessConsRela列表
	 * @param PhmBusinessConsRelaVo
	 * @return List
	 */
	public List<PhmBusinessConsRela> getPhmBusinessConsRelaListByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmBusinessConsRela数量
	 * @param PhmBusinessConsRelaVo
	 * @return Integer
	 */
	public Integer getPhmBusinessConsRelaCountByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo);
	
	/**
	 * 根据查询条件获取单个对象PhmBusinessConsRela
	 * @param PhmBusinessConsRelaVo
	 * @return PhmBusinessConsRela
	 */
	public PhmBusinessConsRela getPhmBusinessConsRelaOneByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmBusinessConsRela
	 * @param ID
	 * @return PhmBusinessConsRela
	 */
	public PhmBusinessConsRela getPhmBusinessConsRelaById(String id);
	
	/**
	 * 根据偏id获取用户信息
	 * getConsInfoDetailByPlanId(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * PhmBusinessPlanVo
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public PhmBusinessPlanVo getConsInfoDetailByPlanId(String id) throws Exception;
	
	/**
	 * 根据用户id获取用户信息
	 * getConsInfoDetailByIds(描述这个方法的作用)<br/>
	 * @param ids
	 * @return 
	 * List<ConsInfoDetail>
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public List<ConsInfoDetail> getConsInfoDetailByIds(String[] ids) throws Exception;
	
	/**
	 * 分页获取用户电量收集
	 * getPurchasingPowerDetailByPage(描述这个方法的作用)<br/>
	 * @param phmBusinessConsRelaVo
	 * @return
	 * @throws Exception 
	 * QueryResult<PurchasingPowerDetail>
	 * @exception 
	 * @since  1.0.0
	 */
	public QueryResult<PurchasingPowerDetail> getPurchasingPowerDetailByPage(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception;

    /**
     * 获取用户电量收集列表
     * getPurchasingPowerDetailListByParams(描述这个方法的作用)<br/>
     * @param phmBusinessConsRelaVo
     * @return
     * @throws Exception 
     * List<PurchasingPowerDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<PurchasingPowerDetail> getPurchasingPowerDetailListByParams(PhmBusinessConsRelaVo phmBusinessConsRelaVo) throws Exception;
    
    /**
     * 校验数据是否完整
     * check(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void check(String id);
    
	/**
	 * 添加对象PhmBusinessConsRela
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessConsRela(PhmBusinessConsRela phmBusinessConsRela);
	
	/**
	 * 添加对象PhmBusinessConsRela列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmBusinessConsRelaList(List<PhmBusinessConsRela> phmBusinessConsRelaList);
	
	/**
	 * 更新对象PhmBusinessConsRela
	 * @param 实体对象
	 * @return PhmBusinessConsRela
	 */
	public void updatePhmBusinessConsRela(PhmBusinessConsRela phmBusinessConsRela);
	
	/**
	 * 删除对象PhmBusinessConsRela
	 * @param id数据组
	 */
	public void deletePhmBusinessConsRela(String[] ids);
	
}