package com.hhwy.purchaseweb.arithmetic.bidarithmetic.domain;

public class BidSearchVo {

    /**
     * 年月
     */
    private String ym;
    
    /**
     * 省份
     */
    private String provinceCode;

	/**
     * 模拟方案标识
     */
    private String schemeId;

    /**
     * 分段名称
     */
    private String sectionName;

    /**
     * 用户中标电价
     */
    private String consBidPrc;

    /**
     * 发电企业中标电价
     */
    private String enteBidPrc;
    
    public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getConsBidPrc() {
		return consBidPrc;
	}

	public void setConsBidPrc(String consBidPrc) {
		this.consBidPrc = consBidPrc;
	}

	public String getEnteBidPrc() {
		return enteBidPrc;
	}

	public void setEnteBidPrc(String enteBidPrc) {
		this.enteBidPrc = enteBidPrc;
	}
	
}
