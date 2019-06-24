package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmAgrePunishCons
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmAgrePunishCons")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_agre_punish_cons")
public class SmAgrePunishCons extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同ID")
    @Column(name="agre_id", nullable=true, length=32) 
    private String agreId;
    
    @PropertyDesc("正偏差惩罚类型")
    @Column(name="punish_type_upper", nullable=true, length=8) 
    private String punishTypeUpper;
    
    @PropertyDesc("负偏差惩罚类型")
    @Column(name="punish_type_lower", nullable=true, length=8) 
    private String punishTypeLower;
    
    @PropertyDesc("正偏差域值")
    @Column(name="upper_threshold", nullable=true, length=6) 
    private java.math.BigDecimal upperThreshold;
    
    @PropertyDesc("正偏差惩罚电价基准")
    @Column(name="upper_type", nullable=true, length=8) 
    private String upperType;
    
    @PropertyDesc("超出部分惩罚比例值")
    @Column(name="upper_prop", nullable=true, length=6) 
    private java.math.BigDecimal upperProp;
    
    @PropertyDesc("负偏差域值")
    @Column(name="lower_threshold", nullable=true, length=6) 
    private java.math.BigDecimal lowerThreshold;
    
    @PropertyDesc("负偏差惩罚电价基准")
    @Column(name="lower_type", nullable=true, length=8) 
    private String lowerType;
    
    @PropertyDesc("负偏差惩罚比例值")
    @Column(name="lower_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerProp;
    
    @PropertyDesc("正偏差惩罚协议价")
    @Column(name="upper_prc", nullable=true, length=8) 
    private java.math.BigDecimal upperPrc;
    
    @PropertyDesc("负偏差惩罚协议价")
    @Column(name="lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerPrc;
    
    @PropertyDesc("惩罚附加项(惩罚时额外附加条件是否生效)")
    @Column(name="punish_flag", nullable=true, length=3) 
    private String punishFlag;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getAgreId(){
        return agreId;
    }
    
    public void setAgreId(String agreId){
        this.agreId = agreId;
    }
    
    public String getPunishTypeUpper() {
		return punishTypeUpper;
	}

	public void setPunishTypeUpper(String punishTypeUpper) {
		this.punishTypeUpper = punishTypeUpper;
	}

	public String getPunishTypeLower() {
		return punishTypeLower;
	}

	public void setPunishTypeLower(String punishTypeLower) {
		this.punishTypeLower = punishTypeLower;
	}

	public java.math.BigDecimal getUpperThreshold(){
        return upperThreshold;
    }
    
    public void setUpperThreshold(java.math.BigDecimal upperThreshold){
        this.upperThreshold = upperThreshold;
    }
    
    public String getUpperType(){
        return upperType;
    }
    
    public void setUpperType(String upperType){
        this.upperType = upperType;
    }
    
    public java.math.BigDecimal getUpperProp(){
        return upperProp;
    }
    
    public void setUpperProp(java.math.BigDecimal upperProp){
        this.upperProp = upperProp;
    }
    
    public java.math.BigDecimal getLowerThreshold(){
        return lowerThreshold;
    }
    
    public void setLowerThreshold(java.math.BigDecimal lowerThreshold){
        this.lowerThreshold = lowerThreshold;
    }
    
    public String getLowerType(){
        return lowerType;
    }
    
    public void setLowerType(String lowerType){
        this.lowerType = lowerType;
    }
    
    public java.math.BigDecimal getLowerProp(){
        return lowerProp;
    }
    
    public void setLowerProp(java.math.BigDecimal lowerProp){
        this.lowerProp = lowerProp;
    }
    
    public java.math.BigDecimal getUpperPrc(){
        return upperPrc;
    }
    
    public void setUpperPrc(java.math.BigDecimal upperPrc){
        this.upperPrc = upperPrc;
    }
    
    public java.math.BigDecimal getLowerPrc(){
        return lowerPrc;
    }
    
    public void setLowerPrc(java.math.BigDecimal lowerPrc){
        this.lowerPrc = lowerPrc;
    }
    
    public String getPunishFlag(){
        return punishFlag;
    }
    
    public void setPunishFlag(String punishFlag){
        this.punishFlag = punishFlag;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}