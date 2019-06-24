package com.hhwy.purchaseweb.archives.scconsumerinfo.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.selling.domain.ScConsDate;
import com.hhwy.selling.domain.ScConsumerInfo;
import com.hhwy.selling.domain.ScContactsInfo;
import com.hhwy.selling.domain.ScMpInfo;

/**
 * ScConsumerInfoVo
 * @author xucong
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ScConsumerInfoVo extends PagingProperty{
	
	/**
	 * 电压等级
	 */
	private List<String> splitVoltCode; 

	/**
	 * 用电用户信息
	 */
	private ScConsumerInfo scConsumerInfo = BaseModel.getModel(ScConsumerInfo.class);
	
	
	/**
	 * 计量点信息
	 */
	private List<ScMpInfo> mpList = new ArrayList<ScMpInfo>();
	
	/**
	 * 联系人信息
	 */
	private ScContactsInfo scContactsInfo = BaseModel.getModel(ScContactsInfo.class);
	
	/**
	 * 计量点实体ID集合记录（用于更新时比对数据数量的变化）
	 */
//	private String ids;
	
	/**
	 * 父级用户信息
	 */
	private ScConsumerInfoDetail parentConsInfoDetail;
	
	//用户id数组，用来获取这些用户的子用户，在删除用户时验证用
	private String[] consIds;
	
    @PropertyDesc("父级用户id")
    private String id;
	
    //用户例日信息		--wangzelu
	private List<ScConsDate> usuallyDateList;
    //例日汉语日期，例：5日
	private String usuallyDateName;
    
    
    
    
    
    
	public String getUsuallyDateName() {
		return usuallyDateName;
	}

	public void setUsuallyDateName(String usuallyDateName) {
		this.usuallyDateName = usuallyDateName;
	}

	public List<ScConsDate> getUsuallyDateList() {
		return usuallyDateList;
	}

	public void setUsuallyDateList(List<ScConsDate> usuallyDateList) {
		this.usuallyDateList = usuallyDateList;
	}

	public String[] getConsIds() {
		return consIds;
	}

	public void setConsIds(String[] consIds) {
		this.consIds = consIds;
	}

	public ScConsumerInfoDetail getParentConsInfoDetail() {
		return parentConsInfoDetail;
	}

	public void setParentConsInfoDetail(ScConsumerInfoDetail parentConsInfoDetail) {
		this.parentConsInfoDetail = parentConsInfoDetail;
	}

	public ScContactsInfo getScContactsInfo() {
		return scContactsInfo;
	}

	public void setScContactsInfo(ScContactsInfo scContactsInfo) {
		this.scContactsInfo = scContactsInfo;
	}

	public List<ScMpInfo> getMpList() {
		return mpList;
	}

	public void setMpList(List<ScMpInfo> mpList) {
		this.mpList = mpList;
	}

	public ScConsumerInfo getScConsumerInfo(){
		return scConsumerInfo;
	}
	
	public void setScConsumerInfo(ScConsumerInfo scConsumerInfo){
		this.scConsumerInfo = scConsumerInfo;
	}

	/*public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
	*/
	public List<String> getSplitVoltCode() {
		return splitVoltCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setSplitVoltCode(List<String> splitVoltCode) {
		this.splitVoltCode = splitVoltCode;
	}
}