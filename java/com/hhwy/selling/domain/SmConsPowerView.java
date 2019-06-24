package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * SmConsPowerView
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmConsPowerView")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_cons_power_view")
public class SmConsPowerView extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("计划标识")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("用户标识")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("日期")
    @Column(name="ymd", nullable=true, length=8) 
    private String ymd;
    
    @PropertyDesc("星期数")
    @Column(name="week", nullable=true, length=3) 
    private String week;
    
    @PropertyDesc("计划用电量")
    @Column(name="plan_pq", nullable=true, length=18) 
    private BigDecimal planPq;
    
    @PropertyDesc("实际用电量")
    @Column(name="actual_pq", nullable=true, length=18) 
    private BigDecimal actualPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用电年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("日计划用电量")
    @Column(name="day_plan_pq", nullable=true, length=18) 
    private BigDecimal dayPlanPq;
    
    @PropertyDesc("日实际用电量")
    @Column(name="day_actual_pq", nullable=true, length=18) 
    private BigDecimal dayActualPq;
    
    @PropertyDesc("是否被删除,0:否，启用状态，1：是，删除状态")
    @Column(name="is_delete", nullable=true, length=3) 
    private String isDelete;
    
    
    
    
	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
    public BigDecimal getDayPlanPq() {
		return dayPlanPq;
	}

	public void setDayPlanPq(BigDecimal dayPlanPq) {
		this.dayPlanPq = dayPlanPq;
	}

	public BigDecimal getDayActualPq() {
		return dayActualPq;
	}

	public void setDayActualPq(BigDecimal dayActualPq) {
		this.dayActualPq = dayActualPq;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getYmd(){
        return ymd;
    }
    
    public void setYmd(String ymd){
        this.ymd = ymd;
    }
    
    public String getWeek(){
        return week;
    }
    
    public void setWeek(String week){
        this.week = week;
    }
    
    public BigDecimal getPlanPq(){
        return planPq;
    }
    
    public void setPlanPq(BigDecimal planPq){
        this.planPq = planPq;
    }
    
    public BigDecimal getActualPq(){
        return actualPq;
    }
    
    public void setActualPq(BigDecimal actualPq){
        this.actualPq = actualPq;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	@Override
	public String toString() {
		return "SmConsPowerView [planId=" + planId + ", consId=" + consId + ", ymd=" + ymd + ", week=" + week
				+ ", planPq=" + planPq + ", actualPq=" + actualPq + ", orgNo=" + orgNo + ", ym=" + ym + ", dayPlanPq="
				+ dayPlanPq + ", dayActualPq=" + dayActualPq + "]";
	}
    
}