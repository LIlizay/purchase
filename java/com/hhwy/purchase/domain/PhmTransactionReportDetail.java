package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * PhmTransactionReportDetail
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmTransactionReportDetail")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_transaction_report_detail")
public class PhmTransactionReportDetail extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("月度购电计划标识")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("申报电量")
    @Column(name="report_pq", nullable=true, length=16) 
    private BigDecimal reportPq;
    
    @PropertyDesc("申报电价")
    @Column(name="report_prc", nullable=true, length=16) 
    private BigDecimal reportPrc;
    
    @PropertyDesc("第几段")
    @Column(name="stage", nullable=true, length=11) 
    private int stage;
    
    @PropertyDesc("电压等级")
    @Column(name="volt_code", nullable=true, length=8) 
    private String voltCode;
    
    @PropertyDesc("合同转让方向")
    @Column(name="attorn_type", nullable=true, length=8) 
    private String attornType;
    
    public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
    public java.math.BigDecimal getReportPq(){
        return reportPq;
    }
    
    public void setReportPq(java.math.BigDecimal reportPq){
        this.reportPq = reportPq;
    }
    
	public BigDecimal getReportPrc() {
		return reportPrc;
	}

	public void setReportPrc(BigDecimal reportPrc) {
		this.reportPrc = reportPrc;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getAttornType() {
		return attornType;
	}

	public void setAttornType(String attornType) {
		this.attornType = attornType;
	}
    
}