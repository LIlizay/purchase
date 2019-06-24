package com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.conswarningrule.smconswarninginfo.domain.SmConsWarningInfoVo;
import com.hhwy.selling.domain.SmConsWarningInfo;


/**
 * ISmConsWarningInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmConsWarningInfoService{
	
	/**
	 * 分页获取对象SmConsWarningInfo
	 * @param SmConsWarningInfoVo
	 * @return QueryResult
	 */
	public QueryResult<SmConsWarningInfo> getSmConsWarningInfoByPage(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmConsWarningInfo列表
	 * @param SmConsWarningInfoVo
	 * @return List
	 */
	public List<SmConsWarningInfo> getSmConsWarningInfoListByParams(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmConsWarningInfo数量
	 * @param SmConsWarningInfoVo
	 * @return Integer
	 */
	public Integer getSmConsWarningInfoCountByParams(SmConsWarningInfoVo smConsWarningInfoVo);
	
	/**
	 * 根据查询条件获取单个对象SmConsWarningInfo
	 * @param SmConsWarningInfoVo
	 * @return SmConsWarningInfo
	 */
	public SmConsWarningInfo getSmConsWarningInfoOneByParams(SmConsWarningInfoVo smConsWarningInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmConsWarningInfo
	 * @param ID
	 * @return SmConsWarningInfo
	 */
	public SmConsWarningInfo getSmConsWarningInfoById(String id);
	
	/**
	 * 添加对象SmConsWarningInfo
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsWarningInfo(SmConsWarningInfo smConsWarningInfo);
	
	/**
	 * 添加对象SmConsWarningInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmConsWarningInfoList(List<SmConsWarningInfo> smConsWarningInfoList);
	
	/**
	 * 更新对象SmConsWarningInfo
	 * @param 实体对象
	 * @return SmConsWarningInfo
	 */
	public void updateSmConsWarningInfo(SmConsWarningInfo smConsWarningInfo);
	
	/**
	 * 删除对象SmConsWarningInfo
	 * @param id数据组
	 */
	public void deleteSmConsWarningInfo(String[] ids);

}