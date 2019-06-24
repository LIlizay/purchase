package com.hhwy.purchaseweb.login.swconsinfo.domain;

import com.hhwy.business.core.modelutil.PagingProperty;

public class RelaVo extends PagingProperty{

    private String userName;
    
    private String loginName;
    //关联状态
    private String relateStatus;
    //组织id
    private String orgNo;
    //登录用户id
    private String userId;
    
    public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

	public String getRelateStatus() {
		return relateStatus;
	}

	public void setRelateStatus(String relateStatus) {
		this.relateStatus = relateStatus;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
