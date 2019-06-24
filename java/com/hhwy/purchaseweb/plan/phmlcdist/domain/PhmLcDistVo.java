package com.hhwy.purchaseweb.plan.phmlcdist.domain;

import com.hhwy.purchase.domain.PhmLcDist;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmLcDistVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmLcDistVo extends PagingProperty{

	private PhmLcDist phmLcDist = BaseModel.getModel(PhmLcDist.class);
	
	public PhmLcDist getPhmLcDist(){
		return phmLcDist;
	}
	
	public void setPhmLcDist(PhmLcDist phmLcDist){
		this.phmLcDist = phmLcDist;
	}
}