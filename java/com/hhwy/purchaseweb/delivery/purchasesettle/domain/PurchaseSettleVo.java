package com.hhwy.purchaseweb.delivery.purchasesettle.domain;

import com.hhwy.business.core.modelutil.PagingProperty;

public class PurchaseSettleVo extends PagingProperty{
	
	//起始年月
	private String startYm;
	
	//截止年月
	private String endYm;

	public String getStartYm() {
		return startYm;
	}

	public void setStartYm(String startYm) {
		this.startYm = startYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}
	
}
