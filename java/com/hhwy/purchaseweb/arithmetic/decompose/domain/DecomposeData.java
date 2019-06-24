package com.hhwy.purchaseweb.arithmetic.decompose.domain;

/**
 * 
 * <b>类 名 称：</b>DecomposeData<br/>
 * <b>类 描 述：</b>分割电量参数实体<br/>
 * <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2017年6月11日 下午4:07:50<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class DecomposeData {
	
	/**
	 * 用户id
	 */
	private String marketUserNo;
	/**
	 * 售电公司id
	 */
	private String companyId;
	/**
	 * 合同标识
	 */
	private String contractId;
	/**
	 * 电厂id
	 */
	private String elePowerId;
	/**
	 * 交易类型（长协或普通）
	 */
	private String dealType;
	/**
	 * 交易电量
	 */
	private int pq;
	/**
	 * 交易电价
	 */
	private Double prc;
	/**
	 * 市场化零售用户合同电价
	 */
	private Double contractPrc;
	/**
	 * 交割电量（用户对应交割，可重复）
	 */
	private int deliveryPq;
	public String getMarketUserNo() {
		return marketUserNo;
	}
	public void setMarketUserNo(String marketUserNo) {
		this.marketUserNo = marketUserNo;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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
	public int getPq() {
		return pq;
	}
	public void setPq(int pq) {
		this.pq = pq;
	}
	public Double getPrc() {
		return prc;
	}
	public void setPrc(Double prc) {
		this.prc = prc;
	}
	public Double getContractPrc() {
		return contractPrc;
	}
	public void setContractPrc(Double contractPrc) {
		this.contractPrc = contractPrc;
	}
	public int getDeliveryPq() {
		return deliveryPq;
	}
	public void setDeliveryPq(int deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

}
