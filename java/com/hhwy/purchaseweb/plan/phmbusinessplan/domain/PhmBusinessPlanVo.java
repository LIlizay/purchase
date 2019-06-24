package com.hhwy.purchaseweb.plan.phmbusinessplan.domain;

import java.util.List;

import com.hhwy.purchase.domain.PhmBusinessPlan;
import com.hhwy.purchaseweb.plan.phmpurchaseplanyear.domain.ConsInfoDetail;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmBusinessPlanVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmBusinessPlanVo extends PagingProperty{

    private String startYear;
    
    private String endYear;
    
	private PhmBusinessPlan phmBusinessPlan = BaseModel.getModel(PhmBusinessPlan.class);
	
	private String[] consIds;
	
	private String[] deleteIds;
	
	private List<ConsInfoDetail> consList;
	
	public PhmBusinessPlan getPhmBusinessPlan(){
		return phmBusinessPlan;
	}
	
	public void setPhmBusinessPlan(PhmBusinessPlan phmBusinessPlan){
		this.phmBusinessPlan = phmBusinessPlan;
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

    public String[] getConsIds() {
        return consIds;
    }

    public void setConsIds(String[] consIds) {
        this.consIds = consIds;
    }

    public String[] getDeleteIds() {
        return deleteIds;
    }

    public void setDeleteIds(String[] deleteIds) {
        this.deleteIds = deleteIds;
    }

    public List<ConsInfoDetail> getConsList() {
        return consList;
    }

    public void setConsList(List<ConsInfoDetail> consList) {
        this.consList = consList;
    }
}