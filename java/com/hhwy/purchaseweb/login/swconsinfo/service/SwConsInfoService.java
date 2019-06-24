package com.hhwy.purchaseweb.login.swconsinfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaDetail;
import com.hhwy.purchaseweb.login.swconsinfo.domain.RelaVo;
import com.hhwy.purchaseweb.login.swconsinfo.domain.SwConsInfoVo;
import com.hhwy.selling.domain.SwConsInfo;

/**
 * ISwConsInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwConsInfoService{
	
	/**
	 * 分页获取对象SwConsInfo
	 * @param SwConsInfoVo
	 * @return QueryResult
	 */
	public QueryResult<SwConsInfo> getSwConsInfoByPage(SwConsInfoVo swConsInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象SwConsInfo列表
	 * @param SwConsInfoVo
	 * @return List
	 */
	public List<SwConsInfo> getSwConsInfoListByParams(SwConsInfoVo swConsInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SwConsInfo数量
	 * @param SwConsInfoVo
	 * @return Integer
	 */
	public Integer getSwConsInfoCountByParams(SwConsInfoVo swConsInfoVo);
	
	/**
     * 分页获取对象RelaDetail
     * @param relaVo
     * @return QueryResult
     */
    public QueryResult<RelaDetail> getRelaDetailByPage(RelaVo relaVo) throws Exception;

    /**
     * 根据查询条件获取对象RelaDetail列表
     * @param relaVo
     * @return List
     */
    public List<RelaDetail> getRelaDetailListByParams(RelaVo relaVo) throws Exception;
    
    /**
     * 根据查询条件获取对象RelaDetail数量
     * @param relaVo
     * @return Integer
     */
    public Integer getRelaDetailCountByParams(RelaVo relaVo);
    
	/**
	 * 根据查询条件获取单个对象SwConsInfo
	 * @param SwConsInfoVo
	 * @return SwConsInfo
	 */
	public SwConsInfo getSwConsInfoOneByParams(SwConsInfoVo swConsInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象SwConsInfo
	 * @param ID
	 * @return SwConsInfo
	 */
	public SwConsInfo getSwConsInfoById(String id);
	
	/**
     * 绑定账号
     * binding(描述这个方法的作用)<br/>
     * @param swConsInfo 
     * void
     * @exception 
     * @since  1.0.0
     */
    public void binding(SwConsInfo swConsInfo);
    
	/**
	 * 添加对象SwConsInfo
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwConsInfo(SwConsInfo swConsInfo);
	
	/**
	 * 添加对象SwConsInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwConsInfoList(List<SwConsInfo> swConsInfoList);
	
	/**
	 * 更新对象SwConsInfo
	 * @param 实体对象
	 * @return SwConsInfo
	 */
	public void updateSwConsInfo(SwConsInfo swConsInfo);
	
	/**
	 * 删除对象SwConsInfo
	 * @param id数据组
	 */
	public void deleteSwConsInfo(String[] ids);
	
	/**
	 * 
	 * remindMessage(提醒用户信息)<br/>
	 * @param relaDetailList
	 * @return
	 * @throws Exception 
	 * boolean
	 * @exception 
	 * @since  1.0.0
	 */
	public void remindMessage(List<RelaDetail> relaDetailList) throws Exception;
}