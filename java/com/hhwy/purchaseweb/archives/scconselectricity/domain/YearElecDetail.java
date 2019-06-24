package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import java.math.BigDecimal;

public class YearElecDetail {
	
	private String year;
	
	private BigDecimal yearPq;
	
	private BigDecimal yearPeakPq;
	
	private BigDecimal yearPlainPq;
	
	private BigDecimal yearValleyPq;
	
	private BigDecimal yearAmt;
	
	private BigDecimal yearOverPeakPq;		//尖峰电量
	
	private BigDecimal yearDoublePq;		//双蓄电量
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getYearPq() {
		return yearPq;
	}

	public void setYearPq(BigDecimal yearPq) {
		this.yearPq = yearPq;
	}

	public BigDecimal getYearPeakPq() {
		return yearPeakPq;
	}

	public void setYearPeakPq(BigDecimal yearPeakPq) {
		this.yearPeakPq = yearPeakPq;
	}

	public BigDecimal getYearPlainPq() {
		return yearPlainPq;
	}

	public void setYearPlainPq(BigDecimal yearPlainPq) {
		this.yearPlainPq = yearPlainPq;
	}

	public BigDecimal getYearValleyPq() {
		return yearValleyPq;
	}

	public void setYearValleyPq(BigDecimal yearValleyPq) {
		this.yearValleyPq = yearValleyPq;
	}

	public BigDecimal getYearAmt() {
		return yearAmt;
	}

	public void setYearAmt(BigDecimal yearAmt) {
		this.yearAmt = yearAmt;
	}

	public BigDecimal getYearOverPeakPq() {
		return yearOverPeakPq;
	}

	public void setYearOverPeakPq(BigDecimal yearOverPeakPq) {
		this.yearOverPeakPq = yearOverPeakPq;
	}

	public BigDecimal getYearDoublePq() {
		return yearDoublePq;
	}

	public void setYearDoublePq(BigDecimal yearDoublePq) {
		this.yearDoublePq = yearDoublePq;
	}
	
	
}
