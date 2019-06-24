package com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmConsDeviationAmt;

/**
 * SmConsDeviationAmtVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmConsDeviationAmtVo extends PagingProperty{

	private SmConsDeviationAmt smConsDeviationAmt = BaseModel.getModel(SmConsDeviationAmt.class);
	
	public SmConsDeviationAmt getSmConsDeviationAmt(){
		return smConsDeviationAmt;
	}
	
	public void setSmConsDeviationAmt(SmConsDeviationAmt smConsDeviationAmt){
		this.smConsDeviationAmt = smConsDeviationAmt;
	}
}