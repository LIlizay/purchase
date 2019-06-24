package com.hhwy.purchaseweb.bid.phmeleclist.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhmElecList;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.ElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListDetail;
import com.hhwy.purchaseweb.bid.phmeleclist.domain.PhmElecListVo;

/**
 * IPhmElecListService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface PhmElecListService{
	
	/**
	 * 分页获取对象PhmElecList
	 * @param PhmElecListVo
	 * @return QueryResult
	 */
	public QueryResult<PhmElecListDetail> getPhmElecListByPage(PhmElecListVo phmElecListVo) throws Exception;

	/**
	 * 根据查询条件获取对象PhmElecList列表
	 * @param PhmElecListVo
	 * @return List
	 */
	public List<PhmElecListDetail> getPhmElecListListByParams(PhmElecListVo phmElecListVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象PhmElecList数量
	 * @param PhmElecListVo
	 * @return Integer
	 */
	public Integer getPhmElecListCountByParams(PhmElecListVo phmElecListVo);
	
	/**
     * 分页获取对象PhmElecListDetail
     * @param PhmElecListVo
     * @return QueryResult
     */
    public QueryResult<ElecListDetail> getElecListDetailListByPage(PhmElecListVo phmElecListVo) throws Exception;

    /**
     * 根据查询条件获取对象PhmElecListDetail列表
     * @param PhmElecListVo
     * @return List
     */
    public List<ElecListDetail> getElecListDetailListByParams(PhmElecListVo phmElecListVo) throws Exception;
    
    /**
     * 根据查询条件获取对象PhmElecListDetail数量
     * @param PhmElecListVo
     * @return Integer
     */
    public Integer getElecListDetailCountByParams(PhmElecListVo phmElecListVo);
	
	/**
	 * 根据查询条件获取单个对象PhmElecList
	 * @param PhmElecListVo
	 * @return PhmElecList
	 */
	public PhmElecListDetail getPhmElecListOneByParams(PhmElecListVo phmElecListVo) throws Exception;
	
	/**
	 * 根据ID获取对象PhmElecList
	 * @param ID
	 * @return PhmElecList
	 */
	public PhmElecList getPhmElecListById(String id);
	
	
	/**
	 * 添加对象PhmElecList
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmElecList(PhmElecList phmElecList);
	
	/**
     * 校验用户是否存在数据
     * checkList(描述这个方法的作用)<br/>
     * @param phmElecList 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void checkList(PhmElecList phmElecList);
    
	/**
	 * 添加对象PhmElecList列表
	 * @param 实体对象
	 * @return null
	 */
	public void savePhmElecListList(List<PhmElecList> phmElecListList);
	
	/**
	 * 更新对象PhmElecList
	 * @param 实体对象
	 * @return PhmElecList
	 */
	public void updatePhmElecList(PhmElecList phmElecList);
	
	/**
	 * 删除对象PhmElecList
	 * @param id数据组
	 */
	public void deletePhmElecList(String[] ids);
	
	/**
	 * 根据consid和年月删除
	 * delede(描述这个方法的作用)<br/>
	 * @param phmElecList 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	public void delete(PhmElecList phmElecList);
	
	/**
	 * 根据用户id获取表号
	 * getMeterNo(描述这个方法的作用)<br/>
	 * @param consId
	 * @return 
	 * String
	 * @exception 
	 * @since  1.0.0
	 */
	public String getMeterNo(String consId);
}