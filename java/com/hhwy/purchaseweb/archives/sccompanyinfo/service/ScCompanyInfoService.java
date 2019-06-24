package com.hhwy.purchaseweb.archives.sccompanyinfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.sccompanyinfo.domain.ScCompanyDetail;
import com.hhwy.purchaseweb.archives.sccompanyinfo.domain.ScCompanyInfoVo;
import com.hhwy.selling.domain.ScCompanyInfo;

/**
 * IScCompanyInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScCompanyInfoService{
	
	/**
	 * 分页获取对象ScCompanyInfo
	 * @param ScCompanyInfoVo
	 * @return QueryResult
	 */
	public QueryResult<ScCompanyDetail> getScCompanyInfoByPage(ScCompanyInfoVo scCompanyInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象ScCompanyInfo列表
	 * @param ScCompanyInfoVo
	 * @return List
	 */
	public List<ScCompanyDetail> getScCompanyInfoListByParams(ScCompanyInfoVo scCompanyInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScCompanyInfo数量
	 * @param ScCompanyInfoVo
	 * @return Integer
	 */
	public Integer getScCompanyInfoCountByParams(ScCompanyInfoVo scCompanyInfoVo);
	
	/**
	 * 根据查询条件获取单个对象ScCompanyInfo
	 * @param ScCompanyInfoVo
	 * @return ScCompanyInfo
	 */
	public ScCompanyDetail getScCompanyInfoOneByParams(ScCompanyInfoVo scCompanyInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScCompanyInfo
	 * @param ID
	 * @return ScCompanyInfo
	 */
	public ScCompanyInfo getScCompanyInfoById(String id);
	
	/**
	 * 添加对象ScCompanyInfo
	 * @param 实体对象
	 * @return null
	 */
	public void saveScCompanyInfo(ScCompanyInfo scCompanyInfo);
	
	/**
	 * 添加对象ScCompanyInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveScCompanyInfoList(List<ScCompanyInfo> scCompanyInfoList);
	
	/**
	 * 更新对象ScCompanyInfo
	 * @param 实体对象
	 * @return ScCompanyInfo
	 */
	public void updateScCompanyInfo(ScCompanyInfo scCompanyInfo);
	
	/**
	 * 删除对象ScCompanyInfo
	 * @param id数据组
	 */
	public void deleteScCompanyInfo(String[] ids);

}