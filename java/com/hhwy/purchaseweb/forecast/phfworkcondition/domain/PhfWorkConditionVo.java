package com.hhwy.purchaseweb.forecast.phfworkcondition.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhfWorkCondition;

/**
 * PhfWorkConditionVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfWorkConditionVo extends PagingProperty{

	private PhfWorkCondition phfWorkCondition = BaseModel.getModel(PhfWorkCondition.class);
	
	private List<PhfWorkCondition> list=new ArrayList<PhfWorkCondition>();
	
	public PhfWorkCondition getPhfWorkCondition(){
		return phfWorkCondition;
	}
	
	public void setPhfWorkCondition(PhfWorkCondition phfWorkCondition){
		this.phfWorkCondition = phfWorkCondition;
	}

	public List<PhfWorkCondition> getList() {
		return list;
	}

	public void setList(List<PhfWorkCondition> list) {
		this.list = list;
	}
}