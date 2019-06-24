package com.hhwy.purchaseweb.delivery.smprcinfo.domain;

import java.math.BigDecimal;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SmPrcInfoDetail {

	private String id;

	/** 电价码 **/
	private String prcCode;

	/** 用电类别 **/
	@PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_elecTypeCode", field = "elecTypeCodeName")
	private String elecTypeCode;
	private String elecTypeCodeName;

	/** 电压等级 **/
	@PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_psVoltCode", field = "voltCodeName")
	private String voltCode;
	private String voltCodeName;
	
	/** 电度电价 **/
	private BigDecimal kwhPrc;

	/** 输配电价 **/
	private BigDecimal transPrc;

	/** 基金及附加电价 **/
	private BigDecimal plPrc;

	/** 状态 **/
	private String state;

	/** 电度平时电价 **/
	private BigDecimal kwhPlainPrc;

	/** 电度峰时电价 **/
	private BigDecimal kwhPeakPrc;

	/** 电度谷时电价 **/
	private BigDecimal kwhValleyPrc;
	
	//电度尖峰电价
	private java.math.BigDecimal kwhTopPrc;
	
	//电度双蓄电价
	private java.math.BigDecimal kwhDualPrc;

	/** 目录平时电价 **/
	private BigDecimal cataPlainPrc;

	/** 目录峰时电价 **/
	private BigDecimal cataPeakPrc;

	/** 目录谷时电价 **/
	private BigDecimal cataValleyPrc;
	//目录尖峰电价
	private java.math.BigDecimal cataTopPrc;
	
	//目录双蓄电价
	private java.math.BigDecimal cataDualPrc;
	
	/**省份ID**/
	private String provinceId;
	
	//市,多个市的代码中间用逗号隔开
    private String cityCodes;
    
    //区县,多个区县的代码中间用逗号隔开
    private String countyCodes;
    
    //生效月份,多个月份中间用逗号隔开
    private String effectMonths;
    //列表顺序，同一电价规则此行相同
    private int listSort;

    
    
    
	public int getListSort() {
		return listSort;
	}

	public void setListSort(int listSort) {
		this.listSort = listSort;
	}

	public java.math.BigDecimal getKwhTopPrc() {
		return kwhTopPrc;
	}

	public void setKwhTopPrc(java.math.BigDecimal kwhTopPrc) {
		this.kwhTopPrc = kwhTopPrc;
	}

	public java.math.BigDecimal getKwhDualPrc() {
		return kwhDualPrc;
	}

	public void setKwhDualPrc(java.math.BigDecimal kwhDualPrc) {
		this.kwhDualPrc = kwhDualPrc;
	}

	public java.math.BigDecimal getCataTopPrc() {
		return cataTopPrc;
	}

	public void setCataTopPrc(java.math.BigDecimal cataTopPrc) {
		this.cataTopPrc = cataTopPrc;
	}

	public java.math.BigDecimal getCataDualPrc() {
		return cataDualPrc;
	}

	public void setCataDualPrc(java.math.BigDecimal cataDualPrc) {
		this.cataDualPrc = cataDualPrc;
	}

	public String getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(String cityCodes) {
		this.cityCodes = cityCodes;
	}

	public String getCountyCodes() {
		return countyCodes;
	}

	public void setCountyCodes(String countyCodes) {
		this.countyCodes = countyCodes;
	}

	public String getEffectMonths() {
		return effectMonths;
	}

	public void setEffectMonths(String effectMonths) {
		this.effectMonths = effectMonths;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	/** 部门id **/
	private String orgNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrcCode() {
		return prcCode;
	}

	public void setPrcCode(String prcCode) {
		this.prcCode = prcCode;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getKwhPlainPrc() {
		return kwhPlainPrc;
	}

	public void setKwhPlainPrc(BigDecimal kwhPlainPrc) {
		this.kwhPlainPrc = kwhPlainPrc;
	}

	public BigDecimal getKwhPeakPrc() {
		return kwhPeakPrc;
	}

	public void setKwhPeakPrc(BigDecimal kwhPeakPrc) {
		this.kwhPeakPrc = kwhPeakPrc;
	}

	public BigDecimal getKwhValleyPrc() {
		return kwhValleyPrc;
	}

	public void setKwhValleyPrc(BigDecimal kwhValleyPrc) {
		this.kwhValleyPrc = kwhValleyPrc;
	}

	public BigDecimal getCataPlainPrc() {
		return cataPlainPrc;
	}

	public void setCataPlainPrc(BigDecimal cataPlainPrc) {
		this.cataPlainPrc = cataPlainPrc;
	}

	public BigDecimal getCataPeakPrc() {
		return cataPeakPrc;
	}

	public void setCataPeakPrc(BigDecimal cataPeakPrc) {
		this.cataPeakPrc = cataPeakPrc;
	}

	public BigDecimal getCataValleyPrc() {
		return cataValleyPrc;
	}

	public void setCataValleyPrc(BigDecimal cataValleyPrc) {
		this.cataValleyPrc = cataValleyPrc;
	}
}
