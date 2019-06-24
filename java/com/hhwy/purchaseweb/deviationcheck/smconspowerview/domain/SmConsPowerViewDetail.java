package com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * <b>类 名 称：</b>SmConsPowerViewDetail<br/>
 * <b>类 描 述：</b><br/> 用户用电计划详情类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午6:54:39<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmConsPowerViewDetail {
	
	private String id;
	//计划标识")
    private String planId;
    //用户标识")
    private String consId;
    //日期")
    private String ymd;
    //星期数")
    private String week;
    //计划用电量")
    private BigDecimal planPq;
    //实际用电量")
    private BigDecimal actualPq;
    //部门id")
    private String orgNo;
    //用电年月")
    private String ym;
    //日计划用电量")
    private BigDecimal dayPlanPq;
    //日实际用电量")
    private BigDecimal dayActualPq;
    //是否被删除,0:否，启用状态，1：是，删除状态")
    private String isDelete;
	
    
    //用户名称
    private String consName;
    
    //合同电量（月度委托电量 ）
    private BigDecimal agrePq;
    
    
    
    /**
     * 已完成电量
     */
//    private BigDecimal dealPq; 
    
    //累计偏差率
    private BigDecimal deviationRate;
    
    // 日偏差率
    private BigDecimal dayDeviationRate;
    
    
    //add by wangzelu 监控平台->综合电量->最下面表格使用
    
    /** 完成度 */
   // private BigDecimal completeRate;

    /** 当天的计划电量 */
    private BigDecimal planPqToday;
    
    //用电类别Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
    private String elecTypeName;

	//电压等级Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    
	//用电年月最小日期（yyyyMMdd格式）
	private String minDate;
	//用电年月最大日期（yyyyMMdd格式）
	private String maxDate;
    
	private String forecastPq;		//预测全月用电量
	
	private String forecastRate;	//预测偏差率
	
	private String date;
	
	
	
	
	
	
	
	public BigDecimal getDeviationRate() {
		return deviationRate;
	}

	public void setDeviationRate(BigDecimal deviationRate) {
		this.deviationRate = deviationRate;
	}

	public BigDecimal getDayDeviationRate() {
		return dayDeviationRate;
	}

	public void setDayDeviationRate(BigDecimal dayDeviationRate) {
		this.dayDeviationRate = dayDeviationRate;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public BigDecimal getDayPlanPq() {
		return dayPlanPq;
	}

	public void setDayPlanPq(BigDecimal dayPlanPq) {
		this.dayPlanPq = dayPlanPq;
	}

	public BigDecimal getPlanPqToday() {
		return planPqToday;
	}

	public void setPlanPqToday(BigDecimal planPqToday) {
		this.planPqToday = planPqToday;
	}

	public BigDecimal getDayActualPq() {
		return dayActualPq;
	}

	public void setDayActualPq(BigDecimal dayActualPq) {
		this.dayActualPq = dayActualPq;
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

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public java.math.BigDecimal getPlanPq() {
		return planPq;
	}

	public void setPlanPq(java.math.BigDecimal planPq) {
		this.planPq = planPq;
	}

	public java.math.BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(java.math.BigDecimal actualPq) {
		this.actualPq = actualPq;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
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

	public String getForecastPq() {
		return forecastPq;
	}

	public void setForecastPq(String forecastPq) {
		this.forecastPq = forecastPq;
	}

	public String getForecastRate() {
		return forecastRate;
	}

	public void setForecastRate(String forecastRate) {
		this.forecastRate = forecastRate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
