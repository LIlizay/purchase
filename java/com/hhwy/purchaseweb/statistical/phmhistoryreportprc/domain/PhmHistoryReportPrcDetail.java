package com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain;

public class PhmHistoryReportPrcDetail {

    private String month;
    
    private java.math.BigDecimal prodMaxPrc;
    
    private java.math.BigDecimal prodMinPrc;
    
    private java.math.BigDecimal prodMaxBidPrc;
    
    private java.math.BigDecimal useMaxPrc;
    
    private java.math.BigDecimal useMinPrc;
    
    private java.math.BigDecimal useMinBidPrc;

    /** 申报电价  **/
    private java.math.BigDecimal reportPrc;
    
    /** 成交电价  **/
    private java.math.BigDecimal dealPrc;
    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public java.math.BigDecimal getProdMaxPrc() {
        return prodMaxPrc;
    }

    public void setProdMaxPrc(java.math.BigDecimal prodMaxPrc) {
        this.prodMaxPrc = prodMaxPrc;
    }

    public java.math.BigDecimal getProdMinPrc() {
        return prodMinPrc;
    }

    public void setProdMinPrc(java.math.BigDecimal prodMinPrc) {
        this.prodMinPrc = prodMinPrc;
    }

    public java.math.BigDecimal getProdMaxBidPrc() {
        return prodMaxBidPrc;
    }

    public void setProdMaxBidPrc(java.math.BigDecimal prodMaxBidPrc) {
        this.prodMaxBidPrc = prodMaxBidPrc;
    }

    public java.math.BigDecimal getUseMaxPrc() {
        return useMaxPrc;
    }

    public void setUseMaxPrc(java.math.BigDecimal useMaxPrc) {
        this.useMaxPrc = useMaxPrc;
    }

    public java.math.BigDecimal getUseMinPrc() {
        return useMinPrc;
    }

    public void setUseMinPrc(java.math.BigDecimal useMinPrc) {
        this.useMinPrc = useMinPrc;
    }

    public java.math.BigDecimal getUseMinBidPrc() {
        return useMinBidPrc;
    }

    public void setUseMinBidPrc(java.math.BigDecimal useMinBidPrc) {
        this.useMinBidPrc = useMinBidPrc;
    }

    public java.math.BigDecimal getReportPrc() {
        return reportPrc;
    }

    public void setReportPrc(java.math.BigDecimal reportPrc) {
        this.reportPrc = reportPrc;
    }

    public java.math.BigDecimal getDealPrc() {
        return dealPrc;
    }

    public void setDealPrc(java.math.BigDecimal dealPrc) {
        this.dealPrc = dealPrc;
    }
}
