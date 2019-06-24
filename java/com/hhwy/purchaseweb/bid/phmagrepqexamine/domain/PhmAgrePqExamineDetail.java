package com.hhwy.purchaseweb.bid.phmagrepqexamine.domain;

import java.math.BigDecimal;

public class PhmAgrePqExamineDetail {
	
	private String id;						//审核表id
	
	private String planId;					//购电计划id
	
	private String consId;					//用户id
	
	private String consName;				//用户名称
	
	private String ym;
	
	private BigDecimal agrePq;				// 月度合同电量(委托电量)
	
	private String contId;					//联系人id
	
	private String contName;				//联系人
	
	private String contPhone;				//联系人电话
	
	private String sellPerson;				//客户经理
	
	private BigDecimal businessPqUpper;		//交易电量上限
	
	private BigDecimal bidPq;				//已成交电量
	
	private String fileId;					// 附件
	
	private BigDecimal reportPq;			//已委托电量
	
	private BigDecimal forecastPq;			// 负荷预测电量
	
	private BigDecimal forecastPqMA;		// 上月预测电量
	
	private BigDecimal agrePqMA;			// 上月用电量
	
	private BigDecimal forecastPqMB;		// 上上月预测电量
	
	private BigDecimal agrePqMB;			// 上上月用电量
	
	private BigDecimal forecastPqMC;		// 上上上月预测电量
	
	private BigDecimal agrePqMC;			// 上上上月用电量
	
	private BigDecimal forecastPqYA;		// 去年预测电量
	
	private BigDecimal agrePqYA;			// 去年用电量
	
	private BigDecimal forecastPqYB;		// 前年预测电量
	
	private BigDecimal agrePqYB;			// 前年用电量
	
	private BigDecimal agrePqYC;			// 前前年用电量
	
	private BigDecimal newReportPq;			//本次交易委托电量
	
	private String subitemFlag;				//是否是年分项
	
	private String orgNo;

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

	public String getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(String sellPerson) {
		this.sellPerson = sellPerson;
	}

	public BigDecimal getBusinessPqUpper() {
		return businessPqUpper;
	}

	public void setBusinessPqUpper(BigDecimal businessPqUpper) {
		this.businessPqUpper = businessPqUpper;
	}

	public BigDecimal getBidPq() {
		return bidPq;
	}

	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public BigDecimal getForecastPq() {
		return forecastPq;
	}

	public void setForecastPq(BigDecimal forecastPq) {
		this.forecastPq = forecastPq;
	}

	public BigDecimal getForecastPqMA() {
		return forecastPqMA;
	}

	public void setForecastPqMA(BigDecimal forecastPqMA) {
		this.forecastPqMA = forecastPqMA;
	}

	public BigDecimal getAgrePqMA() {
		return agrePqMA;
	}

	public void setAgrePqMA(BigDecimal agrePqMA) {
		this.agrePqMA = agrePqMA;
	}

	public BigDecimal getForecastPqMB() {
		return forecastPqMB;
	}

	public void setForecastPqMB(BigDecimal forecastPqMB) {
		this.forecastPqMB = forecastPqMB;
	}

	public BigDecimal getAgrePqMB() {
		return agrePqMB;
	}

	public void setAgrePqMB(BigDecimal agrePqMB) {
		this.agrePqMB = agrePqMB;
	}

	public BigDecimal getForecastPqMC() {
		return forecastPqMC;
	}

	public void setForecastPqMC(BigDecimal forecastPqMC) {
		this.forecastPqMC = forecastPqMC;
	}

	public BigDecimal getAgrePqMC() {
		return agrePqMC;
	}

	public void setAgrePqMC(BigDecimal agrePqMC) {
		this.agrePqMC = agrePqMC;
	}

	public BigDecimal getForecastPqYA() {
		return forecastPqYA;
	}

	public void setForecastPqYA(BigDecimal forecastPqYA) {
		this.forecastPqYA = forecastPqYA;
	}

	public BigDecimal getAgrePqYA() {
		return agrePqYA;
	}

	public void setAgrePqYA(BigDecimal agrePqYA) {
		this.agrePqYA = agrePqYA;
	}

	public BigDecimal getForecastPqYB() {
		return forecastPqYB;
	}

	public void setForecastPqYB(BigDecimal forecastPqYB) {
		this.forecastPqYB = forecastPqYB;
	}

	public BigDecimal getAgrePqYB() {
		return agrePqYB;
	}

	public void setAgrePqYB(BigDecimal agrePqYB) {
		this.agrePqYB = agrePqYB;
	}

	public BigDecimal getAgrePqYC() {
		return agrePqYC;
	}

	public void setAgrePqYC(BigDecimal agrePqYC) {
		this.agrePqYC = agrePqYC;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

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

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getNewReportPq() {
		return newReportPq;
	}

	public void setNewReportPq(BigDecimal newReportPq) {
		this.newReportPq = newReportPq;
	}

	public String getSubitemFlag() {
		return subitemFlag;
	}

	public void setSubitemFlag(String subitemFlag) {
		this.subitemFlag = subitemFlag;
	}

	public String getContId() {
		return contId;
	}

	public void setContId(String contId) {
		this.contId = contId;
	}
	
	
	/**============================= 历史遗留==============================*/
	


//	//登录账号
//	private String loginName;
//	/** 用户性质 **/
//	private String izName;
//	//附件查看记录
//	private String fileRecord;
//	// 已提醒次数
//	private String remindNum;

	
}
