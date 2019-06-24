package com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhmAgrePqExamine;
import com.hhwy.purchase.domain.PhmPurchasePlanMonth;

/**
 * PhmPurchasePlanMonthVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmPurchasePlanMonthVo extends PagingProperty{

	private PhmPurchasePlanMonth phmPurchasePlanMonth = BaseModel.getModel(PhmPurchasePlanMonth.class);
	
	private String startYm;
	
	private String endYm;
	
	private String flag;			//已成交后的电量显示标记,false 不显示
	
	private String planName;
	
	private List<PhmAgrePqExamine> examineList = new ArrayList<PhmAgrePqExamine>();
	
	private String[] delIds;
	
	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public PhmPurchasePlanMonth getPhmPurchasePlanMonth(){
		return phmPurchasePlanMonth;
	}
	
	public void setPhmPurchasePlanMonth(PhmPurchasePlanMonth phmPurchasePlanMonth){
		this.phmPurchasePlanMonth = phmPurchasePlanMonth;
	}

	public String getStartYm() {
		return startYm;
	}

	public void setStartYm(String startYm) {
		this.startYm = startYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}

	public List<PhmAgrePqExamine> getExamineList() {
		return examineList;
	}

	public void setExamineList(List<PhmAgrePqExamine> examineList) {
		this.examineList = examineList;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String[] getDelIds() {
		return delIds;
	}

	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}
}