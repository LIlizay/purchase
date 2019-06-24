package com.hhwy.purchaseweb.delivery.purchasesettle.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SettleDetail implements Serializable{
    
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = 2183367044672539657L;

    /** 用户名称  **/
    private String consName;
    
    /** 代理结算电量 **/
    private BigDecimal proxyPq;
    
    /** 代理结算电费 **/
    private BigDecimal proxyAmt;
    
    /** 成交结算电量 **/
    private BigDecimal bidPq;
    
    /** 成交结算电费 **/
    private BigDecimal bidAmt;
    
    /** 偏差电量 **/
    private BigDecimal deviationPq;
    
    /** 偏差电费 **/
    private BigDecimal deviationAmt;
    
    /** 电压等级 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    /** 用电分类 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
    /** 联系人 **/
    private String contName;
    
    /** 联系人电话 **/
    private String contPhone;

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public BigDecimal getProxyPq() {
        return proxyPq;
    }

    public void setProxyPq(BigDecimal proxyPq) {
        this.proxyPq = proxyPq;
    }

    public BigDecimal getProxyAmt() {
        return proxyAmt;
    }

    public void setProxyAmt(BigDecimal proxyAmt) {
        this.proxyAmt = proxyAmt;
    }

    public BigDecimal getBidPq() {
        return bidPq;
    }

    public void setBidPq(BigDecimal bidPq) {
        this.bidPq = bidPq;
    }

    public BigDecimal getBidAmt() {
        return bidAmt;
    }

    public void setBidAmt(BigDecimal bidAmt) {
        this.bidAmt = bidAmt;
    }

    public BigDecimal getDeviationPq() {
        return deviationPq;
    }

    public void setDeviationPq(BigDecimal deviationPq) {
        this.deviationPq = deviationPq;
    }

    public BigDecimal getDeviationAmt() {
        return deviationAmt;
    }

    public void setDeviationAmt(BigDecimal deviationAmt) {
        this.deviationAmt = deviationAmt;
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

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public String getContPhone() {
        return contPhone;
    }

    public void setContPhone(String contPhone) {
        this.contPhone = contPhone;
    }
    
}
