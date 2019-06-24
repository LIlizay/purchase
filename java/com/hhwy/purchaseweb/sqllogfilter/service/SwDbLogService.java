package com.hhwy.purchaseweb.sqllogfilter.service;

import java.util.List;

import org.aspectj.lang.JoinPoint;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogDetail;
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogVo;
import com.hhwy.selling.domain.SwDbLog;

/**
 * ISwDbLogService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SwDbLogService{
	
	/**
	 * 分页获取对象SwDbLog
	 * @param SwDbLogVo
	 * @return QueryResult
	 */
	public QueryResult<SwDbLogDetail> getSwDbLogByPage(SwDbLogVo swDbLogVo) throws Exception;

	/**
	 * 根据查询条件获取对象SwDbLog列表
	 * @param SwDbLogVo
	 * @return List
	 */
	public List<SwDbLogDetail> getSwDbLogListByParams(SwDbLogVo swDbLogVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SwDbLog数量
	 * @param SwDbLogVo
	 * @return Integer
	 */
	public Integer getSwDbLogCountByParams(SwDbLogVo swDbLogVo);
	
	/**
	 * 根据查询条件获取单个对象SwDbLog
	 * @param SwDbLogVo
	 * @return SwDbLog
	 */
	public SwDbLogDetail getSwDbLogOneByParams(SwDbLogVo swDbLogVo) throws Exception;
	
	/**
	 * 根据ID获取对象SwDbLog
	 * @param ID
	 * @return SwDbLog
	 */
	public SwDbLog getSwDbLogById(String id);
	
	/**
	 * 添加对象SwDbLog
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwDbLog(SwDbLog swDbLog);
	
	/**
	 * 添加对象SwDbLog列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSwDbLogList(List<SwDbLog> swDbLogList);
	
	/**
	 * 更新对象SwDbLog
	 * @param 实体对象
	 * @return SwDbLog
	 */
	public void updateSwDbLog(SwDbLog swDbLog);
	
	/**
	 * 删除对象SwDbLog
	 * @param id数据组
	 */
	public void deleteSwDbLog(String[] ids);
	
	/**
	 * @Title: recordLogoutLog
	 * @Description: spring的aop通知，用户登录成功后记录日志
	 * @param joinPoint 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月10日 下午7:15:42
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月10日 下午7:15:42
	 * @since  1.0.0
	 */
	public void recordLoginLog(JoinPoint joinPoint, Object result);

	/**
	 * @Title: recordLogoutLog
	 * @Description: spring的aop通知，用户点击退出后记录日志
	 * @param joinPoint 
	 * void
	 * <b>创 建 人：</b>wangzelu<br/>
	 * <b>创建时间:</b>2018年8月10日 下午7:15:42
	 * <b>修 改 人：</b>wangzelu<br/>
	 * <b>修改时间:</b>2018年8月10日 下午7:15:42
	 * @since  1.0.0
	 */
	public void recordLogoutLog(JoinPoint joinPoint);
}