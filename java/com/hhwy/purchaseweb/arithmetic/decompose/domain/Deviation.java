package com.hhwy.purchaseweb.arithmetic.decompose.domain;

import java.math.BigDecimal;
import java.util.List;

public class Deviation {
	
	/**
	 * 用户-电厂成交信息
	 */
	private List<DecomposeData> list;
	/**
	 * 总交易电量
	 */
	private BigDecimal totalPq; 
	/**
	 * 计算电价类型
	 */
	private String prcType;
	/**
	 * 计算电价
	 */
	private BigDecimal prcValue;
	/**
	 * 参与计算总电量（未区分电厂）
	 */
	private int pq;
	/**
	 * 电量排序规则
	 */
	private String principleFlag;
	/**
	 * 电量类型
	 */
	private String pqTypeCode;
	/**
	 * 电量比例
	 */
	private BigDecimal pqProp;
	public List<DecomposeData> getList() {
		return list;
	}
	public void setList(List<DecomposeData> list) {
		this.list = list;
	}
	public BigDecimal getTotalPq() {
		return totalPq;
	}
	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
	}
	public String getPrcType() {
		return prcType;
	}
	public void setPrcType(String prcType) {
		this.prcType = prcType;
	}
	public BigDecimal getPrcValue() {
		return prcValue;
	}
	public void setPrcValue(BigDecimal prcValue) {
		this.prcValue = prcValue;
	}
	public int getPq() {
		return pq;
	}
	public void setPq(int pq) {
		this.pq = pq;
	}
	public String getPrincipleFlag() {
		return principleFlag;
	}
	public void setPrincipleFlag(String principleFlag) {
		this.principleFlag = principleFlag;
	}
	public String getPqTypeCode() {
		return pqTypeCode;
	}
	public void setPqTypeCode(String pqTypeCode) {
		this.pqTypeCode = pqTypeCode;
	}
	public BigDecimal getPqProp() {
		return pqProp;
	}
	public void setPqProp(BigDecimal pqProp) {
		this.pqProp = pqProp;
	}

}
