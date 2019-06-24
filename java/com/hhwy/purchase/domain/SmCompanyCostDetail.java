package com.hhwy.purchase.domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

/**
 * SmCompanyCostDetail
 * @author hhwy		江苏结算中的“批发市场购电支出明细”表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmCompanyCostDetail")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_company_cost_detail")
public class SmCompanyCostDetail extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("月度结算方案id")
    @Column(name="scheme_id", nullable=true, length=32) 
    private String schemeId;
    
    @PropertyDesc("合同名称")
    @Column(name="agre_name", nullable=true, length=64) 
    private String agreName;
    
    @PropertyDesc("合同分月电量(兆瓦时）")
    @Column(name="month_pq", nullable=true, length=16) 
    private BigDecimal monthPq;
    
    @PropertyDesc("合同电价（元/兆瓦时）")
    @Column(name="month_prc", nullable=true, length=16) 
    private BigDecimal monthPrc;
    
    @PropertyDesc("结算电量(兆瓦时）")
    @Column(name="delivery_pq", nullable=true, length=16) 
    private BigDecimal deliveryPq;
    
    @PropertyDesc("结算电费（元）")
    @Column(name="delivery_amt", nullable=true, length=16) 
    private BigDecimal deliveryAmt;
    
    @PropertyDesc("序号")
    @Column(name="sort", nullable=true, length=10) 
    private Integer sort;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public String getAgreName(){
        return agreName;
    }
    
    public void setAgreName(String agreName){
        this.agreName = agreName;
    }
    
    public BigDecimal getMonthPq(){
        return monthPq;
    }
    
    public void setMonthPq(BigDecimal monthPq){
        this.monthPq = monthPq;
    }
    
    public BigDecimal getMonthPrc(){
        return monthPrc;
    }
    
    public void setMonthPrc(BigDecimal monthPrc){
        this.monthPrc = monthPrc;
    }
    
    public BigDecimal getDeliveryPq(){
        return deliveryPq;
    }
    
    public void setDeliveryPq(BigDecimal deliveryPq){
        this.deliveryPq = deliveryPq;
    }
    
    public BigDecimal getDeliveryAmt(){
        return deliveryAmt;
    }
    
    public void setDeliveryAmt(BigDecimal deliveryAmt){
        this.deliveryAmt = deliveryAmt;
    }
    
    public Integer getSort(){
        return sort;
    }
    
    public void setSort(Integer sort){
        this.sort = sort;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}