package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PhmTransactionReportDetailDetail {
	
	private String id;			//电量申报id
	
	private String planId;		//月度购电计划标识
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_attornType" ,field="attornTypeName")
	private String attornType;	//合同转让方向
	private String attornTypeName;

	private String reportPq;	//申报电量
	
	private String reportPrc;	//申报电价
	
	private String stage;		//第几段
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
	private String voltCode;	//电压等级
	private String voltCodeName;
	
	//------------------------------------------------------以下为山西、辽宁 交易申报列表字段 -----------------------------------------------------
	
	private String consId;			//用户id
	
	private String consName;		//用户名称
	
	@PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_elecTypeCode", field = "elecTypeName")
	private String elecTypeCode;	//用电类别
	private String elecTypeName;
	
	private BigDecimal agrePq;		//本次委托电量
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPlanId() {
		return planId;
	}
	public void setPlanId(String planId) {
		this.planId = planId;
	}
	public String getReportPq() {
		return reportPq;
	}
	public void setReportPq(String reportPq) {
		this.reportPq = reportPq;
	}
	public String getReportPrc() {
		return reportPrc;
	}
	public void setReportPrc(String reportPrc) {
		this.reportPrc = reportPrc;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
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
	public String getAttornType() {
		return attornType;
	}
	public void setAttornType(String attornType) {
		this.attornType = attornType;
	}
	public String getAttornTypeName() {
		return attornTypeName;
	}
	public void setAttornTypeName(String attornTypeName) {
		this.attornTypeName = attornTypeName;
	}
	public String getConsId() {
		return consId;
	}
	public void setConsId(String consId) {
		this.consId = consId;
	}
	public String getConsName() {
		return consName;
	}
	public void setConsName(String consName) {
		this.consName = consName;
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
