package com.hhwy.purchaseweb.contract.smagrepunishcomp.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagrepunishcomp.domain.SmAgrePunishCompVo;
import com.hhwy.selling.domain.SmAgrePunishComp;

/**
 * ISmAgrePunishCompService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmAgrePunishCompService{
	
	/**
	 * 分页获取对象SmAgrePunishComp
	 * @param SmAgrePunishCompVo
	 * @return QueryResult
	 */
	public QueryResult<SmAgrePunishComp> getSmAgrePunishCompByPage(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmAgrePunishComp列表
	 * @param SmAgrePunishCompVo
	 * @return List
	 */
	public List<SmAgrePunishComp> getSmAgrePunishCompListByParams(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmAgrePunishComp数量
	 * @param SmAgrePunishCompVo
	 * @return Integer
	 */
	public Integer getSmAgrePunishCompCountByParams(SmAgrePunishCompVo smAgrePunishCompVo);
	
	/**
	 * 根据查询条件获取单个对象SmAgrePunishComp
	 * @param SmAgrePunishCompVo
	 * @return SmAgrePunishComp
	 */
	public SmAgrePunishComp getSmAgrePunishCompOneByParams(SmAgrePunishCompVo smAgrePunishCompVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmAgrePunishComp
	 * @param ID
	 * @return SmAgrePunishComp
	 */
	public SmAgrePunishComp getSmAgrePunishCompById(String id);
	
	/**
	 * 添加对象SmAgrePunishComp
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp);
	
	/**
	 * 添加对象SmAgrePunishComp列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgrePunishCompList(List<SmAgrePunishComp> smAgrePunishCompList);
	
	/**
	 * 更新对象SmAgrePunishComp
	 * @param 实体对象
	 * @return SmAgrePunishComp
	 */
	public void updateSmAgrePunishComp(SmAgrePunishComp smAgrePunishComp);
	
	/**
	 * 删除对象SmAgrePunishComp
	 * @param id数据组
	 */
	public void deleteSmAgrePunishComp(String[] ids);
	
	/**
	 * 根据合同标识删除对象SmAgrePunishComp
	 * @param id数据组
	 */
	public void deleteSmAgrePunishCompByAgreId(String[] ids);

}