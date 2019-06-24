package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmForecastPrc
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmForecastPrc")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_forecast_prc")
public class PhmForecastPrc extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("预测年份")
    @Column(name="year", nullable=true, length=4) 
    private String year;
    
    @PropertyDesc("长协高风险预测电价")
    @Column(name="lc_upper_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcUpperPrc;
    
    @PropertyDesc("长协中风险预测电价")
    @Column(name="lc_middle_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcMiddlePrc;
    
    @PropertyDesc("长协低风险预测电价")
    @Column(name="lc_lower_prc", nullable=true, length=10) 
    private java.math.BigDecimal lcLowerPrc;
    
    @PropertyDesc("竞价高风险预测电价")
    @Column(name="bid_upper_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidUpperPrc;
    
    @PropertyDesc("竞价中风险预测电价")
    @Column(name="bid_middle_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidMiddlePrc;
    
    @PropertyDesc("竞价低风险预测电价")
    @Column(name="bid_lower_prc", nullable=true, length=10) 
    private java.math.BigDecimal bidLowerPrc;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    
    public java.math.BigDecimal getLcUpperPrc(){
        return lcUpperPrc;
    }
    
    public void setLcUpperPrc(java.math.BigDecimal lcUpperPrc){
        this.lcUpperPrc = lcUpperPrc;
    }
    
    public java.math.BigDecimal getLcMiddlePrc(){
        return lcMiddlePrc;
    }
    
    public void setLcMiddlePrc(java.math.BigDecimal lcMiddlePrc){
        this.lcMiddlePrc = lcMiddlePrc;
    }
    
    public java.math.BigDecimal getLcLowerPrc(){
        return lcLowerPrc;
    }
    
    public void setLcLowerPrc(java.math.BigDecimal lcLowerPrc){
        this.lcLowerPrc = lcLowerPrc;
    }
    
    public java.math.BigDecimal getBidUpperPrc(){
        return bidUpperPrc;
    }
    
    public void setBidUpperPrc(java.math.BigDecimal bidUpperPrc){
        this.bidUpperPrc = bidUpperPrc;
    }
    
    public java.math.BigDecimal getBidMiddlePrc(){
        return bidMiddlePrc;
    }
    
    public void setBidMiddlePrc(java.math.BigDecimal bidMiddlePrc){
        this.bidMiddlePrc = bidMiddlePrc;
    }
    
    public java.math.BigDecimal getBidLowerPrc(){
        return bidLowerPrc;
    }
    
    public void setBidLowerPrc(java.math.BigDecimal bidLowerPrc){
        this.bidLowerPrc = bidLowerPrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}