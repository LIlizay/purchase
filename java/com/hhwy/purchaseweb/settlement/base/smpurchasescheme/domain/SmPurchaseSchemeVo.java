package com.hhwy.purchaseweb.settlement.base.smpurchasescheme.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmPurchaseScheme;

/**
 * SmPurchaseSchemeVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmPurchaseSchemeVo extends PagingProperty{

	private SmPurchaseScheme smPurchaseScheme = BaseModel.getModel(SmPurchaseScheme.class);
	
	public SmPurchaseScheme getSmPurchaseScheme(){
		return smPurchaseScheme;
	}
	
	public void setSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme){
		this.smPurchaseScheme = smPurchaseScheme;
	}
}