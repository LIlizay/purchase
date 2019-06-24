package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class AgrePqDetail {
	
	//用户id
	private String consId;
	
	/** 用户性质 **/
	private String izName;
	
	//用户名称
	private String consName;
	
	//电压等级
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
	
	//用电类别
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
    private String elecTypeName;

	// 月度合同电量
	private BigDecimal agrePq;

	
	
	
	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getIzName() {
		return izName;
	}

	public void setIzName(String izName) {
		this.izName = izName;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}
}
