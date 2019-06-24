package com.hhwy.selling.domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

/**
 * ScConsElectricity
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScConsElectricity")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_cons_electricity")
public class ScConsElectricity extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户标识")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("尖峰电量")
    @Column(name="over_peak_pq", nullable=true, length=16) 
    private BigDecimal overPeakPq;
    
    @PropertyDesc("尖峰电费")
    @Column(name="over_peak_amt", nullable=true, length=12) 
    private BigDecimal overPeakAmt;
    
    @PropertyDesc("峰时电量")
    @Column(name="peak_pq", nullable=true, length=16) 
    private BigDecimal peakPq;
    
    @PropertyDesc("峰时电费")
    @Column(name="peak_amt", nullable=true, length=12) 
    private BigDecimal peakAmt;
    
    @PropertyDesc("双蓄电量")
    @Column(name="double_pq", nullable=true, length=16) 
    private BigDecimal doublePq;
    
    @PropertyDesc("双蓄电费")
    @Column(name="double_amt", nullable=true, length=12) 
    private BigDecimal doubleAmt;
    
    @PropertyDesc("谷时电量")
    @Column(name="valley_pq", nullable=true, length=16) 
    private BigDecimal valleyPq;
    
    @PropertyDesc("谷时电费")
    @Column(name="valley_amt", nullable=true, length=12) 
    private BigDecimal valleyAmt;
    
    @PropertyDesc("平时电量")
    @Column(name="plain_pq", nullable=true, length=16) 
    private BigDecimal plainPq;
    
    @PropertyDesc("平时电费")
    @Column(name="plain_amt", nullable=true, length=12) 
    private BigDecimal plainAmt;
    
    @PropertyDesc("基本电费")
    @Column(name="base_amt", nullable=true, length=12) 
    private BigDecimal baseAmt;
    
    @PropertyDesc("力调电费")
    @Column(name="force_amt", nullable=true, length=12) 
    private BigDecimal forceAmt;
    
    @PropertyDesc("代征电费")
    @Column(name="levy_amt", nullable=true, length=12) 
    private BigDecimal levyAmt;
    
    @PropertyDesc("实际用电量")
    @Column(name="practical_pq", nullable=true, length=18) 
    private BigDecimal practicalPq;
    
    @PropertyDesc("其他电费")
    @Column(name="other_amt", nullable=true, length=12) 
    private BigDecimal otherAmt;
    
    @PropertyDesc("总电费")
    @Column(name="total_amt", nullable=true, length=18) 
    private BigDecimal totalAmt;
    
    @PropertyDesc("大数据预测电量")
    @Column(name="data_forecast_pq", nullable=true, length=18) 
    private BigDecimal dataForecastPq;
    
    @PropertyDesc("人工预测电量")
    @Column(name="person_forecast_pq", nullable=true, length=18) 
    private BigDecimal personForecastPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("备注")
    @Column(name="remark", nullable=true, length=255) 
    private String remark;
    
    public BigDecimal getOverPeakPq() {
		return overPeakPq;
	}

	public void setOverPeakPq(BigDecimal overPeakPq) {
		this.overPeakPq = overPeakPq;
	}

	public BigDecimal getOverPeakAmt() {
		return overPeakAmt;
	}

	public void setOverPeakAmt(BigDecimal overPeakAmt) {
		this.overPeakAmt = overPeakAmt;
	}

	public BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public BigDecimal getDoubleAmt() {
		return doubleAmt;
	}

	public void setDoubleAmt(BigDecimal doubleAmt) {
		this.doubleAmt = doubleAmt;
	}

	public BigDecimal getBaseAmt() {
		return baseAmt;
	}

	public void setBaseAmt(BigDecimal baseAmt) {
		this.baseAmt = baseAmt;
	}

	public BigDecimal getForceAmt() {
		return forceAmt;
	}

	public void setForceAmt(BigDecimal forceAmt) {
		this.forceAmt = forceAmt;
	}

	public BigDecimal getLevyAmt() {
		return levyAmt;
	}

	public void setLevyAmt(BigDecimal levyAmt) {
		this.levyAmt = levyAmt;
	}

	public BigDecimal getDataForecastPq() {
		return dataForecastPq;
	}

	public void setDataForecastPq(BigDecimal dataForecastPq) {
		this.dataForecastPq = dataForecastPq;
	}

	public BigDecimal getPersonForecastPq() {
		return personForecastPq;
	}

	public void setPersonForecastPq(BigDecimal personForecastPq) {
		this.personForecastPq = personForecastPq;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public BigDecimal getPeakPq(){
        return peakPq;
    }
    
    public void setPeakPq(BigDecimal peakPq){
        this.peakPq = peakPq;
    }
    
    public BigDecimal getPeakAmt(){
        return peakAmt;
    }
    
    public void setPeakAmt(BigDecimal peakAmt){
        this.peakAmt = peakAmt;
    }
    
    public BigDecimal getValleyPq(){
        return valleyPq;
    }
    
    public void setValleyPq(BigDecimal valleyPq){
        this.valleyPq = valleyPq;
    }
    
    public BigDecimal getValleyAmt(){
        return valleyAmt;
    }
    
    public void setValleyAmt(BigDecimal valleyAmt){
        this.valleyAmt = valleyAmt;
    }
    
    public BigDecimal getPlainPq(){
        return plainPq;
    }
    
    public void setPlainPq(BigDecimal plainPq){
        this.plainPq = plainPq;
    }
    
    public BigDecimal getPlainAmt(){
        return plainAmt;
    }
    
    public void setPlainAmt(BigDecimal plainAmt){
        this.plainAmt = plainAmt;
    }
    
    public BigDecimal getOtherAmt(){
        return otherAmt;
    }
    
    public void setOtherAmt(BigDecimal otherAmt){
        this.otherAmt = otherAmt;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public BigDecimal getPracticalPq() {
		return practicalPq;
	}

	public void setPracticalPq(BigDecimal practicalPq) {
		this.practicalPq = practicalPq;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
}