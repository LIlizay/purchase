package com.hhwy.purchaseweb.archives.consdialog.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * ConsDialogVo
 * @author xucong
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ConsDialogVo extends PagingProperty{
	
	@PropertyDesc("用户名称")
	private String consName;
	
	@PropertyDesc("所属供电公司")
	private String orgId;
	
	@PropertyDesc("用电类别")
	private String elecTypeCode;
	
	@PropertyDesc("电压等级")
	private String voltCode;
	
	@PropertyDesc("用户类型")
	private String consType;
	
	private List<String> consIds;

	/**
	 * 省码
	 */
	private String provinceCode;
	
	/**
	 * 市码
	 */
	private String cityCode;
	
	/**
	 * 区县
	 */
	private String countyCode;
	 //筛选条件：用户售电合同在此月内有效
    private String smppaYm;
    
    private String smppaYY;
    
    /**  供电公司名称  **/
    private String name;
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVatName() {
		return vatName;
	}

	public void setVatName(String vatName) {
		this.vatName = vatName;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getVatAcct() {
		return vatAcct;
	}

	public void setVatAcct(String vatAcct) {
		this.vatAcct = vatAcct;
	}

	public String getRegAddr() {
		return regAddr;
	}

	public void setRegAddr(String regAddr) {
		this.regAddr = regAddr;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	/**  增值税名称  **/
    private String vatName;
    
    /**  增值税号  **/
    private String vatNo;
    
    /**  开户行  **/
    private String bankName;
    
    /**  增值税账号  **/
    private String vatAcct;
    
    /**  注册地址  **/
    private String regAddr;
    
    /**  售电公司id  **/
    private String companyId;
    
    /**  部门id  **/
    private String orgNo;
    
    
	
	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getConsType() {
		return consType;
	}

	public void setConsType(String consType) {
		this.consType = consType;
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

	public String getSmppaYm() {
		return smppaYm;
	}

	public void setSmppaYm(String smppaYm) {
		this.smppaYm = smppaYm;
	}

	public String getSmppaYY() {
		return smppaYY;
	}

	public void setSmppaYY(String smppaYY) {
		this.smppaYY = smppaYY;
	}

	public List<String> getConsIds() {
		return consIds;
	}

	public void setConsIds(List<String> consIds) {
		this.consIds = consIds;
	}
	
}