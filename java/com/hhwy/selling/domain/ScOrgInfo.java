package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScOrgInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScOrgInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_org_info")
public class ScOrgInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("供电公司名称")
    @Column(name="name", nullable=true, length=256) 
    private String name;
    
    @PropertyDesc("省码")
    @Column(name="province_code", nullable=true, length=8) 
    private String provinceCode;
    
    @PropertyDesc("市码")
    @Column(name="city_code", nullable=true, length=8) 
    private String cityCode;
    
    @PropertyDesc("区县码")
    @Column(name="county_code", nullable=true, length=8) 
    private String countyCode;
    
    @PropertyDesc("增值税名称")
    @Column(name="vat_name", nullable=true, length=256) 
    private String vatName;
    
    @PropertyDesc("增值税号")
    @Column(name="vat_no", nullable=true, length=32) 
    private String vatNo;
    
    @PropertyDesc("开户行")
    @Column(name="bank_name", nullable=true, length=256) 
    private String bankName;
    
    @PropertyDesc("增值税账号")
    @Column(name="vat_acct", nullable=true, length=32) 
    private String vatAcct;
    
    @PropertyDesc("注册地址")
    @Column(name="reg_addr", nullable=true, length=256) 
    private String regAddr;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getProvinceCode(){
        return provinceCode;
    }
    
    public void setProvinceCode(String provinceCode){
        this.provinceCode = provinceCode;
    }
    
    public String getCityCode(){
        return cityCode;
    }
    
    public void setCityCode(String cityCode){
        this.cityCode = cityCode;
    }
    
    public String getCountyCode(){
        return countyCode;
    }
    
    public void setCountyCode(String countyCode){
        this.countyCode = countyCode;
    }
    
    public String getVatName(){
        return vatName;
    }
    
    public void setVatName(String vatName){
        this.vatName = vatName;
    }
    
    public String getVatNo(){
        return vatNo;
    }
    
    public void setVatNo(String vatNo){
        this.vatNo = vatNo;
    }
    
    public String getBankName(){
        return bankName;
    }
    
    public void setBankName(String bankName){
        this.bankName = bankName;
    }
    
    public String getVatAcct(){
        return vatAcct;
    }
    
    public void setVatAcct(String vatAcct){
        this.vatAcct = vatAcct;
    }
    
    public String getRegAddr(){
        return regAddr;
    }
    
    public void setRegAddr(String regAddr){
        this.regAddr = regAddr;
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