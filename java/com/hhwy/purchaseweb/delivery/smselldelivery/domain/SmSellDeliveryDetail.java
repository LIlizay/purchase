package com.hhwy.purchaseweb.delivery.smselldelivery.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;
import com.hhwy.framework.annotation.PropertyDesc;

public class SmSellDeliveryDetail{

	private String id;
	
	//是否赔偿
	private String compPunishFlag;
    
    /**
     * 售电公司赔偿电价
     */
    private BigDecimal compLowerPrc;
    
    /**
     * 售电公司赔偿阈值
     */
    private BigDecimal compLowerThreshold;
    
    //正偏差惩罚类型
    private String upperPunishType;
    
    //负偏差惩罚类型
    private String lowerPunishType;
    
    /**
     * 用户正偏差惩罚阈值
     */
    private BigDecimal consUpperThreshold;
    
    /**
     * 用户负偏差惩罚阈值
     */
    private BigDecimal consLowerThreshold;
    
    /**
     * 用户正偏差惩罚电价
     */
    private BigDecimal consUpperPrc;
    
    /**
     * 用户负偏差惩罚电价
     */
    private BigDecimal consLowerPrc;
    
    /**
     * 用户正偏差惩罚比例
     */
    private BigDecimal consUpperProp;
    
    /**
     * 用户负偏差惩罚比例
     */
    private BigDecimal consLowerProp;
    
    /**
     * 用户正偏差惩罚基准
     */
    private String consUpperType;
    
    /**
     * 用户负偏差惩罚基准
     */
    private String consLowerType;
    
    /**
     * 园区折扣比
     */
    private BigDecimal izProp;
    
    /**  部门id  **/
    private String orgNo;
    
    /**  用户名  **/
    private String consName;
    
    /**  用户id  **/
    private String consId;
    
    /**  用电分类  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
    private String elecTypeCode;
    private String elecTypeCodeName;
    
    /**  电压等级  **/
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName;
    
    @PropertyDesc("竞价年月")
    private String ym;
    
    @PropertyDesc("月度合同电量")
    private BigDecimal agrePq;
    
    @PropertyDesc("成交电量")
    private BigDecimal bidPq;
    
    @PropertyDesc("实际用电量")
    private BigDecimal deliveryPq;
    
    @PropertyDesc("结算电量")
    private BigDecimal proxyPq;
    
    @PropertyDesc("偏差电量")
    private BigDecimal deviationPq;
    
    @PropertyDesc("电度电价")
    private BigDecimal kwhPrc;
    
    @PropertyDesc("政府基金及附加")
    private BigDecimal plPrc;
    
    @PropertyDesc("合同电价")
    private BigDecimal agrePrc;
    
    @PropertyDesc("输配电价") 
    private BigDecimal transPrc;
    
    @PropertyDesc("价差")
    private BigDecimal dValue;
    
    @PropertyDesc("用户购电利润")
    private BigDecimal consElecProfit;
    
    @PropertyDesc("补偿用户电费")
    private BigDecimal compensateAmt;
    
    @PropertyDesc("用户利润")
    private BigDecimal consProfit;
    
    @PropertyDesc("用户偏差考核电费") 
    private BigDecimal consCheckAwt;
    
    @PropertyDesc("偏差考核电量")
    private BigDecimal consCheckPq;
    
    @PropertyDesc("偏差考核电价") 
    private BigDecimal consCheckPrc;
    
    @PropertyDesc("赔偿用户电量")
    private BigDecimal compensatePq;
    
    @PropertyDesc("赔偿用户电价")
    private BigDecimal compensatePrc;
    
    //电度平时电价
    private BigDecimal kwhPlainPrc;
    
    //电度峰时电价
    private BigDecimal kwhPeakPrc;
    
    //电度谷时电价
    private BigDecimal kwhValleyPrc;
    
    //目录平时电价
    private BigDecimal cataPlainPrc;
    
    //目录峰时电价
    private BigDecimal cataPeakPrc;
    
    //目录谷时电价
    private BigDecimal cataValleyPrc;
    
    //实抄主表总电量
    private BigDecimal totalPq;
    
    //模峰电量
    private BigDecimal peakPq;
    
    //模平电量
    private BigDecimal plainPq;
    
    //模谷电量
    private BigDecimal valleyPq;
    
    //实抄主表目录电价
    private BigDecimal elecKwhTotalPrc;
    
    //模峰目录电价
    private BigDecimal elecKwhPeakPrc;
    
    //模平目录电价
    private BigDecimal elecKwhPlainPrc;
    
    //模谷目录电价
    private BigDecimal elecKwhValleyPrc;
    
    //是否分时 "01"不分时    "02"分时
    private String timeFlag;

