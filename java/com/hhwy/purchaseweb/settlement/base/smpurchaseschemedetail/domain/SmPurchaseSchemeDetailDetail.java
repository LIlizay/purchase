package com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain;
import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SmPurchaseSchemeDetailDetail{
	
	//年月 yyyy-MM格式
	private String ym;
	
	
	
    private String id;
    
	//用户Id
    private String consId;
    
    //用户名称
    private String consName;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; //sell_psVoltCode
    
    //方案Id
    private String schemeId;
    

    //双边分成方式		p_code : sell_divideWayCode
    private String diviCode;
    
    //双边保底协议价
    private BigDecimal agrePrc;
    
	    //双边分成基准方式（临时字段）    初始值为售电合同的同名字段，用作计算“初始化结算页面中的用户月度电量分配表”中的“双边分成基准值”时中用到，为临时字段，不做保存和展示
	    private String diviType;
    
    //双边分成基准值
    private BigDecimal diviValue;
    
    //用户双边分成比例
    private BigDecimal partyALcProp;
    
    //竞价分成方式
    private String bidDiviCode;
    
    //竞价保底协议价
    private BigDecimal bidAgrePrc;
    
	    //双边分成基准方式（临时字段）    初始值为售电合同的同名字段，用作计算“初始化结算页面中的用户月度电量分配表”中的“双边分成基准值”时中用到，为临时字段，不做保存和展示
	    private String bidDiviType;
    
    //竞价分成基准值
    private BigDecimal bidDiviValue;
    
    //用户竞价分成比例
    private BigDecimal partyABidProp;
    
    //分配长协电量
    private BigDecimal lcPq;
    
    //分配竞价电量
    private BigDecimal bidPq;
    
    //分配挂牌电量
    private BigDecimal listedPq;
    
    //月委托电量
    private BigDecimal proxyPq;
    
    //结算电量
    private BigDecimal deliveryPq;
    
    //结算电价(元/兆瓦时，江苏用)(合同加权平均价)
    private BigDecimal deliveryPrc;
    
    //服务费用(元，江苏用)
    private BigDecimal serviceAmt;
    
    //部门id
    private String orgNo;
    
    
    
    
    
    
    //实际用电量
    private BigDecimal actualPq;
    
    //分配总电量
    private BigDecimal totalPq;
    
    //偏差电量比例
//    private String devPqProp;
    
    
    //以下4个字段是 售电合同的分成方式中字段，s_m_dist_mode表，用于计算 服务费用

    //双边收益模式	p_code:	sell_settlementModeCode
    //(01:价差; 02:电价; 03:按电量收取; 04:一次性收取),如果是03，则agent是代理服务价，如果是04，则agent是代理服务费
    private String profitMode;		
    //双边代理服务费（元），或代理服务价（元/兆瓦时）
    private BigDecimal agent;
    //竞价收益模式
    private String bidProfitMode;
    //竞价代理服务费（元），或代理服务价（元/兆瓦时）
    private BigDecimal  bidAgent;
    
    
    
    
    
    
	public String getDiviType() {
		return diviType;
	}

	public void setDiviType(String diviType) {
		this.diviType = diviType;
	}

	public String getBidDiviType() {
		return bidDiviType;
	}

	public void setBidDiviType(String bidDiviType) {
		this.bidDiviType = bidDiviType;
	}

	public String getProfitMode() {
		return profitMode;
	}

	public void setProfitMode(String profitMode) {
		this.profitMode = profitMode;
	}

	public BigDecimal getAgent() {
		return agent;
	}

	public void setAgent(BigDecimal agent) {
		this.agent = agent;
	}

	public String getBidProfitMode() {
		return bidProfitMode;
	}

	public void setBidProfitMode(String bidProfitMode) {
		this.bidProfitMode = bidProfitMode;
	}

	public BigDecimal getBidAgent() {
		return bidAgent;
	}

	public void setBidAgent(BigDecimal bidAgent) {
		this.bidAgent = bidAgent;
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

	public BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getDiviCode() {
		return diviCode;
	}

	public void setDiviCode(String diviCode) {
		this.diviCode = diviCode;
	}

	public BigDecimal getAgrePrc() {
		return agrePrc;
	}

	public void setAgrePrc(BigDecimal agrePrc) {
		this.agrePrc = agrePrc;
	}

	public BigDecimal getPartyALcProp() {
		return partyALcProp;
	}

	public void setPartyALcProp(BigDecimal partyALcProp) {
		this.partyALcProp = partyALcProp;
	}

	public BigDecimal getPartyABidProp() {
		return partyABidProp;
	}

	public void setPartyABidProp(BigDecimal partyABidProp) {
		this.partyABidProp = partyABidProp;
	}

	public BigDecimal getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(BigDecimal proxyPq) {
		this.proxyPq = proxyPq;
	}

	public BigDecimal getLcPq() {
		return lcPq;
	}

	public void setLcPq(BigDecimal lcPq) {
		this.lcPq = lcPq;
	}

	public BigDecimal getBidPq() {
		return bidPq;
	}

	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}

	public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getTotalPq() {
		return totalPq;
	}

	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getDiviValue() {
		return diviValue;
	}

	public void setDiviValue(BigDecimal diviValue) {
		this.diviValue = diviValue;
	}

	public String getBidDiviCode() {
		return bidDiviCode;
	}

	public void setBidDiviCode(String bidDiviCode) {
		this.bidDiviCode = bidDiviCode;
	}

	public BigDecimal getBidAgrePrc() {
		return bidAgrePrc;
	}

	public void setBidAgrePrc(BigDecimal bidAgrePrc) {
		this.bidAgrePrc = bidAgrePrc;
	}

	public BigDecimal getBidDiviValue() {
		return bidDiviValue;
	}

	public void setBidDiviValue(BigDecimal bidDiviValue) {
		this.bidDiviValue = bidDiviValue;
	}

	public BigDecimal getListedPq() {
		return listedPq;
	}

	public void setListedPq(BigDecimal listedPq) {
		this.listedPq = listedPq;
	}

	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public BigDecimal getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}
}