package com.hhwy.purchaseweb.delivery.smfundprcinfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmFundPrcInfo;

/**
 * SmFundPrcInfoVo
 * 
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmFundPrcInfoVo extends PagingProperty {

	private SmFundPrcInfo smFundPrcInfo = BaseModel
			.getModel(SmFundPrcInfo.class);

	public SmFundPrcInfo getSmFundPrcInfo() {
		return smFundPrcInfo;
	}

	public void setSmFundPrcInfo(SmFundPrcInfo smFundPrcInfo) {
		this.smFundPrcInfo = smFundPrcInfo;
	}
}