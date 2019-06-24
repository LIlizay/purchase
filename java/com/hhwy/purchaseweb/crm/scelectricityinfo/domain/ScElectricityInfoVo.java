package com.hhwy.purchaseweb.crm.scelectricityinfo.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.spi.PrivateElements;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScConsElectricity;

/**
 * 
 * <b>类 名 称：</b>ScElectricityInfoVo<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月18日 上午10:36:53<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScElectricityInfoVo extends PagingProperty {
	
	private QueryDetail queryDetail;//条件查询数据
	
	private String id;//树表格查询子节点时父节点consId
	
	private String consId;
	
	private String year;
	
	private String consName;
	
	private List<ScConsElectricity> scConsElectricitieList = new ArrayList<ScConsElectricity>();

	private String ym;
	
	//开始年月
	private String startTime;
	
	//结束年月
	private String endTime;
	
	//上月同期
	private String upMYm;
	
	//去年同期
	private String upYYm;
	
	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public List<ScConsElectricity> getScConsElectricitieList() {
		return scConsElectricitieList;
	}

	public void setScConsElectricitieList(
			List<ScConsElectricity> scConsElectricitieList) {
		this.scConsElectricitieList = scConsElectricitieList;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public QueryDetail getQueryDetail() {
		return queryDetail;
	}

	public void setQueryDetail(QueryDetail queryDetail) {
		this.queryDetail = queryDetail;
	}

	public String getYm() {
		return ym;
	}

	public void setYm(String ym) {
		this.ym = ym;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUpMYm() {
		return upMYm;
	}

	public void setUpMYm(String upMYm) {
		this.upMYm = upMYm;
	}

	public String getUpYYm() {
		return upYYm;
	}

	public void setUpYYm(String upYYm) {
		this.upYYm = upYYm;
	}
	
	
}