package com.hhwy.purchaseweb.arithmetic.divide.domain;

import java.math.BigDecimal;

public class DivideObject {
	
	/**
	 * 分成方式
	 */
	private String diviCode;
	/**
	 * 是否优惠上限
	 */
	private String discountUpperFlag;
	/**
	 * 优惠上限金额
	 */
	private BigDecimal discountUpperAmt;
	/**
	 * 是否优惠下限
	 */
	private String discountLowerFlag;
	/**
	 * 优惠下限金额
	 */
	private BigDecimal discountLowerAmt;
	/**
	 * 长协合同总电量
	 */
	private BigDecimal lcAgrePq;
	/**
	 * 长协合同总金额
	 */
	private BigDecimal lcAgreAmt;
	/**
	 * 长协成交电价
	 */
	private BigDecimal lcPrc;
	/**
	 * 竞价成交电价
	 */
	private BigDecimal bidPrc;
	/**
	 * 保底协议电价
	 */
	private BigDecimal agrePrc;
	/**
	 * 申报长协电量
	 */
	private BigDecimal lcPq;
	/**
	 * 申报竞价电量
	 */
	private BigDecimal bidPq;
	/**
	 * 长协成交电量
	 */
	private BigDecimal dealLcPq;
	/**
	 * 竞价成交电量
	 */
	private BigDecimal dealBidPq;
	/**
	 * 竞价申报电量
	 */
	private BigDecimal reportPq;
	/**
	 * 交割电量
	 */
	private BigDecimal deliveryPq;
	/**
	 * 竞价用户分成比例(保底加分成模式时，计算比例按照partyABidProp计算，partyALcProp弃用)
	 */
	private BigDecimal partyABidProp;
	/**
	 * 长协用户分成比例
	 */
	private BigDecimal partyALcProp;
	/**
	 * 用户id
	 */
	private String consId;
	
	public String getDiviCode() {
		return diviCode;
	}
	public void setDiviCode(String diviCode) {
		this.diviCode = diviCode;
	}
	public String getDiscountUpperFlag() {
		return discountUpperFlag;
	}
	public void setDiscountUpperFlag(String discountUpperFlag) {
		this.discountUpperFlag = discountUpperFlag;
	}
	public BigDecimal getDiscountUpperAmt() {
		return discountUpperAmt;
	}
	public void setDiscountUpperAmt(BigDecimal discountUpperAmt) {
		this.discountUpperAmt = discountUpperAmt;
	}
	public String getDiscountLowerFlag() {
		return discountLowerFlag;
	}
	public void setDiscountLowerFlag(String discountLowerFlag) {
		this.discountLowerFlag = discountLowerFlag;
	}
	public BigDecimal getDiscountLowerAmt() {
		return discountLowerAmt;
	}
	public void setDiscountLowerAmt(BigDecimal discountLowerAmt) {
		this.discountLowerAmt = discountLowerAmt;
	}
	public BigDecimal getLcAgrePq() {
		return lcAgrePq;
	}
	public void setLcAgrePq(BigDecimal lcAgrePq) {
		this.lcAgrePq = lcAgrePq;
	}
	public BigDecimal getLcAgreAmt() {
		return lcAgreAmt;
	}
	public void setLcAgreAmt(BigDecimal lcAgreAmt) {
		this.lcAgreAmt = lcAgreAmt;
	}
	public BigDecimal getLcPrc() {
		return lcPrc;
	}
	public void setLcPrc(BigDecimal lcPrc) {
		this.lcPrc = lcPrc;
	}
	public BigDecimal getBidPrc() {
		return bidPrc;
	}
	public void setBidPrc(BigDecimal bidPrc) {
		this.bidPrc = bidPrc;
	}
	public BigDecimal getAgrePrc() {
		return agrePrc;
	}
	public void setAgrePrc(BigDecimal agrePrc) {
		this.agrePrc = agrePrc;
	}
	public BigDecimal getLcPq() {
		return lcPq;
	}
	public void setLcPq(BigDecimal lcPq) {
		this.lcPq = lcPq;
	}
	public BigDecimal getBidPq() {
		return bidPq;
	}
	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}
	public BigDecimal getDealLcPq() {
		return dealLcPq;
	}
	public void setDealLcPq(BigDecimal dealLcPq) {
		this.dealLcPq = dealLcPq;
	}
	public BigDecimal getDealBidPq() {
		return dealBidPq;
	}
	public void setDealBidPq(BigDecimal dealBidPq) {
		this.dealBidPq = dealBidPq;
	}
	public BigDecimal getReportPq() {
		return reportPq;
	}
	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}
	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}
	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}
	public BigDecimal getPartyABidProp() {
		return partyABidProp;
	}
	public void setPartyABidProp(BigDecimal partyABidProp) {
		this.partyABidProp = partyABidProp;
	}
	public BigDecimal getPartyALcProp() {
		return partyALcProp;
	}
	public void setPartyALcProp(BigDecimal partyALcProp) {
		this.partyALcProp = partyALcProp;
	}
	public String getConsId() {
		return consId;
	}
	public void setConsId(String consId) {
		this.consId = consId;
	} 

}
