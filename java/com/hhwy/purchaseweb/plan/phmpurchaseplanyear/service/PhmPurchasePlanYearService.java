package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPurchasePlanYear;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PhmPurchasePlanYearVo;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.PurchasePlanYearDetail;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.SchemeDetail;

/**
 * IPhmPurchasePlanYearService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmPurchasePlanYearService{
	
	/**
	 * 分页获取对象PhmPurchasePlanYear
	 * @param PhmPurchasePlanYearVo
	 * @return QueryResult
	 */
	public QueryResult<PhmPurchasePlanYear> getPhmPurchasePlanYearByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmPurchasePlanYear列表
	 * @param PhmPurchasePlanYearVo
	 * @return List
	 */
	public List<PhmPurchasePlanYear> getPhmPurchasePlanYearListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmPurchasePlanYear数量
	 * @param PhmPurchasePlanYearVo
	 * @return Integer
	 */
	public Integer getPhmPurchasePlanYearCountByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo);
	
	/**
	 * 根据查询条件获取单个对象PhmPurchasePlanYear
	 * @param PhmPurchasePlanYearVo
	 * @return PhmPurchasePlanYear
	 */
	public PhmPurchasePlanYear getPhmPurchasePlanYearOneByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmPurchasePlanYear
	 * @param ID
	 * @return PhmPurchasePlanYear
	 */
	public PhmPurchasePlanYear getPhmPurchasePlanYearById(String id);
	
	/**
	 * 分页获取购电计划
	 * getPurchasePlanYearByPage(描述这个方法的作用)<br/>
	 * @param phmPurchasePlanYearVo
	 * @return
	 * @throws Exception 
	 * QueryResult<PurchasePlanYearDetail>
	 * @exception 
	 * @since  1.0.0
	 */
    public QueryResult<PurchasePlanYearDetail> getPurchasePlanYearByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;

    /**
     * 根据条件获取购电计划列表
     * getPurchasePlanYearListByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * List<PurchasePlanYearDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<PurchasePlanYearDetail> getPurchasePlanYearListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;
    
    /**
     * 分页获取用户信息
     * getConsInfoDetailByPage(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * QueryResult<ConsInfoDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<ConsInfoDetail> getConsInfoDetailByPage(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;

    /**
     * 获取用户信息列表
     * getConsInfoDetailListByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return
     * @throws Exception 
     * List<ConsInfoDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<ConsInfoDetail> getConsInfoDetailListByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo) throws Exception;
    
    /**
     * 获取用户信息数量
     * getConsInfoDetailCountByParams(描述这个方法的作用)<br/>
     * @param phmPurchasePlanYearVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getConsInfoDetailCountByParams(PhmPurchasePlanYearVo phmPurchasePlanYearVo);
    
    /**
     * 校验该年份是否存在计划
     * checkPlan(描述这个方法的作用)<br/>
     * @param year 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void checkPlan(String year);
    
    /**
     * 获取初始化方案信息
     * getInitScheme(描述这个方法的作用)<br/>
     * @param year
     * @return 
     * SchemeVo
     * @throws Exception 
     * @exception 
     * @since  1.0.0
     */
    public SchemeDetail getInitScheme(String year) throws Exception;
    
	/**
	 * 添加对象PhmPurchasePlanYear
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPurchasePlanYear(PhmPurchasePlanYear phmPurchasePlanYear) throws Exception;
	
	/**
	 * 添加对象PhmPurchasePlanYear列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPurchasePlanYearList(List<PhmPurchasePlanYear> phmPurchasePlanYearList);
	
	/**
	 * @Title: updatePhmPurchasePlanYear
	 * @Description: 更新对象PhmPurchasePlanYear,先删除年度购电计划和用户的关联关系表，然后再添加
	 * 				（这里因时间紧迫暂时没有考虑删除再添加关联关系是否会影响其他功能。。。）
	 * @param phmPurchasePlanYear 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年12月15日 下午2:25:15
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年12月15日 下午2:25:15
	 * @since  1.0.0
	 */
	public void updatePhmPurchasePlanYear(PhmPurchasePlanYear phmPurchasePlanYear)  throws Exception;
	
	/**
	 * 删除对象PhmPurchasePlanYear
	 * @param id数据组
	 */
	public void deletePhmPurchasePlanYear(String[] ids);

}