package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfLcDealInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfLcDealInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_lc_deal_info")
public class PhfLcDealInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年份")
    @Column(name="year", nullable=true, length=4) 
    private String year;
    
    @PropertyDesc("总成交电量")
    @Column(name="deal_pq", nullable=true, length=18) 
    private java.math.BigDecimal dealPq;
    
    @PropertyDesc("成交均价")
    @Column(name="deal_prc", nullable=true, length=10) 
    private java.math.BigDecimal dealPrc;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    
    public java.math.BigDecimal getDealPq(){
        return dealPq;
    }
    
    public void setDealPq(java.math.BigDecimal dealPq){
        this.dealPq = dealPq;
    }
    
    public java.math.BigDecimal getDealPrc(){
        return dealPrc;
    }
    
    public void setDealPrc(java.math.BigDecimal dealPrc){
        this.dealPrc = dealPrc;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}