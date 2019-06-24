package com.hhwy.purchaseweb.agreement.phmagredeviation.domain;

import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>PhmAgreDeviationDetail<br/>
 * <b>类 描 述：合同偏差考核规则信息详情类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月22日 上午9:22:38<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class PhmAgreDeviationDetail {
	
	private String id;
	
	private String agreId;
	
	@PropertyDesc("正偏差考核标识")
    private String upperCheckFlag;
    
    @PropertyDesc("正偏差阈值")
    private java.math.BigDecimal upperPqProp;
    
    @PropertyDesc("正偏差考核基准")
    private String upperCheckPrcType;
    
    @PropertyDesc("正偏差考核电价")
    private java.math.BigDecimal upperCheckPrc;
    
    @PropertyDesc("正偏差考核电价比例")
    private java.math.BigDecimal upperCheckPrcProp;
    
    @PropertyDesc("负偏差考核标识")
    private String lowerCheckFlag;
    
    @PropertyDesc("负偏差阈值")
    private java.math.BigDecimal lowerPqProp;
    
    @PropertyDesc("负偏差考核基准")
    private String lowerCheckPrcType;
    
    @PropertyDesc("负偏差考核电价")
    private java.math.BigDecimal lowerCheckPrc;
    
    @PropertyDesc("负偏差考核电价比例")
    private java.math.BigDecimal lowerCheckPrcProp;
    
    @PropertyDesc("部门id")
    private String orgNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreId() {
        return agreId;
    }

    public void setAgreId(String agreId) {
        this.agreId = agreId;
    }

    public String getUpperCheckFlag() {
		return upperCheckFlag;
	}

	public void setUpperCheckFlag(String upperCheckFlag) {
		this.upperCheckFlag = upperCheckFlag;
	}

	public java.math.BigDecimal getUpperPqProp() {
		return upperPqProp;
	}

	public void setUpperPqProp(java.math.BigDecimal upperPqProp) {
		this.upperPqProp = upperPqProp;
	}

	public String getUpperCheckPrcType() {
		return upperCheckPrcType;
	}

	public void setUpperCheckPrcType(String upperCheckPrcType) {
		this.upperCheckPrcType = upperCheckPrcType;
	}

	public java.math.BigDecimal getUpperCheckPrc() {
		return upperCheckPrc;
	}

	public void setUpperCheckPrc(java.math.BigDecimal upperCheckPrc) {
		this.upperCheckPrc = upperCheckPrc;
	}

	public java.math.BigDecimal getUpperCheckPrcProp() {
		return upperCheckPrcProp;
	}

	public void setUpperCheckPrcProp(java.math.BigDecimal upperCheckPrcProp) {
		this.upperCheckPrcProp = upperCheckPrcProp;
	}

	public String getLowerCheckFlag() {
		return lowerCheckFlag;
	}

	public void setLowerCheckFlag(String lowerCheckFlag) {
		this.lowerCheckFlag = lowerCheckFlag;
	}

	public java.math.BigDecimal getLowerPqProp() {
		return lowerPqProp;
	}

	public void setLowerPqProp(java.math.BigDecimal lowerPqProp) {
		this.lowerPqProp = lowerPqProp;
	}

	public String getLowerCheckPrcType() {
		return lowerCheckPrcType;
	}

	public void setLowerCheckPrcType(String lowerCheckPrcType) {
		this.lowerCheckPrcType = lowerCheckPrcType;
	}

	public java.math.BigDecimal getLowerCheckPrc() {
		return lowerCheckPrc;
	}

	public void setLowerCheckPrc(java.math.BigDecimal lowerCheckPrc) {
		this.lowerCheckPrc = lowerCheckPrc;
	}

	public java.math.BigDecimal getLowerCheckPrcProp() {
		return lowerCheckPrcProp;
	}

	public void setLowerCheckPrcProp(java.math.BigDecimal lowerCheckPrcProp) {
		this.lowerCheckPrcProp = lowerCheckPrcProp;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
}
