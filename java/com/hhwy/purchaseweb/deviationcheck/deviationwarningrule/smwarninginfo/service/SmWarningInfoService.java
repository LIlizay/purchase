package com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.deviationcheck.deviationwarningrule.smwarninginfo.domain.SmWarningInfoVo;
import com.hhwy.selling.domain.SmWarningInfo;

/**
 * ISmWarningInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmWarningInfoService{
	
	/**
	 * 分页获取对象SmWarningInfo
	 * @param SmWarningInfoVo
	 * @return QueryResult
	 */
	public QueryResult<SmWarningInfo> getSmWarningInfoByPage(SmWarningInfoVo smWarningInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmWarningInfo列表
	 * @param SmWarningInfoVo
	 * @return List
	 */
	public List<SmWarningInfo> getSmWarningInfoListByParams(SmWarningInfoVo smWarningInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmWarningInfo数量
	 * @param SmWarningInfoVo
	 * @return Integer
	 */
	public Integer getSmWarningInfoCountByParams(SmWarningInfoVo smWarningInfoVo);
	
	/**
	 * 根据查询条件获取单个对象SmWarningInfo
	 * @param SmWarningInfoVo
	 * @return SmWarningInfo
	 */
	public SmWarningInfo getSmWarningInfoOneByParams(SmWarningInfoVo smWarningInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmWarningInfo
	 * @param ID
	 * @return SmWarningInfo
	 */
	public SmWarningInfo getSmWarningInfoById(String id);
	
	/**
	 * 添加对象SmWarningInfo
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmWarningInfo(SmWarningInfo smWarningInfo);
	
	/**
	 * 添加对象SmWarningInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmWarningInfoList(List<SmWarningInfo> smWarningInfoList);
	
	/**
	 * 更新对象SmWarningInfo
	 * @param 实体对象
	 * @return SmWarningInfo
	 */
	public void updateSmWarningInfo(SmWarningInfo smWarningInfo);
	
	/**
	 * 删除对象SmWarningInfo
	 * @param id数据组
	 */
	public void deleteSmWarningInfo(String[] ids);

}