package com.hhwy.purchaseweb.delivery.purchasesettle.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchasesettleInfoDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.SettleDetail;

public interface PurchaseSettleService {

    /**
     * 分页查询电费结算
     * getPurchaseSettleByPage(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return
     * @throws Exception 
     * QueryResult<PurchaseSettleDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<PurchaseSettleDetail> getPurchaseSettleByPage(PurchaseSettleVo purchaseSettleVo) throws Exception;
    
    /**
     * 查询电费结算列表
     * getPurchaseSettleListByParams(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return
     * @throws Exception 
     * List<PurchaseSettleDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<PurchaseSettleDetail> getPurchaseSettleListByParams(PurchaseSettleVo purchaseSettleVo) throws Exception;
    
    /**
     * 查询电费结算数量
     * getPurchaseSettleCountByParams(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getPurchaseSettleCountByParams(PurchaseSettleVo purchaseSettleVo);
    
   /**
    * 分页获取结算详细
    * getSettleDetailByPage(描述这个方法的作用)<br/>
    * @param purchaseSettleVo
    * @return
    * @throws Exception 
    * QueryResult<SettleDetail>
    * @exception 
    * @since  1.0.0
    */
    public QueryResult<SettleDetail> getSettleDetailByPage(PurchaseSettleVo purchaseSettleVo) throws Exception;
    
    /**
     * 获取结算详细列表
     * getSettleDetailListByParams(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return
     * @throws Exception 
     * List<SettleDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<SettleDetail> getSettleDetailListByParams(PurchaseSettleVo purchaseSettleVo) throws Exception;
    
    /**
     * 获取结算详细数量
     * getSettleDetailCountByParams(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getSettleDetailCountByParams(PurchaseSettleVo purchaseSettleVo);
    
    /**
     * 获取合计
     * getSum(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * PurchaseSettleDetail
     * @exception 
     * @since  1.0.0
     */
    public PurchaseSettleDetail getSum(String id);
    
    /**
     * @Title: getPurchasesettleInfo
     * @Description: 获取已经归档的计划
     * @param phmPurchasePlanMonthVo
     * @return
     * @throws Exception 
     * QueryResult<PurchasesettleInfoDetail>
     * <b>创 建 人：</b>sunqi<br/>
     * <b>创建时间:</b>2017年9月21日 下午3:20:35
     * <b>修 改 人：</b>sunqi<br/>
     * <b>修改时间:</b>2017年9月21日 下午3:20:35
     * @since  1.0.0
     */
    public QueryResult<PurchasesettleInfoDetail> getPurchasesettleInfo(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception;
}
