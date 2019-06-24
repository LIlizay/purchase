package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class SmDeviationEarlyWarningDetail {
	
	@PropertyDesc("规则id")
	private String id;

	@PropertyDesc("规则名称")
    private String name;
    
    @PropertyDesc("状态")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_contractVersStatus " ,field="statusName")
    private String status;
    
    @PropertyDesc("状态名称")
    private String statusName;
    
    @PropertyDesc("预警频率")
    private String frequency;
    
    @PropertyDesc("参考文件")
    private String fileId;
    
    @PropertyDesc("预警规则说明")
    private String ruleExplain;
    
    @PropertyDesc("部门id")
    private String orgNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getRuleExplain() {
		return ruleExplain;
	}

	public void setRuleExplain(String ruleExplain) {
		this.ruleExplain = ruleExplain;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
    
}
