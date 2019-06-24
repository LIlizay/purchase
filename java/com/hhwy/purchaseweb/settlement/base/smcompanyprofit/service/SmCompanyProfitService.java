package com.hhwy.purchaseweb.settlement.base.smcompanyprofit.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmCompanyProfit;
import com.hhwy.purchaseweb.settlement.base.smcompanyprofit.domain.SmCompanyProfitVo;

/**
 * ISmCompanyProfitService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmCompanyProfitService{
	
	/**
	 * 分页获取对象SmCompanyProfit
	 * @param SmCompanyProfitVo
	 * @return QueryResult
	 */
	public QueryResult<SmCompanyProfit> getSmCompanyProfitByPage(SmCompanyProfitVo smCompanyProfitVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmCompanyProfit列表
	 * @param SmCompanyProfitVo
	 * @return List
	 */
	public List<SmCompanyProfit> getSmCompanyProfitListByParams(SmCompanyProfitVo smCompanyProfitVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmCompanyProfit数量
	 * @param SmCompanyProfitVo
	 * @return Integer
	 */
	public Integer getSmCompanyProfitCountByParams(SmCompanyProfitVo smCompanyProfitVo);
	
	/**
	 * 根据查询条件获取单个对象SmCompanyProfit
	 * @param SmCompanyProfitVo
	 * @return SmCompanyProfit
	 */
	public SmCompanyProfit getSmCompanyProfitOneByParams(SmCompanyProfitVo smCompanyProfitVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmCompanyProfit
	 * @param ID
	 * @return SmCompanyProfit
	 */
	public SmCompanyProfit getSmCompanyProfitById(String id);
	
	/**
	 * 添加对象SmCompanyProfit
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmCompanyProfit(SmCompanyProfit smCompanyProfit);
	
	/**
	 * 添加对象SmCompanyProfit列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmCompanyProfitList(List<SmCompanyProfit> smCompanyProfitList);
	
	/**
	 * 更新对象SmCompanyProfit
	 * @param 实体对象
	 * @return SmCompanyProfit
	 */
	public void updateSmCompanyProfit(SmCompanyProfit smCompanyProfit);
	
	/**
	 * 删除对象SmCompanyProfit
	 * @param id数据组
	 */
	public void deleteSmCompanyProfit(String[] ids);
	
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
	public SmCompanyProfit getSmCompanyProfitBySchemeId(String schemeId) throws Exception;
	
	/**
	 * @Title: deleteSmCompanyProfitBySchemeIds
	 * @Description: 根据方案id数组删除公司结算数据信息及用户收益列表信息
	 * @param schemeIds void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2017年9月28日 上午9:16:22
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2017年9月28日 上午9:16:22
	 * @since  1.0.0
	 */
	public void deleteComAndConsProfitBySchemeIds(String[] schemeIds);
}