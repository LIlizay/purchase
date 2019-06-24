package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SwMessage
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SellingSwMessage")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_message")
public class SellingSwMessage extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("消息标题")
    @Column(name="title", nullable=true, length=256) 
    private String title;
    
    @PropertyDesc("消息内容")
    @Column(name="content", nullable=true, length=1024) 
    private String content;
    
    @PropertyDesc("消息发送人(name)")
    @Column(name="send_person", nullable=true, length=32) 
    private String sendPerson;
    
    @PropertyDesc("消息接收人标识(userId)")
    @Column(name="receive_person", nullable=true, length=32) 
    private String receivePerson;
    
    @PropertyDesc("消息发送时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="send_date", nullable=true, length=10) 
    private Date sendDate;
    
    @PropertyDesc("消息状态（0未读,1已读）")
    @Column(name="message_status", nullable=true, length=3) 
    private String messageStatus;
    
    @PropertyDesc("档案标识consId")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("计划id")
    @Column(name="plan_id", nullable=true, length=32) 
    private String planId;
    
    @PropertyDesc("消息分类（主表表名）")
    @Column(name="message_type", nullable=true, length=32) 
    private String messageType;
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public String getSendPerson(){
        return sendPerson;
    }
    
    public void setSendPerson(String sendPerson){
        this.sendPerson = sendPerson;
    }
    
    public String getReceivePerson(){
        return receivePerson;
    }
    
    public void setReceivePerson(String receivePerson){
        this.receivePerson = receivePerson;
    }
    
    public Date getSendDate(){
        return sendDate;
    }
    
    public void setSendDate(Date sendDate){
        this.sendDate = sendDate;
    }
    
    public String getMessageStatus(){
        return messageStatus;
    }
    
    public void setMessageStatus(String messageStatus){
        this.messageStatus = messageStatus;
    }
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
    public String getPlanId(){
        return planId;
    }
    
    public void setPlanId(String planId){
        this.planId = planId;
    }
    
    public String getMessageType(){
        return messageType;
    }
    
    public void setMessageType(String messageType){
        this.messageType = messageType;
    }
    
}