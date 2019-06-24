package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmAgreMp
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmAgreMp")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_agre_mp")
public class SmAgreMp extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同实体ID")
    @Column(name="agre_id", nullable=true, length=32) 
    private String agreId;
    
    @PropertyDesc("计量点实体ID")
    @Column(name="mp_id", nullable=true, length=32) 
    private String mpId;
    
    @PropertyDesc("代理电量")
    @Column(name="proxy_pq", nullable=true, length=16) 
    private java.math.BigDecimal proxyPq;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getAgreId(){
        return agreId;
    }
    
    public void setAgreId(String agreId){
        this.agreId = agreId;
    }
    
    public String getMpId(){
        return mpId;
    }
    
    public void setMpId(String mpId){
        this.mpId = mpId;
    }
    
    public java.math.BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(java.math.BigDecimal proxyPq){
        this.proxyPq = proxyPq;
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
    
}