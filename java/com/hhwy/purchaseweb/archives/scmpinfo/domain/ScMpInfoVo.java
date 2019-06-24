package com.hhwy.purchaseweb.archives.scmpinfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScMpInfo;

/**
 * ScMpInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ScMpInfoVo extends PagingProperty{

	private ScMpInfo scMpInfo = BaseModel.getModel(ScMpInfo.class);
	
	public ScMpInfo getScMpInfo(){
		return scMpInfo;
	}
	
	public void setScMpInfo(ScMpInfo scMpInfo){
		this.scMpInfo = scMpInfo;
	}
}