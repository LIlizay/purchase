package com.hhwy.purchaseweb.contract.smdistmode.domain;

import com.hhwy.selling.domain.SmDistMode;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * SmDistModeVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmDistModeVo extends PagingProperty{

	private SmDistMode smDistMode = BaseModel.getModel(SmDistMode.class);
	
	public SmDistMode getSmDistMode(){
		return smDistMode;
	}
	
	public void setSmDistMode(SmDistMode smDistMode){
		this.smDistMode = smDistMode;
	}
}