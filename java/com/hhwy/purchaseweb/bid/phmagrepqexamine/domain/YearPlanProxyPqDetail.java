package com.hhwy.purchaseweb.bid.phmagrepqexamine.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;


 /**
 * <b>类 名 称：</b>YearPlanProxyPqDetail<br/>
 * <b>类 描 述：</b>年度计划的一个用户委托分月电量详情类，在月度购电计划委托电量审核环节第二个表格“年度交易委托电量分月信息”的对应类<br/>
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2019年3月1日 上午10:08:05<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class YearPlanProxyPqDetail {
	
	private String year;		//年

	private String consId;		//用户id
	
	private String consName;	//用户名称
	
	private String planId;		//计划id
	
	private String planName;	//计划名称（交易名称）
	
//	private String compositeName;	//综合名称（格式：年+交易方式+“-”+交易名称（如果没有交易名称就不加“-”））
	
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="purchase_tradeMode" ,field="tradeModeName")
    private String tradeMode;
	private String tradeModeName;	//交易方式
	
	
	
	
	private BigDecimal total;		//总电量
	
	private BigDecimal jan;		//1月委托电量	
	
	private BigDecimal feb;		//2月委托电量	
	
	private BigDecimal mar;		//3月委托电量	
	
	private BigDecimal apr;		//4月委托电量	
	
	private BigDecimal may;		//5月委托电量	
	
	private BigDecimal jun;		//6月委托电量	
	
	private BigDecimal jul;		//7月委托电量	
	
	private BigDecimal aug;		//8月委托电量	
	
	private BigDecimal sept;	//9月委托电量	
	
	private BigDecimal oct;		//10月委托电量	
	
	private BigDecimal nov;		//11月委托电量	
	
	private BigDecimal dece;	//12月委托电量	

	
	//综合名称（格式：年+交易方式+“-”+交易名称（如果没有交易名称就不加“-”））
	public String getCompositeName() {
		return year + tradeModeName + (planName == null ? "" : "-" + planName);
	}
	
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getTradeMode() {
		return tradeMode;
	}

	public void setTradeMode(String tradeMode) {
		this.tradeMode = tradeMode;
	}

	public String getTradeModeName() {
		return tradeModeName;
	}

	public void setTradeModeName(String tradeModeName) {
		this.tradeModeName = tradeModeName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getJan() {
		return jan;
	}

	public void setJan(BigDecimal jan) {
		this.jan = jan;
	}

	public BigDecimal getFeb() {
		return feb;
	}

	public void setFeb(BigDecimal feb) {
		this.feb = feb;
	}

	public BigDecimal getMar() {
		return mar;
	}

	public void setMar(BigDecimal mar) {
		this.mar = mar;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getMay() {
		return may;
	}

	public void setMay(BigDecimal may) {
		this.may = may;
	}

	public BigDecimal getJun() {
		return jun;
	}

	public void setJun(BigDecimal jun) {
		this.jun = jun;
	}

	public BigDecimal getJul() {
		return jul;
	}

	public void setJul(BigDecimal jul) {
		this.jul = jul;
	}

	public BigDecimal getAug() {
		return aug;
	}

	public void setAug(BigDecimal aug) {
		this.aug = aug;
	}

	public BigDecimal getSept() {
		return sept;
	}

	public void setSept(BigDecimal sept) {
		this.sept = sept;
	}

	public BigDecimal getOct() {
		return oct;
	}

	public void setOct(BigDecimal oct) {
		this.oct = oct;
	}

	public BigDecimal getNov() {
		return nov;
	}

	public void setNov(BigDecimal nov) {
		this.nov = nov;
	}

	public BigDecimal getDece() {
		return dece;
	}

	public void setDece(BigDecimal dece) {
		this.dece = dece;
	}
}
