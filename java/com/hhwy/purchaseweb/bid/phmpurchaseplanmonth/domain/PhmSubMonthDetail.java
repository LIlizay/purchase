package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain;

import java.math.BigDecimal;


public class PhmSubMonthDetail {
	
	private String id;
	
	private String consId;		//用户id
	
	private String planId;		//计划id
	
	private String consName;	//用户名称
	
	private String contName;	//联系人
	
	private String contPhone;	//联系电话
	
	private BigDecimal jan;		//1月委托电量	
	
	private BigDecimal feb;		//2月委托电量	
	
	private BigDecimal mar;		//3月委托电量	
	
	private BigDecimal apr;		//4月委托电量	
	
	private BigDecimal may;		//5月委托电量	
	
	private BigDecimal jun;		//6月委托电量	
	
	private BigDecimal jul;		//7月委托电量	
	
	private BigDecimal aug;		//8月委托电量	
	
	private BigDecimal sept;	//9月委托电量	
	
	private BigDecimal oct;		//10月委托电量	
	
	private BigDecimal nov;		//11月委托电量	
	
	private BigDecimal dece;	//12月委托电量	

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

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(BigDecimal feb) {
		this.feb = feb;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getMay() {
		return may;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAug() {
		return aug;
	}

	public void setAug(BigDecimal aug) {
		this.aug = aug;
	}

	public BigDecimal getSept() {
		return sept;
	}

	public void setSept(BigDecimal sept) {
		this.sept = sept;
	}

	public BigDecimal getOct() {
		return oct;
	}

	public void setOct(BigDecimal oct) {
		this.oct = oct;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDece() {
		return dece;
	}

	public void setDece(BigDecimal dece) {
		this.dece = dece;
	}
	
}
