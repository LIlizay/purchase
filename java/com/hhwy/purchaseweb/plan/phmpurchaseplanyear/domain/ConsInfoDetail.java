package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class ConsInfoDetail {

    private String id;
    
    /**  用户名称  **/
    private String consName;

    /**  电压等级Code  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; 

    /** 合同电量 **/
    private BigDecimal proxyPq;

    /** 合同电价 **/
    private BigDecimal agrePrc;
    
    /** 预测合同电量 **/
    private BigDecimal forecastAgrePq;
    
    /** 预测合同电价 **/
    private BigDecimal forecastAgrePrc;
    
    /**  联系人  **/
    private String contName;

    /**  联系人电话  **/
    private String phone;
    
    /** 用电地址 **/
    private String elecAddr;

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

    public String getVoltCodeName() {
        return voltCodeName;
    }

    public void setVoltCodeName(String voltCodeName) {
        this.voltCodeName = voltCodeName;
    }

    public BigDecimal getProxyPq() {
        return proxyPq;
    }

    public void setProxyPq(BigDecimal proxyPq) {
        this.proxyPq = proxyPq;
    }

    public BigDecimal getAgrePrc() {
        return agrePrc;
    }

    public void setAgrePrc(BigDecimal agrePrc) {
        this.agrePrc = agrePrc;
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

    public String getElecAddr() {
        return elecAddr;
    }

    public void setElecAddr(String elecAddr) {
        this.elecAddr = elecAddr;
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
}
