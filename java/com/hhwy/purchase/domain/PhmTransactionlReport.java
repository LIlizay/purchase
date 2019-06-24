package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmTransactionlReport
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmTransactionlReport")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_transactionl_report")
public class PhmTransactionlReport extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("高风险预测电价")
    @Column(name="high_prc", nullable=true, length=16) 
    private java.math.BigDecimal highPrc;
    
    @PropertyDesc("高风险预测电价上限")
    @Column(name="high_upper_prc", nullable=true, length=16) 
    private java.math.BigDecimal highUpperPrc;
    
    @PropertyDesc("高风险预测电价下限")
    @Column(name="high_lower_prc", nullable=true, length=16) 
    private java.math.BigDecimal highLowerPrc;
    
    @PropertyDesc("中风险预测电价")
    @Column(name="middle_prc", nullable=true, length=16) 
    private java.math.BigDecimal middlePrc;
    
    @PropertyDesc("中风险预测电价上限")
    @Column(name="middle_upper_prc", nullable=true, length=16) 
    private java.math.BigDecimal middleUpperPrc;
    
    @PropertyDesc("中风险预测电价下限")
    @Column(name="middle_lower_prc", nullable=true, length=16) 
    private java.math.BigDecimal middleLowerPrc;
    
    @PropertyDesc("低风险预测电价")
    @Column(name="low_prc", nullable=true, length=16) 
    private java.math.BigDecimal lowPrc;
    
    @PropertyDesc("低风险预测电价上限")
    @Column(name="low_upper_prc", nullable=true, length=16) 
    private java.math.BigDecimal lowUpperPrc;
    
    @PropertyDesc("低风险预测电价下限")
    @Column(name="low_lower_prc", nullable=true, length=16) 
    private java.math.BigDecimal lowLowerPrc;
    
    @PropertyDesc("申报电量")
    @Column(name="report_pq", nullable=true, length=16) 
    private java.math.BigDecimal reportPq;
    
    @PropertyDesc("申报电价")
    @Column(name="report_prc", nullable=true, length=16) 
    private java.math.BigDecimal reportPrc;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getHighPrc(){
        return highPrc;
    }
    
    public void setHighPrc(java.math.BigDecimal highPrc){
        this.highPrc = highPrc;
    }
    
    public java.math.BigDecimal getHighUpperPrc(){
        return highUpperPrc;
    }
    
    public void setHighUpperPrc(java.math.BigDecimal highUpperPrc){
        this.highUpperPrc = highUpperPrc;
    }
    
    public java.math.BigDecimal getHighLowerPrc(){
        return highLowerPrc;
    }
    
    public void setHighLowerPrc(java.math.BigDecimal highLowerPrc){
        this.highLowerPrc = highLowerPrc;
    }
    
    public java.math.BigDecimal getMiddlePrc(){
        return middlePrc;
    }
    
    public void setMiddlePrc(java.math.BigDecimal middlePrc){
        this.middlePrc = middlePrc;
    }
    
    public java.math.BigDecimal getMiddleUpperPrc(){
        return middleUpperPrc;
    }
    
    public void setMiddleUpperPrc(java.math.BigDecimal middleUpperPrc){
        this.middleUpperPrc = middleUpperPrc;
    }
    
    public java.math.BigDecimal getMiddleLowerPrc(){
        return middleLowerPrc;
    }
    
    public void setMiddleLowerPrc(java.math.BigDecimal middleLowerPrc){
        this.middleLowerPrc = middleLowerPrc;
    }
    
    public java.math.BigDecimal getLowPrc(){
        return lowPrc;
    }
    
    public void setLowPrc(java.math.BigDecimal lowPrc){
        this.lowPrc = lowPrc;
    }
    
    public java.math.BigDecimal getLowUpperPrc(){
        return lowUpperPrc;
    }
    
    public void setLowUpperPrc(java.math.BigDecimal lowUpperPrc){
        this.lowUpperPrc = lowUpperPrc;
    }
    
    public java.math.BigDecimal getLowLowerPrc(){
        return lowLowerPrc;
    }
    
    public void setLowLowerPrc(java.math.BigDecimal lowLowerPrc){
        this.lowLowerPrc = lowLowerPrc;
    }
    
    public java.math.BigDecimal getReportPq(){
        return reportPq;
    }
    
    public void setReportPq(java.math.BigDecimal reportPq){
        this.reportPq = reportPq;
    }
    
    public java.math.BigDecimal getReportPrc(){
        return reportPrc;
    }
    
    public void setReportPrc(java.math.BigDecimal reportPrc){
        this.reportPrc = reportPrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
}