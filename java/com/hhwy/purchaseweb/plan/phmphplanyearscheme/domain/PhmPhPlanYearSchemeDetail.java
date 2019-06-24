package com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain;

public class PhmPhPlanYearSchemeDetail {

    private String id;
    
    private String year;
    
    private String planName;
    
    /** 显示名称  **/
    private String name;
    
    /**  计划标识  **/
    private String planId;
    
    /** 方案id **/
    private String schemeId;
    
    /**  方案名称  **/
    private String schemeName;
    
    /**  制定人  **/
    private String setters;
    
    /**  用户数  **/
    private int consSum;
    
    /**  合同总电量  **/
    private java.math.BigDecimal agrePq;
    
    /**  合同总销售额  **/
    private java.math.BigDecimal agreAmt;
    
    /**  平均代理单价  **/
    private java.math.BigDecimal avgPrc;
    
    /**  购电成本单价  **/
    private java.math.BigDecimal costPrc;
    
    /**  长协电量  **/
    private java.math.BigDecimal lcPq;
    
    /**  长协电价  **/
    private java.math.BigDecimal lcPrc;
    
    /**  长协电量占比  **/
    private java.math.BigDecimal lcProp;
    
    /**  最终长协电量占比  **/
    private java.math.BigDecimal lcFinalProp;
    
    /**  竞价电量  **/
    private java.math.BigDecimal bidPq;
    
    /**  竞价电价  **/
    private java.math.BigDecimal bidPrc;
    
    /**  竞价电量占比  **/
    private java.math.BigDecimal bidProp;
    
    /**  最终竞价电量占比  **/
    private java.math.BigDecimal bidFinalProp;
    
    /**  年度利润指标  **/
    private java.math.BigDecimal profitIndex;
    
    /**  长协经验电价  **/
    private java.math.BigDecimal lcExperiencePrc;
    
    /**  竞价经验电价  **/
    private java.math.BigDecimal bidExperiencePrc;
    
    /**  差值电量  **/
    private java.math.BigDecimal dvaluePq;
    
    /**  部门id  **/
    private String orgNo;

    /**
     * 附加利润
     */
    private java.math.BigDecimal addProfit;
    
    /**
     * 差值利润
     */
    private java.math.BigDecimal differenceProfit;
    
    
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

    public String getPlanId() {
        return planId;
    }

    public void setPlanId(String planId) {
        this.planId = planId;
    }

    public String getSchemeName() {
        return schemeName;
    }

    public void setSchemeName(String schemeName) {
        this.schemeName = schemeName;
    }

    public java.math.BigDecimal getAgrePq() {
        return agrePq;
    }

    public void setAgrePq(java.math.BigDecimal agrePq) {
        this.agrePq = agrePq;
    }

    public java.math.BigDecimal getAgreAmt() {
        return agreAmt;
    }

    public void setAgreAmt(java.math.BigDecimal agreAmt) {
        this.agreAmt = agreAmt;
    }

    public java.math.BigDecimal getAvgPrc() {
        return avgPrc;
    }

    public void setAvgPrc(java.math.BigDecimal avgPrc) {
        this.avgPrc = avgPrc;
    }

    public java.math.BigDecimal getCostPrc() {
        return costPrc;
    }

    public void setCostPrc(java.math.BigDecimal costPrc) {
        this.costPrc = costPrc;
    }

    public java.math.BigDecimal getLcPq() {
        return lcPq;
    }

    public void setLcPq(java.math.BigDecimal lcPq) {
        this.lcPq = lcPq;
    }

    public java.math.BigDecimal getLcPrc() {
        return lcPrc;
    }

    public void setLcPrc(java.math.BigDecimal lcPrc) {
        this.lcPrc = lcPrc;
    }

    public java.math.BigDecimal getLcProp() {
        return lcProp;
    }

    public void setLcProp(java.math.BigDecimal lcProp) {
        this.lcProp = lcProp;
    }

    public java.math.BigDecimal getLcFinalProp() {
        return lcFinalProp;
    }

    public void setLcFinalProp(java.math.BigDecimal lcFinalProp) {
        this.lcFinalProp = lcFinalProp;
    }

    public java.math.BigDecimal getBidPq() {
        return bidPq;
    }

    public void setBidPq(java.math.BigDecimal bidPq) {
        this.bidPq = bidPq;
    }

    public java.math.BigDecimal getBidPrc() {
        return bidPrc;
    }

    public void setBidPrc(java.math.BigDecimal bidPrc) {
        this.bidPrc = bidPrc;
    }

    public java.math.BigDecimal getBidProp() {
        return bidProp;
    }

    public void setBidProp(java.math.BigDecimal bidProp) {
        this.bidProp = bidProp;
    }

    public java.math.BigDecimal getBidFinalProp() {
        return bidFinalProp;
    }

    public void setBidFinalProp(java.math.BigDecimal bidFinalProp) {
        this.bidFinalProp = bidFinalProp;
    }

    public java.math.BigDecimal getProfitIndex() {
        return profitIndex;
    }

    public void setProfitIndex(java.math.BigDecimal profitIndex) {
        this.profitIndex = profitIndex;
    }

    public java.math.BigDecimal getLcExperiencePrc() {
        return lcExperiencePrc;
    }

    public void setLcExperiencePrc(java.math.BigDecimal lcExperiencePrc) {
        this.lcExperiencePrc = lcExperiencePrc;
    }

    public java.math.BigDecimal getBidExperiencePrc() {
        return bidExperiencePrc;
    }

    public void setBidExperiencePrc(java.math.BigDecimal bidExperiencePrc) {
        this.bidExperiencePrc = bidExperiencePrc;
    }

    public java.math.BigDecimal getDvaluePq() {
        return dvaluePq;
    }

    public void setDvaluePq(java.math.BigDecimal dvaluePq) {
        this.dvaluePq = dvaluePq;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    public String getSetters() {
        return setters;
    }

    public void setSetters(String setters) {
        this.setters = setters;
    }

    public int getConsSum() {
        return consSum;
    }

    public void setConsSum(int consSum) {
        this.consSum = consSum;
    }

    public java.math.BigDecimal getAddProfit() {
        return addProfit;
    }

    public void setAddProfit(java.math.BigDecimal addProfit) {
        this.addProfit = addProfit;
    }

    public java.math.BigDecimal getDifferenceProfit() {
        return differenceProfit;
    }

    public void setDifferenceProfit(java.math.BigDecimal differenceProfit) {
        this.differenceProfit = differenceProfit;
    }
    
}
