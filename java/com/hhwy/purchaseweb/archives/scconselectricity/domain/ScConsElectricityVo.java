package com.hhwy.purchaseweb.archives.scconselectricity.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmSettlementMonth;
import com.hhwy.selling.domain.ScConsElectricity;

/**
 * ScConsElectricityVo
 * @author hhwy
 * @date 2016-9-28 13:46:42
 * @version 1.0
 */
/**
 * 
 * <b>类 名 称：</b>ScConsElectricityVo<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhangzhao<br/>
 * <b>修 改 人：</b>zhangzhao<br/>
 * <b>修改时间：</b>2018年5月19日 下午1:40:32<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScConsElectricityVo extends PagingProperty{

	private ScConsElectricity scConsElectricity = BaseModel.getModel(ScConsElectricity.class);
	
	private List<ScConsElectricity> scConsElectricitieList = new ArrayList<ScConsElectricity>();
	
	private List<ScConsElecTreeDetail> treeList = new ArrayList<ScConsElecTreeDetail>();
	
	private String year;
	
	private String[] years = new String[]{};
	
	private String consId;
	
	//实际用量录入  起始年月
	private String startYm;
	
	//实际用量录入  截止年月
	private String endYm;
	
	//实际用电量 删除
	List<String> ymList;
	
	
	//江苏专用，用于保存工业电量及商业电量字段
	private SmSettlementMonth smSettlementMonth;
	
	
	
	
	public SmSettlementMonth getSmSettlementMonth() {
		return smSettlementMonth;
	}

	public void setSmSettlementMonth(SmSettlementMonth smSettlementMonth) {
		this.smSettlementMonth = smSettlementMonth;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] years) {
		this.years = years;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public List<ScConsElectricity> getScConsElectricitieList() {
		return scConsElectricitieList;
	}

	public void setScConsElectricitieList(
			List<ScConsElectricity> scConsElectricitieList) {
		this.scConsElectricitieList = scConsElectricitieList;
	}

	public ScConsElectricity getScConsElectricity(){
		return scConsElectricity;
	}
	
	public void setScConsElectricity(ScConsElectricity scConsElectricity){
		this.scConsElectricity = scConsElectricity;
	}

	public List<ScConsElecTreeDetail> getTreeList() {
		return treeList;
	}

	public void setTreeList(List<ScConsElecTreeDetail> treeList) {
		this.treeList = treeList;
	}

	public String getStartYm() {
		return startYm;
	}

	public void setStartYm(String startYm) {
		this.startYm = startYm;
	}

	public String getEndYm() {
		return endYm;
	}

	public void setEndYm(String endYm) {
		this.endYm = endYm;
	}

	public List<String> getYmList() {
		return ymList;
	}

	public void setYmList(List<String> ymList) {
		this.ymList = ymList;
	}
	
}