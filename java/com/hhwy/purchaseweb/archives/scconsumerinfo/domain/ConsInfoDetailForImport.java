package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

 /**
 * <b>类 名 称：</b>ConsInfoDetailForImport<br/>
 * <b>类 描 述：</b><br/>  用于页面中的用户导入、导出的类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年4月18日 下午3:07:25<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ConsInfoDetailForImport {
	
    //所属供电公司
    private String orgId;
    private String orgName; //sell_orgCode
    
    //行业分类
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_industryType" ,field="industryTypeName")
    private String industryType;
    private String industryTypeName;
    
    //用电类别
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
    private String elecTypeName;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; //sell_psVoltCode
    
    //用户类型
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_consType" ,field="consTypeName")
    private String consType;
    private String consTypeName; //sell_consType
    
    @PropertyDesc("负荷性质")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_lodeAttrCode" ,field="lodeAttrName")
    private String lodeAttrCode;
    private String lodeAttrName;
    
    @PropertyDesc("省")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode",field="provinceName")
    private String provinceCode;
    private String provinceName;
    
    @PropertyDesc("市")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="cityCode",field="cityName")
    private String cityCode;
    private String cityName;
    
    @PropertyDesc("区县")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="countyCode",field="countyName")
    private String countyCode;
    private String countyName;
    
    @PropertyDesc("是否完成电能信息采集系统")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="scadaFlagName")
    private String scadaFlag;
    private String scadaFlagName;
    
    @PropertyDesc("所属园区")
    private String industrialZone;
    //所属园区 名称
    private String industrialZoneName;
    
    @PropertyDesc("是否按园区打包")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="scPackageName")
    private String scPackage;
    private String scPackageName;
    
    //市场状态：00：未准入、01：已注册、02：已公示
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_marketStatusCode" ,field="marketStatusName")
    private String marketStatus;
    private String marketStatusName;
    
    //是否直购电用户: 0：否、1：是
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="directPowerName")
    private String directPower;
    private String directPowerName;
    
//------------------------------------以上是需要特殊处理的用户信息字段 ------------------------------------------
	private String id;
    
    @PropertyDesc("用户编号")
    private String consNo;
    
    @PropertyDesc("用户名称")
    private String consName;
    
//    @PropertyDesc("行业分类")
//    private String industryType;
    
//    @PropertyDesc("所属供电公司")
//    private String orgId;
    
//    @PropertyDesc("省")
//    private String provinceCode;
//    
//    @PropertyDesc("市")
//    private String cityCode;
//    
//    @PropertyDesc("区县")
//    private String countyCode;
    
    @PropertyDesc("用电地址")
    private String elecAddr;
    
    @PropertyDesc("用电容量")
    private BigDecimal electricCapacity;
    
//    @PropertyDesc("用电类别")
//    private String elecTypeCode;
    
//    @PropertyDesc("负荷性质")
//    private String lodeAttrCode;
    
//    @PropertyDesc("电压等级")
//    private String voltCode;
    
    @PropertyDesc("营业执照注册号")
    private String registrationNo;
    
    @PropertyDesc("税务登记证号")
    private String vatNo;
    
    @PropertyDesc("组织机构代码")
    private String orgCode;
    
    @PropertyDesc("法人名称")
    private String legalAgent;
    
    @PropertyDesc("法人代表名称")
    private String authAgent;
    
    @PropertyDesc("企业注册地址")
    private String registeredAddress;
    
    @PropertyDesc("开户银行")
    private String bankName;
    
    @PropertyDesc("开户名称")
    private String countName;
    
    @PropertyDesc("开户账号")
    private String countNo;
    
    @PropertyDesc("安排年度上限，单位是（亿千瓦时）")
    private BigDecimal upperPq;
    
    @PropertyDesc("负责人姓名")
    private String sellPerson;
    
    @PropertyDesc("手机号码")
    private String sellPhone;
    
    @PropertyDesc("职务")
    private String sellPost;
    
    @PropertyDesc("电子邮件")
    private String sellEMail;
    
    @PropertyDesc("办公电话")
    private String officePhone;
    
    @PropertyDesc("传真号")
    private String faxNo;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
//    @PropertyDesc("用户类型")
//    private String consType;
    
//    @PropertyDesc("所属园区")
//    private String industrialZone;
    
//    @PropertyDesc("是否完成电能信息采集系统")
//    private String scadaFlag;
    
    @PropertyDesc("公示序列号")
    private String dealSeq;
    
    @PropertyDesc("父级用户id")
    private String parentId;
    
    @PropertyDesc("用户路径，父id间用逗号隔开(不包含本用户id)")
    private String consPath;
    
//    @PropertyDesc("是否按园区打包")
//    private String scPackage;
    
  //------------------------------------以上是用户信息字段 ， 以下是上级单位信息字段 （只用作导出用）------------------------------------------
    //上级单位名称,与上面的parentId属性对应
    private String parentName;
    
    //上级单位用电类别
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="parentElecTypeName")
    private String parentElecTypeCode;
    private String parentElecTypeName;
    
    //上级单位电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="parentVoltCodeName")
    private String parentVoltCode;
    private String parentVoltCodeName;
    
  //------------------------------------以上是上级单位信息字段 ， 以下计量点信息字段------------------------------------------
//    @PropertyDesc("用户实体id")
//    private String consId;
    
    @PropertyDesc("计量点编号")
    private String mpNo;
    
    @PropertyDesc("计量点名称")
    private String mpName;
    
    @PropertyDesc("电能表倍率")
    private String meterRate;
    
    @PropertyDesc("电能表位数")
    private String meterDigits;
    
    @PropertyDesc("电能表编号")
    private String meterNo;
    
//    @PropertyDesc("部门id")
//    private String orgNo;
    
    @PropertyDesc("营销户号")
    private String marketConsNo;
    
  //------------------------------------以上是上级单位信息字段 ， 以下联系人信息字段------------------------------------------
    @PropertyDesc("传真号码")
    private String fax;
    
    @PropertyDesc("联系地址")
    private String addr;
    
    @PropertyDesc("电子邮箱")
    private String eMail;
    
    @PropertyDesc("联系人名称")
    private String contName;
    
    @PropertyDesc("手机号")
    private String phone;
    
    @PropertyDesc("邮政编码")
    private String postcode;
    
//    @PropertyDesc("用户id")
//    private String consId;
    
//    @PropertyDesc("部门id")
//    private String orgNo;
    
    @PropertyDesc("办公电话")
    private String officePhone2;
    
    @PropertyDesc("职务")
    private String post;

    
    
    
    
    
    
    
    
    
    
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

	public String getLodeAttrCode() {
		return lodeAttrCode;
	}

	public void setLodeAttrCode(String lodeAttrCode) {
		this.lodeAttrCode = lodeAttrCode;
	}

	public String getLodeAttrName() {
		return lodeAttrName;
	}

	public void setLodeAttrName(String lodeAttrName) {
		this.lodeAttrName = lodeAttrName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getScadaFlag() {
		return scadaFlag;
	}

	public void setScadaFlag(String scadaFlag) {
		this.scadaFlag = scadaFlag;
	}

	public String getScadaFlagName() {
		return scadaFlagName;
	}

	public void setScadaFlagName(String scadaFlagName) {
		this.scadaFlagName = scadaFlagName;
	}

	public String getIndustrialZone() {
		return industrialZone;
	}

	public void setIndustrialZone(String industrialZone) {
		this.industrialZone = industrialZone;
	}

	public String getIndustrialZoneName() {
		return industrialZoneName;
	}

	public void setIndustrialZoneName(String industrialZoneName) {
		this.industrialZoneName = industrialZoneName;
	}

	public String getScPackage() {
		return scPackage;
	}

	public void setScPackage(String scPackage) {
		this.scPackage = scPackage;
	}

	public String getScPackageName() {
		return scPackageName;
	}

	public void setScPackageName(String scPackageName) {
		this.scPackageName = scPackageName;
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

	public String getElecAddr() {
		return elecAddr;
	}

	public void setElecAddr(String elecAddr) {
		this.elecAddr = elecAddr;
	}

	public BigDecimal getElectricCapacity() {
		return electricCapacity;
	}

	public void setElectricCapacity(BigDecimal electricCapacity) {
		this.electricCapacity = electricCapacity;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLegalAgent() {
		return legalAgent;
	}

	public void setLegalAgent(String legalAgent) {
		this.legalAgent = legalAgent;
	}

	public String getAuthAgent() {
		return authAgent;
	}

	public void setAuthAgent(String authAgent) {
		this.authAgent = authAgent;
	}

	public String getRegisteredAddress() {
		return registeredAddress;
	}

	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCountName() {
		return countName;
	}

	public void setCountName(String countName) {
		this.countName = countName;
	}

	public String getCountNo() {
		return countNo;
	}

	public void setCountNo(String countNo) {
		this.countNo = countNo;
	}

	public BigDecimal getUpperPq() {
		return upperPq;
	}

	public void setUpperPq(BigDecimal upperPq) {
		this.upperPq = upperPq;
	}

	public String getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(String sellPerson) {
		this.sellPerson = sellPerson;
	}

	public String getSellPhone() {
		return sellPhone;
	}

	public void setSellPhone(String sellPhone) {
		this.sellPhone = sellPhone;
	}

	public String getSellPost() {
		return sellPost;
	}

	public void setSellPost(String sellPost) {
		this.sellPost = sellPost;
	}

	public String getSellEMail() {
		return sellEMail;
	}

	public void setSellEMail(String sellEMail) {
		this.sellEMail = sellEMail;
	}

	public String getOfficePhone() {
		return officePhone;
	}

	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getDealSeq() {
		return dealSeq;
	}

	public void setDealSeq(String dealSeq) {
		this.dealSeq = dealSeq;
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

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getParentElecTypeCode() {
		return parentElecTypeCode;
	}

	public void setParentElecTypeCode(String parentElecTypeCode) {
		this.parentElecTypeCode = parentElecTypeCode;
	}

	public String getParentElecTypeName() {
		return parentElecTypeName;
	}

	public void setParentElecTypeName(String parentElecTypeName) {
		this.parentElecTypeName = parentElecTypeName;
	}

	public String getParentVoltCode() {
		return parentVoltCode;
	}

	public void setParentVoltCode(String parentVoltCode) {
		this.parentVoltCode = parentVoltCode;
	}

	public String getParentVoltCodeName() {
		return parentVoltCodeName;
	}

	public void setParentVoltCodeName(String parentVoltCodeName) {
		this.parentVoltCodeName = parentVoltCodeName;
	}

	public String getMpNo() {
		return mpNo;
	}

	public void setMpNo(String mpNo) {
		this.mpNo = mpNo;
	}

	public String getMpName() {
		return mpName;
	}

	public void setMpName(String mpName) {
		this.mpName = mpName;
	}

	public String getMeterRate() {
		return meterRate;
	}

	public void setMeterRate(String meterRate) {
		this.meterRate = meterRate;
	}

	public String getMeterDigits() {
		return meterDigits;
	}

	public void setMeterDigits(String meterDigits) {
		this.meterDigits = meterDigits;
	}

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

	public String getMarketConsNo() {
		return marketConsNo;
	}

	public void setMarketConsNo(String marketConsNo) {
		this.marketConsNo = marketConsNo;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getOfficePhone2() {
		return officePhone2;
	}

	public void setOfficePhone2(String officePhone2) {
		this.officePhone2 = officePhone2;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getMarketStatus() {
		return marketStatus;
	}

	public void setMarketStatus(String marketStatus) {
		this.marketStatus = marketStatus;
	}

	public String getMarketStatusName() {
		return marketStatusName;
	}

	public void setMarketStatusName(String marketStatusName) {
		this.marketStatusName = marketStatusName;
	}

	public String getDirectPower() {
		return directPower;
	}

	public void setDirectPower(String directPower) {
		this.directPower = directPower;
	}

	public String getDirectPowerName() {
		return directPowerName;
	}

	public void setDirectPowerName(String directPowerName) {
		this.directPowerName = directPowerName;
	}
	
}