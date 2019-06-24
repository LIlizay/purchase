package com.hhwy.purchaseweb.deviationcheck.smconspowerview.domain;

import java.math.BigDecimal;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.SmConsPowerView;

/**
 * <b>类 名 称：</b>SmConsPowerViewVo<br/>
 * <b>类 描 述：</b><br/>		用电计划的vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月17日 下午5:29:01<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmConsPowerViewVo extends PagingProperty{

	private SmConsPowerView smConsPowerView = BaseModel.getModel(SmConsPowerView.class);
	
	private String ym;
	private String consName;
	
	private String column;	//要查询的月份英文简写，即列名
	
	private String[] consIds;
	
	private BigDecimal[] agrePqs;
	/*
	//用户id
	private String consId;
	
	private List<SmConsPowerView> smConsPowerViews;*/
	

	//add by wangzelu 用于监控平台->综合电量中的查询
	//用电类别Code
	private String elecTypeCode;
	//电压等级Code
	private String voltCode;
	
	private String loginUserId;
	
	
	
	
	
	
	public String[] getConsIds() {
		return consIds;
	}

	public void setConsIds(String[] consIds) {
		this.consIds = consIds;
	}

	public BigDecimal[] getAgrePqs() {
		return agrePqs;
	}

	public void setAgrePqs(BigDecimal[] agrePqs) {
		this.agrePqs = agrePqs;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}

	public String getVoltCode() {
		return voltCode;
	}

	public void setVoltCode(String voltCode) {
		this.voltCode = voltCode;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public SmConsPowerView getSmConsPowerView(){
		return smConsPowerView;
	}
	
	public void setSmConsPowerView(SmConsPowerView smConsPowerView){
		this.smConsPowerView = smConsPowerView;
	}
}