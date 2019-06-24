package com.hhwy.purchaseweb.forecast.phfelecreport.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhfElecReport;

/**
 * PhfElecReportVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfElecReportVo extends PagingProperty{

	private PhfElecReport phfElecReport = BaseModel.getModel(PhfElecReport.class);
	
	public PhfElecReport getPhfElecReport(){
		return phfElecReport;
	}
	
	public void setPhfElecReport(PhfElecReport phfElecReport){
		this.phfElecReport = phfElecReport;
	}
}