package com.hhwy.purchaseweb.arithmetic.deviation.domain;

import java.math.BigDecimal;

/**
 * 
 * <b>类 名 称：</b>DeviationObject<br/>
 * <b>类 描 述：</b>偏差考核参数对象<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年5月4日 下午10:15:08<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DeviationObject {

	/**
	 * 长协电量
	 */
	private BigDecimal lcPq;
	/**
	 * 售电公司长协合同总电量
	 */
	private BigDecimal lcAgrePq;
	/**
	 * 售电公司长协总金额
	 */
	private BigDecimal lcAgreAmt;
	/**
	 * 售电公司长协电价(平均价)
	 */
	private BigDecimal lcPrc;
	/**
	 * 竞价电量
	 */
	private BigDecimal bidPq;
	/**
	 * 竞价电价
	 */
	private BigDecimal bidPrc;
	/**
	 * 交割电量(实际用电量)
	 */
	private BigDecimal deliveryPq;
	/**
	 * 申报电量
	 */
	private BigDecimal reportPq;
	/**
	 * 用户id
	 */
	private String consId;
	
	public BigDecimal getLcPq() {
		return lcPq;
	}
	public void setLcPq(BigDecimal lcPq) {
		this.lcPq = lcPq;
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
	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}
	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}
	public BigDecimal getReportPq() {
		return reportPq;
	}
	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}
	public String getConsId() {
		return consId;
	}
	public void setConsId(String consId) {
		this.consId = consId;
	}
	
}
