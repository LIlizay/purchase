package com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain;

import java.math.BigDecimal;

import com.hhwy.framework.annotation.PropertyDesc;

/**
 * 
 * <b>类 名 称：</b>PhmGeneratorMonthPqDetail<br/>
 * <b>类 描 述：合同机组电量分月信息详情类</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年7月22日 上午9:16:30<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class PhmGeneratorMonthPqDetail {
	
	private String id;
	
	@PropertyDesc("合同标识")
    private String agreId;
    
    @PropertyDesc("机组标识")
    private String generatorId;
    
    @PropertyDesc("年月")
    private String ym;
    
    @PropertyDesc("机组电量")
    private BigDecimal generatorPq;
    
    @PropertyDesc("电价")
    private BigDecimal generatorPrc;
    
    @PropertyDesc("部门id")
    private String orgNo;
    
    private String distId;
    
    @PropertyDesc("总电量")
    private java.math.BigDecimal totaPq;
    
    @PropertyDesc("分解方式")
    private String distributionMode;
    
    @PropertyDesc("保留小数位")
    private String saveDecimal;
    
    /**
     * 机组名称
     */
    private String geneName;

    
    //购电合同查询合同执行情况中的表格用到
    private String itemName;
    private BigDecimal jan;		//   1月
    private BigDecimal feb;		//   2月
    private BigDecimal mar;		//   3月
    private BigDecimal apr;		//   4月
    private BigDecimal may;		//   5月
    private BigDecimal jun;		//   6月
    private BigDecimal jul;		//   7月
    private BigDecimal aug;		//   8月
    private BigDecimal sep;		//   9月
    private BigDecimal oct;		//   10月
    private BigDecimal nov;		//   11月
    private BigDecimal dec;		//   12月
    
    
    
    
    
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public BigDecimal getSep() {
		return sep;
	}

	public void setSep(BigDecimal sep) {
		this.sep = sep;
	}

	public BigDecimal getDec() {
		return dec;
	}

	public void setDec(BigDecimal dec) {
		this.dec = dec;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAgreId() {
		return agreId;
	}

	public void setAgreId(String agreId) {
		this.agreId = agreId;
	}

	public String getGeneratorId() {
		return generatorId;
	}

	public void setGeneratorId(String generatorId) {
		this.generatorId = generatorId;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public java.math.BigDecimal getGeneratorPq() {
		return generatorPq;
	}

	public void setGeneratorPq(java.math.BigDecimal generatorPq) {
		this.generatorPq = generatorPq;
	}

	public String getOrgNo() {
		return orgNo;
	}

	public void setOrgNo(String orgNo) {
		this.orgNo = orgNo;
	}

	public String getDistId() {
		return distId;
	}

	public void setDistId(String distId) {
		this.distId = distId;
	}

	public java.math.BigDecimal getTotaPq() {
		return totaPq;
	}

	public void setTotaPq(java.math.BigDecimal totaPq) {
		this.totaPq = totaPq;
	}

	public String getDistributionMode() {
		return distributionMode;
	}

	public void setDistributionMode(String distributionMode) {
		this.distributionMode = distributionMode;
	}

	public String getSaveDecimal() {
		return saveDecimal;
	}

	public void setSaveDecimal(String saveDecimal) {
		this.saveDecimal = saveDecimal;
	}

	public String getGeneName() {
		return geneName;
	}

	public void setGeneName(String geneName) {
		this.geneName = geneName;
	}

    public java.math.BigDecimal getGeneratorPrc() {
        return generatorPrc;
    }

    public void setGeneratorPrc(java.math.BigDecimal generatorPrc) {
        this.generatorPrc = generatorPrc;
    }
}
