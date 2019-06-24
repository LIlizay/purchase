package com.hhwy.purchaseweb.forecast.phftradingcenter.domain;
import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.PhfElecReport;
import com.hhwy.purchase.domain.PhfPurcReport;
import com.hhwy.purchase.domain.PhfTradingCenter;

/**
 * PhfTradingCenterVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhfTradingCenterVo extends PagingProperty{

	private PhfTradingCenter phfTradingCenter = BaseModel.getModel(PhfTradingCenter.class);
	
	List<PhfElecReport> phfElecReportList=new ArrayList<PhfElecReport>();
	
	List<PhfPurcReport> phfPurcReportList=new ArrayList<PhfPurcReport>();
	
	private String startTime;
	
	private String endTime;
	
	public PhfTradingCenter getPhfTradingCenter(){
		return phfTradingCenter;
	}
	
	public void setPhfTradingCenter(PhfTradingCenter phfTradingCenter){
		this.phfTradingCenter = phfTradingCenter;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public List<PhfElecReport> getPhfElecReportList() {
		return phfElecReportList;
	}

	public void setPhfElecReportList(List<PhfElecReport> phfElecReportList) {
		this.phfElecReportList = phfElecReportList;
	}

	public List<PhfPurcReport> getPhfPurcReportList() {
		return phfPurcReportList;
	}

	public void setPhfPurcReportList(List<PhfPurcReport> phfPurcReportList) {
		this.phfPurcReportList = phfPurcReportList;
	}
}