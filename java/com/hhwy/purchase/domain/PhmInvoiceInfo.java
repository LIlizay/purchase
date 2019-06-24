package com.hhwy.purchase.domain;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.framework.persistent.entity.Domain;
import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * PhmInvoiceInfo
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
@Entity(name="PhmInvoiceInfo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name="ph_m_invoice_info")
public class PhmInvoiceInfo extends Domain implements Serializable {

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
    
    @PropertyDesc("开票时间")
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    @Column(name="invoice_date", nullable=true, length=10) 
    private Date invoiceDate;
    
    @PropertyDesc("开票人")
    @Column(name="drawer", nullable=true, length=32) 
    private String drawer;
    
    @PropertyDesc("发票代码")
    @Column(name="invoice_code", nullable=true, length=16) 
    private String invoiceCode;
    
    @PropertyDesc("发票号码")
    @Column(name="invoice_number", nullable=true, length=16) 
    private String invoiceNumber;
    
    @PropertyDesc("结算id")
    @Column(name="settle_id", nullable=true, length=32) 
    private String settleId;
    
    @PropertyDesc("部门id")
    @Column(name="org_no", nullable=true, length=32) 
    private String orgNo;
    
    @PropertyDesc("附件")
    @Column(name="file", nullable=true, length=255) 
    private String file;
    
    public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getYm(){
        return ym;
    }
    
    public void setYm(String ym){
        this.ym = ym;
    }
    
    public Date getInvoiceDate(){
        return invoiceDate;
    }
    
    public void setInvoiceDate(Date invoiceDate){
        this.invoiceDate = invoiceDate;
    }
    
    public String getDrawer(){
        return drawer;
    }
    
    public void setDrawer(String drawer){
        this.drawer = drawer;
    }
    
    public String getInvoiceCode(){
        return invoiceCode;
    }
    
    public void setInvoiceCode(String invoiceCode){
        this.invoiceCode = invoiceCode;
    }
    
    public String getInvoiceNumber(){
        return invoiceNumber;
    }
    
    public void setInvoiceNumber(String invoiceNumber){
        this.invoiceNumber = invoiceNumber;
    }
    
	public String getSettleId() {
		return settleId;
	}

	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}

	public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}