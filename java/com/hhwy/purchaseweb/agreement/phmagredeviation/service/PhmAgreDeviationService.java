package com.hhwy.purchaseweb.agreement.phmagredeviation.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmAgreDeviation;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationDetail;
import com.hhwy.purchaseweb.agreement.phmagredeviation.domain.PhmAgreDeviationVo;

/**
 * IPhmAgreDeviationService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmAgreDeviationService{
	
	/**
	 * 分页获取对象PhmAgreDeviation
	 * @param PhmAgreDeviationVo
	 * @return QueryResult
	 */
	public QueryResult<PhmAgreDeviationDetail> getPhmAgreDeviationByPage(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmAgreDeviation列表
	 * @param PhmAgreDeviationVo
	 * @return List
	 */
	public List<PhmAgreDeviationDetail> getPhmAgreDeviationListByParams(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmAgreDeviation数量
	 * @param PhmAgreDeviationVo
	 * @return Integer
	 */
	public Integer getPhmAgreDeviationCountByParams(PhmAgreDeviationVo phmAgreDeviationVo);
	
	/**
	 * 根据查询条件获取单个对象PhmAgreDeviation
	 * @param PhmAgreDeviationVo
	 * @return PhmAgreDeviation
	 */
	public PhmAgreDeviationDetail getPhmAgreDeviationOneByParams(PhmAgreDeviationVo phmAgreDeviationVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmAgreDeviation
	 * @param ID
	 * @return PhmAgreDeviation
	 */
	public PhmAgreDeviation getPhmAgreDeviationById(String id);
	
	/**
	 * 添加对象PhmAgreDeviation
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmAgreDeviation(PhmAgreDeviation phmAgreDeviation);
	
	/**
	 * 添加对象PhmAgreDeviation列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmAgreDeviationList(List<PhmAgreDeviation> phmAgreDeviationList);
	
	/**
	 * 更新对象PhmAgreDeviation
	 * @param 实体对象
	 * @return PhmAgreDeviation
	 */
	public void updatePhmAgreDeviation(PhmAgreDeviation phmAgreDeviation);
	
	/**
	 * 删除对象PhmAgreDeviation
	 * @param id数据组
	 */
	public void deletePhmAgreDeviation(String[] ids);

}