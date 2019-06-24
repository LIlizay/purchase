package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smdeviationearlywarning.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmDeviationEarlyWarning;
import com.hhwy.selling.domain.SmWarningInfo;

/**
 * SmDeviationEarlyWarningVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmDeviationEarlyWarningVo extends PagingProperty{

	private SmDeviationEarlyWarning smDeviationEarlyWarning = BaseModel.getModel(SmDeviationEarlyWarning.class);
	
	private List<SmWarningInfo> smWarningInfoList = new ArrayList<SmWarningInfo>();
	
	private List<SmWarningInfo> updateList = new ArrayList<SmWarningInfo>();
	
	private List<SmWarningInfo> addList = new ArrayList<SmWarningInfo>();
	
	private String[] delIds = new String[]{};
	
	public List<SmWarningInfo> getUpdateList() {
		return updateList;
	}

	public void setUpdateList(List<SmWarningInfo> updateList) {
		this.updateList = updateList;
	}

	public List<SmWarningInfo> getAddList() {
		return addList;
	}

	public void setAddList(List<SmWarningInfo> addList) {
		this.addList = addList;
	}

	public String[] getDelIds() {
		return delIds;
	}

	public void setDelIds(String[] delIds) {
		this.delIds = delIds;
	}

	public List<SmWarningInfo> getSmWarningInfoList() {
		return smWarningInfoList;
	}

	public void setSmWarningInfoList(List<SmWarningInfo> smWarningInfoList) {
		this.smWarningInfoList = smWarningInfoList;
	}

	public SmDeviationEarlyWarning getSmDeviationEarlyWarning(){
		return smDeviationEarlyWarning;
	}
	
	public void setSmDeviationEarlyWarning(SmDeviationEarlyWarning smDeviationEarlyWarning){
		this.smDeviationEarlyWarning = smDeviationEarlyWarning;
	}
}