package com.hhwy.purchaseweb.delivery.smfundprcinfo.domain;

import java.io.Serializable;

public class SmFundPrcInfoDetail implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = -7716200676443614888L;
	
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/** 电镀电价实体id **/
	private String prcId;

	/** 电价码 **/
	private String prcCode;

	/** 用电类别 **/
	private String elecTypeName;
	private String elecType;

	/** 销售电价 **/
	private java.math.BigDecimal sellPrc;

	/** 状态 **/
	private String state;

	/** 部门id **/

	private String orgNo;

	public String getPrcId() {
		return prcId;
	}

	public void setPrcId(String prcId) {
		this.prcId = prcId;
	}

	public String getPrcCode() {
		return prcCode;
	}

	public void setPrcCode(String prcCode) {
		this.prcCode = prcCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public String getElecType() {
		return elecType;
	}

	public void setElecType(String elecType) {
		this.elecType = elecType;
	}

	public java.math.BigDecimal getSellPrc() {
		return sellPrc;
	}

	public void setSellPrc(java.math.BigDecimal sellPrc) {
		this.sellPrc = sellPrc;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

}
