package com.hhwy.purchaseweb.sqllogfilter.controller;

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
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogDetail;
import com.hhwy.purchaseweb.sqllogfilter.domain.SwDbLogVo;
import com.hhwy.purchaseweb.sqllogfilter.service.SwDbLogService;
import com.hhwy.selling.domain.SwDbLog;

/**
 * SwDbLogController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/swDbLogController")
public class SwDbLogController {

	public static final Logger log = LoggerFactory.getLogger(SwDbLogController.class);
	
	/**
	 * swDbLogService
	 */
	@Autowired
	private SwDbLogService swDbLogService;
	
	
	/**
	 * 分页获取对象SwDbLog
	 * @param ID
	 * @return SwDbLog
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSwDbLogByPage(@RequestBody(required=false) SwDbLogVo swDbLogVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SwDbLogDetail> queryResult = swDbLogService.getSwDbLogByPage(swDbLogVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setTotal(queryResult.getTotal());	//设置数据总条数
			result.setRows(queryResult.getRows() == null ? queryResult.getData() : queryResult.getRows());		//设置数据列表
			result.setMsg("分页查询列表成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 根据ID获取对象SwDbLog
	 * @param ID
	 * @return SwDbLog
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSwDbLogById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SwDbLog swDbLog = swDbLogService.getSwDbLogById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(swDbLog);			//设置数据实体
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
	 * 添加对象SwDbLog
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSwDbLog(@RequestBody SwDbLog swDbLog) {
		ExecutionResult result = new ExecutionResult();
		try {
			swDbLogService.saveSwDbLog(swDbLog);
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
	 * 更新对象SwDbLog
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSwDbLog(@RequestBody SwDbLog swDbLog) {
		ExecutionResult result = new ExecutionResult();
		try {
			swDbLogService.updateSwDbLog(swDbLog);
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
	 * 删除对象SwDbLog
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSwDbLog(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			swDbLogService.deleteSwDbLog(new String[]{id});
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