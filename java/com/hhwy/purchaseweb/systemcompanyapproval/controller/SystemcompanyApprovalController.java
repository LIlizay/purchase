package com.hhwy.purchaseweb.systemcompanyapproval.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
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
@RestController
@RequestMapping("/systemcompanyApprovalController")
public class SystemcompanyApprovalController {

	public static final Logger log = LoggerFactory.getLogger(SystemcompanyApprovalController.class);
	
	/**
	 * systemcompanyApprovalService
	 */
	@Autowired
	private SystemcompanyApprovalService systemcompanyApprovalService;
	
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
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSystemcompanyApproval(@RequestBody SystemcompanyApproval systemcompanyApproval) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyApprovalService.saveSystemcompanyApproval(systemcompanyApproval);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
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
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSystemcompanyApprovalByPage(@RequestBody(required=false) SystemcompanyApprovalVo systemcompanyApprovalVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SystemcompanyApprovalDetail> queryResult = systemcompanyApprovalService.getSystemcompanyApprovalByPage(systemcompanyApprovalVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setRows(new Object[]{});						//设置返回结果为空
			result.setTotal(0);							//设置数据总条数为0
			result.setMsg("分页查询列表失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 根据ID获取对象SystemcompanyApproval
	 * @param ID
	 * @return SystemcompanyApproval
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSystemcompanyApprovalById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SystemcompanyApproval systemcompanyApproval = systemcompanyApprovalService.getSystemcompanyApprovalById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(systemcompanyApproval);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(null);						//设置返回结果为空
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
	
	/**
	 * 续期审批
	 * 更新对象SystemcompanyApproval
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSystemcompanyApproval(@RequestBody SystemcompanyApproval systemcompanyApproval) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyApprovalService.updateSystemcompanyApproval(systemcompanyApproval);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象SystemcompanyApproval
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSystemcompanyApproval(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			systemcompanyApprovalService.deleteSystemcompanyApproval(new String[]{id});
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
}