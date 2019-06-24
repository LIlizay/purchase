package com.hhwy.purchaseweb.login.swmessage.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>SellingSwMessageDetail<br/>
 * <b>类 描 述：消息详情类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年9月8日 下午5:43:25<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SellingSwMessageDetail {
	
	//id
	private String id;

	@PropertyDesc("消息标题")
    private String title;
    
    @PropertyDesc("消息内容")
    private String content;
    
    @PropertyDesc("消息发送人(name)")
    private String sendPerson;
    
    @PropertyDesc("消息接收人标识(userId)")
    private String receivePerson;
    
    @PropertyDesc("消息发送时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date sendDate;
    
    @PropertyDesc("消息状态（0未读,1已读）")
    private String messageStatus;
    
    @PropertyDesc("档案标识consId")
    private String consId;
    
    @PropertyDesc("售电公司id")
    private String companyId;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    @PropertyDesc("计划id")
    private String planId;
    
    @PropertyDesc("消息分类（主表表名）")
    private String messageType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendPerson() {
		return sendPerson;
	}

	public void setSendPerson(String sendPerson) {
		this.sendPerson = sendPerson;
	}

	public String getReceivePerson() {
		return receivePerson;
	}

	public void setReceivePerson(String receivePerson) {
		this.receivePerson = receivePerson;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public String getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(String messageStatus) {
		this.messageStatus = messageStatus;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
}