    @PropertyDesc("状态('01'已计算，'02'已提交)")
    private String status;
    
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public BigDecimal getKwhPlainPrc() {
		return kwhPlainPrc;
	}

	public void setKwhPlainPrc(BigDecimal kwhPlainPrc) {
		this.kwhPlainPrc = kwhPlainPrc;
	}

	public BigDecimal getKwhPeakPrc() {
		return kwhPeakPrc;
	}

	public void setKwhPeakPrc(BigDecimal kwhPeakPrc) {
		this.kwhPeakPrc = kwhPeakPrc;
	}

	public BigDecimal getKwhValleyPrc() {
		return kwhValleyPrc;
	}

	public void setKwhValleyPrc(BigDecimal kwhValleyPrc) {
		this.kwhValleyPrc = kwhValleyPrc;
	}

	public BigDecimal getCataPlainPrc() {
		return cataPlainPrc;
	}

	public void setCataPlainPrc(BigDecimal cataPlainPrc) {
		this.cataPlainPrc = cataPlainPrc;
	}

	public BigDecimal getCataPeakPrc() {
		return cataPeakPrc;
	}

	public void setCataPeakPrc(BigDecimal cataPeakPrc) {
		this.cataPeakPrc = cataPeakPrc;
	}

	public BigDecimal getCataValleyPrc() {
		return cataValleyPrc;
	}

	public void setCataValleyPrc(BigDecimal cataValleyPrc) {
		this.cataValleyPrc = cataValleyPrc;
	}

	public BigDecimal getTotalPq() {
		return totalPq;
	}

	public void setTotalPq(BigDecimal totalPq) {
		this.totalPq = totalPq;
	}

	public BigDecimal getPeakPq() {
		return peakPq;
	}

	public void setPeakPq(BigDecimal peakPq) {
		this.peakPq = peakPq;
	}

	public BigDecimal getPlainPq() {
		return plainPq;
	}

	public void setPlainPq(BigDecimal plainPq) {
		this.plainPq = plainPq;
	}

	public BigDecimal getValleyPq() {
		return valleyPq;
	}

	public void setValleyPq(BigDecimal valleyPq) {
		this.valleyPq = valleyPq;
	}

	public BigDecimal getElecKwhTotalPrc() {
		return elecKwhTotalPrc;
	}

	public void setElecKwhTotalPrc(BigDecimal elecKwhTotalPrc) {
		this.elecKwhTotalPrc = elecKwhTotalPrc;
	}

	public BigDecimal getElecKwhPeakPrc() {
		return elecKwhPeakPrc;
	}

	public void setElecKwhPeakPrc(BigDecimal elecKwhPeakPrc) {
		this.elecKwhPeakPrc = elecKwhPeakPrc;
	}

	public BigDecimal getElecKwhPlainPrc() {
		return elecKwhPlainPrc;
	}

	public void setElecKwhPlainPrc(BigDecimal elecKwhPlainPrc) {
		this.elecKwhPlainPrc = elecKwhPlainPrc;
	}

	public BigDecimal getElecKwhValleyPrc() {
		return elecKwhValleyPrc;
	}

	public void setElecKwhValleyPrc(BigDecimal elecKwhValleyPrc) {
		this.elecKwhValleyPrc = elecKwhValleyPrc;
	}

	public String getTimeFlag() {
		return timeFlag;
	}

	public void setTimeFlag(String timeFlag) {
		this.timeFlag = timeFlag;
	}

	public BigDecimal getCompLowerPrc() {
		return compLowerPrc;
	}

	public void setCompLowerPrc(BigDecimal compLowerPrc) {
		this.compLowerPrc = compLowerPrc;
	}

	public BigDecimal getCompLowerThreshold() {
		return compLowerThreshold;
	}

	public void setCompLowerThreshold(BigDecimal compLowerThreshold) {
		this.compLowerThreshold = compLowerThreshold;
	}

	public BigDecimal getConsUpperThreshold() {
		return consUpperThreshold;
	}

	public void setConsUpperThreshold(BigDecimal consUpperThreshold) {
		this.consUpperThreshold = consUpperThreshold;
	}

	public BigDecimal getConsLowerThreshold() {
		return consLowerThreshold;
	}

	public void setConsLowerThreshold(BigDecimal consLowerThreshold) {
		this.consLowerThreshold = consLowerThreshold;
	}

	public BigDecimal getConsUpperPrc() {
		return consUpperPrc;
	}

	public void setConsUpperPrc(BigDecimal consUpperPrc) {
		this.consUpperPrc = consUpperPrc;
	}

	public BigDecimal getConsLowerPrc() {
		return consLowerPrc;
	}

