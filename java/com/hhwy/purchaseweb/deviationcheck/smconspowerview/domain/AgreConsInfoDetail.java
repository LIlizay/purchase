package com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain;

import java.math.BigDecimal;

import com.hhwy.business.market.annotation.PropertyAnnotation;
import com.hhwy.business.market.util.ConstantsStatus;

/**
 * <b>类 名 称：</b>AgreConsInfoDetail<br/>
 * <b>类 描 述：</b><br/>		用户用电计划新增时弹框用到的 用户信息detail类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午7:29:10<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class AgreConsInfoDetail {
	
	/** 用户id */
	private String consId;
	
	/** 用户名称 */
	private String consName;
	
	/** 电压等级 */
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_psVoltCode" ,field="voltCodeName")
	private String voltCode;
	private String voltCodeName;
	
	/** 用电类别 */
	@PropertyAnnotation(cacheType=ConstantsStatus.CACHE_TYPE_PCODE,domain=ConstantsStatus.PCODE_DOMAIN_SELLING,key="sell_elecTypeCode" ,field="elecTypeCodeName")
	private String elecTypeCode;
	private String elecTypeCodeName;
	
	/** 用电容量 */
	private BigDecimal electricCapacity;
	
	/** 合同电量 */
	private BigDecimal agrePq;

	
	
	
	
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

	public BigDecimal getElectricCapacity() {
		return electricCapacity;
	}

	public void setElectricCapacity(BigDecimal electricCapacity) {
		this.electricCapacity = electricCapacity;
	}

	public BigDecimal getAgrePq() {
		return agrePq;
	}

	public void setAgrePq(BigDecimal agrePq) {
		this.agrePq = agrePq;
	}
	
}
