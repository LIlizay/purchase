package com.hhwy.purchaseweb.delivery.entersettle.domain;

public class EnterSettleDetail {
	
	private String id;			//id
	
	private String ym;			//购电年月
	
	private String consNum;		//用户数
	
	private java.math.BigDecimal reportPq;		//委托电量
	
	private java.math.BigDecimal purchasePq;	//总购电量
	
	private java.math.BigDecimal compProfit;  	//售电公司收益
	
	private java.math.BigDecimal consProfit; 	//用户收益
	
	private java.math.BigDecimal compCheck;		//售电公司偏差费用
	
	private java.math.BigDecimal consCheck;		//用户偏差费用
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getConsNum() {
		return consNum;
	}

	public void setConsNum(String consNum) {
		this.consNum = consNum;
	}

	public java.math.BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(java.math.BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public java.math.BigDecimal getPurchasePq() {
		return purchasePq;
	}

	public void setPurchasePq(java.math.BigDecimal purchasePq) {
		this.purchasePq = purchasePq;
	}

	public java.math.BigDecimal getCompProfit() {
		return compProfit;
	}

	public void setCompProfit(java.math.BigDecimal compProfit) {
		this.compProfit = compProfit;
	}

	public java.math.BigDecimal getConsProfit() {
		return consProfit;
	}

	public void setConsProfit(java.math.BigDecimal consProfit) {
		this.consProfit = consProfit;
	}

	public java.math.BigDecimal getCompCheck() {
		return compCheck;
	}

	public void setCompCheck(java.math.BigDecimal compCheck) {
		this.compCheck = compCheck;
	}

	public java.math.BigDecimal getConsCheck() {
		return consCheck;
	}

	public void setConsCheck(java.math.BigDecimal consCheck) {
		this.consCheck = consCheck;
	}

	
	
}
