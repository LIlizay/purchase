package com.hhwy.purchaseweb.contract.smagrepunishcons.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmAgrePunishCons;

/**
 * SmAgrePunishConsVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmAgrePunishConsVo extends PagingProperty{

	private SmAgrePunishCons smAgrePunishCons = BaseModel.getModel(SmAgrePunishCons.class);
	
	public SmAgrePunishCons getSmAgrePunishCons(){
		return smAgrePunishCons;
	}
	
	public void setSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons){
		this.smAgrePunishCons = smAgrePunishCons;
	}
}