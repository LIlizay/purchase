package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SsAgreScheme
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SsAgreScheme")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_s_agre_scheme")
public class SsAgreScheme extends Domain implements Serializable {

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
    
    @PropertyDesc("用户名称")
    @Column(name="cons_name", nullable=true, length=256) 
    private String consName;
    
    @PropertyDesc("方案名称")
    @Column(name="scheme_name", nullable=true, length=128) 
    private String schemeName;
    
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
    
    @PropertyDesc("收益模式")
    @Column(name="profit_mode", nullable=true, length=8) 
    private String profitMode;
    
    @PropertyDesc("分成电量比例")
    @Column(name="pq_sharing_ratio", nullable=true, length=6) 
    private java.math.BigDecimal pqSharingRatio;
    
    @PropertyDesc("正偏差惩罚类型")
    @Column(name="punish_type_upper", nullable=true, length=8) 
    private String punishTypeUpper;
    
    @PropertyDesc("负偏差惩罚类型")
    @Column(name="punish_type_lower", nullable=true, length=8) 
    private String punishTypeLower;
    
    @PropertyDesc("正偏差域值")
    @Column(name="punish_upper_threshold", nullable=true, length=6) 
    private java.math.BigDecimal punishUpperThreshold;
    
    @PropertyDesc("负偏差域值")
    @Column(name="punish_lower_threshold", nullable=true, length=6) 
    private java.math.BigDecimal punishLowerThreshold;
    
    @PropertyDesc("正偏差惩罚电价基准")
    @Column(name="punish_upper_type", nullable=true, length=8) 
    private String punishUpperType;
    
    @PropertyDesc("负偏差惩罚电价基准")
    @Column(name="punish_lower_type", nullable=true, length=8) 
    private String punishLowerType;
    
    @PropertyDesc("正偏差惩罚比例值")
    @Column(name="punish_upper_prop", nullable=true, length=6) 
    private java.math.BigDecimal punishUpperProp;
    
    @PropertyDesc("负偏差惩罚比例值")
    @Column(name="punish_lower_prop", nullable=true, length=6) 
    private java.math.BigDecimal punishLowerProp;
    
    @PropertyDesc("正偏差惩罚协议价")
    @Column(name="upper_prc", nullable=true, length=8) 
    private java.math.BigDecimal upperPrc;
    
