package com.hhwy.purchaseweb.deviationcheck.scmpcount.domain;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.selling.domain.ScMpCount;

 /**
 * <b>类 名 称：</b>ScMpCountDetail<br/>
 * <b>类 描 述：</b><br/>		表计示数的detail类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月13日 下午3:36:47<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScMpCountDetail{
	
	//以下5个在edit和detail页面列表中用到
	//计量点id
    private String mpId;
    
    //抄表示数
    private double meterReadPq;
    
    //抄表时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8") 
    private Date meterReadDate;
	
    //是否是超标例日： 1：是， 0：否
    private String usuallyDateFlag;
	
    //yyyyMMdd格式
	private String readDateStr;
    
	/**
	 * 计量点信息表中计量点实体id，或者示数id（edit和detail页面列表中）
	 */
	private String id;
	
    private String consId;					//用户ID
	private String consName;				//用户名称
	
	private String mpName;					//计量点名称
    
    private String meterRate;				//电能表倍率
    private String meterDigits;				//电能表位数
    
    
    private double lastReadPq;				//最后一次抄表总示数
    
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8") 
    private String lastReadDate;			//最后一次抄表时间

	private String orgNo;
	
	private List<ScMpCount> scMpCountList;
	
	
    //计量点编号
    private String mpNo;
    
    //电能表编号
    private String meterNo;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    //用电类别
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;



    
    
    
    
    
    
    
    
    
    
    public String getReadDateStr() {
		return readDateStr;
	}

	public void setReadDateStr(String readDateStr) {
		this.readDateStr = readDateStr;
	}

	public String getMpId() {
		return mpId;
	}

	public void setMpId(String mpId) {
		this.mpId = mpId;
	}

	public double getMeterReadPq() {
		return meterReadPq;
	}

	public void setMeterReadPq(double meterReadPq) {
		this.meterReadPq = meterReadPq;
	}

	public Date getMeterReadDate() {
		return meterReadDate;
	}

	public void setMeterReadDate(Date meterReadDate) {
		this.meterReadDate = meterReadDate;
	}

	public String getUsuallyDateFlag() {
		return usuallyDateFlag;
	}

	public void setUsuallyDateFlag(String usuallyDateFlag) {
		this.usuallyDateFlag = usuallyDateFlag;
	}

	public String getConsName() {
  		return consName;
  	}

  	public void setConsName(String consName) {
  		this.consName = consName;
  	}
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMpName() {
		return mpName;
	}

	public void setMpName(String mpName) {
		this.mpName = mpName;
	}

	public String getMeterRate() {
		return meterRate;
	}

	public void setMeterRate(String meterRate) {
		this.meterRate = meterRate;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getMpNo() {
		return mpNo;
	}

	public void setMpNo(String mpNo) {
		this.mpNo = mpNo;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getVoltCodeName() {
		return voltCodeName;
	}

	public void setVoltCodeName(String voltCodeName) {
		this.voltCodeName = voltCodeName;
	}

	public String getMeterNo() {
		return meterNo;
	}

	public void setMeterNo(String meterNo) {
		this.meterNo = meterNo;
	}

    public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeCodeName() {
		return elecTypeCodeName;
	}

	public void setElecTypeCodeName(String elecTypeCodeName) {
		this.elecTypeCodeName = elecTypeCodeName;
	}

	public double getLastReadPq() {
		return lastReadPq;
	}

	public void setLastReadPq(double lastReadPq) {
		this.lastReadPq = lastReadPq;
	}

	public String getLastReadDate() {
		return lastReadDate;
	}

	public void setLastReadDate(String lastReadDate) {
		this.lastReadDate = lastReadDate;
	}

    public List<ScMpCount> getScMpCountList() {
		return scMpCountList;
	}

	public void setScMpCountList(List<ScMpCount> scMpCountList) {
		this.scMpCountList = scMpCountList;
	}

	public String getMeterDigits() {
		return meterDigits;
	}

	public void setMeterDigits(String meterDigits) {
		this.meterDigits = meterDigits;
	}

}