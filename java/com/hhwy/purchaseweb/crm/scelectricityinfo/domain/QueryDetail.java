package com.hhwy.purchaseweb.crm.scelectricityinfo.domain;

public class QueryDetail {
	
	//用户id
	private String consId;
	
	private String consName;
	
	//负荷性质
	private String lodeAttrCode;
	
	//用电类别
	private String elecTypeCode;
	
	//行业分类
	private String industryType;
	
	//年份
	private String year;

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getLodeAttrCode() {
		return lodeAttrCode;
	}

	public void setLodeAttrCode(String lodeAttrCode) {
		this.lodeAttrCode = lodeAttrCode;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

}
