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
 * SmPurchaseScheme
 * @author hhwy			月度结算方案表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmPurchaseScheme")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_purchase_scheme")
public class SmPurchaseScheme extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("结算id")
    @Column(name="settle_id", nullable=true, length=32) 
    private String settleId;
    
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("方案状态（0：草稿，1：归档）")
    @Column(name="scheme_status", nullable=true, length=3) 
    private int schemeStatus;
    
    @PropertyDesc("方案名称")
    @Column(name="scheme_name", nullable=true, length=256) 
    private String schemeName;
    
    @PropertyDesc("公司收益")
    @Column(name="comp_profit", nullable=true, length=16) 
    private BigDecimal compProfit;
    
    @PropertyDesc("用户总收益")
    @Column(name="cons_profit", nullable=true, length=16) 
    private BigDecimal consProfit;
    
    @PropertyDesc("赔偿用户费用(元)")
    @Column(name="cons_compensate", nullable=true, length=16) 
    private BigDecimal consCompensate;
    
    @PropertyDesc("结算电量")
    @Column(name="delivery_pq", nullable=true, length=16) 
    private BigDecimal deliveryPq;
    
    @PropertyDesc("用户数")
    @Column(name="cons_num", nullable=true, length=10) 
    private Integer consNum;
    
    @PropertyDesc("总委托电量")
    @Column(name="total_proxy_pq", nullable=true, length=16) 
    private BigDecimal totalProxyPq;
    
    @PropertyDesc("总购电量（双边、竞价、挂牌电量和，兆瓦时）")
    @Column(name="total_purchase_pq", nullable=true, length=16) 
    private BigDecimal totalPurchasePq;
    
    @PropertyDesc("双边总电量")
    @Column(name="total_lc_pq", nullable=true, length=16) 
    private BigDecimal totalLcPq;
    
    @PropertyDesc("竞价总电量")
    @Column(name="total_bid_pq", nullable=true, length=16) 
    private BigDecimal totalBidPq;
    
    @PropertyDesc("挂牌总电量")
    @Column(name="total_listed_pq", nullable=true, length=16) 
    private BigDecimal totalListedPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getSettleId(){
        return settleId;
    }
    
    public void setSettleId(String settleId){
        this.settleId = settleId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public int getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(int schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public String getSchemeName(){
        return schemeName;
    }
    
    public void setSchemeName(String schemeName){
        this.schemeName = schemeName;
    }
    
    public BigDecimal getCompProfit(){
        return compProfit;
    }
    
    public void setCompProfit(BigDecimal compProfit){
        this.compProfit = compProfit;
    }
    
    public BigDecimal getConsProfit(){
        return consProfit;
    }
    
    public void setConsProfit(BigDecimal consProfit){
        this.consProfit = consProfit;
    }
    
    public BigDecimal getConsCompensate(){
        return consCompensate;
    }
    
    public void setConsCompensate(BigDecimal consCompensate){
        this.consCompensate = consCompensate;
    }
    
    public BigDecimal getDeliveryPq(){
        return deliveryPq;
    }
    
    public void setDeliveryPq(BigDecimal deliveryPq){
        this.deliveryPq = deliveryPq;
    }
    
    public Integer getConsNum(){
        return consNum;
    }
    
    public void setConsNum(Integer consNum){
        this.consNum = consNum;
    }
    
    public BigDecimal getTotalProxyPq(){
        return totalProxyPq;
    }
    
    public void setTotalProxyPq(BigDecimal totalProxyPq){
        this.totalProxyPq = totalProxyPq;
    }
    
    public BigDecimal getTotalPurchasePq(){
        return totalPurchasePq;
    }
    
    public void setTotalPurchasePq(BigDecimal totalPurchasePq){
        this.totalPurchasePq = totalPurchasePq;
    }
    
    public BigDecimal getTotalLcPq(){
        return totalLcPq;
    }
    
    public void setTotalLcPq(BigDecimal totalLcPq){
        this.totalLcPq = totalLcPq;
    }
    
    public BigDecimal getTotalBidPq(){
        return totalBidPq;
    }
    
    public void setTotalBidPq(BigDecimal totalBidPq){
        this.totalBidPq = totalBidPq;
    }
    
    public BigDecimal getTotalListedPq(){
        return totalListedPq;
    }
    
    public void setTotalListedPq(BigDecimal totalListedPq){
        this.totalListedPq = totalListedPq;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}