package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhcGeneratorSet
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhcGeneratorSet")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_c_generator_set")
public class PhcGeneratorSet extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("电厂标识")
    @Column(name="elec_id", nullable=true, length=32) 
    private String elecId;
    
    @PropertyDesc("机组名称")
    @Column(name="gene_name", nullable=true, length=256) 
    private String geneName;
    
    @PropertyDesc("接入电压等级")
    @Column(name="volt_code", nullable=true, length=8) 
    private String voltCode;
    
    @PropertyDesc("机组类型")
    @Column(name="type_code", nullable=true, length=8) 
    private String typeCode;
    
    @PropertyDesc("调度关系")
    @Column(name="schedul_relation", nullable=true, length=8) 
    private String schedulRelation;
    
    @PropertyDesc("额定容量")
    @Column(name="electric_capacity", nullable=true, length=16) 
    private java.math.BigDecimal electricCapacity;
    
    @PropertyDesc("利用小时数")
    @Column(name="use_hours", nullable=true, length=10) 
    private Integer useHours;
    
    @PropertyDesc("是否内网机组")
    @Column(name="inner_flag", nullable=true, length=3) 
    private String innerFlag;
    
    @PropertyDesc("是否脱硫")
    @Column(name="desu_flag", nullable=true, length=3) 
    private String desuFlag;
    
    @PropertyDesc("是否脱硝")
    @Column(name="deni_flag", nullable=true, length=3) 
    private String deniFlag;
    
    @PropertyDesc("是否除尘")
    @Column(name="dust_flag", nullable=true, length=3) 
    private String dustFlag;
    
    @PropertyDesc("是否碳捕捉")
    @Column(name="carb_flag", nullable=true, length=3) 
    private String carbFlag;
    
    @PropertyDesc("是否竞价机组")
    @Column(name="bid_gene_flag", nullable=true, length=3) 
    private String bidGeneFlag;
    
    @PropertyDesc("是否环保机组")
    @Column(name="prot_gene_flag", nullable=true, length=3) 
    private String protGeneFlag;
    
    @PropertyDesc("机组最大出力")
    @Column(name="max_output", nullable=true, length=16) 
    private java.math.BigDecimal maxOutput;
    
    @PropertyDesc("机组最小出力")
    @Column(name="min_output", nullable=true, length=16) 
    private java.math.BigDecimal minOutput;
    
    @PropertyDesc("设计标准煤耗")
    @Column(name="coal_wastage", nullable=true, length=16) 
    private java.math.BigDecimal coalWastage;
    
    @PropertyDesc("脱硫电价")
    @Column(name="desu_prc", nullable=true, length=8) 
    private java.math.BigDecimal desuPrc;
    
    @PropertyDesc("脱硝电价")
    @Column(name="deni_prc", nullable=true, length=8) 
    private java.math.BigDecimal deniPrc;
    
    @PropertyDesc("除尘电价")
    @Column(name="dust_prc", nullable=true, length=8) 
    private java.math.BigDecimal dustPrc;
    
    @PropertyDesc("超低排放电价")
    @Column(name="disc_prc", nullable=true, length=8) 
    private java.math.BigDecimal discPrc;
    
    @PropertyDesc("能耗因子系数")
    @Column(name="wastage_coefficient", nullable=true, length=6) 
    private java.math.BigDecimal wastageCoefficient;
    
    @PropertyDesc("关口计量点名称")
    @Column(name="mark_mp_name", nullable=true, length=64) 
    private String markMpName;
    
    @PropertyDesc("关口计量点综合倍率")
    @Column(name="mark_mp_rate", nullable=true, length=16) 
    private String markMpRate;
    
    @PropertyDesc("关口计量点编号")
    @Column(name="mark_mp_no", nullable=true, length=64) 
    private String markMpNo;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getElecId(){
        return elecId;
    }
    
    public void setElecId(String elecId){
        this.elecId = elecId;
    }
    
    public String getGeneName(){
        return geneName;
    }
    
    public void setGeneName(String geneName){
        this.geneName = geneName;
    }
    
    public String getVoltCode(){
        return voltCode;
    }
    
    public void setVoltCode(String voltCode){
        this.voltCode = voltCode;
    }
    
    public String getTypeCode(){
        return typeCode;
    }
    
    public void setTypeCode(String typeCode){
        this.typeCode = typeCode;
    }
    
    public String getSchedulRelation(){
        return schedulRelation;
    }
    
    public void setSchedulRelation(String schedulRelation){
        this.schedulRelation = schedulRelation;
    }
    
    public java.math.BigDecimal getElectricCapacity(){
        return electricCapacity;
    }
    
    public void setElectricCapacity(java.math.BigDecimal electricCapacity){
        this.electricCapacity = electricCapacity;
    }
    
    public Integer getUseHours(){
        return useHours;
    }
    
    public void setUseHours(Integer useHours){
        this.useHours = useHours;
    }
    
    public String getInnerFlag(){
        return innerFlag;
    }
    
    public void setInnerFlag(String innerFlag){
        this.innerFlag = innerFlag;
    }
    
    public String getDesuFlag(){
        return desuFlag;
    }
    
    public void setDesuFlag(String desuFlag){
        this.desuFlag = desuFlag;
    }
    
    public String getDeniFlag(){
        return deniFlag;
    }
    
    public void setDeniFlag(String deniFlag){
        this.deniFlag = deniFlag;
    }
    
    public String getDustFlag(){
        return dustFlag;
    }
    
    public void setDustFlag(String dustFlag){
        this.dustFlag = dustFlag;
    }
    
    public String getCarbFlag(){
        return carbFlag;
    }
    
    public void setCarbFlag(String carbFlag){
        this.carbFlag = carbFlag;
    }
    
    public String getBidGeneFlag(){
        return bidGeneFlag;
    }
    
    public void setBidGeneFlag(String bidGeneFlag){
        this.bidGeneFlag = bidGeneFlag;
    }
    
    public String getProtGeneFlag(){
        return protGeneFlag;
    }
    
    public void setProtGeneFlag(String protGeneFlag){
        this.protGeneFlag = protGeneFlag;
    }
    
    public java.math.BigDecimal getMaxOutput(){
        return maxOutput;
    }
    
    public void setMaxOutput(java.math.BigDecimal maxOutput){
        this.maxOutput = maxOutput;
    }
    
    public java.math.BigDecimal getMinOutput(){
        return minOutput;
    }
    
    public void setMinOutput(java.math.BigDecimal minOutput){
        this.minOutput = minOutput;
    }
    
    public java.math.BigDecimal getCoalWastage(){
        return coalWastage;
    }
    
    public void setCoalWastage(java.math.BigDecimal coalWastage){
        this.coalWastage = coalWastage;
    }
    
    public java.math.BigDecimal getDesuPrc(){
        return desuPrc;
    }
    
    public void setDesuPrc(java.math.BigDecimal desuPrc){
        this.desuPrc = desuPrc;
    }
    
    public java.math.BigDecimal getDeniPrc(){
        return deniPrc;
    }
    
    public void setDeniPrc(java.math.BigDecimal deniPrc){
        this.deniPrc = deniPrc;
    }
    
    public java.math.BigDecimal getDustPrc(){
        return dustPrc;
    }
    
    public void setDustPrc(java.math.BigDecimal dustPrc){
        this.dustPrc = dustPrc;
    }
    
    public java.math.BigDecimal getDiscPrc(){
        return discPrc;
    }
    
    public void setDiscPrc(java.math.BigDecimal discPrc){
        this.discPrc = discPrc;
    }
    
    public java.math.BigDecimal getWastageCoefficient(){
        return wastageCoefficient;
    }
    
    public void setWastageCoefficient(java.math.BigDecimal wastageCoefficient){
        this.wastageCoefficient = wastageCoefficient;
    }
    
    public String getMarkMpName(){
        return markMpName;
    }
    
    public void setMarkMpName(String markMpName){
        this.markMpName = markMpName;
    }
    
    public String getMarkMpRate(){
        return markMpRate;
    }
    
    public void setMarkMpRate(String markMpRate){
        this.markMpRate = markMpRate;
    }
    
    public String getMarkMpNo(){
        return markMpNo;
    }
    
    public void setMarkMpNo(String markMpNo){
        this.markMpNo = markMpNo;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}