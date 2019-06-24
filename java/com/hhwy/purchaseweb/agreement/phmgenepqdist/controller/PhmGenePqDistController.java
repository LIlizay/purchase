package com.hhwy.purchaseweb.agreement.phmgenepqdist.controller;

import com.hhwy.purchaseweb.agreement.phmgenepqdist.service.PhmGenePqDistService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import com.hhwy.purchase.domain.PhmGenePqDist;
import com.hhwy.purchaseweb.agreement.phmgenepqdist.domain.PhmGenePqDistVo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhmGenePqDistController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phmGenePqDistController")
public class PhmGenePqDistController {

	public static final Logger log = LoggerFactory.getLogger(PhmGenePqDistController.class);
	
	/**
	 * phmGenePqDistService
	 */
	@Autowired
	private PhmGenePqDistService phmGenePqDistService;
	
	
	/**
	 * 分页获取对象PhmGenePqDist
	 * @param ID
	 * @return PhmGenePqDist
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhmGenePqDistByPage(@RequestBody(required=false) PhmGenePqDistVo phmGenePqDistVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhmGenePqDist> queryResult = phmGenePqDistService.getPhmGenePqDistByPage(phmGenePqDistVo);
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
	 * 根据ID获取对象PhmGenePqDist
	 * @param ID
	 * @return PhmGenePqDist
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhmGenePqDistById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhmGenePqDist phmGenePqDist = phmGenePqDistService.getPhmGenePqDistById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phmGenePqDist);			//设置数据实体
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
	 * 添加对象PhmGenePqDist
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhmGenePqDist(@RequestBody PhmGenePqDist phmGenePqDist) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGenePqDistService.savePhmGenePqDist(phmGenePqDist);
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
	 * 更新对象PhmGenePqDist
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhmGenePqDist(@RequestBody PhmGenePqDist phmGenePqDist) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGenePqDistService.updatePhmGenePqDist(phmGenePqDist);
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
	 * 删除对象PhmGenePqDist
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhmGenePqDist(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phmGenePqDistService.deletePhmGenePqDist(new String[]{id});
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