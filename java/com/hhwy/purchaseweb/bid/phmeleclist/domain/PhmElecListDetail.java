package com.hhwy.purchaseweb.bid.phmeleclist.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.persistent.entity.Domain;

public class PhmElecListDetail extends Domain implements Serializable{

     /**
     * serialVersionUID
     * @return  the serialVersionUID
     * @since   1.0.0
    */
    private static final long serialVersionUID = 609047379671146486L;

    /**  用户id  **/
    private String consId;
    
    /**  年月  **/
    private String ym;
    
    /**  计量类型  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_meteringType" ,field="meteringTypeName")
    private String meteringType;
    private String meteringTypeName;
    
    /**  示数  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_readType" ,field="readTypeName")
    private String readType;
    private String readTypeName;
    
    /**  表号  **/
    private String meterNo;
    
    /**  起码  **/
    private BigDecimal startNumber;
    
    /**  止码  **/
    private BigDecimal endNumber;
    
    /**  倍率  **/
    private String meterRate;
    
    /**  抄见电量  **/
    private BigDecimal copyPq;
    
    /**  减分表  **/
    private BigDecimal subtractForm;
    
    /**  变损  **/
    private BigDecimal transLoss;
    
    /**  线损  **/
    private BigDecimal lineLoss;
    
    /**  总电量  **/
    private BigDecimal totalPq;
    
    /**  目录电价  **/
    private BigDecimal kwhPrc;
    
    /**  目录电费  **/
    private BigDecimal kwhAmt;
    
    /**  容需量  **/
    private BigDecimal capDemand;
    
    /**  基本电费  **/
    private BigDecimal baseAmt;
    
    /**  力调电费  **/
    private BigDecimal adjustAmt;
    
    /**  电铁还贷  **/
    private BigDecimal repayAmt;
    
    /**  代征合计  **/
    private BigDecimal replaceAmt;
    
    /**  附加  **/
    private BigDecimal additionalAmt;
    
    /**  农网  **/
    private BigDecimal ruralAmt;
    
    /**  可再生  **/
    private BigDecimal regenerateAmt;
    
    /**  农维  **/
    private BigDecimal agricultureAmt;
    
    /**  电源  **/
    private BigDecimal elecSourceAmt;
    
    /**  小水库  **/
    private BigDecimal smallReservoirAmt;
    
    /**  大中型水库  **/
    private BigDecimal largeReservoirAmt;
    
    /**  差别  **/
    private BigDecimal differenceAmt;
    
    /**  专项基金  **/
    private BigDecimal specialAmt;
    
    /**  三峡  **/
    private BigDecimal threeGorgesAmt;
    
    /**  输配电损耗加价  **/
    private BigDecimal lossAmt;
    
    /**  电度输配电价  **/
    private BigDecimal transPrc;
    
    /**  总电费  **/
    private BigDecimal totalAmt;
    
    /**  部门id  **/
    private String orgNo;

    public String getConsId() {
        return consId;
    }

    public void setConsId(String consId) {
        this.consId = consId;
    }

    public String getYm() {
        return ym;
    }

    public void setYm(String ym) {
        this.ym = ym;
    }

    public String getMeteringType() {
        return meteringType;
    }

    public void setMeteringType(String meteringType) {
        this.meteringType = meteringType;
    }

    public String getMeteringTypeName() {
        return meteringTypeName;
    }

    public void setMeteringTypeName(String meteringTypeName) {
        this.meteringTypeName = meteringTypeName;
    }

    public String getReadType() {
        return readType;
    }

    public void setReadType(String readType) {
        this.readType = readType;
    }

    public String getReadTypeName() {
        return readTypeName;
    }

    public void setReadTypeName(String readTypeName) {
        this.readTypeName = readTypeName;
    }

