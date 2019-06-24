package com.hhwy.purchaseweb.settlement.base.smconsdeviationamt.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * SmConsDeviationAmt
 * @author hhwy			月度结算用户偏差惩罚费用信息表
 * @date 2016-9-28 13:45:53
 * @version 1.0
 */
public class SmConsDeviationAmtDetail {

	private String id;
	
    //用户Id
    private String consId;
    
    //方案Id
    private String schemeId;
    
    //偏差惩罚类型（偏差是否考核，01：否，02：月偏差，03：季偏差，04：年偏差）
    private String punishType;
    
    //负偏差域值（少用考核比例）
    private BigDecimal lowerThreshold;
    
    //负偏差惩罚协议价（少用考核价格，元/兆瓦时）
    private BigDecimal lowerPrc;
    
    //负偏差考核金额（少用考核金额，元）
    private BigDecimal lowerDevAmt;
    
    //正偏差1段域值（多用1段考核比例）
    private BigDecimal upperThreshold1;
    
    //正偏差1段惩罚协议价（1段考核价格，元/兆瓦时）
    private BigDecimal upperPrc1;
    
    //正偏差1段考核金额（多用1段考核金额，元）
    private BigDecimal upperDevAmt1;
    
    //正偏差2段域值（多用2段考核比例）
    private BigDecimal upperThreshold2;
    
    //正偏差2段惩罚协议价（2段考核价格，元/兆瓦时）
    private BigDecimal upperPrc2;
    
    //正偏差2段考核金额（多用2段考核金额，元）
    private BigDecimal upperDevAmt2;
    
    //部门id
    private String orgNo;
    
    
    //以下属性为附加属性
    
    //年月 yyyy-MM格式
    private String ym;
    
    //用户名称
    private String consName;
    
    //电压等级
    @PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
    private String voltCode;
    private String voltCodeName; //sell_psVoltCode
 
    //实际用电量
    private BigDecimal actualPq;
    
    //月委托电量
    private BigDecimal proxyPq;
    
    //偏差电量
    private BigDecimal devPq;
    
    //偏差电量比例
    private BigDecimal devPqProp;
    
    
    

	public BigDecimal getDevPq() {
		return devPq;
	}

	public void setDevPq(BigDecimal devPq) {
		this.devPq = devPq;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
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

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public BigDecimal getActualPq() {
		return actualPq;
	}

	public void setActualPq(BigDecimal actualPq) {
		this.actualPq = actualPq;
	}

	public BigDecimal getProxyPq() {
		return proxyPq;
	}

	public void setProxyPq(BigDecimal proxyPq) {
		this.proxyPq = proxyPq;
	}

	public BigDecimal getDevPqProp() {
		return devPqProp;
	}

	public void setDevPqProp(BigDecimal devPqProp) {
		this.devPqProp = devPqProp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConsId(){
        return consId;
    }
    
    public void setConsId(String consId){
        this.consId = consId;
    }
    
    public String getSchemeId(){
        return schemeId;
    }
    
    public void setSchemeId(String schemeId){
        this.schemeId = schemeId;
    }
    
    public String getPunishType(){
        return punishType;
    }
    
    public void setPunishType(String punishType){
        this.punishType = punishType;
    }
    
    public BigDecimal getLowerThreshold(){
        return lowerThreshold;
    }
    
    public void setLowerThreshold(BigDecimal lowerThreshold){
        this.lowerThreshold = lowerThreshold;
    }
    
    public BigDecimal getLowerPrc(){
        return lowerPrc;
    }
    
    public void setLowerPrc(BigDecimal lowerPrc){
        this.lowerPrc = lowerPrc;
    }
    
    public BigDecimal getLowerDevAmt(){
        return lowerDevAmt;
    }
    
    public void setLowerDevAmt(BigDecimal lowerDevAmt){
        this.lowerDevAmt = lowerDevAmt;
    }
    
    public BigDecimal getUpperThreshold1(){
        return upperThreshold1;
    }
    
    public void setUpperThreshold1(BigDecimal upperThreshold1){
        this.upperThreshold1 = upperThreshold1;
    }
    
    public BigDecimal getUpperPrc1(){
        return upperPrc1;
    }
    
    public void setUpperPrc1(BigDecimal upperPrc1){
        this.upperPrc1 = upperPrc1;
    }
    
    public BigDecimal getUpperDevAmt1(){
        return upperDevAmt1;
    }
    
    public void setUpperDevAmt1(BigDecimal upperDevAmt1){
        this.upperDevAmt1 = upperDevAmt1;
    }
    
    public BigDecimal getUpperThreshold2(){
        return upperThreshold2;
    }
    
    public void setUpperThreshold2(BigDecimal upperThreshold2){
        this.upperThreshold2 = upperThreshold2;
    }
    
    public BigDecimal getUpperPrc2(){
        return upperPrc2;
    }
    
    public void setUpperPrc2(BigDecimal upperPrc2){
        this.upperPrc2 = upperPrc2;
    }
    
    public BigDecimal getUpperDevAmt2(){
        return upperDevAmt2;
    }
    
    public void setUpperDevAmt2(BigDecimal upperDevAmt2){
        this.upperDevAmt2 = upperDevAmt2;
    }
    
    public String getOrgNo(){
        return orgNo;
    }
    
    public void setOrgNo(String orgNo){
        this.orgNo = orgNo;
    }
    
}