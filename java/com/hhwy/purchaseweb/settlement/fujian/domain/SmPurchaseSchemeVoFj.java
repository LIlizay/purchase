package com.hhwy.purchaseweb.settlement.fujian.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeVoFj<br/>
 * <b>类 描 述：</b><br/>福建的月度结算方案vo类，涉及到结算7张表中的5张，分别是
 * 					s_m_settlement_month，s_m_purchase_scheme，s_m_company_profit，s_m_purchase_scheme_detail，s_m_consumer_profit
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月20日 上午9:14:24<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmPurchaseSchemeVoFj extends PagingProperty{

	private SmPurchaseScheme smPurchaseScheme = BaseModel.getModel(SmPurchaseScheme.class);
	
	//月购电情况及本企业总收益实体对象 用于保存
	private SmCompanyProfit smCompanyProfit;
	
	//前台的“零售市场售电收入明细”表数据；用于保存，分解为s_m_purchase_scheme_detail，s_m_consumer_profit表的数据
	private List<RetailDetailFj> retailDetailFjList;
    
  	//用户受益信息对象；用于保存,由retailDetailFjList分解而来
  	private List<SmConsumerProfit> smConsumerProfitList;
  	
  	//月度结算明细信息 列表，用于保存,由retailDetailFjList分解而来
  	private List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList;
  	
  	//批发交易结算明细 列表数据, 用于保存
    private List<SmCompanyCostDetail> costDetailList;
  	
  	private String settleId;	//结算id
	private String ym;		//年月 yyyy-MM格式
	
	
	
	
	
	
	
	public List<SmCompanyCostDetail> getCostDetailList() {
		return costDetailList;
	}
	public void setCostDetailList(List<SmCompanyCostDetail> costDetailList) {
		this.costDetailList = costDetailList;
	}
	public SmPurchaseScheme getSmPurchaseScheme() {
		return smPurchaseScheme;
	}
	public void setSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme) {
		this.smPurchaseScheme = smPurchaseScheme;
	}
	public SmCompanyProfit getSmCompanyProfit() {
		return smCompanyProfit;
	}
	public void setSmCompanyProfit(SmCompanyProfit smCompanyProfit) {
		this.smCompanyProfit = smCompanyProfit;
	}
	public List<SmConsumerProfit> getSmConsumerProfitList() {
		return smConsumerProfitList;
	}
	public void setSmConsumerProfitList(List<SmConsumerProfit> smConsumerProfitList) {
		this.smConsumerProfitList = smConsumerProfitList;
	}
	public List<SmPurchaseSchemeDetail> getSmPurchaseSchemeDetailList() {
		return smPurchaseSchemeDetailList;
	}
	public void setSmPurchaseSchemeDetailList(List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList) {
		this.smPurchaseSchemeDetailList = smPurchaseSchemeDetailList;
	}
	public List<RetailDetailFj> getRetailDetailFjList() {
		return retailDetailFjList;
	}
	public void setRetailDetailFjList(List<RetailDetailFj> retailDetailFjList) {
		this.retailDetailFjList = retailDetailFjList;
	}
	public String getSettleId() {
		return settleId;
	}
	public void setSettleId(String settleId) {
		this.settleId = settleId;
	}
	public String getYm() {
		return ym;
	}
	public void setYm(String ym) {
		this.ym = ym;
	}
}