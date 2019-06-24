package com.hhwy.purchaseweb.contract.smagresup.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmAgreSup;

/**
 * SmAgreSupVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmAgreSupVo extends PagingProperty{

	private SmAgreSup smAgreSup = BaseModel.getModel(SmAgreSup.class);
	
	public SmAgreSup getSmAgreSup(){
		return smAgreSup;
	}
	
	public void setSmAgreSup(SmAgreSup smAgreSup){
		this.smAgreSup = smAgreSup;
	}
}