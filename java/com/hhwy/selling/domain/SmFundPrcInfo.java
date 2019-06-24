package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmFundPrcInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmFundPrcInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_fund_prc_info")
public class SmFundPrcInfo extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("电镀电价实体id")
    @Column(name="prc_id", nullable=true, length=32) 
    private String prcId;
    
    @PropertyDesc("电价码")
    @Column(name="prc_code", nullable=true, length=16) 
    private String prcCode;
    
    @PropertyDesc("用电类别")
    @Column(name="elec_type", nullable=true, length=8) 
    private String elecType;
    
    @PropertyDesc("销售电价")
    @Column(name="sell_prc", nullable=true, length=8) 
    private java.math.BigDecimal sellPrc;
    
    @PropertyDesc("状态")
    @Column(name="state", nullable=true, length=8) 
    private String state;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getPrcId(){
        return prcId;
    }
    
    public void setPrcId(String prcId){
        this.prcId = prcId;
    }
    
    public String getPrcCode(){
        return prcCode;
    }
    
    public void setPrcCode(String prcCode){
        this.prcCode = prcCode;
    }
    
    public String getElecType(){
        return elecType;
    }
    
    public void setElecType(String elecType){
        this.elecType = elecType;
    }
    
    public java.math.BigDecimal getSellPrc(){
        return sellPrc;
    }
    
    public void setSellPrc(java.math.BigDecimal sellPrc){
        this.sellPrc = sellPrc;
    }
    
    public String getState(){
        return state;
    }
    
    public void setState(String state){
        this.state = state;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}