package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmElecList
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmElecList")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_elec_list")
public class PhmElecList extends Domain implements Serializable {

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
    
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("计量类型")
    @Column(name="metering_type", nullable=true, length=8) 
    private String meteringType;
    
    @PropertyDesc("示数")
    @Column(name="read_type", nullable=true, length=8) 
    private String readType;
    
    @PropertyDesc("表号")
    @Column(name="meter_no", nullable=true, length=16) 
    private String meterNo;
    
    @PropertyDesc("起码")
    @Column(name="start_number", nullable=true, length=18) 
    private java.math.BigDecimal startNumber;
    
    @PropertyDesc("止码")
    @Column(name="end_number", nullable=true, length=18) 
    private java.math.BigDecimal endNumber;
    
    @PropertyDesc("倍率")
    @Column(name="meter_rate", nullable=true, length=8) 
    private String meterRate;
    
    @PropertyDesc("抄见电量")
    @Column(name="copy_pq", nullable=true, length=18) 
    private java.math.BigDecimal copyPq;
    
    @PropertyDesc("减分表")
    @Column(name="subtract_form", nullable=true, length=18) 
    private java.math.BigDecimal subtractForm;
    
    @PropertyDesc("变损")
    @Column(name="trans_loss", nullable=true, length=18) 
    private java.math.BigDecimal transLoss;
    
    @PropertyDesc("线损")
    @Column(name="line_loss", nullable=true, length=18) 
    private java.math.BigDecimal lineLoss;
    
    @PropertyDesc("总电量")
    @Column(name="total_pq", nullable=true, length=18) 
    private java.math.BigDecimal totalPq;
    
    @PropertyDesc("目录电价")
    @Column(name="kwh_prc", nullable=true, length=8) 
    private java.math.BigDecimal kwhPrc;
    
    @PropertyDesc("目录电费")
    @Column(name="kwh_amt", nullable=true, length=18) 
    private java.math.BigDecimal kwhAmt;
    
    @PropertyDesc("容需量")
    @Column(name="cap_demand", nullable=true, length=18) 
    private java.math.BigDecimal capDemand;
    
    @PropertyDesc("基本电费")
    @Column(name="base_amt", nullable=true, length=18) 
    private java.math.BigDecimal baseAmt;
    
    @PropertyDesc("力调电费")
    @Column(name="adjust_amt", nullable=true, length=18) 
    private java.math.BigDecimal adjustAmt;
    
    @PropertyDesc("电铁还贷")
    @Column(name="repay_amt", nullable=true, length=18) 
    private java.math.BigDecimal repayAmt;
    
    @PropertyDesc("代征合计")
    @Column(name="replace_amt", nullable=true, length=18) 
    private java.math.BigDecimal replaceAmt;
    
    @PropertyDesc("附加")
    @Column(name="additional_amt", nullable=true, length=18) 
    private java.math.BigDecimal additionalAmt;
    
    @PropertyDesc("农网")
    @Column(name="rural_amt", nullable=true, length=18) 
    private java.math.BigDecimal ruralAmt;
    
    @PropertyDesc("可再生")
    @Column(name="regenerate_amt", nullable=true, length=18) 
    private java.math.BigDecimal regenerateAmt;
    
    @PropertyDesc("农维")
    @Column(name="agriculture_amt", nullable=true, length=18) 
    private java.math.BigDecimal agricultureAmt;
    
    @PropertyDesc("电源")
    @Column(name="elec_source_amt", nullable=true, length=18) 
    private java.math.BigDecimal elecSourceAmt;
    
    @PropertyDesc("小水库")
    @Column(name="small_reservoir_amt", nullable=true, length=18) 
    private java.math.BigDecimal smallReservoirAmt;
    
    @PropertyDesc("大中型水库")
    @Column(name="large_reservoir_amt", nullable=true, length=18) 
    private java.math.BigDecimal largeReservoirAmt;
    
    @PropertyDesc("差别")
    @Column(name="difference_amt", nullable=true, length=18) 
    private java.math.BigDecimal differenceAmt;
    
    @PropertyDesc("专项基金")
    @Column(name="special_amt", nullable=true, length=18) 
    private java.math.BigDecimal specialAmt;
    
    @PropertyDesc("三峡")
    @Column(name="three_gorges_amt", nullable=true, length=18) 
    private java.math.BigDecimal threeGorgesAmt;
    
    @PropertyDesc("输配电损耗加价")
    @Column(name="loss_amt", nullable=true, length=18) 
    private java.math.BigDecimal lossAmt;
    
    @PropertyDesc("电度输配电价")
    @Column(name="trans_prc", nullable=true, length=8) 
    private java.math.BigDecimal transPrc;
    
    @PropertyDesc("总电费")
    @Column(name="total_amt", nullable=true, length=18) 
    private java.math.BigDecimal totalAmt;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public String getMeteringType(){
        return meteringType;
    }
    
    public void setMeteringType(String meteringType){
        this.meteringType = meteringType;
    }
    
    public String getReadType(){
        return readType;
    }
    
    public void setReadType(String readType){
        this.readType = readType;
    }
    
    public String getMeterNo(){
        return meterNo;
    }
    
    public void setMeterNo(String meterNo){
        this.meterNo = meterNo;
    }
    
    public java.math.BigDecimal getStartNumber(){
        return startNumber;
    }
    
    public void setStartNumber(java.math.BigDecimal startNumber){
        this.startNumber = startNumber;
    }
    
    public java.math.BigDecimal getEndNumber(){
        return endNumber;
    }
    
    public void setEndNumber(java.math.BigDecimal endNumber){
        this.endNumber = endNumber;
    }
    
    public String getMeterRate(){
        return meterRate;
    }
    
