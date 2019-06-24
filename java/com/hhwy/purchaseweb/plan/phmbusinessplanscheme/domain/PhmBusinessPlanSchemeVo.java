package com.hhwy.purchaseweb.plan.phmbusinessplanscheme.domain;

import com.hhwy.purchase.domain.PhmBusinessPlanScheme;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmBusinessPlanSchemeVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmBusinessPlanSchemeVo extends PagingProperty{

    private String year;
    
	private PhmBusinessPlanScheme phmBusinessPlanScheme = BaseModel.getModel(PhmBusinessPlanScheme.class);
	
	public PhmBusinessPlanScheme getPhmBusinessPlanScheme(){
		return phmBusinessPlanScheme;
	}
	
	public void setPhmBusinessPlanScheme(PhmBusinessPlanScheme phmBusinessPlanScheme){
		this.phmBusinessPlanScheme = phmBusinessPlanScheme;
	}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}