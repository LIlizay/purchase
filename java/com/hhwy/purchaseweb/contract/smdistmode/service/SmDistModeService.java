package com.hhwy.purchaseweb.contract.smdistmode.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smdistmode.domain.SmDistModeVo;
import com.hhwy.selling.domain.SmDistMode;

/**
 * ISmDistModeService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmDistModeService{
	
	/**
	 * 分页获取对象SmDistMode
	 * @param SmDistModeVo
	 * @return QueryResult
	 */
	public QueryResult<SmDistMode> getSmDistModeByPage(SmDistModeVo smDistModeVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmDistMode列表
	 * @param SmDistModeVo
	 * @return List
	 */
	public List<SmDistMode> getSmDistModeListByParams(SmDistModeVo smDistModeVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmDistMode数量
	 * @param SmDistModeVo
	 * @return Integer
	 */
	public Integer getSmDistModeCountByParams(SmDistModeVo smDistModeVo);
	
	/**
	 * 根据查询条件获取单个对象SmDistMode
	 * @param SmDistModeVo
	 * @return SmDistMode
	 */
	public SmDistMode getSmDistModeOneByParams(SmDistModeVo smDistModeVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmDistMode
	 * @param ID
	 * @return SmDistMode
	 */
	public SmDistMode getSmDistModeById(String id);
	
	/**
	 * 添加对象SmDistMode
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmDistMode(SmDistMode smDistMode);
	
	/**
	 * 添加对象SmDistMode列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmDistModeList(List<SmDistMode> smDistModeList);
	
	/**
	 * 更新对象SmDistMode
	 * @param 实体对象
	 * @return SmDistMode
	 */
	public void updateSmDistMode(SmDistMode smDistMode);
	
	/**
	 * 删除对象SmDistMode
	 * @param id数据组
	 */
	public void deleteSmDistMode(String[] ids);
	
	/**
	 * 删除对象SmAgreServ
	 * @param id数据组
	 */
	public void deleteSmDistModeByAgreIds(String[] agreId);

	/**
	 * @Title: getSmDistModeOneByConsIdAndYm
	 * @Description: 根据用户id和年月获取其售电合同 分配方式信息,山西辽宁成交信息录入时计算结算电价时用到
	 * @param consId
	 * @param ym
	 * @return 
	 * SmDistMode
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年6月29日 下午5:03:58
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年6月29日 下午5:03:58
	 * @since  1.0.0
	 */
	public SmDistMode getSmDistModeOneByConsIdAndYm(String consId, String ym);
}