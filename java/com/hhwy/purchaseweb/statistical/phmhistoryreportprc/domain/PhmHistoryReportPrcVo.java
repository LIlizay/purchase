package com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain;

import com.hhwy.purchase.domain.PhmHistoryReportPrc;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmHistoryReportPrcVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmHistoryReportPrcVo extends PagingProperty{

    private String year;
    
	private PhmHistoryReportPrc phmHistoryReportPrc = BaseModel.getModel(PhmHistoryReportPrc.class);
	
	public PhmHistoryReportPrc getPhmHistoryReportPrc(){
		return phmHistoryReportPrc;
	}
	
	public void setPhmHistoryReportPrc(PhmHistoryReportPrc phmHistoryReportPrc){
		this.phmHistoryReportPrc = phmHistoryReportPrc;
	}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}