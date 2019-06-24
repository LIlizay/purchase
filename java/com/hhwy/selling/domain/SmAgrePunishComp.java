package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmAgrePunishComp
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmAgrePunishComp")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_agre_punish_comp")
public class SmAgrePunishComp extends Domain implements Serializable {

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
    
    @PropertyDesc("是否赔偿")
    @Column(name="punish_flag", nullable=false, length=3) 
    private String punishFlag;
    
    @PropertyDesc("赔偿域值")
    @Column(name="lower_threshold", nullable=true, length=6) 
    private java.math.BigDecimal lowerThreshold;
    
    @PropertyDesc("赔偿基准")
    @Column(name="lower_type", nullable=true, length=8) 
    private String lowerType;
    
    @PropertyDesc("赔偿协议价")
    @Column(name="lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerPrc;
    
    @PropertyDesc("赔偿比例值")
    @Column(name="lower_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerProp;
    
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
    
    public String getPunishFlag(){
        return punishFlag;
    }
    
    public void setPunishFlag(String punishFlag){
        this.punishFlag = punishFlag;
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
    
    public java.math.BigDecimal getLowerPrc(){
        return lowerPrc;
    }
    
    public void setLowerPrc(java.math.BigDecimal lowerPrc){
        this.lowerPrc = lowerPrc;
    }
    
    public java.math.BigDecimal getLowerProp(){
        return lowerProp;
    }
    
    public void setLowerProp(java.math.BigDecimal lowerProp){
        this.lowerProp = lowerProp;
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