    public void setMeterRate(String meterRate){
        this.meterRate = meterRate;
    }
    
    public java.math.BigDecimal getCopyPq(){
        return copyPq;
    }
    
    public void setCopyPq(java.math.BigDecimal copyPq){
        this.copyPq = copyPq;
    }
    
    public java.math.BigDecimal getSubtractForm(){
        return subtractForm;
    }
    
    public void setSubtractForm(java.math.BigDecimal subtractForm){
        this.subtractForm = subtractForm;
    }
    
    public java.math.BigDecimal getTransLoss(){
        return transLoss;
    }
    
    public void setTransLoss(java.math.BigDecimal transLoss){
        this.transLoss = transLoss;
    }
    
    public java.math.BigDecimal getLineLoss(){
        return lineLoss;
    }
    
    public void setLineLoss(java.math.BigDecimal lineLoss){
        this.lineLoss = lineLoss;
    }
    
    public java.math.BigDecimal getTotalPq(){
        return totalPq;
    }
    
    public void setTotalPq(java.math.BigDecimal totalPq){
        this.totalPq = totalPq;
    }
    
    public java.math.BigDecimal getKwhPrc(){
        return kwhPrc;
    }
    
    public void setKwhPrc(java.math.BigDecimal kwhPrc){
        this.kwhPrc = kwhPrc;
    }
    
    public java.math.BigDecimal getKwhAmt(){
        return kwhAmt;
    }
    
    public void setKwhAmt(java.math.BigDecimal kwhAmt){
        this.kwhAmt = kwhAmt;
    }
    
    public java.math.BigDecimal getCapDemand(){
        return capDemand;
    }
    
    public void setCapDemand(java.math.BigDecimal capDemand){
        this.capDemand = capDemand;
    }
    
    public java.math.BigDecimal getBaseAmt(){
        return baseAmt;
    }
    
    public void setBaseAmt(java.math.BigDecimal baseAmt){
        this.baseAmt = baseAmt;
    }
    
    public java.math.BigDecimal getAdjustAmt(){
        return adjustAmt;
    }
    
    public void setAdjustAmt(java.math.BigDecimal adjustAmt){
        this.adjustAmt = adjustAmt;
    }
    
    public java.math.BigDecimal getRepayAmt(){
        return repayAmt;
    }
    
    public void setRepayAmt(java.math.BigDecimal repayAmt){
        this.repayAmt = repayAmt;
    }
    
    public java.math.BigDecimal getReplaceAmt(){
        return replaceAmt;
    }
    
    public void setReplaceAmt(java.math.BigDecimal replaceAmt){
        this.replaceAmt = replaceAmt;
    }
    
    public java.math.BigDecimal getAdditionalAmt(){
        return additionalAmt;
    }
    
    public void setAdditionalAmt(java.math.BigDecimal additionalAmt){
        this.additionalAmt = additionalAmt;
    }
    
    public java.math.BigDecimal getRuralAmt(){
        return ruralAmt;
    }
    
    public void setRuralAmt(java.math.BigDecimal ruralAmt){
        this.ruralAmt = ruralAmt;
    }
    
    public java.math.BigDecimal getRegenerateAmt(){
        return regenerateAmt;
    }
    
    public void setRegenerateAmt(java.math.BigDecimal regenerateAmt){
        this.regenerateAmt = regenerateAmt;
    }
    
    public java.math.BigDecimal getAgricultureAmt(){
        return agricultureAmt;
    }
    
    public void setAgricultureAmt(java.math.BigDecimal agricultureAmt){
        this.agricultureAmt = agricultureAmt;
    }
    
    public java.math.BigDecimal getElecSourceAmt(){
        return elecSourceAmt;
    }
    
    public void setElecSourceAmt(java.math.BigDecimal elecSourceAmt){
        this.elecSourceAmt = elecSourceAmt;
    }
    
    public java.math.BigDecimal getSmallReservoirAmt(){
        return smallReservoirAmt;
    }
    
    public void setSmallReservoirAmt(java.math.BigDecimal smallReservoirAmt){
        this.smallReservoirAmt = smallReservoirAmt;
    }
    
    public java.math.BigDecimal getLargeReservoirAmt(){
        return largeReservoirAmt;
    }
    
    public void setLargeReservoirAmt(java.math.BigDecimal largeReservoirAmt){
        this.largeReservoirAmt = largeReservoirAmt;
    }
    
    public java.math.BigDecimal getDifferenceAmt(){
        return differenceAmt;
    }
    
    public void setDifferenceAmt(java.math.BigDecimal differenceAmt){
        this.differenceAmt = differenceAmt;
    }
    
    public java.math.BigDecimal getSpecialAmt(){
        return specialAmt;
    }
    
    public void setSpecialAmt(java.math.BigDecimal specialAmt){
        this.specialAmt = specialAmt;
    }
    
    public java.math.BigDecimal getThreeGorgesAmt(){
        return threeGorgesAmt;
    }
    
    public void setThreeGorgesAmt(java.math.BigDecimal threeGorgesAmt){
        this.threeGorgesAmt = threeGorgesAmt;
    }
    
    public java.math.BigDecimal getLossAmt(){
        return lossAmt;
    }
    
    public void setLossAmt(java.math.BigDecimal lossAmt){
        this.lossAmt = lossAmt;
    }
    
    public java.math.BigDecimal getTransPrc(){
        return transPrc;
    }
    
    public void setTransPrc(java.math.BigDecimal transPrc){
        this.transPrc = transPrc;
    }
    
    public java.math.BigDecimal getTotalAmt(){
        return totalAmt;
    }
    
    public void setTotalAmt(java.math.BigDecimal totalAmt){
        this.totalAmt = totalAmt;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}