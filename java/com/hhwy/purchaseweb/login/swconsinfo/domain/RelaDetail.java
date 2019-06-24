package com.hhwy.purchaseweb.login.swconsinfo.domain;

public class RelaDetail {

    private String id;
    
    /** 用户名称**/
    private String userName;
    
    /** 用户id**/
    private String consId;
    
    /** 联系人id**/
    private String contId;
    
    /** 联系电话**/
    private String phone;
    
    /** 用户账号**/
    private String loginName;
    
    /** 用户档案名称**/
    private String consName;
    
    /** 是否关联档案**/
    private String relaFlag;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getConsName() {
        return consName;
    }

    public void setConsName(String consName) {
        this.consName = consName;
    }

    public String getRelaFlag() {
        return relaFlag;
    }

    public void setRelaFlag(String relaFlag) {
        this.relaFlag = relaFlag;
    }

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getContId() {
		return contId;
	}

	public void setContId(String contId) {
		this.contId = contId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
