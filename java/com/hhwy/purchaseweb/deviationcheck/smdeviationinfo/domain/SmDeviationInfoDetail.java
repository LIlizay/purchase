package com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * <b>类 名 称：</b>SmDeviationInfoDetail<br/>
 * <b>类 描 述：</b><br/>  偏差预警信息详情类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月20日 上午10:06:14<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmDeviationInfoDetail {

	private String id;
	
	//用户实体id
    private String consId;
	
	//用户名称
	private String consName;
    
    //预警日期
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date warningDate;
    
    //预警类型( 01:超用,正偏差; 02:偏差,负偏差)
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_deviation" ,field="warningTypeName")
    private String warningType;
    private String warningTypeName;
    
    //预警级别
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_warnClass" ,field="warningGradeName")
    private String warningGrade;
    private String warningGradeName;
    
    //部门id
    private String orgNo;
    
	/*//客户状态：01、未读，02、已读
	private String status;
	
	//售电公司工作人员状态：'01'未读,'02'已读
	private String compStatus;*/

	
	
	
	
	
	

    
	//以下为添加的属性
	
	
	
    //用户月度申报电量
    private BigDecimal planPq;

    //用户当月已使用电量
    private BigDecimal actualPq;
    
    //计划日用电量
    private BigDecimal dayPlanPq;
    
    //日用电量
    private BigDecimal dayActualPq;
	
    //用电偏差率
    private BigDecimal deviationProp;
    
    //日偏差率
    private BigDecimal dayDeviationProp;
    
    //合同电量
    private BigDecimal agrePq;
    
    //剩余电量
    private BigDecimal surplusPq;
    
    
    
 /*   //用电完成率
    private BigDecimal actualProp;
    
    //偏差电量
    private BigDecimal deviationPq;
    
    //日偏差电量
    private BigDecimal dayDeviationPq;*/
   

   
	public String getId() {
		return id;
	}

	public BigDecimal getPlanPq() {
		return planPq;
	}

	public void setPlanPq(BigDecimal planPq) {
		this.planPq = planPq;
	}

	public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
	}

	public BigDecimal getDayPlanPq() {
		return dayPlanPq;
	}

	public void setDayPlanPq(BigDecimal dayPlanPq) {
		this.dayPlanPq = dayPlanPq;
	}

	public BigDecimal getDayActualPq() {
		return dayActualPq;
	}

	public void setDayActualPq(BigDecimal dayActualPq) {
		this.dayActualPq = dayActualPq;
	}

	public BigDecimal getDayDeviationProp() {
		return dayDeviationProp;
	}

	public void setDayDeviationProp(BigDecimal dayDeviationProp) {
		this.dayDeviationProp = dayDeviationProp;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

	public BigDecimal getSurplusPq() {
		return surplusPq;
	}

	public void setSurplusPq(BigDecimal surplusPq) {
		this.surplusPq = surplusPq;
	}

	public BigDecimal getDeviationProp() {
		return deviationProp;
	}

	public void setDeviationProp(BigDecimal deviationProp) {
		this.deviationProp = deviationProp;
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

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public Date getWarningDate() {
		return warningDate;
	}

	public void setWarningDate(Date warningDate) {
		this.warningDate = warningDate;
	}

	public String getWarningType() {
		return warningType;
	}

	public void setWarningType(String warningType) {
		this.warningType = warningType;
	}

	public String getWarningTypeName() {
		return warningTypeName;
	}

	public void setWarningTypeName(String warningTypeName) {
		this.warningTypeName = warningTypeName;
	}

	public String getWarningGrade() {
		return warningGrade;
	}

	public void setWarningGrade(String warningGrade) {
		this.warningGrade = warningGrade;
	}

	public String getWarningGradeName() {
		return warningGradeName;
	}

	public void setWarningGradeName(String warningGradeName) {
		this.warningGradeName = warningGradeName;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
}
