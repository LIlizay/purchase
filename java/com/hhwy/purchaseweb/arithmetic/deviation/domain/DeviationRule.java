package com.hhwy.purchaseweb.arithmetic.deviation.domain;

import java.math.BigDecimal;

import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>DeviationRule<br/>
 * <b>类 描 述：</b>偏差考核规则（仅计算时使用）<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年7月27日 下午7:48:45<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DeviationRule {
	 
	 @PropertyDesc("交割优先原则")
	 private String priorityFlag;
	 
	 @PropertyDesc("正偏差考核标识")
	 private String upperCheckFlag;
	 
	 @PropertyDesc("正偏差阈值")
	 private BigDecimal upperPqProp;
	 
	 @PropertyDesc("正偏差电价类型")
	 private String upperPrcType;
	 
	 @PropertyDesc("正偏差电价") 
	 private BigDecimal upperPrc;
	 
	 @PropertyDesc("正偏差电价比例值")
	 private BigDecimal upperPrcProp;
	 
	 @PropertyDesc("正偏差惩罚电价")
	 private String upperCheckPrcType;
	 
	 @PropertyDesc("正偏差惩罚协议价")
	 private BigDecimal upperCheckPrc;
	 
	 @PropertyDesc("正偏差惩罚电价比列")
	 private BigDecimal upperCheckPrcProp;
	 
	 @PropertyDesc("负偏差是否考核")
	 private String lowerCheckFlag;
	 
	 @PropertyDesc("负偏差拆分考核标识")
	 private String splitCheckFlag;
	 
	 @PropertyDesc("负偏差阈值")
	 private BigDecimal lowerPqProp;
	 
	 @PropertyDesc("负偏差惩罚电价类型") 
	 private String lowerCheckPrcType;
	 
	 @PropertyDesc("负偏差惩罚电价")
	 private BigDecimal lowerCheckPrc;
	 
	 @PropertyDesc("负偏差惩罚电价比列")
	 private BigDecimal lowerCheckPrcProp;
	 
	 @PropertyDesc("长协负偏差考核标识")
	 private String lowerCheckLcFlag;
	 
	 @PropertyDesc("长协负偏差阈值") 
	 private BigDecimal lowerCheckLcPqProp;
	 
	 @PropertyDesc("长协负偏差惩罚电价类型") 
	 private String lowerCheckLcPrcType;
	 
	 @PropertyDesc("长协负偏差惩罚电价")
	 private BigDecimal lowerCheckLcPrc;
	 
	 @PropertyDesc("长协负偏差惩罚电价比列")
	 private BigDecimal lowerCheckLcPrcProp;
	 
	 @PropertyDesc("竞价负偏差考核标识")
	 private String lowerCheckBidFlag;
	 
	 @PropertyDesc("竞价负偏差阈值")
	 private BigDecimal lowerCheckBidPqProp;
	 
	 @PropertyDesc("负偏差竞价惩罚电价")
	 private String lowerCheckBidPrcType;
	 
	 @PropertyDesc("竞价惩罚协议价")
	 private BigDecimal lowerCheckBidPrc;
	 
	 @PropertyDesc("负偏差竞价惩罚电价比列")
	 private BigDecimal lowerCheckBidPrcProp;

	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public String getUpperCheckFlag() {
		return upperCheckFlag;
	}

	public void setUpperCheckFlag(String upperCheckFlag) {
		this.upperCheckFlag = upperCheckFlag;
	}

	public BigDecimal getUpperPqProp() {
		return upperPqProp;
	}

	public void setUpperPqProp(BigDecimal upperPqProp) {
		this.upperPqProp = upperPqProp;
	}

	public String getUpperPrcType() {
		return upperPrcType;
	}

	public void setUpperPrcType(String upperPrcType) {
		this.upperPrcType = upperPrcType;
	}

	public BigDecimal getUpperPrc() {
		return upperPrc;
	}

	public void setUpperPrc(BigDecimal upperPrc) {
		this.upperPrc = upperPrc;
	}

	public BigDecimal getUpperPrcProp() {
		return upperPrcProp;
	}

	public void setUpperPrcProp(BigDecimal upperPrcProp) {
		this.upperPrcProp = upperPrcProp;
	}

	public String getUpperCheckPrcType() {
		return upperCheckPrcType;
	}

	public void setUpperCheckPrcType(String upperCheckPrcType) {
		this.upperCheckPrcType = upperCheckPrcType;
	}

	public BigDecimal getUpperCheckPrc() {
		return upperCheckPrc;
	}

	public void setUpperCheckPrc(BigDecimal upperCheckPrc) {
		this.upperCheckPrc = upperCheckPrc;
	}

	public BigDecimal getUpperCheckPrcProp() {
		return upperCheckPrcProp;
	}

	public void setUpperCheckPrcProp(BigDecimal upperCheckPrcProp) {
		this.upperCheckPrcProp = upperCheckPrcProp;
	}

	public String getLowerCheckFlag() {
		return lowerCheckFlag;
	}

	public void setLowerCheckFlag(String lowerCheckFlag) {
		this.lowerCheckFlag = lowerCheckFlag;
	}

	public String getSplitCheckFlag() {
		return splitCheckFlag;
	}

	public void setSplitCheckFlag(String splitCheckFlag) {
		this.splitCheckFlag = splitCheckFlag;
	}

	public BigDecimal getLowerPqProp() {
		return lowerPqProp;
	}

	public void setLowerPqProp(BigDecimal lowerPqProp) {
		this.lowerPqProp = lowerPqProp;
	}

	public String getLowerCheckPrcType() {
		return lowerCheckPrcType;
	}

	public void setLowerCheckPrcType(String lowerCheckPrcType) {
		this.lowerCheckPrcType = lowerCheckPrcType;
	}

	public BigDecimal getLowerCheckPrc() {
		return lowerCheckPrc;
	}

	public void setLowerCheckPrc(BigDecimal lowerCheckPrc) {
		this.lowerCheckPrc = lowerCheckPrc;
	}

	public BigDecimal getLowerCheckPrcProp() {
		return lowerCheckPrcProp;
	}

	public void setLowerCheckPrcProp(BigDecimal lowerCheckPrcProp) {
		this.lowerCheckPrcProp = lowerCheckPrcProp;
	}

	public String getLowerCheckLcFlag() {
		return lowerCheckLcFlag;
	}

	public void setLowerCheckLcFlag(String lowerCheckLcFlag) {
		this.lowerCheckLcFlag = lowerCheckLcFlag;
	}

	public BigDecimal getLowerCheckLcPqProp() {
		return lowerCheckLcPqProp;
	}

	public void setLowerCheckLcPqProp(BigDecimal lowerCheckLcPqProp) {
		this.lowerCheckLcPqProp = lowerCheckLcPqProp;
	}

	public String getLowerCheckLcPrcType() {
		return lowerCheckLcPrcType;
	}

	public void setLowerCheckLcPrcType(String lowerCheckLcPrcType) {
		this.lowerCheckLcPrcType = lowerCheckLcPrcType;
	}

	public BigDecimal getLowerCheckLcPrc() {
		return lowerCheckLcPrc;
	}

	public void setLowerCheckLcPrc(BigDecimal lowerCheckLcPrc) {
		this.lowerCheckLcPrc = lowerCheckLcPrc;
	}

	public BigDecimal getLowerCheckLcPrcProp() {
		return lowerCheckLcPrcProp;
	}

	public void setLowerCheckLcPrcProp(BigDecimal lowerCheckLcPrcProp) {
		this.lowerCheckLcPrcProp = lowerCheckLcPrcProp;
	}

	public String getLowerCheckBidFlag() {
		return lowerCheckBidFlag;
	}

	public void setLowerCheckBidFlag(String lowerCheckBidFlag) {
		this.lowerCheckBidFlag = lowerCheckBidFlag;
	}

	public BigDecimal getLowerCheckBidPqProp() {
		return lowerCheckBidPqProp;
	}

	public void setLowerCheckBidPqProp(BigDecimal lowerCheckBidPqProp) {
		this.lowerCheckBidPqProp = lowerCheckBidPqProp;
	}

	public String getLowerCheckBidPrcType() {
		return lowerCheckBidPrcType;
	}

	public void setLowerCheckBidPrcType(String lowerCheckBidPrcType) {
		this.lowerCheckBidPrcType = lowerCheckBidPrcType;
	}

	public BigDecimal getLowerCheckBidPrc() {
		return lowerCheckBidPrc;
	}

	public void setLowerCheckBidPrc(BigDecimal lowerCheckBidPrc) {
		this.lowerCheckBidPrc = lowerCheckBidPrc;
	}

	public BigDecimal getLowerCheckBidPrcProp() {
		return lowerCheckBidPrcProp;
	}

	public void setLowerCheckBidPrcProp(BigDecimal lowerCheckBidPrcProp) {
		this.lowerCheckBidPrcProp = lowerCheckBidPrcProp;
	}
	
}
