package com.hhwy.purchaseweb.agreement.phmppa.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationDetail;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;

public class PhmPpaDetail {
	
	private String id;
	
    /**
     * 电厂名称
     */
    private String elecName;
    /**
     * 发电类别
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecType" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
	@PropertyDesc("合同编号")
    private String agreNo;
    
    @PropertyDesc("交易中心序列号")
    private String serialNo;
    
    @PropertyDesc("合同名称")
    private String agreName;
    
    @PropertyDesc("合同类型")
    private String agreType;
    
    @PropertyDesc("合同模版")
    private String templateId;
    
    @PropertyDesc("电厂标识")
    private String elecId;
    
    @PropertyDesc("合同电量")
    private String agrePq;
    
    @PropertyDesc("是否含税")
    private String taxFlag;
    
    @PropertyDesc("电力业务许可证编号（发电类）")
    private String licenseNo;
    
    @PropertyDesc("合同开始日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date bgnDate;
    
    @PropertyDesc("合同结束日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date endDate;
    
    @PropertyDesc("合同签订日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date signDate;
    
    @PropertyDesc("备案时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date recordDate;
    
    @PropertyDesc("售方签订人")
    private String partyA;
    
    @PropertyDesc("购方签订人")
    private String partyB;
    
    @PropertyDesc("签订地点")
    private String signAddr;
    
    @PropertyDesc("附件")
    private String fileId;
    
    @PropertyDesc("违约信息备注")
    private String violExplain;
    
    @PropertyDesc("合同状态")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_agreStatusCode" ,field="agreStatusName")
    private String agreStatus;
    private String agreStatusName;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    /**
     * 网内网外
     */
    private String networkFlag;
    
    /**
     * 年
     */
    private String year;
    
    /**
     * 所属发电集团id
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecGenerationGroup" ,field="blocName")
    private String blocId;
    private String blocName;
    
    
    /**
     * 合同机组电量分月信息
     */
    private List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList = new ArrayList<>();
    
    /**
     * 合同偏差考核规则信息
     */
    private PhmAgreDeviationDetail phmAgreDeviationDetail;

    
    
    
    
    
	public String getAgreNo() {
		return agreNo;
	}

	public void setAgreNo(String agreNo) {
		this.agreNo = agreNo;
	}

	public String getAgreName() {
		return agreName;
	}

	public void setAgreName(String agreName) {
		this.agreName = agreName;
	}

	public String getTaxFlag() {
		return taxFlag;
	}

	public void setTaxFlag(String taxFlag) {
		this.taxFlag = taxFlag;
	}

	public String getElecId() {
		return elecId;
	}

	public void setElecId(String elecId) {
		this.elecId = elecId;
	}

	public String getElecName() {
		return elecName;
	}

	public void setElecName(String elecName) {
		this.elecName = elecName;
	}

	public String getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(String agrePq) {
		this.agrePq = agrePq;
	}

	public String getAgreType() {
		return agreType;
	}

	public void setAgreType(String agreType) {
		this.agreType = agreType;
	}

	public String getViolExplain() {
		return violExplain;
	}

	public void setViolExplain(String violExplain) {
		this.violExplain = violExplain;
	}

	public Date getBgnDate() {
		return bgnDate;
	}

	public void setBgnDate(Date bgnDate) {
		this.bgnDate = bgnDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public String getSignAddr() {
		return signAddr;
	}

	public void setSignAddr(String signAddr) {
		this.signAddr = signAddr;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getAgreStatus() {
		return agreStatus;
	}

	public void setAgreStatus(String agreStatus) {
		this.agreStatus = agreStatus;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<PhmGeneratorMonthPqDetail> getPhmGeneratorMonthPqDetailList() {
		return phmGeneratorMonthPqDetailList;
	}

	public void setPhmGeneratorMonthPqDetailList(
			List<PhmGeneratorMonthPqDetail> phmGeneratorMonthPqDetailList) {
		this.phmGeneratorMonthPqDetailList = phmGeneratorMonthPqDetailList;
	}

	public PhmAgreDeviationDetail getPhmAgreDeviationDetail() {
		return phmAgreDeviationDetail;
	}

	public void setPhmAgreDeviationDetail(
			PhmAgreDeviationDetail phmAgreDeviationDetail) {
		this.phmAgreDeviationDetail = phmAgreDeviationDetail;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getLicenseNo() {
		return licenseNo;
	}

	public void setLicenseNo(String licenseNo) {
		this.licenseNo = licenseNo;
	}

	public String getAgreStatusName() {
		return agreStatusName;
	}

	public void setAgreStatusName(String agreStatusName) {
		this.agreStatusName = agreStatusName;
	}

	public String getNetworkFlag() {
		return networkFlag;
	}

	public void setNetworkFlag(String networkFlag) {
		this.networkFlag = networkFlag;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getBlocId() {
		return blocId;
	}

	public void setBlocId(String blocId) {
		this.blocId = blocId;
	}

	public String getBlocName() {
		return blocName;
	}

	public void setBlocName(String blocName) {
		this.blocName = blocName;
	}
    public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}
	
	
}
