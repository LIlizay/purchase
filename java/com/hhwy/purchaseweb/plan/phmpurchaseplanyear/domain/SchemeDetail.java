package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain;

import java.math.BigDecimal;

import com.hhwy.purchase.domain.PhmForecastPrc;

public class SchemeDetail {
    
    /** 合同总电量 **/
    private BigDecimal agrePq;
    
    /** 合同总销售额 **/
    private BigDecimal agreAmt;
    
    /** 平均代理单价 **/
    private BigDecimal avgPrc;
    
    /** 预测电价 **/
    private PhmForecastPrc phmForecastPrc;

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

    public BigDecimal getAvgPrc() {
        return avgPrc;
    }

    public void setAvgPrc(BigDecimal avgPrc) {
        this.avgPrc = avgPrc;
    }

    public PhmForecastPrc getPhmForecastPrc() {
        return phmForecastPrc;
    }

    public void setPhmForecastPrc(PhmForecastPrc phmForecastPrc) {
        this.phmForecastPrc = phmForecastPrc;
    }
}
