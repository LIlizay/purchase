package com.hhwy.purchaseweb.bid.phmreportremind.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.purchase.domain.PhmReportRemind;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmReportRemindVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmReportRemindVo extends PagingProperty{

	private PhmReportRemind phmReportRemind = BaseModel.getModel(PhmReportRemind.class);
	
	private String planId;
	
	private String remindInfo;
	
	private List<String> consIds = new ArrayList<String>();
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getRemindInfo() {
		return remindInfo;
	}

	public void setRemindInfo(String remindInfo) {
		this.remindInfo = remindInfo;
	}

	public List<String> getConsIds() {
		return consIds;
	}

	public void setConsIds(List<String> consIds) {
		this.consIds = consIds;
	}

	public PhmReportRemind getPhmReportRemind(){
		return phmReportRemind;
	}
	
	public void setPhmReportRemind(PhmReportRemind phmReportRemind){
		this.phmReportRemind = phmReportRemind;
	}
}