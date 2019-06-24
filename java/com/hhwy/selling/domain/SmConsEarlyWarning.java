package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmConsEarlyWarning
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmConsEarlyWarning")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_cons_early_warning")
public class SmConsEarlyWarning extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户名称")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("规则名称")
    @Column(name="name", nullable=true, length=32) 
    private String name;
    
    @PropertyDesc("规则类型标识（是否默认规则）")
    @Column(name="rule_flag", nullable=true, length=8) 
    private String ruleFlag;
    
    @PropertyDesc("预警频率")
    @Column(name="frequency", nullable=true, length=8) 
    private String frequency;
    
    @PropertyDesc("预警规则说明")
    @Column(name="rule_explain", nullable=true, length=256) 
    private String ruleExplain;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getRuleFlag() {
		return ruleFlag;
	}

	public void setRuleFlag(String ruleFlag) {
		this.ruleFlag = ruleFlag;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getFrequency(){
        return frequency;
    }
    
    public void setFrequency(String frequency){
        this.frequency = frequency;
    }
    
    public String getRuleExplain(){
        return ruleExplain;
    }
    
    public void setRuleExplain(String ruleExplain){
        this.ruleExplain = ruleExplain;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}