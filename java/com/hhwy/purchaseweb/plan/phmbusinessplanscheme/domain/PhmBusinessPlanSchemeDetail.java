package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain;

import java.math.BigDecimal;

public class PhmBusinessPlanSchemeDetail {

    private String id;
    
    /** 计划id **/
    private String planId;
    
    /**  年份   **/
    private String year;
    
    private String name;
    
    /**  计划名称  **/
    private String planName;
    
    /** 制定人  **/
    private String setters;
    
    /**  用户数量  **/
    private int consSum;
    
    /** 计划状态  **/
    private String planStatus;
    
    /** 预测总合同电量 **/
    private BigDecimal agrePq;
    
    /** 长协电量  **/
    private BigDecimal lcPq;
    
    /** 竞价电量  **/
    private BigDecimal bidPq;

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

    public BigDecimal getAgrePq() {
        return agrePq;
    }

    public void setAgrePq(BigDecimal agrePq) {
        this.agrePq = agrePq;
    }

    public BigDecimal getLcPq() {
        return lcPq;
    }

    public void setLcPq(BigDecimal lcPq) {
        this.lcPq = lcPq;
    }

    public BigDecimal getBidPq() {
        return bidPq;
    }

    public void setBidPq(BigDecimal bidPq) {
        this.bidPq = bidPq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConsSum() {
        return consSum;
    }

    public void setConsSum(int consSum) {
        this.consSum = consSum;
    }
}
