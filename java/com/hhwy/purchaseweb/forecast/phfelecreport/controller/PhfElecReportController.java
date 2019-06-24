package com.hhwy.purchaseweb.forecast.phfelecreport.controller;

import com.hhwy.purchaseweb.forecast.phfelecreport.service.PhfElecReportService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfElecReport;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import com.hhwy.purchaseweb.forecast.phfelecreport.domain.PhfElecReportVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfElecReportController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfElecReportController")
public class PhfElecReportController {

	public static final Logger log = LoggerFactory.getLogger(PhfElecReportController.class);
	
	/**
	 * phfElecReportService
	 */
	@Autowired
	private PhfElecReportService phfElecReportService;
	
	
	/**
	 * 分页获取对象PhfElecReport
	 * @param ID
	 * @return PhfElecReport
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfElecReportByPage(@RequestBody(required=false) PhfElecReportVo phfElecReportVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfElecReport> queryResult = phfElecReportService.getPhfElecReportByPage(phfElecReportVo);
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
	 * 根据ID获取对象PhfElecReport
	 * @param ID
	 * @return PhfElecReport
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfElecReportById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfElecReport phfElecReport = phfElecReportService.getPhfElecReportById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfElecReport);			//设置数据实体
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
	 * 添加对象PhfElecReport
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfElecReport(@RequestBody PhfElecReport phfElecReport) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfElecReportService.savePhfElecReport(phfElecReport);
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
	 * 更新对象PhfElecReport
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfElecReport(@RequestBody PhfElecReport phfElecReport) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfElecReportService.updatePhfElecReport(phfElecReport);
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
	 * 删除对象PhfElecReport
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfElecReport(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfElecReportService.deletePhfElecReport(new String[]{id});
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