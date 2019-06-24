package com.hhwy.purchaseweb.agreement.phmagretemplate.domain;

import com.hhwy.purchase.domain.PhmAgreTemplate;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmAgreTemplateVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmAgreTemplateVo extends PagingProperty{

	private PhmAgreTemplate phmAgreTemplate = BaseModel.getModel(PhmAgreTemplate.class);
	
	public PhmAgreTemplate getPhmAgreTemplate(){
		return phmAgreTemplate;
	}
	
	public void setPhmAgreTemplate(PhmAgreTemplate phmAgreTemplate){
		this.phmAgreTemplate = phmAgreTemplate;
	}
}