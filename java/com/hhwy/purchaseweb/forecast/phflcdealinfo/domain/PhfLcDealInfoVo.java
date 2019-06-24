package com.hhwy.purchaseweb.forecast.phflcdealinfo.domain;

import com.hhwy.purchase.domain.PhfLcDealInfo;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhfLcDealInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfLcDealInfoVo extends PagingProperty{

	private PhfLcDealInfo phfLcDealInfo = BaseModel.getModel(PhfLcDealInfo.class);
	
	public PhfLcDealInfo getPhfLcDealInfo(){
		return phfLcDealInfo;
	}
	
	public void setPhfLcDealInfo(PhfLcDealInfo phfLcDealInfo){
		this.phfLcDealInfo = phfLcDealInfo;
	}
}