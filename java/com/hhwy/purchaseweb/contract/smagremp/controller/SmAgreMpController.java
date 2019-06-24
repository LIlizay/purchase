package com.hhwy.purchaseweb.contract.smagremp.controller;

import java.util.List;
import java.util.Map;

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
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpInfoDetail;
import com.hhwy.purchaseweb.contract.smagremp.domain.SmAgreMpVo;
import com.hhwy.purchaseweb.contract.smagremp.service.SmAgreMpService;
import com.hhwy.selling.domain.SmAgreMp;

/**
 * SmAgreMpController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/smAgreMpController")
public class SmAgreMpController {

	public static final Logger log = LoggerFactory.getLogger(SmAgreMpController.class);
	
	/**
	 * smAgreMpService
	 */
	@Autowired
	private SmAgreMpService smAgreMpService;
	
	
	/**
	 * 获取对象集合ScMpInfo
	 * @param consId
	 * @return ScMpInfo
	 */
	@RequestMapping( value = "/getScMpInfoList", method = RequestMethod.POST)
	public List<SmAgreMpInfoDetail> getScMpInfoList(@RequestBody(required=false) Map<String,String> map) {
		List<SmAgreMpInfoDetail> queryResult = null;
		try {
			queryResult = smAgreMpService.getScMpInfoList(map);
		} catch (Exception e) {
			// TODO: handle exception
			log.error("分页查询列表失败",e);				//记录异常日志，根据实际情况修改
		}
		return queryResult;
	}
	
	/**
	 * 分页获取对象SmAgreMp
	 * @param ID
	 * @return SmAgreMp
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getSmAgreMpByPage(@RequestBody(required=false) SmAgreMpVo smAgreMpVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<SmAgreMp> queryResult = smAgreMpService.getSmAgreMpByPage(smAgreMpVo);
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
	 * 根据ID获取对象SmAgreMp
	 * @param ID
	 * @return SmAgreMp
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getSmAgreMpById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			SmAgreMp smAgreMp = smAgreMpService.getSmAgreMpById(id);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(smAgreMp);			//设置数据实体
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
	 * 添加对象SmAgreMp
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object saveSmAgreMp(@RequestBody SmAgreMp smAgreMp) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreMpService.saveSmAgreMp(smAgreMp);
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
	 * 更新对象SmAgreMp
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updateSmAgreMp(@RequestBody SmAgreMp smAgreMp) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreMpService.updateSmAgreMp(smAgreMp);
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
	 * 删除对象SmAgreMp
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deleteSmAgreMp(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			smAgreMpService.deleteSmAgreMp(new String[]{id});
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