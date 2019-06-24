package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.math.BigDecimal;

public class ElecYearDetail {
	
	private String year;
	
	private BigDecimal proxyPq;
	
	private BigDecimal actualPq;
	
	private BigDecimal deviationPq;
	
	private BigDecimal deviationProp;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public BigDecimal getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(BigDecimal proxyPq) {
		this.proxyPq = proxyPq;
	}

	public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
	}

	public BigDecimal getDeviationPq() {
		return deviationPq;
	}

	public void setDeviationPq(BigDecimal deviationPq) {
		this.deviationPq = deviationPq;
	}

	public BigDecimal getDeviationProp() {
		return deviationProp;
	}

	public void setDeviationProp(BigDecimal deviationProp) {
		this.deviationProp = deviationProp;
	}
}
