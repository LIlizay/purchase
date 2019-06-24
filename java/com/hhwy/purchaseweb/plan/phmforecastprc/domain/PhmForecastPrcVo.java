package com.hhwy.purchaseweb.plan.phmforecastprc.domain;

import com.hhwy.purchase.domain.PhmForecastPrc;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmForecastPrcVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmForecastPrcVo extends PagingProperty{

	private PhmForecastPrc phmForecastPrc = BaseModel.getModel(PhmForecastPrc.class);
	
	public PhmForecastPrc getPhmForecastPrc(){
		return phmForecastPrc;
	}
	
	public void setPhmForecastPrc(PhmForecastPrc phmForecastPrc){
		this.phmForecastPrc = phmForecastPrc;
	}
}