package com.hhwy.purchaseweb.archives.scconsdate.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScConsDate;

 /**
 * <b>类 名 称：</b>ScConsDateVo<br/>
 * <b>类 描 述：</b><br/>		用户例日维护vo
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2018年12月12日 上午11:02:12<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScConsDateVo extends PagingProperty{

	private ScConsDate scConsDate = BaseModel.getModel(ScConsDate.class);
	
	private String consId;
	

	
	
	public String getConsId() {
		return consId;
	}

	public void setConsId(String consId) {
		this.consId = consId;
	}

	public ScConsDate getScConsDate(){
		return scConsDate;
	}
	
	public void setScConsDate(ScConsDate scConsDate){
		this.scConsDate = scConsDate;
	}
}