package com.hhwy.purchaseweb.systemcompanycontract.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractDetail;
import com.hhwy.purchaseweb.systemcompanycontract.domain.SystemcompanyContractVo;
import com.hhwy.selling.domain.SystemcompanyContract;

/**
 * ISystemcompanyContractService
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
public interface SystemcompanyContractService{
	
	/**
	 * @Title: getSystemcompanyContractByPage<br/>
	 * @Description:TODO(平台用户管理主列表)<br/>
	 * @param systemcompanyContractVo
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年11月30日 下午5:24:51
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年11月30日 下午5:24:51
	 * @since  1.0.0
	 */
	public QueryResult<SystemcompanyContractDetail> getSystemcompanyContractByPage(SystemcompanyContractVo systemcompanyContractVo) throws Exception;

	/**
	 * @Title: saveSystemcompanyContractList<br/>
	 * @Description:TODO(平台用户管理编辑页面保存)<br/>
	 * @param systemcompanyContractList
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月1日 下午5:13:35
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月1日 下午5:13:35
	 * @since  1.0.0
	 */
	public void saveSystemcompanyContractList(SystemcompanyContractVo systemcompanyContractVo);
	
	/**
	 * @Title: exportExcel<br/>
	 * @Description:TODO(平台用户明细导出，需求暂定导出全部数据)<br/>
	 * @param request
	 * @param response
	 * @return
	 * Object
	 * <b>创 建 人：</b>zhagnzhao<br/>
	 * <b>创建时间:</b>2018年12月5日 上午11:10:19
	 * <b>修 改 人：</b>zhagnzhao<br/>
	 * <b>修改时间:</b>2018年12月5日 上午11:10:19
	 * @throws Exception 
	 * @since  1.0.0
	 */
	public void exportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception;
	
	/**
	 * 根据查询条件获取对象SystemcompanyContract列表
	 * @param SystemcompanyContractVo
	 * @return List
	 */
	public List<SystemcompanyContractDetail> getSystemcompanyContractListByParams(SystemcompanyContractVo systemcompanyContractVo) throws Exception;
	
	/**
	 * 根据查询条件获取对象SystemcompanyContract数量
	 * @param SystemcompanyContractVo
	 * @return Integer
	 */
	public Integer getSystemcompanyContractCountByParams(SystemcompanyContractVo systemcompanyContractVo);
	
	/**
	 * 根据查询条件获取单个对象SystemcompanyContract
	 * @param SystemcompanyContractVo
	 * @return SystemcompanyContract
	 */
	public SystemcompanyContractDetail getSystemcompanyContractOneByParams(SystemcompanyContractVo systemcompanyContractVo) throws Exception;
	
	/**
	 * 根据ID获取对象SystemcompanyContract
	 * @param ID
	 * @return SystemcompanyContract
	 */
	public SystemcompanyContract getSystemcompanyContractById(String id);
	
	/**
	 * 添加对象SystemcompanyContract
	 * @param 实体对象
	 * @return null
	 */
	public void saveSystemcompanyContract(SystemcompanyContract systemcompanyContract);
	
	/**
	 * 更新对象SystemcompanyContract
	 * @param 实体对象
	 * @return SystemcompanyContract
	 */
	public void updateSystemcompanyContract(SystemcompanyContract systemcompanyContract);
	
	/**
	 * 删除对象SystemcompanyContract
	 * @param id数据组
	 */
	public void deleteSystemcompanyContract(String[] ids);

}