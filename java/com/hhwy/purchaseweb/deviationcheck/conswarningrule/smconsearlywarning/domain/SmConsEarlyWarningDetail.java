package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class SmConsEarlyWarningDetail {

	@PropertyDesc("id")
	private String id;
	
	@PropertyDesc("用户名称")
	private String consName;
	
	@PropertyDesc("用户名称")
    private String consId;
    
    @PropertyDesc("规则名称")
    private String name;
    
    @PropertyDesc("规则类型标识（是否默认规则）")
    private String ruleFlag;
    
    @PropertyDesc("预警频率")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_warnFrequency " ,field="frequencyName")
    private String frequency;
    
    @PropertyDesc("预警频率")
    private String frequencyName;
    
    @PropertyDesc("预警规则说明")
    private String ruleExplain;
    
    @PropertyDesc("部门id")
    private String orgNo;

	public String getRuleFlag() {
		return ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	public String getRuleExplain() {
		return ruleExplain;
	}

	public void setRuleExplain(String ruleExplain) {
		this.ruleExplain = ruleExplain;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFrequencyName() {
		return frequencyName;
	}

	public void setFrequencyName(String frequencyName) {
		this.frequencyName = frequencyName;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
	
}
