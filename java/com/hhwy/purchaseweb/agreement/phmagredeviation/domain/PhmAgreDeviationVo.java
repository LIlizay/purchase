package com.hhwy.purchaseweb.agreement.phmagredeviation.domain;

import com.hhwy.purchase.domain.PhmAgreDeviation;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmAgreDeviationVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmAgreDeviationVo extends PagingProperty{

	private PhmAgreDeviation phmAgreDeviation = BaseModel.getModel(PhmAgreDeviation.class);
	
	public PhmAgreDeviation getPhmAgreDeviation(){
		return phmAgreDeviation;
	}
	
	public void setPhmAgreDeviation(PhmAgreDeviation phmAgreDeviation){
		this.phmAgreDeviation = phmAgreDeviation;
	}
}