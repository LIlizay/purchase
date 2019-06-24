package com.hhwy.purchaseweb.delivery.entersettle.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhmConsSettleRela;
import com.hhwy.purchase.domain.PhmEnterSettle;

public class EnterSettleVo  extends PagingProperty{
	
	private String startYm;		//起始年月
	
	private String endYm;		//截止年月
	
	private List<PhmConsSettleRela> phmConsSettleRelaList;
	
	private PhmEnterSettle phmEnterSettle;

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
	
	public List<PhmConsSettleRela> getPhmConsSettleRelaList() {
		return phmConsSettleRelaList;
	}

	public void setPhmConsSettleRelaList(List<PhmConsSettleRela> phmConsSettleRelaList) {
		this.phmConsSettleRelaList = phmConsSettleRelaList;
	}

	public PhmEnterSettle getPhmEnterSettle() {
		return phmEnterSettle;
	}

	public void setPhmEnterSettle(PhmEnterSettle phmEnterSettle) {
		this.phmEnterSettle = phmEnterSettle;
	}

}
