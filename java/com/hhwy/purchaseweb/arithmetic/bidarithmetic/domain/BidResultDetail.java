package com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain;

import java.math.BigDecimal;

public class BidResultDetail {
	
	/**
	 * 用户名称
	 */
	private String consName;

	/**
	 * 成交电量
	 */
	private BigDecimal bidPq;
	
	/**
	 * 成交电价
	 */
	private BigDecimal bidPrc;
	
	/**
	 * 发电企业名称
	 */
	private String enteName;
	
	/**
	 * 用户申报价
	 */
	private BigDecimal consDeclarePrc;
	
	/**
	 * 发电企业申报价
	 */
	private BigDecimal enteDeclarePrc;
	
	/**
	 * 段名，不分段为空
	 */
	private String sectionName;

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public BigDecimal getBidPq() {
		return bidPq;
	}

	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}

	public BigDecimal getBidPrc() {
		return bidPrc;
	}

	public void setBidPrc(BigDecimal bidPrc) {
		this.bidPrc = bidPrc;
	}

	public String getEnteName() {
		return enteName;
	}

	public void setEnteName(String enteName) {
		this.enteName = enteName;
	}

	public BigDecimal getConsDeclarePrc() {
		return consDeclarePrc;
	}

	public void setConsDeclarePrc(BigDecimal consDeclarePrc) {
		this.consDeclarePrc = consDeclarePrc;
	}

	public BigDecimal getEnteDeclarePrc() {
		return enteDeclarePrc;
	}

	public void setEnteDeclarePrc(BigDecimal enteDeclarePrc) {
		this.enteDeclarePrc = enteDeclarePrc;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	
}
