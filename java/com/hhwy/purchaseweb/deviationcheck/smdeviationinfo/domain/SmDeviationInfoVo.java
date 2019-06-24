package com.hhwy.purchaseweb.deviationcheck.smdeviationinfo.domain;

import com.hhwy.selling.domain.SmDeviationInfo;
import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * <b>类 名 称：</b>SmDeviationInfoVo<br/>
 * <b>类 描 述：</b><br/>			偏差预警的vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月19日 下午4:21:27<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmDeviationInfoVo extends PagingProperty{

	private SmDeviationInfo smDeviationInfo = BaseModel.getModel(SmDeviationInfo.class);
	
	//年月
	private String ym;
	
	private String consName;
	
	
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

	public SmDeviationInfo getSmDeviationInfo(){
		return smDeviationInfo;
	}
	
	public void setSmDeviationInfo(SmDeviationInfo smDeviationInfo){
		this.smDeviationInfo = smDeviationInfo;
	}
}