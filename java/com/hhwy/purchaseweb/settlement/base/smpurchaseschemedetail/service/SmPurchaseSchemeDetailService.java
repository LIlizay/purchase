package com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.service;

import java.util.List;

import com.hhwy.purchase.domain.SmPurchaseSchemeDetail;
import com.hhwy.purchaseweb.settlement.base.smpurchaseschemedetail.domain.SmPurchaseSchemeDetailDetail;

/**
 * ISmPurchaseSchemeDetailService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmPurchaseSchemeDetailService{
	

	/**
	 * 添加对象SmPurchaseSchemeDetail列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmPurchaseSchemeDetailList(List<SmPurchaseSchemeDetail> smPurchaseSchemeDetailList);
	
	/**
	 * @Title: deleteSchemeDetailBySchemeId
	 * @Description: 根据方案id删除其下所有方案详情信息
	 * @param schemeId 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月22日 下午5:47:50
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月22日 下午5:47:50
	 * @since  1.0.0
	 */
	public void deleteSchemeDetailBySchemeId(String schemeId);
	
	/**
	 * @Title: getSchemeDetailBySchemeId
	 * @Description: 根据方案id获取方案详情信息
	 * @param schemeId
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午5:18:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午5:18:40
	 * @since  1.0.0
	 */
	public List<SmPurchaseSchemeDetailDetail> getSchemeDetailBySchemeId(String schemeId);
	
	/**
	 * @Title: getSchemeDetailListByYm
	 * @Description: 根据ym获取当月的所有合同用户信息（方案详情信息）
	 * @param ym	yyyyMM格式或者yyyy-MM格式
	 * @return 
	 * List<SmPurchaseSchemeDetailDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年5月28日 下午5:21:03
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年5月28日 下午5:21:03
	 * @since  1.0.0
	 */
	public List<SmPurchaseSchemeDetailDetail> getSchemeDetailListByYm(String ym);
}