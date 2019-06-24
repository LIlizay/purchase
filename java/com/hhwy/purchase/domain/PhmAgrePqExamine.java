package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmAgrePqExamine
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmAgrePqExamine")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_agre_pq_examine")
public class PhmAgrePqExamine extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年/年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("是否是年分项")
    @Column(name="subitem_flag", nullable=true, length=8) 
    private String subitemFlag;
    
    @PropertyDesc("负荷预测电量")
    @Column(name="forecast_pq", nullable=true, length=16) 
    private java.math.BigDecimal forecastPq;
    
    @PropertyDesc("月度合同电量(委托电量）")
    @Column(name="agre_pq", nullable=true, length=16) 
    private java.math.BigDecimal agrePq;
    
    @PropertyDesc("附件")
    @Column(name="fileId", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getForecastPq(){
        return forecastPq;
    }
    
    public void setForecastPq(java.math.BigDecimal forecastPq){
        this.forecastPq = forecastPq;
    }
    
    public java.math.BigDecimal getAgrePq(){
        return agrePq;
    }
    
    public void setAgrePq(java.math.BigDecimal agrePq){
        this.agrePq = agrePq;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
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

	public String getSubitemFlag() {
		return subitemFlag;
	}

	public void setSubitemFlag(String subitemFlag) {
		this.subitemFlag = subitemFlag;
	}
    
}