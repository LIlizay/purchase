package com.hhwy.purchaseweb.settlement.base.smcompanyprofit.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;

/**
 * SmCompanyProfitVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmCompanyProfitVo extends PagingProperty{

	
	//月购电情况及本企业总收益实体对象
	private SmCompanyProfit smCompanyProfit = BaseModel.getModel(SmCompanyProfit.class);
	
	
	//用户受益信息对象；用于保存
	private List<SmConsumerProfit> smConsumerProfitList;
	
	//用户月度电量 用于计算用户受益
    private List<SmConsumerProfitDetail> smConsumerProfitDetailList;
	
    
    
    
	public List<SmConsumerProfit> getSmConsumerProfitList() {
		return smConsumerProfitList;
	}

	public void setSmConsumerProfitList(List<SmConsumerProfit> smConsumerProfitList) {
		this.smConsumerProfitList = smConsumerProfitList;
	}

	public List<SmConsumerProfitDetail> getSmConsumerProfitDetailList() {
		return smConsumerProfitDetailList;
	}

	public void setSmConsumerProfitDetailList(List<SmConsumerProfitDetail> smConsumerProfitDetailList) {
		this.smConsumerProfitDetailList = smConsumerProfitDetailList;
	}

	public SmCompanyProfit getSmCompanyProfit(){
		return smCompanyProfit;
	}
	
	public void setSmCompanyProfit(SmCompanyProfit smCompanyProfit){
		this.smCompanyProfit = smCompanyProfit;
	}
}