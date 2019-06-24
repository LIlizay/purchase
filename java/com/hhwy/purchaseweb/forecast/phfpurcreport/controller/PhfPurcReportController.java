package com.hhwy.purchaseweb.forecast.phfpurcreport.controller;

import com.hhwy.purchaseweb.forecast.phfpurcreport.service.PhfPurcReportService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfPurcReport;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import com.hhwy.purchaseweb.forecast.phfpurcreport.domain.PhfPurcReportVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfPurcReportController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfPurcReportController")
public class PhfPurcReportController {

	public static final Logger log = LoggerFactory.getLogger(PhfPurcReportController.class);
	
	/**
	 * phfPurcReportService
	 */
	@Autowired
	private PhfPurcReportService phfPurcReportService;
	
	
	/**
	 * 分页获取对象PhfPurcReport
	 * @param ID
	 * @return PhfPurcReport
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfPurcReportByPage(@RequestBody(required=false) PhfPurcReportVo phfPurcReportVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfPurcReport> queryResult = phfPurcReportService.getPhfPurcReportByPage(phfPurcReportVo);
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
	 * 根据ID获取对象PhfPurcReport
	 * @param ID
	 * @return PhfPurcReport
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfPurcReportById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfPurcReport phfPurcReport = phfPurcReportService.getPhfPurcReportById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfPurcReport);			//设置数据实体
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
	 * 添加对象PhfPurcReport
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfPurcReport(@RequestBody PhfPurcReport phfPurcReport) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfPurcReportService.savePhfPurcReport(phfPurcReport);
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
	 * 更新对象PhfPurcReport
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfPurcReport(@RequestBody PhfPurcReport phfPurcReport) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfPurcReportService.updatePhfPurcReport(phfPurcReport);
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
	 * 删除对象PhfPurcReport
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfPurcReport(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfPurcReportService.deletePhfPurcReport(new String[]{id});
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