package com.hhwy.purchaseweb.contract.smagremp.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpInfoDetail;
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpVo;
import com.hhwy.selling.domain.SmAgreMp;
/**
 * ISmAgreMpService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SmAgreMpService{

	/**
	 * 根据查询条件获取对象ScMpInfo列表
	 * @param ScMpInfoVo
	 * @return List<ScMpInfoDetail>
	 */
	public List<SmAgreMpInfoDetail> getScMpInfoList(Map<String,String> map) throws Exception;
	
	/**
	 * 分页获取对象SmAgreMp
	 * @param SmAgreMpVo
	 * @return QueryResult
	 */
	public QueryResult<SmAgreMp> getSmAgreMpByPage(SmAgreMpVo smAgreMpVo) throws Exception;

	/**
	 * 根据查询条件获取对象SmAgreMp列表
	 * @param SmAgreMpVo
	 * @return List
	 */
	public List<SmAgreMp> getSmAgreMpListByParams(SmAgreMpVo smAgreMpVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SmAgreMp数量
	 * @param SmAgreMpVo
	 * @return Integer
	 */
	public Integer getSmAgreMpCountByParams(SmAgreMpVo smAgreMpVo);
	
	/**
	 * 根据查询条件获取单个对象SmAgreMp
	 * @param SmAgreMpVo
	 * @return SmAgreMp
	 */
	public SmAgreMp getSmAgreMpOneByParams(SmAgreMpVo smAgreMpVo) throws Exception;
	
	/**
	 * 根据ID获取对象SmAgreMp
	 * @param ID
	 * @return SmAgreMp
	 */
	public SmAgreMp getSmAgreMpById(String id);
	
	/**
	 * 添加对象SmAgreMp
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgreMp(SmAgreMp smAgreMp);
	
	/**
	 * 添加对象SmAgreMp列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSmAgreMpList(List<SmAgreMp> smAgreMpList);
	
	/**
	 * 更新对象SmAgreMp
	 * @param 实体对象
	 * @return SmAgreMp
	 */
	public void updateSmAgreMp(SmAgreMp smAgreMp);
	
	/**
	 * 删除对象SmAgreMp
	 * @param id数据组
	 */
	public void deleteSmAgreMp(String[] ids);
	
	/**
	 * 删除对象SmAgreServ
	 * @param id数据组
	 */
	public void deleteSmAgreMpByAgreIds(String[] agreId);

}