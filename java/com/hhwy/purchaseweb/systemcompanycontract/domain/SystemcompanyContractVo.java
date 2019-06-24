package com.hhwy.purchaseweb.systemcompanycontract.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
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
public class SystemcompanyContractVo extends PagingProperty{

	private SystemcompanyContract systemcompanyContract = BaseModel.getModel(SystemcompanyContract.class);
	
	private String id;
	
	//省 查询
	private List<String> provinceCodeList;
	//省
	private String province;
	
	//公司名称
	private String companyName;
	
	//用户类型 查询
	private List<String> consTypeCodeList;
	//用户类型 
	private String consTypeCode;
	
	//经理
	private String managerName;
	
	//版本
	private String versionCode;
	
	//合同开始日期
	private String effectiveDate;
	
	//合同结束日期
	private String expiryDate;
	
	//系统开通时间
	private String systemEffectiveDate;
	
	//系统停止时间
	private String systemExpiryDate;
	
	//账号密码
	private String accountPassword;
	
	//备注
	private String remark;

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

	public List<String> getProvinceCodeList() {
		return provinceCodeList;
	}

	public void setProvinceCodeList(List<String> provinceCodeList) {
		this.provinceCodeList = provinceCodeList;
	}

	public List<String> getConsTypeCodeList() {
		return consTypeCodeList;
	}

	public void setConsTypeCodeList(List<String> consTypeCodeList) {
		this.consTypeCodeList = consTypeCodeList;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getConsTypeCode() {
		return consTypeCode;
	}

	public void setConsTypeCode(String consTypeCode) {
		this.consTypeCode = consTypeCode;
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

	
}