package com.hhwy.purchaseweb.agreement.phmppa.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmPpa;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaDetail;
import com.hhwy.purchaseweb.agreement.phmppa.domain.PhmPpaVo;

/**
 * IPhmPpaService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmPpaService{
	
	/**
	 * 分页获取对象PhmPpa
	 * @param PhmPpaVo
	 * @return QueryResult
	 */
	public QueryResult<PhmPpaDetail> getPhmPpaByPage(PhmPpaVo phmPpaVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmPpa列表
	 * @param PhmPpaVo
	 * @return List
	 */
	public List<PhmPpaDetail> getPhmPpaListByParams(PhmPpaVo phmPpaVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmPpa数量
	 * @param PhmPpaVo
	 * @return Integer
	 */
	public Integer getPhmPpaCountByParams(PhmPpaVo phmPpaVo);
	
	/**
	 * 根据查询条件获取单个对象PhmPpa
	 * @param PhmPpaVo
	 * @return PhmPpa
	 */
	public PhmPpaDetail getPhmPpaOneByParams(PhmPpaVo phmPpaVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmPpa
	 * @param ID
	 * @return PhmPpa
	 */
	public PhmPpaDetail getPhmPpaById(String id) throws Exception;
	
	/**
	 * 根据购电合同id获取合同机组电量分月信息
	 * getImplementation(描述这个方法的作用)<br/>
	 * @param agreId
	 * @return 
	 * List<Map<String,Object>>
	 * @exception 
	 * @since  1.0.0
	 */
	public List<PhmGeneratorMonthPqDetail> getImplementation(String agreId);
	
	/**
	 * 添加对象PhmPpa
	 * @param 实体对象
	 * @return null
	 */
	public PhmPpaVo savePhmPpa(PhmPpaVo phmPpaVo);
	
	/**
	 * 添加对象PhmPpa列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmPpaList(List<PhmPpa> phmPpaList);
	
	/**
	 * 更新对象PhmPpa
	 * @param 实体对象
	 * @return PhmPpa
	 */
	public void updatePhmPpa(PhmPpaVo phmPpaVo);
	
	/**
	 * 删除对象PhmPpa
	 * @param id数据组
	 */
	public void deletePhmPpa(Map<String, Object> params) throws Exception;
	
	/**
	 * 
	 * @Title: getPhcElecInfoList
	 * @Description:(查询电厂信息)
	 * @return
	 * @throws Exception 
	 * List<PhmPpaDetail>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月7日 下午5:14:03
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月7日 下午5:14:03
	 * @since  1.0.0
	 */
	public List<PhmPpaDetail> getPhcElecInfoList() throws Exception;
	
	/**
	 * 
	 * @Title: getElecCountByParams
	 * @Description:(获取电厂合同数量)
	 * @param phmPpaVo
	 * @return
	 * @throws Exception 
	 * int
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月7日 下午8:18:53
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月7日 下午8:18:53
	 * @since  1.0.0
	 */
	public Integer getElecCountByParams(PhmPpaVo phmPpaVo) throws Exception;
	
	/**
	 * 
	 * @Title: getYearList
	 * @Description:(获取查询参数年)
	 * @return
	 * @throws Exception 
	 * List<Map<String,String>>
	 * <b>创 建 人：</b>zhouqi<br/>
	 * <b>创建时间:</b>2017年8月8日 上午9:18:02
	 * <b>修 改 人：</b>zhouqi<br/>
	 * <b>修改时间:</b>2017年8月8日 上午9:18:02
	 * @since  1.0.0
	 */
	public List<Map<String,String>> getYearList() throws Exception;
}