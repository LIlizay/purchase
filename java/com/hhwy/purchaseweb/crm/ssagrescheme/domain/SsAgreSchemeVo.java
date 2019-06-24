package com.hhwy.purchaseweb.crm.ssagrescheme.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SsAgreScheme;

/**
 * SsAgreSchemeVo
 * 
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SsAgreSchemeVo extends PagingProperty {
	// （合同辅助设计）实体对象
	private SsAgreScheme ssAgreScheme = BaseModel.getModel(SsAgreScheme.class);
	// 删除的方案的id
	private String[] removeIds;
	// 一个客户id对应的所有的方案数据
	private List<SsAgreScheme> ssAgreSchemeList;
	// service的getSByPage()方法取的分组客户Id
	private List<String> consIdlist;
	// 查询子节点时父节点的id
	private String id;

	//登录用户id
	private String loginUserId;
	
	
	
	
	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public SsAgreScheme getSsAgreScheme() {
		return ssAgreScheme;
	}

	public void setSsAgreScheme(SsAgreScheme ssAgreScheme) {
		this.ssAgreScheme = ssAgreScheme;
	}

	public String[] getRemoveIds() {
		return removeIds;
	}

	public void setRemoveIds(String[] removeIds) {
		this.removeIds = removeIds;
	}

	public List<SsAgreScheme> getSsAgreSchemeList() {
		return ssAgreSchemeList;
	}

	public void setSsAgreSchemeList(List<SsAgreScheme> ssAgreSchemeList) {
		this.ssAgreSchemeList = ssAgreSchemeList;
	}

	public List<String> getConsIdlist() {
		return consIdlist;
	}

	public void setConsIdlist(List<String> consIdlist) {
		this.consIdlist = consIdlist;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}