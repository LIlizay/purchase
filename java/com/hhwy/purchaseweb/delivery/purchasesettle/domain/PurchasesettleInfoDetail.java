package com.hhwy.purchaseweb.delivery.purchasesettle.domain;

import java.math.BigDecimal;

import com.hhwy.framework.persistent.entity.Domain;

public class PurchasesettleInfoDetail extends Domain{

	private static final long serialVersionUID = 1L;
	//计划年月
	private String ym;
	
	//购电计划名称
	private String planName;
	
	//方案id
	private String schemeId;
	//方案名称
	private String schemeName;
	
	//用户数
	private int consNum;
		
	//委托电量（兆瓦时）
	private BigDecimal reportPq;
	
	//总购电量（兆瓦时）
	private BigDecimal purchasePq;
	
	
	//结算电量（兆瓦时）
	private BigDecimal delivryPq;
	
	//售电公司总利润（元）
	private BigDecimal compTotalProfit;
	
	//售电公司偏差考核（元）
	private BigDecimal compCheck;
	
	//用户净收益（元）
	private BigDecimal consProfit;
	
	//赔偿用户费用（元）
	private BigDecimal consCompensateAmt;
//王泽鲁添加
	//竞价成交电量
	private BigDecimal dealPq;
	//长协电量
	private BigDecimal ppaPq;
	//实际用电量
	private BigDecimal actualTotalPq;
	
	public BigDecimal getActualTotalPq() {
		return actualTotalPq;
	}
	public void setActualTotalPq(BigDecimal actualTotalPq) {
		this.actualTotalPq = actualTotalPq;
	}
	public BigDecimal getDealPq() {
		return dealPq;
	}
	public void setDealPq(BigDecimal dealPq) {
		this.dealPq = dealPq;
	}
	public BigDecimal getPpaPq() {
		return ppaPq;
	}
	public void setPpaPq(BigDecimal ppaPq) {
		this.ppaPq = ppaPq;
	}
	public BigDecimal getPurchasePq() {
		if(ppaPq == null || dealPq == null){
			return null;
		}
		if(purchasePq == null){
			return new BigDecimal(0).add(ppaPq).add(dealPq);
		}else{
			return purchasePq;
		}
	}
	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
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

	public void setPurchasePq(BigDecimal purchasePq) {
		this.purchasePq = purchasePq;
	}

	public BigDecimal getDelivryPq() {
		return delivryPq;
	}

	public void setDelivryPq(BigDecimal delivryPq) {
		this.delivryPq = delivryPq;
	}

	public BigDecimal getCompTotalProfit() {
		return compTotalProfit;
	}

	public void setCompTotalProfit(BigDecimal compTotalProfit) {
		this.compTotalProfit = compTotalProfit;
	}

	public BigDecimal getCompCheck() {
		return compCheck;
	}

	public void setCompCheck(BigDecimal compCheck) {
		this.compCheck = compCheck;
	}

	public BigDecimal getConsProfit() {
		return consProfit;
	}

	public void setConsProfit(BigDecimal consProfit) {
		this.consProfit = consProfit;
	}

	public BigDecimal getConsCompensateAmt() {
		return consCompensateAmt;
	}

	public void setConsCompensateAmt(BigDecimal consCompensateAmt) {
		this.consCompensateAmt = consCompensateAmt;
	}	
}
