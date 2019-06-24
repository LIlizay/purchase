package com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain;

public class PhmHistoryReportPqDetail {

    
    private String ym;
    
    private java.math.BigDecimal pqProp;
    
    private String pqType;
    
    private String statisticType;

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public java.math.BigDecimal getPqProp() {
        return pqProp;
    }

    public void setPqProp(java.math.BigDecimal pqProp) {
        this.pqProp = pqProp;
    }

    public String getPqType() {
        return pqType;
    }

    public void setPqType(String pqType) {
        this.pqType = pqType;
    }

    public String getStatisticType() {
        return statisticType;
    }

    public void setStatisticType(String statisticType) {
        this.statisticType = statisticType;
    }
}
