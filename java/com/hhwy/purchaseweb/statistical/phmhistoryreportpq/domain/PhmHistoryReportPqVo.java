package com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain;

import com.hhwy.purchase.domain.PhmHistoryReportPq;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmHistoryReportPqVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmHistoryReportPqVo extends PagingProperty{

	private PhmHistoryReportPq phmHistoryReportPq = BaseModel.getModel(PhmHistoryReportPq.class);
	
	public PhmHistoryReportPq getPhmHistoryReportPq(){
		return phmHistoryReportPq;
	}
	
	public void setPhmHistoryReportPq(PhmHistoryReportPq phmHistoryReportPq){
		this.phmHistoryReportPq = phmHistoryReportPq;
	}
}