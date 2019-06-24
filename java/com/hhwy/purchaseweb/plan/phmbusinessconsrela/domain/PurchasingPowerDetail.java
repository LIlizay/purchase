package com.hhwy.purchaseweb.plan.phmbusinessconsrela.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PurchasingPowerDetail {
    
    private String id;
    
    /**  用户名称  **/
    private String consName;
    
    private String consId;
    
    private String planId;
    
    /**  用户类型  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_consType" ,field="consTypeName")
    private String consType;
    private String consTypeName;
    
    /**  电压等级 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    /**  用电类型 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
    /** 预测合同用电量  **/
    private BigDecimal proxyPq;
    
    /** 负荷预测电量  **/
    private BigDecimal loadPredictionPq;
    
    /** 预测合同用电量  **/
    private BigDecimal forecastAgrePq;
    
    /** 预测合同电价  **/
    private BigDecimal forecastAgrePrc;
    
    /** 计划年-3用电量  **/
    private BigDecimal actPq1;
    
    /** 计划年-2用电价量  **/
    private BigDecimal actPq2;

    /** 计划年-1用电价量  **/
    private BigDecimal actPq3;
    
    /** 计划年-3合同电价  **/
    private BigDecimal hisAgrePrc1;
    
    /** 计划年-2合同电价  **/
    private BigDecimal hisAgrePrc2;
    
    /** 计划年-1合同电价  **/
    private BigDecimal hisAgrePrc3;
    
    /** 联系人  **/
    private String contName;
    
    /** 联系电话  **/
    private String phone;

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

    public String getConsType() {
        return consType;
    }

    public void setConsType(String consType) {
        this.consType = consType;
    }

    public String getConsTypeName() {
        return consTypeName;
    }

    public void setConsTypeName(String consTypeName) {
        this.consTypeName = consTypeName;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getVoltCodeName() {
        return voltCodeName;
    }

    public void setVoltCodeName(String voltCodeName) {
        this.voltCodeName = voltCodeName;
    }

    public String getElecTypeCode() {
        return elecTypeCode;
    }

    public void setElecTypeCode(String elecTypeCode) {
        this.elecTypeCode = elecTypeCode;
    }

    public String getElecTypeCodeName() {
        return elecTypeCodeName;
    }

    public void setElecTypeCodeName(String elecTypeCodeName) {
        this.elecTypeCodeName = elecTypeCodeName;
    }

    public BigDecimal getProxyPq() {
        return proxyPq;
    }

    public void setProxyPq(BigDecimal proxyPq) {
        this.proxyPq = proxyPq;
    }

    public BigDecimal getLoadPredictionPq() {
        return loadPredictionPq;
    }

    public void setLoadPredictionPq(BigDecimal loadPredictionPq) {
        this.loadPredictionPq = loadPredictionPq;
    }

    public BigDecimal getForecastAgrePq() {
        return forecastAgrePq;
    }

    public void setForecastAgrePq(BigDecimal forecastAgrePq) {
        this.forecastAgrePq = forecastAgrePq;
    }

    public BigDecimal getForecastAgrePrc() {
        return forecastAgrePrc;
    }

    public void setForecastAgrePrc(BigDecimal forecastAgrePrc) {
        this.forecastAgrePrc = forecastAgrePrc;
    }

    public BigDecimal getActPq1() {
        return actPq1;
    }

    public void setActPq1(BigDecimal actPq1) {
        this.actPq1 = actPq1;
    }

    public BigDecimal getActPq2() {
        return actPq2;
    }

    public void setActPq2(BigDecimal actPq2) {
        this.actPq2 = actPq2;
    }

    public BigDecimal getActPq3() {
        return actPq3;
    }

    public void setActPq3(BigDecimal actPq3) {
        this.actPq3 = actPq3;
    }

    public BigDecimal getHisAgrePrc1() {
        return hisAgrePrc1;
    }

    public void setHisAgrePrc1(BigDecimal hisAgrePrc1) {
        this.hisAgrePrc1 = hisAgrePrc1;
    }

    public BigDecimal getHisAgrePrc2() {
        return hisAgrePrc2;
    }

    public void setHisAgrePrc2(BigDecimal hisAgrePrc2) {
        this.hisAgrePrc2 = hisAgrePrc2;
    }

    public BigDecimal getHisAgrePrc3() {
        return hisAgrePrc3;
    }

    public void setHisAgrePrc3(BigDecimal hisAgrePrc3) {
        this.hisAgrePrc3 = hisAgrePrc3;
    }

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }
}
