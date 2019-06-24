package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class PhmPurchasePlanConsRelaDetail {
	@PropertyDesc("数据标识")
	private String id;
	
	@PropertyDesc("用户id")
	private String consId;
	
	@PropertyDesc("计划id")
	private String planId;
	
	@PropertyDesc("用户名称")
	private String consName;
	
	@PropertyDesc("电压等级Code")
	@PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_psVoltCode", field = "voltCodeName")
	private String voltCode;

	@PropertyDesc("电压等级")
	private String voltCodeName;
	
	@PropertyDesc("用电类别Code")
	@PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_elecTypeCode", field = "elecTypeName")
	private String elecTypeCode;

	@PropertyDesc("用电类别")
	private String elecTypeName;
	
	@PropertyDesc("用电容量")
	private BigDecimal electricCapacity;
	
	@PropertyDesc("合同电量")
	private String ppaPq;
	
	@PropertyDesc("联系人")
	private String contName;
	
	@PropertyDesc("联系电话")
	private String contPhone;
	
	@PropertyDesc("客户经理")
	private String sellPerson;
	
	private BigDecimal agrePq;
	
	private String fileId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public BigDecimal getElectricCapacity() {
		return electricCapacity;
	}

	public void setElectricCapacity(BigDecimal electricCapacity) {
		this.electricCapacity = electricCapacity;
	}

	public String getPpaPq() {
		return ppaPq;
	}

	public void setPpaPq(String ppaPq) {
		this.ppaPq = ppaPq;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
	}

	public String getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(String sellPerson) {
		this.sellPerson = sellPerson;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
}
