package com.hhwy.purchase.domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

/**
 * SmConsumerProfit
 * @author hhwy			月度结算用户收益表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmConsumerProfit")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_consumer_profit")
public class SmConsumerProfit extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("结算方案id")
    @Column(name="scheme_id", nullable=true, length=32) 
    private String schemeId;
    
    @PropertyDesc("用户标识")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("长协分配电量")
    @Column(name="lc_dist_pq", nullable=true, length=16) 
    private BigDecimal lcDistPq;
    
    @PropertyDesc("竞价分配电量")
    @Column(name="bid_dist_pq", nullable=true, length=16) 
    private BigDecimal bidDistPq;
    
    @PropertyDesc("分配挂牌电量")
    @Column(name="listed_pq", nullable=true, length=18) 
    private BigDecimal listedPq;
    
    @PropertyDesc("月委托电量(售电公司当月代理用户电量)")
    @Column(name="proxy_pq", nullable=true, length=16) 
    private BigDecimal proxyPq;
    
    @PropertyDesc("实际用电量")
    @Column(name="actual_pq", nullable=true, length=16) 
    private BigDecimal actualPq;
    
    @PropertyDesc("分配总电量")
    @Column(name="dist_total_pq", nullable=true, length=16) 
    private BigDecimal distTotalPq;
    
    @PropertyDesc("交割电量")
    @Column(name="cons_del_pq", nullable=true, length=16) 
    private BigDecimal consDelPq;
    
    @PropertyDesc("交易总受益")
    @Column(name="trans_total_pro", nullable=true, length=16) 
    private BigDecimal transTotalPro;
    
    @PropertyDesc("售电公司未考核收益")
    @Column(name="comp_unchecked_pro", nullable=true, length=16) 
    private BigDecimal compUncheckedPro;
    
    @PropertyDesc("用户未考核收益（差额电费）")
    @Column(name="cons_unchecked_pro", nullable=true, length=16) 
    private BigDecimal consUncheckedPro;
    
    @PropertyDesc("用电偏差")
    @Column(name="cons_elec_dev", nullable=true, length=16) 
    private BigDecimal consElecDev;
    
    @PropertyDesc("用户承担考核  (用户偏差考核费用（元）)")
    @Column(name="cons_checked_pro", nullable=true, length=16) 
    private BigDecimal consCheckedPro;
    
    @PropertyDesc("用户总收益(总节省电费，净收益)")
    @Column(name="cons_total_pro", nullable=true, length=16) 
    private BigDecimal consTotalPro;
    
    @PropertyDesc("售电公司承担考核（售电公司赔偿金额）")
    @Column(name="comp_checked_pro", nullable=true, length=16) 
    private BigDecimal compCheckedPro;
    
    @PropertyDesc("分成模式")
    @Column(name="divi_code", nullable=true, length=8) 
    private String diviCode;
    
    @PropertyDesc("售电公司赔偿金额（即售电公司赔偿用户费用）")
    @Column(name="cons_compensate", nullable=true, length=16) 
    private BigDecimal consCompensate;
    
    @PropertyDesc("结算电价(江苏结算专用字段)")
    @Column(name="delivery_prc", nullable=true, length=16) 
    private BigDecimal deliveryPrc;
    
    @PropertyDesc("市场化交易结算电量（兆瓦时,江苏结算专用字段）")
    @Column(name="marketize_pq", nullable=true, length=16) 
    private BigDecimal marketizePq;
    
    @PropertyDesc("按非市场电价结算电量(兆瓦时,江苏结算专用字段）")
    @Column(name="nmarketize_pq", nullable=true, length=16) 
    private BigDecimal nmarketizePq;
    
    @PropertyDesc("结算电费（元,江苏结算专用字段）")
    @Column(name="delivery_cost", nullable=true, length=16) 
    private BigDecimal deliveryCost;
    
    @PropertyDesc("代理比例 ： 福建专用字段")
    @Column(name="agent_prop", nullable=true, length=16) 
    private BigDecimal agentProp;
    
    @PropertyDesc("代理费用： 福建专用字段")
    @Column(name="agent_amt", nullable=true, length=16) 
    private BigDecimal agentAmt;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    

    public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public BigDecimal getLcDistPq(){
        return lcDistPq;
    }
    
    public void setLcDistPq(BigDecimal lcDistPq){
        this.lcDistPq = lcDistPq;
    }
    
    public BigDecimal getBidDistPq(){
        return bidDistPq;
    }
    
    public void setBidDistPq(BigDecimal bidDistPq){
        this.bidDistPq = bidDistPq;
    }
    
    public BigDecimal getListedPq(){
        return listedPq;
    }
    
    public void setListedPq(BigDecimal listedPq){
        this.listedPq = listedPq;
    }
    
    public BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public BigDecimal getDistTotalPq(){
        return distTotalPq;
    }
    
    public void setDistTotalPq(BigDecimal distTotalPq){
        this.distTotalPq = distTotalPq;
    }
    
    public BigDecimal getConsDelPq(){
        return consDelPq;
    }
    
    public void setConsDelPq(BigDecimal consDelPq){
        this.consDelPq = consDelPq;
    }
    
    public BigDecimal getTransTotalPro(){
        return transTotalPro;
    }
    
    public void setTransTotalPro(BigDecimal transTotalPro){
        this.transTotalPro = transTotalPro;
    }
    
    public BigDecimal getCompUncheckedPro(){
        return compUncheckedPro;
    }
    
    public void setCompUncheckedPro(BigDecimal compUncheckedPro){
        this.compUncheckedPro = compUncheckedPro;
    }
    
    public BigDecimal getConsUncheckedPro(){
        return consUncheckedPro;
    }
    
    public void setConsUncheckedPro(BigDecimal consUncheckedPro){
        this.consUncheckedPro = consUncheckedPro;
    }
    
    public BigDecimal getConsElecDev(){
        return consElecDev;
    }
    
    public void setConsElecDev(BigDecimal consElecDev){
        this.consElecDev = consElecDev;
    }
    
    public BigDecimal getConsCheckedPro(){
        return consCheckedPro;
    }
    
    public void setConsCheckedPro(BigDecimal consCheckedPro){
        this.consCheckedPro = consCheckedPro;
    }
    
    public BigDecimal getConsTotalPro(){
        return consTotalPro;
    }
    
    public void setConsTotalPro(BigDecimal consTotalPro){
        this.consTotalPro = consTotalPro;
    }
    
    public BigDecimal getCompCheckedPro(){
        return compCheckedPro;
    }
    
    public void setCompCheckedPro(BigDecimal compCheckedPro){
        this.compCheckedPro = compCheckedPro;
    }
    
    public String getDiviCode(){
        return diviCode;
    }
    
    public void setDiviCode(String diviCode){
        this.diviCode = diviCode;
    }
    
    public BigDecimal getConsCompensate(){
        return consCompensate;
    }
    
    public void setConsCompensate(BigDecimal consCompensate){
        this.consCompensate = consCompensate;
    }
    
    public BigDecimal getDeliveryPrc(){
        return deliveryPrc;
    }
    
    public void setDeliveryPrc(BigDecimal deliveryPrc){
        this.deliveryPrc = deliveryPrc;
    }
    
    public BigDecimal getMarketizePq(){
        return marketizePq;
    }
    
    public void setMarketizePq(BigDecimal marketizePq){
        this.marketizePq = marketizePq;
    }
    
    public BigDecimal getNmarketizePq(){
        return nmarketizePq;
    }
    
    public void setNmarketizePq(BigDecimal nmarketizePq){
        this.nmarketizePq = nmarketizePq;
    }
    
    public BigDecimal getDeliveryCost(){
        return deliveryCost;
    }
    
    public void setDeliveryCost(BigDecimal deliveryCost){
        this.deliveryCost = deliveryCost;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public BigDecimal getAgentProp() {
		return agentProp;
	}

	public void setAgentProp(BigDecimal agentProp) {
		this.agentProp = agentProp;
	}

	public BigDecimal getAgentAmt() {
		return agentAmt;
	}

	public void setAgentAmt(BigDecimal agentAmt) {
		this.agentAmt = agentAmt;
	}

	public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
	}
}