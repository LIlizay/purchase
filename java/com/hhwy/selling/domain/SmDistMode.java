package com.hhwy.selling.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmDistMode
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
/**
 * 
 * <b>类 名 称：</b>SmDistMode<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年5月18日 下午1:22:44<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
/**
 * 
 * <b>类 名 称：</b>SmDistMode<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年5月18日 下午1:22:46<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
@Entity(name="SmDistMode")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_dist_mode")
public class SmDistMode extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("合同ID")
    @Column(name="agre_id", nullable=true, length=32) 
    private String agreId;
    
    @PropertyDesc("分成方式")
    @Column(name="divi_code", nullable=true, length=8) 
    private String diviCode;
    
    @PropertyDesc("结算模式")
    @Column(name="settlement_mode", nullable=true, length=8) 
    private String settlementMode;
    
    @PropertyDesc("是否优惠上限")
    @Column(name="discount_upper_flag", nullable=true, length=3) 
    private String discountUpperFlag;
    
    @PropertyDesc("优惠上限金额")
    @Column(name="discount_upper_amt", nullable=true, length=16) 
    private java.math.BigDecimal discountUpperAmt;
    
    @PropertyDesc("是否优惠下限")
    @Column(name="discount_lower_flag", nullable=true, length=3) 
    private String discountLowerFlag;
    
    @PropertyDesc("优惠下限金额")
    @Column(name="discount_lower_amt", nullable=true, length=16) 
    private java.math.BigDecimal discountLowerAmt;
    
    @PropertyDesc("保底协议价差")
    @Column(name="agre_prc", nullable=true, length=8) 
    private java.math.BigDecimal agrePrc;
    
    @PropertyDesc("售方长协分成比例")
    @Column(name="party_b_lc_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyBLcProp;
    
    @PropertyDesc("用户长协分成比例")
    @Column(name="party_a_lc_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyALcProp;
    
    @PropertyDesc("售方竞价分成比例")
    @Column(name="party_b_bid_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyBBidProp;
    
    @PropertyDesc("用户竞价分成比例")
    @Column(name="party_a_bid_prop", nullable=true, length=6) 
    private java.math.BigDecimal partyABidProp;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("收益模式")
    @Column(name="profit_mode", nullable=true, length=8) 
    private String profitMode;
    
    @PropertyDesc("分成电量比例")
    @Column(name="pq_sharing_ratio", nullable=true, length=6) 
    private java.math.BigDecimal pqSharingRatio;
    
    @PropertyDesc("分成基准（01：标杆电价，02：市场均价，03：人工录入）")
    @Column(name="divi_type", nullable=true, length=4) 
    private String diviType;
    
    @PropertyDesc("分成基准值")
    @Column(name="divi_value", nullable=true, length=6) 
    private java.math.BigDecimal diviValue;
    
    @PropertyDesc("代理服务费")
    @Column(name="agent", nullable=true, length=12) 
    private java.math.BigDecimal agent;
    /*				竞价分成方式				*/
    @PropertyDesc("竞价分成方式")
    @Column(name="bid_divi_code", nullable=true, length=8) 
    private String bidDiviCode;
    
    @PropertyDesc("竞价结算模式")
    @Column(name="bid_settlement_mode", nullable=true, length=8) 
    private String bidSettlementMode;
    
    @PropertyDesc("竞价保底协议价差")
    @Column(name="bid_agre_prc", nullable=true, length=8) 
    private java.math.BigDecimal bidAgrePrc;
    
    @PropertyDesc("竞价收益模式")
    @Column(name="bid_profit_mode", nullable=true, length=8) 
    private String bidProfitMode;
    
    @PropertyDesc("竞价分成基准（01：标杆电价，02：市场均价，03：人工录入）")
    @Column(name="bid_divi_type", nullable=true, length=4) 
    private String bidDiviType;
    
    @PropertyDesc("竞价分成基准值")
    @Column(name="bid_divi_value", nullable=true, length=6) 
    private java.math.BigDecimal bidDiviValue;
    
    @PropertyDesc("竞价代理服务费")
    @Column(name="bid_agent", nullable=true, length=12) 
    private java.math.BigDecimal bidAgent;
    
    public String getAgreId(){
        return agreId;
    }
    
    public void setAgreId(String agreId){
        this.agreId = agreId;
    }
    
    public String getDiviCode(){
        return diviCode;
    }
    
    public void setDiviCode(String diviCode){
        this.diviCode = diviCode;
    }
    
    public String getSettlementMode(){
        return settlementMode;
    }
    
    public void setSettlementMode(String settlementMode){
        this.settlementMode = settlementMode;
    }
    
    public String getDiscountUpperFlag(){
        return discountUpperFlag;
    }
    
    public void setDiscountUpperFlag(String discountUpperFlag){
        this.discountUpperFlag = discountUpperFlag;
    }
    
    public java.math.BigDecimal getDiscountUpperAmt(){
        return discountUpperAmt;
    }
    
    public void setDiscountUpperAmt(java.math.BigDecimal discountUpperAmt){
        this.discountUpperAmt = discountUpperAmt;
    }
    
    public String getDiscountLowerFlag(){
        return discountLowerFlag;
    }
    
    public void setDiscountLowerFlag(String discountLowerFlag){
        this.discountLowerFlag = discountLowerFlag;
    }
    
    public java.math.BigDecimal getDiscountLowerAmt(){
        return discountLowerAmt;
    }
    
    public void setDiscountLowerAmt(java.math.BigDecimal discountLowerAmt){
        this.discountLowerAmt = discountLowerAmt;
    }
    
    public java.math.BigDecimal getAgrePrc(){
        return agrePrc;
    }
    
    public void setAgrePrc(java.math.BigDecimal agrePrc){
        this.agrePrc = agrePrc;
    }
    
    public java.math.BigDecimal getPartyBLcProp(){
        return partyBLcProp;
    }
    
    public void setPartyBLcProp(java.math.BigDecimal partyBLcProp){
        this.partyBLcProp = partyBLcProp;
    }
    
    public java.math.BigDecimal getPartyALcProp(){
        return partyALcProp;
    }
    
    public void setPartyALcProp(java.math.BigDecimal partyALcProp){
        this.partyALcProp = partyALcProp;
    }
    
    public java.math.BigDecimal getPartyBBidProp(){
        return partyBBidProp;
    }
    
    public void setPartyBBidProp(java.math.BigDecimal partyBBidProp){
        this.partyBBidProp = partyBBidProp;
    }
    
    public java.math.BigDecimal getPartyABidProp(){
        return partyABidProp;
    }
    
    public void setPartyABidProp(java.math.BigDecimal partyABidProp){
        this.partyABidProp = partyABidProp;
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
    
    public String getProfitMode(){
        return profitMode;
    }
    
    public void setProfitMode(String profitMode){
        this.profitMode = profitMode;
    }
    
    public java.math.BigDecimal getPqSharingRatio(){
        return pqSharingRatio;
    }
    
    public void setPqSharingRatio(java.math.BigDecimal pqSharingRatio){
        this.pqSharingRatio = pqSharingRatio;
    }

	public String getDiviType() {
		return diviType;
	}

	public void setDiviType(String diviType) {
		this.diviType = diviType;
	}

	public java.math.BigDecimal getDiviValue() {
		return diviValue;
	}

	public void setDiviValue(java.math.BigDecimal diviValue) {
		this.diviValue = diviValue;
	}

	public java.math.BigDecimal getAgent() {
		return agent;
	}

	public void setAgent(java.math.BigDecimal agent) {
		this.agent = agent;
	}

	public String getBidDiviCode() {
		return bidDiviCode;
	}

	public void setBidDiviCode(String bidDiviCode) {
		this.bidDiviCode = bidDiviCode;
	}

	public String getBidSettlementMode() {
		return bidSettlementMode;
	}

	public void setBidSettlementMode(String bidSettlementMode) {
		this.bidSettlementMode = bidSettlementMode;
	}

	public java.math.BigDecimal getBidAgrePrc() {
		return bidAgrePrc;
	}

	public void setBidAgrePrc(java.math.BigDecimal bidAgrePrc) {
		this.bidAgrePrc = bidAgrePrc;
	}

	public String getBidProfitMode() {
		return bidProfitMode;
	}

	public void setBidProfitMode(String bidProfitMode) {
		this.bidProfitMode = bidProfitMode;
	}

	public String getBidDiviType() {
		return bidDiviType;
	}

	public void setBidDiviType(String bidDiviType) {
		this.bidDiviType = bidDiviType;
	}

	public java.math.BigDecimal getBidDiviValue() {
		return bidDiviValue;
	}

	public void setBidDiviValue(java.math.BigDecimal bidDiviValue) {
		this.bidDiviValue = bidDiviValue;
	}

	public java.math.BigDecimal getBidAgent() {
		return bidAgent;
	}

	public void setBidAgent(java.math.BigDecimal bidAgent) {
		this.bidAgent = bidAgent;
	}
	
	
    
}