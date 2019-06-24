package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import com.hhwy.business.core.modelutil.PagingProperty;

/**
 * ScConsElectricityVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ScConsElecTreeVo extends PagingProperty{
	
	private String id ; //父节点id标识，consId
	
	private String consId;
	
	private String consName;
	
	private String voltCode;
	
	private String elecTypeCode;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getElecTypeCode() {
		return elecTypeCode;
	}

	public void setElecTypeCode(String elecTypeCode) {
		this.elecTypeCode = elecTypeCode;
	}
	
}