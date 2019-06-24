package com.hhwy.purchaseweb.bid.phmagrepqexamine.domain;

import java.math.BigDecimal;
import java.util.List;

public class ExamineDetail {
	
	//总用户数
	private Long consNum;
	
	//交易电量合计
	private BigDecimal businessPqCount;
	
	//成交电量合计
	private BigDecimal bidPqCount;
	
	//月度合同电量合计
	private BigDecimal agrePqCount;
	
	//用户委托电量信息
	private List<PhmAgrePqExamineDetail> phmAgrePqExamineList;
	
	//月度负荷预测权限
	private String forecastRes;

	public List<PhmAgrePqExamineDetail> getPhmAgrePqExamineList() {
		return phmAgrePqExamineList;
	}

	public void setPhmAgrePqExamineList(
			List<PhmAgrePqExamineDetail> phmAgrePqExamineList) {
		this.phmAgrePqExamineList = phmAgrePqExamineList;
	}

	public Long getConsNum() {
		return consNum;
	}

	public void setConsNum(Long consNum) {
		this.consNum = consNum;
	}

	public BigDecimal getBusinessPqCount() {
		return businessPqCount;
	}

	public void setBusinessPqCount(BigDecimal businessPqCount) {
		this.businessPqCount = businessPqCount;
	}

	public BigDecimal getBidPqCount() {
		return bidPqCount;
	}

	public void setBidPqCount(BigDecimal bidPqCount) {
		this.bidPqCount = bidPqCount;
	}

	public BigDecimal getAgrePqCount() {
		return agrePqCount;
	}

	public void setAgrePqCount(BigDecimal agrePqCount) {
		this.agrePqCount = agrePqCount;
	}

	public String getForecastRes() {
		return forecastRes;
	}

	public void setForecastRes(String forecastRes) {
		this.forecastRes = forecastRes;
	}
	
}

