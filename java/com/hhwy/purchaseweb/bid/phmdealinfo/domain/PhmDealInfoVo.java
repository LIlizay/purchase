package com.hhwy.purchaseweb.bid.phmdealinfo.domain;

import java.util.List;

import com.hhwy.purchase.domain.PhmDealInfo;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmDealInfoVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmDealInfoVo extends PagingProperty{

	private PhmDealInfo phmDealInfo = BaseModel.getModel(PhmDealInfo.class);
	
	/**
	 * 购电计划id
	 */
	private String id;
	
	/**
	 * 购电计划年月
	 */
	private String ym;
	
	private boolean flag;
	
	private String consId;
	
	/**
	 * 购电计划月份
	 */
	private String month;
	
	private String[] consIds;
	
	private String tradePeriod;
	
	private String[] ids;
	
	private List<PhmDealInfo> insertList;
	
	private List<PhmDealInfo> updateList;
	
	private List<MonthDealInfoDetail> monthList;
	
	private List<PhmDealInfoDetail> slList;
	
	
	public PhmDealInfo getPhmDealInfo(){
		return phmDealInfo;
	}
	
	public void setPhmDealInfo(PhmDealInfo phmDealInfo){
		this.phmDealInfo = phmDealInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String[] getConsIds() {
		return consIds;
	}

	public void setConsIds(String[] consIds) {
		this.consIds = consIds;
	}

	public List<PhmDealInfo> getInsertList() {
		return insertList;
	}

	public void setInsertList(List<PhmDealInfo> insertList) {
		this.insertList = insertList;
	}

	public List<PhmDealInfo> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<PhmDealInfo> updateList) {
		this.updateList = updateList;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}

	public List<MonthDealInfoDetail> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<MonthDealInfoDetail> monthList) {
		this.monthList = monthList;
	}

	public String getTradePeriod() {
		return tradePeriod;
	}

	public void setTradePeriod(String tradePeriod) {
		this.tradePeriod = tradePeriod;
	}

	public List<PhmDealInfoDetail> getSlList() {
		return slList;
	}

	public void setSlList(List<PhmDealInfoDetail> slList) {
		this.slList = slList;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}
}