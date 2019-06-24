package com.hhwy.purchaseweb.agreement.phmgenepqdist.domain;

import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmGenePqDistVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmGenePqDistVo extends PagingProperty{

	private PhmGenePqDist phmGenePqDist = BaseModel.getModel(PhmGenePqDist.class);
	
	public PhmGenePqDist getPhmGenePqDist(){
		return phmGenePqDist;
	}
	
	public void setPhmGenePqDist(PhmGenePqDist phmGenePqDist){
		this.phmGenePqDist = phmGenePqDist;
	}
}