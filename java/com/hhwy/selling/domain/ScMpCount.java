package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * ScMpInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScMpCount")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_mp_count")
public class ScMpCount extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("计量点id")
    @Column(name="mp_id", nullable=false, length=32) 
    private String mpId;
    
    @PropertyDesc("抄表示数")
    @Column(name="meter_read_pq", nullable=true, length=15) 
    private double meterReadPq;
    
    @PropertyDesc("抄表时间")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    @Column(name="meter_read_date", nullable=true, length=10) 
    private Date meterReadDate;
    

	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

	public double getMeterReadPq() {
		return meterReadPq;
	}

	public void setMeterReadPq(double meterReadPq) {
		this.meterReadPq = meterReadPq;
	}

	public Date getMeterReadDate() {
		return meterReadDate;
	}

	public void setMeterReadDate(Date meterReadDate) {
		this.meterReadDate = meterReadDate;
	}

}