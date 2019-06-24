package com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain;

import java.math.BigDecimal;

public class CalcVo {
    
    /** 最小利润 **/
    private BigDecimal minProfit;
    
    /** 最大利润 **/
    private BigDecimal maxProfit;
    
    /** 最小附加利润 **/
    private BigDecimal minAddProfit;
    
    /** 最大附加利润 **/
    private BigDecimal maxAddProfit;
    
    /** 最小差值利润 **/
    private BigDecimal minDifferenceProfit;
    
    /** 最大差值利润 **/
    private BigDecimal maxDifferenceProfit;
    
    /** 竞价差值电量  **/
    private BigDecimal bidDifferencePq;
    
    /** 长协电量 **/
    private BigDecimal lcPq;
    
    /** 长协电价 **/
    private BigDecimal lcPrc;
    
    /** 长协电量比例 **/
    private BigDecimal lcProp;
    
    /** 竞价电量 **/
    private BigDecimal bidPq;
    
    /** 竞价电价 **/
    private BigDecimal bidPrc;
    
    /** 竞价电量比例 **/
    private BigDecimal bidProp;

    public BigDecimal getMinProfit() {
        return minProfit;
    }

    public void setMinProfit(BigDecimal minProfit) {
        this.minProfit = minProfit;
    }

    public BigDecimal getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(BigDecimal maxProfit) {
        this.maxProfit = maxProfit;
    }

    public BigDecimal getMinAddProfit() {
        return minAddProfit;
    }

    public void setMinAddProfit(BigDecimal minAddProfit) {
        this.minAddProfit = minAddProfit;
    }

    public BigDecimal getMaxAddProfit() {
        return maxAddProfit;
    }

    public void setMaxAddProfit(BigDecimal maxAddProfit) {
        this.maxAddProfit = maxAddProfit;
    }

    public BigDecimal getMinDifferenceProfit() {
        return minDifferenceProfit;
    }

    public void setMinDifferenceProfit(BigDecimal minDifferenceProfit) {
        this.minDifferenceProfit = minDifferenceProfit;
    }

    public BigDecimal getMaxDifferenceProfit() {
        return maxDifferenceProfit;
    }

    public void setMaxDifferenceProfit(BigDecimal maxDifferenceProfit) {
        this.maxDifferenceProfit = maxDifferenceProfit;
    }

    public BigDecimal getLcPq() {
        return lcPq;
    }

    public void setLcPq(BigDecimal lcPq) {
        this.lcPq = lcPq;
    }

    public BigDecimal getLcPrc() {
        return lcPrc;
    }

    public void setLcPrc(BigDecimal lcPrc) {
        this.lcPrc = lcPrc;
    }

    public BigDecimal getLcProp() {
        return lcProp;
    }

    public void setLcProp(BigDecimal lcProp) {
        this.lcProp = lcProp;
    }

    public BigDecimal getBidPq() {
        return bidPq;
    }

    public void setBidPq(BigDecimal bidPq) {
        this.bidPq = bidPq;
    }

    public BigDecimal getBidPrc() {
        return bidPrc;
    }

    public void setBidPrc(BigDecimal bidPrc) {
        this.bidPrc = bidPrc;
    }

    public BigDecimal getBidProp() {
        return bidProp;
    }

    public void setBidProp(BigDecimal bidProp) {
        this.bidProp = bidProp;
    }

    public BigDecimal getBidDifferencePq() {
        return bidDifferencePq;
    }

    public void setBidDifferencePq(BigDecimal bidDifferencePq) {
        this.bidDifferencePq = bidDifferencePq;
    }
}