	public void setConsLowerPrc(BigDecimal consLowerPrc) {
		this.consLowerPrc = consLowerPrc;
	}

	public BigDecimal getConsUpperProp() {
		return consUpperProp;
	}

	public void setConsUpperProp(BigDecimal consUpperProp) {
		this.consUpperProp = consUpperProp;
	}

	public BigDecimal getConsLowerProp() {
		return consLowerProp;
	}

	public void setConsLowerProp(BigDecimal consLowerProp) {
		this.consLowerProp = consLowerProp;
	}

	public String getConsUpperType() {
		return consUpperType;
	}

	public void setConsUpperType(String consUpperType) {
		this.consUpperType = consUpperType;
	}

	public String getConsLowerType() {
		return consLowerType;
	}

	public void setConsLowerType(String consLowerType) {
		this.consLowerType = consLowerType;
	}

	public BigDecimal getIzProp() {
		return izProp;
	}

	public void setIzProp(BigDecimal izProp) {
		this.izProp = izProp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
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

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}

	public BigDecimal getBidPq() {
		return bidPq;
	}

	public void setBidPq(BigDecimal bidPq) {
		this.bidPq = bidPq;
	}

	public BigDecimal getDeliveryPq() {
		return deliveryPq;
	}

	public void setDeliveryPq(BigDecimal deliveryPq) {
		this.deliveryPq = deliveryPq;
	}

	public BigDecimal getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(BigDecimal proxyPq) {
		this.proxyPq = proxyPq;
	}

	public BigDecimal getDeviationPq() {
		return deviationPq;
	}

	public void setDeviationPq(BigDecimal deviationPq) {
		this.deviationPq = deviationPq;
	}

	public BigDecimal getKwhPrc() {
		return kwhPrc;
	}

	public void setKwhPrc(BigDecimal kwhPrc) {
		this.kwhPrc = kwhPrc;
	}

	public BigDecimal getPlPrc() {
		return plPrc;
	}

	public void setPlPrc(BigDecimal plPrc) {
		this.plPrc = plPrc;
	}

	public BigDecimal getAgrePrc() {
		return agrePrc;
	}

	public void setAgrePrc(BigDecimal agrePrc) {
		this.agrePrc = agrePrc;
	}

	public BigDecimal getTransPrc() {
		return transPrc;
	}

	public void setTransPrc(BigDecimal transPrc) {
		this.transPrc = transPrc;
	}

	public BigDecimal getdValue() {
		return dValue;
	}

	public void setdValue(BigDecimal dValue) {
		this.dValue = dValue;
	}
	
	public BigDecimal getConsElecProfit() {
		return consElecProfit;
	}

	public void setConsElecProfit(BigDecimal consElecProfit) {
		this.consElecProfit = consElecProfit;
	}

	public BigDecimal getCompensateAmt() {
		return compensateAmt;
	}

	public void setCompensateAmt(BigDecimal compensateAmt) {
		this.compensateAmt = compensateAmt;
	}

	public BigDecimal getConsProfit() {
		return consProfit;
	}

	public void setConsProfit(BigDecimal consProfit) {
		this.consProfit = consProfit;
	}

	public BigDecimal getConsCheckAwt() {
		return consCheckAwt;
	}

	public void setConsCheckAwt(BigDecimal consCheckAwt) {
		this.consCheckAwt = consCheckAwt;
	}

	public BigDecimal getConsCheckPq() {
		return consCheckPq;
	}

	public void setConsCheckPq(BigDecimal consCheckPq) {
		this.consCheckPq = consCheckPq;
	}

	public BigDecimal getConsCheckPrc() {
		return consCheckPrc;
	}

	public void setConsCheckPrc(BigDecimal consCheckPrc) {
		this.consCheckPrc = consCheckPrc;
	}

	public BigDecimal getCompensatePq() {
		return compensatePq;
	}

	public void setCompensatePq(BigDecimal compensatePq) {
		this.compensatePq = compensatePq;
	}

	public BigDecimal getCompensatePrc() {
		return compensatePrc;
	}

	public void setCompensatePrc(BigDecimal compensatePrc) {
		this.compensatePrc = compensatePrc;
	}

	public String getCompPunishFlag() {
		return compPunishFlag;
	}

	public void setCompPunishFlag(String compPunishFlag) {
		this.compPunishFlag = compPunishFlag;
	}

	public String getUpperPunishType() {
		return upperPunishType;
	}

	public void setUpperPunishType(String upperPunishType) {
		this.upperPunishType = upperPunishType;
	}

	public String getLowerPunishType() {
		return lowerPunishType;
	}

	public void setLowerPunishType(String lowerPunishType) {
		this.lowerPunishType = lowerPunishType;
	}
}
