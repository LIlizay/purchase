package com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * 
 * <b>类 名 称：</b>SectionResult电价段匹配信息<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月2日 上午9:46:41<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SectionResult {
	
	/**
	 * 用户申报电价
	 */
	private BigDecimal consDeclarePrc;
	/**
	 * 用户申报电量
	 */
	private BigDecimal consDeclarePq;
	/**
	 * 发电企业申报电价
	 */
	private BigDecimal enteDeclarePrc;
	/**
	 * 发电企业申报电量
	 */
	private BigDecimal enteDeclarePq;
	/**
	 * 中标电量
	 */
	private BigDecimal bidPq;
	/**
	 * 用户匹配电量组成信息
	 */
	private List<Section> consDetails;
	/**
	 * 发电企业匹配电量组成信息
	 */
	private List<Section> enteDetails;
	public BigDecimal getConsDeclarePrc() {
		return consDeclarePrc;
	}
	public void setConsDeclarePrc(BigDecimal consDeclarePrc) {
		this.consDeclarePrc = consDeclarePrc;
	}
	public BigDecimal getConsDeclarePq() {
		return consDeclarePq;
	}
	public void setConsDeclarePq(BigDecimal consDeclarePq) {
		this.consDeclarePq = consDeclarePq;
	}
	public BigDecimal getEnteDeclarePrc() {
		return enteDeclarePrc;
	}
	public void setEnteDeclarePrc(BigDecimal enteDeclarePrc) {
		this.enteDeclarePrc = enteDeclarePrc;
	}
	public BigDecimal getEnteDeclarePq() {
		return enteDeclarePq;
	}
	public void setEnteDeclarePq(BigDecimal enteDeclarePq) {
		this.enteDeclarePq = enteDeclarePq;
	}
	public BigDecimal getBidPq() {
		return bidPq;
	}
	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}
	public List<Section> getConsDetails() {
		return consDetails;
	}
	public void setConsDetails(List<Section> consDetails) {
		this.consDetails = consDetails;
	}
	public List<Section> getEnteDetails() {
		return enteDetails;
	}
	public void setEnteDetails(List<Section> enteDetails) {
		this.enteDetails = enteDetails;
	}

}
