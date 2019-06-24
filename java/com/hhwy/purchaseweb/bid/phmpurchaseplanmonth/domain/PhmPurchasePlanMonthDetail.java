package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * PhmPurchasePlanMonthDetail
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class PhmPurchasePlanMonthDetail {
	
	private String id;			//实体id
	
	private String planName;	//计划名称
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradePeriod" ,field="tradePeriod")
	private String period;
	private String tradePeriod;	//交易周期
    
	private String ym;			//交易时段
    
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradeVariety" ,field="tradeVariety")
    private String variety;
	private String tradeVariety;//交易品种
    
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradeMode" ,field="tradeMode")
    private String mode;
	private String tradeMode;	//交易方式
    
    private int consNum;		//用户数 
    
    private BigDecimal reportPq;//委托电量 
    
    private BigDecimal tranPq;	//申报电量
    
    private BigDecimal dealPq;	//成交电量
    
    private BigDecimal ppaPq;	//合同电量
    
    private String setters;		//制定人 
    
    
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_monthBidStatus" ,field="planStatusName")
    private String planStatus;
    private String planStatusName;	//计划状态code
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getTradePeriod() {
		return tradePeriod;
	}
	public void setTradePeriod(String tradePeriod) {
		this.tradePeriod = tradePeriod;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
	public String getTradeVariety() {
		return tradeVariety;
	}
	public void setTradeVariety(String tradeVariety) {
		this.tradeVariety = tradeVariety;
	}
	public String getTradeMode() {
		return tradeMode;
	}
	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}
	public int getConsNum() {
		return consNum;
	}
	public void setConsNum(int consNum) {
		this.consNum = consNum;
	}
	public BigDecimal getReportPq() {
		return reportPq;
	}
	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}
	public BigDecimal getTranPq() {
		return tranPq;
	}
	public void setTranPq(BigDecimal tranPq) {
		this.tranPq = tranPq;
	}
	public BigDecimal getDealPq() {
		return dealPq;
	}
	public void setDealPq(BigDecimal dealPq) {
		this.dealPq = dealPq;
	}
	public String getSetters() {
		return setters;
	}
	public void setSetters(String setters) {
		this.setters = setters;
	}
	public String getPlanStatus() {
		return planStatus;
	}
	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}
	public String getPlanStatusName() {
		return planStatusName;
	}
	public void setPlanStatusName(String planStatusName) {
		this.planStatusName = planStatusName;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public BigDecimal getPpaPq() {
		return ppaPq;
	}
	public void setPpaPq(BigDecimal ppaPq) {
		this.ppaPq = ppaPq;
	}
}