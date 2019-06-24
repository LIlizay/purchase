package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import java.math.BigDecimal;

import com.hhwy.framework.annotation.PropertyDesc;

public class MonthElecDetail {
	
	private String id;
	
	
	private String month;
	
	//平均电度电价,展示用，不存数据库
	private BigDecimal monthAvgPrc;
	
	
	@PropertyDesc("用户标识")
    private String consId;
    
    @PropertyDesc("年月")
    private String ym;
    
    @PropertyDesc("尖峰电量")
    private BigDecimal overPeakPq;
    
    @PropertyDesc("尖峰电费")
    private BigDecimal overPeakAmt;
    
    @PropertyDesc("峰时电量")
    private BigDecimal peakPq;
    
    @PropertyDesc("峰时电费")
    private BigDecimal peakAmt;
    
    @PropertyDesc("双蓄电量")
    private BigDecimal doublePq;
    
    @PropertyDesc("双蓄电费")
    private BigDecimal doubleAmt;
    
    @PropertyDesc("谷时电量")
    private BigDecimal valleyPq;
    
    @PropertyDesc("谷时电费")
    private BigDecimal valleyAmt;
    
    @PropertyDesc("平时电量")
    private BigDecimal plainPq;
    
    @PropertyDesc("平时电费")
    private BigDecimal plainAmt;
    
    @PropertyDesc("基本电费")
    private BigDecimal baseAmt;
    
    @PropertyDesc("力调电费")
    private BigDecimal forceAmt;
    
    @PropertyDesc("代征电费")
    private BigDecimal levyAmt;
    
    @PropertyDesc("实际用电量")
    private BigDecimal practicalPq;
    
    @PropertyDesc("其他电费")
    private BigDecimal otherAmt;
    
    @PropertyDesc("总电费")
    private BigDecimal totalAmt;
    
    @PropertyDesc("大数据预测电量")
    private BigDecimal dataForecastPq;
    
    @PropertyDesc("人工预测电量")
    private BigDecimal personForecastPq;
    
    @PropertyDesc("部门id")
    private String orgNo;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getMonthAvgPrc() {
		return monthAvgPrc;
	}

	public void setMonthAvgPrc(BigDecimal monthAvgPrc) {
		this.monthAvgPrc = monthAvgPrc;
	}

	public BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public BigDecimal getPeakAmt() {
		return peakAmt;
	}

	public void setPeakAmt(BigDecimal peakAmt) {
		this.peakAmt = peakAmt;
	}

	public BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public BigDecimal getValleyAmt() {
		return valleyAmt;
	}

	public void setValleyAmt(BigDecimal valleyAmt) {
		this.valleyAmt = valleyAmt;
	}

	public BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public BigDecimal getPlainAmt() {
		return plainAmt;
	}

	public void setPlainAmt(BigDecimal plainAmt) {
		this.plainAmt = plainAmt;
	}

	public BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

	public BigDecimal getOverPeakPq() {
		return overPeakPq;
	}

	public void setOverPeakPq(BigDecimal overPeakPq) {
		this.overPeakPq = overPeakPq;
	}

	public BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public BigDecimal getOverPeakAmt() {
		return overPeakAmt;
	}

	public void setOverPeakAmt(BigDecimal overPeakAmt) {
		this.overPeakAmt = overPeakAmt;
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

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
}
