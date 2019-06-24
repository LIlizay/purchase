package com.hhwy.purchaseweb.sqllogfilter.service.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hhwy.business.core.sqlfilter.CompanyDomainInfoUtil;
import com.hhwy.business.system.domain.UserInfo;
import com.hhwy.business.system.util.SystemServiceUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogDetail;
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogVo;
import com.hhwy.purchaseweb.sqllogfilter.service.SwDbLogService;
import com.hhwy.selling.domain.SwDbLog;
import com.hhwy.sso.client.filter.SessionUtil;

/**
 * SwDbLogService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@Component
public class SwDbLogServiceImpl implements SwDbLogService {

	public static final Logger log = LoggerFactory.getLogger(SwDbLogServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * 分页获取对象SwDbLog
	 * @param ID
	 * @return SwDbLog
	 */
	public QueryResult<SwDbLogDetail> getSwDbLogByPage(SwDbLogVo swDbLogVo) throws Exception{
		QueryResult<SwDbLogDetail> queryResult = new QueryResult<SwDbLogDetail>();
		long total = getSwDbLogCountByParams(swDbLogVo);
		List<SwDbLogDetail> swDbLogList = getSwDbLogListByParams(swDbLogVo);
		queryResult.setTotal(total);
		queryResult.setData(swDbLogList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SwDbLog数量
	 * @param SwDbLogVo
	 * @return Integer
	 */
	public Integer getSwDbLogCountByParams(SwDbLogVo swDbLogVo){
		Object result = dao.getOneBySQL("swDbLog.sql.getSwDbLogCountByParams",swDbLogVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SwDbLog列表
	 * @param SwDbLogVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SwDbLogDetail> getSwDbLogListByParams(SwDbLogVo swDbLogVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(swDbLogVo == null){
			swDbLogVo = new SwDbLogVo();
		}
		List<SwDbLogDetail> swDbLogList = (List<SwDbLogDetail>)dao.getBySql("swDbLog.sql.getSwDbLogListByParams",swDbLogVo);
		//ConvertListUtil.convert(swDbLogList);
		return swDbLogList;
	}
	/**
	 * 根据查询条件获取单个对象SwDbLog
	 * @param SwDbLogVo
	 * @return SwDbLog
	 */
	public SwDbLogDetail getSwDbLogOneByParams(SwDbLogVo swDbLogVo) throws Exception{
		SwDbLogDetail swDbLog = null;
		List<SwDbLogDetail> swDbLogList = getSwDbLogListByParams(swDbLogVo);
		if(swDbLogList != null && swDbLogList.size() > 0){
			swDbLog = swDbLogList.get(0);
		}
		return swDbLog;
	}
	
	/**
	 * 根据ID获取对象SwDbLog
	 * @param ID
	 * @return SwDbLog
	 */
	public SwDbLog getSwDbLogById(String id) {
		return dao.findById(id, SwDbLog.class);
	}	
	
	/**
	 * 添加对象SwDbLog
	 * @param 实体对象
	 */
	public void saveSwDbLog(SwDbLog swDbLog) {
		dao.save(swDbLog);
	}
	
	/**
	 * 添加对象SwDbLog
	 * @param 实体对象
	 */
	public void saveSwDbLogList(List<SwDbLog> swDbLogList) {
		dao.saveList(swDbLogList);
	}
	
	/**
	 * 更新对象SwDbLog
	 * @param 实体对象
	 */
	public void updateSwDbLog(SwDbLog swDbLog) {
		dao.update(swDbLog);
	}
	
	/**
	 * 删除对象SwDbLog
	 * @param id数据组
	 */
	public void deleteSwDbLog(String[] ids) {
		dao.delete(ids, SwDbLog.class);
	}	
	
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
	public void recordLoginLog(JoinPoint joinPoint, Object result) {
		//登录成功
		if(result!=null && "0001".equals(result.toString())) {
			SwDbLog log = new SwDbLog();
			UserInfo user = SystemServiceUtil.getLoginUserInfo();
			if(user != null) {
				log.setOperator(user.getUserName());
			}
			log.setOperateTime(new Date());
			log.setType("登录");
			log.setSql("sessionId:" + SessionUtil.getSession().getId()); 
			try {
				ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
				HttpServletRequest request = attr.getRequest();
				String remoteAddr = request.getRemoteAddr();
		        String forwarded = request.getHeader("X-Forwarded-For");
		        String realIp = request.getHeader("X-Real-IP");
		        log.setParams("IP:"+remoteAddr+",forwarded:"+forwarded+",realIp:"+realIp);
			} catch (Exception e) {
			}
			String currentdb = null;
	        try {
	            currentdb = CompanyDomainInfoUtil.getInstance().currentDatabase();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
			log.setCompanyDomain(currentdb);
			dao.save(log);
		}
	}
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
	public void recordLogoutLog(JoinPoint joinPoint) {
		SwDbLog log = new SwDbLog();
		UserInfo user = SystemServiceUtil.getLoginUserInfo();
		if(user != null) {
			log.setOperator(user.getUserName());
		}
		log.setOperateTime(new Date());
		log.setType("退出");
		log.setSql("sessionId:" + SessionUtil.getSession().getId()); 
		try {
			ServletRequestAttributes attr = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = attr.getRequest();
			String remoteAddr = request.getRemoteAddr();
	        String forwarded = request.getHeader("X-Forwarded-For");
	        String realIp = request.getHeader("X-Real-IP");
	        log.setParams("IP:"+remoteAddr+",forwarded:"+forwarded+",realIp:"+realIp);
		} catch (Exception e) {
		}
		String currentdb = null;
		try {
			currentdb = CompanyDomainInfoUtil.getInstance().currentDatabase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.setCompanyDomain(currentdb);
		dao.save(log);
	}
}