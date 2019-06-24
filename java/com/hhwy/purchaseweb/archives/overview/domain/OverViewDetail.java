package com.hhwy.purchaseweb.archives.overview.domain;
/**
 * 
 * <b>类 名 称：</b>OverViewDetail<br/>
 * <b>类 描 述：</b><br/>概览的实体类
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2017年12月29日 下午2:28:00<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class OverViewDetail {
	//每种用电类别总数
	private Integer total;
	//每种用电类别名称
	private String name;
	
	//年累计购电量 ：双边（合同电量）
	private Integer agrePq;
	//年累计购电量 ：双边字段名
	private String agrePqName;
	//年累计购电量 : 竞价
	private Integer dealPq;
	//年累计购电量 : 竞价字段名
	private String dealPqName;
	//年累计购电量：挂牌
	private String listedPq;
	
	//售电 双边
	private Integer lcPq;
	private String lcPqName;
	//售电 竞价
	private Integer bidPq;
	private String bidPqName;
	//年累计总售电量
	private Integer totalPq;
	
	private Integer gpPq;
	
	/*购电计划交易情况*/
	//用户委托电量
	private Integer reportPq;
	private String reportPqName;
	//双边 
	private Integer monthGeneratorPq;
	private String monthGeneratorPqName;
	//竞价
	private Integer monthDealPq;
	private String monthDealPqName;
	//总购电量
	private String purchasePqName;
	private Integer purchasePq;
	//挂牌
	private String hangPqName;
	private Integer hangPq;
	
	
	
	public String getPurchasePqName() {
		return purchasePqName;
	}
	public void setPurchasePqName(String purchasePqName) {
		this.purchasePqName = purchasePqName;
	}
	public Integer getMonthGeneratorPq() {
		return monthGeneratorPq;
	}
	public void setMonthGeneratorPq(Integer monthGeneratorPq) {
		this.monthGeneratorPq = monthGeneratorPq;
	}
	public String getMonthGeneratorPqName() {
		return monthGeneratorPqName;
	}
	public void setMonthGeneratorPqName(String monthGeneratorPqName) {
		this.monthGeneratorPqName = monthGeneratorPqName;
	}
	public Integer getMonthDealPq() {
		return monthDealPq;
	}
	public void setMonthDealPq(Integer monthDealPq) {
		this.monthDealPq = monthDealPq;
	}
	public String getMonthDealPqName() {
		return monthDealPqName;
	}
	public void setMonthDealPqName(String monthDealPqName) {
		this.monthDealPqName = monthDealPqName;
	}
	public Integer getReportPq() {
		return reportPq;
	}
	public void setReportPq(Integer reportPq) {
		this.reportPq = reportPq;
	}
	public String getReportPqName() {
		return reportPqName;
	}
	public void setReportPqName(String reportPqName) {
		this.reportPqName = reportPqName;
	}
	public Integer getLcPq() {
		return lcPq;
	}
	public void setLcPq(Integer lcPq) {
		this.lcPq = lcPq;
	}
	public String getLcPqName() {
		return lcPqName;
	}
	public void setLcPqName(String lcPqName) {
		this.lcPqName = lcPqName;
	}
	public Integer getBidPq() {
		return bidPq;
	}
	public void setBidPq(Integer bidPq) {
		this.bidPq = bidPq;
	}
	public String getBidPqName() {
		return bidPqName;
	}
	public void setBidPqName(String bidPqName) {
		this.bidPqName = bidPqName;
	}
	public Integer getAgrePq() {
		return agrePq;
	}
	public void setAgrePq(Integer agrePq) {
		this.agrePq = agrePq;
	}
	public String getAgrePqName() {
		return agrePqName;
	}
	public void setAgrePqName(String agrePqName) {
		this.agrePqName = agrePqName;
	}
	public Integer getDealPq() {
		return dealPq;
	}
	public void setDealPq(Integer dealPq) {
		this.dealPq = dealPq;
	}
	public String getDealPqName() {
		return dealPqName;
	}
	public void setDealPqName(String dealPqName) {
		this.dealPqName = dealPqName;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getListedPq() {
		return listedPq;
	}
	public void setListedPq(String listedPq) {
		this.listedPq = listedPq;
	}
	public Integer getGpPq() {
		return gpPq;
	}
	public void setGpPq(Integer gpPq) {
		this.gpPq = gpPq;
	}
	public Integer getTotalPq() {
		return totalPq;
	}
	public void setTotalPq(Integer totalPq) {
		this.totalPq = totalPq;
	}
	public Integer getPurchasePq() {
		return purchasePq;
	}
	public void setPurchasePq(Integer purchasePq) {
		this.purchasePq = purchasePq;
	}
	public String getHangPqName() {
		return hangPqName;
	}
	public void setHangPqName(String hangPqName) {
		this.hangPqName = hangPqName;
	}
	public Integer getHangPq() {
		return hangPq;
	}
	public void setHangPq(Integer hangPq) {
		this.hangPq = hangPq;
	}
	
	
}	












