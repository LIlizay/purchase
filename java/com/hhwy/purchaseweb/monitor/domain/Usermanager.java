package com.hhwy.purchaseweb.monitor.domain;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.framework.persistent.entity.Domain;
/**
 * Usermanager
 * @author wangchaochao
 * @date 2017-08-24
 * @version 1.0
 */
public class Usermanager extends Domain implements Serializable {

	private static final long serialVersionUID = -9028743777936147108L;
	
    //
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date dataDate;
    
    //电表编号，和user表关联字段
    private String device;
    
    //总电量
    private float totalElectricQuantity;
    
    //尖峰时段
    private float timeinterval01;
    
    //峰时段
    private float timeinterval02;
    
    //平时段
    private float timeinterval03;
    
    //谷时段
    private float timeinterval04;
    
    //低谷时段
    private float timeinterval05;
    
    //区域编码
    private String areaCode;
    
    //用户名称
    private String custName;
    
    //用户id
    private String consId;
    
    //用户code
    private String clientCode;
    
    //计划电量
    private String planPower;
    
    //完成度
    private String completion;
    
   
    
    
	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public Date getDataDate() {
		return dataDate;
	}

	public void setDataDate(Date dataDate) {
		this.dataDate = dataDate;
	}

	public float getTotalElectricQuantity() {
		return totalElectricQuantity;
	}

	public void setTotalElectricQuantity(float totalElectricQuantity) {
		this.totalElectricQuantity = totalElectricQuantity;
	}

	public float getTimeinterval01() {
		return timeinterval01;
	}
	
	public void setTimeinterval01(float timeinterval01) {
		this.timeinterval01 = timeinterval01;
	}

	public float getTimeinterval02() {
		return timeinterval02;
	}

	public void setTimeinterval02(float timeinterval02) {
		this.timeinterval02 = timeinterval02;
	}

	public float getTimeinterval03() {
		return timeinterval03;
	}

	public void setTimeinterval03(float timeinterval03) {
		this.timeinterval03 = timeinterval03;
	}

	public float getTimeinterval04() {
		return timeinterval04;
	}
	
	public void setTimeinterval04(float timeinterval04) {
		this.timeinterval04 = timeinterval04;
	}

	public float getTimeinterval05() {
		return timeinterval05;
	}

	public void setTimeinterval05(float timeinterval05) {
		this.timeinterval05 = timeinterval05;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getPlanPower() {
		return planPower;
	}

	public void setPlanPower(String planPower) {
		this.planPower = planPower;
	}

	public String getCompletion() {
		return completion;
	}

	public void setCompletion(String completion) {
		this.completion = completion;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}
}