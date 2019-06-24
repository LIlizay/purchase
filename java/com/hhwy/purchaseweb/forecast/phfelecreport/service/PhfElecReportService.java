package com.hhwy.purchaseweb.forecast.phfelecreport.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfElecReport;
import com.hhwy.purchaseweb.forecast.phfelecreport.domain.PhfElecReportVo;

/**
 * IPhfElecReportService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfElecReportService{
	
	/**
	 * 分页获取对象PhfElecReport
	 * @param PhfElecReportVo
	 * @return QueryResult
	 */
	public QueryResult<PhfElecReport> getPhfElecReportByPage(PhfElecReportVo phfElecReportVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfElecReport列表
	 * @param PhfElecReportVo
	 * @return List
	 */
	public List<PhfElecReport> getPhfElecReportListByParams(PhfElecReportVo phfElecReportVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfElecReport数量
	 * @param PhfElecReportVo
	 * @return Integer
	 */
	public Integer getPhfElecReportCountByParams(PhfElecReportVo phfElecReportVo);
	
	/**
	 * 根据查询条件获取单个对象PhfElecReport
	 * @param PhfElecReportVo
	 * @return PhfElecReport
	 */
	public PhfElecReport getPhfElecReportOneByParams(PhfElecReportVo phfElecReportVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfElecReport
	 * @param ID
	 * @return PhfElecReport
	 */
	public PhfElecReport getPhfElecReportById(String id);
	
	/**
	 * 添加对象PhfElecReport
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfElecReport(PhfElecReport phfElecReport);
	
	/**
	 * 添加对象PhfElecReport列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfElecReportList(List<PhfElecReport> phfElecReportList);
	
	/**
	 * 更新对象PhfElecReport
	 * @param 实体对象
	 * @return PhfElecReport
	 */
	public void updatePhfElecReport(PhfElecReport phfElecReport);
	
	/**
	 * 删除对象PhfElecReport
	 * @param id数据组
	 */
	public void deletePhfElecReport(String[] ids);

}