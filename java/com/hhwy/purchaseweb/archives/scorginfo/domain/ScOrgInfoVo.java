package com.hhwy.purchaseweb.archives.scorginfo.domain;

import com.hhwy.business.core.modelutil.BaseModel;
import com.hhwy.business.core.modelutil.PagingProperty;
import com.hhwy.selling.domain.ScOrgInfo;

 /**
 * <b>类 名 称：</b>ScOrgInfoVo<br/>
 * <b>类 描 述：</b><br/>		供电公司信息的vo类
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2016年11月22日 下午4:00:14<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public class ScOrgInfoVo extends PagingProperty{

	private ScOrgInfo scOrgInfo = BaseModel.getModel(ScOrgInfo.class);
	
	public ScOrgInfo getScOrgInfo(){
		return scOrgInfo;
	}
	
	public void setScOrgInfo(ScOrgInfo scOrgInfo){
		this.scOrgInfo = scOrgInfo;
	}
}