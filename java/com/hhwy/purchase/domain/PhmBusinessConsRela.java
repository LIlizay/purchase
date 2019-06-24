package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmBusinessConsRela
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmBusinessConsRela")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_business_cons_rela")
public class PhmBusinessConsRela extends Domain implements Serializable {

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
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("负荷预测电量")
    @Column(name="load_prediction_pq", nullable=true, length=18) 
    private java.math.BigDecimal loadPredictionPq;
    
    @PropertyDesc("预测合同电量")
    @Column(name="forecast_agre_pq", nullable=true, length=18) 
    private java.math.BigDecimal forecastAgrePq;
    
    @PropertyDesc("预测合同电价")
    @Column(name="forecast_agre_prc", nullable=true, length=10) 
    private java.math.BigDecimal forecastAgrePrc;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
    public java.math.BigDecimal getLoadPredictionPq(){
        return loadPredictionPq;
    }
    
    public void setLoadPredictionPq(java.math.BigDecimal loadPredictionPq){
        this.loadPredictionPq = loadPredictionPq;
    }
    
    public java.math.BigDecimal getForecastAgrePq(){
        return forecastAgrePq;
    }
    
    public void setForecastAgrePq(java.math.BigDecimal forecastAgrePq){
        this.forecastAgrePq = forecastAgrePq;
    }
    
    public java.math.BigDecimal getForecastAgrePrc(){
        return forecastAgrePrc;
    }
    
    public void setForecastAgrePrc(java.math.BigDecimal forecastAgrePrc){
        this.forecastAgrePrc = forecastAgrePrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}