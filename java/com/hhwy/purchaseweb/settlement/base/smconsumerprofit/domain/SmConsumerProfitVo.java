package com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmConsumerProfit;

/**
 * SmConsumerProfitVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmConsumerProfitVo extends PagingProperty{

	private SmConsumerProfit smConsumerProfit = BaseModel.getModel(SmConsumerProfit.class);
	
	public SmConsumerProfit getSmConsumerProfit(){
		return smConsumerProfit;
	}
	
	public void setSmConsumerProfit(SmConsumerProfit smConsumerProfit){
		this.smConsumerProfit = smConsumerProfit;
	}
}