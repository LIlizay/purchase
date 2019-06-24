package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * SwSms
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SwSms")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_w_sms")
public class SwSms extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("接收人userId")
    @Column(name="receive_person", nullable=true, length=32) 
    private String receivePerson;
    
    @PropertyDesc("发送人(name)")
    @Column(name="send_person", nullable=true, length=32) 
    private String sendPerson;
    
    @PropertyDesc("短信内容")
    @Column(name="content", nullable=true, length=1024) 
    private String content;
    
    @PropertyDesc("发送时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="send_date", nullable=true, length=10) 
    private Date sendDate;
    
    @PropertyDesc("接收人手机号码")
    @Column(name="receive_phone", nullable=true, length=16) 
    private String receivePhone;
    
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
    @Column(name="sms_type", nullable=true, length=32) 
    private String smsType;
    
    @PropertyDesc("消息发送状态")
    @Column(name="status", nullable=true, length=8) 
    private String status;
    
    public String getReceivePerson(){
        return receivePerson;
    }
    
    public void setReceivePerson(String receivePerson){
        this.receivePerson = receivePerson;
    }
    
    public String getSendPerson(){
        return sendPerson;
    }
    
    public void setSendPerson(String sendPerson){
        this.sendPerson = sendPerson;
    }
    
    public String getContent(){
        return content;
    }
    
    public void setContent(String content){
        this.content = content;
    }
    
    public Date getSendDate(){
        return sendDate;
    }
    
    public void setSendDate(Date sendDate){
        this.sendDate = sendDate;
    }
    
    public String getReceivePhone(){
        return receivePhone;
    }
    
    public void setReceivePhone(String receivePhone){
        this.receivePhone = receivePhone;
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
    
    public String getSmsType(){
        return smsType;
    }
    
    public void setSmsType(String smsType){
        this.smsType = smsType;
    }
    
    public String getStatus(){
        return status;
    }
    
    public void setStatus(String status){
        this.status = status;
    }
    
}