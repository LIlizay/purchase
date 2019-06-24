package com.hhwy.purchaseweb.archives.scdevicerelation.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScDeviceRelation;

/**
 * ScDeviceRelationVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
public class ScDeviceRelationVo extends PagingProperty{

	private ScDeviceRelation scDeviceRelation = BaseModel.getModel(ScDeviceRelation.class);
	
	private String _parentId;  //父节点id标识，consId
	
	private String consId;
	
	/*
	 * 多选打压等级
	 */
	private String[] voltCodeList;
	
	/*
	 * 设备表id
	 */
	private String id ;
	
	/*
	 * 设备id
	 */
	private String deviceId;
	
	/*
	 * 设备编码
	 */
	private String deviceCode;
	
	/*
	 * 年月日
	 */
	private String ymd;
	
	/*
	 * 年月
	 */
	private String ym;
	
	/*
	 * 年
	 */
	private String year;
	
	/*
	 * 用户名称
	 */
	private String consName;
	
	/*
	 * 用电类别
	 */
	private String elecTypeCode;
	
	/*
	 * 电压等级
	 */
	private String voltCode;
	
	/*
	 * 营销户号
	 */
	private String marketConsNo;
	
	/*
	 * 设备编号
	 */
	private String code;
	/*
	 * 设备名称
	 */
	private String name;
	
	private String consType;		//用户类型
	

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}

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

	public ScDeviceRelation getScDeviceRelation(){
		return scDeviceRelation;
	}
	
	public void setScDeviceRelation(ScDeviceRelation scDeviceRelation){
		this.scDeviceRelation = scDeviceRelation;
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

	public String getMarketConsNo() {
		return marketConsNo;
	}

	public void setMarketConsNo(String marketConsNo) {
		this.marketConsNo = marketConsNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getYmd() {
		return ymd;
	}

	public void setYmd(String ymd) {
		this.ymd = ymd;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceCode() {
		return deviceCode;
	}

	public void setDeviceCode(String deviceCode) {
		this.deviceCode = deviceCode;
	}

	public String[] getVoltCodeList() {
		return voltCodeList;
	}

	public void setVoltCodeList(String[] voltCodeList) {
		this.voltCodeList = voltCodeList;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConsType() {
		return consType;
	}

	public void setConsType(String consType) {
		this.consType = consType;
	}
	
	
}