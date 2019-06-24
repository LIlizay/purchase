package com.hhwy.purchaseweb.swhtmlinfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * SwHtmlInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SwHtmlInfoVo extends PagingProperty{

	private SwHtmlInfo swHtmlInfo = BaseModel.getModel(SwHtmlInfo.class);
	
	private String releaseTime ;
	
	public String getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(String releaseTime) {
		this.releaseTime = releaseTime;
	}

	public SwHtmlInfo getSwHtmlInfo(){
		return swHtmlInfo;
	}
	
	public void setSwHtmlInfo(SwHtmlInfo swHtmlInfo){
		this.swHtmlInfo = swHtmlInfo;
	}

}