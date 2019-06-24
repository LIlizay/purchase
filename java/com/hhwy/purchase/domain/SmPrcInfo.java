package com.hhwy.purchase.domain;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmPrcInfo
 * 
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name = "SmPrcInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "s_m_prc_info")
public class SmPrcInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;

	@PropertyDesc("电价码")
	@Column(name = "prc_code", nullable = true, length = 16)
	private String prcCode;

	@PropertyDesc("用电类别")
	@Column(name = "elec_type_code", nullable = true, length = 8)
	private String elecTypeCode;

	@PropertyDesc("电压等级")
	@Column(name = "volt_code", nullable = true, length = 8)
	private String voltCode;

	@PropertyDesc("电度电价")
	@Column(name = "kwh_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhPrc;

	@PropertyDesc("输配电价")
	@Column(name = "trans_prc", nullable = true, length = 10)
	private java.math.BigDecimal transPrc;

	@PropertyDesc("基金及附加电价")
	@Column(name = "pl_prc", nullable = true, length = 10)
	private java.math.BigDecimal plPrc;

	@PropertyDesc("电度平时电价")
	@Column(name = "kwh_plain_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhPlainPrc;

	@PropertyDesc("电度峰时电价")
	@Column(name = "kwh_peak_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhPeakPrc;

	@PropertyDesc("电度谷时电价")
	@Column(name = "kwh_valley_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhValleyPrc;

	@PropertyDesc("电度尖峰电价")
	@Column(name = "kwh_top_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhTopPrc;
	
	@PropertyDesc("电度双蓄电价")
	@Column(name = "kwh_dual_prc", nullable = true, length = 10)
	private java.math.BigDecimal kwhDualPrc;
	
	@PropertyDesc("目录平时电价")
	@Column(name = "cata_plain_prc", nullable = true, length = 10)
	private java.math.BigDecimal cataPlainPrc;

	@PropertyDesc("目录峰时电价")
	@Column(name = "cata_peak_prc", nullable = true, length = 10)
	private java.math.BigDecimal cataPeakPrc;

	@PropertyDesc("目录谷时电价")
	@Column(name = "cata_valley_prc", nullable = true, length = 10)
	private java.math.BigDecimal cataValleyPrc;
	
	@PropertyDesc("目录尖峰电价")
	@Column(name = "cata_top_prc", nullable = true, length = 10)
	private java.math.BigDecimal cataTopPrc;
	
	@PropertyDesc("目录双蓄电价")
	@Column(name = "cata_dual_prc", nullable = true, length = 10)
	private java.math.BigDecimal cataDualPrc;

	@PropertyDesc("状态")
	@Column(name = "state", nullable = true, length = 8)
	private String state;

	@PropertyDesc("省份ID")
	@Column(name = "province_id", nullable = true, length = 32)
	private String provinceId;
	
    @PropertyDesc("市,多个市的代码中间用逗号隔开")
    @Column(name="city_codes", nullable=true, length=256) 
    private String cityCodes;
    
    @PropertyDesc("区县,多个区县的代码中间用逗号隔开")
    @Column(name="county_codes", nullable=true, length=256) 
    private String countyCodes;
    
    @PropertyDesc("生效月份,多个月份中间用逗号隔开")
    @Column(name="effect_months", nullable=true, length=64) 
    private String effectMonths;
    
    @PropertyDesc("列表顺序，同一电价规则此行相同")
    @Column(name="list_sort", nullable=false, length=10) 
    private int listSort;

	@PropertyDesc("部门id")
	@Column(name = "org_no", nullable = true, length = 32)
	private String orgNo;

	
	public int getListSort() {
		return listSort;
	}

	public void setListSort(int listSort) {
		this.listSort = listSort;
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

	public String getEffectMonths() {
		return effectMonths;
	}

	public void setEffectMonths(String effectMonths) {
		this.effectMonths = effectMonths;
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

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
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

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public java.math.BigDecimal getKwhPrc() {
		return kwhPrc;
	}

	public void setKwhPrc(java.math.BigDecimal kwhPrc) {
		this.kwhPrc = kwhPrc;
	}

	public java.math.BigDecimal getTransPrc() {
		return transPrc;
	}

	public void setTransPrc(java.math.BigDecimal transPrc) {
		this.transPrc = transPrc;
	}

	public java.math.BigDecimal getPlPrc() {
		return plPrc;
	}

	public void setPlPrc(java.math.BigDecimal plPrc) {
		this.plPrc = plPrc;
	}

	public java.math.BigDecimal getKwhPlainPrc() {
		return kwhPlainPrc;
	}

	public void setKwhPlainPrc(java.math.BigDecimal kwhPlainPrc) {
		this.kwhPlainPrc = kwhPlainPrc;
	}

	public java.math.BigDecimal getKwhPeakPrc() {
		return kwhPeakPrc;
	}

	public void setKwhPeakPrc(java.math.BigDecimal kwhPeakPrc) {
		this.kwhPeakPrc = kwhPeakPrc;
	}

	public java.math.BigDecimal getKwhValleyPrc() {
		return kwhValleyPrc;
	}

	public void setKwhValleyPrc(java.math.BigDecimal kwhValleyPrc) {
		this.kwhValleyPrc = kwhValleyPrc;
	}

	public java.math.BigDecimal getCataPlainPrc() {
		return cataPlainPrc;
	}

	public void setCataPlainPrc(java.math.BigDecimal cataPlainPrc) {
		this.cataPlainPrc = cataPlainPrc;
	}

	public java.math.BigDecimal getCataPeakPrc() {
		return cataPeakPrc;
	}

	public void setCataPeakPrc(java.math.BigDecimal cataPeakPrc) {
		this.cataPeakPrc = cataPeakPrc;
	}

	public java.math.BigDecimal getCataValleyPrc() {
		return cataValleyPrc;
	}

	public void setCataValleyPrc(java.math.BigDecimal cataValleyPrc) {
		this.cataValleyPrc = cataValleyPrc;
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

}