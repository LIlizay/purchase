package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfElecReport
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfElecReport")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_elec_report")
public class PhfElecReport extends Domain implements Serializable {

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
    
    @PropertyDesc("交易中心信息实体id")
    @Column(name="center_id", nullable=true, length=32) 
    private String centerId;
    
    @PropertyDesc("申报区间")
    @Column(name="reporting_interval", nullable=true, length=16) 
    private String reportingInterval;
    
    @PropertyDesc("区间占比")
    @Column(name="interval_prop", nullable=true, length=8) 
    private java.math.BigDecimal intervalProp;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
    	this.ym = ym;
    }
    
    public String getCenterId(){
        return centerId;
    }
    
    public void setCenterId(String centerId){
    	this.centerId = centerId;
    }
    
    public String getReportingInterval(){
        return reportingInterval;
    }
    
    public void setReportingInterval(String reportingInterval){
    	this.reportingInterval = reportingInterval;
    }
    
    public java.math.BigDecimal getIntervalProp(){
        return intervalProp;
    }
    
    public void setIntervalProp(java.math.BigDecimal intervalProp){
    	this.intervalProp = intervalProp;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
    	this.orgNo = orgNo;
    }
    
}