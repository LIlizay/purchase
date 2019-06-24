package com.hhwy.purchaseweb.delivery.smprcinfo.domain;

import java.util.ArrayList;
import java.util.List;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.purchase.domain.SmPrcInfo;

 /**
 * <b>类 名 称：</b>SmPrcInfoVo<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2017年11月21日 下午5:56:14<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class SmPrcInfoVo extends PagingProperty {

	private SmPrcInfo smPrcInfo = BaseModel.getModel(SmPrcInfo.class);
	
	
	
	//以下属性为查询时返回结果用到
	
	/**省份ID**/
	private String provinceId;
	
	//市,多个市的代码中间用逗号隔开
    private String cityCodes;
    
    //区县,多个区县的代码中间用逗号隔开
    private String countyCodes;
    
    //生效月份,多个月份中间用逗号隔开
    private String effectMonths;
    //列表顺序，同一电价规则此行相同
    private int listSort;
	//同组电价列表（用于展示）
	List<SmPrcInfoDetail> smPrcInfoDetailList;
	
	//同组电价列表（用于保存）
	List<SmPrcInfo> smPrcInfoList;
	
	
	

	public List<SmPrcInfo> getSmPrcInfoList() {
		return smPrcInfoList;
	}

	public void setSmPrcInfoList(List<SmPrcInfo> smPrcInfoList) {
		this.smPrcInfoList = smPrcInfoList;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityCodes() {
		return cityCodes;
	}

	public void setCityCodes(String cityCodes) {
		this.cityCodes = cityCodes;
	}

	public String getCountyCodes() {
		return countyCodes;
	}

	public void setCountyCodes(String countyCodes) {
		this.countyCodes = countyCodes;
	}

	public String getEffectMonths() {
		return effectMonths;
	}

	public void setEffectMonths(String effectMonths) {
		this.effectMonths = effectMonths;
	}

	public int getListSort() {
		return listSort;
	}

	public void setListSort(int listSort) {
		this.listSort = listSort;
	}

	public List<SmPrcInfoDetail> getSmPrcInfoDetailList() {
		if(smPrcInfoDetailList == null) {
			smPrcInfoDetailList = new ArrayList<>();
		}
		return smPrcInfoDetailList;
	}

	public void setSmPrcInfoDetailList(List<SmPrcInfoDetail> smPrcInfoDetailList) {
		this.smPrcInfoDetailList = smPrcInfoDetailList;
	}

	public SmPrcInfo getSmPrcInfo() {
		return smPrcInfo;
	}

	public void setSmPrcInfo(SmPrcInfo smPrcInfo) {
		this.smPrcInfo = smPrcInfo;
	}
}