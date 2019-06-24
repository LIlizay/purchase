package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.math.BigDecimal;

public class ElecMonthDetail {
	private String month;
	
	private BigDecimal proxyPq;
	
	private BigDecimal actualPq;
	
	private BigDecimal agrePq;
	
	private BigDecimal agrePrc;
	
	private BigDecimal deviationPq;
	
	private BigDecimal deviationProp;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
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

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

	public BigDecimal getAgrePrc() {
		return agrePrc;
	}

	public void setAgrePrc(BigDecimal agrePrc) {
		this.agrePrc = agrePrc;
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
