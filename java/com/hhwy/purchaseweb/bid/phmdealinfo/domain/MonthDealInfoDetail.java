package com.hhwy.purchaseweb.bid.phmdealinfo.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

public class MonthDealInfoDetail {
	private String id;			

	private String reportId;	//申报id
	
	private String planId;
	
	private String dealId;		//成交信息id
	
	private String generatorId;	//机组id
	
	private BigDecimal deliveryPrc;	//结算电价
	
	private BigDecimal dealPq;		//成交电量
	
	private BigDecimal dealPrc;		//成交电价
	
	private String consId;
	
	private String consName;
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradeVariety" ,field="tradeVariety")
    private String variety;
	private String tradeVariety;//交易品种
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradeMode" ,field="tradeMode")
    private String mode;
	private String tradeMode;	//交易方式
	
	private String producerId;	//电厂id
	
	private String traderName;	//交易对象
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_attornType" ,field="attornType")
	private String attorn;
	private String attornType;	//合同转让方向
	
	private BigDecimal totalDealPq; //总交易电量
	
	private BigDecimal mon1;
	
	private BigDecimal mon2;
	
	private BigDecimal mon3;
	
	private BigDecimal mon4;
	
	private BigDecimal mon5;
	
	private BigDecimal mon6;
	
	private BigDecimal mon7;
	
	private BigDecimal mon8;
	
	private BigDecimal mon9;
	
	private BigDecimal mon10;
	
	private BigDecimal mon11;
	
	private BigDecimal mon12;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
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

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}

	public BigDecimal getDeliveryPrc() {
		return deliveryPrc;
	}

	public void setDeliveryPrc(BigDecimal deliveryPrc) {
		this.deliveryPrc = deliveryPrc;
	}

	public BigDecimal getDealPq() {
		return dealPq;
	}

	public void setDealPq(BigDecimal dealPq) {
		this.dealPq = dealPq;
	}

	public BigDecimal getDealPrc() {
		return dealPrc;
	}

	public void setDealPrc(BigDecimal dealPrc) {
		this.dealPrc = dealPrc;
	}

	public String getAttorn() {
		return attorn;
	}

	public void setAttorn(String attorn) {
		this.attorn = attorn;
	}

	public String getVariety() {
		return variety;
	}

	public void setVariety(String variety) {
		this.variety = variety;
	}

	public String getTradeVariety() {
		return tradeVariety;
	}

	public void setTradeVariety(String tradeVariety) {
		this.tradeVariety = tradeVariety;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}

	public String getProducerId() {
		return producerId;
	}

	public void setProducerId(String producerId) {
		this.producerId = producerId;
	}

	public String getTraderName() {
		return traderName;
	}

	public void setTraderName(String traderName) {
		this.traderName = traderName;
	}

	public String getAttornType() {
		return attornType;
	}

	public void setAttornType(String attornType) {
		this.attornType = attornType;
	}

	public BigDecimal getTotalDealPq() {
		return totalDealPq;
	}

	public void setTotalDealPq(BigDecimal totalDealPq) {
		this.totalDealPq = totalDealPq;
	}

	public BigDecimal getMon1() {
		return mon1;
	}

	public void setMon1(BigDecimal mon1) {
		this.mon1 = mon1;
	}

	public BigDecimal getMon2() {
		return mon2;
	}

	public void setMon2(BigDecimal mon2) {
		this.mon2 = mon2;
	}

	public BigDecimal getMon3() {
		return mon3;
	}

	public void setMon3(BigDecimal mon3) {
		this.mon3 = mon3;
	}

	public BigDecimal getMon4() {
		return mon4;
	}

	public void setMon4(BigDecimal mon4) {
		this.mon4 = mon4;
	}

	public BigDecimal getMon5() {
		return mon5;
	}

	public void setMon5(BigDecimal mon5) {
		this.mon5 = mon5;
	}

	public BigDecimal getMon6() {
		return mon6;
	}

	public void setMon6(BigDecimal mon6) {
		this.mon6 = mon6;
	}

	public BigDecimal getMon7() {
		return mon7;
	}

	public void setMon7(BigDecimal mon7) {
		this.mon7 = mon7;
	}

	public BigDecimal getMon8() {
		return mon8;
	}

	public void setMon8(BigDecimal mon8) {
		this.mon8 = mon8;
	}

	public BigDecimal getMon9() {
		return mon9;
	}

	public void setMon9(BigDecimal mon9) {
		this.mon9 = mon9;
	}

	public BigDecimal getMon10() {
		return mon10;
	}

	public void setMon10(BigDecimal mon10) {
		this.mon10 = mon10;
	}

	public BigDecimal getMon11() {
		return mon11;
	}

	public void setMon11(BigDecimal mon11) {
		this.mon11 = mon11;
	}

	public BigDecimal getMon12() {
		return mon12;
	}

	public void setMon12(BigDecimal mon12) {
		this.mon12 = mon12;
	}
}
