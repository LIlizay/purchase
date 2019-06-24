package com.hhwy.purchaseweb.crm.smruleconfigure.service;

import java.util.List;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.SmRuleConfigure;
import com.hhwy.purchaseweb.crm.smruleconfigure.domain.SmRuleConfigureVo;

/**
 * ISmRuleConfigureService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmRuleConfigureService{
	
	/**
	 * 分页获取对象SmRuleConfigure
	 * @param SmRuleConfigureVo
	 * @return QueryResult
	 */
	public QueryResult<SmRuleConfigure> getSmRuleConfigureByPage(SmRuleConfigureVo smRuleConfigureVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmRuleConfigure列表
	 * @param SmRuleConfigureVo
	 * @return List
	 */
	public List<SmRuleConfigure> getSmRuleConfigureListByParams(SmRuleConfigureVo smRuleConfigureVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmRuleConfigure数量
	 * @param SmRuleConfigureVo
	 * @return Integer
	 */
	public Integer getSmRuleConfigureCountByParams(SmRuleConfigureVo smRuleConfigureVo);
	
	/**
	 * 根据查询条件获取单个对象SmRuleConfigure
	 * @param SmRuleConfigureVo
	 * @return SmRuleConfigure
	 */
	public SmRuleConfigure getSmRuleConfigureOneByParams(SmRuleConfigureVo smRuleConfigureVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmRuleConfigure
	 * @param ID
	 * @return SmRuleConfigure
	 */
	public SmRuleConfigure getSmRuleConfigureById(String id);
	
	/**
	 * 添加对象SmRuleConfigure
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmRuleConfigure(SmRuleConfigure smRuleConfigure);
	
	/**
	 * 添加对象SmRuleConfigure列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmRuleConfigureList(List<SmRuleConfigure> smRuleConfigureList);
	
	/**
	 * 更新对象SmRuleConfigure
	 * @param 实体对象
	 * @return SmRuleConfigure
	 */
	public void updateSmRuleConfigure(SmRuleConfigure smRuleConfigure);
	
	/**
	 * 删除对象SmRuleConfigure
	 * @param id数据组
	 */
	public void deleteSmRuleConfigure(String[] ids);
	
	/**
	 * 获取树
	 * getTree(描述这个方法的作用)<br/>
	 * @param nodeType
	 * @param provinceId
	 * @return 
	 * Object
	 * @exception 
	 * @since  1.0.0
	 */
	public Object getTree(String nodeType,String provinceId);
	
	/**
	 * 规则生效
	 * effect(描述这个方法的作用)<br/>
	 * @param id
	 * @return 
	 * ExecutionResult
	 * @exception 
	 * @since  1.0.0
	 */
	public ExecutionResult effect(String provinceId,String id) throws Exception;
	
	/**
	 * 规则失效
	 * invalid(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void invalid(String id) throws Exception;
	
}