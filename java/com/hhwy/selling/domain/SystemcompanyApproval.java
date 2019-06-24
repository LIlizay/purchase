package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SystemcompanyApproval
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SystemcompanyApproval")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="system_company_approval")
public class SystemcompanyApproval extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("")
    @Column(name="domain_id", nullable=false, length=32) 
    private String domainId;
    
    @PropertyDesc("系统开通时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="system_effective_date", nullable=true, length=10) 
    private Date systemEffectiveDate;
    
    @PropertyDesc("系统停止时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="system_expiry_date", nullable=true, length=10) 
    private Date systemExpiryDate;
    
    @PropertyDesc("合同开始时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="effective_date", nullable=true, length=10) 
    private Date effectiveDate;
    
    @PropertyDesc("合同结束时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="expiry_date", nullable=true, length=10) 
    private Date expiryDate;
    
    @PropertyDesc("系统开通时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="system_effective_date_new", nullable=true, length=10) 
    private Date systemEffectiveDateNew;
    
    @PropertyDesc("系统停止时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="system_expiry_date_new", nullable=true, length=10) 
    private Date systemExpiryDateNew;
    
    @PropertyDesc("合同开始时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="effective_date_new", nullable=true, length=10) 
    private Date effectiveDateNew;
    
    @PropertyDesc("合同结束时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="expiry_date_new", nullable=true, length=10) 
    private Date expiryDateNew;
    
    @PropertyDesc("续费原因")
    @Column(name="cause", nullable=true, length=1024) 
    private String cause;
    
    @PropertyDesc("附件")
    @Column(name="file_id", nullable=true, length=256) 
    private String fileId;
    
    @PropertyDesc("是否同意标识")
    @Column(name="agree_sign", nullable=true, length=10) 
    private String agreeSign;
    
    @PropertyDesc("审批状态")
    @Column(name="approval_status", nullable=true, length=10) 
    private String approvalStatus;
    
    @PropertyDesc("审批人")
    @Column(name="approver", nullable=true, length=64) 
    private String approver;
    
    @PropertyDesc("审批意见")
    @Column(name="opinion", nullable=true, length=128) 
    private String opinion;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getDomainId() {
		return domainId;
	}

	public void setDomainId(String domainId) {
		this.domainId = domainId;
	}

	public Date getSystemEffectiveDate(){
        return systemEffectiveDate;
    }
    
    public void setSystemEffectiveDate(Date systemEffectiveDate){
        this.systemEffectiveDate = systemEffectiveDate;
    }
    
    public Date getSystemExpiryDate(){
        return systemExpiryDate;
    }
    
    public void setSystemExpiryDate(Date systemExpiryDate){
        this.systemExpiryDate = systemExpiryDate;
    }
    
    public Date getEffectiveDate(){
        return effectiveDate;
    }
    
    public void setEffectiveDate(Date effectiveDate){
        this.effectiveDate = effectiveDate;
    }
    
    public Date getExpiryDate(){
        return expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate){
        this.expiryDate = expiryDate;
    }
    
    public Date getSystemEffectiveDateNew(){
        return systemEffectiveDateNew;
    }
    
    public void setSystemEffectiveDateNew(Date systemEffectiveDateNew){
        this.systemEffectiveDateNew = systemEffectiveDateNew;
    }
    
    public Date getSystemExpiryDateNew(){
        return systemExpiryDateNew;
    }
    
    public void setSystemExpiryDateNew(Date systemExpiryDateNew){
        this.systemExpiryDateNew = systemExpiryDateNew;
    }
    
    public Date getEffectiveDateNew(){
        return effectiveDateNew;
    }
    
    public void setEffectiveDateNew(Date effectiveDateNew){
        this.effectiveDateNew = effectiveDateNew;
    }
    
    public Date getExpiryDateNew(){
        return expiryDateNew;
    }
    
    public void setExpiryDateNew(Date expiryDateNew){
        this.expiryDateNew = expiryDateNew;
    }
    
    public String getCause(){
        return cause;
    }
    
    public void setCause(String cause){
        this.cause = cause;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getAgreeSign(){
        return agreeSign;
    }
    
    public void setAgreeSign(String agreeSign){
        this.agreeSign = agreeSign;
    }
    
    public String getApprover(){
        return approver;
    }
    
    public void setApprover(String approver){
        this.approver = approver;
    }
    
    public String getOpinion(){
        return opinion;
    }
    
    public void setOpinion(String opinion){
        this.opinion = opinion;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}
    
}