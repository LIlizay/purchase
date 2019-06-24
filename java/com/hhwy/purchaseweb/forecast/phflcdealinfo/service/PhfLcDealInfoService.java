package com.hhwy.purchaseweb.forecast.phflcdealinfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfLcDealInfo;
import com.hhwy.purchaseweb.forecast.phflcdealinfo.domain.PhfLcDealInfoVo;

/**
 * IPhfLcDealInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfLcDealInfoService{
	
	/**
	 * 分页获取对象PhfLcDealInfo
	 * @param PhfLcDealInfoVo
	 * @return QueryResult
	 */
	public QueryResult<PhfLcDealInfo> getPhfLcDealInfoByPage(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfLcDealInfo列表
	 * @param PhfLcDealInfoVo
	 * @return List
	 */
	public List<PhfLcDealInfo> getPhfLcDealInfoListByParams(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfLcDealInfo数量
	 * @param PhfLcDealInfoVo
	 * @return Integer
	 */
	public Integer getPhfLcDealInfoCountByParams(PhfLcDealInfoVo phfLcDealInfoVo);
	
	/**
	 * 根据查询条件获取单个对象PhfLcDealInfo
	 * @param PhfLcDealInfoVo
	 * @return PhfLcDealInfo
	 */
	public PhfLcDealInfo getPhfLcDealInfoOneByParams(PhfLcDealInfoVo phfLcDealInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfLcDealInfo
	 * @param ID
	 * @return PhfLcDealInfo
	 */
	public PhfLcDealInfo getPhfLcDealInfoById(String id);
	
	/**
	 * 添加对象PhfLcDealInfo
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfLcDealInfo(PhfLcDealInfo phfLcDealInfo);
	
	/**
	 * 添加对象PhfLcDealInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfLcDealInfoList(List<PhfLcDealInfo> phfLcDealInfoList);
	
	/**
	 * 更新对象PhfLcDealInfo
	 * @param 实体对象
	 * @return PhfLcDealInfo
	 */
	public void updatePhfLcDealInfo(PhfLcDealInfo phfLcDealInfo);
	
	/**
	 * 删除对象PhfLcDealInfo
	 * @param id数据组
	 */
	public void deletePhfLcDealInfo(String[] ids);

	/**
	 * 获取近3年长协电价 
	 * getPhfLcDealInfoByYear(描述这个方法的作用)<br/>
	 * @param year
	 * @return
	 * @throws Exception 
	 * List<PhfLcDealInfo>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhfLcDealInfo> getPhfLcDealInfoByYear(String year); 
}