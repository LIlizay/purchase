package com.hhwy.purchaseweb.forecast.phftradingcenter.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfTradingCenter;
import com.hhwy.purchaseweb.forecast.phftradingcenter.domain.PhfTradingCenterVo;

/**
 * IPhfTradingCenterService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfTradingCenterService{
	
	/**
	 * 分页获取对象PhfTradingCenter
	 * @param PhfTradingCenterVo
	 * @return QueryResult
	 */
	public QueryResult<PhfTradingCenter> getPhfTradingCenterByPage(PhfTradingCenterVo phfTradingCenterVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfTradingCenter列表
	 * @param PhfTradingCenterVo
	 * @return List
	 */
	public List<PhfTradingCenter> getPhfTradingCenterListByParams(PhfTradingCenterVo phfTradingCenterVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfTradingCenter数量
	 * @param PhfTradingCenterVo
	 * @return Integer
	 */
	public Integer getPhfTradingCenterCountByParams(PhfTradingCenterVo phfTradingCenterVo);
	
	/**
	 * 根据查询条件获取单个对象PhfTradingCenter
	 * @param PhfTradingCenterVo
	 * @return PhfTradingCenter
	 */
	public PhfTradingCenter getPhfTradingCenterOneByParams(PhfTradingCenterVo phfTradingCenterVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfTradingCenter
	 * @param ID
	 * @return PhfTradingCenter
	 */
	public PhfTradingCenter getPhfTradingCenterById(String id);
	
	/**
	 * 添加对象PhfTradingCenter
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfTradingCenter(PhfTradingCenter phfTradingCenter);
	
	/**
	 * 添加对象PhfTradingCenter列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfTradingCenterList(List<PhfTradingCenter> phfTradingCenterList);
	
	/**
	 * 更新对象PhfTradingCenter
	 * @param 实体对象
	 * @return PhfTradingCenter
	 */
	public void updatePhfTradingCenter(PhfTradingCenter phfTradingCenter);
	
	/**
	 * 删除对象PhfTradingCenter
	 * @param id数据组
	 */
	public void deletePhfTradingCenter(String[] ids);
	
	/**
	 * 保存预测模块的信息
	 */
	public String saveForecastInfo(PhfTradingCenterVo phfTradingCenterVo)throws Exception;
	
	/**
	 * 根据id查询预测模块具体信息
	 */
	public PhfTradingCenterVo getForecast(String id)throws Exception;
	
	/**
	 * 更新预测模块信息
	 * @throws Exception 
	 */
	public String updateForecastInfo(PhfTradingCenterVo phfTradingCenterVo) throws Exception;
}