package com.hhwy.purchaseweb.delivery.purchasesettle.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PurchaseSettleDetail{

    /** 年月  **/
    private String ym;
    
    /** 购电计划id  **/
    private String id;
    
    /** 购电计划名称  **/
    private String planName;
    
    /** 制定人 **/
    private String setters;
    
    /** 用户数 **/
    private String consNum;
    
    /** 申报电量（兆瓦时） **/
    private BigDecimal reportPq;
    
    /** 成交电量 **/
    private BigDecimal dealPq;
    
    /** 成交结算电费 **/
    private BigDecimal delivryPq;
    
    /** 状态**/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_monthBidStatus" ,field="planStatusName")
    private String planStatus;
    private String planStatusName;
    
    /** 供电单位  **/
    private String orgNo;
    
    /** 售电公司结算电费  **/
    private BigDecimal compBalanceAmt;
    
    /** 代缴费后售电公司利润  **/
    private BigDecimal sellCheckProfit;
    
    /** 售电公司利润  **/
    private BigDecimal sellProfit;

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

    public String getPlanName() {
        return planName;
    }
    
    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getSetters() {
        return setters;
    }

    public void setSetters(String setters) {
        this.setters = setters;
    }

    public BigDecimal getSellProfit() {
        return sellProfit;
    }

    public void setSellProfit(BigDecimal sellProfit) {
        this.sellProfit = sellProfit;
    }

    public BigDecimal getReportPq() {
        return reportPq;
    }

    public void setReportPq(BigDecimal reportPq) {
        this.reportPq = reportPq;
    }

	public String getConsNum() {
		return consNum;
	}

	public void setConsNum(String consNum) {
		this.consNum = consNum;
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

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getCompBalanceAmt() {
		return compBalanceAmt;
	}

	public void setCompBalanceAmt(BigDecimal compBalanceAmt) {
		this.compBalanceAmt = compBalanceAmt;
	}

	public BigDecimal getSellCheckProfit() {
		return sellCheckProfit;
	}

	public void setSellCheckProfit(BigDecimal sellCheckProfit) {
		this.sellCheckProfit = sellCheckProfit;
	}

	public String getPlanStatusName() {
		return planStatusName;
	}

	public void setPlanStatusName(String planStatusName) {
		this.planStatusName = planStatusName;
	}
}
