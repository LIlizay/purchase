package com.hhwy.purchaseweb.statistical.phmhistoryreportpq.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmHistoryReportPq;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportpq.domain.PhmHistoryReportPqVo;

/**
 * IPhmHistoryReportPqService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmHistoryReportPqService{
	
	/**
	 * 分页获取对象PhmHistoryReportPq
	 * @param PhmHistoryReportPqVo
	 * @return QueryResult
	 */
	public QueryResult<PhmHistoryReportPq> getPhmHistoryReportPqByPage(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmHistoryReportPq列表
	 * @param PhmHistoryReportPqVo
	 * @return List
	 */
	public List<PhmHistoryReportPq> getPhmHistoryReportPqListByParams(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPq数量
	 * @param PhmHistoryReportPqVo
	 * @return Integer
	 */
	public Integer getPhmHistoryReportPqCountByParams(PhmHistoryReportPqVo phmHistoryReportPqVo);
	
	/**
	 * 根据查询条件获取单个对象PhmHistoryReportPq
	 * @param PhmHistoryReportPqVo
	 * @return PhmHistoryReportPq
	 */
	public PhmHistoryReportPq getPhmHistoryReportPqOneByParams(PhmHistoryReportPqVo phmHistoryReportPqVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmHistoryReportPq
	 * @param ID
	 * @return PhmHistoryReportPq
	 */
	public PhmHistoryReportPq getPhmHistoryReportPqById(String id);
	
	/**
	 * 根据年份获取历史电量
	 * getPqHistoryByYear(描述这个方法的作用)<br/>
	 * @param year
	 * @return
	 * @throws Exception 
	 * List<PhmHistoryReportPq>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmHistoryReportPq> getPqHistoryByYear(String year) throws Exception;
	
	/**
	 * 添加对象PhmHistoryReportPq
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmHistoryReportPq(PhmHistoryReportPq phmHistoryReportPq);
	
	/**
	 * 添加对象PhmHistoryReportPq列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmHistoryReportPqList(List<PhmHistoryReportPq> phmHistoryReportPqList);
	
	/**
	 * 更新对象PhmHistoryReportPq
	 * @param 实体对象
	 * @return PhmHistoryReportPq
	 */
	public void updatePhmHistoryReportPq(PhmHistoryReportPq phmHistoryReportPq);
	
	/**
	 * 删除对象PhmHistoryReportPq
	 * @param id数据组
	 */
	public void deletePhmHistoryReportPq(String[] ids);

	 /**
     * 根据年份获取历史报量信息
     * getPqHistoryPage(描述这个方法的作用)<br/>
     * @param ym
     * @return 
     * PhmHistoryReportPqDetail
     * @exception 
     * @since  1.0.0
     */
     public List<PhmHistoryReportPqDetail> getPqHistoryPage(String ym);
}