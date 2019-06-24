package com.hhwy.purchaseweb.archives.scmpinfo.service;

import java.util.List;
import java.util.Map;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.archives.scmpinfo.domain.ScMpInfoVo;
import com.hhwy.purchaseweb.archives.scorginfo.domain.ScOrgInfoDetail;
import com.hhwy.selling.domain.ScMpInfo;


/**
 * IScMpInfoService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface ScMpInfoService{
	
	/**
	 * 分页获取对象ScMpInfo
	 * @param ScMpInfoVo
	 * @return QueryResult
	 */
	public QueryResult<ScMpInfo> getScMpInfoByPage(ScMpInfoVo scMpInfoVo) throws Exception;

	/**
	 * 根据查询条件获取对象ScMpInfo列表
	 * @param ScMpInfoVo
	 * @return List
	 */
	public List<ScMpInfo> getScMpInfoListByParams(ScMpInfoVo scMpInfoVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象ScMpInfo数量
	 * @param ScMpInfoVo
	 * @return Integer
	 */
	public Integer getScMpInfoCountByParams(ScMpInfoVo scMpInfoVo);
	
	/**
	 * 根据查询条件获取单个对象ScMpInfo
	 * @param ScMpInfoVo
	 * @return ScMpInfo
	 */
	public ScMpInfo getScMpInfoOneByParams(ScMpInfoVo scMpInfoVo) throws Exception;
	
	/**
	 * 根据ID获取对象ScMpInfo
	 * @param ID
	 * @return ScMpInfo
	 */
	public ScMpInfo getScMpInfoById(String id);
	
	/**
	 * 添加对象ScMpInfo
	 * @param 实体对象
	 * @return null
	 */
	public void saveScMpInfo(ScMpInfo scMpInfo);
	
	/**
	 * 添加对象ScMpInfo列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveScMpInfoList(List<ScMpInfo> scMpInfoList);
	
	/**
	 * 更新对象ScMpInfo
	 * @param 实体对象
	 * @return ScMpInfo
	 */
	public void updateScMpInfo(ScMpInfo scMpInfo);
	
	/**
	 * 更新对象ScMpInfo集合
	 * @param 实体对象
	 * @return ScMpInfo
	 */
	public void updateScMpInfoList(List<ScMpInfo> scMpInfoList);
	
	/**
	 * @Title: deleteSCMpInfo
	 * @Description: 判断是否有表记示数，根据计量点id删除计量点信息
	 * @param id 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午6:48:34
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午6:48:34
	 * @since  1.0.0
	 */
	public void validateAndDeleteSCMpInfo(String id);
	
	/**
	 * 删除对象ScMpInfo
	 * @param id数据组
	 */
	public void deleteScMpInfo(String[] ids);
	
	/**
	 * 根据用电用户id删除对象ScMpInfo
	 * @param id数据组
	 */
	public void deleteScMpInfoByConsId(String[] consIds);
	
	/**
	 * @Title: getMpInfoListByMeterNoAndConsId
	 * @Description: 根据电能表编号和consId获取列表，     用户信息导入时验证一个电能表编号在系统中已经维护到另外一个用户下时用到
	 * @param mpQueryParams
	 * @return 
	 * List<ScMpInfo>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月20日 下午6:00:40
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月20日 下午6:00:40
	 * @since  1.0.0
	 */
	public List<ScMpInfo> getMpInfoListByMeterNoAndConsId(List<Map<String, String>> mpQueryParams);
	
	/**
	 * @Title: getMpInfoListByMeterNos
	 * @Description: 根据电能表编号列表获取计量点列表
	 * @param meterNos
	 * @return 
	 * List<ScMpInfo>
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年4月21日 下午1:22:15
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年4月21日 下午1:22:15
	 * @since  1.0.0
	 */
	public List<ScMpInfo> getMpInfoListByMeterNos(List<String> meterNos);
}