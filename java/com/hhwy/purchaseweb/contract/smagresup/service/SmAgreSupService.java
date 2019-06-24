package com.hhwy.purchaseweb.contract.smagresup.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupDetail;
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupVo;
import com.hhwy.selling.domain.SmAgreSup;

/**
 * ISmAgreSupService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmAgreSupService{
	
	/**
	 * 分页获取对象SmAgreSup
	 * @param SmAgreSupVo
	 * @return QueryResult
	 */
	public QueryResult<SmAgreSupDetail> getSmAgreSupByPage(SmAgreSupVo smAgreSupVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmAgreSup列表
	 * @param SmAgreSupVo
	 * @return List
	 */
	public List<SmAgreSupDetail> getSmAgreSupListByParams(SmAgreSupVo smAgreSupVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmAgreSup数量
	 * @param SmAgreSupVo
	 * @return Integer
	 */
	public Integer getSmAgreSupCountByParams(SmAgreSupVo smAgreSupVo);
	
	/**
	 * 根据查询条件获取单个对象SmAgreSup
	 * @param SmAgreSupVo
	 * @return SmAgreSup
	 */
	public SmAgreSupDetail getSmAgreSupOneByParams(SmAgreSupVo smAgreSupVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmAgreSup
	 * @param ID
	 * @return SmAgreSup
	 */
	public SmAgreSup getSmAgreSupById(String id);
	
	/**
	 * 添加对象SmAgreSup
	 * @param 实体对象
	 * @return null
	 */
	public SmAgreSup saveSmAgreSup(SmAgreSup smAgreSup);
	
	/**
	 * 添加对象SmAgreSup列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgreSupList(List<SmAgreSup> smAgreSupList);
	
	/**
	 * 更新对象SmAgreSup
	 * @param 实体对象
	 * @return SmAgreSup
	 */
	public void updateSmAgreSup(SmAgreSup smAgreSup);
	
	/**
	 * 删除对象SmAgreSup 根据售电合同id
	 * @param id数据组
	 * by-zhangzhao
	 */
	public void deleteSmAgreSup(String[] smppaIds);

	/**
	 * 删除对象SmAgreSup 根据补充协议id
	 * @param id数据组
	 * by-zhangzhao
	 */
	public void deleteBySupId(String[] ids);
	
	

}