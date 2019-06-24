package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * SmRuleConfigure
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmRuleConfigure")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_rule_configure")
public class SmRuleConfigure extends Domain implements Serializable {

	/**
	 * serialVersionUID
	 * 
	 * @return the serialVersionUID
	 * @since 1.0.0
	 */
	private static final long serialVersionUID = 1L;
	
    @PropertyDesc("省份标识")
    @Column(name="province_id", nullable=true, length=32) 
    private String provinceId;
    
    @PropertyDesc("规则名称")
    @Column(name="rule_name", nullable=true, length=256) 
    private String ruleName;
    
    @PropertyDesc("状态")
    @Column(name="rule_status", nullable=true, length=8) 
    private String ruleStatus;
    
    @PropertyDesc("交割优先原则")
    @Column(name="priority_flag", nullable=true, length=8) 
    private String priorityFlag;
    
    @PropertyDesc("竞价优先原则")
    @Column(name="bid_priority_flag", nullable=true, length=8) 
    private String bidPriorityFlag;
    
    @PropertyDesc("长协优先原则")
    @Column(name="lc_priority_flag", nullable=true, length=8) 
    private String lcPriorityFlag;
    
    @PropertyDesc("正偏差考核标识")
    @Column(name="upper_check_flag", nullable=true, length=0) 
    private String upperCheckFlag;
    
    @PropertyDesc("正偏差阈值")
    @Column(name="upper_pq_prop", nullable=true, length=6) 
    private java.math.BigDecimal upperPqProp;
    
    @PropertyDesc("正偏差电价类型")
    @Column(name="upper_prc_type", nullable=true, length=8) 
    private String upperPrcType;
    
    @PropertyDesc("正偏差电价")
    @Column(name="upper_prc", nullable=true, length=8) 
    private java.math.BigDecimal upperPrc;
    
    @PropertyDesc("正偏差电价比例值")
    @Column(name="upper_prc_prop", nullable=true, length=6) 
    private java.math.BigDecimal upperPrcProp;
    
    @PropertyDesc("正偏差惩罚电价")
    @Column(name="upper_check_prc_type", nullable=true, length=8) 
    private String upperCheckPrcType;
    
    @PropertyDesc("正偏差惩罚协议价")
    @Column(name="upper_check_prc", nullable=true, length=8) 
    private java.math.BigDecimal upperCheckPrc;
    
    @PropertyDesc("正偏差惩罚电价比列")
    @Column(name="upper_check_prc_prop", nullable=true, length=6) 
    private java.math.BigDecimal upperCheckPrcProp;
    
    @PropertyDesc("负偏差是否考核")
    @Column(name="lower_check_flag", nullable=true, length=0) 
    private String lowerCheckFlag;
    
    @PropertyDesc("负偏差拆分考核标识")
    @Column(name="split_check_flag", nullable=true, length=0) 
    private String splitCheckFlag;
    
