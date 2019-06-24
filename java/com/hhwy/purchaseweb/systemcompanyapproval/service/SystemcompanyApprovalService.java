package com.hhwy.purchaseweb.systemcompanyapproval.service;

import java.util.List;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.systemcompanyapproval.domain.SystemcompanyApprovalDetail;
import com.hhwy.purchaseweb.systemcompanyapproval.domain.SystemcompanyApprovalVo;
import com.hhwy.selling.domain.SystemcompanyApproval;

/**
 * SystemcompanyApprovalController
 * @author zhangzhao
 *  平台用户管理续费流程
 * @date  2018-12-05
 * @version 1.0
 */
public interface SystemcompanyApprovalService{
	
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
	public void saveSystemcompanyApproval(SystemcompanyApproval systemcompanyApproval);
	
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
	public QueryResult<SystemcompanyApprovalDetail> getSystemcompanyApprovalByPage(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception;

	/**
	 * 根据查询条件获取对象SystemcompanyApproval列表
	 * @param SystemcompanyApprovalVo
	 * @return List
	 */
	public List<SystemcompanyApprovalDetail> getSystemcompanyApprovalListByParams(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SystemcompanyApproval数量
	 * @param SystemcompanyApprovalVo
	 * @return Integer
	 */
	public Integer getSystemcompanyApprovalCountByParams(SystemcompanyApprovalVo systemcompanyApprovalVo);
	
	/**
	 * 根据查询条件获取单个对象SystemcompanyApproval
	 * @param SystemcompanyApprovalVo
	 * @return SystemcompanyApproval
	 */
	public SystemcompanyApprovalDetail getSystemcompanyApprovalOneByParams(SystemcompanyApprovalVo systemcompanyApprovalVo) throws Exception;
	
	/**
	 * 根据ID获取对象SystemcompanyApproval
	 * @param ID
	 * @return SystemcompanyApproval
	 */
	public SystemcompanyApproval getSystemcompanyApprovalById(String id);
	
	/**
	 * 添加对象SystemcompanyApproval列表
	 * @param 实体对象
	 * @return null
	 */
	public void saveSystemcompanyApprovalList(List<SystemcompanyApproval> systemcompanyApprovalList);
	
	/**
	 * 续期审批
	 * 更新对象SystemcompanyApproval
	 * @param 实体对象
	 * @return SystemcompanyApproval
	 */
	public void updateSystemcompanyApproval(SystemcompanyApproval systemcompanyApproval);
	
	/**
	 * 删除对象SystemcompanyApproval
	 * @param id数据组
	 */
	public void deleteSystemcompanyApproval(String[] ids);

}