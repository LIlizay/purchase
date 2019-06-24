package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmPhPlanYearScheme
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmPhPlanYearScheme")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_ph_plan_year_scheme")
public class PhmPhPlanYearScheme extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("计划标识")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("方案名称")
    @Column(name="scheme_name", nullable=true, length=256) 
    private String schemeName;
    
    @PropertyDesc("合同总电量")
    @Column(name="agre_pq", nullable=true, length=18) 
    private java.math.BigDecimal agrePq;
    
    @PropertyDesc("合同总销售额")
    @Column(name="agre_amt", nullable=true, length=18) 
    private java.math.BigDecimal agreAmt;
    
    @PropertyDesc("平均代理单价")
    @Column(name="avg_prc", nullable=true, length=10) 
    private java.math.BigDecimal avgPrc;
    
    @PropertyDesc("购电成本单价")
    @Column(name="cost_prc", nullable=true, length=10) 
    private java.math.BigDecimal costPrc;
    
    @PropertyDesc("长协电量")
    @Column(name="lc_pq", nullable=true, length=18) 
    private java.math.BigDecimal lcPq;
    
    @PropertyDesc("长协电价")
    @Column(name="lc_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcPrc;
    
    @PropertyDesc("长协电量占比")
    @Column(name="lc_prop", nullable=true, length=5) 
    private java.math.BigDecimal lcProp;
    
    @PropertyDesc("最终长协电量占比")
    @Column(name="lc_final_prop", nullable=true, length=5) 
    private java.math.BigDecimal lcFinalProp;
    
    @PropertyDesc("竞价电量")
    @Column(name="bid_pq", nullable=true, length=18) 
    private java.math.BigDecimal bidPq;
    
    @PropertyDesc("竞价电价")
    @Column(name="bid_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidPrc;
    
    @PropertyDesc("竞价电量占比")
    @Column(name="bid_prop", nullable=true, length=5) 
    private java.math.BigDecimal bidProp;
    
    @PropertyDesc("最终竞价电量占比")
    @Column(name="bid_final_prop", nullable=true, length=5) 
    private java.math.BigDecimal bidFinalProp;
    
    @PropertyDesc("年度利润指标")
    @Column(name="profit_index", nullable=true, length=18) 
    private java.math.BigDecimal profitIndex;
    
    @PropertyDesc("长协经验电价")
    @Column(name="lc_experience_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcExperiencePrc;
    
    @PropertyDesc("竞价经验电价")
    @Column(name="bid_experience_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidExperiencePrc;
    
    @PropertyDesc("竞价差值电量")
    @Column(name="dvalue_pq", nullable=true, length=18) 
    private java.math.BigDecimal dvaluePq;
    
    @PropertyDesc("竞价差值电量")
    @Column(name="bid_difference_pq", nullable=true, length=18) 
    private java.math.BigDecimal bidDifferencePq;
    
    @PropertyDesc("附加利润")
    @Column(name="add_profit", nullable=true, length=18) 
    private java.math.BigDecimal addProfit;
    
    @PropertyDesc("差值利润")
    @Column(name="difference_profit", nullable=true, length=18) 
    private java.math.BigDecimal differenceProfit;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
    public String getSchemeName(){
        return schemeName;
    }
    
    public void setSchemeName(String schemeName){
        this.schemeName = schemeName;
    }
    
    public java.math.BigDecimal getAgrePq(){
        return agrePq;
    }
    
    public void setAgrePq(java.math.BigDecimal agrePq){
        this.agrePq = agrePq;
    }
    
    public java.math.BigDecimal getAgreAmt(){
        return agreAmt;
    }
    
    public void setAgreAmt(java.math.BigDecimal agreAmt){
        this.agreAmt = agreAmt;
    }
    
    public java.math.BigDecimal getAvgPrc(){
        return avgPrc;
    }
    
    public void setAvgPrc(java.math.BigDecimal avgPrc){
        this.avgPrc = avgPrc;
    }
    
    public java.math.BigDecimal getCostPrc(){
        return costPrc;
    }
    
    public void setCostPrc(java.math.BigDecimal costPrc){
        this.costPrc = costPrc;
    }
    
    public java.math.BigDecimal getLcPq(){
        return lcPq;
    }
    
    public void setLcPq(java.math.BigDecimal lcPq){
        this.lcPq = lcPq;
    }
    
    public java.math.BigDecimal getLcPrc(){
        return lcPrc;
    }
    
    public void setLcPrc(java.math.BigDecimal lcPrc){
        this.lcPrc = lcPrc;
    }
    
    public java.math.BigDecimal getLcProp(){
        return lcProp;
    }
    
    public void setLcProp(java.math.BigDecimal lcProp){
        this.lcProp = lcProp;
    }
    
    public java.math.BigDecimal getLcFinalProp(){
        return lcFinalProp;
    }
    
    public void setLcFinalProp(java.math.BigDecimal lcFinalProp){
        this.lcFinalProp = lcFinalProp;
    }
    
    public java.math.BigDecimal getBidPq(){
        return bidPq;
    }
    
    public void setBidPq(java.math.BigDecimal bidPq){
        this.bidPq = bidPq;
    }
    
    public java.math.BigDecimal getBidPrc(){
        return bidPrc;
    }
    
    public void setBidPrc(java.math.BigDecimal bidPrc){
        this.bidPrc = bidPrc;
    }
    
    public java.math.BigDecimal getBidProp(){
        return bidProp;
    }
    
    public void setBidProp(java.math.BigDecimal bidProp){
        this.bidProp = bidProp;
    }
    
    public java.math.BigDecimal getBidFinalProp(){
        return bidFinalProp;
    }
    
    public void setBidFinalProp(java.math.BigDecimal bidFinalProp){
        this.bidFinalProp = bidFinalProp;
    }
    
    public java.math.BigDecimal getProfitIndex(){
        return profitIndex;
    }
    
    public void setProfitIndex(java.math.BigDecimal profitIndex){
        this.profitIndex = profitIndex;
    }
    
    public java.math.BigDecimal getLcExperiencePrc(){
        return lcExperiencePrc;
    }
    
    public void setLcExperiencePrc(java.math.BigDecimal lcExperiencePrc){
        this.lcExperiencePrc = lcExperiencePrc;
    }
    
    public java.math.BigDecimal getBidExperiencePrc(){
        return bidExperiencePrc;
    }
    
    public void setBidExperiencePrc(java.math.BigDecimal bidExperiencePrc){
        this.bidExperiencePrc = bidExperiencePrc;
    }
    
    public java.math.BigDecimal getDvaluePq(){
        return dvaluePq;
    }
    
    public void setDvaluePq(java.math.BigDecimal dvaluePq){
        this.dvaluePq = dvaluePq;
    }
    
    public java.math.BigDecimal getBidDifferencePq(){
        return bidDifferencePq;
    }
    
    public void setBidDifferencePq(java.math.BigDecimal bidDifferencePq){
        this.bidDifferencePq = bidDifferencePq;
    }
    
    public java.math.BigDecimal getAddProfit(){
        return addProfit;
    }
    
    public void setAddProfit(java.math.BigDecimal addProfit){
        this.addProfit = addProfit;
    }
    
    public java.math.BigDecimal getDifferenceProfit(){
        return differenceProfit;
    }
    
    public void setDifferenceProfit(java.math.BigDecimal differenceProfit){
        this.differenceProfit = differenceProfit;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}