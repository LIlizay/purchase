package com.hhwy.purchaseweb.forecast.phfcoalprice.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PhfCoalPriceDetail {
    
    private String id;
    
    /**  年月  **/
    private String ym;
    
    /**  种类  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_coalTypeCode" ,field="coalTypeName")
    private String coalType;
    private String coalTypeName;
    
    /**  价格  **/
    private java.math.BigDecimal price;
    
    /**  部门id  **/
    private String orgNo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getCoalType() {
        return coalType;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType;
    }

    public String getCoalTypeName() {
        return coalTypeName;
    }

    public void setCoalTypeName(String coalTypeName) {
        this.coalTypeName = coalTypeName;
    }

    public java.math.BigDecimal getPrice() {
        return price;
    }

    public void setPrice(java.math.BigDecimal price) {
        this.price = price;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
    
}
