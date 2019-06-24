package com.hhwy.purchaseweb.contract.smagremp.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmAgreMp;

/**
 * SmAgreMpVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmAgreMpVo extends PagingProperty{

	private SmAgreMp smAgreMp = BaseModel.getModel(SmAgreMp.class);
	
	public SmAgreMp getSmAgreMp(){
		return smAgreMp;
	}
	
	public void setSmAgreMp(SmAgreMp smAgreMp){
		this.smAgreMp = smAgreMp;
	}
}