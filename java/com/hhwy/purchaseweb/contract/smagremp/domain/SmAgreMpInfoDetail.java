package com.hhwy.purchaseweb.contract.smagremp.domain;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * SmAgreMpInfoDetail
 * @author hhwy
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class SmAgreMpInfoDetail{
	
	/**
	 * 计量点信息表中计量点实体id
	 */
	private String id;
	
	/**
	 * 计量点信息表中计量点实体id
	 */
	private String agreId;
	
	/**
	 * 合同-计量点关系表中计量点实体id
	 */
	private String mpId;
	
    /**
     * 用户实体ID
     */
    private String consId;
    
    /**
     * 计量点编号
     */ 
    private String mpNo;
    
    /**
     * 电压等级
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    
    /**
     * 电压等级
     */
    private String voltCodeName;

    /**
     * 电能表编号
     */
    private String meterNo;
    
    /**
     * 是否执行峰谷0否,1是
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_flag" ,field="fluctuatesFlagName")
    private String fluctuatesFlag;
    
    /**
     * 是否执行峰谷0否,1是
     */
    private String fluctuatesFlagName;
    
    /**
     * 执行电价
     */ 
    private String catPrc;
    
    /**
     * 执行电价
     */ 
    private String catPrcName;
    
    /**
     * 用电类别
     */
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecSortName")
    private String elecSort;
    
    /**
     * 用电类别
     */
    private String elecSortName;
    
    /**
     * 代理电量
     */
    private String proxyPq;
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreId() {
		return agreId;
	}

	public void setAgreId(String agreId) {
		this.agreId = agreId;
	}

	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getMpNo(){
        return mpNo;
    }
    
    public void setMpNo(String mpNo){
        this.mpNo = mpNo;
    }
    
    public String getVoltCode(){
        return voltCode;
    }
    
    public void setVoltCode(String voltCode){
        this.voltCode = voltCode;
    }
    
    public String getMeterNo(){
        return meterNo;
    }
    
    public void setMeterNo(String meterNo){
        this.meterNo = meterNo;
    }
    
    public String getFluctuatesFlag(){
        return fluctuatesFlag;
    }
    
    public void setFluctuatesFlag(String fluctuatesFlag){
        this.fluctuatesFlag = fluctuatesFlag;
    }
    
    public String getCatPrc(){
        return catPrc;
    }
    
    public void setCatPrc(String catPrc){
        this.catPrc = catPrc;
    }
    
    public String getElecSort(){
        return elecSort;
    }
    
    public void setElecSort(String elecSort){
        this.elecSort = elecSort;
    }

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public String getFluctuatesFlagName() {
		return fluctuatesFlagName;
	}

	public void setFluctuatesFlagName(String fluctuatesFlagName) {
		this.fluctuatesFlagName = fluctuatesFlagName;
	}

	public String getCatPrcName() {
		return catPrcName;
	}

	public void setCatPrcName(String catPrcName) {
		this.catPrcName = catPrcName;
	}

	public String getElecSortName() {
		return elecSortName;
	}

	public void setElecSortName(String elecSortName) {
		this.elecSortName = elecSortName;
	}

	public String getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(String proxyPq) {
		this.proxyPq = proxyPq;
	}
    
}