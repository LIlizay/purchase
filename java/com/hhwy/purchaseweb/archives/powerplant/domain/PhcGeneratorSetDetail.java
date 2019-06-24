package com.hhwy.purchaseweb.archives.powerplant.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class PhcGeneratorSetDetail{

    private String id;
    
    /**  电厂标识  **/
    private String elecId;
    
    /**  机组名称  **/
    private String geneName;
    
    /**  接入电压等级  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    /**  机组类型  **/
    private String typeCode;
    
    /**  调度关系  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_schedulRelation" ,field="schedulRelationName")
    private String schedulRelation;
    private String schedulRelationName;
    
    /**  额定容量  **/
    private java.math.BigDecimal electricCapacity;
    
    /**  利用小时数  **/
    private Integer useHours;
    
    /**  是否内网机组  **/
    private String innerFlag;
    
    /**  是否脱硫  **/
    private String desuFlag;
    
    /**  是否脱硝  **/
    private String deniFlag;
    
    /**  是否除尘  **/
    private String dustFlag;
    
    /**  是否碳捕捉  **/
    private String carbFlag;
    
    /**  是否竞价机组  **/
    private String bidGeneFlag;
    
    /**  是否环保机组  **/
    private String protGeneFlag;
    
    /**  机组最大出力  **/
    private java.math.BigDecimal maxOutput;
    
    /**  机组最小出力  **/
    private java.math.BigDecimal minOutput;
    
    /**  设计标准煤耗  **/
    private java.math.BigDecimal coalWastage;
    
    /**  脱硫电价  **/
    private java.math.BigDecimal desuPrc;
    
    /**  脱硝电价  **/
    private java.math.BigDecimal deniPrc;
    
    /**  除尘电价  **/
    private java.math.BigDecimal dustPrc;
    
    /**  超低排放电价  **/
    private java.math.BigDecimal discPrc;
    
    /**  能耗因子系数  **/
    private java.math.BigDecimal wastageCoefficient;
    
    /**  关口计量点名称  **/
    private String markMpName;
    
    /**  关口计量点综合倍率 **/
    private String markMpRate;
    
    /**  关口计量点编号  **/
    private String markMpNo;
    
    /**  部门id  **/
    private String orgNo;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getElecId() {
        return elecId;
    }

    public void setElecId(String elecId) {
        this.elecId = elecId;
    }

    public String getGeneName() {
        return geneName;
    }

    public void setGeneName(String geneName) {
        this.geneName = geneName;
    }

    public String getVoltCode() {
        return voltCode;
    }

    public void setVoltCode(String voltCode) {
        this.voltCode = voltCode;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getSchedulRelation() {
        return schedulRelation;
    }

    public void setSchedulRelation(String schedulRelation) {
        this.schedulRelation = schedulRelation;
    }

    public java.math.BigDecimal getElectricCapacity() {
        return electricCapacity;
    }

    public void setElectricCapacity(java.math.BigDecimal electricCapacity) {
        this.electricCapacity = electricCapacity;
    }

    public Integer getUseHours() {
        return useHours;
    }

    public void setUseHours(Integer useHours) {
        this.useHours = useHours;
    }

    public String getInnerFlag() {
        return innerFlag;
    }

    public void setInnerFlag(String innerFlag) {
        this.innerFlag = innerFlag;
    }

    public String getDesuFlag() {
        return desuFlag;
    }

    public void setDesuFlag(String desuFlag) {
        this.desuFlag = desuFlag;
    }

    public String getDeniFlag() {
        return deniFlag;
    }

    public void setDeniFlag(String deniFlag) {
        this.deniFlag = deniFlag;
    }

    public String getDustFlag() {
        return dustFlag;
    }

    public void setDustFlag(String dustFlag) {
        this.dustFlag = dustFlag;
    }

    public String getCarbFlag() {
        return carbFlag;
    }

    public void setCarbFlag(String carbFlag) {
        this.carbFlag = carbFlag;
    }

    public String getBidGeneFlag() {
        return bidGeneFlag;
    }

    public void setBidGeneFlag(String bidGeneFlag) {
        this.bidGeneFlag = bidGeneFlag;
    }

    public String getProtGeneFlag() {
        return protGeneFlag;
    }

    public void setProtGeneFlag(String protGeneFlag) {
        this.protGeneFlag = protGeneFlag;
    }

    public java.math.BigDecimal getMaxOutput() {
        return maxOutput;
    }

    public void setMaxOutput(java.math.BigDecimal maxOutput) {
        this.maxOutput = maxOutput;
    }

    public java.math.BigDecimal getMinOutput() {
        return minOutput;
    }

    public void setMinOutput(java.math.BigDecimal minOutput) {
        this.minOutput = minOutput;
    }

    public java.math.BigDecimal getCoalWastage() {
        return coalWastage;
    }

    public void setCoalWastage(java.math.BigDecimal coalWastage) {
        this.coalWastage = coalWastage;
    }

    public java.math.BigDecimal getDesuPrc() {
        return desuPrc;
    }

    public void setDesuPrc(java.math.BigDecimal desuPrc) {
        this.desuPrc = desuPrc;
    }

    public java.math.BigDecimal getDeniPrc() {
        return deniPrc;
    }

    public void setDeniPrc(java.math.BigDecimal deniPrc) {
        this.deniPrc = deniPrc;
    }

    public java.math.BigDecimal getDustPrc() {
        return dustPrc;
    }

    public void setDustPrc(java.math.BigDecimal dustPrc) {
        this.dustPrc = dustPrc;
    }

    public java.math.BigDecimal getDiscPrc() {
        return discPrc;
    }

    public void setDiscPrc(java.math.BigDecimal discPrc) {
        this.discPrc = discPrc;
    }

    public java.math.BigDecimal getWastageCoefficient() {
        return wastageCoefficient;
    }

    public void setWastageCoefficient(java.math.BigDecimal wastageCoefficient) {
        this.wastageCoefficient = wastageCoefficient;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getVoltCodeName() {
        return voltCodeName;
    }

    public void setVoltCodeName(String voltCodeName) {
        this.voltCodeName = voltCodeName;
    }

    public String getSchedulRelationName() {
        return schedulRelationName;
    }

    public void setSchedulRelationName(String schedulRelationName) {
        this.schedulRelationName = schedulRelationName;
    }

    public String getMarkMpName() {
        return markMpName;
    }

    public void setMarkMpName(String markMpName) {
        this.markMpName = markMpName;
    }

    public String getMarkMpRate() {
        return markMpRate;
    }

    public void setMarkMpRate(String markMpRate) {
        this.markMpRate = markMpRate;
    }

    public String getMarkMpNo() {
        return markMpNo;
    }

    public void setMarkMpNo(String markMpNo) {
        this.markMpNo = markMpNo;
    }
    
}
