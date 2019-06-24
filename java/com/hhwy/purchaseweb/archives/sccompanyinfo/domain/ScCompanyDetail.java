package com.hhwy.purchaseweb.archives.sccompanyinfo.domain;

/**
 * <b>类 名 称：</b>ScCompanyDetail<br/>
 * <b>类 描 述：</b><br/>
 * 售电公司的detail类 <b>创 建 人：</b>xucong<br/>
 * <b>修 改 人：</b>xucong<br/>
 * <b>修改时间：</b>2016年11月23日 下午5:37:49<br/>
 * <b>修改备注：</b><br/>
 * 
 * @version 1.0.0<br/>
 */
public class ScCompanyDetail {

	private String id;

	// 售电公司名称
	private String companyName;

	// 法定代表人
	private String legalAgent;

	// 授权代理人
	private String authAgent;

	// 法定地址
	private String addr;

	// 电话
	private String mobile;

	// 传真
	private String fax;

	// 邮编
	private String postalcode;

	// 开户银行
	private String bankName;

	// 银行帐号
	private String bankNo;

	// 税务登记证号
	private String vatNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLegalAgent() {
		return legalAgent;
	}

	public void setLegalAgent(String legalAgent) {
		this.legalAgent = legalAgent;
	}

	public String getAuthAgent() {
		return authAgent;
	}

	public void setAuthAgent(String authAgent) {
		this.authAgent = authAgent;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

}