package com.hhwy.purchaseweb.bid.phminvoiceinfo.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>PhmInvoiceInfoDetail<br/>
 * <b>类 描 述：发票登记详情类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月19日 下午3:19:53<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class PhmInvoiceInfoDetail {

	private String id;
	
	@PropertyDesc("年月")
    private String ym;
    
    @PropertyDesc("开票时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private Date invoiceDate;
    
    @PropertyDesc("开票人")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_USER,field="drawerName")
    private String drawer;
    private String drawerName;
    
    @PropertyDesc("发票代码")
    private String invoiceCode;
    
    @PropertyDesc("发票号码")
    private String invoiceNumber;
    
    @PropertyDesc("结算id")
    private String settleId;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    @PropertyDesc("附件")
    private String file;
    
    private BigDecimal deliveryPq;		//结算电量（MWh）balancePq
    
    private BigDecimal profit;			// 竞争性售电利润（元） （售电公司总利润）
    
    
    
    
    
    
    
    public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
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

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getDrawer() {
		return drawer;
	}

	public void setDrawer(String drawer) {
		this.drawer = drawer;
	}

	public String getInvoiceCode() {
		return invoiceCode;
	}

	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public BigDecimal getDelivryPq() {
		return deliveryPq;
	}

	public void setDelivryPq(BigDecimal delivryPq) {
		this.deliveryPq = delivryPq;
	}

	public java.math.BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(java.math.BigDecimal profit) {
		this.profit = profit;
	}


	public String getDrawerName() {
		return drawerName;
	}

	public void setDrawerName(String drawerName) {
		this.drawerName = drawerName;
	}

}
