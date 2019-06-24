package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfWorkCondition
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfWorkCondition")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_work_condition")
public class PhfWorkCondition extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
	@PropertyDesc("年月日")
    @Column(name="ymd", nullable=true, length=8) 
    private String ymd;
	 
    @PropertyDesc("温度(c)")
    @Column(name="temperature", nullable=true, length=5) 
    private java.math.BigDecimal temperature;
    
    @PropertyDesc("湿度(%rh)")
    @Column(name="humidity", nullable=true, length=5) 
    private java.math.BigDecimal humidity;
    
    @PropertyDesc("是否节假日")
    @Column(name="holiday_flag", nullable=true, length=3) 
    private String holidayFlag;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public java.math.BigDecimal getTemperature(){
        return temperature;
    }
    
    public void setTemperature(java.math.BigDecimal temperature){
    	this.temperature = temperature;
    }
    
    public java.math.BigDecimal getHumidity(){
        return humidity;
    }
    
    public void setHumidity(java.math.BigDecimal humidity){
    	this.humidity = humidity;
    }
    
    public String getHolidayFlag(){
        return holidayFlag;
    }
    
    public void setHolidayFlag(String holidayFlag){
	    if("".equals(holidayFlag)){
	        this.holidayFlag = null;
	    }else{
	    	this.holidayFlag = holidayFlag;
	    }
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
    	this.orgNo = orgNo;
    }

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
}