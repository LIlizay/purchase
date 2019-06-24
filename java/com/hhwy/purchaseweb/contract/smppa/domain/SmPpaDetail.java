package com.hhwy.purchaseweb.contract.smppa.domain;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SmPpaDetail
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class SmPpaDetail{
	
	/**
	 * 实体id
	 */
    private String id;
	
    /**
     * 合同编号
     */
    private String agreNo;
    
    /**
     * 合同名称
     */
    private String agreName;
    
    /**
     * 用户实体id
     */
    private String consId;
    
    /**
     * 用户编号
     */
    private String consNo;
    
    /**
     * 用户名称
     */
    private String consName;
    
    /**
     * 所属供电单位
     */
    private String orgName;
    
    /**
     * 用电类别
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecType;
    
    /**
     * 用电类别
     */
    private String elecTypeName;
    
    /**
     * 合同类型
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_sellElecContractType" ,field="agreTypeName")
    private String agreType;
    
    /**
     * 合同类型
     */
    private String agreTypeName;
    
    /**
     * 合同版本号
     */
    private String agreVer;
    
    /**
     * 合同补充协议
     */
    private String supNum;
    
	/**
     * 售电方签约人
     */
    private String partyB;
    
    /**
     * 客户签约人
     */
    private String partyA;
    
    /**
     * 签订时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date signDate;
    
    /**
     * 生效日期
     */
    private String effectiveDate;
    
    /**
     * 失效日期
     */
    private String expiryDate;
    
    /**
     * 终止日期
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date stopDate;
    
    /**
     * 合同录入人
     */
    private String recorder;
    
    /**
     * 录入时间
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date recordDate;
    
    /**
     * 合同状态
     */ 
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_contractStatusCode" ,field="agreStatusName")
    private String agreStatus;
    
    /**
     * 合同状态
     */ 
    private String agreStatusName;
    
    /**
     * 附件
     */
    private String fileId;
    
    /**
     * 代理方式
     */ 
    private String proxyMode;
    
    /**
     * 代理总电量
     */
    private String proxyPq;
    
    /**
     * 分配方式
     */
    private String distMode;
    
    /**
     * 1月
     */
    private String jan;
    
    /**
     * 2月
     */
    private String feb;
    
    /**
     * 3月
     */ 
    private String mar;
    
    /**
     * 4月
     */ 
    private String apr;
    
    /**
     * 5月
     */ 
    private String may;
    
    /**
     * 6月
     */
    private String jun;
    
    /**
     * 7月
     */
    private String jul;
    
    /**
     * 8月
     */ 
    private String aug;
    
    /**
     * 9月
     */
    private String sept;
    
    /**
     * 10月
     */ 
    private String oct;
    
    /**
     * 11月
     */ 
    private String nov;
    
    /**
     * 12月
     */
    private String dece;
    
  //购电合同查询合同执行情况中的表格用到
    private String itemName;
    
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreNo() {
		return agreNo;
	}

	public void setAgreNo(String agreNo) {
		this.agreNo = agreNo;
	}

	public String getAgreName() {
		return agreName;
	}

	public void setAgreName(String agreName) {
		this.agreName = agreName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getConsNo() {
		return consNo;
	}

	public void setConsNo(String consNo) {
		this.consNo = consNo;
	}

    public String getSupNum() {
		return supNum;
	}

	public void setSupNum(String supNum) {
		this.supNum = supNum;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getElecType() {
		return elecType;
	}

	public void setElecType(String elecType) {
		this.elecType = elecType;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public String getAgreType() {
		return agreType;
	}

	public void setAgreType(String agreType) {
		this.agreType = agreType;
	}

	public String getAgreTypeName() {
		return agreTypeName;
	}

	public void setAgreTypeName(String agreTypeName) {
		this.agreTypeName = agreTypeName;
	}

	public String getAgreVer() {
		return agreVer;
	}

	public void setAgreVer(String agreVer) {
		this.agreVer = agreVer;
	}

	public String getPartyB() {
		return partyB;
	}

	public void setPartyB(String partyB) {
		this.partyB = partyB;
	}

	public String getPartyA() {
		return partyA;
	}

	public void setPartyA(String partyA) {
		this.partyA = partyA;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
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

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public Date getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}

	public String getAgreStatus() {
		return agreStatus;
	}

	public void setAgreStatus(String agreStatus) {
		this.agreStatus = agreStatus;
	}

	public String getAgreStatusName() {
		return agreStatusName;
	}

	public void setAgreStatusName(String agreStatusName) {
		this.agreStatusName = agreStatusName;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getProxyMode() {
		return proxyMode;
	}

	public void setProxyMode(String proxyMode) {
		this.proxyMode = proxyMode;
	}

	public String getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(String proxyPq) {
		this.proxyPq = proxyPq;
	}

	public String getDistMode() {
		return distMode;
	}

	public void setDistMode(String distMode) {
		this.distMode = distMode;
	}

	public String getJan() {
		return jan;
	}

	public void setJan(String jan) {
		this.jan = jan;
	}

	public String getFeb() {
		return feb;
	}

	public void setFeb(String feb) {
		this.feb = feb;
	}

	public String getMar() {
		return mar;
	}

	public void setMar(String mar) {
		this.mar = mar;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getMay() {
		return may;
	}

	public void setMay(String may) {
		this.may = may;
	}

	public String getJun() {
		return jun;
	}

	public void setJun(String jun) {
		this.jun = jun;
	}

	public String getJul() {
		return jul;
	}

	public void setJul(String jul) {
		this.jul = jul;
	}

	public String getAug() {
		return aug;
	}

	public void setAug(String aug) {
		this.aug = aug;
	}

	public String getSept() {
		return sept;
	}

	public void setSept(String sept) {
		this.sept = sept;
	}

	public String getOct() {
		return oct;
	}

	public void setOct(String oct) {
		this.oct = oct;
	}

	public String getNov() {
		return nov;
	}

	public void setNov(String nov) {
		this.nov = nov;
	}

	public String getDece() {
		return dece;
	}

	public void setDece(String dece) {
		this.dece = dece;
	}

}