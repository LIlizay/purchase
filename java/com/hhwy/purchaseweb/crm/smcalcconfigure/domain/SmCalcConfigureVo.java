package com.hhwy.purchaseweb.crm.smcalcconfigure.domain;

import com.hhwy.purchase.domain.SmCalcConfigure;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * SmCalcConfigureVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmCalcConfigureVo extends PagingProperty{

	private SmCalcConfigure smCalcConfigure = BaseModel.getModel(SmCalcConfigure.class);
	
	public SmCalcConfigure getSmCalcConfigure(){
		return smCalcConfigure;
	}
	
	public void setSmCalcConfigure(SmCalcConfigure smCalcConfigure){
		this.smCalcConfigure = smCalcConfigure;
	}
}