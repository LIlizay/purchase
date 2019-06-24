package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScCompanyInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScCompanyInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_company_info")
public class ScCompanyInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("省份")
    @Column(name="province", nullable=true, length=32) 
    private String province;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("售电公司名称")
    @Column(name="company_name", nullable=true, length=256) 
    private String companyName;
    
    @PropertyDesc("法定代表人")
    @Column(name="legal_agent", nullable=true, length=8) 
    private String legalAgent;
    
    @PropertyDesc("授权代理人")
    @Column(name="auth_agent", nullable=true, length=8) 
    private String authAgent;
    
    @PropertyDesc("法定地址")
    @Column(name="addr", nullable=true, length=256) 
    private String addr;
    
    @PropertyDesc("电话")
    @Column(name="mobile", nullable=true, length=256) 
    private String mobile;
    
    @PropertyDesc("传真")
    @Column(name="fax", nullable=true, length=32) 
    private String fax;
    
    @PropertyDesc("邮政编码")
    @Column(name="postcode", nullable=true, length=8) 
    private String postcode;
    
    @PropertyDesc("开户银行")
    @Column(name="bank_name", nullable=true, length=32) 
    private String bankName;
    
    @PropertyDesc("银行账号")
    @Column(name="bank_no", nullable=true, length=32) 
    private String bankNo;
    
    @PropertyDesc("税务登记证号")
    @Column(name="vat_no", nullable=true, length=32) 
    private String vatNo;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getProvince(){
        return province;
    }
    
    public void setProvince(String province){
        this.province = province;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getCompanyName(){
        return companyName;
    }
    
    public void setCompanyName(String companyName){
        this.companyName = companyName;
    }
    
    public String getLegalAgent(){
        return legalAgent;
    }
    
    public void setLegalAgent(String legalAgent){
        this.legalAgent = legalAgent;
    }
    
    public String getAuthAgent(){
        return authAgent;
    }
    
    public void setAuthAgent(String authAgent){
        this.authAgent = authAgent;
    }
    
    public String getAddr(){
        return addr;
    }
    
    public void setAddr(String addr){
        this.addr = addr;
    }
    
    public String getMobile(){
        return mobile;
    }
    
    public void setMobile(String mobile){
        this.mobile = mobile;
    }
    
    public String getFax(){
        return fax;
    }
    
    public void setFax(String fax){
        this.fax = fax;
    }
    
    public String getPostcode(){
        return postcode;
    }
    
    public void setPostcode(String postcode){
        this.postcode = postcode;
    }
    
    public String getBankName(){
        return bankName;
    }
    
    public void setBankName(String bankName){
        this.bankName = bankName;
    }
    
    public String getBankNo(){
        return bankNo;
    }
    
    public void setBankNo(String bankNo){
        this.bankNo = bankNo;
    }
    
    public String getVatNo(){
        return vatNo;
    }
    
    public void setVatNo(String vatNo){
        this.vatNo = vatNo;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}