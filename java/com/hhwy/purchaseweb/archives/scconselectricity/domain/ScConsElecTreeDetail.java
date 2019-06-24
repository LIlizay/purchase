package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class ScConsElecTreeDetail {
	
	private String state;		//树表格父节点字段 是否打开节点
	
	private String _parentId;	//子节点字段 对应父节点id
	
	private String consId;		//用户id 根节点标识
	
	private String consName;	//用户名称
	
	private String year;		//年份
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
	private String voltCode;//电压等级
	private String voltCodeName;//电压等级名称
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
	private String elecTypeCode;//用电类别
	private String elecTypeCodeName;//用电类别名称
	
	private BigDecimal totalPq; //总用电量
	
	private BigDecimal totalAmt; //总用电费
	
	//历史用电信息表ID 用于实际用电量录入功能的 更新和修改
	private String id;
	//年月
//	private String ym;
	
	//用户数
	private Integer consNum;
	
	//实际用电量
	private BigDecimal practicalPq;
	
	//委托电量
	private BigDecimal reportPq;
	
	//总交易电量
	private BigDecimal totalTranPq;

    //年月/
    private String ym;
    
    //尖峰电量
    private BigDecimal overPeakPq;
    
    //尖峰电费
    private BigDecimal overPeakAmt;
    
    //峰时电量
    private BigDecimal peakPq;
    
    //峰时电费
    private BigDecimal peakAmt;
    
    //双蓄电量
    private BigDecimal doublePq;
    
    //双蓄电费
    private BigDecimal doubleAmt;
    
    //谷时电量
    private BigDecimal valleyPq;
    
    //谷时电费
    private BigDecimal valleyAmt;
    
    //平时电量
    private BigDecimal plainPq;
    
    //平时电费
    private BigDecimal plainAmt;
    
    //基本电费
    private BigDecimal baseAmt;
    
    //力调电费
    private BigDecimal forceAmt;
    
    //代征电费
    private BigDecimal levyAmt;
    
    //其他电费
    private BigDecimal otherAmt;
    
    //大数据预测电量
    private BigDecimal dataForecastPq;
    
    //人工预测电量
    private BigDecimal personForecastPq;
    
    //部门id"
    private String orgNo;
    
    
    
    //江苏结算专用		--by wangzelu
    //结算的id
    private String settlementId;
    
    //工业电量(江苏专用,在实际用电量录入时存入)
    private BigDecimal industryPq;
    
    //商业电量(江苏专用,在实际用电量录入时存入)
    private BigDecimal businessPq;
    
    
    
    
	
	public String getSettlementId() {
		return settlementId;
	}

	public void setSettlementId(String settlementId) {
		this.settlementId = settlementId;
	}

	public BigDecimal getIndustryPq() {
		return industryPq;
	}

	public void setIndustryPq(BigDecimal industryPq) {
		this.industryPq = industryPq;
	}

	public BigDecimal getBusinessPq() {
		return businessPq;
	}

	public void setBusinessPq(BigDecimal businessPq) {
		this.businessPq = businessPq;
	}

	public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
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

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public BigDecimal getTotalPq() {
		return totalPq;
	}

	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public BigDecimal getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(BigDecimal totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public Integer getConsNum() {
		return consNum;
	}

	public void setConsNum(Integer consNum) {
		this.consNum = consNum;
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

	public BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getOverPeakAmt() {
		return overPeakAmt;
	}

	public void setOverPeakAmt(BigDecimal overPeakAmt) {
		this.overPeakAmt = overPeakAmt;
	}

	public BigDecimal getPeakAmt() {
		return peakAmt;
	}

	public void setPeakAmt(BigDecimal peakAmt) {
		this.peakAmt = peakAmt;
	}

	public BigDecimal getDoubleAmt() {
		return doubleAmt;
	}

	public void setDoubleAmt(BigDecimal doubleAmt) {
		this.doubleAmt = doubleAmt;
	}

	public BigDecimal getValleyAmt() {
		return valleyAmt;
	}

	public void setValleyAmt(BigDecimal valleyAmt) {
		this.valleyAmt = valleyAmt;
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

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public BigDecimal getTotalTranPq() {
		return totalTranPq;
	}

	public void setTotalTranPq(BigDecimal totalTranPq) {
		this.totalTranPq = totalTranPq;
	}
	
}
