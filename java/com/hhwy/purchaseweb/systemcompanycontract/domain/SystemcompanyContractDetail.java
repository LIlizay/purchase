package com.hhwy.purchaseweb.systemcompanycontract.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.selling.domain.SystemcompanyContract;


/**
 * <b>类 名 称：</b>SystemcompanyContractVo<br/>
 * <b>类 描 述：</b>平台用户管理<br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年11月30日 下午3:32:04<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SystemcompanyContractDetail extends PagingProperty{

	private SystemcompanyContract systemcompanyContract = BaseModel.getModel(SystemcompanyContract.class);
	
	private String id;
	
	//省
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode" ,field="provinceName")
	private String province;
	private String provinceName;
	
	//公司名称
	private String companyName;
	
	//用户类型
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="platform_consType" ,field="consTypeCodeName")
	private String consTypeCode;
	private String consTypeCodeName;
	
	//版本
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="platform_version" ,field="versionCodeName")
	private String versionCode;
	private String versionCodeName;
	
	//访问地址、
	private String companyDomain;
	
	//经理
	private String managerName;
	
	//系统开通时间
	private String systemEffectiveDate;
	
	//系统停止时间
	private String systemExpiryDate;
	
	//合同开始日期
	private String effectiveDate;
	
	//合同结束日期
	private String expiryDate;
	
	//账号密码
	private String accountPassword;
	
	//备注
	private String remark;
	
	private String orgNo;
	

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(String effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public SystemcompanyContract getSystemcompanyContract(){
		return systemcompanyContract;
	}
	
	public void setSystemcompanyContract(SystemcompanyContract systemcompanyContract){
		this.systemcompanyContract = systemcompanyContract;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsTypeCode() {
		return consTypeCode;
	}

	public void setConsTypeCode(String consTypeCode) {
		this.consTypeCode = consTypeCode;
	}

	public String getConsTypeCodeName() {
		return consTypeCodeName;
	}

	public void setConsTypeCodeName(String consTypeCodeName) {
		this.consTypeCodeName = consTypeCodeName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getVersionCodeName() {
		return versionCodeName;
	}

	public void setVersionCodeName(String versionCodeName) {
		this.versionCodeName = versionCodeName;
	}

	public String getCompanyDomain() {
		return companyDomain;
	}

	public void setCompanyDomain(String companyDomain) {
		this.companyDomain = companyDomain;
	}

	public String getSystemEffectiveDate() {
		return systemEffectiveDate;
	}

	public void setSystemEffectiveDate(String systemEffectiveDate) {
		this.systemEffectiveDate = systemEffectiveDate;
	}

	public String getSystemExpiryDate() {
		return systemExpiryDate;
	}

	public void setSystemExpiryDate(String systemExpiryDate) {
		this.systemExpiryDate = systemExpiryDate;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	
}