package com.hhwy.purchaseweb.contract.smagretemplate.domain;

import com.hhwy.selling.domain.SmAgreTemplate;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * SmAgreTemplateVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmAgreTemplateVo extends PagingProperty{

	private SmAgreTemplate smAgreTemplate = BaseModel.getModel(SmAgreTemplate.class);
	
	public SmAgreTemplate getSmAgreTemplate(){
		return smAgreTemplate;
	}
	
	public void setSmAgreTemplate(SmAgreTemplate smAgreTemplate){
		this.smAgreTemplate = smAgreTemplate;
	}
}