package com.hhwy.purchaseweb.arithmetic.decompose.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * <b>类 名 称：</b>ConsDecompose<br/>
 * <b>类 描 述：</b>用户电量分割实体<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月11日 下午5:08:37<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ConsDecompose {

	/**
	 * 分割参数集合（按用户）
	 */
	private List<DecomposeData> decomposeBidList;
	/**
	 * 分割参数集合（按用户）
	 */
	private List<DecomposeData> decomposeLcList;
	/**
	 * 用户id
	 */
	private String consId;
	/**
	 * 普通交易总电量
	 */
	private BigDecimal bidPq;
	/**
	 * 长协交易总电量
	 */
	private BigDecimal lcPq;
	/**
	 * 总交易金额
	 */
	private BigDecimal amt;
	/**
	 * 交割电量
	 */
	private BigDecimal deliveryPq;
	/**
	 * 合同加权平均价（不一定用到）
	 */
	private BigDecimal avgPrc;
	/**
	 * 竞价电价
	 */
	private BigDecimal bidPrc;

	public List<DecomposeData> getDecomposeBidList() {
		return decomposeBidList;
	}
	public void setDecomposeBidList(List<DecomposeData> decomposeBidList) {
		this.decomposeBidList = decomposeBidList;
	}
	public List<DecomposeData> getDecomposeLcList() {
		return decomposeLcList;
	}
	public void setDecomposeLcList(List<DecomposeData> decomposeLcList) {
		this.decomposeLcList = decomposeLcList;
	}
	public String getConsId() {
		return consId;
	}
	public void setConsId(String consId) {
		this.consId = consId;
	}
	public BigDecimal getBidPq() {
		return bidPq;
	}
	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}
	public BigDecimal getLcPq() {
		return lcPq;
	}
	public void setLcPq(BigDecimal lcPq) {
		this.lcPq = lcPq;
	}
	public BigDecimal getAmt() {
		return amt;
	}
	public void setAmt(BigDecimal amt) {
		this.amt = amt;
	}
	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}
	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}
	public BigDecimal getAvgPrc() {
		return avgPrc;
	}
	public void setAvgPrc(BigDecimal avgPrc) {
		this.avgPrc = avgPrc;
	}
	public BigDecimal getBidPrc() {
		return bidPrc;
	}
	public void setBidPrc(BigDecimal bidPrc) {
		this.bidPrc = bidPrc;
	}
	
}
