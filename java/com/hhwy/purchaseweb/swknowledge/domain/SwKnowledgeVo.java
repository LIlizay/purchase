package com.hhwy.purchaseweb.swknowledge.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * SwKnowledgeVo
 * @author hhwy
 * @date 2017-11-8 13:46:42
 * @version 1.0
 */
public class SwKnowledgeVo extends PagingProperty{

	private SwKnowledge swKnowledge = BaseModel.getModel(SwKnowledge.class);

	public SwKnowledge getSwKnowledge() {
		return swKnowledge;
	}

	public void setSwKnowledge(SwKnowledge swKnowledge) {
		this.swKnowledge = swKnowledge;
	}
	

}