package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class ImportVo {
	
	//用户名称
	private String name;
	
    @PropertyDesc("用户标识")
    private String consId;
    
    /**  用电类型 **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
    @PropertyDesc("电压等级")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; 
    
    /*月度总用电量*/
    private BigDecimal monthPq;
    /*月度用电费*/
    private BigDecimal monthAmt;
    /*平均电度电价*/
    private BigDecimal monthAvgPrc;
    
    @PropertyDesc("年月")
    private String ym;
    
    @PropertyDesc("尖峰电量")
    private java.math.BigDecimal overPeakPq;
    
    @PropertyDesc("尖峰电费")
    private java.math.BigDecimal overPeakAmt;
    
    @PropertyDesc("峰时电量")
    private java.math.BigDecimal peakPq;
    
    @PropertyDesc("峰时电费")
    private java.math.BigDecimal peakAmt;
    
    @PropertyDesc("双蓄电量")
    private java.math.BigDecimal doublePq;
    
    @PropertyDesc("双蓄电费")
    private java.math.BigDecimal doubleAmt;
    
    @PropertyDesc("谷时电量")
    private java.math.BigDecimal valleyPq;
    
    @PropertyDesc("谷时电费")
    private java.math.BigDecimal valleyAmt;
    
    @PropertyDesc("平时电量")
    private java.math.BigDecimal plainPq;
    
    @PropertyDesc("平时电费")
    private java.math.BigDecimal plainAmt;
    
    @PropertyDesc("基本电费")
    private java.math.BigDecimal baseAmt;
    
    @PropertyDesc("力调电费")
    private java.math.BigDecimal forceAmt;
    
    @PropertyDesc("代征电费")
    private java.math.BigDecimal levyAmt;
    
    @PropertyDesc("其他电费")
    private java.math.BigDecimal otherAmt;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}
	
	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}
	
	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}
	
	public BigDecimal getMonthPq() {
		return monthPq;
	}

	public void setMonthPq(BigDecimal monthPq) {
		this.monthPq = monthPq;
	}

	public BigDecimal getMonthAmt() {
		return monthAmt;
	}

	public void setMonthAmt(BigDecimal monthAmt) {
		this.monthAmt = monthAmt;
	}

	public BigDecimal getMonthAvgPrc() {
		return monthAvgPrc;
	}

	public void setMonthAvgPrc(BigDecimal monthAvgPrc) {
		this.monthAvgPrc = monthAvgPrc;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public java.math.BigDecimal getOverPeakPq() {
		return overPeakPq;
	}

	public void setOverPeakPq(java.math.BigDecimal overPeakPq) {
		this.overPeakPq = overPeakPq;
	}

	public java.math.BigDecimal getOverPeakAmt() {
		return overPeakAmt;
	}

	public void setOverPeakAmt(java.math.BigDecimal overPeakAmt) {
		this.overPeakAmt = overPeakAmt;
	}

	public java.math.BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(java.math.BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public java.math.BigDecimal getPeakAmt() {
		return peakAmt;
	}

	public void setPeakAmt(java.math.BigDecimal peakAmt) {
		this.peakAmt = peakAmt;
	}

	public java.math.BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(java.math.BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public java.math.BigDecimal getDoubleAmt() {
		return doubleAmt;
	}

	public void setDoubleAmt(java.math.BigDecimal doubleAmt) {
		this.doubleAmt = doubleAmt;
	}

	public java.math.BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(java.math.BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public java.math.BigDecimal getValleyAmt() {
		return valleyAmt;
	}

	public void setValleyAmt(java.math.BigDecimal valleyAmt) {
		this.valleyAmt = valleyAmt;
	}

	public java.math.BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(java.math.BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public java.math.BigDecimal getPlainAmt() {
		return plainAmt;
	}

	public void setPlainAmt(java.math.BigDecimal plainAmt) {
		this.plainAmt = plainAmt;
	}

	public java.math.BigDecimal getBaseAmt() {
		return baseAmt;
	}

	public void setBaseAmt(java.math.BigDecimal baseAmt) {
		this.baseAmt = baseAmt;
	}

	public java.math.BigDecimal getForceAmt() {
		return forceAmt;
	}

	public void setForceAmt(java.math.BigDecimal forceAmt) {
		this.forceAmt = forceAmt;
	}

	public java.math.BigDecimal getLevyAmt() {
		return levyAmt;
	}

	public void setLevyAmt(java.math.BigDecimal levyAmt) {
		this.levyAmt = levyAmt;
	}

	public java.math.BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(java.math.BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

}
