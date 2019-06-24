package com.hhwy.purchaseweb.systemcompanyapproval.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SystemcompanyApproval;

/**
 * SystemcompanyApprovalVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SystemcompanyApprovalVo extends PagingProperty{

	private SystemcompanyApproval systemcompanyApproval = BaseModel.getModel(SystemcompanyApproval.class);
	
	//审批状态
	private String approvalStatus;
	
	//流程发起时间
	private String startTime;
	
	//至
	private String endTime;
	
	
	
	
	public SystemcompanyApproval getSystemcompanyApproval(){
		return systemcompanyApproval;
	}
	
	public void setSystemcompanyApproval(SystemcompanyApproval systemcompanyApproval){
		this.systemcompanyApproval = systemcompanyApproval;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
}