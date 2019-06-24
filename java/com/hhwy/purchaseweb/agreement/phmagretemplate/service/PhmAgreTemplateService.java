package com.hhwy.purchaseweb.agreement.phmagretemplate.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmAgreTemplate;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateDetail;
import com.hhwy.purchaseweb.agreement.phmagretemplate.domain.PhmAgreTemplateVo;

/**
 * IPhmAgreTemplateService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmAgreTemplateService{
	
	/**
	 * 分页获取对象PhmAgreTemplate
	 * @param PhmAgreTemplateVo
	 * @return QueryResult
	 */
	public QueryResult<PhmAgreTemplateDetail> getPhmAgreTemplateByPage(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmAgreTemplate列表
	 * @param PhmAgreTemplateVo
	 * @return List
	 */
	public List<PhmAgreTemplateDetail> getPhmAgreTemplateListByParams(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmAgreTemplate数量
	 * @param PhmAgreTemplateVo
	 * @return Integer
	 */
	public Integer getPhmAgreTemplateCountByParams(PhmAgreTemplateVo phmAgreTemplateVo);
	
	/**
	 * 根据查询条件获取单个对象PhmAgreTemplate
	 * @param PhmAgreTemplateVo
	 * @return PhmAgreTemplate
	 */
	public PhmAgreTemplateDetail getPhmAgreTemplateOneByParams(PhmAgreTemplateVo phmAgreTemplateVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmAgreTemplate
	 * @param ID
	 * @return PhmAgreTemplate
	 */
	public PhmAgreTemplate getPhmAgreTemplateById(String id);
	
	/**
	 * 添加对象PhmAgreTemplate
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmAgreTemplate(PhmAgreTemplate phmAgreTemplate);
	
	/**
	 * 添加对象PhmAgreTemplate列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmAgreTemplateList(List<PhmAgreTemplate> phmAgreTemplateList);
	
	/**
     * 模板生效
     * effect(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void effect(String id);
    
    /**
     * 模板失效
     * invalid(描述这个方法的作用)<br/>
     * @param id 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void invalid(String id);
    
	/**
	 * 更新对象PhmAgreTemplate
	 * @param 实体对象
	 * @return PhmAgreTemplate
	 */
	public void updatePhmAgreTemplate(PhmAgreTemplate phmAgreTemplate);
	
	/**
	 * 删除对象PhmAgreTemplate
	 * @param id数据组
	 */
	public void deletePhmAgreTemplate(String[] ids);

}