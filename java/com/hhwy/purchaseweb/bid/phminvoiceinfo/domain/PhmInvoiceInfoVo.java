package com.hhwy.purchaseweb.bid.phminvoiceinfo.domain;

import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmInvoiceInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmInvoiceInfoVo extends PagingProperty{

	private PhmInvoiceInfo phmInvoiceInfo = BaseModel.getModel(PhmInvoiceInfo.class);
	
	/**
	 * 起始年月
	 */
	private String bgnYm;
	
	/**
	 * 截止年月
	 */
	private String endYm;
	
	public PhmInvoiceInfo getPhmInvoiceInfo(){
		return phmInvoiceInfo;
	}
	
	public void setPhmInvoiceInfo(PhmInvoiceInfo phmInvoiceInfo){
		this.phmInvoiceInfo = phmInvoiceInfo;
	}

	public String getBgnYm() {
		return bgnYm;
	}

	public void setBgnYm(String bgnYm) {
		this.bgnYm = bgnYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}
}