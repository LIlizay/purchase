package com.hhwy.purchaseweb.bid.phmreportremind.controller;


import com.hhwy.purchaseweb.bid.phmreportremind.service.PhmReportRemindService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmReportRemind;
import com.hhwy.purchaseweb.bid.phmreportremind.domain.PhmReportRemindVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmReportRemindController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmReportRemindController")
public class PhmReportRemindController {

	public static final Logger log = LoggerFactory.getLogger(PhmReportRemindController.class);
	
	/**
	 * phmReportRemindService
	 */
	@Autowired
	private PhmReportRemindService phmReportRemindService;
	
	
	/**
	 * 分页获取对象PhmReportRemind
	 * @param ID
	 * @return PhmReportRemind
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmReportRemindByPage(@RequestBody(required=false) PhmReportRemindVo phmReportRemindVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmReportRemind> queryResult = phmReportRemindService.getPhmReportRemindByPage(phmReportRemindVo);
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
	 * 根据ID获取对象PhmReportRemind
	 * @param ID
	 * @return PhmReportRemind
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmReportRemindById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmReportRemind phmReportRemind = phmReportRemindService.getPhmReportRemindById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmReportRemind);			//设置数据实体
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
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
	 * 添加对象PhmReportRemind
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmReportRemind(@RequestBody PhmReportRemind phmReportRemind) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmReportRemindService.savePhmReportRemind(phmReportRemind);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象PhmReportRemind
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmReportRemind(@RequestBody PhmReportRemind phmReportRemind) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmReportRemindService.updatePhmReportRemind(phmReportRemind);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("更新成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("更新失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("更新失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 删除对象PhmReportRemind
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmReportRemind(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmReportRemindService.deletePhmReportRemind(new String[]{id});
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("删除成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("删除失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("删除失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}	
}