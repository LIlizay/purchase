package com.hhwy.purchaseweb.bid.phmeleclist.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class ElecListDetail implements Serializable{
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = 7036577646855638190L;

    private String id;
    
    private String consId;
    
    /** 用户名称  **/
    private String consName;
    
    /** 电压等级 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    /** 供电单位 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_orgCode" ,field="orgIdName")
    private String orgId;
    private String orgIdName;
    
    /** 年月 **/
    private String ym;
    
    /** 实际用电量 **/
    private BigDecimal totalPq;
    
    /** 结算电量 **/
    private BigDecimal balancePq;
    
    /** 市场化交易前电费   **/
    private BigDecimal originalAmt;
    
    /** 市场化交易后电费   **/
    private BigDecimal totalAmt;
    
    /** 交易利润   **/
    private BigDecimal profit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public BigDecimal getTotalPq() {
        return totalPq;
    }

    public void setTotalPq(BigDecimal totalPq) {
        this.totalPq = totalPq;
    }

    public BigDecimal getBalancePq() {
        return balancePq;
    }

    public void setBalancePq(BigDecimal balancePq) {
        this.balancePq = balancePq;
    }

    public BigDecimal getOriginalAmt() {
        return originalAmt;
    }

    public void setOriginalAmt(BigDecimal originalAmt) {
        this.originalAmt = originalAmt;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }

    public String getVoltCodeName() {
        return voltCodeName;
    }

    public void setVoltCodeName(String voltCodeName) {
        this.voltCodeName = voltCodeName;
    }

    public String getOrgIdName() {
        return orgIdName;
    }

    public void setOrgIdName(String orgIdName) {
        this.orgIdName = orgIdName;
    }

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }
}
