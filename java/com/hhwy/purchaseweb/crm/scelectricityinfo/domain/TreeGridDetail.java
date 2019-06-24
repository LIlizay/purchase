package com.hhwy.purchaseweb.crm.scelectricityinfo.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class TreeGridDetail {
	
	private String state;		//树表格父节点字段 是否打开节点
	
	private String _parentId;	//子节点字段 对应父节点id
	
	private String consId;		//用户id 根节点标识
	
	private String id;		//用户历史用电信息id
	
	private String consName;	//用户名称
	
	private String year;		//年份
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_lodeAttrCode" ,field="lodeAttrCodeName")
	private String lodeAttrCode;//负荷性质
	
	private String lodeAttrCodeName;//负荷性质名称
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
	private String elecTypeCode;//用电类别
	
	private String elecTypeCodeName;//用电类别名称
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_industryType" ,field="industryTypeName")
	private String industryType;//行业分类
	
	private String industryTypeName;//行业分类名称
	
	private BigDecimal totalPq; //总用电量
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLodeAttrCodeName() {
		return lodeAttrCodeName;
	}

	public void setLodeAttrCodeName(String lodeAttrCodeName) {
		this.lodeAttrCodeName = lodeAttrCodeName;
	}

	public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
	}

	public String getIndustryTypeName() {
		return industryTypeName;
	}

	public void setIndustryTypeName(String industryTypeName) {
		this.industryTypeName = industryTypeName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getLodeAttrCode() {
		return lodeAttrCode;
	}

	public void setLodeAttrCode(String lodeAttrCode) {
		this.lodeAttrCode = lodeAttrCode;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getIndustryType() {
		return industryType;
	}

	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}

	public BigDecimal getTotalPq() {
		return totalPq;
	}

	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
	}
}
