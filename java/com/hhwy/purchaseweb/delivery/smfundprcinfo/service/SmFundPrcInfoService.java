package com.hhwy.purchaseweb.delivery.smfundprcinfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoDetail;
import com.hhwy.purchaseweb.delivery.smfundprcinfo.domain.SmFundPrcInfoVo;
import com.hhwy.selling.domain.SmFundPrcInfo;

/**
 * ISmFundPrcInfoService
 * 
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmFundPrcInfoService {

	/**
	 * 分页获取对象SmFundPrcInfo
	 * 
	 * @param SmFundPrcInfoVo
	 * @return QueryResult
	 */
	public QueryResult<SmFundPrcInfoDetail> getSmFundPrcInfoByPage(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmFundPrcInfo列表
	 * 
	 * @param SmFundPrcInfoVo
	 * @return List
	 */
	public List<SmFundPrcInfoDetail> getSmFundPrcInfoListByParams(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmFundPrcInfo数量
	 * 
	 * @param SmFundPrcInfoVo
	 * @return Integer
	 */
	public Integer getSmFundPrcInfoCountByParams(SmFundPrcInfoVo smFundPrcInfoVo);

	/**
	 * 根据查询条件获取单个对象SmFundPrcInfo
	 * 
	 * @param SmFundPrcInfoVo
	 * @return SmFundPrcInfo
	 */
	public SmFundPrcInfoDetail getSmFundPrcInfoOneByParams(
			SmFundPrcInfoVo smFundPrcInfoVo) throws Exception;

	/**
	 * 根据ID获取对象SmFundPrcInfo
	 * 
	 * @param ID
	 * @return SmFundPrcInfo
	 */
	public SmFundPrcInfo getSmFundPrcInfoById(String id);

	/**
	 * 添加对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmFundPrcInfo(SmFundPrcInfo smFundPrcInfo);

	/**
	 * 添加对象SmFundPrcInfo列表
	 * 
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmFundPrcInfoList(List<SmFundPrcInfo> smFundPrcInfoList);

	/**
	 * 更新对象SmFundPrcInfo
	 * 
	 * @param 实体对象
	 * @return SmFundPrcInfo
	 */
	public void updateSmFundPrcInfo(SmFundPrcInfo smFundPrcInfo);

	/**
	 * 删除对象SmFundPrcInfo
	 * 
	 * @param id数据组
	 */
	public void deleteSmFundPrcInfo(String[] ids);
	
	/**
	 * @Title: deleteSmFundPrcInfoByPrcIds
	 * @Description: 根据电价信息（SmPrcInfo）id数组删除对象SmFundPrcInfo
	 * @param prcIds 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年2月27日 下午3:10:10
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年2月27日 下午3:10:10
	 * @since  1.0.0
	 */
	public void deleteSmFundPrcInfoByPrcIds(String[] prcIds);
}