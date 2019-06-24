package com.hhwy.purchaseweb.archives.consdialog.domain;

import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ConsDialogDetail {
	
	private String createUser ;
	private String updateUser ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Timestamp createTime ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Timestamp updateTime ;

	//省码，说明供电公司属于哪个省，引用国家标准GBT2260-2002
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode")
    private String provinceCode;
    
    //市码，说明供电公司属于哪个市(地区),,引用国家标准GBT2260-2002
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="cityCode")
    private String cityCode;
    
    //区县码，说明供电公司属于哪个市直辖区或市直属县，引用国家标准GBT2260-2002
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="countyCode")
    private String countyCode;
    //增值税名称
    private String vatName;
    
    
    public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
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

	//增值税号
    private String vatNo;
    
    //开户行名称
    private String bankName;
    
    //增值税账号
    private String vatAcct;
    
    //注册地址
    private String regAddr;
    
    
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
    private String contName;

	@PropertyDesc("联系人电话")
    private String contPhone;
	
	@PropertyDesc("客户经理")
	private String sellPerson;

	@PropertyDesc("所属供电公司Code")
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
    
    @PropertyDesc("合同电量")
    private String ppaPq;
    
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
	
	public String getContName() {
		return contName;
	}

	public void setContName(String contName) {
		this.contName = contName;
	}

	public String getContPhone() {
		return contPhone;
	}

	public void setContPhone(String contPhone) {
		this.contPhone = contPhone;
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

	public String getPpaPq() {
		return ppaPq;
	}

	public void setPpaPq(String ppaPq) {
		this.ppaPq = ppaPq;
	}

	public String getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(String sellPerson) {
		this.sellPerson = sellPerson;
	}

}