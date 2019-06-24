package com.hhwy.purchaseweb.arithmetic.decompose.domain;

import java.math.BigDecimal;

public class Rule {

	/**
	 * 交割分割优先原则
	 */
	private String priorityFlag;
	/**
	 * 竞价电量优先交割原则
	 */
	private String bidPriorityFlag;
	/**
	 * 长协电量优先交割原则
	 */
	private String lcPriorityFlag;
	/**
	 * 正偏差考核标识
	 */
	private String upperCheckFlag;
	/**
	 * 正偏差阈值
	 */
	private BigDecimal upperPqProp;
	/**
	 * 正偏差结算价类型
	 */
	private String upperPrcType;
	/**
	 * 正偏差结算价
	 */
	private BigDecimal upperPrc;
	/**
	 * 正偏差结算价比例
	 */
	private BigDecimal upperPrcProp;
	/**
	 * 正偏差考核电价类型
	 */
	private String upperCheckPrcType;
	/**
	 * 正偏差考核电价
	 */
	private BigDecimal upperCheckPrc;
	/**
	 * 正偏差考核电价比例
	 */
	private BigDecimal upperCheckPrcProp;
	/**
	 * 负偏差考核标识
	 */
	private String lowerCheckFlag;
	/**
	 * 负偏差普长协交易考核标识
	 */
	private String lowerCheckLcFlag;
	/**
	 * 长协交易负偏差阈值
	 */
	private BigDecimal lowerCheckLcPqProp;
	/**
	 * 长协交易负偏差结算价类型
	 */
	private String lowerLcPrcType;
	/**
	 * 长协交易负偏差结算价
	 */
	private BigDecimal lowerLcPrc;
	/**
	 * 长协交易负偏差结算价比例
	 */
	private BigDecimal lowerLcPrcProp;
	/**
	 * 长协交易负偏差考核电价类型
	 */
	private String lowerCheckLcPrcType;
	/**
	 * 长协交易负偏差考核电价
	 */
	private BigDecimal lowerCheckLcPrc;
	/**
	 * 长协交易负偏差考核电价比例
	 */
	private BigDecimal lowerCheckLcPrcProp;
	/**
	 * 负偏差普通交易考核标识
	 */
	private String lowerCheckBidFlag;
	/**
	 * 普通交易负偏差结算价类型
	 */
	private String lowerBidPrcType;
	/**
	 * 普通交易负偏差结算价
	 */
	private BigDecimal lowerBidPrc;
	/**
	 * 普通交易负偏差结算价比例
	 */
	private BigDecimal lowerBidPrcProp;
	/**
	 * 普通交易负偏差阈值
	 */
	private BigDecimal lowerCheckBidPqProp;
	/**
	 * 普通交易负偏差考核电价类型
	 */
	private String lowerCheckBidPrcType;
	/**
	 * 普通交易负偏差考核电价
	 */
	private BigDecimal lowerCheckBidPrc;
	/**
	 * 普通交易负偏差考核电价比例
	 */
	private BigDecimal lowerCheckBidPrcProp;
	public String getPriorityFlag() {
		return priorityFlag;
	}
	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}
	public String getBidPriorityFlag() {
		return bidPriorityFlag;
	}
	public void setBidPriorityFlag(String bidPriorityFlag) {
		this.bidPriorityFlag = bidPriorityFlag;
	}
	public String getLcPriorityFlag() {
		return lcPriorityFlag;
	}
	public void setLcPriorityFlag(String lcPriorityFlag) {
		this.lcPriorityFlag = lcPriorityFlag;
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
	public String getLowerLcPrcType() {
		return lowerLcPrcType;
	}
	public void setLowerLcPrcType(String lowerLcPrcType) {
		this.lowerLcPrcType = lowerLcPrcType;
	}
	public BigDecimal getLowerLcPrc() {
		return lowerLcPrc;
	}
	public void setLowerLcPrc(BigDecimal lowerLcPrc) {
		this.lowerLcPrc = lowerLcPrc;
	}
	public BigDecimal getLowerLcPrcProp() {
		return lowerLcPrcProp;
	}
	public void setLowerLcPrcProp(BigDecimal lowerLcPrcProp) {
		this.lowerLcPrcProp = lowerLcPrcProp;
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
	public String getLowerBidPrcType() {
		return lowerBidPrcType;
	}
	public void setLowerBidPrcType(String lowerBidPrcType) {
		this.lowerBidPrcType = lowerBidPrcType;
	}
	public BigDecimal getLowerBidPrc() {
		return lowerBidPrc;
	}
	public void setLowerBidPrc(BigDecimal lowerBidPrc) {
		this.lowerBidPrc = lowerBidPrc;
	}
	public BigDecimal getLowerBidPrcProp() {
		return lowerBidPrcProp;
	}
	public void setLowerBidPrcProp(BigDecimal lowerBidPrcProp) {
		this.lowerBidPrcProp = lowerBidPrcProp;
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
