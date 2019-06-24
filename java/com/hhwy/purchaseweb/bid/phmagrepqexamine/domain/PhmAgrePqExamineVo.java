package com.hhwy.purchaseweb.bid.phmagrepqexamine.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhmAgrePqExamine;
import com.hhwy.purchaseweb.bid.phmpurchaseplanmonth.domain.PhmSubMonthDetail;

/**
 * PhmAgrePqExamineVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmAgrePqExamineVo extends PagingProperty{

	private PhmAgrePqExamine phmAgrePqExamine = BaseModel.getModel(PhmAgrePqExamine.class);
	
	private String planId;
	
	private String ym;
	
	private boolean flag;
	
	private String remindInfo;
	
	private String title;
	
	public List<PhmAgrePqExamineDetail> phmAgrePqExamineDetailList = new ArrayList<>();
	
	private List<PhmSubMonthDetail> subMonthDetail = new ArrayList<PhmSubMonthDetail>();
	
	//存月度负荷预测的数据
	private List<Map<String, Object>> forecastData;
	
	//年度交易委托电量分月信息(是月购电计划中“委托电量审核”环节“年度交易委托电量分月信息”表中数据)
	List<YearPlanProxyPqDetail> yearPlanProxyPqDetailList;
	
	
	
	
	
	
	
	
	public List<YearPlanProxyPqDetail> getYearPlanProxyPqDetailList() {
		return yearPlanProxyPqDetailList;
	}

	public void setYearPlanProxyPqDetailList(List<YearPlanProxyPqDetail> yearPlanProxyPqDetailList) {
		this.yearPlanProxyPqDetailList = yearPlanProxyPqDetailList;
	}

	public String getRemindInfo() {
		return remindInfo;
	}

	public void setRemindInfo(String remindInfo) {
		this.remindInfo = remindInfo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public PhmAgrePqExamine getPhmAgrePqExamine(){
		return phmAgrePqExamine;
	}
	
	public void setPhmAgrePqExamine(PhmAgrePqExamine phmAgrePqExamine){
		this.phmAgrePqExamine = phmAgrePqExamine;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public List<PhmAgrePqExamineDetail> getPhmAgrePqExamineDetailList() {
		return phmAgrePqExamineDetailList;
	}

	public void setPhmAgrePqExamineDetailList(
			List<PhmAgrePqExamineDetail> phmAgrePqExamineDetailList) {
		this.phmAgrePqExamineDetailList = phmAgrePqExamineDetailList;
	}

	public List<Map<String, Object>> getForecastData() {
		return forecastData;
	}

	public void setForecastData(List<Map<String, Object>> forecastData) {
		this.forecastData = forecastData;
	}

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public List<PhmSubMonthDetail> getSubMonthDetail() {
		return subMonthDetail;
	}

	public void setSubMonthDetail(List<PhmSubMonthDetail> subMonthDetail) {
		this.subMonthDetail = subMonthDetail;
	}
}