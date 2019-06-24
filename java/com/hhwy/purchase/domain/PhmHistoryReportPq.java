package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhmHistoryReportPq
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmHistoryReportPq")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_history_report_pq")
public class PhmHistoryReportPq extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("年月")
    @Column(name="ym", nullable=true, length=6) 
    private String ym;
    
    @PropertyDesc("电量占比")
    @Column(name="pq_prop", nullable=true, length=7) 
    private java.math.BigDecimal pqProp;
    
    @PropertyDesc("电量分类")
    @Column(name="pq_type", nullable=true, length=32) 
    private String pqType;
    
    @PropertyDesc("统计分类01电厂02用户")
    @Column(name="statistic_type", nullable=true, length=32) 
    private String statisticType;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public java.math.BigDecimal getPqProp(){
        return pqProp;
    }
    
    public void setPqProp(java.math.BigDecimal pqProp){
        this.pqProp = pqProp;
    }
    
    public String getPqType(){
        return pqType;
    }
    
    public void setPqType(String pqType){
        this.pqType = pqType;
    }
    
    public String getStatisticType(){
        return statisticType;
    }
    
    public void setStatisticType(String statisticType){
        this.statisticType = statisticType;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}