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
 * PhmPlanConsRela
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
/**
 * 
 * <b>类 名 称：</b>PhmPlanConsRela<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年3月27日 下午4:51:23<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Entity(name="PhmPlanConsRela")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_plan_cons_rela")
public class PhmPlanConsRela extends Domain implements Serializable {

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
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("月度交割电量")
    @Column(name="actual_pq", nullable=true, length=16) 
    private BigDecimal actualPq;
    
    @PropertyDesc("峰时电量")
    @Column(name="peak_pq", nullable=true, length=16) 
    private BigDecimal peakPq;
    
    @PropertyDesc("平时电量")
    @Column(name="plain_pq", nullable=true, length=16) 
    private BigDecimal plainPq;
    
    @PropertyDesc("谷时电量")
    @Column(name="valley_pq", nullable=true, length=16) 
    private BigDecimal valleyPq;
    
    @PropertyDesc("尖峰电量")
    @Column(name="over_peak_pq", nullable=true, length=16) 
    private BigDecimal overPeakPq;
    
	@PropertyDesc("双蓄电量")
    @Column(name="double_pq", nullable=true, length=16) 
    private BigDecimal doublePq;
    
	@PropertyDesc("购电计划制定新增用户标识")
    @Column(name="phPlanAddConsFlag", nullable=true, length=4) 
    private String phPlanAddConsFlag;
    
    public String getConsId(){
        return consId;
    }
    
    public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
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
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public BigDecimal getOverPeakPq() {
		return overPeakPq;
	}

	public void setOverPeakPq(BigDecimal overPeakPq) {
		this.overPeakPq = overPeakPq;
	}

	public BigDecimal getDoublePq() {
		return doublePq;
	}

	public void setDoublePq(BigDecimal doublePq) {
		this.doublePq = doublePq;
	}

	public String getPhPlanAddConsFlag() {
		return phPlanAddConsFlag;
	}

	public void setPhPlanAddConsFlag(String phPlanAddConsFlag) {
		this.phPlanAddConsFlag = phPlanAddConsFlag;
	}
	
	
	
}