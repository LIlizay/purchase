package com.hhwy.purchaseweb.systemcompanyapproval.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class SystemcompanyApprovalDetail {

	private String id;
	
	private String domainId;
	//省
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode" ,field="provinceName")
	private String province;
	private String provinceName;
		
	//公司名称
	private String companyName;
	
	//流程发起时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone = "GMT+8") 
	private Date createTime;
	
	//审核状态
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="platform_approvalType" ,field="approvalStatusName")
	private String approvalStatus;
	private String approvalStatusName;
	
 
    
    @PropertyDesc("系统开通时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date systemEffectiveDate;
    
    @PropertyDesc("系统停止时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date systemExpiryDate;
    
    @PropertyDesc("合同开始时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date effectiveDate;
    
    @PropertyDesc("合同结束时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date expiryDate;
    
    @PropertyDesc("系统开通时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date systemEffectiveDateNew;
    
    @PropertyDesc("系统停止时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date systemExpiryDateNew;
    
    @PropertyDesc("合同开始时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date effectiveDateNew;
    
    @PropertyDesc("合同结束时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date expiryDateNew;
    
    @PropertyDesc("续费原因")
    private String cause;
    
    @PropertyDesc("附件")
    private String fileId;
    
    @PropertyDesc("是否同意标识")
    private String agreeSign;
    
    @PropertyDesc("审批人")
    private String approver;
    
    @PropertyDesc("审批意见")
    private String opinion;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    //用户类型
    private String consTypeCode;
    
    //系统版本
    private String versionCode;
    private String versionCodeName;
    
    //经理
    private String managerName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getApprovalStatusName() {
		return approvalStatusName;
	}

	public void setApprovalStatusName(String approvalStatusName) {
		this.approvalStatusName = approvalStatusName;
	}

	public Date getSystemEffectiveDate() {
		return systemEffectiveDate;
	}

	public void setSystemEffectiveDate(Date systemEffectiveDate) {
		this.systemEffectiveDate = systemEffectiveDate;
	}

	public Date getSystemExpiryDate() {
		return systemExpiryDate;
	}

	public void setSystemExpiryDate(Date systemExpiryDate) {
		this.systemExpiryDate = systemExpiryDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public Date getSystemEffectiveDateNew() {
		return systemEffectiveDateNew;
	}

	public void setSystemEffectiveDateNew(Date systemEffectiveDateNew) {
		this.systemEffectiveDateNew = systemEffectiveDateNew;
	}

	public Date getSystemExpiryDateNew() {
		return systemExpiryDateNew;
	}

	public void setSystemExpiryDateNew(Date systemExpiryDateNew) {
		this.systemExpiryDateNew = systemExpiryDateNew;
	}

	public Date getEffectiveDateNew() {
		return effectiveDateNew;
	}

	public void setEffectiveDateNew(Date effectiveDateNew) {
		this.effectiveDateNew = effectiveDateNew;
	}

	public Date getExpiryDateNew() {
		return expiryDateNew;
	}

	public void setExpiryDateNew(Date expiryDateNew) {
		this.expiryDateNew = expiryDateNew;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getAgreeSign() {
		return agreeSign;
	}

	public void setAgreeSign(String agreeSign) {
		this.agreeSign = agreeSign;
	}

	public String getApprover() {
		return approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getConsTypeCode() {
		return consTypeCode;
	}

	public void setConsTypeCode(String consTypeCode) {
		this.consTypeCode = consTypeCode;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionCodeName() {
		return versionCodeName;
	}

	public void setVersionCodeName(String versionCodeName) {
		this.versionCodeName = versionCodeName;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
    
    
}
