package com.hhwy.purchaseweb.archives.sccompanyinfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScCompanyInfo;

/**
 * ScCompanyInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ScCompanyInfoVo extends PagingProperty{

	private ScCompanyInfo scCompanyInfo = BaseModel.getModel(ScCompanyInfo.class);
	
	public ScCompanyInfo getScCompanyInfo(){
		return scCompanyInfo;
	}
	
	public void setScCompanyInfo(ScCompanyInfo scCompanyInfo){
		this.scCompanyInfo = scCompanyInfo;
	}
}