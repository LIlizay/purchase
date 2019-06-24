package com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain;

import com.hhwy.purchase.domain.PhmPurchasePlanYear;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmPurchasePlanYearVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmPurchasePlanYearVo extends PagingProperty{

    private String startYear;
    
    private String endYear;
    
	private PhmPurchasePlanYear phmPurchasePlanYear = BaseModel.getModel(PhmPurchasePlanYear.class);
	
	public PhmPurchasePlanYear getPhmPurchasePlanYear(){
		return phmPurchasePlanYear;
	}
	
	public void setPhmPurchasePlanYear(PhmPurchasePlanYear phmPurchasePlanYear){
		this.phmPurchasePlanYear = phmPurchasePlanYear;
	}

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

}