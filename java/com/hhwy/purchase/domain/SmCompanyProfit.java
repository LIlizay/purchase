package com.hhwy.purchase.domain;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;

/**
 * SmCompanyProfit
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="SmCompanyProfit")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="s_m_company_profit")
public class SmCompanyProfit extends Domain implements Serializable {

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
    
    @PropertyDesc("月度结算方案id")
    @Column(name="scheme_id", nullable=true, length=32) 
    private String schemeId;
    
    @PropertyDesc("月代理总电量")
    @Column(name="proxy_pq", nullable=true, length=16) 
    private BigDecimal proxyPq;
    
    @PropertyDesc("长协分配总量")
    @Column(name="lc_total_pq", nullable=true, length=16) 
    private BigDecimal lcTotalPq;
    
    @PropertyDesc("竞价分配总量")
    @Column(name="bid_total_pq", nullable=true, length=16) 
    private BigDecimal bidTotalPq;
    
    @PropertyDesc("挂牌分配总量")
    @Column(name="listed_total_pq", nullable=true, length=16) 
    private BigDecimal listedTotalPq;
    
    @PropertyDesc("双边加权平均价")
    @Column(name="lc_avg_prc", nullable=true, length=8) 
    private BigDecimal lcAvgPrc;
    
    @PropertyDesc("竞价中标均价（竞价加权平均价）")
    @Column(name="bid_avg_prc", nullable=true, length=8) 
    private BigDecimal bidAvgPrc;
    
    @PropertyDesc("挂牌加权平均价")
    @Column(name="listed_avg_prc", nullable=true, length=8) 
    private BigDecimal listedAvgPrc;
    
    @PropertyDesc("购电总量")
    @Column(name="pur_total_pq", nullable=true, length=16) 
    private BigDecimal purTotalPq;
    
    @PropertyDesc("总交割电量")
    @Column(name="del_total_pq", nullable=true, length=16) 
    private BigDecimal delTotalPq;
    
    @PropertyDesc("偏差电量")
    @Column(name="dev_pq", nullable=true, length=16) 
    private BigDecimal devPq;
    
    @PropertyDesc("偏差电量比例")
    @Column(name="dev_pq_prop", nullable=true, length=6) 
    private BigDecimal devPqProp;
    
    @PropertyDesc("偏差违约金(售电公司偏差考核)")
    @Column(name="dev_dam", nullable=true, length=16) 
    private BigDecimal devDam;
    
    @PropertyDesc("总利润")
    @Column(name="company_pro", nullable=true, length=16) 
    private BigDecimal companyPro;
    
    @PropertyDesc("用户偏差考核总费用(元)")
    @Column(name="cons_checked_pro_total", nullable=true, length=16) 
    private BigDecimal consCheckedProTotal;
    
    @PropertyDesc("赔偿用户金额")
    @Column(name="pay_cons_money", nullable=true, length=16) 
    private BigDecimal payConsMoney;
    
    @PropertyDesc("售电公司id")
    @Column(name="company_id", nullable=true, length=32) 
    private String companyId;
    
    @PropertyDesc("批发市场购电支出(江苏结算用)")
    @Column(name="company_cost_total", nullable=true, length=16) 
    private BigDecimal companyCostTotal;
    
    @PropertyDesc("零售市场购电收入(江苏结算用)")
    @Column(name="cons_amt_total", nullable=true, length=16) 
    private BigDecimal consAmtTotal;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public BigDecimal getLcTotalPq(){
        return lcTotalPq;
    }
    
    public void setLcTotalPq(BigDecimal lcTotalPq){
        this.lcTotalPq = lcTotalPq;
    }
    
    public BigDecimal getBidTotalPq(){
        return bidTotalPq;
    }
    
    public void setBidTotalPq(BigDecimal bidTotalPq){
        this.bidTotalPq = bidTotalPq;
    }
    
    public BigDecimal getListedTotalPq(){
        return listedTotalPq;
    }
    
    public void setListedTotalPq(BigDecimal listedTotalPq){
        this.listedTotalPq = listedTotalPq;
    }
    
    public BigDecimal getLcAvgPrc(){
        return lcAvgPrc;
    }
    
    public void setLcAvgPrc(BigDecimal lcAvgPrc){
        this.lcAvgPrc = lcAvgPrc;
    }
    
    public BigDecimal getBidAvgPrc(){
        return bidAvgPrc;
    }
    
    public void setBidAvgPrc(BigDecimal bidAvgPrc){
        this.bidAvgPrc = bidAvgPrc;
    }
    
    public BigDecimal getListedAvgPrc(){
        return listedAvgPrc;
    }
    
    public void setListedAvgPrc(BigDecimal listedAvgPrc){
        this.listedAvgPrc = listedAvgPrc;
    }
    
    public BigDecimal getPurTotalPq(){
        return purTotalPq;
    }
    
    public void setPurTotalPq(BigDecimal purTotalPq){
        this.purTotalPq = purTotalPq;
    }
    
    public BigDecimal getDelTotalPq(){
        return delTotalPq;
    }
    
    public void setDelTotalPq(BigDecimal delTotalPq){
        this.delTotalPq = delTotalPq;
    }
    
    public BigDecimal getDevPq(){
        return devPq;
    }
    
    public void setDevPq(BigDecimal devPq){
        this.devPq = devPq;
    }
    
    public BigDecimal getDevPqProp(){
        return devPqProp;
    }
    
    public void setDevPqProp(BigDecimal devPqProp){
        this.devPqProp = devPqProp;
    }
    
    public BigDecimal getDevDam(){
        return devDam;
    }
    
    public void setDevDam(BigDecimal devDam){
        this.devDam = devDam;
    }
    
    public BigDecimal getCompanyPro(){
        return companyPro;
    }
    
    public void setCompanyPro(BigDecimal companyPro){
        this.companyPro = companyPro;
    }
    
    public BigDecimal getPayConsMoney(){
        return payConsMoney;
    }
    
    public void setPayConsMoney(BigDecimal payConsMoney){
        this.payConsMoney = payConsMoney;
    }
    
    public String getCompanyId(){
        return companyId;
    }
    
    public void setCompanyId(String companyId){
        this.companyId = companyId;
    }
    
    public BigDecimal getCompanyCostTotal(){
        return companyCostTotal;
    }
    
    public void setCompanyCostTotal(BigDecimal companyCostTotal){
        this.companyCostTotal = companyCostTotal;
    }
    
    public BigDecimal getConsAmtTotal(){
        return consAmtTotal;
    }
    
    public void setConsAmtTotal(BigDecimal consAmtTotal){
        this.consAmtTotal = consAmtTotal;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }

	public BigDecimal getConsCheckedProTotal() {
		return consCheckedProTotal;
	}

	public void setConsCheckedProTotal(BigDecimal consCheckedProTotal) {
		this.consCheckedProTotal = consCheckedProTotal;
	}
    
}