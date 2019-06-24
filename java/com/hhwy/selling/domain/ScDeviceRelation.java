package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * ScDeviceRelation
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="ScDeviceRelation")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_c_device_relation")
public class ScDeviceRelation extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("营销户号")
    @Column(name="market_cons_no", nullable=true, length=128) 
    private String marketConsNo;
    
    @PropertyDesc("设备编号")
    @Column(name="device_id", nullable=true, length=32) 
    private String deviceId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getMarketConsNo(){
        return marketConsNo;
    }
    
    public void setMarketConsNo(String marketConsNo){
        this.marketConsNo = marketConsNo;
    }

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}
    
}