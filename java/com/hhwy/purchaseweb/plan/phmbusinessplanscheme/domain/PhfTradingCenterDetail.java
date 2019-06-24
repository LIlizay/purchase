package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain;

import java.math.BigDecimal;

public class PhfTradingCenterDetail {

    private String id;
    
    private String ym;
    
    private BigDecimal avgDealPrc;
    
    private BigDecimal compDealAvgPrc;
    
    private BigDecimal consDealAvgPrc;

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

    public BigDecimal getAvgDealPrc() {
        return avgDealPrc;
    }

    public void setAvgDealPrc(BigDecimal avgDealPrc) {
        this.avgDealPrc = avgDealPrc;
    }

    public BigDecimal getCompDealAvgPrc() {
        return compDealAvgPrc;
    }

    public void setCompDealAvgPrc(BigDecimal compDealAvgPrc) {
        this.compDealAvgPrc = compDealAvgPrc;
    }

    public BigDecimal getConsDealAvgPrc() {
        return consDealAvgPrc;
    }

    public void setConsDealAvgPrc(BigDecimal consDealAvgPrc) {
        this.consDealAvgPrc = consDealAvgPrc;
    }
    
}
