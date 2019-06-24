package com.hhwy.purchaseweb.crm.smruleconfigure.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmRuleConfigure;

/**
 * SmRuleConfigureVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmRuleConfigureVo extends PagingProperty{

	private SmRuleConfigure smRuleConfigure = BaseModel.getModel(SmRuleConfigure.class);
	
	public SmRuleConfigure getSmRuleConfigure(){
		return smRuleConfigure;
	}
	
	public void setSmRuleConfigure(SmRuleConfigure smRuleConfigure){
		this.smRuleConfigure = smRuleConfigure;
	}
}