package com.hhwy.purchaseweb.arithmetic.common.domain;

import java.math.BigDecimal;

public class AmtScale {
	
	/**
	 * 电量精度
	 */
	private BigDecimal pqScale;
	/**
	 * 电价精度
	 */
	private BigDecimal prcScale;
	/**
	 * 金额精度
	 */
	private BigDecimal amtScale;
	public BigDecimal getPqScale() {
		return pqScale;
	}
	public void setPqScale(BigDecimal pqScale) {
		this.pqScale = pqScale;
	}
	public BigDecimal getPrcScale() {
		return prcScale;
	}
	public void setPrcScale(BigDecimal prcScale) {
		this.prcScale = prcScale;
	}
	public BigDecimal getAmtScale() {
		return amtScale;
	}
	public void setAmtScale(BigDecimal amtScale) {
		this.amtScale = amtScale;
	}

}
