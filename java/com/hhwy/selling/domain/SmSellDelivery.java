package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmSellDelivery
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmSellDelivery")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_sell_delivery")
public class SmSellDelivery extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("用电分类")
    @Column(name="elec_type_code", nullable=true, length=8) 
    private String elecTypeCode;
    
    @PropertyDesc("电压等级")
    @Column(name="volt_code", nullable=true, length=8) 
    private String voltCode;
    
    @PropertyDesc("竞价年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("月度合同电量")
    @Column(name="agre_pq", nullable=true, length=18) 
    private java.math.BigDecimal agrePq;
    
    @PropertyDesc("成交电量")
    @Column(name="bid_pq", nullable=true, length=18) 
    private java.math.BigDecimal bidPq;
    
    @PropertyDesc("实际用电量")
    @Column(name="delivery_pq", nullable=true, length=18) 
    private java.math.BigDecimal deliveryPq;
    
    @PropertyDesc("结算电量")
    @Column(name="proxy_pq", nullable=true, length=18) 
    private java.math.BigDecimal proxyPq;
    
    @PropertyDesc("偏差电量")
    @Column(name="deviation_pq", nullable=true, length=18) 
    private java.math.BigDecimal deviationPq;
    
    @PropertyDesc("电度电价")
    @Column(name="kwh_prc", nullable=true, length=10) 
    private java.math.BigDecimal kwhPrc;
    
    @PropertyDesc("政府基金及附加")
    @Column(name="pl_prc", nullable=true, length=10) 
    private java.math.BigDecimal plPrc;
    
    @PropertyDesc("合同电价")
    @Column(name="agre_prc", nullable=true, length=10) 
    private java.math.BigDecimal agrePrc;
    
    @PropertyDesc("输配电价")
    @Column(name="trans_prc", nullable=true, length=10) 
    private java.math.BigDecimal transPrc;
    
    @PropertyDesc("价差")
    @Column(name="d_value", nullable=true, length=10) 
    private java.math.BigDecimal dValue;
    
    @PropertyDesc("用户购电利润")
    @Column(name="cons_elec_profit", nullable=true, length=16) 
    private java.math.BigDecimal consElecProfit;
    
    @PropertyDesc("补偿用户电费")
    @Column(name="compensate_amt", nullable=true, length=16) 
    private java.math.BigDecimal compensateAmt;
    
    @PropertyDesc("用户利润")
    @Column(name="cons_profit", nullable=true, length=16) 
    private java.math.BigDecimal consProfit;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用户偏差考核电费")
    @Column(name="cons_check_awt", nullable=true, length=16) 
    private java.math.BigDecimal consCheckAwt;
    
    @PropertyDesc("偏差考核电量")
    @Column(name="cons_check_pq", nullable=true, length=18) 
    private java.math.BigDecimal consCheckPq;
    
    @PropertyDesc("偏差考核电价")
    @Column(name="cons_check_prc", nullable=true, length=18) 
    private java.math.BigDecimal consCheckPrc;
    
    @PropertyDesc("赔偿用户电量")
    @Column(name="compensate_pq", nullable=true, length=18) 
    private java.math.BigDecimal compensatePq;
    
    @PropertyDesc("赔偿用户电价")
    @Column(name="compensate_prc", nullable=true, length=18) 
    private java.math.BigDecimal compensatePrc;
    
    @PropertyDesc("状态('01'已计算，'02'已提交)")
    @Column(name="status", nullable=true, length=8) 
    private String status;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
    
    public String getVoltCode(){
        return voltCode;
    }
    
    public void setVoltCode(String voltCode){
        this.voltCode = voltCode;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getAgrePq(){
        return agrePq;
    }
    
    public void setAgrePq(java.math.BigDecimal agrePq){
        this.agrePq = agrePq;
    }
    
    public java.math.BigDecimal getBidPq(){
        return bidPq;
    }
    
    public void setBidPq(java.math.BigDecimal bidPq){
        this.bidPq = bidPq;
    }
    
    public java.math.BigDecimal getDeliveryPq(){
        return deliveryPq;
    }
    
    public void setDeliveryPq(java.math.BigDecimal deliveryPq){
        this.deliveryPq = deliveryPq;
    }
    
    public java.math.BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(java.math.BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public java.math.BigDecimal getDeviationPq(){
        return deviationPq;
    }
    
    public void setDeviationPq(java.math.BigDecimal deviationPq){
        this.deviationPq = deviationPq;
    }
    
    public java.math.BigDecimal getKwhPrc(){
        return kwhPrc;
    }
    
    public void setKwhPrc(java.math.BigDecimal kwhPrc){
        this.kwhPrc = kwhPrc;
    }
    
    public java.math.BigDecimal getPlPrc(){
        return plPrc;
    }
    
    public void setPlPrc(java.math.BigDecimal plPrc){
        this.plPrc = plPrc;
    }
    
    public java.math.BigDecimal getAgrePrc(){
        return agrePrc;
    }
    
    public void setAgrePrc(java.math.BigDecimal agrePrc){
        this.agrePrc = agrePrc;
    }
    
    public java.math.BigDecimal getTransPrc(){
        return transPrc;
    }
    
    public void setTransPrc(java.math.BigDecimal transPrc){
        this.transPrc = transPrc;
    }
    
    public java.math.BigDecimal getdValue(){
        return dValue;
    }
    
    public void setdValue(java.math.BigDecimal dValue){
        this.dValue = dValue;
    }
    
    public java.math.BigDecimal getConsElecProfit(){
        return consElecProfit;
    }
    
    public void setConsElecProfit(java.math.BigDecimal consElecProfit){
        this.consElecProfit = consElecProfit;
    }
    
    public java.math.BigDecimal getCompensateAmt(){
        return compensateAmt;
    }
    
    public void setCompensateAmt(java.math.BigDecimal compensateAmt){
        this.compensateAmt = compensateAmt;
    }
    
    public java.math.BigDecimal getConsProfit(){
        return consProfit;
    }
    
    public void setConsProfit(java.math.BigDecimal consProfit){
        this.consProfit = consProfit;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public java.math.BigDecimal getConsCheckAwt(){
        return consCheckAwt;
    }
    
    public void setConsCheckAwt(java.math.BigDecimal consCheckAwt){
        this.consCheckAwt = consCheckAwt;
    }
    
    public java.math.BigDecimal getConsCheckPq(){
        return consCheckPq;
    }
    
    public void setConsCheckPq(java.math.BigDecimal consCheckPq){
        this.consCheckPq = consCheckPq;
    }
    
    public java.math.BigDecimal getConsCheckPrc(){
        return consCheckPrc;
    }
    
    public void setConsCheckPrc(java.math.BigDecimal consCheckPrc){
        this.consCheckPrc = consCheckPrc;
    }
    
    public java.math.BigDecimal getCompensatePq(){
        return compensatePq;
    }
    
    public void setCompensatePq(java.math.BigDecimal compensatePq){
        this.compensatePq = compensatePq;
    }
    
    public java.math.BigDecimal getCompensatePrc(){
        return compensatePrc;
    }
    
    public void setCompensatePrc(java.math.BigDecimal compensatePrc){
        this.compensatePrc = compensatePrc;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
}