    @PropertyDesc("负偏差阈值")
    @Column(name="lower_pq_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerPqProp;
    
    @PropertyDesc("负偏差惩罚电价类型")
    @Column(name="lower_check_prc_type", nullable=true, length=8) 
    private String lowerCheckPrcType;
    
    @PropertyDesc("负偏差惩罚电价")
    @Column(name="lower_check_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerCheckPrc;
    
    @PropertyDesc("负偏差惩罚电价比列")
    @Column(name="lower_check_prc_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerCheckPrcProp;
    
    @PropertyDesc("长协负偏差考核标识")
    @Column(name="lower_check_lc_flag", nullable=true, length=0) 
    private String lowerCheckLcFlag;
    
    @PropertyDesc("长协负偏差阈值")
    @Column(name="lower_check_lc_pq_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerCheckLcPqProp;
    
    @PropertyDesc("长协负偏差惩罚电价类型")
    @Column(name="lower_check_lc_prc_type", nullable=true, length=8) 
    private String lowerCheckLcPrcType;
    
    @PropertyDesc("长协负偏差惩罚电价")
    @Column(name="lower_check_lc_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerCheckLcPrc;
    
    @PropertyDesc("长协负偏差惩罚电价比列")
    @Column(name="lower_check_lc_prc_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerCheckLcPrcProp;
    
    @PropertyDesc("竞价负偏差考核标识")
    @Column(name="lower_check_bid_flag", nullable=true, length=0) 
    private String lowerCheckBidFlag;
    
    @PropertyDesc("竞价负偏差阈值")
    @Column(name="lower_check_bid_pq_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerCheckBidPqProp;
    
    @PropertyDesc("负偏差竞价惩罚电价")
    @Column(name="lower_check_bid_prc_type", nullable=true, length=8) 
    private String lowerCheckBidPrcType;
    
    @PropertyDesc("竞价惩罚协议价")
    @Column(name="lower_check_bid_prc", nullable=true, length=8) 
    private java.math.BigDecimal lowerCheckBidPrc;
    
    @PropertyDesc("负偏差竞价惩罚电价比列")
    @Column(name="lower_check_bid_prc_prop", nullable=true, length=6) 
    private java.math.BigDecimal lowerCheckBidPrcProp;
    
    @PropertyDesc("申报分段")
    @Column(name="decl_seg", nullable=true, length=8) 
    private String declSeg;
    
    @PropertyDesc("最小报量单位")
    @Column(name="min_report_unit", nullable=true, length=8) 
    private String minReportUnit;
    
    @PropertyDesc("售方申报单元")
    @Column(name="sell_decl_unit", nullable=true, length=8) 
    private String sellDeclUnit;
    
    @PropertyDesc("报价方式")
    @Column(name="quot_mode", nullable=true, length=8) 
    private String quotMode;
    
    @PropertyDesc("最小报价单位")
    @Column(name="min_quot_unit", nullable=true, length=8) 
    private String minQuotUnit;
    
    @PropertyDesc("购方申报单元")
    @Column(name="buy_decl_unit", nullable=true, length=8) 
    private String buyDeclUnit;
    
    @PropertyDesc("中标规则算法")
    @Column(name="bid_rule_algo_id", nullable=true, length=32) 
    private String bidRuleAlgoId;
    
    @PropertyDesc("中标算法说明")
    @Column(name="bid_algo_expln", nullable=true, length=2048) 
    private String bidAlgoExpln;
    
    @PropertyDesc("出清规则算法")
    @Column(name="void_rule_algo_id", nullable=true, length=32) 
    private String voidRuleAlgoId;
    
    @PropertyDesc("出清算法说明")
    @Column(name="void_algo_expln", nullable=true, length=2048) 
    private String voidAlgoExpln;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getProvinceId(){
        return provinceId;
    }
    
    public void setProvinceId(String provinceId){
        this.provinceId = provinceId;
    }
    
    public String getRuleName(){
        return ruleName;
    }
    
    public void setRuleName(String ruleName){
        this.ruleName = ruleName;
    }
    
    public String getRuleStatus(){
        return ruleStatus;
    }
    
    public void setRuleStatus(String ruleStatus){
        this.ruleStatus = ruleStatus;
    }
    
    public String getPriorityFlag(){
        return priorityFlag;
    }
    
    public void setPriorityFlag(String priorityFlag){
        this.priorityFlag = priorityFlag;
    }
    
    public String getBidPriorityFlag(){
        return bidPriorityFlag;
    }
    
    public void setBidPriorityFlag(String bidPriorityFlag){
        this.bidPriorityFlag = bidPriorityFlag;
    }
    
    public String getLcPriorityFlag(){
        return lcPriorityFlag;
    }
    
    public void setLcPriorityFlag(String lcPriorityFlag){
        this.lcPriorityFlag = lcPriorityFlag;
    }
    
    public String getUpperCheckFlag(){
        return upperCheckFlag;
    }
    
    public void setUpperCheckFlag(String upperCheckFlag){
        this.upperCheckFlag = upperCheckFlag;
    }
    
    public java.math.BigDecimal getUpperPqProp(){
        return upperPqProp;
    }
    
    public void setUpperPqProp(java.math.BigDecimal upperPqProp){
        this.upperPqProp = upperPqProp;
    }
    
    public String getUpperPrcType(){
        return upperPrcType;
    }
    
    public void setUpperPrcType(String upperPrcType){
        this.upperPrcType = upperPrcType;
    }
    
    public java.math.BigDecimal getUpperPrc(){
        return upperPrc;
    }
    
    public void setUpperPrc(java.math.BigDecimal upperPrc){
        this.upperPrc = upperPrc;
    }
    
    public java.math.BigDecimal getUpperPrcProp(){
        return upperPrcProp;
    }
    
    public void setUpperPrcProp(java.math.BigDecimal upperPrcProp){
        this.upperPrcProp = upperPrcProp;
    }
    
    public String getUpperCheckPrcType(){
        return upperCheckPrcType;
    }
    
    public void setUpperCheckPrcType(String upperCheckPrcType){
        this.upperCheckPrcType = upperCheckPrcType;
    }
    
    public java.math.BigDecimal getUpperCheckPrc(){
        return upperCheckPrc;
    }
    
    public void setUpperCheckPrc(java.math.BigDecimal upperCheckPrc){
        this.upperCheckPrc = upperCheckPrc;
    }
    
    public java.math.BigDecimal getUpperCheckPrcProp(){
        return upperCheckPrcProp;
    }
    
    public void setUpperCheckPrcProp(java.math.BigDecimal upperCheckPrcProp){
        this.upperCheckPrcProp = upperCheckPrcProp;
    }
    
    public String getLowerCheckFlag(){
        return lowerCheckFlag;
    }
    
    public void setLowerCheckFlag(String lowerCheckFlag){
        this.lowerCheckFlag = lowerCheckFlag;
    }
    
    public String getSplitCheckFlag(){
        return splitCheckFlag;
    }
    
    public void setSplitCheckFlag(String splitCheckFlag){
        this.splitCheckFlag = splitCheckFlag;
    }
    
    public java.math.BigDecimal getLowerPqProp(){
        return lowerPqProp;
    }
    
    public void setLowerPqProp(java.math.BigDecimal lowerPqProp){
        this.lowerPqProp = lowerPqProp;
    }
    
    public String getLowerCheckPrcType(){
        return lowerCheckPrcType;
    }
    
    public void setLowerCheckPrcType(String lowerCheckPrcType){
        this.lowerCheckPrcType = lowerCheckPrcType;
    }
    
    public java.math.BigDecimal getLowerCheckPrc(){
        return lowerCheckPrc;
    }
    
    public void setLowerCheckPrc(java.math.BigDecimal lowerCheckPrc){
        this.lowerCheckPrc = lowerCheckPrc;
    }
    
    public java.math.BigDecimal getLowerCheckPrcProp(){
        return lowerCheckPrcProp;
    }
    
    public void setLowerCheckPrcProp(java.math.BigDecimal lowerCheckPrcProp){
        this.lowerCheckPrcProp = lowerCheckPrcProp;
    }
    
    public String getLowerCheckLcFlag(){
        return lowerCheckLcFlag;
    }
    
    public void setLowerCheckLcFlag(String lowerCheckLcFlag){
        this.lowerCheckLcFlag = lowerCheckLcFlag;
    }
    
    public java.math.BigDecimal getLowerCheckLcPqProp(){
        return lowerCheckLcPqProp;
    }
    
    public void setLowerCheckLcPqProp(java.math.BigDecimal lowerCheckLcPqProp){
        this.lowerCheckLcPqProp = lowerCheckLcPqProp;
    }
    
    public String getLowerCheckLcPrcType(){
        return lowerCheckLcPrcType;
    }
    
    public void setLowerCheckLcPrcType(String lowerCheckLcPrcType){
        this.lowerCheckLcPrcType = lowerCheckLcPrcType;
    }
    
    public java.math.BigDecimal getLowerCheckLcPrc(){
        return lowerCheckLcPrc;
    }
    
    public void setLowerCheckLcPrc(java.math.BigDecimal lowerCheckLcPrc){
        this.lowerCheckLcPrc = lowerCheckLcPrc;
    }
    
    public java.math.BigDecimal getLowerCheckLcPrcProp(){
        return lowerCheckLcPrcProp;
    }
    
    public void setLowerCheckLcPrcProp(java.math.BigDecimal lowerCheckLcPrcProp){
        this.lowerCheckLcPrcProp = lowerCheckLcPrcProp;
    }
    
    public String getLowerCheckBidFlag(){
        return lowerCheckBidFlag;
    }
    
    public void setLowerCheckBidFlag(String lowerCheckBidFlag){
        this.lowerCheckBidFlag = lowerCheckBidFlag;
    }
    
    public java.math.BigDecimal getLowerCheckBidPqProp(){
        return lowerCheckBidPqProp;
    }
    
    public void setLowerCheckBidPqProp(java.math.BigDecimal lowerCheckBidPqProp){
        this.lowerCheckBidPqProp = lowerCheckBidPqProp;
    }
    
    public String getLowerCheckBidPrcType(){
        return lowerCheckBidPrcType;
    }
    
    public void setLowerCheckBidPrcType(String lowerCheckBidPrcType){
        this.lowerCheckBidPrcType = lowerCheckBidPrcType;
    }
    
    public java.math.BigDecimal getLowerCheckBidPrc(){
        return lowerCheckBidPrc;
    }
    
    public void setLowerCheckBidPrc(java.math.BigDecimal lowerCheckBidPrc){
        this.lowerCheckBidPrc = lowerCheckBidPrc;
    }
    
    public java.math.BigDecimal getLowerCheckBidPrcProp(){
        return lowerCheckBidPrcProp;
    }
    
    public void setLowerCheckBidPrcProp(java.math.BigDecimal lowerCheckBidPrcProp){
        this.lowerCheckBidPrcProp = lowerCheckBidPrcProp;
    }
    
    public String getDeclSeg(){
        return declSeg;
    }
    
    public void setDeclSeg(String declSeg){
        this.declSeg = declSeg;
    }
    
    public String getMinReportUnit(){
        return minReportUnit;
    }
    
    public void setMinReportUnit(String minReportUnit){
        this.minReportUnit = minReportUnit;
    }
    
    public String getSellDeclUnit(){
        return sellDeclUnit;
    }
    
    public void setSellDeclUnit(String sellDeclUnit){
        this.sellDeclUnit = sellDeclUnit;
    }
    
    public String getQuotMode(){
        return quotMode;
    }
    
    public void setQuotMode(String quotMode){
        this.quotMode = quotMode;
    }
    
    public String getMinQuotUnit(){
        return minQuotUnit;
    }
    
    public void setMinQuotUnit(String minQuotUnit){
        this.minQuotUnit = minQuotUnit;
    }
    
    public String getBuyDeclUnit(){
        return buyDeclUnit;
    }
    
    public void setBuyDeclUnit(String buyDeclUnit){
        this.buyDeclUnit = buyDeclUnit;
    }
    
    public String getBidRuleAlgoId(){
        return bidRuleAlgoId;
    }
    
    public void setBidRuleAlgoId(String bidRuleAlgoId){
        this.bidRuleAlgoId = bidRuleAlgoId;
    }
    
    public String getBidAlgoExpln(){
        return bidAlgoExpln;
    }
    
    public void setBidAlgoExpln(String bidAlgoExpln){
        this.bidAlgoExpln = bidAlgoExpln;
    }
    
    public String getVoidRuleAlgoId(){
        return voidRuleAlgoId;
    }
    
    public void setVoidRuleAlgoId(String voidRuleAlgoId){
        this.voidRuleAlgoId = voidRuleAlgoId;
    }
    
    public String getVoidAlgoExpln(){
        return voidAlgoExpln;
    }
    
    public void setVoidAlgoExpln(String voidAlgoExpln){
        this.voidAlgoExpln = voidAlgoExpln;
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