package com.hhwy.purchaseweb.delivery.smselldelivery.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class CalcDetail {
	
	@PropertyDesc("计划id")
	private String id;
	
    @PropertyDesc("年月")
    private String ym;
    
    @PropertyDesc("用户数") 
    private int consNum;
    
    @PropertyDesc("申报电量") 
    private BigDecimal reportPq;
    
    @PropertyDesc("成交电量") 
    private BigDecimal dealPq;
    
    @PropertyDesc("总结算电量") 
    private BigDecimal delivryPq;
    
    @PropertyDesc("计划状态code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_consCalcStatus" ,field="statusName")
    private String status;
    
    @PropertyDesc("计划状态")
    private String statusName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
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

	public BigDecimal getDealPq() {
		return dealPq;
	}

	public void setDealPq(BigDecimal dealPq) {
		this.dealPq = dealPq;
	}

	public BigDecimal getDelivryPq() {
		return delivryPq;
	}

	public void setDelivryPq(BigDecimal delivryPq) {
		this.delivryPq = delivryPq;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
    
    
}
