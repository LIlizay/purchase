package com.hhwy.purchaseweb.crm.smcalcconfigure.domain;

import com.hhwy.framework.annotation.PropertyDesc;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class SmCalcConfigureDetail {
	
	/** 实体id */
	private String id; 
	
	@PropertyDesc("省份(编码)")
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode" ,field="provinceName")
    private String province;
	
	/** 省份名称 */
	private String provinceName;
    
    @PropertyDesc("售电公司(库名)")
    private String sellDb;
    
    /** 库名名称 */
    private String sellDbName;
    
    @PropertyDesc("算法名称")
    private String calcName;
    
    @PropertyDesc("算法说明)")
    private String calcContent;
    
    @PropertyDesc("脚本选择(编码)")
    private String calcCode;
    
    /** 脚本名称 */
    private String calcCodeName;
    
    @PropertyDesc("组织机构")
    private String orgNo;
    
    @PropertyDesc("脚本参数(json)")
    private String calcParam;
    
    @PropertyDesc("状态")
    private String status;
    
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCalcParam() {
		return calcParam;
	}

	public void setCalcParam(String calcParam) {
		this.calcParam = calcParam;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getSellDbName() {
		return sellDbName;
	}

	public void setSellDbName(String sellDbName) {
		this.sellDbName = sellDbName;
	}

	public String getCalcCodeName() {
		return calcCodeName;
	}

	public void setCalcCodeName(String calcCodeName) {
		this.calcCodeName = calcCodeName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProvince(){
        return province;
    }
    
    public void setProvince(String province){
        this.province = province;
    }
    
    public String getSellDb(){
        return sellDb;
    }
    
    public void setSellDb(String sellDb){
        this.sellDb = sellDb;
    }
    
    public String getCalcName(){
        return calcName;
    }
    
    public void setCalcName(String calcName){
        this.calcName = calcName;
    }
    
    public String getCalcContent(){
        return calcContent;
    }
    
    public void setCalcContent(String calcContent){
        this.calcContent = calcContent;
    }
    
    public String getCalcCode(){
        return calcCode;
    }
    
    public void setCalcCode(String calcCode){
        this.calcCode = calcCode;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}
