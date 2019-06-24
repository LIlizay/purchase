package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconsearlywarning.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmConsEarlyWarning;
import com.hhwy.selling.domain.SmConsWarningInfo;

/**
 * SmConsEarlyWarningVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmConsEarlyWarningVo extends PagingProperty{
	private String consName;	//用户名称

	private SmConsEarlyWarning smConsEarlyWarning = BaseModel.getModel(SmConsEarlyWarning.class);
	
	private List<SmConsWarningInfo> smConsWarningInfoList = new ArrayList<SmConsWarningInfo>();
	
	private List<SmConsWarningInfo> updateList = new ArrayList<SmConsWarningInfo>();
	
	private List<SmConsWarningInfo> addList = new ArrayList<SmConsWarningInfo>();
	
	private String[] delIds = new String[]{};
	
	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public List<SmConsWarningInfo> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<SmConsWarningInfo> updateList) {
		this.updateList = updateList;
	}

	public List<SmConsWarningInfo> getAddList() {
		return addList;
	}

	public void setAddList(List<SmConsWarningInfo> addList) {
		this.addList = addList;
	}

	public String[] getDelIds() {
		return delIds;
	}

	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}

	public List<SmConsWarningInfo> getSmConsWarningInfoList() {
		return smConsWarningInfoList;
	}

	public void setSmConsWarningInfoList(
			List<SmConsWarningInfo> smConsWarningInfoList) {
		this.smConsWarningInfoList = smConsWarningInfoList;
	}

	public SmConsEarlyWarning getSmConsEarlyWarning(){
		return smConsEarlyWarning;
	}
	
	public void setSmConsEarlyWarning(SmConsEarlyWarning smConsEarlyWarning){
		this.smConsEarlyWarning = smConsEarlyWarning;
	}
}