package com.hhwy.purchaseweb.crm.ssagrescheme.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SsAgreScheme;
import com.hhwy.selling.domain.SmAgreMp;
import com.hhwy.selling.domain.SmAgrePunishComp;
import com.hhwy.selling.domain.SmAgrePunishCons;
import com.hhwy.selling.domain.SmDistMode;
import com.hhwy.selling.domain.SmPpa;

/**
 * SmPpaVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class SmPpaVo extends PagingProperty{

	/**
	 * 合同基本信息
	 */
	private SmPpa smPpa = BaseModel.getModel(SmPpa.class);

	/**
	 * 分成信息
	 */
	private SmDistMode smDistMode = BaseModel.getModel(SmDistMode.class);
	
	/**
	 * 客户合同方案表
	 */
	private SsAgreScheme ssAgreScheme = BaseModel.getModel(SsAgreScheme.class);

	/**
	 * 合同服务信息(查询)
	 *//*
	private List<SmAgreServDetail> smAgreServDetail = new ArrayList<SmAgreServDetail>();

	*//**
	 * 合同服务信息(保存)
	 *//*
	private List<SmAgreServ> smAgreServ = new ArrayList<SmAgreServ>();*/
	
	/**
	 * 合同计量点分成信息
	 */
	private List<SmAgreMp> smAgreMp = new ArrayList<SmAgreMp>();

	/**
	 * 用户惩罚方式信息
	 */
	private SmAgrePunishCons smAgrePunishCons = BaseModel.getModel(SmAgrePunishCons.class);
	
	/**
	 * 售电公司惩罚方式信息
	 */
	private SmAgrePunishComp smAgrePunishComp = BaseModel.getModel(SmAgrePunishComp.class);
	
	/**
	 * 用户名称
	 */
	private String consName;
	
	/**
	 * 用户编号
	 */
	private String consNo;
	
	/**
	 * 供电单位名称
	 */
	private String orgId;
	
	/**
	 * 供电单位名称
	 */
	private String orgName;

	public SmPpa getSmPpa() {
		return smPpa;
	}

	public void setSmPpa(SmPpa smPpa) {
		this.smPpa = smPpa;
	}

	public SmDistMode getSmDistMode() {
		return smDistMode;
	}

	public void setSmDistMode(SmDistMode smDistMode) {
		this.smDistMode = smDistMode;
	}

	public List<SmAgreMp> getSmAgreMp() {
		return smAgreMp;
	}

	public void setSmAgreMp(List<SmAgreMp> smAgreMp) {
		this.smAgreMp = smAgreMp;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getConsNo() {
		return consNo;
	}

	public void setConsNo(String consNo) {
		this.consNo = consNo;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public SmAgrePunishCons getSmAgrePunishCons() {
		return smAgrePunishCons;
	}

	public void setSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons) {
		this.smAgrePunishCons = smAgrePunishCons;
	}

	public SmAgrePunishComp getSmAgrePunishComp() {
		return smAgrePunishComp;
	}

	public void setSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp) {
		this.smAgrePunishComp = smAgrePunishComp;
	}

	public SsAgreScheme getSsAgreScheme() {
		return ssAgreScheme;
	}

	public void setSsAgreScheme(SsAgreScheme ssAgreScheme) {
		this.ssAgreScheme = ssAgreScheme;
	}
}