package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;
/**
 * ScMpInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScMpInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_mp_info")
public class ScMpInfo extends Domain implements Serializable {

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
    
    @PropertyDesc("计量点编号")
    @Column(name="mp_no", nullable=true, length=16) 
    private String mpNo;
    
    @PropertyDesc("计量点名称")
    @Column(name="mp_name", nullable=true, length=32) 
    private String mpName;
    
    @PropertyDesc("电能表倍率")
    @Column(name="meter_rate", nullable=true, length=8) 
    private String meterRate;
    
    @PropertyDesc("电能表位数")
    @Column(name="meter_digits", nullable=true, length=16) 
    private String meterDigits;
    
    @PropertyDesc("电能表编号")
    @Column(name="meter_no", nullable=true, length=16) 
    private String meterNo;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("营销户号")
    @Column(name="market_cons_no", nullable=true, length=32) 
    private String marketConsNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getMpNo(){
        return mpNo;
    }
    
    public void setMpNo(String mpNo){
        this.mpNo = mpNo;
    }
    
    public String getMpName(){
        return mpName;
    }
    
    public void setMpName(String mpName){
        this.mpName = mpName;
    }
    
    public String getMeterRate(){
        return meterRate;
    }
    
    public void setMeterRate(String meterRate){
        this.meterRate = meterRate;
    }
    
    public String getMeterDigits() {
		return meterDigits;
	}

	public void setMeterDigits(String meterDigits) {
		this.meterDigits = meterDigits;
	}

	public String getMeterNo(){
        return meterNo;
    }
    
    public void setMeterNo(String meterNo){
        this.meterNo = meterNo;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public String getMarketConsNo() {
		return marketConsNo;
	}

	public void setMarketConsNo(String marketConsNo) {
		this.marketConsNo = marketConsNo;
	}
    
    
}