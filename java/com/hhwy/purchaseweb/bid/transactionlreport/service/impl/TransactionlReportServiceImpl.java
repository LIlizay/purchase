package com.hhwy.purchaseweb.bid.transactionlreport.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.framework.util.StringUtils;
import com.hhwy.purchase.domain.PhmTransactionlReport;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.PhmTransactionlReportVo;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlPriceHistoryDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportDetail;
import com.hhwy.purchaseweb.bid.transactionlreport.domain.TransactionlReportVo;
import com.hhwy.purchaseweb.bid.transactionlreport.service.TransactionlReportService;

@Component
public class TransactionlReportServiceImpl implements TransactionlReportService{
    
    @Autowired
    DAO<?> dao;
    
/*    @Autowired
    PhmPurchasePlanMonthService phmPurchasePlanMonthService;
*/    
    public void setDao(DAO<?> dao) {
        this.dao = dao;
    }

    @Override
    public QueryResult<TransactionlReportDetail> getTransactionlReportByPage(TransactionlReportVo transactionlReportVo) throws Exception {
        QueryResult<TransactionlReportDetail> queryResult = new QueryResult<TransactionlReportDetail>();
        long total = getTransactionlReportCountByParams(transactionlReportVo);
        List<TransactionlReportDetail> transactionlReportList = getTransactionlReportListByParams(transactionlReportVo);
        queryResult.setTotal(total);
        queryResult.setData(transactionlReportList);
        return queryResult;
    }
    
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
    @Override
    @SuppressWarnings("unchecked")
    public List<TransactionlReportDetail> getTransactionlReportListByParams(TransactionlReportVo transactionlReportVo) throws Exception{
        List<TransactionlReportDetail> transactionlReportList = (List<TransactionlReportDetail>)dao.getBySql("transactionreport.sql.getTransactionReportList",transactionlReportVo);
        return transactionlReportList;
    }
    
    /**
     * 根据查询条件获取交易申报列表
     * getPhmTransactionlReportCountByParams(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Integer getTransactionlReportCountByParams(TransactionlReportVo transactionlReportVo){
        Object result = dao.getOneBySQL("transactionreport.sql.getTransactionReportCount",transactionlReportVo);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
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
    @Override
    public QueryResult<TransactionlReportDetail> getConsHistoryByPage(TransactionlReportVo transactionlReportVo) throws Exception{
        QueryResult<TransactionlReportDetail> queryResult = new QueryResult<TransactionlReportDetail>();
        if(StringUtils.isNull(transactionlReportVo.getConsId())){
            queryResult.setTotal(0L);
            queryResult.setData(new ArrayList<TransactionlReportDetail>());
            return queryResult; 
        }
        long total = getConsHistoryCountByParams(transactionlReportVo);
        List<TransactionlReportDetail> transactionlReportList = getConsHistoryListByParams(transactionlReportVo);
        queryResult.setTotal(total);
        queryResult.setData(transactionlReportList);
        return queryResult;
    }
    
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
    @Override
    @SuppressWarnings("unchecked")
    public List<TransactionlReportDetail> getConsHistoryListByParams(TransactionlReportVo transactionlReportVo) throws Exception{
        List<TransactionlReportDetail> transactionlReportList = (List<TransactionlReportDetail>)dao.getBySql("transactionreport.sql.getConsHistoryList",transactionlReportVo);
        return transactionlReportList;
    }
    
    /**
     * 根据查询条件获取用户历史报价数量
     * getConsHistoryCountByParams(描述这个方法的作用)<br/>
     * @param transactionlReportVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    @Override
    public Integer getConsHistoryCountByParams(TransactionlReportVo transactionlReportVo){
        Object result = dao.getOneBySQL("transactionreport.sql.getConsHistoryCount",transactionlReportVo);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 查询计划合计
     * getSum(描述这个方法的作用)<br/>
     * @param planId
     * @return 
     * Map<String,Object>
     * @exception 
     * @since  1.0.0
     */
   /* @SuppressWarnings("unchecked")
    public Map<String,Object> getSum(String planId){
        Map<String,Object> map = (Map<String, Object>) dao.getOneBySQL("transactionreport.sql.getSum",planId);
        return map;
    }*/
    
    /**
     * 提交前校验数据
     * check(描述这个方法的作用)<br/>
     * @param planId
     * @throws Exception 
     * void
     * @exception 
     * @since  1.0.0
     */
  /*  @SuppressWarnings("unchecked")
    @Override
    public void check(String planId) throws Exception{
        List<String> list = (List<String>) dao.getBySql("transactionreport.sql.check",planId);
        if(list.size()>0){
            throw new RuntimeException("请填写数据再提交！");
        }else{
            phmPurchasePlanMonthService.updatePlanStatusByPlanId(planId,BusinessContants.SELL_MONTHBIDSTATUS03);
        }
    }*/
    
