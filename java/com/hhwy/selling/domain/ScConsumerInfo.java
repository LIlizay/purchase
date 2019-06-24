package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScConsumerInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScConsumerInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_consumer_info")
public class ScConsumerInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户编号")
    @Column(name="cons_no", nullable=true, length=16) 
    private String consNo;
    
    @PropertyDesc("用户名称")
    @Column(name="cons_name", nullable=true, length=256) 
    private String consName;
    
    @PropertyDesc("行业分类")
    @Column(name="industry_type", nullable=true, length=8) 
    private String industryType;
    
    @PropertyDesc("所属供电公司")
    @Column(name="org_id", nullable=true, length=32) 
    private String orgId;
    
    @PropertyDesc("省")
    @Column(name="province_code", nullable=true, length=16) 
    private String provinceCode;
    
    @PropertyDesc("市")
    @Column(name="city_code", nullable=true, length=16) 
    private String cityCode;
    
    @PropertyDesc("区县")
    @Column(name="county_code", nullable=true, length=16) 
    private String countyCode;
    
    @PropertyDesc("用电地址")
    @Column(name="elec_addr", nullable=true, length=256) 
    private String elecAddr;
    
    @PropertyDesc("用电容量")
    @Column(name="electric_capacity", nullable=true, length=16) 
    private java.math.BigDecimal electricCapacity;
    
    @PropertyDesc("用电类别")
    @Column(name="elec_type_code", nullable=true, length=8) 
    private String elecTypeCode;
    
    @PropertyDesc("负荷性质")
    @Column(name="lode_attr_code", nullable=true, length=8) 
    private String lodeAttrCode;
    
    @PropertyDesc("电压等级")
    @Column(name="volt_code", nullable=true, length=8) 
    private String voltCode;
    
    @PropertyDesc("营业执照注册号")
    @Column(name="registration_no", nullable=true, length=32) 
    private String registrationNo;
    
    @PropertyDesc("税务登记证号")
    @Column(name="vat_no", nullable=true, length=32) 
    private String vatNo;
    
    @PropertyDesc("组织机构代码")
    @Column(name="org_code", nullable=true, length=32) 
    private String orgCode;
    
    @PropertyDesc("法人名称")
    @Column(name="legal_agent", nullable=true, length=32) 
    private String legalAgent;
    
    @PropertyDesc("法人代表名称")
    @Column(name="auth_agent", nullable=true, length=32) 
    private String authAgent;
    
    @PropertyDesc("企业注册地址")
    @Column(name="registered_address", nullable=true, length=256) 
    private String registeredAddress;
    
    @PropertyDesc("开户银行")
    @Column(name="bank_name", nullable=true, length=256) 
    private String bankName;
    
    @PropertyDesc("开户名称")
    @Column(name="count_name", nullable=true, length=256) 
    private String countName;
    
    @PropertyDesc("开户账号")
    @Column(name="count_no", nullable=true, length=16) 
    private String countNo;
    
    @PropertyDesc("安排年度上限")
    @Column(name="upper_pq", nullable=true, length=16) 
    private java.math.BigDecimal upperPq;
    
    @PropertyDesc("负责人姓名")
    @Column(name="sell_person", nullable=true, length=4) 
    private String sellPerson;
    
    @PropertyDesc("手机号码")
    @Column(name="sell_phone", nullable=true, length=16) 
    private String sellPhone;
    
    @PropertyDesc("职务")
    @Column(name="sell_post", nullable=true, length=32) 
    private String sellPost;
    
    @PropertyDesc("电子邮件")
    @Column(name="sell_e_mail", nullable=true, length=32) 
    private String sellEMail;
    
    @PropertyDesc("办公电话")
    @Column(name="office_phone", nullable=true, length=16) 
    private String officePhone;
    
    @PropertyDesc("传真号")
    @Column(name="fax_no", nullable=true, length=16) 
    private String faxNo;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用户类型")
    @Column(name="cons_type", nullable=true, length=8) 
    private String consType;
    
    @PropertyDesc("所属园区")
    @Column(name="industrial_zone", nullable=true, length=32) 
    private String industrialZone;
    
    @PropertyDesc("是否完成电能信息采集系统")
    @Column(name="scada_flag", nullable=true, length=3) 
    private String scadaFlag;
    
    @PropertyDesc("公示序列号")
    @Column(name="deal_seq", nullable=true, length=32) 
    private String dealSeq;
    
    @PropertyDesc("父级用户id")
    @Column(name="parent_id", nullable=true, length=32) 
    private String parentId;
    
    @PropertyDesc("用户路径，父id间用逗号隔开(不包含本用户id)")
    @Column(name="cons_path", nullable=true, length=300) 
    private String consPath;
    
    @PropertyDesc("是否打包")
    @Column(name="sc_package", nullable=true, length=20) 
    private String scPackage;
    
    @PropertyDesc("市场状态：00：未准入、01：已注册、02：已公示")
    @Column(name="market_status", nullable=true, length=16) 
    private String marketStatus;
    
    @PropertyDesc("是否直购电用户: 0：否、1：是")
    @Column(name="direct_power", nullable=true, length=16) 
    private String directPower;
    
    
    public String getScPackage() {
		return scPackage;
	}

	public void setScPackage(String scPackage) {
		this.scPackage = scPackage;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getConsPath() {
		return consPath;
	}

	public void setConsPath(String consPath) {
		this.consPath = consPath;
	}

	public String getConsNo(){
        return consNo;
    }
    
    public void setConsNo(String consNo){
        this.consNo = consNo;
    }
    
    public String getConsName(){
        return consName;
    }
    
    public void setConsName(String consName){
        this.consName = consName;
    }
    
    public String getIndustryType(){
        return industryType;
    }
    
    public void setIndustryType(String industryType){
        this.industryType = industryType;
    }
    
    public String getOrgId(){
        return orgId;
    }
    
    public void setOrgId(String orgId){
        this.orgId = orgId;
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
    
    public String getElecAddr(){
        return elecAddr;
    }
    
    public void setElecAddr(String elecAddr){
        this.elecAddr = elecAddr;
    }
    
    public java.math.BigDecimal getElectricCapacity(){
        return electricCapacity;
    }
    
    public void setElectricCapacity(java.math.BigDecimal electricCapacity){
        this.electricCapacity = electricCapacity;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
    
    public String getLodeAttrCode(){
        return lodeAttrCode;
    }
    
    public void setLodeAttrCode(String lodeAttrCode){
        this.lodeAttrCode = lodeAttrCode;
    }
    
    public String getVoltCode(){
        return voltCode;
    }
    
    public void setVoltCode(String voltCode){
        this.voltCode = voltCode;
    }
    
    public String getRegistrationNo(){
        return registrationNo;
    }
    
    public void setRegistrationNo(String registrationNo){
        this.registrationNo = registrationNo;
    }
    
    public String getVatNo(){
        return vatNo;
    }
    
    public void setVatNo(String vatNo){
        this.vatNo = vatNo;
    }
    
    public String getOrgCode(){
        return orgCode;
    }
    
    public void setOrgCode(String orgCode){
        this.orgCode = orgCode;
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
    
    public String getRegisteredAddress(){
        return registeredAddress;
    }
    
    public void setRegisteredAddress(String registeredAddress){
        this.registeredAddress = registeredAddress;
    }
    
    public String getBankName(){
        return bankName;
    }
    
    public void setBankName(String bankName){
        this.bankName = bankName;
    }
    
    public String getCountName(){
        return countName;
    }
    
    public void setCountName(String countName){
        this.countName = countName;
    }
    
    public String getCountNo(){
        return countNo;
    }
    
    public void setCountNo(String countNo){
        this.countNo = countNo;
    }
    
    public java.math.BigDecimal getUpperPq(){
        return upperPq;
    }
    
    public void setUpperPq(java.math.BigDecimal upperPq){
        this.upperPq = upperPq;
    }
    
    public String getSellPerson(){
        return sellPerson;
    }
    
    public void setSellPerson(String sellPerson){
        this.sellPerson = sellPerson;
    }
    
    public String getSellPhone(){
        return sellPhone;
    }
    
    public void setSellPhone(String sellPhone){
        this.sellPhone = sellPhone;
    }
    
    public String getSellPost(){
        return sellPost;
    }
    
    public void setSellPost(String sellPost){
        this.sellPost = sellPost;
    }
    
    public String getSellEMail(){
        return sellEMail;
    }
    
    public void setSellEMail(String sellEMail){
        this.sellEMail = sellEMail;
    }
    
    public String getOfficePhone(){
        return officePhone;
    }
    
    public void setOfficePhone(String officePhone){
        this.officePhone = officePhone;
    }
    
    public String getFaxNo(){
        return faxNo;
    }
    
    public void setFaxNo(String faxNo){
        this.faxNo = faxNo;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getConsType(){
        return consType;
    }
    
    public void setConsType(String consType){
        this.consType = consType;
    }
    
    public String getIndustrialZone(){
        return industrialZone;
    }
    
    public void setIndustrialZone(String industrialZone){
        this.industrialZone = industrialZone;
    }
    
    public String getScadaFlag(){
        return scadaFlag;
    }
    
    public void setScadaFlag(String scadaFlag){
        this.scadaFlag = scadaFlag;
    }

	public String getDealSeq() {
		return dealSeq;
	}

	public void setDealSeq(String dealSeq) {
		this.dealSeq = dealSeq;
	}

	public String getMarketStatus() {
		return marketStatus;
	}

	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}

	public String getDirectPower() {
		return directPower;
	}

	public void setDirectPower(String directPower) {
		this.directPower = directPower;
	}
    
   
    
}