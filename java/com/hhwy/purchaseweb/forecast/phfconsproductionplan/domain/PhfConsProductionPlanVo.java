package com.hhwy.purchaseweb.forecast.phfconsproductionplan.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhfConsProductionPlan;

/**
 * PhfConsProductionPlanVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfConsProductionPlanVo extends PagingProperty{

	private PhfConsProductionPlan phfConsProductionPlan = BaseModel.getModel(PhfConsProductionPlan.class);
	
	public PhfConsProductionPlan getPhfConsProductionPlan(){
		return phfConsProductionPlan;
	}
	
	public void setPhfConsProductionPlan(PhfConsProductionPlan phfConsProductionPlan){
		this.phfConsProductionPlan = phfConsProductionPlan;
	}
}