package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class ConsAgreDetail {
	 private String id;
	    
	    /** 年份  **/
	    private String year;
	    
	    /** 合同编号  **/
	    private String agreNo;
	    
	    /** 合同名称  **/
	    private String agreName;
	    
	    /** 用户名称 **/
	    private String consName;
	    
	    /** 合同类型  **/
	    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_sellElecContractType" ,field="agreTypeName")
	    private String agreType;
	    private String agreTypeName;
	    
	    /** 合同电量  **/
	    private java.math.BigDecimal proxyPq;
	    
	    /** 合同开始日期  **/
	    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
	    private Date effectiveDate;
	    
	    /** 合同结束日期  **/
	    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
	    private Date expiryDate;
	    
	    /** 附件 **/
	    private String fileId;
	    
	    /** 合同状态  **/
	    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_contractStatusCode" ,field="agreStatusName")
	    private String agreStatus;
	    private String agreStatusName;
	    
	    private BigDecimal agreProp;

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getYear() {
			return year;
		}

		public void setYear(String year) {
			this.year = year;
		}

		public String getAgreNo() {
			return agreNo;
		}

		public void setAgreNo(String agreNo) {
			this.agreNo = agreNo;
		}

		public String getAgreName() {
			return agreName;
		}

		public void setAgreName(String agreName) {
			this.agreName = agreName;
		}

		public String getConsName() {
			return consName;
		}

		public void setConsName(String consName) {
			this.consName = consName;
		}

		public String getAgreType() {
			return agreType;
		}

		public void setAgreType(String agreType) {
			this.agreType = agreType;
		}

		public String getAgreTypeName() {
			return agreTypeName;
		}

		public void setAgreTypeName(String agreTypeName) {
			this.agreTypeName = agreTypeName;
		}

		public java.math.BigDecimal getProxyPq() {
			return proxyPq;
		}

		public void setProxyPq(java.math.BigDecimal proxyPq) {
			this.proxyPq = proxyPq;
		}

		public Date getEffectiveDate() {
			return effectiveDate;
		}

		public void setEffectiveDate(Date effectiveDate) {
			this.effectiveDate = effectiveDate;
		}

		public Date getExpiryDate() {
			return expiryDate;
		}

		public void setExpiryDate(Date expiryDate) {
			this.expiryDate = expiryDate;
		}

		public String getFileId() {
			return fileId;
		}

		public void setFileId(String fileId) {
			this.fileId = fileId;
		}

		public String getAgreStatus() {
			return agreStatus;
		}

		public void setAgreStatus(String agreStatus) {
			this.agreStatus = agreStatus;
		}

		public String getAgreStatusName() {
			return agreStatusName;
		}

		public void setAgreStatusName(String agreStatusName) {
			this.agreStatusName = agreStatusName;
		}

		public BigDecimal getAgreProp() {
			return agreProp;
		}

		public void setAgreProp(BigDecimal agreProp) {
			this.agreProp = agreProp;
		}
	    
}
