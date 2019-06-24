package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmGenePqDist
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmGenePqDist")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_gene_pq_dist")
public class PhmGenePqDist extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同标识")
    @Column(name="agre_id", nullable=true, length=32) 
    private String agreId;
    
    @PropertyDesc("机组标识")
    @Column(name="generator_id", nullable=true, length=32) 
    private String generatorId;
    
    @PropertyDesc("总电量")
    @Column(name="tota_pq", nullable=true, length=16) 
    private java.math.BigDecimal totaPq;
    
    @PropertyDesc("分解方式")
    @Column(name="distribution_mode", nullable=true, length=8) 
    private String distributionMode;
    
    @PropertyDesc("保留小数位")
    @Column(name="save_decimal", nullable=true, length=3) 
    private String saveDecimal;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getAgreId(){
        return agreId;
    }
    
    public void setAgreId(String agreId){
        this.agreId = agreId;
    }
    
    public String getGeneratorId(){
        return generatorId;
    }
    
    public void setGeneratorId(String generatorId){
        this.generatorId = generatorId;
    }
    
    public java.math.BigDecimal getTotaPq(){
        return totaPq;
    }
    
    public void setTotaPq(java.math.BigDecimal totaPq){
        this.totaPq = totaPq;
    }
    
    public String getDistributionMode(){
        return distributionMode;
    }
    
    public void setDistributionMode(String distributionMode){
        this.distributionMode = distributionMode;
    }
    
    public String getSaveDecimal(){
        return saveDecimal;
    }
    
    public void setSaveDecimal(String saveDecimal){
        this.saveDecimal = saveDecimal;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}