package com.hhwy.purchaseweb.delivery.purchasesettle.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.common.Parameter;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmPurchasePlanMonthVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchaseSettleVo;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.PurchasesettleInfoDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.domain.SettleDetail;
import com.hhwy.purchaseweb.delivery.purchasesettle.service.PurchaseSettleService;

@Component
public class PurchaseSettleServiceImpl implements PurchaseSettleService{

    @Autowired
    DAO<?> dao;
    
    public void setDao(DAO<?> dao) {
        this.dao = dao;
    }
    
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
    public QueryResult<PurchaseSettleDetail> getPurchaseSettleByPage(PurchaseSettleVo purchaseSettleVo) throws Exception{
        QueryResult<PurchaseSettleDetail> queryResult = new QueryResult<PurchaseSettleDetail>();
        long total = getPurchaseSettleCountByParams(purchaseSettleVo);
        List<PurchaseSettleDetail> purchaseSettleDetailList = getPurchaseSettleListByParams(purchaseSettleVo);
        queryResult.setTotal(total);
        queryResult.setData(purchaseSettleDetailList);
        return queryResult;
    }
    
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
    @SuppressWarnings("unchecked")
    public List<PurchaseSettleDetail> getPurchaseSettleListByParams(PurchaseSettleVo purchaseSettleVo) throws Exception{
        if(purchaseSettleVo==null){
            purchaseSettleVo = new PurchaseSettleVo();
        }
        Parameter.isFilterData.set(true);
        List<PurchaseSettleDetail> list = (List<PurchaseSettleDetail>)dao.getBySql("purchasesettle.sql.getPurchaseSettleList",purchaseSettleVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 查询电费结算数量
     * getPurchaseSettleCountByParams(描述这个方法的作用)<br/>
     * @param purchaseSettleVo
     * @return 
     * Integer
     * @exception 
     * @since  1.0.0
     */
    public Integer getPurchaseSettleCountByParams(PurchaseSettleVo purchaseSettleVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("purchasesettle.sql.getPurchaseSettleCount",purchaseSettleVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 分页获取结算详细
     * getSettleDetailByPage(描述这个方法的作用)<br/>
     * @param PurchaseSettleVo
     * @return
     * @throws Exception 
     * QueryResult<SettleDetail>
     * @exception 
     * @since  1.0.0
     */
    public QueryResult<SettleDetail> getSettleDetailByPage(PurchaseSettleVo purchaseSettleVo) throws Exception{
        QueryResult<SettleDetail> queryResult = new QueryResult<SettleDetail>();
        long total = getSettleDetailCountByParams(purchaseSettleVo);
        List<SettleDetail> list = getSettleDetailListByParams(purchaseSettleVo);
        queryResult.setTotal(total);
        queryResult.setData(list);
        return queryResult;
    }
    
    /**
     * 获取结算详细列表
     * getSettleDetailByPage(描述这个方法的作用)<br/>
     * @param PurchaseSettleVo
     * @return
     * @throws Exception 
     * QueryResult<SettleDetail>
     * @exception 
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
    public List<SettleDetail> getSettleDetailListByParams(PurchaseSettleVo purchaseSettleVo) throws Exception{
        if(purchaseSettleVo==null){
            purchaseSettleVo = new PurchaseSettleVo();
        }
        Parameter.isFilterData.set(true);
        List<SettleDetail> list = (List<SettleDetail>)dao.getBySql("purchasesettle.sql.getSettleDetailList",purchaseSettleVo);
        Parameter.isFilterData.set(false);
        ConvertListUtil.convert(list);
        return list;
    }
    
    /**
     * 获取结算详细数量
     * getSettleDetailByPage(描述这个方法的作用)<br/>
     * @param PurchaseSettleVo
     * @return
     * @throws Exception 
     * QueryResult<SettleDetail>
     * @exception 
     * @since  1.0.0
     */
    public Integer getSettleDetailCountByParams(PurchaseSettleVo purchaseSettleVo){
    	Parameter.isFilterData.set(true);
        Object result = dao.getOneBySQL("purchasesettle.sql.getSettleDetailCount",purchaseSettleVo);
        Parameter.isFilterData.set(false);
        int total = result == null ? 0 : Integer.valueOf(result.toString());
        return total;
    }
    
    /**
     * 获取合计
     * getSum(描述这个方法的作用)<br/>
     * @param id
     * @return 
     * PurchaseSettleDetail
     * @exception 
     * @since  1.0.0
     */
    public PurchaseSettleDetail getSum(String id){
        return (PurchaseSettleDetail) dao.getOneBySQL("purchasesettle.sql.getSum", id);
    }

    /**
     * 获取已经归档的计划
     */
	@Override
	public QueryResult<PurchasesettleInfoDetail> getPurchasesettleInfo(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo)
			throws Exception {
		QueryResult<PurchasesettleInfoDetail> queryResult = new QueryResult<PurchasesettleInfoDetail>();
		long total = getPurchasesettleCountByParams(phmPurchasePlanMonthVo);
		List<PurchasesettleInfoDetail> purchasesettleInfoDetail = getPurchasesettleInfoByParams(phmPurchasePlanMonthVo);
		queryResult.setTotal(total);
		queryResult.setData(purchasesettleInfoDetail);
		return queryResult;
	}

	public Integer getPurchasesettleCountByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo){
		Parameter.isFilterData.set(true);
		Object result = dao.getOneBySQL("phmPurchasePlanMonth.sql.getPurchasesettleCountByParams",phmPurchasePlanMonthVo);
		Parameter.isFilterData.set(false);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}

	@SuppressWarnings("unchecked")
	public List<PurchasesettleInfoDetail> getPurchasesettleInfoByParams(PhmPurchasePlanMonthVo phmPurchasePlanMonthVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(phmPurchasePlanMonthVo == null){
			phmPurchasePlanMonthVo = new PhmPurchasePlanMonthVo();
		}
		Parameter.isFilterData.set(true);
		List<PurchasesettleInfoDetail> PurchasesettleInfoList = (List<PurchasesettleInfoDetail>)dao.getBySql("phmPurchasePlanMonth.sql.getPurchasesettleInfoByParams",phmPurchasePlanMonthVo);
		Parameter.isFilterData.set(false);
		ConvertListUtil.convert(PurchasesettleInfoList);
		return PurchasesettleInfoList;
	}
}
