package com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain;

import java.math.BigDecimal;

/**
 * 
 * <b>类 名 称：</b>Section电价匹配单元<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月1日 上午10:04:31<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class Section {

	/**
	 * 匹配单元名称（售电公司或发电企业名称）
	 */
	private String enteName;
	/**
	 * 匹配段名称
	 */
	private String sectionName;
	/**
	 * 申报电量
	 */
	private BigDecimal declarePq;
	/**
	 * 申报电价
	 */
	private BigDecimal declarePrc;
	public String getEnteName() {
		return enteName;
	}
	public void setEnteName(String enteName) {
		this.enteName = enteName;
	}
	public String getSectionName() {
		return sectionName;
	}
	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}
	public BigDecimal getDeclarePq() {
		return declarePq;
	}
	public void setDeclarePq(BigDecimal declarePq) {
		this.declarePq = declarePq;
	}
	public BigDecimal getDeclarePrc() {
		return declarePrc;
	}
	public void setDeclarePrc(BigDecimal declarePrc) {
		this.declarePrc = declarePrc;
	}
	
}
