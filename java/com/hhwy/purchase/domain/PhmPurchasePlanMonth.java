package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmPurchasePlanMonth
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmPurchasePlanMonth")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_purchase_plan_month")
public class PhmPurchasePlanMonth extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("计划名称")
    @Column(name="plan_name", nullable=true, length=32) 
    private String planName;
    
    @PropertyDesc("制定人")
    @Column(name="setters", nullable=true, length=32) 
    private String setters;
    
    @PropertyDesc("交易周期")
    @Column(name="trade_period", nullable=true, length=8) 
    private String tradePeriod;
    
    @PropertyDesc("交易方式")
    @Column(name="trade_mode", nullable=true, length=8) 
    private String tradeMode;
    
    @PropertyDesc("交易品种")
    @Column(name="trade_variety", nullable=true, length=8) 
    private String tradeVariety;
    
    @PropertyDesc("计划状态")
    @Column(name="plan_status", nullable=true, length=8) 
    private String planStatus;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    
    public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
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

	public String getTradePeriod() {
		return tradePeriod;
	}

	public void setTradePeriod(String tradePeriod) {
		this.tradePeriod = tradePeriod;
	}

	public String getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}

	public String getTradeVariety() {
		return tradeVariety;
	}

	public void setTradeVariety(String tradeVariety) {
		this.tradeVariety = tradeVariety;
	}
    
}