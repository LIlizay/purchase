package com.hhwy.purchaseweb.systemcompanyapproval.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hhwy.business.market.util.ConvertListUtil;
import com.hhwy.framework.persistent.DAO;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.systemcompanyapproval.domain.SystemcompanyApprovalDetail;
import com.hhwy.purchaseweb.systemcompanyapproval.domain.SystemcompanyApprovalVo;
import com.hhwy.purchaseweb.systemcompanyapproval.service.SystemcompanyApprovalService;
import com.hhwy.selling.domain.SystemcompanyApproval;

/**
 * SystemcompanyApprovalController
 * @author zhangzhao
 *  平台用户管理续费流程
 * @date  2018-12-05
 * @version 1.0
 */
@Component
public class SystemcompanyApprovalServiceImpl implements SystemcompanyApprovalService {

	public static final Logger log = LoggerFactory.getLogger(SystemcompanyApprovalServiceImpl.class);
	
	@Autowired
	DAO<?> dao;
	
	public void setDao(DAO<?> dao) {
		this.dao = dao;
	}
	
	/**
	 * @Title: saveSystemcompanyApproval<br/>
	 * @Description:TODO(平台用户管理续期，保存)<br/>
	 * @param systemcompanyApproval
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月5日 下午4:40:09
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月5日 下午4:40:09
	 * @since  1.0.0
	 */
	public void saveSystemcompanyApproval(SystemcompanyApproval systemcompanyApproval) {
		dao.save(systemcompanyApproval);
	}
	
	/**
	 * @Title: getSystemcompanyApprovalByPage<br/>
	 * @Description:TODO(用户续期审批主列表)<br/>
	 * @param systemcompanyApprovalVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月6日 上午10:36:43
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月6日 上午10:36:43
	 * @since  1.0.0
	 */
	public QueryResult<SystemcompanyApprovalDetail> getSystemcompanyApprovalByPage(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception{
		QueryResult<SystemcompanyApprovalDetail> queryResult = new QueryResult<SystemcompanyApprovalDetail>();
		long total = getSystemcompanyApprovalCountByParams(systemcompanyApprovalVo);
		List<SystemcompanyApprovalDetail> systemcompanyApprovalList = getSystemcompanyApprovalListByParams(systemcompanyApprovalVo);
		queryResult.setTotal(total);
		queryResult.setData(systemcompanyApprovalList);
		return queryResult;
	}	
	
	/**
	 * 根据查询条件获取对象SystemcompanyApproval数量
	 * @param SystemcompanyApprovalVo
	 * @return Integer
	 */
	public Integer getSystemcompanyApprovalCountByParams(SystemcompanyApprovalVo systemcompanyApprovalVo){
		Object result = dao.getOneBySQL("systemcompanyApproval.sql.getSystemcompanyApprovalCountByParams",systemcompanyApprovalVo);
		int total = result == null ? 0 : Integer.valueOf(result.toString());
		return total;
	}
	
	/**
	 * 根据查询条件获取对象SystemcompanyApproval列表
	 * @param SystemcompanyApprovalVo
	 * @return List
	 */
	@SuppressWarnings("unchecked")
	public List<SystemcompanyApprovalDetail> getSystemcompanyApprovalListByParams(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception{
		//当Vo为空时,初始化Vo对象,应用分页参数
		if(systemcompanyApprovalVo == null){
			systemcompanyApprovalVo = new SystemcompanyApprovalVo();
		}
		List<SystemcompanyApprovalDetail> systemcompanyApprovalList = (List<SystemcompanyApprovalDetail>)dao.getBySql("systemcompanyApproval.sql.getSystemcompanyApprovalListByParams",systemcompanyApprovalVo);
		ConvertListUtil.convert(systemcompanyApprovalList);
		return systemcompanyApprovalList;
	}
	/**
	 * 根据查询条件获取单个对象SystemcompanyApproval
	 * @param SystemcompanyApprovalVo
	 * @return SystemcompanyApproval
	 */
	public SystemcompanyApprovalDetail getSystemcompanyApprovalOneByParams(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception{
		SystemcompanyApprovalDetail systemcompanyApprovalDetail = null;
		List<SystemcompanyApprovalDetail> systemcompanyApprovalList = getSystemcompanyApprovalListByParams(systemcompanyApprovalVo);
		if(systemcompanyApprovalList != null && systemcompanyApprovalList.size() > 0){
			systemcompanyApprovalDetail = systemcompanyApprovalList.get(0);
		}
		return systemcompanyApprovalDetail;
	}
	
	/**
	 * 续期审批
	 * 更新对象SystemcompanyApproval
	 * @param 实体对象
	 */
	@Transactional
	public void updateSystemcompanyApproval(SystemcompanyApproval systemcompanyApproval) {
		//更新审批表中的审批数据
		dao.save(systemcompanyApproval);
		//更新系统期限表中的时间
		dao.updateBySql("systemcompanyContract.sql.updateSyatemDateSql", systemcompanyApproval);
	}
	
	/**
	 * 根据ID获取对象SystemcompanyApproval
	 * @param ID
	 * @return SystemcompanyApproval
	 */
	public SystemcompanyApproval getSystemcompanyApprovalById(String id) {
		return dao.findById(id, SystemcompanyApproval.class);
	}	
	
	/**
	 * 添加对象SystemcompanyApproval
	 * @param 实体对象
	 */
	public void saveSystemcompanyApprovalList(List<SystemcompanyApproval> systemcompanyApprovalList) {
		dao.saveList(systemcompanyApprovalList);
	}
	
	/**
	 * 删除对象SystemcompanyApproval
	 * @param id数据组
	 */
	public void deleteSystemcompanyApproval(String[] ids) {
		dao.delete(ids, SystemcompanyApproval.class);
	}
}