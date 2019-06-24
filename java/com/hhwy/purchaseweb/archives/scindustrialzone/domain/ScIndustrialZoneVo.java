package com.hhwy.purchaseweb.archives.scindustrialzone.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScIndustrialZone;

/**
 * 
 * <b>类 名 称：</b>ScIndustrialZoneVo<br/>
 * <b>类 描 述：</b><br/>
 * <b>创 建 人：</b>zhouqi<br/>
 * <b>修 改 人：</b>zhouqi<br/>
 * <b>修改时间：</b>2017年5月22日 下午1:53:15<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScIndustrialZoneVo extends PagingProperty{

	/**
	 * 园区维护实体对象
	 */
	private ScIndustrialZone scIndustrialZone = BaseModel.getModel(ScIndustrialZone.class);
	
	public ScIndustrialZone getScIndustrialZone(){
		return scIndustrialZone;
	}
	
	public void setScIndustrialZone(ScIndustrialZone scIndustrialZone){
		this.scIndustrialZone = scIndustrialZone;
	}
}