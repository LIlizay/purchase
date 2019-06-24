package com.hhwy.purchaseweb.agreement.phmppa.domain;

import java.util.List;

import com.hhwy.purchase.domain.PhmAgreDeviation;
import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.purchase.domain.PhmPpa;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * PhmPpaVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class PhmPpaVo extends PagingProperty{

	private PhmPpa phmPpa = BaseModel.getModel(PhmPpa.class);
	
	/**
	 * 合同机组电量分月信息
	 */
	private List<PhmGeneratorMonthPq> phmGeneratorMonthPqList;
	
	/**
	 * 合同机组电量分配方式信息
	 */
	private List<PhmGenePqDist> phmGenePqDistList;
	
	/**
     * 合同偏差考核规则信息
     */
	private PhmAgreDeviation phmAgreDeviation = BaseModel.getModel(PhmAgreDeviation.class);
	
	/**
	 * 年
	 */
	private String year;
	
	/**
	 * 电厂名称
	 */
	private String elecName;
	/**
	 * 发电类别
	 */
	private String elecTypeCode;
	
	/**
	 * 电厂区分
	 */
	private String networkFlag;
	
	

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public PhmPpa getPhmPpa(){
		return phmPpa;
	}
	
	public void setPhmPpa(PhmPpa phmPpa){
		this.phmPpa = phmPpa;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public List<PhmGeneratorMonthPq> getPhmGeneratorMonthPqList() {
		return phmGeneratorMonthPqList;
	}

	public void setPhmGeneratorMonthPqList(
			List<PhmGeneratorMonthPq> phmGeneratorMonthPqList) {
		this.phmGeneratorMonthPqList = phmGeneratorMonthPqList;
	}

	public List<PhmGenePqDist> getPhmGenePqDistList() {
		return phmGenePqDistList;
	}

	public void setPhmGenePqDistList(List<PhmGenePqDist> phmGenePqDistList) {
		this.phmGenePqDistList = phmGenePqDistList;
	}

	public PhmAgreDeviation getPhmAgreDeviation() {
		return phmAgreDeviation;
	}

	public void setPhmAgreDeviation(PhmAgreDeviation phmAgreDeviation) {
		this.phmAgreDeviation = phmAgreDeviation;
	}

	public String getElecName() {
		return elecName;
	}

	public void setElecName(String elecName) {
		this.elecName = elecName;
	}

	public String getNetworkFlag() {
		return networkFlag;
	}

	public void setNetworkFlag(String networkFlag) {
		this.networkFlag = networkFlag;
	}
}