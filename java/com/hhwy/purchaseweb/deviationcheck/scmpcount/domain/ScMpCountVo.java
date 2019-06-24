package com.hhwy.purchaseweb.deviationcheck.scmpcount.domain;

import java.util.List;

import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScMpCount;

/**
 * <b>类 名 称：</b>ScMpCountVo<br/>
 * <b>类 描 述：</b><br/> 表计示数的vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月13日 下午3:32:28<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScMpCountVo extends PagingProperty{

	private String consId;				//用户Id
	private String consName;				//用户名称
	
	private String voltCode;				//电压等级
	
	private String elecTypeCode;			//用电类别
	
	
	private String id;						//电表id，即计量点id
	
	
	private List<ScMpCount> updateRows;
	
	private List<ScMpCount> addRows;
	
	private List<ScMpCount> delRows;
	
	
	
	

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<ScMpCount> getUpdateRows() {
		return updateRows;
	}

	public void setUpdateRows(List<ScMpCount> updateRows) {
		this.updateRows = updateRows;
	}

	public List<ScMpCount> getAddRows() {
		return addRows;
	}

	public void setAddRows(List<ScMpCount> addRows) {
		this.addRows = addRows;
	}

	public List<ScMpCount> getDelRows() {
		return delRows;
	}

	public void setDelRows(List<ScMpCount> delRows) {
		this.delRows = delRows;
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

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}
}