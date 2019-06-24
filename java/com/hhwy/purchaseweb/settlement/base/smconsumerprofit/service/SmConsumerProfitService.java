package com.hhwy.purchaseweb.settlement.base.smconsumerprofit.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmConsumerProfit;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitDetail;
import com.hhwy.purchaseweb.settlement.base.smconsumerprofit.domain.SmConsumerProfitVo;

/**
 * ISmConsumerProfitService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmConsumerProfitService{
	
	/**
	 * 分页获取对象SmConsumerProfit
	 * @param ID
	 * @return SmConsumerProfit
	 */
	public QueryResult<SmConsumerProfitDetail> getSmConsumerProfitByPage(SmConsumerProfitVo smConsumerProfitVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmConsumerProfit列表
	 * @param SmConsumerProfitVo
	 * @return List
	 */
	public List<SmConsumerProfitDetail> getSmConsumerProfitListByParams(SmConsumerProfitVo smConsumerProfitVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmConsumerProfit数量
	 * @param SmConsumerProfitVo
	 * @return Integer
	 */
	public Integer getSmConsumerProfitCountByParams(SmConsumerProfitVo smConsumerProfitVo);
	
	/**
	 * 根据查询条件获取单个对象SmConsumerProfit
	 * @param SmConsumerProfitVo
	 * @return SmConsumerProfit
	 */
	public SmConsumerProfitDetail getSmConsumerProfitOneByParams(SmConsumerProfitVo smConsumerProfitVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmConsumerProfit
	 * @param ID
	 * @return SmConsumerProfit
	 */
	public SmConsumerProfit getSmConsumerProfitById(String id);
	
	/**
	 * 添加对象SmConsumerProfit
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsumerProfit(SmConsumerProfit smConsumerProfit);
	
	/**
	 * 添加对象SmConsumerProfit列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsumerProfitList(List<SmConsumerProfit> smConsumerProfitList);
	
	/**
	 * 更新对象SmConsumerProfit
	 * @param 实体对象
	 * @return SmConsumerProfit
	 */
	public void updateSmConsumerProfit(SmConsumerProfit smConsumerProfit);
	
	/**
	 * 删除对象SmConsumerProfit
	 * @param id数据组
	 */
	public void deleteSmConsumerProfit(String[] ids);
	
	/**
	 * @Title: getSmCompanyProfitBySchemeId
	 * @Description: 根据方案id获取售电公司结算信息
	 * @param schemeId
	 * @return
	 * @throws Exception SmCompanyProfitVo
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月27日 上午1:14:59
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月27日 上午1:14:59
	 * @since  1.0.0
	 */
	public List<SmConsumerProfitDetail> getSmConsumerProfitListBySchemeId(String schemeId) throws Exception;
	
	/**
	 * @Title: deleteSmConsumerProfitBySchemeIds
	 * @Description: 根据方案id列表删除用户结算信息
	 * @param schemeIds void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月28日 上午10:46:26
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月28日 上午10:46:26
	 * @since  1.0.0
	 */
	public void deleteSmConsumerProfitBySchemeIds(String[] schemeIds);
}