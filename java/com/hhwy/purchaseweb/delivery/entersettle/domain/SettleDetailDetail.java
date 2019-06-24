package com.hhwy.purchaseweb.delivery.entersettle.domain;

public class SettleDetailDetail {
	
	private String id;
	
	private String consName;					//用户名称
	
	private String consId;						//用户id
	
	private String settleId;					//结算id
	
	private java.math.BigDecimal reportPq;		//委托电量
	
	private java.math.BigDecimal actPq;			//实际用电量
	
	private java.math.BigDecimal consCheckPq;	//偏差电量
	
	private java.math.BigDecimal settlePrc;		//结算电价
	
	private java.math.BigDecimal consCheck;		//偏差费用
	
	private java.math.BigDecimal profit;		//节省电费
	
	private java.math.BigDecimal prc;			//结算电费
	
	private String agent;						//代理人
	
	private java.math.BigDecimal agentPrc;		//代理费用
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public java.math.BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(java.math.BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public java.math.BigDecimal getActPq() {
		return actPq;
	}

	public void setActPq(java.math.BigDecimal actPq) {
		this.actPq = actPq;
	}

	public java.math.BigDecimal getConsCheckPq() {
		return consCheckPq;
	}

	public void setConsCheckPq(java.math.BigDecimal consCheckPq) {
		this.consCheckPq = consCheckPq;
	}

	public java.math.BigDecimal getSettlePrc() {
		return settlePrc;
	}

	public void setSettlePrc(java.math.BigDecimal settlePrc) {
		this.settlePrc = settlePrc;
	}

	public java.math.BigDecimal getConsCheck() {
		return consCheck;
	}

	public void setConsCheck(java.math.BigDecimal consCheck) {
		this.consCheck = consCheck;
	}

	public java.math.BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(java.math.BigDecimal profit) {
		this.profit = profit;
	}

	public java.math.BigDecimal getPrc() {
		return prc;
	}

	public void setPrc(java.math.BigDecimal prc) {
		this.prc = prc;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public String getAgent() {
		return agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	public java.math.BigDecimal getAgentPrc() {
		return agentPrc;
	}

	public void setAgentPrc(java.math.BigDecimal agentPrc) {
		this.agentPrc = agentPrc;
	}
	
}
