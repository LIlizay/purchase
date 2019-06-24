package com.hhwy.purchaseweb.contract.smagrepunishcomp.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmAgrePunishComp;

/**
 * SmAgrePunishCompVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmAgrePunishCompVo extends PagingProperty{

	private SmAgrePunishComp smAgrePunishComp = BaseModel.getModel(SmAgrePunishComp.class);
	
	public SmAgrePunishComp getSmAgrePunishComp(){
		return smAgrePunishComp;
	}
	
	public void setSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp){
		this.smAgrePunishComp = smAgrePunishComp;
	}
}