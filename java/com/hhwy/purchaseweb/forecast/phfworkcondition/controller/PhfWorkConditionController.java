package com.hhwy.purchaseweb.forecast.phfworkcondition.controller;

import com.hhwy.purchaseweb.forecast.phfworkcondition.service.PhfWorkConditionService;
import com.hhwy.framework.persistent.QueryResult;
import com.hhwy.purchase.domain.PhfWorkCondition;
import com.hhwy.business.core.modelutil.ExecutionResult;
import com.hhwy.business.core.modelutil.ReturnCode;

import com.hhwy.purchaseweb.forecast.phfworkcondition.domain.PhfWorkConditionVo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * PhfWorkConditionController
 * @author hhwy
 * @date 2016-09-24
 * @version 1.0
 */
@RestController
@RequestMapping("/phfWorkConditionController")
public class PhfWorkConditionController {

	public static final Logger log = LoggerFactory.getLogger(PhfWorkConditionController.class);
	
	/**
	 * phfWorkConditionService
	 */
	@Autowired
	private PhfWorkConditionService phfWorkConditionService;
	
	
	/**
	 * 分页获取对象PhfWorkCondition
	 * @param ID
	 * @return PhfWorkCondition
	 */
	@RequestMapping( value = "/page", method = RequestMethod.POST)
	public Object getPhfWorkConditionByPage(@RequestBody(required=false) PhfWorkConditionVo phfWorkConditionVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			QueryResult<PhfWorkCondition> queryResult = phfWorkConditionService.getPhfWorkConditionByPage(phfWorkConditionVo);
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
	 * 根据ID获取对象PhfWorkCondition
	 * @param ID
	 * @return PhfWorkCondition
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Object getPhfWorkConditionById(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			PhfWorkCondition phfWorkCondition = phfWorkConditionService.getPhfWorkConditionById(id);;
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(phfWorkCondition);			//设置数据实体
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
	 * 添加对象PhfWorkCondition
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.POST)
	public Object savePhfWorkCondition(@RequestBody PhfWorkCondition phfWorkCondition) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfWorkConditionService.savePhfWorkCondition(phfWorkCondition);
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
	 * 更新对象PhfWorkCondition
	 * @param 实体对象
	 */
	@RequestMapping( method = RequestMethod.PUT)
	public Object updatePhfWorkCondition(@RequestBody PhfWorkCondition phfWorkCondition) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfWorkConditionService.updatePhfWorkCondition(phfWorkCondition);
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
	 * 删除对象PhfWorkCondition
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Object deletePhfWorkCondition(@PathVariable String id) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfWorkConditionService.deletePhfWorkCondition(new String[]{id});
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
	
	/**
	 *  工况信息维护
	 */
	@RequestMapping(value = "/getWorkInfo/{ym}", method = RequestMethod.GET)
	public Object getWorkInfo(@PathVariable String ym) {
		ExecutionResult result = new ExecutionResult();
		try {
			List<PhfWorkCondition> list=phfWorkConditionService.getWorkInfo(ym);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setData(list);
			result.setMsg("查询成功！");					//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询失败",e);						//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("查询失败！");					//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
	
	/**
	 * 保存工况信息
	 */
	@RequestMapping( value = "/saveWorkInfo", method = RequestMethod.POST)
	public Object saveWorkInfo(@RequestBody(required=false) PhfWorkConditionVo phfWorkConditionVo) {
		ExecutionResult result = new ExecutionResult();
		try {
			phfWorkConditionService.saveWorkInfo(phfWorkConditionVo);
			result.setCode(ReturnCode.RES_SUCCESS);		//设置返回结果编码：成功
			result.setFlag(true);						//设置是否成功标识
			result.setMsg("保存成功！");			//设置返回消息，根据实际情况修改
		} catch (Exception e) {
			// TODO: handle exception
			log.error("保存失败",e);				//记录异常日志，根据实际情况修改
			result.setCode(ReturnCode.RES_FAILED);		//设置返回结果编码：失败
			result.setFlag(false);						//设置是否成功标识
			result.setMsg("保存失败！");			//设置返回消息，根据实际情况修改
			return result;
		}
		return result;
	}
}