package com.hhwy.purchase.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

@Entity(name="PhmPlanYearSubitem")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_plan_year_subitem")
public class PhmPlanYearSubitem extends Domain implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
	
	@PropertyDesc("成交信息id")
    @Column(name="deal_id", nullable=true, length=32) 
    private String dealId;
	
	@PropertyDesc("交易年份")
    @Column(name="year", nullable=true, length=6) 
    private String year;
	
	@PropertyDesc("1月申报电量")
    @Column(name="jan", nullable=true, length=16) 
    private BigDecimal jan;
	
	@PropertyDesc("2月申报电量")
    @Column(name="feb", nullable=true, length=16) 
    private BigDecimal feb;
	
	@PropertyDesc("3月申报电量")
    @Column(name="mar", nullable=true, length=16) 
    private BigDecimal mar;
	
	@PropertyDesc("4月申报电量")
    @Column(name="apr", nullable=true, length=16) 
    private BigDecimal apr;
	
	@PropertyDesc("5月申报电量")
    @Column(name="may", nullable=true, length=16) 
    private BigDecimal may;
	
	@PropertyDesc("6月申报电量")
    @Column(name="jun", nullable=true, length=16) 
    private BigDecimal jun;
	
	@PropertyDesc("7月申报电量")
    @Column(name="jul", nullable=true, length=16) 
    private BigDecimal jul;
	
	@PropertyDesc("8月申报电量")
    @Column(name="aug", nullable=true, length=16) 
    private BigDecimal aug;
	
	@PropertyDesc("9月申报电量")
    @Column(name="sept", nullable=true, length=16) 
    private BigDecimal sept;
	
	@PropertyDesc("10月申报电量")
    @Column(name="oct", nullable=true, length=16) 
    private BigDecimal oct;
	
	@PropertyDesc("11月申报电量")
    @Column(name="nov", nullable=true, length=16) 
    private BigDecimal nov;

	@PropertyDesc("12月申报电量")
    @Column(name="dece", nullable=true, length=16) 
    private BigDecimal dece;

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(BigDecimal feb) {
		this.feb = feb;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getMay() {
		return may;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAug() {
		return aug;
	}

	public void setAug(BigDecimal aug) {
		this.aug = aug;
	}

	public BigDecimal getSept() {
		return sept;
	}

	public void setSept(BigDecimal sept) {
		this.sept = sept;
	}

	public BigDecimal getOct() {
		return oct;
	}

	public void setOct(BigDecimal oct) {
		this.oct = oct;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDece() {
		return dece;
	}

	public void setDece(BigDecimal dece) {
		this.dece = dece;
	}
	
}