    @PropertyDesc("负偏差惩罚协议价")
    @Column(name="lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerPrc;
    
    @PropertyDesc("惩罚附加项(惩罚时额外附加条件是否生效)")
    @Column(name="punish_flag", nullable=true, length=3) 
    private String punishFlag;
    
    @PropertyDesc("是否赔偿")
    @Column(name="compensate_flag", nullable=false, length=3) 
    private String compensateFlag;
    
    @PropertyDesc("赔偿域值")
    @Column(name="compensate_threshold", nullable=true, length=6) 
    private java.math.BigDecimal compensateThreshold;
    
    @PropertyDesc("赔偿基准")
    @Column(name="compensate_type", nullable=true, length=8) 
    private String compensateType;
    
    @PropertyDesc("赔偿协议价")
    @Column(name="compensate_prc", nullable=true, length=8) 
    private java.math.BigDecimal compensatePrc;
    
    @PropertyDesc("赔偿比例值")
    @Column(name="compensate_prop", nullable=true, length=6) 
    private java.math.BigDecimal compensateProp;
    
    @PropertyDesc("申报电量")
    @Column(name="report_pq", nullable=true, length=16) 
    private java.math.BigDecimal reportPq;
    
    @PropertyDesc("分配长协电量")
    @Column(name="lc_pq", nullable=true, length=16) 
    private java.math.BigDecimal lcPq;
    
    @PropertyDesc("长协价差")
    @Column(name="lc_prc", nullable=true, length=8) 
    private java.math.BigDecimal lcPrc;
    
    @PropertyDesc("竞价中标电量")
    @Column(name="bid_pq", nullable=true, length=16) 
    private java.math.BigDecimal bidPq;
    
    @PropertyDesc("竞价价差")
    @Column(name="bid_prc", nullable=true, length=8) 
    private java.math.BigDecimal bidPrc;
    
    @PropertyDesc("交割电量")
    @Column(name="del_pq", nullable=true, length=16) 
    private java.math.BigDecimal delPq;
    
    @PropertyDesc("售电公司购电收益")
    @Column(name="trans_total_pro", nullable=true, length=16) 
    private java.math.BigDecimal transTotalPro;
    
    @PropertyDesc("用户返还电费")
    @Column(name="cons_pro", nullable=true, length=16) 
    private java.math.BigDecimal consPro;
    
    @PropertyDesc("用户考核电费")
    @Column(name="cons_checked_pro", nullable=true, length=16) 
    private java.math.BigDecimal consCheckedPro;
    
    @PropertyDesc("售电公司代理收益")
    @Column(name="sell_total_pro", nullable=true, length=16) 
    private java.math.BigDecimal sellTotalPro;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("售电公司赔偿金额")
    @Column(name="compensate_amt", nullable=true, length=16) 
    private java.math.BigDecimal compensateAmt;
    
    @PropertyDesc("售电公司承担考核(交易中心考核)")
    @Column(name="comp_checked_pro", nullable=true, length=16) 
    private java.math.BigDecimal compCheckedPro;
    
    
    public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getSchemeName(){
        return schemeName;
    }
    
    public void setSchemeName(String schemeName){
        this.schemeName = schemeName;
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
    
    public String getPunishTypeUpper() {
		return punishTypeUpper;
	}

	public void setPunishTypeUpper(String punishTypeUpper) {
		this.punishTypeUpper = punishTypeUpper;
	}

	public String getPunishTypeLower() {
		return punishTypeLower;
	}

	public void setPunishTypeLower(String punishTypeLower) {
		this.punishTypeLower = punishTypeLower;
	}

	public java.math.BigDecimal getPunishUpperThreshold(){
        return punishUpperThreshold;
    }
    
    public void setPunishUpperThreshold(java.math.BigDecimal punishUpperThreshold){
        this.punishUpperThreshold = punishUpperThreshold;
    }
    
    public java.math.BigDecimal getPunishLowerThreshold(){
        return punishLowerThreshold;
    }
    
    public void setPunishLowerThreshold(java.math.BigDecimal punishLowerThreshold){
        this.punishLowerThreshold = punishLowerThreshold;
    }
    
    public String getPunishUpperType(){
        return punishUpperType;
    }
    
    public void setPunishUpperType(String punishUpperType){
        this.punishUpperType = punishUpperType;
    }
    
    public String getPunishLowerType(){
        return punishLowerType;
    }
    
    public void setPunishLowerType(String punishLowerType){
        this.punishLowerType = punishLowerType;
    }
    
    public java.math.BigDecimal getPunishUpperProp(){
        return punishUpperProp;
    }
    
    public void setPunishUpperProp(java.math.BigDecimal punishUpperProp){
        this.punishUpperProp = punishUpperProp;
    }
    
    public java.math.BigDecimal getPunishLowerProp(){
        return punishLowerProp;
    }
    
    public void setPunishLowerProp(java.math.BigDecimal punishLowerProp){
        this.punishLowerProp = punishLowerProp;
    }
    
    public java.math.BigDecimal getUpperPrc(){
        return upperPrc;
    }
    
    public void setUpperPrc(java.math.BigDecimal upperPrc){
        this.upperPrc = upperPrc;
    }
    
    public java.math.BigDecimal getLowerPrc(){
        return lowerPrc;
    }
    
    public void setLowerPrc(java.math.BigDecimal lowerPrc){
        this.lowerPrc = lowerPrc;
    }
    
    public String getPunishFlag(){
        return punishFlag;
    }
    
    public void setPunishFlag(String punishFlag){
        this.punishFlag = punishFlag;
    }
    
    public String getCompensateFlag(){
        return compensateFlag;
    }
    
    public void setCompensateFlag(String compensateFlag){
        this.compensateFlag = compensateFlag;
    }
    
    public java.math.BigDecimal getCompensateThreshold(){
        return compensateThreshold;
    }
    
    public void setCompensateThreshold(java.math.BigDecimal compensateThreshold){
        this.compensateThreshold = compensateThreshold;
    }
    
    public String getCompensateType(){
        return compensateType;
    }
    
    public void setCompensateType(String compensateType){
        this.compensateType = compensateType;
    }
    
    public java.math.BigDecimal getCompensatePrc(){
        return compensatePrc;
    }
    
    public void setCompensatePrc(java.math.BigDecimal compensatePrc){
        this.compensatePrc = compensatePrc;
    }
    
    public java.math.BigDecimal getCompensateProp(){
        return compensateProp;
    }
    
    public void setCompensateProp(java.math.BigDecimal compensateProp){
        this.compensateProp = compensateProp;
    }
    
    public java.math.BigDecimal getReportPq(){
        return reportPq;
    }
    
    public void setReportPq(java.math.BigDecimal reportPq){
        this.reportPq = reportPq;
    }
    
    public java.math.BigDecimal getLcPq(){
        return lcPq;
    }
    
    public void setLcPq(java.math.BigDecimal lcPq){
        this.lcPq = lcPq;
    }
    
    public java.math.BigDecimal getLcPrc(){
        return lcPrc;
    }
    
    public void setLcPrc(java.math.BigDecimal lcPrc){
        this.lcPrc = lcPrc;
    }
    
    public java.math.BigDecimal getBidPq(){
        return bidPq;
    }
    
    public void setBidPq(java.math.BigDecimal bidPq){
        this.bidPq = bidPq;
    }
    
    public java.math.BigDecimal getBidPrc(){
        return bidPrc;
    }
    
    public void setBidPrc(java.math.BigDecimal bidPrc){
        this.bidPrc = bidPrc;
    }
    
    public java.math.BigDecimal getDelPq(){
        return delPq;
    }
    
    public void setDelPq(java.math.BigDecimal delPq){
        this.delPq = delPq;
    }
    
    public java.math.BigDecimal getTransTotalPro(){
        return transTotalPro;
    }
    
    public void setTransTotalPro(java.math.BigDecimal transTotalPro){
        this.transTotalPro = transTotalPro;
    }
    
    public java.math.BigDecimal getConsPro(){
        return consPro;
    }
    
    public void setConsPro(java.math.BigDecimal consPro){
        this.consPro = consPro;
    }
    
    public java.math.BigDecimal getConsCheckedPro(){
        return consCheckedPro;
    }
    
    public void setConsCheckedPro(java.math.BigDecimal consCheckedPro){
        this.consCheckedPro = consCheckedPro;
    }
    
    public java.math.BigDecimal getSellTotalPro(){
        return sellTotalPro;
    }
    
    public void setSellTotalPro(java.math.BigDecimal sellTotalPro){
        this.sellTotalPro = sellTotalPro;
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
    
    public java.math.BigDecimal getCompensateAmt(){
        return compensateAmt;
    }
    
    public void setCompensateAmt(java.math.BigDecimal compensateAmt){
        this.compensateAmt = compensateAmt;
    }
    
    public java.math.BigDecimal getCompCheckedPro(){
        return compCheckedPro;
    }
    
    public void setCompCheckedPro(java.math.BigDecimal compCheckedPro){
        this.compCheckedPro = compCheckedPro;
    }
    
}