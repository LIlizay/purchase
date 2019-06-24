package com.hhwy.purchaseweb.settlement.other.domain;
import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * <b>类 名 称：</b>RetailDetailOther<br/>
 * <b>类 描 述：</b><br/>		江苏、福建以外其他省的零售市场售电收入明细
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月17日 下午5:47:16<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class RetailDetailOther{
	
	//用户ID
	private String consId;
	//SmConsumerProfit的ID
	private String consProId;
	//SmPurchaseSchemeDetail的ID
	private String schemeDetailId;
	
	 //用户名称
    private String consName;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; //sell_psVoltCode
    
    //月委托电量
    private BigDecimal proxyPq;
    
    //结算电量
    private BigDecimal deliveryPq;

    //交易电价  ： 结算电价(元/兆瓦时)(合同加权平均价)
    private BigDecimal deliveryPrc;
    
    //交易电费 	： 结算电费	s_m_consumer_profit表的字段
    private BigDecimal deliveryCost;
    
    //服务费用(元)
    private BigDecimal serviceAmt;
    
    //用户偏差考核费用（元）: s_m_consumer_profit表的字段
    private BigDecimal consCheckedPro;

    
    
    //分配总电量
    private BigDecimal totalPq;
    
    //分配长协电量
    private BigDecimal lcPq;
    
    //分配竞价电量
    private BigDecimal bidPq;
    
    //分配挂牌电量
    private BigDecimal listedPq;

    
    
    
    
    
    
	public String getConsProId() {
		return consProId;
	}

	public void setConsProId(String consProId) {
		this.consProId = consProId;
	}

	public String getSchemeDetailId() {
		return schemeDetailId;
	}

	public void setSchemeDetailId(String schemeDetailId) {
		this.schemeDetailId = schemeDetailId;
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

	public BigDecimal getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(BigDecimal proxyPq) {
		this.proxyPq = proxyPq;
	}

	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public BigDecimal getDeliveryCost() {
		return deliveryCost;
	}

	public void setDeliveryCost(BigDecimal deliveryCost) {
		this.deliveryCost = deliveryCost;
	}

	public BigDecimal getServiceAmt() {
		return serviceAmt;
	}

	public void setServiceAmt(BigDecimal serviceAmt) {
		this.serviceAmt = serviceAmt;
	}

	public BigDecimal getConsCheckedPro() {
		return consCheckedPro;
	}

	public void setConsCheckedPro(BigDecimal consCheckedPro) {
		this.consCheckedPro = consCheckedPro;
	}

	public BigDecimal getTotalPq() {
		return totalPq;
	}

	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
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

	public BigDecimal getListedPq() {
		return listedPq;
	}

	public void setListedPq(BigDecimal listedPq) {
		this.listedPq = listedPq;
	}
}