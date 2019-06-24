package com.hhwy.purchaseweb.login.swconsinfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SwConsInfo;

/**
 * SwConsInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SwConsInfoVo extends PagingProperty{

	private SwConsInfo swConsInfo = BaseModel.getModel(SwConsInfo.class);
	
	public SwConsInfo getSwConsInfo(){
		return swConsInfo;
	}
	
	public void setSwConsInfo(SwConsInfo swConsInfo){
		this.swConsInfo = swConsInfo;
	}
}