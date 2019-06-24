package com.hhwy.purchaseweb.archives.dialogapi;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * ConsDialogDetail
 * 
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class ConsDetail {
	
	@PropertyDesc("实体id")
	private String id;

	@PropertyDesc("用户编号")
	private String consNo;
	
	@PropertyDesc("用户名称")
    private String consName;

	@PropertyDesc("用电类别Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
	
	@PropertyDesc("用电类别")
    private String elecTypeName;

	@PropertyDesc("电压等级Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;

	@PropertyDesc("电压等级")
    private String voltCodeName; //sell_psVoltCode

	@PropertyDesc("用电容量")
	private BigDecimal electricCapacity;
	
	@PropertyDesc("联系人")
    private String contactsName;

	@PropertyDesc("联系人电话")
    private String phone;

	@PropertyDesc("所属供电公司Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_orgCode" ,field="orgName")
    private String orgId;

	@PropertyDesc("所属供电公司")
    private String orgName; 

	@PropertyDesc("电度电价")
    private BigDecimal kwhPrc; 

	@PropertyDesc("输配电价")
    private BigDecimal transPrc; 

	@PropertyDesc("政府性基金及附加")
    private BigDecimal plPrc; 

	@PropertyDesc("用户类型Code")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_consType" ,field="consTypeName")
    private String consType;

	@PropertyDesc("用户类型")
    private String consTypeName;
	
	@PropertyDesc("父级用户id")
    private String parentId;
    
    @PropertyDesc("用户路径，父id间用逗号隔开")
    private String consPath;

    @PropertyDesc("行业分类")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_consType" ,field="industryTypeName")
    private String industryType;
   
    private String industryTypeName;
    
    //省
    private String provinceCode;
    
	//市
    private String cityCode;
    
    //区
    private String countyCode;
    
    //用电地址
    private String elecAddr;
    
    //负荷性质
    private String lodeAttrCode;
    
    
    
    public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getElecAddr() {
		return elecAddr;
	}

	public void setElecAddr(String elecAddr) {
		this.elecAddr = elecAddr;
	}

	public String getLodeAttrCode() {
		return lodeAttrCode;
	}

	public void setLodeAttrCode(String lodeAttrCode) {
		this.lodeAttrCode = lodeAttrCode;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsNo() {
		return consNo;
	}

	public void setConsNo(String consNo) {
		this.consNo = consNo;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public String getContactsName() {
		return contactsName;
	}

	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public BigDecimal getKwhPrc() {
		return kwhPrc;
	}

	public void setKwhPrc(BigDecimal kwhPrc) {
		this.kwhPrc = kwhPrc;
	}

	public BigDecimal getTransPrc() {
		return transPrc;
	}

	public void setTransPrc(BigDecimal transPrc) {
		this.transPrc = transPrc;
	}

	public BigDecimal getPlPrc() {
		return plPrc;
	}

	public void setPlPrc(BigDecimal plPrc) {
		this.plPrc = plPrc;
	}

	public String getConsType() {
		return consType;
	}

	public void setConsType(String consType) {
		this.consType = consType;
	}

	public String getConsTypeName() {
		return consTypeName;
	}

	public void setConsTypeName(String consTypeName) {
		this.consTypeName = consTypeName;
	}

    public java.math.BigDecimal getElectricCapacity() {
        return electricCapacity;
    }

    public void setElectricCapacity(java.math.BigDecimal electricCapacity) {
        this.electricCapacity = electricCapacity;
    }
	
}