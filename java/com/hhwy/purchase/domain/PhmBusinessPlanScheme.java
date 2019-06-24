package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmBusinessPlanScheme
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmBusinessPlanScheme")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_business_plan_scheme")
public class PhmBusinessPlanScheme extends Domain implements Serializable {

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
    
    @PropertyDesc("长协电量")
    @Column(name="lc_pq", nullable=true, length=18) 
    private java.math.BigDecimal lcPq;
    
    @PropertyDesc("长协电价")
    @Column(name="lc_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcPrc;
    
    @PropertyDesc("长协电量占比")
    @Column(name="lc_prop", nullable=true, length=5) 
    private java.math.BigDecimal lcProp;
    
    @PropertyDesc("竞价电量")
    @Column(name="bid_pq", nullable=true, length=18) 
    private java.math.BigDecimal bidPq;
    
    @PropertyDesc("竞价电价")
    @Column(name="bid_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidPrc;
    
    @PropertyDesc("竞价电量占比")
    @Column(name="bid_prop", nullable=true, length=5) 
    private java.math.BigDecimal bidProp;
    
    @PropertyDesc("利润")
    @Column(name="profit", nullable=true, length=18) 
    private java.math.BigDecimal profit;
    
    @PropertyDesc("长协经验电价")
    @Column(name="lc_experience_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcExperiencePrc;
    
    @PropertyDesc("竞价经验电价")
    @Column(name="bid_experience_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidExperiencePrc;
    
    @PropertyDesc("利润指标")
    @Column(name="profit_norm", nullable=true, length=18) 
    private java.math.BigDecimal profitNorm;
    
    @PropertyDesc("差值利润")
    @Column(name="dvalue_profit", nullable=true, length=18) 
    private java.math.BigDecimal dvalueProfit;
    
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
    
    public java.math.BigDecimal getProfit(){
        return profit;
    }
    
    public void setProfit(java.math.BigDecimal profit){
        this.profit = profit;
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
    
    public java.math.BigDecimal getProfitNorm(){
        return profitNorm;
    }
    
    public void setProfitNorm(java.math.BigDecimal profitNorm){
        this.profitNorm = profitNorm;
    }
    
    public java.math.BigDecimal getDvalueProfit(){
        return dvalueProfit;
    }
    
    public void setDvalueProfit(java.math.BigDecimal dvalueProfit){
        this.dvalueProfit = dvalueProfit;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}