package com.hhwy.purchaseweb.contract.smagresup.controller;

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
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupDetail;
import com.hhwy.purchaseweb.contract.smagresup.domain.SmAgreSupVo;
import com.hhwy.purchaseweb.contract.smagresup.service.SmAgreSupService;
import com.hhwy.selling.domain.SmAgreSup;

/**
 * SmAgreSupController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smAgreSupController")
public class SmAgreSupController {

	public static final Logger log = LoggerFactory.getLogger(SmAgreSupController.class);
	
	/**
	 * smAgreSupService
	 */
	@Autowired
	private SmAgreSupService smAgreSupService;
	
	
	/**
	 * 分页获取对象SmAgreSup
	 * @param ID
	 * @return SmAgreSup
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmAgreSupByPage(@RequestBody(required=false) SmAgreSupVo smAgreSupVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmAgreSupDetail> queryResult = smAgreSupService.getSmAgreSupByPage(smAgreSupVo);
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
	 * 根据ID获取对象SmAgreSup
	 * @param ID
	 * @return SmAgreSup
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmAgreSupById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmAgreSup smAgreSup = smAgreSupService.getSmAgreSupById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smAgreSup);			//设置数据实体
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
	 * 添加对象SmAgreSup
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmAgreSup(@RequestBody SmAgreSup smAgreSup) {
		ExecutionResult result = new ExecutionResult();
		try {
		    SmAgreSup query = smAgreSupService.saveSmAgreSup(smAgreSup);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(query);                      //设置返回结果实体
			result.setMsg("保存成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			log.error("保存失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setData(new SmAgreSup());            //设置返回结果实体
			result.setMsg("保存失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
		
	}
	
	/**
	 * 更新对象SmAgreSup
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmAgreSup(@RequestBody SmAgreSup smAgreSup) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreSupService.updateSmAgreSup(smAgreSup);
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
	 * 删除对象SmAgreSup 根据补充协议id
	 * @param id
	 */
	@RequestMapping( method = RequestMethod.DELETE)
	public Object deleteSmAgreSup(@RequestBody String[] ids) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreSupService.deleteBySupId(ids);
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