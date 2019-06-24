package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SmAgreSup
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmAgreSup")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_agre_sup")
public class SmAgreSup extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("购电合同实体id")
    @Column(name="ppa_id", nullable=true, length=32) 
    private String ppaId;
    
    @PropertyDesc("补充协议编号")
    @Column(name="agre_no", nullable=true, length=16) 
    private String agreNo;
    
    @PropertyDesc("补充协议名称")
    @Column(name="agre_name", nullable=true, length=128) 
    private String agreName;
    
    @PropertyDesc("签订日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="sign_date", nullable=true, length=10) 
    private Date signDate;
    
    @PropertyDesc("售电方签订人")
    @Column(name="sell_party", nullable=true, length=64) 
    private String sellParty;
    
    @PropertyDesc("客户签订人")
    @Column(name="cust_party", nullable=true, length=64) 
    private String custParty;
    
    @PropertyDesc("协议状态")
    @Column(name="agre_status", nullable=true, length=8) 
    private String agreStatus;
    
    @PropertyDesc("生效日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="effective_date", nullable=true, length=10) 
    private Date effectiveDate;
    
    @PropertyDesc("失效日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="expiry_date", nullable=true, length=10) 
    private Date expiryDate;
    
    @PropertyDesc("协议附件")
    @Column(name="file_id", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("协议文本内容")
    @Column(name="agre_content", nullable=true, length=16777215) 
    private String agreContent;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getPpaId(){
        return ppaId;
    }
    
    public void setPpaId(String ppaId){
        this.ppaId = ppaId;
    }
    
    public String getAgreNo(){
        return agreNo;
    }
    
    public void setAgreNo(String agreNo){
        this.agreNo = agreNo;
    }
    
    public String getAgreName(){
        return agreName;
    }
    
    public void setAgreName(String agreName){
        this.agreName = agreName;
    }
    
    public Date getSignDate(){
        return signDate;
    }
    
    public void setSignDate(Date signDate){
        this.signDate = signDate;
    }
    
    public String getSellParty(){
        return sellParty;
    }
    
    public void setSellParty(String sellParty){
        this.sellParty = sellParty;
    }
    
    public String getCustParty(){
        return custParty;
    }
    
    public void setCustParty(String custParty){
        this.custParty = custParty;
    }
    
    public String getAgreStatus(){
        return agreStatus;
    }
    
    public void setAgreStatus(String agreStatus){
        this.agreStatus = agreStatus;
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
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getAgreContent(){
        return agreContent;
    }
    
    public void setAgreContent(String agreContent){
        this.agreContent = agreContent;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}