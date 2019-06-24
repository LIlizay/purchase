package com.hhwy.purchaseweb.bid.phmdealinfo.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * 
 * <b>类 名 称：</b>PhmDealInfoDetail<br/>
 * <b>类 描 述：成交信息录入详情类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年9月19日 下午4:01:48<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
/**
 * @author Mrs.Li
 *
 */
public class PhmDealInfoDetail {
	
	private String id;
	
    private String ym;			//年月
    
    private String planId;		//计划id
  
    private String reportId;	//交易申报明细id
    
    private String producerId;	//电厂id
    
    private int stage;			//申报的段数（第几段)
    
    private java.math.BigDecimal reportPq; 	//申报电量
    
    private java.math.BigDecimal reportPrc;	//申报电价
    
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
	private String voltCodeName;			//电压等级
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_attornType" ,field="attornTypeName")
	private String attornType;	//合同转让方向
	private String attornTypeName;
	
    private java.math.BigDecimal dealPq;	//成交电量
    
    private java.math.BigDecimal dealPrc;	//成交电价
    
    private String elecName;				//电厂名称
    
    private String traderName;				//交易对象
    
    private String geneName;				//机组名称
    
    private String generatorId;				//机组id
   
  //------------------------------------------------------以下为山西、辽宁 交易申报列表字段 -----------------------------------------------------
    private String consId;					//用户id
    
    private String consName;				//用户名称
    
    @PropertyAnnotation(cacheType = ConstantsStatus.CACHE_TYPE_PCODE, domain = ConstantsStatus.PCODE_DOMAIN_SELLING, key = "sell_elecTypeCode", field = "elecTypeName")
	private String elecTypeCode;	
	private String elecTypeName;			//用电类别
    
	private java.math.BigDecimal deliveryPrc;				//结算电价
	
	private String subitemFlag;				//是否年分项

	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public java.math.BigDecimal getReportPq() {
		return reportPq;
	}

	public void setReportPq(java.math.BigDecimal reportPq) {
		this.reportPq = reportPq;
	}

	public java.math.BigDecimal getReportPrc() {
		return reportPrc;
	}

	public void setReportPrc(java.math.BigDecimal reportPrc) {
		this.reportPrc = reportPrc;
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

	public String getAttornType() {
		return attornType;
	}

	public void setAttornType(String attornType) {
		this.attornType = attornType;
	}

	public java.math.BigDecimal getDealPq() {
		return dealPq;
	}

	public void setDealPq(java.math.BigDecimal dealPq) {
		this.dealPq = dealPq;
	}

	public java.math.BigDecimal getDealPrc() {
		return dealPrc;
	}

	public void setDealPrc(java.math.BigDecimal dealPrc) {
		this.dealPrc = dealPrc;
	}

	public String getElecName() {
		return elecName;
	}

	public void setElecName(String elecName) {
		this.elecName = elecName;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getElecTypeName() {
		return elecTypeName;
	}

	public void setElecTypeName(String elecTypeName) {
		this.elecTypeName = elecTypeName;
	}

	public BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public String getAttornTypeName() {
		return attornTypeName;
	}

	public void setAttornTypeName(String attornTypeName) {
		this.attornTypeName = attornTypeName;
	}

	public String getSubitemFlag() {
		return subitemFlag;
	}

	public void setSubitemFlag(String subitemFlag) {
		this.subitemFlag = subitemFlag;
	}

	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}
	
}
