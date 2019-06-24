package com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain;

import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmGeneratorMonthPqVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmGeneratorMonthPqVo extends PagingProperty{

	private PhmGeneratorMonthPq phmGeneratorMonthPq = BaseModel.getModel(PhmGeneratorMonthPq.class);
	
	public PhmGeneratorMonthPq getPhmGeneratorMonthPq(){
		return phmGeneratorMonthPq;
	}
	
	public void setPhmGeneratorMonthPq(PhmGeneratorMonthPq phmGeneratorMonthPq){
		this.phmGeneratorMonthPq = phmGeneratorMonthPq;
	}
}