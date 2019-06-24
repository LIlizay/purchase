package com.hhwy.purchaseweb.settlement.other.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeVoOther<br/>
 * <b>类 描 述：</b><br/>江苏、福建以外其他省的月度结算方案vo类，涉及到结算7张表中的5张，分别是
 * 					s_m_settlement_month，s_m_purchase_scheme，s_m_company_profit，s_m_purchase_scheme_detail，s_m_consumer_profit
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年8月20日 上午9:14:24<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmPurchaseSchemeVoOther extends PagingProperty{

	private SmPurchaseScheme smPurchaseScheme = BaseModel.getModel(SmPurchaseScheme.class);
	
	//月购电情况及本企业总收益实体对象 用于保存
	private SmCompanyProfit smCompanyProfit;
	
	//前台的“零售市场售电收入明细”表数据；用于保存，分解为s_m_purchase_scheme_detail，s_m_consumer_profit表的数据
	private List<RetailDetailOther> retailDetailOtherList;
    
  	//用户受益信息对象；用于保存,由retailDetailOtherList分解而来
  	private List<SmConsumerProfit> smConsumerProfitList;
  	
  	//月度结算明细信息 列表，用于保存,由retailDetailOtherList分解而来
  	private List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList;
  	
  	private String settleId;	//结算id
	private String ym;		//年月 yyyy-MM格式
	
	
	
	
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
	public List<RetailDetailOther> getRetailDetailOtherList() {
		return retailDetailOtherList;
	}
	public void setRetailDetailOtherList(List<RetailDetailOther> retailDetailOtherList) {
		this.retailDetailOtherList = retailDetailOtherList;
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