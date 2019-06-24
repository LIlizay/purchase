package com.hhwy.purchaseweb.archives.scorginfo.domain;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

 /**
 * <b>类 名 称：</b>ScOrgInfoDetail<br/>
 * <b>类 描 述：</b><br/>		供电公司的detail类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2016年11月23日 下午2:19:05<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScOrgInfoDetail{

	private String id;
	private String createUser ;
	private String updateUser ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Timestamp createTime ;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Timestamp updateTime ;
	
    //供电公司名称
    private String name;
    
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
    
    //增值税号
    private String vatNo;
    
    //开户行名称
    private String bankName;
    
    //增值税账号
    private String vatAcct;
    
    //注册地址
    private String regAddr;

    
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
}