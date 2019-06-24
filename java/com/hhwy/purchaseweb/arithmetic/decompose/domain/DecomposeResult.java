package com.hhwy.purchaseweb.arithmetic.decompose.domain;

/**
 * 
 * <b>类 名 称：</b>CostDecompose<br/>
 * <b>类 描 述：</b>购电成本分割结果实体<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月6日 下午3:35:54<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DecomposeResult {
	
	/**
	 * 市场化用户编号
	 */
	private String marketUserNo;
	/**
	 * 01参与市场化直接交易；02市场化代理零售
	 */
	private String marketSortCode;
	/**
	 * 合同标识
	 */
	private String contractId;
	/**
	 * 电厂标识
	 */
	private String elePowerId;
	/**
	 * 01普通、02长协
	 */
	private String dealType;
	/**
	 * 电量类型包括：01交易电量、02正偏差电量、03正偏差考核电量、
	 * 				04负偏差电量、05负偏差考核电量
	 */	
	private String pqTypeCode;
	/**
	 * 电价时段代码，包括：03 平
	 */
	private String prcTsCode;
	/**
	 * 分割后的电量
	 */
	private int divPq;
	/**
	 * 电价类型：对电价value字段进行设置，01数字型为电价value具体值，02字符型value为目录电价1倍
	 */
	private String typeCode;
	/**
	 * type_code为01时，value为具体电价值；type_code为02时，value为电价倍数，目前值为1，即1倍目录电价
	 */
	private Double value;
	/**
	 * 市场化零售用户合同电价
	 */
	private Double contractPrc;
	public String getMarketUserNo() {
		return marketUserNo;
	}
	public void setMarketUserNo(String marketUserNo) {
		this.marketUserNo = marketUserNo;
	}
	public String getMarketSortCode() {
		return marketSortCode;
	}
	public void setMarketSortCode(String marketSortCode) {
		this.marketSortCode = marketSortCode;
	}
	public String getContractId() {
		return contractId;
	}
	public void setContractId(String contractId) {
		this.contractId = contractId;
	}
	public String getElePowerId() {
		return elePowerId;
	}
	public void setElePowerId(String elePowerId) {
		this.elePowerId = elePowerId;
	}
	public String getDealType() {
		return dealType;
	}
	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
	public String getPqTypeCode() {
		return pqTypeCode;
	}
	public void setPqTypeCode(String pqTypeCode) {
		this.pqTypeCode = pqTypeCode;
	}
	public String getPrcTsCode() {
		return prcTsCode;
	}
	public void setPrcTsCode(String prcTsCode) {
		this.prcTsCode = prcTsCode;
	}
	public int getDivPq() {
		return divPq;
	}
	public void setDivPq(int divPq) {
		this.divPq = divPq;
	}
	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public Double getContractPrc() {
		return contractPrc;
	}
	public void setContractPrc(Double contractPrc) {
		this.contractPrc = contractPrc;
	}

}