    public String getMeterNo() {
        return meterNo;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public BigDecimal getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(BigDecimal startNumber) {
        this.startNumber = startNumber;
    }

    public BigDecimal getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(BigDecimal endNumber) {
        this.endNumber = endNumber;
    }

    public String getMeterRate() {
        return meterRate;
    }

    public void setMeterRate(String meterRate) {
        this.meterRate = meterRate;
    }

    public BigDecimal getCopyPq() {
        return copyPq;
    }

    public void setCopyPq(BigDecimal copyPq) {
        this.copyPq = copyPq;
    }

    public BigDecimal getSubtractForm() {
        return subtractForm;
    }

    public void setSubtractForm(BigDecimal subtractForm) {
        this.subtractForm = subtractForm;
    }

    public BigDecimal getTransLoss() {
        return transLoss;
    }

    public void setTransLoss(BigDecimal transLoss) {
        this.transLoss = transLoss;
    }

    public BigDecimal getLineLoss() {
        return lineLoss;
    }

    public void setLineLoss(BigDecimal lineLoss) {
        this.lineLoss = lineLoss;
    }

    public BigDecimal getTotalPq() {
        return totalPq;
    }

    public void setTotalPq(BigDecimal totalPq) {
        this.totalPq = totalPq;
    }

    public BigDecimal getKwhPrc() {
        return kwhPrc;
    }

    public void setKwhPrc(BigDecimal kwhPrc) {
        this.kwhPrc = kwhPrc;
    }

    public BigDecimal getKwhAmt() {
        return kwhAmt;
    }

    public void setKwhAmt(BigDecimal kwhAmt) {
        this.kwhAmt = kwhAmt;
    }

    public BigDecimal getCapDemand() {
        return capDemand;
    }

    public void setCapDemand(BigDecimal capDemand) {
        this.capDemand = capDemand;
    }

    public BigDecimal getBaseAmt() {
        return baseAmt;
    }

    public void setBaseAmt(BigDecimal baseAmt) {
        this.baseAmt = baseAmt;
    }

    public BigDecimal getAdjustAmt() {
        return adjustAmt;
    }

    public void setAdjustAmt(BigDecimal adjustAmt) {
        this.adjustAmt = adjustAmt;
    }

    public BigDecimal getRepayAmt() {
        return repayAmt;
    }

    public void setRepayAmt(BigDecimal repayAmt) {
        this.repayAmt = repayAmt;
    }

    public BigDecimal getReplaceAmt() {
        return replaceAmt;
    }

    public void setReplaceAmt(BigDecimal replaceAmt) {
        this.replaceAmt = replaceAmt;
    }

    public BigDecimal getAdditionalAmt() {
        return additionalAmt;
    }

    public void setAdditionalAmt(BigDecimal additionalAmt) {
        this.additionalAmt = additionalAmt;
    }

    public BigDecimal getRuralAmt() {
        return ruralAmt;
    }

    public void setRuralAmt(BigDecimal ruralAmt) {
        this.ruralAmt = ruralAmt;
    }

    public BigDecimal getRegenerateAmt() {
        return regenerateAmt;
    }

    public void setRegenerateAmt(BigDecimal regenerateAmt) {
        this.regenerateAmt = regenerateAmt;
    }

    public BigDecimal getAgricultureAmt() {
        return agricultureAmt;
    }

    public void setAgricultureAmt(BigDecimal agricultureAmt) {
        this.agricultureAmt = agricultureAmt;
    }

    public BigDecimal getElecSourceAmt() {
        return elecSourceAmt;
    }

    public void setElecSourceAmt(BigDecimal elecSourceAmt) {
        this.elecSourceAmt = elecSourceAmt;
    }

    public BigDecimal getSmallReservoirAmt() {
        return smallReservoirAmt;
    }

    public void setSmallReservoirAmt(BigDecimal smallReservoirAmt) {
        this.smallReservoirAmt = smallReservoirAmt;
    }

    public BigDecimal getLargeReservoirAmt() {
        return largeReservoirAmt;
    }

    public void setLargeReservoirAmt(BigDecimal largeReservoirAmt) {
        this.largeReservoirAmt = largeReservoirAmt;
    }

    public BigDecimal getDifferenceAmt() {
        return differenceAmt;
    }

    public void setDifferenceAmt(BigDecimal differenceAmt) {
        this.differenceAmt = differenceAmt;
    }

    public BigDecimal getSpecialAmt() {
        return specialAmt;
    }

    public void setSpecialAmt(BigDecimal specialAmt) {
        this.specialAmt = specialAmt;
    }

    public BigDecimal getThreeGorgesAmt() {
        return threeGorgesAmt;
    }

    public void setThreeGorgesAmt(BigDecimal threeGorgesAmt) {
        this.threeGorgesAmt = threeGorgesAmt;
    }

    public BigDecimal getLossAmt() {
        return lossAmt;
    }

    public void setLossAmt(BigDecimal lossAmt) {
        this.lossAmt = lossAmt;
    }

    public BigDecimal getTransPrc() {
        return transPrc;
    }

    public void setTransPrc(BigDecimal transPrc) {
        this.transPrc = transPrc;
    }

    public BigDecimal getTotalAmt() {
        return totalAmt;
    }

    public void setTotalAmt(BigDecimal totalAmt) {
        this.totalAmt = totalAmt;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }
}
