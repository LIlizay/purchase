package com.hhwy.purchaseweb.forecast.phfpurcreport.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfPurcReport;
import com.hhwy.purchaseweb.forecast.phfpurcreport.domain.PhfPurcReportVo;

/**
 * IPhfPurcReportService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhfPurcReportService{
	
	/**
	 * 分页获取对象PhfPurcReport
	 * @param PhfPurcReportVo
	 * @return QueryResult
	 */
	public QueryResult<PhfPurcReport> getPhfPurcReportByPage(PhfPurcReportVo phfPurcReportVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhfPurcReport列表
	 * @param PhfPurcReportVo
	 * @return List
	 */
	public List<PhfPurcReport> getPhfPurcReportListByParams(PhfPurcReportVo phfPurcReportVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhfPurcReport数量
	 * @param PhfPurcReportVo
	 * @return Integer
	 */
	public Integer getPhfPurcReportCountByParams(PhfPurcReportVo phfPurcReportVo);
	
	/**
	 * 根据查询条件获取单个对象PhfPurcReport
	 * @param PhfPurcReportVo
	 * @return PhfPurcReport
	 */
	public PhfPurcReport getPhfPurcReportOneByParams(PhfPurcReportVo phfPurcReportVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhfPurcReport
	 * @param ID
	 * @return PhfPurcReport
	 */
	public PhfPurcReport getPhfPurcReportById(String id);
	
	/**
	 * 添加对象PhfPurcReport
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfPurcReport(PhfPurcReport phfPurcReport);
	
	/**
	 * 添加对象PhfPurcReport列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhfPurcReportList(List<PhfPurcReport> phfPurcReportList);
	
	/**
	 * 更新对象PhfPurcReport
	 * @param 实体对象
	 * @return PhfPurcReport
	 */
	public void updatePhfPurcReport(PhfPurcReport phfPurcReport);
	
	/**
	 * 删除对象PhfPurcReport
	 * @param id数据组
	 */
	public void deletePhfPurcReport(String[] ids);

}