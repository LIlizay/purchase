package com.hhwy.purchaseweb.consstatistics.domain;

import java.util.Date;

import com.hhwy.business.core.modelutil.PagingProperty;

public class ConsstatisticsVo extends PagingProperty{
	
	//省份
	private String orgNo;
	
	//省份foreach
	private String[] item;
	
	//系统开通时间
	private Date startDate;
	
	//至
	private Date endDate;

	
	public String getOrgNo() {
		return orgNo;
	}

	public String[] getItem() {
		return item;
	}

	public void setItem(String[] item) {
		this.item = item;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	
}
