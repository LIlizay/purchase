package com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain;

import java.math.BigDecimal;

 /**
 * <b>类 名 称：</b>SmSettlementMonthDetail<br/>
 * <b>类 描 述：</b><br/>   结算主表的detail类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年5月26日 下午4:58:00<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmSettlementMonthDetail{
	
	
	private String id;
	 //年月
    private String ym;
    
    
    //以下为结算方案中的数据
    
    //方案状态（0：草稿，1：归档）
    
    private String schemeStatus;
    //结算电量
    private BigDecimal deliveryPq;
    
    //用户数
    private Integer consNum;
    
    //总委托电量
    private BigDecimal totalProxyPq;
    
    //总购电量（双边、竞价、挂牌电量和，兆瓦时）
    private BigDecimal totalPurchasePq;
    
    //双边总电量
    private BigDecimal totalLcPq;
    
    //竞价总电量
    private BigDecimal totalBidPq;
    
    //挂牌总电量
    private BigDecimal totalListedPq;

    //其他字段
    
    private BigDecimal actualTotalPq;
    
    private BigDecimal devDam;	//偏差违约金(售电公司偏差考核)
    
    private BigDecimal companyPro;	//售电公司总利润 
    
    private BigDecimal consCheckedProTotal;	//用户偏差考核总费用(元)
    
    private String companyId;	//售电公司收益id
    
    
    
    
    
    
    
    
    
    
    
	public BigDecimal getActualTotalPq() {
		return actualTotalPq;
	}

	public void setActualTotalPq(BigDecimal actualTotalPq) {
		this.actualTotalPq = actualTotalPq;
	}

	public String getSchemeStatus() {
		return schemeStatus;
	}

	public void setSchemeStatus(String schemeStatus) {
		this.schemeStatus = schemeStatus;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public Integer getConsNum() {
		return consNum;
	}

	public void setConsNum(Integer consNum) {
		this.consNum = consNum;
	}

	public BigDecimal getTotalProxyPq() {
		return totalProxyPq;
	}

	public void setTotalProxyPq(BigDecimal totalProxyPq) {
		this.totalProxyPq = totalProxyPq;
	}

	public BigDecimal getTotalPurchasePq() {
		return totalPurchasePq;
	}

	public void setTotalPurchasePq(BigDecimal totalPurchasePq) {
		this.totalPurchasePq = totalPurchasePq;
	}

	public BigDecimal getTotalLcPq() {
		return totalLcPq;
	}

	public void setTotalLcPq(BigDecimal totalLcPq) {
		this.totalLcPq = totalLcPq;
	}

	public BigDecimal getTotalBidPq() {
		return totalBidPq;
	}

	public void setTotalBidPq(BigDecimal totalBidPq) {
		this.totalBidPq = totalBidPq;
	}

	public BigDecimal getTotalListedPq() {
		return totalListedPq;
	}

	public void setTotalListedPq(BigDecimal totalListedPq) {
		this.totalListedPq = totalListedPq;
	}

	public BigDecimal getDevDam() {
		return devDam;
	}

	public void setDevDam(BigDecimal devDam) {
		this.devDam = devDam;
	}

	public BigDecimal getCompanyPro() {
		return companyPro;
	}

	public void setCompanyPro(BigDecimal companyPro) {
		this.companyPro = companyPro;
	}

	public BigDecimal getConsCheckedProTotal() {
		return consCheckedProTotal;
	}

	public void setConsCheckedProTotal(BigDecimal consCheckedProTotal) {
		this.consCheckedProTotal = consCheckedProTotal;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}	
	
}