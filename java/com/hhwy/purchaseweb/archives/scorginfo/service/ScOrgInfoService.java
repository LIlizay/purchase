package com.hhwy.purchaseweb.archives.scorginfo.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoVo;
import com.hhwy.selling.domain.ScOrgInfo;

 /**
 * <b>类 名 称：</b>ScOrgInfoService<br/>
 * <b>类 描 述：</b><br/>		供电公司信息的service
 * <b>创 建 人：</b>wangzelu<br/>
 * <b>修 改 人：</b>wangzelu<br/>
 * <b>修改时间：</b>2016年11月22日 下午4:00:31<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 */
public interface ScOrgInfoService{
	
	/**
	 * 分页获取对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param scOrgInfoVo		查询条件
	 * @return QueryResult<ScOrgInfoDetail>
	 */
	public QueryResult<ScOrgInfoDetail> getScOrgInfoByPage(ScOrgInfoVo scOrgInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScOrgInfo(供电公司信息)数量
	 * @param ScOrgInfoVo		查询条件
	 * @return Integer
	 */
	public Integer getScOrgInfoCountByParams(ScOrgInfoVo scOrgInfoVo);
	
	/**
	 * 根据查询条件获取对象ScOrgInfoDetail(供电公司信息detail类)列表
	 * @param ScOrgInfoVo		查询条件
	 * @return List
	 */
	public List<ScOrgInfoDetail> getOrgInfoDetailListByParams(ScOrgInfoVo scOrgInfoVo) throws Exception;
	/**
	 * 根据查询条件获取单个对象ScOrgInfoDetail(供电公司信息detail类)
	 * @param ScOrgInfoVo		查询条件
	 * @return ScOrgInfoDetail
	 */
	public ScOrgInfoDetail getScOrgInfoOneByParams(ScOrgInfoVo scOrgInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScOrgInfo(供电公司信息)
	 * @param ID
	 * @return ScOrgInfo
	 */
	public ScOrgInfo getScOrgInfoById(String id);
	
	/**
	 * 添加对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void saveScOrgInfo(ScOrgInfo scOrgInfo);
	
	/**
	 * 添加对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void saveScOrgInfoList(List<ScOrgInfo> scOrgInfoList);
	
	/**
	 * 更新对象ScOrgInfo(供电公司信息)
	 * @param 实体对象
	 */
	public void updateScOrgInfo(ScOrgInfo scOrgInfo);
	
	/**
	 * 删除对象ScOrgInfo(供电公司信息)
	 * @param id数据组
	 */
	public void deleteScOrgInfo(String[] ids);
	/**
	 * 供电单位信息下拉列表查询
	 * @return List<ScOrgInfo>
	 */
	public List<ScOrgInfo> getSelectOrgList() throws Exception;

	/**
	  * @Title: checkScOrgInfo
	  * @Description: 供电公司重复性校验
	  * @param scOrgInfo
	  * void
	  * <b>创 建 人：</b>LiXinze<br/>
	  * <b>创建时间:</b>2017年11月9日 下午3:24:46
	  * <b>修 改 人：</b>LiXinze<br/>
	  * <b>修改时间:</b>2017年11月9日 下午3:24:46
	  * @since  1.0.0
	 */
	public void checkScOrgInfo(ScOrgInfo scOrgInfo);
	
	/**
	 * @Title: getOrgInfoListByNames
	 * @Description: 根据供电公司名称获取供电公司id，     用户信息导入时用到
	 * @param orgNames
	 * @return 
	 * List<ScOrgInfoDetail>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午2:16:07
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午2:16:07
	 * @since  1.0.0
	 */
	public List<ScOrgInfoDetail> getOrgInfoListByNames(List<String> orgNames);
}