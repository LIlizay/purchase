package com.hhwy.purchaseweb.contract.smagrepunishcons.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagrepunishcons.domain.SmAgrePunishConsVo;
import com.hhwy.selling.domain.SmAgrePunishCons;

/**
 * ISmAgrePunishConsService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmAgrePunishConsService{
	
	/**
	 * 分页获取对象SmAgrePunishCons
	 * @param SmAgrePunishConsVo
	 * @return QueryResult
	 */
	public QueryResult<SmAgrePunishCons> getSmAgrePunishConsByPage(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmAgrePunishCons列表
	 * @param SmAgrePunishConsVo
	 * @return List
	 */
	public List<SmAgrePunishCons> getSmAgrePunishConsListByParams(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmAgrePunishCons数量
	 * @param SmAgrePunishConsVo
	 * @return Integer
	 */
	public Integer getSmAgrePunishConsCountByParams(SmAgrePunishConsVo smAgrePunishConsVo);
	
	/**
	 * 根据查询条件获取单个对象SmAgrePunishCons
	 * @param SmAgrePunishConsVo
	 * @return SmAgrePunishCons
	 */
	public SmAgrePunishCons getSmAgrePunishConsOneByParams(SmAgrePunishConsVo smAgrePunishConsVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmAgrePunishCons
	 * @param ID
	 * @return SmAgrePunishCons
	 */
	public SmAgrePunishCons getSmAgrePunishConsById(String id);
	
	/**
	 * 添加对象SmAgrePunishCons
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons);
	
	/**
	 * 添加对象SmAgrePunishCons列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgrePunishConsList(List<SmAgrePunishCons> smAgrePunishConsList);
	
	/**
	 * 更新对象SmAgrePunishCons
	 * @param 实体对象
	 * @return SmAgrePunishCons
	 */
	public void updateSmAgrePunishCons(SmAgrePunishCons smAgrePunishCons);
	
	/**
	 * 删除对象SmAgrePunishCons
	 * @param id数据组
	 */
	public void deleteSmAgrePunishCons(String[] ids);
	
	/**
	 * 根据合同标识删除对象SmAgrePunishCons
	 * @param id数据组
	 */
	public void deleteSmAgrePunishConsByAgreId(String[] ids);

}