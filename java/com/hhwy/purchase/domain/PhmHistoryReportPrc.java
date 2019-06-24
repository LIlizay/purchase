package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmHistoryReportPrc
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmHistoryReportPrc")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_history_report_prc")
public class PhmHistoryReportPrc extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("发电侧用户最高报价")
    @Column(name="prod_max_prc", nullable=true, length=8) 
    private java.math.BigDecimal prodMaxPrc;
    
    @PropertyDesc("发电侧用户最低报价")
    @Column(name="prod_min_prc", nullable=true, length=8) 
    private java.math.BigDecimal prodMinPrc;
    
    @PropertyDesc("发电侧最高成交价")
    @Column(name="prod_max_bid_prc", nullable=true, length=8) 
    private java.math.BigDecimal prodMaxBidPrc;
    
    @PropertyDesc("用电侧用户最高报价")
    @Column(name="use_max_prc", nullable=true, length=8) 
    private java.math.BigDecimal useMaxPrc;
    
    @PropertyDesc("用电侧用户最低报价")
    @Column(name="use_min_prc", nullable=true, length=8) 
    private java.math.BigDecimal useMinPrc;
    
    @PropertyDesc("用电侧最低成交价")
    @Column(name="use_min_bid_prc", nullable=true, length=8) 
    private java.math.BigDecimal useMinBidPrc;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getProdMaxPrc(){
        return prodMaxPrc;
    }
    
    public void setProdMaxPrc(java.math.BigDecimal prodMaxPrc){
        this.prodMaxPrc = prodMaxPrc;
    }
    
    public java.math.BigDecimal getProdMinPrc(){
        return prodMinPrc;
    }
    
    public void setProdMinPrc(java.math.BigDecimal prodMinPrc){
        this.prodMinPrc = prodMinPrc;
    }
    
    public java.math.BigDecimal getProdMaxBidPrc(){
        return prodMaxBidPrc;
    }
    
    public void setProdMaxBidPrc(java.math.BigDecimal prodMaxBidPrc){
        this.prodMaxBidPrc = prodMaxBidPrc;
    }
    
    public java.math.BigDecimal getUseMaxPrc(){
        return useMaxPrc;
    }
    
    public void setUseMaxPrc(java.math.BigDecimal useMaxPrc){
        this.useMaxPrc = useMaxPrc;
    }
    
    public java.math.BigDecimal getUseMinPrc(){
        return useMinPrc;
    }
    
    public void setUseMinPrc(java.math.BigDecimal useMinPrc){
        this.useMinPrc = useMinPrc;
    }
    
    public java.math.BigDecimal getUseMinBidPrc(){
        return useMinBidPrc;
    }
    
    public void setUseMinBidPrc(java.math.BigDecimal useMinBidPrc){
        this.useMinBidPrc = useMinBidPrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}