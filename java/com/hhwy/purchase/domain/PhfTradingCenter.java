package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

/**
 * PhfTradingCenter
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhfTradingCenter")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_f_trading_center")
public class PhfTradingCenter extends Domain implements Serializable {

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
    
    @PropertyDesc("供需比")
    @Column(name="supply_demand_ratio", nullable=true, length=16) 
    private String supplyDemandRatio;
    
    @PropertyDesc("总成交电量")
    @Column(name="total_deal_pq", nullable=true, length=18) 
    private java.math.BigDecimal totalDealPq;
    
    @PropertyDesc("成交平均价")
    @Column(name="avg_deal_prc", nullable=true, length=8) 
    private java.math.BigDecimal avgDealPrc;
    
    @PropertyDesc("发电侧参与数量")
    @Column(name="elec_num", nullable=true, length=10) 
    private Integer elecNum;
    
    @PropertyDesc("发电侧成交数量")
    @Column(name="elec_deal_num", nullable=true, length=10) 
    private Integer elecDealNum;
    
    @PropertyDesc("发电侧未成交数量")
    @Column(name="elec_undeal_num", nullable=true, length=10) 
    private Integer elecUndealNum;
    
    @PropertyDesc("发电侧申报最低电价")
    @Column(name="elec_lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal elecLowerPrc;
    
    @PropertyDesc("发电侧申报最高电价")
    @Column(name="elec_upper_prc", nullable=true, length=8) 
    private java.math.BigDecimal elecUpperPrc;
    
    @PropertyDesc("发电侧最高成交价")
    @Column(name="elec_max_prc", nullable=true, length=8) 
    private java.math.BigDecimal elecMaxPrc;
    
    @PropertyDesc("发电侧申报电量")
    @Column(name="elec_report_pq", nullable=true, length=18) 
    private java.math.BigDecimal elecReportPq;
    
    @PropertyDesc("售电公司参与数量")
    @Column(name="comp_num", nullable=true, length=10) 
    private Integer compNum;
    
    @PropertyDesc("售电公司成交数量")
    @Column(name="comp_deal_num", nullable=true, length=10) 
    private Integer compDealNum;
    
    @PropertyDesc("售电公司成交电量")
    @Column(name="comp_undeal_num", nullable=true, length=18) 
    private java.math.BigDecimal compUndealNum;
    
    @PropertyDesc("售电公司成交均价")
    @Column(name="comp_deal_avg_prc", nullable=true, length=8) 
    private java.math.BigDecimal compDealAvgPrc;
    
    @PropertyDesc("大用户参与数量")
    @Column(name="cons_num", nullable=true, length=10) 
    private Integer consNum;
    
    @PropertyDesc("大用户成交数量")
    @Column(name="cons_deal_num", nullable=true, length=10) 
    private Integer consDealNum;
    
    @PropertyDesc("大用户成交电量")
    @Column(name="cons_undeal_num", nullable=true, length=18) 
    private java.math.BigDecimal consUndealNum;
    
    @PropertyDesc("大用户成交均价")
    @Column(name="cons_deal_avg_prc", nullable=true, length=8) 
    private java.math.BigDecimal consDealAvgPrc;
    
    @PropertyDesc("用电侧申报最高电价")
    @Column(name="purc_upper_prc", nullable=true, length=8) 
    private java.math.BigDecimal purcUpperPrc;
    
    @PropertyDesc("用电侧申报最低电价")
    @Column(name="purc_lower_prc", nullable=true, length=8) 
    private java.math.BigDecimal purcLowerPrc;
    
    @PropertyDesc("用电侧最低成交价")
    @Column(name="purc_min_prc", nullable=true, length=8) 
    private java.math.BigDecimal purcMinPrc;
    
    @PropertyDesc("用电侧申报电量")
    @Column(name="purc_report_pq", nullable=true, length=18) 
    private java.math.BigDecimal purcReportPq;
    
    @PropertyDesc("用电侧参与总数")
    @Column(name="purc_num", nullable=true, length=10) 
    private Integer purcNum;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
    	this.ym = ym;
    }
    
    public String getSupplyDemandRatio(){
        return supplyDemandRatio;
    }
    
    public void setSupplyDemandRatio(String supplyDemandRatio){
    	this.supplyDemandRatio = supplyDemandRatio;
    }
    
    public java.math.BigDecimal getTotalDealPq(){
        return totalDealPq;
    }
    
    public void setTotalDealPq(java.math.BigDecimal totalDealPq){
    	this.totalDealPq = totalDealPq;
    }
    
    public java.math.BigDecimal getAvgDealPrc(){
        return avgDealPrc;
    }
    
    public void setAvgDealPrc(java.math.BigDecimal avgDealPrc){
    	this.avgDealPrc = avgDealPrc;
    }
    
    public Integer getElecNum(){
        return elecNum;
    }
    
    public void setElecNum(Integer elecNum){
    	this.elecNum = elecNum;
    }
    
    public Integer getElecDealNum(){
        return elecDealNum;
    }
    
    public void setElecDealNum(Integer elecDealNum){
    	this.elecDealNum = elecDealNum;
    }
    
    public Integer getElecUndealNum(){
        return elecUndealNum;
    }
    
    public void setElecUndealNum(Integer elecUndealNum){
    	this.elecUndealNum = elecUndealNum;
    }
    
    public java.math.BigDecimal getElecLowerPrc(){
        return elecLowerPrc;
    }
    
    public void setElecLowerPrc(java.math.BigDecimal elecLowerPrc){
    	this.elecLowerPrc = elecLowerPrc;
    }
    
    public java.math.BigDecimal getElecUpperPrc(){
        return elecUpperPrc;
    }
    
    public void setElecUpperPrc(java.math.BigDecimal elecUpperPrc){
    	this.elecUpperPrc = elecUpperPrc;
    }
    
    public java.math.BigDecimal getElecMaxPrc(){
        return elecMaxPrc;
    }
    
    public void setElecMaxPrc(java.math.BigDecimal elecMaxPrc){
    	this.elecMaxPrc = elecMaxPrc;
    }
    
    public java.math.BigDecimal getElecReportPq(){
        return elecReportPq;
    }
    
    public void setElecReportPq(java.math.BigDecimal elecReportPq){
    	this.elecReportPq = elecReportPq;
    }
    
    public Integer getCompNum(){
        return compNum;
    }
    
    public void setCompNum(Integer compNum){
    	this.compNum = compNum;
    }
    
    public Integer getCompDealNum(){
        return compDealNum;
    }
    
    public void setCompDealNum(Integer compDealNum){
    	this.compDealNum = compDealNum;
    }
    
    public java.math.BigDecimal getCompUndealNum(){
        return compUndealNum;
    }
    
    public void setCompUndealNum(java.math.BigDecimal compUndealNum){
    	this.compUndealNum = compUndealNum;
    }
    
    public java.math.BigDecimal getCompDealAvgPrc(){
        return compDealAvgPrc;
    }
    
    public void setCompDealAvgPrc(java.math.BigDecimal compDealAvgPrc){
    	this.compDealAvgPrc = compDealAvgPrc;
    }
    
    public Integer getConsNum(){
        return consNum;
    }
    
    public void setConsNum(Integer consNum){
    	this.consNum = consNum;
    }
    
    public Integer getConsDealNum(){
        return consDealNum;
    }
    
    public void setConsDealNum(Integer consDealNum){
    	this.consDealNum = consDealNum;
    }
    
    public java.math.BigDecimal getConsUndealNum(){
        return consUndealNum;
    }
    
    public void setConsUndealNum(java.math.BigDecimal consUndealNum){
    	this.consUndealNum = consUndealNum;
    }
    
    public java.math.BigDecimal getConsDealAvgPrc(){
        return consDealAvgPrc;
    }
    
    public void setConsDealAvgPrc(java.math.BigDecimal consDealAvgPrc){
    	this.consDealAvgPrc = consDealAvgPrc;
    }
    
    public java.math.BigDecimal getPurcUpperPrc(){
        return purcUpperPrc;
    }
    
    public void setPurcUpperPrc(java.math.BigDecimal purcUpperPrc){
    	this.purcUpperPrc = purcUpperPrc;
    }
    
    public java.math.BigDecimal getPurcLowerPrc(){
        return purcLowerPrc;
    }
    
    public void setPurcLowerPrc(java.math.BigDecimal purcLowerPrc){
    	this.purcLowerPrc = purcLowerPrc;
    }
    
    public java.math.BigDecimal getPurcMinPrc(){
        return purcMinPrc;
    }
    
    public void setPurcMinPrc(java.math.BigDecimal purcMinPrc){
    	this.purcMinPrc = purcMinPrc;
    }
    
    public java.math.BigDecimal getPurcReportPq(){
        return purcReportPq;
    }
    
    public void setPurcReportPq(java.math.BigDecimal purcReportPq){
    	this.purcReportPq = purcReportPq;
    }
    
    public Integer getPurcNum(){
        return purcNum;
    }
    
    public void setPurcNum(Integer purcNum){
    	this.purcNum = purcNum;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
    	this.orgNo = orgNo;
    }
    
}