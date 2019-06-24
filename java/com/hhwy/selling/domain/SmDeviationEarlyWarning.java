package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmDeviationEarlyWarning
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmDeviationEarlyWarning")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_deviation_early_warning")
public class SmDeviationEarlyWarning extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("规则名称")
    @Column(name="name", nullable=true, length=32) 
    private String name;
    
    @PropertyDesc("状态")
    @Column(name="status", nullable=true, length=8) 
    private String status;
    
    @PropertyDesc("预警频率")
    @Column(name="frequency", nullable=true, length=8) 
    private String frequency;
    
    @PropertyDesc("参考文件")
    @Column(name="file_id", nullable=true, length=128) 
    private String fileId;
    
    @PropertyDesc("预警规则说明")
    @Column(name="rule_explain", nullable=true, length=256) 
    private String ruleExplain;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getFrequency(){
        return frequency;
    }
    
    public void setFrequency(String frequency){
        this.frequency = frequency;
    }
    
    public String getFileId(){
        return fileId;
    }
    
    public void setFileId(String fileId){
        this.fileId = fileId;
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