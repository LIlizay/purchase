package com.hhwy.purchaseweb.plan.phmplanyearconsrela.domain;

import com.hhwy.purchase.domain.PhmPlanYearConsRela;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmPlanYearConsRelaVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmPlanYearConsRelaVo extends PagingProperty{

	private PhmPlanYearConsRela phmPlanYearConsRela = BaseModel.getModel(PhmPlanYearConsRela.class);
	
	public PhmPlanYearConsRela getPhmPlanYearConsRela(){
		return phmPlanYearConsRela;
	}
	
	public void setPhmPlanYearConsRela(PhmPlanYearConsRela phmPlanYearConsRela){
		this.phmPlanYearConsRela = phmPlanYearConsRela;
	}
}