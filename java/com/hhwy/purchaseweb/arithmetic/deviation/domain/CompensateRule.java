package com.hhwy.purchaseweb.arithmetic.deviation.domain;

import java.math.BigDecimal;

import com.hhwy.framework.annotation.PropertyDesc;

public class CompensateRule {
	
	@PropertyDesc("是否赔偿")
	private String compFlag;
	 
	@PropertyDesc("赔偿阈值")
	private BigDecimal compPqProp;
	  
	@PropertyDesc("赔偿电价类型") 
	private String compPrcType;
	 
	@PropertyDesc("赔偿电价")
	private BigDecimal compPrc;
	
	@PropertyDesc("赔偿电价比列")
	private BigDecimal compPrcProp;

	public String getCompFlag() {
		return compFlag;
	}

	public void setCompFlag(String compFlag) {
		this.compFlag = compFlag;
	}

	public BigDecimal getCompPqProp() {
		return compPqProp;
	}

	public void setCompPqProp(BigDecimal compPqProp) {
		this.compPqProp = compPqProp;
	}

	public String getCompPrcType() {
		return compPrcType;
	}

	public void setCompPrcType(String compPrcType) {
		this.compPrcType = compPrcType;
	}

	public BigDecimal getCompPrc() {
		return compPrc;
	}

	public void setCompPrc(BigDecimal compPrc) {
		this.compPrc = compPrc;
	}

	public BigDecimal getCompPrcProp() {
		return compPrcProp;
	}

	public void setCompPrcProp(BigDecimal compPrcProp) {
		this.compPrcProp = compPrcProp;
	}
}
