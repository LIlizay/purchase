package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SmDeviationInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmDeviationInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_deviation_info")
public class SmDeviationInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户实体id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("预警日期")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="warning_date", nullable=true, length=19) 
    private Timestamp warningDate;
    
    @PropertyDesc("预警类型")
    @Column(name="warning_type", nullable=true, length=8) 
    private String warningType;
    
    @PropertyDesc("客户状态：01、未读，02、已读")
    @Column(name="status", nullable=true, length=8) 
    private String status;
    
    @PropertyDesc("预警级别")
    @Column(name="warning_grade", nullable=true, length=8) 
    private String warningGrade;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("售电公司工作人员状态：'01'未读,'02'已读")
    @Column(name="comp_status", nullable=true, length=8) 
    private String compStatus;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public Timestamp getWarningDate(){
        return warningDate;
    }
    
    public void setWarningDate(Timestamp warningDate){
        this.warningDate = warningDate;
    }
    
    public String getWarningType(){
        return warningType;
    }
    
    public void setWarningType(String warningType){
        this.warningType = warningType;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
    public String getWarningGrade(){
        return warningGrade;
    }
    
    public void setWarningGrade(String warningGrade){
        this.warningGrade = warningGrade;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getCompStatus(){
        return compStatus;
    }
    
    public void setCompStatus(String compStatus){
        this.compStatus = compStatus;
    }
    
}