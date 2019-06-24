package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.math.BigDecimal;
import java.util.List;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * ScConsumerInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class ScConsumerInfoDetail {
	
	/**
	 * 实体id
	 */
	private String id;
	
    //用户名称
    private String consName;
    
    //所属供电公司
    private String orgId;

    private String orgName; //sell_orgCode
    
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
    
    //市场状态：00：未准入、01：已注册、02：已公示
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_marketStatusCode" ,field="marketStatusName")
    private String marketStatus;
    private String marketStatusName;
    
    //是否直购电用户: 0：否、1：是
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="directPowerName")
    private String directPower;
    private String directPowerName;
    
    @PropertyDesc("所属园区")
    private String industrialZone;
   
    private String industrialZoneName;
    
    @PropertyDesc("用电容量")
    private BigDecimal electricCapacity;
    
    @PropertyDesc("负责人姓名(客户经理)")
    private String sellPerson;
    
    
    @PropertyDesc("父级用户id")
    private String parentId;
    
    @PropertyDesc("用户路径，父id间用逗号隔开(不包含本用户id)")
    private String consPath;
    
    private String state;//树表格父节点字段 是否打开节点
    
    //统计子节点
    private Integer count;
    
  //子节点字段 对应父节点id
    private String _parentId;
    
    private List<ScConsumerInfoDetail> children;	//其子用户
    
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
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

	public List<ScConsumerInfoDetail> getChildren() {
		return children;
	}

	public void setChildren(List<ScConsumerInfoDetail> children) {
		this.children = children;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
    public String getConsName(){
        return consName;
    }
    
    public void setConsName(String consName){
        this.consName = consName;
    }
    
    public String getOrgId(){
        return orgId  ;
    }
    
    public void setOrgId(String orgId){
        this.orgId   = orgId  ;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
   
	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public BigDecimal getElectricCapacity() {
		return electricCapacity;
	}

	public void setElectricCapacity(BigDecimal electricCapacity) {
		this.electricCapacity = electricCapacity;
	}

	public String getSellPerson() {
		return sellPerson;
	}

	public void setSellPerson(String sellPerson) {
		this.sellPerson = sellPerson;
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
    
}