package com.hhwy.purchaseweb.settlement.base.smcompanycostdetail.service;

import java.util.List;

import com.hhwy.purchase.domain.SmCompanyCostDetail;

/**
 * ISmCompanyCostDetailService	批发市场购电支出明细service
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmCompanyCostDetailService{
	
	/**
	 * @Title: getCostDetailListBySchemeId
	 * @Description: 通过方案id获取所有 批发市场购电支出明细
	 * @param schemeId
	 * @return
	 * @throws Exception 
	 * List<SmCompanyCostDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年11月29日 上午11:18:00
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年11月29日 上午11:18:00
	 * @since  1.0.0
	 */
	public List<SmCompanyCostDetail> getCostDetailListBySchemeId(String schemeId) throws Exception;
	
	
	/**
	 * 添加对象SmCompanyCostDetail列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmCompanyCostDetailList(List<SmCompanyCostDetail> smCompanyCostDetailList);
	
	
	/**
	 * @Title: deleteSmCompanyCostDetailBySchemeId
	 * @Description: 根据月度购电方案id 删除‘批发市场购电支出明细’信息
	 * @param schemeIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年3月30日 下午4:47:36
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年3月30日 下午4:47:36
	 * @since  1.0.0
	 */
	public void deleteSmCompanyCostDetailBySchemeId(String[] schemeIds);
}