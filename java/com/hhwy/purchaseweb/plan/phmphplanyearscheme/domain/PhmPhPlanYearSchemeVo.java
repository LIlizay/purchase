package com.hhwy.purchaseweb.plan.phmphplanyearscheme.domain;

import com.hhwy.purchase.domain.PhmPhPlanYearScheme;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmPhPlanYearSchemeVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmPhPlanYearSchemeVo extends PagingProperty{

    private String year;
    
	private String startYear;
	
	private String endYear;
	
	private PhmPhPlanYearScheme phmPhPlanYearScheme = BaseModel.getModel(PhmPhPlanYearScheme.class);
	
	public PhmPhPlanYearScheme getPhmPhPlanYearScheme(){
		return phmPhPlanYearScheme;
	}
	
	public void setPhmPhPlanYearScheme(PhmPhPlanYearScheme phmPhPlanYearScheme){
		this.phmPhPlanYearScheme = phmPhPlanYearScheme;
	}

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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