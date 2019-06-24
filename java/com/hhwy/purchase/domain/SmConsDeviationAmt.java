package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmConsDeviationAmt
 * @author hhwy			月度结算用户偏差惩罚费用信息表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmConsDeviationAmt")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_cons_deviation_amt")
public class SmConsDeviationAmt extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("用户Id")
    @Column(name="cons_id", nullable=true, length=32) 
    private String consId;
    
    @PropertyDesc("方案Id")
    @Column(name="scheme_id", nullable=true, length=32) 
    private String schemeId;
    
    @PropertyDesc("偏差惩罚类型（偏差是否考核，1：考核，0：不考核）")
    @Column(name="punish_type", nullable=true, length=8) 
    private String punishType;
    
    @PropertyDesc("负偏差域值（少用考核比例）")
    @Column(name="lower_threshold", nullable=true, length=6) 
    private java.math.BigDecimal lowerThreshold;
    
    @PropertyDesc("负偏差惩罚协议价（少用考核价格，元/兆瓦时）")
    @Column(name="lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerPrc;
    
    @PropertyDesc("负偏差考核金额（少用考核金额，元）")
    @Column(name="lower_dev_amt", nullable=true, length=16) 
    private java.math.BigDecimal lowerDevAmt;
    
    @PropertyDesc("正偏差1段域值（多用1段考核比例）")
    @Column(name="upper_threshold1", nullable=true, length=6) 
    private java.math.BigDecimal upperThreshold1;
    
    @PropertyDesc("正偏差1段惩罚协议价（1段考核价格，元/兆瓦时）")
    @Column(name="upper_prc1", nullable=true, length=8) 
    private java.math.BigDecimal upperPrc1;
    
    @PropertyDesc("正偏差1段考核金额（多用1段考核金额，元）")
    @Column(name="upper_dev_amt1", nullable=true, length=16) 
    private java.math.BigDecimal upperDevAmt1;
    
    @PropertyDesc("正偏差2段域值（多用2段考核比例）")
    @Column(name="upper_threshold2", nullable=true, length=6) 
    private java.math.BigDecimal upperThreshold2;
    
    @PropertyDesc("正偏差2段惩罚协议价（2段考核价格，元/兆瓦时）")
    @Column(name="upper_prc2", nullable=true, length=8) 
    private java.math.BigDecimal upperPrc2;
    
    @PropertyDesc("正偏差2段考核金额（多用2段考核金额，元）")
    @Column(name="upper_dev_amt2", nullable=true, length=16) 
    private java.math.BigDecimal upperDevAmt2;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public String getPunishType(){
        return punishType;
    }
    
    public void setPunishType(String punishType){
        this.punishType = punishType;
    }
    
    public java.math.BigDecimal getLowerThreshold(){
        return lowerThreshold;
    }
    
    public void setLowerThreshold(java.math.BigDecimal lowerThreshold){
        this.lowerThreshold = lowerThreshold;
    }
    
    public java.math.BigDecimal getLowerPrc(){
        return lowerPrc;
    }
    
    public void setLowerPrc(java.math.BigDecimal lowerPrc){
        this.lowerPrc = lowerPrc;
    }
    
    public java.math.BigDecimal getLowerDevAmt(){
        return lowerDevAmt;
    }
    
    public void setLowerDevAmt(java.math.BigDecimal lowerDevAmt){
        this.lowerDevAmt = lowerDevAmt;
    }
    
    public java.math.BigDecimal getUpperThreshold1(){
        return upperThreshold1;
    }
    
    public void setUpperThreshold1(java.math.BigDecimal upperThreshold1){
        this.upperThreshold1 = upperThreshold1;
    }
    
    public java.math.BigDecimal getUpperPrc1(){
        return upperPrc1;
    }
    
    public void setUpperPrc1(java.math.BigDecimal upperPrc1){
        this.upperPrc1 = upperPrc1;
    }
    
    public java.math.BigDecimal getUpperDevAmt1(){
        return upperDevAmt1;
    }
    
    public void setUpperDevAmt1(java.math.BigDecimal upperDevAmt1){
        this.upperDevAmt1 = upperDevAmt1;
    }
    
    public java.math.BigDecimal getUpperThreshold2(){
        return upperThreshold2;
    }
    
    public void setUpperThreshold2(java.math.BigDecimal upperThreshold2){
        this.upperThreshold2 = upperThreshold2;
    }
    
    public java.math.BigDecimal getUpperPrc2(){
        return upperPrc2;
    }
    
    public void setUpperPrc2(java.math.BigDecimal upperPrc2){
        this.upperPrc2 = upperPrc2;
    }
    
    public java.math.BigDecimal getUpperDevAmt2(){
        return upperDevAmt2;
    }
    
    public void setUpperDevAmt2(java.math.BigDecimal upperDevAmt2){
        this.upperDevAmt2 = upperDevAmt2;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}