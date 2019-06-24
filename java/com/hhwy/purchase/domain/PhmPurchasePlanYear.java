package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmPurchasePlanYear
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmPurchasePlanYear")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_purchase_plan_year")
public class PhmPurchasePlanYear extends Domain implements Serializable {

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
    
    @PropertyDesc("购电计划名称")
    @Column(name="plan_name", nullable=true, length=16) 
    private String planName;
    
    @PropertyDesc("制定人")
    @Column(name="setters", nullable=true, length=32) 
    private String setters;
    
    @PropertyDesc("计划状态")
    @Column(name="plan_status", nullable=true, length=8) 
    private String planStatus;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYear(){
        return year;
    }
    
    public void setYear(String year){
        this.year = year;
    }
    
    public String getPlanName(){
        return planName;
    }
    
    public void setPlanName(String planName){
        this.planName = planName;
    }
    
    public String getSetters(){
        return setters;
    }
    
    public void setSetters(String setters){
        this.setters = setters;
    }
    
    public String getPlanStatus(){
        return planStatus;
    }
    
    public void setPlanStatus(String planStatus){
        this.planStatus = planStatus;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}