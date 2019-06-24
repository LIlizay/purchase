package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SystemcompanyContract
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SystemcompanyContract")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="system_company_contract")
public class SystemcompanyContract extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户类型01:合同用户02:续费用户03:到期用户04:试用用户")
    @Column(name="cons_type_code", nullable=true, length=8) 
    private String consTypeCode;
    
    @PropertyDesc("版本01:极简版02:基础班03:到期用户04:试用用户")
    @Column(name="version_code", nullable=true, length=64) 
    private String versionCode;
    
    @PropertyDesc("销售经理")
    @Column(name="manager_name", nullable=true, length=32) 
    private String managerName;
    
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
    @Column(name="system_effective_date", nullable=true, length=10) 
    private Date systemEffectiveDate;
    
    @PropertyDesc("系统停止时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="system_expiry_date", nullable=true, length=10) 
    private Date systemExpiryDate;
    
    @PropertyDesc("账号密码")
    @Column(name="account_password", nullable=true, length=128) 
    private String accountPassword;
    
    @PropertyDesc("备注")
    @Column(name="remark", nullable=true, length=1024) 
    private String remark;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsTypeCode(){
        return consTypeCode;
    }
    
    public void setConsTypeCode(String consTypeCode){
        this.consTypeCode = consTypeCode;
    }
    
    public String getVersionCode(){
        return versionCode;
    }
    
    public void setVersionCode(String versionCode){
        this.versionCode = versionCode;
    }
    
    public String getManagerName(){
        return managerName;
    }
    
    public void setManagerName(String managerName){
        this.managerName = managerName;
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
    
    public String getAccountPassword(){
        return accountPassword;
    }
    
    public void setAccountPassword(String accountPassword){
        this.accountPassword = accountPassword;
    }
    
    public String getRemark(){
        return remark;
    }
    
    public void setRemark(String remark){
        this.remark = remark;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}