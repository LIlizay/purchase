package com.hhwy.purchaseweb.delivery.smselldelivery.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.CalcDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryDetail;
import com.hhwy.purchaseweb.delivery.smselldelivery.domain.SmSellDeliveryVo;
import com.hhwy.selling.domain.SmSellDelivery;

/**
 * ISmSellDeliveryService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmSellDeliveryService{
	
	/**
	 * 分页获取对象SmSellDelivery
	 * @param SmSellDeliveryVo
	 * @return QueryResult
	 */
	public QueryResult<SmSellDeliveryDetail> getSmSellDeliveryByPage(SmSellDeliveryVo smSellDeliveryVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmSellDelivery列表
	 * @param SmSellDeliveryVo
	 * @return List
	 */
	public List<SmSellDeliveryDetail> getSmSellDeliveryListByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmSellDelivery数量
	 * @param SmSellDeliveryVo
	 * @return Integer
	 */
	public Integer getSmSellDeliveryCountByParams(SmSellDeliveryVo smSellDeliveryVo);
	
	/**
	 * 根据查询条件获取单个对象SmSellDelivery
	 * @param SmSellDeliveryVo
	 * @return SmSellDelivery
	 */
	public SmSellDeliveryDetail getSmSellDeliveryOneByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmSellDelivery
	 * @param ID
	 * @return SmSellDelivery
	 */
	public SmSellDelivery getSmSellDeliveryById(String id);
	
	/**
     * 分页获取未结算列表
     * getCalcByPage(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return
     * @throws Exception 
     * QueryResult<SmSellDelivery>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<SmSellDeliveryDetail> getCalcByPage(SmSellDeliveryVo smSellDeliveryVo) throws Exception;

    /**
     * 获取未结算列表
     * getCalcListByParams(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return
     * @throws Exception 
     * List<SmSellDelivery>
     * @exception 
     * @since  1.0.0
     */
    public List<SmSellDeliveryDetail> getCalcListByParams(SmSellDeliveryVo smSellDeliveryVo) throws Exception;
    
    /**
     * 获取未结算数量
     * getCalcCountByParams(描述这个方法的作用)<br/>
     * @param smSellDeliveryVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getCalcCountByParams(SmSellDeliveryVo smSellDeliveryVo);
    
	/**
	 * 添加对象SmSellDelivery
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmSellDelivery(SmSellDelivery smSellDelivery);
	
	/**
	 * 计算未结算
	 * calc(描述这个方法的作用)<br/> 
	 * void
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	public void calc(SmSellDeliveryVo smSellDeliveryVo) throws Exception;
	
	/**
	 * 添加对象SmSellDelivery列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmSellDeliveryList(List<SmSellDelivery> smSellDeliveryList);
	
	/**
	 * 更新对象SmSellDelivery
	 * @param 实体对象
	 * @return SmSellDelivery
	 */
	public void updateSmSellDelivery(SmSellDelivery smSellDelivery);
	
	/**
	 * 删除对象SmSellDelivery
	 * @param id数据组
	 */
	public void deleteSmSellDelivery(String[] ids);
	
	/**
	 * 
	 * @Title: getPlanList<br/>
	 * @Description: 查询计划列表<br/>
	 * @param smSellDeliveryVo
	 * @return
	 * @throws Exception <br/>
	 * QueryResult<CalcDetail><br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月14日 下午9:52:04
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月14日 下午9:52:04
	 * @since  1.0.0
	 */
	public QueryResult<CalcDetail> getPlanList(SmSellDeliveryVo smSellDeliveryVo) throws Exception;
	
	/**
	 * 
	 * @Title: submitByYm<br/>
	 * @Description: 更新提交状态<br/>
	 * @param smSellDeliveryVo <br/>
	 * void<br/>
	 * <b>创 建 人：</b>chengpeng<br/>
	 * <b>创建时间:</b>2017年9月15日 上午10:46:25
	 * <b>修 改 人：</b>chengpeng<br/>
	 * <b>修改时间:</b>2017年9月15日 上午10:46:25
	 * @since  1.0.0
	 */
	public void submitByYm(SmSellDeliveryVo smSellDeliveryVo);

}