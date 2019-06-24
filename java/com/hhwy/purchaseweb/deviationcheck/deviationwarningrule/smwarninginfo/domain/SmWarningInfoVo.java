package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmWarningInfo;

/**
 * SmWarningInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmWarningInfoVo extends PagingProperty{

	private SmWarningInfo smWarningInfo = BaseModel.getModel(SmWarningInfo.class);
	
	public SmWarningInfo getSmWarningInfo(){
		return smWarningInfo;
	}
	
	public void setSmWarningInfo(SmWarningInfo smWarningInfo){
		this.smWarningInfo = smWarningInfo;
	}
}