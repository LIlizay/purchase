package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmConsWarningInfo;

/**
 * SmConsWarningInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmConsWarningInfoVo extends PagingProperty{

	private SmConsWarningInfo smConsWarningInfo = BaseModel.getModel(SmConsWarningInfo.class);
	
	public SmConsWarningInfo getSmConsWarningInfo(){
		return smConsWarningInfo;
	}
	
	public void setSmConsWarningInfo(SmConsWarningInfo smConsWarningInfo){
		this.smConsWarningInfo = smConsWarningInfo;
	}
}