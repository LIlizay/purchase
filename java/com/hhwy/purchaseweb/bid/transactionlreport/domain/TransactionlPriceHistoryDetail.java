package com.hhwy.purchaseweb.bid.transactionlreport.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionlPriceHistoryDetail  implements Serializable{
    
	 private static final long serialVersionUID = 1L;

	 private String ym;
     
     private BigDecimal reportPq;
     
     private BigDecimal reportPrc;
     
     private int stage;
     
     private BigDecimal dealPq;
     
     private BigDecimal dealPrc;

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public BigDecimal getReportPrc() {
		return reportPrc;
	}

	public void setReportPrc(BigDecimal reportPrc) {
		this.reportPrc = reportPrc;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public BigDecimal getDealPq() {
		return dealPq;
	}

	public void setDealPq(BigDecimal dealPq) {
		this.dealPq = dealPq;
	}

	public BigDecimal getDealPrc() {
		return dealPrc;
	}

	public void setDealPrc(BigDecimal dealPrc) {
		this.dealPrc = dealPrc;
	}
}
