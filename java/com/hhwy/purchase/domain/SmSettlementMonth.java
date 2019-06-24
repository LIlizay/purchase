package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

/**
 * SmSettlementMonth
 * @author hhwy			月度结算主表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmSettlementMonth")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_settlement_month")
public class SmSettlementMonth extends Domain implements Serializable {

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
    
    @PropertyDesc("工业电量(江苏专用,在实际用电量录入时存入)")
    @Column(name="industry_pq", nullable=true, length=16) 
    private BigDecimal industryPq;
    
    @PropertyDesc("商业电量(江苏专用,在实际用电量录入时存入)")
    @Column(name="business_pq", nullable=true, length=16) 
    private BigDecimal businessPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    
    
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public BigDecimal getIndustryPq() {
		return industryPq;
	}

	public void setIndustryPq(BigDecimal industryPq) {
		this.industryPq = industryPq;
	}

	public BigDecimal getBusinessPq() {
		return businessPq;
	}

	public void setBusinessPq(BigDecimal businessPq) {
		this.businessPq = businessPq;
	}

	public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}