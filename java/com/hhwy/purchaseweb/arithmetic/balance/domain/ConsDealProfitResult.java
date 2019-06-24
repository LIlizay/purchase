package com.hhwy.purchaseweb.arithmetic.balance.domain;

import java.math.BigDecimal;

public class ConsDealProfitResult {

	/**
	 * 交易利润
	 */
	private BigDecimal consDealProfit;
	/**
	 * 用户利润
	 */
	private BigDecimal consProfit;
	/**
	 * 用户偏差考核金额
	 */
	private BigDecimal consPunish;
	/**
	 * 用户偏差考核电量
	 */
	private BigDecimal consDeviationPq;
	/**
	 * 售电公司赔偿金额
	 */
	private BigDecimal consCompensate;

	public BigDecimal getConsDealProfit() {
		return consDealProfit;
	}

	public void setConsDealProfit(BigDecimal consDealProfit) {
		this.consDealProfit = consDealProfit;
	}

	public BigDecimal getConsProfit() {
		return consProfit;
	}

	public void setConsProfit(BigDecimal consProfit) {
		this.consProfit = consProfit;
	}

	public BigDecimal getConsDeviationPq() {
		return consDeviationPq;
	}

	public void setConsDeviationPq(BigDecimal consDeviationPq) {
		this.consDeviationPq = consDeviationPq;
	}

	public BigDecimal getConsPunish() {
		return consPunish;
	}

	public void setConsPunish(BigDecimal consPunish) {
		this.consPunish = consPunish;
	}

	public BigDecimal getConsCompensate() {
		return consCompensate;
	}

	public void setConsCompensate(BigDecimal consCompensate) {
		this.consCompensate = consCompensate;
	}

}
