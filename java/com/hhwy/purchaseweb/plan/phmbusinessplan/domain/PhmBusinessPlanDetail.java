package com.hhwy.purchaseweb.plan.phmbusinessplan.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PhmBusinessPlanDetail {
    
    private String id;
    
    /** 计划id **/
    private String planId;
    
    /**  年份   **/
    private String year;
    
    /** 名称 **/
    private String name;
    
    /**  计划名称  **/
    private String planName;
    
    /** 制定人  **/
    private String setters;
    
    /** 计划状态  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_planState" ,field="planStatusName")
    private String planStatus;
    private String planStatusName;
    
    /** 用户数量  **/
    private Integer consSum;
    
    /** 预测合同总电量  **/
    private BigDecimal agrePq;
    
    /** 开关闭合状态   **/
    private String state;

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

    public String getPlanStatusName() {
        return planStatusName;
    }

    public void setPlanStatusName(String planStatusName) {
        this.planStatusName = planStatusName;
    }

    public BigDecimal getAgrePq() {
        return agrePq;
    }

    public void setAgrePq(BigDecimal agrePq) {
        this.agrePq = agrePq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
