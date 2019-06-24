package com.hhwy.purchaseweb.contract.smagretemplate.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateDetail;
import com.hhwy.purchaseweb.contract.smagretemplate.domain.SmAgreTemplateVo;
import com.hhwy.selling.domain.SmAgreTemplate;

/**
 * ISmAgreTemplateService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmAgreTemplateService{
	
	/**
	 * 分页获取对象SmAgreTemplate
	 * @param SmAgreTemplateVo
	 * @return QueryResult
	 */
	public QueryResult<SmAgreTemplateDetail> getSmAgreTemplateByPage(SmAgreTemplateVo smAgreTemplateVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmAgreTemplate列表
	 * @param SmAgreTemplateVo
	 * @return List
	 */
	public List<SmAgreTemplateDetail> getSmAgreTemplateListByParams(SmAgreTemplateVo smAgreTemplateVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmAgreTemplate数量
	 * @param SmAgreTemplateVo
	 * @return Integer
	 */
	public Integer getSmAgreTemplateCountByParams(SmAgreTemplateVo smAgreTemplateVo);
	
	/**
	 * 根据查询条件获取单个对象SmAgreTemplate
	 * @param SmAgreTemplateVo
	 * @return SmAgreTemplate
	 */
	public SmAgreTemplateDetail getSmAgreTemplateOneByParams(SmAgreTemplateVo smAgreTemplateVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmAgreTemplate
	 * @param ID
	 * @return SmAgreTemplate
	 */
	public SmAgreTemplate getSmAgreTemplateById(String id);
	
	/**
	 * 获取下拉list
	 * getSelect(描述这个方法的作用)<br/>
	 * @return 
	 * List<Map<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<Map<String,Object>> getSelect(String templateType);
	
	/**
	 * 添加对象SmAgreTemplate
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgreTemplate(SmAgreTemplate smAgreTemplate);
	
	/**
	 * 添加对象SmAgreTemplate列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgreTemplateList(List<SmAgreTemplate> smAgreTemplateList);
	
	/**
	 * 生效
	 * effect(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void effect(String id);
	
	/**
	 * 失效
	 * invalid(描述这个方法的作用)<br/>
	 * @param id 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void invalid(String id);
	
	/**
	 * 更新对象SmAgreTemplate
	 * @param 实体对象
	 * @return SmAgreTemplate
	 */
	public void updateSmAgreTemplate(SmAgreTemplate smAgreTemplate);
	
	/**
	 * 删除对象SmAgreTemplate
	 * @param id数据组
	 */
	public void deleteSmAgreTemplate(String[] ids);

}