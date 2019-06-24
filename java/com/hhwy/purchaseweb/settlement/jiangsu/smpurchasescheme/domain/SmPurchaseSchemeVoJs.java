package com.hhwy.purchaseweb.settlement.jiangsu.smpurchasescheme.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmCompanyCostDetail;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchase.domain.SmConsDeviationAmt;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchase.domain.SmPurchaseScheme;
import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;

 /**
 * <b>类 名 称：</b>SmPurchaseSchemeVoJs<br/>
 * <b>类 描 述：</b><br/>	江苏的月度结算方案vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年3月30日 下午3:49:53<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmPurchaseSchemeVoJs extends PagingProperty{

	private SmPurchaseScheme smPurchaseScheme = BaseModel.getModel(SmPurchaseScheme.class);
	
	private List<SmPurchaseSchemeDetailDetail> smPurchaseSchemeDetailDetailList =new ArrayList<SmPurchaseSchemeDetailDetail>();
	
	//月购电情况及本企业总收益实体对象
	//也用于edit页面初始化时返回前台数据
	private SmCompanyProfit smCompanyProfit;
	
	//用户受益信息对象；用于保存
	private List<SmConsumerProfit> smConsumerProfitList;
	
    //批发市场购电支出明细；用于保存
  	private List<SmCompanyCostDetail> smCompanyCostDetailList;
  	
  	//------------------以上属性不仅是用于前后台传递，更用于保存
  	
  	private String settleId;	//结算id
	private String ym;		//年月 yyyy-MM格式
  	
  	//用户月度电量 用于计算用户受益，前台传后台，后台传前台，然后在前台组装成smConsumerProfitList用于保存
    private List<SmConsumerProfitDetail> smConsumerProfitDetailList;
  	
	//月度结算用户偏差惩罚费用信息 列表，用于保存
    private List<SmConsDeviationAmt> smConsDeviationAmtList;
    //月度结算明细信息 列表，用于保存
    private List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList;
    
    
    
    
    
	public List<SmPurchaseSchemeDetail> getSmPurchaseSchemeDetailList() {
		return smPurchaseSchemeDetailList;
	}

	public void setSmPurchaseSchemeDetailList(List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList) {
		this.smPurchaseSchemeDetailList = smPurchaseSchemeDetailList;
	}

	public List<SmConsDeviationAmt> getSmConsDeviationAmtList() {
		return smConsDeviationAmtList;
	}

	public void setSmConsDeviationAmtList(List<SmConsDeviationAmt> smConsDeviationAmtList) {
		this.smConsDeviationAmtList = smConsDeviationAmtList;
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

	public SmPurchaseScheme getSmPurchaseScheme(){
		return smPurchaseScheme;
	}
	
	public void setSmPurchaseScheme(SmPurchaseScheme smPurchaseScheme){
		this.smPurchaseScheme = smPurchaseScheme;
	}

	public List<SmPurchaseSchemeDetailDetail> getSmPurchaseSchemeDetailDetailList() {
		return smPurchaseSchemeDetailDetailList;
	}

	public void setSmPurchaseSchemeDetailDetailList(List<SmPurchaseSchemeDetailDetail> smPurchaseSchemeDetailDetailList) {
		this.smPurchaseSchemeDetailDetailList = smPurchaseSchemeDetailDetailList;
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

	public List<SmCompanyCostDetail> getSmCompanyCostDetailList() {
		return smCompanyCostDetailList;
	}

	public void setSmCompanyCostDetailList(List<SmCompanyCostDetail> smCompanyCostDetailList) {
		this.smCompanyCostDetailList = smCompanyCostDetailList;
	}

	public List<SmConsumerProfitDetail> getSmConsumerProfitDetailList() {
		return smConsumerProfitDetailList;
	}

	public void setSmConsumerProfitDetailList(List<SmConsumerProfitDetail> smConsumerProfitDetailList) {
		this.smConsumerProfitDetailList = smConsumerProfitDetailList;
	}
}