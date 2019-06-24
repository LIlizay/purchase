package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmGeneratorMonthPq
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmGeneratorMonthPq")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_generator_month_pq")
public class PhmGeneratorMonthPq extends Domain implements Serializable {

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
    
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("机组电量")
    @Column(name="generator_pq", nullable=true, length=18) 
    private java.math.BigDecimal generatorPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("机组电价")
    @Column(name="generator_prc", nullable=true, length=10) 
    private java.math.BigDecimal generatorPrc;
    
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
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getGeneratorPq(){
        return generatorPq;
    }
    
    public void setGeneratorPq(java.math.BigDecimal generatorPq){
        this.generatorPq = generatorPq;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public java.math.BigDecimal getGeneratorPrc(){
        return generatorPrc;
    }
    
    public void setGeneratorPrc(java.math.BigDecimal generatorPrc){
        this.generatorPrc = generatorPrc;
    }
    
}