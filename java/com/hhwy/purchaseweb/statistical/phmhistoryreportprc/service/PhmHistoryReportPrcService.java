package com.hhwy.purchaseweb.statistical.phmhistoryreportprc.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmHistoryReportPrc;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcDetail;
import com.hhwy.purchaseweb.statistical.phmhistoryreportprc.domain.PhmHistoryReportPrcVo;

/**
 * IPhmHistoryReportPrcService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmHistoryReportPrcService{
	
	/**
	 * 分页获取对象PhmHistoryReportPrc
	 * @param PhmHistoryReportPrcVo
	 * @return QueryResult
	 */
	public QueryResult<PhmHistoryReportPrc> getPhmHistoryReportPrcByPage(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmHistoryReportPrc列表
	 * @param PhmHistoryReportPrcVo
	 * @return List
	 */
	public List<PhmHistoryReportPrc> getPhmHistoryReportPrcListByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmHistoryReportPrc数量
	 * @param PhmHistoryReportPrcVo
	 * @return Integer
	 */
	public Integer getPhmHistoryReportPrcCountByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo);
	
	/**
	 * 根据查询条件获取单个对象PhmHistoryReportPrc
	 * @param PhmHistoryReportPrcVo
	 * @return PhmHistoryReportPrc
	 */
	public PhmHistoryReportPrc getPhmHistoryReportPrcOneByParams(PhmHistoryReportPrcVo phmHistoryReportPrcVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmHistoryReportPrc
	 * @param ID
	 * @return PhmHistoryReportPrc
	 */
	public PhmHistoryReportPrc getPhmHistoryReportPrcById(String id);
	
	/**
	 * 添加对象PhmHistoryReportPrc
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmHistoryReportPrc(PhmHistoryReportPrc phmHistoryReportPrc);
	
	/**
	 * 添加对象PhmHistoryReportPrc列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmHistoryReportPrcList(List<PhmHistoryReportPrc> phmHistoryReportPrcList);
	
	/**
	 * 更新对象PhmHistoryReportPrc
	 * @param 实体对象
	 * @return PhmHistoryReportPrc
	 */
	public void updatePhmHistoryReportPrc(PhmHistoryReportPrc phmHistoryReportPrc);
	
	/**
	 * 删除对象PhmHistoryReportPrc
	 * @param id数据组
	 */
	public void deletePhmHistoryReportPrc(String[] ids);
	
	 /**
	  * 根据年份获取历史报价信息
	  * getPriceHistoryPage(描述这个方法的作用)<br/>
	  * @param map
	  * @return 
	  * List<PhmHistoryReportPrcDetail>
	  * @exception 
	  * @since  1.0.0
	  */
    public List<PhmHistoryReportPrcDetail> getPriceHistoryPage(Map<String,Object> map);
}