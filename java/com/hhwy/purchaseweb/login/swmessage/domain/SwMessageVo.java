package com.hhwy.purchaseweb.login.swmessage.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SellingSwMessage;

/**
 * SwMessageVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SwMessageVo extends PagingProperty{

	private SellingSwMessage sellingSwMessage = BaseModel.getModel(SellingSwMessage.class);

	public SellingSwMessage getSellingSwMessage() {
		return sellingSwMessage;
	}

	public void setSellingSwMessage(SellingSwMessage sellingSwMessage) {
		this.sellingSwMessage = sellingSwMessage;
	}
	
}