package com.hhwy.purchaseweb.arithmetic.common.domain;

import java.math.BigDecimal;

/**
 * 
 * <b>类 名 称：</b>TypeForPrc<br/>
 * <b>类 描 述：</b>电价计算参数对象<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月27日 下午8:34:28<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class TypeForPrc {
	
	/**
	 * 电价类型
	 */
	private String prcType;
	/**
	 * 电价比例值
	 */
	private BigDecimal prcProp;
	/**
	 * 长协合同总电量
	 */
	private BigDecimal lcAgrePq;
	/**
	 * 长协合同总金额(未进行精度处理的金额)
	 */
	private BigDecimal lcAgreAmt;
	/**
	 * 长协电价
	 */
	private BigDecimal lcPrc;
	/**
	 * 竞价电价
	 */
	private BigDecimal bidPrc;
	/**
	 * 长协申报电量（模拟时视为购电长协电量）
	 */
	private BigDecimal lcPq;
	/**
	 * 竞价申报电量（模拟时视为购电竞价电量）
	 */
	private BigDecimal bidPq;
	/**
	 * 人工录入时电价
	 */
	private BigDecimal customPrc;
	/**
	 * 用户id
	 */
	private String consId;
	
	public String getPrcType() {
		return prcType;
	}
	public void setPrcType(String prcType) {
		this.prcType = prcType;
	}
	public BigDecimal getPrcProp() {
		return prcProp;
	}
	public void setPrcProp(BigDecimal prcProp) {
		this.prcProp = prcProp;
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
	public BigDecimal getCustomPrc() {
		return customPrc;
	}
	public void setCustomPrc(BigDecimal customPrc) {
		this.customPrc = customPrc;
	}
	public String getConsId() {
		return consId;
	}
	public void setConsId(String consId) {
		this.consId = consId;
	}
	
}
