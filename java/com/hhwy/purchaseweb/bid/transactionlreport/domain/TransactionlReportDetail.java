package com.hhwy.purchaseweb.bid.transactionlreport.domain;

import java.io.Serializable;

public class TransactionlReportDetail  implements Serializable{
    
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = 3132884173741066638L;

    /**  交易申报id **/
    private String id;
    
    /**  关系表标识 **/
    private String planId;
    
    private String izId;
    
    /** 用户id  **/
    private String consId;
    
    /** 用户性质  **/
    private String izName;
    
    /** 客户名称  **/
    private String name;
    
    /** 联系人名称  **/
    private String contactName;
    
    /**  联系人电话  **/
    private String contactPhone;
    
    /** 年月  **/
    private String ym;
    
    /** 合同电量  **/
    private java.math.BigDecimal agrePq;
    
    /** 预测电量  **/
    private java.math.BigDecimal forecastPq;
    
    /**  申报电量 **/
    private java.math.BigDecimal reportPq;
    
    /**  申报电价 **/
    private java.math.BigDecimal reportPrc;
    
    /**  高风险预测电价 **/
    private java.math.BigDecimal highPrc;
    
    /**  高风险预测电价上限 **/
    private java.math.BigDecimal highUpperPrc;
    
    /**  高风险预测电价下限 **/
    private java.math.BigDecimal highLowerPrc;
    
    /**  高风险预测范围  **/
    private String highRang;
    
    /**  中风险预测电价 **/
    private java.math.BigDecimal middlePrc;
    
    /**  中风险预测电价上限 **/
    private java.math.BigDecimal middleUpperPrc;
    
    /**  中风险预测电价下限 **/
    private java.math.BigDecimal middleLowerPrc;
    
    /**  中风险预测范围  **/
    private String middleRang;
    
    /**  低风险预测电价 **/
    private java.math.BigDecimal lowPrc;
    
    /**  低风险预测电价上限 **/
    private java.math.BigDecimal lowUpperPrc;
    
    /**  低风险预测电价下限 **/
    private java.math.BigDecimal lowLowerPrc;
    
    /**  低风险预测范围  **/
    private String lowRang;
    
    /** 成交电量    **/
    private java.math.BigDecimal dealPq;
    
    /** 成交电价    **/
    private java.math.BigDecimal dealPrc;
    
    /** 公示目录内序号   **/
    private String dealSeq;
    
    public String getIzId() {
		return izId;
	}

	public void setIzId(String izId) {
		this.izId = izId;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public java.math.BigDecimal getAgrePq() {
        return agrePq;
    }

    public void setAgrePq(java.math.BigDecimal agrePq) {
        this.agrePq = agrePq;
    }

    public java.math.BigDecimal getForecastPq() {
        return forecastPq;
    }

    public void setForecastPq(java.math.BigDecimal forecastPq) {
        this.forecastPq = forecastPq;
    }

    public java.math.BigDecimal getReportPq() {
        return reportPq;
    }

    public void setReportPq(java.math.BigDecimal reportPq) {
        this.reportPq = reportPq;
    }

    public java.math.BigDecimal getReportPrc() {
        return reportPrc;
    }

    public void setReportPrc(java.math.BigDecimal reportPrc) {
        this.reportPrc = reportPrc;
    }

    public java.math.BigDecimal getHighPrc() {
        return highPrc;
    }

    public void setHighPrc(java.math.BigDecimal highPrc) {
        this.highPrc = highPrc;
    }

    public java.math.BigDecimal getHighUpperPrc() {
        return highUpperPrc;
    }

    public void setHighUpperPrc(java.math.BigDecimal highUpperPrc) {
        this.highUpperPrc = highUpperPrc;
    }

    public java.math.BigDecimal getHighLowerPrc() {
        return highLowerPrc;
    }

    public void setHighLowerPrc(java.math.BigDecimal highLowerPrc) {
        this.highLowerPrc = highLowerPrc;
    }

    public String getHighRang() {
        return highRang;
    }

    public void setHighRang(String highRang) {
        this.highRang = highRang;
    }

    public java.math.BigDecimal getMiddlePrc() {
        return middlePrc;
    }

    public void setMiddlePrc(java.math.BigDecimal middlePrc) {
        this.middlePrc = middlePrc;
    }

    public java.math.BigDecimal getMiddleUpperPrc() {
        return middleUpperPrc;
    }

    public void setMiddleUpperPrc(java.math.BigDecimal middleUpperPrc) {
        this.middleUpperPrc = middleUpperPrc;
    }

    public java.math.BigDecimal getMiddleLowerPrc() {
        return middleLowerPrc;
    }

    public void setMiddleLowerPrc(java.math.BigDecimal middleLowerPrc) {
        this.middleLowerPrc = middleLowerPrc;
    }

    public String getMiddleRang() {
        return middleRang;
    }

    public void setMiddleRang(String middleRang) {
        this.middleRang = middleRang;
    }

    public java.math.BigDecimal getLowPrc() {
        return lowPrc;
    }

    public void setLowPrc(java.math.BigDecimal lowPrc) {
        this.lowPrc = lowPrc;
    }

    public java.math.BigDecimal getLowUpperPrc() {
        return lowUpperPrc;
    }

    public void setLowUpperPrc(java.math.BigDecimal lowUpperPrc) {
        this.lowUpperPrc = lowUpperPrc;
    }

    public java.math.BigDecimal getLowLowerPrc() {
        return lowLowerPrc;
    }

    public void setLowLowerPrc(java.math.BigDecimal lowLowerPrc) {
        this.lowLowerPrc = lowLowerPrc;
    }

    public String getLowRang() {
        return lowRang;
    }

    public void setLowRang(String lowRang) {
        this.lowRang = lowRang;
    }

    public java.math.BigDecimal getDealPq() {
        return dealPq;
    }

    public void setDealPq(java.math.BigDecimal dealPq) {
        this.dealPq = dealPq;
    }

    public java.math.BigDecimal getDealPrc() {
        return dealPrc;
    }

    public void setDealPrc(java.math.BigDecimal dealPrc) {
        this.dealPrc = dealPrc;
    }

    public String getIzName() {
        return izName;
    }

    public void setIzName(String izName) {
        this.izName = izName;
    }

    public String getDealSeq() {
        return dealSeq;
    }

    public void setDealSeq(String dealSeq) {
        this.dealSeq = dealSeq;
    }
}
