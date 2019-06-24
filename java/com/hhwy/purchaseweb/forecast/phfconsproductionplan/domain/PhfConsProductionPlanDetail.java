package com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain;

import com.hhwy.framework.persistent.entity.Domain;

public class PhfConsProductionPlanDetail extends Domain{
	
	private static final long serialVersionUID = 1L;

	//是否展开树节点的标志
	private String state;
	
	//用户标识
    private String consId;
    
    //用户名称
    private String consName;
    
    //父节点用户名称
    private String parentName;
    
	//年份
    private String year;
    
    //产品名称
    private String productName;
    
    //产品计量单位
    private String unit;
    
    //1月
    private java.math.BigDecimal jan;
    
    //2月
    private java.math.BigDecimal feb;
    
    //3月
    private java.math.BigDecimal mar;
    
    //4月
    private java.math.BigDecimal apr;
    
    //5月
    private java.math.BigDecimal may;
    
    //6月
    private java.math.BigDecimal jun;
    
    //7月
    private java.math.BigDecimal jul;
    
    //8月
    private java.math.BigDecimal aug;
    
    //9月
    private java.math.BigDecimal sept;
    
    //10月
    private java.math.BigDecimal oct;
    
    //11月
    private java.math.BigDecimal nov;
    
    //12月
    private java.math.BigDecimal dece;
    
    //部门id
    private String orgNo;

	public String getParentName() {
		return  parentName == null ? "" : parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
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

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public java.math.BigDecimal getJan() {
		return jan;
	}

	public void setJan(java.math.BigDecimal jan) {
		this.jan = jan;
	}

	public java.math.BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(java.math.BigDecimal feb) {
		this.feb = feb;
	}

	public java.math.BigDecimal getMar() {
		return mar;
	}

	public void setMar(java.math.BigDecimal mar) {
		this.mar = mar;
	}

	public java.math.BigDecimal getApr() {
		return apr;
	}

	public void setApr(java.math.BigDecimal apr) {
		this.apr = apr;
	}

	public java.math.BigDecimal getMay() {
		return may;
	}

	public void setMay(java.math.BigDecimal may) {
		this.may = may;
	}

	public java.math.BigDecimal getJun() {
		return jun;
	}

	public void setJun(java.math.BigDecimal jun) {
		this.jun = jun;
	}

	public java.math.BigDecimal getJul() {
		return jul;
	}

	public void setJul(java.math.BigDecimal jul) {
		this.jul = jul;
	}

	public java.math.BigDecimal getAug() {
		return aug;
	}

	public void setAug(java.math.BigDecimal aug) {
		this.aug = aug;
	}

	public java.math.BigDecimal getSept() {
		return sept;
	}

	public void setSept(java.math.BigDecimal sept) {
		this.sept = sept;
	}

	public java.math.BigDecimal getOct() {
		return oct;
	}

	public void setOct(java.math.BigDecimal oct) {
		this.oct = oct;
	}

	public java.math.BigDecimal getNov() {
		return nov;
	}

	public void setNov(java.math.BigDecimal nov) {
		this.nov = nov;
	}

	public java.math.BigDecimal getDece() {
		return dece;
	}

	public void setDece(java.math.BigDecimal dece) {
		this.dece = dece;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
