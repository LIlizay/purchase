package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmAgreDeviation
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmAgreDeviation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_agre_deviation")
public class PhmAgreDeviation extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
	@PropertyDesc("合同标识")
    @Column(name="agre_id", nullable=true, length=32) 
    private String agreId;
	
    @PropertyDesc("正偏差考核标识")
    @Column(name="upper_check_flag", nullable=true, length=3) 
    private String upperCheckFlag;
    
    @PropertyDesc("正偏差阈值")
    @Column(name="upper_pq_prop", nullable=true, length=5) 
    private java.math.BigDecimal upperPqProp;
    
    @PropertyDesc("正偏差考核基准")
    @Column(name="upper_check_prc_type", nullable=true, length=8) 
    private String upperCheckPrcType;
    
    @PropertyDesc("正偏差考核电价")
    @Column(name="upper_check_prc", nullable=true, length=8) 
    private java.math.BigDecimal upperCheckPrc;
    
    @PropertyDesc("正偏差考核电价比例")
    @Column(name="upper_check_prc_prop", nullable=true, length=5) 
    private java.math.BigDecimal upperCheckPrcProp;
    
    @PropertyDesc("负偏差考核标识")
    @Column(name="lower_check_flag", nullable=true, length=3) 
    private String lowerCheckFlag;
    
    @PropertyDesc("负偏差阈值")
    @Column(name="lower_pq_prop", nullable=true, length=5) 
    private java.math.BigDecimal lowerPqProp;
    
    @PropertyDesc("负偏差考核基准")
    @Column(name="lower_check_prc_type", nullable=true, length=8) 
    private String lowerCheckPrcType;
    
    @PropertyDesc("负偏差考核电价")
    @Column(name="lower_check_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerCheckPrc;
    
    @PropertyDesc("负偏差考核电价比例")
    @Column(name="lower_check_prc_prop", nullable=true, length=5) 
    private java.math.BigDecimal lowerCheckPrcProp;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;

	public String getAgreId() {
		return agreId;
	}

	public void setAgreId(String agreId) {
		this.agreId = agreId;
	}
    
    public String getUpperCheckFlag(){
        return upperCheckFlag;
    }
    
    public void setUpperCheckFlag(String upperCheckFlag){
        this.upperCheckFlag = upperCheckFlag;
    }
    
    public java.math.BigDecimal getUpperPqProp(){
        return upperPqProp;
    }
    
    public void setUpperPqProp(java.math.BigDecimal upperPqProp){
        this.upperPqProp = upperPqProp;
    }
    
    public String getUpperCheckPrcType(){
        return upperCheckPrcType;
    }
    
    public void setUpperCheckPrcType(String upperCheckPrcType){
        this.upperCheckPrcType = upperCheckPrcType;
    }
    
    public java.math.BigDecimal getUpperCheckPrc(){
        return upperCheckPrc;
    }
    
    public void setUpperCheckPrc(java.math.BigDecimal upperCheckPrc){
        this.upperCheckPrc = upperCheckPrc;
    }
    
    public java.math.BigDecimal getUpperCheckPrcProp(){
        return upperCheckPrcProp;
    }
    
    public void setUpperCheckPrcProp(java.math.BigDecimal upperCheckPrcProp){
        this.upperCheckPrcProp = upperCheckPrcProp;
    }
    
    public String getLowerCheckFlag(){
        return lowerCheckFlag;
    }
    
    public void setLowerCheckFlag(String lowerCheckFlag){
        this.lowerCheckFlag = lowerCheckFlag;
    }
    
    public java.math.BigDecimal getLowerPqProp(){
        return lowerPqProp;
    }
    
    public void setLowerPqProp(java.math.BigDecimal lowerPqProp){
        this.lowerPqProp = lowerPqProp;
    }
    
    public String getLowerCheckPrcType(){
        return lowerCheckPrcType;
    }
    
    public void setLowerCheckPrcType(String lowerCheckPrcType){
        this.lowerCheckPrcType = lowerCheckPrcType;
    }
    
    public java.math.BigDecimal getLowerCheckPrc(){
        return lowerCheckPrc;
    }
    
    public void setLowerCheckPrc(java.math.BigDecimal lowerCheckPrc){
        this.lowerCheckPrc = lowerCheckPrc;
    }
    
    public java.math.BigDecimal getLowerCheckPrcProp(){
        return lowerCheckPrcProp;
    }
    
    public void setLowerCheckPrcProp(java.math.BigDecimal lowerCheckPrcProp){
        this.lowerCheckPrcProp = lowerCheckPrcProp;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}