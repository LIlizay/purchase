package com.hhwy.purchaseweb.forecast.phfpurcreport.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhfPurcReport;

/**
 * PhfPurcReportVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfPurcReportVo extends PagingProperty{

	private PhfPurcReport phfPurcReport = BaseModel.getModel(PhfPurcReport.class);
	
	public PhfPurcReport getPhfPurcReport(){
		return phfPurcReport;
	}
	
	public void setPhfPurcReport(PhfPurcReport phfPurcReport){
		this.phfPurcReport = phfPurcReport;
	}
}