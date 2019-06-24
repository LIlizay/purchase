package com.hhwy.purchaseweb.bigdata.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

 /**
 * <b>类 名 称：</b>TpowerPriceUser<br/>
 * <b>类 描 述：</b><br/> 电力用户（售电公司）信息表  ，大数据用
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年4月24日 下午4:49:50<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class TpowerPriceUser{
	private String id;
	 
	private String createUser;
	 
	private String updateUser;
	 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp createTime;
	 
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp updateTime;
	
    @PropertyDesc("逻辑删除标志")
    private String deleteFlag = "0";
    
    @PropertyDesc("版本标记")
    private Integer version;
    
    @PropertyDesc("用户编号")
    private String consNo;
    
    @PropertyDesc("用户名称")
    private String consName;
    
    @PropertyDesc("行业编号")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_industryType" ,field="tradeName")
    private String tradeCode;
    @PropertyDesc("行业")
    private String tradeName;
    
    @PropertyDesc("用电类别编号")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeName")
    private String elecTypeCode;
    @PropertyDesc("用电类别")
    private String elecTypeName;
    
    @PropertyDesc("所属地区编码")
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_COMMON,key="provinceCode",field="region")
    private String regionCode;
    @PropertyDesc("所属地区")
    private String region;
    
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public String getDeleteFlag(){
        return deleteFlag;
    }
    
    public void setDeleteFlag(String deleteFlag){
        this.deleteFlag = deleteFlag;
    }
    
    public Integer getVersion(){
        return version;
    }
    
    public void setVersion(Integer version){
        this.version = version;
    }
    
    public String getConsNo(){
        return consNo;
    }
    
    public void setConsNo(String consNo){
        this.consNo = consNo;
    }
    
    public String getConsName(){
        return consName;
    }
    
    public void setConsName(String consName){
        this.consName = consName;
    }
    
    public String getTradeCode(){
        return tradeCode;
    }
    
    public void setTradeCode(String tradeCode){
        this.tradeCode = tradeCode;
    }
    
    public String getTradeName(){
        return tradeName;
    }
    
    public void setTradeName(String tradeName){
        this.tradeName = tradeName;
    }
    
    public String getElecTypeCode(){
        return elecTypeCode;
    }
    
    public void setElecTypeCode(String elecTypeCode){
        this.elecTypeCode = elecTypeCode;
    }
    
    public String getElecTypeName(){
        return elecTypeName;
    }
    
    public void setElecTypeName(String elecTypeName){
        this.elecTypeName = elecTypeName;
    }
    
    public String getRegion(){
        return region;
    }
    
    public void setRegion(String region){
        this.region = region;
    }
    
    public String getRegionCode(){
        return regionCode;
    }
    
    public void setRegionCode(String regionCode){
        this.regionCode = regionCode;
    }
    
}