package com.hhwy.purchaseweb.archives.powerplant.domain;

import java.io.Serializable;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PowerPlantDetail implements Serializable{
    
     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = -3719907426818678999L;

    private String id;
    
    /** 电厂名称  **/
    private String elecName;
    
    /** 机组数量  **/
    private Integer generCount;
    
    /**  装机容量   **/
    private java.math.BigDecimal instCap;
    
    /** 电厂类型 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecType" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
    /** 发电集团  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecGenerationGroup" ,field="blocIdName")
    private String blocId;
    private String blocIdName;

    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="networkFlagName")
    private String networkFlag;
    private String networkFlagName;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElecName() {
        return elecName;
    }

    public void setElecName(String elecName) {
        this.elecName = elecName;
    }

    public Integer getGenerCount() {
        return generCount;
    }

    public void setGenerCount(Integer generCount) {
        this.generCount = generCount;
    }

    public java.math.BigDecimal getInstCap() {
        return instCap;
    }

    public void setInstCap(java.math.BigDecimal instCap) {
        this.instCap = instCap;
    }

    public String getElecTypeCode() {
        return elecTypeCode;
    }

    public void setElecTypeCode(String elecTypeCode) {
        this.elecTypeCode = elecTypeCode;
    }

    public String getBlocId() {
        return blocId;
    }

    public void setBlocId(String blocId) {
        this.blocId = blocId;
    }

    public String getElecTypeCodeName() {
        return elecTypeCodeName;
    }

    public void setElecTypeCodeName(String elecTypeCodeName) {
        this.elecTypeCodeName = elecTypeCodeName;
    }

    public String getBlocIdName() {
        return blocIdName;
    }

    public void setBlocIdName(String blocIdName) {
        this.blocIdName = blocIdName;
    }

    public String getNetworkFlag() {
        return networkFlag;
    }

    public void setNetworkFlag(String networkFlag) {
        this.networkFlag = networkFlag;
    }

    public String getNetworkFlagName() {
        return networkFlagName;
    }

    public void setNetworkFlagName(String networkFlagName) {
        this.networkFlagName = networkFlagName;
    }
    
}
