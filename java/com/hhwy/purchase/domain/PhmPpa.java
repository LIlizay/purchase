package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * PhmPpa
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmPpa")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_ppa")
public class PhmPpa extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同编号")
    @Column(name="agre_no", nullable=true, length=16) 
    private String agreNo; 
    
    @PropertyDesc("交易中心序列号")
    @Column(name="serial_no", nullable=true, length=32) 
    private String serialNo;
    
    @PropertyDesc("合同名称")
    @Column(name="agre_name", nullable=true, length=128) 
    private String agreName;
    
    @PropertyDesc("合同类型")
    @Column(name="agre_type", nullable=true, length=8) 
    private String agreType;
    
    @PropertyDesc("合同模版")
    @Column(name="template_id", nullable=true, length=32) 
    private String templateId;
    
    @PropertyDesc("电厂标识")
    @Column(name="elec_id", nullable=true, length=32) 
    private String elecId;
    
    @PropertyDesc("合同电量")
    @Column(name="agre_pq", nullable=true, length=8) 
    private String agrePq;
    
    @PropertyDesc("是否含税")
    @Column(name="tax_flag", nullable=true, length=3) 
    private String taxFlag;
    
    @PropertyDesc("电力业务许可证编号（发电类）")
    @Column(name="license_no", nullable=true, length=16) 
    private String licenseNo;
    
    @PropertyDesc("合同开始日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="bgn_date", nullable=true, length=10) 
    private Date bgnDate;
    
    @PropertyDesc("合同结束日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="end_date", nullable=true, length=10) 
    private Date endDate;
    
    @PropertyDesc("合同签订日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="sign_date", nullable=true, length=10) 
    private Date signDate;
    
    @PropertyDesc("备案时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="record_date", nullable=true, length=10) 
    private Date recordDate;
    
    @PropertyDesc("售方签订人")
    @Column(name="party_a", nullable=true, length=8) 
    private String partyA;
    
    @PropertyDesc("购方签订人")
    @Column(name="party_b", nullable=true, length=8) 
    private String partyB;
    
    @PropertyDesc("签订地点")
    @Column(name="sign_addr", nullable=true, length=256) 
    private String signAddr;
    
    @PropertyDesc("附件")
    @Column(name="file_id", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("违约信息备注")
    @Column(name="viol_explain", nullable=true, length=256) 
    private String violExplain;
    
    @PropertyDesc("合同状态")
    @Column(name="agre_status", nullable=true, length=8) 
    private String agreStatus;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getAgreNo(){
        return agreNo;
    }
    
    public void setAgreNo(String agreNo){
        this.agreNo = agreNo;
    }
    
    public String getSerialNo(){
        return serialNo;
    }
    
    public void setSerialNo(String serialNo){
        this.serialNo = serialNo;
    }
    
    public String getAgreName(){
        return agreName;
    }
    
    public void setAgreName(String agreName){
        this.agreName = agreName;
    }
    
    public String getAgreType(){
        return agreType;
    }
    
    public void setAgreType(String agreType){
        this.agreType = agreType;
    }
    
    public String getTemplateId(){
        return templateId;
    }
    
    public void setTemplateId(String templateId){
        this.templateId = templateId;
    }
    
    public String getElecId(){
        return elecId;
    }
    
    public void setElecId(String elecId){
        this.elecId = elecId;
    }
    
    public String getAgrePq(){
        return agrePq;
    }
    
    public void setAgrePq(String agrePq){
        this.agrePq = agrePq;
    }
    
    public String getTaxFlag(){
        return taxFlag;
    }
    
    public void setTaxFlag(String taxFlag){
        this.taxFlag = taxFlag;
    }
    
    public String getLicenseNo(){
        return licenseNo;
    }
    
    public void setLicenseNo(String licenseNo){
        this.licenseNo = licenseNo;
    }
    
    public Date getBgnDate(){
        return bgnDate;
    }
    
    public void setBgnDate(Date bgnDate){
        this.bgnDate = bgnDate;
    }
    
    public Date getEndDate(){
        return endDate;
    }
    
    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }
    
    public Date getSignDate(){
        return signDate;
    }
    
    public void setSignDate(Date signDate){
        this.signDate = signDate;
    }
    
    public Date getRecordDate(){
        return recordDate;
    }
    
    public void setRecordDate(Date recordDate){
        this.recordDate = recordDate;
    }
    
    public String getPartyA(){
        return partyA;
    }
    
    public void setPartyA(String partyA){
        this.partyA = partyA;
    }
    
    public String getPartyB(){
        return partyB;
    }
    
    public void setPartyB(String partyB){
        this.partyB = partyB;
    }
    
    public String getSignAddr(){
        return signAddr;
    }
    
    public void setSignAddr(String signAddr){
        this.signAddr = signAddr;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getViolExplain(){
        return violExplain;
    }
    
    public void setViolExplain(String violExplain){
        this.violExplain = violExplain;
    }
    
    public String getAgreStatus(){
        return agreStatus;
    }
    
    public void setAgreStatus(String agreStatus){
        this.agreStatus = agreStatus;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}