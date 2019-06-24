package com.hhwy.purchaseweb.arithmetic.deviation.domain;

import java.math.BigDecimal;

/**
 * 
 * <b>类 名 称：</b>DeviationResult<br/>
 * <b>类 描 述：</b>偏差考核计算结果<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年5月4日 下午10:15:08<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DeviationResult {

	/**
	 * 偏差电量
	 */
	private BigDecimal deviationPq;
	/**
	 * 偏差电量比例
	 */
	private BigDecimal deviationProp;
	/**
	 * 偏差考核金额
	 */
	private BigDecimal deviationAmt;
	
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
	public BigDecimal getDeviationAmt() {
		return deviationAmt;
	}
	public void setDeviationAmt(BigDecimal deviationAmt) {
		this.deviationAmt = deviationAmt;
	}
	
}
