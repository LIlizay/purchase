package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
/**
 * PhmReportRemind
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmReportRemind")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_report_remind")
public class PhmReportRemind extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("发送时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="send_date", nullable=false, length=19) 
    private Timestamp sendDate;
    
    @PropertyDesc("提醒时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="remind_time", nullable=true, length=10) 
    private Date remindTime;
    
    @PropertyDesc("发送人")
    @Column(name="sender", nullable=true, length=32) 
    private String sender;
    
    @PropertyDesc("提醒内容")
    @Column(name="content", nullable=true, length=1024) 
    private String content;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    public Timestamp getSendDate(){
        return sendDate;
    }
    
    public void setSendDate(Timestamp sendDate){
        this.sendDate = sendDate;
    }
    
    public Date getRemindTime(){
        return remindTime;
    }
    
    public void setRemindTime(Date remindTime){
        this.remindTime = remindTime;
    }
    
    public String getSender(){
        return sender;
    }
    
    public void setSender(String sender){
        this.sender = sender;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
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
    
}