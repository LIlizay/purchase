package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain;

import java.math.BigDecimal;


public class PurchasePlanYearDetail {
    
    private String id;
    
    /** 计划id **/
    private String planId;
    
    /** 显示名称  **/
    private String name;
    
    /**  年份   **/
    private String year;
    
    /**  计划名称  **/
    private String planName;
    
    /** 制定人  **/
    private String setters;
    
    /** 计划状态  **/
    private String planStatus;
    
    /** 用户数量  **/
    private Integer consSum;
    
    /** 开关闭合状态   **/
    private String state;
    
    private BigDecimal agrePq;
    
    private BigDecimal agreAmt;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public Integer getConsSum() {
        return consSum;
    }

    public void setConsSum(Integer consSum) {
        this.consSum = consSum;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public BigDecimal getAgrePq() {
        return agrePq;
    }

    public void setAgrePq(BigDecimal agrePq) {
        this.agrePq = agrePq;
    }

    public BigDecimal getAgreAmt() {
        return agreAmt;
    }

    public void setAgreAmt(BigDecimal agreAmt) {
        this.agreAmt = agreAmt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
