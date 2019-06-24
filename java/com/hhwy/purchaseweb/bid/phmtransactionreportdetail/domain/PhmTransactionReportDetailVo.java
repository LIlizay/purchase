package com.hhwy.purchaseweb.bid.phmtransactionreportdetail.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhmTransactionReportDetail;

 /**
 * <b>类 名 称：</b>PhmTransactionReportDetailVo<br/>
 * <b>类 描 述：</b><br/>交易申报明细vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月9日 下午3:25:12<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class PhmTransactionReportDetailVo extends PagingProperty{

	private PhmTransactionReportDetail phmTransactionReportDetail = BaseModel.getModel(PhmTransactionReportDetail.class);
	
	private List<PhmTransactionReportDetail> insertList;
	
	private List<PhmTransactionReportDetail> updateList;
	
	private String[] ids;
	
	private String tradePeriod;
	
	//月度购电计划id，用于查询委托电量明细
	//竞价交易申报的交易申报明细中的 委托电量明细中用到，   用于获取用户信息及当月的委托电量
	private String planId;
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public PhmTransactionReportDetail getPhmTransactionReportDetail(){
		return phmTransactionReportDetail;
	}
	
	public void setPhmTransactionReportDetail(PhmTransactionReportDetail phmTransactionReportDetail){
		this.phmTransactionReportDetail = phmTransactionReportDetail;
	}

	public List<PhmTransactionReportDetail> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<PhmTransactionReportDetail> insertList) {
		this.insertList = insertList;
	}

	public List<PhmTransactionReportDetail> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<PhmTransactionReportDetail> updateList) {
		this.updateList = updateList;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public String getTradePeriod() {
		return tradePeriod;
	}

	public void setTradePeriod(String tradePeriod) {
		this.tradePeriod = tradePeriod;
	}
	
}