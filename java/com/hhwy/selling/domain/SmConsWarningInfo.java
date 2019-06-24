package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmConsWarningInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmConsWarningInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_cons_warning_info")
public class SmConsWarningInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户预警规则标识")
    @Column(name="rule_id", nullable=true, length=32) 
    private String ruleId;
    
    @PropertyDesc("区间下限比例")
    @Column(name="min_prop", nullable=true, length=6) 
    private java.math.BigDecimal minProp;
    
    @PropertyDesc("区间上限比例")
    @Column(name="max_prop", nullable=true, length=6) 
    private java.math.BigDecimal maxProp;
    
    @PropertyDesc("预警类型")
    @Column(name="warn_prompt", nullable=true, length=8) 
    private String warnPrompt;
    
    @PropertyDesc("预警级别")
    @Column(name="warn_type", nullable=true, length=8) 
    private String warnType;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getRuleId(){
        return ruleId;
    }
    
    public void setRuleId(String ruleId){
        this.ruleId = ruleId;
    }
    
    public java.math.BigDecimal getMinProp(){
        return minProp;
    }
    
    public void setMinProp(java.math.BigDecimal minProp){
        this.minProp = minProp;
    }
    
    public java.math.BigDecimal getMaxProp(){
        return maxProp;
    }
    
    public void setMaxProp(java.math.BigDecimal maxProp){
        this.maxProp = maxProp;
    }
    
    public String getWarnPrompt(){
        return warnPrompt;
    }
    
    public void setWarnPrompt(String warnPrompt){
        this.warnPrompt = warnPrompt;
    }
    
    public String getWarnType(){
        return warnType;
    }
    
    public void setWarnType(String warnType){
        this.warnType = warnType;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}