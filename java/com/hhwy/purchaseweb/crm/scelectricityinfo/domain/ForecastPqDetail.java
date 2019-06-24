package com.hhwy.purchaseweb.crm.scelectricityinfo.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class ForecastPqDetail {
	
	private String id;						//历史用电信息id
	
	private String ym;						//年月
	
	private String month;					//月份
	
	private BigDecimal yearAPq; 			//上上上年电量
	
	private BigDecimal yearBPq; 			//上上年电量
	
	private BigDecimal yearCPq;				//上年电量
	
	private BigDecimal dataForecastPq;		//大数据预测电量
	
	private BigDecimal personForecastPq;	//人工预测电量
	
	private BigDecimal yearDPq; 			//当年电量
	
	private BigDecimal upYearPracticalPq;			//去年同期
	
	private BigDecimal 	upMonPracticalPq;		//上期值
	
	private BigDecimal practicalPq;			//实际电量
	
	 //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;

	private String remark;					//备注
	
	private String consId;					//用户id
	
	private String ymName;					//页面显示年月字段
	
	private String consName;
										/*   	 月度负荷预测电量saveList所需数据		  */
	private BigDecimal overPeakPq;
	private BigDecimal overPeakAmt;
	private BigDecimal peakPq;
	private BigDecimal peakAmt;
	private BigDecimal doublePq;
	private BigDecimal doubleAmt;
	private BigDecimal valleyPq;
	private BigDecimal valleyAmt;
	private BigDecimal plainPq;
	private BigDecimal plainAmt;
	private BigDecimal baseAmt;
	private BigDecimal forceAmt;
	private BigDecimal levyAmt;
	private BigDecimal otherAmt;
	private BigDecimal totalAmt;
	private String createTime;
	private String createUser;
	private String updateTime;
	private String updateUser;
	private String orgNo;
	
	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public BigDecimal getYearAPq() {
		return yearAPq;
	}

	public void setYearAPq(BigDecimal yearAPq) {
		this.yearAPq = yearAPq;
	}

	public BigDecimal getYearBPq() {
		return yearBPq;
	}

	public void setYearBPq(BigDecimal yearBPq) {
		this.yearBPq = yearBPq;
	}

	public BigDecimal getYearCPq() {
		return yearCPq;
	}

	public void setYearCPq(BigDecimal yearCPq) {
		this.yearCPq = yearCPq;
	}

	public BigDecimal getDataForecastPq() {
		return dataForecastPq;
	}

	public void setDataForecastPq(BigDecimal dataForecastPq) {
		this.dataForecastPq = dataForecastPq;
	}

	public BigDecimal getPersonForecastPq() {
		return personForecastPq;
	}

	public void setPersonForecastPq(BigDecimal personForecastPq) {
		this.personForecastPq = personForecastPq;
	}

	public BigDecimal getYearDPq() {
		return yearDPq;
	}

	public void setYearDPq(BigDecimal yearDPq) {
		this.yearDPq = yearDPq;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public BigDecimal getPracticalPq() {
		return practicalPq;
	}

	public void setPracticalPq(BigDecimal practicalPq) {
		this.practicalPq = practicalPq;
	}

	public BigDecimal getOverPeakPq() {
		return overPeakPq;
	}

	public void setOverPeakPq(BigDecimal overPeakPq) {
		this.overPeakPq = overPeakPq;
	}

	public BigDecimal getOverPeakAmt() {
		return overPeakAmt;
	}

	public void setOverPeakAmt(BigDecimal overPeakAmt) {
		this.overPeakAmt = overPeakAmt;
	}

	public BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public BigDecimal getPeakAmt() {
		return peakAmt;
	}

	public void setPeakAmt(BigDecimal peakAmt) {
		this.peakAmt = peakAmt;
	}

	public BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public BigDecimal getDoubleAmt() {
		return doubleAmt;
	}

	public void setDoubleAmt(BigDecimal doubleAmt) {
		this.doubleAmt = doubleAmt;
	}

	public BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public BigDecimal getValleyAmt() {
		return valleyAmt;
	}

	public void setValleyAmt(BigDecimal valleyAmt) {
		this.valleyAmt = valleyAmt;
	}

	public BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public BigDecimal getPlainAmt() {
		return plainAmt;
	}

	public void setPlainAmt(BigDecimal plainAmt) {
		this.plainAmt = plainAmt;
	}

	public BigDecimal getBaseAmt() {
		return baseAmt;
	}

	public void setBaseAmt(BigDecimal baseAmt) {
		this.baseAmt = baseAmt;
	}

	public BigDecimal getForceAmt() {
		return forceAmt;
	}

	public void setForceAmt(BigDecimal forceAmt) {
		this.forceAmt = forceAmt;
	}

	public BigDecimal getLevyAmt() {
		return levyAmt;
	}

	public void setLevyAmt(BigDecimal levyAmt) {
		this.levyAmt = levyAmt;
	}

	public BigDecimal getOtherAmt() {
		return otherAmt;
	}

	public void setOtherAmt(BigDecimal otherAmt) {
		this.otherAmt = otherAmt;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getUpYearPracticalPq() {
		return upYearPracticalPq;
	}

	public void setUpYearPracticalPq(BigDecimal upYearPracticalPq) {
		this.upYearPracticalPq = upYearPracticalPq;
	}

	public BigDecimal getUpMonPracticalPq() {
		return upMonPracticalPq;
	}

	public void setUpMonPracticalPq(BigDecimal upMonPracticalPq) {
		this.upMonPracticalPq = upMonPracticalPq;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getYmName() {
		return ymName;
	}

	public void setYmName(String ymName) {
		this.ymName = ymName;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}
	
	
}
