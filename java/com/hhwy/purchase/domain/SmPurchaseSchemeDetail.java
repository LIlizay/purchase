package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmPurchaseSchemeDetail
 * @author hhwy			月度结算方案详细表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmPurchaseSchemeDetail")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_purchase_scheme_detail")
public class SmPurchaseSchemeDetail extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户Id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("方案Id")
    @Column(name="scheme_id", nullable=true, length=32) 
    private String schemeId;
    
    @PropertyDesc("双边分成方式")
    @Column(name="divi_code", nullable=true, length=8) 
    private String diviCode;
    
    @PropertyDesc("双边保底协议价")
    @Column(name="agre_prc", nullable=true, length=8) 
    private java.math.BigDecimal agrePrc;
    
    @PropertyDesc("双边分成基准值")
    @Column(name="divi_value", nullable=true, length=8) 
    private java.math.BigDecimal diviValue;
    
    @PropertyDesc("用户双边分成比例")
    @Column(name="party_a_lc_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyALcProp;
    
    @PropertyDesc("竞价分成方式")
    @Column(name="bid_divi_code", nullable=true, length=8) 
    private String bidDiviCode;
    
    @PropertyDesc("竞价保底协议价")
    @Column(name="bid_agre_prc", nullable=true, length=8) 
    private java.math.BigDecimal bidAgrePrc;
    
    @PropertyDesc("竞价分成基准值")
    @Column(name="bid_divi_value", nullable=true, length=8) 
    private java.math.BigDecimal bidDiviValue;
    
    @PropertyDesc("用户竞价分成比例")
    @Column(name="party_a_bid_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyABidProp;
    
    @PropertyDesc("分配长协电量")
    @Column(name="lc_pq", nullable=true, length=18) 
    private java.math.BigDecimal lcPq;
    
    @PropertyDesc("分配竞价电量")
    @Column(name="bid_pq", nullable=true, length=18) 
    private java.math.BigDecimal bidPq;
    
    @PropertyDesc("分配挂牌电量")
    @Column(name="listed_pq", nullable=true, length=18) 
    private java.math.BigDecimal listedPq;
    
    @PropertyDesc("月委托电量")
    @Column(name="proxy_pq", nullable=true, length=16) 
    private java.math.BigDecimal proxyPq;
    
    @PropertyDesc("结算电量")
    @Column(name="delivery_pq", nullable=true, length=18) 
    private java.math.BigDecimal deliveryPq;
    
    @PropertyDesc("结算电价(元/兆瓦时，江苏用)")
    @Column(name="delivery_prc", nullable=true, length=18) 
    private java.math.BigDecimal deliveryPrc;
    
    @PropertyDesc("服务费用(元，江苏用)")
    @Column(name="service_amt", nullable=true, length=18) 
    private java.math.BigDecimal serviceAmt;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public String getDiviCode(){
        return diviCode;
    }
    
    public void setDiviCode(String diviCode){
        this.diviCode = diviCode;
    }
    
    public java.math.BigDecimal getAgrePrc(){
        return agrePrc;
    }
    
    public void setAgrePrc(java.math.BigDecimal agrePrc){
        this.agrePrc = agrePrc;
    }
    
    public java.math.BigDecimal getDiviValue(){
        return diviValue;
    }
    
    public void setDiviValue(java.math.BigDecimal diviValue){
        this.diviValue = diviValue;
    }
    
    public java.math.BigDecimal getPartyALcProp(){
        return partyALcProp;
    }
    
    public void setPartyALcProp(java.math.BigDecimal partyALcProp){
        this.partyALcProp = partyALcProp;
    }
    
    public String getBidDiviCode(){
        return bidDiviCode;
    }
    
    public void setBidDiviCode(String bidDiviCode){
        this.bidDiviCode = bidDiviCode;
    }
    
    public java.math.BigDecimal getBidAgrePrc(){
        return bidAgrePrc;
    }
    
    public void setBidAgrePrc(java.math.BigDecimal bidAgrePrc){
        this.bidAgrePrc = bidAgrePrc;
    }
    
    public java.math.BigDecimal getBidDiviValue(){
        return bidDiviValue;
    }
    
    public void setBidDiviValue(java.math.BigDecimal bidDiviValue){
        this.bidDiviValue = bidDiviValue;
    }
    
    public java.math.BigDecimal getPartyABidProp(){
        return partyABidProp;
    }
    
    public void setPartyABidProp(java.math.BigDecimal partyABidProp){
        this.partyABidProp = partyABidProp;
    }
    
    public java.math.BigDecimal getLcPq(){
        return lcPq;
    }
    
    public void setLcPq(java.math.BigDecimal lcPq){
        this.lcPq = lcPq;
    }
    
    public java.math.BigDecimal getBidPq(){
        return bidPq;
    }
    
    public void setBidPq(java.math.BigDecimal bidPq){
        this.bidPq = bidPq;
    }
    
    public java.math.BigDecimal getListedPq(){
        return listedPq;
    }
    
    public void setListedPq(java.math.BigDecimal listedPq){
        this.listedPq = listedPq;
    }
    
    public java.math.BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(java.math.BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public java.math.BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(java.math.BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public java.math.BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(java.math.BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public java.math.BigDecimal getServiceAmt(){
        return serviceAmt;
    }
    
    public void setServiceAmt(java.math.BigDecimal serviceAmt){
        this.serviceAmt = serviceAmt;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}