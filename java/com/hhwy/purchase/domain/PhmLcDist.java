package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmLcDist
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmLcDist")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_lc_dist")
public class PhmLcDist extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("方案id")
    @Column(name="scheme_id", nullable=false, length=32) 
    private String schemeId;
    
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("用户月度合同电量")
    @Column(name="agre_pq", nullable=true, length=18) 
    private java.math.BigDecimal agrePq;
    
    @PropertyDesc("月度长协手动分解电量")
    @Column(name="edit_lc_pq", nullable=true, length=18) 
    private java.math.BigDecimal editLcPq;
    
    @PropertyDesc("月度长协电量")
    @Column(name="lc_pq", nullable=true, length=18) 
    private java.math.BigDecimal lcPq;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getAgrePq(){
        return agrePq;
    }
    
    public void setAgrePq(java.math.BigDecimal agrePq){
        this.agrePq = agrePq;
    }
    
    public java.math.BigDecimal getEditLcPq(){
        return editLcPq;
    }
    
    public void setEditLcPq(java.math.BigDecimal editLcPq){
        this.editLcPq = editLcPq;
    }
    
    public java.math.BigDecimal getLcPq(){
        return lcPq;
    }
    
    public void setLcPq(java.math.BigDecimal lcPq){
        this.lcPq = lcPq;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}