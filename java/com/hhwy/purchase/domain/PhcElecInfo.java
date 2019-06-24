package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * PhcElecInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhcElecInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_c_elec_info")
public class PhcElecInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("电厂名称")
    @Column(name="elec_name", nullable=true, length=128) 
    private String elecName;
    
    @PropertyDesc("公用自备")
    @Column(name="mode_code", nullable=true, length=8) 
    private String modeCode;
    
    @PropertyDesc("电厂类别")
    @Column(name="elec_type_code", nullable=true, length=8) 
    private String elecTypeCode;
    
    @PropertyDesc("总装机容量")
    @Column(name="inst_cap", nullable=true, length=16) 
    private java.math.BigDecimal instCap;
    
    @PropertyDesc("交易中心注册时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="regist_date", nullable=true, length=10) 
    private Date registDate;
    
    @PropertyDesc("所属发电集团")
    @Column(name="bloc_id", nullable=true, length=32) 
    private String blocId;
    
    @PropertyDesc("省")
    @Column(name="province_code", nullable=true, length=16) 
    private String provinceCode;
    
    @PropertyDesc("市")
    @Column(name="city_code", nullable=true, length=16) 
    private String cityCode;
    
    @PropertyDesc("区县")
    @Column(name="county_code", nullable=true, length=16) 
    private String countyCode;
    
    @PropertyDesc("是否统调电厂")
    @Column(name="unify_flag", nullable=true, length=3) 
    private String unifyFlag;
    
    @PropertyDesc("调度关系")
    @Column(name="schedul_relation", nullable=true, length=8) 
    private String schedulRelation;
    
    @PropertyDesc("最低技术出力")
    @Column(name="min_technology", nullable=true, length=16) 
    private java.math.BigDecimal minTechnology;
    
    @PropertyDesc("最高技术出力")
    @Column(name="max_technology", nullable=true, length=16) 
    private java.math.BigDecimal maxTechnology;
    
    @PropertyDesc("最大电煤库存")
    @Column(name="max_inventory", nullable=true, length=16) 
    private java.math.BigDecimal maxInventory;
    
    @PropertyDesc("是否网内电厂")
    @Column(name="network_flag", nullable=true, length=3) 
    private String networkFlag;
    
    @PropertyDesc("税务登记号")
    @Column(name="tax_regist_no", nullable=true, length=32) 
    private String taxRegistNo;
    
    @PropertyDesc("法人代表")
    @Column(name="legal_person", nullable=true, length=32) 
    private String legalPerson;
    
    @PropertyDesc("法定地址")
    @Column(name="regist_addr", nullable=true, length=256) 
    private String registAddr;
    
    @PropertyDesc("注册工商局所在地")
    @Column(name="saic_addr", nullable=true, length=256) 
    private String saicAddr;
    
    @PropertyDesc("增值税率")
    @Column(name="educational_tariff", nullable=true, length=6) 
    private java.math.BigDecimal educationalTariff;
    
    @PropertyDesc("许可证颁发机构")
    @Column(name="issuing_agency", nullable=true, length=256) 
    private String issuingAgency;
    
    @PropertyDesc("许可证编号")
    @Column(name="permit_no", nullable=true, length=32) 
    private String permitNo;
    
    @PropertyDesc("许可证失效时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="expire_date", nullable=true, length=10) 
    private Date expireDate;
    
    @PropertyDesc("工商局注册时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="regist_date2", nullable=true, length=10) 
    private Date registDate2;
    
    @PropertyDesc("开户银行")
    @Column(name="bank_name", nullable=true, length=256) 
    private String bankName;
    
    @PropertyDesc("开户行账号")
    @Column(name="bank_no", nullable=true, length=32) 
    private String bankNo;
    
    @PropertyDesc("开户名称")
    @Column(name="account_name", nullable=true, length=256) 
    private String accountName;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getElecName(){
        return elecName;
    }
    
    public void setElecName(String elecName){
        this.elecName = elecName;
    }
    
    public String getModeCode(){
        return modeCode;
    }
    
    public void setModeCode(String modeCode){
        this.modeCode = modeCode;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
    
    public java.math.BigDecimal getInstCap(){
        return instCap;
    }
    
    public void setInstCap(java.math.BigDecimal instCap){
        this.instCap = instCap;
    }
    
    public Date getRegistDate(){
        return registDate;
    }
    
    public void setRegistDate(Date registDate){
        this.registDate = registDate;
    }
    
    public String getBlocId(){
        return blocId;
    }
    
    public void setBlocId(String blocId){
        this.blocId = blocId;
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
    
    public String getUnifyFlag(){
        return unifyFlag;
    }
    
    public void setUnifyFlag(String unifyFlag){
        this.unifyFlag = unifyFlag;
    }
    
    public String getSchedulRelation(){
        return schedulRelation;
    }
    
    public void setSchedulRelation(String schedulRelation){
        this.schedulRelation = schedulRelation;
    }
    
    public java.math.BigDecimal getMinTechnology(){
        return minTechnology;
    }
    
    public void setMinTechnology(java.math.BigDecimal minTechnology){
        this.minTechnology = minTechnology;
    }
    
    public java.math.BigDecimal getMaxTechnology(){
        return maxTechnology;
    }
    
    public void setMaxTechnology(java.math.BigDecimal maxTechnology){
        this.maxTechnology = maxTechnology;
    }
    
    public java.math.BigDecimal getMaxInventory(){
        return maxInventory;
    }
    
    public void setMaxInventory(java.math.BigDecimal maxInventory){
        this.maxInventory = maxInventory;
    }
    
    public String getNetworkFlag(){
        return networkFlag;
    }
    
    public void setNetworkFlag(String networkFlag){
        this.networkFlag = networkFlag;
    }
    
    public String getTaxRegistNo(){
        return taxRegistNo;
    }
    
    public void setTaxRegistNo(String taxRegistNo){
        this.taxRegistNo = taxRegistNo;
    }
    
    public String getLegalPerson(){
        return legalPerson;
    }
    
    public void setLegalPerson(String legalPerson){
        this.legalPerson = legalPerson;
    }
    
    public String getRegistAddr(){
        return registAddr;
    }
    
    public void setRegistAddr(String registAddr){
        this.registAddr = registAddr;
    }
    
    public String getSaicAddr(){
        return saicAddr;
    }
    
    public void setSaicAddr(String saicAddr){
        this.saicAddr = saicAddr;
    }
    
    public java.math.BigDecimal getEducationalTariff(){
        return educationalTariff;
    }
    
    public void setEducationalTariff(java.math.BigDecimal educationalTariff){
        this.educationalTariff = educationalTariff;
    }
    
    public String getIssuingAgency(){
        return issuingAgency;
    }
    
    public void setIssuingAgency(String issuingAgency){
        this.issuingAgency = issuingAgency;
    }
    
    public String getPermitNo(){
        return permitNo;
    }
    
    public void setPermitNo(String permitNo){
        this.permitNo = permitNo;
    }
    
    public Date getExpireDate(){
        return expireDate;
    }
    
    public void setExpireDate(Date expireDate){
        this.expireDate = expireDate;
    }
    
    public Date getRegistDate2(){
        return registDate2;
    }
    
    public void setRegistDate2(Date registDate2){
        this.registDate2 = registDate2;
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
    
    public String getAccountName(){
        return accountName;
    }
    
    public void setAccountName(String accountName){
        this.accountName = accountName;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}