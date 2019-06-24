package com.hhwy.purchaseweb.settlement.base.smsettlementmonth.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmSettlementMonth;

 /**
 * <b>类 名 称：</b>SmSettlementMonthVo<br/>
 * <b>类 描 述：</b><br/>   结算主表的vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年5月26日 下午4:58:00<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmSettlementMonthVo extends PagingProperty{

	private SmSettlementMonth smSettlementMonth = BaseModel.getModel(SmSettlementMonth.class);
	
	//搜索用，开始年月和结束年月，yyyy-MM格式
	private String startYm;
	
	private String endYm;
	
	
	
	
	public SmSettlementMonth getSmSettlementMonth(){
		return smSettlementMonth;
	}
	
	public void setSmSettlementMonth(SmSettlementMonth smSettlementMonth){
		this.smSettlementMonth = smSettlementMonth;
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
}