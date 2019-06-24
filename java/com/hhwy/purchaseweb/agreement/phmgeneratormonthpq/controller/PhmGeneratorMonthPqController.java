package com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.controller;

import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.service.PhmGeneratorMonthPqService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;
import com.hhwy.purchase.domain.PhmGeneratorMonthPq;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqDetail;
import com.hhwy.purchaseweb.agreement.phmgeneratormonthpq.domain.PhmGeneratorMonthPqVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmGeneratorMonthPqController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmGeneratorMonthPqController")
public class PhmGeneratorMonthPqController {

	public static final Logger log = LoggerFactory.getLogger(PhmGeneratorMonthPqController.class);
	
	/**
	 * phmGeneratorMonthPqService
	 */
	@Autowired
	private PhmGeneratorMonthPqService phmGeneratorMonthPqService;
	
	
	/**
	 * 分页获取对象PhmGeneratorMonthPq
	 * @param ID
	 * @return PhmGeneratorMonthPq
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmGeneratorMonthPqByPage(@RequestBody(required=false) PhmGeneratorMonthPqVo phmGeneratorMonthPqVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmGeneratorMonthPqDetail> queryResult = phmGeneratorMonthPqService.getPhmGeneratorMonthPqByPage(phmGeneratorMonthPqVo);
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
	 * 根据ID获取对象PhmGeneratorMonthPq
	 * @param ID
	 * @return PhmGeneratorMonthPq
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmGeneratorMonthPqById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmGeneratorMonthPq phmGeneratorMonthPq = phmGeneratorMonthPqService.getPhmGeneratorMonthPqById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmGeneratorMonthPq);			//设置数据实体
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
	 * 添加对象PhmGeneratorMonthPq
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmGeneratorMonthPq(@RequestBody PhmGeneratorMonthPq phmGeneratorMonthPq) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGeneratorMonthPqService.savePhmGeneratorMonthPq(phmGeneratorMonthPq);
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
	 * 更新对象PhmGeneratorMonthPq
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmGeneratorMonthPq(@RequestBody PhmGeneratorMonthPq phmGeneratorMonthPq) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGeneratorMonthPqService.updatePhmGeneratorMonthPq(phmGeneratorMonthPq);
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
	 * 删除对象PhmGeneratorMonthPq
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmGeneratorMonthPq(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGeneratorMonthPqService.deletePhmGeneratorMonthPq(new String[]{id});
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