    /**
     * 保存交易申报
     * save(描述这个方法的作用)<br/>
     * @param phmTransactionlReportVo 
     * void
     * @exception 
     * @since  1.0.0
     */
    @Override
    @Transactional
    public void savePhmTransactionlReport(PhmTransactionlReportVo phmTransactionlReportVo){
        for(PhmTransactionlReport phmTransactionlReport:phmTransactionlReportVo.getTransactionlReportList()){
            dao.save(phmTransactionlReport);
        }
    }

    /**
     * 获取历史申报信息
     */
	@Override
	@SuppressWarnings("unchecked")
	public List<TransactionlPriceHistoryDetail> getTransactionlPriceHistoryInfo(
			PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) {
		Parameter.isFilterData.set(true);
		List<TransactionlPriceHistoryDetail> list=(List<TransactionlPriceHistoryDetail>) dao.getBySql("transactionreport.sql.getTransactionlPriceHistory", phmPurchasePlanMonthVo);
		Parameter.isFilterData.set(false);
		List<TransactionlPriceHistoryDetail> resultList=new ArrayList<TransactionlPriceHistoryDetail>();
		if(list!=null&&list.size()>1){//只有list长度大于1，才有被合并的可能性
			int count=1;
			TransactionlPriceHistoryDetail transactionlPriceHistoryDetail=list.get(0);
			for(int i=1;i<list.size();i++){
				//当前数据与下一条数据属于同一阶段，可以合并
				if(transactionlPriceHistoryDetail.getStage()==list.get(i).getStage() && transactionlPriceHistoryDetail.getYm().equals(list.get(i).getYm())){
				    count++;
				}else{
					BigDecimal totalDealPq=null;
					BigDecimal totalDealPrc=null;
					if(list.get(i-1).getDealPq()!=null){
						totalDealPq=list.get(i-1).getDealPq();
						totalDealPrc=list.get(i-1).getDealPrc().multiply(list.get(i-1).getDealPq());
						for(int j=i-2;j>=i-count;j--){
							totalDealPq=totalDealPq.add(list.get(j).getDealPq());
							totalDealPrc=totalDealPrc.add(list.get(j).getDealPq().multiply(list.get(j).getDealPrc()));
						}
						totalDealPrc=totalDealPrc.divide(totalDealPq,2,BigDecimal.ROUND_HALF_DOWN);
					}
					transactionlPriceHistoryDetail.setReportPq(list.get(i-1).getReportPq());
					transactionlPriceHistoryDetail.setReportPrc(list.get(i-1).getReportPrc());
					transactionlPriceHistoryDetail.setStage(list.get(i-1).getStage());
					transactionlPriceHistoryDetail.setDealPq(totalDealPq);
					transactionlPriceHistoryDetail.setDealPrc(totalDealPrc);
					resultList.add(transactionlPriceHistoryDetail);
					transactionlPriceHistoryDetail=list.get(i);
					count=1;
				}
			}
			if(count>=1){
				BigDecimal totalDealPq=null;
				BigDecimal totalDealPrc=null;
				if(list.get(list.size()-1).getDealPq()!=null){
					totalDealPq=list.get(list.size()-1).getDealPq();
					totalDealPrc=list.get(list.size()-1).getDealPrc().multiply(list.get(list.size()-1).getDealPq());
					for(int t=list.size()-count;t<list.size()-1;t++){
						totalDealPq=totalDealPq.add(list.get(t).getDealPq());
						totalDealPrc=totalDealPrc.add(list.get(t).getDealPq().multiply(list.get(t).getDealPrc()));
					}
					totalDealPrc=totalDealPrc.divide(totalDealPq,2,BigDecimal.ROUND_HALF_DOWN);
				}
				transactionlPriceHistoryDetail.setReportPq(list.get(list.size()-1).getReportPq());
				transactionlPriceHistoryDetail.setReportPrc(list.get(list.size()-1).getReportPrc());
				transactionlPriceHistoryDetail.setStage(list.get(list.size()-1).getStage());
				transactionlPriceHistoryDetail.setDealPq(totalDealPq);
				transactionlPriceHistoryDetail.setDealPrc(totalDealPrc);
				resultList.add(transactionlPriceHistoryDetail);
			}
		}
		return resultList;
	}
}
