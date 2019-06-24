package com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain;
import java.math.BigDecimal;

/**
 * SmConsumerProfit
 * @author hhwy			月度结算用户收益表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class SmConsumerProfitDetail{

	private String id;
	
    //月度购电方案id
    private String schemeId;
    
    //用户标识
    private String consId;
    
    //长协分配电量
    private BigDecimal lcDistPq;
    
    //竞价分配电量
    private BigDecimal bidDistPq;
    
    //分配挂牌电量
    private BigDecimal listedPq;
    
    //月委托电量(售电公司当月代理用户电量)
    private BigDecimal proxyPq;
    
    //分配总电量
    private BigDecimal distTotalPq;
    
    //交割电量
    private BigDecimal consDelPq;
    
    //交易总受益
    private BigDecimal transTotalPro;
    
    //售电公司未考核收益
    private BigDecimal compUncheckedPro;
    
    //用户未考核收益（江苏结算中的 差额电费）
    private BigDecimal consUncheckedPro;
    
    //用电偏差
    private BigDecimal consElecDev;
    
    //用户承担考核
    private BigDecimal consCheckedPro;	 //江苏结算中的  偏差考核费用（元）
    
    //用户总收益(总节省电费，净收益)
    private BigDecimal consTotalPro;
    
    //售电公司承担考核（售电公司赔偿金额）
    private BigDecimal compCheckedPro;
    
    //分成模式
    //private String diviCode;
    
    //售电公司赔偿金额（即售电公司赔偿用户费用）	江苏的赔偿用户费用
    private BigDecimal consCompensate;
    
    //结算电价(江苏结算专用字段)
    private BigDecimal deliveryPrc;
    
    //市场化交易结算电量（兆瓦时,江苏结算专用字段）
    private BigDecimal marketizePq;
    
    //按非市场电价结算电量(兆瓦时,江苏结算专用字段）
    private BigDecimal nmarketizePq;
    
    //结算电费（元,江苏结算专用字段）
    private BigDecimal deliveryCost;
    
    //部门id
    private String orgNo;
    
    
    
    
    //附加字段
    
    private String consName;
    
//--by	王泽鲁  用于计算收益时，覆盖合同中的分成模式信息
    
    //正偏差1段域值（多用1段考核比例）  (用户偏差费用计算表中的字段)
    //用于计算用户 市场化和非市场化交易电量
    private BigDecimal upperThreshold1;
    
    
    //@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_divideWayCode" ,field="diviCodeName")
    //private String diviCode;			//分成模式
    //private String diviCodeName;
    
//    private BigDecimal agrePrc;				//保底协议价 
//    private BigDecimal partyALcProp;		//用户长协分成比例
//    private BigDecimal partyABidProp;		//用户竞价分成比例
    
    
    
    
    
    
	public String getId() {
		return id;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public BigDecimal getUpperThreshold1() {
		return upperThreshold1;
	}

	public void setUpperThreshold1(BigDecimal upperThreshold1) {
		this.upperThreshold1 = upperThreshold1;
	}

	public void setId(String id) {
		this.id = id;
	}

    public String getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(String schemeId) {
		this.schemeId = schemeId;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public BigDecimal getLcDistPq(){
        return lcDistPq;
    }
    
    public void setLcDistPq(BigDecimal lcDistPq){
        this.lcDistPq = lcDistPq;
    }
    
    public BigDecimal getBidDistPq(){
        return bidDistPq;
    }
    
    public void setBidDistPq(BigDecimal bidDistPq){
        this.bidDistPq = bidDistPq;
    }
    
    public BigDecimal getListedPq(){
        return listedPq;
    }
    
    public void setListedPq(BigDecimal listedPq){
        this.listedPq = listedPq;
    }
    
    public BigDecimal getProxyPq(){
        return proxyPq;
    }
    
    public void setProxyPq(BigDecimal proxyPq){
        this.proxyPq = proxyPq;
    }
    
    public BigDecimal getDistTotalPq(){
        return distTotalPq;
    }
    
    public void setDistTotalPq(BigDecimal distTotalPq){
        this.distTotalPq = distTotalPq;
    }
    
    public BigDecimal getConsDelPq(){
        return consDelPq;
    }
    
    public void setConsDelPq(BigDecimal consDelPq){
        this.consDelPq = consDelPq;
    }
    
    public BigDecimal getTransTotalPro(){
        return transTotalPro;
    }
    
    public void setTransTotalPro(BigDecimal transTotalPro){
        this.transTotalPro = transTotalPro;
    }
    
    public BigDecimal getCompUncheckedPro(){
        return compUncheckedPro;
    }
    
    public void setCompUncheckedPro(BigDecimal compUncheckedPro){
        this.compUncheckedPro = compUncheckedPro;
    }
    
    public BigDecimal getConsUncheckedPro(){
        return consUncheckedPro;
    }
    
    public void setConsUncheckedPro(BigDecimal consUncheckedPro){
        this.consUncheckedPro = consUncheckedPro;
    }
    
    public BigDecimal getConsElecDev(){
        return consElecDev;
    }
    
    public void setConsElecDev(BigDecimal consElecDev){
        this.consElecDev = consElecDev;
    }
    
    public BigDecimal getConsCheckedPro(){
        return consCheckedPro;
    }
    
    public void setConsCheckedPro(BigDecimal consCheckedPro){
        this.consCheckedPro = consCheckedPro;
    }
    
    public BigDecimal getConsTotalPro(){
        return consTotalPro;
    }
    
    public void setConsTotalPro(BigDecimal consTotalPro){
        this.consTotalPro = consTotalPro;
    }
    
    public BigDecimal getCompCheckedPro(){
        return compCheckedPro;
    }
    
    public void setCompCheckedPro(BigDecimal compCheckedPro){
        this.compCheckedPro = compCheckedPro;
    }
    
    public BigDecimal getConsCompensate(){
        return consCompensate;
    }
    
    public void setConsCompensate(BigDecimal consCompensate){
        this.consCompensate = consCompensate;
    }
    
    public BigDecimal getDeliveryPrc(){
        return deliveryPrc;
    }
    
    public void setDeliveryPrc(BigDecimal deliveryPrc){
        this.deliveryPrc = deliveryPrc;
    }
    
    public BigDecimal getMarketizePq(){
        return marketizePq;
    }
    
    public void setMarketizePq(BigDecimal marketizePq){
        this.marketizePq = marketizePq;
    }
    
    public BigDecimal getNmarketizePq(){
        return nmarketizePq;
    }
    
    public void setNmarketizePq(BigDecimal nmarketizePq){
        this.nmarketizePq = nmarketizePq;
    }
    
    public BigDecimal getDeliveryCost(){
        return deliveryCost;
    }
    
    public void setDeliveryCost(BigDecimal deliveryCost){
        this.deliveryCost = deliveryCost;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}