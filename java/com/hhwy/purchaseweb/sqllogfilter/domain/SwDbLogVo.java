package com.hhwy.purchaseweb.sqllogfilter.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SwDbLog;

/**
 * SwDbLogVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SwDbLogVo extends PagingProperty{

	private SwDbLog swDbLog = BaseModel.getModel(SwDbLog.class);
	
	private String startTime;
	
	private String endTime;
	
	private String companyName;
	
	
	
	public SwDbLog getSwDbLog(){
		return swDbLog;
	}
	
	public void setSwDbLog(SwDbLog swDbLog){
		this.swDbLog = swDbLog;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
}