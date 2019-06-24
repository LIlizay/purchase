package com.hhwy.purchaseweb.bid.transactionlreport.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.PhmTransactionlReportVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlPriceHistoryDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportVo;

public interface TransactionlReportService {
    /**
     * 分页获取交易申报信息
     * getTransactionlReportByPage(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return
     * @throws Exception 
     * QueryResult<TransactionlReportDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<TransactionlReportDetail> getTransactionlReportByPage(TransactionlReportVo transactionlReportVo) throws Exception;
    
    /**
     * 根据查询条件获取交易申报列表
     * getPhmTransactionlReportListByParams(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo
     * @return
     * @throws Exception 
     * List<PhmTransactionlReport>
     * @exception 
     * @since  1.0.0
     */
    public List<TransactionlReportDetail> getTransactionlReportListByParams(TransactionlReportVo transactionlReportVo) throws Exception;
    
    /**
     * 根据查询条件获取交易申报列表
     * getPhmTransactionlReportCountByParams(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getTransactionlReportCountByParams(TransactionlReportVo transactionlReportVo);
    
    /**
     * 分页获取用户历史报价信息
     * getConsHistoryByPage(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return
     * @throws Exception 
     * QueryResult<TransactionlReportDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<TransactionlReportDetail> getConsHistoryByPage(TransactionlReportVo transactionlReportVo) throws Exception;
    
    /**
     * 根据查询条件获取用户历史报价列表  用户交易申报
     * getConsHistoryListByParams(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return
     * @throws Exception 
     * List<TransactionlReportDetail>
     * @exception 
     * @since  1.0.0
     */
    public List<TransactionlReportDetail> getConsHistoryListByParams(TransactionlReportVo transactionlReportVo) throws Exception;
    
    /**
     * 根据查询条件获取用户历史报价数量
     * getConsHistoryCountByParams(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getConsHistoryCountByParams(TransactionlReportVo transactionlReportVo);
    
    /**
     * 查询计划合计
     * getSum(描述这个方法的作用)<br/>
     * @param planId
     * @return 
     * Map<String,Object>
     * @exception 
     * @since  1.0.0
     */
    /*public Map<String,Object> getSum(String planId);*/
    
    /**
     * 提交前校验数据
     * check(描述这个方法的作用)<br/>
     * @param planId
     * @throws Exception 
     * void
     * @exception 
     * @since  1.0.0
     */
    /*public void check(String planId) throws Exception;;*/
    /**
     * 保存交易申报
     * save(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void savePhmTransactionlReport(PhmTransactionlReportVo phmTransactionlReportVo);
    
    /**
     * @Title: getTransactionlPriceHistoryInfo
     * @Description: 获取历史申报信息
     * @param phmPurchasePlanMonthVo
     * @return 
     * List<TransactionlPriceHistoryDetail>
     * <b>创 建 人：</b>sunqi<br/>
     * <b>创建时间:</b>2017年9月18日 下午8:22:08
     * <b>修 改 人：</b>sunqi<br/>
     * <b>修改时间:</b>2017年9月18日 下午8:22:08
     * @since  1.0.0
     */
    public List<TransactionlPriceHistoryDetail> getTransactionlPriceHistoryInfo(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo);
    
}
