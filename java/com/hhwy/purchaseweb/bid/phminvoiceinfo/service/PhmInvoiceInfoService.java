package com.hhwy.purchaseweb.bid.phminvoiceinfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmInvoiceInfo;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoDetail;
import com.hhwy.purchaseweb.bid.phminvoiceinfo.domain.PhmInvoiceInfoVo;

/**
 * IPhmInvoiceInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmInvoiceInfoService{
	
	/**
	 * 分页获取对象PhmInvoiceInfo
	 * @param PhmInvoiceInfoVo
	 * @return QueryResult
	 */
	public QueryResult<PhmInvoiceInfoDetail> getPhmInvoiceInfoByPage(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmInvoiceInfo列表
	 * @param PhmInvoiceInfoVo
	 * @return List
	 */
	public List<PhmInvoiceInfoDetail> getPhmInvoiceInfoListByParams(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmInvoiceInfo数量
	 * @param PhmInvoiceInfoVo
	 * @return Integer
	 */
	public Integer getPhmInvoiceInfoCountByParams(PhmInvoiceInfoVo phmInvoiceInfoVo);
	
	/**
	 * 根据查询条件获取单个对象PhmInvoiceInfo
	 * @param PhmInvoiceInfoVo
	 * @return PhmInvoiceInfo
	 */
	public PhmInvoiceInfoDetail getPhmInvoiceInfoOneByParams(PhmInvoiceInfoVo phmInvoiceInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmInvoiceInfo
	 * @param ID
	 * @return PhmInvoiceInfo
	 */
	public PhmInvoiceInfo getPhmInvoiceInfoById(String id);
	
	/**
	 * 添加对象PhmInvoiceInfo
	 * @param 实体对象
	 * @return null
	 */
	public PhmInvoiceInfo savePhmInvoiceInfo(PhmInvoiceInfo phmInvoiceInfo) throws Exception;
	
	/**
	 * 添加对象PhmInvoiceInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmInvoiceInfoList(List<PhmInvoiceInfo> phmInvoiceInfoList);
	
	/**
	 * 更新对象PhmInvoiceInfo
	 * @param 实体对象
	 * @return PhmInvoiceInfo
	 */
	public PhmInvoiceInfo updatePhmInvoiceInfo(PhmInvoiceInfo phmInvoiceInfo);
	
	/**
	 * 删除对象PhmInvoiceInfo
	 * @param id数据组
	 */
	public void deletePhmInvoiceInfo(String[] ids);

}